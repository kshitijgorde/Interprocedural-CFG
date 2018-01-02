import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class a
{
    private int A;
    private Color[] B;
    private int C;
    
    public a(final Color color) {
        this.A = 0;
        (this.B = new Color[1])[0] = color;
    }
    
    public a(final Color color, final Color color2, final int n) {
        this.A = 1;
        this.C = n << 1;
        this.B = new Color[this.C];
        for (int i = 0; i < this.C; ++i) {
            this.B[i] = o.U(color, color2, i, this.C);
        }
    }
    
    public Color A(final int n, final int n2) {
        if (this.A == 1) {
            return this.B[Math.max(0, Math.min(this.C, n2))];
        }
        return this.B[0];
    }
}
