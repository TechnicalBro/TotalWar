package com.caved_in.Handlers.EntityHandlers.Attacks;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class Throw extends Attack
{
	private Class<Arrow> ProjectileType = Arrow.class;
	private Entity Ent;

	/**
	 * 
	 * @param Projectile
	 * @param Entity
	 */
	public Throw(Class<Arrow> Projectile, Entity Entity)
	{
		this.ProjectileType = Projectile;
		this.Ent = Entity;
	}

	public Throw(Entity Ent)
	{
		this.Ent = Ent;
	}

	@Override
	public boolean Do(LivingEntity From)
	{
		LivingEntity Entity = (LivingEntity) this.Ent;
		if (!Entity.isDead())
		{
			Entity.launchProjectile(this.ProjectileType);
		}
		return true;
	}

	@Override
	public int ExecutionChance()
	{
		// TODO Auto-generated method stub
		return 0;
	}
}

/*
 * Location: C:\Users\Brandon\Desktop\TotalWar.jar Qualified Name:
 * com.caved_in.Mobs.Attacks.Throw JD-Core Version: 0.6.2
 */