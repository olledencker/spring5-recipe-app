package guru.springframework.controllers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class IngredientController {
    final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;
    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
    @RequestMapping("recipe/{id}/ingredients")
    public String getIngredients(@PathVariable String id, Model model) {
        log.debug("################3Test ingredients");
        model.addAttribute("recipe", recipeService.findCommandById(new Long(id)));
        return "recipe/ingredient/list";
    }
    @PostMapping
    @RequestMapping("recipe/{id}/ingredient")
    public String saveIngredient(@ModelAttribute IngredientCommand command,@PathVariable String id, Model model) {
        command.setRecipeId(Long.valueOf(id));
        IngredientCommand savedCommand =  ingredientService.saveIngredient(command);
        model.addAttribute("recipe", recipeService.findCommandById(savedCommand.getRecipeId()));
        return "recipe/ingredient/list";
    }
    @GetMapping
    @RequestMapping("recipe/{id}/ingredient/{ingredientId}/show")
    public String getIngredient(@PathVariable String id, @PathVariable String ingredientId, Model model){

        model.addAttribute("ingredient", ingredientService.findByRecipeIdandIngredientId(Long.valueOf(id), Long.valueOf(ingredientId)));
        return "recipe/ingredient/show";
    }
    @GetMapping
    @RequestMapping("recipe/{id}/ingredient/{ingredientId}/update")
    public String updateRecipeIngredient(@PathVariable String id, @PathVariable String ingredientId, Model model){
        log.debug("Ingredientid:"+ ingredientId);
        model.addAttribute("ingredient", ingredientService.findByRecipeIdandIngredientId(Long.valueOf(id), Long.valueOf(ingredientId)));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }
}
