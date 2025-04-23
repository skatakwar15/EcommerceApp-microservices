-- Ensure the category sequence starts correctly
DO $$
    BEGIN
        IF EXISTS (SELECT 1 FROM information_schema.sequences WHERE sequence_name = 'category_seq') THEN
            ALTER SEQUENCE category_seq RESTART WITH 1; -- Reset category sequence
        END IF;
    END $$;

-- Insert data into category table
-- Note: Ensure these IDs are consistent with the IDs referenced in the product table
INSERT INTO category (id, description, name)
VALUES
    (1, 'Devices primarily used for communication and media consumption', 'Mobile'),
    (2, 'Portable computers for work and entertainment', 'Laptop'),
    (3, 'Electronic devices for home or office assisting in daily tasks', 'Appliances'),
    (4, 'Wearable technology for fitness and health', 'Wearables'),
    (5, 'Accessories like headphones, chargers, and cables', 'Accessories');

-- Ensure the product sequence starts correctly
DO $$
    BEGIN
        IF EXISTS (SELECT 1 FROM information_schema.sequences WHERE sequence_name = 'product_seq') THEN
            ALTER SEQUENCE product_seq RESTART WITH 1; -- Reset product sequence
        END IF;
    END $$;

-- Insert data into product table, referencing valid category IDs
INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES
    (nextval('product_seq'), 'Latest smartphone with 128GB storage and OLED display', 'Smartphone Model X', 100, 899.99, 1),
    (nextval('product_seq'), '5G-enabled phone with advanced triple camera', 'Smartphone Model Y', 80, 1099.99, 1),
    (nextval('product_seq'), 'Affordable smartphone with great battery life', 'Smartphone Model Z', 200, 499.99, 1),
    (nextval('product_seq'), 'High-performance gaming laptop with RTX 3080 GPU', 'Gaming Laptop G1', 50, 2499.99, 2),
    (nextval('product_seq'), 'Lightweight ultrabook with long battery life', 'Ultrabook U2', 70, 1199.99, 2),
    (nextval('product_seq'), 'Budget-friendly laptop with SSD and 8GB RAM', 'Laptop B3', 150, 699.99, 2),
    (nextval('product_seq'), '42-inch smart TV with 4K resolution and HDR support', 'Smart TV S4', 30, 599.99, 3),
    (nextval('product_seq'), 'Energy-efficient washing machine with dryer', 'Washing Machine WM5', 20, 799.99, 3),
    (nextval('product_seq'), 'Compact 800W microwave oven for quick cooking', 'Microwave Oven M6', 40, 149.99, 3),
    (nextval('product_seq'), 'Smart fitness band with heart rate monitoring', 'Fitness Band F7', 120, 49.99, 4),
    (nextval('product_seq'), 'Noise-cancelling wireless headphones', 'Headphones H8', 90, 199.99, 5),
    (nextval('product_seq'), 'Fast charging USB-C cable', 'USB-C Cable U9', 300, 12.99, 5),
    (nextval('product_seq'), 'Portable Bluetooth speaker with deep bass', 'Bluetooth Speaker S10', 100, 129.99, 5),
    (nextval('product_seq'), 'Multi-device wireless charging dock', 'Charging Dock C11', 70, 89.99, 5),
    (nextval('product_seq'), 'Ergonomic wireless mouse for all-day use', 'Wireless Mouse M12', 150, 29.99, 5);