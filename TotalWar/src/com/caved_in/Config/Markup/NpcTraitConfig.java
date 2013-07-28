package com.caved_in.Config.Markup;

import java.util.HashMap;
import java.util.List;

import com.caved_in.Handlers.FileHandler.*;

public class NpcTraitConfig
{
	private DataHandler DataHandler;
	private Tag NPCTag = new Tag("NPC");
	private Tag IDTag = new Tag("ID");
	private Tag TraitTag = new Tag("Trait");
	
	public NpcTraitConfig(String Location)
	{
		this.DataHandler = new DataHandler(Location);
	}
	
	public String makeTextBlock(int NPCID, String Trait)
	{
		return NPCTag.Open() + "\r\n" + IDTag.Open() + NPCID + IDTag.Close() + "\r\n" + TraitTag.Open() + Trait + TraitTag.Close() + "\r\n" + NPCTag.Close();
	}
	
	public HashMap<Integer, String> getData()
	{
		HashMap<Integer, String> NpcData = new HashMap<Integer, String>();
		
		List<String> Blocks = this.DataHandler.getAllBetween(NPCTag.Open(), NPCTag.Close());
		for(String S : Blocks)
		{
			try
			{
				int ID = Integer.parseInt(this.DataHandler.getStringBetween(S, IDTag.Open(), IDTag.Close()));
				if (!NpcData.containsKey(ID))
				{
					String Trait = this.DataHandler.getStringBetween(S, TraitTag.Open(), TraitTag.Close());
					NpcData.put(ID, Trait);
				}
			}
			catch (Exception Ex)
			{
				//Slop
			}
		}
		return NpcData;
	}
	
	public String getTrait(int NPCID)
	{
		if (hasData(NPCID))
		{
			return getData().get(NPCID);
		}
		return null;
	}
	
	public boolean WriteData(int NPCID, String Trait)
	{
		if (!hasData(NPCID))
		{
			this.DataHandler.AppendString(this.makeTextBlock(NPCID, Trait));
			return true;
		}
		return false;
	}
	
	public boolean hasData(int NPCID)
	{
		List<String> Blocks = this.DataHandler.getAllBetween(NPCTag.Open(), NPCTag.Close());
		for(String S : Blocks)
		{
			try
			{
				int ID = Integer.parseInt(this.DataHandler.getStringBetween(S, IDTag.Open(), IDTag.Close()));
				if (ID == NPCID)
				{
					return true;
				}
			}
			catch (Exception Ex)
			{
				//Slop
			}
		}
		return false;
	}
	
	
}
