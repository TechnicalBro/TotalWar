package com.caved_in.Handlers.FireworksHandler;

import java.util.Random;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;

public class FireworkSettings
{
	static Random newrandom = new Random();

	FireworkEffectPlayer fplayer = new FireworkEffectPlayer();

	public static FireworkEffect.Type randomType()
	{
		double random1 = newrandom.nextDouble() * 10.0D;

		if (random1 < 2.0D)
		{
			return FireworkEffect.Type.BALL;
		}
		if ((random1 >= 2.0D) && (random1 < 4.0D))
		{
			return FireworkEffect.Type.BURST;
		}
		if ((random1 >= 4.0D) && (random1 < 6.0D))
		{
			return FireworkEffect.Type.CREEPER;
		}
		if ((random1 >= 6.0D) && (random1 < 8.0D))
		{
			return FireworkEffect.Type.STAR;
		}

		return FireworkEffect.Type.BALL_LARGE;
	}

	public static Color randomColor()
	{
		double random2 = newrandom.nextDouble() * 8.0D;
		if (random2 < 1.0D)
		{
			return Color.WHITE;
		}
		if ((random2 >= 1.0D) && (random2 < 2.0D))
		{
			return Color.ORANGE;
		}
		if ((random2 >= 2.0D) && (random2 < 3.0D))
		{
			return Color.BLUE;
		}
		if ((random2 >= 3.0D) && (random2 < 4.0D))
		{
			return Color.YELLOW;
		}
		if ((random2 >= 4.0D) && (random2 < 5.0D))
		{
			return Color.GREEN;
		}
		if ((random2 >= 5.0D) && (random2 < 6.0D))
		{
			return Color.GRAY;
		}
		if ((random2 >= 6.0D) && (random2 < 7.0D))
		{
			return Color.PURPLE;
		}

		return Color.RED;
	}

	public static boolean randomBoolean()
	{
		double random3 = newrandom.nextDouble() * 8.0D;
		if (random3 <= 4.0D)
		{
			return false;
		}

		return true;
	}

	public void playFw(Location location, FireworkEffect fwEffect)
	{
		try
		{
			this.fplayer.playFirework(location.getWorld(), location, fwEffect);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public FireworkEffect randomFireworkEffect()
	{
		return FireworkEffect.builder().with(randomType()).withColor(randomColor()).withFade(randomColor()).trail(randomBoolean()).flicker(randomBoolean()).build();
	}
}