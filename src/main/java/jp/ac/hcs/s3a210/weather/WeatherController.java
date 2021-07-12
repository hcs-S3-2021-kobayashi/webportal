package jp.ac.hcs.s3a210.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class WeatherController {
	
	@Autowired
	private WeatherService weatherService;
	
	/**
	 * 地域コードから天気を検索し、結果画面を表示する
	 * @param sityCode 検索する地域コード
	 * @param model
	 * @return 結果画面-天気情報
	 */
	@PostMapping("/weather")
	public String getWeather(@RequestParam("weather")String cityCode,Model model) {
				//cityCode,地域名
				String[] information = cityCode.split(",");
				WeatherEntity weatherEntity = weatherService.getWeather(information[0]);
				model.addAttribute("weatherEntity", weatherEntity);
				model.addAttribute("cityName", information[1]);
				return "weather/weather";
		
	}

}
