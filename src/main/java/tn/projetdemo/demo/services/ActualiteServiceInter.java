package tn.projetdemo.demo.services;

import java.util.List;
import java.util.NoSuchElementException;

import tn.projetdemo.demo.entities.Actualite;



public interface ActualiteServiceInter {
	public Actualite addActualite(Long userId,Actualite actualite);
	public List<Actualite> addListActualite(List<Actualite> listactualite);
	public Actualite updateActualite(Long id , Actualite actualite);
	public void deleteActualite(Long id);
	public List<Actualite> getListActualites();
	public Actualite getByTitre(String titre);
	public List<Actualite> getActualitesSWSA (String ch);
	public  Actualite getActualiteById(Long id_actualite) throws NoSuchElementException;
	public List<Actualite> getAllPublicActualites();

}
