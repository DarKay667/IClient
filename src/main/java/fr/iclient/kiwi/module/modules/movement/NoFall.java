package fr.iclient.kiwi.module.modules.movement;

import fr.iclient.kiwi.eventbus.EventTarget;
import fr.iclient.kiwi.eventbus.events.update.EventUpdate;
import fr.iclient.kiwi.module.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends Module
{
    public NoFall()
    {
        super("NoFall", Module.Category.Movement);
    }
    
    @EventTarget
    public void onUpdate(EventUpdate e)
    {
        if (this.mc.thePlayer.fallDistance >= 2.0F)
        {
            this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C03PacketPlayer(true));
        }
    }
}