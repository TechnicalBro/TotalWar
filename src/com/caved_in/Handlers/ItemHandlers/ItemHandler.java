package com.caved_in.Handlers.ItemHandlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemHandler
{
	/**
	 * Get the lore of an item if it has lore
	 * 
	 * @param Item
	 *            Item to get lore of
	 * @return ArrayList<String> of the lore if the item has lore, otherwise
	 *         null
	 */
	public static ArrayList<String> getItemLore(ItemStack Item)
	{
		ArrayList<String> ReturnLore = new ArrayList<String>();
		if (Item.hasItemMeta() && Item.getItemMeta().hasLore())
		{
			for (String S : Item.getItemMeta().getLore())
			{
				ReturnLore.add(S);
			}
			return ReturnLore;
		}
		return null;
	}

	/**
	 * Get lore of item at specific line
	 * 
	 * @param Item
	 *            Item to get lore of
	 * @param Line
	 *            Index of lore to get
	 * @return String of lore if it exists, otherwise null
	 */
	public static String getItemLore(ItemStack Item, int Line)
	{
		if (Item.hasItemMeta() && Item.getItemMeta().hasLore())
		{
			try
			{
				return Item.getItemMeta().getLore().get(Line);
			}
			catch (IndexOutOfBoundsException Exception)
			{
				Exception.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	public static ItemStack addLore(ItemStack Item, List<String> Lore)
	{
		ItemStack Stack = Item.clone();
		ItemMeta iMeta = Stack.getItemMeta();
		List<String> LoreLine = new ArrayList<String>();
		if (Item.hasItemMeta() && Item.getItemMeta().hasLore())
		{
			LoreLine = Item.getItemMeta().getLore();
		}
		LoreLine.addAll(Lore);
		iMeta.setLore(LoreLine);
		Stack.setItemMeta(iMeta);
		return new ItemStack(Stack);
	}

	/**
	 * Checks if an items lore contains specific text
	 * 
	 * @param Item
	 *            Item to check
	 * @param Text
	 *            Text to check the item for
	 * @return true if the item has the text in its lore, otherwise false.
	 */
	public static boolean itemLoreContains(ItemStack Item, String Text)
	{
		if (Item.hasItemMeta() && Item.getItemMeta().hasLore())
		{
			ArrayList<String> Lore = getItemLore(Item);
			for (String s : Lore)
			{
				if (s.toLowerCase().contains(Text.toLowerCase()))
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Set the name of an Item
	 * 
	 * @param Item
	 *            Item to set the name of
	 * @param Text
	 *            The name to give the item
	 */
	public static ItemStack setItemName(ItemStack Item, String Text)
	{
		ItemStack Stack = Item.clone();
		ItemMeta iMeta = Stack.getItemMeta();
		iMeta.setDisplayName(Text);
		Stack.setItemMeta(iMeta);
		return new ItemStack(Stack);
	}

	/**
	 * Get the name of an item
	 * 
	 * @param Item
	 * @return Name of the item if it has a name, otherwise its material type in
	 *         lowercase
	 */
	public static String getItemName(ItemStack Item)
	{
		if (Item.hasItemMeta() && Item.getItemMeta().hasDisplayName())
		{
			return Item.getItemMeta().getDisplayName();
		}
		return getFormattedMaterialName(Item.getType());
	}

	/**
	 * Removes an amount of items from a stack
	 * 
	 * @param Item
	 * @param Amount
	 * @return The itemstack if there are more than 0 items left in the stack,
	 *         otherwise null
	 */
	public static ItemStack RemoveFromStack(ItemStack Item, int Amount)
	{
		if (Item.getAmount() > Amount)
		{
			ItemMeta StackMeta = Item.getItemMeta();
			ItemStack Return = new ItemStack(Item.getType(), Item.getAmount() - Amount);
			Return.setItemMeta(StackMeta);
			Return.setDurability(Item.getDurability());
			return Return;
		}
		return null;
	}

	/**
	 * Set the color to a piece of leather armor
	 * 
	 * @param Item
	 * @param Color
	 * @return true if the color was set, otherwise false
	 */
	public static ItemStack setColor(ItemStack Item, Color Color)
	{
		List<Material> Leathers = Arrays.asList(new Material[] { Material.LEATHER_BOOTS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LEATHER_LEGGINGS });
		if (Leathers.contains(Item.getType()))
		{
			try
			{
				LeatherArmorMeta ItemMeta = (LeatherArmorMeta) Item.getItemMeta();
				ItemMeta.setColor(Color);
				Item.setItemMeta(ItemMeta);
			}
			catch (Exception Ex)
			{
				Ex.printStackTrace();
				return Item;
			}
		}
		return Item;
	}

	/**
	 * Add an enchantment to an item ignoring the restrictions
	 * 
	 * @param Item
	 * @param Enchant
	 * @param Level
	 * @param IgnoreRestrictions
	 * @return true if the enchantment was added, false otherwise
	 */
	public static ItemStack addEnchantment(ItemStack Item, Enchantment Enchant, int Level, boolean IgnoreRestrictions)
	{
		ItemStack Stack = Item.clone();
		ItemMeta iMeta = Stack.getItemMeta();
		boolean Status = iMeta.addEnchant(Enchant, Level, IgnoreRestrictions);
		Stack.setItemMeta(iMeta);
		return new ItemStack(Stack);
	}

	/**
	 * Calls addEnchantment but follows restrictions
	 * 
	 * @param Item
	 * @param Enchant
	 * @param Level
	 * @return
	 */
	public static ItemStack addEnchantment(ItemStack Item, Enchantment Enchant, int Level)
	{
		return addEnchantment(Item, Enchant, Level, false);
	}

	public static ItemStack setItemLore(ItemStack Item, List<String> Lore)
	{
		ItemStack Stack = Item.clone();
		try
		{
			ItemMeta iMeta = Stack.getItemMeta();
			iMeta.setLore(Lore);
			Stack.setItemMeta(iMeta);
			return new ItemStack(Stack);
		}
		catch (Exception Ex)
		{
			Ex.printStackTrace();
			return new ItemStack(Stack);
		}
	}

	public static ItemStack makeItemStack(Material Material)
	{
		return new ItemStack(Material);
	}

	public static ItemStack makeItemStack(Material Material, String Name)
	{
		ItemStack Item = new ItemStack(Material);
		Item = setItemName(Item, Name);
		return Item;
	}

	public static ItemStack makeItemStack(Material Material, String Name, List<String> Lore)
	{
		ItemStack Item = new ItemStack(Material);
		Item = setItemName(Item,Name);
		Item = setItemLore(Item,Lore);
		return new ItemStack(Item);
	}

	public static ItemStack makeLeatherItemStack(Material Material, String Name, List<String> Lore, HashMap<Enchantment, Integer> Enchantments, Color Color)
	{
		ItemStack Item = new ItemStack(Material);
		Item = setItemName(Item, Name);
		Item = setItemLore(Item, Lore);
		for (Entry<Enchantment, Integer> Enchant : Enchantments.entrySet())
		{
			Item = addEnchantment(Item, Enchant.getKey(), Enchant.getValue(), true);
		}
		Item = setColor(Item, Color);
		return new ItemStack(Item);
	}
	
	public static ItemStack makeLeatherItemStack(Material Material, String Name, List<String> Lore, Color Color)
	{
		ItemStack Item = new ItemStack(Material);
		Item = setItemName(Item, Name);
		Item = setItemLore(Item, Lore);
		Item = setColor(Item, Color);
		return new ItemStack(Item);
	}
	
	public static ItemStack makeLeatherItemStack(Material Material,Color Color)
	{
		ItemStack Item = new ItemStack(Material);
		Item = setColor(Item, Color);
		return new ItemStack(Item);
	}
	
	public static String getFormattedMaterialName(Material Material)
	{
		return WordUtils.capitalize(Material.name().toLowerCase().replaceAll("_", " "));
	}
	
	public static Material unFormatMaterialName(String string)
	{
		  return Material.valueOf(string.toUpperCase().replaceAll(" ", "_"));
	}
	
	public static boolean IsSameItemstack(ItemStack One, ItemStack Two)
	{
		ItemStack Oc = One.clone();
		ItemStack Tc = Two.clone();
		
		if (Oc.getType() == Tc.getType())
		{
			if (Oc.getData() == Tc.getData())
			{
				if (Oc.hasItemMeta() && Tc.hasItemMeta())
				{
					ItemMeta oMeta = Oc.getItemMeta();
					ItemMeta tMeta = Tc.getItemMeta();
					if (oMeta.hasDisplayName() && tMeta.hasDisplayName())
					{
						if (!oMeta.getDisplayName().equalsIgnoreCase(tMeta.getDisplayName()))
						{
							return false;
						}
					}
					
					if (oMeta.hasLore() && tMeta.hasLore())
					{
						if (!Arrays.equals(oMeta.getLore().toArray(), tMeta.getLore().toArray()))
						{
							return false;
						}
					}
					
					if (oMeta.hasEnchants() && tMeta.hasEnchants())
					{
						if ((!Arrays.equals(oMeta.getEnchants().keySet().toArray(),tMeta.getEnchants().keySet().toArray())) && !Arrays.equals(oMeta.getEnchants().values().toArray(), tMeta.getEnchants().values().toArray()))
						{
							return false;
						}
					}
					
					return true;
					
				}
				else
				{
					return true;
				}
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
}