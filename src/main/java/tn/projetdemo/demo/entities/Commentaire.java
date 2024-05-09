package tn.projetdemo.demo.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
public class Commentaire {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;
	private String body;

	@Temporal(TemporalType.DATE)
	private Date created;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idevenement")
	private Evenement evenement;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_article")
	private Article article;
	
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_actualite")
	private Actualite actualite;

	public Commentaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commentaire(Long commentId, String body, Date created, Evenement evenement, Article article,
			Actualite actualite) {
		super();
		this.commentId = commentId;
		this.body = body;
		this.created = created;
		this.evenement = evenement;
		this.article = article;
		this.actualite = actualite;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Evenement getEvenement() {
		return evenement;
	}

	public void setEvenement(Evenement evenement) {
		this.evenement = evenement;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Actualite getActualite() {
		return actualite;
	}

	public void setActualite(Actualite actualite) {
		this.actualite = actualite;
	}

	@Override
	public String toString() {
		return "Commentaire [commentId=" + commentId + ", body=" + body + ", created=" + created + ", evenement="
				+ evenement + ", article=" + article + ", actualite=" + actualite + "]";
	}

	
	




}
