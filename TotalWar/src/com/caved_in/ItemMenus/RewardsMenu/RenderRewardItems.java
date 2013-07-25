package com.caved_in.ItemMenus.RewardsMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.cybermaxke.materialapi.inventory.CustomItemStack;

import org.bukkit.material.MaterialData;

public class RenderRewardItems
{
	public List<RewardItem> generateItems(CustomItemStack[] Stack)
	{
		return generateItems(Arrays.asList(Stack));
	}

	public List<RewardItem> generateItems(List<CustomItemStack> Stacks)
	{
		List<RewardItem> Items = new ArrayList<RewardItem>();
		for (CustomItemStack Item : Stacks)
		{
			RewardItem Temp = new RewardItem(Item.getName(), new MaterialData(Item.getType()));
			Temp.setDescriptions(Item.getLore());
			Items.add(Temp);
		}
		return Items;
	}
}