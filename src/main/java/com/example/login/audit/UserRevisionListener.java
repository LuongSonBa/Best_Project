package com.example.login.audit;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.login.entity.Audit;

public class UserRevisionListener implements RevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
	    Audit rev = (Audit) revisionEntity;

	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	    if (auth == null || !auth.isAuthenticated()
	        || auth instanceof AnonymousAuthenticationToken) {
	        rev.setUsername("system");
	    } else {
	        rev.setUsername(auth.getName());
	    }
	}

}
