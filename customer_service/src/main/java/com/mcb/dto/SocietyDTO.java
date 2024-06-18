package com.mcb.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SocietyDTO {
    private Long id;
    private Integer mainPurpose;
    private String companyName;
    private Long entityType;
    private Integer businessActivity;
    private String licenseRequirements;
    private String countryOfIncorporation;
    private String registrationNumber;
    private Date dateOfIncorporation;
    private String directorShareholdersName;
    private String directorPassportNumber;
    private String designatedApplicantName;
    private String emailAddress;
    private Long customerId;
    private Long processingTeamId;
    private Long approverId;
}
