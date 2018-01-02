// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

public class ImmediateDispatcher implements IDispatcher
{
    @Override
    public void execute(final Runnable runnable) {
        runnable.run();
    }
}
