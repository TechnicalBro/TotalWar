package com.caved_in.CitizenTraits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;

import com.caved_in.TotalWar;
import com.caved_in.Handlers.DynamicEvents.AreaEvent;
import com.caved_in.Handlers.DynamicEvents.EventGenerator;
import com.caved_in.Handlers.DynamicEvents.Wrappers.RequirementWrapper;

public class EventNPCTrait extends Trait
{
	public EventNPCTrait()
	{
		super("eventnpc");
	}

	@EventHandler
	public void EventNPC_Rightclick(NPCRightClickEvent Event)
	{
		if (Event.getClicker().isOp())
		{
			CheckForEvents(Event.getClicker());
		}
		else
		{
			if (Event.getClicker().getGameMode() != GameMode.CREATIVE)
			{
				if (!isThisNpc(Event.getNPC()))
				{
					Event.setCancelled(true);
					return;
				}
				CheckForEvents(Event.getClicker());
			}
			else
			{
				Event.getClicker().sendMessage(this.NpcText(getNPC().getName(),ChatColor.RED + "Don't be a freakin' cheat! You're in creative :("));
				TotalWar.Console(ChatColor.RED + "Player [" + Event.getClicker().getName() + "] is trying to hand in events while in creative...");
			}
		}
	}

	private String NpcText(String NPCName, String Input)
	{
		return ChatColor.GREEN + "[" + NPCName + "] -> You: " + Input;
	}
	
	public RequirementWrapper hasRequiredMaterials(Player Player, AreaEvent e) throws Exception
	{
		HashMap<Integer, ItemStack> RemovingItems = new HashMap<Integer, ItemStack>();
		int AmountRequired = e.getAmountRequired();
		int PlayerAmount = 0;
		if (Player.getInventory().contains(e.getEventMaterial()))
		{
			HashMap<Integer, ? extends ItemStack> EventMaterialSlots = Player.getInventory().all(e.getEventMaterial()); //Get all Slots with their itemstack for the events material
			for(Entry<Integer, ? extends ItemStack> Pair : EventMaterialSlots.entrySet())
			{
				int StackSize = Pair.getValue().getAmount();
				PlayerAmount += StackSize; //The Amount required is reduced by the amount of event materials the player has
				if (PlayerAmount >= AmountRequired) //If the player has the amount required
				{
					RemovingItems.put(Pair.getKey(), Pair.getValue());
					break; // No moar loop kthx
				}
				else
				{
					RemovingItems.put(Pair.getKey(), Pair.getValue());
				}
				//TotalWar.Console("Inventory of " + Player.getName() + " at slot " + Pair.getKey() + " has " + StackAmount + " of " + e.getEventMaterial().name() + " with a remainder of " + AmountNeeded + " required.");
			}
			return new RequirementWrapper(AmountRequired, PlayerAmount, RemovingItems);
		}
		throw new Exception("Players inventory does not contain Items for this Event");
	}
	
	public void removeEventMaterials(Player Player, AreaEvent Event, RequirementWrapper Wrapper)
	{
		for(Entry<Integer, ? extends ItemStack> Item : Wrapper.getItemLocations().entrySet())
		{
			Player.getInventory().setItem(Item.getKey(), null); //Setting all the items in the wrapper (items for the event) to nothing (aka removing them)
			TotalWar.Console("Took items from Player [" + Player.getName() + "] at slot " + Item.getKey() + " with item " + Event.getEventMaterial().name());
		}
		if (Wrapper.getLeftovers() > 0) // Handles "leftovers" or extra that the player has
		{
			int Returning = Wrapper.getLeftovers();
			while (true)
			{
				if (Returning > 64)
				{
					Player.getInventory().addItem(new ItemStack(Event.getEventMaterial(),64));
					TotalWar.Console("Gave 64 " + Event.getEventMaterial().name() + " leftovers to player [" + Player.getName() + "]");
					Returning -= 64;
				}
				else if (Returning != 0 && Returning <= 64)
				{
					Player.getInventory().addItem(new ItemStack(Event.getEventMaterial(),Returning));
					TotalWar.Console("Gave " + Returning + " " + Event.getEventMaterial().name() + " leftovers to player [" + Player.getName() + "]");
					break;
				}
			}
		}
		Player.updateInventory();
	}
	
	public boolean isMaterialEvent(AreaEvent Event)
	{
		if (Event.getType() == AreaEvent.EventType.CRAFT || Event.getType() == AreaEvent.EventType.BREAK_BLOCK || Event.getType() == AreaEvent.EventType.DELIVER)
		{
			return true;
		}
		return false;
	}
	
	public void CheckForEvents(Player Player)
	{
		List<AreaEvent> Events = TotalWar.EventDynamics.getEventsForNPC(getNPC().getId());
		for (AreaEvent Event : Events)
		{
			if (!TotalWar.EventDynamics.hasBeenRewarded(Event, Player.getName())) //Player hasn't been rewarded for this event
			{
				if (isMaterialEvent(Event)) //Is it an event with materials / Items involved?
				{
					if (Player.getInventory().contains(Event.getEventMaterial()))
					{
						RequirementWrapper Wrapper;
						try
						{
							Wrapper = this.hasRequiredMaterials(Player, Event);
							if (Wrapper.hasRequiredAmount())
							{
								this.removeEventMaterials(Player, Event, Wrapper);
								TotalWar.EventDynamics.setRewarded(Event, Player.getName(), true);
								Event.GiveRewards(Player);
							}
							else
							{
								Player.sendMessage(this.NpcText(getNPC().getName(), "You're making progress! I just need " + Wrapper.amountRemaining() + " more " + Event.getEventMaterial().name().toLowerCase().replace('_', ' ') + "!"));
							}
						}
						catch (Exception e)
						{
							//SLOPPY AS FUCK Catch, but, needed
							Player.sendMessage(ChatColor.RED + "[ERROR] You've found a bug in the events! Please tell Brandon what you did before you got this error so he can fix it!");
							e.printStackTrace();
						}
					}
				}
				else if (TotalWar.EventDynamics.isPlayerCompleted(Event, Player.getName()))
				{
					TotalWar.EventDynamics.setRewarded(Event, Player.getName(), true);
					Event.GiveRewards(Player);
				}
				else
				{
					Player.sendMessage(this.NpcText(Event.getEventNPC().getName(), Event.getRequestText()));
				}
			}
			else
			{
				Player.sendMessage(this.NpcText(Event.getNpcName(), "Thanks for all the help, " + Player.getName() + ", I really appreciate it!"));
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
		if (!TotalWar.EventDynamics.isNpcInUse(this.getNPC().getId()))
		{
			AreaEvent Event = new EventGenerator().generateEvent(this.getNPC().getId());
			TotalWar.EventDynamics.AddEvent(Event);
			TotalWar.Console("Generated event for: " + this.getNPC().getName());
			for (Player P : Bukkit.getOnlinePlayers())
			{
				TotalWar.EventDynamics.putPlayerInEvent(Event, P.getName());
			}
		}
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