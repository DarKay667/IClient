package fr.iclient.kiwi.module.modules.movement;

import fr.iclient.kiwi.eventbus.EventTarget;
import fr.iclient.kiwi.eventbus.events.update.EventUpdate;
import fr.iclient.kiwi.module.Module;
import fr.iclient.util.Timer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Fly extends Module
{
    public Fly()
    {
        super("Fly", 33, Module.Category.Movement);
    }
    
    public static float speed, fall = 2.0F;
    
    @EventTarget
    public void onUpdate(EventUpdate e)
    {
        Timer.timerSpeed = 1.5F;
        this.mc.thePlayer.capabilities.isFlying = false;
        this.mc.thePlayer.capabilities.allowFlying = false;
        this.mc.thePlayer.motionX = 0.0D;
        this.mc.thePlayer.motionY = 0.0D;
        this.mc.thePlayer.motionZ = 0.0D;
        this.mc.thePlayer.jumpMovementFactor = speed;
        
        if (this.mc.gameSettings.keyBindJump.getIsKeyPressed())
        {
            this.mc.thePlayer.motionY += speed;
            this.mc.thePlayer.fallDistance = fall;
            this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C03PacketPlayer(true));
        }
        
        if (this.mc.gameSettings.keyBindSneak.getIsKeyPressed())
        {
            this.mc.thePlayer.motionY -= speed;
            this.mc.thePlayer.fallDistance = fall;
            this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C03PacketPlayer(true));
        }
    }
    
    public void onDisable()
    {
        Timer.timerSpeed = 1.0F;
        this.mc.thePlayer.motionX = 0.0D;
        this.mc.thePlayer.motionY = 0.0D;
        this.mc.thePlayer.motionZ = 0.0D;
        super.onDisable();
    }
}   