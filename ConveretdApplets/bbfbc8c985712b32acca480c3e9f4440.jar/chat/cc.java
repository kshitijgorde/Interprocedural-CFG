// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Event;

public final class cc extends aC implements aB
{
    cs a;
    ca a;
    
    public final String a(final Object o) {
        return null;
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (!(event.arg instanceof aU)) {
                    break;
                }
                final aU au = (aU)event.arg;
                final aZ az = (aZ)this.a.c.b(au.e);
                if (au.c && au.e != this.a.i && !au.e) {
                    this.a.a(au, az);
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void dispose() {
        super.dispose();
    }
    
    public cc(final cs a) {
        this.a = a;
        this.a = new aR(a, false);
        this.setBackground(a.a.a);
        if (a.a(59)) {
            this.setTitle(aS.a(465));
        }
        else {
            this.setTitle(aS.a(466));
        }
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final bF bf = new bF();
        this.setLayout(gridBagLayout);
        bf.setLayout(gridBagLayout);
        this.a.setFont(a.a.b());
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final i i = new i(this.a);
        gridBagLayout.setConstraints(i, gridBagConstraints);
        bf.add(i);
        bf.setBackground(a.a.h);
        bf.setForeground(a.a.g);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(bf, gridBagConstraints);
        this.add(bf);
    }
}
