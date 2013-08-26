package com.caved_in.Events;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import com.caved_in.TotalWar;
import com.caved_in.Cooldown.Cooldown;
import com.caved_in.Handlers.EffectHandlers.ParticleEffects;
import com.caved_in.Handlers.ItemHandlers.ItemHandler;
import com.caved_in.ItemMenus.PlayerMenu.PlayerMenu;

public class PlayerInteractedEntity implements Listener
{
	private Cooldown Hearts = new Cooldown(1);

	public PlayerInteractedEntity(TotalWar Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void PlayerInteractEntity(PlayerInteractEntityEvent Event)
	{
		Player Event_Player = Event.getPlayer();
		Entity Event_Entity = Event.getRightClicked();
		
		if (!Event_Entity.hasMetadata("NPC") && Event_Entity instanceof Player)
		{
			if (Event_Player.getItemInHand().getType() == Material.RED_ROSE && !this.Hearts.IsOnCooldown(Event_Player.getName()))
			{
				try
				{
					ParticleEffects.HEART.sendToAll(((Player)Event_Entity).getLocation(), new Random().nextFloat(),new Random().nextInt(5) + 4);
					Event_Player.setItemInHand(ItemHandler.RemoveFromStack(Event_Player.getItemInHand(), 1));
					this.Hearts.SetOnCooldown(Event_Player.getName());
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				return;
			}
			if (Event_Player.isSneaking() && (Event_Player.getItemInHand() == null || Event_Player.getItemInHand().getType() == Material.AIR))
			{
				new PlayerMenu(Event_Player,((Player) Event_Entity).getName());
			}
		}
	}
}