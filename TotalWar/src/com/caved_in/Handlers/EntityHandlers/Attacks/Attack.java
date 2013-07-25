package com.caved_in.Handlers.EntityHandlers.Attacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public abstract class Attack
{
	public abstract boolean Do(LivingEntity Attacker);

	public abstract int ExecutionChance();

	public boolean PercentCheck(double Chances)
	{
		return new Random().nextInt(100) <= Chances;
	}

	public List<Player> getPlayersInRadius(LivingEntity Attacker, int Radius)
	{
		List<Player> Return = new ArrayList<Player>();
		if (Radius != 0)
		{
			for (Player P : Bukkit.getOnlinePlayers())
			{
				if (P.getLocation().getWorld() == Attacker.getLocation().getWorld())
				{
					if (P.getGameMode() != GameMode.CREATIVE)
					{
						if (Attacker.getLocation().distance(P.getLocation()) < Radius)
						{
							Return.add(P);
						}
					}
				}
			}
		}
		return Return;
	}
}

/*
 * Location: C:\Users\Brandon\Desktop\TotalWar.jar Qualified Name:
 * com.caved_in.Mobs.Attacks.Attack JD-Core Version: 0.6.2
 */