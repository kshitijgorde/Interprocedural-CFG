// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.xidget;

import org.xmodel.IDispatcher;
import java.util.concurrent.Executors;
import com.stonewall.cornerstone.component.ComponentServer;
import java.util.concurrent.ExecutorService;
import com.stonewall.cornerstone.component.Component;

public class XidgetNBIComponent implements Component
{
    public static final String incomingQueue = "queue.xidget.ps";
    public static final String outgoingQueue = "queue.xidget.ui";
    private JmsLink link;
    private ExecutorService executor;
    
    @Override
    public void init(final ComponentServer container) throws Exception {
        this.executor = Executors.newCachedThreadPool();
        this.link = new JmsLink("queue.xidget.ps", "queue.xidget.ui", 30000);
        this.link.getProtocol().setDispatcher(new ExecutorDispatcher(this.executor));
        this.link.getProtocol().addPackage("com.stonewall.cornerstone.xaction");
        this.link.open();
    }
    
    @Override
    public void shutdown() {
        this.link.close();
        this.executor.shutdownNow();
    }
    
    @Override
    public void trace() {
    }
    
    private static class ExecutorDispatcher implements IDispatcher
    {
        private ExecutorService executor;
        
        public ExecutorDispatcher(final ExecutorService executor) {
            this.executor = executor;
        }
        
        @Override
        public void execute(final Runnable runnable) {
            this.executor.execute(runnable);
        }
    }
}
