ALTER TABLE orders 
ADD COLUMN phone_number VARCHAR(20) AFTER status,
ADD COLUMN shipping_address VARCHAR(255) AFTER phone_number;

-- Đồng bộ luôn cái tên cột ngày tháng cho chắc
ALTER TABLE orders CHANGE COLUMN order_date created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

-- Đồng bộ luôn cái cột tổng tiền
ALTER TABLE orders CHANGE COLUMN total_price total_amount DECIMAL(19, 2);