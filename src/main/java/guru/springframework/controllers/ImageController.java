package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@Slf4j
public class ImageController {
    private final RecipeService recipeService;
    private final ImageService imageService;

    public ImageController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping("recipe/{id}/image")
    public String getFormImage(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/imageuploadform";

    }

    @PostMapping("recipe/{id}/image")
    public String saveImage(@PathVariable String id, @RequestParam("imagefile")MultipartFile file){
        imageService.saveImage(Long.valueOf(id), file);
        return "redirect:/recipe/"+id+"/show";


    }

    @GetMapping("recipe/{id}/recipeimage")
    public void getImage(@PathVariable String id, HttpServletResponse responce) throws IOException {
        RecipeCommand command = recipeService.findCommandById(Long.valueOf(id));
        if(command.getImage()!=null) {
            byte[] wrapped = new byte[command.getImage().length];
            int i = 0;
            for (byte b : command.getImage()) {
                wrapped[i++] = b;
            }
            responce.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(wrapped);
            IOUtils.copy(is, responce.getOutputStream());

        }else{
            log.error("Can not get image");
            System.out.println("Can not get image");
        }

    }
}
