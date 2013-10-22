package com.caved_in.ItemMenus.SpellbindMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import com.caved_in.TotalWar;
import com.caved_in.Handlers.ItemHandlers.ItemHandler;

import TotalWizard.Handlers.Magic.*;
import TotalWizard.Handlers.Magic.Spells.*;
import me.xhawk87.PopupMenuAPI.MenuItem;

public class SpellbindItem extends MenuItem
{
	private Spell Spell;
	private int CostOfBind = 0;
	public SpellbindItem(String text, MaterialData icon,Spell Spell)
	{
		super(text, icon);
		this.Spell = Spell;
		this.CostOfBind = ((Spell.getLevelRequirement() * Spell.getManaRequirement()) * 4);
	}
	
	public Spell getSpell()
	{
		return this.Spell;
	}
	
	public int getCost()
	{
		return this.CostOfBind;
	}

	@Override
	public void onClick(Player Player)
	{
		if (Player.getItemInHand() != null && Player.getItemInHand().getType() != Material.AIR)
		{
			if (TotalWar.economy.has(Player.getName(), this.CostOfBind))
			{
				TotalWar.economy.withdrawPlayer(Player.getName(), this.CostOfBind);
				TotalWar.economy.depositPlayer("TheGamersCave",this.CostOfBind);
				List<String> LoreAdd = new ArrayList<String>();
				LoreAdd.add(ChatColor.GOLD + "Spell: " + this.Spell.getName());
				for(String S : this.Spell.getDescription())
				{
					LoreAdd.add(S);
				}
				Player.setItemInHand(ItemHandler.addLore(Player.getItemInHand(), LoreAdd));
				Player.updateInventory();
				this.getMenu().closeMenu(Player);
				Player.sendMessage(ChatColor.GREEN + "Enjoy your spell-bound item");
			}
			else
			{
				Player.sendMessage(ChatColor.RED + "You don't have enough money to bind this spell to your item!");
			}
		}
	}

}
