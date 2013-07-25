package com.caved_in.Items;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.rit.sucy.EnchantmentAPI;

public class ArcaneGem
{
	public ArcaneGem()
	{

	}

	public ItemStack getItem()
	{
		ItemStack Gem = new ItemStack(Material.EMERALD);
		ItemMeta iMeta = Gem.getItemMeta();
		iMeta.setDisplayName(ChatColor.AQUA + "Embued gem of Arcane Enhancement");
		iMeta.setLore(Arrays.asList(new String[] { ChatColor.YELLOW + "Combine with armor to increase mana regen" }));
		Gem.setItemMeta(iMeta);
		EnchantmentAPI.getEnchantment("Arcane Enhancement").addToItem(Gem, 1);
		return Gem;
	}
}
