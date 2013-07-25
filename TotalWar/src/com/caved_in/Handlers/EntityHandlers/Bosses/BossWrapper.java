package com.caved_in.Handlers.EntityHandlers.Bosses;

import org.bukkit.entity.LivingEntity;

public class BossWrapper
{
	private LivingEntity Entity;
	private Boss Boss;

	public BossWrapper(LivingEntity Entity, Boss Boss)
	{
		this.Entity = Entity;
		this.Boss = Boss;
	}

	public Boss getBoss()
	{
		return this.Boss;
	}

	public LivingEntity getEntity()
	{
		return this.Entity;
	}
}
