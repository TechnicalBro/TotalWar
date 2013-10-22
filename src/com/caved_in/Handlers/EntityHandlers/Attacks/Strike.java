package com.caved_in.Handlers.EntityHandlers.Attacks;

import java.util.Random;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.caved_in.Handlers.EffectHandlers.ParticleEffects;

public class Strike extends Attack
{
	private ParticleEffects Flames = ParticleEffects.CRIT;

	private int Chance = 0;
	private int Radius = 3;
	private int Damage = 15;

	/**
	 * 
	 * @param Force
	 * @param Chance
	 * @param Rad
	 */
	public Strike(int Force, int Chance, int Rad)
	{
		this.Chance = Chance;
		this.Radius = Rad;
		this.Damage = Force;
	}

	@Override
	public boolean Do(LivingEntity From)
	{
		try
		{
			for (Player P : this.getPlayersInRadius(From, this.Radius))
			{
				this.Flames.sendToAll(P.getLocation(), new Random().nextFloat(), 1 + new Random().nextInt(10));
				P.damage((double) (Math.round(new Random().nextInt(this.Damage) + new Random().nextInt(this.Damage) + 1)));
			}
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int ExecutionChance()
	{
		return this.Chance;
	}
}

/*
 * Location: C:\Users\Brandon\Desktop\TotalWar.jar Qualified Name:
 * com.caved_in.Mobs.Attacks.Strike JD-Core Version: 0.6.2
 */