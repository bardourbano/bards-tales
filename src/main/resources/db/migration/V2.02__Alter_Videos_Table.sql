ALTER Table videos
    ADD categoria_id BIGINT UNSIGNED,
    ADD FOREIGN KEY categoria_id REFERENCES categorias.id;