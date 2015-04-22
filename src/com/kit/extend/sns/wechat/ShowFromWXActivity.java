package com.kit.extend.sns.wechat;


import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.kit.extend.wechat.R;

public class ShowFromWXActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.show_from_wx);
		initView();
	}

	private void initView() {

		final String title = getIntent().getStringExtra(WeChatConfig.ShowMsgActivity.STitle);
		final String message = getIntent().getStringExtra(WeChatConfig.ShowMsgActivity.SMessage);
		final byte[] thumbData = getIntent().getByteArrayExtra(WeChatConfig.ShowMsgActivity.BAThumbData);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setMessage(message);
		
		if (thumbData != null && thumbData.length > 0) {
			ImageView thumbIv = new ImageView(this);
			thumbIv.setImageBitmap(BitmapFactory.decodeByteArray(thumbData, 0, thumbData.length));
			builder.setView(thumbIv);
		}
		
		builder.show();
	}
}
