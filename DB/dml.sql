-- ============================================================================
-- E.SUN Bank Financial Product Preference System - Sample Data
-- ============================================================================

-- Insert sample users
INSERT INTO users (user_id, user_name, email) VALUES
('A123456789', '王o明', 'test@email.com'),
('B987654321', '陳o迅', 'chen@email.com'),
('C456789012', '林o豪', 'lin@email.com');

-- Insert sample user accounts
INSERT INTO user_accounts (user_id, account_no) VALUES
('A123456789', '1111999666'),
('A123456789', '1111888555'),
('B987654321', '2222888777'),
('C456789012', '3333555444'),
('C456789012', '3333444333');

-- Insert products
INSERT INTO products (product_no, product_name, price, fee_rate) VALUES
('2884', '玉山金', 27.50, 0.001425),
('00929', '玉山永續高息ETF', 19.85, 0.001),
('2330', '台積電', 948.00, 0.001425),
('A11', '富邦金融債10年期', 1000.00, 0.0005);

-- Insert sample like_list data
INSERT INTO like_list (user_id, product_no, order_name, account, total_fee, total_amount) VALUES
-- 王o明's like_list
('A123456789', '2884', 100, '1111999666', 3.92, 2753.91),
('A123456789', '2330', 10, '1111999666', 13.51, 9493.51),

-- 陳o迅's like_list
('B987654321', '00929', 200, '2222888777', 3.97, 3973.97);