// 
// Decompiled by Procyon v0.5.30
// 

public class bp implements Runnable
{
    public b7 fq;
    public ab km;
    
    public bp(final b7 fq, final ab km) {
        this.fq = fq;
        this.km = km;
    }
    
    public final void run() {
        try {
            while (true) {
                final String ex = this.km.ex("", "", false);
                final av av = new av(16, this.km.f7);
                av.jt(String.valueOf(ex) + "\n");
                this.fq.ee(av);
                Thread.sleep(400L);
            }
        }
        catch (aa aa) {
            this.fq.ls("exit");
        }
        catch (Exception ex2) {
            this.fq.h9("Error in console-thread: " + ex2.toString());
        }
    }
}
