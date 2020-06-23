package com.jeremy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class LoanMessage {

    private ExternalMember externalMember;
    List<BorrowedBook> borrowedBooks;
}
