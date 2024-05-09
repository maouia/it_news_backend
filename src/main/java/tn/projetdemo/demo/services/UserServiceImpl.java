package tn.projetdemo.demo.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.projetdemo.demo.entities.User;
import tn.projetdemo.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserServiceinter {
	
	@Autowired
	UserRepository userRep;

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		
		return userRep.save(user);
		
	}

	@Override
	public List<User> addListUser(List<User> listuser) {
		// TODO Auto-generated method stub
		return userRep.saveAll(listuser);
	}

	@Override
	public String addUserWTCP(User user) {
		// TODO Auto-generated method stub
		String ch="";
		if (user.getPass_ut().equals(user.getConfirm_pass())) {
			userRep.save(user);
			ch="user added successfuly";
		}
		else {
			ch= "check confirm password";
		}
		return ch;
	}

	@Override
	public String addUserWTUN(User user) {
		// TODO Auto-generated method stub
		String ch="";
    	if( userRep.existsByUsername(user.getUsername())){
    		ch="username already exists";
    	}else {
    		userRep.save(user);
    		ch="user added successfuly";
    	}
	
	return ch;

}

	@Override
	public User updateUser(Long iduser, User user) {
		// TODO Auto-generated method stub
		User usr = userRep.findById(iduser).get();
		
		usr.setPrenom_ut(user.getPrenom_ut());
		usr.setNom_ut(user.getNom_ut());
		return userRep.save(usr);
	}
	@Override
	public void deleteUser(Long iduser) {
		// TODO Auto-generated method stub
		userRep.deleteById(iduser);
	}

	@Override
	public List<User> getListUsers() {
		// TODO Auto-generated method stub
		return userRep.findAll();
	}

	@Override
	public User getByUsername(String username) {
	    Optional<User> userOptional = userRep.findByUsername(username);
	    return userOptional.orElse(null); 
	}


	@Override
	public List<User> getUsersSWSUN(String ch) {
		// TODO Auto-generated method stub
		return userRep.getUsersSWSUN(ch);
	}
	
	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		Optional<User> user =  userRep.findById(id);
        if (user.isPresent()) {
            return user.get();
            
	}else {
        throw new  NoSuchElementException("Événement non trouvé avec l'ID : " + id);
    }
	}}
