package com.caved_in.ItemMenus.PlayerMenu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import com.caved_in.ItemMenus.PlayerMenu.PlayerItem.ItemType;

import me.xhawk87.PopupMenuAPI.PopupMenu;
import me.xhawk87.PopupMenuAPI.PopupMenuAPI;

public class PlayerMenu
{
	private PopupMenu PlayerMenu;
	
	public PlayerMenu(Player Player, String Clicked)
	{
		this.PlayerMenu = PopupMenuAPI.createMenu("Whatchu Doin'? :3", 1);
		this.PlayerMenu.addMenuItem(new PlayerItem("Add " + Clicked + " as a friend", new MaterialData(Material.SKULL_ITEM,(byte)3), ItemType.AddFriend, Clicked), 0);
		this.PlayerMenu.setExitOnClickOutside(false);
		this.PlayerMenu.openMenu(Player);
	}

}
