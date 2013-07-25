package com.caved_in.command_executors;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.caved_in.TotalWar;
import com.caved_in.Handlers.EntityHandlers.EntityHandler;

public class MobSpawnCommandExecutor implements CommandExecutor
{
	private EntityHandler MobHandler = new EntityHandler();
	private TotalWar Plugin;

	public MobSpawnCommandExecutor(TotalWar Plugin)
	{
		this.Plugin = Plugin;
	}

	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args)
	{
		if ((((Sender instanceof Player)) && (Sender.isOp())) || (((Sender instanceof BlockCommandSender)) && (Args.length > 0)))
		{
			boolean Success = true;

			World Mob_World = this.Plugin.getServer().getWorld("PaganTemplar");
			try
			{
				EntityType MobType = EntityHandler.GetMobByName(Args[0]);
				String Mob_Name = Args[1];
				int Mob_HP = Integer.parseInt(Args[2]);
				int Max_Mob_HP = Integer.parseInt(Args[3]);
				if (Args[4].equalsIgnoreCase("here"))
				{
					if ((Sender instanceof Player))
					{
						Location localLocation1 = ((Player) Sender).getLocation();
						LivingEntity Spawning = (LivingEntity) Mob_World.spawnEntity(localLocation1, MobType);
						this.MobHandler.SetMob(Spawning, Mob_Name, Mob_HP, Max_Mob_HP, true);
					}
				}
				else
				{
					Location Spawn_Loc = new Location(Mob_World, Double.parseDouble(Args[4]), Double.parseDouble(Args[5]), Double.parseDouble(Args[6]));
					LivingEntity Spawning = (LivingEntity) Mob_World.spawnEntity(Spawn_Loc, MobType);
					this.MobHandler.SetMob(Spawning, Mob_Name, Mob_HP, Max_Mob_HP, true);
				}
				Success = true;
			}
			catch (NumberFormatException Ex)
			{
				Sender.sendMessage("An argument which expected a number did not receive a valid number");
				Sender.sendMessage(Ex.getMessage());
				Success = false;
			}

			return Success;
		}
		return false;
	}
}