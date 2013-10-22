package com.caved_in.Handlers.ItemHandlers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.rit.sucy.EnchantmentAPI;

public class GemCraft
{
	private String Player_Name = "";
	private int Gem_Slot = 0;
	private String Gem_Name = "";

	/**
	 * Creates a new Instance
	 */
	public GemCraft()
	{
	}

	/**
	 * A new instance of "GemCraft" where the variables are assigned
	 * 
	 * @param PlayerName
	 *            The Player Name that's involved
	 * @param Gem_Slot
	 *            The inventory slot which the gem is in
	 * @param Gem_Name
	 *            The gems name
	 */
	public GemCraft(String PlayerName, int Gem_Slot, String Gem_Name)
	{
		this.Player_Name = PlayerName;
		this.Gem_Slot = Gem_Slot;
		this.Gem_Name = Gem_Name;
	}

	/**
	 * Has the player already set the gem they with to combine?
	 * 
	 * @return true if they have, false otherwise.
	 */
	public boolean isItemSet()
	{
		return this.Player_Name != "" && this.Gem_Slot != 0 && this.Gem_Name != "";
	}

	/**
	 * Get the involved players Name if it isn't null
	 * 
	 * @return Get the involved players Name
	 */
	public String getPlayerName()
	{
		return this.Player_Name;
	}

	/**
	 * @return Inventory slot index of the gem
	 */
	public int getGemSlot()
	{
		return this.Gem_Slot;
	}

	/**
	 * 
	 * @return The gems name
	 */
	public String getGemName()
	{
		return this.Gem_Name;
	}

	public void setPlayerName(String Name)
	{
		this.Player_Name = Name;
	}

	public void setGemSlot(int Slot)
	{
		this.Gem_Slot = Slot;
	}

	public void setGemName(String Name)
	{
		this.Gem_Name = Name;
	}

	public boolean isSameGem(ItemStack Item, int Slot)
	{
		if (this.isItemSet() == true)
		{
			if (Item.hasItemMeta())
			{
				ItemMeta Gem = Item.getItemMeta();
				if (Gem.hasDisplayName())
				{
					return Gem.getDisplayName().equalsIgnoreCase(this.Gem_Name) && Slot == this.Gem_Slot;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if the item involved is infact a gem
	 * 
	 * @param Item
	 *            The itemstack to check
	 * @return True if the item is a gem with enchantments, otherwise false
	 */
	public boolean isGem(ItemStack Item)
	{
		if (Item != null)
		{
			if ((Item.hasItemMeta()) && (Item.getType() != Material.WRITTEN_BOOK) && (Item.getType() != Material.BOOK))
			{
				ItemMeta GemMeta = Item.getItemMeta();
				if ((GemMeta.hasDisplayName()) && (GemMeta.getDisplayName().toLowerCase().contains("gem of")))
				{
					if (GemMeta.getEnchants().size() > 0 || EnchantmentAPI.getEnchantments(Item).size() > 0)
					{
						return true;
					}
				}
				return false;
			}
			return false;
		}
		return false;
	}
}