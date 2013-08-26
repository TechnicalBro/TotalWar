package com.caved_in.Events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.cybermaxke.materialapi.inventory.CustomItemStack;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import com.caved_in.TotalWar;
import com.caved_in.Handlers.DynamicEvents.AreaEvent;
import com.caved_in.Handlers.EntityHandlers.MobDropRate;
import com.caved_in.Handlers.EntityHandlers.MobdropHandler;
import com.caved_in.Handlers.Misc.MiscUtils;
import com.caved_in.Handlers.PlayerHandlers.PlayerHandler;
import com.caved_in.Items.Gem;

public class MobDeath implements Listener
{
	public double Donator_Drop_Increase = 3.0;
	private ArrayList<String> PlayersWhoKilledABoss = new ArrayList<String>();

	public MobDeath(TotalWar Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void EntityDeath(EntityDeathEvent Event)
	{
		Event.setDroppedExp(0);
		if ((Event.getEntity().getKiller() instanceof Player))
		{
			//Check if a boss died
			if (Event.getEntity() instanceof Damageable)
			{
				if (TotalWar.BossHandler.isBoss(Event.getEntity()))
				{
					PlayersWhoKilledABoss.add(Event.getEntity().getKiller().getName());
				}
			}
			//Check for mob drops
			HandleEntityDrops((Player)Event.getEntity().getKiller(),Event.getEntityType(),((Damageable)Event.getEntity()).getHealth());

			//Check boss events
			if (TotalWar.BossHandler.isBoss(Event.getEntity()))
			{
				CheckBossEvents(Event.getEntity(), Event.getEntity().getKiller());
				TotalWar.BossHandler.removeBoss(Event.getEntity().getEntityId());
			}
			else
			{
				//Not a boss? Check regular mob events
				CheckMobEvents(Event.getEntity(), Event.getEntity().getKiller());
			}
		}
	}

	private void HandleBossEvent(LivingEntity Entity, AreaEvent e, Player Player)
	{
		if (TotalWar.BossHandler.isBoss(Entity))
		{
			if (TotalWar.BossHandler.getBossByID(Entity.getEntityId()).Type() == e.getBossType())
			{
				if ((!TotalWar.EventDynamics.hasBeenRewarded(e, Player.getName())) && (!TotalWar.EventDynamics.isPlayerCompleted(e, Player.getName())))
				{
					TotalWar.EventDynamics.addPlayerProgress(e, Player.getName());
				}
			}
		}
	}

	private void HandleKillMobEvent(LivingEntity Entity, AreaEvent e, Player Player)
	{
		if (Entity.getType().equals(e.getEntityType()))
		{
			if ((!TotalWar.EventDynamics.hasBeenRewarded(e, Player.getName())) && (!TotalWar.EventDynamics.isPlayerCompleted(e, Player.getName())))
			{
				TotalWar.EventDynamics.addPlayerProgress(e, Player.getName());
			}
		}
	}

	private void CheckBossEvents(LivingEntity Entity, Player Player)
	{
		ArrayList<AreaEvent> BossEvents = TotalWar.EventDynamics.getActiveEvents(AreaEvent.EventType.BOSS_HUNT);
		if (BossEvents.size() > 0)
		{
			for (AreaEvent e : BossEvents)
			{
				HandleBossEvent(Entity, e, Player);
			}
		}
	}

	private void CheckMobEvents(LivingEntity Entity, Player Player)
	{
		ArrayList<AreaEvent> MobEvents = TotalWar.EventDynamics.getActiveEvents(AreaEvent.EventType.KILL_MOB);
		if (MobEvents.size() > 0)
		{
			for (AreaEvent e : MobEvents)
			{
				HandleKillMobEvent(Entity, e, Player);
			}
		}
	}
	
	private void HandleEntityDrops(Player Player, EntityType Entity, double Health)
	{
		List<CustomItemStack> Drops = MobdropHandler.Drops(Entity);
		if (Drops != null && Drops.size() > 0)
		{
			Drops.add(new CustomItemStack(new Gem("",Material.EMERALD)));
			if (PlayerHandler.hasEmptySlot(Player))
			{
				double DropRate = MobDropRate.getDropRate(Entity);
				if (DropRate > 0)
				{
					if (MiscUtils.PercentCheck(DropRate * (Health / 10) + GetDropIncrease(Player)))
					{
						CustomItemStack Drop =  Drops.get(new Random().nextInt(Drops.size()));
						Player.getInventory().addItem(Drop.getItem());
						Player.updateInventory();
						Player.sendMessage(ChatColor.LIGHT_PURPLE + "You just recieved a \"" + ChatColor.RESET + Drop.getName() + ChatColor.LIGHT_PURPLE + " from a " + (WordUtils.capitalize(Entity.getName().replace('_', ' '))));
					}
				}
			}
		}
	}

	private double GetDropIncrease(Player Player)
	{
		double DropIncrease = 0;

		if (this.PlayersWhoKilledABoss.contains(Player.getName()))
		{
			DropIncrease += 20;
			this.PlayersWhoKilledABoss.remove(Player.getName());
		}

		if (TunnelsSQL.TunnelsSQL.PlayerSQL.isDonator(Player.getName()) || TotalWar.permission.playerHas(Player, "TotalWar.Donator"))
		{
			DropIncrease += this.Donator_Drop_Increase;
		}

		return DropIncrease;
	}
}