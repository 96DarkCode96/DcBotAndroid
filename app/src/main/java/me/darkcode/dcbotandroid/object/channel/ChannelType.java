package me.darkcode.dcbotandroid.object.channel;

import java.util.HashMap;

public enum ChannelType {
    UNKNOWN(-1),
    GUILD_TEXT(0),
    DM(1),
    GUILD_VOICE(2),
    GROUP_DM(3),
    GUILD_CATEGORY(4),
    GUILD_ANNOUNCEMENT(5),
    ANNOUNCEMENT_THREAD(10),
    PUBLIC_THREAD(11),
    PRIVATE_THREAD(12),
    GUILD_STAGE_VOICE(13),
    GUILD_DIRECTORY(14),
    GUILD_FORUM(15);

    private static final HashMap<Integer, ChannelType> map = new HashMap<>();

    static{
        for (ChannelType value : values()) {
            map.put(value.getId(), value);
        }
    }

    public static ChannelType getChannelType(int id){
        return map.getOrDefault(id, ChannelType.UNKNOWN);
    }

    private final int id;

    ChannelType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}