package jp.ac.hcs.s3a210.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * タスク情報を操作する.
 * @author KobayashiDaisuke
 *
 */
@Transactional
@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * 指定したユーザIDのタスク情報を全件取得する.
	 * @param userID ユーザID
	 * @return TaskEntity
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
	

//	/**
//	 * 入力されたタスク内容と期限日でDBに登録する
//	 * @param user_id ユーザID
//	 * @param comment タスク内容
//	 * @param limitday 期限日
//	 * @return 1件以上であればtrue, それ以外はfalse
//	 */
//	public boolean insert(String user_id, String comment, String limitday){
//			System.out.println(comment);
//			if(comment.isEmpty() || limitday.isEmpty() || comment.length() > 50) {
//				System.out.println(limitday.getClass().getSimpleName());
//				return false;
//			}else{
//				System.out.println(limitday);
//				//TaskData方へ詰め替える
//				TaskData taskData = refillToData(user_id, comment, limitday);
//				//登録件数を取得する
//				int count = taskRepository.insertOne(taskData);
//				return count > 0;
//			}
//	}
}
