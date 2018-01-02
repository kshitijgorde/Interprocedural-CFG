// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;

final class bS extends cV
{
    cc q;
    
    public final void w() {
    }
    
    public final void q() {
    }
    
    public bS(final aS as, final aS as2) {
        super(be.w("Manage"));
        (this.q = new cc()).resize(630, 300);
        final aX ax = new aX(be.w("Name"), "name");
        final aX ax2 = new aX(be.w("Start In"));
        final aX ax3 = new aX(be.w("Ends In"));
        final aX ax4 = new aX(be.w("Monthes"));
        final aX ax5 = new aX("ID");
        final aX ax6 = new aX(be.w("Payment $"));
        final aX ax7 = new aX(be.w("Comment"));
        ax.w(100);
        ax2.w(90);
        ax3.w(90);
        ax4.w(43);
        ax5.w(43);
        ax6.w(43);
        ax.q(true);
        ax2.q(true);
        ax4.q(true);
        ax5.q(true);
        ax3.q(true);
        ax.w(true);
        ax2.w(true);
        ax4.w(true);
        ax5.w(true);
        ax3.w(true);
        ax6.w(true);
        ax7.w(true);
        this.q.q(true);
        this.q.q(ax5);
        this.q.w(ax5);
        this.q.w(ax);
        this.q.w(ax2);
        this.q.w(ax3);
        this.q.w(ax4);
        this.q.w(ax6);
        this.q.w(ax7);
        this.q(new bZ(this.q), 1, 1.0f, 1.0f);
    }
}
