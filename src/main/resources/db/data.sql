INSERT INTO registry_db.dbo.address (city, house_number, address_type, street, zip_code, person_id) VALUES (1, N'Budapest', N'15b', N'permanent', N'Kossuth', 1234, null);
INSERT INTO registry_db.dbo.address (city, house_number, address_type, street, zip_code, person_id) VALUES (2, N'Szolnok', N'9', N'permanent', N'Petofi', 5000, 4);
INSERT INTO registry_db.dbo.address (city, house_number, address_type, street, zip_code, person_id) VALUES (3, N'Kecskemét', N'12', N'permanent', N'Ásvány', 6000, null);
INSERT INTO registry_db.dbo.address (city, house_number, address_type, street, zip_code, person_id) VALUES (4, N'Szentendre', N'25', N'temporary', N'Mátyás király', 2000, 1);
INSERT INTO registry_db.dbo.address (city, house_number, address_type, street, zip_code, person_id) VALUES (5, N'Hatvan', N'99', N'temporary', N'Cinege', 3000, null);
INSERT INTO registry_db.dbo.address (city, house_number, address_type, street, zip_code, person_id) VALUES (6, N'Debrecen', N'5', N'temporary', N'Zöldfa', 4000, null);
INSERT INTO registry_db.dbo.address (city, house_number, address_type, street, zip_code, person_id) VALUES (7, N'Sárbogárd', N'1', N'temporary', N'Rózsa', 7000, null);
INSERT INTO registry_db.dbo.address (city, house_number, address_type, street, zip_code, person_id) VALUES (8, N'Felsoszölnök', N'3', N'permanent', N'Régi', 9985, 1);

INSERT INTO registry_db.dbo.person (name, permanent_address, temporary_address) VALUES (1, N'Harmadik Henrietta', 8, 4);
INSERT INTO registry_db.dbo.person (name, permanent_address, temporary_address) VALUES (2, N'Elso Elek', 3, null);
INSERT INTO registry_db.dbo.person (name, permanent_address, temporary_address) VALUES (4, N'Elso Elek3', 2, null);

INSERT INTO registry_db.dbo.contact_detail (identifier, platform, person_id) VALUES (2, N'myVeryOwnSkypeName', N'SKYPE', 1);
INSERT INTO registry_db.dbo.contact_detail (identifier, platform, person_id) VALUES (3, N'myVeryOwnTwitterHandle', N'TWITTER', 2);
INSERT INTO registry_db.dbo.contact_detail (identifier, platform, person_id) VALUES (4, N'+36201234567', N'PHONE', 2);
