package com.caved_in.Runnables;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.caved_in.Handlers.EffectHandlers.ParticleEffects;
import com.rit.sucy.CustomEnchantment;
import com.rit.sucy.EnchantmentAPI;

public class ArmorEffects implements Runnable
{

	public ArmorEffects()
	{
	}

	@Override
	public void run()
	{
		for (Player p : Bukkit.getServer().getOnlinePlayers())
		{
			for (ItemStack is : p.getInventory().getArmorContents())
			{
				if (is != null)
				{
					ItemStack ArmorPiece = is.clone();
					for (CustomEnchantment enchantment : EnchantmentAPI.getEnchantments(ArmorPiece).keySet())
					{
						this.HandleItem(enchantment, p);
					}
				}
			}
		}
	}

	public void HandleItem(CustomEnchantment Enchant, Player Player)
	{
		ParticleEffects PE;
		try
		{
			switch (Enchant.name())
			{
			case "Lava Scorch":
				{
					PE = ParticleEffects.DRIP_LAVA;
					PE.sendToAll(Player.getLocation(), new Random().nextFloat(), 5 + new Random().nextInt(14));
					break;
				}
			case "Arcane Enhancement":
				{
					PE = ParticleEffects.SPELL;
					PE.sendToAll(Player.getLocation(), new Random().nextFloat(), 5 + new Random().nextInt(14));
					break;
				}
			default:
				{
					break;
				}
			}
		}
		catch (Exception Ex)
		{
			Ex.printStackTrace();
		}
	}
}
