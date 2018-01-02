// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.A;

import java.awt.Color;
import java.util.StringTokenizer;
import java.awt.Font;
import java.util.Vector;
import com.cc.D.D;
import java.util.Enumeration;
import java.io.UnsupportedEncodingException;
import A.A.E;
import com.cc.D.C;
import com.cc.C.A;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;
import java.io.ByteArrayInputStream;

public class B
{
    private static final int A = 67324752;
    private byte[] B;
    
    public B(final byte[] b) {
        this.B = b;
        if (this.A()) {
            try {
                final ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(this.B));
                zipInputStream.getNextEntry();
                this.B = this.A(zipInputStream).toByteArray();
            }
            catch (IOException ex) {
                throw new RuntimeException("Can not unzip file: " + ex.getMessage());
            }
        }
    }
    
    private ByteArrayOutputStream A(final InputStream inputStream) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final byte[] array = new byte[1000];
        for (int i = inputStream.read(array); i > -1; i = inputStream.read(array)) {
            byteArrayOutputStream.write(array, 0, i);
        }
        return byteArrayOutputStream;
    }
    
    public boolean B() {
        int n = 0;
        for (int n2 = 0; n2 < this.B.length - "<?xml".length() && this.B[n2] != 60; ++n2) {
            ++n;
        }
        return new String(this.B, n, "<?xml".length()).equals("<?xml");
    }
    
    private boolean A() {
        return 0x4034B50 == (this.B[0] | this.B[1] << 8 | this.B[2] << 16 | this.B[3] << 24);
    }
    
    public void A(final A a, final com.cc.B.A a2, final C c) throws UnsupportedEncodingException {
        A.A.A a3;
        try {
            a3 = E.A(new String(this.B, "UTF8"));
        }
        catch (UnsupportedEncodingException ex) {
            a3 = E.A(new String(this.B));
        }
        final A.A.B b = a3.B();
        a.A = "UTF8";
        a.¥ = "UTF8";
        a.D = "UTF8";
        final A.A.B b2 = b.B("rectangular-puzzle");
        if (b2.A("alphabet")) {
            a2.Y = b2.H("alphabet");
        }
        if (b2.D("metadata")) {
            this.A(b2.B("metadata"), a);
        }
        if (b2.D("instructions")) {
            a.l = b2.B("instructions").A();
        }
        final A.A.B b3 = b.B("applet-settings");
        if (b3 != null) {
            this.A(a, a2, b3);
        }
        final A.A.B a4 = this.A(b2, a2);
        final A.A.B b4 = a4.B("grid");
        a2.M = this.A(a4, "box-height", a2.M);
        a2.T = this.A(a4, "box-width", a2.T);
        this.A(a2, b4, c);
        if (!a2.z) {
            this.C(a4, a2);
            this.B(a4, a2, c);
        }
        if (a2.d) {
            for (int i = 0; i < a2.A; ++i) {
                for (int j = 0; j < a2.D; ++j) {
                    a2.A(i, j, false).I = true;
                }
            }
        }
    }
    
    private void A(final A.A.B b, final A a) {
        if (b.D("creator")) {
            a.d = b.B("creator").A();
        }
        if (b.D("created")) {
            a.G = b.B("created").A();
        }
        if (b.D("title")) {
            a.v = b.B("title").A();
        }
        if (b.D("editor")) {
            a.k = b.B("editor").A();
        }
        if (b.D("rights")) {
            a.\u00d3 = b.B("rights").A();
        }
        if (b.D("copyright")) {
            a.\u00ce = b.B("copyright").A();
        }
        if (b.D("publisher")) {
            a.n = b.B("publisher").A();
        }
        if (b.D("description")) {
            a.B = b.B("description").A();
        }
    }
    
    private A.A.B A(final A.A.B b, final com.cc.B.A a) {
        if (b.D("crossword")) {
            return b.B("crossword");
        }
        if (b.D("coded")) {
            a.h = true;
            return b.B("coded");
        }
        if (b.D("sudoku")) {
            a.z = true;
            return b.B("sudoku");
        }
        if (b.D("word-search")) {
            a.d = true;
            a.N = true;
            return b.B("word-search");
        }
        throw new IllegalArgumentException("Unknown xml format!");
    }
    
    private void A(final A a, final com.cc.B.A a2, final A.A.B b) {
        a.\u00c5 = this.A(b, "width", 0);
        a.S = this.A(b, "height", 0);
        a._ = this.A(b, "scroll-height", 0);
        a.o = this.A(b, "scroll-width", 0);
        if (b.A("solve-state")) {
            a.\u00ca = b.H("solve-state");
        }
        a.p = this.A(b, "is-solving", false);
        a.\u00c4 = this.A(b, "background-color", a.\u00c4);
        a2.G = this.A(b, "show-alphabet", false);
        if (a2.G) {
            a2.i = this.A(b, "enforce-alphabet", true);
        }
        a.I = this.A(b, "title-left", a.I);
        a.Z = this.A(b, "title-height", a.Z);
        if (b.A("wrong-letter-color")) {
            a2.µ = true;
            a2.E = this.A(b.I("wrong-letter-color"));
        }
        a.µ = this.A(b, "hide-numbers", a.µ);
        a2.X = this.A(b.I("selected-cells-color"));
        a2.a = this.A(b.I("cursor-color"));
        this.D(a, b);
        this.A(a, b);
        this.C(a, b);
        this.C(a, b);
        this.B(a, b);
        if (b.D("title")) {
            a.Q = a.A(com.cc.D.A.A(b.B("title")));
        }
        if (b.D("copyright")) {
            a.z = a.A(com.cc.D.A.A(b.B("copyright")));
        }
    }
    
    private void B(final A.A.B b, final com.cc.B.A a, final C c) {
        final Enumeration g = b.G("clues");
        while (g.hasMoreElements()) {
            final A.A.B b2 = g.nextElement();
            final com.cc.D.B b3 = new com.cc.D.B();
            final int a2 = c.A(b3);
            if (a2 > 1) {
                throw new RuntimeException("Can not load more than 2 clue blocks!");
            }
            this.A(b3, b2, a, 1 - a2);
        }
    }
    
    private D A(final A.A.B m, final com.cc.B.A a, final int n) {
        final D d = new D(n, a);
        d.M = m;
        d.F = com.cc.D.A.A(m);
        if (m.A("format")) {
            d.F.A(new com.cc.D.A._A(" (" + m.H("format") + ")"));
        }
        d.D = this.A(m, "is-link", 0);
        d.F = d.F.A("\\n", false);
        if (m.A("hint-url")) {
            d.B(m.H("hint-url"));
        }
        if (m.A("number")) {
            d.E = m.H("number");
        }
        if (m.A("word")) {
            d.A(m.H("word"));
        }
        return d;
    }
    
    private void C(final A.A.B b, final com.cc.B.A a) {
        final Enumeration g = b.G("word");
        while (g.hasMoreElements()) {
            a.B(this.B(g.nextElement(), a));
        }
    }
    
    private com.cc.B.D B(final A.A.B b, final com.cc.B.A a) {
        final com.cc.B.D d = new com.cc.B.D(b.H("id"));
        d.C(this.A(b, "hidden", a.d));
        d.A(this.A(b, "keyword", false));
        if (b.A("x")) {
            this.A(a, b, d);
        }
        final Enumeration g = b.G("cells");
        while (g.hasMoreElements()) {
            this.A(a, g.nextElement(), d);
        }
        return d;
    }
    
    private void A(final com.cc.B.A a, final A.A.B b, final com.cc.B.D d) {
        final Vector b2 = this.B(b.H("x"));
        final Vector b3 = this.B(b.H("y"));
        final Enumeration<Integer> elements = b2.elements();
        if (a.d && b2.size() == b3.size()) {
            final Enumeration<Integer> elements2 = b3.elements();
            while (elements.hasMoreElements()) {
                final com.cc.B.E a2 = a.A(elements.nextElement() - 1, elements2.nextElement() - 1, true);
                if (a2 != null) {
                    d.B(a2);
                }
            }
        }
        else {
            while (elements.hasMoreElements()) {
                final Integer n = elements.nextElement();
                final Enumeration<Integer> elements3 = b3.elements();
                while (elements3.hasMoreElements()) {
                    final com.cc.B.E a3 = a.A(n - 1, elements3.nextElement() - 1, true);
                    if (a3 != null) {
                        d.B(a3);
                    }
                }
            }
        }
    }
    
    private Vector B(final String s) {
        final Vector<Integer> vector = new Vector<Integer>();
        final int index = s.indexOf(45);
        if (index > -1) {
            final int intValue = Integer.valueOf(s.substring(0, index));
            final int intValue2 = Integer.valueOf(s.substring(index + 1, s.length()));
            if (intValue <= intValue2) {
                for (int i = intValue; i <= intValue2; ++i) {
                    vector.addElement(new Integer(i));
                }
            }
            else {
                for (int j = intValue; j >= intValue2; --j) {
                    vector.addElement(new Integer(j));
                }
            }
        }
        else {
            vector.addElement(Integer.valueOf(s));
        }
        return vector;
    }
    
    private void A(final com.cc.D.B b, final A.A.B b2, final com.cc.B.A a, final int n) {
        if (b2.D("title")) {
            b.E = com.cc.D.A.A(b2.B("title"));
        }
        else {
            b.E = new com.cc.D.A("");
        }
        b.F = this.A(b2, "ordering", b.F);
        b.B = this.A(b2, "hide-numbers", b.B);
        final Enumeration g = b2.G("clue");
        final Vector vector = new Vector<D>();
        while (g.hasMoreElements()) {
            vector.addElement(this.A(g.nextElement(), a, n));
        }
        b.D = new D[vector.size()];
        final Enumeration<D> elements = vector.elements();
        int n2 = 0;
        while (elements.hasMoreElements()) {
            b.D[n2] = elements.nextElement();
            ++n2;
        }
    }
    
    private void A(final com.cc.B.A a, final A.A.B b, final C c) {
        a.A(Integer.valueOf(b.H("width")), Integer.valueOf(b.H("height")));
        a.¤ = this.A(b, "one-letter-words", a.¤);
        final A.A.B b2 = b.B("grid-look");
        if (b2.D("numbered-sides")) {
            final A.A.B b3 = b2.B("numbered-sides");
            a.F = true;
            a.I = this.A(b3.H("top"));
            a.H = this.A(b3.H("left"));
        }
        final A.A.B b4 = b2.B("arrows");
        if (b4 != null) {
            a.c = true;
            a.t = this.A(b4, "stem", a.t);
            a.W = this.A(b4, "head-width", a.W);
            a.K = this.A(b4, "head-length", a.K);
            a.S = this.A(b4, "bend-start", a.S);
            a.g = this.A(b4, "bend-end", a.g);
            a.Q = this.A(b4, "bend-side-offset", a.Q);
        }
        a.N = this.A(b2, "hide-lines", a.N);
        a.J = this.A(b2, "italian-style", a.J);
        a.r = this.A(b2, "thick-border", a.r);
        a.p = this.A(b2.I("numbering-scheme"), new String[] { "normal", "sequential", "coded", "none" }, new int[] { 0, 1, 3, 2 });
        a.L = this.A(b2, "cell-size-in-pixels", a.L);
        this.A(a, b2);
        a.q = this.A(b2, "clue-square-divider-width", a.q);
        this.B(a, b, c);
    }
    
    private void B(final com.cc.B.A a, final A.A.B b, final C c) {
        final Enumeration g = b.G("cell");
        final Vector<A.A.B> vector = new Vector<A.A.B>();
        while (g.hasMoreElements()) {
            final A.A.B b2 = g.nextElement();
            int a2;
            if (b2.A("type")) {
                a2 = this.A(b2.I("type"), new String[] { "block", "letter", "void", "clue" }, new int[] { 1, 2, 4, 8 });
            }
            else {
                a2 = 2;
            }
            final com.cc.B.E e = new com.cc.B.E(a2);
            final Vector b3 = this.B(b2.H("x"));
            final Vector b4 = this.B(b2.H("y"));
            final int n = b3.firstElement() - 1;
            final int n2 = b4.firstElement() - 1;
            final int n3 = 1 + b3.lastElement() - b3.firstElement();
            final int n4 = 1 + b4.lastElement() - b4.firstElement();
            e.B(n3);
            e.A(n4);
            this.A(e, b2, vector);
            a.A(e, n, n2);
        }
        final Enumeration<A.A.B> elements = vector.elements();
        while (elements.hasMoreElements()) {
            this.A(elements.nextElement(), a, c);
        }
    }
    
    private void A(final A.A.B b, final com.cc.B.A a, final C c) {
        final Vector b2 = this.B(b.H("x"));
        final Vector b3 = this.B(b.H("y"));
        final int n = b2.firstElement() - 1;
        final int n2 = b3.firstElement() - 1;
        final int b4 = c.B();
        final com.cc.D.B b5 = c.D()[b4];
        final Enumeration g = b.G("clue");
        while (g.hasMoreElements()) {
            final D a2 = this.A(g.nextElement(), a, b4);
            a2.L = a.A(n, n2, false);
            final D[] d = new D[b5.D.length + 1];
            for (int i = 0; i < b5.D.length; ++i) {
                d[i] = b5.D[i];
            }
            d[d.length - 1] = a2;
            b5.D = d;
        }
    }
    
    private void A(final com.cc.B.E e, final A.A.B b, final Vector vector) {
        if (b.A("solution")) {
            e.M = b.H("solution");
            e.I = this.A(b, "hint", e.I);
        }
        if (b.A("solve-state")) {
            final String a = this.A(b, "solve-status", "");
            e.A(b.H("solve-state"), "pencil".equals(a));
            if ("revealed".equals(a)) {
                e.T();
            }
        }
        if (b.A("number")) {
            e.B = b.H("number");
        }
        if (b.A("top-right-number")) {
            e.L = true;
            e.P = b.H("top-right-number");
        }
        e.K = b.A("background-shape");
        e.U = this.A(b, "background-color", e.U);
        e.O = this.A(b, "foreground-color", e.O);
        if (b.D("background-picture")) {
            final A.A.B b2 = b.B("background-picture");
            e.A(b2.B("encoded-image").A(), b2.H("format"));
            e.c = this.A(b2, "rebus", e.c);
        }
        e.N = this.A(b, "top-bar", e.N);
        e.F = this.A(b, "left-bar", e.F);
        if (b.D("arrow")) {
            final Enumeration g = b.G("arrow");
            while (g.hasMoreElements()) {
                final A.A.B b3 = g.nextElement();
                final com.cc.B.B b4 = new com.cc.B.B(this.A(b3.I("from"), new String[] { "left", "right", "top", "bottom" }, new int[] { 0, 1, 2, 3 }), this.A(b3.I("to"), new String[] { "left", "right", "top", "bottom" }, new int[] { 0, 1, 2, 3 }));
                b4.A(this.A(b3, "from-fraction", 0.5));
                b4.D = this.A(b3, "continue-word", false);
                e.A(b4);
            }
        }
        if (b.D("clue")) {
            vector.addElement(b);
        }
    }
    
    private void A(final com.cc.B.A a, final A.A.B b) {
        a._ = this.A(b, "grid-line-color", a._);
        a.w = this.A(b, "block-color", a.w);
        a.e = this.A(b, "font-color", a.e);
        a.y = this.A(b, "number-color", a.y);
        a.v = this.A(b, "pencil-color", a.v);
        a.s = this.A(b, "revealed-color", a.e);
    }
    
    private void C(final A a, final A.A.B b) {
        final A.A.B b2 = b.B("actions");
        if (b2 == null) {
            return;
        }
        final String h = b2.H("buttons-layout");
        if (h.equals("left")) {
            a.¤ = false;
        }
        else {
            if (!h.equals("below")) {
                throw new RuntimeException("Button error!");
            }
            a.¤ = true;
        }
        a.T = this.A(b2, "wide-buttons", a.T);
        a.u = this.A(b2, "graphical-buttons", a.u);
        final A.A.B b3 = b2.B("reveal-word");
        if (b3 != null) {
            a.H.F(b3.H("label"));
        }
        final A.A.B b4 = b2.B("reveal-letter");
        if (b4 != null) {
            a.H.D(b4.H("label"));
        }
        final A.A.B b5 = b2.B("check");
        if (b5 != null) {
            a.H.C(b5.H("label"));
        }
        final A.A.B b6 = b2.B("revert");
        if (b6 != null) {
            a.H.E(b6.H("label"));
        }
        final A.A.B b7 = b2.B("submit");
        if (b7 != null) {
            a.H.A(b7.H("label"));
            a.e = this.A(b7, "target-frame", (String)null);
            a.\u00c8 = this.A(b7, "target-url", (String)null);
            a.q = this.A(b7, "target-type", a.q);
        }
        final A.A.B b8 = b2.B("solution");
        if (b8 != null) {
            a.H.G(b8.H("label"));
        }
        final A.A.B b9 = b2.B("save");
        if (b9 != null) {
            a.h = this.A(b9, "target-url", (String)null);
            a.H.B(b9.H("label"));
        }
        final A.A.B b10 = b2.B("pencil");
        if (b10 != null) {
            a.H.H(b10.H("label"));
        }
    }
    
    private void B(final A a, final A.A.B b) {
        final A.A.B b2 = b.B("clues");
        if (b2 != null) {
            a.L = this.A(b2.I("layout"), new String[] { "right", "left", "below" }, new int[] { 0, 1, 2 });
            a.M = new Font(b2.H("font-family"), 0, b2.I("font-size").C());
            a.\u00cb = this.A(b2, "color", a.\u00cb);
            a.K = b2.I("gutter").C();
            a.\u00c7 = b2.I("width").C();
            a.w = this.A(b2, "selection-color", a.w);
            a.R = this.A(b2, "right-align-numbers", a.R);
            a.ª = (a.R || this.A(b2, "clue-indent-align", a.ª));
            a.i = this.A(b2, "hint-url-text", a.i);
            a.\u00cc = this.A(b2, "target-frame", (String)null);
            a.\u00d1 = this.A(b2, "url-color", a.\u00d1);
            a.O = this.A(b2, "bold-numbers", a.O);
            a.W = this.A(b2, "heading-centered", a.W);
            a.y = this.A(b2, "period-with-numbers", a.y);
            a.X = this.A(b2, "scroll-color", a.X);
            a.£ = this.A(b2, "completed-color", a.£);
        }
    }
    
    private void A(final A a, final A.A.B b) {
        final A.A.B b2 = b.B("timer");
        if (b2 == null) {
            return;
        }
        a.\u00c3 = this.A(b2, "allow-pause", a.\u00c3);
        a.V = this.A(b2, "start-on-load", a.V);
        a.F = this.A(b2, "initial-value", a.F);
        a.\u00c2 = true;
    }
    
    private void D(final A a, final A.A.B b) {
        final A.A.B b2 = b.B("completion");
        if (b2 == null) {
            return;
        }
        a.b = this.A(b2, "only-if-correct", a.b);
        a.t = this.A(b2, "friendly-submit", a.t);
        a.\u00d2 = this.A(b2, "cheating", a.\u00d2);
        a.\u00c9 = this.A(b2, "show-solution", a.\u00c9);
        a.x = this.A(b2, "target-url", (String)null);
        a.J = this.A(b2, "target-frame", (String)null);
        a.s = b2.A();
    }
    
    private boolean A(final A.A.B b, final String s, final boolean b2) {
        if (b.I(s) != null) {
            return Boolean.valueOf(b.H(s));
        }
        return b2;
    }
    
    private int A(final A.A.B b, final String s, final int n) {
        if (b.I(s) != null) {
            return Integer.parseInt(b.H(s));
        }
        return n;
    }
    
    private String[] A(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        final String[] array = new String[stringTokenizer.countTokens()];
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            array[n] = stringTokenizer.nextToken().trim();
            ++n;
        }
        return array;
    }
    
    private int A(final A.A.D d, final String[] array, final int[] array2) {
        if (array2.length != array.length) {
            throw new IllegalArgumentException("Size of enumeration does not equal to size of constants!");
        }
        final String a = d.A();
        for (int i = 0; i < array.length; ++i) {
            if (a.equals(array[i])) {
                return array2[i];
            }
        }
        throw new IllegalArgumentException("Value (" + a + ") of attribute (" + d.B() + ") is invalid!");
    }
    
    private Color A(final A.A.D d) {
        if (d.A().startsWith("#")) {
            return new Color(Integer.valueOf(d.A().substring(1, 3), 16), Integer.valueOf(d.A().substring(3, 5), 16), Integer.valueOf(d.A().substring(5, 7), 16));
        }
        final String[] a = this.A(d.A());
        if (a.length != 3) {
            throw new IllegalArgumentException("Bad format for color:" + d.B() + "\"" + d.A() + "\"");
        }
        return new Color(Integer.valueOf(a[0]), Integer.valueOf(a[1]), Integer.valueOf(a[2]));
    }
    
    private Color A(final A.A.B b, final String s, final Color color) {
        if (b.A(s)) {
            return this.A(b.I(s));
        }
        return color;
    }
    
    private String A(final A.A.B b, final String s, final String s2) {
        if (b.A(s)) {
            return b.H(s);
        }
        return s2;
    }
    
    private double A(final A.A.B b, final String s, final double n) {
        if (b.A(s)) {
            return Double.valueOf(b.H(s).replace(',', '.'));
        }
        return n;
    }
}
