package com.caved_in.command_executors;

import com.caved_in.TotalWarItems.TotalWarItems;
import com.caved_in.TotalWarItems.Handlers.ItemStackHandler.Tier;
import com.rit.sucy.EnchantmentAPI;

import java.io.File;
import java.util.Map.Entry;
import java.util.Random;

import com.caved_in.TotalWar;
import com.caved_in.Config.YML.MobDroprateConfig;
import com.caved_in.Config.YML.PluginConfig;
import com.caved_in.Handlers.PlayerHandlers.PlayerHandler.Faction;
import com.caved_in.Items.ArcaneGem;
import com.caved_in.Items.ArmorGem;
import com.caved_in.Items.Gem;
import com.caved_in.Items.GemPackGem;
import com.caved_in.Items.GemPlus;

import me.cybermaxke.materialapi.inventory.CustomItemStack;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ToggleWarCommandExecutor implements CommandExecutor
{
	private TotalWar Plugin;
	private World TotalWarWorld;

	public ToggleWarCommandExecutor(TotalWar Plugin)
	{
		this.Plugin = Plugin;
		this.TotalWarWorld = Plugin.getServer().getWorld("PaganTemplar");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		// TODO Make a command to generate / regen events
		if (args.length > 0)
		{
			String Subcommand = args[0];
			switch (Subcommand)
			{
			case "reload":
				{
					if (sender.isOp())
					{
						TotalWar.Mob_Drops = new MobDroprateConfig(this.Plugin.getDataFolder() + File.separator + "Mobdrops.yml");
						TotalWar.Plugin_Config = new PluginConfig(this.Plugin.getDataFolder() + File.separator + "Config.yml");
						return true;
					}
					else
					{
						sender.sendMessage("You don't have permission to do this, sorry");
						return false;
					}
				}
			case "loadtraits":
			{
				if (sender.isOp())
				{
					for(Entry<Integer, String> Pair : TotalWar.NpcTraitConfig.getData().entrySet())
					{
						NPC Npc = CitizensAPI.getNPCRegistry().getById(Pair.getKey());
						if (Npc != null)
						{
							try
							{
								Trait Trait = CitizensAPI.getTraitFactory().getTrait(Pair.getValue());
								if (!Npc.hasTrait(Trait.getClass()))
								{
									Npc.addTrait(Trait);
									TotalWar.Console("Loaded the cached trait " + Trait.getName() + " for " + Npc.getName());
								}
							}
							catch (Exception Ex)
							{
								Ex.printStackTrace();
							}
						}
					}
					return true;
				}
				return false;
			}
			case "newevents":
				{
					if (sender.isOp())
					{
						TotalWar.EventDynamics.ReplaceAllEvents();
						TotalWar.Console("--- ALL EVENTS RELOADED! ---");
						return true;
					}
					return false;
				}
			case "setjail":
				{
					if (sender instanceof Player)
					{
						Player Player = (Player) sender;
						if (Player.isOp())
						{
							Faction Faction = TotalWar.PlayerHandler.getPlayerFaction(Player);
							Location PlayerLoc = Player.getLocation();
							TotalWar.Jail_Config.setJail(PlayerLoc.getX(), PlayerLoc.getY(), PlayerLoc.getZ(), Faction);
							TotalWar.Console("Set the jail location of " + args[1] + " to : " + PlayerLoc.getX() + "," + PlayerLoc.getY() + "," + PlayerLoc.getZ());
							Player.sendMessage("Jail location for " + args[1] + " SET!");
							return true;
						}
					}
					return false;
				}
			case "makedonator":
				{
					if (sender.isOp())
					{
						if (!args[1].isEmpty())
						{
							Player Player = Bukkit.getPlayer(args[1]);
							if (Player != null && Player.isOnline())
							{
								TotalWar.permission.playerAdd(Player, "TotalWar.Donator");
								TotalWar.permission.playerAdd(Player, "slot.reserve");
								TotalWar.permission.playerAdd(Player, "idc.keepallitems");
								TotalWar.permission.playerAdd(Player, "essentials.nick");
								TotalWar.permission.playerAdd(Player, "essentials.nick.color");
								TotalWar.permission.playerAdd(Player, "essentials.nick.format");
								Player.sendMessage(ChatColor.LIGHT_PURPLE + "Thanks for Donating <3333");
								sender.sendMessage("Added : " + Player.getName() + " to donators list!");

								return true;
							}
							else
							{
								sender.sendMessage(ChatColor.RED + "The player must be online to promote them");
								return false;
							}
						}
						else
						{
							sender.sendMessage(ChatColor.RED + "You need to include a player to promote.");
							return false;
						}
					}
					else
					{
						sender.sendMessage("Sorry, you don't have permission to use that.");
						return false;
					}
				}
			case "skylands":
				{
					if (sender.isOp())
					{
						if (!args[1].isEmpty())
						{
							final String Name = args[1];
							Bukkit.getScheduler().runTaskLater(this.Plugin, new Runnable()
							{

								@Override
								public void run()
								{
									Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mv tp " + Name + " skylands");
								}

							}, 120L);
							return true;
						}
						else
						{
							sender.sendMessage("You need to include a players name");
							return false;
						}
					}
					else
					{
						sender.sendMessage("You don't have the permissions required to use '/totalwar skylands <player', sorry!");
						return false;
					}
				}
			case "lavagem":
				{
					if (sender.isOp())
					{
						if (!args[1].isEmpty())
						{
							if (Bukkit.getPlayer(args[1]) != null)
							{
								Player Player = Bukkit.getPlayer(args[1]);
								ItemStack Gem = new ItemStack(Material.EMERALD);
								ItemMeta iMeta = Gem.getItemMeta();
								iMeta.setDisplayName(ChatColor.YELLOW + "Embued gem of Molten Lava");
								Gem.setItemMeta(iMeta);
								EnchantmentAPI.getEnchantment("Lava Scorch").addToItem(Gem, 1);
								Player.getInventory().addItem(Gem);
								Player.getInventory().addItem(Gem);
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "say " + Player.getName() + " just bought 2" + ChatColor.YELLOW + " Embued Lava Scorch Gems" + ChatColor.LIGHT_PURPLE + " to make their armor look bad-ass!");
								Player.updateInventory();
								return true;
							}
						}
					}
					return false;
				}
			case "magegem":
				{
					if (sender.isOp())
					{
						if (!args[1].isEmpty())
						{
							if (Bukkit.getPlayer(args[1]) != null)
							{
								Player Player = Bukkit.getPlayer(args[1]);
								ItemStack Gem = new ItemStack(Material.EMERALD);
								ItemMeta iMeta = Gem.getItemMeta();
								iMeta.setDisplayName(ChatColor.YELLOW + "Embued gem of Mana Regen");
								Gem.setItemMeta(iMeta);
								EnchantmentAPI.getEnchantment("Arcane Enhancement").addToItem(Gem, 1);
								Player.getInventory().addItem(Gem);
								Player.getInventory().addItem(Gem);
								Player.updateInventory();
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "say " + Player.getName() + " just bought 2" + ChatColor.YELLOW + " Embued Arcane Enhancement Gems" + ChatColor.LIGHT_PURPLE + " to increase their mana regen!");
								return true;
							}
						}
					}
					return false;
				}
			case "itempack":
				{
					if (sender.isOp())
					{
						if (!args[1].isEmpty())
						{
							String PlayerName = args[1];
							Player Player = Bukkit.getPlayer(PlayerName);
							CustomItemStack GemOne = new CustomItemStack(new GemPlus("", Material.EMERALD));
							CustomItemStack GemTwo;

							if (new Random().nextInt(11) <= 4)
							{
								GemTwo = new CustomItemStack(new GemPlus("", Material.EMERALD));
							}
							else
							{
								GemTwo = new CustomItemStack(new Gem("", Material.EMERALD));
							}
							CustomItemStack BladeOne = TotalWarItems.ItemHandler.getCustomItemStack("CrateBlade", Tier.Epic);
							CustomItemStack BladeTwo;
							if (new Random().nextInt(20) >= 15)
							{
								BladeTwo = TotalWarItems.ItemHandler.getCustomItemStack("CrateBlade", Tier.Epic);
							}
							else
							{
								BladeTwo = TotalWarItems.ItemHandler.getCustomItemStack("Blade", Tier.Epic);
							}

							CustomItemStack BowOne = TotalWarItems.ItemHandler.getCustomItemStack("CrateBow", Tier.Epic);
							CustomItemStack BowTwo;
							if (new Random().nextInt(20) >= 15)
							{
								BowTwo = TotalWarItems.ItemHandler.getCustomItemStack("CrateBow", Tier.Epic);
							}
							else
							{
								BowTwo = TotalWarItems.ItemHandler.getCustomItemStack("Bow", Tier.Epic);
							}
							Player.getInventory().addItem(GemOne.getItem());
							Player.getInventory().addItem(GemTwo.getItem());
							Player.getInventory().addItem(BladeOne.getItem());
							Player.getInventory().addItem(BladeTwo.getItem());
							Player.getInventory().addItem(BowOne.getItem());
							Player.getInventory().addItem(BowTwo.getItem());
							Player.updateInventory();
							TotalWar.Console("Gave " + Player.getName() + " a weapon pack!");
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "say " + Player.getName() + " just bought an itemcrate, and got some kickass weapons!");
							Player.sendMessage(ChatColor.GREEN + "Big Brother - " + ChatColor.RED + "Enjoy your Item Crate, " + Player.getName() + " <3");
							return true;
						}
						else
						{
							sender.sendMessage("You need to include a player name");
							return false;
						}
					}
					else
					{
						sender.sendMessage("Sorry, you don't have permissions to use '/totalwar itempack <player'");
						return false;
					}
				}
			case "armorpack":
				{
					if (sender.isOp())
					{
						if (args[1].isEmpty() == false)
						{
							String PlayerName = args[1];
							Player Player = Bukkit.getPlayer(PlayerName);
							Player.getInventory().addItem(TotalWarItems.ItemHandler.getArmor("DiamondHelmet").getItem());
							Player.getInventory().addItem(TotalWarItems.ItemHandler.getArmor("DiamondPants").getItem());
							Player.getInventory().addItem(TotalWarItems.ItemHandler.getArmor("DiamondChest").getItem());
							Player.getInventory().addItem(TotalWarItems.ItemHandler.getArmor("DiamondBoots").getItem());
							Player.getInventory().addItem(new CustomItemStack(new ArmorGem("", Material.EMERALD)).getItem());
							Player.getInventory().addItem(new CustomItemStack(new ArmorGem("", Material.EMERALD)).getItem());
							Player.getInventory().addItem(new CustomItemStack(new ArmorGem("", Material.EMERALD)).getItem());
							Player.updateInventory();
							TotalWar.Console("Gave " + Player.getName() + " a armor pack!");
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "say " + Player.getName() + " just bought an armor crate, and got some EPIC armor!");
							Player.sendMessage(ChatColor.GREEN + "Big Brother - " + ChatColor.RED + "Enjoy your Armor Crate, " + Player.getName() + " <3");
							return true;
						}
						else
						{
							sender.sendMessage("Include a player name");
							return false;
						}
					}
					else
					{
						sender.sendMessage("You don't have permission to do this..");
						return false;
					}
				}
			case "gempack":
				{
					if (sender.isOp())
					{
						if (!args[1].isEmpty())
						{
							String PlayerName = args[1];
							Player Player = Bukkit.getPlayer(PlayerName);
							Player.getInventory().addItem(new CustomItemStack(new GemPackGem("", Material.EMERALD)).getItem());
							Player.getInventory().addItem(new CustomItemStack(new ArmorGem("", Material.EMERALD)).getItem());
							Player.getInventory().addItem(new CustomItemStack(new GemPackGem("", Material.EMERALD)).getItem());
							Player.getInventory().addItem(new CustomItemStack(new ArmorGem("", Material.EMERALD)).getItem());
							Player.getInventory().addItem(new CustomItemStack(new GemPackGem("", Material.EMERALD)).getItem());
							if (new Random().nextInt(2) == 0)
							{
								Player.getInventory().addItem(new ArcaneGem().getItem());
							}
							else
							{
								Player.getInventory().addItem(new CustomItemStack(new ArmorGem("", Material.EMERALD)).getItem());
							}
							Player.updateInventory();
							TotalWar.Console("Gave " + Player.getName() + " a gem pack!");
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "say " + Player.getName() + " just bought a gem crate, and got some AMAZING gems!");
							Player.sendMessage(ChatColor.GREEN + "Big Brother - " + ChatColor.RED + "Enjoy your Gem Crate, " + Player.getName() + " <3");
						}
						else
						{
							sender.sendMessage("Include a player name");
							return false;
						}
					}
					else
					{
						sender.sendMessage("You don't have permissions, sorry.");
						return false;
					}
					break;
				}
			case "votepack":
				{
					if (sender.isOp())
					{
						if (args[1].isEmpty() == false)
						{
							String PlayerName = args[1];
							Player Player = Bukkit.getPlayer(PlayerName);
							CustomItemStack VoteItem;
							int VoteInt = new Random().nextInt(4);
							switch (VoteInt)
							{
							case 0:
								if (new Random().nextInt(10) <= 4)
								{
									VoteItem = TotalWarItems.ItemHandler.getCustomItemStack("Blade", Tier.Epic);
								}
								else
								{
									VoteItem = TotalWarItems.ItemHandler.getCustomItemStack("Blade", Tier.Rare);
								}
								break;
							case 1:
								if (new Random().nextInt(10) <= 4)
								{
									VoteItem = TotalWarItems.ItemHandler.getCustomItemStack("Bow", Tier.Epic);
								}
								else
								{
									VoteItem = TotalWarItems.ItemHandler.getCustomItemStack("Bow", Tier.Rare);
								}
								break;
							case 2: // Random Gem (Non Itempack)
								VoteItem = new CustomItemStack(new Gem("", Material.EMERALD));
								break;
							case 3: // Random Katana < Rare
								if (new Random().nextInt(10) <= 4)
								{
									VoteItem = TotalWarItems.ItemHandler.getCustomItemStack("Katana", Tier.Epic);
								}
								else
								{
									VoteItem = TotalWarItems.ItemHandler.getCustomItemStack("Katana", Tier.Rare);
								}
								break;
							default:
								VoteItem = TotalWarItems.ItemHandler.getCustomItemStack("Blade", Tier.Rare);
								break;
							}
							Player.getInventory().addItem(VoteItem.getItem());
							Player.updateInventory();
							TotalWar.Console("Gave " + Player.getName() + " a Vote Item!");
							Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "say " + Player.getName() + " just voted for TotalWar, and got a " + VoteItem.getName() + ChatColor.LIGHT_PURPLE + "!");
							Player.sendMessage(ChatColor.GREEN + "Big Brother - " + ChatColor.RED + "Enjoy your Vote Reward, " + Player.getName() + " <3");
							return true;
						}
						else
						{
							sender.sendMessage("Include a name, please.");
							return false;
						}
					}
					else
					{
						sender.sendMessage("You don't have permissions");
						return false;
					}
				}
			case "motd":
				{
					if (sender.isOp())
					{
						TotalWar.WarTimeMOTD = !TotalWar.WarTimeMOTD;
						sender.sendMessage("MOTD toggled to the one in the file!");
						return true;
					}
					return false;
				}
			default:
				break;
			}
		}
		return false;
	}

	private void BroadCast(String Message)
	{
		for (Player P : this.Plugin.getServer().getOnlinePlayers())
		{
			P.sendMessage(ChatColor.translateAlternateColorCodes('&', Message));
		}
	}
}