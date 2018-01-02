// 
// Decompiled by Procyon v0.5.30
// 

public abstract class abstract implements Runnable
{
    protected Object r;
    protected volatile Thread q;
    protected int n;
    protected String name;
    
    protected abstract(final String name, final Object r) {
        this.n = 25000;
        this.r = r;
        this.name = name;
    }
    
    public void start() {
        (this.q = new Thread(this, this.name)).start();
    }
    
    public void stop() {
        final Thread q = this.q;
        this.q = null;
        if (q != null && q.isAlive()) {
            q.interrupt();
        }
    }
    
    protected boolean b(final String s) {
        return true;
    }
    
    protected boolean _(final String s) {
        final String a = m.a(this.r, s, null);
        return a != null && a.trim().length() != 0;
    }
    
    public abstract void run();
}
