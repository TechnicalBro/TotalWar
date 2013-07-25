package com.caved_in.Runnables;

import org.bukkit.Bukkit;

public class BuildContestRunnable implements Runnable
{

	@Override
	public void run()
	{
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "say Be sure to sign up on our forums and be active in the community! http://www.caved.in/forum");
	}

}
