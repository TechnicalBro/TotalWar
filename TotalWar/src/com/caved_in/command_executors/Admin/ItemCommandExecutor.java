package com.caved_in.command_executors.Admin;

import com.caved_in.Items.Gem;
import com.caved_in.TotalWarItems.TotalWarItems;
import com.rit.sucy.EnchantmentAPI;

import me.cybermaxke.materialapi.inventory.CustomItemStack;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemCommandExecutor implements CommandExecutor
{
	public ItemCommandExecutor()
	{
	}

	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args)
	{
		if ((Sender.isOp()) && (Args.length > 0) && ((Sender instanceof Player)))
		{
			try
			{
				if (Args[0].equalsIgnoreCase("gem"))
				{
					CustomItemStack GemStack = new CustomItemStack(new Gem("", Material.EMERALD));
					Player Player = (Player) Sender;
					Player.getInventory().addItem(GemStack.getItem());
					Player.updateInventory();
					return true;
				}
				else if (Args[0].equalsIgnoreCase("lavagem"))
				{
					Player Player = (Player) Sender;
					ItemStack Gem = new ItemStack(Material.EMERALD);
					ItemMeta iMeta = Gem.getItemMeta();
					iMeta.setDisplayName(ChatColor.YELLOW + "Embued gem of Molten Lava");
					Gem.setItemMeta(iMeta);
					iMeta.addEnchant(Enchantment.DURABILITY, 5, true);
					EnchantmentAPI.getEnchantment("Lava Scorch").addToItem(Gem, 1);
					Player.getInventory().addItem(Gem);
					Player.updateInventory();
					return true;
				}
				else if (Args[0].equalsIgnoreCase("magegem"))
				{
					Player Player = (Player) Sender;
					ItemStack Gem = new ItemStack(Material.EMERALD);
					ItemMeta iMeta = Gem.getItemMeta();
					iMeta.setDisplayName(ChatColor.YELLOW + "Embued gem of Arcane Enhancement");
					iMeta.addEnchant(Enchantment.DURABILITY, 5, true);
					Gem.setItemMeta(iMeta);
					EnchantmentAPI.getEnchantment("Arcane Enhancement").addToItem(Gem, 1);
					Player.getInventory().addItem(Gem);
					Player.updateInventory();
					return true;
				}
				else if (Args[0].equalsIgnoreCase("dragongem"))
				{
					Player Player = (Player) Sender;
					ItemStack Gem = new ItemStack(Material.EMERALD);
					ItemMeta iMeta = Gem.getItemMeta();
					iMeta.setDisplayName(ChatColor.YELLOW + "Embued gem of Dragon Temperment");
					iMeta.addEnchant(Enchantment.DURABILITY, 5, true);
					Gem.setItemMeta(iMeta);
					EnchantmentAPI.getEnchantment("Dragon Scream").addToItem(Gem, 1);
					Player.getInventory().addItem(Gem);
					Player.updateInventory();
					return true;
				}
				else
				{
					CustomItemStack Gen = TotalWarItems.ItemHandler.getCustomItemStack(Args[0]);
					Player P = (Player) Sender;
					if (Gen != null)
					{
						P.getInventory().addItem(new ItemStack[] { Gen.getItem() });
						P.updateInventory();
						return true;
					}
					P.sendMessage("That item doesn't exist...");
					return false;
				}
			}
			catch (Exception Ex)
			{
				Player P = (Player) Sender;
				Ex.printStackTrace();
				P.sendMessage("That item doesn't exist...");
				return false;
			}
		}
		return false;
	}
}