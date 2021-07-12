package jp.ac.hcs.s3a210.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	private UserForm userForm;
	
	/**
	 * ユーザ一覧画面を表示する
	 * @param zipcode 検索する郵便番号（ハイフン無し）
	 * @param model
	 * @return ユーザ一覧画面
	 */
	@GetMapping("/user/list")
	public String getUserList(Model model) {
				UserEntity userEntity = userService.selectAll();
				model.addAttribute("userEntity", userEntity);
		return "user/user";
	}
	

	/**
	 * ユーザ登録画面(管理者用)を表示する
	 * @param form 登録時に入力チェック用UserForm
	 * @param model
	 * @return ユーザ登録画面(管理者用)
	 */
	@GetMapping("/user/insert")
	public String getUserInsert(UserForm form, Model model) {
		return "user/insert";
	}
	
	/**
	 * 1件分のユーザ情報をデータベースに登録する
	 * @param form 登録するユーザ情報(パスワードは平文)
	 * @param bindingResult データバインド実施結果
	 * @param principal ログイン情報
	 * @param model
	 * @return ユーザ一覧画面
	 */
	@PostMapping("/user/insert")
	public String getUserInsert(@ModelAttribute @Validated UserForm form,
			BindingResult bindingResult,
			Principal principal,
			Model model) {
		//入力チェックに引っかかった場合、前の画面に戻る
		if (bindingResult.hasErrors()) {
			return getUserInsert(form, model);
		}
		//追加処理
		boolean isSuccess = userService.insert(form);
		if (isSuccess) {
			log.info("成功");
		}else {
			log.info("失敗");
		}
		return getUserList (model);
	}
	
	/**
	 * 1件分のユーザ情報を更新する
	 * @param form 登録するユーザ情報(パスワードは平文)
	 * @param bindingResult データバインド実施結果
	 * @param principal ログイン情報
	 * @param model
	 * @return ユーザ一覧画面
	 */
	@PostMapping("/user/update")
	public String getUserUpdate(@ModelAttribute @Validated UserFormForUpdate form,
			BindingResult bindingResult,
			Principal principal,
			Model model) {
		//入力チェックに引っかかった場合、前の画面に戻る
		if (bindingResult.hasErrors()) {
			return getUserDetail(form.getUser_id(), principal, model);
		}
		//追加処理
		boolean isSuccess = userService.update(form);
		if (isSuccess) {
			log.info("成功");
		}else {
			log.info("失敗");
		}
		return getUserList (model);
	}
	
	/**
	 * 指定されたIDのタスクを削除する
	 * @param id タスクID
	 * @param principal ログイン情報
	 * @param model
	 * @return
	 */
	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") String user_id, Principal principal, Model model) {
		boolean isSuccess = userService.delete(user_id);
		if (isSuccess) {
			model.addAttribute("deletemessage", "成功");
		}else {
			model.addAttribute("deletemessage", "失敗");
		}
		return getUserList(model);
	}
	
	/**
	 * ユーザ詳細情報画面を表示する
	 * @param user_id 検索するユーザID
	 * @param principal ログイン情報
	 * @param model
	 * @return ユーザ詳細情報画面
	 */
	@GetMapping("/user/detail/{id}")
	public String getUserDetail(@PathVariable("id") String user_id,
			Principal principal,
			Model model) {
		
		UserData userData = userService.selectUserData(user_id);
		if(userData.isEnabled()==false) {
			return getUserList(model);
		}
		
		model.addAttribute("userData", userData);
		return "user/detail";	
	}
}
