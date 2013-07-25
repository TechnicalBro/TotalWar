package com.caved_in.Events;

import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.caved_in.TotalWar;

public class PlayerDeath implements Listener
{

	public PlayerDeath(TotalWar Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}

	public void PlayerHasDied(PlayerDeathEvent Event)
	{

	}

}
