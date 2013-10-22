package com.caved_in.command_executors;

import com.caved_in.TotalWar;
import com.caved_in.command_executors.Admin.AdminCommands;
import com.caved_in.command_executors.Admin.ItemGen;
import com.caved_in.command_executors.Player.PlayerCommands;

public class CommandRegister
{
	public CommandRegister(TotalWar Plugin)
	{
		CommandController.registerCommands(Plugin, new AdminCommands(Plugin));
		CommandController.registerCommands(Plugin, new ItemGen());
		CommandController.registerCommands(Plugin, new PlayerCommands());
	}

}
