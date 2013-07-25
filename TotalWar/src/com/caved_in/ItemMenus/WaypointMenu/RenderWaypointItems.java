package com.caved_in.ItemMenus.WaypointMenu;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;

import com.caved_in.TotalWar;

public class RenderWaypointItems
{
	/*
	 * public ArrayList<WaypointItem> generateItems() { ArrayList<WaypointItem>
	 * Items = new ArrayList<WaypointItem>(); for (String S :
	 * TotalWar.Waypoints.getIDS(TotalWar.Waypoints.WarpBlocks())) {
	 * Items.add(new WaypointItem(TotalWar.Waypoints.getName(S), new
	 * MaterialData(Material.COMPASS), TotalWar.Waypoints.getCoords(S))); }
	 * return Items; }
	 */
	public ArrayList<WaypointItem> generateNPCItems(String NPC)
	{
		ArrayList<WaypointItem> Items = new ArrayList<WaypointItem>();
		for (String S : TotalWar.Waypoints.getNPCBlocks(NPC))
		{
			Items.add(new WaypointItem(TotalWar.Waypoints.getWarpName(S), new MaterialData(Material.COMPASS), TotalWar.Waypoints.getCoords(NPC, TotalWar.Waypoints.getWarpName(S)), TotalWar.Waypoints.getWorld(NPC, TotalWar.Waypoints.getWarpName(S))));
		}
		return Items;
	}
}