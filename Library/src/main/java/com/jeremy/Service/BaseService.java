package com.jeremy.Service;

import java.util.List;

public interface BaseService <B,I>{

    void create(B b);
    void update(B b);
    void delete(B b);

    List<B>findAll();
    B findById(I i);

    List<B> saveAll(List<B> b);
}
