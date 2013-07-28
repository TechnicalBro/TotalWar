package com.caved_in.Handlers.Misc;

import java.util.concurrent.TimeUnit;

public class TimeHandler
{
	public static String getDurationBreakdown(long millis)
	{
		if (millis < 0)
		{
			return null;
		}
		long hours = TimeUnit.MILLISECONDS.toHours(millis);
		millis -= TimeUnit.HOURS.toMillis(hours);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);

		StringBuilder sb = new StringBuilder(64);
		if (hours > 0)
		{
			sb.append(hours);
			sb.append(" hour, ");
		}
		sb.append(minutes);
		sb.append(" mins");

		return (sb.toString());
	}
}
