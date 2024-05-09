package tn.projetdemo.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import static org.mockito.Mockito.when;

import static org.junit.Assert.assertThrows;


import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tn.projetdemo.demo.entities.Evenement;
import tn.projetdemo.demo.entities.User;
import tn.projetdemo.demo.repository.EvenementRepository;
import tn.projetdemo.demo.repository.UserRepository;
import tn.projetdemo.demo.services.EvenementServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EvenementServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EvenementRepository evenementRepository;

    @InjectMocks
    private EvenementServiceImpl evenementService;

    @Before
    public void setUp() {
        // Mocking UserRepository behavior
        User existingUser = new User();
        existingUser.setId(1L); // Assuming user ID 1 exists
        when(userRepository.getUserById(1L)).thenReturn(existingUser);

        // Mocking EvenementRepository behavior
        List<Evenement> savedEvents = new ArrayList<>();
        Evenement event1 = new Evenement();
        
        event1.setTitre("Event 1");
        Evenement event2 = new Evenement();
        
        event2.setTitre("Event 2");
        savedEvents.add(event1);
        savedEvents.add(event2);
        when(evenementRepository.saveAll(savedEvents)).thenReturn(savedEvents);
        when(evenementRepository.existsByTitre("existingTitle")).thenReturn(true);
        when(evenementRepository.existsByTitre("newTitle")).thenReturn(false);
        when(evenementRepository.findById(1L)).thenReturn(Optional.of(new Evenement()));
        when(evenementRepository.findById(1L)).thenReturn(Optional.of(new Evenement()));
    }

    @Test
    public void testAddevenement_Success() {
        // Arrange
        Evenement eventToAdd = new Evenement();
        eventToAdd.setTitre("Test Event");
        eventToAdd.setDescription("Description of the test event");
        eventToAdd.setCreated(new Date());
        eventToAdd.setLieu("Test Location");
        eventToAdd.setImageURL("Test Image URL");

        // Act
        Evenement addedEvent = evenementService.addevenement(1L, eventToAdd); // Assuming user ID 1 exists

        // Assert
        assertEquals("Test Event", addedEvent.getTitre());
        // Add more assertions as needed
    }
 

    @Test
    public void testAddListEvenement_Success() {
        // Arrange
        List<Evenement> eventsToAdd = new ArrayList<>();
        Evenement event1 = new Evenement();
        event1.setTitre("Event 1");
        Evenement event2 = new Evenement();
        event2.setTitre("Event 2");
        eventsToAdd.add(event1);
        eventsToAdd.add(event2);

        // Act
        List<Evenement> addedEvents = evenementService.addListEvenement(eventsToAdd);

        // Assert
        assertEquals(2, addedEvents.size());
        assertEquals("Event 1", addedEvents.get(0).getTitre());
        assertEquals("Event 2", addedEvents.get(1).getTitre());
        // Add more assertions as needed
    }
    
    
    @Test
    public void testAddevenementWTT_TitleExists() {
        // Arrange
        Evenement evenement = new Evenement();
        evenement.setTitre("existingTitle");
        evenement.setDescription("Test Description");
        evenement.setCreated(new Date());

        // Act
        String result = evenementService.addevenementWTT(evenement);

        // Assert
        assertEquals("title already exists", result);
    }

    @Test
    public void testAddevenementWTT_TitleDoesNotExist() {
        // Arrange
        Evenement evenement = new Evenement();
        evenement.setTitre("newTitle");
        evenement.setDescription("Test Description");
        evenement.setCreated(new Date());

        // Act
        String result = evenementService.addevenementWTT(evenement);

        // Assert
        assertEquals("event added successfuly", result);
    }
    @Test
    public void testUpdateEvenement() {
        // Arrange
        Evenement evenementToUpdate = new Evenement();
        evenementToUpdate.setIdevenement(1L);
        evenementToUpdate.setTitre("Updated Title");
        evenementToUpdate.setDescription("Updated Description");

        when(evenementRepository.save(evenementToUpdate)).thenReturn(evenementToUpdate);

        // Act
        Evenement updatedEvenement = evenementService.updateEvenement(1L, evenementToUpdate);

        // Assert
        assertNotNull(updatedEvenement);
        assertEquals("Updated Title", updatedEvenement.getTitre());
        assertEquals("Updated Description", updatedEvenement.getDescription());
    }
    
    @Test
    public void testDeleteEvenement() {
        // Act
        evenementService.deleteEvenement(1L);

        // Assert
        // Vérifier si la méthode deleteById() est appelée avec l'identifiant correct
        verify(evenementRepository, times(1)).deleteById(1L);
    }
    
    
    @Test
    public void testGetListEvenements() {
        // Créer une liste d'événements simulée
        List<Evenement> evenements = new ArrayList<>();
        evenements.add(new Evenement());
        evenements.add(new Evenement());

        // Configurer le comportement du repository pour retourner la liste simulée lorsque findAll() est appelé
        when(evenementRepository.findAll()).thenReturn(evenements);

        // Appeler la méthode du service
        List<Evenement> result = evenementService.getListEvenements();

        // Vérifier si la méthode findAll() du repository est appelée une fois
        verify(evenementRepository, times(1)).findAll();

        // Vérifier si le résultat retourné par la méthode du service correspond à la liste simulée
        assertSame(evenements, result);
    }
    
    
    
    @Test
    public void testGetByTitre() {
        // Titre à rechercher
        String titre = "Test Titre";

        // Création d'un événement simulé
        Evenement evenement = new Evenement();
        evenement.setTitre(titre);

        // Configurer le comportement du repository pour retourner l'événement simulé lorsque findByTitre() est appelé avec le titre spécifié
        when(evenementRepository.findByTitre(titre)).thenReturn(evenement);

        // Appeler la méthode du service
        Evenement result = evenementService.getByTitre(titre);

        // Vérifier si la méthode findByTitre() du repository est appelée une fois avec le titre spécifié
        verify(evenementRepository, times(1)).findByTitre(titre);

        // Vérifier si le résultat retourné par la méthode du service correspond à l'événement simulé
        assertNotNull(result);
        assertEquals(titre, result.getTitre());
    }
    
    @Test
    public void testGetEvenementsSWST() {
        // Chaîne à rechercher
        String ch = "Test String";

        // Création d'une liste simulée d'événements
        List<Evenement> evenements = new ArrayList<>();
        Evenement evenement1 = new Evenement();
        evenement1.setTitre("Event 1");
        evenements.add(evenement1);
        Evenement evenement2 = new Evenement();
        evenement2.setTitre("Event 2");
        evenements.add(evenement2);

        // Configurer le comportement du repository pour retourner la liste simulée d'événements lorsque getEvenementsSWST() est appelé avec la chaîne spécifiée
        when(evenementRepository.getEvenementsSWST(ch)).thenReturn(evenements);

        // Appeler la méthode du service
        List<Evenement> result = evenementService.getEvenementsSWST(ch);

        // Vérifier si la méthode getEvenementsSWST() du repository est appelée une fois avec la chaîne spécifiée
        verify(evenementRepository, times(1)).getEvenementsSWST(ch);

        // Vérifier si le résultat retourné par la méthode du service correspond à la liste simulée d'événements
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Event 1", result.get(0).getTitre());
        assertEquals("Event 2", result.get(1).getTitre());
    }
    
    
    @Test
    public void testGetEvenementsByUserId() {
        // ID de l'utilisateur pour lequel récupérer les événements
        Long userId = 1L;

        // Création d'une liste simulée d'événements
        List<Evenement> evenements = new ArrayList<>();
        Evenement evenement1 = new Evenement();
        evenement1.setTitre("Event 1");
        evenements.add(evenement1);
        Evenement evenement2 = new Evenement();
        evenement2.setTitre("Event 2");
        evenements.add(evenement2);

        // Configurer le comportement du repository pour retourner la liste simulée d'événements lorsque findByUserId(userId) est appelé avec l'ID de l'utilisateur spécifié
        when(evenementRepository.findByUserId(userId)).thenReturn(evenements);

        // Appeler la méthode du service
        List<Evenement> result = evenementService.getEvenementsByUserId(userId);

        // Vérifier si la méthode findByUserId(userId) du repository est appelée une fois avec l'ID de l'utilisateur spécifié
        verify(evenementRepository, times(1)).findByUserId(userId);

        // Vérifier si le résultat retourné par la méthode du service correspond à la liste simulée d'événements
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Event 1", result.get(0).getTitre());
        assertEquals("Event 2", result.get(1).getTitre());
    }
    
    
    @Test
    public void testGetEvenementById_ExistingId() {
        // ID de l'événement à récupérer
        Long idevenement = 1L;

        // Création d'un événement simulé
        Evenement evenement = new Evenement();
        evenement.setTitre("Test Event");

        // Configurer le comportement du repository pour retourner l'événement simulé lorsque findById(idevenement) est appelé avec l'ID d'événement spécifié
        when(evenementRepository.findById(idevenement)).thenReturn(Optional.of(evenement));

        // Appeler la méthode du service
        Evenement result = evenementService.getEvenementById(idevenement);

        // Vérifier si la méthode findById(idevenement) du repository est appelée une fois avec l'ID d'événement spécifié
        verify(evenementRepository, times(1)).findById(idevenement);

        // Vérifier si le résultat retourné par la méthode du service correspond à l'événement simulé
        assertNotNull(result);
        assertEquals("Test Event", result.getTitre());
    }

    @Test
    public void testGetEvenementById_NonExistingId() {
        // ID de l'événement à récupérer
        Long idevenement = 1L;

        // Configurer le comportement du repository pour retourner un Optional vide lorsque findById(idevenement) est appelé avec l'ID d'événement spécifié
        when(evenementRepository.findById(idevenement)).thenReturn(Optional.empty());

        // Vérifier si une NoSuchElementException est levée lorsque la méthode du service est appelée avec un ID d'événement qui n'existe pas
        assertThrows(NoSuchElementException.class, () -> {
            evenementService.getEvenementById(idevenement);
        });

        // Vérifier si la méthode findById(idevenement) du repository est appelée une fois avec l'ID d'événement spécifié
        verify(evenementRepository, times(1)).findById(idevenement);
    }
}
