package com.caved_in.Handlers.EntityHandlers.Attacks;

import java.util.Random;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.caved_in.Handlers.EffectHandlers.ParticleEffects;

public class Explode extends Attack
{
	private ParticleEffects Explode = ParticleEffects.HUGE_EXPLOSION;
	private int Chance = 0;
	private int Radius = 3;

	/**
	 * 
	 * @param Chances
	 * @param Radius
	 */
	public Explode(int Chances, int Radius)
	{
		this.Chance = Chances;
		this.Radius = Radius;
	}

	@Override
	public boolean Do(LivingEntity From)
	{
		try
		{
			for (Player P : this.getPlayersInRadius(From, this.Radius))
			{
				this.Explode.sendToAll(P.getLocation(), new Random().nextFloat(), 1 + new Random().nextInt(10));
				P.getWorld().createExplosion(P.getLocation().getX(), P.getLocation().getY(), P.getLocation().getZ(), 0.2F, false, false);
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
		// TODO Auto-generated method stub
		return this.Chance;
	}
}