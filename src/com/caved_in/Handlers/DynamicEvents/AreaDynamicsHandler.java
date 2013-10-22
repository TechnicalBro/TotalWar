package com.caved_in.Handlers.DynamicEvents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import com.caved_in.TotalWar;
import com.caved_in.CitizenTraits.EventNPCTrait;
import com.caved_in.Handlers.FileHandler.Tag;

public class AreaDynamicsHandler
{
	private HashMap<AreaEvent, ArrayList<EventProgress>> Event_Progress = new HashMap<AreaEvent, ArrayList<EventProgress>>();
	private List<Material> Event_BreakBlock_Materials_In_Use = new ArrayList<Material>();
	private List<Material> Event_Craft_Materials_In_Use = new ArrayList<Material>();
	private List<EntityType> Event_Entities_In_Use = new ArrayList<EntityType>();
	private List<String> Event_Bosses_In_Use = new ArrayList<String>();

	private List<AreaEventNPC> EventNPCs = new ArrayList<AreaEventNPC>();

	private Tag DescriptionTag = new Tag("Description");
	private Tag LineTag = new Tag("Line");

	private String[] BossDescriptionWords = { "Ferocious", "Blood Thirsty", "Fearsome", "Nightmareish", "Death Bringing", "Intimidating", "Horrific", "Mighty" };

	public void AddEvent(AreaEvent Event)
	{
		if (!this.Event_Progress.containsKey(Event))
		{
			if (!this.isNpcInUse(Event.getEventNPCID()))
			{
				this.EventNPCs.add(new AreaEventNPC(Event.getEventNPCID()));
				this.Event_Progress.put(Event, new ArrayList<EventProgress>());
				TotalWar.Console("[Area Specific Events] New Event for NPC [" + Event.getNpcName() + "] TYPE = " + Event.getType().name());
			}
		}
	}

	public String getEventText(AreaEvent e)
	{
		String eText = "";
		switch (e.getType())
		{
		case BOSS_HUNT:
			eText = "The " + generateBossDescription() + " " + e.getBossName() + " has been causing me some MASSIVE problems here... Kill it for me and you'll be rewarded!";
			break;
		case BREAK_BLOCK:
			eText = "I need like " + e.getAmountRequired() + " " + e.getEventMaterial().toString().toLowerCase().replaceAll("_", " ") + " to finish a build, do you think you can get them for me? If you do, I've got a reward for you.";
			break;
		case KILL_MOB:
			eText = "There's a bit of a problem; " + e.getAmountRequired() + " " + e.getEntityType().toString().toLowerCase().replaceAll("_", " ") + "'s went by here earlier, and caused a load of damage... Think you can kill them for me? There's something in it for you if you do!";
			break;
		case CRAFT:
			eText = "I desperately need " + e.getAmountRequired() + " " + e.getEventMaterial().toString().toLowerCase().replaceAll("_", " ") + " but I don't have time to waste, do you think you can get them for me? If you do, I've got a reward for you.";
			break;
		default:
			break;
		}

		return eText;
	}

	public boolean isNpcInUse(int ID)
	{
		for (AreaEventNPC N : this.EventNPCs)
		{
			if (N.getID() == ID)
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * private String getEventDescription(Event e) { DataHandler Text;
	 * switch(e.getType()) { case BOSS_HUNT: Text = new
	 * DataHandler("plugins/TotalWar/DynamicEvents/Descriptions/Bosshunt.txt");
	 * break; case BREAK_BLOCK: Text = new
	 * DataHandler("plugins/TotalWar/DynamicEvents/Descriptions/Breakblock.txt"
	 * ); break; case CRAFT: Text = new
	 * DataHandler("plugins/TotalWar/DynamicEvents/Descriptions/Craft.txt");
	 * break; case KILL_MOB: Text = new
	 * DataHandler("plugins/TotalWar/DynamicEvents/Descriptions/Mobhunt.txt");
	 * break; default: Text = null; break; }
	 * 
	 * if (Text != null) { List<String> Blocks =
	 * Text.getAllBetween(this.DescriptionTag.Open(),
	 * this.DescriptionTag.Close()); String Block = Blocks.get(new
	 * Random().nextInt(Blocks.size())); for(String S :
	 * Text.getAllBetween(Block,
	 * this.LineTag.getOpen(),this.LineTag.getClose())) {
	 * 
	 * } } return ""; }
	 */

	private String generateBossDescription()
	{
		return this.BossDescriptionWords[new java.util.Random().nextInt(this.BossDescriptionWords.length)];
	}

	public void ReplaceEvent(AreaEvent OldEvent)
	{
		int OldCount = this.Event_Progress.size();
		if (this.Event_Progress.containsKey(OldEvent))
		{
			TotalWar.Console("[Events] Replace Event Fired");
			RemoveEvent(OldEvent);
			while (!isEventsCount(OldCount))
			{
				AddEvent(new EventGenerator().generateEvent());
			}
			TotalWar.Console("[Events] Event Replaced with a randomly generated one");
		}
		TotalWar.UpdateAllPlayerEvents();
	}

	public void ReplaceAllEvents()
	{
		int OldCount = this.Event_Progress.size();
		this.Event_Progress.clear();
		this.Event_Bosses_In_Use.clear();
		this.Event_BreakBlock_Materials_In_Use.clear();
		this.Event_Entities_In_Use.clear();
		this.Event_Craft_Materials_In_Use.clear();
		while (!isEventsCount(OldCount))
		{
			AddEvent(new EventGenerator().generateEvent());
		}
		TotalWar.Console("[Events] All Events have been replaced with new, Randomly generated ones!");
		TotalWar.UpdateAllPlayerEvents();
	}

	public boolean isEventsCount(int Check)
	{
		if (this.Event_Progress.size() >= Check)
		{
			return true;
		}
		return false;
	}

	public int NPCAmount()
	{
		int Return = 0;
		for (NPC N : CitizensAPI.getNPCRegistry())
		{
			if (N.hasTrait(EventNPCTrait.class))
			{
				Return += 1;
			}
		}
		return Return;
	}

	public void putPlayerInEvent(AreaEvent Event, String PlayerName)
	{
		if (this.Event_Progress.containsKey(Event))
		{
			if (!getPlayersInEvent(Event).contains(PlayerName))
			{
				this.Event_Progress.get(Event).add(new EventProgress(PlayerName, Event.getAmountRequired()));
				return;
			}
		}
	}

	public void putPlayerInAllEvents(String PlayerName)
	{
		for (AreaEvent e : this.Event_Progress.keySet())
		{
			if (!getPlayersInEvent(e).contains(PlayerName))
			{
				this.Event_Progress.get(e).add(new EventProgress(PlayerName, e.getAmountRequired()));
			}
		}
	}

	public ArrayList<AreaEvent> getActiveEvents(AreaEvent.EventType Type)
	{
		ArrayList<AreaEvent> eGet = new ArrayList<AreaEvent>();
		for (AreaEvent e : this.Event_Progress.keySet())
		{
			if (e.getType().equals(Type))
			{
				eGet.add(e);
			}
		}
		return eGet;
	}

	public ArrayList<AreaEvent> getActiveEvents()
	{
		ArrayList<AreaEvent> eGet = new ArrayList<AreaEvent>();
		for (AreaEvent e : this.Event_Progress.keySet())
		{
			eGet.add(e);
		}
		return eGet;
	}

	public void RemoveEvent(AreaEvent Event)
	{
		if (this.Event_Progress.containsKey(Event))
		{
			this.Event_Progress.remove(Event);
		}
	}

	public ArrayList<EventProgress> getProgressArray(AreaEvent Event)
	{
		return this.Event_Progress.get(Event);
	}

	public ArrayList<String> getPlayersInEvent(AreaEvent Event)
	{
		ArrayList<String> PlayersInEvent = new ArrayList<String>();
		for (int I = 0; I < getProgressArray(Event).size(); I++)
		{
			PlayersInEvent.add(getProgressArray(Event).get(I).getPlayerName());
		}
		return PlayersInEvent;
	}

	public void addPlayerProgress(AreaEvent Event, String PlayerName, int Amount)
	{
		PlayerProgress(Event, PlayerName).addPlayerProgress(Amount);
	}

	public void addPlayerProgress(AreaEvent Event, String PlayerName)
	{
		addPlayerProgress(Event, PlayerName, 1);
	}

	public int getPlayerProgress(AreaEvent Event, String PlayerName)
	{
		return PlayerProgress(Event, PlayerName).getPlayerProgress();
	}

	public int getRemaining(AreaEvent Event, String PlayerName)
	{
		return PlayerProgress(Event, PlayerName).getRemaining();
	}

	public boolean isPlayerCompleted(AreaEvent Event, String PlayerName)
	{
		if (getRemaining(Event, PlayerName) <= 0)
		{
			return true;
		}
		return false;
	}

	public boolean hasBeenRewarded(AreaEvent Event, String PlayerName)
	{
		if (PlayerProgress(Event, PlayerName).hasGottenReward())
		{
			return true;
		}
		return false;
	}

	public void setRewarded(AreaEvent Event, String PlayerName, boolean Rewarded)
	{
		PlayerProgress(Event, PlayerName).setRewarded(Rewarded);
	}

	private EventProgress PlayerProgress(AreaEvent Event, String PlayerName)
	{
		if (this.Event_Progress.containsKey(Event))
		{
			List<EventProgress> EventProgressList = this.Event_Progress.get(Event);
			for (int I = 0; I < EventProgressList.size(); I++)
			{
				if (EventProgressList.get(I).getPlayerName().equals(PlayerName))
				{
					return EventProgressList.get(I);
				}
			}
		}
		return null;
	}

	public List<AreaEvent> getEventsForNPC(int NPCID)
	{
		List<AreaEvent> Return = new ArrayList<AreaEvent>();
		for (AreaEvent Event : this.Event_Progress.keySet())
		{
			if (Event.getEventNPCID() == NPCID)
			{
				Return.add(Event);
			}
		}
		return Return;
	}
}