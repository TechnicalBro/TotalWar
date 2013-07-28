package com.caved_in.Handlers.EntityHandlers.Bosses;

import java.util.List;

import com.caved_in.Handlers.EntityHandlers.BossHandler.BossType;
import com.caved_in.Handlers.EntityHandlers.Attacks.Attack;

public class Snowman extends Boss
{

	@Override
	public String getName()
	{
		return "Snowman";
	}

	@Override
	public int getMaxHP()
	{
		return 400;
	}

	@Override
	public List<Attack> getAttacks()
	{
		return null;
	}

	@Override
	public void onDeath()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int getEntityID()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setEntityID(int ID)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public BossType Type()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
