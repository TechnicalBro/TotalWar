package com.caved_in.Handlers.EntityHandlers.Bosses;

import java.util.Arrays;
import java.util.List;

import com.caved_in.Handlers.EntityHandlers.Attacks.Attack;
import com.caved_in.Handlers.EntityHandlers.Attacks.Knockback;
import com.caved_in.Handlers.EntityHandlers.Attacks.Poison;
import com.caved_in.Handlers.EntityHandlers.Attacks.Slow;
import com.caved_in.Handlers.EntityHandlers.Attacks.Strike;
import com.caved_in.Handlers.EntityHandlers.BossHandler.BossType;

public class Queen_Widow extends Boss
{
	private String Name = "Queen Widow";
	private int HP = 700;

	private Knockback Knockback = new Knockback(-2, 30, 4);
	private Poison Poison = new Poison(65, 5);
	private Strike Strike = new Strike(42, 50, 2);
	private Slow Slow = new Slow(60, 7);
	private int EntityID = -1;

	@Override
	public String getName()
	{
		return this.Name;
	}

	@Override
	public int getMaxHP()
	{
		return this.HP;
	}

	@Override
	public List<Attack> getAttacks()
	{
		return Arrays.asList(new Attack[] { this.Knockback, this.Poison, this.Strike, this.Slow });
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
		return BossType.QueenWidow;
	}
}