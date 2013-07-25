package com.caved_in.Handlers.DynamicEvents;

public class AreaEventNPC
{
	private int NPCID = -1;

	public AreaEventNPC(int ID)
	{
		this.NPCID = ID;
	}

	public int getID()
	{
		return this.NPCID;
	}

	public void setID(int ID)
	{
		this.NPCID = ID;
	}
}