package com.caved_in.ItemMenus.MainMenu;

import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import com.caved_in.ItemMenus.FriendsMenu.FriendsMenu;
import com.caved_in.ItemMenus.JoinMenu.JoinMenu;
import com.caved_in.ItemMenus.MainMenu.MainMenu.Menus;
import com.caved_in.ItemMenus.SpellsMenu.SpellsMenu;
import com.caved_in.ItemMenus.StatsMenu.SkillsMenu;

import me.xhawk87.PopupMenuAPI.MenuItem;

public class MainItem extends MenuItem
{
	private Menus Menu;
	public MainItem(String text, MaterialData icon, Menus Menu)
	{
		super(text, icon);
		this.Menu = Menu;
	}

	@Override
	public void onClick(Player Player)
	{
		switch (this.Menu)
		{
			case Friends:
				this.getMenu().switchMenu(Player, new FriendsMenu().getMenu(Player.getName()));
				break;
			case Join:
				this.getMenu().switchMenu(Player, new JoinMenu().getMenu());
				break;
			case Spells:
				this.getMenu().switchMenu(Player,new SpellsMenu().getMenu());
				break;
			case Stats:
				this.getMenu().switchMenu(Player, new SkillsMenu().getMenu(Player.getName()));
				break;
			default:
				break;
		}
	}

}
