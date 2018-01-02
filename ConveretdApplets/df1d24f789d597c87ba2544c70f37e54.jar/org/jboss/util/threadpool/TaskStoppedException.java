// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.threadpool;

public class TaskStoppedException extends RuntimeException
{
    public TaskStoppedException() {
    }
    
    public TaskStoppedException(final String message) {
        super(message);
    }
}
