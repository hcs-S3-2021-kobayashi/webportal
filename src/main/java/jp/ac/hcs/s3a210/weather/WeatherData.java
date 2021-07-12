package jp.ac.hcs.s3a210.weather;

import lombok.Data;
/**
 * 1件分の天気情報
 * 各項目のデータ使用について、APIの使用を参照する
 * -https://weather.tsukumijima.net/
 * @author KobayashiDaisuke
 *
 */

@Data
public class WeatherData {
	/** 日付 */
	private String dateLabel;
	
	/** 予報 */
	private String telop;

	/** 詳細 */
	private String text;
}
