package com.caved_in.Handlers.EntityHandlers.Bosses;

import java.util.Arrays;
import java.util.List;

import com.caved_in.Handlers.EntityHandlers.Attacks.Attack;
import com.caved_in.Handlers.EntityHandlers.Attacks.Burn;
import com.caved_in.Handlers.EntityHandlers.Attacks.Knockback;
import com.caved_in.Handlers.EntityHandlers.Attacks.Poison;
import com.caved_in.Handlers.EntityHandlers.Attacks.Slow;
import com.caved_in.Handlers.EntityHandlers.Attacks.Strike;
import com.caved_in.Handlers.EntityHandlers.Attacks.Teleport;
import com.caved_in.Handlers.EntityHandlers.BossHandler.BossType;

public class Cthulu extends Boss
{
	private String Name = "Cthulu";

	private Burn Burn = new Burn(10, 4);
	private Knockback Knockback = new Knockback(-2, 30, 4);
	private Poison Poison = new Poison(55, 2);
	private Strike Strike = new Strike(15, 50, 2);
	private Slow Slow = new Slow(50, 7);
	private Teleport Teleport = new Teleport(45, 5);
	private int MaxHP = 750;

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
		return Arrays.asList(new Attack[] { this.Burn, this.Knockback, this.Poison, this.Strike, this.Slow, this.Teleport });
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
		return BossType.Cthulu;
	}
}