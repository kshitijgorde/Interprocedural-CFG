// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.thread;

public final class DefaultThreadPools
{
    private static final TaskRunnerFactory DEFAULT_TASK_RUNNER_FACTORY;
    
    public static TaskRunnerFactory getDefaultTaskRunnerFactory() {
        return DefaultThreadPools.DEFAULT_TASK_RUNNER_FACTORY;
    }
    
    public static void shutdown() {
        DefaultThreadPools.DEFAULT_TASK_RUNNER_FACTORY.shutdown();
    }
    
    static {
        DEFAULT_TASK_RUNNER_FACTORY = new TaskRunnerFactory();
    }
}
