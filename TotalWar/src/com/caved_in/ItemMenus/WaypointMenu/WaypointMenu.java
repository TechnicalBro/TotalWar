package com.caved_in.ItemMenus.WaypointMenu;

import java.util.ArrayList;
import me.xhawk87.PopupMenuAPI.MenuItem;
import me.xhawk87.PopupMenuAPI.PopupMenu;
import me.xhawk87.PopupMenuAPI.PopupMenuAPI;
import org.bukkit.entity.Player;

public class WaypointMenu
{
	private PopupMenu Waypoint;

	public WaypointMenu(String NPCName, boolean NPC)
	{
		if (NPC)
		{
			ArrayList<WaypointItem> Items = new RenderWaypointItems().generateNPCItems(NPCName);
			this.Waypoint = PopupMenuAPI.createMenu("Travel Desinations!", (int) Math.ceil(Items.size() / 9.0D));
			for (int i = 0; i < Items.size(); i++)
			{
				this.Waypoint.addMenuItem(Items.get(i), i);
			}
		}
	}

	public void ShowMenu(Player P)
	{
		this.Waypoint.openMenu(P);
	}
}