package com.jeremy.Resource;

import com.jeremy.Service.AuthorService;
import com.jeremy.Service.BookService;
import com.jeremy.Service.CategoryService;
import com.jeremy.model.Author;
import com.jeremy.model.Book;
import com.jeremy.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lib")
public class LibraryResource {

    private  CategoryService categoryService;//Initializer
    private  BookService bookService;//Initializer
    private  AuthorService authorService;//Initializer

    public LibraryResource(BookService bookService,
                           AuthorService authorService,
                           CategoryService categoryService){
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    //CRUD Category/Book
    // //Listar libros por categoria
    @GetMapping("/categories/{codCateg}/books")
    public ResponseEntity findBookByCategory(@PathVariable String codCateg){
        Category cCategory = categoryService.findById(codCateg);
        if (cCategory == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        List<Book> books = cCategory.getBooks();
        if (books == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(books, HttpStatus.OK);
    }

    //Listar libros por autor
    @GetMapping("/authors/{codAuthor}/books")
    public ResponseEntity findBookByAuthor(@PathVariable String codAuthor){
        Author cAuthor = authorService.findById(codAuthor);
        if (cAuthor == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        List<Book> books = cAuthor.getBooks();
        if (books == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(books, HttpStatus.OK);
    }

    //Encontrar libro por id en categoria
    @GetMapping("/categories/{codCateg}/books/{codBook}")
    public ResponseEntity findByCodBookC(@PathVariable String codCateg,
                                        @PathVariable String codBook){
        Category cCategory = categoryService.findById(codCateg);
        if (cCategory == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        List<Book> books = cCategory.getBooks();
        if (books == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        Book book = books.stream()
                .filter(p -> codBook.equals(p.getCodBook()))
                .findFirst()
                .orElse(null);

        if (books == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(book, HttpStatus.OK);
    }

    //Encontrar libro por id en autor
    @GetMapping("/authors/{codAuthor}/books/{codBook}")
    public ResponseEntity findByCodBookA(@PathVariable String codAuthor,
                                        @PathVariable String codBook){
        Author cAuthor = authorService.findById(codAuthor);
        if (cAuthor == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        List<Book> books = cAuthor.getBooks();
        if (books == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        Book book = books.stream()
                .filter(p -> codBook.equals(p.getCodBook()))
                .findFirst()
                .orElse(null);

        if (books == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(book, HttpStatus.OK);
    }

    @GetMapping("/books/{codBook}")
    public ResponseEntity findByCodBook(@PathVariable String codBook){
        Book cBook = bookService.findById(codBook);
        if (cBook == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(cBook, HttpStatus.OK);
    }

    //Crear libros con el codigo de autor y el codigo de la categoria
    @PostMapping("/{codCateg}/{codAuthor}/books")
    public ResponseEntity createCB(@PathVariable String codCateg,
                                   @PathVariable String codAuthor,
                                   @RequestBody Book book){
        Category cCategory = categoryService.findById(codCateg);
        if (cCategory == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Author cAuthor = authorService.findById(codAuthor);
        if (cAuthor == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        book.setCategory(cCategory);
        book.setAuthor(cAuthor);
        bookService.create(book);
        return new ResponseEntity(book,HttpStatus.CREATED);
    }

    //Actualizar
    @PutMapping("/{codCateg}/{codAuthor}/books/{codBook}")
    public ResponseEntity update(@PathVariable String codCateg,
                                 @PathVariable String codAuthor,
                                 @PathVariable String codBook,
                                 @RequestBody Book book){
        Category cCategory = categoryService.findById(codCateg);
        if (cCategory == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Author cAuthor = authorService.findById(codAuthor);
        if (cAuthor == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Book cBook = bookService.findById(codBook);
        if (cBook == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        book.setCategory(cCategory);
        book.setAuthor(cAuthor);
        book.setCodBook(codBook);
        bookService.update(book);
        return new ResponseEntity(book,HttpStatus.OK);
    }

    //Eliminar en caso de perdida o retiro de libro
    @DeleteMapping("/books/{codBook}")
    public ResponseEntity remove(@PathVariable String codBook,
                                 @RequestBody Book book){
        Book cBook = bookService.findById(codBook);
        if (cBook == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        book.setCodBook(codBook);
        bookService.delete(book);
        return new ResponseEntity(book,HttpStatus.OK);
    }

    @PostMapping("/books/verifyAndUpdate")
    public ResponseEntity verifyAndUpdate(@RequestBody Book book){
        if(bookService.verifyAndUpdate(book)){
            return new ResponseEntity(book,HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
