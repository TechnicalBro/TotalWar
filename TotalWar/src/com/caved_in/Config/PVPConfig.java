package com.caved_in.Config;

public class PVPConfig
{
	private YML_Wrapper Wrapper;
	private String PVP_Status_Path = ".PVP.Enabled";

	public PVPConfig(String FileLocation)
	{
		this.Wrapper = new YML_Wrapper(FileLocation);
	}

	public boolean IsPvpEnabled(String Playername)
	{
		return this.Wrapper.getBoolean(Playername + this.PVP_Status_Path);
	}

	public void setPvpStatus(String Playername, boolean Enabled)
	{
		this.Wrapper.setBoolean(Playername + this.PVP_Status_Path, Enabled);
	}
}

/*
 * Location: C:\Users\Brandon\Desktop\TotalWar.jar Qualified Name:
 * com.caved_in.Config.PVPConfig JD-Core Version: 0.6.2
 */