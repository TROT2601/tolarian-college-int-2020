package com.jeremy.Resource;

import com.jeremy.Service.AuthorService;
import com.jeremy.model.Author;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lib")
public class AuthorResource {

    private AuthorService authorService;

    public AuthorResource(AuthorService authorService){
        this.authorService = authorService;
    }

    //CRUD Author
    //Traer la lista
    @GetMapping("/authors")
    public ResponseEntity findAll(){
        List<Author> authors = authorService.findAll();
        if (authors == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(authors, HttpStatus.OK);
    }

    //Encontrar por Id
    @GetMapping("/authors/{codAuthor}")
    public ResponseEntity findByCodAuthor(@PathVariable String codAuthor){
        Author cAuthor = authorService.findById(codAuthor);
        if (cAuthor == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(cAuthor, HttpStatus.OK);
    }

    //Crear
    @PostMapping("/authors")
    public ResponseEntity createA(@RequestBody Author author){
        authorService.create(author);
        return new ResponseEntity(author,HttpStatus.CREATED);
    }

    //Actualizar
    @PutMapping("/authors/{codAuthor}")
    public ResponseEntity updateA(@PathVariable String codAuthor,
                                  @RequestBody Author author){
        Author cAuthor = authorService.findById(codAuthor);
        if (cAuthor == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        author.setCodAuthor(codAuthor);
        authorService.update(author);
        return new ResponseEntity(author,HttpStatus.OK);
    }

    @DeleteMapping("/authors/{codAuthor}")
    public ResponseEntity removeA(@PathVariable String codAuthor,
                                  @RequestBody Author author){
        Author cAuthor = authorService.findById(codAuthor);
        author.setCodAuthor(codAuthor);
        if (cAuthor == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        authorService.delete(author);
        return new ResponseEntity(author,HttpStatus.OK);
    }

}
