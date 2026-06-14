CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tokens (
    id SERIAL PRIMARY KEY,
    usuario_id INT UNIQUE NOT NULL,
    valor INT NOT NULL CHECK (valor >= 0 AND valor <= 100),
    data_geracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_usuario_token FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE votos (
    id SERIAL PRIMARY KEY,
    usuario_id INT UNIQUE NOT NULL,
    filme_id INT NOT NULL,
    diretor_id INT NOT NULL,
    data_voto TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_usuario_voto FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);


-- ==============================================================================
-- POPULANDO O BANCO DE DADOS
-- ==============================================================================

INSERT INTO usuarios (id, username, senha) VALUES
(1, 'joao.fanti', 'senha123'),  
(2, 'maria.lima', 'senha123'),  
(3, 'pedro.nossol', 'senha123'),
(4, 'ana.voter', 'senha123'),   
(5, 'matheus.kruger', 'senha123');

SELECT setval('usuarios_id_seq', (SELECT MAX(id) FROM usuarios));

INSERT INTO tokens (usuario_id, valor) VALUES
(1, 42), -- joao.fanti tem o token 42
(4, 88), -- ana.voter tem o token 88
(5, 15); -- matheus.kruger tem o token 15

INSERT INTO votos (usuario_id, filme_id, diretor_id) VALUES
(1, 101, 201); -- joao.fanti já votou no filme_id 101 e diretor_id 201.

