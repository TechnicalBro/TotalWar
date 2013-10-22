package com.caved_in.ItemMenus.FriendsMenu;

import java.util.List;

import org.bukkit.entity.Player;

import me.xhawk87.PopupMenuAPI.PopupMenu;
import me.xhawk87.PopupMenuAPI.PopupMenuAPI;

public class FriendsMenu
{
	private PopupMenu FriendsMenu;
	
	public FriendsMenu(Player Player)
	{
		List<FriendItem> Friends = new RenderFriendItems().getFriendItems(Player.getName());
		this.FriendsMenu = PopupMenuAPI.createMenu("Your Friends!", (int) Math.ceil(Friends.size() / 9.0D));
		for(int I = 0; I < Friends.size(); I++)
		{
			if (I >= 44)
			{
				break;
			}
			this.FriendsMenu.addMenuItem(Friends.get(I), I);
		}
		this.FriendsMenu.setExitOnClickOutside(false);
		this.FriendsMenu.openMenu(Player);
	}
	
	public FriendsMenu() { }
	
	public PopupMenu getMenu(String Player)
	{
		List<FriendItem> Friends = new RenderFriendItems().getFriendItems(Player);
		this.FriendsMenu = PopupMenuAPI.createMenu("Your Friends!", (int) Math.ceil(Friends.size() / 9.0D));
		for(int I = 0; I < Friends.size(); I++)
		{
			if (I >= 44)
			{
				break;
			}
			this.FriendsMenu.addMenuItem(Friends.get(I), I);
		}
		this.FriendsMenu.setExitOnClickOutside(false);
		return this.FriendsMenu;
	}

}
