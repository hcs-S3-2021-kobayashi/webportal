package jp.ac.hcs.s3a210.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class WeatherController {
	
	@Autowired
	private WeatherService weatherService;
	
	/**
	 * 郵便番号から住所を検索し、結果画面を表示する
	 * @param zipcode 検索する郵便番号（ハイフン無し）
	 * @param principal ログイン情報
	 * @param model
	 * @return 結果画面-郵便番号
	 */
	@PostMapping("/weather")
	public String getWeather(Model model) {
				
				WeatherEntity weatherEntity = weatherService.getWeather("016010");
				model.addAttribute("weatherEntity", weatherEntity);
				return "weather/weather";
		
	}

}
