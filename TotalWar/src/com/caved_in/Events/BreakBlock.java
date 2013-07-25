package com.caved_in.Events;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.CoalType;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Coal;

import com.caved_in.TotalWar;
import com.caved_in.Cooldown.Cooldown;
import com.caved_in.Handlers.DynamicEvents.AreaEvent;

public class BreakBlock implements Listener
{
	// private Material[] BlocksEnumerate = { Material.COAL_ORE,
	// Material.LEAVES, Material.LOG, Material.WOOD, Material.FURNACE };
	private List<Material> Blocks = Arrays.asList(new Material[] { Material.COAL_ORE, Material.LEAVES, Material.LOG, Material.WOOD, Material.FURNACE });
	private int LEAVES_CHANCES = 2;
	private int COAL_CHANCE = 3;
	private int FURNACE_CHANCE = 5;
	private int LOG_CHANCE = 2;
	private int WOOD_CHANCE = 2;
	private Cooldown BlockeffectCooldown = new Cooldown(600);

	public BreakBlock(TotalWar Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
		/*
		 * for (Material Mat : this.BlocksEnumerate) { this.Blocks.add(Mat); }
		 */
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void OnBlockBreak(BlockBreakEvent Event)
	{
		if (Event.isCancelled())
		{
			return;
		}
		if (Event.getPlayer().getGameMode() != GameMode.CREATIVE)
		{
			if (this.Blocks.contains(Event.getBlock().getType()))
			{
				// CheckForEvents(Event.getBlock().getType(),
				// Event.getPlayer());
				switch (Event.getBlock().getType())
				{
				case FURNACE:
					HandleFurnace(Event.getBlock(), Event.getPlayer());
					break;
				case LEAVES:
					if (this.BlockeffectCooldown.IsOnCooldown(Event.getPlayer().getName()) == false)
					{
						HandleLeaves(Event.getBlock(), Event.getPlayer());
						this.BlockeffectCooldown.SetOnCooldown(Event.getPlayer().getName());
					}
					break;
				case LOG:
					HandleLog(Event.getBlock(), Event.getPlayer());
					break;
				case WOOD:
					HandleWood(Event.getBlock(), Event.getPlayer());
					break;
				default:
					break;
				}
			}
		}
	}

	private void CheckForEvents(Material Material, Player Player)
	{
		for (AreaEvent e : TotalWar.EventDynamics.getActiveEvents(AreaEvent.EventType.BREAK_BLOCK))
		{
			if (Material.equals(e.getEventMaterial()))
			{
				if ((!TotalWar.EventDynamics.hasBeenRewarded(e, Player.getName())) && (!TotalWar.EventDynamics.isPlayerCompleted(e, Player.getName())))
				{
					TotalWar.EventDynamics.addPlayerProgress(e, Player.getName());
					break;
				}
			}
		}
	}

	public void HandleCoal(Block Block, Player Player)
	{
		if (PercentCheck(this.COAL_CHANCE))
		{
			Block.getWorld().createExplosion(Block.getLocation(), 1.0F);
		}
		Coal Coal = new Coal(CoalType.CHARCOAL);
		ItemStack CharCoal = new ItemStack(Coal.toItemStack());
		CharCoal.setAmount(1);
		Block.getWorld().dropItem(Block.getLocation(), CharCoal);
	}

	public void HandleFurnace(Block Block, Player Player)
	{
		if (PercentCheck(this.FURNACE_CHANCE))
		{
			Player.setFireTicks(20);
			SendMessage(Player, "You burn your hand while removing the furnace!");
		}
	}

	public void HandleLog(Block Block, Player Player)
	{
		if (Player.getItemInHand() == null)
		{
			if (PercentCheck(this.LOG_CHANCE))
			{
				Player.damage(1);
				SendMessage(Player, "Ouch! You get a splinter from the bark!");
			}
		}
	}

	public void HandleWood(Block Block, Player Player)
	{
		if (Player.getItemInHand() == null)
		{
			if (PercentCheck(this.WOOD_CHANCE))
			{
				Player.damage(1);
				SendMessage(Player, "Ow! You get a splinter from the rough wood!");
			}
		}
	}

	public void HandleLeaves(Block Block, Player Player)
	{
		if (PercentCheck(this.LEAVES_CHANCES))
		{
			if (PercentCheck(50))
			{
				Knockback(Player, -1);
				Player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.STICK, 1) });
				Player.damage(1);
				SendMessage(Player, "A stick falls from the leaves and crushes your foot!");
			}
			else
			{
				Knockback(Player, -1);
				Player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.APPLE, 1) });
				Player.damage(2);
				SendMessage(Player, "An apple falls from the leaves and hits you!");
			}
		}
	}

	public void Knockback(Player Player, int NegativeAmount)
	{
		Player.setVelocity(Player.getLocation().getDirection().multiply(NegativeAmount));
	}

	public void SendMessage(Player Player, String Message)
	{
		Player.sendMessage(ChatColor.RED + Message);
	}

	private boolean PercentCheck(int Chances)
	{
		if (new Random().nextInt(100) <= Chances)
		{
			return true;
		}
		return false;
	}
}