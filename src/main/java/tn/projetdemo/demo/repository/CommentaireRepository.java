package tn.projetdemo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.projetdemo.demo.entities.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {

}
