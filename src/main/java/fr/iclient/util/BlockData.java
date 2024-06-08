package fr.iclient.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;

@SideOnly(Side.CLIENT)
public class BlockData
{
    private Block block;
    private int x, y, z;
    private boolean fullRender = true;
    
    public BlockData(Block block, int x, int y, int z, boolean fullRender)
    {
        this.block = block;
        this.x = x;
        this.y = y;
        this.z = z;
        this.fullRender = fullRender;
    }
    
    public void RenderBlock(RenderBlocks renderer)
    {
        if (this.fullRender)
        {
            renderer.renderBlockAllFaces(this.block, this.x, this.y, this.z);
        } else {
            renderer.renderBlockByRenderType(this.block, this.x, this.y, this.z);
        }
    }
}   