package com.migui.transferenciasbancarias.repository;

import com.migui.transferenciasbancarias.model.IdentificationTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentificationTypesRepository extends JpaRepository<IdentificationTypes, String> {
}
