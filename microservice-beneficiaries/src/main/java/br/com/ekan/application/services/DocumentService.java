package br.com.ekan.application.services;


import br.com.ekan.adapters.output.persistence.DocumentRepository;
import br.com.ekan.application.dto.DocumentDTO;
import br.com.ekan.domain.model.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<DocumentDTO> findDocumentsByBeneficiaryId(Long beneficiaryId) {
        List<Document> documents = documentRepository.findByBeneficiaryId(beneficiaryId);
        return documents.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private DocumentDTO convertToDTO(Document document) {
        DocumentDTO dto = new DocumentDTO();
        dto.setId(document.getId());
        dto.setDocumentType(document.getDocumentType());
        dto.setDescription(document.getDescription());
        dto.setCreateAt(document.getCreateAt());
        dto.setUpdateDate(document.getUpdateDate());
        return dto;
    }
}
