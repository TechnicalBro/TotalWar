package com.caved_in.Handlers.DynamicEvents;

public class EventProgress
{
	private String PlayerName = "";
	private int PlayerAmount = 0;
	private int EventAmount = 0;
	private boolean Rewarded = false;

	public EventProgress(String PlayerName, int EventAmount)
	{
		this.PlayerName = PlayerName;
		this.EventAmount = EventAmount;
	}

	public EventProgress()
	{
	}

	public String getPlayerName()
	{
		return this.PlayerName;
	}

	public boolean hasGottenReward()
	{
		return this.Rewarded;
	}

	public void setRewarded(boolean TrueFalse)
	{
		this.Rewarded = TrueFalse;
	}

	public void addPlayerProgress(int Amount)
	{
		this.PlayerAmount += Amount;
	}

	public void setPlayerProgress(int Amount)
	{
		this.PlayerAmount = Amount;
	}

	public int getPlayerProgress()
	{
		return this.PlayerAmount;
	}

	public int getRemaining()
	{
		return this.EventAmount - this.PlayerAmount;
	}

	public void setEventAmount(int Amount)
	{
		this.EventAmount = Amount;
	}

	public int getEventAmount()
	{
		return this.EventAmount;
	}
}