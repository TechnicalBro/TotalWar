package com.caved_in.command_executors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.caved_in.TotalWar;

public class SetwaypointCommandExecutor implements CommandExecutor
{
	public SetwaypointCommandExecutor() { }
	
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args)
	{
		if (Sender instanceof Player)
		{
			Player Player = (Player)Sender;
			if (TotalWar.permission.has(Player, "TotalWar.Waypoints"))
			{
				if (Args.length >= 2)
				{
					String NPC = Args[0];
					String WarpName = "";
					for(int I = 1; I < Args.length; I++)
					{
						WarpName += Args[I] + "_";
					}
					String World = Player.getWorld().getName();
					int X = (int)Player.getLocation().getX();
					int Y = (int)Player.getLocation().getY();
					int Z = (int)Player.getLocation().getZ();
					if (TotalWar.Waypoints.WriteNPCData(NPC, WarpName, new int[] {X,Y,Z},World) == true)
					{
						Player.sendMessage(ChatColor.GOLD + "You've created the waypoint '" + WarpName + "' for the NPC: " + NPC);
						return true;
					}
					else
					{
						Player.sendMessage(ChatColor.RED + "Error saving waypoint, check console for error");
						return false;
					}
				}
			}
			else
			{
				Player.sendMessage("You don't have permission to use this command, sorry!");
			}
		}
		else
		{
			Sender.sendMessage("You must be in-game to use this command");
		}
		return false;
	}

}
