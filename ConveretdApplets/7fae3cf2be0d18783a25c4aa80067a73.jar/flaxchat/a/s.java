// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.a;

public class s extends Thread
{
    public boolean a;
    
    public s() {
        this.a = true;
    }
    
    public s(final Runnable runnable) {
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
