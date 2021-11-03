package jp.co.aiosl_tec.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.aiosl_tec.model.KeywordForm;
import jp.co.aiosl_tec.model.ProductForm;
import jp.co.aiosl_tec.repository.ProductRepository;

@Controller
public class ProductController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	ProductRepository productsRepository;

	// 新規登録画面へ偏移
	@GetMapping("/product_insert")
	public String insert(Model model, ProductForm productForm) {
		return "product_insert";
	}

	// 商品検索画面の新規登録ボタンを押すと新規登録画面へ偏移
	@PostMapping(path = "/products_search", name = "regist")
	public String insertView() {
		return "redirect:product_insert";
	}

	// 商品を新規登録
	@PostMapping("/product_insert")
	public String insert(RedirectAttributes redirectAttributes, Model model, Locale locale,
			@ModelAttribute @Validated ProductForm productForm, BindingResult bindingResult) {
		// 入力（登録内容）のチェック
		if (bindingResult.hasErrors()) {
			// エラーの場合、再度新規登録画面へ偏移
			return "product_insert";
		}
		// DBへ登録し完了画面へ偏移
		productsRepository.productInsert(productForm);
		redirectAttributes.addAttribute("message", "登録");
		return "redirect:done";
	}

	// 商品登録画面のキャンセルボタンを押すと検索画面へ偏移
	@PostMapping(path = "/product_insert", params = "back")
	public String insertBack() {
		return "redirect:products_search";
	}

	// 商品IDを受け取り、商品更新画面へ偏移
	@GetMapping(path = "/product_edit/{id}", name = "edit")
	public String updateTest(Model model, @PathVariable("id") int id) {
		ProductForm productForm = productsRepository.findById(id);
		model.addAttribute("productForm", productForm);
		return "product_edit";
	}
	
	// 商品情報を削除後、完了画面へ偏移
	@GetMapping(path = "/product_delete/{id}", name = "delete")
	public String deleteTest(RedirectAttributes redirectAttributes, Model model, @PathVariable("id") int id) {
		productsRepository.deleteOne(id);
		redirectAttributes.addAttribute("message", "削除");
		return "redirect:/done";
	}

	// 商品情報の更新後、完了画面へ偏移
	@PostMapping(path = "/product_edit", params = "update")
	public String edit(RedirectAttributes redirectAttributes, Model model, Locale locale,
			@ModelAttribute @Validated ProductForm productForm, BindingResult bindingResult) {
		// 入力（登録内容）のチェック
		if (bindingResult.hasErrors()) {
			// エラーの場合、再度新規登録画面へ偏移
			return "product_edit";
		}
		// DBへ登録し完了画面へ偏移
		productsRepository.updateOne(productForm);
		redirectAttributes.addAttribute("message", "更新");

		return "redirect:done";

	}

	// 商品更新画面のキャンセルボタンを押すと検索画面へ偏移
	@PostMapping(path = "/product_edit", params = "back")
	public String updateBack() {
		return "redirect:products_search";
	}

	// 商品検索画面へ偏移
	@GetMapping("/products_search")
	public String search(Model model, KeywordForm keywordForm) {

		return "products_search";
	}

	// 商品検索結果の表示
	@PostMapping(path = "/products_search", params = "search")
	public String searchResult(Model model, @ModelAttribute KeywordForm keywordForm, BindingResult bindingResult) {
		// 入力（登録内容）のチェック
		if (bindingResult.hasErrors()) {
			// エラーの場合、再度新規登録画面へ偏移
			return "products_search";
		}
		// 検索結果の商品データを取得
		List<Map<String, Object>> productList = productsRepository.searchSelect(keywordForm);
		model.addAttribute("productList", productList);

		return "products_search";
	}

	// 新規登録、更新、削除処理後に偏移する
	@GetMapping("/done")
	public String commmon(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("message", message);
		return "done";
	}
}
