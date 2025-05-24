-- insert sample categories
insert into category (name, description) values
('electronics', 'electronic devices and gadgets'),
('clothing', 'apparel and fashion items'),
('books', 'books and educational materials'),
('home & garden', 'home improvement and gardening supplies'),
('sports', 'sports equipment and accessories'),
('toys', 'toys and games for all ages');

-- insert sample products
insert into product (name, description, available_quantity, price, category_id) values
-- electronics
('laptop', 'high-performance laptop for work and gaming', 25, 999.99, (select id from category where name = 'electronics')),
('smartphone', 'latest smartphone with advanced camera', 50, 699.99, (select id from category where name = 'electronics')),
('wireless headphones', 'noise-cancelling bluetooth headphones', 100, 199.99, (select id from category where name = 'electronics')),
('tablet', '10-inch tablet for entertainment and productivity', 30, 349.99, (select id from category where name = 'electronics')),

-- clothing
('cotton t-shirt', 'comfortable 100% cotton t-shirt', 200, 19.99, (select id from category where name = 'clothing')),
('jeans', 'classic denim jeans in various sizes', 150, 59.99, (select id from category where name = 'clothing')),
('sneakers', 'comfortable running sneakers', 75, 89.99, (select id from category where name = 'clothing')),
('winter jacket', 'warm and waterproof winter jacket', 40, 129.99, (select id from category where name = 'clothing')),

-- books
('programming guide', 'comprehensive guide to modern programming', 80, 45.99, (select id from category where name = 'books')),
('cookbook', 'collection of healthy and delicious recipes', 60, 29.99, (select id from category where name = 'books')),
('mystery novel', 'thrilling mystery novel by bestselling author', 120, 14.99, (select id from category where name = 'books')),

-- home & garden
('coffee maker', 'automatic drip coffee maker', 35, 79.99, (select id from category where name = 'home & garden')),
('garden hose', '50-foot expandable garden hose', 45, 39.99, (select id from category where name = 'home & garden')),
('desk lamp', 'adjustable led desk lamp', 90, 49.99, (select id from category where name = 'home & garden')),

-- sports
('yoga mat', 'non-slip exercise and yoga mat', 110, 24.99, (select id from category where name = 'sports')),
('basketball', 'official size basketball', 85, 29.99, (select id from category where name = 'sports')),
('tennis racket', 'lightweight tennis racket for beginners', 25, 79.99, (select id from category where name = 'sports')),

-- toys
('building blocks', 'colorful building blocks set for kids', 65, 34.99, (select id from category where name = 'toys')),
('board game', 'fun family board game for ages 8+', 40, 24.99, (select id from category where name = 'toys')),
('remote control car', 'fast remote control racing car', 30, 89.99, (select id from category where name = 'toys'));