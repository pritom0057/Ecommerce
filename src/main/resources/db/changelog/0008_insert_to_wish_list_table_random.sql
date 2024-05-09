-- Generate wishlist items for existing customers
DO $$
DECLARE
    customer_count INTEGER;
BEGIN
    -- Get the total number of existing customers
    SELECT COUNT(*) INTO customer_count FROM "customer";

    -- Loop through each customer to generate wishlist items
    FOR i IN 1..customer_count LOOP
        -- Generate a random number of wishlist items (1 to 5) for each customer
        FOR j IN 1..(random() * 5 + 1)::INT LOOP
            -- Insert a new wishlist item
            INSERT INTO "wishlist" ("customer_id", "product_id", "created_at", "updated_at")
            SELECT
                i,
                product.id,
                CURRENT_TIMESTAMP,
                CURRENT_TIMESTAMP
            FROM
                "product"
            ORDER BY
                random()
            LIMIT
                1;
        END LOOP;
    END LOOP;
END $$;
