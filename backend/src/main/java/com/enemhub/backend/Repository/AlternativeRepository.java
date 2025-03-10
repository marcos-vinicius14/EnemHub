package com.enemhub.backend.Repository;

import com.enemhub.backend.Model.AlternativeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlternativeRepository extends JpaRepository<AlternativeModel, Long> {
}
