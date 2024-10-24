package br.com.ekan.application.services;

import br.com.ekan.adapters.output.persistence.BeneficiaryRepository;
import br.com.ekan.adapters.output.persistence.DocumentRepository;
import br.com.ekan.application.dto.*;
import br.com.ekan.domain.model.Beneficiary;
import br.com.ekan.domain.model.Document;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BeneficiaryService {

    private BeneficiaryRepository beneficiaryRepository;

    private DocumentRepository documentRepository;

    public BeneficiaryService(BeneficiaryRepository beneficiaryRepository, DocumentRepository documentRepository) {
        this.beneficiaryRepository = beneficiaryRepository;
        this.documentRepository = documentRepository;
    }

    public Beneficiary createBeneficiary(BeneficiaryCreateDTO beneficiaryDTO) {

        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setName(beneficiaryDTO.getName());
        beneficiary.setPhone(beneficiaryDTO.getPhone());
        beneficiary.setBirthDate(beneficiaryDTO.getBirthDate());
        beneficiary.setCreateAt(LocalDate.now());

        List<Document> documents = beneficiaryDTO.getDocuments().stream().map(docDTO -> {
            Document document = new Document();
            document.setDocumentType(docDTO.getDocumentType());
            document.setDescription(docDTO.getDescription());
            document.setCreateAt(LocalDate.now());
            document.setBeneficiary(beneficiary);
            return document;
        }).toList();

        beneficiary.setDocuments(documents);

        return beneficiaryRepository.save(beneficiary);
    }

    public List<BeneficiaryDTO> findAll() {
        return beneficiaryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public BeneficiaryDTO updateBeneficiary(Long id, BeneficiaryUpdateDTO updateBeneficiaryDTO) {
        Optional<Beneficiary> optionalBeneficiary = beneficiaryRepository.findById(id);

        if (optionalBeneficiary.isPresent()) {
            Beneficiary beneficiary = optionalBeneficiary.get();

            beneficiary.setName(updateBeneficiaryDTO.getName());
            beneficiary.setPhone(updateBeneficiaryDTO.getPhone());
            beneficiary.setBirthDate(updateBeneficiaryDTO.getBirthDate());
            beneficiary.setUpdateDate(LocalDate.now());

            if (updateBeneficiaryDTO.getDocuments() != null) {
                for (DocumentUpdateDTO updateDocumentDTO : updateBeneficiaryDTO.getDocuments()) {
                    Optional<Document> optionalDocument = documentRepository.findById(updateDocumentDTO.getId());

                    if (optionalDocument.isPresent()) {
                        Document document = optionalDocument.get();
                        document.setDocumentType(updateDocumentDTO.getDocumentType());
                        document.setDescription(updateDocumentDTO.getDescription());
                        document.setUpdateDate(LocalDate.now());
                        documentRepository.save(document);
                    }
                }
            }

            beneficiaryRepository.save(beneficiary);
            return convertToDTO(beneficiary);
        } else {
            throw new RuntimeException("Beneficiary not found with id " + id);
        }
    }

    public void deleteBeneficiary(Long id) {
        Optional<Beneficiary> optionalBeneficiary = beneficiaryRepository.findById(id);

        if (optionalBeneficiary.isPresent()) {
            documentRepository.deleteByBeneficiaryId(id);
            beneficiaryRepository.delete(optionalBeneficiary.get());
        } else {
            throw new RuntimeException("Beneficiary not found with id " + id);
        }
    }

    private BeneficiaryDTO convertToDTO(Beneficiary beneficiary) {
        BeneficiaryDTO dto = new BeneficiaryDTO();
        dto.setId(beneficiary.getId());
        dto.setName(beneficiary.getName());
        dto.setPhone(beneficiary.getPhone());
        dto.setBirthDate(beneficiary.getBirthDate());
        dto.setCreateAt(beneficiary.getCreateAt());
        dto.setUpdateDate(beneficiary.getUpdateDate());

        List<DocumentDTO> documentDTOs = beneficiary.getDocuments().stream()
                .map(this::convertDocumentToDTO)
                .collect(Collectors.toList());
        dto.setDocuments(documentDTOs);

        return dto;
    }

    private DocumentDTO convertDocumentToDTO(Document document) {
        DocumentDTO dto = new DocumentDTO();
        dto.setId(document.getId());
        dto.setDocumentType(document.getDocumentType());
        dto.setDescription(document.getDescription());
        dto.setCreateAt(document.getCreateAt());
        dto.setUpdateDate(document.getUpdateDate());
        return dto;
    }

}
