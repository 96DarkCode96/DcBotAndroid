package me.darkcode.dcbotandroid.object.channel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.darkcode.dcbotandroid.object.Guild;
import me.darkcode.dcbotandroid.object.channel.interfaces.IPermissionContainer;

public interface GuildChannel extends Channel{

    String JUMP_URL ="https://discord.com/channels/%s/%s";

    @NonNull Guild getGuild();

    @NonNull default String getJumpUrl()
    {
        return String.format(JUMP_URL, getGuild().getId(), getId());
    }

    public @Nullable String getIconEmoji();

}
