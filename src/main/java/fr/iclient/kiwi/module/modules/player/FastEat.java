package fr.iclient.kiwi.module.modules.player;

import fr.iclient.kiwi.eventbus.EventTarget;
import fr.iclient.kiwi.eventbus.events.update.EventUpdate;
import fr.iclient.kiwi.module.Module;
import net.minecraft.item.ItemFood;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class FastEat extends Module
{
    public FastEat()
    {
        super("FastEat", Module.Category.Player);
    }
    
    @EventTarget
    public void onUpdate(EventUpdate e)
    {
        if (this.mc.thePlayer.isEating() && this.mc.thePlayer.getItemInUse() != null && this.mc.thePlayer.getItemInUse().getItem() instanceof ItemFood && this.mc.thePlayer.fallDistance < 3.0F)
        {
            for (int i = 0; i < 8; i++)
            {
                this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C03PacketPlayer(this.mc.thePlayer.onGround));
            }
        }
    }
}   