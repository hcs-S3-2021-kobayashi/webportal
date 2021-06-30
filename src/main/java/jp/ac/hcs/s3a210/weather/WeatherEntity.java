package jp.ac.hcs.s3a210.weather;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 天気予報検索結果の天気情報
 * 各項目のデータ使用については、APIの使用を参照する
 * htpps;//weather.tsukumijima.net/
 * @author KobayashiDaisuke
 *
 */

@Data
public class WeatherEntity {
	/** タイトル */
	private String title;
	
	/** 説明文 */
	private String description;
	
	/** 天気情報のリスト */
	private List<WeatherData> forecasts = new ArrayList<WeatherData>();

}
