package com.caved_in.Events;

import com.caved_in.TotalWar;

public class Events
{
	public Events(TotalWar Plugin)
	{
		//Plugin.getServer().getPluginManager().registerEvents(new BreakBlock(Plugin), Plugin);
		Plugin.getServer().getPluginManager().registerEvents(new DamageEnemy(Plugin), Plugin);
		Plugin.getServer().getPluginManager().registerEvents(new InteractEvent(Plugin), Plugin);
		Plugin.getServer().getPluginManager().registerEvents(new MobDeath(Plugin), Plugin);
		Plugin.getServer().getPluginManager().registerEvents(new MobSpawnEvent(Plugin), Plugin);
		Plugin.getServer().getPluginManager().registerEvents(new OnPlayerJoin(Plugin), Plugin);
		Plugin.getServer().getPluginManager().registerEvents(new PlayerInteractedEntity(Plugin), Plugin);
		Plugin.getServer().getPluginManager().registerEvents(new ServerListPing(Plugin), Plugin);
		Plugin.getServer().getPluginManager().registerEvents(new Voting(Plugin), Plugin);
		Plugin.getServer().getPluginManager().registerEvents(new DungeonChests(Plugin), Plugin);
		TotalWar.Console("All Event listeners have been registered!");
	}
}