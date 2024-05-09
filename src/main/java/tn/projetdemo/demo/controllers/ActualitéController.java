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

import tn.projetdemo.demo.entities.Actualite;

import tn.projetdemo.demo.services.ActualiteServiceImpl;

@RestController
@CrossOrigin(origins = "*")
public class ActualitéController {
	@Autowired
	ActualiteServiceImpl actServ;
	
	@PostMapping(value="/addAcualité/{userId}")
	public Actualite addActualite (@PathVariable Long userId,@RequestBody Actualite actualite) {
		return actServ.addActualite(userId,actualite);
	}
	
	@PostMapping(value="/addListAcualité")
	public List <Actualite> addListActualite (@RequestBody List<Actualite> listactualite) {
		return actServ.addListActualite(listactualite);
	}
	
	@PutMapping(value="/updateAcualité/{id}")
	public Actualite updateActualite (@PathVariable Long id,@RequestBody Actualite actualite) {
		return actServ.updateActualite(id,actualite);
	}
	
	@DeleteMapping(value="/deleteAcualité/{id}")
	public void deleteActualite (@PathVariable Long id) {
		 actServ.deleteActualite(id);
	}
	
	@GetMapping(value="/getAllAcualités")
	public List <Actualite> getAllActualites () {
		return actServ.getListActualites();
	}
	
	@GetMapping(value="/getAcualitésByT/{t}")
	public Actualite getActualiteByTitre (@PathVariable String t) {
		return actServ.getByTitre(t);
	}
	@GetMapping(value="/getAcualitésSWSA/{t}")
	public List<Actualite> getActualitesSWSA (@PathVariable String t) {
		return actServ.getActualitesSWSA(t);
	}
	
	@GetMapping(value="/getActualiteById/{id_actualite}")
	public Actualite getActualiteById(@PathVariable Long id_actualite) {
	    return actServ.getActualiteById(id_actualite);
	}
	 @GetMapping("/api/actualites/public")
	    public List<Actualite> getAllPublicActualites() {
	        return actServ.getAllPublicActualites();
	    }

}
