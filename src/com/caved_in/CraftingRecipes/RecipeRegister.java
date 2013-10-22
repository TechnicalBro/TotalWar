package com.caved_in.CraftingRecipes;

import com.caved_in.TotalWar;
import com.caved_in.CraftingRecipes.Recipes.DiamondRecipe;
import com.caved_in.CraftingRecipes.Recipes.LeatherRecipe;
import com.caved_in.CraftingRecipes.Recipes.SaddleRecipe;

public class RecipeRegister
{
	public RecipeRegister(TotalWar Plugin)
	{
		// new CoalRecipe(Plugin).registerRecipe();
		new DiamondRecipe(Plugin).registerRecipe();
		new LeatherRecipe(Plugin).registerRecipe();
		new SaddleRecipe(Plugin).registerRecipe();
		// new GrapplerArrowRecipe().registerRecipe();
		// new GrapplerRecipe().registerRecipe();
	}
}