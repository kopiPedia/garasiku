package com.koped.repository;
import com.koped.model.Cart;
import com.koped.model.User;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
        List<Cart> findAllByUser(String user);
}