package com.caved_in.Runnables;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.caved_in.TotalWar;

public class DonateReminderRunnable implements Runnable
{
	@Override
	public void run()
	{
		for (Player P : Bukkit.getOnlinePlayers())
		{
			if (!TotalWar.permission.has(P, "TotalWar.Donator"))
			{
				switch (new Random().nextInt(3))
				{
				case 0:
					P.sendMessage(ChatColor.GOLD + "Have you considered donating? For only 5$ you get 2x EXP and Mob Drops on weekends, a reserved slot so you can play no matter what, and you help us out with keeping the server up!");
					P.sendMessage(ChatColor.GOLD + "If you want to donate, you can go to http://www.shop.caved.in or type '/donate' - Thanks!");
					break;
				case 1:
					P.sendMessage(ChatColor.GOLD + "Hey, " + P.getName() + ", did you know that we now sell EXCULSIVE items in our shop?! For only 2$ you get 6 EPIC Items, including 2 Shop-Exclusive Gems above Level 5, 2 Blades that make enemies " + ChatColor.AQUA + "Shiver " + ChatColor.GOLD + "with " + ChatColor.RED + "Fear" + ChatColor.GOLD + ", and 2 AMAZING Bows that'll kill anything!");
					P.sendMessage(ChatColor.GOLD + "To get these amazing items, go to http://www.shop.caved.in -- Thank you for playing TotalWar!");
					break;
				case 2:
					P.sendMessage(ChatColor.YELLOW + "Do you want to look like a " + ChatColor.BOLD + "badass" + ChatColor.YELLOW + "? Wouldn't it be cool to have " + ChatColor.RED + "molten lava" + ChatColor.YELLOW + " dripping from your armor?");
					P.sendMessage(ChatColor.YELLOW + "For only 50 cents you can be a bad-ass! http://www.shop.caved.in to purchase your cosmetic bad-assery today!");
				default:
					break;
				}
			}
			else
			{
				switch (new Random().nextInt(2))
				{
				case 0:
					P.sendMessage(ChatColor.GOLD + "Hey, " + P.getName() + ", did you know that we now sell EXCULSIVE items in our shop?! For only 2$ you get 6 EPIC Items, including 2 Shop-Exclusive Gems above Level 5, 2 Blades that make enemies " + ChatColor.AQUA + "Shiver " + ChatColor.GOLD + "with " + ChatColor.RED + "Fear" + ChatColor.GOLD + ", and 2 AMAZING Bows that'll kill anything!");
					P.sendMessage(ChatColor.GOLD + "To get these amazing items, go to http://www.shop.caved.in -- Thank you for playing TotalWar!");
					break;
				case 1:
					P.sendMessage(ChatColor.YELLOW + "Do you want to look like a " + ChatColor.BOLD + "badass" + ChatColor.YELLOW + "? Wouldn't it be cool to have " + ChatColor.RED + "molten lava" + ChatColor.YELLOW + " dripping from your armor?");
					P.sendMessage(ChatColor.YELLOW + "For only 50 cents you can be a bad-ass! http://www.shop.caved.in to purchase your cosmetic bad-assery today!");
				default:
					break;
				}
			}
		}
	}
}