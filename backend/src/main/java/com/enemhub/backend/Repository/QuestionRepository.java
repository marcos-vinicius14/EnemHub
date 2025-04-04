package com.enemhub.backend.Repository;

import com.enemhub.backend.Model.QuestionModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionModel, Long> {
    Page<QuestionModel> findQuestionModelByYear(int year, Pageable pageable);
}


