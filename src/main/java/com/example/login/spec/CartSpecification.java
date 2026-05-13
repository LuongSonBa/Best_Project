package com.example.login.spec;

import com.example.login.entity.Cart;
import com.example.login.entity.CartItem;
import com.example.login.entity.CartItem_;
import com.example.login.entity.Cart_;
import com.example.login.entity.User_;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class CartSpecification {

    public static Specification<Cart> getCartWithDetails(String username) {
        return (root, query, cb) -> {
            if (Long.class != query.getResultType()) {
                // Fetch sâu: Cart -> Items -> Computer
                root.fetch(Cart_.cartItems, JoinType.LEFT)
                    .fetch(CartItem_.computer, JoinType.LEFT);
            }
            return cb.equal(root.get(Cart_.user).get(User_.username), username);
        };
    }

    public static Specification<CartItem> getItemsByUsername(String username) {
        return (root, query, cb) -> {
            if (Long.class != query.getResultType()) {
                root.fetch(CartItem_.computer, JoinType.LEFT);
            }
            return cb.equal(root.get(CartItem_.cart).get(Cart_.user).get(User_.username), username);
        };
    }
}