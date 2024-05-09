package tn.projetdemo.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import tn.projetdemo.demo.entities.Article;

import tn.projetdemo.demo.services.ArticleServiceImpl;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "*")
public class ArticleController {
	@Autowired
	ArticleServiceImpl articleserv;
	
	@PostMapping(value ="/addArticle/{userId}")
	public Article addArticle (@PathVariable Long userId,@RequestBody Article article) {
		return articleserv.addArticle(userId,article);
	}
	@PostMapping(value ="/addListArticle")
	public List<Article> addListArticle (@RequestBody List<Article> listarticle) {
		return articleserv.addListArticle(listarticle);
	}
	@PutMapping(value ="/updateArticle/{id_article}")
	public Article updateArticle (@PathVariable Long id_article,@RequestBody Article article) {
		return articleserv.updateArticle(id_article,article);
	}
	@DeleteMapping(value="/deleteArticle/{id_article}")
	public void deleteArticle(@PathVariable Long id_article) {
		articleserv.deleteArticle(id_article);
	}
	@GetMapping(value="/getAllArticles")
	public List<Article> getAllArticles(){
		return articleserv.getListArticles();	
	}
	@GetMapping(value="/getArticleByTitre/{t}")
	public Article getArticleByTitre(@PathVariable String t){
		return articleserv.getByTitre(t);	
	}
	@GetMapping(value="/getArticlesSWST/{t}")
	public List<Article> getArticlesSWST(@PathVariable String t){
		return articleserv.getArticlesSWST(t);	
	}
	@GetMapping(value="/getArticleById/{id_article}")
	public Article getArticleById(@PathVariable Long id_article) {
	    return articleserv.getArticleById(id_article);
	}

}
