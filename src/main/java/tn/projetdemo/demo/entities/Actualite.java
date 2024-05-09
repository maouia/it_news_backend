package tn.projetdemo.demo.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;




@Entity
public class Actualite {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id_actualite ;
	private String titre;
	private String contenu;
	@Temporal(TemporalType.DATE)
	private Date created;
	private String imageURL;
	
	
	@ManyToMany(mappedBy = "actualite")
	private Set<User> user = new HashSet<>();
	
	   @JsonIgnore
	   @OneToMany(mappedBy = "actualite", cascade = CascadeType.ALL)
	   private Set<Commentaire> commentaires = new HashSet<>();

	public Actualite() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Actualite(Long id_actualite, String titre, String contenu, Date created, String imageURL, Set<User> user,
			Set<Commentaire> commentaires) {
		super();
		this.id_actualite = id_actualite;
		this.titre = titre;
		this.contenu = contenu;
		this.created = created;
		this.imageURL = imageURL;
		this.user = user;
		this.commentaires = commentaires;
	}

	public Long getId_actualite() {
		return id_actualite;
	}

	public void setId_actualite(Long id_actualite) {
		this.id_actualite = id_actualite;
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
		return "Actualite [id_actualite=" + id_actualite + ", titre=" + titre + ", contenu=" + contenu + ", created="
				+ created + ", imageURL=" + imageURL + ", user=" + user + ", commentaires=" + commentaires + "]";
	}

	

	

}
