-- =======================
-- V2__insert_initial_data.sql
-- =======================

-- Insert 15 manufactures
INSERT INTO manufacture (name, address, phone, email, description) VALUES
('Manufacture 1', 'Address 1', '0900000001', 'm1@example.com', 'Description 1'),
('Manufacture 2', 'Address 2', '0900000002', 'm2@example.com', 'Description 2'),
('Manufacture 3', 'Address 3', '0900000003', 'm3@example.com', 'Description 3'),
('Manufacture 4', 'Address 4', '0900000004', 'm4@example.com', 'Description 4'),
('Manufacture 5', 'Address 5', '0900000005', 'm5@example.com', 'Description 5'),
('Manufacture 6', 'Address 6', '0900000006', 'm6@example.com', 'Description 6'),
('Manufacture 7', 'Address 7', '0900000007', 'm7@example.com', 'Description 7'),
('Manufacture 8', 'Address 8', '0900000008', 'm8@example.com', 'Description 8'),
('Manufacture 9', 'Address 9', '0900000009', 'm9@example.com', 'Description 9'),
('Manufacture 10', 'Address 10', '0900000010', 'm10@example.com', 'Description 10'),
('Manufacture 11', 'Address 11', '0900000011', 'm11@example.com', 'Description 11'),
('Manufacture 12', 'Address 12', '0900000012', 'm12@example.com', 'Description 12'),
('Manufacture 13', 'Address 13', '0900000013', 'm13@example.com', 'Description 13'),
('Manufacture 14', 'Address 14', '0900000014', 'm14@example.com', 'Description 14'),
('Manufacture 15', 'Address 15', '0900000015', 'm15@example.com', 'Description 15');

-- Insert 30 products, 3 products per manufacture
INSERT INTO product (name, price, description, active, created_at, updated_at, manufacture_id) VALUES
-- Manufacture 1
('Product 1', 100.00, 'Description 1', TRUE, NOW(), NOW(), 1),
('Product 2', 110.00, 'Description 2', TRUE, NOW(), NOW(), 1),
('Product 3', 120.00, 'Description 3', TRUE, NOW(), NOW(), 1),
-- Manufacture 2
('Product 4', 130.00, 'Description 4', TRUE, NOW(), NOW(), 2),
('Product 5', 140.00, 'Description 5', TRUE, NOW(), NOW(), 2),
('Product 6', 150.00, 'Description 6', TRUE, NOW(), NOW(), 2),
-- Manufacture 3
('Product 7', 160.00, 'Description 7', TRUE, NOW(), NOW(), 3),
('Product 8', 170.00, 'Description 8', TRUE, NOW(), NOW(), 3),
('Product 9', 180.00, 'Description 9', TRUE, NOW(), NOW(), 3),
-- Manufacture 4
('Product 10', 190.00, 'Description 10', TRUE, NOW(), NOW(), 4),
('Product 11', 200.00, 'Description 11', TRUE, NOW(), NOW(), 4),
('Product 12', 210.00, 'Description 12', TRUE, NOW(), NOW(), 4),
-- Manufacture 5
('Product 13', 220.00, 'Description 13', TRUE, NOW(), NOW(), 5),
('Product 14', 230.00, 'Description 14', TRUE, NOW(), NOW(), 5),
('Product 15', 240.00, 'Description 15', TRUE, NOW(), NOW(), 5),
-- Manufacture 6
('Product 16', 250.00, 'Description 16', TRUE, NOW(), NOW(), 6),
('Product 17', 260.00, 'Description 17', TRUE, NOW(), NOW(), 6),
('Product 18', 270.00, 'Description 18', TRUE, NOW(), NOW(), 6),
-- Manufacture 7
('Product 19', 280.00, 'Description 19', TRUE, NOW(), NOW(), 7),
('Product 20', 290.00, 'Description 20', TRUE, NOW(), NOW(), 7),
('Product 21', 300.00, 'Description 21', TRUE, NOW(), NOW(), 7),
-- Manufacture 8
('Product 22', 310.00, 'Description 22', TRUE, NOW(), NOW(), 8),
('Product 23', 320.00, 'Description 23', TRUE, NOW(), NOW(), 8),
('Product 24', 330.00, 'Description 24', TRUE, NOW(), NOW(), 8),
-- Manufacture 9
('Product 25', 340.00, 'Description 25', TRUE, NOW(), NOW(), 9),
('Product 26', 350.00, 'Description 26', TRUE, NOW(), NOW(), 9),
('Product 27', 360.00, 'Description 27', TRUE, NOW(), NOW(), 9),
-- Manufacture 10
('Product 28', 370.00, 'Description 28', TRUE, NOW(), NOW(), 10),
('Product 29', 380.00, 'Description 29', TRUE, NOW(), NOW(), 10),
('Product 30', 390.00, 'Description 30', TRUE, NOW(), NOW(), 10);