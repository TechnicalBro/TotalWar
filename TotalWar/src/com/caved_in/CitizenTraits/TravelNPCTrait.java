package com.caved_in.CitizenTraits;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import com.caved_in.TotalWar;
import com.caved_in.ItemMenus.WaypointMenu.WaypointMenu;

public class TravelNPCTrait extends Trait
{
	public TravelNPCTrait()
	{
		super("travelnpc");
	}

	@EventHandler
	public void travelnpc_Rightclick(NPCRightClickEvent Event)
	{
		if (!isThisNpc(Event.getNPC()))
		{
			Event.setCancelled(true);
			return;
		}
		CheckTeleports(Event.getClicker(), Event.getNPC().getName().replace(" ", "_"), getPlayerFaction(Event.getClicker()));
	}

	private String getPlayerFaction(Player Player)
	{
		if (TotalWar.permission.has(Player, "Totalwar.Pagan"))
		{
			return "Totalwar.Pagan";
		}
		if (TotalWar.permission.has(Player, "Totalwar.Pirate"))
		{
			return "Totalwar.Pirate";
		}
		if (TotalWar.permission.has(Player, "Totalwar.Dragonkin"))
		{
			return "Totalwar.Dragonkin";
		}
		if (TotalWar.permission.has(Player, "Totalwar.Templar"))
		{
			return "Totalwar.Templar";
		}
		return null;
	}

	private void CheckTeleports(Player Player, String NPC_Name, String Player_Faction)
	{
		WaypointMenu Menu = new WaypointMenu(NPC_Name, true);
		Menu.ShowMenu(Player);
	}

	private boolean isThisNpc(NPC Npc)
	{
		return Npc == getNPC();
	}

	@Override
	public void run()
	{
	}

	@Override
	public void onAttach()
	{
		if (!TotalWar.NpcTraitConfig.hasData(this.getNPC().getId()))
		{
			TotalWar.NpcTraitConfig.WriteData(this.getNPC().getId(), "travelnpc");
			TotalWar.Console("Cached the travelnpc trait for " + this.getNPC().getName());
		}
	}

	@Override
	public void onDespawn()
	{
	}

	@Override
	public void onSpawn()
	{
		if (!TotalWar.NpcTraitConfig.hasData(this.getNPC().getId()))
		{
			TotalWar.NpcTraitConfig.WriteData(this.getNPC().getId(), "travelnpc");
			TotalWar.Console("Cached the travelnpc trait for " + this.getNPC().getName());
		}
	}

	@Override
	public void onRemove()
	{
	}
}