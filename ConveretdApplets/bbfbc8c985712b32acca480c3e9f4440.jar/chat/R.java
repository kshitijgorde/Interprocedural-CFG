// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Cursor;
import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.Event;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.TextField;

public final class R extends T
{
    private TextField a;
    private TextField b;
    private TextField c;
    private cr e;
    private cr f;
    private cg b;
    private Checkbox a;
    private TextField d;
    private Checkbox b;
    private TextField e;
    private Choice a;
    private Checkbox c;
    private Checkbox d;
    private Choice b;
    private boolean c;
    private cr g;
    
    public final U a() {
        final cf cf;
        (cf = new cf(-999, "")).c = true;
        cf.i = super.a.b();
        return cf;
    }
    
    public final boolean a(final cg cg, final Event event) {
        return false;
    }
    
    public final void a(final U u) {
        final cf cf = (cf)u;
        this.a.setText(cf.i + "");
        this.a.enable(cf.c);
        this.b.setText(cf.d);
        this.d.setText(cf.a);
        this.e.setText(cf.b);
        this.b.enable(cf.c);
        this.a.setEnabled(((bH)super.a).e);
        this.a.setState(cf.a(61));
        if (u.i != 0) {
            this.d.setState(cf.a(20));
            this.c.setState(cf.a(21));
        }
        this.d.setVisible(u.i != 0);
        this.c.setVisible(u.i != 0);
        this.b.setState(cf.a(50));
        this.c.setText(new Integer(cf.a).toString());
        this.c = false;
    }
    
    public final void d(U u) {
        final cf cf;
        if ((cf = (cf)u).f != 0 && (u = (U)super.a.i.b(cf.f)) != null) {
            this.a.select(u.d);
        }
        else {
            this.a.select(0);
        }
        this.c = true;
    }
    
    public final boolean a(final U u) {
        final p p = (p)u;
        if (!this.c) {
            final String text = this.b.getText();
            final int a = super.a.a((a)u);
            int n = 0;
            if (a != -1) {
                n = 1;
            }
            final int int1 = Integer.parseInt(this.a.getText());
            int i = 0;
            while (true) {
                while (i < this.a()) {
                    final U a2 = this.a(i);
                    if (u != a2 && a2.i == int1) {
                        final boolean b = true;
                        if (b) {
                            this.a.requestFocus();
                            new bD(this.a(), aS.a(1), aS.a(285), super.a).setVisible(true);
                            return false;
                        }
                        if (u.a && Integer.parseInt(this.a.getText()) < 1000) {
                            this.a.requestFocus();
                            new bD(this.a(), aS.a(1), aS.a(286), super.a).setVisible(true);
                            return false;
                        }
                        if (text.length() == 0) {
                            this.b.requestFocus();
                            new bD(this.a(), aS.a(1), aS.a(287) + aS.a(10), super.a).setVisible(true);
                            return false;
                        }
                        if (this.e.getText().length() == 0) {
                            this.e.requestFocus();
                            new bD(this.a(), aS.a(1), aS.a(659) + aS.a(10), super.a).setVisible(true);
                            return false;
                        }
                        if (this.a(text, u)) {
                            this.b.requestFocus();
                            new bD(this.a(), aS.a(1), aS.a(288), super.a).setVisible(true);
                            return false;
                        }
                        final int c = this.c();
                        final int n2;
                        if ((n2 = ((bH)super.a).n) != 1023 && c - n >= n2) {
                            new bD(a(this), aS.a(55), new String[] { bm.a(aS.a(289), new String[] { String.valueOf(n2) }) }, super.a).setVisible(true);
                            return false;
                        }
                        for (int j = 0; j < text.length(); ++j) {
                            final char char1;
                            if (Character.isLetterOrDigit(char1 = text.charAt(j)) && (char1 < 'a' || char1 > 'z') && (char1 < 'A' || char1 > 'Z') && (char1 < '0' || char1 > '9') && char1 != '_') {
                                this.b.requestFocus();
                                new bD(this.a(), aS.a(1), aS.a(290), super.a).setVisible(true);
                                return false;
                            }
                        }
                        final String trim = this.c.getText().trim();
                        int int2;
                        try {
                            int2 = Integer.parseInt(trim);
                        }
                        catch (NumberFormatException ex) {
                            this.c.requestFocus();
                            this.c.selectAll();
                            new bD(this.a(), aS.a(1), aS.a(291), super.a).setVisible(true);
                            return false;
                        }
                        p.d = text;
                        p.a(61, this.a.getState());
                        p.a(50, this.b.getState());
                        p.a(20, this.d.getState());
                        p.a(21, this.c.getState());
                        p.a = int2;
                        p.a = this.d.getText();
                        p.b = this.e.getText();
                        p.i = Integer.parseInt(this.a.getText());
                        return true;
                    }
                    else {
                        ++i;
                    }
                }
                final boolean b = false;
                continue;
            }
        }
        if (this.a.getSelectedIndex() == 0) {
            p.f = 0;
            p.g = 0;
        }
        else {
            p.f = ((p)super.a.i.a(this.a.getSelectedIndex())).i;
        }
        return true;
    }
    
    public final void c() {
        final int c = this.c();
        final int n;
        if ((n = ((bH)super.a).n) != 1023 && c > n) {
            new bD(a(this), aS.a(55), new String[] { bm.a(aS.a(289), new String[] { String.valueOf(n) }) }, super.a).setVisible(true);
            this.handleEvent(new Event(super.b, 1001, null));
        }
    }
    
    private boolean a(final String s, final U u) {
        for (int i = 0; i < this.a(); ++i) {
            final U a = this.a(i);
            if (u != a && a.d.equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
    
    public final void a(final cg cg) {
        if (cg.getTitle().startsWith(aS.a(660))) {
            cg.a(aS.a(661), this.a, 0);
            return;
        }
        cg.a(new A(aS.a(292)), 2, 1.0f, 0.0f);
        cg.a(aS.a(74), this.b, 0);
        cg.a(aS.a(294), this.a, 0);
        cg.a(aS.a(296), new Component[] { this.c, new bi(aS.a(683), (byte)0) });
        cg.a(aS.a("FTP Password:"), this.e, 0);
        cg.a(aS.a(293), this.d, 0);
        cg.a(aS.a(295), this.a, 0);
        cg.a(aS.a(298), this.b, 0);
        cg.a(aS.a("Stop FTP"), this.c, 0);
        cg.a(aS.a("Stop Site"), this.d, 0);
    }
    
    public final void a(final U u, final cg cg) {
        if (cg.equals(this.b)) {
            this.b.dispose();
            this.b = null;
            return;
        }
        super.a(u, cg);
    }
    
    public final void b() {
        final int o = ((bH)super.a).o;
        int n = 0;
        final r r;
        (r = new r(67337, this.b())).e = -1;
        r.d = -1;
        for (int i = 0; i < this.a(); ++i) {
            final p p;
            if ((p = (p)this.a(i)).j != 0) {
                r.a(n, p.b);
                r.a(n, 0, p.i);
                r.a(n, 1, p.a);
                r.a(n, 2, p.b);
                r.a(n, 1, p.a);
                r.a(n, 2, o);
                r.a(n, 4, p.f);
                if (!p.a(63)) {
                    r.a(n, 0, p.d);
                }
                ++n;
            }
        }
        super.a.o(r);
    }
    
    public final void c(final U u) {
        if (u != null || super.a.b() == -1) {
            this.b.removeAll();
            this.b.add("--- Select Backup ---");
        }
        if (u == null || u.i == 0 || super.a.b() == -1) {
            this.e.b();
            this.g.b();
            this.f.b();
        }
        else if (u.i != 0) {
            this.e.a();
            this.f.a();
            this.g.b();
            if (((p)u).c != null) {
                StringTokenizer stringTokenizer;
                for (int countTokens = (stringTokenizer = new StringTokenizer(((p)u).c, "&")).countTokens(), i = 0; i < countTokens; ++i) {
                    this.b.add(stringTokenizer.nextToken());
                }
                if (this.b.getSelectedIndex() != 0) {
                    this.g.a();
                }
            }
            else {
                this.g.b();
            }
        }
        super.a.a();
        super.c(u);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.b) {
                    if (this.b.getSelectedIndex() == 0) {
                        this.g.b();
                    }
                    else {
                        this.g.a();
                    }
                    return super.handleEvent(event);
                }
                final U u = (U)super.a.a();
                if (event.target == this.f) {
                    if (this.b == null && u != null) {
                        this.setCursor(Cursor.getPredefinedCursor(3));
                        (this.b = new cg(aS.a(660) + " " + u.d, this, u, super.b, super.a)).setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                        super.a.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.e) {
                    final r r;
                    (r = new r(33622276, 1)).a(0, 0, u.i);
                    super.a.o(r);
                }
                if (event.target == this.g) {
                    final U u2 = u;
                    final bD bd;
                    (bd = new bD(this.a(), aS.a(1), new String[] { aS.a(2), aS.a(3) }, new String[] { "Are you sure you want to restore the backup file " + this.b.getSelectedItem() + " for site " + u2.d + " ?" }, null, super.a)).setVisible(true);
                    if (bd.a.equalsIgnoreCase(aS.a(2))) {
                        final r r2;
                        (r2 = new r(67344, 1)).d = -1;
                        r2.e = -1;
                        r2.a(0, 0, u2.i);
                        r2.a(0, 0, this.b.getSelectedItem());
                        super.a.o(r2);
                    }
                }
                return super.handleEvent(event);
            }
            case 701: {
                this.c((U)event.arg);
                return true;
            }
            case 702: {
                this.c(null);
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    private final int c() {
        int n = 0;
        for (int i = 0; i < this.a(); ++i) {
            if (!((p)this.a(i)).a(63)) {
                ++n;
            }
        }
        return n;
    }
    
    public final void a() {
        this.a.removeAll();
        this.a.addItem(aS.a(503));
        super.a();
        try {
            for (int i = 0; i < super.a.i.a(); ++i) {
                final cf cf;
                (cf = new cf((p)super.a.i.a(i))).c = false;
                cf.a = (cf.i != 0);
                this.b(cf);
                super.a.a(cf, cf.i != 0);
                if (cf.i != 0) {
                    this.a.addItem(cf.d);
                }
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    private static final Frame a(Component parent) {
        while (!(parent instanceof Frame)) {
            parent = parent.getParent();
        }
        return (Frame)parent;
    }
    
    public final void b(final U u) {
        if (this.c) {
            if (this.b != null) {
                this.b.dispose();
                this.b = null;
            }
            this.c(u);
            this.c = false;
            return;
        }
        super.a.a(u, u.i);
        final int a;
        if ((a = super.a.a((a)u)) == -1) {
            super.a.c(u);
            if (u.a(62)) {
                super.a.a(u, true);
            }
            if (super.a != null) {
                final U a2;
                if ((a2 = this.a()) != null) {
                    super.a.a(a2);
                }
                else {
                    super.a.dispose();
                    super.a = null;
                }
            }
        }
        else {
            super.a.a(u, a);
            if (super.a != null) {
                super.a.dispose();
                super.a = null;
            }
        }
        this.c(u);
    }
    
    public R(final cx cx) {
        super(cx, aS.a(107), aS.a(108));
        this.c = false;
        this.a = new TextField(20);
        this.b = new TextField(20);
        this.c = new TextField(5);
        this.d = new TextField(20);
        this.e = new TextField(20);
        this.e = new cr(80, 20);
        this.a = new Checkbox();
        this.b = new Checkbox();
        this.c = new Checkbox();
        this.d = new Checkbox();
        this.g = new cr(80, 20);
        this.b = new Choice();
        (this.a = new Choice()).addItem(aS.a(503));
        this.b.setForeground(Color.black);
        this.b.add("--- Select Backup ---");
        this.e.a(aS.a(284));
        int stringWidth = this.e.getFontMetrics(this.e.getFont()).stringWidth(aS.a(284));
        stringWidth += 20;
        this.e.resize(stringWidth, 20);
        this.g.a(aS.a("Restore"));
        int stringWidth2 = this.g.getFontMetrics(this.g.getFont()).stringWidth(aS.a("Restore"));
        stringWidth2 += 20;
        this.g.resize(stringWidth2, 20);
        final Component[] array = { this.e, new bi("Backup", (byte)0), this.b, this.g };
        this.f = new cr(50, 20);
        final GridBagLayout gridBagLayout;
        final GridBagConstraints constraints = (gridBagLayout = (GridBagLayout)super.a.getParent().getLayout()).getConstraints(super.a);
        gridBagLayout.getConstraints(super.a.getParent()).gridwidth = 0;
        gridBagLayout.setConstraints(super.a, gridBagLayout.getConstraints(super.b));
        this.d();
        constraints.insets.top = 15;
        gridBagLayout.setConstraints(this.f, constraints);
        this.f.a(aS.a(662));
        super.a.getParent().add(this.f);
        this.a(null, array);
        final cC cc = new cC("Site", "site");
        final cC cc2;
        (cc2 = new cC("FTP", "ftp")).a(40);
        cc.a(true);
        cc.c = true;
        cc2.c = true;
        this.a(cc2);
        this.a(cc);
        cc.a(45);
        cc.b = false;
        cc.d = true;
        cc2.d = true;
        cc.f = true;
        cc2.f = true;
        this.e.b();
        this.g.b();
        this.f.b();
    }
}
