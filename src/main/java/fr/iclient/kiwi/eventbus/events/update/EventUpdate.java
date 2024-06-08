package fr.iclient.kiwi.eventbus.events.update;

import fr.iclient.kiwi.eventbus.Event;

public class EventUpdate extends Event
{
    public Event.State state;
    public double x, y, z;
    public float yaw, pitch;
    public boolean onGround;
    
    public EventUpdate(Event.State state, double x, double y, double z, float yaw, float pitch, boolean onGround)
    {
        this.state = state;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }
}   