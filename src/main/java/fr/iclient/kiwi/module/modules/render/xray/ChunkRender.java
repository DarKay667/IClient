package fr.iclient.kiwi.module.modules.render.xray;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import fr.iclient.IClient;
import fr.iclient.kiwi.module.Module;
import fr.iclient.kiwi.module.modules.render.XRay;
import fr.iclient.kiwi.setting.Setting;
import fr.iclient.util.BlockData;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class ChunkRender
{
    public static void clearGLids()
    {
        GLids.clear();
    }
    
    private Minecraft mc = FMLClientHandler.instance().getClient();
    private static Map<Integer, Integer> GLids = new HashMap<Integer, Integer>();
    private World w;
    private int xc, yc, zc = 0;
    public int GLid = 0;
    public boolean needsupdate = true, needsrenderupdate = false;
    private List<BlockData> renderList = new ArrayList<BlockData>();
    
    public ChunkRender(World w, int xc, int yc, int zc)
    {
        this.w = w;
        this.xc = xc;
        this.yc = yc;
        this.zc = zc;
        this.GLid = getAvailableID();
    }
    
    private int getAvailableID()
    {
        int i = 1;
        
        for (; i < 500; i++)
        {
            if (!GLids.containsKey(Integer.valueOf(99999 + i)))
            {
                GLids.put(Integer.valueOf(99999 + i), Integer.valueOf(99999 + i));
                return 99999 + i;
            }
        }
        
        return 99999;
    }
    
    public void removeGLid()
    {
        GLids.remove(Integer.valueOf(this.GLid));
    }
    
    public void render(float fx, float fy, float fz, float partialTick)
    {
        if (this.needsrenderupdate)
        {
            this.needsrenderupdate = false;
            
            Tessellator tes = Tessellator.instance;
            int ambi = this.mc.gameSettings.ambientOcclusion;
            this.mc.gameSettings.ambientOcclusion = 0;
            GL11.glNewList(this.GLid, 4864);
            GL11.glPushMatrix();
            
            tes.startDrawingQuads();
            tes.setBrightness(200);
            
            this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
            
            for (BlockData r : this.renderList)
            {
                RenderBlocks rb = null;
                
                try {
                    Field f = this.mc.renderGlobal.getClass().getDeclaredField("renderBlocksRg");
                    f.setAccessible(true);
                    rb = (RenderBlocks)
                            f.get(this.mc.renderGlobal);
                } catch (Exception e) {
                    try {
                        Field f = this.mc.renderGlobal.getClass().getDeclaredField("field_147592_B");
                        f.setAccessible(true);
                        rb = (RenderBlocks)
                                f.get(this.mc.renderGlobal);
                    } catch (Exception ex) {
                        e.printStackTrace();
                        ex.printStackTrace();
                    }
                }
                
                if (rb != null)
                    r.RenderBlock(rb);
            }
            tes.draw();
            
            GL11.glPopMatrix();
            GL11.glEndList();
            
            this.mc.gameSettings.ambientOcclusion = ambi;
        }
    }
    
    public void updateChunk()
    {
        if (this.needsupdate)
        {
            this.needsupdate = false;
            this.renderList.clear();
            
            String mode = getSetting("Mode", IClient.proxy.module_manager.getModule("XRay")).string();
            
            int xb = this.xc * 16;
            int zb = this.zc * 16;
            int yb = this.yc * 16;
            
            for (int y = yb; y < yb + 16; y++)
            {
                for (int x = xb; x < xb + 16; x++)
                {
                    for (int z = zb; z < zb + 16; z++)
                    {
                        int id = Block.getIdFromBlock(this.w.getBlock(x, y, z));
                        
                        if (mode.equalsIgnoreCase("XRay") && id > 0 && !XRay.blacklist.contains(id, -1))
                        {
                            this.renderList.add(new BlockData(Block.getBlockById(id), x, y, z, true));
                        }
                        
                        if (mode.equalsIgnoreCase("CaveFinder") && id > 0 && (Block.getBlockById(id) == Blocks.stone || !XRay.blacklist.contains(id, -1)))
                        {
                            this.renderList.add(new BlockData(Block.getBlockById(id), x, y, z, false));
                        }
                    }
                }
            }
            this.needsrenderupdate = true;
        }
    }
    
    public static Setting getSetting(String name, Module m)
    {
        return m.gsu(name);
    }
}