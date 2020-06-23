package com.jeremy;

import com.jeremy.Service.AuthorService;
import com.jeremy.Service.BookService;
import com.jeremy.Service.CategoryService;
import com.jeremy.model.Author;
import com.jeremy.model.Book;
import com.jeremy.model.Category;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LibraryApp implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public LibraryApp(CategoryService categoryService,AuthorService authorService,BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }


    public static void main(String[] args) {
        SpringApplication.run(LibraryApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Category> categories = new ArrayList<>();
        Category category = new Category("LIT131","Literatura","Literatura Comptemporanea","Menos bonita",null);
        //categories.add(new Category("LIT131","Literatura","Literatura Comptemporanea","Menos bonita",null));
        categories.add(category);
        categoryService.saveAll(categories);

        List<Author> authors = new ArrayList<>();
        Author author = new Author("JK245","Fiodor Dostoyeski", LocalDate.of(1852,01,13),"Rusia",null);
        //authors.add(new Author("JK245","Fiodor Dostoyeski", LocalDate.of(1852,01,13),"Rusia",null));
        authors.add(author);
        authorService.saveAll(authors);

        List<Book> books = new ArrayList<>();
        books.add(new Book("LIT362", "978-3-16-148410-0", "Los hermanos Karamazov",category , author,30 ,0 , 125.00));
        books.add(new Book("LIT332", "928-3-14-148410-0", "Crimen y Castigo", category , author,10 ,0 , 119.00));
        books.add(new Book("LIT392", "988-3-14-148230-0", "Los Demonios", category , author,20 ,0 , 101.00));
        bookService.saveAll(books);

    }
}
