package com.caved_in.ItemMenus.SpellsMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.entity.Player;

import me.xhawk87.PopupMenuAPI.PopupMenu;
import me.xhawk87.PopupMenuAPI.PopupMenuAPI;
import TotalWizard.TotalWizard;
import TotalWizard.Handlers.Magic.Spells.Spell;

public class SpellsMenu
{
	private PopupMenu Spells;

	public SpellsMenu(Player Player)
	{
		List<SpellsItem> sItems = new ArrayList<SpellsItem>();
		for (Entry<String, Spell> Spell : TotalWizard.MagicHandler.getSpellList().entrySet())
		{
			sItems.add(new RenderSpellItems().generateItem(Spell.getKey(), Arrays.asList(Spell.getValue().getDescription()), Spell.getValue().getMaterialData()));
		}
		this.Spells = PopupMenuAPI.createMenu("Spells, buffs, and magic casts", (int) Math.ceil(sItems.size() / 9.0D));
		for (int I = 0; I < sItems.size(); I++)
		{
			this.Spells.addMenuItem(sItems.get(I), I);
		}
		this.Spells.setExitOnClickOutside(false);
		this.Spells.openMenu(Player);
	}

	public SpellsMenu()
	{
		List<SpellsItem> sItems = new ArrayList<SpellsItem>();
		for (Entry<String, Spell> Spell : TotalWizard.MagicHandler.getSpellList().entrySet())
		{
			sItems.add(new RenderSpellItems().generateItem(Spell.getKey(), Arrays.asList(Spell.getValue().getDescription()), Spell.getValue().getMaterialData()));
		}
		this.Spells = PopupMenuAPI.createMenu("Spells, buffs, and magic casts", (int) Math.ceil(sItems.size() / 9.0D));
		for (int I = 0; I < sItems.size(); I++)
		{
			this.Spells.addMenuItem(sItems.get(I), I);
		}
		this.Spells.setExitOnClickOutside(false);
	}

	public PopupMenu getMenu()
	{
		return this.Spells;
	}

}
