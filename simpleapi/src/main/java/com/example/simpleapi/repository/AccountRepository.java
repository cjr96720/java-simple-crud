package com.example.simpleapi.repository;

import com.example.simpleapi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// JpaRepository<T, ID>
// T being your entities
// ID being the primary key of your entities
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // define a custom JPQL (Java Persistence Query Language)
    @Query("SELECT a FROM Account a WHERE a.email = ?1")
    // Optional: a container object that may or may not contain a non-null value
    Optional<Account> findAccountByEmail(String email);
}
