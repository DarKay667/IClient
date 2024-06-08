package fr.iclient.kiwi.eventbus.events.update;

import fr.iclient.kiwi.eventbus.Event;

public class EventTick extends Event
{
    public Event.State state;
    
    public EventTick(Event.State state)
    {
        this.state = state;
    }
}