package com.caved_in.Handlers.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class DataHandler
{
	private String FilePath = "";

	public DataHandler(String FilePath)
	{
		this.FilePath = FilePath;
	}

	public DataHandler()
	{

	}

	public void OverwriteFile(String Data)
	{
		try
		{
			FileUtils.writeStringToFile(new File(this.FilePath), Data + "\r\n", false);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void AppendString(String Data)
	{
		try
		{
			FileUtils.writeStringToFile(new File(this.FilePath), Data + "\r\n", true);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public List<String> getContentsAsList()
	{
		try
		{
			return FileUtils.readLines(new File(this.FilePath));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static String getStringBetween(String Data, String Start, String End)
	{
		return StringUtils.substringBetween(Data, Start, End);
	}
	
	public static String getStringBetween(String Data, Tag Tag)
	{
		return getStringBetween(Data,Tag.Open(),Tag.Close());
	}

	public String getBetween(String Start, String End)
	{
		return getStringBetween(getText(), Start, End);
	}

	public String getBetween(Tag Tag)
	{
		return getStringBetween(getText(), Tag.getOpen(), Tag.getClose());
	}

	public static List<String> getAllBetween(String Data, String Start, String End)
	{
		return Arrays.asList(StringUtils.substringsBetween(Data, Start, End));
	}
	
	public static List<String> getAllBetween(String Data, Tag Tag)
	{
		return getAllBetween(Data,Tag.Open(),Tag.Close());
	}

	public List<String> getAllBetween(String Start, String End)
	{
		try
		{
			return Arrays.asList(StringUtils.substringsBetween(FileUtils.readFileToString(new File(this.FilePath)), Start, End));
		}
		catch (IOException e)
		{
			return null;
		}
	}

	public boolean Contains(String Text)
	{
		return getContentsAsList().contains(Text);
	}

	public String getText()
	{
		try
		{
			return FileUtils.readFileToString(new File(this.FilePath));
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}