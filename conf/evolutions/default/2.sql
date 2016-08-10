# --- Switching to plain CHAR column

# --- !Ups

ALTER TABLE "trades" DROP COLUMN direction;

DROP TYPE "trade_direction";

ALTER TABLE "trades" ADD COLUMN "direction" CHAR(1) NOT NULL;

# --- !Downs

ALTER TABLE "trades" DROP COLUMN direction;

CREATE TYPE "trade_direction" AS ENUM ('B', 'S');

ALTER TABLE "trades" ADD COLUMN "direction" "trade_direction" NOT NULL;