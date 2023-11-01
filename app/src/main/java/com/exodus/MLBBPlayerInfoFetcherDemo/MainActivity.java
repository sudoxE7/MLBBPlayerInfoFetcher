package com.exodus.MLBBPlayerInfoFetcherDemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.exodus.MLBBPlayerInfoFetcher.MLBBPlayerInfoFetcher;
import com.exodus.MLBBPlayerInfoFetcher.listener.MLBBPlayerInfoFetcherListener;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		((Button) findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int playerId = Integer.parseInt(((EditText) findViewById(R.id.edittext1)).getText().toString());
				int serverId = Integer.parseInt(((EditText) findViewById(R.id.edittext2)).getText().toString());
				new MLBBPlayerInfoFetcher(MainActivity.this, playerId, serverId)
						.fetchPlayerInfo(new MLBBPlayerInfoFetcherListener() {
							@Override
							public void onPlayerInfoFetched(String imageUrl, String playerName) {
								((TextView) findViewById(R.id.textview1))
										.setText("Image Url: " + imageUrl + "\n\nPlayer Name: " + playerName);
							}
						});
			}
		});
	}
}