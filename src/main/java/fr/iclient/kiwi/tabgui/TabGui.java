package fr.iclient.kiwi.tabgui;

import java.util.ArrayList;

import com.google.common.base.Objects;

import fr.iclient.IClient;
import fr.iclient.kiwi.ModuleManager;
import fr.iclient.kiwi.eventbus.EventTarget;
import fr.iclient.kiwi.eventbus.events.other.EventKeyPressed;
import fr.iclient.kiwi.module.Module;
import fr.iclient.util.GuiUtils;
import net.minecraft.client.Minecraft;

public final class TabGui
{
    private final Minecraft mc = Minecraft.getMinecraft();
    private int colourBody = -1795161088, colourBox = 1862314667, guiHeight = 0, selectedItem = 0, selectedTab = 0, tabHeight = 12, transition = 0;
    private String colourHighlight = "", colourNormal = "", colourTitle = "";
    private boolean mainMenu = true, visible;
    private final ArrayList<TabGuiItem> tabs = new ArrayList<TabGuiItem>();
    
    public TabGui()
    {
        Module.Category[] values;
        
        for (int length = (values = Module.Category.values()).length, i = 0; i < length; i++)
        {
            Module.Category category = values[i];
            
            if (!Objects.equal(category, Module.Category.NONE) && !Objects.equal(category, Module.Category.FILLERS))
            {
                String otherLetters = category.name().toLowerCase().substring(1);
                String firstLetter = category.name().substring(0, 1).toUpperCase();
                String name = String.valueOf(String.valueOf(String.valueOf(firstLetter))) + otherLetters;
                TabGuiItem tab = new TabGuiItem(this, name);
                ModuleManager ModManager = IClient.proxy.module_manager;
                
                for (Module m : ModManager.getMods())
                {
                    if (m.getCategory().equals(category))
                    {
                        tab.getMods().add(new TabGuiItem.GuiItem(m));
                    }
                }
                
                this.tabs.add(tab);
            }
        }
        
        this.guiHeight = this.tabs.size() * this.tabHeight;
        IClient.proxy.event_manager.register(this);
    }
    
    public void drawGui(int x, int y)
    {
        if (!this.visible)
        {
            return;
        }
        
        int guiWidth = 60;
        GuiUtils.getInstance().drawBorderedRect(x, y, (x + 60 - 2), (y + this.guiHeight), this.colourBody, -2147483648);
        
        for (int i = 0; i < this.tabs.size(); i++)
        {
            int transitionTop = this.mainMenu ? (this.transition + ((Objects.equal(Integer.valueOf(this.selectedTab), Integer.valueOf(0)) && this.transition < 0) ? -this.transition : 0)) : 0;
            int transitionBottom = this.mainMenu ? (this.transition + ((Objects.equal(Integer.valueOf(this.selectedTab), Integer.valueOf(this.tabs.size() - 1)) && this.transition > 0) ? -this.transition : 0)) : 0;
            
            if (Objects.equal(Integer.valueOf(this.selectedTab), Integer.valueOf(i)))
            {
                GuiUtils.getInstance().drawBorderedRect(x, (i * 12 + y + transitionTop), (x + 60 - 2), (i + y + 12 + i * 11 + transitionBottom), this.colourBox, -2147483648);
            }
        }
        
        int yOff = y + 2;
        
        for (int j = 0; j < this.tabs.size(); j++)
        {
            TabGuiItem tab = this.tabs.get(j);
            this.mc.fontRenderer.drawString(String.valueOf(String.valueOf(String.valueOf(this.colourTitle))) + tab.getTabName(), x + 2, yOff, -1);
            
            if (Objects.equal(Integer.valueOf(this.selectedTab), Integer.valueOf(j)) && !this.mainMenu)
            {
                tab.drawTabMenu(this.mc, x + 60, yOff - 2);
            }
            
            yOff += this.tabHeight;
        }
        
        if (this.transition > 0)
        {
            this.transition--;
        } else if (this.transition < 0)
        {
            this.transition++;
        }
    }
    
    @EventTarget
    public void onkeyPress(EventKeyPressed e)
    {
        switch (e.key) {
            case 200:
                
                if (!this.visible)
                {
                    break;
                }
                
                if (this.mainMenu)
                {
                    this.selectedTab--;
                    
                    if (this.selectedTab < 0)
                    {
                        this.selectedTab = this.tabs.size() - 1;
                    }
                    
                    this.transition = 3;
                    break;
                }
                
                this.selectedItem--;
                
                if (this.selectedItem < 0)
                {
                    this.selectedItem = ((TabGuiItem) this.tabs.get(this.selectedTab)).getMods().size() - 1;
                }
                
                if (((TabGuiItem) this.tabs.get(this.selectedTab)).getMods().size() > 1)
                {
                    this.transition = 3;
                }
                break;
                
            case 208:
                
                if (!this.visible)
                {
                    break;
                }
                
                if (this.mainMenu)
                {
                    this.selectedTab++;
                    
                    if (this.selectedTab > this.tabs.size() - 1)
                    {
                        this.selectedTab = 0;
                    }
                    
                    this.transition = -6;
                    break;
                }
                
                this.selectedItem++;
                
                if (this.selectedItem > ((TabGuiItem) this.tabs.get(this.selectedTab)).getMods().size() - 1)
                {
                    this.selectedItem = 0;
                }
                
                if (((TabGuiItem) this.tabs.get(this.selectedTab)).getMods().size() > 1)
                {
                    this.transition = -6;
                }
                break;
                
            case 203:
                
                if (this.mainMenu)
                {
                    this.mainMenu = false;
                    break;
                }
                
                this.mainMenu = true;
                break;
                
            case 205:
                
                if (this.mainMenu)
                {
                    this.mainMenu = false;
                    this.selectedItem = 0;
                }
                break;
                
            case 28:
                
                if (!this.mainMenu && this.visible)
                {
                    ((TabGuiItem.GuiItem) ((TabGuiItem) this.tabs.get(this.selectedTab)).getMods().get(this.selectedItem)).getMod().toggle();
                }
                break;
        }
    }
    
    public int getColourBody()
    {
        return this.colourBody;
    }
    
    public void setColourBody(int colourBody)
    {
        this.colourBody = colourBody;
    }
    
    public int getColourBox()
    {
        return this.colourBox;
    }
    
    public void setColourBox(int colourBox)
    {
        this.colourBox = colourBox;
    }
    
    public String getColourHighlight()
    {
        return this.colourHighlight;
    }
    
    public void setColourHighlight(String colourHighlight)
    {
        this.colourHighlight = colourHighlight;
    }
    
    public String getColourNormal()
    {
        return this.colourNormal;
    }
    
    public void setColourNormal(String colourNormal)
    {
        this.colourNormal = colourNormal;
    }
    
    public String getColourTitle()
    {
        return this.colourTitle;
    }
    
    public void setColourTitle(String colourTitle)
    {
        this.colourTitle = colourTitle;
    }
    
    public int getSelectedItem()
    {
        return this.selectedItem;
    }
    
    public int getTabHeight()
    {
        return this.tabHeight;
    }
    
    public int getTransition()
    {
        return this.transition;
    }
}