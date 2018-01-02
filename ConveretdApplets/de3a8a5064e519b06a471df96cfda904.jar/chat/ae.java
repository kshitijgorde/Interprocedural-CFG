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

public final class ae extends Panel implements Runnable
{
    private i a;
    private TextField a;
    private TextField b;
    private TextField c;
    private TextField d;
    private bh a;
    private Component a;
    
    private void b() {
        final String c;
        if ((c = this.a.c) != null) {
            this.a.setText(c);
            this.b.requestFocus();
        }
        else {
            this.a.setText("");
            this.a.requestFocus();
        }
        if (this.a.e != null) {
            this.c.setText(this.a.e);
        }
        else {
            this.c.setText("");
        }
        this.b.setText("");
        if (this.a.o == -999) {
            this.d.setText("");
            return;
        }
        this.d.setText(String.valueOf(this.a.o));
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
            new ad(null, ak.a(4), ak.a(20) + ak.a(10), this.a).setVisible(true);
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
            new ad(null, ak.a(4), ak.a(21) + ak.a(10), this.a).setVisible(true);
            this.a();
            return;
        }
        if (trim.length() == 0) {
            this.a.requestFocus();
            this.a.selectAll();
            new ad(null, ak.a(1), ak.a(22) + ak.a(10), this.a).setVisible(true);
            this.a();
            return;
        }
        if (trim.length() > 100) {
            this.a.setText(trim.substring(0, 99));
            this.a.requestFocus();
            this.a.selectAll();
            new ad(null, ak.a(1), ak.a(ak.a(23) + ak.a(10), new String[] { "100" }), this.a).setVisible(true);
            this.a();
            return;
        }
        System.gc();
        ap ap;
        String s;
        if (text2.length() > 0) {
            ap = new ap(text2);
            s = trim;
        }
        else {
            ap = null;
            s = "Guest";
        }
        this.a.e = text;
        this.a.o = int1;
        try {
            this.a.a(trim, s, ap, text, int1);
        }
        catch (Exception ex2) {
            this.a();
        }
    }
    
    public final void repaint(final int n, final int n2, final int n3, final int n4) {
        this.a.setForeground(this.getForeground());
        super.repaint(n, n2, n3, n4);
    }
    
    public ae(final bh bh, final boolean b, final boolean b2) {
        this(bh, b, false, false, b2, null);
    }
    
    private ae(final bh a, final boolean b, final boolean b2, final boolean b3, final boolean b4, final String s) {
        this.a = new i(70, 20);
        this.a = new TextField(12);
        this.b = new TextField(12);
        this.c = new TextField(12);
        this.d = new TextField(10);
        this.a = a;
        final Component a2 = s.a(ak.a(7));
        final aw aw = new aw(ak.a(8), (byte)0);
        this.a = s.a(ak.a(5));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.a.setFont(ay.c);
        this.a.setFont(ay.d);
        this.b.setEchoCharacter('*');
        a2.setFont(ay.d);
        aw.setFont(ay.d);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 17;
        if (b) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(this.a, gridBagConstraints);
            this.add(this.a);
            gridBagConstraints.gridwidth = 0;
            final g g = new g(this.a);
            layout.setConstraints(g, gridBagConstraints);
            this.add(g);
        }
        if (b4) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(aw, gridBagConstraints);
            this.add(aw);
            gridBagConstraints.gridwidth = 0;
            final g g2 = new g(this.d);
            layout.setConstraints(g2, gridBagConstraints);
            this.add(g2);
        }
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 0;
        this.a.a(ak.a(26));
        this.a.d();
        this.a.setForeground(Color.black);
        this.a.setForeground(this.getForeground());
        a2.setForeground(this.getForeground());
        aw.setForeground(this.getForeground());
        final f f = new f(this.a);
        layout.setConstraints(f, gridBagConstraints);
        this.add(f);
        this.b();
        if (!b && !b4) {
            this.c();
        }
    }
}
