package tn.projetdemo.demo.services;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.projetdemo.demo.entities.Actualite;
import tn.projetdemo.demo.entities.User;
import tn.projetdemo.demo.repository.ActualiteRepository;
import tn.projetdemo.demo.repository.UserRepository;


@Service
public class ActualiteServiceImpl implements ActualiteServiceInter {
	@Autowired
	ActualiteRepository actRep;
	@Autowired
	UserRepository userRep;

	@Override
	public Actualite addActualite(Long userId,Actualite actualite) {
		User user = userRep.getUserById(userId);
        if (user != null) {
            Set<User> users = new HashSet<>();
            users.add(user);
            actualite.setUser(users);
            actualite.setCreated(new Date());
            return actRep.save(actualite);
        } else {
            throw new IllegalArgumentException("User Not found");
        }
	}

	@Override
	public List<Actualite> addListActualite(List<Actualite> listactualite) {
		// TODO Auto-generated method stub
		return actRep.saveAll(listactualite);
	}

	@Override
	public Actualite updateActualite(Long id, Actualite actualite) {
		// TODO Auto-generated method stub
		Actualite act =actRep.findById(id).orElseThrow(() -> new NoSuchElementException("Actualité non trouvée"));;
		
		act.setTitre(actualite.getTitre());
		act.setContenu(act.getContenu());
		actualite.setCreated(new Date());
		return actRep.save(act);
	}

	@Override
	public void deleteActualite(Long id) {
	    // Vérifier si l'actualité existe
	    Optional<Actualite> optionalActualite = actRep.findById(id);
	    if (optionalActualite.isPresent()) {
	        // Si l'actualité existe, la supprimer
	        actRep.deleteById(id);
	    } else {
	        // Si l'actualité n'existe pas, lever une exception
	        throw new NoSuchElementException("L'actualité avec l'ID " + id + " n'a pas été trouvée.");
	    }
	}


	@Override
	public List<Actualite> getListActualites() {
		// TODO Auto-generated method stub
		return actRep.findAll();
	}

	@Override
	public Actualite getByTitre(String titre) {
		// TODO Auto-generated method stub
		return actRep.findByTitre(titre);
	}

	@Override
	public List<Actualite> getActualitesSWSA(String ch) {
		// TODO Auto-generated method stub
		return actRep.getActualitésSWSA(ch);
	}

	@Override
	public Actualite getActualiteById(Long id_actualite) {
		// TODO Auto-generated method stub
		Optional<Actualite> actualite = actRep.findById(id_actualite);
        if (actualite.isPresent()) {
            return actualite.get();
            
	}else {
        throw new  NoSuchElementException("Événement non trouvé avec l'ID : " + id_actualite);
    }

	

}

	@Override
	public List<Actualite> getAllPublicActualites() {
		// TODO Auto-generated method stub
		return actRep.findAll();
	}
	

}
