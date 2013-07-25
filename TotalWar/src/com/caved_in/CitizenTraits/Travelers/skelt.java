package com.caved_in.CitizenTraits.Travelers;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.caved_in.TotalWar;

public class skelt extends Traveler
{

	@Override
	public String getFaction()
	{
		return TotalWar.DragonKin_Permission;
	}

	@Override
	public String getName()
	{
		return "skelt";
	}

	@Override
	public Location getTeleportLocation(Player Player)
	{
		return new Location(Player.getWorld(), 433, 46, -788);
	}

}
