package br.com.ekan.application.dto;

import java.time.LocalDate;
import java.util.List;

public class BeneficiaryUpdateDTO {

    private String name;
    private String phone;
    private LocalDate birthDate;
    private List<DocumentUpdateDTO> documents;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<DocumentUpdateDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentUpdateDTO> documents) {
        this.documents = documents;
    }
}
