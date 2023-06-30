package me.darkcode.dcbotandroid.object.permission;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public enum Permission {
	CREATE_INSTANT_INVITE(0),
	KICK_MEMBERS(1),
	BAN_MEMBERS(2),
	ADMINISTRATOR(3),
	MANAGE_CHANNELS(4),
	MANAGE_GUILD(5),
	ADD_REACTIONS(6),
	VIEW_AUDIT_LOG(7),
	PRIORITY_SPEAKER(8),
	STREAM(9),
	VIEW_CHANNEL(10),
	SEND_MESSAGES(11),
	SEND_TTS_MESSAGES(12),
	MANAGE_MESSAGES(13),
	EMBED_LINKS(14),
	ATTACH_FILES(15),
	READ_MESSAGE_HISTORY(16),
	MENTION_EVERYONE(17),
	USE_EXTERNAL_EMOJIS(18),
	VIEW_GUILD_INSIGHTS(19),
	CONNECT(20),
	SPEAK(21),
	MUTE_MEMBERS(22),
	DEAFEN_MEMBERS(23),
	MOVE_MEMBERS(24),
	USE_VAD(25),
	CHANGE_NICKNAME(26),
	MANAGE_NICKNAMES(27),
	MANAGE_ROLES(28),
	MANAGE_WEBHOOKS(29),
	MANAGE_GUILD_EXPRESSIONS(30),
	USE_APPLICATION_COMMANDS(31),
	REQUEST_TO_SPEAK(32),
	MANAGE_EVENTS(33),
	MANAGE_THREADS(34),
	CREATE_PUBLIC_THREADS(35),
	CREATE_PRIVATE_THREADS(36),
	USE_EXTERNAL_STICKERS(37),
	SEND_MESSAGES_IN_THREADS(38),
	USE_EMBEDDED_ACTIVITIES(39),
	MODERATE_MEMBERS(40),
	VIEW_CREATOR_MONETIZATION_ANALYTICS(41),
	USE_SOUNDBOARD(42),
	USE_EXTERNAL_SOUNDS(45),
	SEND_VOICE_MESSAGES(46);

	private final int n;

	Permission(int n) {
		this.n = n;
	}

	public int getNthBit() {
		return n;
	}

	public static List<Permission> fromBinary(String binary){
		ArrayList<Permission> list = new ArrayList<>();
		BigInteger i = new BigInteger(binary);
		for (Permission value : values()) {
			if(i.testBit(value.getNthBit())){
				list.add(value);
			}
		}
		return list;
	}

	public static String toBinary(List<Permission> permissions){
		BigInteger i = new BigInteger("0");
		for (Permission permission : permissions) {
			i = i.setBit(permission.getNthBit());
		}
		return i.toString();
	}

}