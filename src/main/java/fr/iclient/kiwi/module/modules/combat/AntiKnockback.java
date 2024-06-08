package fr.iclient.kiwi.module.modules.combat;

import fr.iclient.kiwi.eventbus.EventTarget;
import fr.iclient.kiwi.eventbus.events.update.EventUpdate;
import fr.iclient.kiwi.module.Module;
import fr.iclient.util.MovementUtils;

public class AntiKnockback extends Module
{
    private double kb;
    
    public AntiKnockback()
    {
        super("AntiKnockback", Module.Category.Combat);
        this.kb = 0.0D;
    }
    
    @EventTarget
    public void onUpdate(EventUpdate event)
    {
        if (this.mc.thePlayer.hurtTime == 9)
        {
            this.mc.thePlayer.motionY = this.kb;
            MovementUtils.setSpeed(MovementUtils.getSpeed());
        } else {
            this.kb = this.mc.thePlayer.motionY;
        }
    }
}