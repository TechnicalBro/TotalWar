package com.caved_in.ItemMenus.StatsMenu;

import java.util.ArrayList;

import me.xhawk87.PopupMenuAPI.PopupMenu;
import me.xhawk87.PopupMenuAPI.PopupMenuAPI;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import SkillSystem.Handlers.EXPChart;
import SkillSystem.Handlers.PlayerHandler;
import SkillSystem.Skills;

public class SkillsMenu
{
	private PopupMenu SkillsGUI;
	private String[] SkillNames = new String[] { "Melee", "Archery", "Magic", "Unarmed", "Taming", "Crafting", "Farming" };

	public SkillsMenu(Player Player)
	{
		String pName = Player.getName();
		ArrayList<StatShowItem> SkillsMenuItems = new ArrayList<StatShowItem>();
		PlayerHandler pSkillData = Skills.getPlayerHandler(pName);
		for (String Skill : this.SkillNames)
		{
			int ExpUntilLevel = EXPChart.getExpUntilNextLevel((int) pSkillData.getSkill(Skill).getExp(), (int) pSkillData.getSkill(Skill).getLevel());
			SkillsMenuItems.add(new StatShowItem(Skill, Skills.SkillsMenu.getIcon(Skill), String.valueOf(pSkillData.getSkill(Skill).getLevel()), String.valueOf(ExpUntilLevel)));
		}
		this.SkillsGUI = PopupMenuAPI.createMenu(ChatColor.GOLD + "Current Stats!", (int) Math.ceil(SkillsMenuItems.size() / 9.0D));
		for (int I = 0; I < SkillsMenuItems.size(); I++)
		{
			this.SkillsGUI.addMenuItem(SkillsMenuItems.get(I), I);
		}
		this.SkillsGUI.openMenu(Player);
	}

	public SkillsMenu(Player Viewer, Player StatsOf)
	{
		String pName = StatsOf.getName();
		ArrayList<StatShowItem> SkillsMenuItems = new ArrayList<StatShowItem>();
		PlayerHandler pSkillData = Skills.getPlayerHandler(pName);
		for (String Skill : this.SkillNames)
		{
			int ExpUntilLevel = EXPChart.getExpUntilNextLevel((int) pSkillData.getSkill(Skill).getExp(), (int) pSkillData.getSkill(Skill).getLevel());
			SkillsMenuItems.add(new StatShowItem(StatsOf.getName(), Skill, Skills.SkillsMenu.getIcon(Skill), String.valueOf(pSkillData.getSkill(Skill).getLevel()), String.valueOf(ExpUntilLevel)));
		}
		this.SkillsGUI = PopupMenuAPI.createMenu(ChatColor.GOLD + pName + "'s Current Stats!", (int) Math.ceil(SkillsMenuItems.size() / 9.0D));
		for (int I = 0; I < SkillsMenuItems.size(); I++)
		{
			this.SkillsGUI.addMenuItem(SkillsMenuItems.get(I), I);
		}
		this.SkillsGUI.openMenu(Viewer);
	}
}