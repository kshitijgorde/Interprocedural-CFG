// 
// Decompiled by Procyon v0.5.30
// 

public class C00 extends C35
{
    boolean a;
    public static String b;
    
    public C00(final boolean a) {
        this.a = a;
    }
    
    public Object clone() {
        return new C00(this.a);
    }
    
    public void a(final C20 c20, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        System.out.println("SetFillWidget::worldToWindowPaintPrint();");
    }
    
    static {
        C00.b = "Copyright (c) 2000 - ZoomON Inc.  All Rights Reserved";
    }
    
    public void b(final C15 c15, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f) {
            return;
        }
        c15.n(this.a);
    }
}
