package com.caved_in.Handlers.Scoreboards;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.caved_in.TotalWar;

public class ScoreboardHandler
{
	private TotalWar Plugin;
	private ScoreboardManager Manager;
	private Scoreboard Board;
	private Objective War;
	private Objective Objective;
	private Score Pirate, Pagan, Templar, Dragonkin;
	private boolean WarShow = false;

	public ScoreboardHandler(TotalWar Plugin)
	{
		this.Plugin = Plugin;
		this.Manager = this.Plugin.getServer().getScoreboardManager();
		this.Board = this.Manager.getNewScoreboard();
		this.Objective = this.Board.registerNewObjective("Playerhp", "dummy");
		this.Objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
		this.Objective.setDisplayName(ChatColor.RED + "❤");
	}

	public void UpdatePlayerHealth(Player Player)
	{
		Damageable Play = Player;
		Score PlayersHealth = this.Objective.getScore(Player);
		PlayersHealth.setScore((int) Play.getHealth());
		Player.setScoreboard(this.Board);
		// Player.setLevel((int) Play.getHealth());
	}

	public void UpdatePlayersHealth()
	{
		for (Player P : Bukkit.getOnlinePlayers())
		{
			UpdatePlayerHealth(P);
		}
	}

	/*
	 * public void ShowWarboard(boolean Reset) { if (Reset == true) { this.War =
	 * this.Board.registerNewObjective("WarScores", "dummy");
	 * this.War.setDisplaySlot(DisplaySlot.SIDEBAR);
	 * this.War.setDisplayName("Faction Kills"); this.Pirate =
	 * this.War.getScore(this.getFakePlayer("Pirate:")); this.Pagan =
	 * this.War.getScore(this.getFakePlayer("Pagan:")); this.Templar =
	 * this.War.getScore(this.getFakePlayer("Templar:")); this.Dragonkin =
	 * this.War.getScore(this.getFakePlayer("Dragonkin:"));
	 * this.DefaultScores(); } else { this.War =
	 * this.Board.registerNewObjective("WarScores", "dummy");
	 * this.War.setDisplaySlot(DisplaySlot.SIDEBAR);
	 * this.War.setDisplayName("Faction Kills"); this.Pirate =
	 * this.War.getScore(this.getFakePlayer("Pirate:")); this.Pagan =
	 * this.War.getScore(this.getFakePlayer("Pagan:")); this.Templar =
	 * this.War.getScore(this.getFakePlayer("Templar:")); this.Dragonkin =
	 * this.War.getScore(this.getFakePlayer("Dragonkin:"));
	 * this.Pirate.setScore(TotalWar.WarConfig.getPoints(Faction.Pirate));
	 * this.Pagan.setScore(TotalWar.WarConfig.getPoints(Faction.Pagan));
	 * this.Templar.setScore(TotalWar.WarConfig.getPoints(Faction.Templar));
	 * this.Dragonkin.setScore(TotalWar.WarConfig.getPoints(Faction.Dragonkin));
	 * } }
	 * 
	 * public void HideWarboard() { if (this.War != null) {
	 * this.Board.clearSlot(DisplaySlot.SIDEBAR); this.War.unregister(); //
	 * this.UpdatePlayersHealth(); } }
	 * 
	 * public void SetScore(Faction Faction) { if (this.War != null) { int Score
	 * = TotalWar.WarConfig.getPoints(Faction); Score += 1; switch (Faction) {
	 * case Dragonkin: this.Dragonkin.setScore(Score); break; case None: break;
	 * case Pagan: this.Pagan.setScore(Score); break; case Pirate:
	 * this.Pirate.setScore(Score); break; case Templar:
	 * this.Templar.setScore(Score); break; default: break; }
	 * TotalWar.WarConfig.SavePoints(this.Dragonkin.getScore(),
	 * this.Pagan.getScore(), this.Pirate.getScore(), this.Templar.getScore());
	 * } }
	 * 
	 * public void DefaultScores() { this.Templar.setScore(0);
	 * this.Dragonkin.setScore(0); this.Pagan.setScore(0);
	 * this.Pirate.setScore(0); }
	 */
	public OfflinePlayer getFakePlayer(String Name)
	{
		return Bukkit.getOfflinePlayer(Name);
	}
}