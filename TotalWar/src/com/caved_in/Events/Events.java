package com.caved_in.Events;

import com.caved_in.TotalWar;

public class Events
{
	public Events(TotalWar Plugin)
	{
		new EntityEvents(Plugin);
		Plugin.getServer().getPluginManager().registerEvents(new InteractEvent(Plugin), Plugin);
		Plugin.getServer().getPluginManager().registerEvents(new MobDeath(Plugin), Plugin);
		Plugin.getServer().getPluginManager().registerEvents(new MobSpawnEvent(Plugin), Plugin);
		Plugin.getServer().getPluginManager().registerEvents(new OnPlayerJoin(Plugin), Plugin);
		new PlayerInteractedEntity(Plugin);
		//new ServerListPing(Plugin);
		new Voting(Plugin);
		new DungeonChests(Plugin);
		TotalWar.Console("All Event listeners have been registered!");
	}
}