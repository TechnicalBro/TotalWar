package com.caved_in.Handlers.DungeonChestHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import me.cybermaxke.materialapi.inventory.CustomItemStack;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.caved_in.Items.Gem;
import com.caved_in.TotalWarItems.TotalWarItems;
import com.caved_in.TotalWarItems.Handlers.Items.ItemStackHandler.Tier;

public class LootGenerator
{

	private List<Material> StackedLoot = Arrays.asList(new Material[] { Material.COAL, Material.BREAD, Material.APPLE, Material.ARROW, Material.BONE, Material.BLAZE_POWDER, Material.COCOA, Material.IRON_INGOT, Material.LEATHER, Material.REDSTONE, Material.COOKED_BEEF, Material.COOKIE, Material.GOLD_NUGGET, Material.EGG, Material.GLOWSTONE_DUST, Material.DIRT, Material.TORCH });

	private List<Material> UnstackedLoot = Arrays.asList(new Material[] { Material.BOAT, Material.IRON_SPADE, Material.IRON_AXE, Material.LEASH, Material.SADDLE, Material.STONE_PICKAXE, Material.STONE_BUTTON, Material.STONE_SPADE });

	public LootGenerator()
	{
	}

	public List<ItemStack> generateLoot()
	{
		List<ItemStack> Loot = new ArrayList<ItemStack>();
		int AmountOfLoot = 2 + new Random().nextInt(4);
		for (int I = 0; I < AmountOfLoot; I++)
		{
			Loot.add(randomLoot());
		}
		return Loot;
	}

	public ItemStack randomLoot()
	{
		switch (new Random().nextInt(3))
		{
		case 0:
			return new ItemStack(this.StackedLoot.get(new Random().nextInt(this.StackedLoot.size())), 1 + new Random().nextInt(18));
		case 1:
			return new ItemStack(this.StackedLoot.get(new Random().nextInt(this.StackedLoot.size())), 1);
		case 2:
			if (PercentCheck(2))
			{
				return TotalWarItems.ItemHandler.getCustomItemStack("Bow", Tier.Epic).getItem();
			}
			else if (PercentCheck(5))
			{
				return TotalWarItems.ItemHandler.getCustomItemStack("Blade", Tier.Epic).getItem();
			}
			else if (PercentCheck(13))
			{
				return TotalWarItems.ItemHandler.getCustomItemStack("Blade").getItem();
			}
			else if (PercentCheck(25))
			{
				return TotalWarItems.ItemHandler.getCustomItemStack("HorseArmor").getItem();
			}
			else if (PercentCheck(30))
			{
				return new CustomItemStack(new Gem("", Material.EMERALD)).getItem();
			}
			else if (PercentCheck(38))
			{
				return TotalWarItems.ItemHandler.getCustomItemStack("HealingI").getItem();
			}
			else if (PercentCheck(30))
			{
				return TotalWarItems.ItemHandler.getCustomItemStack("FatigueI").getItem();
			}
			else if (PercentCheck(38))
			{
				return TotalWarItems.ItemHandler.getCustomItemStack("LightningI").getItem();
			}
			else if (PercentCheck(30))
			{
				return TotalWarItems.ItemHandler.getCustomItemStack("FatigueII").getItem();
			}
			else if (PercentCheck(50))
			{
				return TotalWarItems.ItemHandler.getCustomItemStack("Blade", Tier.Rare).getItem();
			}
			else if (PercentCheck(70))
			{
				return TotalWarItems.ItemHandler.getCustomItemStack("Katana", Tier.Common).getItem();
			}
			else
			{
				switch (new Random().nextInt(3))
				{
				case 0:
					return TotalWarItems.ItemHandler.getCustomItemStack("Blade", Tier.Common).getItem();
				case 1:
					return TotalWarItems.ItemHandler.getCustomItemStack("Katana", Tier.Common).getItem();
				case 2:
					return TotalWarItems.ItemHandler.getCustomItemStack("Bow", Tier.Common).getItem();
				default:
					break;
				}
			}
		default:
			return TotalWarItems.ItemHandler.getCustomItemStack("Blade").getItem();
		}
	}

	public boolean PercentCheck(double Chances)
	{
		return new Random().nextInt(100) <= Chances;
	}
}
