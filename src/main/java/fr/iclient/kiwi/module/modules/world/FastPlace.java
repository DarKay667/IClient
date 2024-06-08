package fr.iclient.kiwi.module.modules.world;

import java.lang.reflect.Field;

import fr.iclient.kiwi.eventbus.EventTarget;
import fr.iclient.kiwi.eventbus.events.update.EventUpdate;
import fr.iclient.kiwi.module.Module;

public class FastPlace extends Module
{
    public FastPlace()
    {
        super("FastPlace", Module.Category.World);
    }
    
    @EventTarget
    public void onUpdate(EventUpdate e)
    {
        try {
            Field f = this.mc.getClass().getDeclaredField("rightClickDelayTimer");
            f.setAccessible(true);
            f.set(this.mc, Integer.valueOf(0));
        } catch (Exception ex) {
            try {
                Field f = this.mc.getClass().getDeclaredField("field_71467_ac");
                f.setAccessible(true);
                f.set(this.mc, Integer.valueOf(0));
            } catch (Exception exx) {
                ex.printStackTrace();
                exx.printStackTrace();
            }
        }
    }
}