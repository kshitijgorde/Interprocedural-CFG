import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class ak extends al
{
    public int[][] a;
    public am b;
    
    public void a(final k k) {
        super.a(k);
        k.f(this.b);
    }
    
    public int a(final int n) {
        return super.a(n) + 12;
    }
    
    public void a(final n n) {
        super.a(n);
        n.a(new Color(20, 53, 0));
        n.b(2, super.d - super.k - 22, super.c - super.k - 4, 1);
    }
    
    public ak(final k k, final b b, final String s, final String s2, final String s3, final int n) {
        super(k, b, s, s2, s3, n);
        this.a = new int[][] { { 114, 186, 0 }, { 114, 186, 0 }, { 114, 186, 0 }, { 114, 186, 0 }, { 51, 85, 1 }, { 255, 235, 30 }, { 255, 255, 255 } };
        this.b = new am(super.m, 201, b);
        this.b.b = "Click here to disable hints";
        this.b.a(this.a);
        this.b.p = true;
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        super.a(n, n2, n3, n4);
        final aa h = super.h;
        h.b -= 8;
        this.b.a(super.a + 2, super.b + super.d - super.k - 21, super.c - super.k - 4, 20);
    }
    
    public void b(final k k) {
        super.b(k);
        k.c(this.b);
    }
}
