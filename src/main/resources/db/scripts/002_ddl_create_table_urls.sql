CREATE TABLE IF NOT EXISTS urls (
    id SERIAL PRIMARY KEY NOT NULL ,
    url TEXT UNIQUE NOT NULL ,
    code TEXT NOT NULL ,
    counter TEXT NOT NULL
);

COMMENT ON TABLE urls IS 'Сокращенные ссылки';
COMMENT ON COLUMN urls.id IS 'Идентификатор ссылки';
COMMENT ON COLUMN urls.url IS 'Ссылка';
COMMENT ON COLUMN urls.code IS 'Сокращенная ссылка';
COMMENT ON COLUMN urls.counter IS 'Счетчик количества обращения к сокращенной ссылке';