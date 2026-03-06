-- 1. Thêm cột, tạm thời cho phép NULL
ALTER TABLE product
ADD COLUMN image_path VARCHAR(255) NULL;

-- 2. Gán giá trị mặc định cho các bản ghi cũ
UPDATE product
SET image_path = '/default/image.png'; -- hoặc giá trị placeholder

-- 3. Thay đổi cột thành NOT NULL
ALTER TABLE product
MODIFY COLUMN image_path VARCHAR(255) NOT NULL;