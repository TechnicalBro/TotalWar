package com.caved_in.Runnables;

import java.util.List;

import org.bukkit.entity.LivingEntity;

import com.caved_in.TotalWar;
import com.caved_in.Handlers.EntityHandlers.Attacks.Attack;
import com.caved_in.Handlers.EntityHandlers.Bosses.Boss;
import com.caved_in.Handlers.EntityHandlers.Bosses.BossWrapper;

public class BossAttackRunnable implements Runnable
{

	@Override
	public void run()
	{
		for (BossWrapper Wrapper : TotalWar.BossHandler.currentBosses().values())
		{
			Boss Boss = Wrapper.getBoss();
			LivingEntity Entity = Wrapper.getEntity();
			if (!Entity.isDead())
			{
				if (Boss.IsNearPlayers(Entity))
				{
					List<Attack> BossAttacks = Boss.getAttacks();
					for (Attack At : BossAttacks)
					{
						if (At.PercentCheck(At.ExecutionChance()))
						{
							if (At.Do(Entity))
							{
								TotalWar.Console("Boss with ID[" + Boss.getEntityID() + "] executed an attack");
								break;
							}
						}
					}
				}
			}
		}
	}

}
