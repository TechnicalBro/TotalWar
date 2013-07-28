package com.caved_in.Events;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import com.caved_in.TotalWar;
import com.caved_in.Cooldown.Cooldown;
import com.caved_in.Handlers.EntityHandlers.EntityHandler;
import com.caved_in.Handlers.EntityHandlers.HorseModifier;
import com.caved_in.Handlers.EntityHandlers.BossHandler.BossType;
import com.caved_in.Handlers.EntityHandlers.Bosses.Boss;
import com.caved_in.Handlers.EntityHandlers.Bosses.BossWrapper;

public class MobSpawnEvent implements Listener
{
	private EntityHandler MobChanger = new EntityHandler();
	private Cooldown GiantSpawn = new Cooldown(600);

	public MobSpawnEvent(TotalWar Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void MobSpawn(CreatureSpawnEvent Event)
	{
		if (!Event.getEntity().hasMetadata("NPC"))
		{
			switch (Event.getEntityType())
			{
			case OCELOT:
				this.MobChanger.ModifyPeacefulMob(Event.getEntity());
				break;
			case MUSHROOM_COW:
				this.MobChanger.ModifyPeacefulMob(Event.getEntity());
				break;
			case CHICKEN:
				if (PercentCheck(3))
				{
					Boss Shicken = TotalWar.BossHandler.makeBoss(BossType.Shicken, Event.getEntity().getEntityId());
					double ShickenHealth = TotalWar.BossHandler.generateBossHP(Shicken.getMaxHP());
					MobChanger.SetMob(Event.getEntity(), ChatColor.RED + Shicken.getName(), ShickenHealth, ShickenHealth, true);
					TotalWar.BossHandler.addBoss(new BossWrapper(Event.getEntity(), Shicken));
				}
				else
				{
					this.MobChanger.ModifyPeacefulMob(Event.getEntity());
				}
				break;
			case SHEEP:
				this.MobChanger.ModifyPeacefulMob(Event.getEntity());
				break;
			case SNOWMAN:
				this.MobChanger.ModifyPeacefulMob(Event.getEntity());
				break;
			case PIG:
				if (PercentCheck(new Random().nextInt(10)))
				{
					Boss Nate = TotalWar.BossHandler.makeBoss(BossType.DrNate, Event.getEntity().getEntityId());
					double Health = TotalWar.BossHandler.generateBossHP(Nate.getMaxHP());
					Health = (int) Math.round(Health);
					MobChanger.SetMob(Event.getEntity(), ChatColor.RED + Nate.getName(), Health, Health, true);
					TotalWar.BossHandler.addBoss(new BossWrapper(Event.getEntity(), Nate));
				}
				else
				{
					this.MobChanger.ModifyPeacefulMob(Event.getEntity());
				}
				break;
			case SQUID:
				if (PercentCheck(new Random().nextInt(6)))
				{
					switch (new Random().nextInt(2))
					{
					case 0:
						Boss Kraken = TotalWar.BossHandler.makeBoss(BossType.Kraken, Event.getEntity().getEntityId());
						double Health = TotalWar.BossHandler.generateBossHP(Kraken.getMaxHP());
						MobChanger.SetMob(Event.getEntity(), ChatColor.RED + Kraken.getName(), Health, Health, true);
						TotalWar.BossHandler.addBoss(new BossWrapper(Event.getEntity(), Kraken));
						break;
					case 1:
						Boss Dan = TotalWar.BossHandler.makeBoss(BossType.AquaDan, Event.getEntity().getEntityId());
						double HealthD = TotalWar.BossHandler.generateBossHP(Dan.getMaxHP());
						MobChanger.SetMob(Event.getEntity(), ChatColor.RED + Dan.getName(), HealthD, HealthD, true);
						TotalWar.BossHandler.addBoss(new BossWrapper(Event.getEntity(), Dan));
						break;
					default:
						break;
					}
				}
				else
				{
					this.MobChanger.ModifyPeacefulMob(Event.getEntity());
				}
				break;
			case CREEPER:
				if (PercentCheck(new Random().nextInt(15)))
				{
					Boss Kamikaze = TotalWar.BossHandler.makeBoss(BossType.KamikazeCreeper, Event.getEntity().getEntityId());
					double Health = TotalWar.BossHandler.generateBossHP(Kamikaze.getMaxHP());
					Health = (int) Math.round(Health);
					MobChanger.SetMob(Event.getEntity(), ChatColor.RED + Kamikaze.getName(), Health, Health, true);
					TotalWar.BossHandler.addBoss(new BossWrapper(Event.getEntity(), Kamikaze));
				}
				else
				{
					this.MobChanger.ModifyMob(Event.getEntity());
				}
				break;
			case SKELETON:
				if (PercentCheck(new Random().nextInt(9)))
				{
					Boss Kraken = TotalWar.BossHandler.makeBoss(BossType.Skeletor, Event.getEntity().getEntityId());
					double Health = TotalWar.BossHandler.generateBossHP(Kraken.getMaxHP());
					Health = (int) Math.round(Health);
					MobChanger.SetMob(Event.getEntity(), ChatColor.RED + Kraken.getName(), Health, Health, true);
					TotalWar.BossHandler.addBoss(new BossWrapper(Event.getEntity(), Kraken));
				}
				else
				{
					this.MobChanger.ModifyMob(Event.getEntity());
				}
				break;
			case PIG_ZOMBIE:
				if (PercentCheck(new Random().nextInt(8)))
				{
					Boss Kamikaze = TotalWar.BossHandler.makeBoss(BossType.Porker, Event.getEntity().getEntityId());
					double Health = TotalWar.BossHandler.generateBossHP(Kamikaze.getMaxHP());
					Health = (int) Math.round(Health);
					MobChanger.SetMob(Event.getEntity(), ChatColor.RED + Kamikaze.getName(), Health, Health, true);
					TotalWar.BossHandler.addBoss(new BossWrapper(Event.getEntity(), Kamikaze));
				}
				else
				{
					this.MobChanger.ModifyMob(Event.getEntity());
				}
				break;
			case SPIDER:
				if (PercentCheck(new Random().nextInt(9)))
				{
					Boss Kamikaze = TotalWar.BossHandler.makeBoss(BossType.QueenWidow, Event.getEntity().getEntityId());
					double Health = TotalWar.BossHandler.generateBossHP(Kamikaze.getMaxHP());
					Health = (int) Math.round(Health);
					MobChanger.SetMob(Event.getEntity(), ChatColor.RED + Kamikaze.getName(), Health, Health, true);
					TotalWar.BossHandler.addBoss(new BossWrapper(Event.getEntity(), Kamikaze));
				}
				else
				{
					this.MobChanger.ModifyMob(Event.getEntity());
				}
				break;
			case ZOMBIE:
				if (PercentCheck(new Random().nextInt(10)))
				{
					Boss Kamikaze = TotalWar.BossHandler.makeBoss(BossType.ZombieBoss, Event.getEntity().getEntityId());
					double Health = TotalWar.BossHandler.generateBossHP(Kamikaze.getMaxHP());
					Health = (int) Math.round(Health);
					MobChanger.SetMob(Event.getEntity(), ChatColor.RED + Kamikaze.getName(), Health, Health, true);
					TotalWar.BossHandler.addBoss(new BossWrapper(Event.getEntity(), Kamikaze));
				}
				else
				{
					if (PercentCheck(new Random().nextInt(2)))
					{
						if (!this.GiantSpawn.IsOnCooldown("G"))
						{
							Location L = Event.getEntity().getLocation();
							Biome SpawnedB = L.getWorld().getBiome(L.getBlockX(), L.getBlockZ());
							if (SpawnedB == Biome.PLAINS || SpawnedB == Biome.FOREST)
							{
								LivingEntity Giant = (LivingEntity) L.getWorld().spawnEntity(L, EntityType.GIANT);
								this.MobChanger.ModifyMob(Giant);
								this.GiantSpawn.SetOnCooldown("G");
							}
						}
					}
					this.MobChanger.ModifyMob(Event.getEntity());
				}
				break;
			case ENDERMAN:
				if (PercentCheck(new Random().nextInt(9)))
				{
					Boss Kamikaze = TotalWar.BossHandler.makeBoss(BossType.Cthulu, Event.getEntity().getEntityId());
					double Health = TotalWar.BossHandler.generateBossHP(Kamikaze.getMaxHP());
					Health = (int) Math.round(Health);
					MobChanger.SetMob(Event.getEntity(), ChatColor.RED + Kamikaze.getName(), Health, Health, true);
					TotalWar.BossHandler.addBoss(new BossWrapper(Event.getEntity(), Kamikaze));
				}
				else
				{
					this.MobChanger.ModifyMob(Event.getEntity());
				}
				break;
			case HORSE:
				this.MobChanger.SetMob(Event.getEntity(), "Horse", ((Damageable) Event.getEntity()).getHealth(), ((Damageable) Event.getEntity()).getMaxHealth(), true);
				break;
			case UNKNOWN:
				break;
			default:
				this.MobChanger.ModifyMob(Event.getEntity());
				break;
			}
		}
	}

	private boolean PercentCheck(int Chances)
	{
		if (new Random().nextInt(100) <= Chances)
		{
			return true;
		}
		return false;
	}
}