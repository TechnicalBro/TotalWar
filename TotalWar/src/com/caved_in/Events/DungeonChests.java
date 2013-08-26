package com.caved_in.Events;

import java.util.List;

import me.McGrizZz.ChestRegen.API.Events.ChestRegenEvent;
import me.McGrizZz.ChestRegen.API.Events.RegenChestOpenEvent;
import me.McGrizZz.ChestRegen.Chests.Chest;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.caved_in.TotalWar;
import com.caved_in.Handlers.DungeonChestHandler.LootGenerator;

public class DungeonChests implements Listener
{

	public DungeonChests(TotalWar Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}

	@EventHandler
	public void ChestIsRegenerated(ChestRegenEvent Event)
	{
		Chest Chest = Event.getChest();
		List<ItemStack> ChestStuff = new LootGenerator().generateLoot();
		ItemStack[] ChestInv = ChestStuff.toArray(new ItemStack[ChestStuff.size()]);
		Chest.setTempInv(ChestInv);
		Chest.setItemStack(ChestInv);
	}

	@EventHandler
	public void ChestIsOpened(RegenChestOpenEvent Event)
	{
		Chest Chest = Event.getChest();
		List<ItemStack> ChestStuff = new LootGenerator().generateLoot();
		ItemStack[] ChestInv = ChestStuff.toArray(new ItemStack[ChestStuff.size()]);
		Chest.setItemStack(ChestInv);
	}
}
