package tn.projetdemo.demo.services;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.projetdemo.demo.entities.Actualite;
import tn.projetdemo.demo.entities.Article;
import tn.projetdemo.demo.entities.Commentaire;
import tn.projetdemo.demo.entities.Evenement;
import tn.projetdemo.demo.entities.User;
import tn.projetdemo.demo.repository.ActualiteRepository;
import tn.projetdemo.demo.repository.ArticleRepository;
import tn.projetdemo.demo.repository.CommentaireRepository;
import tn.projetdemo.demo.repository.EvenementRepository;
import tn.projetdemo.demo.repository.UserRepository;
@Service
public class CommentaireServiceImpl implements CommentaireServiceInter {
	@Autowired
	CommentaireRepository commentaireRep;
	
	@Autowired
	EvenementRepository evenementRep;
	
	@Autowired
	ArticleRepository articleRep;
	
	@Autowired
	ActualiteRepository actRep;
	
	@Autowired
	UserRepository userRep;

	public Commentaire addCommentaire(Long userId, Long idevenement, Commentaire commentaire) {

        User user=userRep.getUserById(userId);
        if(user!=null)
        {
            Optional<Evenement> optionalevenement=evenementRep.findById(idevenement);
            if(optionalevenement.isPresent())
            {
            	Evenement evenement=optionalevenement.get();
                commentaire.setEvenement(evenement);


                commentaire.setCreated(new Date());

            return commentaireRep.save(commentaire);

            }else{
                throw new IllegalArgumentException("ERROR");
            }

        }else{
            throw new IllegalArgumentException("ERROR");
        }
		// TODO Auto-generated method stub
	
	}
	
	
	public Commentaire addCommentairee(Long userId, Long id_article, Commentaire commentaire) {

        User user=userRep.getUserById(userId);
        if(user!=null)
        {
            Optional<Article> optionalarticle=articleRep.findById(id_article);
            if(optionalarticle.isPresent())
            {
            	Article article=optionalarticle.get();
                commentaire.setArticle(article);


                commentaire.setCreated(new Date());

            return commentaireRep.save(commentaire);

            }else{
                throw new IllegalArgumentException("ERROR");
            }

        }else{
            throw new IllegalArgumentException("ERROR");
        }
		// TODO Auto-generated method stub
	
	}
	
	
	public Commentaire addCommentaireee(Long userId, Long id_actualite, Commentaire commentaire) {

        User user=userRep.getUserById(userId);
        if(user!=null)
        {
            Optional<Actualite> optionalactualite=actRep.findById(id_actualite);
            if(optionalactualite.isPresent())
            {
            	Actualite actualite=optionalactualite.get();
                commentaire.setActualite(actualite);


                commentaire.setCreated(new Date());

            return commentaireRep.save(commentaire);

            }else{
                throw new IllegalArgumentException("ERROR");
            }

        }else{
            throw new IllegalArgumentException("ERROR");
        }
		// TODO Auto-generated method stub
	
	}
	 @Override
	    public Commentaire updateCommentaire(Long commentaireId, Commentaire newCommentaire) {
	        Optional<Commentaire> optionalCommentaire = commentaireRep.findById(commentaireId);
	        if (optionalCommentaire.isPresent()) {
	            Commentaire existingCommentaire = optionalCommentaire.get();
	            // Mise à jour des champs du commentaire existant avec les nouvelles valeurs
	            existingCommentaire.setBody(newCommentaire.getBody());
	            existingCommentaire.setCreated(newCommentaire.getCreated());
	            // Mettre à jour les relations avec d'autres entités si nécessaire
	            existingCommentaire.setEvenement(newCommentaire.getEvenement());
	            existingCommentaire.setArticle(newCommentaire.getArticle());
	            existingCommentaire.setActualite(newCommentaire.getActualite());
	            return commentaireRep.save(existingCommentaire);
	        } else {
	            throw new IllegalArgumentException("Commentaire not found with ID: " + commentaireId);
	        }
	    }
	 
	 @Override
	    public void deleteCommentaire(Long commentaireId) {
	        Optional<Commentaire> optionalCommentaire = commentaireRep.findById(commentaireId);
	        if (optionalCommentaire.isPresent()) {
	            commentaireRep.deleteById(commentaireId);
	        } else {
	            throw new IllegalArgumentException("Commentaire not found with ID: " + commentaireId);
	        }
	    }

	public List<Commentaire> getListCommentaires() {
		// TODO Auto-generated method stub
		return commentaireRep.findAll();
	}
	
	@Override
	public Commentaire getCommentaireById(Long commentaireId) {
		// TODO Auto-generated method stub
		Optional<Commentaire> commentaire = commentaireRep.findById(commentaireId);
        if (commentaire.isPresent()) {
            return commentaire.get();
            
	}else {
        throw new  NoSuchElementException("Événement non trouvé avec l'ID : " + commentaireId);
    }

}}
