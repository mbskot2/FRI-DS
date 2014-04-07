package sk.uniza.fri.II008.events;

import java.util.logging.Level;
import java.util.logging.Logger;
import sk.uniza.fri.II008.EventSimulation;

public class PauseEvent extends Event
{
	private long interval, paused;

	public PauseEvent(double timestamp, EventSimulation simulation, long interval, long paused)
	{
		super(timestamp, simulation);

		this.interval = interval;
		this.paused = paused;
	}

	public long getInterval()
	{
		return interval;
	}

	public void setInterval(long interval)
	{
		this.interval = interval;
	}

	public long getPaused()
	{
		return paused;
	}

	public void setPaused(long paused)
	{
		this.paused = paused;
	}

	@Override
	public void run()
	{
		try
		{
			Thread.sleep(paused);

			if (getSimulation().hasPauseEvent())
			{
				setTimestamp(getNextTimestamp(getTimestamp(), interval));
				getSimulation().addEvent(this);
			}
		}
		catch (InterruptedException ex)
		{
			Logger.getLogger(PauseEvent.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static double getNextTimestamp(double timestamp, long interval)
	{
		return (timestamp / interval + 1.0) * interval;
	}
}
