package guru.springframework.recipeapp.controllers;

import guru.springframework.recipeapp.commands.RecipeCommand;
import guru.springframework.recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/showPage")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("recipe", recipeService.findById(new Long(id)));

        return "recipe/showPage";
    }

    @GetMapping
    @RequestMapping("recipe/new")
    public String newRecipe(Model model){

        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeForm";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model ){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/recipeForm";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand){
       RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);

        return "redirect:/recipe/"  + savedRecipeCommand.getId() + "/showPage";
    }

    @RequestMapping("/recipe/{id}/delete")
    public String deleteById(@PathVariable String id){

        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }

}
