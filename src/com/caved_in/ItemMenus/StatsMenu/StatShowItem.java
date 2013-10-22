package com.caved_in.ItemMenus.StatsMenu;

import java.util.Arrays;

import me.xhawk87.PopupMenuAPI.MenuItem;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import com.caved_in.ItemMenus.SpellsMenu.SpellsMenu;

public class StatShowItem extends MenuItem
{
	public StatShowItem(String SkillName, MaterialData icon, String SkillLevel, String ExpUntilLevel)
	{
		super(SkillName, icon);
		setDescriptions(Arrays.asList(new String[] { ChatColor.YELLOW + "Your " + SkillName + " is level " + SkillLevel, ChatColor.YELLOW + "You need " + ExpUntilLevel + " EXP to advance this skill!" }));
	}

	public StatShowItem(String Player, String SkillName, MaterialData icon, String SkillLevel, String ExpUntilLevel)
	{
		super(SkillName, icon);
		setDescriptions(Arrays.asList(new String[] { ChatColor.YELLOW + Player + "'s " + SkillName + " is level " + SkillLevel, ChatColor.YELLOW + Player + " needs " + ExpUntilLevel + " EXP to advance this skill!" }));
	}

	@Override
	public void onClick(Player Player)
	{
		if (this.getText().equalsIgnoreCase("magic"))
		{
			this.getMenu().switchMenu(Player, new SpellsMenu().getMenu());
		}
	}
}