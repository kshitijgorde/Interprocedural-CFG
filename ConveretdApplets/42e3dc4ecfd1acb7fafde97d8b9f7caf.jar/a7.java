// 
// Decompiled by Procyon v0.5.30
// 

public class a7 extends Thread
{
    private r a;
    private a4 b;
    public boolean c;
    
    public a7(final r a, final a4 b) {
        this.c = true;
        this.a = null;
        this.b = null;
        this.a = a;
        this.b = b;
    }
    
    public void run() {
        while (this.c) {
            try {
                this.a.j((String)this.b.a());
            }
            catch (Exception ex) {}
        }
    }
}
