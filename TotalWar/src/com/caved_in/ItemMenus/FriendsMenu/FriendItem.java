package com.caved_in.ItemMenus.FriendsMenu;

import me.xhawk87.PopupMenuAPI.MenuItem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import com.caved_in.ItemMenus.FriendsMenu.ActionsMenu.ActionsMenu;

public class FriendItem extends MenuItem
{

	public FriendItem(String text, MaterialData icon, String SkullOwner)
	{
		super(text, icon);
		if (Bukkit.getPlayer(text) != null)
		{
			this.addDescription(ChatColor.GREEN + "Currently Online!");
		}
		else
		{
			this.addDescription(ChatColor.RED + "Offline");
		}
	}

	@Override
	public void onClick(Player Player)
	{
		this.getMenu().switchMenu(Player, new ActionsMenu(this.getText()).getMenu());
	}

}
