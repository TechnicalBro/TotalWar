package com.caved_in.Events;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.caved_in.TotalWar;
import com.caved_in.Cooldown.Cooldown;
import com.caved_in.Handlers.EffectHandlers.ParticleEffects;
import com.caved_in.Handlers.PlayerHandlers.PlayerHandler;

public class PlayerInteractedEntity implements Listener
{
	private ParticleEffects Particles;
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
		boolean isNPC = Event_Entity.hasMetadata("NPC");
		if (!isNPC)
		{
			if ((Event_Player.getItemInHand().getType() == Material.RED_ROSE) && ((Event_Entity instanceof Player)))
			{
				if (!this.Hearts.IsOnCooldown(Event_Player.getName()))
				{
					this.Particles = ParticleEffects.HEART;
					Player RecievedRose = (Player) Event_Entity;
					try
					{
						this.Particles.sendToAll(RecievedRose.getLocation(), new Random().nextFloat(), new Random().nextInt(5) + 4);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					Event_Player.setItemInHand(TotalWar.ItemNamer.RemoveFromStack(Event_Player.getItemInHand(), 1));
					this.Hearts.SetOnCooldown(Event_Player.getName());
					return;
				}
				return;
			}
		}
	}
}