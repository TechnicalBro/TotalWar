package com.caved_in.Runnables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import me.cybermaxke.materialapi.inventory.CustomItemStack;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import com.caved_in.TotalWar;

public class UpdateInventories
{
	private TotalWar Plugin;
	private ArrayList<Material> LoreItems = new ArrayList<Material>();
	private Material[] Materials = { Material.WOOD_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.DIAMOND_SWORD, Material.BOW };

	public UpdateInventories(TotalWar Plugin)
	{
		this.Plugin = Plugin;
		for (Material M : this.Materials)
		{
			this.LoreItems.add(M);
		}
	}

	public void UpdateWeaponLores()
	{
		for (Player P : this.Plugin.getServer().getOnlinePlayers())
		{
			UpdateWeaponLore(P);
		}
	}

	/*
	public void UpdateEventBooks()
	{
		for (Player P : this.Plugin.getServer().getOnlinePlayers())
		{
			if ((!TotalWar.BookManager.isUpdated(P.getName())) || (!P.getInventory().contains(Material.WRITTEN_BOOK)))
			{
				RemoveEventBooks(P);
				GiveEventBook(P);
			}
		}
	}

	public void UpdatePlayerEventBook(Player P)
	{
		RemoveEventBooks(P);
		GiveEventBook(P);
	}

	
	public void GiveEventBook(Player P)
	{
		ItemStack NewEventBook = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta NewMeta = (BookMeta) NewEventBook.getItemMeta();
		for (String Page : TotalWar.BookManager.getPages())
		{
			NewMeta.addPage(new String[] { Page });
		}
		NewMeta.setAuthor("TotalWar Events");
		NewMeta.setTitle("Current Events");
		NewEventBook.setItemMeta(NewMeta);
		P.getInventory().addItem(new ItemStack[] { NewEventBook });
		P.updateInventory();
		TotalWar.BookManager.setUpdated(P.getName(), Long.valueOf(System.currentTimeMillis()));
	}
	*/

	public void RemoveEventBooks(Player P)
	{
		if (P.getInventory().contains(Material.WRITTEN_BOOK))
		{
			HashMap<Integer, ? extends ItemStack> PlayerBook = P.getInventory().all(Material.WRITTEN_BOOK);
			for (Entry<Integer, ? extends ItemStack> Pair : PlayerBook.entrySet())
			{
				BookMeta PairBM = (BookMeta) ((ItemStack) Pair.getValue()).getItemMeta();
				if ((PairBM.hasAuthor()) && (PairBM.getAuthor().equalsIgnoreCase("TotalWar Events")) && (PairBM.hasTitle()) && (PairBM.getTitle().equalsIgnoreCase("Current Events")))
				{
					P.getInventory().setItem(Pair.getKey().intValue(), null);
					P.updateInventory();
				}
			}
		}
	}

	public void UpdateWeaponLore(Player P)
	{
		ItemStack[] InvItems = P.getInventory().getContents();
		for (ItemStack I : InvItems)
		{
			try
			{
				if ((I.getType() != null) && (I.getType() != Material.AIR))
				{
					if ((this.LoreItems.contains(I.getType())) && (I.hasItemMeta()))
					{
						ItemMeta Meta = I.getItemMeta();
						if (Meta.hasLore())
						{
							CustomItemStack Replacement = new CustomItemStack(I);
							ItemMeta ReplaceMeta = Replacement.getItemMeta();
							ArrayList<String> Lore = new ArrayList<String>();
							boolean ReplaceIt = false;
							int ReplaceSlot = -1;
							for (String S : Meta.getLore())
							{
								if (S.contains("Dishes out"))
								{
									int Damage = Integer.parseInt(StringUtils.substringBetween(S, "Dishes out ", " Damage!"));
									Lore.add(ChatColor.RED + "Deals " + (1 + new Random().nextInt(Damage)) + " to " + Damage + " damage!");
									ReplaceIt = true;
									ReplaceSlot = P.getInventory().first(I);
								}
								else
								{
									Lore.add(S);
								}
							}
							if ((ReplaceIt) && (ReplaceSlot != -1))
							{
								ReplaceMeta.setLore(Lore);
								Replacement.setItemMeta(ReplaceMeta);
								P.getInventory().setItem(ReplaceSlot, Replacement.getItem());
								P.updateInventory();
								ReplaceIt = false;
								ReplaceSlot = -1;
							}
						}
					}
				}
			}
			catch (NullPointerException localNullPointerException)
			{
			}
		}
	}
}
