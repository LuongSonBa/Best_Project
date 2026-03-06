-- Cho phép NULL ở bảng chính (nếu chưa làm)
ALTER TABLE product MODIFY image_path VARCHAR(255) NULL;

-- QUAN TRỌNG: Cho phép NULL ở bảng Audit
ALTER TABLE product_aud MODIFY image_path VARCHAR(255) NULL;