package jp.ac.hcs.s3a210.weather;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 郵便番号情報を操作する
 * zipcloud社の郵便番号検索APIを活用する
 * -http://zipcloud.ibsnet.co.jp/doc/api
 * @author KobayashiDaisuke
 *
 */
@Transactional
@Service
public class WeatherService {
	
	@Autowired
	RestTemplate restTemplate;
	
	/** 郵便番号県検索API リクエストURL */
	private static final String URL = "https://weather.tsukumijima.net/api/forecast?city={cityCode}";
	
	/**
	 * 指定した郵便番号に紐つく郵便番号情報を取得する
	 * zipcode 郵便番号(7桁、ハイフン無し)
	 * @return ZipCodeEntity
	 */
	public WeatherEntity getWeather(String cityCode) {
		
		//APIにアクセスして、結果を取得 
		String json = restTemplate.getForObject(URL, String.class, cityCode);
		
		//エンティティクラスを生成
		WeatherEntity weatherEntity = new WeatherEntity();
		
		//jsonクラスへの変換失敗のため、例外処理
		try {
			//変換クラスを生成し、文字列からjsonクラスへ変換する(例外の可能性もあり)
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			
			
			//resultsパラメータの抽出(配列分取得する)
			for(JsonNode forecast : node.get("forecasts")) {
				//データクラスの生成(result3件分)
				WeatherData data = new WeatherData();
				// dateLabelをDataクラスへ設定
				data.setDateLabel(forecast.get("dateLabel").asText());
				// telopをDataクラスへ設定
				data.setTelop(forecast.get("telop").asText());
				// DataクラスをEntityの配列に追加
				weatherEntity.getForecasts().add(data);
			}
			//データクラスの生成(result3件分)
			WeatherData data = new WeatherData();
			JsonNode description = node.get("description");
			data.setText(description.get("text").asText());
			weatherEntity.getForecasts().add(data);
			
		}catch(IOException e) {
			//例外発生時は、エラーメッセージの詳細を標準エラー出力
			e.printStackTrace();
		}
		return weatherEntity;
		
		
	}

}
