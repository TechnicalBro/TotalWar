package com.caved_in.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import com.caved_in.TotalWar;
import com.caved_in.Handlers.Misc.MOTDHandler;

public class ServerListPing implements Listener
{

	public ServerListPing(TotalWar Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}

	@EventHandler
	public void ServerListPingCall(ServerListPingEvent Event)
	{
		Event.setMotd(TotalWar.MotdHandler.getRandomMotd());
	}
}