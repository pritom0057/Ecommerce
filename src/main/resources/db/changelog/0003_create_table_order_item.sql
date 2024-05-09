DROP TABLE IF EXISTS "order_item";

CREATE TABLE "order_item" (
    "id" SERIAL PRIMARY KEY,
    "order_id" INT NOT NULL,
    "product_id" INT NOT NULL,
    "quantity" DECIMAL(40,20) NOT NULL,
    "unit_price" DECIMAL(40,20) NOT NULL,
    "total_price" DECIMAL(40,20) NOT NULL,
    "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE FUNCTION update_orderItem_updated_at()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER orderItem_update_updated_at_trigger
BEFORE UPDATE ON "order_item"
FOR EACH ROW
EXECUTE FUNCTION update_orderItem_updated_at();
