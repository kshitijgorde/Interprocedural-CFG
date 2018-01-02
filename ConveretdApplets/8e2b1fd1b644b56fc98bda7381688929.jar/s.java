import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class s extends p
{
    private double[] R;
    private int tra;
    
    public s(final int n) {
        super(n, 1);
        this.R = new double[1];
        this.tra = 0;
        this.tra = 0;
    }
    
    public s(final int n, int tra) {
        super(n, 1);
        this.R = new double[1];
        this.tra = 0;
        if (tra < 0) {
            tra = 0;
        }
        this.tra = tra;
    }
    
    public boolean a(final double n, final double n2) {
        this.R[0] = n2;
        return super.b(n, this.R);
    }
    
    public void b(final Graphics graphics, final o o, final o o2) {
        if (super.S < 0) {
            return;
        }
        this._(graphics, o, o2);
        graphics.setColor(super.T);
        final int max = Math.max(super.U, this.b());
        int n = this._();
        if (super.V >= 0) {
            n = Math.min(n, super.V);
        }
        if (max == n) {
            graphics.fillOval((int)o.b(super.W[max]) - this.tra, (int)o2.b(super.X.getValue(max, 0)) - this.tra, 1 + this.tra * 2, 1 + this.tra * 2);
        }
        else if (max < n) {
            if (this.tra == 0) {
                final int[] array = new int[1 + n - max];
                final int[] array2 = new int[1 + n - max];
                for (int i = max; i <= n; ++i) {
                    array[i - max] = (int)o.b(super.W[i]);
                    array2[i - max] = (int)o2.b(super.X.getValue(i, 0));
                }
                graphics.drawPolyline(array, array2, array.length);
            }
            else {
                for (int j = max; j < n; ++j) {
                    d._(graphics, (int)o.b(super.W[j]), (int)o2.b(super.X.getValue(j, 0)), (int)o.b(super.W[j + 1]), (int)o2.b(super.X.getValue(j + 1, 0)), this.tra);
                }
            }
        }
    }
    
    public int X() {
        return this.tra;
    }
    
    public void I(final int tra) {
        this.tra = tra;
    }
}
