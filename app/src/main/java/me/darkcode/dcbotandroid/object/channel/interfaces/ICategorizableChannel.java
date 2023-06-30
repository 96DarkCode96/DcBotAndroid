package me.darkcode.dcbotandroid.object.channel.interfaces;

import androidx.annotation.Nullable;
import me.darkcode.dcbotandroid.object.channel.Category;
import me.darkcode.dcbotandroid.object.channel.GuildChannel;

public interface ICategorizableChannel extends GuildChannel, IPermissionContainer{
	long getParentCategoryIdLong();
	@Nullable
	default String getParentCategoryId()
	{
		long parentID = getParentCategoryIdLong();
		if (parentID == 0L)
			return null;
		return Long.toUnsignedString(parentID);
	}

	@Nullable
	default Category getParentCategory()
	{
		return getGuild().getCategoryById(getParentCategoryIdLong());
	}

	boolean isSynced();

}