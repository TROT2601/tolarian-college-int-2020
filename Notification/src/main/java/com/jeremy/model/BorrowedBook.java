package com.jeremy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer borrowedBook = 0;
}
