package com.caved_in;

import com.caved_in.CitizenTraits.TraitRegister;
import com.caved_in.Config.Markup.WarpsConfig;
import com.caved_in.Config.YML.JailConfig;
import com.caved_in.Config.YML.MobDroprateConfig;
import com.caved_in.Config.YML.PluginConfig;
import com.caved_in.CraftingRecipes.Recipes;
import com.caved_in.Events.Events;
import com.caved_in.Handlers.BookHandlers.BookUpdater;
import com.caved_in.Handlers.DynamicEvents.AreaDynamicsHandler;
import com.caved_in.Handlers.DynamicEvents.EventGenerator;
import com.caved_in.Handlers.EntityHandlers.*;
import com.caved_in.Handlers.FileHandler.DataHandler;
import com.caved_in.Handlers.ItemHandlers.ItemHandler;
import com.caved_in.Handlers.Misc.MOTDHandler;
import com.caved_in.Handlers.PlayerHandlers.PlayerHandler;
import com.caved_in.Handlers.Scoreboards.ScoreboardHandler;
import com.caved_in.Items.Gem;
import com.caved_in.Runnables.Runnables;
import com.caved_in.Runnables.UpdateInventories;
import com.caved_in.command_executors.Commands;
import com.caved_in.Config.Markup.*;
import com.caved_in.Cooldown.GlobalCooldowns;

import java.io.File;

import me.cybermaxke.inputgui.api.InputGuiAPI;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class TotalWar extends JavaPlugin
{
	public static Economy economy = null;
	public static Permission permission = null;

	public static final String Default_World_Name = "PaganTemplar";
	public static final String Donator_Permission = "TotalWar.Donator";
	public static final String Dungeon_Maker_Permission = "TotalWar.DungeonKeep";
	public static final String Player_Voter_Permission = "TotalWar.Voter";
	public static final String Templar_Permission = "Totalwar.Templar";
	public static final String Pagan_Permission = "Totalwar.Pagan";
	public static final String DragonKin_Permission = "Totalwar.Dragonkin";
	public static final String Default_Player_Permission = "TotalWar.player";
	public static final String Pirate_Permission = "Totalwar.Pirate";
	public static final String Templar_Name_Color = "nametags.color.yellow";
	public static final String Pagan_Name_Color = "nametags.color.green";
	public static final String DragonKin_Name_Color = "nametags.color.aqua";
	public static final String Pirate_Name_Color = "nametags.color.light_purple";
	public static final String Pagan_Group_Name = "Pagan";
	public static final String Templar_Group_Name = "Templar";
	public static final String Dragonkin_Group_Name = "DragonKin";
	public static final String Pirate_Group_Name = "Pirate";
	public static final String War_Config_Name = "War.yml";

	public static JailConfig Jail_Config;
	public static MobDroprateConfig Mob_Drops;
	public static PluginConfig Plugin_Config;
	public static AreaDynamicsHandler EventDynamics = new AreaDynamicsHandler();
	public static ItemHandler ItemNamer = new ItemHandler();
	public static PlayerHandler PlayerHandler = new PlayerHandler();
	public static UpdateInventories InventoryUpdater;
	public static DataHandler PlayersWhoveJoinedHandler;
	public static WarpsConfig Waypoints;
	public static NpcTraitConfig NpcTraitConfig;
	
	public static GlobalCooldowns Cooldowns = new GlobalCooldowns();
	
	public static boolean Debug = true;
	public static boolean WarTimeMOTD = true;
	public static Gem Gem = new Gem("", Material.EMERALD);
	public static ScoreboardHandler SBMan;
	public static MOTDHandler MotdHandler;

	public static BossHandler BossHandler = new BossHandler();
	
	public static InputGuiAPI GUIAPI;

	@Override
	public void onEnable()
	{
		saveResource("Mobdrops.yml", false);
		saveResource("Jail.yml", false);
		saveResource("Config.yml", false);
		Jail_Config = new JailConfig(getDataFolder() + File.separator + "Jail.yml");
		Mob_Drops = new MobDroprateConfig(getDataFolder() + File.separator + "Mobdrops.yml");
		Plugin_Config = new PluginConfig(getDataFolder() + File.separator + "Config.yml");
		PlayersWhoveJoinedHandler = new DataHandler(getDataFolder() + File.separator + "PlayersList.txt");
		Waypoints = new WarpsConfig(getDataFolder() + File.separator + "Warps.txt");
		MotdHandler = new MOTDHandler(getDataFolder() + File.separator + "Motd.txt");
		NpcTraitConfig = new NpcTraitConfig(getDataFolder() + File.separator + "TraitCache.txt");
		SBMan = new ScoreboardHandler(this);
		GUIAPI = (InputGuiAPI) Bukkit.getPluginManager().getPlugin("InputGui");
		
		new Events(this);
		new TraitRegister();
		new Runnables(this);
		setupEconomy();
		setupPermissions();
		new Commands(this);
		new Recipes(this);
		AddEvents(Plugin_Config.getEventAmout());
		InventoryUpdater = new UpdateInventories(this);
		Bukkit.getWorld(Default_World_Name).setPVP(true);
	}

	@Override
	public void onDisable()
	{
		HandlerList.unregisterAll(this);
	}

	private boolean setupEconomy()
	{
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
		if (economyProvider != null)
		{
			economy = economyProvider.getProvider();
		}
		return economy != null;
	}

	private void AddEvents(int Amount)
	{
		while (!EventDynamics.isEventsCount(EventDynamics.NPCAmount()))
		{
			EventDynamics.AddEvent(new EventGenerator().generateEvent());
		}
	}

	public static void ReloadConfig()
	{
		TotalWar.Plugin_Config = new PluginConfig("plugins/TotalWar/Config.yml");
	}

	public static void UpdateAllPlayerEvents()
	{
		Console("[Events] Updating all players Event Status");
		for (Player P : Bukkit.getServer().getOnlinePlayers())
		{
			EventDynamics.putPlayerInAllEvents(P.getName());
		}
		Console("[Events] Updated all players Event Status' Successfully");
	}

	private boolean setupPermissions()
	{
		RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(Permission.class);
		if (permissionProvider != null)
		{
			permission = permissionProvider.getProvider();
		}
		return permission != null;
	}

	public static String ColorMessage(String Message)
	{
		return ChatColor.translateAlternateColorCodes('&', Message);
	}

	public static boolean isTemplar(Player Player)
	{
		return permission.has(Player, "Totalwar.Templar");
	}

	public static boolean isPagan(Player Player)
	{
		return permission.has(Player, "Totalwar.Pagan");
	}

	public static boolean isPirate(Player Player)
	{
		return permission.has(Player, "Totalwar.Pirate");
	}

	public static boolean isDragonKin(Player Player)
	{
		return permission.has(Player, "Totalwar.Dragonkin");
	}

	public static boolean isSameFaction(Player A, Player B)
	{
		if (((isTemplar(A)) && (isTemplar(B))) || ((isPagan(A)) && (isPagan(B))) || ((isDragonKin(A)) && (isDragonKin(B))) || ((isPirate(A)) && (isPirate(B))))
		{
			return true;
		}
		return false;
	}

	public static void Console(String Text)
	{
		if (Debug)
		{
			Bukkit.getLogger().info("[TotalWar] " + Text);
		}
	}
}