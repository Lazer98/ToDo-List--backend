package com.springbootPostGresql.repository;




import com.springbootPostGresql.entity.User1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User1, Long> {
    // additional methods if needed
}
