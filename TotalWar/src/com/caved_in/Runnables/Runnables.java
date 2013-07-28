package com.caved_in.Runnables;

import com.caved_in.TotalWar;

public class Runnables
{
	public Runnables(TotalWar Plugin)
	{
		Plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Plugin, new DonateReminderRunnable(), 9000L, 28000L);
		Plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Plugin, new VoteReminder(), 14000L, 32000L);
		Plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Plugin, new UpdateHealthDisplayRunnable(), 60L, 40L);
		Plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Plugin, new BossAttackRunnable(), 120L, 40L);
		Plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Plugin, new ManaRegen(), 60L, 100L);
		Plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Plugin, new ArmorEffects(), 60L, 35L);
	}
}