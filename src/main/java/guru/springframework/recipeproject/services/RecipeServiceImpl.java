package guru.springframework.recipeproject.services;

import guru.springframework.recipeproject.domain.Recipe;
import guru.springframework.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe findById(Long id) {

        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);

        if(!optionalRecipe.isPresent()) {
            throw new RuntimeException("Recipe Not Found!");
        }

        return optionalRecipe.get();
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("getRecipes() was called.");

        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().forEach(recipes::add);

        return recipes;
    }
}
