package jp.co.aiosl_tec.model;

import lombok.Data;

//検索画面用entity
@Data
public class KeywordForm {
	
	private Integer id;
	private String name;
	private Integer listPriceMin;
	private Integer listPriceMax;
	private Integer sellingPriceMin;
	private Integer sellingPriceMax;
	private Integer stockMin;
	private Integer stockMax;

}
