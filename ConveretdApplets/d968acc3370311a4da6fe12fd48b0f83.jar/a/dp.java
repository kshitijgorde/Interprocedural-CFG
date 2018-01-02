// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;

final class dp extends G
{
    w q;
    
    public final void w() {
    }
    
    public final void q() {
    }
    
    public dp(final dn dn, final dn dn2) {
        final String q = eb.q("Sites");
        dn.q(dn);
        super(q);
        (this.q = new w()).resize(630, 300);
        final y y = new y(eb.q("Name"), "name");
        final y y2 = new y(eb.q("Max"));
        final y y3 = new y(eb.q("Current"));
        final y y4 = new y(eb.q("Hits"));
        final y y5 = new y("ID");
        final y y6 = new y(eb.q("Last Connection"));
        final y y7 = new y(eb.q("Service End Date"));
        y.w(100);
        y2.w(43);
        y3.w(56);
        y4.w(43);
        y5.w(43);
        y6.w(120);
        y.q(true);
        y2.q(true);
        y4.q(true);
        y5.q(true);
        y3.q(true);
        y.w(true);
        y2.w(true);
        y4.w(true);
        y5.w(true);
        y3.w(true);
        y6.w(true);
        y7.w(true);
        this.q.q(true);
        this.q.q(y5);
        this.q.w(y5);
        this.q.w(y);
        this.q.w(y3);
        this.q.w(y2);
        this.q.w(y4);
        this.q.w(y6);
        this.q.w(y7);
        this.q(new t(this.q), 1, 1.0f, 1.0f);
    }
}
