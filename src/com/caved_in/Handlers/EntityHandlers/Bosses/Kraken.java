package com.caved_in.Handlers.EntityHandlers.Bosses;

import java.util.Arrays;
import java.util.List;

import com.caved_in.Handlers.EntityHandlers.Attacks.Attack;
import com.caved_in.Handlers.EntityHandlers.Attacks.Knockback;
import com.caved_in.Handlers.EntityHandlers.Attacks.Poison;
import com.caved_in.Handlers.EntityHandlers.Attacks.Slow;
import com.caved_in.Handlers.EntityHandlers.Attacks.Strike;
import com.caved_in.Handlers.EntityHandlers.BossHandler.BossType;

public class Kraken extends Boss
{
	private String Name = "Kraken";
	private int MaxHP = 1200;

	private Knockback Knockback = new Knockback(-2, 50, 4);
	private Poison Poison = new Poison(55, 2);
	private Strike Strike = new Strike(75, 50, 2);
	private Slow Slow = new Slow(50, 7);

	private int EntityID = -1;

	@Override
	public String getName()
	{
		return this.Name;
	}

	@Override
	public int getMaxHP()
	{
		return this.MaxHP;
	}

	@Override
	public List<Attack> getAttacks()
	{
		return Arrays.asList(new Attack[] { this.Knockback, this.Poison, this.Slow, this.Strike });
	}

	@Override
	public void onDeath()
	{
	}

	@Override
	public int getEntityID()
	{
		return this.EntityID;
	}

	@Override
	public void setEntityID(int ID)
	{
		this.EntityID = ID;
	}

	@Override
	public BossType Type()
	{
		return BossType.Kraken;
	}
}