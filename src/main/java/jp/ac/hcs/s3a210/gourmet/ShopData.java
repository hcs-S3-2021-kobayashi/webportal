package jp.ac.hcs.s3a210.gourmet;

import lombok.Data;
/**
 * 1件分の店舗情報
 * @author KobayashiDaisuke
 *
 */

@Data
public class ShopData {
	
	/** お店ID */
	private String id;

	/** 掲載店舗名 */
	private String name;
	
	/** 掲載店名かな */
	private String name_kana;
	
	/** ロゴURL */
	private String logo_image;

	/** 住所 */
	private String address;
	
	/** 最寄り駅 */
	private String access;
	
	/** googlemap */
	private String googlemap;
	
	/** URL */
	private String url;
	
	/** 携帯用のイメージ画像 */
	private String image;
	
	/** 北海道に固定 */
	private String large_service_area;
}
