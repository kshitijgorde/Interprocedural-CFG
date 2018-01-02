// 
// Decompiled by Procyon v0.5.30
// 

class SunLineArray extends WorldArray
{
    private MofPanel p;
    
    public SunLineArray(final int n, final MofPanel p2) {
        super(n, 2);
        this.p = p2;
    }
    
    public void Compute() {
        if (!this.p.Julian.IsChanged() && !this.p.Time.IsChanged()) {
            return;
        }
        final double n = 0.017453292519943295;
        final double n2 = 1.0 / n;
        final double n3 = 23.5 * Math.sin(360.0 * (this.p.Julian.GetValue() - 91.2) * n / 365.0) * n;
        for (int i = 0; i < this.Length(); ++i) {
            final double n4 = Math.tan(n3) * Math.tan(1.5707963267948966 - i * 3.141592653589793 / this.Length());
            double n5;
            double n6;
            if (n4 > 1.0) {
                n5 = 0.0;
                n6 = -1.0;
            }
            else if (n4 < -1.0) {
                n5 = -1.0;
                n6 = 0.0;
            }
            else {
                n5 = -1.0 * (360.0f + this.p.Time.GetValue() * 15.0f - Math.acos(n4) * n2) % 360.0;
                n6 = -1.0 * (360.0f + this.p.Time.GetValue() * 15.0f + Math.acos(n4) * n2) % 360.0;
            }
            final int n7 = i;
            final MofPanel p = this.p;
            this.SetValue(n7, MofPanel.SunRiseIndex, (float)(-n5));
            final int n8 = i;
            final MofPanel p2 = this.p;
            this.SetValue(n8, MofPanel.SunSetIndex, (float)(-n6));
        }
        this.SetChanged();
    }
}
