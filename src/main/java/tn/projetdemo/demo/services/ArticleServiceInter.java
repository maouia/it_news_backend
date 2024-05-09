package tn.projetdemo.demo.services;

import java.util.List;
import java.util.NoSuchElementException;

import tn.projetdemo.demo.entities.Article;


public interface ArticleServiceInter {
	public Article addArticle (Long userId,Article article);
	public List<Article> addListArticle(List<Article> listarticle);
	public Article updateArticle(Long id_article , Article article);
	public void deleteArticle(Long id_article);
	public List<Article> getListArticles();
	public Article getByTitre(String titre);
	public List<Article> getArticlesSWST(String ch);
	public Article getArticleById(Long id_article) throws NoSuchElementException;

}
