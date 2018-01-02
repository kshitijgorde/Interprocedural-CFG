// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.awt.Event;

public class ct extends aO implements aB
{
    protected t h;
    protected ay c;
    
    public String a(final Object o) {
        return null;
    }
    
    public void a(final Z z) {
        this.c.a(z);
    }
    
    public void a(final byte[] array) {
        final boolean d = this.h.d(59);
        if (d && array[3] == 0) {
            return;
        }
        if (!d && array[3] == 1) {
            return;
        }
        try {
            for (int i = this.c.b() - 1; i >= 0; --i) {
                if (this.c.a(i).a[0] == array[0] && this.c.a(i).a[1] == array[1]) {
                    if (array[2] == 2) {
                        this.c.b(i);
                    }
                    else {
                        this.c.a(i).ad = (array[2] == 1);
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.arg instanceof Z) {
                    final Z z = (Z)event.arg;
                    final ab ab = (ab)this.h.c.b(z.Z);
                    if (z.w && z.Z != this.h.h() && !z.ad) {
                        this.h.a(z, ab);
                    }
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void a() {
        this.c.b();
    }
    
    public void dispose() {
        super.dispose();
    }
    
    public ct(final Frame frame, final t h) {
        this.h = h;
        this.c = new W(h, false);
        this.setBackground(h.a.a);
        if (h.d(59)) {
            this.setTitle(ao.e("Guest Speaker Queue"));
        }
        else {
            this.setTitle(ao.e("Moderated Queue"));
        }
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final cA ca = new cA();
        this.setLayout(gridBagLayout);
        ca.setLayout(gridBagLayout);
        (this.c = this.c).setFont(h.a.b());
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final aR ar = new aR(this.c);
        gridBagLayout.setConstraints(ar, gridBagConstraints);
        ca.add(ar);
        ca.setBackground(h.a.g);
        ca.setForeground(h.a.f);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(ca, gridBagConstraints);
        this.add(ca);
    }
}
