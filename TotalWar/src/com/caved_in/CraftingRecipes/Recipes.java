package com.caved_in.CraftingRecipes;

import com.caved_in.TotalWar;

public class Recipes
{
	public Recipes(TotalWar Plugin)
	{
		// new CoalRecipe(Plugin).registerRecipe();
		new DiamondRecipe(Plugin).registerRecipe();
		new LeatherRecipe(Plugin).registerRecipe();
		new SaddleRecipe(Plugin).registerRecipe();
		// new GrapplerArrowRecipe().registerRecipe();
		// new GrapplerRecipe().registerRecipe();
	}
}