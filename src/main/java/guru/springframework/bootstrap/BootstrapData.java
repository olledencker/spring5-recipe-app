package guru.springframework.bootstrap;

import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Slf4j
@Component
public class BootstrapData implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public BootstrapData(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("Start run in runner");
        Recipe recipeGuacamole = new Recipe();
        recipeGuacamole.setCookTime(3);
        recipeGuacamole.setDifficulty(Difficulty.MODERATE);
        recipeGuacamole.setDescription("How to Make Perfect Guacamole Recipe");
        recipeGuacamole.setDirections("Work hard");
        recipeGuacamole.setPrepTime(5);
        recipeGuacamole.setServing(3);
        recipeGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        recipeGuacamole.setSource("Simply Recipes");
        Ingredient in1=new Ingredient("ripe avocados", new BigDecimal(2), unitOfMeasureRepository.findByDescription("St").get());
        in1.setRecipe(recipeGuacamole);
        recipeGuacamole.getIngredients().add(in1);
        in1 = new Ingredient("salt, more to taste", new BigDecimal(0.25), unitOfMeasureRepository.findByDescription("Tesked").get());
        in1.setRecipe(recipeGuacamole);
        recipeGuacamole.getIngredients().add(
                in1);
        in1 = new Ingredient(" fresh lime juice or lemon juice", new BigDecimal(1), unitOfMeasureRepository.findByDescription("Tesked").get());
        in1.setRecipe(recipeGuacamole);
        recipeGuacamole.getIngredients().add(
                in1);

        in1 = new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), unitOfMeasureRepository.findByDescription("Tesked").get());
        in1.setRecipe(recipeGuacamole);
        recipeGuacamole.getIngredients().add(
                in1);
        in1 = new Ingredient(" serrano chiles, stems and seeds removed, minced", new BigDecimal(1), unitOfMeasureRepository.findByDescription("St").get());
        in1.setRecipe(recipeGuacamole);
        recipeGuacamole.getIngredients().add(
                in1);
        in1 =  new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), unitOfMeasureRepository.findByDescription("Tesked").get());
        in1.setRecipe(recipeGuacamole);
        recipeGuacamole.getIngredients().add(
                in1);
        in1 = new Ingredient(" freshly grated black pepper", new BigDecimal(1), unitOfMeasureRepository.findByDescription("Stänk").get());
        in1.setRecipe(recipeGuacamole);
        recipeGuacamole.getIngredients().add(
                in1);
        in1 = new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(0.5), unitOfMeasureRepository.findByDescription("St").get());
        in1.setRecipe(recipeGuacamole);
        recipeGuacamole.getIngredients().add(
                in1);
        in1 = new Ingredient(" Red radishes or jicama, to garnish", new BigDecimal(1), unitOfMeasureRepository.findByDescription("St").get());
        in1.setRecipe(recipeGuacamole);
        recipeGuacamole.getIngredients().add(
                in1);
        in1 = new Ingredient(" Tortilla chips, to serve", new BigDecimal(1), unitOfMeasureRepository.findByDescription("Påse").get());
        in1.setRecipe(recipeGuacamole);
        recipeGuacamole.getIngredients().add(
                in1);
        recipeGuacamole.getCategories().add(categoryRepository.findByDescription("Mexikanskt").get());
        Notes note = new Notes("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");
        note.setRecipe(recipeGuacamole);
        recipeGuacamole.setNotes(note);
        recipeRepository.save(recipeGuacamole);
        Recipe spicyChicken = new Recipe();
        spicyChicken.setDescription("Spicy Grilled Chicken Tacos");
        spicyChicken.setCookTime(15);
        spicyChicken.setDifficulty(Difficulty.EASY);
        spicyChicken.setDescription("How to Make Perfect Guacamole Recipe");
        spicyChicken.setDirections("picy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.");
        spicyChicken.setPrepTime(20);
        spicyChicken.setServing(6);
        spicyChicken.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        spicyChicken.setSource("Simply Recipes");
        Ingredient ingredient = new Ingredient("ancho chili powder",
                        new BigDecimal(2), unitOfMeasureRepository.findByDescription("Matsked").get());
        ingredient.setRecipe(spicyChicken);
        spicyChicken.getIngredients().add(
                ingredient);

        ingredient = new Ingredient("dried oregano",
                        new BigDecimal(1), unitOfMeasureRepository.findByDescription("Tesked").get());
        ingredient.setRecipe(spicyChicken);
        spicyChicken.getIngredients().add(
                ingredient);
        ingredient = new Ingredient("dried cumins",
                        new BigDecimal(1), unitOfMeasureRepository.findByDescription("Tesked").get());
        ingredient.setRecipe(spicyChicken);
        spicyChicken.getIngredients().add(
                ingredient);
        ingredient = new Ingredient("sugar",
                        new BigDecimal(1), unitOfMeasureRepository.findByDescription("Tesked").get());
        ingredient.setRecipe(spicyChicken);
        spicyChicken.getIngredients().add(
                ingredient);
        ingredient = new Ingredient("salt",
                        new BigDecimal(0.5), unitOfMeasureRepository.findByDescription("Tesked").get());
        ingredient.setRecipe(spicyChicken);
        spicyChicken.getIngredients().add(
                ingredient);
        ingredient =  new Ingredient("clove garlic, finely chopped",
                        new BigDecimal(1), unitOfMeasureRepository.findByDescription("St").get());
        ingredient.setRecipe(spicyChicken);
        spicyChicken.getIngredients().add(
                ingredient);
        ingredient =  new Ingredient("finely grated orange zest",
                        new BigDecimal(1), unitOfMeasureRepository.findByDescription("Tesked").get());
        ingredient.setRecipe(spicyChicken);
        spicyChicken.getIngredients().add(
                ingredient);
        ingredient = new Ingredient("fresh-squeezed orange juice",
                        new BigDecimal(1), unitOfMeasureRepository.findByDescription("Matsked").get());
        ingredient.setRecipe(spicyChicken);
        spicyChicken.getIngredients().add(
                ingredient);
        ingredient =  new Ingredient("olive oil",
                        new BigDecimal(2), unitOfMeasureRepository.findByDescription("Matsked").get());
        ingredient.setRecipe(spicyChicken);
        spicyChicken.getIngredients().add(
                ingredient);
        ingredient =   new Ingredient("skinless, boneless chicken thighs (1 1/4 pounds)",
                        new BigDecimal(6), unitOfMeasureRepository.findByDescription("St").get());
        ingredient.setRecipe(spicyChicken);
        spicyChicken.getIngredients().add(
                ingredient);
        ingredient =  new Ingredient("small corn tortillas",
                        new BigDecimal(8), unitOfMeasureRepository.findByDescription("St").get());
        ingredient.setRecipe(spicyChicken);
        spicyChicken.getIngredients().add(
                ingredient);
        ingredient =   new Ingredient("packed baby arugula (3 ounces)",
                        new BigDecimal(5), unitOfMeasureRepository.findByDescription("Deciliter").get());
        ingredient.setRecipe(spicyChicken);
        spicyChicken.getIngredients().add(
                ingredient);
        ingredient =   new Ingredient("medium ripe avocados, sliced",
                        new BigDecimal(2), unitOfMeasureRepository.findByDescription("St").get());
        ingredient.setRecipe(spicyChicken);
        spicyChicken.getIngredients().add(
                ingredient);
        ingredient =   new Ingredient("radishes, thinly sliced",
                        new BigDecimal(4), unitOfMeasureRepository.findByDescription("St").get());
        ingredient.setRecipe(spicyChicken);
        spicyChicken.getIngredients().add(
                ingredient);
        ingredient =  new Ingredient("cherry tomatoes, halved",
                        new BigDecimal(5), unitOfMeasureRepository.findByDescription("Deciliter").get());
        ingredient.setRecipe(spicyChicken);
        spicyChicken.getIngredients().add(
                ingredient);
        ingredient =  new Ingredient(" red onion, thinly sliced",
                        new BigDecimal(0.25), unitOfMeasureRepository.findByDescription("St").get());
        ingredient.setRecipe(spicyChicken);
        spicyChicken.getIngredients().add(
                ingredient);
        ingredient =    new Ingredient("Roughly chopped cilantro",
                        new BigDecimal(1), unitOfMeasureRepository.findByDescription("St").get());
        ingredient.setRecipe(spicyChicken);
        spicyChicken.getIngredients().add(
                ingredient);
        ingredient =  new Ingredient("sour cream thinned with 1/4 cup milk",
                        new BigDecimal(1), unitOfMeasureRepository.findByDescription("Deciliter").get());
        ingredient.setRecipe(spicyChicken);
        spicyChicken.getIngredients().add(
                ingredient);
        ingredient =    new Ingredient("lime, cut into wedges\n",
                        new BigDecimal(1), unitOfMeasureRepository.findByDescription("St").get());
        ingredient.setRecipe(spicyChicken);
        spicyChicken.getIngredients().add(
                ingredient);

        spicyChicken.setNotes(new Notes("METHODSHOW PHOTOS\n" +
                "1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges."));
        spicyChicken.getCategories().add(categoryRepository.findByDescription("Mexikanskt").get());
        recipeRepository.save(spicyChicken);

    }
}
