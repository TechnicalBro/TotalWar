package com.caved_in.CitizenTraits;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import me.cybermaxke.materialapi.inventory.CustomItemStack;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;

import com.caved_in.TotalWar;
import com.caved_in.Cooldown.Cooldown;
import com.caved_in.Handlers.ItemHandlers.ItemHandler;
import com.caved_in.TotalWarItems.TotalWarItems;

public class ItemIdentifierNPCTrait extends Trait
{
	private Cooldown PlayersOnCooldown = new Cooldown(3);
	private String[] Dialog = new String[] { "Hey there, If you've got any items with hidden potential, I can appraise them for you, but at $" + TotalWar.Plugin_Config.getHiddenPotentialAppraisalPrice() + " each! Want em' appraised?" };
	private int Donator_Epic_Chance = 5;

	public ItemIdentifierNPCTrait()
	{
		super("appraiser");
	}

	@EventHandler
	public void EventNPC_Rightclick(NPCRightClickEvent Event)
	{
		if (!isThisNpc(Event.getNPC()))
		{
			Event.setCancelled(true);
			return;
		}
		Handle_Potential(Event.getClicker(), Event.getNPC().getName());
	}

	private String NpcText(String NPCName, String Input)
	{
		return ChatColor.GREEN + "[" + NPCName + "] -> You: " + Input;
	}

	@EventHandler
	public void EventNPC_Leftclick(NPCLeftClickEvent Event)
	{
		if (!isThisNpc(Event.getNPC()))
		{
			Event.setCancelled(true);
			return;
		}
		if (!PlayersOnCooldown.IsOnCooldown(Event.getClicker().getName()))
		{
			for (String S : Dialog)
			{
				Event.getClicker().sendMessage(S);
			}
			PlayersOnCooldown.SetOnCooldown(Event.getClicker().getName());
		}
	}

	public void Handle_Potential(Player Player, String NPC)
	{
		if (Player.getItemInHand() != null && Player.getItemInHand().hasItemMeta() == true)
		{
			ItemStack Item = Player.getItemInHand();
			if (ItemHandler.itemLoreContains(Item, "hidden potential") == true)
			{
				if (TotalWar.economy.has(Player.getName(), TotalWar.Plugin_Config.getHiddenPotentialAppraisalPrice()) == true)
				{
					TotalWar.economy.withdrawPlayer(Player.getName(), TotalWar.Plugin_Config.getHiddenPotentialAppraisalPrice());
					Unveil_Potential(Player);
				}
				else
				{
					Player.sendMessage(NpcText(NPC, "You don't seem to have the $" + TotalWar.Plugin_Config.getHiddenPotentialAppraisalPrice() + " needed for an appraisal!"));
				}
			}
			else
			{
				Player.sendMessage(NpcText(NPC, "That item doesn't need an appraisal!"));
			}
		}
	}

	public void Unveil_Potential(Player Player)
	{
		ItemStack Hand = Player.getItemInHand();
		switch (Hand.getType())
		{
		case WOOD_HOE:
		case STONE_HOE:
		case DIAMOND_HOE:
		case IRON_HOE:
		case GOLD_HOE:
		case WOOD_SWORD:
		case STONE_SWORD:
		case IRON_SWORD:
		case GOLD_SWORD:
		case DIAMOND_SWORD:
			String ItemName = ChatColor.GOLD + TotalWarItems.ItemHandler.getGeneratedName("Blade");
			String Damages = "";
			List<String> Effects = TotalWarItems.ItemHandler.getEffectLoreLines("Blade");
			for (String S : Hand.getItemMeta().getLore())
			{
				String LoreLine = S.toLowerCase();
				if ((LoreLine.contains("deals")) && (LoreLine.contains("to")) && (LoreLine.contains("damage!")))
				{
					int DamageMin = Integer.parseInt(StringUtils.substringBetween(S.toLowerCase(), "deals ", " to "));
					int DamageMax = Integer.parseInt(StringUtils.substringBetween(S.toLowerCase(), " to ", " damage!"));
					int dRange = DamageMax - DamageMin;
					DamageMin += 3 + new Random().nextInt(10);
					DamageMax += 13 + new Random().nextInt(10);
					DamageMax += new Random().nextInt(dRange);
					Damages = ChatColor.RED + "Deals " + DamageMin + " to " + DamageMax + " damage!";
					break;
				}
				else
				{
					int DamageMin = 10;
					int DamageMax = 30;
					int dRange = DamageMax - DamageMin;
					DamageMin += 3 + new Random().nextInt(10);
					DamageMax += 13 + new Random().nextInt(10);
					DamageMax += new Random().nextInt(dRange);
					Damages = ChatColor.RED + "Deals " + DamageMin + " to " + DamageMax + " damage!";
					break;
				}
			}
			CustomItemStack Item = new CustomItemStack(Hand.getType());
			if (Hand.getEnchantments().size() > 0)
			{
				Item.addEnchantments(Hand.getEnchantments());
			}
			Item.addLore(Damages);
			Item.addLore(Effects);
			Item.setName(ItemName);
			SetHandItem(Player, Item);
			break;
		case LEATHER_LEGGINGS:
		case LEATHER_HELMET:
		case LEATHER_CHESTPLATE:
		case LEATHER_BOOTS:
		case IRON_LEGGINGS:
		case IRON_HELMET:
		case IRON_CHESTPLATE:
		case IRON_BOOTS:
		case GOLD_LEGGINGS:
		case GOLD_HELMET:
		case GOLD_CHESTPLATE:
		case GOLD_BOOTS:
		case DIAMOND_LEGGINGS:
		case DIAMOND_HELMET:
		case DIAMOND_CHESTPLATE:
		case DIAMOND_BOOTS:
			String[] HPBonus = new String[] { "Increases Max HP by ", "!" };
			int CurrentBonus = TotalWarItems.ArmorHandler.getHealthBonus(Hand, Player);
			CurrentBonus += (15 + new Random().nextInt(CurrentBonus));
			CustomItemStack Armor = new CustomItemStack(Hand.getType());
			if (Hand.getEnchantments().size() > 0)
			{
				Armor.addEnchantments(Hand.getEnchantments());
			}
			Armor.setLore(Arrays.asList(new String[] { ChatColor.RED + HPBonus[0] + CurrentBonus + HPBonus[1] }));
			SetHandItem(Player, Armor);
			break;
		case BOW:
			break;
		case ARROW:
			break;
		default:
			break;
		}
	}

	private void SetHandItem(Player Player, CustomItemStack Stack)
	{
		Player.setItemInHand(Stack.getItem());
	}

	private boolean PercentCheck(int Chances)
	{
		if (new Random().nextInt(100) <= Chances)
		{
			return true;
		}
		return false;
	}

	private boolean isThisNpc(NPC Npc)
	{
		return Npc == this.getNPC();
	}

	// Called Every Tick
	@Override
	public void run()
	{

	}

	// Run code when your trait is attached to a NPC.
	// This is called BEFORE onSpawn, so npc.getBukkitEntity() will return null
	// This would be a good place to load configurable defaults for new NPCs.
	@Override
	public void onAttach()
	{
		if (!TotalWar.NpcTraitConfig.hasData(this.getNPC().getId()))
		{
			TotalWar.NpcTraitConfig.WriteData(this.getNPC().getId(), "appraiser");
			TotalWar.Console("Cached the appraiser trait for " + this.getNPC().getName());
		}
	}

	@Override
	public void onDespawn()
	{

	}

	// Run code when the NPC is spawned. Note that npc.getBukkitEntity() will be
	// null until this method is called.
	// This is called AFTER onAttach and AFTER Load when the server is started.
	@Override
	public void onSpawn()
	{
		if (!TotalWar.NpcTraitConfig.hasData(this.getNPC().getId()))
		{
			TotalWar.NpcTraitConfig.WriteData(this.getNPC().getId(), "appraiser");
			TotalWar.Console("Cached the appraiser trait for " + this.getNPC().getName());
		}
	}

	// run code when the NPC is removed. Use this to tear down any repeating
	// tasks.
	@Override
	public void onRemove()
	{

	}
}