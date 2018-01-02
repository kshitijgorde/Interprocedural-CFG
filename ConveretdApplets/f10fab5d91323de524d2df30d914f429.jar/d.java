// 
// Decompiled by Procyon v0.5.30
// 

public class d extends Thread
{
    CHAT a;
    public int b;
    public int a;
    String a;
    public static boolean a;
    
    public d(final CHAT a, final String a2, final int b, final int a3) {
        this.a = a;
        this.a = a3;
        this.b = b;
        this.a = a2;
    }
    
    public void run() {
        final boolean a = d.a;
        int n2;
        int a2;
        final int n = a2 = (n2 = (this.a.x ? 1 : 0));
        if (!a) {
            if (n == 0) {
                return;
            }
            n2 = (a2 = this.a);
        }
        final int m = this.a.m;
        d d = null;
        Label_0079: {
            if (!a) {
                if (a2 != m) {
                    return;
                }
                this.setName(String.valueOf(this.b));
                this.a.a(this.a, this.b, this.a);
                d = this;
                if (a) {
                    break Label_0079;
                }
                n2 = this.b;
            }
            if (n2 != m) {
                return;
            }
            d = this;
        }
        d.a.n = true;
    }
}
