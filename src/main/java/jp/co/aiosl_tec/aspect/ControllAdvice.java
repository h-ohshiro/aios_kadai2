package jp.co.aiosl_tec.aspect;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllAdvice {

	//データベース関連の例外処理
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e,Model model) {
		
		//空文字をセット
		model.addAttribute("error","");
		//メッセージをModelに登録
		model.addAttribute("message", "DataAccessExceprionが発生しました");
		//Httpのエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}
}
