package com.caved_in.Runnables;

import com.caved_in.TotalWar;

public class ReplaceEventsRunnable implements Runnable
{
	@Override
	public void run()
	{
		TotalWar.EventDynamics.ReplaceAllEvents();
	}
}