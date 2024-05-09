package tn.projetdemo.demo.services;



import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.projetdemo.demo.entities.Evenement;

import tn.projetdemo.demo.entities.User;
import tn.projetdemo.demo.repository.EvenementRepository;
import tn.projetdemo.demo.repository.ParticipationRepository;
import tn.projetdemo.demo.repository.UserRepository;


@Service
public class EvenementServiceImpl implements EvenementServiceInter {
	@Autowired
	EvenementRepository evenementRep;
	@Autowired
	UserRepository userRep;
	 @Autowired
	    ParticipationRepository participationRepository;

	    @Override
	    public Evenement addevenement(Long userId, Evenement evenement) {
	        User user = userRep.getUserById(userId);
	        if (user != null) {
	            Set<User> users = new HashSet<>();
	            users.add(user);
	            evenement.setUser(users);
	            evenement.setCreated(new Date());
	            return evenementRep.save(evenement);
	        } else {
	            throw new IllegalArgumentException("User Not found");
	        }
	    }


	@Override
	public List<Evenement> addListEvenement(List<Evenement> listevenement) {
		// TODO Auto-generated method stub
		return evenementRep.saveAll(listevenement) ;
	}

	@Override
	public String addevenementWTT(Evenement evenement) {
		// TODO Auto-generated method stub
		String ch="";
		if (evenementRep.existsByTitre(evenement.getTitre())) {
			ch="title already exists";
		}else {
			evenementRep.save(evenement);
			ch="event added successfuly";
		}
		return ch;
		
	}

	@Override
	public Evenement updateEvenement(Long idevenement, Evenement evenement) {
		// TODO Auto-generated method stub
		Evenement evnt= evenementRep.findById(idevenement).get();
		evnt.setTitre(evenement.getTitre());
		evnt.setDescription(evenement.getDescription());
		return evenementRep.save(evnt);
	}

	@Override
	public void deleteEvenement(Long idevenement) {
		// TODO Auto-generated method stub
		evenementRep.deleteById(idevenement);
		
	}

	@Override
	public List<Evenement> getListEvenements() {
		// TODO Auto-generated method stub
		return evenementRep.findAll();
	}

	@Override
	public Evenement getByTitre(String titre) {
		// TODO Auto-generated method stub
		return evenementRep.findByTitre(titre);
	}

	@Override
	public List<Evenement> getEvenementsSWST(String ch) {
		// TODO Auto-generated method stub
		return evenementRep.getEvenementsSWST(ch);
	}

	@Override
	public List<Evenement> getEvenementsByUserId(Long userId) {
		// TODO Auto-generated method stub
		return evenementRep.findByUserId(userId);
	}


	@Override
	public Evenement getEvenementById(Long idevenement) {
		// TODO Auto-generated method stub
		Optional<Evenement> evenement = evenementRep.findById(idevenement);
        if (evenement.isPresent()) {
            return evenement.get();
            
	}else {
        throw new  NoSuchElementException("Événement non trouvé avec l'ID : " + idevenement);
    }
        
        
        
        

	
	
    

	
	

	

	

	
	
	

}}