package com.caved_in.Runnables;

import org.bukkit.Bukkit;

public class CPACraftReminder implements Runnable
{

	@Override
	public void run()
	{
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "say If you'd like to help support the server, but aren't able to donate there's a new alternative! Visit www.totalwar.cpacraft.com for more info; It's like donating, without the cash!");
	}

}
