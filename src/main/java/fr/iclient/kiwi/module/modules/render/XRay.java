package fr.iclient.kiwi.module.modules.render;

import fr.iclient.kiwi.eventbus.EventTarget;
import fr.iclient.kiwi.eventbus.events.render.EventRender3D;
import fr.iclient.kiwi.module.Module;
import fr.iclient.kiwi.module.modules.render.xray.Renderer;
import fr.iclient.util.XData;

public class XRay extends Module
{
    private Renderer renderer;
    public static XData blacklist;
    
    public XRay()
    {
        super("XRay", 45, Module.Category.Render);
        
        blacklist = new XData();
        
        blacklist.add(1, 0);
        blacklist.add(2, 0);
        blacklist.add(3, 0);
        blacklist.add(4, 0);
        blacklist.add(7, 0);
        blacklist.add(12, 0);
        blacklist.add(13, 0);
        blacklist.add(17, 0);
        blacklist.add(18, 0);
        blacklist.add(24, 0);
        blacklist.add(31, 0);
        blacklist.add(32, 0);
        blacklist.add(78, 0);
        blacklist.add(87, 0);
        blacklist.add(88, 0);
        blacklist.add(106, 0);
        blacklist.add(121, 0);
        
        this.renderer = new Renderer();
    }
    
    @EventTarget
    public void onRender3D(EventRender3D event)
    {
        float fx = event.partialTicks;
        
        this.renderer.render(fx);
    }
}   