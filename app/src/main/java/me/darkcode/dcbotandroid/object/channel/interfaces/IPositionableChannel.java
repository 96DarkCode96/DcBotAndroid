package me.darkcode.dcbotandroid.object.channel.interfaces;

import me.darkcode.dcbotandroid.object.channel.GuildChannel;

public interface IPositionableChannel extends GuildChannel {

    default int getPosition() {
        int position = getGuild().getChannels().indexOf(this);
        if (position > -1) {
            return position;
        }
        throw new IllegalStateException("This shouldn't be possible!");
    }

    int getPositionRaw();

}