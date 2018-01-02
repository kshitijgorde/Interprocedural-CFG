import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class C43 extends C35
{
    Font j;
    double k;
    public static final Font m;
    Font q;
    public static String s;
    
    public String a() {
        return this.j.getName();
    }
    
    public boolean b() {
        return this.j.isItalic();
    }
    
    public C43(final String s, final int n, final boolean b, final boolean b2) {
        this.k = n;
        String s2 = "TimesRoman";
        if (s.toLowerCase().startsWith("helvetica")) {
            s2 = "Helvetica";
        }
        else if (s.toLowerCase().startsWith("courier")) {
            s2 = "Courier";
        }
        if (b) {
            this.q = new Font(s2, 1, n);
        }
        else if (b2) {
            this.q = new Font(s2, 2, n);
        }
        else {
            this.q = new Font(s2, 0, n);
        }
        this.j = this.q;
    }
    
    public boolean c() {
        return this.j.isBold();
    }
    
    static {
        C43.s = "Copyright (c) 2000 - ZoomON Inc.  All Rights Reserved";
        m = new Font("courier", 1, 4);
    }
    
    public int e() {
        return (int)this.k;
    }
    
    public Object clone() {
        return new C43(this.q.getName(), this.q.getSize(), this.q.getStyle() == 1, this.q.getStyle() == 0);
    }
    
    public void a(final C20 c20, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        System.out.println("SetFontWidget::worldToWindowPaintPrint();");
    }
    
    public void b(final C15 c15, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f) {
            return;
        }
        c15.A(this.q);
    }
}
