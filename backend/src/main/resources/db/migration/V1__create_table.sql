
CREATE TABLE tb_questions (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(1000) NOT NULL,
    question_index INTEGER NOT NULL,
    year INTEGER NOT NULL,
    discipline VARCHAR(1000),
    context TEXT
);


CREATE TABLE tb_alternative (
    id BIGSERIAL PRIMARY KEY,
    letter VARCHAR(10) NOT NULL,
    text VARCHAR(2000) NOT NULL,
    file VARCHAR(255),
    is_correct BOOLEAN NOT NULL,
    questions_id BIGINT NOT NULL,
    CONSTRAINT fk_question_detail
        FOREIGN KEY (questions_id)
        REFERENCES tb_questions(id)
        ON DELETE CASCADE
);
