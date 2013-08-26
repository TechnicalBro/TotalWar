package com.caved_in.CraftingRecipes.RecipeInterface;

import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public interface IRecipe
{
	
	public List<RecipeIngredient> getIngredients();
	
	public ItemStack getOutput();
	
	public String[] getShape();

	
	
}
