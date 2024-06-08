package fr.iclient.kiwi.module.modules.render;

import fr.iclient.kiwi.eventbus.EventTarget;
import fr.iclient.kiwi.eventbus.events.update.EventUpdate;
import fr.iclient.kiwi.module.Module;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class Fullbright extends Module
{
    public Fullbright()
    {
        super("Fullbright", Module.Category.Render);
    }
    
    @EventTarget
    public void onUpdate(EventUpdate e)
    {
        this.mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, 860, 10));
    }
    
    public void onDisable()
    {
        this.mc.thePlayer.removePotionEffect(Potion.nightVision.id);
        super.onDisable();
    }
}