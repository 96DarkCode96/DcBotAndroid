package me.darkcode.dcbotandroid.object;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.darkcode.dcbotandroid.object.channel.*;
import me.darkcode.dcbotandroid.object.channel.interfaces.ISnowflake;
import me.darkcode.dcbotandroid.object.users.Role;
import me.darkcode.dcbotandroid.object.users.RoleManager;
import me.darkcode.dcbotandroid.utils.SnowflakeCacheViewImpl;

import java.lang.reflect.Member;
import java.util.List;

public interface Guild extends /*IGuildChannelContainer,*/ ISnowflake {
    String ICON_URL = "https://cdn.discordapp.com/icons/%s/%s.%s";
    String SPLASH_URL = "https://cdn.discordapp.com/splashes/%s/%s.png";
    String BANNER_URL = "https://cdn.discordapp.com/banners/%s/%s.%s";

    boolean isLoaded();
    boolean unloadMember(long userId);
    int getMemberCount();

    @NonNull String getName();
    @Nullable String getIconId();

    @Nullable
    default String getIconUrl(){
        String iconId = getIconId();
        return iconId == null ? null :String.format(ICON_URL, getId(), iconId, iconId.startsWith("a_") ? "gif" : "png");
    }

    @Nullable
    String getDescription();

    @Nullable
    String getSplashId();

    @Nullable
    default String getSplashUrl() {
        String splashId = getSplashId();
        return splashId == null ? null : String.format(SPLASH_URL, getId(), splashId);
    }

    @Nullable
    String getVanityCode();

    @Nullable
    default String getVanityUrl() {
        return getVanityCode() == null ? null : "https://discord.gg/" + getVanityCode();
    }

    @Nullable
    String getBannerId();

    @Nullable
    default String getBannerUrl() {
        String bannerId = getBannerId();
        return bannerId == null ? null : String.format(BANNER_URL, getId(), bannerId, bannerId.startsWith("a_") ? "gif" : "png");
    }

    @Nullable
    Member getOwner();

    long getOwnerIdLong();

    @NonNull
    default String getOwnerId() {
        return Long.toUnsignedString(getOwnerIdLong());
    }

    @NonNull
    List<GuildChannel> getChannels();

    SnowflakeCacheViewImpl<TextChannel> getTextChannelsView();

    default @Nullable TextChannel getTextChannelById(long channelId){
        return getTextChannelsView().get(channelId);
    }

    SnowflakeCacheViewImpl<Category> getCategoryChannelsView();

    default @Nullable Category getCategoryById(long categoryId){
        return getCategoryChannelsView().get(categoryId);
    }

    SnowflakeCacheViewImpl<VoiceChannel> getVoiceChannelsView();

    default @Nullable VoiceChannel getVoiceChannelById(long channelId){
        return getVoiceChannelsView().get(channelId);
    }

    RoleManager getRoleManager();

    @Nullable
    default Role getRoleById(long roleId){
        return getRoleManager().getRoleById(roleId);
    }

    String getRegion();



}