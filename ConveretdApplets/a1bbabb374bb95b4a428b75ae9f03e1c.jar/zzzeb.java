import java.awt.Point;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class zzzeb
{
    private PackedPieChartEval l;
    zzzq BdHb;
    int bDHb;
    int BDHb;
    boolean bdhB;
    boolean BdhB;
    float bDhB;
    float BDhB;
    
    void bdhb(final int bdHb, final int bdHb2, final boolean bdhB, final boolean bdhB2, final float bDhB, final float bDhB2) {
        this.BDHb = bdHb2;
        this.bDHb = bdHb;
        this.BdhB = bdhB2;
        this.bdhB = bdhB;
        this.BDhB = bDhB2;
        this.bDhB = bDhB;
    }
    
    float Bdhb(final Vector vector, final int n) {
        return vector.elementAt(n).bdHb() - this.bdHb();
    }
    
    void bDhb(final Vector vector) {
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
    
    zzzeb(final PackedPieChartEval l, final zzzq bdHb) {
        this.BdHb = bdHb;
        this.l = l;
    }
    
    boolean BDhb(final Point point) {
        final Point point2 = new Point(point.x - this.bDHb, point.y - this.BDHb);
        float zzzrb = this.l.zzzrb(point2);
        if (zzzrb < this.bDhB) {
            zzzrb += 6.283185307179586;
        }
        return zzzrb <= this.BDhB + this.bDhB && this.l.zzzyb(point2) <= this.l.BDHblF;
    }
    
    float bdHb() {
        final float n = (float)Math.cos(this.bDhB);
        if (n * (float)Math.cos(this.BDhB + this.bDhB) >= 0.0f) {
            return (float)Math.sin(this.bDhB + this.BDhB / 2.0f);
        }
        if (n > 0.0f) {
            return 1.0f;
        }
        return -1.0f;
    }
}
