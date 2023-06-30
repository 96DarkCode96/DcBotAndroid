package me.darkcode.dcbotandroid.object.channel.interfaces;

import androidx.annotation.NonNull;

public interface IMentionable extends ISnowflake {
    @NonNull String getAsMention();
}