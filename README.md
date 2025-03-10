 EnemHub

(URL/dominio ainda em criação, em breve disponivel)

EnemHub é uma plataforma dedicada a ajudar estudantes a se prepararem para o Exame Nacional do Ensino Médio (ENEM). O projeto tem como objetivo disponibilizar questões do ENEM, permitindo que os usuários pratiquem e se familiarizem com o formato e o estilo das perguntas do exame.
Tecnologias Utilizadas
Back-end

    Java: Linguagem de programação principal.
    Spring Boot: Framework para criação de aplicações Java.
    Spring JPA: Para mapeamento objeto-relacional e acesso ao banco de dados.
    Flyway: Ferramenta de migração de banco de dados.
    PostgreSQL v17: Sistema de gerenciamento de banco de dados relacional.
    Docker: Para containerização e facilitação do ambiente de desenvolvimento.

Front-end

    Next.js: Framework React para aplicações web.
    Tailwind CSS: Framework CSS utilitário para estilização rápida e responsiva.

Funcionalidades

    Questões do ENEM: Acesso a uma coleção de questões de anos anteriores do ENEM.
    Prática e Simulação: Ferramentas para praticar questões individuais ou simular provas completas.
    Feedback Imediato: Respostas corretas e explicações para auxiliar no aprendizado.
    Interface Intuitiva: Design limpo e fácil de usar, focado na experiência do usuário.

Como Contribuir

Contribuições são bem-vindas! Para contribuir com o EnemHub, siga os passos abaixo:

    Faça um fork do repositório.
    Crie uma branch para sua feature (git checkout -b feature/nova-feature).
    Commit suas mudanças (git commit -m 'Adiciona nova feature').
    Push para a branch (git push origin feature/nova-feature).
    Abra um Pull Request.

Instalação e Configuração
Pré-requisitos

    Java 17 ou superior
    Node.js 16 ou superior
    Docker e Docker Compose
    PostgreSQL v17 (ou use o container Docker fornecido)

Back-end

    Clone o repositório:
    bash

git clone https://github.com/seu-usuario/enemhub.git
cd enemhub/backend
Configure as variáveis de ambiente:

    Crie um arquivo .env baseado no .env.example e preencha com suas credenciais (ex.: conexão com o banco de dados).

Inicie o banco de dados com Docker:
bash
docker-compose up -d db
Execute a aplicação Spring Boot:
bash

    ./mvnw spring-boot:run
        As migrações do Flyway serão aplicadas automaticamente ao iniciar a aplicação.

Front-end

    Navegue para o diretório do front-end:
    bash

cd ../frontend
Instale as dependências:
bash
npm install
Inicie o servidor de desenvolvimento:
bash

    npm run dev
    Acesse a aplicação em http://localhost:3000.

Uso

    Explorar Questões: Navegue pelas questões disponíveis, filtradas por ano, disciplina ou tema.
    Simular Provas: Crie simulados personalizados ou pratique com provas completas de anos anteriores.
    Acompanhar Progresso: Monitore seu desempenho e identifique áreas que precisam de mais estudo.

Licença

Este projeto é licenciado sob a MIT License.
