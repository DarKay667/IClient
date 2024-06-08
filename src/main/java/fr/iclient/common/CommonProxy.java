package fr.iclient.common;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.iclient.IClient;
import fr.iclient.kiwi.ModuleManager;
import fr.iclient.kiwi.eventbus.EventManager;
import fr.iclient.kiwi.setting.SettingsManager;
import fr.iclient.kiwi.tabgui.TabGui;

public class CommonProxy
{
    public ModuleManager module_manager;
    public EventManager event_manager;
    public SettingsManager settings_manager;
    public static TabGui tab_gui;
    
    public void preInit(FMLPreInitializationEvent event) {}
    
    public void init(FMLInitializationEvent event)
    {
        try {
            this.event_manager = new EventManager();
            this.module_manager = new ModuleManager();
            this.module_manager.init();
            this.settings_manager = new SettingsManager();
            
            tab_gui = new TabGui();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        IClient.proxy.registerRenders();
    }
    
    public void postInit(FMLPostInitializationEvent event) {}
    
    public void registerRenders() {}
}