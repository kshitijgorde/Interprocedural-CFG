// 
// Decompiled by Procyon v0.5.30
// 

package a;

import com.spilka.client.muc.AppletAbstract;

public class dH implements dJ
{
    private cU q;
    private b q;
    
    public dH(final cU q) {
        this.q = q;
    }
    
    public final void c(final es es) {
        if (es.q() != 4202576) {
            return;
        }
        final String q = es.q(0, 0);
        final int q2 = es.q(0, 0);
        final int q3 = es.q(0, 1);
        final int q4 = es.q(0, 2);
        switch (q3) {
            case 1: {
                final b b;
                (b = new b(this.q.q, eb.q("Info"), new String[] { eb.q("Yes"), eb.q("No") }, new String[] { eb.q(q) }, null, this.q)).setVisible(true);
                final es q5;
                (q5 = q(q2, 3, this.q.q(), "")).q(0, 0, eb.q("Yes").equals(b.q()));
                this.q.q(q5);
            }
            case 3: {
                if (this.q != null && this.q.isVisible()) {
                    this.q.dispose();
                }
                cz cz;
                if ((cz = (cz)this.q.e.w(q4)) == null) {
                    cz = (cz)this.q.r.w(q4);
                }
                final cm cm = (cm)this.q.w.w(cz.e);
                final String string = "AV conference with " + cz.getName();
                final String s = q;
                final StringBuffer append = new StringBuffer().append(AppletAbstract.q().getCodeBase()).append("Resources/");
                final cU q6 = this.q;
                ea.q(string, s, append.append(cU.a).append("/").append(cm.getName()).toString());
                System.out.println("Open AV");
            }
            case 2: {
                if (this.q != null && this.q.isVisible()) {
                    this.q.q(q);
                    return;
                }
                (this.q = new b(this.q.q, eb.q("Info"), new String[] { eb.q("Close") }, new String[] { eb.q(q) }, null, this.q)).setModal(false);
                this.q.setVisible(true);
                break;
            }
        }
    }
    
    public static es q(final int n, final int n2, final int n3, final String s) {
        final es es;
        (es = new es(4202576, 1)).q(0, 0, n);
        es.q(0, 1, n2);
        es.q(0, 2, n3);
        es.q(0, 0, s);
        return es;
    }
    
    public dH() {
    }
}
