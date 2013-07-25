package com.caved_in.Config;

public class TravelConfig
{
	private YML_Wrapper Wrapper;
	private String Places_Path = "Locations.";
	private String Place_Location_X = ".X";
	private String Place_Location_Y = ".Y";
	private String Place_Location_Z = ".Z";

	public TravelConfig(String YMLLocation)
	{
		this.Wrapper = new YML_Wrapper(YMLLocation);
	}

	public double[] getXYZ(String Location_Name)
	{
		return new double[] { this.Wrapper.getDouble(this.Places_Path + Location_Name + this.Place_Location_X), this.Wrapper.getDouble(this.Places_Path + Location_Name + this.Place_Location_Y), this.Wrapper.getDouble(this.Places_Path + Location_Name + this.Place_Location_Z) };
	}

	public void setXYZ(String Location_Name, double[] XYZ)
	{
		setXYZ(Location_Name, XYZ[0], XYZ[1], XYZ[2]);
	}

	public void setXYZ(String Location_Name, double X, double Y, double Z)
	{
		this.Wrapper.setDouble(this.Places_Path + Location_Name.toLowerCase() + this.Place_Location_X, X);
		this.Wrapper.setDouble(this.Places_Path + Location_Name.toLowerCase() + this.Place_Location_Y, Y);
		this.Wrapper.setDouble(this.Places_Path + Location_Name.toLowerCase() + this.Place_Location_Z, Z);
	}

	public boolean locationExists(String Location_Name)
	{
		if (this.Wrapper.Contains(this.Places_Path + Location_Name.toLowerCase()))
		{
			return true;
		}
		return false;
	}
}

/*
 * Location: C:\Users\Brandon\Desktop\TotalWar.jar Qualified Name:
 * com.caved_in.Config.TravelConfig JD-Core Version: 0.6.2
 */