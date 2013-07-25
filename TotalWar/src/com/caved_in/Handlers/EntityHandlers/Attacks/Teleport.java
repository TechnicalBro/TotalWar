package com.caved_in.Handlers.EntityHandlers.Attacks;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Teleport extends Attack
{
	private int Chance = 0;
	private int Radius = 3;

	/**
	 * 
	 * @param Chance
	 * @param Rad
	 */
	public Teleport(int Chance, int Rad)
	{
		this.Chance = Chance;
		this.Radius = Rad;
	}

	@Override
	public boolean Do(LivingEntity Attacker)
	{
		for (Player P : this.getPlayersInRadius(Attacker, this.Radius))
		{
			P.teleport(Attacker.getLocation());
		}
		return true;
	}

	@Override
	public int ExecutionChance()
	{
		// TODO Auto-generated method stub
		return this.Chance;
	}

}
