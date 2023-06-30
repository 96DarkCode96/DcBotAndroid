package me.darkcode.dcbotandroid.object.channel;

import androidx.annotation.NonNull;

import java.util.Collection;
import java.util.EnumSet;

public enum ChannelFlag {
    PINNED(1<<4),
    REQUIRE_TAG(1<<4);

    private final int value;

    ChannelFlag(int value) {
        this.value = value;
    }

    public int getRaw() {
        return value;
    }

    @NonNull
    public static EnumSet<ChannelFlag> fromRaw(int bitset) {
        EnumSet<ChannelFlag> set = EnumSet.noneOf(ChannelFlag.class);
        if (bitset == 0)
            return set;

        for (ChannelFlag flag : values()) {
            if (flag.value == bitset)
                set.add(flag);
        }

        return set;
    }

    public static int getRaw(@NonNull Collection<ChannelFlag> flags) {
        int raw = 0;
        for (ChannelFlag flag : flags)
            raw |= flag.getRaw();
        return raw;
    }

}