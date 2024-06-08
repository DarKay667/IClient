package fr.iclient.kiwi.eventbus.events.render;

import fr.iclient.kiwi.eventbus.Event;

public class EventRender2D extends Event
{
    public float partialTicks;
    
    public EventRender2D(float partialTicks)
    {
        this.partialTicks = partialTicks;
    }
}