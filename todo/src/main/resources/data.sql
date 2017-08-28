CREATE TABLE IF NOT EXISTS Todo(id IDENTITY PRIMARY KEY, done BOOLEAN, text VARCHAR(100));
DELETE FROM Todo;
INSERT INTO Todo VALUES (1, true, 'Prepare presentation');
INSERT INTO Todo VALUES (2, true, 'Procastinate');
INSERT INTO Todo VALUES (3, false, 'Have presentation');