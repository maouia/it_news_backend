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

import tn.projetdemo.demo.entities.Evenement;
import tn.projetdemo.demo.services.EvenementServiceImpl;



@RestController

@CrossOrigin(origins = "*")
public class EvenementController {
	@Autowired
	EvenementServiceImpl evenementServ;
	
	@PostMapping(value="/addEvenement/{userId}")
	public Evenement addEvenement(@PathVariable Long userId,@RequestBody Evenement evenement) {
		return evenementServ.addevenement(userId,evenement);
		
	}
	@PostMapping(value = "/addListEvenement")
    public List<Evenement> addListEvenement(@RequestBody List<Evenement> listevenement){
    	return evenementServ.addListEvenement(listevenement);
    }
	@PostMapping(value="/addEvenementWTT")
	public String addEvenementWTT(@RequestBody Evenement evenement) {
		return evenementServ.addevenementWTT(evenement);
		
	}
	@PutMapping(value="/updateEvenement/{idevenement}")
	public Evenement updateEvenement(@PathVariable Long idevenement,@RequestBody Evenement evenement) {
		return evenementServ.updateEvenement(idevenement,evenement);
		
	}
	@DeleteMapping(value="/deleteEvenement/{idevenement}")
	public void deleteEvenement(@PathVariable Long idevenement) {
		evenementServ.deleteEvenement(idevenement);
	}
	@GetMapping(value="/getAllEvenements")
	public List <Evenement> getAllEvenements(){
		return evenementServ.getListEvenements();
	}
	@GetMapping(value="/getEvenementByTitre/{tt}")
	public Evenement getEvenementByTT(@PathVariable String tt){
		return evenementServ.getByTitre(tt);
	}
	@GetMapping(value="/getEvenementsSWST/{tt}")
	public List<Evenement> getEvenementsSWST(@PathVariable String tt){
		return evenementServ.getEvenementsSWST(tt);
	}
	@GetMapping(value="/user/{userId}")
	public List<Evenement> getEvenementsByUserId(@PathVariable Long userId){
		return evenementServ.getEvenementsByUserId(userId);
	}
	@GetMapping(value="/getEvenementById/{idevenement}")
	public Evenement getEvenementById(@PathVariable Long idevenement) {
	    return evenementServ.getEvenementById(idevenement);
	}

	 
}
