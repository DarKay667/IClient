package fr.iclient.kiwi.module.modules.render.xray;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import fr.iclient.kiwi.module.modules.render.XRay;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.ForgeHooksClient;

public class Renderer
{
    public static List<ChunkRender> chunkList = new ArrayList<ChunkRender>();
    private Minecraft mc = FMLClientHandler.instance().getClient();
    
    public void render(float partialTick)
    {
        if (this.mc.theWorld == null || this.mc.thePlayer == null)
        {
            return;
        }
        
        float px = (float) this.mc.thePlayer.posX;
        float py = (float) this.mc.thePlayer.posY;
        float pz = (float) this.mc.thePlayer.posZ;
        float mx = (float) this.mc.thePlayer.prevPosX;
        float my = (float) this.mc.thePlayer.prevPosY;
        float mz = (float) this.mc.thePlayer.prevPosZ;
        float x = mx + (px - mx) * partialTick;
        float y = my + (py - my) * partialTick;
        float z = mz + (pz - mz) * partialTick;
        
        GL11.glPushMatrix();
        renderXBlocks(x, y, z, partialTick);
        GL11.glPopMatrix();
    }
    
    private void renderXBlocks(float fx, float fy, float fz, float partialTick)
    {
        GL11.glClear(16640);
        GL11.glEnable(3553);
        GL11.glDisable(2912);
        GL11.glEnable(2884);
        GL11.glDisable(3042);
        GL11.glBlendFunc(770, 771);
        
        RenderHelper.disableStandardItemLighting();
        
        for (ChunkRender r : chunkList)
        {
            r.render(fx, fy, fz, partialTick);
        }
        
        GL11.glEnable(3553);
        GL11.glPushMatrix();
        GL11.glTranslatef(-fx, -fy, -fz);
        
        for (ChunkRender r : chunkList)
        {
            GL11.glCallList(r.GLid);
        }
        
        GL11.glPopMatrix();
        
        RenderHelper.enableStandardItemLighting();
        
        /*for (TileEntity tile : this.mc.renderGlobal.tileEntities)
        {
            if (tile != null && tile.blockType != null && !XRay.blacklist.contains(Block.getIdFromBlock(tile.blockType), -1))
            {
                TileEntityRendererDispatcher.instance.renderTileEntity(tile, partialTick);
            }
        }*/
        
        if (this.mc.renderViewEntity instanceof EntityPlayer && !this.mc.gameSettings.hideGUI && this.mc.objectMouseOver != null && !this.mc.renderViewEntity.isInsideOfMaterial(Material.water))
        {
            EntityPlayer entityplayer = (EntityPlayer) this.mc.renderViewEntity;
            GL11.glEnable(3042);
            
            if (!ForgeHooksClient.onDrawBlockHighlight(this.mc.renderGlobal, entityplayer, this.mc.objectMouseOver, 0, entityplayer.inventory.getCurrentItem(), partialTick))
            {
                this.mc.renderGlobal.drawSelectionBox(entityplayer, this.mc.objectMouseOver, 0, partialTick);
            }
        }
        
        this.mc.renderGlobal.drawBlockDamageTexture(Tessellator.instance, this.mc.renderViewEntity, partialTick);
        this.mc.effectRenderer.renderLitParticles((Entity) this.mc.renderViewEntity, partialTick);
        
        List<Entity> entities = this.mc.theWorld.loadedEntityList;
        
        for (Entity ent : entities)
        {
            double d0 = (this.mc.renderViewEntity.getPosition(partialTick)).xCoord;
            double d1 = (this.mc.renderViewEntity.getPosition(partialTick)).yCoord;
            double d2 = (this.mc.renderViewEntity.getPosition(partialTick)).zCoord;
            
            if (ent.isInRangeToRender3d(d0, d1, d2) && (ent != this.mc.renderViewEntity || this.mc.gameSettings.thirdPersonView != 0 || this.mc.renderViewEntity.isPlayerSleeping()))
            {
                RenderManager.instance.renderEntitySimple(ent, partialTick);
            }
        }
        
        RenderHelper.disableStandardItemLighting();
        
        GL11.glEnable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(3042);
    }
}