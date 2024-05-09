package tn.projetdemo.demo.services;

import java.util.List;
import java.util.NoSuchElementException;

import tn.projetdemo.demo.entities.Evenement;



public interface EvenementServiceInter {
	public Evenement addevenement(Long userId,Evenement evenement);
	public List<Evenement> addListEvenement(List<Evenement> listevenement);
	public String addevenementWTT (Evenement evenement);
	public Evenement updateEvenement(Long idevenement,Evenement evenement);
	public void deleteEvenement(Long idevenement);
	public List<Evenement> getListEvenements();
	public Evenement getByTitre(String Titre);
	public List<Evenement>getEvenementsSWST(String ch);
	public List<Evenement> getEvenementsByUserId(Long userId);
	public Evenement getEvenementById(Long idevenement) throws NoSuchElementException;
	


}
