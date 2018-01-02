// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.awt.event.KeyEvent;
import java.awt.event.TextEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import ji.res.z;
import ji.font.j;
import java.awt.Color;
import ji.util.k;
import ji.io.h;
import ji.annotate.dg;
import java.awt.Dimension;
import ji.util.i;
import java.awt.Label;
import java.awt.Component;
import java.awt.Container;
import ji.util.l;
import ji.v1event.af;
import java.awt.event.WindowListener;
import java.awt.Toolkit;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import ji.util.e;
import ji.util.d;
import java.awt.Rectangle;
import ji.font.ct;
import java.awt.Frame;
import java.awt.Button;
import ji.io.p;
import ji.v1base.jiPanel;
import ji.image.cy;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Checkbox;
import java.util.Vector;
import java.awt.Choice;
import java.awt.event.TextListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.Dialog;

class oh extends Dialog implements ActionListener, KeyListener, ItemListener, TextListener
{
    Choice a;
    Vector b;
    Choice c;
    Checkbox d;
    Checkbox e;
    Checkbox f;
    Checkbox g;
    Checkbox h;
    TextField i;
    Font j;
    String k;
    static String[] l;
    static Font[] m;
    static String[] n;
    static String[] o;
    static String[] p;
    static boolean q;
    cy r;
    Font s;
    boolean t;
    boolean u;
    boolean v;
    boolean w;
    String x;
    boolean y;
    jiPanel z;
    jiPanel aa;
    jiPanel ab;
    jiPanel ac;
    jiPanel ad;
    jiPanel ae;
    jiPanel af;
    p ag;
    boolean ah;
    Button ai;
    Button aj;
    static double ak;
    String al;
    Vector am;
    String an;
    Frame ao;
    int ap;
    int aq;
    boolean ar;
    
    public oh(final Frame ao, final String an, final Font s, final ct ct, final Rectangle rectangle, final cy r, final String x) {
        super(ao, ji.util.d.b(1273, an), true);
        this.b = new Vector();
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.k = null;
        this.r = null;
        this.s = null;
        this.t = false;
        this.u = false;
        this.v = false;
        this.w = false;
        this.x = null;
        this.y = false;
        this.z = null;
        this.aa = null;
        this.ab = null;
        this.ac = null;
        this.ad = null;
        this.ae = null;
        this.af = null;
        this.ag = null;
        this.ah = false;
        this.ai = null;
        this.aj = null;
        this.al = "";
        this.am = new Vector(10);
        this.an = null;
        this.ao = null;
        this.ap = 350;
        this.aq = 215;
        this.ar = false;
        this.ao = ao;
        this.an = an;
        this.y = this.b(an);
        this.r = r;
        this.al = "";
        if (r != null) {
            this.aq -= 70;
            this.s = s;
            this.t = ct.g;
            if (ji.util.e.av()) {
                this.u = ct.h;
            }
        }
        this.x = x;
        this.setLayout(new BorderLayout());
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (rectangle != null) {
            this.setLocation(rectangle.x + (rectangle.width - this.ap) / 2, rectangle.y + (rectangle.height - this.aq) / 2);
        }
        else {
            this.setLocation((screenSize.width - this.ap) / 2, (screenSize.height - this.aq) / 2);
        }
        this.addWindowListener(new aae(this));
        this.ac = new jiPanel(an, false);
        (this.ad = new jiPanel(an, false)).setBorderStyle(0);
        (this.ae = new jiPanel(an, false)).setBorderStyle(0);
        this.a = new Choice();
        this.ap = this.a(ao, an, this.ap, this.y, true, r);
        final dg b3 = r.b3();
        int d = 1;
        if (r.r() != null && r.r().c()) {
            d = r.r().d();
        }
        int n;
        if (ji.util.d.bf()) {
            n = (int)Math.round((int)Math.round(b3.c6() * s.getSize() / ji.util.d.a() / d) * 73.0 / b3.bb());
        }
        else {
            n = (int)Math.round(s.getSize() / this.i());
        }
        this.c = new Choice();
        final int n2 = 10;
        final int n3 = 2;
        int n4;
        if (ji.util.d.bf()) {
            n4 = 250 + n3;
        }
        else {
            n4 = (int)Math.round(667.0 / this.i()) + n3;
        }
        final l l = new l(30);
        int n5 = -1;
        int n6 = 0;
        for (int i = n2; i < n4; i += n3) {
            if (i == n) {
                n5 = n6;
            }
            l.a("".concat(String.valueOf(String.valueOf(i))));
            ++n6;
        }
        if (n5 < 0) {
            final String concat = "".concat(String.valueOf(String.valueOf(n)));
            l.a(concat);
            for (int a = l.a(), j = 0; j < a; ++j) {
                final String b4 = l.b();
                if (b4.equals(concat)) {
                    n5 = j;
                }
                this.c.add("".concat(String.valueOf(String.valueOf(b4))));
            }
        }
        else {
            l.c();
            for (int k = n2; k < n4; k += n3) {
                this.c.add("".concat(String.valueOf(String.valueOf(k))));
            }
        }
        if (n5 > 0) {
            this.c.select(n5);
        }
        this.setSize(this.ap, this.aq);
        super.setResizable(false);
        this.addKeyListener(this);
        if (this.a.getItemCount() > 0) {
            this.a.select(this.a(s.getFamily()));
        }
        this.a.addKeyListener(this);
        this.a.addItemListener(this);
        if (this.c != null) {
            this.c.addItemListener(this);
        }
        (this.d = new Checkbox(String.valueOf(String.valueOf(ji.util.d.b(1268, an))).concat("     "))).setState(s.isBold());
        this.d.addItemListener(this);
        (this.e = new Checkbox(String.valueOf(String.valueOf(ji.util.d.b(1269, an))).concat("     "))).setState(s.isItalic());
        (this.f = new Checkbox(String.valueOf(String.valueOf(ji.util.d.b(1270, an))).concat("     "))).addItemListener(this);
        this.f.setState(ct.g);
        if (ji.util.e.av()) {
            (this.g = new Checkbox(ji.util.d.b(1271, an))).addItemListener(this);
            this.g.setState(ct.h);
        }
        this.e.addItemListener(this);
        this.a(this.ad, this.a);
        this.a(this.ad, new Label(String.valueOf(String.valueOf(new StringBuffer(" ").append(ji.util.d.b(1272, an)).append(": ")))));
        if (this.i != null) {
            this.a(this.ad, this.i);
        }
        if (this.c != null) {
            this.a(this.ad, this.c);
        }
        this.a(this.ae, this.d);
        this.a(this.ae, this.e);
        this.a(this.ae, this.f);
        if (ji.util.e.av()) {
            this.a(this.ae, this.g);
        }
        this.a(this.ac, this.ad, "North");
        this.a(this.ac, this.ae, "South");
        this.add(this.ac, "Center");
        this.af = new jiPanel(an, new BorderLayout());
        (this.aj = new Button(ji.util.d.b(232, an))).addActionListener(new aaf(this));
        (this.ai = new Button(ji.util.d.b(235, an))).addActionListener(new aag(this));
        (this.ab = new jiPanel(an, false)).setBorderStyle(0);
        this.ab.setLayout(new BorderLayout());
        if (ji.util.i.d(10) == 3 && !this.ah) {
            (this.aa = new jiPanel(an, false)).setBorderStyle(0);
            (this.h = new Checkbox(ji.util.d.b(1284, an))).addItemListener(this);
            this.h.setState(this.y);
            this.a(this.aa, this.h);
            this.a(this.ab, this.aa, "West");
        }
        (this.z = new jiPanel(an, false)).setBorderStyle(0);
        this.a(this.z, this.aj);
        this.a(this.z, this.ai);
        if (ji.util.i.d(10) == 3) {
            this.a(this.ab, this.z, "East");
        }
        else {
            this.a(this.ab, this.z, "Center");
        }
        this.a(this.af, this.ab, "South");
        this.af.setBorderStyle(0);
        this.add(this.af, "South");
        this.setBackground(ji.util.e.ao());
    }
    
    private final double i() {
        if (ji.util.d.bf()) {
            return 73.0;
        }
        return oh.ak;
    }
    
    private final boolean b(final String s) {
        try {
            if (this.ag == null) {
                this.ag = new p(s);
            }
            return this.ag.aw(false, this);
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
            return false;
        }
    }
    
    private final int a(final Frame frame, final String s, int n, final boolean b, final boolean b2, final af af) {
        String[] array = null;
        Font[] array2 = null;
        boolean b3 = false;
        this.a.setVisible(false);
        if (ji.util.i.c(253)) {
            ji.io.h.d(s, "FontDialog: font mode = ".concat(String.valueOf(String.valueOf(ji.util.i.d(10)))));
        }
        switch (ji.util.i.d(10)) {
            case 1: {
                array = this.a(this.r);
                break;
            }
            case 2: {
                array = new String[0];
                array2 = this.a(frame, s, af);
                if (array2 == null) {
                    ji.io.h.d(s, "FontDialog: #1 Viewer is configured to use ViewONE fonts only, but there are no ViewONE Fonts available (they need to be correctly added to the ViewONE Install path)");
                    ji.io.h.d(s, "FontDialog: Reverting to fonts installed on this machine...");
                    this.ah = true;
                    array = this.a(this.r);
                    break;
                }
                break;
            }
            case 3: {
                array = this.a(this.r);
                array2 = this.a(frame, s, af);
                if (b) {
                    break;
                }
                if (array2 != null) {
                    b3 = true;
                    break;
                }
                ji.io.h.d(s, "FontDialog: #2 Viewer is configured to show ViewONE fonts only, but there are no ViewONE Fonts available (they need to be correctly added to the ViewONE Install path)");
                ji.io.h.d(s, "FontDialog: Reverting to fonts installed on this machine...");
                this.ah = true;
                b3 = false;
                break;
            }
        }
        final k k = new k(array.length);
        int max = 0;
        for (int i = 0; i < array.length; ++i) {
            String s2 = array[i];
            final String lowerCase = s2.toLowerCase();
            if (array2 != null && !b3) {
                for (int j = 0; j < oh.p.length; ++j) {
                    if (oh.p[j].equals(lowerCase)) {
                        s2 = null;
                        break;
                    }
                }
            }
            if (s2 != null) {
                max = Math.max(this.a(this.r, s2), max);
                if (!b3) {
                    k.a(s2);
                }
            }
        }
        if (array2 != null) {
            for (int l = 0; l < oh.o.length; ++l) {
                k.a(oh.o[l]);
            }
        }
        this.a.removeAll();
        while (k.a() > 0) {
            this.a.add(k.b());
        }
        if (max > 137) {
            n += 3 * (max - 137);
        }
        this.a.setSize(max, this.a.getSize().height);
        if (!b2) {
            this.a.select(this.a(this.j.getFamily()));
        }
        this.a.setVisible(true);
        if (!b2) {
            this.ad.validate();
            this.ac.validate();
        }
        return n;
    }
    
    private final int a(final Component component, final String s) {
        return component.getGraphics().create().getFontMetrics().stringWidth(s);
    }
    
    public void a(final Container container, final Component component) {
        this.am.addElement(component);
        component.addKeyListener(this);
        container.add(component);
    }
    
    public void a(final Container container, final Component component, final Object o) {
        this.am.addElement(component);
        component.addKeyListener(this);
        container.add(component, o);
    }
    
    public void add(final Component component, final Object o) {
        this.am.addElement(component);
        component.addKeyListener(this);
        super.add(component, o);
    }
    
    public void setBackground(final Color color) {
        super.setBackground(color);
        this.ab.setBackground(color);
        this.af.setBackground(color);
        this.ac.setBackground(color);
        this.ad.setBackground(color);
        this.ae.setBackground(color);
        this.d.setBackground(color);
        this.e.setBackground(color);
        this.f.setBackground(color);
        if (ji.util.e.av()) {
            this.g.setBackground(color);
        }
        this.aj.setBackground(color);
        this.ai.setBackground(color);
    }
    
    private final void j() {
        while (this.am.size() > 0) {
            this.am.elementAt(0).removeKeyListener(this);
            this.am.removeElementAt(0);
        }
        this.removeKeyListener(this);
        if (this.i != null) {
            this.i.removeTextListener(this);
            this.i.removeActionListener(this);
        }
        if (this.c != null) {
            this.c.removeItemListener(this);
        }
        this.a.removeItemListener(this);
        this.aj.removeActionListener(this);
        this.ai.removeActionListener(this);
        this.z.remove(this.ai);
        this.z.remove(this.aj);
        this.ab.remove(this.z);
        if (this.aa != null) {
            this.h.removeItemListener(this);
            this.aa.remove(this.h);
            this.ab.remove(this.aa);
        }
        this.remove(this.ab);
        this.remove(this.af);
        this.ae.remove(this.d);
        this.ae.remove(this.e);
        this.ae.remove(this.f);
        if (ji.util.e.av()) {
            this.ae.remove(this.g);
        }
        if (this.i != null) {
            this.ad.remove(this.i);
        }
        this.ad.remove(this.a);
        if (this.c != null) {
            this.ad.remove(this.c);
        }
        this.ac.remove(this.ad);
        this.ac.remove(this.ae);
        this.remove(this.ac);
        if (this.ag != null) {
            this.ag.a();
            this.ag = null;
        }
        this.an = null;
        this.ao = null;
    }
    
    public String[] a(final Component component) {
        if (oh.l == null) {
            oh.l = ji.font.j.a(component);
            if (ji.util.i.c(253)) {
                for (int i = 0; i < oh.l.length; ++i) {
                    ji.io.h.d(this.an, String.valueOf(String.valueOf(new StringBuffer("FontDialog:").append(oh.l[i]).append(" is a local font"))));
                }
            }
        }
        return oh.l;
    }
    
    public Font[] a(final Component component, final String s, final af af) {
        if (!oh.q) {
            ji.font.j.a(af, component, s);
            final String[] h = ji.font.j.h(s);
            if (h != null) {
                String s2;
                if (ji.util.d.eg()) {
                    s2 = ji.res.z.c(s);
                }
                else {
                    s2 = ji.util.e.an();
                }
                final Vector vector = new Vector<Font>(h.length);
                for (int i = 0; i < h.length; ++i) {
                    vector.addElement(ji.font.j.a(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append("/").append(h[i]))), 0, 0, component, s));
                }
                final int size = vector.size();
                oh.m = new Font[size];
                oh.o = new String[size];
                oh.p = new String[size];
                oh.n = new String[size];
                if (size > 0) {
                    for (int j = 0; j < size; ++j) {
                        oh.n[j] = h[j];
                        oh.m[j] = vector.elementAt(j);
                        oh.o[j] = oh.m[j].getFamily();
                        oh.p[j] = oh.o[j].toLowerCase();
                        if (ji.util.i.c(253)) {
                            ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FontDialog:").append(oh.n[j]).append(" is an installed font  named '").append(oh.o[j]).append("/").append(oh.m[j].getFamily()).append("'"))));
                        }
                    }
                }
                while (vector.size() > 0) {
                    vector.removeElementAt(0);
                }
                oh.q = true;
            }
        }
        return oh.m;
    }
    
    public int a(final String s) {
        final String lowerCase = s.toLowerCase();
        for (int countItems = this.a.countItems(), i = 0; i < countItems; ++i) {
            if (this.a.getItem(i).toLowerCase().equals(lowerCase)) {
                return i;
            }
        }
        return 0;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a();
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource().equals(this.h)) {
            this.y = this.h.getState();
            this.a(this.ao, this.an, this.ap, this.y, false, this.r);
            this.ag.ax(this.y, this.ao);
        }
        else {
            this.a();
        }
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        this.a();
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 27) {
            this.h();
        }
        else if (keyEvent.getKeyCode() == 10) {
            this.f();
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        boolean b = false;
        if (keyEvent.getSource().equals(this.a)) {
            b = true;
        }
        if (this.c != null && keyEvent.getSource().equals(this.c)) {
            b = true;
        }
        if (b) {
            this.a();
        }
    }
    
    protected void a() {
        try {
            this.v = this.f.getState();
            if (ji.util.e.av()) {
                this.w = this.g.getState();
            }
            int n = 0;
            if (this.e.getState()) {
                n = 2;
            }
            String s;
            if (this.c != null) {
                s = this.c.getSelectedItem();
            }
            else {
                s = this.i.getText();
            }
            int max;
            if (ji.util.d.bf()) {
                double bb = this.r.b3().bb();
                if (this.r.r() != null && this.r.r().c()) {
                    bb *= this.r.r().d();
                }
                max = (int)Math.round(bb * ji.util.d.c(s, 84) / 73.0);
            }
            else {
                max = Math.max((int)Math.round(ji.util.d.c(s, 84) * this.i()), 2);
            }
            Font deriveFont = null;
            final String selectedItem = this.a.getSelectedItem();
            if (oh.m != null) {
                final String lowerCase = selectedItem.toLowerCase();
                for (int i = 0; i < oh.p.length; ++i) {
                    if (lowerCase.equals(oh.p[i])) {
                        deriveFont = oh.m[i].deriveFont((this.d.getState() ? 1 : 0) + n, max);
                        this.k = oh.n[i];
                        break;
                    }
                }
            }
            if (deriveFont == null) {
                deriveFont = new Font(this.a.getSelectedItem(), (this.d.getState() ? 1 : 0) + n, max);
            }
            this.j = deriveFont;
            if (this.r != null) {
                final String value = String.valueOf(String.valueOf(new StringBuffer("").append(deriveFont).append(this.v).append(this.w)));
                if (!this.al.equals(value)) {
                    this.al = value;
                    this.r.a(deriveFont, this.v, this.w, this.k, false);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void a(final Font j) {
        this.j = j;
    }
    
    public Font b() {
        return this.j;
    }
    
    public String c() {
        return this.k;
    }
    
    public boolean d() {
        return this.v;
    }
    
    public boolean e() {
        return this.w;
    }
    
    public void f() {
        this.r = null;
        this.setVisible(false);
        this.j();
    }
    
    public boolean g() {
        return this.ar;
    }
    
    public void h() {
        this.ar = true;
        if (this.r != null) {
            this.j = this.s;
            this.v = this.t;
            this.w = this.u;
            this.k = this.x;
        }
        else {
            this.j = null;
        }
        this.r = null;
        this.setVisible(false);
        this.j();
    }
    
    static {
        oh.l = null;
        oh.m = null;
        oh.n = null;
        oh.o = null;
        oh.p = null;
        oh.q = false;
        oh.ak = 1.6666;
    }
}
