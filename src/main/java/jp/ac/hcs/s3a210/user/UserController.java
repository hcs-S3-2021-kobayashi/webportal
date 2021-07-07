package jp.ac.hcs.s3a210.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
	 * 郵便番号から住所を検索し、結果画面を表示する
	 * @param zipcode 検索する郵便番号（ハイフン無し）
	 * @param principal ログイン情報
	 * @param model
	 * @return 結果画面-郵便番号
	 */
	@GetMapping("/user/list")
	public String getUserList( Principal principal, Model model) {
		
				UserEntity userEntity = userService.selectAll();
				model.addAttribute("userEntity", userEntity);
		return "user/user";
	}


}
