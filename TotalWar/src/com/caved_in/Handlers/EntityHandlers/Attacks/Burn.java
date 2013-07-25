package com.caved_in.Handlers.EntityHandlers.Attacks;

import java.util.Random;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.caved_in.Handlers.EffectHandlers.ParticleEffects;

public class Burn extends Attack
{
	private ParticleEffects Flames = ParticleEffects.LARGE_SMOKE;
	private int Chance = 0;
	private int Radius = 3;

	/**
	 * Instances a new Attack
	 * 
	 * @param ExecutionChance
	 * @param Radius
	 */
	public Burn(int ExecutionChance, int Radius)
	{
		this.Chance = ExecutionChance;
		this.Radius = Radius;
	}

	@Override
	public boolean Do(LivingEntity From)
	{
		try
		{
			for (Player P : this.getPlayersInRadius(From, this.Radius))
			{
				this.Flames.sendToAll(P.getLocation(), new Random().nextFloat(), new Random().nextInt(12) + 1);
				P.setFireTicks(10 + new Random().nextInt(100));
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
 * com.caved_in.Mobs.Attacks.Burn JD-Core Version: 0.6.2
 */