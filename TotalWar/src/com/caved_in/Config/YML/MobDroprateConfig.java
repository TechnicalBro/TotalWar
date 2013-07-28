package com.caved_in.Config.YML;

public class MobDroprateConfig
{
	private YML_Wrapper Wrapper;

	public MobDroprateConfig(String YMLPath)
	{
		this.Wrapper = new YML_Wrapper(YMLPath);
	}

	public double getSkeletonChance()
	{
		return this.Wrapper.getDouble("Skeleton.Droprate");
	}

	public double getSpiderChance()
	{
		return this.Wrapper.getDouble("Spider.Droprate");
	}

	public double getCaveSpiderChance()
	{
		return this.Wrapper.getDouble("CaveSpider.Droprate");
	}

	public double getCreeperChance()
	{
		return this.Wrapper.getDouble("Creeper.Droprate");
	}

	public double getZombieChance()
	{
		return this.Wrapper.getDouble("Zombie.Droprate");
	}

	public double getGiantChance()
	{
		return this.Wrapper.getDouble("Giant.Droprate");
	}

	public double getZombiePigmanChance()
	{
		return this.Wrapper.getDouble("ZombiePigman.Droprate");
	}

	public double getGhastChance()
	{
		return this.Wrapper.getDouble("Ghast.Droprate");
	}

	public double getEndermanChance()
	{
		return this.Wrapper.getDouble("Enderman.Droprate");
	}

	public double getWitchChance()
	{
		return this.Wrapper.getDouble("Witch.Droprate");
	}

	public double getWitherChance()
	{
		return this.Wrapper.getDouble("Wither.Droprate");
	}

	public double getSlimeChance()
	{
		return this.Wrapper.getDouble("Slime.Droprate");
	}

	public double getBlazeChance()
	{
		return this.Wrapper.getDouble("Blaze.Droprate");
	}
}