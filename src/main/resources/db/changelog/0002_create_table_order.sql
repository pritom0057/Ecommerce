DROP TABLE IF EXISTS "order";

CREATE TABLE "order" (
    "id" SERIAL PRIMARY KEY,
    "customer_id" INT NOT NULL,
    "order_date" TIMESTAMP NOT NULL,
    "total_amount" DECIMAL(40,20) NOT NULL,
    "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE FUNCTION update_order_updated_at()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER order_update_updated_at_trigger
BEFORE UPDATE ON "order"
FOR EACH ROW
EXECUTE FUNCTION update_order_updated_at();
