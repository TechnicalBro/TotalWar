package com.caved_in.Events;

import ca.wacos.nametagedit.NametagAPI;
import me.cybermaxke.materialapi.inventory.CustomItemStack;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import com.caved_in.TotalWar;
import com.caved_in.Items.Armors.LeatherBoots;
import com.caved_in.Items.Armors.LeatherCaps;
import com.caved_in.Items.Armors.LeatherChest;
import com.caved_in.Items.Armors.LeatherPants;
import com.caved_in.TotalWarItems.TotalWarItems;
import com.caved_in.TotalWarItems.Handlers.ItemStackHandler;
import com.caved_in.TotalWarItems.Handlers.ItemStackHandler.Tier;

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
		if (!TotalWar.PlayersWhoveJoinedHandler.Contains(Player.getName()))
		{
			TotalWar.PlayersWhoveJoinedHandler.AppendString(Player.getName());
			Player.getInventory().setHelmet(new CustomItemStack(new LeatherCaps("StarterHat", Material.LEATHER_HELMET, Tier.Common)).getItem());
			Player.getInventory().setChestplate(new CustomItemStack(new LeatherChest("StarterChest", Material.LEATHER_CHESTPLATE, Tier.Common)).getItem());
			Player.getInventory().setLeggings(new CustomItemStack(new LeatherPants("StarterPants", Material.LEATHER_LEGGINGS, Tier.Common)).getItem());
			Player.getInventory().setBoots(new CustomItemStack(new LeatherBoots("StarterBoots", Material.LEATHER_BOOTS, Tier.Common)).getItem());
			Player.getInventory().addItem(new ItemStack[] { TotalWarItems.ItemHandler.getCustomItemStack("Blade", ItemStackHandler.Tier.Common, Material.STONE_SWORD).getItem() });
			Player.getInventory().addItem(new ItemStack[] { TotalWarItems.ItemHandler.getCustomItemStack("Bow", ItemStackHandler.Tier.Common, Material.BOW).getItem() });
			Player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.ARROW, 15) });
			Player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.APPLE, 1) });
			Player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.BREAD, 1) });
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
		// TotalWar.InventoryUpdater.UpdatePlayerEventBook(Player);
	}
}