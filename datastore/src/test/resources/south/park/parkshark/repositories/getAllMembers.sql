INSERT INTO persons (first_name, last_name, address_id) VALUES ('Test', 'Person', 1);
INSERT INTO persons (first_name, last_name, address_id) VALUES ('Test', 'Person', 1);
INSERT INTO persons (first_name, last_name, address_id) VALUES ('Test', 'Person', 1);
INSERT INTO persons (first_name, last_name, address_id) VALUES ('Test', 'Person', 1);

INSERT INTO membership_levels  VALUES ('BRONZE'), ('SILVER'), ('GOLD');

INSERT INTO members (member_id, person_id, membership_level_id, registration_date) VALUES (1, 1, 2, to_date('22-04-2020', 'DD-MM-YYYY'));
INSERT INTO members (member_id, person_id, membership_level_id, registration_date) VALUES (2, 2, 3, to_date('02-01-2000', 'DD-MM-YYYY'));
INSERT INTO members (member_id, person_id, membership_level_id, registration_date) VALUES (3, 3, 1, to_date('22-04-1990', 'DD-MM-YYYY'));
INSERT INTO members (member_id, person_id, membership_level_id, registration_date) VALUES (4, 4, 2, to_date('22-04-2020', 'DD-MM-YYYY'));