package com.caved_in.ItemMenus.TamingMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;

public class RenderTamingItems
{
	public RenderTamingItems()
	{
	}

	public List<TamingItem> generateItems()
	{
		List<TamingItem> Items = new ArrayList<TamingItem>();
		Items.add(new TamingItem(ChatColor.YELLOW + "Chicken", new MaterialData(Material.EGG), Arrays.asList(new String[] { "Requires level 1 taming", "Use Seeds to tame" })));
		return null;
	}

}
