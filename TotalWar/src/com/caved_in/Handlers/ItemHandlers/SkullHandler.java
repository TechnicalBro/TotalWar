package com.caved_in.Handlers.ItemHandlers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullHandler
{
	public SkullHandler() { }
	
	public ItemStack getSkull(String Player)
	{
		ItemStack SkullStack = new ItemStack(Material.SKULL_ITEM,(byte)3);
		SkullMeta Meta = (SkullMeta)SkullStack.getItemMeta();
		Meta.setOwner(Player);
		SkullStack.setItemMeta(Meta);
		return SkullStack;
	}

}
