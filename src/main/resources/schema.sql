-- userテーブルの削除
DROP TABLE IF EXISTS kadai8db.user;
-- userテーブルの生成
CREATE TABLE kadai8db.user (
			id INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
			password VARCHAR(20) NOT NULL COMMENT 'パスワード',
			PRIMARY KEY (id))
			ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='ユーザー情報テーブル';

-- 商品テーブルの削除
DROP TABLE IF EXISTS kadai8db.products;
-- 商品テーブルの生成
CREATE TABLE kadai8db.products (
			id INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
			name VARCHAR(100) NOT NULL COMMENT '商品名',
			detail VARCHAR(300) DEFAULT NULL COMMENT '商品説明',
			image VARCHAR(100) DEFAULT NULL COMMENT '商品画像',
			list_price INT DEFAULT NULL COMMENT '定価',
			selling_price INT NOT NULL COMMENT '販売価格',
			selling_pieces INT NOT NULL COMMENT '販売単位',
			pieces VARCHAR(20) DEFAULT NULL COMMENT '単位',
			stock_quantity INT NOT NULL COMMENT '在庫数',
			PRIMARY KEY (id))
			ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品情報テーブル';