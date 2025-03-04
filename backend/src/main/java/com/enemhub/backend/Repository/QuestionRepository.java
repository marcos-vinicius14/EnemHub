package com.enemhub.backend.Repository;

import com.enemhub.backend.Model.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionModel, Long> {
}
