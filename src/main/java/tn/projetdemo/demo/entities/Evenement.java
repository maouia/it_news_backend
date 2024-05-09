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
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idevenement;
    private String titre;
    private String description;

    @Temporal(TemporalType.DATE)
    private Date created;
    private String lieu;
    private String imageURL;

    
    
    
    @JsonIgnore
    @ManyToMany(mappedBy = "evenement")
    private Set<User> user = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "evenement", cascade = CascadeType.ALL)
    private Set<Commentaire> commentaires = new HashSet<>();

    public Evenement() {
        super();
    }

	public Evenement(Long idevenement, String titre, String description, Date created, String lieu, String imageURL,
			Set<User> user, Set<Commentaire> commentaires) {
		super();
		this.idevenement = idevenement;
		this.titre = titre;
		this.description = description;
		this.created = created;
		this.lieu = lieu;
		this.imageURL = imageURL;
		this.user = user;
		this.commentaires = commentaires;
	}

	public Long getIdevenement() {
		return idevenement;
	}

	public void setIdevenement(Long idevenement) {
		this.idevenement = idevenement;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
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
		return "Evenement [idevenement=" + idevenement + ", titre=" + titre + ", description=" + description
				+ ", created=" + created + ", lieu=" + lieu + ", imageURL=" + imageURL + ", user=" + user
				+ ", commentaires=" + commentaires + "]";
	}

	
   
}
