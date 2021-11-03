package jp.co.aiosl_tec.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

//商品用entity
@Data
public class ProductForm {
	@NotNull
	private int id;

	@NotBlank
	@Size(max=100)
	private String name;

	private String detail;

	@Size(max=100)
	private String image;

	private Integer listPrice;
	
	@NotNull
	private Integer sellingPrice;
	
	@NotNull
	private Integer sellingPieces;
	
	@Size(max=20)
	private String pieces;
	
	@NotNull
	private Integer stockQuantity;
	
}
