package com.caved_in.Handlers.EntityHandlers.Bosses;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.caved_in.Handlers.EntityHandlers.Attacks.*;
import com.caved_in.Handlers.EntityHandlers.BossHandler.BossType;

public class Skeletor extends Boss
{
	private List<String> Names = Arrays.asList(new String[] { "Skeletor","Brutal Bones", "Brittle Bones","Skeletron","Necrobone","Bones","Old Bones","Calcimite"});
	private int MaxHealth = 650;
	private String Name = "Skeletor";

	private Slow Slow = new Slow(65, 5);
	private Strike Strike = new Strike(50, 25, 2);
	private Teleport Teleport = new Teleport(25, 4);
	private Poison Poison = new Poison(30, 5);

	private int EntityID = -1;
	
	public Skeletor()
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
		return Arrays.asList(new Attack[] { this.Slow, this.Strike, this.Teleport, this.Poison });
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
		return BossType.Skeletor;
	}

}
