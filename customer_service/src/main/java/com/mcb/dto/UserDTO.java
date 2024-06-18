package com.mcb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String lastname;
    private String firstname;
    private Date brithday;
    private String surnameBrithday;
    private String nicNumber;
    private List<String> files;
}