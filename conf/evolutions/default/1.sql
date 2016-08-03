# --- Base Schema for trades

# --- !Ups

CREATE TYPE "trade_direction" AS ENUM ('B', 'S');

CREATE TABLE "trades" (
    "id" UUID NOT NULL,
    "date" DATE NOT NULL,
    "symbol" VARCHAR(100) NOT NULL,
    "direction" "trade_direction" NOT NULL,
    "quantity" BIGINT NOT NULL,
    "price" NUMERIC(12, 2) NOT NULL,
    PRIMARY KEY ("id"),
    CHECK ("quantity" <> 0)
);


# --- !Downs

DROP TABLE "trades";

DROP TYPE "trade_direction";
