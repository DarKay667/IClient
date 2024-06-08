package fr.iclient.kiwi.module.modules.render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import fr.iclient.IClient;
import fr.iclient.common.CommonProxy;
import fr.iclient.kiwi.eventbus.EventTarget;
import fr.iclient.kiwi.eventbus.events.render.EventRender2D;
import fr.iclient.kiwi.module.Module;
import fr.iclient.util.GuiUtils;
import fr.iclient.util.RainbowUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class HUD extends Module
{
    public HUD()
    {
        super("HUD", Keyboard.KEY_H, Module.Category.Render, true);
    }
    
    @EventTarget
    public void onRender2D(EventRender2D event)
    {
        ScaledResolution sr = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        
        if (!this.mc.gameSettings.showDebugInfo && Objects.nonNull(this.mc.thePlayer) && Objects.nonNull(this.mc.theWorld))
        {
            renderArrayList(sr);
            Gui.drawRect(0, 0, 0, 0, 0);
            drawTabGui();
            GL11.glPushMatrix();
            GL11.glScalef(1.5F, 1.5F, 1.5F);
            drawStringWithRainbow("IClient", 2, 2, 0.4F);
            GL11.glScalef(1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
            this.mc.fontRenderer.drawStringWithShadow("b1", (int) (this.mc.fontRenderer.getStringWidth("IClient") * 1.5F) + 5, 2, Color.lightGray.getRGB());
            this.mc.fontRenderer.drawStringWithShadow(this.mc.debug.split(",", 2)[0], 3, 80, Color.white.getRGB());
            int right = sr.getScaledWidth();
            int i = sr.getScaledHeight();
        }
    }
    
    private void renderArrayList(ScaledResolution sr)
    {
        int index = 0;
        long x = 0L;
        int yCount = 4;
        int right = sr.getScaledWidth();
        ArrayList<Module> mod = IClient.proxy.module_manager.getMods();
        
        for (Module mods : IClient.proxy.module_manager.getMods())
        {
            if (!mods.isToggled() || mods.getCategory() == Module.Category.NONE)
            {
                continue;
            }
            
            if (mods.getCategory() == Module.Category.FILLERS)
            {
                return;
            }
            
            GuiUtils.getInstance().drawRect((right - this.mc.fontRenderer.getStringWidth(mods.getDisplayName()) - 2), yCount, (right - 20), (yCount + 15), 0);
            
            if (mods.getCategory() == Module.Category.Combat)
            {
                this.mc.fontRenderer.drawStringWithShadow("" + mods.getDisplayName(), right - this.mc.fontRenderer.getStringWidth(mods.getDisplayName()) - 1, yCount, Color.HSBtoRGB((float)(System.currentTimeMillis() % 1000L) / 1000.0F, 0.4F, 1.0F));
            }
            
            if (mods.getCategory() == Module.Category.Movement)
            {
                this.mc.fontRenderer.drawStringWithShadow("" + mods.getDisplayName(), right - this.mc.fontRenderer.getStringWidth(mods.getDisplayName()) - 1, yCount, Color.HSBtoRGB((float)(System.currentTimeMillis() % 1000L) / 1000.0F, 0.4F, 1.0F));
            }
            
            if (mods.getCategory() == Module.Category.Render)
            {
                this.mc.fontRenderer.drawStringWithShadow("" + mods.getDisplayName(), right - this.mc.fontRenderer.getStringWidth(mods.getDisplayName()) - 1, yCount, Color.HSBtoRGB((float)(System.currentTimeMillis() % 1000L) / 1000.0F, 0.4F, 1.0F));
            }
            
            if (mods.getCategory() == Module.Category.Player)
            {
                this.mc.fontRenderer.drawStringWithShadow("" + mods.getDisplayName(), right - this.mc.fontRenderer.getStringWidth(mods.getDisplayName()) - 1, yCount, Color.HSBtoRGB((float)(System.currentTimeMillis() % 1000L) / 1000.0F, 0.4F, 1.0F));
            }
            
            if (mods.getCategory() == Module.Category.World)
            {
                this.mc.fontRenderer.drawStringWithShadow("" + mods.getDisplayName(), right - this.mc.fontRenderer.getStringWidth(mods.getDisplayName()) - 1, yCount, Color.HSBtoRGB((float)(System.currentTimeMillis() % 1000L) / 1000.0F, 0.4F, 1.0F));
            }
            
            yCount += 10;
            index++;
            x++;
        }
    }
    
    public void drawTabGui()
    {
        CommonProxy.tab_gui.setColourBox(Color.blue.getRGB());
        CommonProxy.tab_gui.drawGui(2, 18);
    }
    
    public void drawStringWithRainbow(String string, int x, int y, float brightness)
    {
        int xpos = x;
        
        for (int i = 0; i < string.length(); i++)
        {
            String s = string.charAt(i) + "";
            this.mc.fontRenderer.drawStringWithShadow(s, xpos, y, RainbowUtils.effect(i * 3500000L, brightness, 150).getRGB());
            xpos += this.mc.fontRenderer.getStringWidth(s);
        }
    }
}