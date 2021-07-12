package jp.ac.hcs.s3a210.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ユーザ情報を操作する.
 * @author KobayashiDaisuke
 *
 */
@Transactional
@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * ユーザ情報を全件取得する.
	 * @return userEntity
	 */
	public UserEntity selectAll() {
			UserEntity userEntity;
			try {
				userEntity = userRepository.selectAll();
			}catch(DataAccessException e){
				e.printStackTrace();
				userEntity = null;
			}
			return userEntity;
	}
	
	/**
	 * 指定したユーザIDのユーザ情報を1件取得する.
	 * @param user_id ユーザID
	 * @return userData
	 */
	public UserData selectUserData(String user_id) {
			UserData userData;
			try {
				userData = userRepository.selectOne(user_id);
			}catch(DataAccessException e){
				e.printStackTrace();
				userData = null;
			}
			return userData;
	}
	

	/**
	 * 入力されたユーザ情報をDBに登録する
	 * @param form 入力項目
	 * @return 1件以上であればtrue, それ以外はfalse
	 */
	public boolean insert(UserForm form){
			//TaskData方へ詰め替える
			UserData userData = refillToData(form);
			//登録件数を取得する
			int count = userRepository.insertOne(userData);
			System.out.println(count);
			return count > 0;
	}
	
	/**
	 * 入力されたタスク内容を更新する.
	 * @param form 更新項目
	 * @return 1件以上であればtrue, それ以外はfalse
	 */
	public boolean update(UserFormForUpdate form){
			//TaskData方へ詰め替える
			UserData userData = refillToUpdateData(form);
			int count;
			//登録件数を取得する
			if(form.getPassword().isEmpty()) {
				count = userRepository.updateOne(userData);
			}else {
				count = userRepository.updateOneWithPassword(userData);
			}
			return count > 0;
	}
	
	/**
	 * 入力項目をuserDataへ変換する
	 * (このメソッドは入力チェック実施した上で呼び出すこと)
	 * @param form 入力項目
	 * @return userData
	 */
	public UserData refillToData(UserForm form){
			UserData userData = new UserData();
			userData.setUser_id(form.getUser_id());
			userData.setUser_name(form.getUser_name());
			userData.setPassword(form.getPassword());
			userData.setRole(form.getRole());
			userData.setDarkmode(false);
			userData.setEnabled(true);
			return userData;
	}
	
	/**
	 * 更新項目をuserDataへ変換する
	 * (このメソッドは入力チェック実施した上で呼び出すこと)
	 * @param form 更新項目
	 * @return userData
	 */
	public UserData refillToUpdateData(UserFormForUpdate form){
		UserData userData = new UserData();
		if(form.getPassword().isEmpty()) {
			userData.setUser_id(form.getUser_id());
			userData.setUser_name(form.getUser_name());
			userData.setRole(form.getRole());
			return userData;
		}else {
			userData.setUser_id(form.getUser_id());
			userData.setUser_name(form.getUser_name());
			userData.setPassword(form.getPassword());
			userData.setRole(form.getRole());
			return userData;		
		}
	}
	
	/**
	 * 指定されたIDのユーザ情報を削除する
	 * @param id ユーザID
	 * @return 1件以上であればtrue, それ以外はfalse
	 */
	public boolean delete(String id) {
		int count;
		try {
			count = userRepository.deleteOne(id);
		}catch(DataAccessException e) {
			e.printStackTrace();
			count = 0;
		}
		return count > 0;
	}
	
}
