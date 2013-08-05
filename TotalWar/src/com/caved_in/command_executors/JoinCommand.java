package com.caved_in.command_executors;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.caved_in.ItemMenus.MainMenu.JoinMenu.JoinMenu;

public class JoinCommand implements CommandExecutor
{
	public JoinCommand() { }

	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args)
	{
		if (Sender instanceof ConsoleCommandSender)
		{
			if (Args.length > 0)
			{
				Player Player = Bukkit.getPlayer(Args[0]);
				if (Player != null)
				{
					 new JoinMenu(Player);
					 return true;
				}
				return false;
			}
			return false;
		}
		else if (Sender instanceof Player)
		{
			new JoinMenu((Player)Sender);
			return true;
		}
		return false;
	}
}