package jp.ac.hcs.s3a210.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * アップデートようにパスワード、ダークモード、権限のチェックを外したUserForm
 * @author KobayashiDaisuke
 */
@Data
public class UserFormForUpdate {

	/** ユーザID(メールアドレス */
	@NotBlank(message="{require_check}")
	@Email(message="{email_check}")
	private String user_id;
	
	/** パスワード */
	private String password;
	
	/** ユーザ名 */
	@NotBlank(message="{require_check}")
	private String user_name;
	
	/** ダークモードフラグ */
	private boolean darkmode;
	
	/** 権限 */
	private String role;
}