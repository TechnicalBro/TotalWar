package com.caved_in.Handlers.BookHandlers;

import java.util.ArrayList;
import java.util.HashMap;

public class BookUpdater
{
	private Long LastUpdated;
	private HashMap<String, Long> PlayersUpdated = new HashMap<String, Long>();
	private ArrayList<String> Book_Pages = new ArrayList<String>();

	/**
	 * Creates a new instance of the Book updater
	 * 
	 * @param System
	 *            The System.currentmilleseconds that the book was instanced
	 */
	public BookUpdater(Long System)
	{
		this.LastUpdated = System;
	}

	/**
	 * Checks if a players book instance is up to date with the current book
	 * instance
	 * 
	 * @param Player
	 *            Players name to check
	 * @return true if the players was updated at the same time, or after the
	 *         referenced BookUpdater instance was updated; Otherwise false
	 */
	public boolean isUpdated(String Player)
	{
		if (this.PlayersUpdated.containsKey(Player))
		{
			if (this.PlayersUpdated.get(Player).longValue() < this.LastUpdated.longValue())
			{
				return false;
			}

			return true;
		}

		return false;
	}

	/**
	 * Clears the "Pages" of the BookUpdater (Clears an ArrayList<String> which
	 * is the "Pages")
	 */
	public void ClearPages()
	{
		this.Book_Pages.clear();
	}

	/**
	 * Gets the current Pages
	 * 
	 * @return An ArrayList<String> of the current pages for this instance
	 */
	public ArrayList<String> getPages()
	{
		return this.Book_Pages;
	}

	/**
	 * Adds a page to the books array
	 * 
	 * @param PageText
	 *            Text of the page
	 */
	public void AddPage(String PageText)
	{
		this.Book_Pages.add(PageText);
	}

	/**
	 * Set the time a player was last updated
	 * 
	 * @param Player
	 *            Players Name
	 * @param Long
	 *            System Milleseconds
	 */
	public void setUpdated(String Player, Long Long)
	{
		this.PlayersUpdated.put(Player, Long);
	}

	/**
	 * Sets the time an instance of BookUpdater was updated last
	 * 
	 * @param Long
	 *            System time in milleseconds which it was last updated
	 */
	public void setLastUpdated(Long Long)
	{
		this.LastUpdated = Long;
	}
}