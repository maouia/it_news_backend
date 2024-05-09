package tn.projetdemo.demo;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import tn.projetdemo.demo.entities.User;
import tn.projetdemo.demo.repository.UserRepository;
import tn.projetdemo.demo.services.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testAddUser() {
        // Création d'un utilisateur factice pour le test
        User user = new User();
        user.setNom_ut("John");
        user.setPrenom_ut("Doe");
        user.setUsername("johndoe");
        user.setEmail("johndoe@example.com");
        user.setPass_ut("password");
        user.setConfirm_pass("password");
        user.setCreated(new Date());
        user.setRolename("ROLE_USER");
        user.setEnabled(true);

        // Définir le comportement attendu lors de l'ajout d'un utilisateur
        when(userRepository.save(user)).thenReturn(user);

        // Appel de la méthode à tester
        User savedUser = userService.addUser(user);

        // Vérification que l'utilisateur enregistré n'est pas nul
        assertNotNull(savedUser);
        // Vérification que les propriétés de l'utilisateur enregistré correspondent à celles de l'utilisateur d'origine
        assertEquals("John", savedUser.getNom_ut());
        assertEquals("Doe", savedUser.getPrenom_ut());
        assertEquals("johndoe", savedUser.getUsername());
        assertEquals("johndoe@example.com", savedUser.getEmail());
        assertEquals("password", savedUser.getPass_ut());
        assertEquals("password", savedUser.getConfirm_pass());
        assertEquals("ROLE_USER", savedUser.getRolename());
        assertEquals(true, savedUser.isEnabled());
        // Vous pouvez ajouter d'autres assertions pour vérifier d'autres propriétés si nécessaire
    }
    
    @Test
    public void testAddListUser() {
        // Création d'une liste d'utilisateurs factice pour le test
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setNom_ut("John");
        user1.setPrenom_ut("Doe");
        user1.setUsername("johndoe");
        user1.setPass_ut("password");
        user1.setConfirm_pass("password");
        userList.add(user1);

        User user2 = new User();
        user2.setNom_ut("Jane");
        user2.setPrenom_ut("Doe");
        user2.setUsername("janedoe");
        user2.setPass_ut("password");
        user2.setConfirm_pass("password");
        userList.add(user2);

        // Définir le comportement attendu lors de l'ajout de la liste d'utilisateurs
        when(userRepository.saveAll(userList)).thenReturn(userList);

        // Appel de la méthode à tester
        List<User> savedUsers = userService.addListUser(userList);

        // Vérification que la liste d'utilisateurs retournée correspond à celle fournie en argument
        assertNotNull(savedUsers);
        assertEquals(2, savedUsers.size()); // Vérification que deux utilisateurs ont été ajoutés
        assertEquals("John", savedUsers.get(0).getNom_ut());
        assertEquals("johndoe", savedUsers.get(0).getUsername());
        assertEquals("Jane", savedUsers.get(1).getNom_ut());
        assertEquals("janedoe", savedUsers.get(1).getUsername());
    }
    
    @Test
    public void testAddUserWTCP_PasswordsMatch() {
        // Création d'un utilisateur avec des mots de passe concordants
        User user = new User();
        user.setPass_ut("password");
        user.setConfirm_pass("password");

        // Définir le comportement attendu lors de l'ajout de l'utilisateur
        when(userRepository.save(user)).thenReturn(user);

        // Appel de la méthode à tester
        String result = userService.addUserWTCP(user);

        // Vérification que le message retourné est correct
        assertEquals("user added successfuly", result);
    }

    @Test
    public void testAddUserWTCP_PasswordsMismatch() {
        // Création d'un utilisateur avec des mots de passe non concordants
        User user = new User();
        user.setPass_ut("password1");
        user.setConfirm_pass("password2");

        // Appel de la méthode à tester
        String result = userService.addUserWTCP(user);

        // Vérification que le message retourné est correct
        assertEquals("check confirm password", result);
    }
    
    
    @Test
    public void testAddUserWTUN_UsernameExists() {
        // Création d'un utilisateur avec un nom d'utilisateur existant
        User user = new User();
        user.setUsername("existing_username");

        // Définir le comportement attendu lors de l'ajout de l'utilisateur
        when(userRepository.existsByUsername("existing_username")).thenReturn(true);

        // Appel de la méthode à tester
        String result = userService.addUserWTUN(user);

        // Vérification que le message retourné est correct
        assertEquals("username already exists", result);
    }

    @Test
    public void testAddUserWTUN_UsernameUnique() {
        // Création d'un utilisateur avec un nom d'utilisateur unique
        User user = new User();
        user.setUsername("unique_username");

        // Définir le comportement attendu lors de l'ajout de l'utilisateur
        when(userRepository.existsByUsername("unique_username")).thenReturn(false);

        // Appel de la méthode à tester
        String result = userService.addUserWTUN(user);

        // Vérification que le message retourné est correct
        assertEquals("user added successfuly", result);
    }
    
    
    @Test
    public void testUpdateUser_UserExists() {
        // Création d'un utilisateur à mettre à jour
        User existingUser = new User();
        existingUser.setId(1L); // ID de l'utilisateur existant
        existingUser.setNom_ut("John");
        existingUser.setPrenom_ut("Doe");

        // Création d'un utilisateur avec les nouvelles données
        User updatedUser = new User();
        updatedUser.setId(1L); // ID de l'utilisateur existant
        updatedUser.setNom_ut("Jane");
        updatedUser.setPrenom_ut("Smith");

        // Simulation du comportement du repository lors de la recherche de l'utilisateur existant
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));

        // Simulation du comportement du repository lors de la sauvegarde de l'utilisateur mis à jour
        when(userRepository.save(existingUser)).thenReturn(updatedUser);

        // Appel de la méthode à tester
        User result = userService.updateUser(1L, updatedUser);

        // Vérification que l'utilisateur retourné correspond à l'utilisateur mis à jour
        assertNotNull(result);
        assertEquals("Jane", result.getNom_ut());
        assertEquals("Smith", result.getPrenom_ut());
    }

    @Test(expected = NoSuchElementException.class)
    public void testUpdateUser_UserNotFound() {
        // Simulation du comportement du repository lorsque l'utilisateur n'est pas trouvé
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Appel de la méthode à tester qui devrait lever une exception NoSuchElementException
        userService.updateUser(1L, new User());
    }
    
    
    
    @Test
    public void testDeleteUser_UserExists() {
        // Création d'un utilisateur factice à supprimer
        User userToDelete = new User();
        userToDelete.setId(1L); // ID de l'utilisateur à supprimer

        // Simulation du comportement du repository lors de la recherche de l'utilisateur à supprimer
        when(userRepository.findById(1L)).thenReturn(Optional.of(userToDelete));

        // Appel de la méthode à tester
        userService.deleteUser(1L);

        // Vérification que la méthode deleteById a été appelée avec le bon ID d'utilisateur
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDeleteUser_UserNotFound() {
        // Simulation du comportement du repository lorsque l'utilisateur à supprimer n'est pas trouvé
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Appel de la méthode à tester qui devrait lever une exception NoSuchElementException
        userService.deleteUser(1L);
    }
    
    
    @Test
    public void testGetListUsers() {
        // Création d'une liste factice d'utilisateurs
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "John", "Doe", "johndoe", "password", "password", "johndoe@example.com", "Address", "imageURL", null, "ROLE_USER", true, null, null, null));
        userList.add(new User(2L, "Alice", "Smith", "alicesmith", "password", "password", "alicesmith@example.com", "Address", "imageURL", null, "ROLE_USER", true, null, null, null));

        // Simulation du comportement du repository lors de la recherche de tous les utilisateurs
        when(userRepository.findAll()).thenReturn(userList);

        // Appel de la méthode à tester
        List<User> returnedUserList = userService.getListUsers();

        // Vérification que la liste retournée correspond à celle simulée
        assertEquals(userList.size(), returnedUserList.size());
        assertEquals(userList.get(0), returnedUserList.get(0));
        assertEquals(userList.get(1), returnedUserList.get(1));
        // Vous pouvez ajouter d'autres assertions pour vérifier d'autres éléments si nécessaire
    }
    
    @Test
    public void testGetByUsername() {
        // Création d'un utilisateur factice
        User user = new User();
        user.setId(1L);
        user.setUsername("johndoe");

        // Simulation du comportement du repository lors de la recherche de l'utilisateur par nom d'utilisateur
        when(userRepository.findByUsername("johndoe")).thenReturn(Optional.of(user));

        // Appel de la méthode à tester
        User returnedUser = userService.getByUsername("johndoe");

        // Vérification que l'utilisateur retourné correspond à celui simulé
        assertNotNull(returnedUser);
        assertEquals(user.getId(), returnedUser.getId());
        assertEquals(user.getUsername(), returnedUser.getUsername());
        // Vous pouvez ajouter d'autres assertions pour vérifier d'autres propriétés si nécessaire
    }
    
    @Test
    public void testGetUsersSWSUN() {
        // Création d'une liste factice d'utilisateurs
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("johndoe");
        userList.add(user1);

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("janedoe");
        userList.add(user2);

        // Simulation du comportement du repository lors de la recherche des utilisateurs avec un nom d'utilisateur partiel
        when(userRepository.getUsersSWSUN("doe")).thenReturn(userList);

        // Appel de la méthode à tester
        List<User> returnedUsers = userService.getUsersSWSUN("doe");

        // Vérification que la liste d'utilisateurs retournée correspond à celle simulée
        assertNotNull(returnedUsers);
        assertEquals(2, returnedUsers.size());
        assertEquals("johndoe", returnedUsers.get(0).getUsername());
        assertEquals("janedoe", returnedUsers.get(1).getUsername());
        // Vous pouvez ajouter d'autres assertions pour vérifier d'autres propriétés si nécessaire
    }
    
    @Test
    public void testGetUserById() {
        // Création d'un utilisateur factice pour le test
        User user = new User();
        user.setId(1L);
        user.setUsername("johndoe");

        // Simulation du comportement du repository lors de la recherche de l'utilisateur par ID
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Appel de la méthode à tester
        User returnedUser = userService.getUserById(1L);

        // Vérification que l'utilisateur retourné correspond à celui simulé
        assertNotNull(returnedUser);
        assertEquals(1L, returnedUser.getId().longValue());
        assertEquals("johndoe", returnedUser.getUsername());
        // Vous pouvez ajouter d'autres assertions pour vérifier d'autres propriétés si nécessaire
    }
}


