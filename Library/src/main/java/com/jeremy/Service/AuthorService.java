package com.jeremy.Service;

import com.jeremy.Repository.AuthorRepository;
import com.jeremy.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements BaseService<Author, String> {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void create(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void update(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void delete(Author author) {
        authorRepository.delete(author);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(String id) {
        return authorRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<Author> saveAll(List<Author> b) {
        return authorRepository.saveAll(b);
    }
}
