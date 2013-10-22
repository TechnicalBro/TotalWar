package com.caved_in.ItemMenus.RewardsMenu;

import java.util.List;

import me.cybermaxke.materialapi.inventory.CustomItemStack;
import me.xhawk87.PopupMenuAPI.PopupMenu;
import me.xhawk87.PopupMenuAPI.PopupMenuAPI;

import org.bukkit.entity.Player;

public class RewardsMenu
{
	private PopupMenu RewardsGUI;

	public RewardsMenu(Player Player, List<CustomItemStack> Stacks)
	{
		List<RewardItem> rItems = new RenderRewardItems().generateItems(Stacks);
		this.RewardsGUI = PopupMenuAPI.createMenu("Please choose a reward!", (int) Math.ceil(rItems.size() / 9.0D));
		for (int I = 0; I < rItems.size(); I++)
		{
			this.RewardsGUI.addMenuItem(rItems.get(I), I);
		}
		this.RewardsGUI.setExitOnClickOutside(false);
		this.RewardsGUI.openMenu(Player);
	}
}