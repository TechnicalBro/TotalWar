package com.caved_in.command_executors;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.caved_in.Handlers.ItemHandlers.ItemHandler;
import com.caved_in.ItemMenus.FriendsMenu.FriendsMenu;

public class FriendCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args)
	{
		if (Sender instanceof Player)
		{
			ItemStack Paper = new ItemHandler().makeItemStack(Material.PAPER, ChatColor.WHITE + "Adventurers Toolkit", Arrays.asList(new String[] {ChatColor.YELLOW + "Used to access in-game menus!"}));
			Player Player = (Player)Sender;
			if (!Player.getInventory().contains(Paper))
			{
				Player.getInventory().addItem(Paper);
			}
			return true;
		}
		return true;
	}

}
