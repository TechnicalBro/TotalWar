package com.caved_in.Handlers.Misc;

import java.util.List;
import java.util.Random;

import com.caved_in.Handlers.FileHandler.DataHandler;

public class MOTDHandler
{
	private DataHandler Data;

	public MOTDHandler(String Location)
	{
		Data = new DataHandler(Location);
	}

	public List<String> getRawMessages()
	{
		return this.Data.getAllBetween("<Message>", "</Message>");
	}

	public String getRandomMotd()
	{
		Random Random = new Random();
		String Message = getRawMessages().get(Random.nextInt(getRawMessages().size()));
		String Translated = formatColors(Message);
		return Translated;
	}

	public String ParseValues(String Text)
	{
		return Text;
	}

	public String formatColors(String str)
	{
		char[] chars = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f', 'n', 'r', 'l', 'k', 'o', 'm' };
		char[] array = str.toCharArray();
		for (int t = 0; t < array.length - 1; t++)
		{
			if (array[t] == '&')
			{
				for (char c : chars)
				{
					if (c == array[t + 1])
					{
						array[t] = '§';
					}
				}
			}
		}
		return new String(array);
	}

}
