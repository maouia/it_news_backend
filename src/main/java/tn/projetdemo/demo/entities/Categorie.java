package tn.projetdemo.demo.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Categorie {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ;
	private String nomCategorie;
	public Categorie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Categorie(Long id, String nomCategorie) {
		super();
		this.id = id;
		this.nomCategorie = nomCategorie;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomCategorie() {
		return nomCategorie;
	}
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
	@Override
	public String toString() {
		return "Categorie [id=" + id + ", nomCategorie=" + nomCategorie + "]";
	}
	

}
