// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature;

import java.lang.reflect.InvocationTargetException;

public interface IAsyncFeature
{
    void run(final Runnable p0);
    
    void runWait(final Runnable p0) throws InvocationTargetException, InterruptedException;
    
    void schedule(final Object p0, final int p1, final boolean p2, final Runnable p3);
    
    void cancel(final Object p0);
}
