package tn.projetdemo.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.projetdemo.demo.entities.Evenement;




@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {
	boolean existsByTitre(String titre);
	Evenement findByTitre(String titre);
	@Query(value = "select * from evenement e where e.titre like :cle%" , nativeQuery = true)
	List<Evenement>getEvenementsSWST(@Param ("cle")String ch);
	List<Evenement> findByUserId(Long userId);
	

}
