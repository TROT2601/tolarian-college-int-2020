package com.jeremy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BorrowedBook {
    private String codBook;
    private String isbn;
    private String nameBook;
    private Integer borrowedBook;
}
