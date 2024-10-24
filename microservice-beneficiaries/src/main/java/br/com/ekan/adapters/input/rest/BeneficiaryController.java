package br.com.ekan.adapters.input.rest;

import br.com.ekan.application.dto.BeneficiaryCreateDTO;
import br.com.ekan.application.dto.BeneficiaryDTO;
import br.com.ekan.application.dto.BeneficiaryUpdateDTO;
import br.com.ekan.application.services.BeneficiaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficiaries")
public class BeneficiaryController {

    private BeneficiaryService beneficiaryService;

    public BeneficiaryController(BeneficiaryService beneficiaryService) {
        this.beneficiaryService = beneficiaryService;
    }

    @PostMapping
    public ResponseEntity<BeneficiaryCreateDTO> createBeneficiary(@RequestBody BeneficiaryCreateDTO beneficiaryDTO) {
        beneficiaryService.createBeneficiary(beneficiaryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(beneficiaryDTO);
    }

    @GetMapping
    public ResponseEntity<List<BeneficiaryDTO>> getAllBeneficiaries() {
        List<BeneficiaryDTO> beneficiaries = beneficiaryService.findAll();
        return ResponseEntity.ok(beneficiaries);
    }

    @PutMapping("/{id}")
    public BeneficiaryDTO updateBeneficiary(@PathVariable Long id, @RequestBody BeneficiaryUpdateDTO beneficiaryDTO) {
        return beneficiaryService.updateBeneficiary(id, beneficiaryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBeneficiary(@PathVariable Long id) {
        beneficiaryService.deleteBeneficiary(id);
        return ResponseEntity.ok("Beneficiary deleted successfully.");
    }
}
