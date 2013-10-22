package com.caved_in.ItemMenus.SpellbindMenu;

import java.util.ArrayList;
import java.util.List;

import TotalWizard.Handlers.Magic.Spells.*;

import org.bukkit.ChatColor;
import org.bukkit.material.MaterialData;

public class RenderSpellbindItems
{

	public SpellbindItem generateItem(String Name, List<String> Lore, MaterialData Data, Spell Spell)
	{
		List<String> Lores = new ArrayList<String>();
		SpellbindItem Spells = new SpellbindItem(Name, Data,Spell);
		Lores.add(ChatColor.AQUA + "Costs $" + Spells.getCost() + " to bind");
		Lores.addAll(Lore);
		Spells.setDescriptions(Lores);
		return Spells;
	}

}
