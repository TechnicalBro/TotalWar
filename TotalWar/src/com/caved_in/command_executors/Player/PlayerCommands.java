package com.caved_in.command_executors.Player;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.caved_in.Handlers.ItemHandlers.ItemHandler;
import com.caved_in.ItemMenus.JoinMenu.JoinMenu;
import com.caved_in.ItemMenus.StatsMenu.SkillsMenu;
import com.caved_in.command_executors.CommandController.CommandHandler;

public class PlayerCommands
{
	
	@CommandHandler(name = "menu")
	public void MenuCommand(Player Player, String[] Args)
	{
		ItemStack Paper = ItemHandler.makeItemStack(Material.PAPER, ChatColor.WHITE + "Adventurers Toolkit", Arrays.asList(new String[] {ChatColor.YELLOW + "Used to access in-game menus!"}));
		if (!Player.getInventory().contains(Paper))
		{
			Player.getInventory().addItem(Paper);
		}
	}
	
	@CommandHandler(name = "stats")
	public void StatsCommand(Player Player, String[] Args)
	{
		if (Args.length > 0)
		{
			String Playername = Args[0];
			Player Selected = Bukkit.getPlayer(Playername);
			if (Selected != null)
			{
				new SkillsMenu(Player, Selected);
			}
			else
			{
				Player.sendMessage(ChatColor.RED + "That's not a valid player... Make sure they're online!");
			}
		}
		else
		{
			new SkillsMenu(Player);
		}
	}
	
	@CommandHandler(name = "join")
	public void JoinCommand(CommandSender Sender, String[] Args)
	{
		if (Sender instanceof ConsoleCommandSender)
		{
			if (Args.length > 0)
			{
				Player Player = Bukkit.getPlayer(Args[0]);
				if (Player != null)
				{
					 new JoinMenu(Player);
				}
			}
		}
		else if (Sender instanceof Player)
		{
			new JoinMenu((Player)Sender);
		}
	}

}
