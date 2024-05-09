package tn.projetdemo.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Optional;

import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.projetdemo.demo.entities.Article;
import tn.projetdemo.demo.entities.User;
import tn.projetdemo.demo.repository.ArticleRepository;
import tn.projetdemo.demo.repository.UserRepository;
import tn.projetdemo.demo.services.ArticleServiceImpl;

public class ArticleServiceImplTest {

    @Mock
    private ArticleRepository articleRepositoryMock;

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
    }

    @Test
    public void testAddArticle_Success() {
        // Mocking user and article data
        Long userId = 1L;
        User mockUser = new User();
        mockUser.setId(userId);

        Article mockArticle = new Article();
        mockArticle.setTitre("Test Article");
        mockArticle.setContenu("Test Content");
        mockArticle.setCreated(new Date());

        // Stubbing the behavior of userRepositoryMock
        when(userRepositoryMock.getUserById(userId)).thenReturn(mockUser);

        // Stubbing the behavior of articleRepositoryMock
        when(articleRepositoryMock.save(any(Article.class))).thenReturn(mockArticle);

        // Calling the method to test
        Article result = articleService.addArticle(userId, mockArticle);

        // Verifying that userRepositoryMock was called with the correct argument
        verify(userRepositoryMock, times(1)).getUserById(userId);

        // Verifying that articleRepositoryMock was called with the correct argument
        verify(articleRepositoryMock, times(1)).save(any(Article.class));

        // Asserting the result
        assertEquals(mockArticle, result);
    }
    
    
    @Test
    public void testAddListArticle_Success() {
        // Mocking list of articles to be added
        List<Article> articlesToAdd = new ArrayList<>();
        articlesToAdd.add(new Article());
        articlesToAdd.add(new Article());

        // Mocking the behavior of articleRepositoryMock
        when(articleRepositoryMock.saveAll(articlesToAdd)).thenReturn(articlesToAdd);

        // Calling the method to test
        List<Article> result = articleService.addListArticle(articlesToAdd);

        // Verifying that articleRepositoryMock was called with the correct argument
        verify(articleRepositoryMock, times(1)).saveAll(articlesToAdd);

        // Asserting the result
        assertEquals(articlesToAdd, result);
    }
    
    @Test
    public void testUpdateArticle_Success() {
        // Mocking an existing article
        Long articleId = 1L;
        Article existingArticle = new Article();
        existingArticle.setId_article(articleId);
        existingArticle.setTitre("Old Title");
        existingArticle.setContenu("Old Content");
        existingArticle.setCreated(new Date());

        // Mocking the behavior of articleRepositoryMock
        when(articleRepositoryMock.findById(articleId)).thenReturn(Optional.of(existingArticle));
        when(articleRepositoryMock.save(any(Article.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Creating new article data for update
        Article updatedArticleData = new Article();
        updatedArticleData.setTitre("New Title");
        updatedArticleData.setContenu("New Content");

        // Calling the method to test
        Article updatedArticle = articleService.updateArticle(articleId, updatedArticleData);

        // Verifying that articleRepositoryMock was called with the correct argument
        verify(articleRepositoryMock, times(1)).findById(articleId);
        verify(articleRepositoryMock, times(1)).save(any(Article.class));

        // Asserting the result
        assertEquals(articleId, updatedArticle.getId_article());
        assertEquals("New Title", updatedArticle.getTitre());
        assertEquals("New Content", updatedArticle.getContenu());
        assertEquals(existingArticle.getCreated(), updatedArticle.getCreated());
    }
    
    
    @Test
    public void testDeleteArticle_Success() {
        // Mocking an existing article
        Long articleId = 1L;
        Article existingArticle = new Article();
        existingArticle.setId_article(articleId);

        // Mocking the behavior of articleRepositoryMock
        when(articleRepositoryMock.findById(articleId)).thenReturn(Optional.of(existingArticle));

        // Calling the method to test
        articleService.deleteArticle(articleId);

        // Verifying that articleRepositoryMock was called with the correct argument
        verify(articleRepositoryMock, times(1)).findById(articleId);
        verify(articleRepositoryMock, times(1)).deleteById(articleId);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDeleteArticle_NotFound() {
        // Mocking a non-existing article
        Long articleId = 1L;

        // Mocking the behavior of articleRepositoryMock
        when(articleRepositoryMock.findById(articleId)).thenReturn(Optional.empty());

        // Calling the method to test should throw NoSuchElementException
        articleService.deleteArticle(articleId);
    }
    
    
    
    @Test
    public void testGetListArticles() {
        // Création de quelques articles fictifs
        Article article1 = new Article();
        article1.setId_article(1L);
        article1.setTitre("Article 1");

        Article article2 = new Article();
        article2.setId_article(2L);
        article2.setTitre("Article 2");

        // Création d'une liste d'articles fictifs
        List<Article> expectedArticles = new ArrayList<>();
        expectedArticles.add(article1);
        expectedArticles.add(article2);

        // Mocking du comportement du repository pour retourner la liste d'articles fictifs
        when(articleRepositoryMock.findAll()).thenReturn(expectedArticles);

        // Appel de la méthode à tester
        List<Article> actualArticles = articleService.getListArticles();

        // Vérification que la liste retournée est égale à la liste attendue
        assertEquals(expectedArticles, actualArticles);

        // Vérification que le repository a été appelé une seule fois avec la méthode findAll
        verify(articleRepositoryMock, times(1)).findAll();
    }
    
    
    @Test
    public void testGetByTitre() {
        // Titre de l'article à rechercher
        String titre = "Mon titre d'article";

        // Création d'un article fictif avec le titre spécifié
        Article expectedArticle = new Article();
        expectedArticle.setId_article(1L);
        expectedArticle.setTitre(titre);

        // Mocking du comportement du repository pour retourner l'article fictif lorsqu'il est recherché par titre
        when(articleRepositoryMock.findByTitre(titre)).thenReturn(expectedArticle);

        // Appel de la méthode à tester
        Article actualArticle = articleService.getByTitre(titre);

        // Vérification que l'article retourné correspond à celui attendu
        assertEquals(expectedArticle, actualArticle);

        // Vérification que le repository a été appelé une seule fois avec la méthode findByTitre avec le titre spécifié
        verify(articleRepositoryMock, times(1)).findByTitre(titre);
    }
    
    
    @Test
    public void testGetArticlesSWST() {
        // Terme de recherche
        String searchQuery = "example";

        // Liste d'articles fictive
        List<Article> expectedArticles = new ArrayList<>();
        expectedArticles.add(new Article(1L, "Example Article 1", "Lorem ipsum...", null, null, null, null));
        expectedArticles.add(new Article(2L, "Example Article 2", "Lorem ipsum...", null, null, null, null));

        // Mocking du comportement du repository pour retourner la liste d'articles fictive lorsqu'elle est recherchée avec le terme spécifié
        when(articleRepositoryMock.getArticlesSWST("%" + searchQuery + "%")).thenReturn(expectedArticles);

        // Appel de la méthode à tester
        List<Article> actualArticles = articleService.getArticlesSWST(searchQuery);

        // Vérification que la liste d'articles retournée correspond à celle attendue
        assertEquals(expectedArticles, actualArticles);

        // Vérification que le repository a été appelé une seule fois avec la méthode getArticlesSWST avec le terme spécifié
        verify(articleRepositoryMock, times(1)).getArticlesSWST("%" + searchQuery + "%");
    }
    
    @Test
    public void testGetArticleById() {
        // ID de l'article à rechercher
        Long articleId = 123L;

        // Article fictif avec l'ID spécifié
        Article expectedArticle = new Article(articleId, "Example Article", "Lorem ipsum...", null, null, null, null);

        // Mocking du comportement du repository pour retourner l'article fictif lorsqu'il est recherché avec l'ID spécifié
        when(articleRepositoryMock.findById(articleId)).thenReturn(Optional.of(expectedArticle));

        // Appel de la méthode à tester
        Article actualArticle = articleService.getArticleById(articleId);

        // Vérification que l'article retourné correspond à celui attendu
        assertEquals(expectedArticle, actualArticle);

        // Vérification que le repository a été appelé une seule fois avec la méthode findById avec l'ID spécifié
        verify(articleRepositoryMock, times(1)).findById(articleId);
    }
}