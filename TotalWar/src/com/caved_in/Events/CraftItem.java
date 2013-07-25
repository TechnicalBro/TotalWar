package com.caved_in.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

import com.caved_in.TotalWar;
import com.caved_in.Handlers.DynamicEvents.AreaEvent;

public class CraftItem implements Listener
{
	public CraftItem(TotalWar Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}

	// @EventHandler(priority = EventPriority.MONITOR)
	public void CraftedAnItem(CraftItemEvent Event)
	{
		/*
		 * if (Event.isCancelled()) { return; } try { if (Event.getSlotType() ==
		 * SlotType.CRAFTING) {
		 * CheckForEvents(Event.getInventory().getResult().getType(),(Player)
		 * Event.getWhoClicked()); } } catch (Exception Ex) {
		 * 
		 * }
		 */
	}

	private void CheckForEvents(Material Material, Player Player)
	{
		for (AreaEvent e : TotalWar.EventDynamics.getActiveEvents(AreaEvent.EventType.CRAFT))
		{
			if (Material.equals(e.getEventMaterial()))
			{
				if ((!TotalWar.EventDynamics.hasBeenRewarded(e, Player.getName())) && (!TotalWar.EventDynamics.isPlayerCompleted(e, Player.getName())))
				{
					TotalWar.EventDynamics.addPlayerProgress(e, Player.getName());
				}
			}
		}
	}
}