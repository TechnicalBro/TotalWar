package com.caved_in.Handlers.EntityHandlers.Bosses;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.EntityType;

import com.caved_in.Handlers.EntityHandlers.Attacks.*;
import com.caved_in.Handlers.EntityHandlers.BossHandler.BossType;

public class Porker extends Boss
{
	private String Name = "Porker";
	private int HP = 550;

	private Burn Burn = new Burn(60, 4);
	private Knockback Knockback = new Knockback(-2, 30, 4);
	private Poison Poison = new Poison(55, 2);
	private Strike Strike = new Strike(25, 25, 2);
	private Slow Slow = new Slow(20, 4);
	private Teleport Teleport = new Teleport(5, 5);
	private Explode Explode = new Explode(15, 2);
	private Spawn Spawn = new Spawn(15, 5, EntityType.PIG_ZOMBIE);

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
		return Arrays.asList(new Attack[] { this.Explode, this.Slow, this.Knockback, this.Burn, this.Strike, this.Teleport, this.Spawn });
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
		return BossType.Porker;
	}
}

/*
 * Location: C:\Users\Brandon\Desktop\TotalWar.jar Qualified Name:
 * com.caved_in.Mobs.Bosses.Porker JD-Core Version: 0.6.2
 */