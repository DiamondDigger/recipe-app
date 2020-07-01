package guru.springframework.recipeapp.controllers;

import guru.springframework.recipeapp.domain.Category;
import guru.springframework.recipeapp.domain.UnitOfMeasure;
import guru.springframework.recipeapp.repositories.CategoryRepository;
import guru.springframework.recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(){

        Optional<Category> optionalCategory = categoryRepository.findByDescription("Mexican");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Pinch");

        System.out.println("Category ID is: " + optionalCategory.get().getId());
        System.out.println("UDM ID is: " + unitOfMeasureOptional.get().getId());

        return "index";
    }
}
