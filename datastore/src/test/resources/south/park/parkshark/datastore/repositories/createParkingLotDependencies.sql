INSERT INTO addresses (address_id, street_name, street_number, postal_code, postal_label) VALUES
(1, 'here street', '33', '1000', 'Bruxelles');
INSERT INTO divisions (name, original_name, director_full_name, parent_division_id) VALUES ('name', 'originalName', 'directorFullName', 1);
INSERT INTO persons (person_id, first_name, last_name, address_id) VALUES (1, 'Test', 'Person', 1);
INSERT INTO parking_categories (parking_category_id, parking_category_name) VALUES (1, 'BELOW_GROUND');
INSERT INTO parking_lots (name, max_capacity, price_per_hour, person_id, address_id, division_id, parking_category_id) VALUES ('NAME', 1500, 15, 1, 1, 1, 1);
INSERT INTO parking_lots (name, max_capacity, price_per_hour, person_id, address_id, division_id, parking_category_id) VALUES ('NAME', 1500, 15, 1, 1, 1, 1);
INSERT INTO parking_lots (name, max_capacity, price_per_hour, person_id, address_id, division_id, parking_category_id) VALUES ('NAME', 1500, 15, 1, 1, 1, 1);