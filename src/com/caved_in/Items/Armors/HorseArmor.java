package com.caved_in.Items.Armors;

import java.util.Random;

import me.cybermaxke.materialapi.material.CustomMaterial;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class HorseArmor extends CustomMaterial
{

	public HorseArmor(String id, Material material)
	{
		super(id, material);
		this.setType(this.getRandomBarding());
	}

	public Material getRandomBarding()
	{
		switch (new Random().nextInt(3))
		{
		case 0:
			return Material.IRON_BARDING;
		case 1:
			return Material.GOLD_BARDING;
		case 2:
			return Material.DIAMOND_BARDING;
		default:
			return Material.IRON_BARDING;
		}
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
	public void onBlockInteract(PlayerInteractEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onBlockPlaced(BlockPlaceEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onDrop(PlayerDropItemEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onHit(EntityDamageByEntityEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onHold(PlayerItemHeldEvent arg0)
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

}
