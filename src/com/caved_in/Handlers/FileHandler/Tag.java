package com.caved_in.Handlers.FileHandler;

public class Tag
{
	private String OpenTag = "";
	private String CloseTag = "";

	public Tag(String Tag)
	{
		this.OpenTag = "<" + Tag + ">";
		this.CloseTag = "</" + Tag + ">";
	}

	public String getOpen()
	{
		return this.OpenTag;
	}

	public String getClose()
	{
		return this.CloseTag;
	}

	public String Open()
	{
		return this.OpenTag;
	}

	public String Close()
	{
		return this.CloseTag;
	}
}
