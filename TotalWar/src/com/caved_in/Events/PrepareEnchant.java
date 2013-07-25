package com.caved_in.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;

import com.caved_in.TotalWar;

public class PrepareEnchant implements Listener
{

	public PrepareEnchant(TotalWar Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}

	@EventHandler
	public void PreparedEnchant(PrepareItemEnchantEvent Event)
	{
		Event.setCancelled(true);
	}

}
