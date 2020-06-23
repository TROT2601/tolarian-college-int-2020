package com.jeremy.Resource;

import com.jeremy.Service.CategoryService;
import com.jeremy.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lib")
public class CategoryResource {

    private CategoryService categoryService;

    public CategoryResource(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    //CRUD Category
    //Traer la lista
    @GetMapping("/categories")
    public ResponseEntity findAll(){
        List<Category> categories = categoryService.findAll();
        if (categories == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(categories, HttpStatus.OK);
    }

    //Encontrar por Id
    @GetMapping("/categories/{codCateg}")
    public ResponseEntity findByCodCateg(@PathVariable String codCateg){
        Category cCategory = categoryService.findById(codCateg);
        if (cCategory == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(cCategory, HttpStatus.OK);
    }

    //Crear
    @PostMapping("/categories")
    public ResponseEntity createC(@RequestBody Category category){
        categoryService.create(category);
        return new ResponseEntity(category,HttpStatus.CREATED);
    }

    //Actualizar
    @PutMapping("/categories/{codCateg}")
    public ResponseEntity updateC(@PathVariable String codCateg,
                                  @RequestBody Category category){
        Category cCategory = categoryService.findById(codCateg);
        if (cCategory == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        category.setCodCateg(codCateg);
        categoryService.update(category);
        return new ResponseEntity(category,HttpStatus.OK);
    }

    //Eliminar en caso de que por alguna razon extrema se debe quitar toda una seccion de la biblioteca que abarque una categoria
    @DeleteMapping("/categories/{codCateg}")
    public ResponseEntity removeC(@PathVariable String codCateg,
                                  @RequestBody Category category){
        Category cCategory = categoryService.findById(codCateg);
        category.setCodCateg(codCateg);
        if (cCategory == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        categoryService.delete(category);
        return new ResponseEntity(category,HttpStatus.OK);
    }
}
