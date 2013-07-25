package com.caved_in.Handlers.EntityHandlers;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import com.caved_in.Handlers.EntityHandlers.Bosses.*;

public class BossHandler
{
	private HashMap<Integer, BossWrapper> CurrentBosses = new HashMap<Integer, BossWrapper>();
	public int BossTypes = 10;

	public enum BossType
	{
		AquaDan, Cthulu, DrNate, KamikazeCreeper, Kraken, Porker, QueenWidow, Shicken, Skeletor, ZombieBoss
	}

	public BossHandler()
	{

	}

	/**
	 * Gets the Boss instance of a specific entity ID
	 * 
	 * @param EntityID
	 *            The entityID to search
	 * @return The boss with said ID if exists, otherwise null
	 */
	public Boss getBossByID(int EntityID)
	{
		if (this.CurrentBosses.containsKey(EntityID))
		{
			return this.CurrentBosses.get(EntityID).getBoss();
		}
		return null;
	}

	/**
	 * Gets the entity for said boss
	 * 
	 * @param Boss
	 *            Boss to get entity of
	 * @return Entity of said Boss, null if it doesn't exist
	 */
	public LivingEntity getBossEntity(Boss Boss)
	{
		if (this.CurrentBosses.containsValue(Boss))
		{
			return this.CurrentBosses.get(Boss.getEntityID()).getEntity();
		}
		return null;
	}

	/**
	 * Gets a boss entity with this ID
	 * 
	 * @param EntityID
	 *            ID of entity to get boss of
	 * @return Returns the entity if it's a boss, otherwise null
	 */
	public LivingEntity getEntityByID(int EntityID)
	{
		if (this.CurrentBosses.containsKey(EntityID))
		{
			return this.CurrentBosses.get(EntityID).getEntity();
		}
		return null;
	}

	/**
	 * Instances a new Boss and assigns it to the EntityID
	 * 
	 * @param EntityID
	 *            ID of entity to assign boss to
	 * @param Boss
	 *            The type of Boss to create
	 */
	public void addBoss(int EntityID, BossType Boss, LivingEntity Entity)
	{
		if (!this.CurrentBosses.containsKey(EntityID))
		{
			this.CurrentBosses.put(EntityID, new BossWrapper(Entity, this.newBossFromType(Boss, EntityID)));
		}
	}

	/**
	 * Generates a new instance of a boss with the specified type and ID
	 * 
	 * @param Type
	 *            Type of boss to get
	 * @param EntityID
	 *            ID of boss entity
	 * @return Boss with these values
	 */
	public Boss makeBoss(BossType Type, int EntityID)
	{
		return this.newBossFromType(Type, EntityID);
	}

	/**
	 * Adds a BossWrapper to the Current Bosses list
	 * 
	 * @param Wrapper
	 *            Wrapper to add
	 */
	public void addBoss(BossWrapper Wrapper)
	{
		if (!this.CurrentBosses.containsKey(Wrapper.getEntity().getEntityId()))
		{
			this.CurrentBosses.put(Wrapper.getEntity().getEntityId(), Wrapper);
		}
	}

	/**
	 * Checks if the EntityID is a boss
	 * 
	 * @param EntityID
	 *            ID to check
	 * @return True if a current boss has this EntityID, false otherwise
	 */
	public boolean isBoss(int EntityID)
	{
		return this.CurrentBosses.containsKey(EntityID);
	}

	/**
	 * Accesses 'isBoss(EntityID)' but via Entity.getEntityID()
	 * 
	 * @param Entity
	 *            Entity to check
	 * @return True if this entity is a boss, false otherwise
	 */
	public boolean isBoss(Entity Entity)
	{
		return isBoss(Entity.getEntityId());
	}

	/**
	 * Removes the Boss from the CurrentBosses List
	 * 
	 * @param Boss
	 */
	public void removeBoss(Boss Boss)
	{
		removeBoss(Boss.getEntityID());
	}

	/**
	 * Removes the Boss from the CurrentBosses List
	 * 
	 * @param Boss
	 */
	public void removeBoss(int EntityID)
	{
		if (this.CurrentBosses.containsKey(EntityID))
		{
			this.CurrentBosses.remove(EntityID);
		}
	}

	/**
	 * 
	 * @return All the current / alive bosses
	 */
	public HashMap<Integer, BossWrapper> currentBosses()
	{
		return this.CurrentBosses;
	}

	/**
	 * Creates a new instanced boss of the specified type and assigns it an
	 * EntityID
	 * 
	 * @param Type
	 *            Boss Type to create
	 * @param EntityID
	 *            EntityID to assign
	 * @return A boss with the assigned values
	 */
	public Boss newBossFromType(BossType Type, int EntityID)
	{
		switch (Type)
		{
		case AquaDan:
			AquaDan Dan = new AquaDan();
			Dan.setEntityID(EntityID);
			return Dan;
		case Cthulu:
			Cthulu Cthulu = new Cthulu();
			Cthulu.setEntityID(EntityID);
			return Cthulu;
		case DrNate:
			DrNate Nate = new DrNate();
			Nate.setEntityID(EntityID);
			return Nate;
		case KamikazeCreeper:
			Kamikaze_Creeper Creep = new Kamikaze_Creeper();
			Creep.setEntityID(EntityID);
			return Creep;
		case Kraken:
			Kraken Kraken = new Kraken();
			Kraken.setEntityID(EntityID);
			return Kraken;
		case Porker:
			Porker Porker = new Porker();
			Porker.setEntityID(EntityID);
			return Porker;
		case QueenWidow:
			Queen_Widow Widow = new Queen_Widow();
			Widow.setEntityID(EntityID);
			return Widow;
		case Shicken:
			Shicken Shicken = new Shicken();
			Shicken.setEntityID(EntityID);
			return Shicken;
		case Skeletor:
			Skeletor Skele = new Skeletor();
			Skele.setEntityID(EntityID);
			return Skele;
		case ZombieBoss:
			ZombieBoss Zombie = new ZombieBoss();
			Zombie.setEntityID(EntityID);
			return Zombie;
		default:
			return null;
		}
	}

	/**
	 * Generates a number for a bosses HP based on the input
	 * 
	 * @param Input
	 * @return Double of health for boss
	 */
	public double generateBossHP(int Input)
	{
		int Health = ((100 + new Random().nextInt(Input)) + (100 + new Random().nextInt(Input)));
		return Health;
	}

}
