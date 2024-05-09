package tn.projetdemo.demo.services;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.projetdemo.demo.entities.Article;
import tn.projetdemo.demo.entities.User;
import tn.projetdemo.demo.repository.ArticleRepository;
import tn.projetdemo.demo.repository.UserRepository;

@Service
public class ArticleServiceImpl implements ArticleServiceInter{
	
	@Autowired
	ArticleRepository articleRep;
	@Autowired
	UserRepository userRep;

	@Override
	public Article addArticle(Long userId,Article article) {
		// TODO Auto-generated method stub
		 User user = userRep.getUserById(userId);
	        if (user != null) {
	            Set<User> users = new HashSet<>();
	            users.add(user);
	            article.setUser(users);
	            article.setCreated(new Date());
	            return articleRep.save(article);
	        } else {
	            throw new IllegalArgumentException("User Not found");
	        }
	}

	@Override
	public List<Article> addListArticle(List<Article> listarticle) {
		// TODO Auto-generated method stub
		return articleRep.saveAll(listarticle) ;
	}

	@Override
	public Article updateArticle(Long id_article, Article article) {
		// TODO Auto-generated method stub
		Article art = articleRep.findById(id_article).get();
		art.setTitre(article.getTitre());
		art.setContenu(article.getContenu());
		article.setCreated(new Date());
		return articleRep.save(article);
	}

	@Override
	public void deleteArticle(Long id_article) {
		// TODO Auto-generated method stub
		articleRep.deleteById(id_article);
		
	}

	@Override
	public List<Article> getListArticles() {
		// TODO Auto-generated method stub
		return articleRep.findAll();
	}

	@Override
	public Article getByTitre(String titre) {
		// TODO Auto-generated method stub
		return articleRep.findByTitre(titre);
	}

	@Override
	public List<Article> getArticlesSWST(String ch) {
		// TODO Auto-generated method stub
		return articleRep.getArticlesSWST(ch);
	}
	
	
	@Override

	public Article getArticleById(Long id_article) {
		// TODO Auto-generated method stub
		Optional<Article> article = articleRep.findById(id_article);
        if (article.isPresent()) {
            return article.get();
            
	}else {
        throw new  NoSuchElementException("Événement non trouvé avec l'ID : " + id_article);

	

	

}}}
