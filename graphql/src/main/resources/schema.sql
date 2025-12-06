CREATE TABLE IF NOT EXISTS users (
    id varchar(255) PRIMARY KEY,
    name VARCHAR(50) NOT NULL DEFAULT 'sri rama',
    email VARCHAR(50) NOT NULL DEFAULT 'srirama@gmail.com',
    age INT NOT NULL DEFAULT 18,
    gender CHAR(1) NOT NULL DEFAULT 'M',
    employment VARCHAR(50) NOT NULL DEFAULT 'SALARIED'
);

CREATE TABLE IF NOT EXISTS address (
    id varchar(255) PRIMARY KEY,
    street VARCHAR(50) NOT NULL DEFAULT 'Darbar',
    city VARCHAR(50) NOT NULL DEFAULT 'Ayodhya',
    state VARCHAR(50) NOT NULL DEFAULT 'Ayodhya',
    country VARCHAR(50) NOT NULL DEFAULT 'India',
    user_id varchar(255) NOT NULL DEFAULT 'sri rama',
    FOREIGN KEY (user_id) REFERENCES users (id)
);