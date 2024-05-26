package com.koped.repository;
import com.koped.model.Cart;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
        List<Cart> findAllByUsername(String username);
        Cart findCartById(long id);
        Cart findByProductIdAndUsername(String productId, String username);
        void deleteByProductId(String ProductId);
}