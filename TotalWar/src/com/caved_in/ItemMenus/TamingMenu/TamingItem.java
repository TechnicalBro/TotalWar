package com.caved_in.ItemMenus.TamingMenu;

import java.util.ArrayList;
import java.util.List;

import me.xhawk87.PopupMenuAPI.MenuItem;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

public class TamingItem extends MenuItem
{	
	public TamingItem(String ItemName, MaterialData mData, List<String> Lore)
	{
		super(ItemName,mData);
		List<String> LoreLines = new ArrayList<String>();
		for(String S : Lore)
		{
			LoreLines.add(ChatColor.YELLOW + S);
		}
		this.setDescriptions(Lore);
	}

	@Override
	public void onClick(Player arg0)
	{
		
	}
}
