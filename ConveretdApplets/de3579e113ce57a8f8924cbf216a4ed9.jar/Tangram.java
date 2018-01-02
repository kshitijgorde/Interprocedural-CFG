import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.util.Vector;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Font;
import java.applet.AudioClip;
import java.awt.Button;
import java.util.Hashtable;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Tangram extends Applet implements ActionListener
{
    Image T;
    Graphics U;
    Dimension size;
    FontMetrics Ba;
    Color Za;
    Color Gpa;
    Hashtable Hpa;
    boolean Ipa;
    boolean ab;
    String[] fb;
    Image Jpa;
    Button Kpa;
    super ib;
    AudioClip gb;
    AudioClip Lpa;
    switch Mpa;
    synchronized sa;
    private static String o = "\u83fc\u83f4\u83e2\u83e2\u83f0\u83f6\u83f4\u83f7\u83f8\u83fd\u83f4";
    private static String p = "\u83fc\u83f4\u83e2\u83e2\u83f0\u83f6\u83f4\u83e2\u83ce\u83f4\u83ff\u83bf\u83f5\u83f0\u83e5";
    private static String q = "\u83e5\u83f8\u83f2\u83fa\u83bf\u83f0\u83e4";
    private static String C = "\u83e2\u83f8\u83fd\u83f4\u83ff\u83e5\u83bf\u83f0\u83e4";
    private static String D = "\u83f3\u83f0\u83f2\u83fa\u83d2\u83fe\u83fd\u83fe\u83e3";
    private static String E = "\u83f7\u83fe\u83ff\u83e5\u83d2\u83fe\u83fd\u83fe\u83e3";
    private static String F = "\u83d0\u83e3\u83f8\u83f0\u83fd";
    private static String ta = "\u83f4\u83f0\u83e2\u83e8";
    private static String ua = "\u83fc\u83f4\u83f5\u83f8\u83e4\u83fc";
    private static String wa = "\u83f9\u83f0\u83e3\u83f5";
    private static String xa = "\u83ad\u83d3\u83c3\u83af";
    private static String Eoa = "\u83b1";
    private static String Moa = "\u83e2\u83b1\u83e5\u83b1\u83f0\u83b1\u83e3\u83b1\u83e5\u83b1\u83b1\u83b1\u83e5\u83b1\u83f0\u83b1\u83ff\u83b1\u83f6\u83b1\u83e3\u83b1\u83f0\u83b1\u83fc";
    private static String Noa = "\u83e5\u83f0\u83ff\u83f6\u83e3\u83f0\u83fc\u83bf\u83f6\u83f8\u83f7";
    private static String Ooa = "\u83f7\u83f8\u83f4\u83fd\u83f5\u83d2\u83fe\u83fd\u83fe\u83e3";
    private static String Poa = "\u83e1\u83f8\u83f4\u83f2\u83f4\u83d2\u83fe\u83fd\u83fe\u83e3";
    private static String Qoa = "\u83f3\u83fe\u83e3\u83f5\u83f4\u83e3\u83d2\u83fe\u83fd\u83fe\u83e3";
    private static String Roa = "\u83e2\u83f9\u83f0\u83e1\u83f4\u83d2\u83fe\u83fd\u83fe\u83e3";
    private static String Soa = "\u83e5\u83f4\u83e9\u83e5\u83d2\u83fe\u83fd\u83fe\u83e3";
    private static String Toa = "\u83f4\u83f5\u83f8\u83e5\u83fc\u83fe\u83f5\u83f4";
    private static String Uoa = "\u83bf\u83f6\u83eb";
    private static String Voa = "\u83e5\u83f0\u83ff\u83f6\u83e3\u83f0\u83fc";
    private static String Woa = "\u83e3\u83f4\u83f0\u83f5\u83f8\u83ff\u83f6\u83b1";
    private static String Xoa = "\u83b1\u83f9\u83f0\u83e2\u83b1\u83f7\u83f0\u83f8\u83fd\u83f4\u83f5";
    private static String Yoa = "\u83f9\u83f4\u83fd\u83e1\u83f7\u83f8\u83fd\u83f4";
    private static String Zoa = "\u83f9\u83f4\u83fd\u83e1\u83f7\u83f8\u83fd\u83f4\u83b1\u83e1\u83f0\u83e3\u83f0\u83fc\u83f4\u83e5\u83f4\u83e3\u83b1\u83f8\u83e2\u83b1\u83fc\u83f8\u83e2\u83e2\u83f8\u83ff\u83f6";
    private static String _pa = "\u83ff\u83e4\u83fd\u83fd\u83ce\u83e2\u83f4\u83f2\u83e5\u83f8\u83fe\u83ff";
    
    public void init() {
        String s = this.getParameter(Tangram.o);
        if (s == null) {
            s = Tangram.p;
        }
        this.sa = new synchronized(this.getCodeBase(), s);
        this.Mpa = new switch(this, this.sa);
        this.gb = this.Mpa._(Tangram.q);
        if (this.Mpa.i()) {
            (this.Lpa = this.Mpa._(Tangram.C)).loop();
        }
        this.size = this.getSize();
        this.Za = this.Mpa.a(Tangram.D, Color.white);
        this.Gpa = this.Mpa.a(Tangram.E, Color.black);
        this.setBackground(this.Za);
        this.setFont(new Font(Tangram.F, 1, 12));
        this.Ba = this.getFontMetrics(this.getFont());
    }
    
    private boolean l() {
        if (this.Mpa.h() && !this.Mpa.b(switch.Hoa)) {
            this.Mpa.a(true);
            return false;
        }
        if (!this.m() || !this.n()) {
            return false;
        }
        this.ib = new super(3);
        this.a(this.Mpa.l(Tangram.ta), 0);
        this.a(this.Mpa.l(Tangram.ua), 1);
        this.a(this.Mpa.l(Tangram.wa), 2);
        if (this.Mpa.k()) {
            return false;
        }
        if (!this.c()) {
            return false;
        }
        for (int i = 0; i < this.fb.length; ++i) {
            if (this.fb[i].equalsIgnoreCase(Tangram.xa)) {
                this.fb[i] = Tangram.Eoa;
            }
        }
        this.T = this.createImage(this.size.width, this.size.height);
        (this.U = this.T.getGraphics()).setFont(new Font(Tangram.F, 0, 16));
        this.Ba = this.getFontMetrics(this.U.getFont());
        this.setBackground(this.Za);
        this.setLayout(null);
        (this.Kpa = new Button(Tangram.Moa)).setFont(this.getFont());
        this.Kpa.setBounds(0, 189, 189, 26);
        this.Kpa.addActionListener(this);
        this.add(this.Kpa);
        this.validate();
        return !this.Mpa.k();
    }
    
    private boolean n() {
        final Vector<Image> vector = new Vector<Image>();
        vector.addElement(this.Jpa = this.getImage(this.getCodeBase(), Tangram.Noa));
        this.Mpa.a(vector, 0);
        return !this.Mpa.k();
    }
    
    private boolean m() {
        (this.Hpa = new Hashtable()).put(Tangram.Ooa, this.Mpa.a(Tangram.Ooa, new Color(8622277)));
        this.Hpa.put(Tangram.Poa, this.Mpa.a(Tangram.Poa, new Color(16777215)));
        this.Hpa.put(Tangram.Qoa, this.Mpa.a(Tangram.Qoa, new Color(128)));
        this.Hpa.put(Tangram.Roa, this.Mpa.a(Tangram.Roa, new Color(12964091)));
        this.Hpa.put(Tangram.Soa, this.Mpa.a(Tangram.Soa, new Color(16777215)));
        this.Hpa.put(Tangram.D, this.Za);
        this.ab = this.Mpa.b(Tangram.Toa, false);
        return !this.Mpa.k();
    }
    
    private boolean a(final String s, final int n) {
        throw throw1;
        if (s.endsWith(Tangram.Uoa)) {
            throw1 = new this();
        }
        else {
            throw1 = new throw();
        }
        if (throw1.b(this.getCodeBase(), s)) {
            for (int n2 = throw1.n(), i = 0; i < n2; ++i) {
                this.ib._(throw1.b(Tangram.Voa + i), n);
            }
            return true;
        }
        return this.Mpa.n(Tangram.Woa + s + Tangram.Xoa);
    }
    
    private boolean c() {
        final String a = this.Mpa.a(Tangram.Yoa, (String)null);
        if (a == null) {
            return this.Mpa.n(Tangram.Zoa);
        }
        final throw throw1 = new throw();
        if (throw1.b(this.getCodeBase(), a, false)) {
            this.fb = throw1.b(Tangram._pa);
            return true;
        }
        return this.Mpa.n(Tangram.Woa + a + Tangram.Xoa);
    }
    
    private void _(final Graphics graphics) {
        this.Mpa.b(graphics);
        final String k = this.sa.k();
        graphics.setColor(this.Gpa);
        graphics.drawString(k, this.Mpa._(k, true, graphics), this.Mpa._(k, false, graphics));
        if (this.l()) {
            this.Ipa = true;
            this.Mpa.b(graphics);
            this.repaint();
            return;
        }
        this.Mpa.b(graphics);
        String s;
        if (this.Mpa.j()) {
            s = switch.Foa;
        }
        else {
            s = this.sa.l();
        }
        graphics.setColor(this.Gpa);
        graphics.drawString(s, this.Mpa._(s, true, graphics), this.Mpa._(s, false, graphics));
    }
    
    public void update(final Graphics graphics) {
        this.U.drawImage(this.Jpa, 0, 0, this);
        graphics.drawImage(this.T, 0, 0, this);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.Ipa) {
            this._(graphics);
            return;
        }
        this.update(graphics);
        if (this.Mpa.k()) {
            this.showStatus(this.sa._());
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.Kpa) {
            new throws(2, this.Hpa, this.ib, this.fb, this.ab, this.sa, this.gb);
        }
    }
    
    public void destroy() {
        switch.b((Object)this.gb);
        switch.b((Object)this.Lpa);
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x18391);
        }
        return new String(array);
    }
    
    static {
        Tangram.o = _(Tangram.o);
        Tangram.p = _(Tangram.p);
        Tangram.q = _(Tangram.q);
        Tangram.C = _(Tangram.C);
        Tangram.D = _(Tangram.D);
        Tangram.E = _(Tangram.E);
        Tangram.F = _(Tangram.F);
        Tangram.ta = _(Tangram.ta);
        Tangram.ua = _(Tangram.ua);
        Tangram.wa = _(Tangram.wa);
        Tangram.xa = _(Tangram.xa);
        Tangram.Eoa = _(Tangram.Eoa);
        Tangram.Moa = _(Tangram.Moa);
        Tangram.Noa = _(Tangram.Noa);
        Tangram.Ooa = _(Tangram.Ooa);
        Tangram.Poa = _(Tangram.Poa);
        Tangram.Qoa = _(Tangram.Qoa);
        Tangram.Roa = _(Tangram.Roa);
        Tangram.Soa = _(Tangram.Soa);
        Tangram.Toa = _(Tangram.Toa);
        Tangram.Uoa = _(Tangram.Uoa);
        Tangram.Voa = _(Tangram.Voa);
        Tangram.Woa = _(Tangram.Woa);
        Tangram.Xoa = _(Tangram.Xoa);
        Tangram.Yoa = _(Tangram.Yoa);
        Tangram.Zoa = _(Tangram.Zoa);
        Tangram._pa = _(Tangram._pa);
    }
}
