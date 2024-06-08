package fr.iclient.kiwi.tabgui;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

import fr.iclient.kiwi.module.Module;
import fr.iclient.util.GuiUtils;
import net.minecraft.client.Minecraft;

public final class TabGuiItem
{
    private final TabGui gui;
    private final ArrayList<GuiItem> Mods = new ArrayList<GuiItem>();
    private int menuHeight = 0, menuWidth = 0;
    private String tabName;
    
    public TabGuiItem(TabGui gui, String tabName)
    {
        this.gui = gui;
        this.tabName = tabName;
    }
    
    public void drawTabMenu(Minecraft mc, int x, int y)
    {
        countMenuSize(mc);
        int boxY = y, i2 = 0;
        GuiUtils.getInstance().drawBorderedRect(x, y, (x + this.menuWidth + 2), (y + this.menuHeight), this.gui.getColourBody(), -2147483648);
        
        while (i2 < this.Mods.size())
        {
            if (this.gui.getSelectedItem() == i2)
            {
                int transitionTop = this.gui.getTransition() + ((this.gui.getSelectedItem() == 0 && this.gui.getTransition() < 0) ? -this.gui.getTransition() : 0);
                int transitionBottom = this.gui.getTransition() + ((this.gui.getSelectedItem() == this.Mods.size() - 1 && this.gui.getTransition() > 0) ? -this.gui.getTransition() : 0);
                GuiUtils.getInstance().drawBorderedRect(x, (boxY + transitionTop), (x + this.menuWidth + 2), (boxY + 12 + transitionBottom), this.gui.getColourBox(), -2147483648);}
        }
        
        final Collator collator = Collator.getInstance(Locale.US);
        this.Mods.sort(new Comparator<GuiItem>()
                {
            
                public int compare(TabGuiItem.GuiItem mod1, TabGuiItem.GuiItem mod2)
                {
                    return collator.compare(mod1.getMod().getName(), mod2.getMod().getName());
                }
               });
        
        mc.fontRenderer.drawStringWithShadow(String.valueOf(String.valueOf(this.gui.getColourNormal())) + ((GuiItem) this.Mods.get(i2)).getMod().getName(), x + 2, y + this.gui.getTabHeight() * i2 - 1 + 2, -1);
        boxY += 12;
        i2++;
    }
    
    private void countMenuSize(Minecraft mc)
    {
        int maxWidth = 0;
        
        for (GuiItem Mod2 : this.Mods)
        {
            if (mc.fontRenderer.getStringWidth(Mod2.getMod().getName()) <= maxWidth)
            {
                continue;
            }
            
            maxWidth = mc.fontRenderer.getStringWidth(Mod2.getMod().getName()) + 4;
        }
        
        this.menuWidth = maxWidth;
        this.menuHeight = this.Mods.size() * this.gui.getTabHeight();
    }
    
    public String getTabName()
    {
        return this.tabName;
    }
    
    public ArrayList<GuiItem> getMods()
    {
        return this.Mods;
    }
    
    public static class GuiItem
    {
        private final Module mod;
        
        public GuiItem(Module mod)
        {
            this.mod = mod;
        }
        
        public Module getMod()
        {
            return this.mod;
        }
    }
}   