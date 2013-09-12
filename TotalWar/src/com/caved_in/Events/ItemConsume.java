package com.caved_in.Events;

import com.caved_in.Handlers.FileHandler.DataHandler;
import com.caved_in.Handlers.ItemHandlers.ItemHandler;
import com.caved_in.TotalWar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Damageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

/**
 * User: Brandon
 */
public class ItemConsume implements Listener
{
	public ItemConsume(TotalWar Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this,Plugin);
	}

	@EventHandler
	public void PlayerEats(PlayerItemConsumeEvent Event)
	{
		ItemStack Item = Event.getItem();
		if (Item.getType().isEdible())
		{
			if (ItemHandler.getItemName(Item).toLowerCase().contains("swag beef"))
			{
				Bukkit.getLogger().info("is swag beef");
				if (ItemHandler.itemLoreContains(Item,"made in swagville"))
				{
					Bukkit.getLogger().info("has lore");
					double HealthRestore = 100;
					Damageable pEntity = (Damageable)Event.getPlayer();
					if (HealthRestore + pEntity.getHealth() > pEntity.getMaxHealth())
					{
						pEntity.setHealth((pEntity.getMaxHealth()));
					}
					else
					{
						pEntity.setHealth(HealthRestore + pEntity.getMaxHealth());
					}
				}
			}
			/*
			if (ItemHandler.itemLoreContains(Item,"heals "))
			{
				for(String S : ItemHandler.getItemLore(Item))
				{
					if (S.toLowerCase().contains("heals ") && S.toLowerCase().contains(" hp!"))
					{
						String HealAmount = DataHandler.getStringBetween(S.toLowerCase(),"heals "," hp!");
						try
						{
							int HealthRestore = Integer.parseInt(HealAmount);
							if (HealthRestore + Event.getPlayer().getHealth() > Event.getPlayer().getMaxHealth())
							{
								Event.getPlayer().setHealth((double)(Event.getPlayer().getHealth()));
							}
							else
							{
								Event.getPlayer().setHealth((double)(HealthRestore + Event.getPlayer().getHealth()));
							}
						}
						catch(Exception Ex)
						{
							Ex.printStackTrace();
						}
					}
				}
			}
			*/
		}
	}
}
