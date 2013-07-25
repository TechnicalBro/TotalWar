package com.caved_in.CitizenTraits.Travelers;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.caved_in.TotalWar;

public class Scally extends Traveler
{

	@Override
	public String getFaction()
	{
		return TotalWar.Templar_Permission;
	}

	@Override
	public String getName()
	{
		return "Scally";
	}

	@Override
	public Location getTeleportLocation(Player Player)
	{
		return new Location(Player.getWorld(), -561, 63, -31);
	}

}
