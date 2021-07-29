ALTER Table videos
    ADD categoria_id BIGINT UNSIGNED DEFAULT 1,
    ADD FOREIGN KEY categoria_id REFERENCES categorias.id ON DELETE SET DEFAULT;