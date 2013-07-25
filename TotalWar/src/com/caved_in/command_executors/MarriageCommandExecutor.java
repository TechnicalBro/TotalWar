package com.caved_in.command_executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MarriageCommandExecutor implements CommandExecutor
{

	public MarriageCommandExecutor()
	{

	}

	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args)
	{
		if (Sender instanceof Player)
		{
			Player Player = (Player) Sender;
			if (Args.length > 0)
			{
				if (Args[0].equalsIgnoreCase("accept"))
				{
					if (Args[1].isEmpty() == false)
					{

					}
				}
			}
		}
		return false;
	}

}
