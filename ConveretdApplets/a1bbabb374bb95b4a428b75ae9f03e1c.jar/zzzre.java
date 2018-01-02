import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class zzzre
{
    private PackedPieChartEval l;
    String[] BdHb;
    int bDHb;
    int BDHb;
    int bdhB;
    float BdhB;
    
    int bdhb() {
        return this.l.zzzhb(this.BdhB, this.l.BDHblF * this.l.bDHblF, this.l.bDHblf).y;
    }
    
    zzzre(final PackedPieChartEval l, final zzzq zzzq) {
        this.l = l;
    }
    
    float Bdhb(final Vector vector, final int n) {
        return vector.elementAt(n).bDhb() - this.bDhb();
    }
    
    float bDhb() {
        final float n = (float)Math.sin(this.BdhB);
        float n2;
        if (n >= 0.0f) {
            n2 = n + 1.0f;
            if (null != null) {}
        }
        else {
            n2 = n - 1.0f;
        }
        return n2;
    }
    
    void BDhb(final Vector vector) {
        int i = 0;
        int size = vector.size();
        int n = size / 2;
        while (i != size) {
            final float bdhb = this.Bdhb(vector, n);
            if (bdhb < 0.0f) {
                size = n;
            }
            else {
                if (bdhb <= 0.0f) {
                    break;
                }
                if (i == n) {
                    ++n;
                    break;
                }
                i = n;
            }
            n = (i + size) / 2;
        }
        vector.insertElementAt(this, n);
    }
    
    String[] bdHb() {
        return this.BdHb;
    }
}
