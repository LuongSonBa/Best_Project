UPDATE computer
SET expired_date = NOW() - INTERVAL 1 DAY,
    status = 'ACTIVE';

UPDATE computer
SET expired_date = NOW() + INTERVAL (id - 5) DAY,
    status = 'ACTIVE'
WHERE id BETWEEN 6 AND 12;