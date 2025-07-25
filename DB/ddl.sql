CREATE TABLE users (
    user_id VARCHAR(10) PRIMARY KEY CHECK (user_id ~ '^[A-Z][0-9]{9}$'),
    user_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE user_accounts (
    user_id VARCHAR(10) NOT NULL,
    account_no VARCHAR(20) NOT NULL,
    PRIMARY KEY (user_id, account_no),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE products (
    no VARCHAR(50) PRIMARY KEY,
    product_name VARCHAR(200) NOT NULL,
    price DECIMAL(10,2) NOT NULL CHECK (price > 0),
    fee_rate DECIMAL(5,4) NOT NULL CHECK (fee_rate >= 0 AND fee_rate <= 1)
);

CREATE TABLE like_list (
    sn SERIAL PRIMARY KEY,
    user_id VARCHAR(10) NOT NULL,
    product_no VARCHAR(50) NOT NULL,
    order_name INTEGER NOT NULL,
    account VARCHAR(20) NOT NULL,
    total_fee DECIMAL(10,2),
    total_amount DECIMAL(10,2),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (product_no) REFERENCES products(no),
    UNIQUE(user_id, product_no, account)
);