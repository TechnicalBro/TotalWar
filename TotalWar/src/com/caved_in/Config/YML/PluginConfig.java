package com.caved_in.Config.YML;

public class PluginConfig
{
	private YML_Wrapper Config;
	private String Event_Amount_Path = "AmountOfEvents";
	private String World_Name_Path = "WorldName";
	private String Event_Refresh_Path = "EventRefreshTicks";
	private String Server_Message_Path = "ServerListingMessage";
	private String Mob_Health_Multiplier_Path = "MobHealthMultiplier";
	private String RareCraftIncreasePerLevelPath = "RareCraftIncreasePerLevel";
	private String DonatorRareCraftIncreasePath = "DonatorRareCraftIncrease";
	private String HiddenPotentialAppraisalPricePath = "HiddenPotentialAppraisalPrice";
	private String DonatorSkillExpMultiplierPath = "DonatorSkillExpMultiplier";
	private String BossDropIncreasePath = "BossDropIncrease";

	public PluginConfig(String Config_Location)
	{
		this.Config = new YML_Wrapper(Config_Location);
	}

	public int getEventAmout()
	{
		return this.Config.getInt(this.Event_Amount_Path);
	}

	public String getWorldName()
	{
		return this.Config.getString(this.World_Name_Path);
	}

	public long getEventRefresh()
	{
		return this.Config.getLong(this.Event_Refresh_Path);
	}

	public String getServerMessage()
	{
		return this.Config.getString(this.Server_Message_Path);
	}

	public int getMobHealthMultiplier()
	{
		return this.Config.getInt(this.Mob_Health_Multiplier_Path);
	}

	public double getRareCraftLevelIncrease()
	{
		return this.Config.getDouble(this.RareCraftIncreasePerLevelPath);
	}

	public double getDonatorRareCraftIncrease()
	{
		return this.Config.getDouble(this.DonatorRareCraftIncreasePath);
	}

	public double getHiddenPotentialAppraisalPrice()
	{
		return this.Config.getDouble(this.HiddenPotentialAppraisalPricePath);
	}

	public double getDonatorSkillExpMultiplier()
	{
		return this.Config.getDouble(this.DonatorSkillExpMultiplierPath);
	}

	public double getBossDropIncrease()
	{
		return this.Config.getDouble(this.BossDropIncreasePath);
	}
}