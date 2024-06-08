package fr.iclient.kiwi.eventbus.events.other;

import fr.iclient.kiwi.eventbus.Event;

public class EventKeyPressed extends Event
{
    public int key;
    
    public EventKeyPressed(int key)
    {
        this.key = key;
    }
}