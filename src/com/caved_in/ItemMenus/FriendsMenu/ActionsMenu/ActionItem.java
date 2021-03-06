package com.caved_in.ItemMenus.FriendsMenu.ActionsMenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import com.caved_in.TotalWar;
import com.caved_in.ItemMenus.FriendsMenu.ActionsMenu.InputGUIMenu.SendMessage;

import me.cybermaxke.inputgui.api.InputGui;
import me.cybermaxke.inputgui.api.InputPlayer;
import me.xhawk87.PopupMenuAPI.MenuItem;

public class ActionItem extends MenuItem
{
	private Action Action;
	private String Player;
	public ActionItem(String text, MaterialData icon, Action Action, String Player)
	{
		super(text, icon);
		this.Action = Action;
		this.Player = Player;
	}

	@Override
	public void onClick(Player Player)
	{
		switch (Action)
		{
			case RemoveFriend:
				FriendSystem.FriendsChat.Friends.get(Player.getName()).getFriendsList().removeFriend(this.Player);
				Player.sendMessage(ChatColor.GREEN + "You've removed " + this.Player + " from your friends list...");
				break;
			case TeleportHereRequest:
				if (!Player.isOp())
				{
					Player.chat("/tpahere " + this.Player);
				}
				else
				{
					Player.chat("/tphere " + this.Player);
				}
				break;
			case TeleportToRequest:
				if (!Player.isOp())
				{
					Player.chat("/tpa " + this.Player);
				}
				else
				{
					Player.chat("/tp " + this.Player);
				}
				break;
			case PrivateMessage:
				if (Bukkit.getPlayer(this.Player) != null)
				{
					InputPlayer iPlayer = TotalWar.GUIAPI.getPlayer(Player);
					this.getMenu().closeMenu(Player);
					iPlayer.openGui(new SendMessage(this.Player));
				}
				else
				{
					Player.sendMessage(ChatColor.YELLOW + "They need to be online in-order to send them a message...");
				}
				break;
			case SeeInventory:
				if (Bukkit.getPlayer(this.Player) != null)
				{
					Player.chat("/invsee " + this.Player);
				}
				else
				{
					Player.sendMessage(Player + " needs to be online for you to do that...");
				}
				break;
			case Kick:
				if (Bukkit.getPlayer(this.Player) != null)
				{
					Bukkit.getPlayer(this.Player).kickPlayer("You were kicked by: " + Player.getName());
				}
				else
				{
					Player.sendMessage(this.Player + " needs to be online for you to do that...");
				}
				break;
			case Kill:
				if (Bukkit.getPlayer(this.Player) != null)
				{
					Bukkit.getPlayer(this.Player).setHealth(0.0D);
					Bukkit.getPlayer(this.Player).sendMessage(ChatColor.AQUA + "Ouch, that must've hurt!");
				}
				else
				{
					Player.sendMessage(Player + " needs to be online for you to do that...");
				}
				break;
			default:
				break;
		}
		this.getMenu().closeMenu(Player);
	}
	
	public enum Action
	{
		TeleportToRequest, TeleportHereRequest, RemoveFriend, PrivateMessage,SeeInventory,Kick,Kill
	}

}
