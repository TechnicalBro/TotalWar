package com.caved_in.Items.Armors;

import java.util.Random;

import me.cybermaxke.materialapi.material.CustomMaterial;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

import com.caved_in.TotalWarItems.Handlers.ItemStackHandler.Tier;

public class LeatherPants extends CustomMaterial
{
	private String[] Prefixes_Common = { "Ass-Less", "Mediocre", "Worn", "Damaged", "Tired", "Second Hand", "Home-Made" };
	private String[] Prefixes_Rare = { "Sturdy", "Premium", "Well-Built", "Fine" };
	private String[] Prefixes_Epic = { "Perfect", "Excellent", "Steel Enforced" };
	private String[] Suffix_Common = { "Nate", "Brandon", "Pride", "Adventuring", "the Adventurer", "Minor Defence", "Beginners", "the Noob", "Newbies" };
	private String[] Suffix_Rare = { "the Adventurer", "Adventuring", "the Bear", "Kinship", "Knights", "the Samurai", "Samurai-Jack" };
	private String[] Suffix_Epic = { "Solid Defence", "Zeus", "Unmoving", "Eternity" };
	private String Weapon_Name = " Leather Chaps";
	private String Weapon_Seperator = " of ";
	private int Common_Enchant_Chance = 10;
	private int Rare_Enchant_Chance = 40;
	private int Epic_Enchant_Chance = 100;
	private ChatColor CommonColor = ChatColor.GREEN;
	private ChatColor RareColor = ChatColor.AQUA;
	private ChatColor EpicColor = ChatColor.GOLD;

	private Enchantment[] Enchants = { Enchantment.DURABILITY, Enchantment.PROTECTION_FALL, Enchantment.PROTECTION_PROJECTILE, Enchantment.PROTECTION_FALL };

	public LeatherPants(String id, Material material, Tier Item_Quality)
	{
		super(id, Material.LEATHER_LEGGINGS);
		String Name = Generate_Name(Item_Quality);

		if (Item_Quality == Tier.Common)
		{
			setName(this.CommonColor + Name);
		}
		else if (Item_Quality == Tier.Rare)
		{
			setName(this.RareColor + Name);
		}
		else if (Item_Quality == Tier.Epic)
		{
			setName(this.EpicColor + Name);
		}

		int Damage = Calculate_Damage(Item_Quality);
		setDamage(Damage);
		Enchantment[] ItemEnchants = Generate_Enchants(Item_Quality);
		if (ItemEnchants != null)
		{
			for (Enchantment i : ItemEnchants)
			{
				addEnchantment(i, new Random().nextInt(5) + 1);
			}
		}

		setLore(new String[] { ChatColor.RED + "Raises defences by " + Damage + "!" });
	}

	private String Generate_Name(Tier ItemTier)
	{
		if (ItemTier == Tier.Common)
		{
			if (new Random().nextInt(100) <= 30)
			{
				return this.Prefixes_Common[new Random().nextInt(this.Prefixes_Common.length)] + this.Weapon_Name + this.Weapon_Seperator + this.Suffix_Common[new Random().nextInt(this.Suffix_Common.length)];
			}

			return this.Prefixes_Common[new Random().nextInt(this.Prefixes_Common.length)] + this.Weapon_Name;
		}

		if (ItemTier == Tier.Rare)
		{
			return this.Prefixes_Rare[new Random().nextInt(this.Prefixes_Rare.length)] + this.Weapon_Name + this.Weapon_Seperator + this.Suffix_Rare[new Random().nextInt(this.Suffix_Rare.length)];
		}
		if (ItemTier == Tier.Epic)
		{
			return this.Prefixes_Epic[new Random().nextInt(this.Prefixes_Epic.length)] + this.Weapon_Name + this.Weapon_Seperator + this.Suffix_Epic[new Random().nextInt(this.Suffix_Epic.length)];
		}
		return this.Weapon_Name;
	}

	private int Calculate_Damage(Tier ItemTier)
	{
		if (ItemTier == Tier.Common)
		{
			return new Random().nextInt(3) + new Random().nextInt(10) - 1 + 2;
		}
		if (ItemTier == Tier.Rare)
		{
			return new Random().nextInt(5) + new Random().nextInt(20) - 1 + 3;
		}
		if (ItemTier == Tier.Epic)
		{
			return new Random().nextInt(8) + new Random().nextInt(30) - 1 + 4;
		}
		return 1;
	}

	private Enchantment[] Generate_Enchants(Tier ItemTier)
	{
		if (ItemTier == Tier.Common)
		{
			if (PercentCheck(this.Common_Enchant_Chance))
			{
				return new Enchantment[] { this.Enchants[new Random().nextInt(this.Enchants.length)] };
			}
			return null;
		}
		if (ItemTier == Tier.Rare)
		{
			if (PercentCheck(this.Rare_Enchant_Chance))
			{
				return new Enchantment[] { this.Enchants[new Random().nextInt(this.Enchants.length)] };
			}
			return null;
		}
		if (ItemTier == Tier.Epic)
		{
			if (PercentCheck(this.Epic_Enchant_Chance))
			{
				return new Enchantment[] { this.Enchants[new Random().nextInt(this.Enchants.length)], this.Enchants[new Random().nextInt(this.Enchants.length)] };
			}
			return null;
		}
		return null;
	}

	private boolean PercentCheck(int Chances)
	{
		if (new Random().nextInt(100) <= Chances)
		{
			return true;
		}
		return false;
	}

	@Override
	public void onBlockBreak(BlockBreakEvent arg0)
	{
	}

	@Override
	public void onBlockDamage(BlockDamageEvent arg0)
	{
	}

	@Override
	public void onBlockInteract(PlayerInteractEvent arg0)
	{
	}

	@Override
	public void onBlockPlaced(BlockPlaceEvent arg0)
	{
	}

	@Override
	public void onHit(EntityDamageByEntityEvent Event)
	{
	}

	@Override
	public void onInteract(PlayerInteractEvent arg0)
	{
	}

	@Override
	public void onInteractEntity(PlayerInteractEntityEvent arg0)
	{
	}

	@Override
	public void onHold(PlayerItemHeldEvent arg0)
	{
	}

	@Override
	public void onDrop(PlayerDropItemEvent arg0)
	{
	}
}

/*
 * Location: C:\Users\Brandon\Desktop\TotalWar.jar Qualified Name:
 * com.caved_in.Items.Armors.LeatherPants JD-Core Version: 0.6.2
 */