package tn.projetdemo.demo.services;

import java.util.List;
import java.util.NoSuchElementException;


import tn.projetdemo.demo.entities.User;

public interface UserServiceinter {
	public User addUser(User user);
	public List<User> addListUser(List<User> listuser);
	public String addUserWTCP(User user);
	public String addUserWTUN (User user);
	public User updateUser(Long iduser , User user);
	public void deleteUser(Long iduser);
	public List<User> getListUsers();
	public User getByUsername(String username);
	public List<User> getUsersSWSUN (String ch);
	public User getUserById(Long id) throws NoSuchElementException;

}
