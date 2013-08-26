package com.caved_in.Events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.caved_in.TotalWar;
import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;

public class Voting implements Listener
{

	public Voting(TotalWar Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}

	@EventHandler
	public void Voted(VotifierEvent Event)
	{
		Vote Vote = Event.getVote();
		if (Bukkit.getOfflinePlayer(Vote.getUsername()).isOnline())
		{
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "totalwar votepack " + Vote.getUsername());
		}
	}
}
