package br.com.ekan.adapters.input.rest;


import br.com.ekan.application.dto.DocumentDTO;
import br.com.ekan.application.services.DocumentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/beneficiaries/{beneficiaryId}/documents")
    public List<DocumentDTO> getDocumentsByBeneficiaryId(@PathVariable Long beneficiaryId) {
        return documentService.findDocumentsByBeneficiaryId(beneficiaryId);
    }
}