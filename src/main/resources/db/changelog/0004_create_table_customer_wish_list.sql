DROP TABLE IF EXISTS "wishlist";

CREATE TABLE "wishlist" (
    "id" SERIAL PRIMARY KEY,
    "customer_id" INT NOT NULL,
    "product_id" INT NOT NULL,
    "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY ("customer_id") REFERENCES "customer"("id"),
    FOREIGN KEY ("product_id") REFERENCES "product"("id")
);
