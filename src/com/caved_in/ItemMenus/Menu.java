package com.caved_in.ItemMenus;

import java.util.ArrayList;
import org.bukkit.entity.Player;

public abstract class Menu
{
	public abstract ArrayList<Object> getOptions();

	public abstract String getMenuTitle();

	public abstract boolean getCanClose();

	public abstract void handleMenuClick(Player paramPlayer, String paramString);
}