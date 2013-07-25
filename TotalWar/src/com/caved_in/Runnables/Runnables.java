package com.caved_in.Runnables;

import com.caved_in.TotalWar;

public class Runnables
{
	public Runnables(TotalWar Plugin)
	{
		// Plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Plugin,
		// new UpdateInventoriesRunnable(), 0L, 800L);
		// Plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Plugin,
		// new ReplaceEventsRunnable(), 216000L, 216000L);
		Plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Plugin, new DonateReminderRunnable(), 9000L, 28000L);
		Plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Plugin, new VoteReminder(), 14000L, 32000L);
		Plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Plugin, new UpdateHealthDisplayRunnable(), 60L, 60L);
		Plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Plugin, new BuildContestRunnable(), 60L, 16800L);
		Plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Plugin, new BossAttackRunnable(), 120L, 40L);
		Plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Plugin, new ManaRegen(), 60L, 100L);
		Plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Plugin, new ArmorEffects(), 60L, 20L);
	}
}