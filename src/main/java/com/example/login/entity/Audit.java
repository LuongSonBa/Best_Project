package com.example.login.entity;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import com.example.login.audit.UserRevisionListener;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "revinfo")
@RevisionEntity(UserRevisionListener.class)
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @RevisionNumber
    private int rev;

    @RevisionTimestamp
    private long revtstmp;

    private String username;

    public int getRev() {
        return rev;
    }

    public long getRevtstmp() {
        return revtstmp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
