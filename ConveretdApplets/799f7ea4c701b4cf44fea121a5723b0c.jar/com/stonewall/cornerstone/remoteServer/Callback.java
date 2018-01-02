// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.remoteServer;

import java.lang.reflect.Method;

public class Callback
{
    private String method;
    private Class[] classes;
    
    public Callback(final String method, final Class[] classes) {
        this.method = method;
        this.classes = classes;
    }
    
    void execute(final Request req, final Object... objects) throws Exception {
        final Method m = req.getClass().getMethod(this.method, (Class<?>[])this.classes);
        m.invoke(req, objects);
    }
}
