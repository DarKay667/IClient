package fr.iclient.util;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;

public class RenderUtils
{
    private static Minecraft mc = Minecraft.getMinecraft();
    
    public static void init3D()
    {
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDisable(2884);
        GL11.glDepthMask(false);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(1.0F);
    }
    
    public static void reset3D()
    {
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glEnable(2884);
    }
    
    public static void drawOutlinedBlock(float fx, double bx, double by, double bz, int color)
    {
        float dx = (float) mc.thePlayer.posX;
        float dy = (float) mc.thePlayer.posY;
        float dz = (float) mc.thePlayer.posZ;
        float mx = (float) mc.thePlayer.prevPosX;
        float my = (float) mc.thePlayer.prevPosY;
        float mz = (float) mc.thePlayer.prevPosZ;
        float px = mx + (dx - mx) * fx;
        float py = my + (dy - my) * fx;
        float pz = mz + (dz - mz) * fx;
        
        float f = 0.0F, f1 = 1.0F;
        
        Tessellator tes = Tessellator.instance;
        
        tes.startDrawing(1);
        tes.setColorRGBA_I(color, 255);
        tes.setBrightness(200);
        
        tes.addVertex(bx - px + f, by - py + f1, bz - pz + f); tes.addVertex(bx - px + f1, by - py + f1, bz - pz + f);
        tes.addVertex(bx - px + f1, by - py + f1, bz - pz + f); tes.addVertex(bx - px + f1, by - py + f1, bz - pz + f1);
        tes.addVertex(bx - px + f1, by - py + f1, bz - pz + f1); tes.addVertex(bx - px + f, by - py + f1, bz - pz + f1);
        tes.addVertex(bx - px + f, by - py + f1, bz - pz + f1); tes.addVertex(bx - px + f, by - py + f1, bz - pz + f);
        
        tes.addVertex(bx - px + f1, by - py + f, bz - pz + f); tes.addVertex(bx - px + f1, by - py + f, bz - pz + f1);
        tes.addVertex(bx - px + f1, by - py + f, bz - pz + f1); tes.addVertex(bx - px + f, by - py + f, bz - pz + f1);
        tes.addVertex(bx - px + f, by - py + f, bz - pz + f1); tes.addVertex(bx - px + f, by - py + f, bz - pz + f);
        tes.addVertex(bx - px + f, by - py + f, bz - pz + f); tes.addVertex(bx - px + f1, by - py + f, bz - pz + f);
        
        tes.addVertex(bx - px + f1, by - py + f, bz - pz + f1); tes.addVertex(bx - px + f1, by - py + f1, bz - pz + f1);
        tes.addVertex(bx - px + f1, by - py + f, bz - pz + f); tes.addVertex(bx - px + f1, by - py + f1, bz - pz + f);
        tes.addVertex(bx - px + f, by - py + f, bz - pz + f1); tes.addVertex(bx - px + f, by - py + f1, bz - pz + f1);
        tes.addVertex(bx - px + f, by - py + f, bz - pz + f); tes.addVertex(bx - px + f, by - py + f1, bz - pz + f);
        
        tes.draw();
    }
    
    public static void drawCircle(int x, int y, float radius, int color)
    {
        float alpha = (color >> 24 & 0xFF) / 255.0F;
        float red = (color >> 16 & 0xFF) / 255.0F;
        float green = (color >> 8 & 0xFF) / 255.0F;
        float blue = (color & 0xFF) / 255.0F;
        
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glLineWidth(1.0F);
        GL11.glBegin(9);
        
        for (int i  = 0; i <= 360; i++)
            GL11.glVertex2d(x + Math.sin(i * Math.PI / 180.0D) * radius, y + Math.cos(i * Math.PI / 180.0D) * radius);
        
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
    
    public static void connectPoints(int xOne, int yOne, int xTwo, int yTwo)
    {
        GL11.glPushMatrix();
        GL11.glEnable(2848);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.8F);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(0.5F);
        GL11.glBegin(1);
        GL11.glVertex2i(xOne, yOne);
        GL11.glVertex2i(xTwo, yTwo);
        GL11.glEnd();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glPopMatrix();
    }
}