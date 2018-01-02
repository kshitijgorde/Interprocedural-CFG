// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import d.a.a.a.c;
import d.a.a.a.e;

public class a4
{
    private e a;
    private c int;
    private short[] for;
    private byte[] do;
    aw if;
    
    public a4(final aw if1) {
        this.a = null;
        this.int = null;
        this.for = null;
        this.do = null;
        this.if = null;
        this.if = if1;
    }
    
    public int if(final byte[] array, final int n, final byte[] array2) {
        try {
            if (n < 320 || n % 320 != 0) {
                this.if.a(3, "ERROR,invalid pcm to gsm packet length");
                return 0;
            }
            if (this.a == null) {
                this.a = new e();
                this.for = new short[160];
                this.do = new byte[33];
            }
            int n2 = 0;
            for (int i = 0; i < n; i += 320) {
                for (int j = 0; j < 160; ++j) {
                    this.for[j] = ah.a(array, i + j * 2, false);
                }
                this.a.a(this.for, this.do);
                System.arraycopy(this.do, 0, array2, n2, 33);
                n2 += 33;
            }
            return n2 + 33;
        }
        catch (Exception ex) {
            this.if.a(3, "gsmencode", ex);
            return 0;
        }
    }
    
    public int a(final byte[] array, final int n, final byte[] array2) {
        try {
            if (n < 32 || (n % 32 != 0 && n % 33 != 0)) {
                this.if.a(3, "ERROR,invalid gsm to pcm packet length");
                return 0;
            }
            if (this.int == null) {
                this.int = new c();
            }
            int n2 = 0;
            for (int i = 0; i < n; i += 33) {
                this.int.a(array, i, array2, n2, false);
                n2 += 320;
            }
            return n2;
        }
        catch (Exception ex) {
            this.if.a(3, "gsmdecode", ex);
            return 0;
        }
    }
}
