package tn.projetdemo.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "participation")
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_evenement")
    private Evenement evenement;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Participation() {
    }

    public Participation(Evenement evenement, User user) {
        this.evenement = evenement;
        this.user = user;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
