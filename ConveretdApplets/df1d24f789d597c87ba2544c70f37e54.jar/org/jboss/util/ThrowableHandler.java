// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public final class ThrowableHandler
{
    protected static List listeners;
    
    public static void addThrowableListener(final ThrowableListener listener) {
        if (!ThrowableHandler.listeners.contains(listener)) {
            ThrowableHandler.listeners.add(listener);
        }
    }
    
    public static void removeThrowableListener(final ThrowableListener listener) {
        ThrowableHandler.listeners.remove(listener);
    }
    
    protected static void fireOnThrowable(final int type, final Throwable t) {
        final Object[] list = ThrowableHandler.listeners.toArray();
        for (int i = 0; i < list.length; ++i) {
            ((ThrowableListener)list[i]).onThrowable(type, t);
        }
    }
    
    public static void add(final int type, final Throwable t) {
        if (t == null) {
            return;
        }
        try {
            fireOnThrowable(type, t);
        }
        catch (Throwable bad) {
            System.err.println("Unable to handle throwable: " + t + " because of:");
            bad.printStackTrace();
        }
    }
    
    public static void add(final Throwable t) {
        add(0, t);
    }
    
    public static void addError(final Throwable t) {
        add(1, t);
    }
    
    public static void addWarning(final Throwable t) {
        add(1, t);
    }
    
    static {
        ThrowableHandler.listeners = Collections.synchronizedList(new ArrayList<Object>());
    }
    
    public interface Type
    {
        public static final int UNKNOWN = 0;
        public static final int ERROR = 1;
        public static final int WARNING = 2;
    }
}
