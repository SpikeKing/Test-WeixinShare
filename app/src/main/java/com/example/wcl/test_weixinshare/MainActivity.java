package com.example.wcl.test_weixinshare;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXTextObject;


public class MainActivity extends ActionBarActivity {

    private IWXAPI mApi;

    private EditText mEditText;
    private Button mShareWeixinButton;
    private Button mShareFriendsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShareWeixinButton = (Button) findViewById(R.id.share_weixin_button);
        mShareFriendsButton = (Button) findViewById(R.id.share_friends_button);
        mEditText = (EditText) findViewById(R.id.share_edit_text);

        mApi = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        mApi.registerApp(Constants.APP_ID);

        mShareWeixinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mEditText.getText().toString();
                if (text == null || text.length() == 0) {
                    return;
                }

                WXTextObject textObj = new WXTextObject();
                textObj.text = text;

                WXMediaMessage msg = new WXMediaMessage();
                msg.mediaObject = textObj;
                msg.description = text;

                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("text");
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneSession;

                mApi.sendReq(req);
            }
        });

        mShareFriendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mEditText.getText().toString();
                if (text == null || text.length() == 0) {
                    return;
                }

                WXTextObject textObj = new WXTextObject();
                textObj.text = text;

                WXMediaMessage msg = new WXMediaMessage();
                msg.mediaObject = textObj;
                msg.description = text;

                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("text");
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneTimeline;

                mApi.sendReq(req);
                finish();
            }
        });
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }



}
