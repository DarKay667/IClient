package fr.iclient.kiwi.module;

import fr.iclient.IClient;
import fr.iclient.kiwi.setting.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Module
{
    protected Minecraft mc = Minecraft.getMinecraft();
    protected FontRenderer fr = (Minecraft.getMinecraft()).fontRenderer;
    private String name, displayName;
    private int key, animation;
    private Category category;
    private boolean toggled;
    
    public Module(String name, String displayName, int key, Category category, boolean toggled)
    {
        this.name = name;
        this.displayName = displayName;
        this.key = key;
        this.animation = toggled ? (this.fr.getStringWidth(name) + 2) : 0;
        this.category = category;
        this.toggled = toggled;
        
        if (toggled)
            iEnable();
    }
    
    public Module(String name, String displayName, int key, Category category)
    {
        this(name, displayName, key, category, false);
    }
    
    public Module(String name, int key, Category category, boolean toggled)
    {
        this(name, name, key, category, toggled);
    }
    
    public Module(String name, String displayName, Category category)
    {
        this(name, displayName, -1, category, false);
    }
    
    public Module(String name, Category category, boolean toggled)
    {
        this(name, name, -1, category, toggled);
    }
    
    public Module(String name, int key, Category category)
    {
        this(name, name, key , category , false);
    }
    
    public Module(String name, Category category)
    {
        this(name, name, -1, category, false);
    }
    
    public String getDisplayName()
    {
        return this.displayName;
    }
    
    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }
    
    public int getKey()
    {
        return this.key;
    }
    
    public void setKey(int key)
    {
        this.key = key;
    }
    
    public int getAnimation()
    {
        return this.animation;
    }
    
    public void setAnimation(int animation)
    {
        this.animation = animation;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public Category getCategory()
    {
        return this.category;
    }
    
    public boolean isToggled()
    {
        return this.toggled;
    }
    
    public boolean isEnabled()
    {
        return this.toggled;
    }
    
    public boolean isCategory(Category cat)
    {
        return (this.category == cat);
    }
    
    public void setToggled(boolean toggled)
    {
        this.toggled = toggled;
    }
    
    public void toggle()
    {
        boolean next = !this.toggled;
        iToggle();
        
        if (next)
        {
            iEnable();
        } else {
            iDisable();
        }
        
        this.toggled = next;
    }
    
    public void setState(boolean state)
    {
        if (state != this.toggled)
        {
            iToggle();
        }
        
        if (state)
        {
            iEnable();
        } else {
            iDisable();
        }
        
        this.toggled = state;
    }
    
    public Setting gsu(String name)
    {
        String unlocalizedName = getName() + " " + name;
        return IClient.proxy.settings_manager.getSettingByUnlocalizedName(unlocalizedName);
    }
    
    public String getMode()
    {
        return (gsu("Mode") != null) ? gsu("Mode").string() : "none";
    }
    
    private void iEnable()
    {
        IClient.proxy.event_manager.register(this);
        
        if (getAnimation() == -1)
            setAnimation(0);
        onEnable();
    }
    
    private void iDisable()
    {
        IClient.proxy.event_manager.unregister(this);
        onDisable();
    }
    
    private void iToggle()
    {
        onToggle();
    }
    
    public void onEnable() {}
    
    public void onDisable() {}
    
    public void onToggle() {}
    
    public enum Category { Combat, Movement, Player, World, Render, NONE, FILLERS; }
}