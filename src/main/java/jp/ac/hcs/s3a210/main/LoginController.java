package jp.ac.hcs.s3a210.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ログインに関する機能・画面を正業する.
 * @author KobayashiDaisuke
 *
 */
@Controller
public class LoginController {

	/**
	 * ログイン画面を表示する.
	 * @param model
	 * @return ログイン画面
	 */
	@GetMapping("/login")
	public String getLogin(Model model) {
		return "login";
	}
}
