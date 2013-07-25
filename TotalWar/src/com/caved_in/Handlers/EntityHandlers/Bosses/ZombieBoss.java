package com.caved_in.Handlers.EntityHandlers.Bosses;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.entity.EntityType;

import com.caved_in.Handlers.EntityHandlers.Attacks.*;
import com.caved_in.Handlers.EntityHandlers.BossHandler.BossType;

public class ZombieBoss extends Boss
{
	private List<String> Names = Arrays.asList(new String[] { "Wolfred", "Wollis", "Ralph", "Jeff", "Jeffery Sr.", "Slim", "Finch", "Stiffler", "Winston", "Cadent", "The Anti Lift", "Big Jeffery", "Stomper", "Stamper" });

	private String Name = "";
	private int MaxHealth = 900;
	private Slow Slow = new Slow(65, 5);
	private Knockback Knockback = new Knockback(-2, 25, 2);
	private Strike Strike = new Strike(40, 45, 3);
	private Spawn Spawn = new Spawn(25, 7, EntityType.ZOMBIE);
	private int EntityID = -1;

	public ZombieBoss()
	{
		this.Name = Names.get(new Random().nextInt(Names.size()));
	}

	@Override
	public String getName()
	{
		return this.Name;
	}

	@Override
	public int getMaxHP()
	{
		return this.MaxHealth;
	}

	@Override
	public List<Attack> getAttacks()
	{
		return Arrays.asList(new Attack[] { this.Slow, this.Knockback, this.Strike, this.Spawn });
	}

	@Override
	public void onDeath()
	{
		// TODO Auto-generated method stub

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
		return BossType.ZombieBoss;
	}

}
