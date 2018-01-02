// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.a;

import com.bullionvault.chart.e.i;
import java.awt.Color;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import com.bullionvault.chart.c.h;
import java.awt.Container;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import com.bullionvault.chart.c.e;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.bullionvault.chart.resources.Resources;
import com.bullionvault.chart.g.b;
import java.awt.event.WindowListener;
import java.awt.Window;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

public final class q extends JDialog
{
    private JCheckBox b;
    private JTextField c;
    private JButton d;
    private JButton e;
    private JButton f;
    boolean a;
    
    public q(final Frame frame) {
        super(frame, true);
        new Font("Serif", 0, 11);
        this.d = null;
        this.e = null;
        this.f = null;
        this.a = false;
        this.addWindowListener(new v(this, false));
        com.bullionvault.chart.g.b.a.d();
    }
    
    public final void a() {
        this.setTitle(Resources.b("dialog.register.title"));
        this.getContentPane().setLayout(new GridBagLayout());
        final JLabel label = new JLabel(Resources.b("dialog.register.line1"));
        this.getContentPane().add(label);
        com.bullionvault.chart.c.e.a(this.getContentPane(), label, 0, 0, 1, 1, 0, 10, 0.0, 0.0, 0, 0, 0, 0);
        final JPanel panel;
        (panel = new JPanel()).setLayout(new GridBagLayout());
        final JLabel label2 = new JLabel(Resources.b("dialog.register.email"));
        (this.c = new JTextField(com.bullionvault.chart.g.b.a.c(), 30)).addActionListener(new a(this));
        com.bullionvault.chart.c.e.a(panel, label2, 0, 0, 1, 1, 0, 10, 0.0, 0.0, 0, 0, 0, 0);
        com.bullionvault.chart.c.e.a(panel, this.c, 1, 0, 1, 1, 0, 10, 0.0, 0.0, 0, 0, 0, 0);
        com.bullionvault.chart.c.e.a(this.getContentPane(), panel, 0, 2, 1, 1, 0, 10, 0.0, 0.0, 3, 3, 3, 3);
        final JPanel panel2;
        (panel2 = new JPanel()).setLayout(new GridBagLayout());
        com.bullionvault.chart.c.e.a(panel2, this.b = new JCheckBox(), 0, 0, 1, 2, 0, 13, 0.0, 0.0, 0, 0, 0, 0);
        com.bullionvault.chart.c.e.a(panel2, new JLabel(Resources.b("dialog.register.autologin"), 0), 1, 0, 1, 1, 0, 17, 0.0, 0.0, 0, 0, 0, 0);
        com.bullionvault.chart.c.e.a(this.getContentPane(), new JLabel(Resources.b("dialog.register.disclaimer"), 0), 0, 5, 1, 1, 0, 10, 0.0, 0.0, 10, 10, 10, 10);
        final JPanel panel3;
        (panel3 = new JPanel()).setLayout(new GridBagLayout());
        (this.d = new JButton(Resources.b("dialog.register.button.submit"))).addActionListener(new com.bullionvault.chart.a.b(this));
        (this.e = new JButton(Resources.b("dialog.register.button.cancel"))).addActionListener(new c(this));
        com.bullionvault.chart.c.e.a(panel3, this.d, 0, 0, 1, 1, 0, 10, 0.0, 0.0, 5, 5, 5, 5);
        com.bullionvault.chart.c.e.a(panel3, this.e, 1, 0, 1, 1, 0, 10, 0.0, 0.0, 5, 5, 5, 5);
        com.bullionvault.chart.c.e.a(this.getContentPane(), panel3, 0, 6, 1, 1, 0, 10, 0.0, 0.0, 10, 10, 10, 10);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        this.a = false;
    }
    
    private void a(final String s) {
        this.getContentPane().removeAll();
        this.getContentPane().setLayout(new GridBagLayout());
        com.bullionvault.chart.c.e.a(this.getContentPane(), new JLabel(com.bullionvault.chart.g.b.a.c()), 0, 0, 1, 1, 0, 10, 0.0, 0.0, 10, 10, 10, 10);
        final JLabel label;
        (label = new JLabel(s)).setHorizontalAlignment(0);
        com.bullionvault.chart.c.e.a(this.getContentPane(), label, 0, 1, 1, 1, 0, 10, 0.0, 0.0, 0, 0, 0, 0);
        (this.f = new JButton("Close")).addActionListener(new d(this));
        com.bullionvault.chart.c.e.a(this.getContentPane(), this.f, 0, 2, 1, 1, 0, 10, 0.0, 0.0, 10, 10, 10, 10);
        this.pack();
        this.setVisible(true);
    }
    
    final boolean b() {
        h.e("Validating...");
        final String text = this.c.getText();
        final StringTokenizer stringTokenizer = new StringTokenizer(text, "@");
        try {
            final String nextToken = stringTokenizer.nextToken();
            final String nextToken2 = stringTokenizer.nextToken();
            h.e("username=" + nextToken + " --> server=" + nextToken2);
            final StringTokenizer stringTokenizer2;
            if ((stringTokenizer2 = new StringTokenizer(nextToken2, ".")).countTokens() >= 2) {
                this.d();
                return true;
            }
            h.e("Only " + stringTokenizer2.countTokens() + " components to server name.");
            boolean b = true;
            while (stringTokenizer2.hasMoreTokens()) {
                final String nextToken3;
                if ((nextToken3 = stringTokenizer2.nextToken()).length() < 2) {
                    b = false;
                    h.e("Server Address component [" + nextToken3 + "] in [" + nextToken2 + "] is too short.");
                }
            }
            if (b) {
                this.d();
                return true;
            }
        }
        catch (NoSuchElementException ex) {
            h.e("Not enough elements in email address.");
        }
        this.a();
        final q q = this;
        final String s = text;
        this = q;
        final JLabel label;
        (label = new JLabel("\"" + s + "\"" + Resources.b("dialog.register.error.invalid_email"))).setForeground(Color.red);
        com.bullionvault.chart.c.e.a(this.getContentPane(), label, 0, 1, 1, 1, 0, 10, 0.0, 0.0, 3, 3, 3, 3);
        this.validate();
        return false;
    }
    
    private void d() {
        h.e("Registering...");
        final String lowerCase = this.c.getText().toLowerCase();
        try {
            com.bullionvault.chart.g.b.a.a(lowerCase);
        }
        catch (i i) {
            h.c(i + " - Unable to register new user.");
            this = this;
            this.a(Resources.b("dialog.register.response.unable_to_register"));
            return;
        }
        if (com.bullionvault.chart.g.b.a.b() == 3) {
            this = this;
            this.a(Resources.b("dialog.register.response.new_user"));
            return;
        }
        if (com.bullionvault.chart.g.b.a.b() == 2) {
            (this = this).c();
            return;
        }
        if (com.bullionvault.chart.g.b.a.b() == 1) {
            this = this;
            this.a(Resources.b("dialog.register.response.unauthorized_user"));
        }
    }
    
    final void c() {
        this.setVisible(false);
        this.dispose();
    }
    
    public final void dispose() {
        this.a = false;
        super.dispose();
    }
}
