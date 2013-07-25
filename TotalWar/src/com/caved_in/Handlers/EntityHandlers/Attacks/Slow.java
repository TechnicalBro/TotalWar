package com.caved_in.Handlers.EntityHandlers.Attacks;

import java.util.Random;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.caved_in.Handlers.EffectHandlers.ParticleEffects;

public class Slow extends Attack
{
	private ParticleEffects Flames = ParticleEffects.SPLASH;
	private int Chance = 0;
	private int Radius = 3;

	public Slow(int Chances, int Rad)
	{
		this.Chance = Chances;
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
				P.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10 + new Random().nextInt(200), 1));
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