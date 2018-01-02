// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Color;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Component;
import java.awt.TextField;
import java.awt.Panel;

public final class aD extends Panel implements Runnable
{
    private cr a;
    private TextField a;
    private TextField b;
    private TextField c;
    private TextField d;
    private Component a;
    private Component b;
    private cs a;
    private String a;
    private boolean a;
    private Component c;
    
    private void b() {
        final String d;
        if ((d = this.a.d) != null) {
            this.a.setText(d);
            this.b.requestFocus();
        }
        else {
            this.a.setText("");
            this.a.requestFocus();
        }
        if (this.a.m != null) {
            this.c.setText(this.a.m);
        }
        else {
            this.c.setText("");
        }
        this.b.setText("");
        if (this.a.v == -999) {
            this.d.setText("");
            return;
        }
        this.d.setText(String.valueOf(this.a.v));
    }
    
    private void c() {
        new Thread(this).start();
    }
    
    public final boolean action(final Event event, final Object o) {
        if (event.target instanceof TextField) {
            this.a.c();
            return true;
        }
        if (event.target == this.a) {
            this.c();
            return true;
        }
        return super.action(event, o);
    }
    
    public final void a() {
        this.a.a();
        System.gc();
    }
    
    public final void layout() {
        super.layout();
        this.b();
    }
    
    public final void run() {
        this.a.b();
        final String trim = this.a.getText().trim();
        final String text = this.c.getText();
        final String text2 = this.b.getText();
        if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
            this.a.requestFocus();
            this.a.selectAll();
            new bD(null, aS.a(4), aS.a(20) + aS.a(10), this.a).setVisible(true);
            this.a();
            return;
        }
        int int1;
        try {
            int1 = Integer.parseInt(this.d.getText());
        }
        catch (NumberFormatException ex) {
            this.d.requestFocus();
            this.d.selectAll();
            new bD(null, aS.a(4), aS.a(21) + aS.a(10), this.a).setVisible(true);
            this.a();
            return;
        }
        if (trim.length() == 0) {
            this.a.requestFocus();
            this.a.selectAll();
            new bD(null, aS.a(1), aS.a(22) + aS.a(10), this.a).setVisible(true);
            this.a();
            return;
        }
        if (trim.length() > 100) {
            this.a.setText(trim.substring(0, 99));
            this.a.requestFocus();
            this.a.selectAll();
            new bD(null, aS.a(1), bm.a(aS.a(23) + aS.a(10), new String[] { "100" }), this.a).setVisible(true);
            this.a();
            return;
        }
        if (this.a && text2.length() == 0) {
            this.b.requestFocus();
            this.b.selectAll();
            new bD(null, aS.a(1), aS.a(24) + aS.a(10), this.a).setVisible(true);
            this.a();
            return;
        }
        if (this.a && text2.length() < 3) {
            this.b.requestFocus();
            this.b.selectAll();
            new bD(null, aS.a(1), aS.a(25) + aS.a(10), this.a).setVisible(true);
            this.a();
            return;
        }
        System.gc();
        aV av;
        String s;
        if (this.a != null && !this.a) {
            av = new aV(this.a);
            s = trim;
        }
        else if (text2.length() > 0) {
            av = new aV(text2);
            s = trim;
        }
        else {
            av = null;
            s = "Guest";
        }
        this.a.m = text;
        this.a.v = int1;
        this.a.b(23, n.a(this.a));
        this.a.b(18, n.a(this.b));
        try {
            this.a.a(trim, s, av, text, int1);
        }
        catch (Exception ex2) {
            this.a();
        }
    }
    
    public final void repaint(final int n, final int n2, final int n3, final int n4) {
        this.c.setForeground(this.getForeground());
        this.a.setForeground(this.getForeground());
        this.b.setForeground(this.getForeground());
        super.repaint(n, n2, n3, n4);
    }
    
    public aD(final cs a, final boolean b, final boolean a2, final boolean b2, final boolean b3, final String a3) {
        this.a = new cr(70, 20);
        this.a = new TextField(12);
        this.b = new TextField(12);
        this.c = new TextField(12);
        this.d = new TextField(10);
        this.a = n.b(aS.a(9));
        this.b = n.b(aS.a(591));
        this.a = a;
        this.a = a2;
        this.a = a3;
        final Component a4 = n.a(aS.a(6));
        final Component a5 = n.a(aS.a(7));
        final bi bi = new bi(aS.a(8), (byte)0);
        this.c = n.a(aS.a(5));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.a.setFont(bk.c);
        this.c.setFont(bk.d);
        a4.setFont(bk.d);
        this.b.setEchoCharacter('*');
        a5.setFont(bk.d);
        bi.setFont(bk.d);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 17;
        if (b) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(this.c, gridBagConstraints);
            this.add(this.c);
            gridBagConstraints.gridwidth = 0;
            final i i = new i(this.a);
            layout.setConstraints(i, gridBagConstraints);
            this.add(i);
        }
        if (a2) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(a4, gridBagConstraints);
            this.add(a4);
            gridBagConstraints.gridwidth = 0;
            final i j = new i(this.b);
            layout.setConstraints(j, gridBagConstraints);
            this.add(j);
        }
        if (b3) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(bi, gridBagConstraints);
            this.add(bi);
            gridBagConstraints.gridwidth = 0;
            final i k = new i(this.d);
            layout.setConstraints(k, gridBagConstraints);
            this.add(k);
        }
        if (a2) {
            gridBagConstraints.gridwidth = 1;
            layout.setConstraints(this.a, gridBagConstraints);
            n.a(this.a, false);
            this.add(this.a);
        }
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.b, gridBagConstraints);
        n.a(this.b, false);
        this.add(this.b);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 0;
        this.a.a(aS.a(26));
        this.a.d();
        this.a.setForeground(Color.black);
        this.a.setForeground(this.getForeground());
        this.b.setForeground(this.getForeground());
        this.c.setForeground(this.getForeground());
        a4.setForeground(this.getForeground());
        a5.setForeground(this.getForeground());
        bi.setForeground(this.getForeground());
        final g g = new g(this.a);
        layout.setConstraints(g, gridBagConstraints);
        this.add(g);
        this.b();
        if (!b && !a2 && !b3) {
            this.c();
        }
    }
}
