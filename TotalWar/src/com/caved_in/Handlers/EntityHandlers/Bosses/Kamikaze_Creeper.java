package com.caved_in.Handlers.EntityHandlers.Bosses;

import java.util.Arrays;
import java.util.List;

import com.caved_in.Handlers.EntityHandlers.Attacks.Attack;
import com.caved_in.Handlers.EntityHandlers.Attacks.Burn;
import com.caved_in.Handlers.EntityHandlers.Attacks.Explode;
import com.caved_in.Handlers.EntityHandlers.Attacks.Knockback;
import com.caved_in.Handlers.EntityHandlers.Attacks.Strike;
import com.caved_in.Handlers.EntityHandlers.BossHandler.BossType;

public class Kamikaze_Creeper extends Boss
{
	private String Name = "Kamikze Creeper";
	private int HP = 350;

	private Burn Burn = new Burn(24, 4);
	private Knockback Knockback = new Knockback(-3, 90, 3);
	private Strike Strike = new Strike(25, 50, 3);
	private Explode Explode = new Explode(70, 4);

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
		return Arrays.asList(new Attack[] { this.Burn, this.Knockback, this.Strike, this.Explode });
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
		return BossType.KamikazeCreeper;
	}
}