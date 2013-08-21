package com.caved_in.ItemMenus.FriendsMenu.ActionsMenu.InputGUIMenu;

import me.cybermaxke.inputgui.api.InputGui;
import me.cybermaxke.inputgui.api.InputPlayer;

public class SendMessage implements InputGui
{
	private String Receiver = "";
	public SendMessage(String Receiver)
	{
		this.Receiver = Receiver;
	}

	@Override
	public String getDefaultText()
	{
		return "Enter the message you wish to send to " + Receiver;
	}

	@Override
	public void onCancel(InputPlayer arg0)
	{
		
	}

	@Override
	public void onConfirm(InputPlayer Player, String Message)
	{
		Player.getPlayer().chat("@" + this.Receiver + " " + Message);
		Player.closeGui();
	}

}
