package com.mcb.dto;

import lombok.Data;

@Data
public class SocietyAddUser {
    private Long idCustomer;
    private Long idUser;
    private int typeUser;
}
