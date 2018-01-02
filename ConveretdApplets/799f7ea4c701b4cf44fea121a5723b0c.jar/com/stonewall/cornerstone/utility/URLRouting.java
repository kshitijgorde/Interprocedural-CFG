// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.util.concurrent.ConcurrentHashMap;
import java.net.URLStreamHandler;
import java.util.Map;
import java.net.URLStreamHandlerFactory;

public class URLRouting implements URLStreamHandlerFactory
{
    static URLRouting inst;
    final Map<String, URLStreamHandler> map;
    
    public static URLRouting getInstance() {
        if (URLRouting.inst == null) {
            URLRouting.inst = new URLRouting();
        }
        return URLRouting.inst;
    }
    
    protected URLRouting() {
        this.map = new ConcurrentHashMap<String, URLStreamHandler>();
    }
    
    @Override
    public URLStreamHandler createURLStreamHandler(final String protocol) {
        return this.map.get(protocol);
    }
    
    public void register(final String protocol, final URLStreamHandler handler) {
        this.map.put(protocol, handler);
    }
    
    public void clear(final String protocol) {
        this.map.remove(protocol);
    }
    
    public static void main(final String[] args) {
        System.out.println("done!");
    }
}
