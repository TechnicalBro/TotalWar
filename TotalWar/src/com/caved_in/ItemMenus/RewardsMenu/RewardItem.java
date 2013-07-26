package com.caved_in.ItemMenus.RewardsMenu;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import me.cybermaxke.materialapi.inventory.CustomItemStack;
import me.xhawk87.PopupMenuAPI.MenuItem;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import com.caved_in.Handlers.ItemHandlers.ItemHandler;
import com.caved_in.Items.ArcaneGem;
import com.caved_in.Items.Gem;

public class RewardItem extends MenuItem
{
	private List<Material> NonMultiple = Arrays.asList(new Material[] { Material.WOOD_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLD_SWORD, Material.DIAMOND_SWORD, Material.BOW, Material.EMERALD, Material.IRON_BARDING, Material.GOLD_BARDING, Material.DIAMOND_BARDING });

	public RewardItem(String text, MaterialData icon)
	{
		super(text, icon);
	}

	@Override
	public void onClick(Player Player)
	{
		Material Type = getIcon().getItemType();
		CustomItemStack Stack = null;
		if (this.NonMultiple.contains(Type))
		{
			if (Type == Material.EMERALD)
			{
				if (new ItemHandler().itemLoreContains(getItemStack(), "Increases your Mana Regen"))
				{
					Player.getInventory().addItem(new ArcaneGem().getItem());
					getMenu().closeMenu(Player);
					Player.updateInventory();
					return;
				}
				else
				{
					Stack = new CustomItemStack(new Gem("", Material.EMERALD));
				}
			}
			else
			{
				Stack = new CustomItemStack(getItemStack());
			}
		}
		else
		{
			Stack = new CustomItemStack(getItemStack());
			Stack.setAmount(1 + new Random().nextInt(36));
		}
		if (Player.getInventory().firstEmpty() != -1)
		{
			Player.getInventory().addItem(new ItemStack[] { Stack.getItem() });
			Player.updateInventory();
		}
		else
		{
			Player.getWorld().dropItem(Player.getLocation(), Stack.getItem());
		}
		getMenu().closeMenu(Player);
	}
}