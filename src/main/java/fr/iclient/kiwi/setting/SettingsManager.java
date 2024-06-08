package fr.iclient.kiwi.setting;

import java.util.ArrayList;

import fr.iclient.kiwi.module.Module;

public class SettingsManager
{
    private ArrayList<Setting> settings = new ArrayList<Setting>();
    
    public void addSetting(Setting in)
    {
        this.settings.add(in);
    }
    
    public void rsSetting(Setting in)
    {
        this.settings.add(in);
    }
    
    public ArrayList<Setting> getSettings()
    {
        return this.settings;
    }
    
    public ArrayList<Setting> getSettingsByMod(Module mod)
    {
        ArrayList<Setting> out = new ArrayList<Setting>();
        
        for (Setting s : getSettings())
        {
            if (s.getParentMod().equals(mod))
            {
                out.add(s);
            }
        }
        
        if (out.isEmpty())
        {
            return null;
        }
        
        return out;
    }
    
    public Setting getSettingByName(String name)
    {
        for (Setting set : getSettings())
        {
            if (set.getName().equalsIgnoreCase(name))
            {
                return set;
            }
        }
        
        System.err.println("[IClient] Error Setting NOT found: '" + name + "'!");
        return null;
    }
    
    public Setting getSettingByUnlocalizedName(String name)
    {
        for (Setting set : getSettings())
        {
            if (set.getUnlocalizedName().equalsIgnoreCase(name))
            {
                return set;
            }
        }
        
        return null;
    }
    
    public Setting getSettingByNameAndMod(String name, Module mod)
    {
        for (Setting set : getSettingsByMod(mod))
        {
            if (set.getName().equalsIgnoreCase(name))
            {
                return set;
            }
        }
        
        System.err.println("[IClient] Error Setting NOT found: '" + name + "' for mod " + mod.getName() + "!");
        return null;
    }
    
    public Setting getSettingByUnlocalizedNameAndMod(String name, Module mod)
    {
        for (Setting set : getSettingsByMod(mod))
        {
            if (set.getUnlocalizedName().equalsIgnoreCase(name))
            {
                return set;
            }
        }
        
        System.err.println("[IClient] Error Setting NOT found: '" + name + "' for mod " + mod.getName() + "!");
        return null;
    }
}