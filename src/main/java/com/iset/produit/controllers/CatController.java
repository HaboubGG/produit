package com.iset.produit.controllers;

import com.iset.produit.entities.Categorie;
import com.iset.produit.entities.Produit;
import com.iset.produit.service.CategorieService;
import com.iset.produit.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CatController {
    @Autowired
    ProduitService produitService;
    @Autowired
    CategorieService categorieService;
    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap) {
        modelMap.addAttribute("produit", new Produit());
        return "createProduit";
    }
    /*
     * @RequestMapping("/showCreate") public String showCreate() { return
     * "createProduit"; }
     */
    @RequestMapping("/saveProduit")
    public String saveProduit(@Valid Produit produit,
                              BindingResult bindingResult,
                              ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "createProduit";
        }
        Produit saveProduit = produitService.saveProduit(produit);
        String msg = "produit enregistré avec Id " +
                saveProduit.getIdProduit();
        modelMap.addAttribute("msg", msg);
        return "createProduit";
    }
    @RequestMapping("/saveCategorie")
    public String saveCategorie(@Valid Categorie categorie,
                              BindingResult bindingResult,
                              ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "createCategorie";
        }
        Categorie saveCategorie = categorieService.saveCategorie(categorie);
        String msg = "Categorie enregistré avec Id " +
                saveCategorie.getIdCat();
        modelMap.addAttribute("msg", msg);
        return "createCategorie";
    }
    @RequestMapping("/ListeCategories")
    public String listeCategories(
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size)
    {
        Page<Categorie> Categories = categorieService.getAllCategoriesParPage(page, size);
        modelMap.addAttribute("categories", Categories);
        modelMap.addAttribute("pages", new int[Categories.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "listeCategories";
    }



    @RequestMapping("/ListeProduits")
    public String listeProduits(
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size)
    {
        Page<Produit> prods = produitService.getAllProduitsParPage(page, size);
        modelMap.addAttribute("produits", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "listeProduits";
    }


    @RequestMapping("/supprimerProduit")
    public String supprimerProduit(@RequestParam("id") Long id, ModelMap
            modelMap,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "2") int size) {
        produitService.deleteProduitById(id);
        Page<Produit> prods = produitService.getAllProduitsParPage(page,
                size);
        modelMap.addAttribute("produits", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeProduits";
    }
    @RequestMapping("/supprimerCategorie")
    public String supprimerCategorie(@RequestParam("id") Long id, ModelMap
            modelMap,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "2") int size) {
        categorieService.deleteCategorieById(id);;
        Page<Categorie> categories = categorieService.getAllCategoriesParPage(page,
                size);

        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("pages", new int[categories.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeCategories";
    }


    @RequestMapping("/modifierProduit")
    public String editerProduit(@RequestParam("id") Long id,ModelMap modelMap)
    {
        Produit p= produitService.getProduit(id);
        modelMap.addAttribute("produit", p);
        return "createProduit";
    }

    @RequestMapping("/modifierCategorie")
    public String editerCategorie(@RequestParam("id") Long id,ModelMap modelMap)
    {
        Categorie c = categorieService.getCategorie(id);
        modelMap.addAttribute("categorie", c);
        return "editerCategorie";
    }
    @RequestMapping("/updateProduit")
    public String updateProduit(@ModelAttribute("produit") Produit produit,
                                @RequestParam("date") String date,
                                ModelMap modelMap) throws ParseException {
        //conversion de la date
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateformat.parse(String.valueOf(date));
        produit.setDateCreation(dateCreation);
        produitService.updateProduit(produit);
        List<Produit> prods = produitService.getAllProduits();
        modelMap.addAttribute("produits", prods);
        return "listeProduits";
    }

    @RequestMapping("/updateCategorie")
    public String updateCategorie(@ModelAttribute("categorie") Categorie categorie,
                                ModelMap modelMap,
                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "2") int size
    ) {
        categorieService.updateCategorie(categorie ,categorie.getIdCat());
        Page<Categorie> Categories = categorieService.getAllCategoriesParPage(page, size);
        modelMap.addAttribute("categories", Categories);
        modelMap.addAttribute("pages", new int[Categories.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "listeCategories";
    }
    }