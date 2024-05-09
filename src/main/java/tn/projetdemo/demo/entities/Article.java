package tn.projetdemo.demo.entities;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity

public class Article {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id_article ;
	private String titre ;
	private String contenu ;
	@Temporal(TemporalType.DATE)
	private Date created;
	private String imageURL;
	
	@ManyToMany(mappedBy = "article")
	private Set<User> user = new HashSet<>();
	
	@JsonIgnore
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private Set<Commentaire> commentaires = new HashSet<>();
	
	
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Article(Long id_article, String titre, String contenu, Date created, String imageURL, Set<User> user,
			Set<Commentaire> commentaires) {
		super();
		this.id_article = id_article;
		this.titre = titre;
		this.contenu = contenu;
		this.created = created;
		this.imageURL = imageURL;
		this.user = user;
		this.commentaires = commentaires;
	}


	public Long getId_article() {
		return id_article;
	}


	public void setId_article(Long id_article) {
		this.id_article = id_article;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getContenu() {
		return contenu;
	}


	public void setContenu(String contenu) {
		this.contenu = contenu;
	}


	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	public String getImageURL() {
		return imageURL;
	}


	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}


	public Set<User> getUser() {
		return user;
	}


	public void setUser(Set<User> user) {
		this.user = user;
	}


	public Set<Commentaire> getCommentaires() {
		return commentaires;
	}


	public void setCommentaires(Set<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}


	@Override
	public String toString() {
		return "Article [id_article=" + id_article + ", titre=" + titre + ", contenu=" + contenu + ", created="
				+ created + ", imageURL=" + imageURL + ", user=" + user + ", commentaires=" + commentaires + "]";
	}


	


	
	

}
