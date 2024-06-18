package com.mcb.model;

import com.mcb.tools.Constants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Society {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer mainPurpose;

    private String companyName;

    private Long entityType;

    private Integer businessActivity;

    private String licenseRequirements;

    private String countryOfIncorporation;

    private String registrationNumber;

    @Temporal(TemporalType.DATE)
    private Date dateOfIncorporation;

    private String directorShareholdersName;

    private String directorPassportNumber;

    private String designatedApplicantName;

    private String emailAddress;


    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = true)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "processing_id", nullable = true)
    private User precessingTeam;

    @ManyToOne
    @JoinColumn(name = "approver_id", nullable = true)
    private User approver;

    public void addUser(User user, int typeUser) {
        switch (typeUser) {
            case Constants.TYPE_USER_CUSTOMER -> setCustomer(user);
            case Constants.TYPE_USER_PROCESS_TEAM -> setPrecessingTeam(user);
            case Constants.TYPE_USER_APPROUVER -> setApprover(user);
            default -> throw new IllegalArgumentException("Invalid user type: " + typeUser);
        }
    }
}
