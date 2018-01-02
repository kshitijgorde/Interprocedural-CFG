// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Panel;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Choice;
import java.awt.TextArea;

public final class O extends bC
{
    private cr a;
    private cr b;
    private TextArea a;
    private Choice a;
    private cx a;
    private av a;
    private int a;
    private Choice b;
    private v a;
    private v b;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == ce.a) {
                    this.a.c();
                    return true;
                }
                if (event.key == 27) {
                    this.b.c();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    final int selectedIndex = this.a.getSelectedIndex();
                    final r r;
                    (r = new r(66307, 1)).e = -1;
                    if (this.a == 1) {
                        r.a(0, 0, -999);
                        if (selectedIndex == 0) {
                            r.d = -1;
                        }
                        else {
                            r.d = ((U)this.a.a(selectedIndex - 1)).i;
                        }
                    }
                    else {
                        r.d = -1;
                        if (selectedIndex == 0) {
                            r.a(0, 0, -1);
                        }
                        else {
                            r.a(0, 0, ((U)this.a.a(selectedIndex - 1)).i);
                        }
                    }
                    r.a(0, 0, aS.a(564 + this.b.getSelectedIndex()));
                    r.a(0, 1, this.a.getText());
                    r.a(0, 1, this.a.d ? 0 : (this.a.getBackground().getRGB() & 0xFFFFFF));
                    r.a(0, 2, this.b.d ? 0 : (this.b.getBackground().getRGB() & 0xFFFFFF));
                    r.a(0, 4, !this.a.d);
                    r.a(0, 5, !this.b.d);
                    if (this.b.getSelectedIndex() != 0) {
                        r.a(0, this.b.getSelectedIndex() + 6, true);
                    }
                    this.a.o(r);
                    this.dispose();
                    return true;
                }
                if (event.target == this.b) {
                    this.dispose();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public O(final Frame frame, final cx a, final int n, int i) {
        super(frame, aS.a(415), true);
        this.a = new cr(80, 20);
        this.b = new cr(80, 20);
        this.a = new TextArea("", 5, 35, 1);
        this.a = new Choice();
        this.a = new v(a, this.a);
        this.b = new v(a, this.a);
        this.a.b = true;
        this.a.a(0);
        this.b.b = true;
        this.b.a(0);
        this.a.c = true;
        this.b.c = true;
        this.a.a((Color)null);
        this.b.a((Color)null);
        this.b.e = true;
        this.a.a = a.a.g;
        this.b.a = a.a.h;
        this.a.setBackground(Color.white);
        this.a.setForeground(Color.black);
        this.setBackground(a.a.a);
        this.a = i;
        final bF bf = new bF();
        final k k = new k();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        final bi bi = new bi(aS.a(416), (byte)0);
        final bi bi2 = new bi(aS.a(415), (byte)0);
        final bi bi3 = new bi(aS.a(556), (byte)0);
        this.a = a;
        (this.b = new Choice()).setBackground(Color.white);
        this.b.setForeground(Color.black);
        if (i == 1) {
            this.a.addItem(aS.a(417));
            this.a = (av)a.d.clone();
        }
        else {
            this.a.addItem(aS.a(418));
            this.a = (av)a.i.clone();
        }
        for (i = 0; i < this.a.a(); ++i) {
            this.a.addItem(((U)this.a.a(i)).d);
        }
        this.a.select(0);
        this.b.addItem(aS.a(419));
        this.b.addItem(aS.a(420));
        this.b.addItem(aS.a(421));
        this.setResizable(true);
        this.a.a(aS.a(13));
        this.a.d();
        this.setLayout(layout);
        bf.setLayout(layout);
        bf.setBackground(a.a.h);
        bf.setForeground(a.a.g);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        bi2.setFont(bk.a);
        layout.setConstraints(bi2, gridBagConstraints);
        bf.add(bi2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(k, gridBagConstraints);
        bf.add(k);
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        bi.setFont(bk.d);
        layout.setConstraints(bi, gridBagConstraints);
        bf.add(bi);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.a, gridBagConstraints);
        bf.add(this.a);
        final Panel panel;
        (panel = new Panel()).setLayout(layout);
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        final i j = new i(this.a);
        this.a.setBackground(a.a.h);
        this.a.setForeground(a.a.g);
        layout.setConstraints(j, gridBagConstraints);
        panel.add(j);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.anchor = 11;
        final bi bi4 = new bi(aS.a(602), (byte)0);
        layout.setConstraints(bi4, gridBagConstraints);
        panel.add(bi4);
        layout.setConstraints(this.a, gridBagConstraints);
        panel.add(this.a);
        final bi bi5 = new bi(aS.a(572), (byte)0);
        layout.setConstraints(bi5, gridBagConstraints);
        panel.add(bi5);
        layout.setConstraints(this.b, gridBagConstraints);
        panel.add(this.b);
        gridBagConstraints.anchor = 11;
        gridBagConstraints.gridheight = -1;
        layout.setConstraints(this.b, gridBagConstraints);
        panel.add(this.b);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(panel, gridBagConstraints);
        bf.add(panel);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridwidth = -1;
        bi3.setFont(bk.d);
        layout.setConstraints(bi3, gridBagConstraints);
        bf.add(bi3);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.b, gridBagConstraints);
        bf.add(this.b);
        layout.setConstraints(bf, gridBagConstraints);
        this.add(bf);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.b.a(aS.a(3));
        this.b.d();
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        final g g = new g(this.a);
        layout.setConstraints(g, gridBagConstraints);
        this.add(g);
        this.setResizable(false);
        this.pack();
        this.a.requestFocus();
    }
}
