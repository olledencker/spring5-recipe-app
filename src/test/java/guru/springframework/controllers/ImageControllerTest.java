package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class ImageControllerTest {
    @Mock
    ImageService imageService;
    @Mock
    RecipeService recipeService;

    ImageController imageController;
    MockMvc mockMvc;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageController = new ImageController(recipeService,imageService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    public void getFormImage() throws Exception {
        RecipeCommand command = RecipeCommand.builder().id(1L).build();
        when(recipeService.findCommandById(anyLong())).thenReturn(command);
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void saveImage() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("imagefile", "testing.txt","text/plain", "OlleTEtart".getBytes());
        mockMvc.perform(MockMvcRequestBuilders.multipart("/recipe/1/image").file(mockMultipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/recipe/1/show"));
        verify(imageService, times(1)).saveImage(anyLong(),any());
    }
    @Test
    public void displayImage() throws Exception {
        //given
        String s = "image olle text";
        Byte[] byteBoxed = new Byte[s.getBytes().length];
        int i = 0;
        for (byte b :s.getBytes()){
            byteBoxed[i++]= b;
        }
        RecipeCommand command = RecipeCommand.builder().id(1L).build();
        command.setImage(byteBoxed);
        when(recipeService.findCommandById(anyLong())).thenReturn(command);
        //when
       MockHttpServletResponse response =  mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/recipeimage"))
               .andExpect(status().isOk())
               .andReturn().getResponse();
       byte[] bytes = response.getContentAsByteArray();
       assertEquals(bytes.length, s.getBytes().length);
    }

}