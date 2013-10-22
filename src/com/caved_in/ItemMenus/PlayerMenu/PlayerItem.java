package com.caved_in.ItemMenus.PlayerMenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import me.xhawk87.PopupMenuAPI.MenuItem;

public class PlayerItem extends MenuItem
{
	private ItemType Type;
	private String ClickedPlayer = "";
	public PlayerItem(String text, MaterialData icon,ItemType ItemType, String ClickedPlayer)
	{
		super(text, icon);
		this.Type = ItemType;
		this.ClickedPlayer = ClickedPlayer;
		this.setSkullOwner(ClickedPlayer);
	}

	@Override
	public void onClick(Player Player)
	{
		switch (this.Type)
		{
			case AddFriend:
				if (Bukkit.getPlayer(ClickedPlayer) != null)
				{
					if (FriendSystem.FriendsChat.Friends.get(Player.getName()).getFriendsList().addFriend(ClickedPlayer) == true)
					{
						Player.sendMessage(ChatColor.GREEN + "You've added " + ClickedPlayer + " to your friends list!");
						Bukkit.getPlayer(ClickedPlayer).sendMessage(ChatColor.GREEN + Player.getName() + " has added you to their friends list!");
					}
					else
					{
						Player.sendMessage(ChatColor.RED + "Failed to add " + ClickedPlayer + " to your friends list...");
					}
				}
				this.getMenu().closeMenu(Player);
				break;
			default:
				break;
		}
	}
	
	public enum ItemType
	{
		AddFriend
	}

}
