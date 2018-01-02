// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

public class l extends k
{
    public l(final int n) {
        super(n);
    }
    
    public void a(final String s) {
        long long1;
        int n;
        for (long1 = Long.parseLong(s), n = 0; n < super.a.b() && long1 >= Long.parseLong(super.a.b(n).toString()); ++n) {}
        super.a.b(s, n);
    }
    
    public String b() {
        String string = null;
        if (super.a.b() > 0) {
            string = super.a.b(0).toString();
            super.a.d(0);
        }
        return string;
    }
}
