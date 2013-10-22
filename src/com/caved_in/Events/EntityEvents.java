package com.caved_in.Events;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.caved_in.TotalWar;
import com.caved_in.Handlers.PlayerHandlers.PlayerHandler;

public class EntityEvents implements Listener
{
	public EntityEvents(TotalWar Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void EntityDamaged(EntityDamageEvent Event)
	{
		if (Event.isCancelled())
		{
			return;
		}

		if (Event.getEntity() instanceof Player)
		{
			TotalWar.SBMan.UpdatePlayerHealth((Player) Event.getEntity());
			return;
		}

		if (Event.getEntity() instanceof LivingEntity)
		{
			HandleEntityName((LivingEntity) Event.getEntity(), Event.getDamage());
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void EnemyGetsHurtByAnotherEnemy(EntityDamageByEntityEvent Event)
	{
		if (Event.isCancelled())
		{
			return;
		}

		if (Event.getEntity().hasMetadata("NPC") || Event.getDamager().hasMetadata("NPC"))
		{
			Event.setCancelled(true);
			return;
		}

		if ((Event.getEntity() instanceof Player) && (Event.getDamager() instanceof Player))
		{
			Player Attacked = (Player) Event.getEntity();
			Player Attacker = (Player) Event.getDamager();
			Event.setCancelled(HandlePVP(Attacker, Attacked));
			return;
		}
		if (Event.getCause() == DamageCause.PROJECTILE && Event.getDamager() instanceof Projectile)
		{
			Projectile Projectile = (Projectile) Event.getDamager();
			if (Projectile.getShooter() instanceof Player && Event.getEntity() instanceof Player)
			{
				Player Shooter = (Player) Projectile.getShooter();
				Event.setCancelled(HandlePVP(Shooter, (Player) Event.getEntity()));
				return;
			}
		}
	}
	
	private boolean HandlePVP(Player Attacker, Player Attacked)
	{
		if (PlayerHandler.isSameFaction(Attacker, Attacked))
		{
			return true;
		}
		return false;
	}

	private void HandleEntityName(Damageable Entity, double Damage)
	{
		if (Entity instanceof LivingEntity)
		{
			LivingEntity Mobs = (LivingEntity) Entity;
			if (Mobs.getCustomName() != null)
			{
				if (Mobs.getCustomName().contains("["))
				{
					if (Entity.getHealth() - Damage >= 0)
					{
						String EntityName = StringUtils.substringBefore(Mobs.getCustomName(), " [");
						Mobs.setCustomName(EntityName + "  " + ChatColor.WHITE + ((int) Math.round((Entity.getHealth() - Damage))) + ChatColor.RED + "❤");
						return;
					}
					return;
				}
				else if (Mobs.getCustomName().contains("❤"))
				{
					if (Entity.getHealth() - Damage >= 0)
					{
						String EntityName = StringUtils.substringBefore(Mobs.getCustomName(), "  ");
						Mobs.setCustomName(EntityName + "  " + ChatColor.WHITE + ((int) Math.round((Entity.getHealth() - Damage))) + ChatColor.RED + "❤");
						return;
					}
					return;
				}
				else if (Mobs.getCustomName().contains("♥"))
				{
					if (Entity.getHealth() - Damage >= 0)
					{
						String EntityName = StringUtils.substringBefore(Mobs.getCustomName(), "  ");
						Mobs.setCustomName(EntityName + "  " + ChatColor.WHITE + ((int) Math.round((Entity.getHealth() - Damage))) + ChatColor.RED + "❤");
						return;
					}
				}
				return;
			
			}
		}
		return;
	}

	
	
}
