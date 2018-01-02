// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.activity;

import java.util.HashMap;

public class ActivityMap
{
    private HashMap m_map;
    private ActivityMap m_parent;
    
    public ActivityMap() {
        this(null);
    }
    
    public ActivityMap(final ActivityMap parent) {
        this.m_map = new HashMap();
        this.m_parent = parent;
    }
    
    public void clear() {
        this.m_map.clear();
    }
    
    public int size() {
        return this.m_map.size();
    }
    
    public Activity get(final String s) {
        final Activity activity = this.m_map.get(s);
        return (activity == null && this.m_parent != null) ? this.m_parent.get(s) : activity;
    }
    
    public Activity runAt(final String s, final long n) {
        final Activity value = this.get(s);
        if (value != null) {
            ActivityManager.scheduleAt(value, n);
        }
        return value;
    }
    
    public Activity run(final String s) {
        final Activity value = this.get(s);
        if (value != null) {
            ActivityManager.scheduleNow(value);
        }
        return value;
    }
    
    public Activity runAfter(final String s, final String s2) {
        final Activity value = this.get(s);
        final Activity value2 = this.get(s2);
        if (value != null && value2 != null) {
            ActivityManager.scheduleAfter(value, value2);
        }
        return value2;
    }
    
    public Activity alwaysRunAfter(final String s, final String s2) {
        final Activity value = this.get(s);
        final Activity value2 = this.get(s2);
        if (value != null && value2 != null) {
            ActivityManager.alwaysScheduleAfter(value, value2);
        }
        return value2;
    }
    
    public Activity cancel(final String s) {
        final Activity value = this.get(s);
        if (value != null) {
            value.cancel();
        }
        return value;
    }
    
    public Activity put(final String s, final Activity activity) {
        return this.m_map.put(s, activity);
    }
    
    public void remove(final Object o) {
        this.m_map.remove(o);
    }
    
    public Object[] keys() {
        return this.m_map.keySet().toArray();
    }
    
    public Object[] allKeys() {
        final Object[] array = this.m_map.keySet().toArray();
        if (this.m_parent != null) {
            final Object[] allKeys = this.m_parent.allKeys();
            if (allKeys != null && allKeys.length > 0) {
                final Object[] array2 = new Object[array.length + allKeys.length];
                System.arraycopy(array, 0, array2, 0, array.length);
                System.arraycopy(allKeys, 0, array2, array.length, allKeys.length);
                return array2;
            }
        }
        return array;
    }
    
    public void setParent(final ActivityMap parent) {
        this.m_parent = parent;
    }
    
    public ActivityMap getParent() {
        return this.m_parent;
    }
}
