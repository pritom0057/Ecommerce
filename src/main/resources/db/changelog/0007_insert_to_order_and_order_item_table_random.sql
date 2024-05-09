DO $$
DECLARE
    customer_count INTEGER;
    order_count INTEGER := 0;
    start_date TIMESTAMP := NOW() - INTERVAL '1 years'; -- Adjust the range as needed
    end_date TIMESTAMP := NOW(); -- Current timestamp
    random_date TIMESTAMP;
    total_price DECIMAL(10, 2);
BEGIN
    -- Get the total number of existing customers
    SELECT COUNT(*) INTO customer_count FROM "customer";

    -- Loop through each customer to generate orders
    FOR i IN 1..customer_count LOOP
        -- Generate a random number of orders for each customer
        FOR j IN 1..(random() * 5 + 1)::INT LOOP
            -- Generate a random order_date within the last 5 years
            random_date := start_date + (random() * (end_date - start_date));

            -- Insert a new order
            INSERT INTO "order" ("customer_id", "order_date", "total_amount", "created_at", "updated_at")
            VALUES (i, random_date, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
            RETURNING id INTO order_count;

            -- Reset total_price for each order
            total_price := 0;

            -- Generate a random number of order items (1 to 5) for each order
            FOR k IN 1..(random() * 5 + 1)::INT LOOP
                -- Get a random product and its price
                SELECT price INTO total_price
                FROM "product"
                ORDER BY random()
                LIMIT 1;

                -- Insert a new order item
                INSERT INTO "order_item" ("order_id", "product_id", "quantity", "unit_price", "total_price", "created_at", "updated_at")
                VALUES (order_count, (SELECT id FROM "product" ORDER BY random() LIMIT 1), FLOOR(random() * 10) + 1, total_price, total_price * (FLOOR(random() * 10) + 1), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
            END LOOP;

            -- Update the total_amount of the order
            UPDATE "order"
            SET "total_amount" = total_price
            WHERE "id" = order_count;
        END LOOP;
    END LOOP;
END $$;
