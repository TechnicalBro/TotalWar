package com.caved_in.Handlers.ItemHandlers;

import com.caved_in.TotalWarItems.TotalWarItems;
import com.caved_in.TotalWarItems.Handlers.Items.ItemStackHandler;

import me.cybermaxke.materialapi.inventory.CustomItemStack;

public class CustomItemHandler
{
	public CustomItemStack getCustomItemStack(String ID)
	{
		return TotalWarItems.ItemHandler.getCustomItemStack(ID);
	}

	public CustomItemStack getCustomItemStack(String ID, ItemStackHandler.Tier Tier)
	{
		return TotalWarItems.ItemHandler.getCustomItemStack(ID, Tier);
	}
}