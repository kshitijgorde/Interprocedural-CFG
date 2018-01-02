// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.util.Enumeration;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.Event;
import java.awt.TextArea;
import java.awt.Checkbox;

public final class ah extends bC
{
    private cp a;
    private av a;
    private cr a;
    private cr b;
    private static Checkbox a;
    private TextArea a;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.a.c();
                    return true;
                }
                break;
            }
            case 201: {
                this.a.c();
                return true;
            }
            case 1001: {
                if (event.target == this.a) {
                    this.dispose();
                    return true;
                }
                if (event.target == this.b) {
                    try {
                        this.a.append(" " + this.a.a().d);
                        if (ah.a.getState()) {
                            this.dispose();
                        }
                    }
                    catch (Exception ex) {}
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public ah(final Frame frame, final cs cs, final TextArea a) {
        super(frame, aS.a(531), true);
        super.a = new cr(80, 20);
        this.a = new cr(80, 20);
        this.b = new cr(80, 20);
        if (ah.a == null) {
            (ah.a = new Checkbox(aS.a(529))).setForeground(cs.a.g);
            ah.a.setState(true);
        }
        this.setBackground(cs.a.a);
        final bF bf = new bF();
        final k k = new k();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final bi bi = new bi(aS.a(531), (byte)0);
        this.a = a;
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        bf.setLayout(gridBagLayout);
        bf.setBackground(cs.a.h);
        bf.setForeground(cs.a.g);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        bi.setFont(bk.a);
        gridBagLayout.setConstraints(bi, gridBagConstraints);
        bf.add(bi);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(k, gridBagConstraints);
        bf.add(k);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        this.a = new av(1000);
        (this.a = new cp(cs)).setSize(350, 200);
        this.a.a.a();
        this.a();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.a, gridBagConstraints);
        bf.add(this.a);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(bf, gridBagConstraints);
        this.add(bf);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.gridwidth = -2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        gridBagLayout.setConstraints(ah.a, gridBagConstraints);
        this.add(ah.a);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.a.a(aS.a(95));
        this.a.d();
        gridBagLayout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.b.a(aS.a(530));
        this.b.d();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        this.pack();
    }
    
    private void a() {
        this.a.a();
        final Enumeration a = ae.a();
        while (a.hasMoreElements()) {
            this.a.a(a.nextElement());
        }
        for (int i = 0; i < this.a.a(); ++i) {
            this.a.a.a((am)this.a.a(i));
        }
    }
}
