// 
// Decompiled by Procyon v0.5.30
// 

public class C16 extends C22
{
    public C16(final int n, final int n2) {
        super(n, n2, 2);
    }
    
    public void a(final C20 c20, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        System.out.println("PointWidget::worldToWindowPaintPrint();");
    }
    
    public void b(final C15 c15, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f || !super.C) {
            return;
        }
        c15.t((int)((super.f - n) * n2 + n3), (int)(n7 - ((super.d - n4) * n5 + n6)));
    }
    
    public Object clone() {
        return new C16(super.f, super.d);
    }
}
