package tn.projetdemo.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.projetdemo.demo.entities.EmailConfirmationToken;

@Repository
public interface ConfirmationTokenRepository extends CrudRepository<EmailConfirmationToken, Long> {
    EmailConfirmationToken findByConfirmationToken(String confirmationToken);
}


