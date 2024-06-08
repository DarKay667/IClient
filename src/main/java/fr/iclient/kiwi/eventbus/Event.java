package fr.iclient.kiwi.eventbus;

import java.lang.reflect.InvocationTargetException;

import fr.iclient.IClient;

public abstract class Event
{
    private boolean cancelled;
    
    public enum State
    {
        PRE, POST;
    }
    
    public Event call()
    {
        this.cancelled = false;
        call(this);
        return this;
    }
    
    public boolean isCancelled()
    {
        return this.cancelled;
    }
    
    public void setCancelled(boolean cancelled)
    {
        this.cancelled = cancelled;
    }
    
    private static void call(Event event)
    {
        ArrayHelper<Data> dataList = IClient.proxy.event_manager.get((Class) event.getClass());
        
        if (dataList != null)
        {
            for (Data data : dataList)
            {
                try {
                    data.target.invoke(data.source, new Object[] { event });
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}