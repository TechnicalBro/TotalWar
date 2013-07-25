package com.caved_in.Handlers.EntityHandlers.Attacks;

import java.util.Random;

import org.bukkit.entity.Ageable;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Spawn extends Attack
{

	private int Chances = 0;
	private int Radius = 3;
	private EntityType Type = EntityType.CHICKEN;

	/**
	 * 
	 * @param Chances
	 * @param Radius
	 * @param Type
	 */
	public Spawn(int Chances, int Radius, EntityType Type)
	{
		this.Chances = Chances;
		this.Radius = Radius;
		this.Type = Type;
	}

	@Override
	public boolean Do(LivingEntity Attacker)
	{
		for (Player P : this.getPlayersInRadius(Attacker, this.Radius))
		{
			if (this.PercentCheck(50))
			{
				LivingEntity Entity = (LivingEntity) Attacker.getWorld().spawnEntity(P.getLocation(), this.Type);
				if (Entity instanceof Ageable)
				{
					((Ageable) Entity).setBaby();
				}
				Entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 500 + new Random().nextInt(30), 2));
				if (Entity instanceof Creature)
				{
					((Creature) Entity).setTarget(P);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int ExecutionChance()
	{
		return this.Chances;
	}

}
