// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingDispatcher implements IDispatcher
{
    private BlockingQueue<Runnable> A;
    
    public BlockingDispatcher() {
        this.A = new ArrayBlockingQueue<Runnable>(100);
    }
    
    @Override
    public void execute(final Runnable runnable) {
        try {
            this.A.put(runnable);
        }
        catch (InterruptedException ex) {}
    }
    
    public void process() {
        try {
            final Runnable runnable = this.A.take();
            if (runnable != null) {
                runnable.run();
            }
        }
        catch (InterruptedException ex) {}
    }
}
