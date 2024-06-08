package fr.iclient.kiwi;

import java.util.ArrayList;

import fr.iclient.IClient;
import fr.iclient.kiwi.eventbus.EventTarget;
import fr.iclient.kiwi.eventbus.events.other.EventKeyPressed;
import fr.iclient.kiwi.module.Module;
import fr.iclient.kiwi.module.modules.combat.*;
import fr.iclient.kiwi.module.modules.movement.*;
import fr.iclient.kiwi.module.modules.player.*;
import fr.iclient.kiwi.module.modules.render.*;
import fr.iclient.kiwi.module.modules.world.*;

public class ModuleManager
{
    public ModuleManager()
    {
        this.mods = new ArrayList<Module>();
        
        IClient.proxy.event_manager.register(this);
    }
    
    public ArrayList<Module> mods;
    
    public void init()
    {
        addModule((Module) new AntiKnockback());
        addModule((Module) new KillAura());
        
        addModule((Module) new FastLadder());
        addModule((Module) new Fly());
        addModule((Module) new NoFall());
        addModule((Module) new Sprint());
        
        addModule((Module) new FastEat());
        
        addModule((Module) new ChestFinder());
        addModule((Module) new Fullbright());
        addModule((Module) new HUD());
        addModule((Module) new XRay());
        
        addModule((Module) new UnclaimFinder());
        addModule((Module) new FastPlace());
    }
    
    private void addModule(Module m)
    {
        this.mods.add(m);
    }
    
    public ArrayList<Module> getMods()
    {
        return this.mods;
    }
    
    public Module getModule(String name)
    {
        Module mod = null;
        
        for (Module m : this.mods)
        {
            if (m.getName().equalsIgnoreCase(name))
            {
                mod = m;
            }
        }
        return mod;
    }
    
    public ArrayList<Module> getModInCategroy(Module.Category cat)
    {
        ArrayList<Module> out = new ArrayList<Module>();
        
        for (Module m : getMods())
        {
            if (m.isCategory(cat))
            {
                out.add(m);
            }
        }
        return out;
    }
    
    @EventTarget
    public void onKeyPressed(EventKeyPressed e)
    {
        for (Module m : this.mods)
        {
            if (m.getKey() == e.key)
            {
                m.toggle();
            }
        }
    }
}