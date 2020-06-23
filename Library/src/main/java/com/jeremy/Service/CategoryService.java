package com.jeremy.Service;

import com.jeremy.Repository.CategoryRepository;
import com.jeremy.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements BaseService<Category, String>{

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void create(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void update(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(String id) {
        return categoryRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<Category> saveAll(List<Category> b) {
        return categoryRepository.saveAll(b);
    }
}
