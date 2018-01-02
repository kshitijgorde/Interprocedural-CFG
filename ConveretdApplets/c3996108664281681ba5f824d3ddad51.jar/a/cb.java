// 
// Decompiled by Procyon v0.5.30
// 

package a;

import com.spilka.client.muc.AppletAbstract;

public class cb implements cd
{
    private bH q;
    private b q;
    
    public cb(final bH q) {
        this.q = q;
    }
    
    public final void x(final cJ cj) {
        if (cj.q() != 4202576) {
            return;
        }
        final String q = cj.q(0, 0);
        final int q2 = cj.q(0, 0);
        final int q3 = cj.q(0, 1);
        final int q4 = cj.q(0, 2);
        switch (q3) {
            case 1: {
                final b b;
                (b = new b(this.q.q, cv.q("Info"), new String[] { cv.q("Yes"), cv.q("No") }, new String[] { cv.q(q) }, null, this.q)).setVisible(true);
                final cJ q5;
                (q5 = q(q2, 3, this.q.q(), "")).q(0, 0, cv.q("Yes").equals(b.q()));
                this.q.q(q5);
            }
            case 3: {
                if (this.q != null && this.q.isVisible()) {
                    this.q.dispose();
                }
                bp bp;
                if ((bp = (bp)this.q.e.w(q4)) == null) {
                    bp = (bp)this.q.r.w(q4);
                }
                final bj bj = (bj)this.q.w.w(bp.u);
                final String string = "AV conference with " + bp.getName();
                final String s = q;
                final StringBuffer append = new StringBuffer().append(AppletAbstract.q().getCodeBase()).append("Resources/");
                final bH q6 = this.q;
                cu.q(string, s, append.append(bH.p).append("/").append(bj.getName()).toString());
                System.out.println("Open AV");
            }
            case 2: {
                if (this.q != null && this.q.isVisible()) {
                    this.q.q(q);
                    return;
                }
                (this.q = new b(this.q.q, cv.q("Info"), new String[] { cv.q("Close") }, new String[] { cv.q(q) }, null, this.q)).setModal(false);
                this.q.setVisible(true);
                break;
            }
        }
    }
    
    public static cJ q(final int n, final int n2, final int n3, final String s) {
        final cJ cj;
        (cj = new cJ(4202576, 1)).q(0, 0, n);
        cj.q(0, 1, n2);
        cj.q(0, 2, n3);
        cj.q(0, 0, s);
        return cj;
    }
    
    public cb() {
    }
}
