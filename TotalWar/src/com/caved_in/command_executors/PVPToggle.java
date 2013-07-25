package com.caved_in.command_executors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.caved_in.TotalWar;
import com.caved_in.Cooldown.Cooldown;

public class PVPToggle implements CommandExecutor
{
	private Cooldown Cooldown = new Cooldown(900);

	public PVPToggle(TotalWar Plugin)
	{
	}

	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args)
	{
		if ((Args.length > 0) && ((Sender instanceof Player)))
		{
			Player Player = (Player) Sender;
			if ((!this.Cooldown.IsOnCooldown(Player.getName())) || (Player.isOp()))
			{
				if (Args[0].equalsIgnoreCase("on"))
				{
					if (TotalWar.PVP_Config.IsPvpEnabled(Player.getName()))
					{
						Player.sendMessage(ChatColor.RED + "Your pvp is already on!");
						return true;
					}

					TotalWar.PVP_Config.setPvpStatus(Player.getName(), true);
					Player.sendMessage(ChatColor.RED + "You've toggled your On!");
					TotalWar.Console(Player.getName() + " toggled PVP On!");
					this.Cooldown.SetOnCooldown(Player.getName());
					return true;
				}

				if (Args[0].equalsIgnoreCase("off"))
				{
					if (!TotalWar.PVP_Config.IsPvpEnabled(Player.getName()))
					{
						Player.sendMessage(ChatColor.RED + "Your pvp is already off!");
						return true;
					}

					TotalWar.PVP_Config.setPvpStatus(Player.getName(), false);
					Player.sendMessage(ChatColor.RED + "You've toggled your Off!");
					TotalWar.Console(Player.getName() + " toggled PVP Off!");
					this.Cooldown.SetOnCooldown(Player.getName());
					return true;
				}

			}
			else
			{
				Player.sendMessage(ChatColor.RED + "You need to wait" + this.Cooldown.RemainingSeconds(Player.getName()) + " seconds before you can use this command");
				return true;
			}
		}
		return false;
	}
}