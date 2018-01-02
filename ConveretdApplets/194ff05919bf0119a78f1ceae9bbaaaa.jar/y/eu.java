// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.util.Vector;
import java.awt.Color;

public final class eu
{
    public String a;
    int a;
    int b;
    public String b;
    String c;
    Color a;
    ax a;
    ax b;
    Vector a;
    boolean a;
    
    eu(final String a, final String b) {
        this.a = 0;
        this.c = "";
        this.a = null;
        this.a = new Vector();
        this.a = a;
        this.b = b;
    }
    
    static int a(final int n) {
        if ((n & 0x4) != 0x0 || (n & 0x2) != 0x0) {
            return 1;
        }
        if ((n & 0x8) != 0x0) {
            return -1;
        }
        return 0;
    }
}
