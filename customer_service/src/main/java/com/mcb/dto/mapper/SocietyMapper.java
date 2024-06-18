package com.mcb.dto.mapper;

import com.mcb.model.Society;
import com.mcb.dto.SocietyDTO;

public class SocietyMapper {

    public static SocietyDTO toDTO(Society customer) {
        if (customer == null) {
            return null;
        }

        SocietyDTO customerDTO = new SocietyDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setMainPurpose(customer.getMainPurpose());
        customerDTO.setCompanyName(customer.getCompanyName());
        customerDTO.setEntityType(customer.getEntityType());
        customerDTO.setBusinessActivity(customer.getBusinessActivity());
        customerDTO.setLicenseRequirements(customer.getLicenseRequirements());
        customerDTO.setCountryOfIncorporation(customer.getCountryOfIncorporation());
        customerDTO.setRegistrationNumber(customer.getRegistrationNumber());
        customerDTO.setDateOfIncorporation(customer.getDateOfIncorporation());
        customerDTO.setDirectorShareholdersName(customer.getDirectorShareholdersName());
        customerDTO.setDirectorPassportNumber(customer.getDirectorPassportNumber());
        customerDTO.setDesignatedApplicantName(customer.getDesignatedApplicantName());
        customerDTO.setEmailAddress(customer.getEmailAddress());
        customerDTO.setCustomerId(customer.getCustomer() != null ? customer.getCustomer().getId() : null);
        customerDTO.setProcessingTeamId(customer.getPrecessingTeam() != null ? customer.getPrecessingTeam().getId() : null);
        customerDTO.setApproverId(customer.getApprover() != null ? customer.getApprover().getId() : null);

        return customerDTO;
    }

    public static Society toEntity(SocietyDTO customerDTO) {
        if (customerDTO == null) {
            return null;
        }

        Society society = new Society();
        society.setId(customerDTO.getId());
        society.setMainPurpose(customerDTO.getMainPurpose());
        society.setCompanyName(customerDTO.getCompanyName());
        society.setEntityType(customerDTO.getEntityType());
        society.setBusinessActivity(customerDTO.getBusinessActivity());
        society.setLicenseRequirements(customerDTO.getLicenseRequirements());
        society.setCountryOfIncorporation(customerDTO.getCountryOfIncorporation());
        society.setRegistrationNumber(customerDTO.getRegistrationNumber());
        society.setDateOfIncorporation(customerDTO.getDateOfIncorporation());
        society.setDirectorShareholdersName(customerDTO.getDirectorShareholdersName());
        society.setDirectorPassportNumber(customerDTO.getDirectorPassportNumber());
        society.setDesignatedApplicantName(customerDTO.getDesignatedApplicantName());
        society.setEmailAddress(customerDTO.getEmailAddress());


        return society;
    }

    public static Society upDate(Society society, SocietyDTO customerDTO) {

        if (customerDTO.getMainPurpose() != null) {
            society.setMainPurpose(customerDTO.getMainPurpose());
        }

        if (customerDTO.getCompanyName() != null) {
            society.setCompanyName(customerDTO.getCompanyName());
        }

        if (customerDTO.getEntityType() != null) {
            society.setEntityType(customerDTO.getEntityType());
        }

        if (customerDTO.getBusinessActivity() != null) {
            society.setBusinessActivity(customerDTO.getBusinessActivity());
        }

        if (customerDTO.getLicenseRequirements() != null) {
            society.setLicenseRequirements(customerDTO.getLicenseRequirements());
        }

        if (customerDTO.getCountryOfIncorporation() != null) {
            society.setCountryOfIncorporation(customerDTO.getCountryOfIncorporation());
        }

        if (customerDTO.getRegistrationNumber() != null) {
            society.setRegistrationNumber(customerDTO.getRegistrationNumber());
        }

        if (customerDTO.getDateOfIncorporation() != null) {
            society.setDateOfIncorporation(customerDTO.getDateOfIncorporation());
        }

        if (customerDTO.getDirectorShareholdersName() != null) {
            society.setDirectorShareholdersName(customerDTO.getDirectorShareholdersName());
        }

        if (customerDTO.getDirectorPassportNumber() != null) {
            society.setDirectorPassportNumber(customerDTO.getDirectorPassportNumber());
        }

        if (customerDTO.getDesignatedApplicantName() != null) {
            society.setDesignatedApplicantName(customerDTO.getDesignatedApplicantName());
        }

        if (customerDTO.getEmailAddress() != null) {
            society.setEmailAddress(customerDTO.getEmailAddress());
        }


        return society;
    }
}
