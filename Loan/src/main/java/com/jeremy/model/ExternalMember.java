package com.jeremy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ExternalMember {

    private String firstName;
    private String lastName;
    private String emailAddress;

}
