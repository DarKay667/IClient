package fr.iclient.kiwi.eventbus;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EventManager
{
    private Map<Class<? extends Event>, ArrayHelper<Data>> REGISTRY_MAP = new HashMap<Class<? extends Event>, ArrayHelper<Data>>();
    
    public void register(Object o)
    {
        for (Method method : o.getClass().getDeclaredMethods())
        {
            if (!isMethodBad(method))
            {
                register(method, o);
            }
        }
    }
    
    public void register(Object o, Class<? extends Event> clazz)
    {
        for (Method method : o.getClass().getDeclaredMethods())
        {
            if (!isMethodBad(method, clazz))
            {
                register(method, o);
            }
        }
    }
    
    private void register(Method method, Object o)
    {
        Class<?> clazz = method.getParameterTypes()[0];
        final Data methodData = new Data(o, method, ((EventTarget) method.<EventTarget> getAnnotation(EventTarget.class)).value());
        
        if (!methodData.target.isAccessible())
        {
            methodData.target.setAccessible(true);
        }
        
        if (this.REGISTRY_MAP.containsKey(clazz))
        {
            if (!((ArrayHelper<Data>) this.REGISTRY_MAP.get(clazz)).contains(methodData))
            {
                ((ArrayHelper<Data>) this.REGISTRY_MAP.get(clazz)).add(methodData);
                sortListValue((Class) clazz);
            }
        } else {
            this.REGISTRY_MAP.put((Class<? extends Event>)clazz, new ArrayHelper<Data>()
                    {
                
                    });
        }
    }
    
    public void unregister(Object o)
    {
        for (ArrayHelper<Data> flexibalArray : this.REGISTRY_MAP.values())
        {
            for (Data methodData : flexibalArray)
            {
                if (methodData.source.equals(0))
                {
                    flexibalArray.remove(methodData);
                }
            }
        }
        cleanMap(true);
    }
    
    public void unregister(Object o, Class<? extends Event> clazz)
    {
        if (this.REGISTRY_MAP.containsKey(clazz))
        {
            for (Data methodData : this.REGISTRY_MAP.get(clazz))
            {
                if (methodData.source.equals(0))
                {
                    ((ArrayHelper<Data>) this.REGISTRY_MAP.get(clazz)).remove(methodData);
                }
            }
            cleanMap(true);
        }
    }
    
    public void cleanMap(boolean b)
    {
        Iterator<Map.Entry<Class<? extends Event>, ArrayHelper<Data>>> iterator = this.REGISTRY_MAP.entrySet().iterator();
        
        while (iterator.hasNext())
        {
            if (!b ||  ((ArrayHelper) ((Map.Entry) iterator.next()).getValue()).isEmpty())
            {
                iterator.remove();
            }
        }
    }
    
    public void removeEntry(Class<? extends Event> clazz)
    {
        Iterator<Map.Entry<Class<? extends Event>, ArrayHelper<Data>>> iterator = this.REGISTRY_MAP.entrySet().iterator();
        
        while (iterator.hasNext())
        {
            if (((Class) ((Map.Entry) iterator.next()).getKey()).equals(clazz))
            {
                iterator.remove();
                break;
            }
        }
    }
    
    private void sortListValue(Class<? extends Event> clazz)
    {
        ArrayHelper<Data> flexibleArray = new ArrayHelper<Data>();
        
        for (byte b : Priority.VALUE_ARRAY)
        {
            for (Data methodData : this.REGISTRY_MAP.get(clazz))
            {
                if (methodData.priority == b)
                {
                    flexibleArray.add(methodData);
                }
            }
        }
        this.REGISTRY_MAP.put(clazz, flexibleArray);
    }
    
    private boolean isMethodBad(Method method)
    {
        return ((method.getParameterTypes()).length != 1 || !method.isAnnotationPresent((Class) EventTarget.class));
    }
    
    private boolean isMethodBad(Method method, Class<? extends Event> clazz)
    {
        return (isMethodBad(method) || method.getParameterTypes()[0].equals(clazz));
    }
    
    public ArrayHelper<Data> get(Class<? extends Event> clazz)
    {
        return this.REGISTRY_MAP.get(clazz);
    }
    
    public void shutdown()
    {
        this.REGISTRY_MAP.clear();
    }
}