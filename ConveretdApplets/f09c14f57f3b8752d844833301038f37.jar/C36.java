import java.util.Enumeration;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class C36 extends C28
{
    Vector c;
    public static String d;
    
    public void f(final int n, final int n2) {
        super.f(n, n2);
        final Enumeration<C28> elements = this.c.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().f(n, n2);
        }
    }
    
    public void a(final C20 c20, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        System.out.println("DrawingWidgetContainer::worldToWindowPaintPrint();");
    }
    
    public void b(final C15 c15, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f || !super.C) {
            return;
        }
        final Enumeration<C28> elements = this.c.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().b(c15, n, n2, n3, n4, n5, n6, n7);
        }
    }
    
    public void b(final C28 c28) {
        this.c.addElement(c28);
        if (c28.P < super.P) {
            super.P = c28.P;
        }
        if (c28.B < super.B) {
            super.B = c28.B;
        }
        if (c28.H + c28.P > super.P + super.H) {
            super.H = c28.H + c28.P - super.P;
        }
        if (c28.B + c28.J > super.B + super.J) {
            super.J = c28.B + c28.J - super.B;
        }
    }
    
    public C36() {
        this.c = new Vector();
    }
    
    public Object clone() {
        final C36 c36 = new C36();
        final Enumeration<C28> elements = this.c.elements();
        while (elements.hasMoreElements()) {
            c36.b(elements.nextElement());
        }
        return c36;
    }
    
    static {
        C36.d = "Copyright (c) 2000 - ZoomON Inc.  All Rights Reserved";
    }
}
