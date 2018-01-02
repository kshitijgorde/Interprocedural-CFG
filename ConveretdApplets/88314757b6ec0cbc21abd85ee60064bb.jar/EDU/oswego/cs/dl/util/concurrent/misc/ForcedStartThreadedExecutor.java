// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.ThreadedExecutor;

class ForcedStartThreadedExecutor extends ThreadedExecutor
{
    public void execute(final Runnable runnable) throws InterruptedException {
        final ForcedStartRunnable forcedStartRunnable = new ForcedStartRunnable(runnable);
        super.execute(forcedStartRunnable);
        forcedStartRunnable.started().acquire();
    }
}
