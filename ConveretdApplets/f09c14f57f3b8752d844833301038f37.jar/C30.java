import java.awt.AWTException;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class C30 extends C35
{
    public static String a;
    Color b;
    public int c;
    
    static {
        C30.a = "Copyright (c) 2000 - ZoomON Inc.  All Rights Reserved";
    }
    
    public C30(final int c) {
        this.c = -1;
        this.c = c;
        this.b = null;
    }
    
    public void a(final C20 c20, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
    }
    
    public C30(final Color b) {
        this.c = -1;
        this.b = b;
        this.c = -1;
    }
    
    public void b(final C15 c15, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f) {
            return;
        }
        if (this.b != null) {
            c15.B(this.b);
        }
        else {
            try {
                c15.H(this.c);
            }
            catch (AWTException ex) {
                System.out.println(ex + " ERROR 39292");
                ex.printStackTrace();
            }
        }
    }
    
    public Object clone() {
        if (this.b != null) {
            return new C30(this.b);
        }
        return new C30(this.c);
    }
}
