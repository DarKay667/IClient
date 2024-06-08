package fr.iclient.kiwi.module.modules.movement;

import fr.iclient.kiwi.eventbus.EventTarget;
import fr.iclient.kiwi.eventbus.events.update.EventUpdate;
import fr.iclient.kiwi.module.Module;

public class Sprint extends Module
{
    public Sprint()
    {
        super("Sprint", Module.Category.Movement, true);
    }
    
    @EventTarget
    public void onUpdate(EventUpdate e)
    {
        if (!this.mc.thePlayer.isCollidedHorizontally && this.mc.thePlayer.moveForward > 0.0F)
        {
            this.mc.thePlayer.setSprinting(true);
        }
    }
}