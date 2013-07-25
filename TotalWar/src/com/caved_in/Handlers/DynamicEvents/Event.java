package com.caved_in.Handlers.DynamicEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.cybermaxke.materialapi.inventory.CustomItemStack;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.caved_in.TotalWar;
import com.caved_in.Handlers.DynamicEvents.EventNPC;
import com.caved_in.Handlers.EntityHandlers.BossHandler.BossType;
import com.caved_in.ItemMenus.RewardsMenu.RewardsMenu;
import com.caved_in.TotalWarItems.TotalWarItems;
import com.caved_in.TotalWarItems.Handlers.ItemStackHandler.Tier;

public class Event
{
	private int EventAmount = 0;
	private String BossName = "";
	private Material EventMaterial = null;
	private EntityType EventEntityType = null;
	private EventType _Type;
	// private Boss Boss;
	private BossType Boss;
	private EventNPC Event_NPC;
	private Material[] RewardMaterials = { Material.LOG, Material.WOOD, Material.ARROW, Material.REDSTONE, Material.GLOWSTONE_DUST, Material.IRON_INGOT, Material.LEATHER, Material.GOLD_NUGGET, Material.APPLE, Material.BAKED_POTATO, Material.BREAD, Material.CARROT, Material.COAL };
	private int MoneyMin = 25;
	private int MoneyMax = 150;

	public Event(EventType Event_Type, int AmountRequired)
	{
		this._Type = Event_Type;
		this.EventAmount = AmountRequired;
	}

	public Event()
	{
	}

	public Event(EventType Event_Type, int AmountRequired, Material Material)
	{
		this._Type = Event_Type;
		this.EventAmount = AmountRequired;
		this.EventMaterial = Material;
	}

	public Event(EventType Event_Type, BossType Boss)
	{
		this._Type = Event_Type;
		this.EventAmount = 1;
		this.Boss = Boss;
		// this.Boss = Boss;
	}

	public Event(EventType Event_Type, int AmountRequired, EntityType eType)
	{
		this._Type = Event_Type;
		this.EventAmount = AmountRequired;
		this.EventEntityType = eType;
	}

	public int getAmountRequired()
	{
		return this.EventAmount;
	}

	public void setAmount(int Amount)
	{
		this.EventAmount = Amount;
	}

	public EventType getType()
	{
		return this._Type;
	}

	public EntityType getEntityType()
	{
		return this.EventEntityType;
	}

	public void setEntityType(EntityType Type)
	{
		this.EventEntityType = Type;
	}

	public String getBossName()
	{
		return this.Boss.name();
	}

	public BossType getBossType()
	{
		return this.Boss;
	}

	public String getNpcName()
	{
		return this.Event_NPC.getName();
	}

	public EventNPC getEventNPC()
	{
		return this.Event_NPC;
	}

	public void setEventNPC(EventNPC NPC)
	{
		this.Event_NPC = NPC;
	}

	public List<CustomItemStack> generateStacks()
	{
		List<CustomItemStack> Stacks = new ArrayList<CustomItemStack>();

		if (PercentCheck(1.0D))
		{
			Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("Banger"));
		}

		if (PercentCheck(1.0D))
		{
			if (new Random().nextInt(2) == 1)
			{
				Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("Blade", Tier.Epic));
			}
			else
			{
				Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("Katana", Tier.Epic));
			}
		}
		else if (PercentCheck(25.0D))
		{
			if (new Random().nextInt(2) == 1)
			{
				Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("Blade", Tier.Rare));
			}
			else
			{
				Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("Katana", Tier.Rare));
			}
		}
		else
		{
			Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("Blade", Tier.Common));
			Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("Katana", Tier.Common));
		}

		if (PercentCheck(1.0D))
		{
			Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("Bow", Tier.Epic));
		}
		else if (PercentCheck(25.0D))
		{
			Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("Bow", Tier.Rare));
		}
		else
		{
			Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("Bow", Tier.Common));
		}

		if (PercentCheck(10D))
		{
			CustomItemStack GemStack = new CustomItemStack(Material.EMERALD);
			GemStack.setName(ChatColor.LIGHT_PURPLE + "Random Gem");
			GemStack.addLore(new String[] { "Choosing this reward will yield a random gem!" });
			Stacks.add(GemStack);
		}

		if (PercentCheck(20D))
		{
			Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("HorseArmor"));
		}

		if (PercentCheck(35D))
		{
			switch (new Random().nextInt(13))
			{
			case 0:
				Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("HealingI"));
				break;
			case 1:
				Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("FatigueI"));
				break;
			case 2:
				Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("LightningI"));
				break;
			case 3:
				Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("CombustI"));
				break;
			case 4:
				Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("FatigueII"));
				break;
			case 5:
				Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("FeedI"));
				break;
			case 6:
				Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("SpeedI"));
				break;
			case 7:
				Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("Blink"));
				break;
			case 8:
				Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("HealingII"));
				break;
			case 9:
				Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("SpeedII"));
				break;
			case 10:
				Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("PhoenixArrow"));
				break;
			case 11:
				Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("Vanish"));
				break;
			case 12:
				Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("BarrageI"));
				break;
			default:
				break;
			}
		}
		return Stacks;
	}

	public void GiveRewards(Player Player)
	{
		List<CustomItemStack> Stacks = generateStacks();
		new RewardsMenu(Player, Stacks);
		TotalWar.economy.depositPlayer(Player.getName(), this.MoneyMin + new Random().nextInt(this.MoneyMax));
		Player.sendMessage("You receive some money for completing the event!");
	}

	private boolean PercentCheck(double Chances)
	{
		if (new Random().nextInt(100) <= Chances)
		{
			return true;
		}
		return false;
	}

	public Material getEventMaterial()
	{
		return this.EventMaterial;
	}

	public void setEventMaterial(Material Material)
	{
		this.EventMaterial = Material;
	}

	public static enum EventType
	{
		KILL_MOB, BOSS_HUNT, BREAK_BLOCK, CRAFT, DELIVER;
	}
}