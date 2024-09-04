-- Insert data into the category table
INSERT INTO category (id, description, name) VALUES
                                                 (nextval('category_seq'), 'Electronics devices and gadgets', 'Electronics'),
                                                 (nextval('category_seq'), 'Books from various genres and authors', 'Books'),
                                                 (nextval('category_seq'), 'Home and kitchen appliances', 'Home & Kitchen'),
                                                 (nextval('category_seq'), 'Clothing and fashion accessories', 'Clothing'),
                                                 (nextval('category_seq'), 'Health and beauty products', 'Health & Beauty');


-- Insert data into the product table
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES
                                                                                        (nextval('product_seq'), 'Latest model of smartphone with high-end features', 'Smartphone', 100, 699.99, (SELECT id FROM category WHERE name = 'Electronics')),
                                                                                        (nextval('product_seq'), 'A suspense thriller novel by a bestselling author', 'Mystery Novel', 200, 14.99, (SELECT id FROM category WHERE name = 'Books')),
                                                                                        (nextval('product_seq'), 'A set of non-stick kitchenware for everyday use', 'Non-Stick Cookware Set', 150, 89.99, (SELECT id FROM category WHERE name = 'Home & Kitchen')),
                                                                                        (nextval('product_seq'), 'Designer leather jacket for men', 'Leather Jacket', 50, 199.99, (SELECT id FROM category WHERE name = 'Clothing')),
                                                                                        (nextval('product_seq'), 'Vitamin C serum for skin rejuvenation', 'Vitamin C Serum', 300, 29.99, (SELECT id FROM category WHERE name = 'Health & Beauty')),
                                                                                        (nextval('product_seq'), '4K Ultra HD LED TV with smart features', '4K LED TV', 80, 999.99, (SELECT id FROM category WHERE name = 'Electronics')),
                                                                                        (nextval('product_seq'), 'Romantic comedy novel', 'Romantic Novel', 150, 12.99, (SELECT id FROM category WHERE name = 'Books')),
                                                                                        (nextval('product_seq'), 'Vacuum cleaner with high suction power', 'Vacuum Cleaner', 120, 149.99, (SELECT id FROM category WHERE name = 'Home & Kitchen')),
                                                                                        (nextval('product_seq'), 'Casual t-shirt made from organic cotton', 'Organic Cotton T-Shirt', 200, 19.99, (SELECT id FROM category WHERE name = 'Clothing')),
                                                                                        (nextval('product_seq'), 'Organic face cream with natural ingredients', 'Organic Face Cream', 250, 24.99, (SELECT id FROM category WHERE name = 'Health & Beauty'));
