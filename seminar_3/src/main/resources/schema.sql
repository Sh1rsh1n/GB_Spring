CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    age INT,
    email VARCHAR(255)
);

INSERT INTO users (name, age, email) VALUES 
('Ivan', 34, 'ivan@mail.ru'),
('Alexey', 45, 'alex@mail.ru'),
('Anna', 23, 'anna@yandex.ru');