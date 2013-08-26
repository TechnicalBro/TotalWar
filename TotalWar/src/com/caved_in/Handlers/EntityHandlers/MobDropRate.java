package com.caved_in.Handlers.EntityHandlers;

import org.bukkit.entity.EntityType;

public enum MobDropRate
{
	Skeleton(0.012),
	Spider(0.0012),
	CaveSpider(0.005),
	Creeper(0.024),
	Zombie(0.03),
	Giant(0.09),
	ZombiePigman(0.03),
	Ghast(0.15),
	Enderman(0.06),
	Witch(0.25),
	Wither(0.7),
	Slime(0.02),
	Blaze(0.04);
	
	private final double DropRate;
	MobDropRate(double DropRate)
	{
		this.DropRate = DropRate;
	}
	
	public double getDropRate()
	{
		return this.DropRate;
	}
	
	public static double getDropRate(EntityType Entity)
	{
		switch (Entity)
		{
			case SKELETON:
				return Skeleton.getDropRate();
			case SPIDER:
				return Spider.getDropRate();
			case CAVE_SPIDER:
				return CaveSpider.getDropRate();
			case CREEPER:
				return Creeper.getDropRate();
			case ZOMBIE:
				return Zombie.getDropRate();
			case GIANT:
				return Giant.getDropRate();
			case PIG_ZOMBIE:
				return ZombiePigman.getDropRate();
			case GHAST:
				return Ghast.getDropRate();
			case ENDERMAN:
				return Enderman.getDropRate();
			case WITCH:
				return Witch.getDropRate();
			case WITHER:
				return Wither.getDropRate();
			case SLIME:
			case MAGMA_CUBE:
				return Slime.getDropRate();
			case BLAZE:
				return Blaze.getDropRate();
			default:
				return 0.0;
			
		}
	}
}
