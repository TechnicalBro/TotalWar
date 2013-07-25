package com.caved_in.Handlers.EntityHandlers.Bosses;

import java.util.Arrays;
import java.util.List;

import com.caved_in.Handlers.EntityHandlers.Attacks.Attack;
import com.caved_in.Handlers.EntityHandlers.Attacks.Burn;
import com.caved_in.Handlers.EntityHandlers.Attacks.Knockback;
import com.caved_in.Handlers.EntityHandlers.Attacks.Slow;
import com.caved_in.Handlers.EntityHandlers.Attacks.Strike;
import com.caved_in.Handlers.EntityHandlers.BossHandler.BossType;

public class DrNate extends Boss
{
	private String Name = "Dr.Nate";
	private int MaxHP = 350;

	private Burn Burn = new Burn(50, 6);
	private Knockback Knockback = new Knockback(-2, 50, 4);
	private Strike Strike = new Strike(35, 50, 2);
	private Slow Slow = new Slow(15, 2);

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
		return Arrays.asList(new Attack[] { this.Burn, this.Knockback, this.Strike, this.Slow });
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
		return BossType.DrNate;
	}
}

/*
 * Location: C:\Users\Brandon\Desktop\TotalWar.jar Qualified Name:
 * com.caved_in.Mobs.Bosses.DrNate JD-Core Version: 0.6.2
 */