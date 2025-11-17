INSERT INTO addresses (country, city, street, building, zip)
VALUES ('US', 'New York', '5th Avenue', '100', 10001),
       ('US', 'Los Angeles', 'Sunset Boulevard', '8400', 90069),
       ('US', 'Chicago', 'Michigan Avenue', '200', 60601);

INSERT INTO hotels (address_id, name, description, phone, email)
VALUES (1, 'Grand Plaza Hotel', 'Luxury hotel in the heart of Manhattan with stunning city views', '+1-212-555-0101',
        'info@grandplaza.com'),
       (2, 'Ocean Breeze Resort', 'Beachfront resort with panoramic ocean views and spa facilities', '+1-310-555-0102',
        'hello@oceanbreeze.com'),
       (3, 'City Center Inn', 'Modern downtown hotel perfect for business and leisure travelers', '+1-312-555-0103',
        'contact@citycenter.com');

INSERT INTO hotel_options (name)
VALUES ('PARKING')
     , ('NOT_SMOKING')
     , ('OPEN_POOL')
     , ('TRANSFER')
     , ('GYM')
     , ('24_7_BAR')
     , ('BREAKFAST')
     , ('WI-FI');

INSERT INTO room_types
    (type, description)
VALUES ('STANDARD',
        'Это самый базовый и доступный тип номера в отеле. Обычно он включает кровать, стол, телевизор, ванную комнату. Такой номер подойдет для путешественников, которым не нужны дополнительные удобства.'),
       ('SUPERIOR',
        'Улучшенный номер имеет дополнительные удобства по сравнению с обычным стандартным номером. Это может быть большее пространство, более удобная мебель, улучшенные санузлы и иногда дополнительные услуги, такие как кофемашина или мини-бар.'),
       ('SUITE',
        'Люксы — это более просторные и роскошные номера, часто с отдельными зонами для отдыха, работы и сна. В люксе могут быть несколько комнат, такие как спальня и гостиная, а также дополнительные удобства, такие как джакузи, балкон или кухня.'),
       ('JUNIOR_SUITE',
        'Это разновидность люкса, но менее просторная. Он обычно включает одну большую комнату с зоной для отдыха и спальней, возможно, с дополнительными удобствами, но без разделения на отдельные комнаты, как в полном люксе.'),
       ('FAMILY',
        'Эти номера предназначены для проживания с детьми и могут включать несколько кроватей, более просторные зоны для отдыха, а также дополнительные удобства, такие как игровые площадки или детские стульчики.'),
       ('VIEW_ROOM',
        'Эти номера предлагают гостям панорамный или уникальный вид, например, на море, город, горы или другие достопримечательности. Чаще всего это более дорогие номера.'),
       ('PRESIDENTIAL_SUITE',
        'Это самый роскошный и просторный номер в отеле, предназначенный для VIP-гостей. В таком номере могут быть несколько комнат, включая спальню, кабинет, гостиную, большую ванную комнату с джакузи, а также эксклюзивные удобства и сервис.'),
       ('ECONOMY',
        'Это минималистичный номер с базовыми удобствами. Он часто предлагается по более низкой цене и подходит для краткосрочного проживания или для тех, кто ищет недорогие варианты.'),
       ('BUSINESS',
        'Эти номера ориентированы на деловых путешественников и часто включают дополнительные удобства, такие как рабочий стол, удобный доступ к интернету, а также, возможно, услуги для проведения встреч и конференций.');

INSERT INTO rooms (hotel_id, number, type, description, capacity)
VALUES (1, '101', 1, 'Comfortable room with city view and modern amenities', 2),
       (1, '102', 2, 'Spacious room with king bed and premium city views', 2),
       (1, '103', 4, 'Luxurious suite with separate living area and skyline views', 4),
       (2, '201', 1, 'Ocean-facing room with balcony and beach access', 2),
       (2, '202', 1, 'Premium oceanfront room with king bed and private balcony', 2),
       (2, '203', 3, 'Luxury beachfront suite with separate living area', 4),
       (3, '301', 1, 'Clean and comfortable room perfect for business travelers', 2),
       (3, '302', 1, 'Spacious business room with work desk and city view', 2),
       (3, '303', 8, 'Premium suite with conference area and city panorama', 4);

INSERT INTO room_options (name)
VALUES ('TOWELS')
     , ('WOOD_FLOOR')
     , ('TV')
     , ('FLAT_SCREEN_TV')
     , ('ALARM_SERVICE')
     , ('CATTLE')
     , ('HAIR_DRIER')
     , ('IRON')
     , ('PHONE')
     , ('TOILET_PAPER')
     , ('PETS_ALLOWED');

INSERT INTO customers (id, first_name, last_name, email, phone, password)
VALUES (1, 'John', 'Smith', 'john.smith@email.com', '+1-555-0001',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
       (2, 'Emily', 'Johnson', 'emily.johnson@email.com', '+1-555-0002',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
       (3, 'Michael', 'Brown', 'michael.brown@email.com', '+1-555-0003',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
       (4, 'Sarah', 'Davis', 'sarah.davis@email.com', '+1-555-0004',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
       (5, 'David', 'Wilson', 'david.wilson@email.com', '+1-555-0005',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy');