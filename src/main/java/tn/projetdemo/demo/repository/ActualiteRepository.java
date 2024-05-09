package tn.projetdemo.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.projetdemo.demo.entities.Actualite;




@Repository
public interface ActualiteRepository extends JpaRepository<Actualite, Long> {
	Actualite findByTitre(String titre);
	@Query(value = "select * from actualite a where a.titre like :cle%" , nativeQuery = true)
	List<Actualite> getActualitésSWSA(@Param("cle")String ch);

}
