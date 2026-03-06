-- 1. Xóa cột image_path cũ (kiểu String)
ALTER TABLE product DROP COLUMN image_path;

-- 2. Thêm cột image mới (kiểu LONGBLOB để chứa được ảnh dung lượng lớn)
ALTER TABLE product ADD COLUMN image LONGBLOB;