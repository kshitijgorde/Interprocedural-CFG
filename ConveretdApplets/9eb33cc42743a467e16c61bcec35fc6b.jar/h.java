import java.util.Hashtable;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.Cursor;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.Toolkit;
import java.awt.Component;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.util.Properties;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.event.FocusListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public final class h extends Canvas implements KeyListener, AdjustmentListener, MouseListener, MouseMotionListener, ComponentListener, FocusListener
{
    public boolean cu;
    public boolean ct;
    public boolean cs;
    public boolean cr;
    public boolean cq;
    public ab cp;
    public ab co;
    public q cn;
    public g cm;
    public boolean cl;
    public PopupMenu ck;
    public Panel cj;
    public Frame ci;
    public int ch;
    public p cg;
    public String cf;
    public Properties ce;
    public boolean cd;
    public String cc;
    public boolean ca;
    public byte b9;
    public byte b8;
    public boolean b7;
    public boolean b6;
    public boolean b5;
    public boolean b4;
    public boolean b3;
    public int b2;
    public int b1;
    public int b0;
    public int b_;
    public int bz;
    public int by;
    public int bx;
    public int bw;
    public int bv;
    public int bu;
    public int bt;
    public int bs;
    public int br;
    public int bq;
    public int bp;
    public int bo;
    public int bn;
    public int bm;
    public int bl;
    public int bk;
    public int bj;
    public int bi;
    public int bh;
    public int bg;
    public int bf;
    public int be;
    public int bd;
    public int bc;
    public boolean ba;
    public boolean a9;
    public String a8;
    public int a7;
    public boolean a6;
    public long a5;
    public int a4;
    public int a3;
    public int a2;
    public int a1;
    public Color a0;
    public Color a_;
    public Color az;
    public static final Color[] ay;
    public static final String[] ax;
    public char[][] aw;
    public int[][] av;
    public int au;
    public int at;
    public int as;
    public static final char[] ar;
    public static final int[] aq;
    public boolean[] ao;
    public boolean[] an;
    public Image am;
    public Graphics al;
    public Dimension ak;
    public Font aj;
    public Font ai;
    public boolean ah;
    public boolean ag;
    
    public h(final Frame ci, final q cn, final Properties properties) throws IllegalArgumentException {
        this.cu = false;
        this.ct = false;
        this.cs = false;
        this.cr = false;
        this.cq = true;
        this.ch = 4;
        this.bu = 2;
        this.bt = 2;
        this.a7 = -1;
        this.ao = new boolean[512];
        this.ah = false;
        this.ag = false;
        this.cm = null;
        this.cl = false;
        this.cf = null;
        this.an = new boolean[18];
        this.b4 = false;
        this.b5 = false;
        this.a4 = 32768;
        this.bj = 0;
        this.bi = 0;
        this.at = 0;
        this.as = 0;
        this.b6 = false;
        this.cc = "";
        this.ci = ci;
        (this.cn = cn).du(this);
        this.cm(properties, this.ca = true);
        this.ca = false;
        this.cd = false;
        this.bl();
        ci.addComponentListener(this);
        this.addKeyListener(this);
        this.addComponentListener(this);
        this.addFocusListener(this);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }
    
    public final void cp(final p cg) {
        this.cg = cg;
    }
    
    public final p co() {
        return this.cg;
    }
    
    public final void cn() {
        if (this.cg != null) {
            this.cg.c3();
        }
    }
    
    public final void cm(final Properties properties, final boolean b) throws IllegalArgumentException, NoSuchElementException {
        final Properties ce = this.ce;
        this.ce = new Properties(r.e5);
        if (b && ce != null) {
            final Enumeration<String> keys = ((Hashtable<String, V>)ce).keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                ((Hashtable<String, String>)this.ce).put(s, ce.getProperty(s));
            }
        }
        final Enumeration<String> keys2 = ((Hashtable<String, V>)properties).keys();
        while (keys2.hasMoreElements()) {
            final String s2 = keys2.nextElement();
            properties.getProperty(s2);
            if (!r.dv(s2)) {
                throw new NoSuchElementException("unknown terminal-property '" + s2 + "'");
            }
        }
        for (int i = this.an.length; i < r.e4.length; ++i) {
            final String s3 = r.e4[i][0];
            String s4 = properties.getProperty(s3);
            if (s4 == null) {
                s4 = this.ce.getProperty(s3);
            }
            if (!b && ce != null) {
                this.ci(s3, s4, !s4.equals(ce.getProperty(s3)));
            }
            else {
                this.ci(s3, s4, this.ca);
            }
        }
        for (int j = 0; j < this.an.length; ++j) {
            final String s5 = r.e4[j][0];
            String s6 = properties.getProperty(s5);
            if (s6 == null) {
                s6 = this.ce.getProperty(s5);
            }
            if (!b && ce != null) {
                this.ci(s5, s6, !s6.equals(ce.getProperty(s5)));
            }
            else {
                this.ci(s5, s6, this.ca);
            }
        }
    }
    
    public final Properties cl() {
        return this.ce;
    }
    
    public final boolean ck() {
        return this.cd;
    }
    
    public final void cj(final boolean cd) {
        this.cd = cd;
    }
    
    public final void ci(final String s, final String s2) throws IllegalArgumentException, NoSuchElementException {
        this.ci(s, s2, false);
    }
    
    public final synchronized void ci(final String s, String string, final boolean b) throws IllegalArgumentException, NoSuchElementException {
        boolean b2 = false;
        final String ce;
        if ((ce = this.ce(s)) != null && ce.equals(string)) {
            b2 = true;
            if (!b) {
                return;
            }
        }
        int n;
        for (n = 0; n < this.an.length && !r.e4[n][0].equals(s); ++n) {}
        Label_0978: {
            if (n < this.an.length) {
                if (!string.equals("true") && !string.equals("false")) {
                    throw new IllegalArgumentException("value for '" + s + "' must be 'true' or 'false'");
                }
                this.ar(n, new Boolean(string));
            }
            else if (s.equals("te")) {
                if (this.cn instanceof q) {
                    this.cn.dr(string);
                }
            }
            else if (s.equals("fn")) {
                this.cd(string, Integer.parseInt(this.ce("fs")));
            }
            else {
                if (s.equals("fs")) {
                    try {
                        this.cd(this.ce("fn"), Integer.parseInt(string));
                        break Label_0978;
                    }
                    catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("value for '" + s + "' must be an integer");
                    }
                }
                if (s.equals("sl")) {
                    try {
                        final int int1 = Integer.parseInt(string);
                        if (int1 < 0 || int1 > 8192) {
                            throw new NumberFormatException();
                        }
                        this.b6(int1);
                        break Label_0978;
                    }
                    catch (NumberFormatException ex2) {
                        throw new IllegalArgumentException("value for '" + s + "' must be an integer (0-8192)");
                    }
                }
                if (s.equals("sb")) {
                    if (this.cj != null) {
                        if (string.equals("left") || string.equals("right")) {
                            if (this.cm != null) {
                                this.cj.remove(this.cm);
                                if (string.equals("right")) {
                                    this.cj.add(this.cm, "East");
                                }
                                else {
                                    this.cj.add(this.cm, "West");
                                }
                                this.cl = true;
                                this.b8();
                                this.cm.l(string);
                                this.ci.pack();
                                this.requestFocus();
                            }
                        }
                        else {
                            if (!string.equals("none")) {
                                throw new IllegalArgumentException("scrollbar can be right, left or none");
                            }
                            if (this.cm != null) {
                                this.cj.remove(this.cm);
                            }
                            this.ci.pack();
                            this.requestFocus();
                            this.cl = false;
                        }
                    }
                }
                else {
                    if (!s.equals("bg") && !s.equals("fg")) {
                        if (!s.equals("cc")) {
                            if (s.equals("rg")) {
                                int bz;
                                if (string.equals("top")) {
                                    bz = 1;
                                }
                                else {
                                    if (!string.equals("bottom")) {
                                        throw new IllegalArgumentException("reszize gravity can be 'top' or 'bottom'");
                                    }
                                    bz = 0;
                                }
                                this.bz = bz;
                                break Label_0978;
                            }
                            if (s.equals("de")) {
                                if (string.equals("DEL")) {
                                    this.b8 = 127;
                                    break Label_0978;
                                }
                                if (string.equals("BS")) {
                                    this.b8 = 8;
                                    break Label_0978;
                                }
                                throw new IllegalArgumentException("delete character can be 'DEL' or 'BS'");
                            }
                            else if (s.equals("bs")) {
                                if (string.equals("DEL")) {
                                    this.b9 = 127;
                                    break Label_0978;
                                }
                                if (string.equals("BS")) {
                                    this.b9 = 8;
                                    break Label_0978;
                                }
                                throw new IllegalArgumentException("backspace character can be 'DEL' or 'BS'");
                            }
                            else {
                                if (s.equals("gm")) {
                                    this.cf(string, true);
                                    break Label_0978;
                                }
                                if (s.equals("sd")) {
                                    if (string.charAt(0) != '\"' || string.charAt(string.length() - 1) != '\"') {
                                        string = "\"" + string + "\"";
                                    }
                                    this.a8 = string.substring(1, string.length());
                                    break Label_0978;
                                }
                                throw new NoSuchElementException("unknown terminal-property '" + s + "'");
                            }
                        }
                    }
                    Color a_;
                    try {
                        if (Character.isDigit(string.charAt(0))) {
                            a_ = ch(string);
                        }
                        else {
                            a_ = cg(string);
                        }
                    }
                    catch (NumberFormatException ex3) {
                        throw new IllegalArgumentException("valid colors: 'color-name' or '<r>,<g>,<b>'");
                    }
                    if (s.equals("bg")) {
                        this.setBackground(this.a0 = a_);
                    }
                    else if (s.equals("cc")) {
                        this.az = a_;
                    }
                    else {
                        this.setForeground(this.a_ = a_);
                    }
                    this.bz(false);
                }
            }
        }
        ((Hashtable<String, String>)this.ce).put(s, string);
        if (!b2) {
            this.cd = true;
            this.cn();
        }
    }
    
    public static Color ch(final String s) throws NumberFormatException {
        final int index = s.indexOf(44);
        final int lastIndex = s.lastIndexOf(44);
        if (index == -1 || lastIndex == -1) {
            throw new NumberFormatException();
        }
        return new Color(Integer.parseInt(s.substring(0, index).trim()), Integer.parseInt(s.substring(index + 1, lastIndex).trim()), Integer.parseInt(s.substring(lastIndex + 1).trim()));
    }
    
    public static Color cg(final String s) throws IllegalArgumentException {
        int n;
        for (n = 0; n < h.ay.length && !h.ax[n].equalsIgnoreCase(s); ++n) {}
        if (n == h.ay.length) {
            throw new IllegalArgumentException("Unknown color: " + s);
        }
        return h.ay[n];
    }
    
    public final void cf(final String s, final boolean b) throws IllegalArgumentException {
        final int index = s.indexOf(120);
        int index2 = s.indexOf(43);
        final int index3 = s.indexOf(45);
        if (index3 != -1) {
            index2 = ((index2 > index3 || index2 == -1) ? index3 : index2);
        }
        int int1;
        int int2;
        int n;
        int n2;
        try {
            if (index == -1) {
                throw new Exception();
            }
            int1 = Integer.parseInt(s.substring(0, index).trim());
            int2 = Integer.parseInt(s.substring(index + 1, (index2 == -1) ? s.length() : index2).trim());
            n = 2 * this.bu + this.bo * int1;
            n2 = 2 * this.bt + this.bn * int2;
            if (index2 != -1) {
                int n3 = s.indexOf(43, index2 + 1);
                if (n3 == -1) {
                    n3 = s.indexOf(45, index2 + 1);
                    if (n3 == -1) {
                        throw new Exception();
                    }
                }
                final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                final Insets insets = this.ci.getInsets();
                final int n4 = this.cl ? this.cm.getSize().width : 0;
                int int3 = Integer.parseInt(s.substring(index2 + 1, n3).trim());
                int int4 = Integer.parseInt(s.substring(n3 + 1).trim());
                if (s.charAt(index2) == '-') {
                    int3 = screenSize.width - n - int3 - insets.left - insets.right - n4;
                }
                if (s.charAt(n3) == '-') {
                    int4 = screenSize.height - n2 - int4 - insets.top - insets.bottom;
                }
                this.cc = s.substring(index2).trim();
                if (this.isShowing()) {
                    this.ci.setLocation(int3, int4);
                }
            }
            else {
                this.cc = "";
            }
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("geometry must be '<cols>x<rows>[position]', e.g. '80x24+0-0'");
        }
        if (b) {
            this.setSize(n, n2);
            if (this.isShowing()) {
                this.componentResized(null);
                this.ci.pack();
                this.requestFocus();
            }
            else {
                this.cq = true;
                this.b4(int2, int1);
                this.bg();
                this.a_();
            }
        }
    }
    
    public final String ce(final String s) {
        return this.ce.getProperty(s);
    }
    
    private final void cd(final String s, final int n) {
        this.aj = new Font(s, 0, n);
        this.ai = new Font(s, 1, n);
        super.setFont(this.aj);
        this.r(0, 0);
        if (this.isShowing()) {
            this.componentResized(null);
        }
    }
    
    public final void setFont(final Font font) {
        this.cd(font.getName(), font.getSize());
    }
    
    public final void cc(final String cf) {
        this.cf = cf;
        this.al(this.by, this.bx, this.bw, this.bv);
    }
    
    public final String cb() {
        return this.cf;
    }
    
    public final void ca(final int n) {
        switch (n) {
            case 1: {
                this.ch = 16;
                break;
            }
            case 2: {
                this.ch = 8;
                break;
            }
            case 3: {
                this.ch = 4;
                break;
            }
        }
    }
    
    public final PopupMenu b9(final String s) {
        if (this.ck != null) {
            return this.ck;
        }
        this.add(this.ck = new PopupMenu(s));
        return this.ck;
    }
    
    public final void b8() {
        if (this.cl) {
            this.cm.setValues(this.at, this.by, 0, this.as + this.by);
            this.cm.setBlockIncrement(this.by);
        }
    }
    
    public final Panel b7() {
        if (this.cj != null) {
            return this.cj;
        }
        this.cl = true;
        this.cm = new g(this, 1);
        this.b8();
        this.cm.addAdjustmentListener(this);
        (this.cj = new Panel(new BorderLayout())).add(this, "Center");
        final String ce = this.ce("sb");
        if (ce.equals("left")) {
            this.cj.add(this.cm, "West");
        }
        else if (ce.equals("right")) {
            this.cj.add(this.cm, "East");
        }
        else {
            this.cl = false;
        }
        if (this.cl) {
            this.cm.l(ce);
        }
        return this.cj;
    }
    
    private final void b6(int au) {
        final int au2 = this.au;
        boolean b = false;
        au = ((au < 0) ? 0 : au);
        au = ((au > 8192) ? 8192 : au);
        if (this.au != au) {
            final char[][] aw = this.aw;
            final int[][] av = this.av;
            this.au = au;
            try {
                this.b4(this.by, this.bx);
            }
            catch (OutOfMemoryError outOfMemoryError) {
                this.au = au2;
                this.b4(this.by, this.bx);
                b = true;
            }
            final int n = 0;
            int n2;
            int n3;
            if (au2 < this.au) {
                n2 = 0;
                n3 = au2 + this.by;
            }
            else if (this.as <= this.au) {
                n2 = 0;
                n3 = this.as + this.by;
            }
            else {
                n2 = this.as - this.au;
                n3 = this.au + this.by;
                this.as -= n2;
            }
            System.arraycopy(aw, n2, this.aw, n, n3);
            System.arraycopy(av, n2, this.av, n, n3);
            this.at = this.as;
            this.b8();
            if (b) {
                this.bx("\n\rOut of memory allocating scrollback buffer, reverting to " + this.au + " lines!");
            }
        }
    }
    
    public final void b5() {
        final char[][] aw = this.aw;
        final int[][] av = this.av;
        this.b4(this.by, this.bx);
        final int as = this.as;
        System.arraycopy(aw, this.as, this.aw, 0, this.by);
        System.arraycopy(av, this.as, this.av, 0, this.by);
        this.as = 0;
        this.at = 0;
        this.b8();
        this.bz(true);
    }
    
    public final void b4(final int by, final int bx) {
        this.by = by;
        this.bx = bx;
        this.aw = new char[by + this.au][bx];
        this.av = new int[by + this.au][bx];
    }
    
    public final String b3() {
        return this.cn.b3();
    }
    
    public final int b2() {
        return this.by;
    }
    
    public final int b1() {
        return this.bx;
    }
    
    public final int b0() {
        return this.bw;
    }
    
    public final int b_() {
        return this.bv;
    }
    
    public final void bz(final boolean b) {
        this.by(0, 0, this.by, this.bx);
        if (b && this.isShowing()) {
            final Graphics graphics = this.getGraphics();
            if (graphics != null) {
                this.update(graphics);
            }
        }
        else {
            this.repaint();
        }
    }
    
    public final void by(final int b2, final int b3, final int b4, final int b_) {
        if (b2 < this.b2) {
            this.b2 = b2;
        }
        if (b4 > this.b1) {
            this.b1 = b4;
        }
        if (b3 < this.b0) {
            this.b0 = b3;
        }
        if (b_ > this.b_) {
            this.b_ = b_;
        }
    }
    
    public final synchronized void bx(final char c) {
        if (this.at != this.as && this.an[6]) {
            this.b6 = false;
            this.at = this.as;
            if (this.cl) {
                this.cm.setValue(this.at);
            }
            this.by(0, 0, this.by, this.bx);
        }
        final int dn;
        if ((dn = this.cn.dn(c)) != -1) {
            final char c2 = (char)dn;
            if (this.bi == this.bx) {
                if (this.an[1]) {
                    ++this.bj;
                    this.bi = 0;
                    if (this.bj == this.br) {
                        this.a3(1);
                        this.bj = this.br - 1;
                    }
                }
                else {
                    --this.bi;
                }
            }
            if (this.an[3]) {
                this.av(1);
            }
            this.by(this.bj, this.bi, this.bj + 1, this.bi + 1);
            final int n = this.at + this.bj;
            this.av[n][this.bi] = this.a4;
            this.aw[n][this.bi++] = c2;
        }
        this.repaint();
    }
    
    public final synchronized void bx(final char[] array, final int n, final int n2) {
        this.ah = true;
        for (int n3 = n + n2, i = n; i < n3; ++i) {
            this.bx(array[i]);
        }
    }
    
    public final void bx(final String s) {
        final char[] charArray = s.toCharArray();
        this.bx(charArray, 0, charArray.length);
    }
    
    public final void bw(final char c) {
        if (this.bi == this.bx) {
            if (this.an[1]) {
                ++this.bj;
                this.bi = 0;
                if (this.bj == this.br) {
                    this.a3(1);
                    this.bj = this.br - 1;
                }
            }
            else {
                --this.bi;
            }
        }
        this.by(this.bj, this.bi, this.bj + 1, this.bi + 1);
        final int n = this.at + this.bj;
        this.av[n][this.bi] = (this.a4 | 0x100);
        this.aw[n][this.bi++] = c;
    }
    
    public final void bv(final ab cp) {
        this.cp = cp;
    }
    
    public final void bu(final ab co) {
        this.co = co;
    }
    
    public final void bt(final byte[] array) {
        if (this.cp != null) {
            try {
                this.cp.bt(array);
            }
            catch (IOException ex) {}
        }
    }
    
    public final void bs() {
        if (this.an[13]) {
            this.ar(0, !this.an[0]);
            this.ar(0, !this.an[0]);
        }
        else {
            final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
            if (defaultToolkit != null) {
                try {
                    defaultToolkit.beep();
                }
                catch (Exception ex) {
                    this.ar(13, true);
                    this.bs();
                }
            }
        }
    }
    
    public final void br() {
        this.a8(1);
    }
    
    public final void bq() {
        if (this.bi < this.bp) {
            int n;
            for (n = this.bi + 1; n < this.bp && !this.ao[n]; ++n) {}
            this.bi = ((n < this.bp) ? n : (this.bp - 1));
        }
    }
    
    public final void bp(int n) {
        while (n-- > 0) {
            this.bq();
        }
    }
    
    public final void bo(int n) {
        if (this.bi > 0 && n >= 0) {
            int n2;
            for (n2 = this.bi - 1; n2 >= 0 && (!this.ao[n2] || --n != 0); --n2) {}
            this.bi = ((n2 < 0) ? 0 : n2);
        }
    }
    
    public final void bn(final int n) {
        this.ao[n] = true;
    }
    
    public final void bm(final int n) {
        this.ao[n] = false;
    }
    
    public final void bl() {
        for (int i = 0; i < 512; ++i) {
            if (i % 8 == 0) {
                this.ao[i] = true;
            }
            else {
                this.ao[i] = false;
            }
        }
    }
    
    public final void bk() {
        for (int i = 0; i < 512; ++i) {
            this.ao[i] = false;
        }
    }
    
    public final void bj() {
        this.bi = this.bq;
    }
    
    public final void bi() {
        ++this.bj;
        if (this.bj == this.br) {
            this.a3(1);
            this.bj = this.br - 1;
        }
        if (this.an[11] && this.an[4]) {
            this.bj();
        }
    }
    
    public final void bh() {
        this.cn.dd();
        this.bz(true);
    }
    
    public final void bg() {
        this.bs = 0;
        this.br = this.by;
        this.bq = 0;
        this.bp = this.bx;
        this.b3 = false;
    }
    
    public final void bf(final int n, final int n2) {
        this.bf(n, 0, n2, this.bx);
    }
    
    public final void bf(final int bs, final int bq, final int br, final int bp) {
        this.bs = bs;
        this.bq = bq;
        this.br = br;
        this.bp = bp;
        if (this.ba) {
            final int n = this.bf - this.at;
            final int n2 = this.bd - this.at;
            if (bs != 0 && (n >= 0 || n2 >= 0)) {
                if ((n >= bs || n2 >= bs) && (n < br || n2 < br)) {
                    this.ab();
                }
            }
            else if (n >= br || n2 >= br) {
                this.ab();
            }
        }
        if (this.bq != 0 || this.bp != this.bx) {
            this.b3 = true;
        }
        else {
            this.b3 = false;
        }
    }
    
    public final int be() {
        return this.bj;
    }
    
    public final int bd() {
        return this.bi;
    }
    
    public final void bc(int bj, int bi, final boolean b) {
        int n = this.by - 1;
        int n2 = this.bx - 1;
        int bs = 0;
        int bq = 0;
        if (b) {
            bj += this.bs;
            n = this.br - 1;
            bs = this.bs;
            bi += this.bq;
            n2 = this.bp - 1;
            bq = this.bq;
        }
        if (bj < bs) {
            bj = bs;
        }
        if (bi < bq) {
            bi = bq;
        }
        if (bj > n) {
            bj = n;
        }
        if (bi > n2) {
            bi = n2;
        }
        this.bj = bj;
        this.bi = bi;
    }
    
    public final void bb(final int n) {
        final int bj = (this.bj < this.bs) ? 0 : this.bs;
        this.bj -= n;
        if (this.bj < bj) {
            this.bj = bj;
        }
    }
    
    public final void ba(final int n) {
        final int bj = (this.bj > this.br - 1) ? (this.by - 1) : (this.br - 1);
        this.bj += n;
        if (this.bj > bj) {
            this.bj = bj;
        }
    }
    
    public final void a9(final int n) {
        this.bi += n;
        if (this.bi > this.bp) {
            this.bi = this.bp;
        }
    }
    
    public final void a8(final int n) {
        this.bi -= n;
        if (this.bi < this.bq) {
            if (this.an[2]) {
                this.bi = this.bp - (this.bq - this.bi);
                this.bb(1);
            }
            else {
                this.bi = this.bq;
            }
        }
    }
    
    public final void a7(final int n) {
        if (this.bj > this.br || this.bj + n < this.br) {
            this.ba(n);
        }
        else {
            final int n2 = this.br - this.bj;
            this.ba(n2);
            this.a3(n - n2 + 1);
        }
    }
    
    public final void a6(final int n) {
        if (this.bj < this.bs || this.bj - n >= this.bs) {
            this.bb(n);
        }
        else {
            final int n2 = this.bj - this.bs;
            this.a2(n - n2);
            this.bb(n2);
        }
    }
    
    public final void a5() {
        this.a3 = this.bj;
        this.a2 = this.bi;
        this.a1 = this.a4;
    }
    
    public final void a4() {
        this.bj = this.a3;
        this.bi = this.a2;
        this.a4 = this.a1;
    }
    
    public final void a3(final int n) {
        final int n2 = this.br - this.bs;
        int bs = this.bs;
        if (this.b3) {
            if (n < n2) {
                bs = n2 - n + this.bs;
                for (int i = this.bs; i < bs; ++i) {
                    System.arraycopy(this.aw[this.at + i + n], this.bq, this.aw[this.at + i], this.bq, this.bp - this.bq);
                    System.arraycopy(this.av[this.at + i + n], this.bq, this.av[this.at + i], this.bq, this.bp - this.bq);
                }
            }
            for (int j = bs; j < this.br; ++j) {
                System.arraycopy(h.ar, 0, this.aw[this.at + j], this.bq, this.bp - this.bq);
                System.arraycopy(h.aq, 0, this.av[this.at + j], this.bq, this.bp - this.bq);
            }
        }
        else if (this.bs == 0 && this.br == this.by && this.au > 0) {
            final int n3 = (n < n2) ? n : n2;
            if (this.at + n3 > this.au) {
                if (this.ba) {
                    if (this.bf - n >= 0 && this.bd - n >= 0) {
                        this.bf -= n;
                        this.bd -= n;
                    }
                    else {
                        this.ab();
                    }
                }
                final int n4 = n2 - n3;
                System.arraycopy(this.aw, n3, this.aw, 0, this.au + n4);
                System.arraycopy(this.av, n3, this.av, 0, this.au + n4);
                for (int k = n2 - n3; k < n2; ++k) {
                    this.aw[this.au + k] = new char[this.bx];
                    this.av[this.au + k] = new int[this.bx];
                }
            }
            else {
                this.at += n3;
                this.as = this.at;
                this.b8();
            }
        }
        else {
            if (n < n2) {
                bs = n2 - n + this.bs;
                System.arraycopy(this.aw, this.at + this.bs + n, this.aw, this.at + this.bs, n2 - n);
                System.arraycopy(this.av, this.at + this.bs + n, this.av, this.at + this.bs, n2 - n);
            }
            for (int l = bs; l < this.br; ++l) {
                this.aw[this.at + l] = new char[this.bx];
                this.av[this.at + l] = new int[this.bx];
            }
        }
        this.by(this.bs, this.bq, this.br, this.bp);
    }
    
    public final void a2(final int n) {
        final int n2 = this.br - this.bs;
        int br = this.br;
        if (this.b3) {
            if (n < n2) {
                br = this.bs + n;
                for (int i = this.br - 1; i >= br; --i) {
                    System.arraycopy(this.aw[this.at + i - n], this.bq, this.aw[this.at + i], this.bq, this.bp - this.bq);
                    System.arraycopy(this.av[this.at + i - n], this.bq, this.av[this.at + i], this.bq, this.bp - this.bq);
                }
            }
            for (int j = this.bs; j < br; ++j) {
                System.arraycopy(h.ar, 0, this.aw[this.at + j], this.bq, this.bp - this.bq);
                System.arraycopy(h.aq, 0, this.av[this.at + j], this.bq, this.bp - this.bq);
            }
        }
        else {
            if (n < n2) {
                br = this.bs + n;
                System.arraycopy(this.aw, this.at + this.bs, this.aw, this.at + this.bs + n, n2 - n);
                System.arraycopy(this.av, this.at + this.bs, this.av, this.at + this.bs + n, n2 - n);
            }
            for (int k = this.bs; k < br; ++k) {
                this.aw[this.at + k] = new char[this.bx];
                this.av[this.at + k] = new int[this.bx];
            }
        }
        this.by(this.bs, 0, this.br, this.bx);
    }
    
    public final void a1() {
        this.az();
        final int[] array = new int[this.bx];
        for (int i = 0; i < this.bx; ++i) {
            array[i] = (this.a4 & 0xFFFF00C0);
        }
        for (int j = this.bj + 1; j < this.br; ++j) {
            this.aw[this.at + j] = new char[this.bx];
            System.arraycopy(array, 0, this.av[this.at + j] = new int[this.bx], 0, this.bx);
        }
        this.by(this.bj, 0, this.br, this.bx);
    }
    
    public final void a0() {
        this.ay();
        final int[] array = new int[this.bx];
        for (int i = 0; i < this.bx; ++i) {
            array[i] = (this.a4 & 0xFFFF00C0);
        }
        for (int j = this.bs; j < this.bj; ++j) {
            this.aw[this.at + j] = new char[this.bx];
            System.arraycopy(array, 0, this.av[this.at + j] = new int[this.bx], 0, this.bx);
        }
        this.by(this.bs, 0, this.bj, this.bx);
    }
    
    public final void a_() {
        final int[] array = new int[this.bx];
        for (int i = 0; i < this.bx; ++i) {
            array[i] = (this.a4 & 0xFFFF00C0);
        }
        for (int j = this.bs; j < this.br; ++j) {
            this.aw[this.as + j] = new char[this.bx];
            System.arraycopy(array, 0, this.av[this.as + j] = new int[this.bx], 0, this.bx);
        }
        this.b2 = 0;
        this.b1 = this.by;
        this.b0 = 0;
        this.b_ = this.bx;
        this.repaint();
    }
    
    public final void az() {
        System.arraycopy(h.ar, 0, this.aw[this.at + this.bj], this.bi, this.bx - this.bi);
        for (int i = this.bi; i < this.bx; ++i) {
            this.av[this.at + this.bj][i] = (this.a4 & 0xFFFF00C0);
        }
        this.by(this.bj, this.bi, this.bj + 1, this.bx);
    }
    
    public final void ay() {
        System.arraycopy(h.ar, 0, this.aw[this.at + this.bj], 0, this.bi);
        for (int i = 0; i < this.bi; ++i) {
            this.av[this.at + this.bj][i] = (this.a4 & 0xFFFF00C0);
        }
        this.b0 = 0;
        this.by(this.bj, 0, this.bj + 1, this.bi);
    }
    
    public final void ax() {
        this.aw[this.at + this.bj] = new char[this.bx];
        this.av[this.at + this.bj] = new int[this.bx];
        for (int i = 0; i < this.bx; ++i) {
            this.av[this.at + this.bj][i] = (this.a4 & 0xFFFF00C0);
        }
        this.b0 = 0;
        this.b_ = this.bx;
        this.by(this.bj, 0, this.bj + 1, this.bx);
    }
    
    public final void aw(int n) {
        if (n > this.bx - this.bi) {
            n = this.bx - this.bi;
        }
        System.arraycopy(h.ar, 0, this.aw[this.at + this.bj], this.bi, n);
        for (int i = 0; i < n; ++i) {
            this.av[this.at + this.bj][this.bi + i] = (this.a4 & 0xFFFF00C0);
        }
        this.by(this.bj, this.bi, this.bj, this.bi + n);
    }
    
    public final void av(final int n) {
        int bp = this.bp;
        if (this.bi < this.bq || this.bi > this.bp) {
            return;
        }
        if (this.bi + n < this.bp) {
            bp = this.bi + n;
            System.arraycopy(this.aw[this.at + this.bj], this.bi, this.aw[this.at + this.bj], bp, this.bp - bp);
            System.arraycopy(this.av[this.at + this.bj], this.bi, this.av[this.at + this.bj], bp, this.bp - bp);
        }
        System.arraycopy(h.ar, 0, this.aw[this.at + this.bj], this.bi, bp - this.bi);
        for (int i = this.bi; i < bp; ++i) {
            this.av[this.at + this.bj][i] = (this.a4 & 0xFFFF00C0);
        }
        this.by(this.bj, this.bi, this.bj + 1, this.bp);
    }
    
    public final void au(final int n) {
        int bi = this.bi;
        if (this.bi < this.bq || this.bi > this.bp) {
            return;
        }
        if (this.bi + n < this.bp) {
            bi = this.bp - n;
            System.arraycopy(this.aw[this.at + this.bj], this.bi + n, this.aw[this.at + this.bj], this.bi, bi - this.bi);
            System.arraycopy(this.av[this.at + this.bj], this.bi + n, this.av[this.at + this.bj], this.bi, bi - this.bi);
        }
        System.arraycopy(h.ar, 0, this.aw[this.at + this.bj], bi, this.bp - bi);
        for (int i = bi; i < this.bp; ++i) {
            this.av[this.at + this.bj][i] = (this.a4 & 0xFFFF00C0);
        }
        this.by(this.bj, this.bi, this.bj + 1, this.bp);
    }
    
    public final void at(final int n) {
        int br = this.br;
        if (this.bj < this.bs || this.bj > this.br) {
            return;
        }
        if (this.b3) {
            if (this.bj + n < this.br) {
                br = this.bj + n;
                for (int i = this.br - 1; i >= br; --i) {
                    System.arraycopy(this.aw[this.at + i - n], this.bq, this.aw[this.at + i], this.bq, this.bp - this.bq);
                    System.arraycopy(this.av[this.at + i - n], this.bq, this.av[this.at + i], this.bq, this.bp - this.bq);
                }
            }
            for (int j = this.bj; j < br; ++j) {
                System.arraycopy(h.ar, 0, this.aw[this.at + j], this.bq, this.bp - this.bq);
                System.arraycopy(h.aq, 0, this.av[this.at + j], this.bq, this.bp - this.bq);
            }
        }
        else {
            if (this.bj + n < this.br) {
                br = this.bj + n;
                System.arraycopy(this.aw, this.at + this.bj, this.aw, this.at + br, this.br - br);
                System.arraycopy(this.av, this.at + this.bj, this.av, this.at + br, this.br - br);
            }
            final int[] array = new int[this.bx];
            for (int k = 0; k < this.bx; ++k) {
                array[k] = (this.a4 & 0xFFFF00C0);
            }
            for (int l = this.bj; l < br; ++l) {
                this.aw[this.at + l] = new char[this.bx];
                System.arraycopy(array, 0, this.av[this.at + l] = new int[this.bx], 0, this.bx);
            }
        }
        this.by(this.bj, 0, this.br, this.bx);
    }
    
    public final void as(final int n) {
        int bj = this.bj;
        if (this.bj < this.bs || this.bj > this.br) {
            return;
        }
        if (this.b3) {
            if (this.bj + n < this.br) {
                bj = this.br - n - 1;
                for (int i = this.bj; i <= bj; ++i) {
                    System.arraycopy(this.aw[this.at + i + n], this.bq, this.aw[this.at + i], this.bq, this.bp - this.bq);
                    System.arraycopy(this.av[this.at + i + n], this.bq, this.av[this.at + i], this.bq, this.bp - this.bq);
                }
            }
            for (int j = bj; j < this.br; ++j) {
                System.arraycopy(h.ar, 0, this.aw[this.at + j], this.bq, this.bp - this.bq);
                System.arraycopy(h.aq, 0, this.av[this.at + j], this.bq, this.bp - this.bq);
            }
        }
        else {
            if (this.bj + n < this.br) {
                bj = this.br - n;
                System.arraycopy(this.aw, this.at + this.bj + n, this.aw, this.at + this.bj, bj - this.bj);
                System.arraycopy(this.av, this.at + this.bj + n, this.av, this.at + this.bj, bj - this.bj);
            }
            final int[] array = new int[this.bx];
            for (int k = 0; k < this.bx; ++k) {
                array[k] = (this.a4 & 0xFFFF00C0);
            }
            for (int l = bj; l < this.br; ++l) {
                this.aw[this.at + l] = new char[this.bx];
                System.arraycopy(array, 0, this.av[this.at + l] = new int[this.bx], 0, this.bx);
            }
        }
        this.by(this.bj, 0, this.br, this.bx);
    }
    
    public final void ar(final int n, final boolean b) {
        if (n > this.an.length || n < 0) {
            return;
        }
        ((Hashtable<String, String>)this.ce).put(r.e4[n][0], String.valueOf(b));
        switch (n) {
            case 0: {
                if (b != this.an[n]) {
                    this.an[n] = b;
                    final Color a0 = this.a0;
                    this.a0 = this.a_;
                    this.a_ = a0;
                    this.bz(true);
                    break;
                }
                break;
            }
            case 9: {
                this.repaint();
                break;
            }
            case 15: {
                if (this.an[n] != b && this.an[16]) {
                    this.ci("gm", String.valueOf(b ? 132 : 80) + "x" + this.by + this.cc);
                    this.bc(0, 0, false);
                    break;
                }
                break;
            }
            case 16: {
                if (this.cg != null) {
                    this.cg.c2(15, b);
                    break;
                }
                break;
            }
        }
        this.an[n] = b;
        if (this.cg != null) {
            this.cg.c1(n, b);
        }
    }
    
    public final boolean aq(final int n) {
        return n <= this.an.length && n >= 0 && this.an[n];
    }
    
    public final void ap(final int n, final boolean b) {
        if (b) {
            this.a4 |= n;
        }
        else {
            this.a4 &= ~n;
        }
    }
    
    public final void ao(int n) {
        if (n >= 0 && n < 8) {
            if ((this.a4 & 0x1) != 0x0) {
                n += 8;
            }
            this.a4 &= 0xFF00FFBF;
            this.a4 |= (0x40 | n << 16);
        }
        else {
            this.a4 &= 0xFFFFFFBF;
        }
    }
    
    public final void an(final int n) {
        if (n >= 0 && n < 8) {
            this.a4 &= 0xFFFF7F;
            this.a4 |= (0x80 | n << 24);
        }
        else {
            this.a4 &= 0xFFFFFF7F;
        }
    }
    
    public final void am() {
        this.a4 = 32768;
    }
    
    public final void al(final int n, final int n2, final int n3, final int n4) {
        if (this.cp != null) {
            this.cp.al(n, n2, n3, n4);
        }
    }
    
    public final void keyTyped(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyChar()) {
            case '~': {
                if (!this.cr) {
                    this.cs = true;
                    final char c = '~';
                    if (this.an[11]) {
                        this.bx(c);
                    }
                    if (this.cp != null) {
                        try {
                            this.cp.ah(c);
                        }
                        catch (IOException ex) {}
                    }
                    else {
                        this.ah(c);
                    }
                }
            }
            default: {}
        }
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        final int modifiers = keyEvent.getModifiers();
        final char keyChar = keyEvent.getKeyChar();
        if (this.ak(keyChar, keyCode, modifiers)) {
            return;
        }
        this.cn.c9(keyCode, modifiers);
        if (keyChar != '\0' && keyChar != '\0') {
            final int aj;
            if ((aj = this.aj(keyChar, keyCode, modifiers)) == -1) {
                return;
            }
            char c = (char)aj;
            if (this.an[11]) {
                this.bx(c);
            }
            if (this.cp != null) {
                try {
                    if ((c == '\n' || c == '\r') && this.an[4]) {
                        c = '\n';
                        this.cp.ah('\r');
                    }
                    this.cp.ah(c);
                }
                catch (IOException ex) {}
            }
            else {
                this.ah(c);
            }
            if (this.at != this.as && this.an[5]) {
                this.at = this.as;
                if (this.cl) {
                    this.cm.setValue(this.at);
                }
                this.bz(false);
            }
        }
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 18: {
                this.cu = false;
                break;
            }
            case 17: {
                this.ct = false;
                break;
            }
        }
    }
    
    public final boolean ak(final int n, final int n2, final int n3) {
        boolean b = false;
        switch (n2) {
            case 18: {
                if (this.ct) {
                    this.ct = false;
                }
                else {
                    this.cu = true;
                }
                b = true;
                break;
            }
            case 17: {
                this.ct = true;
                b = true;
                break;
            }
            case 8: {
                this.bt(new byte[] { this.b9 });
                b = true;
                break;
            }
            case 127: {
                this.bt(new byte[] { this.b8 });
                b = true;
                break;
            }
            case 33:
            case 34:
            case 35:
            case 36: {
                if (n3 == 1 || this.an[7]) {
                    this.ag(n2);
                    b = true;
                    break;
                }
                break;
            }
            case 155: {
                if (n3 == 1) {
                    this.s();
                    b = true;
                    break;
                }
                if (n3 == 2) {
                    this.t();
                    b = true;
                    break;
                }
                break;
            }
            case 16:
            case 20: {
                b = true;
                break;
            }
        }
        return b;
    }
    
    public final int aj(final char c, final int n, final int n2) {
        int ai = c;
        if ((n2 & 0x2) != 0x0) {
            switch (n) {
                case 77: {
                    ai = 13;
                    break;
                }
                case 32: {
                    if (this.an[14]) {
                        ai = 0;
                        break;
                    }
                    break;
                }
                default: {
                    if (n >= 65 && n <= 90) {
                        ai = this.ai(n);
                        break;
                    }
                    if (c == '@') {
                        ai = 0;
                        break;
                    }
                    if (c == '[') {
                        ai = 27;
                        break;
                    }
                    if (c == '\\') {
                        ai = 28;
                        break;
                    }
                    if (c == ']') {
                        ai = 29;
                        break;
                    }
                    if (c == '^') {
                        ai = 30;
                        break;
                    }
                    if (c == '_') {
                        ai = 31;
                        break;
                    }
                    break;
                }
            }
        }
        else {
            if (ai == 10 && n == 10 && !this.ct) {
                ai = 13;
            }
            else if (c == '~') {
                if (!this.cs) {
                    this.cr = true;
                }
                else {
                    ai = -1;
                }
            }
            else if (c == '\uffff') {
                ai = -1;
            }
            else if (c == '\uff7e') {
                ai = -1;
            }
            if (ai != -1 && this.cu && this.cp != null) {
                try {
                    this.cp.ah('\u001b');
                }
                catch (IOException ex) {}
            }
        }
        return ai;
    }
    
    public final int ai(final int n) {
        int n2 = 0;
        switch (n) {
            case 65: {
                n2 = 1;
                break;
            }
            case 66: {
                n2 = 2;
                break;
            }
            case 67: {
                n2 = 3;
                break;
            }
            case 68: {
                n2 = 4;
                break;
            }
            case 69: {
                n2 = 5;
                break;
            }
            case 70: {
                n2 = 6;
                break;
            }
            case 71: {
                n2 = 7;
                break;
            }
            case 72: {
                n2 = 8;
                break;
            }
            case 73: {
                n2 = 9;
                break;
            }
            case 74: {
                n2 = 10;
                break;
            }
            case 75: {
                n2 = 11;
                break;
            }
            case 76: {
                n2 = 12;
                break;
            }
            case 77: {
                n2 = 13;
                break;
            }
            case 78: {
                n2 = 14;
                break;
            }
            case 79: {
                n2 = 15;
                break;
            }
            case 80: {
                n2 = 16;
                break;
            }
            case 81: {
                n2 = 17;
                break;
            }
            case 82: {
                n2 = 18;
                break;
            }
            case 83: {
                n2 = 19;
                break;
            }
            case 84: {
                n2 = 20;
                break;
            }
            case 85: {
                n2 = 21;
                break;
            }
            case 86: {
                n2 = 22;
                break;
            }
            case 87: {
                n2 = 23;
                break;
            }
            case 88: {
                n2 = 24;
                break;
            }
            case 89: {
                n2 = 25;
                break;
            }
            case 90: {
                n2 = 26;
                break;
            }
        }
        return n2;
    }
    
    public final void ah(final char c) {
    }
    
    public final void ag(final int n) {
        switch (n) {
            case 33: {
                this.at -= this.by;
                if (this.at < 0) {
                    this.at = 0;
                }
                this.b8();
                this.bz(true);
                break;
            }
            case 34: {
                this.at += this.by;
                if (this.at > this.as) {
                    this.at = this.as;
                }
                this.b8();
                this.bz(true);
                break;
            }
            case 36: {
                this.at = 0;
                this.b8();
                this.bz(true);
                break;
            }
            case 35: {
                this.at = this.as;
                this.b8();
                this.bz(true);
                break;
            }
        }
    }
    
    public final void focusGained(final FocusEvent focusEvent) {
        this.setCursor(Cursor.getPredefinedCursor(2));
        this.b7 = true;
        this.af();
    }
    
    public final void focusLost(final FocusEvent focusEvent) {
        this.cu = false;
        this.ct = false;
        this.setCursor(Cursor.getPredefinedCursor(0));
        this.b7 = false;
        this.af();
    }
    
    public final synchronized void af() {
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            this.o(graphics);
            this.n(graphics);
        }
    }
    
    public final void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public final void ae() {
        this.componentShown(new ComponentEvent(this.ci, 0));
    }
    
    public final synchronized void componentShown(final ComponentEvent componentEvent) {
        if (componentEvent.getComponent() == this.ci && this.cq && this.cq) {
            try {
                this.wait(500L);
            }
            catch (InterruptedException ex) {}
            this.cq = false;
            this.cf(this.ce("gm"), true);
        }
    }
    
    public final void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public final synchronized void componentResized(final ComponentEvent componentEvent) {
        final Dimension size = this.getSize();
        final int n = (size.width - 2 * this.bu) / this.bo;
        final int n2 = (size.height - 2 * this.bt) / this.bn;
        final int bx = this.bx;
        final int by = this.by;
        final char[][] aw = this.aw;
        final int[][] av = this.av;
        if (this.cq || (componentEvent != null && componentEvent.getComponent() != this) || n <= 0 || n2 <= 0) {
            return;
        }
        this.bw = size.height;
        this.bv = size.width;
        if (n != bx) {
            this.ab();
        }
        if (n2 != this.by || n != this.bx) {
            this.b4(n2, n);
            this.bg();
            this.a_();
            final int n3 = (bx < n) ? bx : n;
            if (this.bz == 1) {
                for (int n4 = ((by < n2) ? by : n2) + this.as, i = 0; i < n4; ++i) {
                    System.arraycopy(aw[i], 0, this.aw[i], 0, n3);
                    System.arraycopy(av[i], 0, this.av[i], 0, n3);
                }
            }
            else {
                if (this.ba) {
                    this.bf += n2 - by;
                    this.bd += n2 - by;
                }
                int n6;
                int n7;
                int n8;
                if (by < n2) {
                    final int n5 = n2 - by;
                    n6 = by + this.as;
                    n7 = 0;
                    this.bj += n5;
                    if (this.as - n5 < 0) {
                        n8 = n5;
                    }
                    else {
                        n8 = 0;
                        this.at -= n5;
                        this.as -= n5;
                    }
                }
                else {
                    final int n9 = by - n2;
                    n8 = 0;
                    this.bj -= n9;
                    if (this.bj < 0) {
                        this.bj = 0;
                    }
                    if (this.as + n9 > this.au) {
                        n6 = n2 + this.as;
                        n7 = n9;
                    }
                    else {
                        n6 = by + this.as;
                        n7 = 0;
                        this.at += n9;
                        this.as += n9;
                    }
                }
                for (int j = 0; j < n6; ++j) {
                    System.arraycopy(aw[j + n7], 0, this.aw[j + n8], 0, n3);
                    System.arraycopy(av[j + n7], 0, this.av[j + n8], 0, n3);
                }
            }
            if (this.bj >= n2) {
                this.bj = n2 - 1;
            }
            if (this.bi >= n) {
                this.bi = n - 1;
            }
            if (this.bh >= n2 || this.bg >= n) {
                this.b4 = false;
                this.b5 = false;
            }
            this.b8();
            this.al(this.by, this.bx, this.bw, this.bv);
            this.al = null;
            final String string = String.valueOf(this.bx) + "x" + this.by + this.cc;
            this.cd = true;
            ((Hashtable<String, String>)this.ce).put("gm", string);
            this.cn();
            this.bz(false);
            this.requestFocus();
        }
    }
    
    public final void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        final int value = adjustmentEvent.getValue();
        if (value >= 0 && value <= this.as) {
            this.at = value;
            this.bz(false);
        }
    }
    
    public final void ad() {
        this.bf = 0;
        this.be = 0;
        this.bd = this.as + this.bj;
        this.bc = this.bi;
        this.ac(this.bf, this.be, this.bd, this.bc);
        this.ba = true;
        if (this.co != null) {
            this.co.en(true);
            if (this.an[17]) {
                this.t();
            }
        }
    }
    
    public final void ac(int n, final int n2, int n3, final int n4) {
        if (n != n3) {
            for (int i = n2; i < this.bx; ++i) {
                final int[] array = this.av[n];
                final int n5 = i;
                array[n5] |= 0x1000;
            }
            for (int j = n + 1; j < n3; ++j) {
                for (int k = 0; k < this.bx; ++k) {
                    final int[] array2 = this.av[j];
                    final int n6 = k;
                    array2[n6] |= 0x1000;
                }
            }
            for (int l = 0; l <= n4; ++l) {
                final int[] array3 = this.av[n3];
                final int n7 = l;
                array3[n7] |= 0x1000;
            }
        }
        else {
            for (int n8 = n2; n8 <= n4; ++n8) {
                final int[] array4 = this.av[n];
                final int n9 = n8;
                array4[n9] |= 0x1000;
            }
        }
        n -= this.at;
        n3 -= this.at;
        if (n < 0) {
            n = 0;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        this.by(n, 0, n3 + 1, this.bx);
        this.repaint();
    }
    
    public final void ab(int n, final int n2, int n3, final int n4) {
        if (n != n3) {
            for (int i = n2; i < this.bx; ++i) {
                final int[] array = this.av[n];
                final int n5 = i;
                array[n5] &= 0xFFFFEFFF;
            }
            for (int j = n + 1; j < n3; ++j) {
                for (int k = 0; k < this.bx; ++k) {
                    final int[] array2 = this.av[j];
                    final int n6 = k;
                    array2[n6] &= 0xFFFFEFFF;
                }
            }
            for (int l = 0; l <= n4; ++l) {
                final int[] array3 = this.av[n3];
                final int n7 = l;
                array3[n7] &= 0xFFFFEFFF;
            }
        }
        else {
            for (int n8 = n2; n8 <= n4; ++n8) {
                final int[] array4 = this.av[n];
                final int n9 = n8;
                array4[n9] &= 0xFFFFEFFF;
            }
        }
        n -= this.at;
        n3 -= this.at;
        if (n < 0) {
            n = 0;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        this.by(n, 0, n3 + 1, this.bx);
        this.repaint();
    }
    
    public final void ab() {
        if (!this.ba) {
            return;
        }
        if (this.a9) {
            this.ab(this.bd, this.bc, this.bf, this.be);
        }
        else {
            this.ab(this.bf, this.be, this.bd, this.bc);
        }
        this.ba = false;
        if (this.co != null) {
            this.co.en(false);
        }
    }
    
    public final int aa(final int n) {
        int n2 = (n - this.bt) / this.bn;
        if (n2 < 0) {
            n2 = 0;
        }
        else if (n2 >= this.by) {
            n2 = this.by - 1;
        }
        return n2;
    }
    
    public final int z(final int n) {
        int n2 = (n - this.bu) / this.bo;
        if (n2 < 0) {
            n2 = 0;
        }
        else if (n2 >= this.bx) {
            n2 = this.bx - 1;
        }
        return n2;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getModifiers() == 16) {
            this.requestFocus();
        }
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        final long currentTimeMillis = System.currentTimeMillis();
        final int aa = this.aa(mouseEvent.getY());
        final int z = this.z(mouseEvent.getX());
        if (mouseEvent.getModifiers() == (this.ch | 0x2) && this.ck != null) {
            this.ct = false;
            this.ck.show(this, mouseEvent.getX(), mouseEvent.getY());
        }
        this.cn.c6(aa, z, true, mouseEvent.getModifiers());
        final int n = aa + this.at;
        this.ab();
        this.bf = n;
        this.be = z;
        this.bd = n;
        this.bc = z;
        if (currentTimeMillis - this.a5 < 250L) {
            this.u(n, z);
        }
        else {
            this.a7 = -1;
            this.a6 = false;
        }
        this.a5 = currentTimeMillis;
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        final int aa = this.aa(mouseEvent.getY());
        final int z = this.z(mouseEvent.getX());
        if (this.cp != null) {
            if (mouseEvent.getModifiers() == 16) {
                if (this.ba) {
                    this.co.en(true);
                    if (this.an[17]) {
                        this.t();
                    }
                }
            }
            else if (mouseEvent.getModifiers() == 8) {
                this.s();
            }
        }
        this.cn.c6(aa, z, false, mouseEvent.getModifiers());
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public final synchronized void mouseDragged(final MouseEvent mouseEvent) {
        int n = (mouseEvent.getY() - this.bt) / this.bn;
        int bc = (mouseEvent.getX() - this.bu) / this.bo;
        if (n < 0) {
            n = 0;
        }
        else if (n >= this.by) {
            n = this.by - 1;
        }
        if (bc < 0) {
            bc = 0;
        }
        else if (bc >= this.bx) {
            bc = this.bx - 1;
        }
        final int bd = n + this.at;
        if (bd == this.bd && bc == this.bc) {
            return;
        }
        final boolean b = this.bf > bd || (this.bf == bd && bc < this.be);
        if (b != this.a9) {
            if (this.a9) {
                this.ab(this.bd, this.bc, this.bf, this.be);
            }
            else {
                this.ab(this.bf, this.be, this.bd, this.bc);
            }
            this.a9 = b;
            this.bd = this.bf;
            this.bc = this.be;
        }
        final boolean b2 = this.bd > bd || (this.bd == bd && bc < this.bc);
        if (this.a9) {
            if (b2) {
                this.ac(bd, bc, this.bd, this.bc);
            }
            else {
                this.ab(this.bd, this.bc, bd, bc);
            }
        }
        else if (b2) {
            this.ab(bd, bc, this.bd, this.bc);
        }
        else {
            this.ac(this.bd, this.bc, bd, bc);
        }
        this.a9 = b;
        this.bd = bd;
        this.bc = bc;
        if (this.bf == this.bd && this.be == this.bc) {
            this.ba = false;
        }
        else {
            this.ba = true;
        }
    }
    
    public final int y(final int n, final int n2) {
        int n3;
        for (n3 = n2; n3 < this.bx && this.aw[n][n3] == '\0'; ++n3) {}
        return n3;
    }
    
    public final int x(final int n, final int n2) {
        int n3;
        for (n3 = n2; n3 >= 0 && this.aw[n][n3] == '\0'; --n3) {}
        return n3;
    }
    
    public final String w(final int n, final int n2) {
        String string = "";
        int n3 = n2 - n;
        if (n2 == this.bx) {
            n3 = -1;
        }
        for (int i = 0; i <= n3; ++i) {
            string = String.valueOf(string) + " ";
        }
        return string;
    }
    
    public final String v() {
        if (!this.ba) {
            return null;
        }
        String s;
        if (this.an[8]) {
            s = "\r\n";
        }
        else {
            s = "\r";
        }
        int n;
        int n2;
        int n3;
        int n4;
        if (this.a9) {
            n = this.bd;
            n2 = this.bc;
            n3 = this.bf;
            n4 = this.be;
        }
        else {
            n = this.bf;
            n2 = this.be;
            n3 = this.bd;
            n4 = this.bc;
        }
        String s2 = "";
        if (n != n3) {
            int i;
            for (i = n2; i < this.bx; ++i) {
                if (this.aw[n][i] == '\0') {
                    final int y = this.y(n, i);
                    s2 = String.valueOf(s2) + this.w(i, y);
                    i = y - 1;
                }
                else {
                    s2 = String.valueOf(s2) + this.aw[n][i];
                }
            }
            if (i == this.bx) {
                s2 = String.valueOf(s2) + s;
            }
            for (int j = n + 1; j < n3; ++j) {
                for (int k = 0; k < this.bx; ++k) {
                    if (this.aw[j][k] == '\0') {
                        final int y2 = this.y(j, k);
                        s2 = String.valueOf(s2) + this.w(k, y2);
                        k = y2 - 1;
                    }
                    else {
                        s2 = String.valueOf(s2) + this.aw[j][k];
                    }
                }
                s2 = String.valueOf(s2) + s;
            }
            int l;
            for (l = 0; l <= n4; ++l) {
                if (this.aw[n3][l] == '\0') {
                    final int y3 = this.y(n3, l);
                    s2 = String.valueOf(s2) + this.w(l, y3);
                    l = y3 - 1;
                }
                else {
                    s2 = String.valueOf(s2) + this.aw[n3][l];
                }
            }
            if (l == this.bx) {
                s2 = String.valueOf(s2) + s;
            }
        }
        else {
            int n5;
            for (n5 = n2; n5 <= n4; ++n5) {
                if (this.aw[n][n5] == '\0') {
                    final int y4 = this.y(n, n5);
                    s2 = String.valueOf(s2) + this.w(n5, y4);
                    n5 = y4 - 1;
                }
                else {
                    s2 = String.valueOf(s2) + this.aw[n][n5];
                }
            }
            if (n5 == this.bx) {
                s2 = String.valueOf(s2) + s;
            }
        }
        return s2;
    }
    
    public final void u(final int bd, final int n) {
        if (this.a7 == bd && this.a6) {
            this.be = 0;
            this.bc = this.bx - 1;
        }
        else {
            if (this.aw[bd][n] != '\0') {
                int n2;
                for (n2 = n; n2 >= 0 && this.a8.indexOf(this.aw[bd][n2]) == -1 && this.aw[bd][n2] != '\0'; --n2) {}
                this.be = n2 + 1;
                int n3;
                for (n3 = n; n3 < this.bx && this.a8.indexOf(this.aw[bd][n3]) == -1 && this.aw[bd][n3] != '\0'; ++n3) {}
                this.bc = n3 - 1;
            }
            else {
                this.be = this.x(bd, n) + 1;
                this.bc = this.y(bd, n) - 1;
            }
            this.be = ((this.be > n) ? n : this.be);
            this.bc = ((this.bc < n) ? n : this.bc);
        }
        this.a6 = !this.a6;
        this.a7 = bd;
        this.bf = bd;
        this.bd = bd;
        this.a9 = false;
        this.ba = true;
        this.ac(this.bf, this.be, this.bd, this.bc);
    }
    
    public final void t() {
        if (this.co != null) {
            this.co.eo(this.v());
        }
    }
    
    public final void s() {
        if (this.co != null) {
            final String v = this.co.v();
            if (v != null) {
                if (this.an[11]) {
                    this.bx(v);
                }
                this.bt(v.getBytes());
            }
        }
    }
    
    public final Dimension r(final int n, final int n2) {
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        this.bo = -1;
        this.bn = fontMetrics.getHeight();
        this.bm = fontMetrics.getMaxAscent();
        fontMetrics.getMaxDescent();
        this.bl = fontMetrics.getLeading();
        this.bk = this.bm + this.bl - 1;
        if (this.bo == -1) {
            this.bo = fontMetrics.charWidth('W');
        }
        return new Dimension(n2 * this.bo + 2 * this.bt, n * this.bn + 2 * this.bu);
    }
    
    public final Dimension getPreferredSize() {
        return this.r(this.by, this.bx);
    }
    
    public final Dimension getMinimumSize() {
        return this.r(2, 8);
    }
    
    public final Dimension getMaximumSize() {
        return this.r(512, 512);
    }
    
    public final void q(final Graphics graphics, final Graphics graphics2) {
        int n;
        int n2;
        int width;
        int height;
        if (this.b0 == 0 && this.b_ == this.bx && this.b2 == 0 && this.b1 == this.by) {
            final Dimension size = this.getSize();
            n = 0;
            n2 = 0;
            width = size.width;
            height = size.height;
        }
        else {
            n = this.bu + this.bo * this.b0;
            n2 = this.bt + this.b2 * this.bn;
            width = this.bo * (this.b_ - this.b0);
            height = this.bn * (this.b1 - this.b2);
        }
        graphics.setColor(this.a0);
        graphics.fillRect(n, n2, width, height);
        graphics.setColor(this.a_);
        graphics2.setClip(n, n2, width, height);
    }
    
    public final void repaint() {
        if (!this.b6 && this.isShowing() && !this.cq) {
            super.repaint();
            this.b6 = true;
        }
    }
    
    public final void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public final Rectangle p(final Graphics graphics) {
        Rectangle clipBounds = graphics.getClipBounds();
        if (clipBounds == null) {
            final Dimension size = this.getSize();
            clipBounds = new Rectangle(0, 0, size.width, size.height);
        }
        return clipBounds;
    }
    
    public final synchronized void update(final Graphics graphics) {
        if (this.bv == 0 || this.bw == 0) {
            final Dimension size = this.getSize();
            this.bw = size.height;
            this.bv = size.width;
            if (this.bv == 0 || this.bw == 0) {
                return;
            }
        }
        int n = 1;
        while (this.ah) {
            this.ah = false;
            try {
                this.wait(n * 25);
            }
            catch (InterruptedException ex) {}
            if (n++ > 3) {
                this.ah = false;
            }
        }
        if (this.al == null || this.ak == null || this.bv != this.ak.width || this.bw != this.ak.height) {
            this.ak = new Dimension(this.bv, this.bw);
            this.am = this.createImage(this.bv, this.bw);
        }
        this.al = this.am.getGraphics();
        Rectangle rectangle;
        if (!this.b6) {
            this.b2 = 0;
            this.b1 = this.by;
            this.b0 = 0;
            this.b_ = this.bx;
            rectangle = this.p(graphics);
            this.al.setClip(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            this.al.setColor(this.a0);
            this.al.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            this.al.setColor(this.a_);
        }
        else {
            if (this.b2 == this.by) {
                this.b2 = this.bj;
            }
            if (this.b1 == 0) {
                this.b1 = this.bj + 1;
            }
            if (this.b_ == 0) {
                this.b_ = this.bi + 1;
            }
            if (this.b0 == this.bx) {
                this.b0 = this.bi;
            }
            this.q(this.al, graphics);
            rectangle = this.p(graphics);
        }
        for (int i = this.b2; i < this.b1; ++i) {
            final int n2 = this.bt + i * this.bn;
            final int[] array = this.av[this.at + i];
            final char[] array2 = this.aw[this.at + i];
            for (int j = this.b0; j < this.b_; ++j) {
                final int n3 = array[j];
                final int n4 = n3 & 0xFFFF;
                if (n4 != 0) {
                    final int n5 = this.bu + this.bo * j;
                    if ((n3 & 0x10) != 0x0 ^ (n3 & 0x1000) != 0x0) {
                        if ((n3 & 0x40) != 0x0) {
                            this.al.setColor(h.ay[(n3 & 0xFF0000) >>> 16]);
                        }
                        this.al.fillRect(n5, n2, this.bo, this.bn);
                        if ((n3 & 0x80) != 0x0) {
                            this.al.setColor(h.ay[(n3 & 0xFF000000) >>> 24]);
                        }
                        else {
                            this.al.setColor(this.a0);
                        }
                    }
                    else {
                        if ((n3 & 0x80) != 0x0) {
                            this.al.setColor(h.ay[(n3 & 0xFF000000) >>> 24]);
                            this.al.fillRect(n5, n2, this.bo, this.bn);
                            this.al.setColor(this.a_);
                        }
                        if ((n3 & 0x40) != 0x0) {
                            this.al.setColor(h.ay[(n3 & 0xFF0000) >>> 16]);
                        }
                    }
                    if ((n4 & 0x8000) != 0x0) {
                        if ((n3 & 0x100) != 0x0) {
                            this.m(this.al, n5, n2, this.bk, array2[j]);
                        }
                        else if ((n3 & 0x1) != 0x0) {
                            this.al.setFont(this.ai);
                            this.al.drawChars(array2, j, 1, n5, n2 + this.bk);
                            this.al.setFont(this.aj);
                        }
                        else {
                            this.al.drawChars(array2, j, 1, n5, n2 + this.bk);
                        }
                        if ((n3 & 0x4) != 0x0) {
                            this.al.drawLine(n5, n2 + this.bk, n5 + this.bo, n2 + this.bk);
                        }
                    }
                    this.al.setColor(this.a_);
                }
            }
        }
        graphics.drawImage(this.am, 0, 0, this);
        final Rectangle rectangle2 = new Rectangle(this.bu + this.bo * this.bg, this.bt + this.bh * this.bn, this.bo, this.bn);
        if (!rectangle.intersects(rectangle2)) {
            graphics.setClip(0, 0, this.bv, this.bw);
            this.o(graphics);
        }
        else {
            final Rectangle intersection = rectangle.intersection(rectangle2);
            graphics.setClip(0, 0, this.bv, this.bw);
            if (!intersection.equals(rectangle2) && !this.ag) {
                graphics.setColor(this.a0);
                graphics.fillRect(0, 0, this.bv, this.bw);
                graphics.setColor(this.a_);
                this.ag = true;
                this.update(graphics);
                this.ag = false;
                return;
            }
        }
        this.n(graphics);
        this.b6 = false;
        this.b2 = this.by;
        this.b1 = 0;
        this.b0 = this.bx;
        this.b_ = 0;
    }
    
    public final synchronized void o(final Graphics graphics) {
        if (this.b4) {
            final int n = this.bu + this.bo * this.bg;
            final int n2 = this.bt + this.bh * this.bn;
            if ((this.av[this.at + this.bh][this.bg] & 0x10) != 0x0) {
                graphics.setColor(this.a_);
            }
            else {
                graphics.setColor(this.a0);
            }
            graphics.setXORMode(this.az);
            if (this.b5) {
                graphics.drawRect(n, n2, this.bo, this.bn - 1);
            }
            else {
                graphics.fillRect(n, n2, this.bo, this.bn);
            }
            graphics.setColor(this.a_);
            graphics.setPaintMode();
            this.b4 = false;
        }
    }
    
    public final synchronized void n(final Graphics graphics) {
        if (this.an[9] && this.bi < this.bx && this.bj < this.by) {
            final int n = this.bu + this.bo * this.bi;
            final int n2 = this.bt + this.bj * this.bn;
            graphics.setColor(this.az);
            if ((this.av[this.at + this.bj][this.bi] & 0x10) != 0x0) {
                graphics.setXORMode(this.a_);
            }
            else {
                graphics.setXORMode(this.a0);
            }
            if (this.b7) {
                graphics.fillRect(n, n2, this.bo, this.bn);
                this.b5 = false;
            }
            else {
                graphics.drawRect(n, n2, this.bo, this.bn - 1);
                this.b5 = true;
            }
            graphics.setPaintMode();
            this.b4 = true;
            this.bh = this.bj;
            this.bg = this.bi;
        }
    }
    
    public final void m(final Graphics graphics, final int n, final int n2, final int n3, final char c) {
        final int n4 = n + this.bo / 2;
        final int n5 = n2 + this.bn / 2;
        final int n6 = n + this.bo;
        final int n7 = n2 + this.bn;
        switch (c) {
            case '}': {
                graphics.drawChars(new char[] { '£' }, 0, 1, n, n2 + n3);
                break;
            }
            case 'f': {
                graphics.drawChars(new char[] { '°' }, 0, 1, n, n2 + n3);
                break;
            }
            case '`': {
                final int[] array = new int[4];
                final int[] array2 = new int[4];
                array[0] = n4;
                array2[0] = n2;
                array[1] = n6;
                array2[1] = n5;
                array[2] = n4;
                array2[2] = n7;
                array[3] = n;
                array2[3] = n5;
                graphics.fillPolygon(array, array2, 4);
                break;
            }
            case 'g': {
                graphics.drawChars(new char[] { '±' }, 0, 1, n, n2 + n3);
                break;
            }
            case 'o': {
                graphics.drawLine(n, n2, n6, n2);
                break;
            }
            case 's': {
                graphics.drawLine(n, n7, n6, n7);
                break;
            }
            case 'l': {
                graphics.drawLine(n4, n7, n4, n5);
                graphics.drawLine(n4, n5, n6, n5);
                break;
            }
            case 'k': {
                graphics.drawLine(n, n5, n4, n5);
                graphics.drawLine(n4, n5, n4, n7);
                break;
            }
            case 'm': {
                graphics.drawLine(n4, n2, n4, n5);
                graphics.drawLine(n4, n5, n6, n5);
                break;
            }
            case 'j': {
                graphics.drawLine(n4, n2, n4, n5);
                graphics.drawLine(n4, n5, n, n5);
                break;
            }
            case 'q': {
                graphics.drawLine(n, n5, n6, n5);
                break;
            }
            case 'x': {
                graphics.drawLine(n4, n2, n4, n7);
                break;
            }
            case 'n': {
                graphics.drawLine(n4, n2, n4, n7);
                graphics.drawLine(n, n5, n6, n5);
                break;
            }
            case 'u': {
                graphics.drawLine(n4, n2, n4, n7);
                graphics.drawLine(n, n5, n4, n5);
                break;
            }
            case 't': {
                graphics.drawLine(n4, n2, n4, n7);
                graphics.drawLine(n4, n5, n6, n5);
                break;
            }
            case 'v': {
                graphics.drawLine(n, n5, n6, n5);
                graphics.drawLine(n4, n5, n4, n2);
                break;
            }
            case 'w': {
                graphics.drawLine(n, n5, n6, n5);
                graphics.drawLine(n4, n5, n4, n7);
                break;
            }
        }
    }
    
    static {
        ay = new Color[] { Color.black, Color.red.darker(), Color.green.darker(), Color.yellow.darker(), Color.blue.darker(), Color.magenta.darker(), Color.cyan.darker(), Color.white, Color.darkGray, Color.red, Color.green, Color.yellow, Color.blue, Color.magenta, Color.cyan, Color.white };
        ax = new String[] { "black", "red", "green", "yellow", "blue", "magenta", "cyan", "white", "i_black", "i_red", "i_green", "i_ yellow", "i_blue", "i_magenta", "i_cyan", "i_white" };
        ar = new char[512];
        aq = new int[512];
        for (int i = 0; i < 512; ++i) {
            h.ar[i] = ' ';
            h.aq[i] = 0;
        }
    }
}
