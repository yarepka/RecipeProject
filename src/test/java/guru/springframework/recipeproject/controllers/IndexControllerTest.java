package guru.springframework.recipeproject.controllers;

import guru.springframework.recipeproject.domain.Recipe;
import guru.springframework.recipeproject.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


public class IndexControllerTest {

    IndexController controller;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() throws Exception {

        // given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        Recipe rec = new Recipe();
        rec.setId(2L);
        recipes.add(rec);

        when(recipeService.getRecipes()).thenReturn(recipes);

        // arguments matcher
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        // when
        String index = controller.getIndexPage(model);

        // then
        assertEquals("index", index);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }
}