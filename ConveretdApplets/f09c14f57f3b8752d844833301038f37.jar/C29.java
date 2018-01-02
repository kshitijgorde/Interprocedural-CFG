import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class C29 extends C35
{
    public static String a;
    Color b;
    int c;
    
    public Object clone() {
        if (this.c == -1) {
            return new C29(this.b);
        }
        return new C29(this.c);
    }
    
    public String toString() {
        return "i=" + this.c + " c=" + this.b;
    }
    
    public void a(final C20 c20, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        System.out.println("SetBackgrounfWidget::worldToWindowPaintPrint();");
    }
    
    public void b(final C15 c15, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f) {
            return;
        }
        if (this.b != null) {
            c15.o(this.b);
        }
        else {
            c15.r(this.c);
        }
    }
    
    static {
        C29.a = "Copyright (c) 2000 - ZoomON Inc.  All Rights Reserved";
    }
    
    public C29(final int c) {
        this.c = -1;
        this.c = c;
        this.b = null;
    }
    
    public C29(final Color b) {
        this.c = -1;
        this.b = b;
        this.c = -1;
    }
}
