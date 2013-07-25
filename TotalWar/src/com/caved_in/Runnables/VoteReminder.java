package com.caved_in.Runnables;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class VoteReminder implements Runnable
{
	@Override
	public void run()
	{
		for (Player P : Bukkit.getOnlinePlayers())
		{
			P.sendMessage(ChatColor.LIGHT_PURPLE + "Hey," + P.getName() + ", did you vote for TotalWar on PlanetMinecraft?");
			P.sendMessage(ChatColor.LIGHT_PURPLE + "If you vote, you can get some sweet armor, or weapons! To vote, go to www.vote.caved.in to vote <3");
		}
		// Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
		// "/say Don't forget to vote for us on planetminecraft!");
	}
}
