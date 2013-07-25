package com.caved_in.command_executors;

import com.caved_in.TotalWar;

public class Commands
{
	public Commands(TotalWar Plugin)
	{
		Plugin.getCommand("join").setExecutor(new TemplarPaganCommandExecutor(Plugin));
		Plugin.getCommand("totalwar").setExecutor(new ToggleWarCommandExecutor(Plugin));
		Plugin.getCommand("itemgen").setExecutor(new ItemCommandExecutor());
		Plugin.getCommand("stats").setExecutor(new StatsCommandExecutor());
		Plugin.getCommand("setwaypoint").setExecutor(new SetwaypointCommandExecutor());
	}
}