package com.caved_in.Runnables;

import com.caved_in.TotalWar;

public class UpdateInventoriesRunnable implements Runnable
{
	@Override
	public void run()
	{
		TotalWar.InventoryUpdater.UpdateEventBooks();
		TotalWar.InventoryUpdater.UpdateWeaponLores();
	}
}