package com.caved_in.Handlers.EntityHandlers;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import com.caved_in.TotalWar;
import com.caved_in.Cooldown.Cooldown;

public class EntityHandler
{
	private int Extreme_Tier_Min = 90;
	private int Extreme_Tier_Max = 125;

	private int Hard_Tier_Min = 47;
	private int Hard_Tier_Max = 80;

	private int Normal_Tier_Min = 20;
	private int Normal_Tier_Max = 45;

	private String Kraken_Cooldown = "KRAKEN_EVENT";
	public static Cooldown Event_Cooldown = new Cooldown(900);

	public void ModifyMob(Damageable Mob)
	{
		if (Mob instanceof LivingEntity)
		{
			if (PercentCheck(1))
			{
				MakeExtremeMob(Mob);
			}
			else
			{
				LivingEntity Entity = (LivingEntity) Mob;
				Enemy_Tier Tier = Random_Tier();
				double Max_HP = Generate_Max_Health(Tier);
				Entity.setCustomName(Mob.getType().getName() + "  " + ChatColor.WHITE + (Mob.getMaxHealth()) + ChatColor.RED + "❤");
				Handle(Entity, true, true, PercentCheck(3), Max_HP, Max_HP);
			}
		}
	}

	public void Handle(LivingEntity Entity, boolean CustomNameVisible, boolean RemoveWhenFarAway, boolean Equipped, double MaximumHealth, double Health)
	{
		double MaxHealth = (int) Math.round(MaximumHealth);
		double cHealth = (int) Math.round(Health);
		Entity.setCustomNameVisible(CustomNameVisible);
		Entity.setRemoveWhenFarAway(RemoveWhenFarAway);
		Entity.setMaxHealth(MaxHealth);
		Entity.setHealth(cHealth);
		if (Equipped == true)
		{
			EquipMob(Entity);
		}
	}
	
	public void Handle(LivingEntity Entity, boolean CustomNameVisible, boolean RemoveWhenFarAway, boolean Equipped, double MaximumHealth, double Health, boolean Name)
	{
		if (Name == true)
		{
			Entity.setCustomName(Entity.getType().getName() + "  " + ChatColor.WHITE + (int)(((Damageable)Entity).getMaxHealth()) + ChatColor.RED + "❤");
		}
		double MaxHealth = (int) Math.round(MaximumHealth);
		double cHealth = (int) Math.round(Health);
		Entity.setCustomNameVisible(CustomNameVisible);
		Entity.setRemoveWhenFarAway(RemoveWhenFarAway);
		Entity.setMaxHealth(MaxHealth);
		Entity.setHealth(cHealth);
		if (Equipped == true)
		{
			EquipMob(Entity);
		}
	}

	public void MakeExtremeMob(Damageable Mob)
	{
		if (Mob instanceof LivingEntity)
		{
			LivingEntity Entity = (LivingEntity) Mob;
			Enemy_Tier Tier = Enemy_Tier.Extreme;
			double Max_HP = Math.round(Generate_Max_Health(Tier) * 4);
			Mob.setMaxHealth(Max_HP);
			Mob.setHealth(Max_HP);
			Entity.setCustomName(ChatColor.YELLOW + Mob.getType().getName() + "  " + ChatColor.WHITE + ((int) Math.round(Mob.getMaxHealth())) + ChatColor.RED + "❤");

			Entity.setCustomNameVisible(true);
			Entity.setRemoveWhenFarAway(false);
			EquipMob(Entity);
		}
	}

	public void ModifyPeacefulMob(Damageable Mob)
	{
		if (Mob instanceof LivingEntity)
		{
			LivingEntity Entity = (LivingEntity) Mob;
			double Max_HP = Math.round((new Random().nextInt(50) / 10 + new Random().nextInt(50) / 10 + 1) * 5);
			Mob.setMaxHealth(Max_HP);
			Mob.setHealth(Max_HP);
			Entity.setCustomName(Mob.getType().getName() + "  " + ChatColor.WHITE + ((int) Math.round(Mob.getMaxHealth())) + ChatColor.RED + "❤");
			Entity.setCustomNameVisible(true);
			Entity.setRemoveWhenFarAway(true);
		}
	}

	public void SetMob(LivingEntity Mob, String Name, double Health, double Maximum_Health, boolean Name_Is_Visible)
	{
		double mHealth = (int) Math.round(Maximum_Health);
		double cHealth = (int) Math.round(Health);
		Mob.setCustomName(Name + "  " + ChatColor.WHITE + (cHealth) + ChatColor.RED + "❤");
		Mob.setMaxHealth(mHealth);
		Mob.setHealth(cHealth);
		Mob.setRemoveWhenFarAway(true);
		Mob.setCustomNameVisible(Name_Is_Visible);
	}

	public boolean MakeKraken(LivingEntity Mob)
	{
		if (!Event_Cooldown.IsOnCooldown(this.Kraken_Cooldown))
		{
			Mob.setCustomName("KRAKEN  1200❤");
			Mob.setMaxHealth(1200D);
			Mob.setHealth(1200D);
			Mob.setRemoveWhenFarAway(false);
			Mob.setCustomNameVisible(true);
			Event_Cooldown.SetOnCooldown(this.Kraken_Cooldown);
			return true;
		}
		ModifyMob(Mob);
		return false;
	}

	private int Generate_Max_Health(Enemy_Tier Tier)
	{
		switch (Tier)
		{
		case Normal:
			return (new Random().nextInt(this.Extreme_Tier_Min / 10) + 1) * TotalWar.Plugin_Config.getMobHealthMultiplier() + (new Random().nextInt(this.Extreme_Tier_Max / 10) + 1) * TotalWar.Plugin_Config.getMobHealthMultiplier();
		case Hard:
			return (new Random().nextInt(this.Hard_Tier_Min / 10) + 1) * TotalWar.Plugin_Config.getMobHealthMultiplier() + (new Random().nextInt(this.Hard_Tier_Max / 10) + 1) * TotalWar.Plugin_Config.getMobHealthMultiplier();
		case Extreme:
			return (new Random().nextInt(this.Normal_Tier_Min / 10) + 1) * TotalWar.Plugin_Config.getMobHealthMultiplier() + (new Random().nextInt(this.Normal_Tier_Max / 10) + 1) * TotalWar.Plugin_Config.getMobHealthMultiplier();
		}
		return 40;
	}

	private Enemy_Tier Random_Tier()
	{
		int TierCheck = new Random().nextInt(100);
		if ((TierCheck >= 0) && (TierCheck <= 80))
		{
			return Enemy_Tier.Normal;
		}
		if ((TierCheck >= 81) && (TierCheck <= 95))
		{
			return Enemy_Tier.Hard;
		}
		if ((TierCheck >= 95) && (TierCheck <= 100))
		{
			return Enemy_Tier.Extreme;
		}

		return Enemy_Tier.Normal;
	}

	public static void EquipMob(LivingEntity Entity)
	{
		ItemStack[] Boots = new ItemStack[] { new ItemStack(Material.LEATHER_BOOTS), new ItemStack(Material.IRON_BOOTS), new ItemStack(Material.GOLD_BOOTS), new ItemStack(Material.DIAMOND_BOOTS) };
		ItemStack[] Helms = new ItemStack[] { new ItemStack(Material.LEATHER_HELMET), new ItemStack(Material.IRON_HELMET), new ItemStack(Material.GOLD_HELMET), new ItemStack(Material.DIAMOND_HELMET) };
		ItemStack[] Chests = new ItemStack[] { new ItemStack(Material.LEATHER_CHESTPLATE), new ItemStack(Material.IRON_CHESTPLATE), new ItemStack(Material.GOLD_CHESTPLATE), new ItemStack(Material.DIAMOND_CHESTPLATE) };
		ItemStack[] Pants = new ItemStack[] { new ItemStack(Material.LEATHER_LEGGINGS), new ItemStack(Material.IRON_LEGGINGS), new ItemStack(Material.GOLD_LEGGINGS), new ItemStack(Material.DIAMOND_LEGGINGS) };
		Entity.getEquipment().setBoots(Boots[new Random().nextInt(Boots.length)]);
		Entity.getEquipment().setHelmet(Helms[new Random().nextInt(Helms.length)]);
		Entity.getEquipment().setChestplate(Chests[new Random().nextInt(Chests.length)]);
		Entity.getEquipment().setLeggings(Pants[new Random().nextInt(Pants.length)]);
		Entity.getEquipment().setChestplateDropChance(0.0F);
		Entity.getEquipment().setLeggingsDropChance(0.0F);
		Entity.getEquipment().setHelmetDropChance(0.0F);
		Entity.getEquipment().setBootsDropChance(0.0F);
	}

	private boolean PercentCheck(int Chances)
	{
		if (new Random().nextInt(100) <= Chances)
		{
			return true;
		}
		return false;
	}

	public static EntityType GetMobByName(String Name)
	{
		if (Name.equalsIgnoreCase("zombie"))
		{
			return EntityType.ZOMBIE;
		}
		if (Name.equalsIgnoreCase("skeleton"))
		{
			return EntityType.SKELETON;
		}
		if (Name.equalsIgnoreCase("spider"))
		{
			return EntityType.SPIDER;
		}
		if (Name.equalsIgnoreCase("cave_spider"))
		{
			return EntityType.CAVE_SPIDER;
		}
		if (Name.equalsIgnoreCase("giant"))
		{
			return EntityType.GIANT;
		}
		if (Name.equalsIgnoreCase("creeper"))
		{
			return EntityType.CREEPER;
		}
		if (Name.equalsIgnoreCase("pig_zombie"))
		{
			return EntityType.PIG_ZOMBIE;
		}
		if (Name.equalsIgnoreCase("blaze"))
		{
			return EntityType.BLAZE;
		}
		if (Name.equalsIgnoreCase("bat"))
		{
			return EntityType.BAT;
		}
		if (Name.equalsIgnoreCase("witch"))
		{
			return EntityType.WITCH;
		}
		if (Name.equalsIgnoreCase("pig"))
		{
			return EntityType.PIG;
		}
		if (Name.equalsIgnoreCase("cow"))
		{
			return EntityType.COW;
		}
		if (Name.equalsIgnoreCase("sheep"))
		{
			return EntityType.SHEEP;
		}
		if ((Name.equalsIgnoreCase("wolf")) || (Name.equalsIgnoreCase("wolves")))
		{
			return EntityType.WOLF;
		}

		return null;
	}

	public static enum Enemy_Tier
	{
		Normal, Hard, Extreme;
	}
}