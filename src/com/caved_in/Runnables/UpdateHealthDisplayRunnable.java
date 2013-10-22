package com.caved_in.Runnables;

import com.caved_in.TotalWar;

public class UpdateHealthDisplayRunnable implements Runnable
{
	@Override
	public void run()
	{
		TotalWar.SBMan.UpdatePlayersHealth();
	}
}