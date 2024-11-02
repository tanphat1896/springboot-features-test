CREATE TABLE expense (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    date DATE NOT NULL,
    category VARCHAR(100) NOT NULL
);

INSERT INTO expense (description, amount, date, category)
VALUES
    ('Lunch with client', 25.50, '2024-10-25', 'Food');