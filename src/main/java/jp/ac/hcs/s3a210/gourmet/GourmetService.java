package jp.ac.hcs.s3a210.gourmet;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 店舗情報を操作する
 * recruit社のhotpapperAPIを活用する
 * -"http://webservice.recruit.co.jp/hotpepper/
 * @author KobayashiDaisuke
 *
 */
@Transactional
@Service
public class GourmetService {
	
	@Autowired
	RestTemplate restTemplate;
	
	/** グルメサーチAPI リクエストURL */
	private static final String URL =
	"http://webservice.recruit.co.jp/hotpepper/gourmet/v1/?key={API_KEY}&name={shopname}&large_service_area={large_service_area}&format=json";
	private static final String MAP = 
	"https://www.google.com/maps/search/?api=1&query=";
	/**
	 * 指定したキーワードに紐つく店舗情報を取得する
	 * shopname 検索するキーワード
	 * @return shopEntity
	 */
	public ShopEntity getShops(String API_KEY, String shopname, String large_service_area) {
		
		//APIにアクセスして、結果を取得 
		String json = restTemplate.getForObject(URL, String.class, API_KEY, shopname, large_service_area);
		
		//エンティティクラスを生成
		ShopEntity shopEntity = new ShopEntity();
		
		//jsonクラスへの変換失敗のため、例外処理
		try {
			//変換クラスを生成し、文字列からjsonクラスへ変換する(例外の可能性もあり)
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			
			
			//resultsパラメータの抽出(配列分取得する)
			for(JsonNode shop : node.get("results").get("shop")) {
				ShopData shopData = new ShopData(); 
				shopData.setId(shop.get("id").asText());
				shopData.setName(shop.get("name").asText());
				shopData.setLogo_image(shop.get("logo_image").asText());
				shopData.setName_kana(shop.get("name_kana").asText());
				shopData.setAddress(shop.get("address").asText());
				shopData.setAccess(shop.get("access").asText());
				shopData.setUrl(shop.get("urls").get("pc").asText());
				shopData.setImage(shop.get("photo").get("mobile").get("l").asText());
				shopData.setGooglemap(MAP+shop.get("address").asText());
				
				//DataクラスをEntityの配列に追加
				shopEntity.getShoplist().add(shopData);
				System.out.println(shopEntity);
			}
			
		}catch(IOException e) {
			//例外発生時は、エラーメッセージの詳細を標準エラー出力
			e.printStackTrace();
		}
		return shopEntity;
		
		
	}

}
