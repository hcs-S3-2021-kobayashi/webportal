package jp.ac.hcs.s3a210.task;

import java.util.Date;

import lombok.Data;

@Data
public class TaskData {
	
	/**
	 * タスクID
	 * 主キー、SQLにて自動採番
	 */
	private int id;
	
	/**
	 * ユーザID(メールアドレス)
	 * Userテーブルの手記ーと紐つく、ログイン情報から取得
	 */
	private String user_id;
	
	/**
	 * コメント
	 * 必須入力
	 */
	private String comment;
	
	/**
	 * 期限日
	 * 必須入力
	 */
	private Date limitday;
	
}
