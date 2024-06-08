package fr.iclient.kiwi.eventbus.events.render;

import fr.iclient.kiwi.eventbus.Event;

public class EventRender3D extends Event
{
    public float partialTicks;
    
    public EventRender3D(float partialTicks)
    {
        this.partialTicks = partialTicks;
    }
}   