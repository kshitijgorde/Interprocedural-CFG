// 
// Decompiled by Procyon v0.5.30
// 

public abstract class P implements Runnable
{
    boolean F;
    volatile boolean J;
    boolean S;
    Thread A;
    volatile int E;
    
    public P() {
        this.J = true;
    }
    
    abstract void D();
    
    abstract void F();
    
    abstract void J();
    
    public abstract void run();
}
