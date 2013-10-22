package com.caved_in.Handlers.EntityHandlers.Attacks;

import java.util.Random;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.caved_in.Handlers.EffectHandlers.ParticleEffects;

public class Poison extends Attack
{
	private ParticleEffects Flames = ParticleEffects.SPLASH;
	private int Chances = 0;
	private int Radius = 3;

	/**
	 * 
	 * @param Chance
	 * @param Rad
	 */
	public Poison(int Chance, int Rad)
	{
		this.Chances = Chance;
		this.Radius = Rad;
	}

	@Override
	public boolean Do(LivingEntity From)
	{
		try
		{
			for (Player P : this.getPlayersInRadius(From, this.Radius))
			{
				this.Flames.sendToAll(P.getLocation(), new Random().nextFloat(), 1 + new Random().nextInt(10));
				P.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 10 + new Random().nextInt(100), 1));
			}
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		// To.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 35, 1));
	}

	@Override
	public int ExecutionChance()
	{
		// TODO Auto-generated method stub
		return this.Chances;
	}
}

/*
 * Location: C:\Users\Brandon\Desktop\TotalWar.jar Qualified Name:
 * com.caved_in.Mobs.Attacks.Poison JD-Core Version: 0.6.2
 */