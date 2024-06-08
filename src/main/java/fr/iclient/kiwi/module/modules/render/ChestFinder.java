package fr.iclient.kiwi.module.modules.render;

import fr.iclient.kiwi.eventbus.EventTarget;
import fr.iclient.kiwi.eventbus.events.render.EventRender3D;
import fr.iclient.kiwi.module.Module;
import fr.iclient.util.RainbowUtils;
import fr.iclient.util.RenderUtils;
import net.minecraft.tileentity.TileEntity;

public class ChestFinder extends Module
{
    public ChestFinder()
    {
        super("ChestFinder", Module.Category.Render);
    }
    
    @EventTarget
    public void onRender3D(EventRender3D e)
    {
        for (Object o : this.mc.theWorld.loadedTileEntityList)
        {
            if (o.getClass().getName().toLowerCase().contains("chest"))
            {
                TileEntity te = (TileEntity) o;
                RenderUtils.init3D();
                RenderUtils.drawOutlinedBlock(e.partialTicks, te.xCoord, te.yCoord, te.zCoord, RainbowUtils.effect(0L, 0.7F, 1).getRGB());
                RenderUtils.reset3D();
            }
        }
    }
}