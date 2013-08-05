package com.caved_in.ItemMenus.MainMenu.JoinMenu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import com.caved_in.Handlers.PlayerHandlers.PlayerHandler.Faction;

import me.xhawk87.PopupMenuAPI.PopupMenu;
import me.xhawk87.PopupMenuAPI.PopupMenuAPI;

public class JoinMenu
{
	private PopupMenu JoinMenu;
	
	public JoinMenu()
	{
		this.JoinMenu = PopupMenuAPI.createMenu("Pick a faction!", 1);
		this.JoinMenu.addMenuItem(new JoinMenuItem(ChatColor.YELLOW + "Join the Templars", new MaterialData(Material.INK_SACK,(byte)11), Faction.Templar), 0);
		this.JoinMenu.addMenuItem(new JoinMenuItem(ChatColor.YELLOW + "Join the Pagans", new MaterialData(Material.INK_SACK,(byte)2), Faction.Pagan), 1);
		this.JoinMenu.addMenuItem(new JoinMenuItem(ChatColor.YELLOW + "Join the Dragonkin", new MaterialData(Material.INK_SACK,(byte)1), Faction.Dragonkin), 2);
		this.JoinMenu.addMenuItem(new JoinMenuItem(ChatColor.YELLOW + "Join the Pirates", new MaterialData(Material.INK_SACK,(byte)12), Faction.Pirate), 3);
	}
	
	public JoinMenu(Player Player)
	{
		this.JoinMenu = PopupMenuAPI.createMenu("Pick a faction!", 1);
		this.JoinMenu.addMenuItem(new JoinMenuItem(ChatColor.YELLOW + "Join the Templars", new MaterialData(Material.INK_SACK,(byte)11), Faction.Templar), 0);
		this.JoinMenu.addMenuItem(new JoinMenuItem(ChatColor.YELLOW + "Join the Pagans", new MaterialData(Material.INK_SACK,(byte)2), Faction.Pagan), 1);
		this.JoinMenu.addMenuItem(new JoinMenuItem(ChatColor.YELLOW + "Join the Dragonkin", new MaterialData(Material.INK_SACK,(byte)1), Faction.Dragonkin), 2);
		this.JoinMenu.addMenuItem(new JoinMenuItem(ChatColor.YELLOW + "Join the Pirates", new MaterialData(Material.INK_SACK,(byte)12), Faction.Pirate), 3);
		this.JoinMenu.openMenu(Player);
	}
	
	public void ShowMenu(Player Player)
	{
		this.JoinMenu.openMenu(Player);
	}
	
}
