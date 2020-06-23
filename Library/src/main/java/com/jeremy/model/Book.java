package com.jeremy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table
public class Book {

    @Id
    private String codBook;
    private String isbn;
    private String nameBook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @JsonIgnore
    private Author author;

    private Integer stock;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer borrowedBooks = 0;
    private Double priceForLoss;

}
