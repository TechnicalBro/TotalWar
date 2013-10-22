package com.caved_in.Runnables;

import java.util.HashMap;

import org.bukkit.Bukkit;

import com.caved_in.TotalWar;
public class RunnableManager
{
	private TotalWar Plugin;
	private HashMap<String, Integer> Tasks = new HashMap<String, Integer>();
	
	public RunnableManager(TotalWar Plugin)
	{
		this.Plugin = Plugin;
	}
	
	public void RegisterSynchRepeatTask(String Name, Runnable Task, long DelayInTicks, long RepeatTimeInTicks)
	{
		if (!Tasks.containsKey(Name))
		{
			Tasks.put(Name, this.Plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.Plugin, Task, DelayInTicks, RepeatTimeInTicks));
		}
	}
	
	public void RunTaskNow(Runnable Task)
	{
		this.Plugin.getServer().getScheduler().runTask(this.Plugin, Task);
	}
	
	public void RunTaskLater(Runnable Task, long DelayInTicks)
	{
		this.Plugin.getServer().getScheduler().runTaskLater(this.Plugin, Task, DelayInTicks);
	}
	
	public boolean CancelTask(String Name)
	{
		if (this.Tasks.containsKey(Name))
		{
			Bukkit.getScheduler().cancelTask(this.Tasks.get(Name));
			this.Tasks.remove(Name);
			return true;
		}
		return false;
	}

}
