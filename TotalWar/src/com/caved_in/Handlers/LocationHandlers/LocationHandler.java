package com.caved_in.Handlers.LocationHandlers;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.caved_in.TotalWar;

public class LocationHandler
{
	private TotalWar Plugin;

	public LocationHandler(TotalWar Plugin)
	{
		this.Plugin = Plugin;
	}

	public ArrayList<Player> getPlayersInRadius(Location location, double radius)
	{
		ArrayList<Player> Players = new ArrayList<Player>();
		Player[] OnlinePlayers = this.Plugin.getServer().getOnlinePlayers();
		double radiusSquared = radius * radius;
		for (Player onlinePlayer : OnlinePlayers)
		{
			if (onlinePlayer.getLocation().distanceSquared(location) <= radiusSquared)
			{
				Players.add(onlinePlayer);
			}
		}
		return Players;
	}

	public boolean getPlayerInRadius(Location location, double Radius, Player P)
	{
		double radiusSquared = Radius * Radius;
		if (P.getLocation().distanceSquared(location) <= radiusSquared)
		{
			return true;
		}

		return false;
	}
}