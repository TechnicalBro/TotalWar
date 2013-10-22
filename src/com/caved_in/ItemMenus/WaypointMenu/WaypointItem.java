package com.caved_in.ItemMenus.WaypointMenu;

import me.xhawk87.PopupMenuAPI.MenuItem;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;
import org.bukkit.World;

public class WaypointItem extends MenuItem
{
	private Double[] XYZ;
	private String World;

	public WaypointItem(String text, MaterialData icon, Double[] XYZ, String World)
	{
		super(text, icon);
		this.XYZ = XYZ;
		this.World = World;
	}

	@Override
	public void onClick(Player Player)
	{
		World wWorld = Bukkit.getWorld(this.World);
		if (wWorld != null)
		{
			Player.teleport(new Location(wWorld, this.XYZ[0], this.XYZ[1], this.XYZ[2]));
		}
		// this.XYZ[0].doubleValue(), this.XYZ[1].doubleValue(),
		// this.XYZ[2].doubleValue()));
	}
}