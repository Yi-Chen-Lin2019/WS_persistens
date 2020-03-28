
/* Populate POSTCODES */
INSERT INTO POSTCODE
VALUES
('9200', 'Aalborg', 'Denmark'),
('9400', 'Nørresundby', 'Denmark');

/* Populate ADDRESS */
INSERT INTO ADDRESS
VALUES
('Sofiendalsvej', '60', '9200'),
('Lindfjord', '33', '9400');

/* Populate LEGAL PERSONS */
INSERT INTO LEGAL_PERSON
VALUES
('20208899', 'roger@ucn.dk', 'Person', 1),
('40408899', 'mikas@ucn.dk', 'Organization', 1),
('60608899', 'lin@ucn.dk', 'Person', 2),
('80808899', 'arsenji@ucn.dk', 'Organization',  2),
('90908899', 'radek@ucn.dk', 'Person', 2);

/* Populate PERSON */
INSERT INTO PERSON
VALUES
(1, 'John', 'Johnson'),
(3, 'Peter', 'Peterson'),
(5, 'Andy', 'Anderson')

/* Populate ORGANIZATION */
INSERT INTO ORGANIZATION
VALUES
(2, '001234777', 'The Cowboy Land'),
(4, '001235899', 'Guns and Guns')


/* Populate EMPLOYEES */
INSERT INTO EMPLOYEE_ROLE
VALUES
('101010101', 'Warehouse', '10000', 1),
('202020202', 'Sales', '12000', 3)



/* Populate CUSTOMERS */
/* Expected value for FK {6, 7, 8}, founded FK {1, 2, 3} */
INSERT INTO CUSTOMER_ROLE
VALUES
(1),
(2),
(3);

/* Populate SUPPLIERS */
/* Expected value for FK {6, 7, 8}, founded FK {1, 2, 3} */
INSERT INTO SUPPLIER_ROLE
VALUES
('Guns',2),
('Clothing', 4)




/* Populate PRODUCTS */
/* ProductCOO is not linked with suppliers */
INSERT INTO PRODUCT
VALUES
('00001','Comboy hat', '14.99', '4.99', 'SSSSS1', 'Clothing'),
('00002','Gun replica', '9.99', '1.99', 'SSSSS2', 'Guns'),
('00003','Wooden carriage', '1449.99', '919.99', 'SSSSS3', 'Equipment'),
('00004','Zippo Lighter', '44.99', '23.99', 'SSSSS4', 'Equipment');

/* Populate GUN_REPLICA */
INSERT INTO GUN_REPLICA
VALUES
('9mm', 'Gold', 1)

/* Populate CLOTHING */
INSERT INTO CLOTHING
VALUES
('Medium', 'Brown', 1)

/* Populate EQUIPMENT */
INSERT INTO EQUIPMENT
VALUES
('Vehicle', 'Large wooden carriage designed to transport gold', 3),
('Lighter', 'Liquid gas lighter', 4)

/* Populate DISCOUNTS */
/* There's no attribute to give a name for the Discounts
Discounts are not calculated for different orders
Discount not linked with generic orders*/
INSERT INTO DISCOUNT
VALUES
('0.0', 0),
('0.9', 1),
('0.95', 0);

/* Populate GENERIC_ORDERS */
/* FK (EmployeeId) REFERENCES EMPLOYEES(Id), it will be easier */
INSERT INTO GENERIC_ORDER
VALUES
('Rent', '03/26/2020', '04/26/2020', 1, 1, '1000.50', '101010101'),
('Sale', '03/20/2020', '04/20/2020', 2, 0, '10.50', '202020202'),
('Supply', '03/26/2020', '03/30/2020', 1, 0, '1500.50', '202020202'),
('Sale', '03/26/2020', '04/26/2020', 2, 1, '2500.50', '202020202'),
('Sale', '03/26/2020', '04/26/2020', 1, 1, '50', '101010101');

/* Populate SALE_ORDERS */
INSERT INTO SALE_ORDER
VALUES
(2, 1, 1),
(4, 1, 1),
(5, 3, 2);

/* Populate SUPPLY_ORDERS */
/* */
INSERT INTO SUPPLY_ORDER
VALUES
(3, 1)

/* Populate RENT_CONTRACT */
/* */
INSERT INTO RENT_CONTRACT
VALUES
('2020-06-26', 0)

/* Populate RENT_ORDER */
/* */
INSERT INTO RENT_ORDER
VALUES
(1, 1, 1)

/* Populate SALE_ORDER_LINE */
/* */
INSERT INTO SALE_ORDER_LINE
VALUES
(1, 1, 1),
(2, 2, 1),
(50, 3, 2),
(23, 4, 3);

/* Populate SUPPLY_ORDER_LINE */
/* */
INSERT INTO SUPPLY_ORDER_LINE
VALUES
(500, 3, 1);

/* Populate RENT_ORDER_LINE */
/* */
INSERT INTO RENT_ORDER_LINE
VALUES
(200, 1, 1);

/* Populate STOCK */
/* */
INSERT INTO STOCK
VALUES
('The Van', null),
('The Tavern', 1);

/* Populate INVENTORY_LINE */
/* */
INSERT INTO INVENTORY_LINE
VALUES
(300, 600, 1, 1),
(300, 500, 2, 1),
(300, 300, 3, 1),
(300, 400, 4, 1),
(30, 60, 1, 2),
(30, 50, 2, 2),
(30, 30, 3, 2),
(30, 40, 4, 2);
