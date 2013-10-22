package com.caved_in.Items.Armors;

import java.awt.Color;

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

public class Dragonkin_Legs extends CustomMaterial
{

	private ChatColor Name_Color = ChatColor.RED;
	private String Item_Name = "DragonKin Platelegs";
	private String[] Item_Lore = new String[] { "Constructed from the kneecaps of Jodies fallen foes", "Legend says a full set of dragonkin armor will have wonderful effects" };
	private Enchantment[] Enchants = new Enchantment[] { Enchantment.DURABILITY, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_FALL, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE, Enchantment.THORNS };

	public Dragonkin_Legs(String id, Material material)
	{
		super(id, material);
		this.setType(Material.LEATHER_LEGGINGS);
		this.setColor(Color.RED);
		this.setName(Name_Color + Item_Name);
		for (Enchantment Enchant : this.Enchants)
		{
			this.addEnchantment(Enchant, 5);
		}
		this.setLore(this.Item_Lore);
	}

	@Override
	public void onBlockBreak(BlockBreakEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onBlockDamage(BlockDamageEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onBlockInteract(PlayerInteractEvent Event)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void onBlockPlaced(BlockPlaceEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onHit(EntityDamageByEntityEvent Event)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onInteract(PlayerInteractEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onInteractEntity(PlayerInteractEntityEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onHold(PlayerItemHeldEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onDrop(PlayerDropItemEvent arg0)
	{
		// TODO Auto-generated method stub

	}

}
