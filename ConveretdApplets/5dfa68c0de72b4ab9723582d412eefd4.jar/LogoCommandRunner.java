// 
// Decompiled by Procyon v0.5.30
// 

class LogoCommandRunner implements Runnable
{
    Object[] listtorun;
    LContext context;
    boolean silent;
    
    static void startLogoThread(final String s, final LContext lContext) {
        stopLogoThread(lContext);
        (lContext.logoThread = new Thread(new LogoCommandRunner(s, lContext, true), "Logo")).start();
    }
    
    static void stopLogoThread(final LContext lContext) {
        if (lContext.logoThread == null) {
            return;
        }
        lContext.timeToStop = true;
        try {
            lContext.logoThread.join();
        }
        catch (InterruptedException ex) {}
        lContext.logoThread = null;
    }
    
    LogoCommandRunner(final String s, final LContext context, final boolean silent) {
        this.listtorun = Logo.parse(s, context);
        this.context = context;
        this.silent = silent;
    }
    
    public void run() {
        synchronized (this.context) {
            if (this.context.logoThread == null) {
                this.context.logoThread = Thread.currentThread();
            }
            if (this.context.logoThread != Thread.currentThread()) {
                return;
            }
            final String runToplevel = Logo.runToplevel(this.listtorun, this.context);
            if (this.context.tyo != null && !this.context.timeToStop) {
                if (runToplevel != null) {
                    this.context.tyo.println(runToplevel);
                    this.context.errormessage = runToplevel;
                }
                if (!this.silent) {
                    this.context.tyo.println("ok");
                }
            }
            this.context.logoThread = null;
        }
    }
}
