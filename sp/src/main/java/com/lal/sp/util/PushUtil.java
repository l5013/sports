package com.lal.sp.util;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class PushUtil {

	private static final String ALERT = "alert";
	private static final String MSG_CONTENT = "content";
	private static final String TITLE = "title";
	private static final String masterSecret = "c8c7b019364320dca019f03b";
	private static final String appKey = "3bb2a808f32af6dc7f0f84b5";
	

	// 快捷地构建推送对象：所有平台，所有设备，内容为 ALERT 的通知。
	public static PushPayload buildPushObject_all_all_alert() {
		return PushPayload.alertAll(ALERT);
	}

	// 构建推送对象：所有平台，推送目标是别名为 "alias1"，通知内容为 ALERT。

	public static PushPayload buildPushObject_all_alias_alert() {
		return PushPayload.newBuilder().setPlatform(Platform.all())
				.setAudience(Audience.alias("alias1"))
				.setMessage(Message.content(MSG_CONTENT))
				.setNotification(Notification.alert(ALERT)).build();
	}

	// 构建推送对象：平台是 Android，目标是 tag 为 "tag1" 的设备，内容是 Android 通知 ALERT，并且标题为 TITLE。
	public static PushPayload buildPushObject_android_tag_alertWithTitle(String title, String content) {
		return PushPayload.newBuilder().setPlatform(Platform.android())
				.setAudience(Audience.all())
				.setNotification(Notification.android(content, title, null))
				.build();
	}

	// 构建推送对象：平台是 iOS，推送目标是 "tag1", "tag_all" 的交集，推送内容同时包括通知与消息 - 通知信息是
	// ALERT，角标数字为 5，通知声音为 "happy"，
	// 并且附加字段 from = "JPush"；消息内容是 MSG_CONTENT。通知是 APNs 推送通道的，消息是 JPush
	// 应用内消息通道的。APNs 的推送环境是“生产”
	// （如果不显式设置的话，Library 会默认指定为开发）
	public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.ios())
				.setAudience(Audience.tag_and("tag1", "tag_all"))
				.setNotification(
						Notification
								.newBuilder()
								.addPlatformNotification(
										IosNotification.newBuilder()
												.setAlert(ALERT).setBadge(5)
												.setSound("happy.caf")
												.addExtra("from", "JPush")
												.build()).build())
				.setMessage(Message.content(MSG_CONTENT))
				.setOptions(
						Options.newBuilder().setApnsProduction(true).build())
				.build();
	}

	// 构建推送对象：平台是 Andorid 与 iOS，推送目标是 （"tag1" 与 "tag2" 的并集）且（"alias1" 与 "alias2"
	// 的并集），
	// 推送内容是 - 内容为 MSG_CONTENT 的消息，并且附加字段 from = JPush。
	public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(
						Audience.newBuilder()
								.addAudienceTarget(
										AudienceTarget.tag("tag1", "tag2"))
								.addAudienceTarget(
										AudienceTarget
												.alias("alias1", "alias2"))
								.build())
				.setMessage(
						Message.newBuilder().setMsgContent(MSG_CONTENT)
								.addExtra("from", "JPush").build()).build();
	}
	
	
	public static void push(String title, String content) {
		JPushClient client = new JPushClient(masterSecret, appKey);  
		if(StringUtil.isEmptyString(title)) {
			title = TITLE;
		}
		if(StringUtil.isEmptyString(content)) {
			content = MSG_CONTENT;
		}
		PushPayload payload=buildPushObject_android_tag_alertWithTitle(title,content);
		try {
			client.sendPush(payload);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws APIConnectionException, APIRequestException {
		// 这是在极光网站上申请的密钥  
		//String masterSecret = "c8c7b019364320dca019f03b";  
		// 应用的appKey,同样在网站上申请  
//		String appKey = "3bb2a808f32af6dc7f0f84b5";  
		// 建立JpushClient类，用来发送消息的对象  
		JPushClient client = new JPushClient(masterSecret, appKey);  
		PushPayload payload=buildPushObject_android_tag_alertWithTitle(TITLE,MSG_CONTENT);
		client.sendPush(payload);
	}

}
