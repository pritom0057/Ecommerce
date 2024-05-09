-- Generate 100 random name, phone, and email customers
INSERT INTO "customer" ("first_name", "last_name", "email", "address", "phone_number")
SELECT
    first_names.first_name,
    last_names.last_name,
    'email' || (ROW_NUMBER() OVER ()) || '@example.com' AS "email",
    'Address ' || (ROW_NUMBER() OVER ()) AS "address",
    '555-' || LPAD((FLOOR(RANDOM() * 1000000000)::TEXT), 9, '0') AS "phone_number"
FROM
    (SELECT unnest(ARRAY['John', 'Jane', 'Robert', 'Mary', 'Michael', 'Elizabeth', 'William', 'Susan', 'David', 'Karen', 'Daniel', 'Nancy', 'James', 'Lisa', 'Joseph', 'Margaret', 'Richard', 'Sarah', 'Thomas', 'Jessica']) AS first_name) AS first_names
CROSS JOIN
    (SELECT unnest(ARRAY['Smith', 'Johnson', 'Williams', 'Jones', 'Brown', 'Davis', 'Miller', 'Wilson', 'Moore', 'Taylor', 'Anderson', 'Thomas', 'Jackson', 'White', 'Harris', 'Martin', 'Thompson', 'Garcia', 'Martinez', 'Robinson']) AS last_name) AS last_names
LIMIT 100;
