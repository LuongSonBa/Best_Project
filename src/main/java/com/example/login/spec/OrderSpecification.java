package com.example.login.spec;

import com.example.login.entity.Order;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification {

    /**
     * Lọc đơn hàng theo username của người sở hữu
     */
    public static Specification<Order> hasUsername(String username) {
        return (root, query, cb) -> 
            cb.equal(root.get("user").get("username"), username);
    }

    /**
     * Lọc đơn hàng theo trạng thái (Ví dụ: PENDING, DONE)
     */
    public static Specification<Order> hasStatus(String status) {
        return (root, query, cb) -> 
            cb.equal(root.get("status"), status);
    }
}