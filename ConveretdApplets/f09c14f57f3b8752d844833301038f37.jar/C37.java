import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class C37 extends C28
{
    String b;
    boolean c;
    
    public C37() {
        this.c = false;
        this.b = null;
        super.P = 0;
        super.B = 0;
        super.H = 0;
        super.J = 0;
    }
    
    public C37(final String b, final int p5, final int b2, final int h, final int j) {
        super.P = p5;
        super.B = b2;
        super.H = h;
        super.J = j;
        this.b = b;
        this.c = true;
    }
    
    public void a(final C20 c20, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        System.out.println("ViewportWidget::worldToWindowPaintPrint();");
    }
    
    public void b(final C15 c15, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f || !super.C) {
            return;
        }
        if (this.c) {
            final int n8 = (int)((super.P - n) * n2 + n3);
            final int n9 = (int)(n7 - ((super.B - n4) * n5 + n6));
            final int n10 = (int)((super.P + super.H - n) * n2 + n3);
            final int n11 = (int)(n7 - ((super.B + super.J - n4) * n5 + n6));
            c15.f(n8, n11, Math.abs(n10 - n8), Math.abs(n11 - n9));
        }
        else {
            c15.m();
        }
    }
    
    public boolean a(final Rectangle rectangle) {
        return true;
    }
    
    public Object clone() {
        return new C37(this.b, super.P, super.B, super.P + super.H, super.B + super.J);
    }
}
