package com.caved_in.ItemMenus.MainMenu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import me.xhawk87.PopupMenuAPI.MenuItem;
import me.xhawk87.PopupMenuAPI.PopupMenu;
import me.xhawk87.PopupMenuAPI.PopupMenuAPI;

public class MainMenu
{
	private PopupMenu Menu;
	
	public MainMenu(Player Player)
	{
		this.Menu = PopupMenuAPI.createMenu("Whatcha' doin? " + ChatColor.GREEN + ":3", 1);
		this.Menu.addMenuItem(new MainItem("Friends", new MaterialData(Material.SKULL_ITEM,(byte)3), Menus.Friends), 0);
		this.Menu.addMenuItem(new MainItem("Stats", new MaterialData(Material.EXP_BOTTLE), Menus.Stats), 1);
		this.Menu.addMenuItem(new MainItem("Spells", new MaterialData(Material.BLAZE_POWDER), Menus.Spells), 2);
		this.Menu.addMenuItem(new MainItem("Join a Faction", new MaterialData(Material.BOOK), Menus.Join), 3);
		this.Menu.setExitOnClickOutside(false);
		this.Menu.openMenu(Player);
	}
	
	public enum Menus
	{
		Friends, Join, Stats, Spells
	}
}
