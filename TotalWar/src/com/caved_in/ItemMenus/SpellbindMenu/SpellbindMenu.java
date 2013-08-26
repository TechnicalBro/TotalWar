package com.caved_in.ItemMenus.SpellbindMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.entity.Player;

import me.xhawk87.PopupMenuAPI.PopupMenu;
import me.xhawk87.PopupMenuAPI.PopupMenuAPI;
import TotalWizard.TotalWizard;
import TotalWizard.Handlers.Magic.Spells.Spell;

public class SpellbindMenu
{
	private PopupMenu Spells;

	public SpellbindMenu(Player Player)
	{
		List<SpellbindItem> sItems = new ArrayList<SpellbindItem>();
		for (Entry<String, Spell> Spell : TotalWizard.MagicHandler.getSpellList().entrySet())
		{
			sItems.add(new RenderSpellbindItems().generateItem(Spell.getKey(), Arrays.asList(Spell.getValue().getDescription()), Spell.getValue().getMaterialData(), Spell.getValue()));
		}
		this.Spells = PopupMenuAPI.createMenu("Select a spell to bind", (int) Math.ceil(sItems.size() / 9.0D));
		for (int I = 0; I < sItems.size(); I++)
		{
			this.Spells.addMenuItem(sItems.get(I), I);
		}
		this.Spells.setExitOnClickOutside(false);
		this.Spells.openMenu(Player);
	}

	public SpellbindMenu()
	{
		List<SpellbindItem> sItems = new ArrayList<SpellbindItem>();
		for (Entry<String, Spell> Spell : TotalWizard.MagicHandler.getSpellList().entrySet())
		{
			sItems.add(new RenderSpellbindItems().generateItem(Spell.getKey(), Arrays.asList(Spell.getValue().getDescription()), Spell.getValue().getMaterialData(), Spell.getValue()));
		}
		this.Spells = PopupMenuAPI.createMenu("Select a spell to bind", (int) Math.ceil(sItems.size() / 9.0D));
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
