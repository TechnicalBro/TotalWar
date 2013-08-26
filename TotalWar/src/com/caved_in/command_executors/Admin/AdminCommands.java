package com.caved_in.command_executors.Admin;

import java.io.File;
import java.util.Random;
import java.util.Map.Entry;

import me.cybermaxke.materialapi.inventory.CustomItemStack;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import TunnelsSQL.TunnelsSQL;

import com.caved_in.TotalWar;
import com.caved_in.Config.YML.PluginConfig;
import com.caved_in.Handlers.HelpMenu.HelpScreen;
import com.caved_in.Handlers.ItemHandlers.ItemHandler;
import com.caved_in.Items.ArcaneGem;
import com.caved_in.Items.ArmorGem;
import com.caved_in.Items.Gem;
import com.caved_in.Items.GemPackGem;
import com.caved_in.Items.GemPlus;
import com.caved_in.TotalWarItems.TotalWarItems;
import com.caved_in.TotalWarItems.Handlers.Items.ItemStackHandler.Tier;
import com.caved_in.command_executors.CommandController.CommandHandler;
import com.caved_in.command_executors.CommandController.SubCommandHandler;
import com.rit.sucy.EnchantmentAPI;

public class AdminCommands
{
	private TotalWar Plugin;
	public AdminCommands(TotalWar Plugin)
	{
		this.Plugin = Plugin;
	}
	
	@CommandHandler(name = "setwaypoint", description = "Command used to set waypoints for Travel NPC's", permission = "totalwar.admin", usage = "/setwaypoint <Travel NPC Name> <What the waypoint will be called on its menu>")
	public void SetWayPoint(Player Player, String[] Args)
	{
		if (Args.length >= 2)
		{
			String NPC = Args[0];
			String WarpName = "";
			for (int I = 1; I < Args.length; I++)
			{
				WarpName += Args[I] + "_";
			}
			String World = Player.getWorld().getName();
			int X = (int) Player.getLocation().getX();
			int Y = (int) Player.getLocation().getY();
			int Z = (int) Player.getLocation().getZ();
			if (TotalWar.Waypoints.WriteNPCData(NPC, WarpName, new int[] { X, Y, Z }, World) == true)
			{
				Player.sendMessage(ChatColor.GOLD + "You've created the waypoint '" + WarpName + "' for the NPC: " + NPC);
			}
			else
			{
				Player.sendMessage(ChatColor.RED + "Error saving waypoint, check console for error");
			}
		}
	}
	
	@CommandHandler(name = "totalwar", description = "Main call for all admin commands in TotalWar", permission = "totalwar.admin")
	public void TotalWarCommand(CommandSender Sender, String[] Args)
	{
		Sender.sendMessage(ChatColor.YELLOW + "For help with the /totalwar command do '/totalwar help");
	}
	
	@SubCommandHandler(parent = "totalwar", name = "help", permission = "totalwar.admin")
	public void TotalWarHelpCommand(CommandSender Sender, String[] Args)
	{
		HelpScreen HelpScreen = new HelpScreen("TotalWar Admin Menu");
		HelpScreen.setHeader(ChatColor.BLUE + "<name> Page <page> of <maxpage>");
		HelpScreen.setFormat("<name> --> <desc>");
		HelpScreen.setFlipColor(ChatColor.GREEN, ChatColor.DARK_GREEN);
		
		HelpScreen.setEntry("/totalwar", "Shows this menu of commands");
		HelpScreen.setEntry("/totalwar reload", "Reloads the plugin config");
		HelpScreen.setEntry("/totalwar loadtraits", "Loads the cached NPC Trait data for TotalWars custom NPC Traits");
		HelpScreen.setEntry("/totalwar newevents", "Replaces all the currently active events from event-npcs with new events; CAUTION: WILL CRASH SERVER");
		HelpScreen.setEntry("/totalwar makedonator <PlayerName>", "Sets a user to premium status");
		HelpScreen.setEntry("/totalwar <lavagem/magegem/gempack/armorpack/itempack/votepack> <PlayerName>", "Gives the player Corresponding items and dispatches the message saying they purchased it");
		if (Args.length == 1)
		{
			HelpScreen.sendTo(Sender, 1, 5);
		}
		else
		{
			if (Args[1] != null && StringUtils.isNumeric(Args[1]))
			{
				int Page = Integer.parseInt(Args[1]);
				HelpScreen.sendTo(Sender, Page, 5);
			}
		}
	}
	
	@SubCommandHandler(parent = "totalwar", name = "reload", permission = "totalwar.admin.reload")
	public void ReloadConfig(CommandSender Sender, String[] Args)
	{
		TotalWar.Plugin_Config = new PluginConfig(this.Plugin.getDataFolder() + File.separator + "Config.yml");
		Sender.sendMessage("Configuration Reloaded.");
	}
	
	@SubCommandHandler(parent = "totalwar", name = "loadtraits", permission = "totalwar.admin.loadtraits")
	public void LoadNPCTraits(CommandSender Sender, String[] Args)
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
		Sender.sendMessage("All traits have been (re)loaded.");
	}
	
	@SubCommandHandler(parent = "totalwar", name = "newevents", permission = "totalwar.admin.newevents")
	public void NewDynamicEvents(CommandSender Sender, String[] Args)
	{
		TotalWar.EventDynamics.ReplaceAllEvents();
		Sender.sendMessage("All events replaced");
	}
	
	@SubCommandHandler(parent = "totalwar", name = "makedonator", permission = "totalwar.admin.makedonator")
	public void MakeDonator(CommandSender Sender, String[] Args)
	{
		if (!Args[1].isEmpty())
		{
			Player Player = Bukkit.getPlayer(Args[1]);
			if (Player != null && Player.isOnline())
			{
				TunnelsSQL.PlayerSQL.setDonator(Args[1]);
				TotalWar.permission.playerAdd(Player, "TotalWar.Donator");
				TotalWar.permission.playerAdd(Player, "slot.reserve");
				TotalWar.permission.playerAdd(Player, "idc.keepallitems");
				TotalWar.permission.playerAdd(Player, "essentials.nick");
				TotalWar.permission.playerAdd(Player, "essentials.nick.color");
				TotalWar.permission.playerAdd(Player, "essentials.nick.format");
				Player.sendMessage(ChatColor.LIGHT_PURPLE + "Thanks for Donating <3333");
				Sender.sendMessage("Added : " + Player.getName() + " to donators list!");
			}
			else
			{
				Sender.sendMessage(ChatColor.RED + "The player must be online to promote them");
			}
		}
		else
		{
			Sender.sendMessage(ChatColor.RED + "You need to include a player to promote.");
		}
	}
	
	@SubCommandHandler(parent = "totalwar", name = "skylands", permission = "totalwar.admin.skylands")
	public void SkylandsTeleport(CommandSender Sender, String[] Args)
	{
		if (!Args[1].isEmpty())
		{
			final String Name = Args[1];
			Bukkit.getScheduler().runTaskLater(this.Plugin, new Runnable()
			{

				@Override
				public void run()
				{
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mv tp " + Name + " skylands");
				}

			}, 120L);
		}
		else
		{
			Sender.sendMessage("You need to include a players name");
		}
	}
	
	@SubCommandHandler(parent = "totalwar", name = "lavagem", permission = "totalwar.admin.lavagem")
	public void LavagemPurchase(CommandSender Sender, String[] Args)
	{
		if (!Args[1].isEmpty())
		{
			if (Bukkit.getPlayer(Args[1]) != null)
			{
				Player Player = Bukkit.getPlayer(Args[1]);
				ItemStack Gem = ItemHandler.makeItemStack(Material.EMERALD, ChatColor.YELLOW + "Embued gem of Molten Lava");
				EnchantmentAPI.getEnchantment("Lava Scorch").addToItem(Gem, 1);
				Player.getInventory().addItem(Gem);
				Player.getInventory().addItem(Gem);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "say " + Player.getName() + " just bought 2" + ChatColor.YELLOW + " Embued Lava Scorch Gems" + ChatColor.LIGHT_PURPLE + " to make their armor look bad-ass!");
				Player.updateInventory();
			}
		}
	}
	
	@SubCommandHandler(parent = "totalwar", name = "magegem", permission = "totalwar.admin.magegem")
	public void MagegemPurchase(CommandSender Sender, String[] Args)
	{
		if (!Args[1].isEmpty())
		{
			if (Bukkit.getPlayer(Args[1]) != null)
			{
				Player Player = Bukkit.getPlayer(Args[1]);
				ItemStack Gem = ItemHandler.makeItemStack(Material.EMERALD, ChatColor.YELLOW + "Embued gem of Mana Regen");
				EnchantmentAPI.getEnchantment("Arcane Enhancement").addToItem(Gem, 1);
				Player.getInventory().addItem(Gem);
				Player.getInventory().addItem(Gem);
				Player.updateInventory();
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "say " + Player.getName() + " just bought 2" + ChatColor.YELLOW + " Embued Arcane Enhancement Gems" + ChatColor.LIGHT_PURPLE + " to increase their mana regen!");
			}
		}
	}
	
	@SubCommandHandler(parent = "totalwar", name = "itempack", permission = "totalwar.admin.itempack")
	public void ItempackPurchase(CommandSender Sender, String[] Args)
	{
		if (!Args[1].isEmpty())
		{
			String PlayerName = Args[1];
			Player Player = Bukkit.getPlayer(PlayerName);
			if (Player != null)
			{
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
			}
			else
			{
				Sender.sendMessage("The player " + Args[1] + " doesn't seem to be online... Or exist for that matter");
			}
		}
		else
		{
			Sender.sendMessage("You need to include a player name");
		}
	}

	@SubCommandHandler(parent = "totalwar", name = "armorpack", permission = "totalwar.admin.armorpack")
	public void ArmorpackPurchase(CommandSender Sender, String[] Args)
	{
		if (Args[1].isEmpty() == false)
		{
			String PlayerName = Args[1];
			Player Player = Bukkit.getPlayer(PlayerName);
			if (Player != null)
			{
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
			}
			else
			{
				Sender.sendMessage("The player " + Args[1] + " doesn't seem to be online... Or exist for that matter");
			}
		}
		else
		{
			Sender.sendMessage("Include a player name");
		}
	}

	@SubCommandHandler(parent = "totalwar", name = "gempack", permission = "totalwar.admin.gempack")
	public void GempackPurchase(CommandSender Sender, String[] Args)
	{
		if (!Args[1].isEmpty())
		{
			String PlayerName = Args[1];
			Player Player = Bukkit.getPlayer(PlayerName);
			if (Player != null)
			{
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
				Sender.sendMessage("Player either doesn't exist, or is offline.");
			}
		}
		else
		{
			Sender.sendMessage("Include a player name");
		}
	}
	
	@SubCommandHandler(parent = "totalwar", name = "votepack", permission = "totalwar.admin.votepack")
	public void VotepackGive(CommandSender Sender, String[] Args)
	{
		if (!Args[1].isEmpty())
		{
			String PlayerName = Args[1];
			Player Player = Bukkit.getPlayer(PlayerName);
			if (Player != null)
			{
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
			}
			else
			{
				Sender.sendMessage("Player either doesn't exist, or is offline.");
			}
		}
		else
		{
			Sender.sendMessage("Include a name, please.");
		}
	}


}
