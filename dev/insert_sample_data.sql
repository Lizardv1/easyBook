INSERT INTO addresses (id, country, city, street, building, zip) VALUES
(1, 'US', 'New York', '5th Avenue', '100', 10001),
(2, 'US', 'Los Angeles', 'Sunset Boulevard', '8400', 90069),
(3, 'US', 'Chicago', 'Michigan Avenue', '200', 60601);

INSERT INTO hotels (id, address_id, name, description, phone, email) VALUES
(1, 1, 'Grand Plaza Hotel', 'Luxury hotel in the heart of Manhattan with stunning city views', '+1-212-555-0101', 'info@grandplaza.com'),
(2, 2, 'Ocean Breeze Resort', 'Beachfront resort with panoramic ocean views and spa facilities', '+1-310-555-0102', 'hello@oceanbreeze.com'),
(3, 3, 'City Center Inn', 'Modern downtown hotel perfect for business and leisure travelers', '+1-312-555-0103', 'contact@citycenter.com');

INSERT INTO hotel_options (id, name)
VALUES (1, 'Parking')
     , (2, 'Not smoking');

INSERT INTO hotel_to_option (hotel_id, option_id)
VALUES (1, 1)
     , (2, 1)
     , (2, 2)

INSERT INTO rooms (id, hotel_id, number, type, description, capacity) VALUES
(1, 1, '101', 'Standard', 'Comfortable room with city view and modern amenities', 2),
(2, 1, '102', 'Deluxe', 'Spacious room with king bed and premium city views', 2),
(3, 1, '103', 'Suite', 'Luxurious suite with separate living area and skyline views', 4),
(4, 2, '201', 'Standard Ocean View', 'Ocean-facing room with balcony and beach access', 2),
(5, 2, '202', 'Deluxe Ocean View', 'Premium oceanfront room with king bed and private balcony', 2),
(6, 2, '203', 'Beach Suite', 'Luxury beachfront suite with separate living area', 4),
(7, 3, '301', 'Standard', 'Clean and comfortable room perfect for business travelers', 2),
(8, 3, '302', 'Business', 'Spacious business room with work desk and city view', 2),
(9, 3, '303', 'Executive Suite', 'Premium suite with conference area and city panorama', 4);

INSERT INTO hotel_options (id, name)
VALUES (1, 'Own Shower')
     , (2, 'TV'),
     , (3, 'Radio')

INSERT INTO hotel_to_option (hotel_id, option_id)
VALUES (1, 1)
     , (2, 1)
     , (2, 2)
     , (2, 3)

INSERT INTO customers (id, first_name, last_name, email, phone, password) VALUES
(1, 'John', 'Smith', 'john.smith@email.com', '+1-555-0001', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
(2, 'Emily', 'Johnson', 'emily.johnson@email.com', '+1-555-0002', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
(3, 'Michael', 'Brown', 'michael.brown@email.com', '+1-555-0003', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
(4, 'Sarah', 'Davis', 'sarah.davis@email.com', '+1-555-0004', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
(5, 'David', 'Wilson', 'david.wilson@email.com', '+1-555-0005', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy');

INSERT INTO bookings (room_id, customer_id, check_in_date, check_out_date) VALUES
-- Hotel 1 - Room 101
(1, 1, '2025-11-01', '2025-11-05'),
(1, 2, '2025-11-10', '2025-11-14'),
(1, 3, '2025-11-20', '2025-11-25'),
-- Hotel 1 - Room 201
(2, 2, '2025-11-01', '2025-11-07'),
(2, 4, '2025-11-12', '2025-11-18'),
(2, 5, '2025-11-24', '2025-11-30'),
-- Hotel 1 - Room 301
(3, 3, '2025-11-05', '2025-11-10'),
(3, 1, '2025-11-15', '2025-11-22'),
(3, 4, '2025-11-28', '2025-11-02'),
-- Hotel 2 - Room Ocean-1
(4, 4, '2025-11-01', '2025-11-06'),
(4, 5, '2025-11-11', '2025-11-15'),
(4, 1, '2025-11-22', '2025-11-28'),
-- Hotel 2 - Room Ocean-2
(5, 5, '2025-11-03', '2025-11-08'),
(5, 3, '2025-11-14', '2025-11-19'),
(5, 2, '2025-11-25', '2025-11-30'),
-- Hotel 2 - Room Beach-1
(6, 1, '2025-11-07', '2025-11-12'),
(6, 4, '2025-11-16', '2025-11-21'),
(6, 5, '2025-11-27', '2025-11-03'),
-- Hotel 3 - Room 201
(7, 2, '2025-11-01', '2025-11-06'),
(7, 3, '2025-11-10', '2025-11-14'),
(7, 1, '2025-11-21', '2025-11-27'),
-- Hotel 3 - Room 302
(8, 3, '2025-11-04', '2025-11-09'),
(8, 5, '2025-11-13', '2025-11-17'),
(8, 2, '2025-11-23', '2025-11-29'),
-- Hotel 3 - Room 501
(9, 5, '2025-11-02', '2025-11-07'),
(9, 4, '2025-11-11', '2025-11-16'),
(9, 3, '2025-11-25', '2025-11-01');

