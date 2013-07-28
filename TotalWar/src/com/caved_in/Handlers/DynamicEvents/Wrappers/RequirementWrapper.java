package com.caved_in.Handlers.DynamicEvents.Wrappers;

import java.util.HashMap;

import org.bukkit.inventory.ItemStack;

public class RequirementWrapper
{
	private int Required = 0;
	private int PlayerHas = 0;
	private HashMap<Integer, ? extends ItemStack> ItemSlots;
	/**
	 * Initiates a new "Requirement Wrapper" for events that require materials
	 * @param AmountRequired
	 * @param PlayerAmount
	 * @param ItemLocations
	 */
	public RequirementWrapper(int AmountRequired, int PlayerAmount,HashMap<Integer, ? extends ItemStack> ItemLocations)
	{
		this.Required = AmountRequired;
		this.PlayerHas = PlayerAmount;
		this.ItemSlots = ItemLocations;
	}
	
	public boolean hasRequiredAmount()
	{
		return (PlayerHas >= Required);
	}
	
	public int amountRemaining()
	{
		if (!hasRequiredAmount())
		{
			return Required - PlayerHas;
		}
		return 0;
	}
	
	public int getLeftovers()
	{
		if (hasRequiredAmount())
		{
			if (PlayerHas > Required)
			{
				return PlayerHas - Required;
			}
			else
			{
				return 0;
			}
		}
		return 0;
	}
	
	public int RequiredAmount()
	{
		return this.Required;
	}
	
	public int PlayerAmount()
	{
		return this.PlayerHas;
	}
	
	public HashMap<Integer, ? extends ItemStack> getItemLocations()
	{
		return this.ItemSlots;
	}

}
