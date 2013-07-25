package com.caved_in.command_executors;

import ca.wacos.nametagedit.NametagAPI;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.caved_in.TotalWar;
import com.caved_in.Cooldown.Cooldown;

public class TemplarPaganCommandExecutor implements CommandExecutor
{
	private TotalWar Plugin;
	private String[] Faction_Names = { "Templar", "Templars", "Pagan", "Pagans", "Pirates", "Pirate", "Dragonkins", "Dragonkin" };
	private String[] Faction_Permissions = { "Totalwar.Dragonkin", "Totalwar.Pagan", "Totalwar.Templar", "Totalwar.Pirate", "nametags.color.aqua", "nametags.color.green", "nametags.color.yellow", "nametags.color.light_purple" };
	private Cooldown Cooldown = new Cooldown(1800);

	public TemplarPaganCommandExecutor(TotalWar plugin)
	{
		this.Plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args)
	{
		if ((Sender instanceof ConsoleCommandSender))
		{
			if (Args.length > 0)
			{
				Player Player = this.Plugin.getServer().getPlayer(Args[0]);
				String Faction_Name = Args[1];
				if ((Faction_Name.equalsIgnoreCase(this.Faction_Names[0])) || (Faction_Name.equalsIgnoreCase(this.Faction_Names[1])))
				{
					MakeTemplar(Player);
					this.Cooldown.SetOnCooldown(Player.getName());
					NametagAPI.setNametagSoft(Player.getName(), "", " the Templar");
					return true;
				}
				if ((Faction_Name.equalsIgnoreCase(this.Faction_Names[2])) || (Faction_Name.equalsIgnoreCase(this.Faction_Names[3])))
				{
					MakePagan(Player);
					this.Cooldown.SetOnCooldown(Player.getName());
					NametagAPI.setNametagSoft(Player.getName(), "", " the Pagan");
					return true;
				}
				if ((Faction_Name.equalsIgnoreCase(this.Faction_Names[4])) || (Faction_Name.equalsIgnoreCase(this.Faction_Names[5])))
				{
					MakePirate(Player);
					this.Cooldown.SetOnCooldown(Player.getName());
					NametagAPI.setNametagSoft(Player.getName(), "", " the Pirate");
					return true;
				}
				if ((Faction_Name.equalsIgnoreCase(this.Faction_Names[6])) || (Faction_Name.equalsIgnoreCase(this.Faction_Names[7])))
				{
					MakeDragonkin(Player);
					NametagAPI.setNametagSoft(Player.getName(), "", " the DragonKin");
					this.Cooldown.SetOnCooldown(Player.getName());
					return true;
				}

				return false;
			}

			return false;
		}
		if ((Sender instanceof Player))
		{
			if (Args.length > 0)
			{
				Player Player = (Player) Sender;
				if (!Player.isOp())
				{
					if (this.Cooldown.IsOnCooldown(Player.getName()))
					{
						Player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou can only switch factions every half hour.. Try again soon."));
						return true;
					}
				}

				String Faction_Name = Args[0];
				if ((Faction_Name.equalsIgnoreCase(this.Faction_Names[0])) || (Faction_Name.equalsIgnoreCase(this.Faction_Names[1])))
				{
					MakeTemplar(Player);
					NametagAPI.setNametagSoft(Player.getName(), "", " the Templar");
					this.Cooldown.SetOnCooldown(Player.getName());
					ColorMessage(Player, "&cWelcome to the Templars!");
					return true;
				}
				if ((Faction_Name.equalsIgnoreCase(this.Faction_Names[2])) || (Faction_Name.equalsIgnoreCase(this.Faction_Names[3])))
				{
					MakePagan(Player);
					NametagAPI.setNametagSoft(Player.getName(), "", " the Pagan");
					this.Cooldown.SetOnCooldown(Player.getName());
					ColorMessage(Player, "&cWelcome to the Pagans!");
					return true;
				}
				if ((Faction_Name.equalsIgnoreCase(this.Faction_Names[4])) || (Faction_Name.equalsIgnoreCase(this.Faction_Names[5])))
				{
					MakePirate(Player);
					NametagAPI.setNametagSoft(Player.getName(), "", " the Pirate");
					this.Cooldown.SetOnCooldown(Player.getName());
					ColorMessage(Player, "&cWelcome to the Pirates!");
					return true;
				}
				if ((Faction_Name.equalsIgnoreCase(this.Faction_Names[6])) || (Faction_Name.equalsIgnoreCase(this.Faction_Names[7])))
				{
					MakeDragonkin(Player);
					NametagAPI.setNametagSoft(Player.getName(), "", " the DragonKin");
					this.Cooldown.SetOnCooldown(Player.getName());
					ColorMessage(Player, "&cWelcome to the Dragonkin!");
					return true;
				}

				Sender.sendMessage("Invalid faction");
				return false;
			}

			Sender.sendMessage("Invalid arguments Q_Q");
			return false;
		}

		return false;
	}

	private void ColorMessage(Player Player, String Message)
	{
		Player.sendMessage(ChatColor.translateAlternateColorCodes('&', Message));
	}

	private void MakePagan(Player Player)
	{
		StripPermissions(Player);
		TotalWar.permission.playerAddGroup(Player, "Pagan");
	}

	private void MakeTemplar(Player Player)
	{
		StripPermissions(Player);
		TotalWar.permission.playerAddGroup(Player, "Templar");
	}

	private void MakePirate(Player Player)
	{
		StripPermissions(Player);
		TotalWar.permission.playerAddGroup(Player, "Pirate");
	}

	private void MakeDragonkin(Player Player)
	{
		StripPermissions(Player);
		TotalWar.permission.playerAddGroup(Player, "Dragonkin");
	}

	private void PermissionRemove(Player Player, String Permission)
	{
		TotalWar.permission.playerRemove(Player, Permission);
	}

	private void PermissionAdd(Player Player, String Permission)
	{
		TotalWar.permission.playerAdd(Player, Permission);
	}

	private void StripPermissions(Player Player)
	{
		/*
		 * for (String S : this.Faction_Permissions) { PermissionRemove(Player,
		 * S); }
		 */
		TotalWar.permission.playerRemoveGroup(Player, "Templar");
		TotalWar.permission.playerRemoveGroup(Player, "Pagan");
		TotalWar.permission.playerRemoveGroup(Player, "Dragonkin");
		TotalWar.permission.playerRemoveGroup(Player, "Pirate");
	}
}