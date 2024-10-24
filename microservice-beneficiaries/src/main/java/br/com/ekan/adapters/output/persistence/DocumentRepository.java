package br.com.ekan.adapters.output.persistence;

import br.com.ekan.domain.model.Document;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findByBeneficiaryId(Long beneficiaryId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Document d WHERE d.beneficiary.id = :beneficiaryId")
    void deleteByBeneficiaryId(@Param("beneficiaryId") Long beneficiaryId);

}
