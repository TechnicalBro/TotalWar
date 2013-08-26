package com.caved_in.CraftingRecipes.Recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import com.caved_in.TotalWar;
import com.caved_in.CraftingRecipes.Recipe;

public class SaddleRecipe extends Recipe
{
	private TotalWar Plugin;

	public SaddleRecipe(TotalWar Plugin)
	{
		this.Plugin = Plugin;
	}

	@Override
	public void registerRecipe()
	{
		ItemStack SaddleCrafted = new ItemStack(Material.SADDLE);
		SaddleCrafted.setAmount(1);
		ShapedRecipe SaddleShape = new ShapedRecipe(SaddleCrafted);
		SaddleShape.shape(new String[] { "   ", " L ", "ILI" });
		SaddleShape.setIngredient('I', Material.IRON_INGOT);
		SaddleShape.setIngredient('L', Material.LEATHER);
		this.Plugin.getServer().addRecipe(SaddleShape);
	}

}
