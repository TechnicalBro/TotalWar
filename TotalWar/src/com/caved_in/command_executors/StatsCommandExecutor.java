package com.caved_in.command_executors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.caved_in.ItemMenus.StatsMenu.SkillsMenu;

public class StatsCommandExecutor implements CommandExecutor
{
	public StatsCommandExecutor()
	{
	}

	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args)
	{
		if ((Sender instanceof Player))
		{
			if (Args.length > 0)
			{
				String Playername = Args[0];
				Player Selected = Bukkit.getPlayer(Playername);
				if (Selected != null)
				{
					Player Player = (Player) Sender;
					new SkillsMenu(Player, Selected);
				}
				else
				{
					((Player) Sender).sendMessage(ChatColor.RED + "That's not a valid player... Make sure they're online!");
				}
			}
			else
			{
				Player Player = (Player) Sender;
				new SkillsMenu(Player);
			}
		}
		return true;
	}
}