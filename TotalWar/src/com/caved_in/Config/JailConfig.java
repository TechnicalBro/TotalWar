package com.caved_in.Config;

import com.caved_in.Handlers.PlayerHandlers.PlayerHandler;

public class JailConfig
{
	private YML_Wrapper Wrapper;
	private String Jail_X = "Jail.X";
	private String Jail_Y = "Jail.Y";
	private String Jail_Z = "Jail.Z";
	private String Templar_Path = ".Templar";
	private String Pagan_Path = ".Pagan";
	private String Pirate_Path = ".Pirate";
	private String Dragon_Path = ".Dragonkin";

	public JailConfig(String FileLocation)
	{
		this.Wrapper = new YML_Wrapper(FileLocation);
	}

	private String getFactionPath(PlayerHandler.Faction Faction)
	{
		switch (Faction)
		{
		case Pirate:
			return this.Dragon_Path;
		case Templar:
			return null;
		case None:
			return this.Pagan_Path;
		case Pagan:
			return this.Pirate_Path;
		case Dragonkin:
			return this.Templar_Path;
		}
		return null;
	}

	public void setJail(double x, double y, double z, String FactionPrefix)
	{
		this.Wrapper.setDouble(this.Jail_X + FactionPrefix, x);
		this.Wrapper.setDouble(this.Jail_Y + FactionPrefix, y);
		this.Wrapper.setDouble(this.Jail_Z + FactionPrefix, z);
	}

	public void setJail(double x, double y, double z, PlayerHandler.Faction Faction)
	{
		setJail(x, y, z, getFactionPath(Faction));
	}

	public double[] getJail(PlayerHandler.Faction Faction)
	{
		switch (Faction)
		{
		case None:
			return new double[] { this.Wrapper.getDouble(this.Jail_X + this.Pagan_Path), this.Wrapper.getDouble(this.Jail_Y + this.Pagan_Path), this.Wrapper.getDouble(this.Jail_Z + this.Pagan_Path) };
		case Dragonkin:
			return new double[] { this.Wrapper.getDouble(this.Jail_X + this.Templar_Path), this.Wrapper.getDouble(this.Jail_Y + this.Templar_Path), this.Wrapper.getDouble(this.Jail_Z + this.Templar_Path) };
		case Pagan:
			return new double[] { this.Wrapper.getDouble(this.Jail_X + this.Pirate_Path), this.Wrapper.getDouble(this.Jail_Y + this.Pirate_Path), this.Wrapper.getDouble(this.Jail_Z + this.Pirate_Path) };
		case Pirate:
			return new double[] { this.Wrapper.getDouble(this.Jail_X + this.Dragon_Path), this.Wrapper.getDouble(this.Jail_Y + this.Dragon_Path), this.Wrapper.getDouble(this.Jail_Z + this.Dragon_Path) };
		default:
			break;
		}
		return null;
	}
}