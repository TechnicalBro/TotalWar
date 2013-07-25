package com.caved_in.Events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.cybermaxke.materialapi.inventory.CustomItemStack;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.caved_in.TotalWar;
import com.caved_in.Cooldown.Cooldown;
import com.caved_in.Handlers.DynamicEvents.AreaEvent;
import com.caved_in.Handlers.EntityHandlers.MobdropHandler;
import com.caved_in.Items.Gem;
import com.caved_in.TotalWarItems.TotalWarItems;
import com.caved_in.TotalWarItems.Handlers.ItemStackHandler.Tier;

public class MobDeath implements Listener
{
	public double Skeleton_Chance_Get = 0.04;
	public double Giant_Chance_Get = 0.5;
	public double Creeper_Chance_Get = 0.024;
	public double Cave_Spider_Chance_Get = 0.013;
	public double Spider_Chance_Get = 0.05;
	public double Pig_Zombie_Chance_Get = 0.07;
	public double Ghast_Chance_Get = 0.15;
	public double Enderman_Chance_Get = 0.06;
	public double Witch_Chance_Get = 0.25;
	public double Wither_Chance_Get = 0.7;
	public double Slime_Chance_Get = 0.07;
	public double Blaze_Chance_Get = 0.08;
	public double Ender_Dragon_Chance_Get = 50.0;
	public double Zombie_Chance_Get = 0.06;
	public double Donator_Drop_Increase = 10.0;
	private ArrayList<String> PlayersWhoKilledABoss = new ArrayList<String>();
	private MobdropHandler DropsHandler = new MobdropHandler();
	private Cooldown PlayersOnCooldown = new Cooldown(90);

	public MobDeath(TotalWar Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
		LoadValues();
	}

	public void LoadValues()
	{
		this.Skeleton_Chance_Get = TotalWar.Mob_Drops.getSkeletonChance();
		this.Giant_Chance_Get = TotalWar.Mob_Drops.getGiantChance();
		this.Creeper_Chance_Get = TotalWar.Mob_Drops.getCreeperChance();
		this.Cave_Spider_Chance_Get = TotalWar.Mob_Drops.getCaveSpiderChance();
		this.Spider_Chance_Get = TotalWar.Mob_Drops.getSpiderChance();
		this.Pig_Zombie_Chance_Get = TotalWar.Mob_Drops.getZombiePigmanChance();
		this.Ghast_Chance_Get = TotalWar.Mob_Drops.getGhastChance();
		this.Enderman_Chance_Get = TotalWar.Mob_Drops.getEndermanChance();
		this.Wither_Chance_Get = TotalWar.Mob_Drops.getWitherChance();
		this.Witch_Chance_Get = TotalWar.Mob_Drops.getWitchChance();
		this.Slime_Chance_Get = TotalWar.Mob_Drops.getSlimeChance();
		this.Blaze_Chance_Get = TotalWar.Mob_Drops.getBlazeChance();
		this.Zombie_Chance_Get = TotalWar.Mob_Drops.getZombieChance();
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void EntityDeath(EntityDeathEvent Event)
	{
		Event.setDroppedExp(0);
		if ((Event.getEntity().getKiller() instanceof Player))
		{
			Damageable Entity;
			if (Event.getEntity() instanceof Damageable)
			{
				Entity = Event.getEntity();
				if (TotalWar.BossHandler.isBoss(Event.getEntity()))
				{
					PlayersWhoKilledABoss.add(Event.getEntity().getKiller().getName());
				}
				switch (Event.getEntityType())
				{
				case BLAZE:
					HandleBlaze(Event.getEntity().getKiller(), (int) Math.round(Entity.getMaxHealth()));
					break;
				case CAVE_SPIDER:
					HandleCaveSpider(Event.getEntity().getKiller(), (int) Math.round(Entity.getMaxHealth()));
					break;
				case CREEPER:
					HandleCreeper(Event.getEntity().getKiller(), (int) Math.round(Entity.getMaxHealth()));
					break;
				case GHAST:
					HandleGhast(Event.getEntity().getKiller(), (int) Math.round(Entity.getMaxHealth()));
					break;
				case GIANT:
					HandleGiant(Event.getEntity().getKiller(), (int) Math.round(Entity.getMaxHealth()));
					break;
				case MAGMA_CUBE:
					HandleMagmaCube(Event.getEntity().getKiller(), (int) Math.round(Entity.getMaxHealth()));
					break;
				case PIG_ZOMBIE:
					HandlePigZombie(Event.getEntity().getKiller(), (int) Math.round(Entity.getMaxHealth()));
					break;
				case SKELETON:
					HandleSkeleton(Event.getEntity().getKiller(), (int) Math.round(Entity.getMaxHealth()));
					break;
				case SPIDER:
					HandleSpider(Event.getEntity().getKiller(), (int) Math.round(Entity.getMaxHealth()));
					break;
				case WITCH:
					HandleWitch(Event.getEntity().getKiller(), (int) Math.round(Entity.getMaxHealth()));
					break;
				case ZOMBIE:
					HandleZombie(Event.getEntity().getKiller(), (int) Math.round(Entity.getMaxHealth()));
					break;
				case SLIME:
					HandleSlime(Event.getEntity().getKiller(), (int) Math.round(Entity.getMaxHealth()));
					break;
				default:
					break;
				}
			}

			if (TotalWar.BossHandler.isBoss(Event.getEntity()))
			{
				CheckBossEvents(Event.getEntity(), Event.getEntity().getKiller());
				TotalWar.BossHandler.removeBoss(Event.getEntity().getEntityId());
			}
			else
			{
				CheckMobEvents(Event.getEntity(), Event.getEntity().getKiller());
			}
			/*
			 * if (new Random().nextInt(100) <= new Random().nextInt(15)) {
			 * TotalWar.economy
			 * .depositPlayer(Event.getEntity().getKiller().getName(), 2 + new
			 * Random().nextInt(10)); }
			 */
		}
	}

	private void HandleBossEvent(LivingEntity Entity, AreaEvent e, Player Player)
	{
		if (TotalWar.BossHandler.isBoss(Entity))
		{
			if (TotalWar.BossHandler.getBossByID(Entity.getEntityId()).Type() == e.getBossType())
			{
				TotalWar.Console("Player [" + Player.getName() + "] Killed Boss [" + e.getBossName() + "]");
				if ((!TotalWar.EventDynamics.hasBeenRewarded(e, Player.getName())) && (!TotalWar.EventDynamics.isPlayerCompleted(e, Player.getName())))
				{
					TotalWar.Console("Player [" + Player.getName() + "] Hasn't been rewarded for killing [" + e.getBossName() + "]");
					TotalWar.EventDynamics.addPlayerProgress(e, Player.getName());
				}
			}
		}
	}

	private void HandleKillMobEvent(LivingEntity Entity, AreaEvent e, Player Player)
	{
		if (Entity.getType().equals(e.getEntityType()))
		{
			TotalWar.Console("Player [" + Player.getName() + "] Killed Event Mob [" + e.getEntityType().toString() + "]");
			if ((!TotalWar.EventDynamics.hasBeenRewarded(e, Player.getName())) && (!TotalWar.EventDynamics.isPlayerCompleted(e, Player.getName())))
			{
				TotalWar.Console("Player [" + Player.getName() + "] Hasn't been rewarded for killing Event Mob [" + e.getEntityType().toString() + "]");
				TotalWar.EventDynamics.addPlayerProgress(e, Player.getName());
			}
		}
	}

	private void CheckBossEvents(LivingEntity Entity, Player Player)
	{
		ArrayList<AreaEvent> BossEvents = TotalWar.EventDynamics.getActiveEvents(AreaEvent.EventType.BOSS_HUNT);
		if (BossEvents.size() > 0)
		{
			for (AreaEvent e : BossEvents)
			{
				HandleBossEvent(Entity, e, Player);
			}
		}
	}

	private void CheckMobEvents(LivingEntity Entity, Player Player)
	{
		ArrayList<AreaEvent> MobEvents = TotalWar.EventDynamics.getActiveEvents(AreaEvent.EventType.KILL_MOB);
		if (MobEvents.size() > 0)
		{
			for (AreaEvent e : MobEvents)
			{
				HandleKillMobEvent(Entity, e, Player);
			}
		}
	}

	private void Announce(Player Player, String Message)
	{
		Player.sendMessage(ChatColor.LIGHT_PURPLE + "You " + Message);
	}

	public CustomItemStack getRandomItem(List<CustomItemStack> Items)
	{
		return Items.get(new Random().nextInt(Items.size()));
	}

	private void HandleZombie(Player Player, double Health)
	{
		List<CustomItemStack> Drops = DropsHandler.Drops(EntityType.ZOMBIE);
		Drops.add(new CustomItemStack(new Gem("", Material.EMERALD)));

		if (hasEmptySlot(Player))
		{
			if (PercentCheck(this.Zombie_Chance_Get * (Health / 10) + GetDropIncrease(Player)))
			{
				CustomItemStack Drop_Stack = getRandomItem(Drops);
				Player.getInventory().addItem(new ItemStack[] { Drop_Stack.getItem() });
				Player.updateInventory();
				Announce(Player, "just received a \"" + Drop_Stack.getName() + ChatColor.LIGHT_PURPLE + "\" from a Zombie!");
			}
		}
	}

	private void HandleSkeleton(Player Player, double Health)
	{
		List<CustomItemStack> Drops = DropsHandler.Drops(EntityType.SKELETON);
		Drops.add(new CustomItemStack(new Gem("", Material.EMERALD)));

		if (hasEmptySlot(Player))
		{
			if (PercentCheck(this.Skeleton_Chance_Get * (Health / 10) + GetDropIncrease(Player)))
			{
				CustomItemStack Drop_Stack = getRandomItem(Drops);
				Player.getInventory().addItem(new ItemStack[] { Drop_Stack.getItem() });
				Player.updateInventory();
				Announce(Player, "just received a \"" + Drop_Stack.getName() + ChatColor.LIGHT_PURPLE + "\" from a Skeleton!");
			}
		}
	}

	private void HandleCreeper(Player Player, double Health)
	{
		List<CustomItemStack> Drops = DropsHandler.Drops(EntityType.CREEPER);
		Drops.add(new CustomItemStack(new Gem("", Material.EMERALD)));

		if (hasEmptySlot(Player))
		{
			if (PercentCheck(this.Creeper_Chance_Get * (Health / 10) + GetDropIncrease(Player)))
			{
				CustomItemStack Drop_Stack = getRandomItem(Drops);
				Player.getInventory().addItem(new ItemStack[] { Drop_Stack.getItem() });
				Player.updateInventory();
				Announce(Player, "just received a \"" + Drop_Stack.getName() + ChatColor.LIGHT_PURPLE + "\" from a Creeper!");
			}
		}
	}

	private void HandleWitch(Player Player, double Health)
	{
		List<CustomItemStack> Drops = DropsHandler.Drops(EntityType.WITCH);
		Drops.add(new CustomItemStack(new Gem("", Material.EMERALD)));
		if (hasEmptySlot(Player))
		{
			if (PercentCheck(this.Witch_Chance_Get * (Health / 10) + GetDropIncrease(Player)))
			{
				CustomItemStack Drop_Stack = getRandomItem(Drops);
				Player.getInventory().addItem(new ItemStack[] { Drop_Stack.getItem() });
				Player.updateInventory();
				Announce(Player, "just received a \"" + Drop_Stack.getName() + ChatColor.LIGHT_PURPLE + "\" from a Witch!");
			}
		}
	}

	private void HandleSpider(Player Player, double Health)
	{
		List<CustomItemStack> Drops = DropsHandler.Drops(EntityType.SPIDER);
		Drops.add(new CustomItemStack(new Gem("", Material.EMERALD)));

		if (hasEmptySlot(Player))
		{
			if (PercentCheck(this.Spider_Chance_Get * (Health / 10) + GetDropIncrease(Player)))
			{
				CustomItemStack Drop_Stack = getRandomItem(Drops);
				Player.getInventory().addItem(new ItemStack[] { Drop_Stack.getItem() });
				Player.updateInventory();
				Announce(Player, "just received a \"" + Drop_Stack.getName() + ChatColor.LIGHT_PURPLE + "\" from a Spider!");
			}
		}
	}

	private void HandleCaveSpider(Player Player, double Health)
	{
		List<CustomItemStack> Drops = DropsHandler.Drops(EntityType.SPIDER);
		Drops.add(new CustomItemStack(new Gem("", Material.EMERALD)));
		if (hasEmptySlot(Player))
		{
			if (PercentCheck(this.Cave_Spider_Chance_Get * (Health / 10) + GetDropIncrease(Player)))
			{
				CustomItemStack Drop_Stack = getRandomItem(Drops);
				Player.getInventory().addItem(new ItemStack[] { Drop_Stack.getItem() });
				Player.updateInventory();
				Announce(Player, "just received a \"" + Drop_Stack.getName() + ChatColor.LIGHT_PURPLE + "\" from a Cave Spider!");
			}
		}
	}

	private void HandlePigZombie(Player Player, double Health)
	{
		List<CustomItemStack> Drops = DropsHandler.Drops(EntityType.PIG_ZOMBIE);
		Drops.add(new CustomItemStack(new Gem("", Material.EMERALD)));
		if (hasEmptySlot(Player))
		{
			if (PercentCheck(this.Pig_Zombie_Chance_Get * (Health / 10) + GetDropIncrease(Player)))
			{
				CustomItemStack Drop_Stack = getRandomItem(Drops);
				Player.getInventory().addItem(new ItemStack[] { Drop_Stack.getItem() });
				Player.updateInventory();
				Announce(Player, "just received a \"" + Drop_Stack.getName() + ChatColor.LIGHT_PURPLE + "\" from a Pig Zombie!");
			}
		}
	}

	private void HandleGiant(Player Player, double Health)
	{
		CustomItemStack[] Giant_Drops = { new CustomItemStack(new Gem("", Material.EMERALD)), TotalWarItems.ItemHandler.getCustomItemStack("Bow", Tier.Rare, Material.BOW), TotalWarItems.ItemHandler.getCustomItemStack("Blade", Tier.Rare, Material.STONE_SWORD), TotalWarItems.ItemHandler.getCustomItemStack("Blade", Tier.Epic, Material.GOLD_SWORD), TotalWarItems.ItemHandler.getCustomItemStack("Blade", Tier.Epic, Material.IRON_SWORD), TotalWarItems.ItemHandler.getCustomItemStack("Katana", Tier.Rare, Material.GOLD_SWORD), TotalWarItems.ItemHandler.getCustomItemStack("Bow", Tier.Epic, Material.BOW), TotalWarItems.ItemHandler.getCustomItemStack("Blade", Tier.Rare, Material.IRON_SWORD), TotalWarItems.ItemHandler.getArmor("IronHelmet"), TotalWarItems.ItemHandler.getArmor("IronBoots"), TotalWarItems.ItemHandler.getArmor("IronChest"), TotalWarItems.ItemHandler.getArmor("IronPants") };

		if (hasEmptySlot(Player))
		{
			if (PercentCheck(this.Giant_Chance_Get * (Health / 10) + GetDropIncrease(Player)))
			{
				CustomItemStack Drop_Stack = Giant_Drops[new Random().nextInt(Giant_Drops.length)];
				Player.getInventory().addItem(new ItemStack[] { Drop_Stack.getItem() });
				Player.updateInventory();
				Announce(Player, "just received a \"" + Drop_Stack.getName() + ChatColor.LIGHT_PURPLE + "\" from a Giant!");
			}
		}
	}

	private void HandleMagmaCube(Player Player, double Health)
	{
		List<CustomItemStack> Drops = DropsHandler.Drops(EntityType.MAGMA_CUBE);
		Drops.add(new CustomItemStack(new Gem("", Material.EMERALD)));
		if (hasEmptySlot(Player))
		{
			if (PercentCheck(this.Slime_Chance_Get * (Health / 10) + GetDropIncrease(Player)))
			{
				CustomItemStack Drop_Stack = getRandomItem(Drops);
				Player.getInventory().addItem(new ItemStack[] { Drop_Stack.getItem() });
				Player.updateInventory();
				Announce(Player, "just received a \"" + Drop_Stack.getName() + ChatColor.LIGHT_PURPLE + "\" from a Magma Cube!");
			}
		}
	}

	private void HandleSlime(Player Player, double Health)
	{
		List<CustomItemStack> Drops = DropsHandler.Drops(EntityType.MAGMA_CUBE);
		Drops.add(new CustomItemStack(new Gem("", Material.EMERALD)));

		if (hasEmptySlot(Player))
		{
			if (PercentCheck(this.Slime_Chance_Get * (Health / 10) + GetDropIncrease(Player)))
			{
				CustomItemStack Drop_Stack = getRandomItem(Drops);
				Player.getInventory().addItem(new ItemStack[] { Drop_Stack.getItem() });
				Player.updateInventory();
				Announce(Player, "just received a \"" + Drop_Stack.getName() + ChatColor.LIGHT_PURPLE + "\" from a Slime!");
			}
		}
	}

	private void HandleGhast(Player Player, double Health)
	{
		List<CustomItemStack> Drops = DropsHandler.Drops(EntityType.MAGMA_CUBE);
		Drops.add(new CustomItemStack(new Gem("", Material.EMERALD)));
		if (hasEmptySlot(Player))
		{
			if (PercentCheck(this.Ghast_Chance_Get * (Health / 10) + GetDropIncrease(Player)))
			{
				CustomItemStack Drop_Stack = getRandomItem(Drops);
				Player.getInventory().addItem(new ItemStack[] { Drop_Stack.getItem() });
				Player.updateInventory();
				Announce(Player, "just received a \"" + Drop_Stack.getName() + ChatColor.LIGHT_PURPLE + "\" from a Ghast!");
			}
		}
	}

	private void HandleBlaze(Player Player, double Health)
	{
		List<CustomItemStack> Drops = DropsHandler.Drops(EntityType.MAGMA_CUBE);
		Drops.add(new CustomItemStack(new Gem("", Material.EMERALD)));

		if (hasEmptySlot(Player))
		{
			if (PercentCheck(this.Blaze_Chance_Get * (Health / 10) + GetDropIncrease(Player)))
			{
				CustomItemStack Drop_Stack = getRandomItem(Drops);
				Player.getInventory().addItem(new ItemStack[] { Drop_Stack.getItem() });
				Player.updateInventory();
				Announce(Player, "just received a \"" + Drop_Stack.getName() + ChatColor.LIGHT_PURPLE + "\" from a Blaze!");
			}
		}
	}

	private boolean hasEmptySlot(Player Player)
	{
		if (Player.getInventory().firstEmpty() == -1)
		{
			return false;
		}
		return true;
	}

	private double GetDropIncrease(Player Player)
	{
		double DropIncrease = 0;

		if (this.PlayersWhoKilledABoss.contains(Player.getName()))
		{
			DropIncrease += TotalWar.Plugin_Config.getBossDropIncrease();
			this.PlayersWhoKilledABoss.remove(Player.getName());
		}

		if (Player.hasPermission("TotalWar.Donator"))
		{
			DropIncrease += this.Donator_Drop_Increase;
		}

		return DropIncrease;
	}

	private boolean PercentCheck(double Chances)
	{
		if (new Random().nextInt(100) <= Chances)
		{
			return true;
		}
		return false;
	}
}