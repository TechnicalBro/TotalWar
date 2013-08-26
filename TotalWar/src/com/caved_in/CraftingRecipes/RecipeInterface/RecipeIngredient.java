package com.caved_in.CraftingRecipes.RecipeInterface;

import org.bukkit.Material;

public class RecipeIngredient implements IRecipeIngredient
{
	private char Identifier;
	private Material Material;
	
	public RecipeIngredient(char Identifier, Material Material)
	{
		this.Identifier = Identifier;
		this.Material = Material;
	}
	
	@Override
	public char getIdentifier()
	{
		return this.Identifier;
	}

	@Override
	public Material getMaterial()
	{
		return this.Material;
	}
	
	public void setIdentifier(char Identifier)
	{
		this.Identifier = Identifier;
	}
	
	public void setMaterial(Material Material)
	{
		this.Material = Material;
	}
	
}
