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
import com.caved_in.ItemMenus.SpellbindMenu.SpellbindMenu;
import com.caved_in.TotalWarItems.TotalWarItems;

public class SpellBinder extends Trait
{
	private Cooldown PlayersOnCooldown = new Cooldown(3);
	private String[] Dialog = new String[] { "Allo; Would you like some spell-bound items?" };//, I can appraise them for you, but at $" + TotalWar.Plugin_Config.getHiddenPotentialAppraisalPrice() + " each! Want em' appraised?" };

	public SpellBinder()
	{
		super("spellbinder");
	}

	@EventHandler
	public void EventNPC_Rightclick(NPCRightClickEvent Event)
	{
		if (!isThisNpc(Event.getNPC()))
		{
			Event.setCancelled(true);
			return;
		}
		//SpellBind_Handler(Event.getClicker(),Event.getNPC().getName());
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
				Event.getClicker().sendMessage(NpcText(Event.getNPC().getName(),S));
			}
			PlayersOnCooldown.SetOnCooldown(Event.getClicker().getName());
		}
	}
	
	public void SpellBind_Handler(Player Player, String NPC)
	{
		if (Player.getItemInHand() != null)
		{
			if (!TotalWizard.TotalWizard.MagicHandler.isWand(Player.getItemInHand()))
			{
				new SpellbindMenu(Player);
			}
			else
			{
				Player.sendMessage(NpcText(NPC,"That's already got a spell bound to it; You can't add another!"));
			}
		}
		else
		{
			Player.sendMessage(NpcText(NPC,"You don't have anything in your hand!"));
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
			TotalWar.NpcTraitConfig.WriteData(this.getNPC().getId(), "spellbinder");
			TotalWar.Console("Cached the spellbind trait for " + this.getNPC().getName());
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
			TotalWar.NpcTraitConfig.WriteData(this.getNPC().getId(), "spellbinder");
			TotalWar.Console("Cached the spellbind trait for " + this.getNPC().getName());
		}
	}

	// run code when the NPC is removed. Use this to tear down any repeating
	// tasks.
	@Override
	public void onRemove()
	{

	}
}