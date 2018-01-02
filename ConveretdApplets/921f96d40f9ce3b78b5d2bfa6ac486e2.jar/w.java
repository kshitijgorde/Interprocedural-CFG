import java.awt.event.ActionEvent;
import java.util.StringTokenizer;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Hashtable;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class w extends Panel implements ActionListener, ItemListener
{
    private static final int[] p;
    private y d;
    private do a;
    private int n;
    private dg v;
    private dg i;
    private dl l;
    private dl b;
    private dl c;
    private dl e;
    private dl f;
    private Panel g;
    private Panel h;
    private String j;
    private int k;
    public Hashtable m;
    public int o;
    private static Hashtable q;
    
    public w(final y d) {
        this.g = new Panel();
        this.h = new Panel();
        this.j = "";
        this.m = new Hashtable();
        this.o = -1;
        this.d = d;
        this.n = d.a();
        this.n = ((this.n < 0 || this.n > 2) ? 1 : this.n);
        this.a = d.p();
        this.l = new dl("FIND TABLE", dl.j);
        this.b = new dl("rooms", dl.j);
        this.c = new dl("tables", dl.j);
        this.f = new dl("new table", dl.j);
        this.e = new dl("Options", dl.j);
        final dl[] array = { this.l, this.b, this.c, this.f, this.e };
        for (int i = 0; i < array.length; ++i) {
            array[i].p(this);
            array[i].setFont(dw.i);
        }
        this.v = new dg(d.p());
        this.i = new dg(d.p());
        this.v.p(this);
        this.v.addItemListener(this);
        this.i.p(this);
        this.i.addItemListener(this);
        final int n = (this.n >= 2) ? 100 : ((this.n == 1) ? 80 : 70);
        this.v.p(110, n);
        this.i.p(110, n);
    }
    
    public final void p(final int o) {
        if (o == this.o) {
            return;
        }
        this.o = o;
        this.removeAll();
        this.invalidate();
        this.v.invalidate();
        this.i.invalidate();
        this.setLayout(new BorderLayout(0, 0));
        if (o == 0) {
            final Panel panel = new Panel();
            panel.setLayout(new BorderLayout(0, 0));
            if (this.d.i()) {
                panel.add("Center", this.b);
            }
            panel.add("East", this.e);
            this.add("North", panel);
            this.add("South", this.i);
            this.add("Center", this.v);
        }
        else {
            final di di = new di(w.p[this.n], 0);
            final Panel panel2 = new Panel();
            this.add("West", di);
            this.add("Center", panel2);
            panel2.setLayout(new BorderLayout(0, 0));
            panel2.add("North", this.g);
            panel2.add("Center", this.v);
            di.setLayout(new BorderLayout(0, 0));
            di.add("North", this.h);
            di.add("Center", this.i);
            this.h.setLayout(new FlowLayout(0, 2, 2));
            this.h.add(this.l);
            this.g.setLayout(new FlowLayout(1, 2, 2));
            if (this.d.i()) {
                this.g.add(this.b);
            }
            this.g.add(this.c);
            this.g.add(this.f);
        }
        this.validate();
    }
    
    public final void p() {
        final Color[] array = dw.p[this.d.p()];
        this.v.setBackground(array[7]);
        this.i.setBackground(array[7]);
        final dl[] array2 = { this.l, this.b, this.c, this.f, this.e };
        for (int i = 0; i < array2.length; ++i) {
            array2[i].setBackground(array[4]);
            array2[i].setForeground(array[5]);
        }
        this.h.setBackground(array[6]);
        this.g.setBackground(array[6]);
    }
    
    public final void d() {
        this.v.d();
        this.i.d();
    }
    
    private static final int p(String lowerCase, String lowerCase2) {
        if (w.q == null) {
            (w.q = new Hashtable()).put("#common", new Integer(0));
            w.q.put("#blueonly", new Integer(1));
            w.q.put("#theriches", new Integer(2));
            w.q.put("#professionals", new Integer(3));
            w.q.put("#celebrities", new Integer(4));
            w.q.put("#royals", new Integer(5));
        }
        lowerCase = lowerCase.toLowerCase();
        lowerCase2 = lowerCase2.toLowerCase();
        if (lowerCase.startsWith("#")) {
            if (!lowerCase2.startsWith("#")) {
                return -1;
            }
            if (w.q.containsKey(lowerCase)) {
                if (w.q.containsKey(lowerCase2)) {
                    return w.q.get(lowerCase) - w.q.get(lowerCase2);
                }
                return -1;
            }
            else {
                if (w.q.containsKey(lowerCase2)) {
                    return 1;
                }
                return lowerCase.toLowerCase().compareTo(lowerCase2);
            }
        }
        else {
            if (lowerCase2.startsWith("#")) {
                return 1;
            }
            return lowerCase.compareTo(lowerCase2);
        }
    }
    
    private static final void p(final dg dg, final String s) {
        for (int i = 0; i < dg.p(); ++i) {
            final int p2 = p(dg.p(i), s);
            if (p2 == 0) {
                return;
            }
            if (p2 > 0) {
                dg.d(s, i);
                return;
            }
        }
        dg.n(s);
    }
    
    private static final void d(final dg dg, final String s) {
        for (int i = 0; i < dg.p(); ++i) {
            if (dg.p(i).equalsIgnoreCase(s)) {
                dg.p(i);
                return;
            }
        }
    }
    
    private final void p(final String s) {
        if (du.p(s, 10) != -1) {
            p(this.i, s.startsWith("#") ? s : ("Table #" + s));
            return;
        }
        if (!s.equalsIgnoreCase("#Idle") && !s.startsWith("#Level")) {
            p(this.i, s);
        }
    }
    
    public final void d(final String s) {
        this.v.p(s, this.d.p(s) ? 12 : 1);
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        final String string = itemEvent.getItem().toString();
        if (itemEvent.getSource() != this.v) {
            if (itemEvent.getSource() == this.i) {
                if (itemEvent.getStateChange() == 1) {
                    final Point locationOnScreen = this.i.getLocationOnScreen();
                    final Point p = this.i.p();
                    locationOnScreen.translate(p.x, p.y);
                    this.d.p(string, locationOnScreen);
                    return;
                }
                this.d.v();
            }
            return;
        }
        if (itemEvent.getStateChange() == 1) {
            final Point locationOnScreen2 = this.v.getLocationOnScreen();
            final Point p2 = this.v.p();
            locationOnScreen2.translate(p2.x, p2.y);
            this.d.d(string, locationOnScreen2);
            return;
        }
        this.d.p();
    }
    
    public final boolean p(final String s, final String s2) {
        final String d = this.d.p().d();
        if (s.equals("JOIN")) {
            final String p2 = du.p(s2, 1, ' ');
            final String p3 = du.p(s2, 0, ' ');
            p(this.v, p2);
            if (!p2.equalsIgnoreCase(d)) {
                if (this.d.p().a()) {
                    this.a.i("<1>***" + p2 + " join table");
                }
            }
            else {
                this.a.l("<12>***You are now in table " + p3);
                this.i.a(p3.startsWith("#") ? p3 : ("Table #" + p3));
                this.i.v(p3.startsWith("#") ? p3 : ("Table #" + p3));
                this.v.a(p2);
            }
            if (this.d.p(p2)) {
                this.v.p(p2, 12);
            }
            this.v.v(d);
            return true;
        }
        if (s.equals("PART")) {
            final String p4 = du.p(s2, 1, ' ');
            du.p(s2, 0, ' ');
            if (!p4.equalsIgnoreCase(d) && this.d.p().a()) {
                this.a.i("<1>***" + p4 + " left");
            }
            d(this.v, p4);
            this.v.v(d);
            return false;
        }
        if (s.equals("QPART")) {
            d(this.v, du.p(s2, 1, ' '));
            this.v.v(d);
            return false;
        }
        if (s.equals("QUERYTABLE")) {
            this.a.l("<5>***" + s2);
            return true;
        }
        if (s.equals("USERS")) {
            this.v.d();
            final StringTokenizer stringTokenizer = new StringTokenizer(s2);
            if (stringTokenizer.hasMoreTokens()) {
                stringTokenizer.nextToken();
            }
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                p(this.v, nextToken);
                if (this.d.p(nextToken)) {
                    this.v.p(nextToken, 12);
                }
            }
            this.v.a(d);
            this.v.v(d);
            return true;
        }
        if (s.equals("OPEN")) {
            this.p(du.p(s2, 1, ' '));
            final String p5 = du.p(s2, 2, ' ');
            if (p5 != null && p5.equals(this.j)) {
                ++this.k;
            }
            else {
                this.k = 1;
            }
            this.j = p5;
            if (this.k == 4 && this.d.p().l()) {
                this.m.put(p5, p5);
            }
            if (p5 == null || (this.m.get(p5) == null && this.d.p().i())) {
                this.a.i("<2>***open " + du.d(s2, 0, ' '));
            }
            return true;
        }
        if (s.equals("CLOSE")) {
            final String p6 = du.p(s2, 1, ' ');
            if (du.p(p6, 10) != -1) {
                d(this.i, p6.startsWith("#") ? p6 : ("Table #" + p6));
            }
            else {
                d(this.i, p6);
            }
            return true;
        }
        if (s.equals("TABLES")) {
            this.i.d();
            final StringTokenizer stringTokenizer2 = new StringTokenizer(s2);
            if (stringTokenizer2.hasMoreTokens()) {
                stringTokenizer2.nextToken();
            }
            while (stringTokenizer2.hasMoreTokens()) {
                this.p(stringTokenizer2.nextToken());
            }
            return true;
        }
        return false;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final i p = this.d.p();
        this.d.requestFocus();
        if (actionEvent.getSource() == this.l) {
            if (!this.d.d()) {
                this.a.l("<4>*** Please unsit first before going to a different table");
                return;
            }
            if (this.d.a()) {
                this.a.l("<2>***Start fighting in " + this.d.p() + "...");
                p.p("REQUEST");
                return;
            }
            this.a.l("<12>***Finding an open table...");
            p.p("FINDTABLE");
        }
        else {
            if (actionEvent.getSource() == this.v) {
                p.d("WHO " + actionEvent.getActionCommand());
                this.d.p();
                return;
            }
            if (actionEvent.getSource() == this.i) {
                String s = actionEvent.getActionCommand().toString();
                if (s.startsWith("Table #")) {
                    s = s.substring(7);
                }
                if (!this.d.d()) {
                    this.a.l("<4>*** Please unsit first before going to a different table");
                }
                else {
                    this.a.l("<12>*** Joining table " + s + "...");
                    p.d("JOIN " + s);
                }
                this.d.v();
                return;
            }
            if (actionEvent.getSource() == this.b) {
                final Point locationOnScreen = this.b.getLocationOnScreen();
                this.d.p().p(locationOnScreen.x, locationOnScreen.y + this.b.getSize().height);
                return;
            }
            if (actionEvent.getSource() == this.c) {
                this.a.l("<12>***Requesting table info...");
                p.p("TABLELIST");
                return;
            }
            if (actionEvent.getSource() != this.f) {
                if (actionEvent.getSource() == this.e) {
                    this.d.p().setVisible(true);
                }
                return;
            }
            if (!this.d.d()) {
                this.a.l("<4>*** Please unsit before creating a different table");
                return;
            }
            if (this.d.a()) {
                this.a.l("<2>***Start fighting in " + this.d.p() + "...");
                p.p("REQUEST");
                return;
            }
            this.a.l("<12>***Creating a new table...");
            p.p("OPEN");
        }
    }
    
    static {
        p = new int[] { 90, 130, 160 };
    }
}
