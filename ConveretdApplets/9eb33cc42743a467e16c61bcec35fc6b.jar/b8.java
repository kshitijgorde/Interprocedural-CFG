// 
// Decompiled by Procyon v0.5.30
// 

public abstract class b8 extends Thread
{
    public int fw;
    public b6 cp;
    
    public b8(final int fw) {
        this.fw = fw;
        this.cp = null;
    }
    
    public final void l4(final b6 cp) {
        this.cp = cp;
    }
    
    public abstract void d_() throws Exception;
    
    public final void ed() {
    }
    
    public final void run() {
        try {
            this.d_();
        }
        catch (Exception ex) {
            if (ca.mn) {
                System.out.println("--- channel exit (exception is not an error):");
                ex.printStackTrace();
                System.out.println("---");
            }
            this.ed();
            if (this.cp != null) {
                this.cp.ed(this);
            }
        }
        catch (ThreadDeath threadDeath) {
            ca.md("Channel killed " + this.fw);
            throw threadDeath;
        }
    }
}
