package com.caved_in.ItemMenus.FriendsMenu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;

import FriendSystem.Friends.Friend;

public class RenderFriendItems
{
	
	public RenderFriendItems()
	{
		
	}
	
	public List<FriendItem> getFriendItems(String Player)
	{
		List<FriendItem> Items = new ArrayList<FriendItem>();
		if (FriendSystem.FriendsChat.Friends.containsKey(Player))
		{
			for(Friend Friend : FriendSystem.FriendsChat.Friends.get(Player).getFriendsList().getFriends())
			{
				Items.add(new FriendItem(Friend.getName(), new MaterialData(Material.SKULL_ITEM,(byte)3), Friend.getName()));
			}
		}
		return Items;
		
	}

}
