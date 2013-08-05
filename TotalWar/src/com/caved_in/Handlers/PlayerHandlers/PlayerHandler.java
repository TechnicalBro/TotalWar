package com.caved_in.Handlers.PlayerHandlers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ca.wacos.nametagedit.NametagAPI;

import com.caved_in.TotalWar;
import com.caved_in.Cooldown.GlobalCooldowns;

public class PlayerHandler
{
	public PlayerHandler()
	{
	}

	public Faction getPlayerFaction(Player Player)
	{
		if (TotalWar.permission.playerInGroup(Player, "Templar"))
		{
			return Faction.Templar;
		}
		else if (TotalWar.permission.playerInGroup(Player, "Pagan"))
		{
			return Faction.Pagan;
		}
		else if (TotalWar.permission.playerInGroup(Player, "Pirate"))
		{
			return Faction.Pirate;
		}
		else if (TotalWar.permission.has(Player, "Dragonkin"))
		{
			return Faction.Dragonkin;
		}
		return Faction.None;
	}
	
	public boolean isSameFaction(Player PlayerOne, Player PlayerTwo)
	{
		return getPlayerFaction(PlayerOne) == getPlayerFaction(PlayerTwo);
	}

	public Faction getFaction(String Name)
	{
		if (Name.equalsIgnoreCase("pagan"))
		{
			return Faction.Pagan;
		}
		if (Name.equalsIgnoreCase("templar"))
		{
			return Faction.Templar;
		}
		if (Name.equalsIgnoreCase("pirate"))
		{
			return Faction.Pirate;
		}
		if (Name.equalsIgnoreCase("dragonkin"))
		{
			return Faction.Dragonkin;
		}
		return null;
	}

	public static enum Faction
	{
		Templar, Pagan, Pirate, Dragonkin, None;
	}
	
	public boolean JoinFaction(Player Player, Faction Faction)
	{
		if (!Player.isOp())
		{
			if (GlobalCooldowns.FactionJoinCooldown.IsOnCooldown(Player.getName()))
			{
				Player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou can only switch factions every half hour.. Try again soon."));
				return true;
			}
		}
		switch (Faction)
		{
		case Dragonkin:
			MakeDragonkin(Player);
			NametagAPI.setNametagSoft(Player.getName(), "", " the DragonKin");
			GlobalCooldowns.FactionJoinCooldown.SetOnCooldown(Player.getName());
			ColorMessage(Player, "&cWelcome to the Dragonkin!");
			return true;
		case None:
			return false;
		case Pagan:
			MakePagan(Player);
			NametagAPI.setNametagSoft(Player.getName(), "", " the Pagan");
			GlobalCooldowns.FactionJoinCooldown.SetOnCooldown(Player.getName());
			ColorMessage(Player, "&cWelcome to the Pagans!");
			return true;
		case Pirate:
			MakePirate(Player);
			NametagAPI.setNametagSoft(Player.getName(), "", " the Pirate");
			GlobalCooldowns.FactionJoinCooldown.SetOnCooldown(Player.getName());
			ColorMessage(Player, "&cWelcome to the Pirates!");
			return true;
		case Templar:
			MakeTemplar(Player);
			NametagAPI.setNametagSoft(Player.getName(), "", " the Templar");
			GlobalCooldowns.FactionJoinCooldown.SetOnCooldown(Player.getName());
			ColorMessage(Player, "&cWelcome to the Templars!");
			return true;
		default:
			return false;
		}
	}
	
	private void StripPermissions(Player Player)
	{
		TotalWar.permission.playerRemoveGroup(Player, "Templar");
		TotalWar.permission.playerRemoveGroup(Player, "Pagan");
		TotalWar.permission.playerRemoveGroup(Player, "Dragonkin");
		TotalWar.permission.playerRemoveGroup(Player, "Pirate");
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
}