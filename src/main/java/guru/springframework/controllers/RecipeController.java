package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @RequestMapping("recipe/{id}/show")
    public String showById(@PathVariable  String id, Model model){
        Long longId = new Long(id);
        model.addAttribute("recipe", recipeService.findById(longId));
        return "recipe/show";

    }
    @RequestMapping("recipe/new")
    public String newRecipe(Model model ){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }
    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable  String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(new Long(id)));
        return "recipe/recipeform";
    }
    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedRecipe = recipeService.saveRecipecommand(command);
        return "redirect:/recipe/"+ savedRecipe.getId()+"/show/";
    }
    @GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable  String id, Model model){
        recipeService.deleteById(Long.valueOf(id));
           return "redirect:/";
    }
}
