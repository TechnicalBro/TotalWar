package com.caved_in.CitizenTraits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;

import com.caved_in.TotalWar;
import com.caved_in.Handlers.DynamicEvents.AreaEvent;

public class AreaEventNPCTrait extends Trait
{
	public AreaEventNPCTrait()
	{
		super("areaeventnpc");
	}

	@EventHandler
	public void EventNPC_Rightclick(NPCRightClickEvent Event)
	{
		if (Event.getClicker().getGameMode() != GameMode.CREATIVE)
		{
			if (!isThisNpc(Event.getNPC()))
			{
				Event.setCancelled(true);
				return;
			}
			// TotalWar.Console("Checking for events @ [" + this.npc.getName() +
			// " for " + Event.getClicker().getName() + "]");
			CheckForEvents(Event.getClicker());
		}
	}

	private String NpcText(String NPCName, String Input)
	{
		return ChatColor.GREEN + "[" + NPCName + "] -> You: " + Input;
	}

	public boolean CheckForRequiredItems(Player Player, AreaEvent e)
	{
		if ((e.getType() == AreaEvent.EventType.CRAFT) || (e.getType() == AreaEvent.EventType.BREAK_BLOCK))
		{
			if (Player.getInventory().contains(e.getEventMaterial()))
			{
				HashMap<Integer, ? extends ItemStack> InventorySlotStack = Player.getInventory().all(e.getEventMaterial());
				HashMap<Integer, ItemStack> RemovingItemStacks = new HashMap<Integer, ItemStack>();
				int AmountNeeded = e.getAmountRequired();
				boolean HasRequiredAmount = false;
				int ReturningAmount = 0;
				for (Entry<Integer, ? extends ItemStack> Pair : InventorySlotStack.entrySet())
				{
					int StackAmount = ((ItemStack) Pair.getValue()).getAmount();
					if (StackAmount > AmountNeeded)
					{
						ReturningAmount = StackAmount - AmountNeeded;
					}
					AmountNeeded -= StackAmount;
					if (AmountNeeded <= 0)
					{
						HasRequiredAmount = true;
						break;
					}

					TotalWar.Console("Inventory of " + Player.getName() + " at slot " + Pair.getKey() + " has " + StackAmount + " of " + e.getEventMaterial().name() + " with a remainder of " + AmountNeeded + " required.");
					RemovingItemStacks.put(Pair.getKey(), Pair.getValue());
				}

				if (HasRequiredAmount)
				{
					TotalWar.Console(Player.getName() + " has the amount required");
					int TotalAmount = e.getAmountRequired();
					int PlayerAmount = 0;
					for (Entry<Integer, ? extends ItemStack> Pair : RemovingItemStacks.entrySet())
					{
						PlayerAmount += ((ItemStack) Pair.getValue()).getAmount();
						if (PlayerAmount >= TotalAmount)
						{
							if (PlayerAmount - TotalAmount != 0)
							{
								ReturningAmount = PlayerAmount - TotalAmount;
								Player.getInventory().setItem(Pair.getKey().intValue(), new ItemStack(e.getEventMaterial(), ReturningAmount));
								TotalWar.Console("Took items from " + Player.getName() + " at " + Pair.getKey() + ", but returned " + ReturningAmount);
							}
							else
							{
								Player.getInventory().setItem(Pair.getKey().intValue(), null);
								TotalWar.Console("Took all the items from " + Player.getName());
							}
						}
						else
						{
							Player.getInventory().setItem(Pair.getKey().intValue(), null);
							TotalWar.Console("Took items at " + Pair.getKey() + " from " + Player.getName());
						}
					}
					return true;
				}

				return false;
			}

			return false;
		}

		return false;
	}

	private void CheckForEvents(Player Player)
	{
		ArrayList<AreaEvent> Events = TotalWar.EventDynamics.getActiveEvents();
		for (AreaEvent e : Events)
		{
			if (e.getNpcName().equals(getNPC().getName()))
			{
				if ((e.getType() == AreaEvent.EventType.CRAFT) || (e.getType() == AreaEvent.EventType.DELIVER) || (e.getType() == AreaEvent.EventType.BREAK_BLOCK))
				{
					if ((!TotalWar.EventDynamics.hasBeenRewarded(e, Player.getName())))
					{
						if (CheckForRequiredItems(Player, e))
						{
							TotalWar.EventDynamics.setRewarded(e, Player.getName(), true);
							e.GiveRewards(Player);
						}
						else
						{
							Player.sendMessage(this.NpcText(e.getNpcName(), "I need " + e.getAmountRequired() + " " + e.getEventMaterial().toString().toLowerCase().replaceAll("_", " ") + ", please make sure you have what I need..."));
						}
					}
					else
					{
						Player.sendMessage(this.NpcText(e.getNpcName(), "Thanks for all the help, " + Player.getName() + ", I really appreciate it!"));
					}

				}
				else if ((!TotalWar.EventDynamics.hasBeenRewarded(e, Player.getName())) && (TotalWar.EventDynamics.isPlayerCompleted(e, Player.getName())))
				{
					TotalWar.EventDynamics.setRewarded(e, Player.getName(), true);
					e.GiveRewards(Player);
				}
			}
			else
			{
				Player.sendMessage(this.NpcText(e.getEventNPC().getName(), e.getRequestText()));
			}
		}
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

	}

	@Override
	public void onDespawn()
	{
	}

	@Override
	public void onSpawn()
	{

	}

	@Override
	public void onRemove()
	{
	}
}

/*
 * Location: C:\Users\Brandon\Desktop\TotalWar.jar Qualified Name:
 * com.caved_in.CitizenTraits.EventNPCTrait JD-Core Version: 0.6.2
 */