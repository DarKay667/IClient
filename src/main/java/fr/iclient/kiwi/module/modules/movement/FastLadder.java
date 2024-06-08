package fr.iclient.kiwi.module.modules.movement;

import fr.iclient.kiwi.eventbus.EventTarget;
import fr.iclient.kiwi.eventbus.events.update.EventUpdate;
import fr.iclient.kiwi.module.Module;
import fr.iclient.util.MovementUtils;

public class FastLadder extends Module
{
    public FastLadder()
    {
        super("FastLadder", Module.Category.Movement);
    }
    
    @EventTarget
    public void onUdpate(EventUpdate e)
    {
        if (MovementUtils.isMoving() && this.mc.thePlayer.isOnLadder() && this.mc.thePlayer.isCollidedHorizontally)
        {
            this.mc.thePlayer.motionY = 1.0D;
        }
    }
}