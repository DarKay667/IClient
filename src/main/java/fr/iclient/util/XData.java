package fr.iclient.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XData
{
    public List<XData> list = new ArrayList<XData>();
    
    public int id = 0, meta = -2;
    
    public XData() {}
    
    public XData(int id, int meta)
    {
        this.id = id;
        this.meta = meta;
    }
    
    public void add(int id, int meta)
    {
        if (!contains(id, meta))
        {
            this.list.add(new XData(id, meta));
        }
    }
    
    public boolean contains(int id, int meta)
    {
        for (XData x : this.list)
        {
            if (x.id == id && (x.meta == meta || x.meta == -1 || meta == -1))
                return true;
        }
        
        return false;
    }
    
    public void remove(int id, int meta)
    {
        XData xdata = null;
        
        for (XData x : this.list)
        {
            if (x.id == id && x.meta == meta)
                xdata = x;
        }
        
        if (xdata != null)
        {
            this.list.remove(xdata);
        }
    }
    
    public void removeAll(int id)
    {
        Iterator<XData> it = this.list.iterator();
        
        while (it.hasNext())
        {
            XData data = it.next();
            
            if (data.id == id)
                it.remove();
        }
    }
}