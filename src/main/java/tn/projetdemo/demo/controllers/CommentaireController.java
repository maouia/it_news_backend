package tn.projetdemo.demo.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.projetdemo.demo.entities.Commentaire;

import tn.projetdemo.demo.services.CommentaireServiceImpl;

@RestController
@CrossOrigin(origins = "*")
public class CommentaireController {
	@Autowired
	CommentaireServiceImpl commentaireserv;
	
	@PostMapping(value ="/addCommentaire/user/{userId}/evenement/{idevenement}")
	public Commentaire addCommentaire (@PathVariable Long userId,@PathVariable Long idevenement,@RequestBody Commentaire commentaire) {
		return commentaireserv.addCommentaire(userId,idevenement,commentaire);
	}
	
	
	 @PutMapping(value = "/updateCommentaire/{commentaireId}")
	    public Commentaire updateCommentaire(@PathVariable Long commentaireId, @RequestBody Commentaire commentaire) {
	        return commentaireserv.updateCommentaire(commentaireId, commentaire);
	    }
	 
	 
	  @DeleteMapping(value = "/deleteCommentaire/{commentaireId}")
	    public void deleteCommentaire(@PathVariable Long commentaireId) {
	        commentaireserv.deleteCommentaire(commentaireId);
	    }
	 
	 
	@PostMapping(value ="/addCommentaire/user/{userId}/article/{id_article}")
	public Commentaire addCommentairee (@PathVariable Long userId,@PathVariable Long id_article,@RequestBody Commentaire commentaire) {
		return commentaireserv.addCommentaire(userId,id_article,commentaire);
	}
	@PostMapping(value ="/addCommentaire/user/{userId}/actualite/{id_actualite}")
	public Commentaire addCommentaireee (@PathVariable Long userId,@PathVariable Long id_actualite,@RequestBody Commentaire commentaire) {
		return commentaireserv.addCommentaire(userId,id_actualite,commentaire);
	}
	
	@GetMapping(value="/getAllComments")
	public List<Commentaire> getAllCommentaires(){
	return commentaireserv.getListCommentaires();
	}
	
	@GetMapping(value="/getCommentaireById/{commentaireId}")
	public Commentaire getCommentaireById(@PathVariable Long commentaireId) {
	    return commentaireserv.getCommentaireById(commentaireId);
	}
}
