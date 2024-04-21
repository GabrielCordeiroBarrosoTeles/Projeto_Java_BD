create DATABASE pjt_estacio;
use pjt_estacio;
CREATE TABLE IF NOT EXISTS produto (
    idproduto INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(85) NOT NULL,
    qtd INT,
    data_entrega DATE
);
