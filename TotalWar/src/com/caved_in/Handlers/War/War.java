package com.caved_in.Handlers.War;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import com.caved_in.Handlers.FileHandler.DataHandler;
import com.caved_in.Handlers.FileHandler.Tag;
import com.caved_in.Handlers.Misc.TimeHandler;
import com.caved_in.Handlers.PlayerHandlers.PlayerHandler.Faction;

public class War
{
	private DataHandler Config;
	private DataHandler Points;
	private boolean isEnabled = false;
	private long TimeBetween = 7200000L;
	private Tag WarStatusTag = new Tag("WarEnabled");
	private Tag WarTimeTag = new Tag("Time");

	private Tag DragonkinTag = new Tag("Dragonkin");
	private Tag PirateTag = new Tag("Pirate");
	private Tag PaganTag = new Tag("Pagan");
	private Tag TemplarTag = new Tag("Templar");
	private Tag PreviousWinner = new Tag("LastWinner");
	private String FileData = "<WarEnabled>false</WarEnabled>\r\n<Time>0</Time>";

	public War()
	{
		File WarData = new File("plugins/TotalWar/WarConfig.txt");
		File WarPoints = new File("plugins/TotalWar/WarPoints.txt");
		if (!WarData.exists())
		{
			try
			{
				this.Config = new DataHandler("plugins/TotalWar/WarConfig.txt");
				setWar(false);
				// FileUtils.writeStringToFile(WarData, this.FileData)
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			this.Config = new DataHandler("plugins/TotalWar/WarConfig.txt");
			this.isEnabled = Boolean.valueOf(this.Config.getBetween(this.WarStatusTag));
		}

		if (!WarPoints.exists())
		{
			try
			{
				this.Points = new DataHandler("plugins/TotalWar/WarPoints.txt");
				this.ResetPoints();
				// FileUtils.writeStringToFile(WarData, this.FileData)
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			this.Points = new DataHandler("plugins/TotalWar/WarPoints.txt");
		}
	}

	public boolean isWarOn()
	{
		return this.isEnabled;
	}

	public void setWar(boolean value)
	{
		this.isEnabled = value;
		Long Time = System.currentTimeMillis();
		Config.OverwriteFile(this.WarStatusTag.getOpen() + String.valueOf(value) + this.WarStatusTag.getClose() + "\r\n" + this.WarTimeTag.getOpen() + String.valueOf(Time) + this.WarTimeTag.getClose());
	}

	public int getPoints(Faction Faction)
	{
		switch (Faction)
		{
		case Dragonkin:
			return Integer.parseInt(this.Points.getBetween(this.DragonkinTag));
		case None:
			return 0;
		case Pagan:
			return Integer.parseInt(this.Points.getBetween(this.PaganTag));
		case Pirate:
			return Integer.parseInt(this.Points.getBetween(this.PirateTag));
		case Templar:
			return Integer.parseInt(this.Points.getBetween(this.TemplarTag));
		default:
			return 0;
		}
	}

	public void ResetPoints()
	{
		List<String> Points = new ArrayList<String>();
		Points.add(this.DragonkinTag.getOpen() + "0" + this.DragonkinTag.Close());
		Points.add(this.PaganTag.getOpen() + "0" + this.PaganTag.Close());
		Points.add(this.PirateTag.Open() + "0" + this.PirateTag.Close());
		Points.add(this.TemplarTag.Open() + "0" + this.TemplarTag.Close());
		// String LastWinner =
		// this.Points.getBetween(this.PreviousWinner.Open(),this.PreviousWinner.Close());
		// LastWinner
		// Points.add(this.PreviousWinner.Open() + LastWinner+
		// this.PreviousWinner.Close());
		String Data = "";
		for (String S : Points)
		{
			Data += S + "\r\n";
		}
		this.Points.OverwriteFile(Data);
	}

	public void SavePoints(int Dragonkin, int Pagan, int Pirate, int Templar)
	{
		List<String> Points = new ArrayList<String>();
		Points.add(this.DragonkinTag.getOpen() + Dragonkin + this.DragonkinTag.Close());
		Points.add(this.PaganTag.getOpen() + Pagan + this.PaganTag.Close());
		Points.add(this.PirateTag.Open() + Pirate + this.PirateTag.Close());
		Points.add(this.TemplarTag.Open() + Templar + this.TemplarTag.Close());
		// String LastWinner =
		// this.Points.getBetween(this.PreviousWinner.Open(),this.PreviousWinner.Close());
		// LastWinner
		// Points.add(this.PreviousWinner.Open() + LastWinner+
		// this.PreviousWinner.Close());
		String Data = "";
		for (String S : Points)
		{
			Data += S + "\r\n";
		}
		this.Points.OverwriteFile(Data);
	}

	public boolean canToggle()
	{
		Long Difference = System.currentTimeMillis() - Long.valueOf(this.Config.getBetween(this.WarTimeTag));
		if (Difference > this.TimeBetween)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public Long getWarTime()
	{
		return Long.parseLong(this.Config.getBetween(this.WarTimeTag.getOpen(), this.WarTimeTag.getClose()));
	}

	public Long getTimeDifference()
	{
		Long TimeDiff = System.currentTimeMillis() - this.getWarTime();
		return this.TimeBetween - TimeDiff;
	}

	public String getWarMessage()
	{
		String TimeMessage = TimeHandler.getDurationBreakdown(this.getTimeDifference());
		if (TimeMessage != null)
		{
			if (this.isEnabled == true)
			{
				return ChatColor.RED + "The War ends in " + ChatColor.YELLOW + TimeMessage;
			}
			else
			{
				return ChatColor.YELLOW + "The War starts in " + ChatColor.RED + TimeMessage;
			}
		}
		else
		{
			return ChatColor.GREEN + "Big Brother took over... Sorry!";
		}
	}

	public void toggleWar()
	{
		this.setWar(!this.isEnabled);
	}
}
