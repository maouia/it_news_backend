package tn.projetdemo.demo.services;

import java.util.List;
import java.util.NoSuchElementException;

import tn.projetdemo.demo.entities.Commentaire;


public interface CommentaireServiceInter {
	public Commentaire addCommentaire (Long userId, Long idevenement, Commentaire commentaire);
	public Commentaire addCommentairee (Long userId, Long id_article, Commentaire commentaire);
	public Commentaire addCommentaireee (Long userId, Long id_actualite, Commentaire commentaire);
	public List<Commentaire> getListCommentaires();
	 Commentaire updateCommentaire(Long commentaireId, Commentaire newCommentaire);
	 void deleteCommentaire(Long commentaireId);
	 public Commentaire getCommentaireById(Long commentaireId) throws NoSuchElementException;

}
