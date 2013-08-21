package com.caved_in.ItemMenus.JoinMenu;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import me.cybermaxke.materialapi.inventory.CustomItemStack;
import me.xhawk87.PopupMenuAPI.MenuItem;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import com.caved_in.TotalWar;
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
		TotalWar.PlayerHandler.JoinFaction(Player, this.Faction);
		getMenu().closeMenu(Player);
	}
}