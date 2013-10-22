package com.caved_in.ItemMenus.InputLoreMenu;

import java.util.Arrays;

import org.bukkit.inventory.ItemStack;

import com.caved_in.Handlers.ItemHandlers.ItemHandler;

import me.cybermaxke.inputgui.api.InputGui;
import me.cybermaxke.inputgui.api.InputPlayer;

public class AddLoreInput implements InputGui
{
	public AddLoreInput()
	{
	}

	@Override
	public String getDefaultText()
	{
		return "Add Item Lore";
	}

	@Override
	public void onCancel(InputPlayer arg0)
	{
		
	}

	@Override
	public void onConfirm(InputPlayer Player, String Message)
	{
		ItemStack Item = ItemHandler.addLore(Player.getPlayer().getItemInHand(), Arrays.asList(new String[] {Message }));
		Player.getPlayer().setItemInHand(Item);
		Player.closeGui();
	}

}
