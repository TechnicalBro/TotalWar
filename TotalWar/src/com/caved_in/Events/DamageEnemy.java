package com.caved_in.Events;

import java.util.Random;

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
import com.caved_in.Handlers.EntityHandlers.HorseModifier;

public class DamageEnemy implements Listener
{
	/*
	 * private Attack[] Spiders = { new Knockback() }; private Attack[] Pigs = {
	 * new Knockback() }; private Attack[] Slimes = { new Knockback(), new
	 * Burn() }; private Attack[] Flame_Slime = { new Burn(), new Knockback() };
	 * private Attack[] Creeper = { new Knockback() }; private Attack[] Bats = {
	 * new Knockback() }; private Attack[] Giants = { new Slow(), new
	 * Knockback() }; private Attack[] Zombies = { new Slow(), new Knockback()
	 * }; private Attack[] Enderman = { new Slow(), new Knockback() }; private
	 * Attack[] Squid = { new Slow() }; private Attack[] Skeleton = { new
	 * Knockback() };
	 */
	public DamageEnemy(TotalWar Plugin)
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

	@EventHandler(priority = EventPriority.HIGH)
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
		/*
		 * if (((Event.getEntity() instanceof Player)) && (((Player)
		 * Event.getEntity()).getGameMode() == GameMode.CREATIVE)) {
		 * Event.setCancelled(true); return; }
		 */

		if ((Event.getEntity() instanceof LivingEntity))
		{
			if (!(Event.getEntity() instanceof Player))
			{

				if (Event.getDamager() instanceof Player && HorseModifier.isHorse((LivingEntity) Event.getEntity()))
				{
					HorseModifier Horsey = new HorseModifier((LivingEntity) Event.getEntity());
					if (Horsey.isTamed())
					{
						Event.setCancelled(true);
						return;
					}
				}
				HandleEntityName((LivingEntity) Event.getEntity(), Event.getDamage());
				/*
				 * else if (((Event.getDamager() instanceof Player)) &&
				 * (PercentCheck(4 + new Random().nextInt(15)))) {
				 * HandleAttack((LivingEntity) Event.getEntity(), (Player)
				 * Event.getDamager()); return; }
				 */
			}
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
		if (TotalWar.PlayerHandler.isSameFaction(Attacker, Attacked))
		{
			return true;
		}
		return false;
		/*
		 * if ((TotalWar.PlayerHandler.getPlayerFactionPerm(Attacker) != null)
		 * && (TotalWar.PlayerHandler.getPlayerFactionPerm(Attacked) != null)) {
		 * if
		 * (TotalWar.PlayerHandler.getPlayerFactionPerm(Attacker).equalsIgnoreCase
		 * (TotalWar.PlayerHandler.getPlayerFactionPerm(Attacked))) { return
		 * true; } return false; } return false;
		 */
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

	/*
	 * public void HandleAttack(LivingEntity Entity, Player Player) { switch
	 * (Entity.getType()) { case BAT: this.Bats[new
	 * Random().nextInt(this.Bats.length)].Do(Entity); break; case MAGMA_CUBE:
	 * this.Flame_Slime[new
	 * Random().nextInt(this.Flame_Slime.length)].Do(Entity); break; case
	 * SPIDER: this.Spiders[new
	 * Random().nextInt(this.Spiders.length)].Do(Entity); break; case PIG:
	 * this.Pigs[new Random().nextInt(this.Pigs.length)].Do(Entity); break; case
	 * COW: this.Pigs[new Random().nextInt(this.Pigs.length)].Do(Entity,
	 * Player); break; case CREEPER: this.Creeper[new
	 * Random().nextInt(this.Creeper.length)].Do(Entity, Player); break; case
	 * ENDERMAN: this.Enderman[new
	 * Random().nextInt(this.Enderman.length)].Do(Entity, Player); break; case
	 * SHEEP: this.Pigs[new Random().nextInt(this.Pigs.length)].Do(Entity,
	 * Player); break; case OCELOT: this.Pigs[new
	 * Random().nextInt(this.Pigs.length)].Do(Entity, Player); break; case
	 * GIANT: this.Giants[new Random().nextInt(this.Giants.length)].Do(Entity,
	 * Player); break; case SILVERFISH: this.Slimes[new
	 * Random().nextInt(this.Slimes.length)].Do(Entity, Player); break; case
	 * CHICKEN: this.Pigs[new Random().nextInt(this.Pigs.length)].Do(Entity,
	 * Player); break; case VILLAGER: this.Pigs[new
	 * Random().nextInt(this.Pigs.length)].Do(Entity, Player); break; case
	 * ZOMBIE: this.Zombies[new
	 * Random().nextInt(this.Zombies.length)].Do(Entity, Player); break; case
	 * MUSHROOM_COW: this.Pigs[new
	 * Random().nextInt(this.Pigs.length)].Do(Entity, Player); break; case
	 * SKELETON: this.Skeleton[new
	 * Random().nextInt(this.Skeleton.length)].Do(Entity, Player); break; case
	 * SLIME: this.Slimes[new Random().nextInt(this.Slimes.length)].Do(Entity,
	 * Player); break; case CAVE_SPIDER: this.Spiders[new
	 * Random().nextInt(this.Spiders.length)].Do(Entity, Player); break; case
	 * SQUID: this.Squid[new Random().nextInt(this.Squid.length)].Do(Entity,
	 * Player); break; case PIG_ZOMBIE: this.Zombies[new
	 * Random().nextInt(this.Zombies.length)].Do(Entity, Player); break;
	 * default: break; } }
	 */

	private boolean PercentCheck(int Chances)
	{
		if (new Random().nextInt(100) <= Chances)
		{
			return true;
		}
		return false;
	}
}