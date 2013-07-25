package com.caved_in.CraftingRecipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import com.caved_in.TotalWar;

public class LeatherRecipe extends Recipe
{
	private TotalWar Plugin;

	public LeatherRecipe(TotalWar Plugin)
	{
		this.Plugin = Plugin;
	}

	@Override
	public void registerRecipe()
	{
		ItemStack LeatherCrafted = new ItemStack(Material.LEATHER);
		LeatherCrafted.setAmount(1);
		ShapedRecipe RtoL = new ShapedRecipe(LeatherCrafted);
		RtoL.shape(new String[] { "RRR", "RRR", "RRR" });
		RtoL.setIngredient('R', Material.ROTTEN_FLESH);
		this.Plugin.getServer().addRecipe(RtoL);
	}
}