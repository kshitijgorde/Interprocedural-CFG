// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.e;

public class r extends Thread
{
    public boolean a;
    
    public r() {
        this.a = true;
    }
    
    public r(final Runnable runnable) {
        super(runnable);
        this.a = true;
    }
    
    public void a() {
        this.a = false;
        try {
            super.interrupt();
        }
        catch (SecurityException ex) {
            ex.printStackTrace();
        }
    }
}
