// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import java.util.Enumeration;
import java.util.Hashtable;

public class AppRegistry
{
    private Hashtable registry;
    private static AppRegistry localreg;
    
    static {
        AppRegistry.localreg = null;
    }
    
    private AppRegistry() {
        this.registry = new Hashtable();
    }
    
    public void clearAll() {
        if (AppRegistry.localreg != null) {
            final Enumeration<Object> keys = AppRegistry.localreg.registry.keys();
            while (keys.hasMoreElements()) {
                AppRegistry.localreg.registry.remove(AppRegistry.localreg.registry.get(keys.nextElement()));
            }
            AppRegistry.localreg.registry.clear();
        }
    }
    
    public static AppRegistry getInstance() {
        if (AppRegistry.localreg == null) {
            (AppRegistry.localreg = new AppRegistry()).put("nodehash", new Hashtable(500));
        }
        return AppRegistry.localreg;
    }
    
    public Object lookup(final String s) {
        return this.registry.get(s);
    }
    
    public void put(final String s, final Object o) {
        this.registry.put(s, o);
    }
    
    public void remove(final Object o) {
        this.registry.remove(o);
    }
    
    public Object remove(final String s) {
        return this.registry.remove(this.registry.get(s));
    }
    
    public String toString() {
        return this.registry.toString();
    }
}
