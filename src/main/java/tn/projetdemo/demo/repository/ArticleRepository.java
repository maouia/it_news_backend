package tn.projetdemo.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.projetdemo.demo.entities.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
	Article findByTitre (String titre);
	@Query(value = "select * from article a where a.titre like :cle%" , nativeQuery = true)
	List<Article> getArticlesSWST(@Param("cle")String ch);

}
