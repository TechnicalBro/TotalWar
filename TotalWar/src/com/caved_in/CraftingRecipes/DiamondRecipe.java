package com.caved_in.CraftingRecipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import com.caved_in.TotalWar;

public class DiamondRecipe extends Recipe
{
	ShapedRecipe CoalToDiamond;
	private TotalWar Plugin;

	public DiamondRecipe(TotalWar Plugin)
	{
		this.Plugin = Plugin;
	}

	@Override
	public void registerRecipe()
	{
		ItemStack DiamondCrafted = new ItemStack(Material.DIAMOND);
		ShapedRecipe CoalToDiamond = new ShapedRecipe(DiamondCrafted);
		CoalToDiamond.shape(new String[] { "CCC", "CLC", "CEC" });
		CoalToDiamond.setIngredient('C', Material.COAL_BLOCK);
		CoalToDiamond.setIngredient('L', Material.LAVA_BUCKET);
		CoalToDiamond.setIngredient('E', Material.EYE_OF_ENDER);
		this.Plugin.getServer().addRecipe(CoalToDiamond);
	}
}