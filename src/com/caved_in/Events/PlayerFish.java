package com.caved_in.Events;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.caved_in.Handlers.PlayerHandlers.PlayerHandler;
import com.caved_in.TotalWarItems.TotalWarItems;

public class PlayerFish implements Listener
{
	public PlayerFish(JavaPlugin Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}
	
	
	@EventHandler
	public void PlayerFished(PlayerFishEvent Event)
	{
		if (Event.getState() == State.CAUGHT_FISH)
		{
			Event.setCancelled(true);
			ItemStack Fish = TotalWarItems.ItemHandler.getCustomItemStack("Fish").getItem();
			ItemMeta Meta = Fish.getItemMeta();
			Meta.setLore(Arrays.asList(new String[] {ChatColor.BLUE + "★ " + ChatColor.GREEN + "Caught by " + ChatColor.YELLOW + Event.getPlayer().getName() + ChatColor.BLUE + " ★"}));
			Fish.setItemMeta(Meta);
			if (PlayerHandler.hasEmptySlot(Event.getPlayer()))
			{
				Event.getPlayer().getInventory().addItem(Fish);
				Event.getPlayer().updateInventory();
			}
			else
			{
				Event.getPlayer().getWorld().dropItemNaturally(Event.getPlayer().getLocation(), Fish);
			}
		}
	}
	

}
