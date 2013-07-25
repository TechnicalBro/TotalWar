package com.caved_in.Handlers.PlayerHandlers;

import org.bukkit.entity.Player;

import com.caved_in.TotalWar;

public class PlayerHandler
{
	public PlayerHandler(TotalWar Plugin)
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

	/*
	 * public String getPlayerFactionPerm(Player Player) { if
	 * (TotalWar.permission.has(Player, "Templar")) { return "Totalwar.Templar";
	 * } else if (TotalWar.permission.has(Player, "Pagan")) { return
	 * "Totalwar.Pagan"; } else if (TotalWar.permission.has(Player,
	 * "Totalwar.Pirate")) { return "Totalwar.Pirate"; } else if
	 * (TotalWar.permission.has(Player, "Totalwar.Dragonkin")) { return
	 * "Totalwar.Dragonkin"; } return null; }
	 */
	public boolean isSameFaction(Player PlayerOne, Player PlayerTwo)
	{
		return getPlayerFaction(PlayerOne) == getPlayerFaction(PlayerTwo);
	}

	/*
	 * public boolean isSameFactionPerm(Player PlayerOne, Player PlayerTwo) {
	 * return }
	 */

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
}