-- Criação da tabela para os detalhes da questão
CREATE TABLE question (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    question_index INTEGER NOT NULL,  -- mapeando o campo "index"
    year INTEGER NOT NULL,
    discipline VARCHAR(255),
    context TEXT
);

-- Criação da tabela para as alternativas
CREATE TABLE alternative (
    id BIGSERIAL PRIMARY KEY,
    letter VARCHAR(10) NOT NULL,
    text VARCHAR(2000) NOT NULL,
    file VARCHAR(255),
    is_correct BOOLEAN NOT NULL,
    questao_id BIGINT NOT NULL,
    CONSTRAINT fk_question_detail
        FOREIGN KEY (questao_id)
        REFERENCES question(id)
        ON DELETE CASCADE
);
