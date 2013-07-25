package com.caved_in.Config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class WarpsConfig
{
	private String FileLocation;
	private String[] WarpTag = { "<Warp>", "</Warp>" };
	private String[] IDTag = { "<ID>", "</ID>" };
	private String[] NameTag = { "<Name>", "</Name>" };
	// private String[] LocationTag = { "<Location>", "</Location>" };
	private String[] NPCTag = { "<NPC>", "</NPC>" };
	private String[] XTag;
	private String[] YTag;
	private String[] ZTag;
	private String[] WorldTag;

	public WarpsConfig(String FileLocation)
	{
		this.FileLocation = FileLocation;
		this.XTag = MakeTag("X");
		this.YTag = MakeTag("Y");
		this.ZTag = MakeTag("Z");
		this.WorldTag = MakeTag("World");
	}

	public String[] MakeTag(String Variable)
	{
		return new String[] { "<" + Variable + ">", "</" + Variable + ">" };
	}

	public String MakeWarpBlock(String Name, int[] Location)
	{
		return this.WarpTag[0] + "\n" + this.IDTag[0] + Name + this.IDTag[1] + "\n" + this.NameTag[0] + Name.replace("_", " ") + this.NameTag[1] + "\n" + this.XTag[0] + Location[0] + this.XTag[1] + "\n" + this.YTag[0] + Location[1] + this.YTag[1] + "\n" + this.ZTag[0] + Location[2] + this.ZTag[1] + "\n" + this.WarpTag[1] + "\n";
	}

	public String MakeNPCBlock(String ID, String Name, int[] Location, String World)
	{
		return this.NPCTag[0] + "\n" + this.IDTag[0] + ID + this.IDTag[1] + "\n" + this.NameTag[0] + Name.replace("_", " ") + this.NameTag[1] + "\n" + this.WorldTag[0] + World + this.WorldTag[1] + "\n" + this.XTag[0] + Location[0] + this.XTag[1] + "\n" + this.YTag[0] + Location[1] + this.YTag[1] + "\n" + this.ZTag[0] + Location[2] + this.ZTag[1] + "\n" + this.NPCTag[1] + "\n";
	}

	public boolean WriteNPCData(String ID, String LocName, int[] Location, String World)
	{
		try
		{
			FileUtils.writeStringToFile(new File(this.FileLocation), MakeNPCBlock(ID, LocName, Location, World), true);
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public void WriteBlockData(String Name, int[] Location)
	{
		try
		{
			FileUtils.writeStringToFile(new File(this.FileLocation), MakeWarpBlock(Name, Location), true);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void WriteBlockData(String Data)
	{
		try
		{
			FileUtils.writeStringToFile(new File(this.FileLocation), Data, true);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public Double[] getCoords(String ID)
	{
		return new Double[] { getX(ID), getY(ID), getZ(ID) };
	}

	public Double[] getNPCCoords(String NPCName)
	{
		return new Double[] { getnX(NPCName), getnY(NPCName), getnZ(NPCName) };
	}

	/**
	 * Gets the <Warp> block data for an ID for VALUE
	 * 
	 * @param ID
	 * @return
	 */
	@Deprecated
	public Double getX(String ID)
	{
		return Double.valueOf(Double.parseDouble(StringUtils.substringBetween(getData(ID), this.XTag[0], this.XTag[1])));
	}

	public Double getnX(String ID)
	{
		return Double.valueOf(Double.parseDouble(StringUtils.substringBetween(getNPCData(ID), this.XTag[0], this.XTag[1])));
	}

	public Double getnY(String ID)
	{
		return Double.valueOf(Double.parseDouble(StringUtils.substringBetween(getNPCData(ID), this.YTag[0], this.YTag[1])));
	}

	public Double getnZ(String ID)
	{
		return Double.valueOf(Double.parseDouble(StringUtils.substringBetween(getNPCData(ID), this.ZTag[0], this.ZTag[1])));
	}

	/**
	 * Gets the <Warp> block data for an ID for VALUE
	 * 
	 * @param ID
	 * @return
	 */
	@Deprecated
	public Double getY(String ID)
	{
		return Double.valueOf(Double.parseDouble(StringUtils.substringBetween(getData(ID), this.YTag[0], this.YTag[1])));
	}

	/**
	 * Gets the <Warp> block data for an ID for VALUE
	 * 
	 * @param ID
	 * @return
	 */
	@Deprecated
	public Double getZ(String ID)
	{
		return Double.valueOf(Double.parseDouble(StringUtils.substringBetween(getData(ID), this.ZTag[0], this.ZTag[1])));
	}

	/**
	 * Gets the name of a world
	 * 
	 * @param ID
	 * @return
	 */
	public String getWorld(String NPC_Name, String Warp_Name)
	{
		// return StringUtils.substringBetween(getNPCData(ID),
		// this.WorldTag[0],this.WorldTag[1]);
		for (String S : getNPCBlocks(NPC_Name))
		{
			if (StringUtils.substringBetween(S, this.NameTag[0], this.NameTag[1]).equalsIgnoreCase(Warp_Name))
			{
				return StringUtils.substringBetween(S, this.WorldTag[0], this.WorldTag[1]);
			}
		}
		return null;
	}

	/**
	 * Gets the block data between <Warp> tags
	 * 
	 * @param ID
	 * @return
	 */
	@Deprecated
	public String getData(String ID)
	{
		for (String S : StringUtils.substringsBetween(getText(), this.WarpTag[0], this.WarpTag[1]))
		{
			if (StringUtils.substringBetween(S, this.IDTag[0], this.IDTag[1]).equalsIgnoreCase(ID))
			{
				return S;
			}
		}
		return null;
	}

	/**
	 * Gets the Block data of an NPC with this name; Singular string, used in
	 * recursion for rendering an npc's waypoints
	 * 
	 * @param NPCName
	 * @return
	 */
	public String getNPCData(String NPCName)
	{
		for (String S : StringUtils.substringsBetween(getText(), this.NPCTag[0], this.NPCTag[1]))
		{
			if (StringUtils.substringBetween(S, this.IDTag[0], this.IDTag[1]).equalsIgnoreCase(NPCName))
			{
				return S;
			}
		}
		return null;
	}

	public String getText()
	{
		try
		{
			return FileUtils.readFileToString(new File(this.FileLocation));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public String getName(String ID)
	{
		return StringUtils.substringBetween(getData(ID), this.NameTag[0], this.NameTag[1]);
	}

	/**
	 * Gets the "Warp" blocks, no longer used
	 * 
	 * @return
	 */
	public ArrayList<String> WarpBlocks()
	{
		ArrayList<String> Blocks = new ArrayList<String>();
		for (String S : StringUtils.substringsBetween(getText(), this.WarpTag[0], this.WarpTag[1]))
		{
			Blocks.add(S);
		}
		return Blocks;
	}

	/**
	 * Gets an Arraylist of IDS in WarpBlocks
	 * 
	 * @param WarpBlocks
	 * @return
	 */
	public ArrayList<String> getIDS(ArrayList<String> WarpBlocks)
	{
		ArrayList<String> IDS = new ArrayList<String>();
		for (String S : WarpBlocks)
		{
			IDS.add(StringUtils.substringBetween(S, this.IDTag[0], this.IDTag[1]));
		}
		return IDS;
	}

	/**
	 * Gets the Name of an NPC
	 * 
	 * @param Data
	 *            The block data to parse
	 * @return
	 */
	public String getNPCID(String Data)
	{
		return StringUtils.substringBetween(Data, this.IDTag[0], this.IDTag[1]);
	}

	/**
	 * Get each block for a certain NPC
	 * 
	 * @param Name
	 *            The name of the NPC
	 * @return
	 */
	public ArrayList<String> getNPCBlocks(String Name)
	{
		ArrayList<String> Blocks = new ArrayList<String>();
		for (String S : StringUtils.substringsBetween(getText(), this.NPCTag[0], this.NPCTag[1]))
		{
			if (StringUtils.substringBetween(S, this.IDTag[0], this.IDTag[1]).equalsIgnoreCase(Name))
			{
				Blocks.add(S);
			}
		}
		return Blocks;
	}

	/**
	 * Gets the name of a warp, as defined between its <Name> tags
	 * 
	 * @param NPCData
	 *            The "block" of npc data
	 * @return
	 */
	public String getWarpName(String NPCData)
	{
		return StringUtils.substringBetween(NPCData, this.NameTag[0], this.NameTag[1]);
	}

	/**
	 * Gets the co-ords for an NPC's Warp
	 * 
	 * @param NPCName
	 *            The name of the npc
	 * @param WarpName
	 *            The name of the warp
	 * @return
	 */
	public Double[] getCoords(String NPCName, String WarpName)
	{
		for (String S : getNPCBlocks(NPCName))
		{
			if (StringUtils.substringBetween(S, this.NameTag[0], this.NameTag[1]).equalsIgnoreCase(WarpName))
			{
				return new Double[] { Double.valueOf(Double.parseDouble(StringUtils.substringBetween(S, this.XTag[0], this.XTag[1]))), Double.valueOf(Double.parseDouble(StringUtils.substringBetween(S, this.YTag[0], this.YTag[1]))), Double.valueOf(Double.parseDouble(StringUtils.substringBetween(S, this.ZTag[0], this.ZTag[1]))) };
			}
		}
		return null;
	}
}