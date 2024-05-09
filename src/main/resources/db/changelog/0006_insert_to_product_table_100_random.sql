-- Generate 100 random products with realistic names
INSERT INTO "product" ("name", "description", "price", "stock_quantity", "created_at", "updated_at")
SELECT
    category_names.name || ' ' || (ROW_NUMBER() OVER ()) AS "name",
    'Description for ' || category_names.name || ' ' || (ROW_NUMBER() OVER ()) AS "description",
    ROUND(RANDOM()::NUMERIC * 1000, 2) AS "price",
    FLOOR(RANDOM()::NUMERIC * 1000) AS "stock_quantity",
    CURRENT_TIMESTAMP AS "created_at",
    CURRENT_TIMESTAMP AS "updated_at"
FROM
    (SELECT unnest(ARRAY['Shirt', 'Pants', 'Shoes', 'Hat', 'Socks', 'Jacket', 'Dress', 'Skirt', 'Jeans', 'Sweater']) AS name) AS category_names
CROSS JOIN
    generate_series(1, 100);
