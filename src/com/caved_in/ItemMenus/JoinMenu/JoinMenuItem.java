package com.caved_in.ItemMenus.JoinMenu;

import me.xhawk87.PopupMenuAPI.MenuItem;

import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import com.caved_in.Handlers.PlayerHandlers.PlayerHandler;
import com.caved_in.Handlers.PlayerHandlers.PlayerHandler.Faction;

public class JoinMenuItem extends MenuItem
{
	private Faction Faction;
	
	public JoinMenuItem(String text, MaterialData icon, Faction Faction)
	{
		super(text, icon);
		this.Faction = Faction;
	}

	@Override
	public void onClick(Player Player)
	{
		PlayerHandler.JoinFaction(Player, this.Faction);
		getMenu().closeMenu(Player);
	}
}