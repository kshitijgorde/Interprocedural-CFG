import java.awt.Event;
import java.awt.Label;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.List;
import java.awt.TextField;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class o extends Frame
{
    TextField a;
    List b;
    Checkbox c;
    Checkbox d;
    TextField e;
    Checkbox f;
    Checkbox g;
    Checkbox h;
    TextField i;
    Checkbox j;
    Checkbox k;
    Checkbox l;
    Button m;
    Button n;
    String o;
    String p;
    String q;
    String r;
    bf s;
    String t;
    String u;
    boolean v;
    boolean w;
    
    void a(final bf s) {
        this.s = s;
    }
    
    void a() {
        this.b.clear();
    }
    
    void a(final String s) {
        this.a.setText(s);
        this.o = s;
    }
    
    String b(String trim) {
        trim = trim.trim();
        final int index = trim.indexOf(32);
        if (index < 0) {
            return trim;
        }
        return trim.substring(index + 1);
    }
    
    o() {
        this.a = new TextField(40);
        this.b = new List(10, false);
        this.c = new Checkbox(bm.dp);
        this.d = new Checkbox(bm.dq);
        this.e = new TextField(4);
        this.f = new Checkbox(bm.dr);
        this.g = new Checkbox(bm.ds);
        this.h = new Checkbox(bm.dt);
        this.i = new TextField(8);
        this.j = new Checkbox(bm.du);
        this.k = new Checkbox(bm.dv);
        this.l = new Checkbox(bm.dw);
        this.m = new Button(bm.dx);
        this.n = new Button(bm.dz);
        this.q = "";
        this.r = "";
        this.w = false;
        this.setLayout(new BorderLayout());
        this.setBackground(bn.x);
        this.b.setBackground(bn.x);
        final Panel panel = new Panel();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        panel.setLayout(layout);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.c, gridBagConstraints);
        panel.add(this.c);
        gridBagConstraints.gridwidth = 1;
        layout.setConstraints(this.d, gridBagConstraints);
        panel.add(this.d);
        layout.setConstraints(this.e, gridBagConstraints);
        panel.add(this.e);
        final Label label = new Label(bm.dA);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        layout.setConstraints(this.f, gridBagConstraints);
        panel.add(this.f);
        layout.setConstraints(this.g, gridBagConstraints);
        panel.add(this.g);
        gridBagConstraints.gridwidth = 1;
        layout.setConstraints(this.h, gridBagConstraints);
        panel.add(this.h);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.i, gridBagConstraints);
        panel.add(this.i);
        layout.setConstraints(this.j, gridBagConstraints);
        panel.add(this.j);
        layout.setConstraints(this.k, gridBagConstraints);
        panel.add(this.k);
        layout.setConstraints(this.l, gridBagConstraints);
        panel.add(this.l);
        final Panel panel2 = new Panel();
        panel2.add(this.m);
        panel2.add(this.n);
        panel2.add(new Button(bm.dy));
        this.add(e("\u0012x<qz"), this.a);
        this.add(e("\u001fr qw."), this.b);
        this.add(e("\u000fx;qz"), panel2);
        this.add(e("\u000br=q"), panel);
        this.resize(600, 300);
    }
    
    void c(final String s) {
        this.setTitle(s);
        this.p = s;
    }
    
    void d(String trim) {
        final int index = trim.indexOf(32);
        if (index < 0) {
            this.t = trim;
            return;
        }
        this.t = trim.substring(0, index);
        trim = trim.substring(index + 1).trim();
        if (this.t.indexOf(108) < 0) {
            if (this.t.indexOf(107) >= 0) {
                this.r = trim;
            }
            return;
        }
        final int index2;
        if ((index2 = trim.indexOf(32)) > 0) {
            this.r = trim.substring(index2 + 1).trim();
            this.q = trim.substring(0, index2);
            return;
        }
        this.q = trim;
    }
    
    void b() {
        final String selectedItem = this.b.getSelectedItem();
        if (selectedItem != null) {
            final int index = selectedItem.indexOf(32);
            String substring = null;
            Label_0038: {
                if (index > 0) {
                    substring = selectedItem.substring(0, index);
                    if (!bm.dX) {
                        break Label_0038;
                    }
                }
                substring = selectedItem;
            }
            this.s.a(e("\u0011X\n@2") + this.p + e("|:,%") + substring + "\n");
            this.b.remove(selectedItem);
        }
    }
    
    void a(final boolean v) {
        this.v = v;
    }
    
    void c() {
        this.a.enable();
        this.a();
        this.f.setState(false);
        this.f.enable();
        this.g.setState(false);
        this.g.enable();
        this.c.setState(false);
        this.c.enable();
        this.l.setState(false);
        this.l.enable();
        this.k.setState(false);
        this.k.enable();
        this.j.setState(false);
        this.j.enable();
        this.d.setState(false);
        this.d.enable();
        this.e.setText("");
        this.e.enable();
        this.q = "";
        this.h.setState(false);
        this.h.enable();
        this.i.setText("");
        this.i.enable();
        this.r = "";
        this.b.enable();
        this.n.enable();
        this.show(this.w = false);
    }
    
    void d() {
        if (this.t.indexOf(112) >= 0) {
            this.f.setState(true);
        }
        if (this.t.indexOf(115) >= 0) {
            this.g.setState(true);
        }
        if (this.t.indexOf(105) >= 0) {
            this.c.setState(true);
        }
        if (this.t.indexOf(116) >= 0) {
            this.l.setState(true);
        }
        if (this.t.indexOf(110) >= 0) {
            this.k.setState(true);
        }
        if (this.t.indexOf(109) >= 0) {
            this.j.setState(true);
        }
        if (this.q != "") {
            this.d.setState(true);
            this.e.setText(this.q);
        }
        if (this.r != "") {
            this.h.setState(true);
            this.i.setText(this.r);
        }
        if (!this.v) {
            if (this.l.getState()) {
                this.a.disable();
            }
            this.f.disable();
            this.g.disable();
            this.c.disable();
            this.l.disable();
            this.k.disable();
            this.j.disable();
            this.d.disable();
            this.e.disable();
            this.h.disable();
            this.i.disable();
            this.b.disable();
            this.n.disable();
        }
    }
    
    void a(final char c, final Checkbox checkbox) {
        if (this.t.indexOf(c) >= 0) {
            if (!checkbox.getState()) {
                this.u = this.u + "-" + String.valueOf(c);
            }
        }
        else if (checkbox.getState()) {
            this.u = this.u + "+" + String.valueOf(c);
        }
    }
    
    boolean e() {
        return this.w;
    }
    
    void b(final boolean w) {
        this.w = w;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof TextField || (event.target instanceof Button && o.equals(bm.dx))) {
            if (!this.o.equals(this.a.getText())) {
                this.s.a(e("\bX\u001eLQ|") + this.p + e("|-") + this.a.getText() + "\n");
            }
            if (this.v) {
                this.u = "";
                this.a('p', this.f);
                this.a('s', this.g);
                this.a('i', this.c);
                this.a('t', this.l);
                this.a('n', this.k);
                this.a('m', this.j);
                this.a('l', this.d);
                this.a('k', this.h);
                if (this.u.indexOf(108) >= 0) {
                    this.u = this.u + " " + this.e.getText();
                }
                if (this.u.indexOf(107) >= 0) {
                    this.u = this.u + " " + this.i.getText();
                }
                if (this.u != "") {
                    this.s.a(e("\u0011X\n@2") + this.p + this.u + "\n");
                }
            }
        }
        if ((event.target instanceof Button && o.equals(bm.dz)) || event.target instanceof List) {
            this.b();
        }
        if ((event.target instanceof Button && (o.equals(bm.dx) || o.equals(bm.dy))) || event.target instanceof TextField) {
            this.c();
            return true;
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.c();
            return true;
        }
        super.handleEvent(event);
        return false;
    }
    
    private static String e(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '\\';
                    break;
                }
                case 1: {
                    c2 = '\u0017';
                    break;
                }
                case 2: {
                    c2 = 'N';
                    break;
                }
                case 3: {
                    c2 = '\u0005';
                    break;
                }
                default: {
                    c2 = '\u0012';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
