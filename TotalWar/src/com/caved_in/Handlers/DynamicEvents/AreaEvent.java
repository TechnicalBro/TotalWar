package com.caved_in.Handlers.DynamicEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.cybermaxke.materialapi.inventory.CustomItemStack;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.caved_in.TotalWar;
import com.caved_in.Handlers.EntityHandlers.BossHandler.BossType;
import com.caved_in.Handlers.Misc.MiscUtils;
import com.caved_in.ItemMenus.RewardsMenu.RewardsMenu;
import com.caved_in.Items.ArcaneGem;
import com.caved_in.TotalWarItems.TotalWarItems;
import com.caved_in.TotalWarItems.Handlers.Items.ItemStackHandler.Tier;

public class AreaEvent
{
	private int EventAmount = 0;
	private String BossName = "";
	private Material EventMaterial = null;
	private EntityType EventEntityType = null;
	private EventType _Type;
	// private Boss Boss;
	private BossType Boss;
	// private EventNPC Event_NPC;
	private int NPCID = -1;
	private String RequestText = "";
	private String FinishText = "";
	private Material[] RewardMaterials = { Material.LOG, Material.WOOD, Material.ARROW, Material.REDSTONE, Material.GLOWSTONE_DUST, Material.IRON_INGOT, Material.LEATHER, Material.GOLD_NUGGET, Material.APPLE, Material.BAKED_POTATO, Material.BREAD, Material.CARROT, Material.COAL };
	private int MoneyMin = 25;
	private int MoneyMax = 150;

	// private int

	public AreaEvent(EventType Event_Type, int AmountRequired)
	{
		this._Type = Event_Type;
		this.EventAmount = AmountRequired;
	}

	public AreaEvent()
	{
	}

	/**
	 * Initializes an AreaEvent with the EventType, an amount required and the
	 * material for that event
	 * 
	 * @param Event_Type
	 *            Event Type
	 * @param AmountRequired
	 *            The amount required
	 * @param Material
	 *            The material required
	 */
	public AreaEvent(EventType Event_Type, int AmountRequired, Material Material)
	{
		this._Type = Event_Type;
		this.EventAmount = AmountRequired;
		this.EventMaterial = Material;
	}

	/**
	 * Creates a new instance of an AreaEvent with the EventType, and a boss
	 * 
	 * @param Event_Type
	 * @param Boss
	 */
	public AreaEvent(EventType Event_Type, BossType Boss)
	{
		this._Type = Event_Type;
		this.EventAmount = 1;
		this.Boss = Boss;
		// this.Boss = Boss;
	}

	public AreaEvent(EventType Event_Type, int AmountRequired, EntityType eType)
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
		// return this.Event_NPC.getName();
		return CitizensAPI.getNPCRegistry().getById(this.NPCID).getName();
	}

	public NPC getEventNPC()
	{
		return CitizensAPI.getNPCRegistry().getById(this.NPCID);
	}

	public int getEventNPCID()
	{
		return this.NPCID;
	}

	public void setEventNPC(NPC NPC)
	{
		this.NPCID = NPC.getId();
	}

	public void setEventNPC(int ID)
	{
		this.NPCID = ID;
	}

	public List<CustomItemStack> generateStacks()
	{
		List<CustomItemStack> Stacks = new ArrayList<CustomItemStack>();

		if (MiscUtils.PercentCheck(3.0D))
		{
			Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("Banger"));
		}

		if (MiscUtils.PercentCheck(8.0D))
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
		else if (MiscUtils.PercentCheck(50.0D))
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

		if (MiscUtils.PercentCheck(3.0D))
		{
			Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("DragonBow"));
		}
		if (MiscUtils.PercentCheck(8.0D))
		{
			Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("Bow", Tier.Epic));
		}
		else if (MiscUtils.PercentCheck(50.0D))
		{
			Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("Bow", Tier.Rare));
		}
		else
		{
			Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("Bow", Tier.Common));
		}

		if (MiscUtils.PercentCheck(40D))
		{
			CustomItemStack GemStack = new CustomItemStack(Material.EMERALD);
			GemStack.setName(ChatColor.LIGHT_PURPLE + "Random Gem");
			GemStack.addLore(new String[] { "Choosing this reward will yield a random gem!" });
			Stacks.add(GemStack);
		}

		if (MiscUtils.PercentCheck(40D))
		{
			CustomItemStack Arcane = new CustomItemStack(new ArcaneGem().getItem());
			Arcane.addLore(new String[] { "Increases your Mana Regen" });
			Stacks.add(Arcane);
		}

		if (MiscUtils.PercentCheck(40D))
		{
			Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("HorseArmor"));
		}

		if (MiscUtils.PercentCheck(60D))
		{
			switch (new Random().nextInt(16))
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
			case 13:
				Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("Healthbubble"));
				break;
			case 14:
				Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("ProtAura"));
				break;
			case 15:
				Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("Chickenfire"));
				break;
			default:
				break;
			}

			if (MiscUtils.PercentCheck(35D))
			{
				switch (new Random().nextInt(4))
				{
				case 0:
					Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("IronPants"));
					break;
				case 1:
					Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("IronChest"));
					break;
				case 2:
					Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("IronHelmet"));
					break;
				case 3:
					Stacks.add(TotalWarItems.ItemHandler.getCustomItemStack("IronBoots"));
					break;
				default:
					break;
				}
			}
		}
		return Stacks;
	}

	public void GiveRewards(Player Player)
	{
		List<CustomItemStack> Stacks = generateStacks();
		new RewardsMenu(Player, Stacks);
		TotalWar.economy.depositPlayer(Player.getName(), this.MoneyMin + new Random().nextInt(this.MoneyMax));
	}

	public Material getEventMaterial()
	{
		return this.EventMaterial;
	}

	public void setEventMaterial(Material Material)
	{
		this.EventMaterial = Material;
	}

	public String getFinishText()
	{
		return this.FinishText;
	}

	public String getRequestText()
	{
		return this.RequestText;
	}

	public void setRequestText(String Text)
	{
		this.RequestText = Text;
	}

	public Location getNPCLocation()
	{
		return CitizensAPI.getNPCRegistry().getById(this.NPCID).getBukkitEntity().getLocation();
	}

	public static enum EventType
	{
		KILL_MOB, BOSS_HUNT, BREAK_BLOCK, CRAFT, DELIVER;
	}
}