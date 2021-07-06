package jp.ac.hcs.s3a210.gourmet;

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
public class ShopEntity {
	
	/** 店舗情報のリスト */
	private List<ShopData> shoplist = new ArrayList<ShopData>();

}
