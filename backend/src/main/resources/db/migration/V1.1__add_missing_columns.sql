ALTER TABLE tb_questions
    ADD COLUMN language VARCHAR(50),
    ADD COLUMN correct_alternative VARCHAR(10),
    ADD COLUMN alternatives_introduction TEXT;