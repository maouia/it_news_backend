package tn.projetdemo.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThrows;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.List;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tn.projetdemo.demo.entities.Article;
import tn.projetdemo.demo.entities.Commentaire;
import tn.projetdemo.demo.entities.Evenement;
import tn.projetdemo.demo.entities.User;
import tn.projetdemo.demo.repository.ArticleRepository;
import tn.projetdemo.demo.repository.CommentaireRepository;
import tn.projetdemo.demo.repository.EvenementRepository;
import tn.projetdemo.demo.repository.UserRepository;
import tn.projetdemo.demo.services.CommentaireServiceImpl;
import tn.projetdemo.demo.repository.ActualiteRepository;
import tn.projetdemo.demo.entities.Actualite;
@RunWith(MockitoJUnitRunner.class)
public class CommentaireServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private EvenementRepository evenementRepository;

    @Mock
    private CommentaireRepository commentaireRepository;
    
    @Mock
    private ActualiteRepository actualiteRepository;

    @InjectMocks
    private CommentaireServiceImpl commentaireService;

    @Test
    public void testAddCommentaire() {
        // Création des données factices
        Long userId = 1L;
        Long evenementId = 2L;

        User user = new User();
        user.setId(userId);

        Evenement evenement = new Evenement();
        evenement.setIdevenement(evenementId);

        Commentaire commentaire = new Commentaire();
        commentaire.setBody("Ceci est un commentaire");
        commentaire.setCreated(new Date());

        // Simulation du comportement des repositories
        when(userRepository.getUserById(userId)).thenReturn(user);
        when(evenementRepository.findById(evenementId)).thenReturn(Optional.of(evenement));
        when(commentaireRepository.save(commentaire)).thenReturn(commentaire);

        // Appel de la méthode à tester
        Commentaire addedCommentaire = commentaireService.addCommentaire(userId, evenementId, commentaire);

        // Vérification
        assertEquals(commentaire, addedCommentaire);
    }
    
    
    @Test
    public void testAddCommentairee() {
        // Création des données factices
        Long userId = 1L;
        Long articleId = 2L;

        User user = new User();
        user.setId(userId);

        Article article = new Article();
        article.setId_article(articleId);

        Commentaire commentaire = new Commentaire();
        commentaire.setBody("Ceci est un commentaire");
        commentaire.setCreated(new Date());

        // Simulation du comportement des repositories
        when(userRepository.getUserById(userId)).thenReturn(user);
        when(articleRepository.findById(articleId)).thenReturn(Optional.of(article));
        when(commentaireRepository.save(commentaire)).thenReturn(commentaire);

        // Appel de la méthode à tester
        Commentaire addedCommentaire = commentaireService.addCommentairee(userId, articleId, commentaire);

        // Vérification
        assertEquals(commentaire, addedCommentaire);
    }
    
    @Test
    public void testAddCommentaireee() {
        // Création des données factices
        Long userId = 1L;
        Long actualiteId = 2L;

        User user = new User();
        user.setId(userId);

        Actualite actualite = new Actualite();
        actualite.setId_actualite(actualiteId);

        Commentaire commentaire = new Commentaire();
        commentaire.setBody("Ceci est un commentaire");
        commentaire.setCreated(new Date());

        // Simulation du comportement des repositories
        when(userRepository.getUserById(userId)).thenReturn(user);
        when(actualiteRepository.findById(actualiteId)).thenReturn(Optional.of(actualite));
        when(commentaireRepository.save(commentaire)).thenReturn(commentaire);

        // Appel de la méthode à tester
        Commentaire addedCommentaire = commentaireService.addCommentaireee(userId, actualiteId, commentaire);

        // Vérification
        assertEquals(commentaire, addedCommentaire);
    }
    
    
    @Test
    public void testUpdateCommentaire() {
        // Création des données factices
        Long commentaireId = 1L;

        Commentaire existingCommentaire = new Commentaire();
        existingCommentaire.setCommentId(commentaireId);
        existingCommentaire.setBody("Ancien corps du commentaire");
        existingCommentaire.setCreated(new Date());

        Commentaire newCommentaire = new Commentaire();
        newCommentaire.setCommentId(commentaireId);
        newCommentaire.setBody("Nouveau corps du commentaire");
        newCommentaire.setCreated(new Date());

        // Simulation du comportement du repository
        when(commentaireRepository.findById(commentaireId)).thenReturn(Optional.of(existingCommentaire));
        when(commentaireRepository.save(existingCommentaire)).thenReturn(existingCommentaire);

        // Appel de la méthode à tester
        Commentaire updatedCommentaire = commentaireService.updateCommentaire(commentaireId, newCommentaire);

        // Vérification
        assertEquals(newCommentaire.getBody(), updatedCommentaire.getBody());
        // Vous pouvez également vérifier d'autres champs si nécessaire
    }
    
    @Test
    public void testDeleteCommentaire() {
        // Création des données factices
        Long commentaireId = 1L;

        Commentaire existingCommentaire = new Commentaire();
        existingCommentaire.setCommentId(commentaireId);

        // Simulation du comportement du repository
        when(commentaireRepository.findById(commentaireId)).thenReturn(Optional.of(existingCommentaire));

        // Appel de la méthode à tester
        commentaireService.deleteCommentaire(commentaireId);

        // Vérification
        // Vérifiez si la méthode a été appelée avec l'ID du commentaire
        assertThrows(NoSuchElementException.class, () -> commentaireRepository.findById(commentaireId).orElseThrow());
    }
    @Test
    public void testGetListCommentaires() {
        // Création des données factices
        Commentaire commentaire1 = new Commentaire();
        commentaire1.setCommentId(1L);
        commentaire1.setBody("Commentaire 1");

        Commentaire commentaire2 = new Commentaire();
        commentaire2.setCommentId(2L);
        commentaire2.setBody("Commentaire 2");

        List<Commentaire> commentaires = Arrays.asList(commentaire1, commentaire2);

        // Simulation du comportement du repository
        when(commentaireRepository.findAll()).thenReturn(commentaires);

        // Appel de la méthode à tester
        List<Commentaire> result = commentaireService.getListCommentaires();

        // Vérification
        assertEquals(2, result.size());
        assertEquals("Commentaire 1", result.get(0).getBody());
        assertEquals("Commentaire 2", result.get(1).getBody());
    }
    
    
    @Test
    public void testGetCommentaireByIdWhenFound() {
        // Création d'un commentaire factice
        Commentaire commentaire = new Commentaire();
        commentaire.setCommentId(1L);
        commentaire.setBody("Commentaire test");

        // Simulation du comportement du repository
        when(commentaireRepository.findById(1L)).thenReturn(Optional.of(commentaire));

        // Appel de la méthode à tester
        Commentaire result = commentaireService.getCommentaireById(1L);

        // Vérification
        assertNotNull(result);
        assertEquals("Commentaire test", result.getBody());
    }

    @Test
    public void testGetCommentaireByIdWhenNotFound() {
        // Simulation du comportement du repository
        when(commentaireRepository.findById(1L)).thenReturn(Optional.empty());

        // Appel de la méthode à tester et vérification de l'exception
        assertThrows(NoSuchElementException.class, () -> {
            commentaireService.getCommentaireById(1L);
        });
    }
}

