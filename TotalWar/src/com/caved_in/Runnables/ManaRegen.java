package com.caved_in.Runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.rit.sucy.CustomEnchantment;
import com.rit.sucy.EnchantmentAPI;

import TotalWizard.TotalWizard;

public class ManaRegen implements Runnable
{

	@Override
	public void run()
	{
		try
		{
			for (Player P : Bukkit.getOnlinePlayers())
			{
				int ManaPlus = 3;
				for (ItemStack is : P.getInventory().getArmorContents())
				{
					if (is != null)
					{
						ItemStack ArmorPiece = is.clone();
						for (CustomEnchantment enchantment : EnchantmentAPI.getEnchantments(ArmorPiece).keySet())
						{
							if (enchantment.name().equalsIgnoreCase("arcane enhancement"))
							{
								ManaPlus += 2;
							}
						}
					}
				}
	
				if ((P.getLevel() + ManaPlus) < TotalWizard.MagicHandler.getMaxMana(P.getName()))
				{
					P.setLevel(P.getLevel() + ManaPlus);
				}
				else
				{
					P.setLevel(TotalWizard.MagicHandler.getMaxMana(P.getName()));
				}
	
			}
		}
		catch (Exception Ex)
		{
			
		}
	}

}
