-- CREATE schema parkshark;
-- Set schema parkshark;
CREATE TABLE parkshark.divisions (
                                     division_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                                     "name" text NOT NULL,
                                     original_name text NOT NULL,
                                     director_full_name text NOT NULL,
                                     CONSTRAINT division_pk PRIMARY KEY (division_id)
);
CREATE TABLE parkshark.subdivisions (
                                        division_id bigint NOT NULL,
                                        parent_division_id bigint NOT NULL,
                                        CONSTRAINT subdivisions_pk PRIMARY KEY (division_id),
                                        CONSTRAINT subdivisions_check CHECK (division_id <> parent_division_id),
                                        CONSTRAINT subdivisions_fk FOREIGN KEY (parent_division_id) REFERENCES parkshark.divisions(division_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                        CONSTRAINT subdivisions_fk_1 FOREIGN KEY (division_id) REFERENCES parkshark.divisions(division_id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE parkshark.parking_categories (
                                              parking_category_id int NOT NULL GENERATED ALWAYS AS IDENTITY,
                                              parking_category_name text NOT NULL,
                                              CONSTRAINT parking_categories_pk PRIMARY KEY (parking_category_id),
                                              CONSTRAINT parking_categories_un UNIQUE (parking_category_name)
);
CREATE TABLE parkshark.addresses (
                                     address_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                                     street_name text NOT NULL,
                                     street_number text NOT NULL,
                                     postal_code text NOT NULL,
                                     postal_label text NOT NULL,
                                     CONSTRAINT address_pk PRIMARY KEY (address_id)
);
CREATE TABLE parkshark.persons (
                                   person_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                                   address_id bigint NOT NULL,
                                   first_name text NOT NULL,
                                   last_name text NOT NULL,
                                   CONSTRAINT persons_pk PRIMARY KEY (person_id),
                                   CONSTRAINT persons_fk FOREIGN KEY (address_id) REFERENCES parkshark.addresses(address_id) ON UPDATE CASCADE
);
CREATE TABLE parkshark.parking_lots (
                                        parking_lot_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                                        "name" text NOT NULL,
                                        max_capacity int NOT NULL,
                                        price_per_hour numeric NOT NULL,
                                        contact_person_id bigint NOT NULL,
                                        address_id bigint NOT NULL,
                                        division_id bigint NOT NULL,
                                        parking_category_id int NOT NULL,
                                        CONSTRAINT parking_lots_pk PRIMARY KEY (parking_lot_id),
                                        CONSTRAINT parking_lots_check_max_capacity_non_negative CHECK (max_capacity >= 0),
                                        CONSTRAINT parking_lots_check_price_per_hour_non_negative CHECK (price_per_hour >= 0.00),
                                        CONSTRAINT parking_lots_fk FOREIGN KEY (contact_person_id) REFERENCES parkshark.persons(person_id) ON UPDATE CASCADE,
                                        CONSTRAINT parking_lots_fk_1 FOREIGN KEY (address_id) REFERENCES parkshark.addresses(address_id) ON UPDATE CASCADE,
                                        CONSTRAINT parking_lots_fk_2 FOREIGN KEY (division_id) REFERENCES parkshark.divisions(division_id) ON UPDATE CASCADE,
                                        CONSTRAINT parking_lots_fk_3 FOREIGN KEY (parking_category_id) REFERENCES parkshark.parking_categories(parking_category_id) ON UPDATE CASCADE
);
CREATE TABLE parkshark.contact_types (
                                         contact_type_id int NOT NULL GENERATED ALWAYS AS IDENTITY,
                                         "name" text NOT NULL,
                                         CONSTRAINT contact_types_pk PRIMARY KEY (contact_type_id),
                                         CONSTRAINT contact_types_un UNIQUE ("name")
);
CREATE TABLE parkshark.contact_data (
                                        contact_data_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                                        person_id bigint NOT NULL,
                                        contact_type_id int NOT NULL,
                                        "data" text NOT NULL,
                                        CONSTRAINT contact_data_pk PRIMARY KEY (contact_data_id),
                                        CONSTRAINT contact_data_fk FOREIGN KEY (person_id) REFERENCES parkshark.persons(person_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                        CONSTRAINT contact_data_fk_1 FOREIGN KEY (contact_type_id) REFERENCES parkshark.contact_types(contact_type_id) ON UPDATE CASCADE
);
CREATE TABLE parkshark.membership_levels (
                                             membership_level_id int NOT NULL GENERATED ALWAYS AS IDENTITY,
                                             "name" text NOT NULL,
                                             CONSTRAINT membership_levels_pk PRIMARY KEY (membership_level_id),
                                             CONSTRAINT membership_levels_un UNIQUE ("name")
);
CREATE TABLE parkshark.users (
                                 user_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                                 username text NOT NULL,
                                 "password" text NOT NULL,
                                 validated boolean NOT NULL DEFAULT FALSE,
                                 CONSTRAINT users_pk PRIMARY KEY (user_id),
                                 CONSTRAINT users_un UNIQUE (username)
);
CREATE TABLE parkshark.members (
                                   member_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
                                   person_id bigint NOT NULL,
                                   membership_level_id int NOT NULL,
                                   registration_date date NOT NULL DEFAULT CURRENT_DATE,
                                   CONSTRAINT members_pk PRIMARY KEY (member_id),
                                   CONSTRAINT members_fk FOREIGN KEY (person_id) REFERENCES parkshark.persons(person_id) ON UPDATE CASCADE,
                                   CONSTRAINT members_fk_1 FOREIGN KEY (membership_level_id) REFERENCES parkshark.membership_levels(membership_level_id) ON UPDATE CASCADE,
                                   CONSTRAINT members_fk_2 FOREIGN KEY (member_id) REFERENCES parkshark.users(user_id) ON UPDATE CASCADE
);
CREATE TABLE parkshark.license_plates (
                                          license_plate_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                                          plate_number text NOT NULL,
                                          country text NOT NULL,
                                          CONSTRAINT license_plates_pk PRIMARY KEY (license_plate_id),
                                          CONSTRAINT license_plates_un UNIQUE (country,plate_number)
);
CREATE TABLE parkshark.member_plates (
                                         member_id bigint NOT NULL,
                                         license_plate_id bigint NOT NULL,
                                         CONSTRAINT member_plates_pk PRIMARY KEY (license_plate_id,member_id),
                                         CONSTRAINT member_plates_fk FOREIGN KEY (member_id) REFERENCES parkshark.members(member_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                         CONSTRAINT member_plates_fk_1 FOREIGN KEY (license_plate_id) REFERENCES parkshark.license_plates(license_plate_id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE parkshark.parking_spot_allocations (
                                                    parking_spot_allocation_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                                                    member_id bigint NOT NULL,
                                                    license_plate_id bigint NOT NULL,
                                                    parking_lot_id bigint NOT NULL,
                                                    start_time timestamptz NOT NULL DEFAULT NOW(),
                                                    stop_time timestamptz NOT NULL DEFAULT NOW(),
                                                    CONSTRAINT parking_spot_allocations_pk PRIMARY KEY (parking_spot_allocation_id),
                                                    CONSTRAINT parking_spot_allocations_fk FOREIGN KEY (member_id) REFERENCES parkshark.members(member_id) ON UPDATE CASCADE,
                                                    CONSTRAINT parking_spot_allocations_fk_1 FOREIGN KEY (license_plate_id) REFERENCES parkshark.license_plates(license_plate_id) ON UPDATE CASCADE,
                                                    CONSTRAINT parking_spot_allocations_fk_2 FOREIGN KEY (parking_lot_id) REFERENCES parkshark.parking_lots(parking_lot_id) ON UPDATE CASCADE
);
CREATE TABLE parkshark.invoices (
                                    invoice_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                                    member_id bigint NOT NULL,
                                    creation_date date NOT NULL DEFAULT CURRENT_DATE,
                                    expiration_date date NOT NULL DEFAULT CURRENT_DATE,
                                    monthly_cost numeric NOT NULL,
                                    CONSTRAINT invoices_pk PRIMARY KEY (invoice_id),
                                    CONSTRAINT invoices_check CHECK (monthly_cost >= 0.00),
                                    CONSTRAINT invoices_fk FOREIGN KEY (member_id) REFERENCES parkshark.members(member_id) ON UPDATE CASCADE
);
CREATE TABLE parkshark.invoice_items (
                                         invoice_item_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                                         invoice_id bigint NOT NULL,
                                         parking_spot_allocation_id bigint NOT NULL,
                                         "cost" numeric NOT NULL,
                                         CONSTRAINT invoice_items_pk PRIMARY KEY (invoice_item_id),
                                         CONSTRAINT invoice_items_check CHECK (cost >= 0.00),
                                         CONSTRAINT invoice_items_fk FOREIGN KEY (invoice_id) REFERENCES parkshark.invoices(invoice_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                         CONSTRAINT invoice_items_fk_1 FOREIGN KEY (parking_spot_allocation_id) REFERENCES parkshark.parking_spot_allocations(parking_spot_allocation_id) ON UPDATE CASCADE
);
CREATE TABLE parkshark.paid_invoices (
                                         invoice_id bigint NOT NULL,
                                         paid_on date NOT NULL DEFAULT CURRENT_DATE,
                                         CONSTRAINT paid_invoices_pk PRIMARY KEY (invoice_id),
                                         CONSTRAINT paid_invoices_fk FOREIGN KEY (invoice_id) REFERENCES parkshark.invoices(invoice_id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE parkshark.roles (
                                 role_id int NOT NULL GENERATED ALWAYS AS IDENTITY,
                                 "name" text NOT NULL,
                                 CONSTRAINT roles_pk PRIMARY KEY (role_id),
                                 CONSTRAINT roles_un UNIQUE ("name")
);
CREATE TABLE parkshark.user_roles (
                                      user_id bigint NOT NULL,
                                      role_id int NOT NULL,
                                      CONSTRAINT user_roles_pk PRIMARY KEY (role_id,user_id),
                                      CONSTRAINT user_roles_fk FOREIGN KEY (role_id) REFERENCES parkshark.roles(role_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                      CONSTRAINT user_roles_fk_1 FOREIGN KEY (user_id) REFERENCES parkshark.users(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);
