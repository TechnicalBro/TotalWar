package com.caved_in.Handlers.DynamicEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import com.caved_in.TotalWar;
import com.caved_in.CitizenTraits.AreaEventNPCTrait;
import com.caved_in.CitizenTraits.EventNPCTrait;
import com.caved_in.Handlers.EntityHandlers.BossHandler.BossType;

public class EventGenerator
{
	private EntityType[] Types = { EntityType.BAT, EntityType.BLAZE, EntityType.SKELETON, EntityType.SPIDER, EntityType.CREEPER, EntityType.ZOMBIE, EntityType.SQUID, EntityType.CAVE_SPIDER, EntityType.ENDERMAN, EntityType.GIANT, EntityType.WITCH, EntityType.GHAST, EntityType.IRON_GOLEM, EntityType.MAGMA_CUBE, EntityType.MUSHROOM_COW, EntityType.OCELOT, EntityType.SILVERFISH, EntityType.SNOWMAN, EntityType.WOLF, EntityType.PIG_ZOMBIE };
	private Material[] Materials = { Material.STONE, Material.COBBLESTONE, Material.SANDSTONE, Material.OBSIDIAN, Material.COAL_ORE, Material.CACTUS, Material.LOG, Material.WOOD, Material.MELON, Material.CAKE, Material.BRICK, Material.LAPIS_BLOCK, Material.IRON_ORE, Material.DISPENSER, Material.JACK_O_LANTERN, Material.PUMPKIN };
	private Material[] Craftables_Stackable = { Material.ARROW, Material.CLAY_BRICK, Material.COBBLESTONE_STAIRS, Material.NETHER_BRICK_STAIRS, Material.SMOOTH_BRICK, Material.SMOOTH_STAIRS, Material.IRON_INGOT, Material.GOLD_INGOT, Material.WOOD_STAIRS, Material.FURNACE, Material.CHEST, Material.WOOD_BUTTON, Material.TORCH, Material.GLASS, Material.GLOWSTONE };
	private int Stackable_Maximum = 64;
	private List<AreaEventNPC> EventNPCs = new ArrayList<AreaEventNPC>();

	public AreaEvent generateEvent()
	{
		Random Random = new Random();
		AreaEvent Event;
		switch (Random.nextInt(4))
		{
		case 0:
			Event = new AreaEvent(AreaEvent.EventType.KILL_MOB, new Random().nextInt(50) + new Random().nextInt(16));
			Event.setEntityType(generateEntityType());
			break;
		case 1:
			Event = new AreaEvent(AreaEvent.EventType.BOSS_HUNT, generateBoss());
			break;
		case 2:
			Event = new AreaEvent(AreaEvent.EventType.BREAK_BLOCK, 1 + new Random().nextInt(120) + new Random().nextInt(20), generateMaterial());
			break;
		case 3:
			Event = new AreaEvent(AreaEvent.EventType.CRAFT, this.generateCraftAmount(), this.generateCraft());
			break;
		default:
			Event = new AreaEvent(AreaEvent.EventType.KILL_MOB, new Random().nextInt(50) + new Random().nextInt(15));
			Event.setEntityType(generateEntityType());
			break;
		}

		Event.setEventNPC(generateNpc());
		return Event;
	}

	public AreaEvent generateEvent(int ID)
	{
		Random Random = new Random();
		AreaEvent Event;
		switch (Random.nextInt(4))
		{
		case 0:
			Event = new AreaEvent(AreaEvent.EventType.KILL_MOB, new Random().nextInt(20) + new Random().nextInt(16));
			Event.setEntityType(generateEntityType());
			break;
		case 1:
			Event = new AreaEvent(AreaEvent.EventType.BOSS_HUNT, generateBoss());
			break;
		case 2:
			Event = new AreaEvent(AreaEvent.EventType.BREAK_BLOCK, 1 + new Random().nextInt(32) + new Random().nextInt(32), generateMaterial());
			break;
		case 3:
			Event = new AreaEvent(AreaEvent.EventType.CRAFT, this.generateCraftAmount(), this.generateCraft());
			break;
		default:
			Event = new AreaEvent(AreaEvent.EventType.KILL_MOB, new Random().nextInt(20) + new Random().nextInt(16));
			Event.setEntityType(generateEntityType());
			break;
		}

		Event.setEventNPC(ID);
		Event.setRequestText(TotalWar.EventDynamics.getEventText(Event));
		return Event;
	}

	private EntityType generateEntityType()
	{
		return this.Types[new Random().nextInt(this.Types.length)];
	}

	private BossType generateBoss()
	{
		switch (new Random().nextInt(TotalWar.BossHandler.BossTypes))
		{
		case 0:
			return BossType.Shicken;
		case 1:
			return BossType.AquaDan;
		case 2:
			return BossType.Porker;
		case 3:
			return BossType.DrNate;
		case 4:
			return BossType.Cthulu;
		case 5:
			return BossType.QueenWidow;
		case 6:
			return BossType.Kraken;
		case 7:
			return BossType.Skeletor;
		case 8:
			return BossType.KamikazeCreeper;
		case 9:
			return BossType.ZombieBoss;
		default:
			return BossType.Shicken;
		}
	}

	private int generateNpc()
	{
		for (NPC N : CitizensAPI.getNPCRegistry())
		{
			if (N.hasTrait(AreaEventNPCTrait.class) || N.hasTrait(EventNPCTrait.class))
			{
				if (!TotalWar.EventDynamics.isNpcInUse(N.getId()))
				{
					return N.getId();
				}
			}
		}
		return 1;
	}

	private Material generateCraft()
	{
		return this.Craftables_Stackable[new Random().nextInt(this.Craftables_Stackable.length)];
	}

	private int generateCraftAmount()
	{
		int CraftAmount = 8 + new Random().nextInt(22) + new Random().nextInt(32) + 1;
		if (CraftAmount > this.Stackable_Maximum)
		{
			return this.Stackable_Maximum;
		}
		return CraftAmount;
	}

	private Material generateMaterial()
	{
		return this.Materials[new Random().nextInt(this.Materials.length)];
	}
}