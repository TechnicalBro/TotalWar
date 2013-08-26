package com.caved_in.Handlers.EntityHandlers;

import java.util.ArrayList;
import java.util.List;

import me.cybermaxke.materialapi.inventory.CustomItemStack;

import org.bukkit.entity.EntityType;

import com.caved_in.Handlers.FileHandler.DataHandler;
import com.caved_in.Handlers.FileHandler.Tag;
import com.caved_in.TotalWarItems.TotalWarItems;
import com.caved_in.TotalWarItems.Handlers.Items.ItemStackHandler.Tier;

public class MobdropHandler
{
	private static Tag DropTag = new Tag("Drop");
	private static Tag IDTag = new Tag("ID");
	private static Tag TierTag = new Tag("Tier");
	private static Tag ArmorTag = new Tag("IsArmor");
	
	public static List<CustomItemStack> Drops(EntityType EntityType)
	{
		DataHandler DropsFile;
		List<CustomItemStack> Returns = new ArrayList<CustomItemStack>();
		switch (EntityType)
		{
			case BLAZE:
				DropsFile = new DataHandler("plugins/TotalWar/Mobdrops/Blaze.txt");
				break;
			case CAVE_SPIDER:
				DropsFile = new DataHandler("plugins/TotalWar/Mobdrops/CaveSpider.txt");
				break;
			case CREEPER:
				DropsFile = new DataHandler("plugins/TotalWar/Mobdrops/Creeper.txt");
				break;
			case GHAST:
				DropsFile = new DataHandler("plugins/TotalWar/Mobdrops/Ghast.txt");
				break;
			case GIANT:
				DropsFile = new DataHandler("plugins/TotalWar/Mobdrops/Giant.txt");
				break;
			case MAGMA_CUBE:
				DropsFile = new DataHandler("plugins/TotalWar/Mobdrops/LavaSlime.txt");
				break;
			case PIG_ZOMBIE:
				DropsFile = new DataHandler("plugins/TotalWar/Mobdrops/PigZombie.txt");
				break;
			case SKELETON:
				DropsFile = new DataHandler("plugins/TotalWar/Mobdrops/Skeleton.txt");
				break;
			case SPIDER:
				DropsFile = new DataHandler("plugins/TotalWar/Mobdrops/Spider.txt");
				break;
			case WITCH:
				DropsFile = new DataHandler("plugins/TotalWar/Mobdrops/Witch.txt");
				break;
			case ZOMBIE:
				DropsFile = new DataHandler("plugins/TotalWar/Mobdrops/Zombie.txt");
				break;
			case SLIME:
				DropsFile = new DataHandler("plugins/TotalWar/Mobdrops/Slime.txt");
				break;
			default:
				DropsFile = null;
				break;
		}
		if (DropsFile != null)
		{
			List<String> ItemBlocks = DropsFile.getAllBetween(DropTag.Open(), DropTag.Close());
			for (String Item : ItemBlocks)
			{
				if (DataHandler.getStringBetween(Item, ArmorTag).equalsIgnoreCase("true"))
				{
					CustomItemStack cItem = TotalWarItems.ItemHandler.getArmor(DataHandler.getStringBetween(Item, IDTag));
					if (cItem != null)
					{
						Returns.add(cItem);
					}
				}
				else
				{
					CustomItemStack cItem;
					if (DataHandler.getStringBetween(Item, TierTag).equalsIgnoreCase("any"))
					{
						cItem = TotalWarItems.ItemHandler.getCustomItemStack(DataHandler.getStringBetween(Item, IDTag));
					}
					else
					{
						Tier ItemTier = TotalWarItems.ItemHandler.getTier(DataHandler.getStringBetween(Item, TierTag));
						cItem = TotalWarItems.ItemHandler.getCustomItemStack(DataHandler.getStringBetween(Item, IDTag), ItemTier);
					}

					if (cItem != null)
					{
						Returns.add(cItem);
					}
				}
			}
			return Returns;
		}
		else
		{
			return null;
		}
	}

}
