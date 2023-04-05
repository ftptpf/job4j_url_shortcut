CREATE TABLE IF NOT EXISTS websites (
    id SERIAL PRIMARY KEY NOT NULL ,
    site TEXT UNIQUE NOT NULL ,
    login TEXT UNIQUE NOT NULL ,
    password TEXT NOT NULL
);

COMMENT ON TABLE websites IS 'Сайты';
COMMENT ON COLUMN websites.id IS 'Идентификатор сайта';
COMMENT ON COLUMN websites.site IS 'Сайт';
COMMENT ON COLUMN websites.login IS 'Логин сайта';
COMMENT ON COLUMN websites.password IS 'Пароль сайта';