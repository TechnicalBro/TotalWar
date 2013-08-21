package com.caved_in.ItemMenus.FriendsMenu.ActionsMenu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import com.caved_in.ItemMenus.FriendsMenu.ActionsMenu.ActionItem.Action;

import me.xhawk87.PopupMenuAPI.PopupMenu;
import me.xhawk87.PopupMenuAPI.PopupMenuAPI;

public class ActionsMenu
{
	private PopupMenu Actions;
	private String Clicked = "";
	
	public ActionsMenu(String Clicked)
	{
		this.Clicked = Clicked;
	}
	
	public ActionsMenu()
	{
		
	}
	
	public PopupMenu getMenu()
	{
		this.Actions = PopupMenuAPI.createMenu("Select an Action", 1);
		this.Actions.addMenuItem(new ActionItem("Request a Teleport to " + Clicked, new MaterialData(Material.ENDER_PEARL), Action.TeleportToRequest, Clicked), 0);
		this.Actions.addMenuItem(new ActionItem("Request " + Clicked + " to teleport to you", new MaterialData(Material.EYE_OF_ENDER), Action.TeleportHereRequest, Clicked), 1);
		this.Actions.addMenuItem(new ActionItem("Send " + Clicked + " a private message!", new MaterialData(Material.BOOK_AND_QUILL),Action.PrivateMessage, Clicked), 2);
		this.Actions.addMenuItem(new ActionItem("Remove " + Clicked + " from your friends", new MaterialData(Material.SKULL_ITEM,(byte)1), Action.RemoveFriend, Clicked), 3);
		this.Actions.setExitOnClickOutside(true);
		return this.Actions;
	}
	
	

}
