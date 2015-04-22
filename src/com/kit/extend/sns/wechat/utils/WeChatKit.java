package com.kit.extend.sns.wechat.utils;

import android.graphics.Bitmap;

import com.kit.utils.File2ByteArrayUtils;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXImageObject;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXTextObject;

/**
 * Created by Zhao on 14-8-25.
 */
public class WeChatKit {


    /**
     * 发送一条纯文本文字到微信朋友圈/朋友
     *
     * @param api
     * @param text
     * @param scene SendMessageToWX.Req.WXSceneTimeline/SendMessageToWX.Req.WXSceneSession
     */
    public static void sendText2WeChat(IWXAPI api, String text, int scene) {
        if (text == null || text.length() == 0) {
            return;
        }

        // 初始化一个WXTextObject对象
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;

        // 用WXTextObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        // 发送文本类型的消息时，title字段不起作用
        // msg.title = "Will be ignored";
        msg.description = text;

        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text"); // transaction字段用于唯一标识一个请求
        req.message = msg;
        req.scene = scene;

        // 调用api接口发送数据到微信
        api.sendReq(req);
    }


    /**
     * 发送一张图片到微信朋友圈/朋友
     *
     * @param api
     * @param text
     * @param scene SendMessageToWX.Req.WXSceneTimeline/SendMessageToWX.Req.WXSceneSession
     */
    public static void sendPic2WeChat(IWXAPI api, String text, Bitmap bmp, int scene) {

        WXImageObject imgObj = new WXImageObject(bmp);

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;

        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 150, true);
        bmp.recycle();
        msg.thumbData = File2ByteArrayUtils.bmpToByteArray(thumbBmp, true);  // 设置缩略图
        msg.title = text;
        msg.description = text;


        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");
        req.message = msg;
        req.scene = scene;
        api.sendReq(req);

    }


    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }


}
