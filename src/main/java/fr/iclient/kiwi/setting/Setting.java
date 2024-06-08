package fr.iclient.kiwi.setting;

import java.util.ArrayList;

import fr.iclient.kiwi.module.Module;

public class Setting
{
    private String name, mode, unlocalizedName, sval, label;
    private Module parent;
    public ArrayList<String> options;
    private double dval, min, max;
    private boolean bval, onlyint = false;
    
    public Setting(String name, Module parent, String sval, ArrayList<String> options)
    {
        this.name = name;
        this.unlocalizedName = parent.getName() + " " + name;
        this.parent = parent;
        this.sval = sval;
        this.options = options;
        this.mode = "Combo";
    }
    
    public Setting(String name, Module parent, boolean bval)
    {
        this.name = name;
        this.unlocalizedName = parent.getName() + " " + name;
        this.parent = parent;
        this.bval = bval;
        this.mode = "Check";
    }
    
    public Setting(String name, Module parent, double dval, double min, double max, boolean onlyint)
    {
        this.name = name;
        this.unlocalizedName = parent.getName() + " " + name;
        this.parent = parent;
        this.dval = dval;
        this.min = min;
        this.max = max;
        this.onlyint = onlyint;
        this.mode = "Slider";
    }
    
    public Setting(String name, Module parent, String label)
    {
        this.name = name;
        this.label = label;
        this.unlocalizedName = parent.getName() + " " + name;
        this.parent = parent;
        this.mode = "Label";
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public Module getParentMod()
    {
        return this.parent;
    }
    
    public String string()
    {
        return this.sval;
    }
    
    public void setValString(String in)
    {
        this.sval = in;
    }
    
    public ArrayList<String> getOptions()
    {
        return this.options;
    }
    
    public void setOptions(ArrayList<String> o)
    {
        this.options = o;
    }
    
    public int getOptionIndex()
    {
        int optionIndex = 0;
        
        for (String s : getOptions())
        {
            if (s.equalsIgnoreCase(string()))
            {
                return optionIndex;
            }
            optionIndex++;
        }
        return -42;
    }
    
    public String getNextOption()
    {
        int optionIndex = getOptionIndex();
        
        if (optionIndex == -42)
        {
            return this.options.get(0);
        }
        
        optionIndex++;
        
        if (optionIndex >= this.options.size())
        {
            optionIndex = 0;
        }
        
        return this.options.get(optionIndex);
    }
    
    public boolean bool()
    {
        return this.bval;
    }
    
    public String getLabel()
    {
        return this.label;
    }
    
    public String getUnlocalizedName()
    {
        return this.unlocalizedName;
    }
    
    public void setUnlocalizedName(String n)
    {
        this.unlocalizedName = n;
    }
    
    public void setValBoolean(boolean in)
    {
        this.bval = in;
    }
    
    public double doubl()
    {
        if (this.onlyint)
        {
            this.dval = Math.round(this.dval);
        }
        
        return this.dval;
    }
    
    public void setValDouble(double in)
    {
        this.dval = in;
    }
    
    public double getMin()
    {
        return this.min;
    }
    
    public double getMax()
    {
        return this.max;
    }
    
    public boolean isCombo()
    {
        return this.mode.equalsIgnoreCase("Combo");
    }
    
    public boolean isCheck()
    {
        return this.mode.equalsIgnoreCase("Check");
    }
    
    public boolean isSlider()
    {
        return this.mode.equalsIgnoreCase("Slider");
    }

    public boolean isLabel()
    {
        return this.mode.equalsIgnoreCase("Label");
    }
    
    public boolean onlyInt()
    {
        return this.onlyint;
    }
}   