package jp.ac.hcs.s3a210.gourmet;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 店舗情報検索結果の店舗情報
 * 各項目のデータ使用については、APIの使用を参照とする。
 * 1つのキーワードに複数の住所が紐つく事もある為、リスト構造となっている
 * -http://webservice.recruit.co.jp/hotpepper/
 * @author KobayashiDaisuke
 *
 */

@Data
public class ShopEntity {
	
	/** 店舗情報のリスト */
	private List<ShopData> shoplist = new ArrayList<ShopData>();

}
