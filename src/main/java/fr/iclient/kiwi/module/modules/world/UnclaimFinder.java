package fr.iclient.kiwi.module.modules.world;

import java.util.HashMap;
import java.util.Objects;

import fr.iclient.kiwi.eventbus.EventTarget;
import fr.iclient.kiwi.eventbus.events.render.EventRender2D;
import fr.iclient.kiwi.module.Module;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;

public class UnclaimFinder extends Module
{
    public String show = "0%";
    public RenderItem itemRenderer = new RenderItem();
    private HashMap<String, Integer> values = new HashMap<String, Integer>();
    
    public UnclaimFinder()
    {
        super("UnclaimFinder", Module.Category.World);
    }
    
    @EventTarget
    public void onRender2D(EventRender2D event)
    {
        if (!this.mc.gameSettings.showDebugInfo && Objects.nonNull(this.mc.thePlayer) && Objects.nonNull(this.mc.theWorld))
        {
            int amountTiles = (this.mc.theWorld.getChunkFromChunkCoords(this.mc.thePlayer.chunkCoordX + 0, this.mc.thePlayer.chunkCoordZ + 0)).chunkTileEntityMap.values().size();
            amountTiles += (this.mc.theWorld.getChunkFromChunkCoords(this.mc.thePlayer.chunkCoordX + 0, this.mc.thePlayer.chunkCoordZ + 1)).chunkTileEntityMap.values().size();
            amountTiles += (this.mc.theWorld.getChunkFromChunkCoords(this.mc.thePlayer.chunkCoordX + 0, this.mc.thePlayer.chunkCoordZ - 1)).chunkTileEntityMap.values().size();
            amountTiles += (this.mc.theWorld.getChunkFromChunkCoords(this.mc.thePlayer.chunkCoordX + 1, this.mc.thePlayer.chunkCoordZ)).chunkTileEntityMap.values().size();
            amountTiles += (this.mc.theWorld.getChunkFromChunkCoords(this.mc.thePlayer.chunkCoordX - 1, this.mc.thePlayer.chunkCoordZ)).chunkTileEntityMap.values().size();
            
            this.values.clear();
            this.values.put("chests", Integer.valueOf(0));
            this.values.put("moddedChest", Integer.valueOf(0));
            
            for (Object o : this.mc.theWorld.loadedTileEntityList)
            {
                if (o instanceof TileEntity)
                {
                    TileEntity te = (TileEntity) o;
                    
                    if (te.getClass().toString().toLowerCase().contains("net.minecraft.tileentity.tileentitychest"))
                    {
                        this.values.put("chests", Integer.valueOf(((Integer) this.values.get("chests")).intValue() + 1));
                        continue;
                    }
                    
                    if (te.getClass().toString().toLowerCase().contains("chest"))
                    {
                        this.values.put("moddedChest", Integer.valueOf(((Integer) this.values.get("moddedChest")).intValue() + 1));
                    }
                }
            }
            
            int x = 70, y = 2;
            
            this.fr.drawStringWithShadow("Chests: ", x, y, -1);
            this.fr.drawStringWithShadow(EnumChatFormatting.RED + "" + this.values.get("chests") + "%", x + this.fr.getStringWidth("Chests :") / 2 - this.fr.getStringWidth((new StringBuilder()).append(this.values.get("chests")).append("%").toString()) / 2, y + 10, -1);
            x += this.fr.getStringWidth("Chests: ") + 2;
            
            this.fr.drawStringWithShadow("ModdedChests: ", x, y, -1);
            this.fr.drawStringWithShadow(EnumChatFormatting.RED + "" + this.values.get("moddedChest") + "%", x + this.fr.getStringWidth("ModdedChests :") / 2 - this.fr.getStringWidth((new StringBuilder()).append(this.values.get("moddedChest")).append("%").toString()) / 2, y + 10, -1);
            x += this.fr.getStringWidth("ModdedChests: ") + 2;
        }
    }
    
    public void onDisable()
    {
        super.onDisable();
    }
}