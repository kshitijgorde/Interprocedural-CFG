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

public final class aF extends C
{
    private i a;
    private i b;
    private TextField a;
    private TextField b;
    private TextField c;
    private TextField d;
    private Checkbox a;
    private Checkbox b;
    private bh a;
    private o a;
    private o b;
    private o c;
    private o d;
    private bg a;
    private bg b;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == aZ.a) {
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
                        new ad(null, ak.a(1), ak.a(555) + ak.a(10), this.a).setVisible(true);
                        return true;
                    }
                    if (trim2.length() == 0) {
                        trim2 = null;
                    }
                    boolean b = false;
                    if (this.b.isEnabled() && this.b.getState()) {
                        b = true;
                    }
                    if (this.a.getState()) {
                        final String text = this.a.getText();
                        final String text2 = this.b.getText();
                        if (text.length() == 0) {
                            new ad(null, ak.a(1), ak.a(496) + ak.a(10), this.a).setVisible(true);
                            return true;
                        }
                        if (!text.equals(text2)) {
                            new ad(null, ak.a(1), ak.a(497) + ak.a(10), this.a).setVisible(true);
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
    
    public aF(final Frame frame, final bh a) {
        super(frame, ak.a(498), true);
        this.a = new i(80, 20);
        this.b = new i(80, 20);
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
        this.a = new bg();
        this.b = new bg();
        this.a = new o(this.a, this.a);
        this.b = new o(this.a, this.b);
        this.c = new o(this.a, this.a, this.b);
        this.d = new o(this.a);
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
        final aw aw;
        (aw = new aw(ak.a(27), (byte)0)).setFont(ay.d);
        gridBagConstraints.gridwidth = 1;
        gridBagLayout.setConstraints(aw, gridBagConstraints);
        panel.add(aw);
        gridBagConstraints.gridwidth = (this.a.a(3) ? -1 : 0);
        gridBagConstraints.anchor = 17;
        final g g = new g(this.c);
        gridBagLayout.setConstraints(g, gridBagConstraints);
        panel.add(g);
        if (this.a.a(3)) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            panel.add(this.a);
        }
        this.d = new TextField(25);
        final aw aw2;
        (aw2 = new aw(ak.a(499), (byte)0)).setFont(ay.d);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 1;
        gridBagLayout.setConstraints(aw2, gridBagConstraints);
        panel.add(aw2);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = (this.a.a(3) ? -1 : 0);
        final g g2 = new g(this.d);
        gridBagLayout.setConstraints(g2, gridBagConstraints);
        panel.add(g2);
        if (this.a.a(3)) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            panel.add(this.b);
        }
        this.a = new TextField(15);
        final aw aw3;
        (aw3 = new aw(ak.a(6), (byte)0)).setFont(ay.d);
        this.a.setEchoCharacter('*');
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 1;
        gridBagLayout.setConstraints(aw3, gridBagConstraints);
        panel.add(aw3);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final g g3 = new g(this.a);
        gridBagLayout.setConstraints(g3, gridBagConstraints);
        panel.add(g3);
        this.b = new TextField(15);
        final aw aw4 = new aw(ak.a(455), (byte)0);
        this.b.setEchoCharacter('*');
        aw4.setFont(ay.d);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 1;
        gridBagLayout.setConstraints(aw4, gridBagConstraints);
        panel.add(aw4);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final g g4 = new g(this.b);
        gridBagLayout.setConstraints(g4, gridBagConstraints);
        panel.add(g4);
        if (this.a.a(3)) {
            this.a.setBackground(j.c);
            this.b.setBackground(j.c);
            final aw aw5;
            (aw5 = new aw(ak.a(573), (byte)0)).setFont(ay.d);
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = 1;
            gridBagLayout.setConstraints(aw5, gridBagConstraints);
            panel.add(aw5);
            gridBagConstraints.anchor = 17;
            gridBagLayout.setConstraints(this.c, gridBagConstraints);
            panel.add(this.c);
            final aw aw6;
            (aw6 = new aw(ak.a(79), (byte)0)).setFont(ay.d);
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = 1;
            gridBagLayout.setConstraints(aw6, gridBagConstraints);
            panel.add(aw6);
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.d, gridBagConstraints);
            panel.add(this.d);
            final aM am;
            (am = new aM(2, 2, 2, 2, (byte)0)).add("North", this.a);
            am.add("Center", this.b);
            gridBagConstraints.fill = 2;
            gridBagLayout.setConstraints(am, gridBagConstraints);
            panel.add(am);
            gridBagConstraints.fill = 0;
        }
        (this.a = new Checkbox(ak.a(500))).setState(true);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.a, gridBagConstraints);
        panel.add(this.a);
        (this.b = new Checkbox(ak.a(501))).setState(true);
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
        this.b.a(ak.a(3));
        this.b.d();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.a.a(ak.a(2));
        this.a.d();
        final f f = new f(this.a);
        gridBagLayout.setConstraints(f, gridBagConstraints);
        this.add(f);
        this.pack();
        this.c.requestFocus();
    }
}
