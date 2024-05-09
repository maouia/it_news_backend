package tn.projetdemo.demo.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom_ut;
    private String prenom_ut;
    @Column(unique=true)
    private String username;
    
    private String pass_ut;
    private String confirm_pass;
    @Column(unique=true)
    private String email;
    private String adresse;
    @Temporal(TemporalType.DATE)
    private Date created;
    private String rolename;
    private boolean isEnabled;
    

    @ManyToOne
	private Image image;


    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "participation", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "idevenement"))
    private Set<Evenement> evenement = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "features", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "id_article"))
    private Set<Article> article = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "news", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "id_actualite"))
    private Set<Actualite> actualite = new HashSet<>();
    
   ; 
    
    public User() {
        super();
    }

	public User(Long id, String nom_ut, String prenom_ut, String username, String pass_ut, String confirm_pass,
			String email, String adresse, Date created, String rolename, boolean isEnabled,
			Set<Evenement> evenement, Set<Article> article, Set<Actualite> actualite) {
		super();
		this.id = id;
		this.nom_ut = nom_ut;
		this.prenom_ut = prenom_ut;
		this.username = username;
		this.pass_ut = pass_ut;
		this.confirm_pass = confirm_pass;
		this.email = email;
		this.adresse = adresse;
		this.created = created;
		this.rolename = rolename;
		this.isEnabled = isEnabled;
		this.evenement = evenement;
		this.article = article;
		this.actualite = actualite;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom_ut() {
		return nom_ut;
	}

	public void setNom_ut(String nom_ut) {
		this.nom_ut = nom_ut;
	}

	public String getPrenom_ut() {
		return prenom_ut;
	}

	public void setPrenom_ut(String prenom_ut) {
		this.prenom_ut = prenom_ut;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass_ut() {
		return pass_ut;
	}

	public void setPass_ut(String pass_ut) {
		this.pass_ut = pass_ut;
	}

	public String getConfirm_pass() {
		return confirm_pass;
	}

	public void setConfirm_pass(String confirm_pass) {
		this.confirm_pass = confirm_pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Set<Evenement> getEvenement() {
		return evenement;
	}

	public void setEvenement(Set<Evenement> evenement) {
		this.evenement = evenement;
	}

	public Set<Article> getArticle() {
		return article;
	}

	public void setArticle(Set<Article> article) {
		this.article = article;
	}

	public Set<Actualite> getActualite() {
		return actualite;
	}

	public void setActualite(Set<Actualite> actualite) {
		this.actualite = actualite;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nom_ut=" + nom_ut + ", prenom_ut=" + prenom_ut + ", username=" + username
				+ ", pass_ut=" + pass_ut + ", confirm_pass=" + confirm_pass + ", email=" + email + ", adresse="
				+ adresse + ", created=" + created + ", rolename=" + rolename
				+ ", isEnabled=" + isEnabled + ", evenement=" + evenement + ", article=" + article + ", actualite="
				+ actualite + "]";
	}

	

	
	
    

	
    

   
}