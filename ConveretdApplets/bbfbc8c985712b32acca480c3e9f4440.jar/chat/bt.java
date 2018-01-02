// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Checkbox;
import java.awt.TextField;

public final class bt extends bC
{
    private cr a;
    private cr b;
    private TextField a;
    private TextField b;
    private TextField c;
    private TextField d;
    private Checkbox a;
    private Checkbox b;
    private cs a;
    private v a;
    private v b;
    private v c;
    private v d;
    private ct a;
    private ct b;
    
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
            case 402: {
                if (event.target == this.c) {
                    this.a.a(this.c.getText());
                }
                if (event.target == this.d) {
                    this.b.a(this.d.getText());
                    break;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    final String trim = this.c.getText().trim();
                    String trim2 = this.d.getText().trim();
                    if (trim.length() == 0) {
                        new bD(null, aS.a(1), aS.a(555) + aS.a(10), this.a).setVisible(true);
                        return true;
                    }
                    if (trim2.length() == 0) {
                        trim2 = null;
                    }
                    boolean b = false;
                    if (this.b.getState()) {
                        b = true;
                    }
                    if (this.a.getState()) {
                        final String text = this.a.getText();
                        final String text2 = this.b.getText();
                        if (text.length() == 0) {
                            new bD(null, aS.a(1), aS.a(496) + aS.a(10), this.a).setVisible(true);
                            return true;
                        }
                        if (!text.equals(text2)) {
                            new bD(null, aS.a(1), aS.a(497) + aS.a(10), this.a).setVisible(true);
                            return true;
                        }
                        this.a.a(trim, trim2, text, b, this.a.a, this.b.a, this.c.a, this.d.a);
                        this.dispose();
                    }
                    else {
                        this.a.a(trim, trim2, "", b, this.a.a, this.b.a, this.c.a, this.d.a);
                        this.dispose();
                    }
                }
                else if (event.target == this.b) {
                    this.dispose();
                }
                else if (event.target == this.a) {
                    this.b.setState(true);
                    this.b.enable();
                    if ((this.a.getState() && (!this.a.a(29) || !this.a.a(38))) || (!this.a.getState() && (!this.a.a(27) || !this.a.a(28)))) {
                        this.b.disable();
                    }
                    if ((this.a.getState() && this.a.a(38)) || (!this.a.getState() && this.a.a(28))) {
                        this.b.setState(false);
                    }
                    if (!this.a.getState()) {
                        this.a.disable();
                        this.b.disable();
                    }
                    else {
                        this.a.enable();
                        this.b.enable();
                    }
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public bt(final Frame frame, final cs a) {
        super(frame, aS.a(498), true);
        this.a = new cr(80, 20);
        this.b = new cr(80, 20);
        this.setBackground(a.a.h);
        this.setForeground(a.a.g);
        this.a = a;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        panel.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 13;
        this.c = new TextField(20);
        this.a = new ct();
        this.b = new ct();
        this.a = new v(this.a, this.a);
        this.b = new v(this.a, this.b);
        this.c = new v(this.a, this.a, this.b);
        this.d = new v(this.a);
        this.a.a(Color.black);
        this.b.a(Color.black);
        if (this.a.a(3)) {
            this.a.b = true;
            this.a.a(0);
            this.b.b = true;
            this.b.a(0);
            this.c.b = true;
            this.c.a(0);
            this.d.b = true;
            this.d.a(0);
        }
        final bi bi;
        (bi = new bi(aS.a(27), (byte)0)).setFont(bk.d);
        gridBagConstraints.gridwidth = 1;
        gridBagLayout.setConstraints(bi, gridBagConstraints);
        panel.add(bi);
        gridBagConstraints.gridwidth = (this.a.a(3) ? -1 : 0);
        gridBagConstraints.anchor = 17;
        final i i = new i(this.c);
        gridBagLayout.setConstraints(i, gridBagConstraints);
        panel.add(i);
        if (this.a.a(3)) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            panel.add(this.a);
        }
        this.d = new TextField(25);
        final bi bi2;
        (bi2 = new bi(aS.a(499), (byte)0)).setFont(bk.d);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 1;
        gridBagLayout.setConstraints(bi2, gridBagConstraints);
        panel.add(bi2);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = (this.a.a(3) ? -1 : 0);
        final i j = new i(this.d);
        gridBagLayout.setConstraints(j, gridBagConstraints);
        panel.add(j);
        if (this.a.a(3)) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            panel.add(this.b);
        }
        this.a = new TextField(15);
        final bi bi3;
        (bi3 = new bi(aS.a(6), (byte)0)).setFont(bk.d);
        this.a.setEchoCharacter('*');
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 1;
        gridBagLayout.setConstraints(bi3, gridBagConstraints);
        panel.add(bi3);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final i k = new i(this.a);
        gridBagLayout.setConstraints(k, gridBagConstraints);
        panel.add(k);
        this.b = new TextField(15);
        final bi bi4 = new bi(aS.a(455), (byte)0);
        this.b.setEchoCharacter('*');
        bi4.setFont(bk.d);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 1;
        gridBagLayout.setConstraints(bi4, gridBagConstraints);
        panel.add(bi4);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final i l = new i(this.b);
        gridBagLayout.setConstraints(l, gridBagConstraints);
        panel.add(l);
        if (this.a.a(3)) {
            this.a.setBackground(o.c);
            this.b.setBackground(o.c);
            final bi bi5;
            (bi5 = new bi(aS.a(573), (byte)0)).setFont(bk.d);
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = 1;
            gridBagLayout.setConstraints(bi5, gridBagConstraints);
            panel.add(bi5);
            gridBagConstraints.anchor = 17;
            gridBagLayout.setConstraints(this.c, gridBagConstraints);
            panel.add(this.c);
            final bi bi6;
            (bi6 = new bi(aS.a(79), (byte)0)).setFont(bk.d);
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = 1;
            gridBagLayout.setConstraints(bi6, gridBagConstraints);
            panel.add(bi6);
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.d, gridBagConstraints);
            panel.add(this.d);
            final bF bf;
            (bf = new bF(2, 2, 2, 2, (byte)0)).add("North", this.a);
            bf.add("Center", this.b);
            gridBagConstraints.fill = 2;
            gridBagLayout.setConstraints(bf, gridBagConstraints);
            panel.add(bf);
            gridBagConstraints.fill = 0;
        }
        (this.a = new Checkbox(aS.a(500))).setState(true);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.a, gridBagConstraints);
        panel.add(this.a);
        (this.b = new Checkbox(aS.a(501))).setState(true);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        panel.add(this.b);
        if (!a.a(29) && !a.a(38)) {
            this.a.setState(false);
            this.a.disable();
            this.a.disable();
            this.b.disable();
        }
        if (!a.a(27) && !a.a(28)) {
            this.a.disable();
        }
        if ((this.a.getState() && (!a.a(29) || !a.a(38))) || (!this.a.getState() && (!a.a(27) || !a.a(28)))) {
            this.b.disable();
        }
        if ((this.a.getState() && a.a(38)) || (!this.a.getState() && a.a(28))) {
            this.b.setState(false);
        }
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        this.b.a(aS.a(3));
        this.b.d();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.a.a(aS.a(2));
        this.a.d();
        final g g = new g(this.a);
        gridBagLayout.setConstraints(g, gridBagConstraints);
        this.add(g);
        this.pack();
        this.c.requestFocus();
    }
}
