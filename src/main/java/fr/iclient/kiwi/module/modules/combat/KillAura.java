package fr.iclient.kiwi.module.modules.combat;

import fr.iclient.kiwi.eventbus.EventTarget;
import fr.iclient.kiwi.eventbus.events.update.EventUpdate;
import fr.iclient.kiwi.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.world.World;

public class KillAura extends Module
{
    private int cps, delay;
    
    public KillAura()
    {
        super("KillAura", 37, Module.Category.Combat);
    }
    
    @EventTarget
    public void onUpdate(EventUpdate event)
    {
        this.cps = 13;
        
        try {
            this.delay++;
            
            if (this.delay >= -1 / this.cps)
            {
                this.delay = 0;
                
                for (Object o : this.mc.theWorld.loadedEntityList)
                {
                    if (o instanceof EntityLivingBase)
                    {
                        EntityLivingBase e = (EntityLivingBase) o;
                        
                        if (e != null && e != this.mc.thePlayer && this.mc.pointedEntity != null && !e.isInvisible() && e.isEntityAlive() && this.mc.thePlayer.getDistanceToEntity((Entity) e) <= this.mc.playerController.getBlockReachDistance())
                        {
                            this.mc.thePlayer.swingItem();
                            this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C02PacketUseEntity((Entity) e, C02PacketUseEntity.Action.ATTACK));
                        }
                        
                        if ((this.mc.pointedEntity != null && this.mc.thePlayer.getHeldItem() != null && this.mc.thePlayer.getHeldItem().getItem() instanceof ItemSword) || this.mc.thePlayer.isBlocking())
                        {
                            this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C08PacketPlayerBlockPlacement());
                            this.mc.playerController.sendUseItem((EntityPlayer) this.mc.thePlayer, (World) this.mc.theWorld, this.mc.thePlayer.getHeldItem());
                        }
                    }
                }
            }
        } catch (Exception exception) {}
    }
}   