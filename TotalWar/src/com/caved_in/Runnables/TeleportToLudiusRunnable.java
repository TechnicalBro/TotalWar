package com.caved_in.Runnables;

import org.bukkit.Bukkit;

public class TeleportToLudiusRunnable implements Runnable
{

	private String PlayerName = "";

	public TeleportToLudiusRunnable(String Player)
	{
		this.PlayerName = Player;
	}

	@Override
	public void run()
	{
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mv tp " + this.PlayerName + " skylands");
	}

}
