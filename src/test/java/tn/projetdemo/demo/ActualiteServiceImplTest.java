package tn.projetdemo.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.projetdemo.demo.entities.Actualite;
import tn.projetdemo.demo.entities.User;
import tn.projetdemo.demo.repository.ActualiteRepository;
import tn.projetdemo.demo.repository.UserRepository;
import tn.projetdemo.demo.services.ActualiteServiceImpl;
import org.junit.Before;
@ExtendWith(MockitoExtension.class)
public class ActualiteServiceImplTest {
	

    @Mock
    private ActualiteRepository actualiteRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ActualiteServiceImpl actualiteService;
    
    @Before
    public void setUp() {
        actualiteRepository = mock(ActualiteRepository.class);
        
        
    }

    @Test
    public void testAddActualite() {
        // Given
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        Actualite actualite = new Actualite();
        actualite.setTitre("Actualite 1");
        actualite.setContenu("Contenu de l'actualite 1");
        actualite.setCreated(new Date());
        actualite.setUser(Set.of(user));

        when(userRepository.getUserById(userId)).thenReturn(user);
        when(actualiteRepository.save(actualite)).thenReturn(actualite);

        // When
        Actualite savedActualite = actualiteService.addActualite(userId, actualite);

        // Then
        assertEquals(actualite, savedActualite);
        verify(userRepository, times(1)).getUserById(userId);
        verify(actualiteRepository, times(1)).save(actualite);
    }
    
    
    
    @Test
    public void testAddListActualite() {
        // Given
        List<Actualite> listActualites = new ArrayList<>();
        Actualite actualite1 = new Actualite();
        actualite1.setTitre("Actualite 1");
        actualite1.setContenu("Contenu de l'actualite 1");
        actualite1.setCreated(new Date());
        listActualites.add(actualite1);

        Actualite actualite2 = new Actualite();
        actualite2.setTitre("Actualite 2");
        actualite2.setContenu("Contenu de l'actualite 2");
        actualite2.setCreated(new Date());
        listActualites.add(actualite2);

        when(actualiteRepository.saveAll(listActualites)).thenReturn(listActualites);

        // When
        List<Actualite> savedActualites = actualiteService.addListActualite(listActualites);

        // Then
        assertEquals(listActualites, savedActualites);
        verify(actualiteRepository, times(1)).saveAll(listActualites);
    }
    
    @Test
    public void testUpdateActualite() {
        // Given
        Long id = 1L;
        String newTitre = "Nouveau titre";
        String newContenu = "Nouveau contenu";

        Actualite actualite = new Actualite();
        actualite.setId_actualite(id);
        actualite.setTitre("Titre original");
        actualite.setContenu("Contenu original");
        actualite.setCreated(new Date());

        // Simuler le comportement de findById pour renvoyer l'actualité avec l'ID donné
        when(actualiteRepository.findById(id)).thenReturn(Optional.of(actualite));
        
        // Simuler le comportement de save pour renvoyer l'actualité mise à jour
        when(actualiteRepository.save(any(Actualite.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Actualite updatedActualite = actualiteService.updateActualite(id, actualite);

        // Then
        assertEquals(newTitre, updatedActualite.getTitre());
        assertEquals(newContenu, updatedActualite.getContenu());
        verify(actualiteRepository, times(1)).findById(id);
        verify(actualiteRepository, times(1)).save(any(Actualite.class));
    }
    
    @Test
    public void testDeleteActualite() {
        // Given
        Long id = 1L;
        Actualite actualite = new Actualite();
        actualite.setId_actualite(id);

        when(actualiteRepository.findById(id)).thenReturn(Optional.of(actualite));

        // When
        actualiteService.deleteActualite(id);

        // Then
        verify(actualiteRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteActualiteThrowsExceptionWhenNotFound() {
        // Given
        Long id = 1L;

        

        // When/Then
        assertThrows(NoSuchElementException.class, () -> {
            actualiteService.deleteActualite(id);
        });

        verify(actualiteRepository, never()).deleteById(id);
    }    
    
    @Test
    public void testGetListActualites() {
        // Given
        Actualite actualite1 = new Actualite();
        actualite1.setId_actualite(1L);
        actualite1.setTitre("Actualite 1");

        Actualite actualite2 = new Actualite();
        actualite2.setId_actualite(2L);
        actualite2.setTitre("Actualite 2");

        List<Actualite> actualites = new ArrayList<>();
        actualites.add(actualite1);
        actualites.add(actualite2);

        when(actualiteRepository.findAll()).thenReturn(actualites);

        // When
        List<Actualite> result = actualiteService.getListActualites();

        // Then
        assertEquals(2, result.size());
        assertEquals("Actualite 1", result.get(0).getTitre());
        assertEquals("Actualite 2", result.get(1).getTitre());
    }
    
    
    @Test
    public void testGetByTitre() {
        // Given
        String titre = "Test Actualite";
        Actualite actualite = new Actualite();
        actualite.setId_actualite(1L);
        actualite.setTitre(titre);

        when(actualiteRepository.findByTitre(titre)).thenReturn(actualite);

        // When
        Actualite result = actualiteService.getByTitre(titre);

        // Then
        assertNotNull(result);
        assertEquals(1L, (long) result.getId_actualite());
        assertEquals(titre, result.getTitre());
    }
    
   

    @Test
    public void testGetActualitesSWSA() {
        // Given
        String searchKeyword = "example";
        List<Actualite> expectedResults = new ArrayList<>();
        // Populate expectedResults with some dummy Actualite objects

        // Stub the behavior of actualiteRepository.getActualitésSWSA(ch)
        when(actualiteRepository.getActualitésSWSA(searchKeyword)).thenReturn(expectedResults);

        // When
        List<Actualite> actualResults = actualiteService.getActualitesSWSA(searchKeyword);

        // Then
        assertEquals(expectedResults, actualResults);
    }
    
    
    @Test
    public void testGetActualiteById() {
        // Given
        Long id = 1L;
        String titre = "Test Actualite";
        Actualite actualite = new Actualite();
        actualite.setId_actualite(id);
        actualite.setTitre(titre);

        when(actualiteRepository.findById(id)).thenReturn(Optional.of(actualite));

        // When
        Actualite result = actualiteService.getActualiteById(id);

        // Then
        assertNotNull(result);
        assertEquals(id, result.getId_actualite());
        assertEquals(titre, result.getTitre());
    }

    @Test
    public void testGetActualiteByIdThrowsExceptionWhenNotFound() {
        // Given
        Long id = 1L;

        when(actualiteRepository.findById(id)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(NoSuchElementException.class, () -> {
            actualiteService.getActualiteById(id);
        });

        verify(actualiteRepository, times(1)).findById(id);
    }
    
    @Test
    public void testGetAllPublicActualites() {
        // Given
        List<Actualite> expectedActualites = new ArrayList<>();
        // Ajoutez des données à expectedActualites si nécessaire

        // Définissez le comportement simulé de actualiteRepository.findAll()
        when(actualiteRepository.findAll()).thenReturn(expectedActualites);

        // When
        List<Actualite> actualActualites = actualiteService.getAllPublicActualites();

        // Then
        assertEquals(expectedActualites, actualActualites);
        verify(actualiteRepository, times(1)).findAll();
    }

   
}
