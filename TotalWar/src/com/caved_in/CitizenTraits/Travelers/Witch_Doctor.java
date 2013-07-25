package com.caved_in.CitizenTraits.Travelers;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.caved_in.TotalWar;

public class Witch_Doctor extends Traveler
{

	@Override
	public String getFaction()
	{
		return TotalWar.Pagan_Permission;
	}

	@Override
	public String getName()
	{
		return "Witch Doctor";
	}

	@Override
	public Location getTeleportLocation(Player Player)
	{
		return new Location(Player.getWorld(), 534, 59, 1380);
	}

}
