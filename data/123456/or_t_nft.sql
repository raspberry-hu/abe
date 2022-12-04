/*
 Navicat Premium Data Transfer

 Source Server         : 47.94.80.39
 Source Server Type    : PostgreSQL
 Source Server Version : 90519 (90519)
 Source Host           : 47.94.80.39:5432
 Source Catalog        : antdb
 Source Schema         : ant

 Target Server Type    : PostgreSQL
 Target Server Version : 90519 (90519)
 File Encoding         : 65001

 Date: 19/09/2022 11:14:52
*/


-- ----------------------------
-- Table structure for t_nft
-- ----------------------------
DROP TABLE IF EXISTS "ant"."t_nft";
CREATE TABLE "ant"."t_nft" (
  "id" int4 NOT NULL DEFAULT nextval('"ant".t_nft_id_seq'::regclass),
  "sn" varchar(256) COLLATE "pg_catalog"."default",
  "nft_name" varchar(256) COLLATE "pg_catalog"."default",
  "nft_desc" text COLLATE "pg_catalog"."default",
  "rights_rules" text COLLATE "pg_catalog"."default" DEFAULT ''::text,
  "token_id" varchar(256) COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "meta_data_uri" text COLLATE "pg_catalog"."default" DEFAULT ''::text,
  "tx_hash" varchar(256) COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "transfer_hash" varchar(256) COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "creater" varchar(256) COLLATE "pg_catalog"."default",
  "block_number" int4 DEFAULT 0,
  "create_time" int4 DEFAULT 0,
  "media_uri" text COLLATE "pg_catalog"."default",
  "create_tax" int4,
  "owner" varchar(256) COLLATE "pg_catalog"."default",
  "status" int4 DEFAULT 0,
  "market_type" int4 DEFAULT 0,
  "approved" int4 DEFAULT 0,
  "chain_name" varchar(256) COLLATE "pg_catalog"."default",
  "currency_name" varchar(256) COLLATE "pg_catalog"."default",
  "lazy" int4 DEFAULT 0,
  "media_ipfs_uri" text COLLATE "pg_catalog"."default" DEFAULT ''::text,
  "collection_id" int4 DEFAULT 0,
  "categories_id" int4 DEFAULT 0,
  "explore_uri" varchar(255) COLLATE "pg_catalog"."default",
  "ant_token_id" int4 NOT NULL,
  "ant_count" int4 NOT NULL,
  "ant_nft_owner" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "ant_token_url" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "ant_tx_hash" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "ant_nft_id" int4 NOT NULL,
  "ant_price" int4 NOT NULL,
  "buy_time" int4,
  "ant_nft_buyer" varchar(255) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "ant"."t_nft" OWNER TO "antdev";

-- ----------------------------
-- Primary Key structure for table t_nft
-- ----------------------------
ALTER TABLE "ant"."t_nft" ADD CONSTRAINT "t_nft_pkey" PRIMARY KEY ("id");
