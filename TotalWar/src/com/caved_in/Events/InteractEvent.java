package com.caved_in.Events;

import com.caved_in.TotalWar;
import com.caved_in.Cooldown.Cooldown;
import com.caved_in.Handlers.FileHandler.DataHandler;
import com.caved_in.Handlers.ItemHandlers.GemCraft;
import com.caved_in.ItemMenus.MainMenu.MainMenu;
import com.rit.sucy.CustomEnchantment;
import com.rit.sucy.EnchantmentAPI;

import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InteractEvent implements Listener
{
	HashMap<String, GemCraft> Gem_Handling = new HashMap<String, GemCraft>();
	private Cooldown GemCool = new Cooldown(1);
	private Cooldown MessageCool = new Cooldown(2);
	private Cooldown MenuCool = new Cooldown(1);

	public InteractEvent(TotalWar Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void PlayerInteract(PlayerInteractEvent Event)
	{
		Player Player = Event.getPlayer();
		if (Event.getAction() == Action.RIGHT_CLICK_AIR || Event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			if (Player.getItemInHand() == null || Player.getItemInHand().getType() == Material.AIR)
			{
				if (this.Gem_Handling.containsKey(Player.getName()))
				{
					this.Gem_Handling.remove(Player.getName());
					return;
				}
				return;
			}
			else if (Player.getItemInHand().getType() == Material.PAPER && Player.getItemInHand().hasItemMeta() && Player.getItemInHand().getItemMeta().hasDisplayName() && Player.getItemInHand().getItemMeta().getDisplayName().contains("Adventurers Toolkit"))
			{
				if (!this.MenuCool.IsOnCooldown(Player.getName()))
				{
					new MainMenu(Player);
					this.MenuCool.SetOnCooldown(Player.getName());
					return;
				}
			}
			else if ((Player.getItemInHand() != null) && (!this.GemCool.IsOnCooldown(Player.getName())))
			{
				if (new GemCraft().isGem(Player.getItemInHand()))
				{
					if ((Event.getAction() == Action.RIGHT_CLICK_AIR) || (Event.getAction() == Action.RIGHT_CLICK_BLOCK))
					{
						if (this.Gem_Handling.containsKey(Player.getName()) && this.Gem_Handling.get(Player.getName()).isSameGem(Player.getItemInHand(), Player.getInventory().getHeldItemSlot()))
						{
							return;
						}
						else if (!this.Gem_Handling.containsKey(Player.getName()))
						{
							this.Gem_Handling.put(Player.getName(), new GemCraft(Player.getName(), Player.getInventory().getHeldItemSlot(), Player.getItemInHand().getItemMeta().getDisplayName()));
							this.GemCool.SetOnCooldown(Player.getName());
							return;
						}
						else
						{
							Handle_GemCraft(Player);
							this.GemCool.SetOnCooldown(Player.getName());
							return;
						}
					}
					else if (!MessageCool.IsOnCooldown(Player.getName()))
					{
						Player.sendMessage(ChatColor.RED + "Left click the gem, then the item ;)");
						MessageCool.SetOnCooldown(Player.getName());
						return;
					}
				}
				else
				{
					Handle_GemCraft(Player);
					this.GemCool.SetOnCooldown(Player.getName());
					return;
				}
			}
		}
	}

	@EventHandler
	public void OnEntityExplode(EntityExplodeEvent Event)
	{
		Event.blockList().clear();
	}

	public void Handle_GemCraft(Player Player)
	{
		if (this.Gem_Handling.containsKey(Player.getName()))
		{
			GemCraft Player_Data = this.Gem_Handling.get(Player.getName());
			Material HandType = Player.getItemInHand().getType();
			if ((HandType != Material.WRITTEN_BOOK) && (HandType != Material.BOOK))
			{
				ItemStack GemStack = Player.getInventory().getItem(Player_Data.getGemSlot());
				if (Player_Data.isGem(GemStack))
				{
					ItemStack Hand = Player.getItemInHand();
					ItemMeta iMeta = Hand.getItemMeta();
					int PreviousPlus = 0;
					boolean HasEnhanced = false;
					if (iMeta.hasDisplayName())
					{
						if (iMeta.getDisplayName().contains("[+"))
						{
							HasEnhanced = true;
							String DataParse = DataHandler.getStringBetween(iMeta.getDisplayName(), "[+", "]");
							try
							{
								PreviousPlus = Integer.parseInt(DataParse);
							}
							catch (Exception Ex)
							{
								// Placeholder until foolproof
							}
							if ((Player_Data.isGem(Hand)) && (!Player_Data.isSameGem(Hand, Player.getInventory().getHeldItemSlot())))
							{
								if (PreviousPlus >= 3)
								{
									Player.sendMessage(ChatColor.RED + "You've already got 3 or more additions to this gem, you can't add more!");
									return;
								}
							}
							else
							{
								if (PreviousPlus >= 7)
								{
									Player.sendMessage(ChatColor.RED + "This item has reached it's maximum potentialal, it can't be enhanced further");
									return;
								}
							}
						}
					}
					for (Entry<Enchantment, Integer> e : GemStack.getEnchantments().entrySet())
					{
						try
						{
							iMeta.addEnchant(e.getKey(), e.getValue(), true);
						}
						catch (Exception Ex)
						{
							Ex.printStackTrace();
							Player.sendMessage(ChatColor.RED + "These items have conflicting enchantments and can't be combined");
							return;
						}
					}
					String ItemName = "";
					if (HasEnhanced == true)
					{
						ItemName = StringUtils.substringBefore(iMeta.getDisplayName(), " [+");
					}
					else
					{
						if (iMeta.hasDisplayName())
						{
							ItemName = iMeta.getDisplayName();
						}
						else
						{
							ItemName = WordUtils.capitalize(Hand.getType().toString());
						}
					}
					iMeta.setDisplayName(ItemName + ChatColor.YELLOW + " [+" + (PreviousPlus + 1) + "]");
					Hand.setItemMeta(iMeta);
					for (CustomEnchantment Enchant : EnchantmentAPI.getEnchantments(GemStack).keySet())
					{
						Enchant.addToItem(Hand, 1);
					}
					Player.setItemInHand(Hand);
					Player.getInventory().setItem(Player_Data.getGemSlot(), null);
					Player.sendMessage(ChatColor.GOLD + "You've successfully combined the items!");
					Player.updateInventory();
					this.Gem_Handling.remove(Player.getName());
				}
			}
		}
	}
}