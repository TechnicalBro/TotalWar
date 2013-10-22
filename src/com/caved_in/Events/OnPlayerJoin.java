package com.caved_in.Events;

import java.util.Arrays;

import ca.wacos.nametagedit.NametagAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import com.caved_in.TotalWar;
import com.caved_in.Handlers.ItemHandlers.ItemHandler;
import com.caved_in.TotalWarItems.TotalWarItems;
import com.caved_in.TotalWarItems.Handlers.Items.ItemStackHandler;
import com.caved_in.TotalWarItems.Handlers.Items.ItemStackHandler.Tier;

public class OnPlayerJoin implements Listener
{
	public OnPlayerJoin(TotalWar Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent Event)
	{
		Player Player = Event.getPlayer();
		Player.setHealthScale(20.0);
		Player.setHealthScaled(true);
		if (!TotalWar.PlayersWhoveJoinedHandler.Contains(Player.getName()))
		{
			TotalWar.PlayersWhoveJoinedHandler.AppendString(Player.getName());
			Player.getInventory().setHelmet(TotalWarItems.ItemHandler.getCustomItemStack("LeatherHat", Tier.Common).getItem());
			Player.getInventory().setChestplate(TotalWarItems.ItemHandler.getCustomItemStack("LeatherChest", Tier.Common).getItem());
			Player.getInventory().setLeggings(TotalWarItems.ItemHandler.getCustomItemStack("LeatherPants", Tier.Common).getItem());
			Player.getInventory().setBoots(TotalWarItems.ItemHandler.getCustomItemStack("LeatherBoots", Tier.Common).getItem());
			Player.getInventory().addItem(new ItemStack[] { TotalWarItems.ItemHandler.getCustomItemStack("Blade", ItemStackHandler.Tier.Common, Material.IRON_SWORD).getItem() });
			Player.getInventory().addItem(new ItemStack[] { TotalWarItems.ItemHandler.getCustomItemStack("Bow", ItemStackHandler.Tier.Common, Material.BOW).getItem() });
			Player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.ARROW, 15) });
			Player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.APPLE, 1) });
			Player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.BREAD, 1) });
			ItemStack Paper = ItemHandler.makeItemStack(Material.PAPER, ChatColor.WHITE + "Adventurers Toolkit", Arrays.asList(new String[] {ChatColor.YELLOW + "Used to access in-game menus!"}));
			Player.getInventory().addItem(Paper);
		}
		
		TotalWar.EventDynamics.putPlayerInAllEvents(Player.getName());
		
		if (TotalWar.permission.has(Player, "Totalwar.Pagan"))
		{
			NametagAPI.setNametagSoft(Player.getName(), "", " the Pagan");
		}
		else if (TotalWar.permission.has(Player, "Totalwar.Pirate"))
		{
			NametagAPI.setNametagSoft(Player.getName(), "", " the Pirate");
		}
		else if (TotalWar.permission.has(Player, "Totalwar.Dragonkin"))
		{
			NametagAPI.setNametagSoft(Player.getName(), "", " the DragonKin");
		}
		else if (TotalWar.permission.has(Player, "Totalwar.Templar"))
		{
			NametagAPI.setNametagSoft(Player.getName(), "", " the Templar");
		}
	}
}