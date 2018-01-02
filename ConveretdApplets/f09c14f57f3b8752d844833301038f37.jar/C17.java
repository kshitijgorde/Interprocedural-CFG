import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class C17 extends C35 implements C41
{
    int d;
    Rectangle e;
    int f;
    public static String g;
    int h;
    String i;
    int j;
    
    static {
        C17.g = "Copyright (c) 2000 - ZoomON Inc.  All Rights Reserved";
    }
    
    public Rectangle a() {
        return this.e;
    }
    
    public C17(final Rectangle rectangle, final String s) {
        this(rectangle.x, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height, s);
    }
    
    public void b(final C15 c15, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
    }
    
    public C17(final int j, final int d, final int f, final int h, final String i) {
        this.i = i;
        this.e = new Rectangle(Math.min(j, f), Math.min(d, h), Math.max(j, f) - Math.min(j, f), Math.max(d, h) - Math.min(d, h));
        this.j = j;
        this.f = f;
        this.d = d;
        this.h = h;
    }
    
    public String c() {
        return this.i;
    }
    
    public Object clone() {
        return new C17(this.j, this.d, this.f, this.h, this.i);
    }
    
    public void a(final C20 c20, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        System.out.println("SetViewWidget::worldToWindowPaintPrint();");
    }
}
