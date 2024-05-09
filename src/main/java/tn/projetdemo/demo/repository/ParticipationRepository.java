package tn.projetdemo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.projetdemo.demo.entities.Participation;
@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {

}
