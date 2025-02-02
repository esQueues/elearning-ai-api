CREATE TABLE users(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50),
    firstname VARCHAR(255) NOT NULL ,
    lastname VARCHAR(255) NOT NULL ,
    created_at TIMESTAMP
)