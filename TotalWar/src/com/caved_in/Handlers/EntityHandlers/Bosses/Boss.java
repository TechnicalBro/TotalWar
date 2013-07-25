package com.caved_in.Handlers.EntityHandlers.Bosses;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.caved_in.Handlers.EntityHandlers.Attacks.Attack;
import com.caved_in.Handlers.EntityHandlers.BossHandler.BossType;

public abstract class Boss
{
	public abstract String getName();

	public abstract int getMaxHP();

	public abstract List<Attack> getAttacks();

	public abstract void onDeath();

	public abstract int getEntityID();

	public abstract void setEntityID(int ID);

	public abstract BossType Type();

	public boolean IsNearPlayers(LivingEntity Entity)
	{
		try
		{
			for (Player P : Bukkit.getOnlinePlayers())
			{
				if (P.getLocation().getWorld() == Entity.getLocation().getWorld())
				{
					if (Entity.getLocation().distance(P.getLocation()) < 10)
					{
						return true;
					}
				}
			}
			return false;
		}
		catch (Exception Ex)
		{
			return false;
		}
	}
}

/*
 * Location: C:\Users\Brandon\Desktop\TotalWar.jar Qualified Name:
 * com.caved_in.Mobs.Bosses.Boss JD-Core Version: 0.6.2
 */