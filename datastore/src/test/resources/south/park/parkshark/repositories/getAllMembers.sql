INSERT INTO persons (first_name, last_name, address_id) VALUES ('Test', 'Person', 1);
INSERT INTO persons (first_name, last_name, address_id) VALUES ('Test', 'Person', 1);
INSERT INTO persons (first_name, last_name, address_id) VALUES ('Test', 'Person', 1);
INSERT INTO persons (first_name, last_name, address_id) VALUES ('Test', 'Person', 1);

INSERT INTO membership_levels (name) VALUES ('BRONZE'), ('SILVER'), ('GOLD');

INSERT INTO members (member_id, membership_level, registration_date, person_id) VALUES (1, 'BRONZE', to_date('22-04-2020', 'DD-MM-YYYY'), 1);
INSERT INTO members (member_id, membership_level, registration_date, person_id) VALUES (2, 'SILVER', to_date('22-04-2000', 'DD-MM-YYYY'), 2);
INSERT INTO members (member_id, membership_level, registration_date, person_id) VALUES (3, 'GOLD', to_date('22-04-1990', 'DD-MM-YYYY'), 3);
INSERT INTO members (member_id, membership_level, registration_date, person_id) VALUES (4, 'BRONZE', to_date('22-04-2019', 'DD-MM-YYYY'), 4);
