package com.caved_in.CitizenTraits.Travelers;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class Traveler
{

	public abstract String getFaction();

	public abstract String getName();

	public abstract Location getTeleportLocation(Player Player);

}
