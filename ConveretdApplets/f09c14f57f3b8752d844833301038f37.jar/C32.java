// 
// Decompiled by Procyon v0.5.30
// 

public class C32 extends C35
{
    String g;
    public static String h;
    Integer i;
    boolean j;
    
    static {
        C32.h = "Copyright (c) 2000 - ZoomON Inc.  All Rights Reserved";
    }
    
    public void b(final C15 c15, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
    }
    
    public void a() {
        this.j = true;
    }
    
    public String b() {
        return this.g;
    }
    
    public C32(final int n) {
        this.j = false;
        this.i = new Integer(n);
    }
    
    public boolean c() {
        return this.j;
    }
    
    public Object clone() {
        if (this.i != null) {
            return new C32(this.i);
        }
        return new C32();
    }
    
    public void a(final C20 c20, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        System.out.println("LayerWidget::worldToWindowPaintPrint();");
    }
    
    public C32() {
        this.j = false;
    }
    
    public Integer e() {
        return this.i;
    }
    
    public void f(final String g) {
        this.g = g;
    }
}
