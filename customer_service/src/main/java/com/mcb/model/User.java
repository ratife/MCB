package com.mcb.model;


import com.mcb.tools.StringListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "lastname", length = 100)
    private String lastname;

    @Column(name = "firstname", length = 100)
    private String firstname;

    @Column(name = "brithday", length = 100)
    private Date brithday;

    @Column(name = "surnameBrithday", length = 100)
    private String surnameBrithday;

    @Column(name = "nicNumber", length = 14)
    private String nicNumber;

    @Convert(converter = StringListConverter.class)
    private List<String>  files = new ArrayList<String>();

    public void addFile(String fileName) {
        List<String> listFile = new ArrayList<String>();
        if(files!=null)
            files.forEach(e->{listFile.add(e);});
        listFile.add(fileName);
        files = listFile;
    }
}

