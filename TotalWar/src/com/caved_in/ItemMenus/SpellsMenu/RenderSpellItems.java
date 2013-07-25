package com.caved_in.ItemMenus.SpellsMenu;

import java.util.ArrayList;
import java.util.List;

import me.cybermaxke.materialapi.inventory.CustomItemStack;

import org.bukkit.material.MaterialData;

public class RenderSpellItems
{

	public List<SpellsItem> generateItems(List<CustomItemStack> Items)
	{
		List<SpellsItem> Spells = new ArrayList<SpellsItem>();
		for (CustomItemStack Item : Items)
		{
			SpellsItem SpellItem = new SpellsItem(Item.getName(), new MaterialData(Item.getType()));
			SpellItem.setDescriptions(Item.getLore());
			Spells.add(SpellItem);
		}
		return Spells;
	}

	public SpellsItem generateItem(String Name, List<String> Lore, MaterialData Data)
	{
		SpellsItem Spells = new SpellsItem(Name, Data);
		Spells.setDescriptions(Lore);
		return Spells;
	}

}
