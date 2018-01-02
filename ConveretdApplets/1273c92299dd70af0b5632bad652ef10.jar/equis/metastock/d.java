// 
// Decompiled by Procyon v0.5.30
// 

package equis.metastock;

import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Button;
import java.awt.Scrollbar;
import java.awt.Choice;
import java.awt.Panel;

class d extends Panel
{
    private Choice a;
    private static Scrollbar b;
    private static Button c;
    private static Button d;
    private static Button e;
    private MS4Java f;
    
    public void a(final String s) {
        this.a.select(s);
    }
    
    public void a() {
        if (equis.metastock.d.c != null) {
            if (MS4Java.d[0].m != MS4Java.d[0].n - 11) {
                equis.metastock.d.c.enable();
            }
            else {
                equis.metastock.d.c.disable();
            }
        }
        if (equis.metastock.d.d != null) {
            if (MS4Java.d[0].m != 0 || MS4Java.d[0].n != MS4Java.d[0].k.b) {
                equis.metastock.d.d.enable();
                equis.metastock.d.e.enable();
                return;
            }
            equis.metastock.d.d.disable();
            equis.metastock.d.e.disable();
        }
    }
    
    public char b() {
        char char1 = '\0';
        if (this.a.getSelectedItem().length() > 0) {
            char1 = this.a.getSelectedItem().toUpperCase().charAt(0);
        }
        return char1;
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public d(final Panel panel, final MS4Java f) {
        this.f = f;
        this.setBackground(Color.lightGray);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.weightx = 5.0;
        layout.setConstraints(equis.metastock.d.b = new e(this), gridBagConstraints);
        this.add(equis.metastock.d.b);
        gridBagConstraints.weightx = 1.0;
        this.add(equis.metastock.d.c = new Button(MS4Java.bf[22]));
        this.add(equis.metastock.d.d = new Button(MS4Java.bf[23]));
        this.add(equis.metastock.d.e = new Button(MS4Java.bf[24]));
        this.a = new Choice();
        if (MS4Java.h() != null) {
            this.a.addItem(MS4Java.bf[25]);
        }
        this.a.addItem(MS4Java.bf[26]);
        this.a.addItem(MS4Java.bf[27]);
        this.a.addItem(MS4Java.bf[28]);
        this.a.setBackground(Color.white);
        this.a.select(MS4Java.bf[26]);
        if (MS4Java.q()) {
            this.add(this.a);
        }
        this.repaint();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Choice) {
            try {
                this.f.LoadData(null);
            }
            catch (Exception ex) {}
        }
        if (event.target instanceof Button) {
            final String s = (String)o;
            final int m = MS4Java.d[0].m;
            final int n = MS4Java.d[0].n;
            if (s.equals(MS4Java.bf[22])) {
                MS4Java.d[0].m = Math.min(MS4Java.d[0].m + 10, MS4Java.d[0].n - 11);
            }
            else if (s.equals(MS4Java.bf[23])) {
                MS4Java.d[0].m = Math.max(MS4Java.d[0].m - 5, 0);
                MS4Java.d[0].n = Math.min(MS4Java.d[0].n + 5, MS4Java.d[0].k.b);
            }
            else if (s.equals(MS4Java.bf[24])) {
                MS4Java.d[0].m = 0;
                MS4Java.d[0].n = MS4Java.d[0].k.b;
            }
            else {
                MS4Java.a("Error: Unknown button in LowerControlPanel.");
            }
            if (m != MS4Java.d[0].m || n != MS4Java.d[0].n) {
                this.a();
                MS4Java.e.repaint();
            }
        }
        return true;
    }
    
    public void c() {
        final int a = MS4Java.d[0].k.a;
        final int b = MS4Java.d[0].k.b;
        final int n = MS4Java.d[0].n - MS4Java.d[0].m;
        equis.metastock.d.b.setValues(MS4Java.d[0].n - n, n, a, b);
    }
    
    static {
        equis.metastock.d.b = null;
        equis.metastock.d.c = null;
        equis.metastock.d.d = null;
        equis.metastock.d.e = null;
    }
}
