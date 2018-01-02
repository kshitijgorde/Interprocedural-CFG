// 
// Decompiled by Procyon v0.5.30
// 

public class C26 extends C35
{
    String e;
    C14 f;
    public static String g;
    C14 h;
    
    public double[] b(final int n, final int n2) {
        return this.h.a(n, n2, 0.0);
    }
    
    public C26(final String e, final double[][] array) {
        this.f = new C14(array);
        this.h = this.f.b();
        this.e = e;
    }
    
    static {
        C26.g = "Copyright (c) 2000 - ZoomON Inc.  All Rights Reserved";
    }
    
    public Object clone() {
        return new C26(this.e, this.f.c());
    }
    
    public boolean d() {
        return true;
    }
    
    public void a(final C20 c20, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        System.out.println("UnitsWidget::worldToWindowPaintPrint();");
    }
    
    public void b(final C15 c15, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
    }
}
