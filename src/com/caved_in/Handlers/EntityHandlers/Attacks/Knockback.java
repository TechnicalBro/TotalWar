package com.caved_in.Handlers.EntityHandlers.Attacks;

import java.util.Random;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.caved_in.Handlers.EffectHandlers.ParticleEffects;

public class Knockback extends Attack
{
	private int Force = -1;
	private int Chances = 0;
	private int Radius = 3;
	private ParticleEffects Knockback = ParticleEffects.CRIT;

	/**
	 * 
	 * @param Force
	 * @param Radius
	 * @param Chances
	 */
	public Knockback(int Force, int Chances, int Radius)
	{
		this.Force = Force;
		this.Chances = Chances;
		this.Radius = Radius;
	}

	@Override
	public boolean Do(LivingEntity From)
	{
		for (Player P : this.getPlayersInRadius(From, this.Radius))
		{
			try
			{
				this.Knockback.sendToAll(P.getLocation(), new Random().nextFloat(), 1 + new Random().nextInt(10));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			P.setVelocity(P.getLocation().getDirection().multiply(this.Force));
		}
		return true;
	}

	@Override
	public int ExecutionChance()
	{
		// TODO Auto-generated method stub
		return this.Chances;
	}
}