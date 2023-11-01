package com.exodus.MLBBPlayerInfoFetcher;

import android.app.Activity;
import com.exodus.MLBBPlayerInfoFetcher.network.RequestNetwork;
import com.exodus.MLBBPlayerInfoFetcher.network.RequestNetworkController;
import com.exodus.MLBBPlayerInfoFetcher.listener.MLBBPlayerInfoFetcherListener;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLDecoder;
import java.util.HashMap;

/**
* Created by Andrei De Ocampo
* https://github.com/sudoxE7
* ytexodus7@gmail.com
* on 11/01/23.
*/

public class MLBBPlayerInfoFetcher {
	private Activity mActivity;
	private int mPlayerId;
	private int mServerId;

	public MLBBPlayerInfoFetcher(Activity activity, int playerId, int serverId) {
		mActivity = activity;
		mPlayerId = playerId;
		mServerId = serverId;
	}

	public void fetchPlayerInfo(MLBBPlayerInfoFetcherListener listener) {
		try {
			HashMap<String, Object> params = new HashMap<>();
			params.put("voucherPricePoint.id", "27670");
			params.put("voucherPricePoint.price", "242535.0");
			params.put("voucherPricePoint.variablePrice", "0");
			params.put("n", "12/7/2022-2046");
			params.put("email", "");
			params.put("userVariablePrice", "0");
			params.put("order.data.profile", "eyJuYW1lIjoiICIsImRhdGVvZmJpcnRoIjoiIiwiaWRfbm8iOiIifQ==");
			params.put("user.userId", String.valueOf(mPlayerId));
			params.put("user.zoneId", String.valueOf(mServerId));
			params.put("msisdn", "");
			params.put("voucherTypeName", "MOBILE_LEGENDS");
			params.put("shopLang", "id_ID");
			params.put("voucherTypeId", "5");
			params.put("gvtId", "19");
			params.put("checkoutId", "");
			params.put("affiliateTrackingId", "");
			params.put("impactClickId", "");
			params.put("anonymousId", "");

			RequestNetwork requestNetwork = new RequestNetwork(mActivity);
			requestNetwork.setParams(params, RequestNetworkController.REQUEST_PARAM);
			requestNetwork.startRequestNetwork("POST", "https://order-sg.codashop.com/initPayment.action", "",
					new RequestNetwork.RequestListener() {
						@Override
						public void onResponse(String tag, String response, HashMap<String, Object> responseHeader) {
							try {
								String imageUrl = "http://face.yuanzhanapp.com/" + mServerId + "/"
										+ String.valueOf(mPlayerId)
												.substring(Math.max(0, String.valueOf(mPlayerId).length() - 2))
										+ "/"
										+ String.valueOf(mPlayerId).substring(
												Math.max(0, String.valueOf(mPlayerId).length() - 4),
												Math.max(0, String.valueOf(mPlayerId).length() - 2))
										+ "/" + mPlayerId + "_1.jpg";
								String username = URLDecoder.decode(new JSONObject(response)
										.getJSONObject("confirmationFields").getString("username"));

								listener.onPlayerInfoFetched(imageUrl, username);
							} catch (JSONException e) {
								listener.onPlayerInfoFetched("", ""); // Player not found
							}
						}

						@Override
						public void onErrorResponse(String tag, String message) {
							// Handle error response if necessary
						}
					});
		} catch (Exception e) {
			// Handle exceptions if necessary
		}
	}
}
