package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {
    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    private final RecipeRepository recipeRepository;

    @Override
    public void saveImage(Long recipeId, MultipartFile image) {
        try {
            Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
            if(recipeOptional.isPresent()){
                Recipe recipe = recipeOptional.get();
                Byte[] byteObject = new Byte[image.getBytes().length];
                int i = 0;
                for(byte b : image.getBytes()){
                    byteObject[i++]=b;
                }
                recipe.setImage(byteObject);
                recipeRepository.save(recipe);
            }
        }catch (Exception e){
            log.error("Error occured when saving image");
            log.error("Error storing image", e);
        }
    }
}
