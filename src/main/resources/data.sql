--//userテーブルへ追加
INSERT INTO kadai8db.user (
				id,
				password)
				VALUES (
				1,
				'pass'
				);
--//商品テーブルへ追加
INSERT INTO kadai8db.products (
				name,
				detail,
				image,
				list_price,
				selling_price,
				selling_pieces,
				pieces, stock_quantity)
				VALUES (
				'みかん',
				'黄色',
				'c:foo/fuga.jpg',
				200,
				190,
				10,
				'袋',
				10),
				(
				'リンゴ',
				'赤い',
				'c:foo/hoge.jpg',
				150,
				130,
				5,
				'袋',
				5),
				(
				'メロン',
				'黄色',
				'c:foo/fuga.jpg',
				1200,
				1000,
				1,
				'個',
				20);