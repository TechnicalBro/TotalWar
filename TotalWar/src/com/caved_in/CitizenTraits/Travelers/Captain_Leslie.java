package com.caved_in.CitizenTraits.Travelers;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.caved_in.TotalWar;

public class Captain_Leslie extends Traveler
{

	@Override
	public String getFaction()
	{
		return TotalWar.Pirate_Permission;
	}

	@Override
	public String getName()
	{
		return "captain leslie";
	}

	@Override
	public Location getTeleportLocation(Player Player)
	{
		return new Location(Player.getWorld(), 633, 63, 173);
	}

}
