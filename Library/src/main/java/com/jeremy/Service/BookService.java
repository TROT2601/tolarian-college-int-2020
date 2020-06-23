package com.jeremy.Service;

import com.jeremy.Repository.BookRepository;
import com.jeremy.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements BaseService<Book, String>{

    private final BookRepository bookRepository; //Initializer

    public BookService(BookRepository bookRepository){ //constructor
        this.bookRepository = bookRepository;
    }

    @Override
    public void create(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void update(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(String id) {
        return bookRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<Book> saveAll(List<Book> b) {
        return bookRepository.saveAll(b);
    }

    public boolean verifyAndUpdate(Book book) {
        Book cbook = findById(book.getCodBook());
        if (cbook != null) {
            boolean EVI = cbook.getStock() > book.getBorrowedBooks();
            if (EVI) {
                cbook.setStock(cbook.getStock() - book.getBorrowedBooks());
                update(cbook);
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}
