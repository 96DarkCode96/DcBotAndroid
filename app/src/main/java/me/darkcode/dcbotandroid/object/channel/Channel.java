package me.darkcode.dcbotandroid.object.channel;

import androidx.annotation.NonNull;
import me.darkcode.dcbotandroid.object.rest.RestAction;
import me.darkcode.dcbotandroid.object.channel.interfaces.IMentionable;

import java.util.EnumSet;

public interface Channel extends IMentionable {

    int MAX_NAME_LENGTH = 100;

    @NonNull
    default EnumSet<ChannelFlag> getFlags() {
        return EnumSet.noneOf(ChannelFlag.class);
    }

    @NonNull
    String getName();

    @NonNull
    ChannelType getType();

    @NonNull
    RestAction<Void> delete();

    @NonNull
    @Override
    default String getAsMention() {
        return "<#" + getId() + ">";
    }

    ;
}