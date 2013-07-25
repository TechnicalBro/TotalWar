package com.caved_in.Items;

import java.util.Random;

import me.cybermaxke.materialapi.material.CustomMaterial;

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

public class ArmorGem extends CustomMaterial
{
	private String[] Gem_Prefixes = { "Emaculate Gem of ", "Pristine Gem of ", "Untainted Gem of ", "Magnificent Gem of ", "Enriched Gem of ", "Perfected Gem of ", "Polished Gem of ", "Refined Gem of ", "Cracked Gem of ", "Crude Gem of " };
	private String[] Enchantment_Names = { "Fire Protection", "Offensive Defenses", "Weight Reduction", "Lead Defenses", "Projectile Reflection", "Tanking" };
	private Enchantment[] Enchants = { Enchantment.PROTECTION_FIRE, Enchantment.THORNS, Enchantment.PROTECTION_FALL, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_PROJECTILE, Enchantment.PROTECTION_ENVIRONMENTAL };
	private int[] Enchantment_Levels = { 12, 11, 10, 8, 6, 5, 4, 3, 2, 1 };

	public ArmorGem(String id, Material material)
	{
		super(id, material);
		setType(Material.EMERALD);
		Generate_Enchantments();
	}

	private void Generate_Enchantments()
	{
		int Generated_Enchantment = new Random().nextInt(this.Enchants.length);
		int Generated_Level = new Random().nextInt(this.Enchantment_Levels.length);
		Enchantment Enchant_Type = this.Enchants[Generated_Enchantment];
		setName(this.Gem_Prefixes[Generated_Level] + this.Enchantment_Names[Generated_Enchantment]);
		addEnchantment(Enchant_Type, this.Enchantment_Levels[Generated_Level]);
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
	public void onHit(EntityDamageByEntityEvent arg0)
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
 * com.caved_in.Items.Gem JD-Core Version: 0.6.2
 */