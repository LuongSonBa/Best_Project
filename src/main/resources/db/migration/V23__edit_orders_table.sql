-- Đổi tên cột trong bảng cart_items
ALTER TABLE cart_items CHANGE COLUMN product_id computer_id BIGINT NOT NULL;

-- Đổi tên cột trong bảng order_items
ALTER TABLE order_items CHANGE COLUMN product_id computer_id BIGINT NOT NULL;