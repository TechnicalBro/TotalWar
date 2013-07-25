package com.caved_in.Handlers.EntityHandlers;

import java.util.ArrayList;
import java.util.List;

import me.cybermaxke.materialapi.inventory.CustomItemStack;

import org.bukkit.entity.EntityType;

import com.caved_in.Handlers.FileHandler.DataHandler;
import com.caved_in.Handlers.FileHandler.Tag;
import com.caved_in.TotalWarItems.TotalWarItems;
import com.caved_in.TotalWarItems.Handlers.ItemStackHandler.Tier;

public class MobdropHandler
{
	private Tag DropTag = new Tag("Drop");
	private Tag IDTag = new Tag("ID");
	private Tag TierTag = new Tag("Tier");
	private Tag ArmorTag = new Tag("IsArmor");

	public MobdropHandler()
	{
	}

	public List<CustomItemStack> Drops(EntityType EntityType)
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
				if (DropsFile.getStringBetween(Item, ArmorTag.Open(), ArmorTag.Close()).equalsIgnoreCase("true"))
				{
					CustomItemStack cItem = TotalWarItems.ItemHandler.getArmor(DropsFile.getStringBetween(Item, IDTag.Open(), IDTag.Close()));
					if (cItem != null)
					{
						Returns.add(cItem);
					}
				}
				else
				{
					CustomItemStack cItem;
					if (DropsFile.getStringBetween(Item, TierTag.Open(), TierTag.Close()).equalsIgnoreCase("any"))
					{
						cItem = TotalWarItems.ItemHandler.getCustomItemStack(DropsFile.getStringBetween(Item, IDTag.Open(), IDTag.Close()));
					}
					else
					{
						Tier ItemTier = TotalWarItems.ItemHandler.getTier(DropsFile.getStringBetween(Item, TierTag.Open(), TierTag.Close()));
						cItem = TotalWarItems.ItemHandler.getCustomItemStack(DropsFile.getStringBetween(Item, IDTag.Open(), IDTag.Close()), ItemTier);
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
