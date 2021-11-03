package jp.co.aiosl_tec.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.aiosl_tec.model.KeywordForm;
import jp.co.aiosl_tec.model.ProductForm;

@Repository
public class ProductRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// return用変数
	List<ProductForm> productListTest = new ArrayList<>();
	List<Map<String, Object>> productList = new ArrayList<>();
	ProductForm productForm = new ProductForm();
	
	// SQL文
	final String INSERT_SQL = "INSERT INTO kadai8db.products ("
			+ "id, name, detail, image, list_price, selling_price, selling_pieces, pieces, stock_quantity) "
			+ "VALUES (?,?,?,?,?,?,?,?,?)";
	final String UPDATE_SQL = "UPDATE kadai8db.products SET "
			+ "name = ?, detail = ?, image = ?, list_price = ?, selling_price = ?, selling_pieces = ?, pieces = ?, stock_quantity = ? WHERE id = ?";
	final String DELETE_SQL = "DELETE FROM kadai8db.products WHERE id = ?";
	
	// 1件登録
	public void productInsert(ProductForm productForm) throws DataAccessException {
		jdbcTemplate.update(INSERT_SQL, productForm.getId(), productForm.getName(), productForm.getDetail(),
				productForm.getImage(), productForm.getListPrice(), productForm.getSellingPrice(),
				productForm.getSellingPieces(), productForm.getPieces(), productForm.getStockQuantity());
	}

	// 1件更新
	public void updateOne(ProductForm productForm) throws DataAccessException {
		
		jdbcTemplate.update(UPDATE_SQL, productForm.getName(), productForm.getDetail(), productForm.getImage(),
				productForm.getListPrice(), productForm.getSellingPrice(), productForm.getSellingPieces(),
				productForm.getPieces(), productForm.getStockQuantity(), productForm.getId());
	}

	// 1件削除
	public void deleteOne(Integer id) throws DataAccessException {

		String stringId = String.valueOf(id);
		jdbcTemplate.update(DELETE_SQL, stringId);
	}

	// 1件取得
	public ProductForm findById(Integer id) throws DataAccessException {
		Map<String, Object> map = jdbcTemplate.queryForMap("SELECT * FROM kadai8db.products WHERE id = ?", id);
		// 取得したデータをreturn用変数にセット
		productForm.setId((int) map.get("id")); // ユーザーID
		productForm.setName((String) map.get("name")); // 名前
		productForm.setDetail((String) map.get("detail")); // 商品詳細
		productForm.setImage((String) map.get("image")); // 商品画像
		productForm.setListPrice((int) map.get("list_price")); // 定価
		productForm.setSellingPrice((int) map.get("selling_price")); // 販売価格
		productForm.setSellingPieces((int) map.get("selling_pieces")); // 販売単位
		productForm.setPieces((String) map.get("pieces")); // 単位
		productForm.setStockQuantity((int) map.get("stock_quantity")); // 在庫数

		return productForm;
	}

	// keywordによる複数件取得
	public List<Map<String, Object>> searchSelect(KeywordForm keywordForm) throws DataAccessException {
		// SQL文
		StringBuilder sql = new StringBuilder();
		// 検索値の格納用
		ArrayList<Object> keyword = new ArrayList<>();

		sql.append("SELECT * FROM kadai8db.products WHERE 1=1 ");

		// keyword毎にSQLを追加していく
		// idのkeywordが存在する場合
		if (null != keywordForm.getId()) {
			sql.append("AND id = ? ");
			keyword.add(keywordForm.getId());
		}
		// nameのkeywordが存在する場合
		if ("" != keywordForm.getName()) {
			sql.append("AND name = ? ");
			keyword.add(keywordForm.getName());
		}
		// listPriceMinのkeywordが存在する場合
		if (null != keywordForm.getListPriceMin()) {
			sql.append("AND list_price >= ? ");
			keyword.add(keywordForm.getListPriceMin());
		}
		// listPriceMaxのkeywordが存在する場合
		if (null != keywordForm.getListPriceMax()) {
			sql.append("AND list_price <= ? ");
			keyword.add(keywordForm.getListPriceMax());
		}
		// sellingPriceMinのkeywordが存在する場合
		if (null != keywordForm.getSellingPriceMin()) {
			sql.append("AND selling_price >= ? ");
			keyword.add(keywordForm.getSellingPriceMin());
		}
		// sellingPriceMaxのkeywordが存在する場合
		if (null != keywordForm.getSellingPriceMax()) {
			sql.append("AND selling_price <= ? ");
			keyword.add(keywordForm.getSellingPriceMax());
		}
		// stockMinのkeywordが存在する場合
		if (null != keywordForm.getStockMin()) {
			sql.append("AND stock_quantity >= ? ");
			keyword.add(keywordForm.getStockMin());
		}
		// srockMaxのkeywordが存在する場合
		if (null != keywordForm.getStockMax()) {
			sql.append("AND stock_quantity <= ? ");
			keyword.add(keywordForm.getStockMax());
		}

		// keywordの個数によってqueryの処理を分ける
		switch (keyword.size()) {
		case 0: {
			return null;
		}
		case 1: {
			productList = jdbcTemplate.queryForList(sql.toString(), keyword.get(0));
			return productList;
		}
		case 2: {
			productList = jdbcTemplate.queryForList(sql.toString(), keyword.get(0), keyword.get(1));
			return productList;
		}
		case 3: {
			productList = jdbcTemplate.queryForList(sql.toString(), keyword.get(0), keyword.get(1), keyword.get(2));
			return productList;
		}
		case 4: {
			productList = jdbcTemplate.queryForList(sql.toString(), keyword.get(0), keyword.get(1), keyword.get(2),
					keyword.get(3));
			return productList;
		}
		case 5: {
			productList = jdbcTemplate.queryForList(sql.toString(), keyword.get(0), keyword.get(1), keyword.get(2),
					keyword.get(3), keyword.get(4));
			return productList;
		}
		case 6: {
			productList = jdbcTemplate.queryForList(sql.toString(), keyword.get(0), keyword.get(1), keyword.get(2),
					keyword.get(3), keyword.get(4), keyword.get(5));
			return productList;
		}
		case 7: {
			productList = jdbcTemplate.queryForList(sql.toString(), keyword.get(0), keyword.get(1), keyword.get(2),
					keyword.get(3), keyword.get(4), keyword.get(5), keyword.get(6));
			return productList;
		}
		case 8: {
			productList = jdbcTemplate.queryForList(sql.toString(), keyword.get(0), keyword.get(1), keyword.get(2),
					keyword.get(3), keyword.get(4), keyword.get(5), keyword.get(6), keyword.get(7));
			return productList;
		}

		}

		return null;
	}

}
