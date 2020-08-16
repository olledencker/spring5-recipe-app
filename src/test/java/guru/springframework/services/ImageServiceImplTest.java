package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ImageServiceImplTest {
    ImageService imageService;

    @Mock
    RecipeRepository recipeRepository;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageService = new ImageServiceImpl(recipeRepository);
    }

    @Test
    public void saveImage() throws IOException {
        //Given
        Recipe recipe = Recipe.builder().id(1L).build();
        MultipartFile file = new MockMultipartFile("imagefile", "testing.txt","text/plain", "OlleTEtart".getBytes());
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        ArgumentCaptor<Recipe> argumentCaptor=ArgumentCaptor.forClass(Recipe.class);
        //when
        imageService.saveImage(1L, file);

        //then
        verify(recipeRepository,times(1)).save(argumentCaptor.capture());
        Recipe savedValue = argumentCaptor.getValue();
        assertEquals(file.getBytes().length,savedValue.getImage().length );
    }
}