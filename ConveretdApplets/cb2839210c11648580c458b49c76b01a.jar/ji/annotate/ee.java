// 
// Decompiled by Procyon v0.5.30
// 

package ji.annotate;

import ji.util.k;
import ji.awt.d4;
import java.util.GregorianCalendar;
import ji.awt.dc;
import ji.v1event.af;
import java.awt.Dimension;
import ji.v1event.a6;
import java.util.TimeZone;
import ji.secure.dh;
import ji.secure.ei;
import java.awt.Rectangle;
import ji.awt.d5;
import ji.awt.da;
import ji.image.dx;
import java.util.Calendar;
import java.awt.Color;
import java.util.Enumeration;
import ji.awt.ax;
import ji.util.i;
import ji.io.h;
import java.util.Vector;
import ji.util.d;
import ji.document.ad;
import java.awt.Component;
import ji.awt.c;

public class ee implements ef
{
    private boolean a;
    private String b;
    private String c;
    private String d;
    private int e;
    String f;
    String g;
    String h;
    String i;
    int[] j;
    private String k;
    static String l;
    static String m;
    static String n;
    private c o;
    public static final String[] p;
    static boolean q;
    
    public ee(final String s) {
        this(0, s);
    }
    
    public ee(final int e, final String k) {
        this.a = false;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = 0;
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "UTF-8";
        this.j = null;
        this.k = null;
        this.o = new c("jiAnnotateTranslator:warnings", 5);
        this.e = e;
        this.k = k;
    }
    
    public final void a(final int e, final String k) {
        this.e = e;
        this.k = k;
    }
    
    public final byte[] a(final String s, final df df, final Component component, final ad ad, final boolean b, final boolean[] array, final String s2, final boolean b2, final boolean b3, final int n, final boolean b4, final boolean[] array2, final boolean b5, final boolean b6) throws Exception {
        if (ji.util.d.bf() && b3) {
            this.a(1, s);
        }
        else {
            this.a(0, s);
        }
        byte[] array3 = null;
        switch (this.e) {
            case 0: {
                array3 = this.a(s, df, component, ad, b, array, s2, b2, n, b4, array2, b5, b6);
                break;
            }
            case 1: {
                array3 = this.a(s, df, component, ad, b, array, s2, b2, n, array2);
                break;
            }
            default: {
                array3 = this.a(s, df, component, ad, b, array, s2, b2, n, b4, array2, b5, b6);
                break;
            }
        }
        return array3;
    }
    
    private final byte[] a(final String s, final df df, final Component component, final ad ad, final boolean b, final boolean[] array, final String s2, final boolean b2, final int n, final boolean[] array2) throws Exception {
        try {
            final long currentTimeMillis = System.currentTimeMillis();
            final StringBuffer sb = new StringBuffer(16000);
            this.f = "";
            this.g = "";
            this.h = ((n == 1) ? "2" : "1");
            final ax a = df.a(component);
            final Vector<dg> vector = new Vector<dg>();
            int max = 1;
            while (a.a()) {
                final dg d = df.d(a.b());
                boolean b3 = false;
                final int i = d.i(-1);
                if (array2 != null && array2.length > i) {
                    b3 = array2[i];
                }
                if (!d.bh() && !d.aw() && d.a(ad.aa(), b3, ad)) {
                    if (!b2) {
                        max = Math.max(max, d.i(0));
                    }
                    if (d.d5() == 17) {
                        final String[] fd = d.fd();
                        for (int j = 0; j < fd.length; ++j) {
                            final String[] s3 = ji.util.d.s(fd[j], "=");
                            if (s3[0].equalsIgnoreCase("id")) {
                                this.f = s3[1];
                            }
                            if (s3[0].equalsIgnoreCase("libname")) {
                                this.g = s3[1];
                            }
                        }
                    }
                    else {
                        vector.addElement(d);
                    }
                }
            }
            if (ji.util.d.c9) {
                sb.append(String.valueOf(String.valueOf(new StringBuffer("\"1.0\" encoding=\"").append(this.i).append("\" ?>\r\n"))));
            }
            else {
                sb.append(String.valueOf(String.valueOf(new StringBuffer("<?xml version=\"1.0\" encoding=\"").append(this.i).append("\" ?>\r\n"))));
            }
            sb.append("<FnDocAnnoList ");
            this.b(sb, "xmlns:xsd", "http:www.w3.org/2001/XMLSchema");
            this.b(sb, "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            this.b(sb, "LibName", this.g);
            this.b(sb, "DocID", this.f);
            this.b(sb, "SystemType", this.h);
            sb.append(">\r\n");
            final Vector[] array3 = new Vector[max];
            this.j = new int[max];
            for (int k = 0; k < array3.length; ++k) {
                array3[k] = new Vector();
            }
            final Enumeration<dg> elements = vector.elements();
            while (elements.hasMoreElements()) {
                final dg dg = elements.nextElement();
                if (dg.d5() != 17) {
                    Vector vector2;
                    if (b2) {
                        vector2 = array3[0];
                        try {
                            this.j[0] = Math.max(this.j[0], Integer.parseInt(dg.dw()));
                        }
                        catch (NumberFormatException ex) {
                            if (dg.dw() == null) {
                                dg.r("");
                            }
                        }
                    }
                    else {
                        final int n2 = dg.i(0) - 1;
                        try {
                            this.j[n2] = Math.max(this.j[n2], Integer.parseInt(dg.dw()));
                        }
                        catch (NumberFormatException ex2) {
                            if (dg.dw() == null) {
                                dg.r("");
                            }
                        }
                        vector2 = array3[n2];
                    }
                    if (vector2 == null) {
                        continue;
                    }
                    vector2.addElement(dg);
                }
            }
            for (int l = 0; l < array3.length; ++l) {
                this.a(array3[l], sb, l + 1, b2, component, n, ad);
            }
            sb.append("</FnDocAnnoList>");
            if (ji.util.d.cs()) {
                ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("XML Serialization Time: (").append(System.currentTimeMillis() - currentTimeMillis).append(" milliseconds)"))));
            }
            if (ji.util.i.c(14)) {
                return ji.util.d.a(new String(sb), false).getBytes(s2);
            }
            return new String(sb).getBytes(s2);
        }
        finally {
            this.j = null;
        }
    }
    
    void a(final Vector vector) {
        for (int size = vector.size(), i = 0; i < size; ++i) {
            for (int j = size - 1; j > i; --j) {
                int int1;
                try {
                    final dg dg = vector.elementAt(j - 1);
                    if (dg.az() == 2) {
                        int1 = 999999;
                    }
                    else {
                        int1 = Integer.parseInt(dg.dw());
                    }
                }
                catch (NumberFormatException ex) {
                    int1 = 999999;
                }
                int int2;
                try {
                    final dg dg2 = vector.elementAt(j);
                    if (dg2.az() == 2) {
                        int2 = 999999;
                    }
                    else {
                        int2 = Integer.parseInt(dg2.dw());
                    }
                }
                catch (NumberFormatException ex2) {
                    int2 = 999999;
                }
                if (int1 > int2) {
                    final dg element = vector.elementAt(j - 1);
                    vector.setElementAt(vector.elementAt(j), j - 1);
                    vector.setElementAt(element, j);
                }
            }
        }
    }
    
    private final void a(final Vector vector, final StringBuffer sb, final int n, final boolean b, final Component component, final int n2, final ad ad) {
        sb.append("<FnPageAnnoList ");
        this.a(sb, "Page", n);
        sb.append(">\r\n");
        if (!ji.util.d.bg()) {
            this.a(vector);
            final Enumeration<dg> elements = vector.elements();
            while (elements.hasMoreElements()) {
                this.a(elements.nextElement(), sb, n, b, component, n2, ad, -1);
            }
        }
        else {
            final int[] a = this.a(vector, b, ad);
            for (int size = vector.size(), i = 0; i < size; ++i) {
                this.a(vector.elementAt(i), sb, n, b, component, n2, ad, a[i]);
            }
        }
        final int b2 = this.o.b();
        if (b2 > 0) {
            sb.append("\r\n<!-- WARNINGS -->\r\n");
            for (int j = 0; j < b2; ++j) {
                sb.append("<!-- ");
                sb.append(this.o.b(j));
                sb.append(" -->\r\n");
            }
            sb.append("<!-- END WARNINGS -->\r\n");
        }
        sb.append("</FnPageAnnoList>\r\n");
    }
    
    private final int[] a(final Vector vector, final boolean b, final ad ad) {
        final int[] b2 = this.b(vector, b, ad);
        final int[] array = b2.clone();
        final Vector<dg> vector2 = (Vector<dg>)vector.clone();
        final int size = vector.size();
        vector.removeAllElements();
        for (int n = b ? ad.j8() : 1, n2 = 0, n3 = 1; n3 <= n && n2 < size; ++n3) {
            int n4 = 1;
            for (int i = 0; i < size; ++i) {
                int n5 = -1;
                for (int j = 0; j < size; ++j) {
                    final int k = vector2.elementAt(j).i(0);
                    boolean b3 = true;
                    if (b) {
                        b3 = (n3 == k);
                    }
                    if (array[j] == n4 && b3) {
                        n5 = j;
                        ++n2;
                        break;
                    }
                }
                ++n4;
                if (n5 > -1) {
                    final int size2 = vector.size();
                    vector.addElement(vector2.elementAt(n5));
                    b2[size2] = array[n5];
                }
            }
        }
        return b2;
    }
    
    private final int[] b(final Vector vector, final boolean b, final ad ad) {
        final int n = b ? ad.j8() : 1;
        final int size = vector.size();
        final int[] array = new int[size];
        int ce = -1;
        int n2 = 0;
        int n3 = 0;
        for (int i = 0; i < size; ++i) {
            final dg dg = vector.elementAt(i);
            dg.a9(false);
            if (dg.az() != 3) {
                if (dg.ce() > ce) {
                    ce = dg.ce();
                }
                ++n3;
            }
            else {
                ++n2;
            }
        }
        final int[] array2 = new int[n];
        for (int j = 0; j < n; ++j) {
            array2[j] = 1;
        }
        int n4 = n3 + 1;
        int n5 = 0;
        int n6 = ji.util.i.c(257) ? ce : 0;
        while (this.a(n6, ce) || n5 < n2) {
            boolean b2 = false;
            for (int k = 0; k < size; ++k) {
                final dg dg2 = vector.elementAt(k);
                final int n7 = b ? dg2.i(0) : 1;
                if (!dg2.ex()) {
                    if (dg2.az() != 3) {
                        if (dg2.ce() == n6) {
                            array[k] = array2[n7 - 1];
                            if (!ji.util.i.c(257)) {
                                ++n6;
                            }
                            else {
                                --n6;
                            }
                            final int[] array3 = array2;
                            final int n8 = n7 - 1;
                            ++array3[n8];
                            dg2.a9(true);
                            b2 = true;
                            break;
                        }
                    }
                    else {
                        array[k] = n4;
                        ++n4;
                        ++n5;
                        dg2.a9(true);
                    }
                }
            }
            if (!b2) {
                if (ji.util.i.c(257)) {
                    --n6;
                }
                else {
                    ++n6;
                }
            }
        }
        return array;
    }
    
    private boolean a(final int n, final int n2) {
        if (ji.util.i.c(257)) {
            return n >= 0;
        }
        return n <= n2;
    }
    
    private Color a(final dg dg) {
        return dg.b(dg, false);
    }
    
    private Color b(final dg dg) {
        return dg.c(dg, false);
    }
    
    private boolean c(final dg dg) {
        return dg.a(dg, false);
    }
    
    private final void a(final dg dg, final StringBuffer sb, final int n, final boolean b, final Component component, final int n2, final ad ad, final int n3) {
        final boolean am = ji.util.d.am(n2);
        final boolean al = ji.util.d.al(n2);
        if (al) {
            if (dg.d5() == 17) {
                return;
            }
        }
        else if (dg.d5() == 17 || dg.d5() == 16 || dg.d5() == 14 || dg.d5() == 4 || dg.d5() == 6 || dg.d5() == 2 || dg.d5() == 3 || dg.d5() == 1 || dg.d5() == 11 || dg.d5() == 12 || dg.d5() == 9) {
            return;
        }
        if (dg.az() == 2 && dg.bs()) {
            ji.io.h.a(this.k, "Annotation not added to list, not a real annotation: ".concat(String.valueOf(String.valueOf(dg))));
            return;
        }
        if (dg.az() == 0) {
            if (!ji.util.d.am(n2) || !ji.util.d.bi()) {
                return;
            }
            if (dg.cg() == n3) {
                return;
            }
            dg.d(1);
        }
        double bb = dg.bb();
        double bc = dg.bc();
        final dx bg = ad.bg(dg.i(n));
        if (bg != null && bg.c()) {
            bb *= bg.d();
            bc *= bg.d();
        }
        if (bb == dg.fp) {
            ji.io.h.a(this.k, "*** Annotation not saved because page has not been viewed: ".concat(String.valueOf(String.valueOf(dg))));
            dg.d(0);
            return;
        }
        final int length = sb.length();
        try {
            sb.append("<FnAnno ");
            final String[] a9 = dg.a9();
            final c c = new c("jiAnnotationTranslator:customProperties", 50);
            dg.a8();
            for (int length2 = a9.length, i = 0; i < length2; ++i) {
                final String[] s = ji.util.d.s(a9[i], "=");
                if (s[0].equalsIgnoreCase("F_CREATOR")) {
                    if (ji.util.i.c(230) && !ji.util.d.by(s[1])) {
                        this.a(c, s[0].toUpperCase(), s[1]);
                    }
                }
                else {
                    this.a(c, s[0].toUpperCase(), s[1]);
                }
            }
            c.a("F_ORDINAL");
            if (ji.util.d.bg()) {
                this.a(c, "F_ORDINAL", "".concat(String.valueOf(String.valueOf(n3))));
            }
            String s2 = null;
            switch (dg.az()) {
                case 0: {
                    s2 = "none";
                    break;
                }
                case 1: {
                    s2 = "change";
                    break;
                }
                case 2: {
                    s2 = "add";
                    break;
                }
                case 3: {
                    s2 = "remove";
                    break;
                }
                default: {
                    s2 = "error";
                    break;
                }
            }
            this.b(sb, "STATE", s2);
            sb.append(">\r\n");
            if (dg.az() == 2) {
                dg.r(Integer.toString(++this.j[n - 1]));
            }
            final int d5 = dg.d5();
            sb.append("\t<PropDesc ");
            if (ji.util.d.am(n2)) {
                final String dx = dg.dx();
                if (dx != null) {
                    this.a(c, "F_ANNOTATEDID", dx);
                }
                else {
                    this.a(c, "F_ANNOTATEDID", "");
                }
            }
            else {
                this.a(c, "F_ANNOTATEDID", this.f);
            }
            if (dg.b0() >= 0) {
                this.a(c, "F_PAGENUMBER", Integer.toString(dg.b0()));
                this.a(c, "F_MULTIPAGETIFFPAGENUMBER", "0");
            }
            else if (b) {
                this.a(c, "F_PAGENUMBER", "1");
                this.a(c, "F_MULTIPAGETIFFPAGENUMBER", Integer.toString(dg.i(0)));
            }
            else {
                this.a(c, "F_PAGENUMBER", Integer.toString(n));
                this.a(c, "F_MULTIPAGETIFFPAGENUMBER", "0");
            }
            this.a(c, "F_ID", dg.dw());
            this.a(c, "F_NAME", String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.f))).append('-').append(Integer.toString(n)).append('-').append(dg.dw()))));
            this.a(c, "F_ENTRYDATE", this.a((Calendar)dg.di()));
            this.a(c, "F_MODIFYDATE", this.a((Calendar)dg.dr()));
            if (ji.util.i.c(230) && dg.dh()) {
                this.a(c, "F_CREATOR", dg.dg());
            }
            final boolean c2 = ji.util.i.c(247);
            int d6 = 1;
            if (bg != null && bg.c()) {
                d6 = bg.d();
            }
            boolean b2 = false;
            switch (d5) {
                case 5: {
                    this.a(c, vf.c);
                    this.a(c, "F_ARROWHEAD_SIZE", Integer.toString(dg.eb()));
                    this.a(c, "F_LINE_COLOR", this.a(this.a(dg)));
                    if (ji.util.i.c(204)) {
                        this.a(c, "F_LINE_WIDTH", Integer.toString(Math.max(dg.a(bg, bb), 1)));
                    }
                    else if (am) {
                        this.a(c, "F_LINE_WIDTH", Integer.toString(Math.max(dg.a(bg, bb), 1)));
                    }
                    else {
                        this.a(c, "F_LINE_WIDTH", Integer.toString(Math.max((int)(dg.a(bg, bb) / 2.0), 1)));
                    }
                    if (dg.az() != 0) {
                        c.a("F_LINE_START_X");
                        c.a("F_LINE_START_Y");
                        c.a("F_LINE_END_X");
                        c.a("F_LINE_END_Y");
                        c.a("F_LEFT");
                        c.a("F_TOP");
                        c.a("F_WIDTH");
                        c.a("F_HEIGHT");
                        this.a(c, "F_LINE_START_X", this.a(dg.fn().a, bb));
                        this.a(c, "F_LINE_START_Y", this.a(dg.fn().b, bc));
                        this.a(c, "F_LINE_END_X", this.a(dg.fo().a, bb));
                        this.a(c, "F_LINE_END_Y", this.a(dg.fo().b, bc));
                        final da fe = dg.fe();
                        this.a(c, "F_LEFT", this.a(fe.a, bb));
                        this.a(c, "F_TOP", this.a(fe.b, bc));
                        this.a(c, "F_WIDTH", this.a(fe.c, bb));
                        this.a(c, "F_HEIGHT", this.a(fe.d, bc));
                        break;
                    }
                    break;
                }
                case 9: {
                    if (!al) {
                        break;
                    }
                    if (dg.ev().startsWith("image")) {
                        b2 = true;
                        this.a(c, "v1-Image Stamp");
                        this.a(c, "F_ROTATION", Integer.toString(dg.b3()));
                        if (dg.b7()) {
                            c.a("F_SCALE", String.valueOf(dg.cb()));
                            if (dg.az() != 0) {
                                c.a("F_LEFT");
                                c.a("F_TOP");
                                final d5 fn = dg.fn();
                                this.a(c, "F_LEFT", this.a(fn.a, bb));
                                this.a(c, "F_TOP", this.a(fn.b, bc));
                            }
                        }
                        else if (dg.az() != 0) {
                            c.a("F_LEFT");
                            c.a("F_TOP");
                            c.a("F_WIDTH");
                            c.a("F_HEIGHT");
                            final Rectangle a10 = dg.bf(false).a();
                            this.a(c, "F_LEFT", this.a(a10.x, bb));
                            this.a(c, "F_TOP", this.a(a10.y, bc));
                            this.a(c, "F_WIDTH", this.a(a10.width, bb));
                            this.a(c, "F_HEIGHT", this.a(a10.height, bc));
                        }
                    }
                    this.a(c, "F_FONT_STRIKETHROUGH", dg.i() ? "true" : "false");
                    if (ji.util.i.c(247)) {
                        this.a(c, "F_FONT_UNDERLINE", dg.j() ? "true" : "false");
                    }
                    if (ji.util.i.c(247)) {
                        this.a(c, "F_FONT_ITALIC", dg.c4() ? "true" : "false");
                    }
                    try {
                        if (c2) {
                            if (dg.cu() != null && dg.c2() != null) {
                                this.a(c, "F_FONT_SIZE", Integer.toString((int)Math.round((int)(dg.c6() * dg.c2().getSize() / ji.util.d.a() / d6) * 73.0 / dg.bb())));
                            }
                            if (dg.a0() != null) {
                                this.a(c, "F_FONT_SOURCE", "".concat(String.valueOf(String.valueOf(dg.a0()))));
                            }
                            final String c3 = dg.c1();
                            String s3;
                            if (c3 != null) {
                                s3 = c3;
                            }
                            else if (dg.c3() != null) {
                                s3 = dg.c3();
                            }
                            else {
                                s3 = dg.cu().c;
                            }
                            final int index = s3.indexOf(44);
                            String bc2 = null;
                            if (index > 0) {
                                bc2 = ji.util.d.bc(s3.substring(index + 1));
                                s3 = s3.substring(0, index);
                            }
                            this.a(c, "F_FONT_NAME", s3);
                            if (bc2 != null) {
                                this.a(c, "F_FONT_BOLD", ((ji.util.d.c(bc2, 0) & 0x1) == 0x1) ? "true" : "false");
                            }
                        }
                    }
                    catch (Exception ex2) {}
                    break;
                }
                case 7: {
                    boolean b3 = false;
                    if (!dg.ay()) {
                        if (dg.d4() == 9 || dg.ax()) {
                            b3 = true;
                            this.a(c, "F_CLASSNAME", "Stamp");
                        }
                        else {
                            this.a(c, "F_CLASSNAME", "Text");
                        }
                    }
                    else {
                        String s4 = (String)c.d("F_CLASSNAME");
                        if (s4 == null) {
                            s4 = "text";
                        }
                        if (s4.equalsIgnoreCase("stamp") || dg.ax()) {
                            b3 = true;
                        }
                    }
                    if (b3) {
                        this.a(c, "F_CLASSID", vf.h.a);
                        this.a(c, "F_ROTATION", Integer.toString(dg.b4()));
                        if (ji.util.d.ao(n2)) {
                            this.a(c, "F_IMAGEROTATION", Integer.toString(dg.b3()));
                        }
                    }
                    else {
                        this.a(c, "F_CLASSID", vf.d.a);
                        if (ji.util.d.ao(n2)) {
                            this.a(c, "F_ROTATION", Integer.toString(dg.b4()));
                            this.a(c, "F_IMAGEROTATION", Integer.toString(dg.b3()));
                        }
                    }
                    this.a(c, "F_HASBORDER", dg.d7() ? "true" : "false");
                    this.a(c, "F_BORDER_WIDTH", Integer.toString(dg.ea()));
                    this.a(c, "F_BACKCOLOR", this.a(this.b(dg)));
                    this.a(c, "F_FORECOLOR", this.a(this.a(dg)));
                    this.a(c, "F_FONT_STRIKETHROUGH", dg.i() ? "true" : "false");
                    if (ji.util.i.c(247)) {
                        this.a(c, "F_FONT_UNDERLINE", dg.j() ? "true" : "false");
                    }
                    if (ji.util.i.c(247)) {
                        this.a(c, "F_FONT_ITALIC", dg.c4() ? "true" : "false");
                    }
                    if (ji.util.i.c(150) && dg.gf() == 1) {
                        this.a(c, "F_TEXT_ALIGNMENT", "RIGHT");
                    }
                    if (this.c(dg) || dg.dz()) {
                        this.a(c, "F_TEXT_BACKMODE", "1");
                    }
                    else {
                        this.a(c, "F_TEXT_BACKMODE", "2");
                    }
                    try {
                        boolean b4 = true;
                        if (c2) {
                            if (dg.cu() != null && dg.c2() != null) {
                                b4 = false;
                                this.a(c, "F_FONT_SIZE", Integer.toString((int)Math.round((int)(dg.c6() * dg.c2().getSize() / ji.util.d.a() / d6) * 73.0 / dg.bb())));
                            }
                            if (dg.a0() != null) {
                                this.a(c, "F_FONT_SOURCE", "".concat(String.valueOf(String.valueOf(dg.a0()))));
                            }
                        }
                        if (b4) {
                            this.a(c, "F_FONT_SIZE", Integer.toString(dg.ed()));
                        }
                        if (c2) {
                            final String c4 = dg.c1();
                            String s5;
                            if (c4 != null) {
                                s5 = c4;
                            }
                            else if (dg.c3() != null) {
                                s5 = dg.c3();
                            }
                            else {
                                s5 = dg.cu().c;
                            }
                            final int index2 = s5.indexOf(44);
                            String bc3 = null;
                            if (index2 > 0) {
                                bc3 = ji.util.d.bc(s5.substring(index2 + 1));
                                s5 = s5.substring(0, index2);
                            }
                            this.a(c, "F_FONT_NAME", s5);
                            if (bc3 != null) {
                                this.a(c, "F_FONT_BOLD", ((ji.util.d.c(bc3, 0) & 0x1) == 0x1) ? "true" : "false");
                            }
                        }
                        else {
                            this.a(c, "F_FONT_NAME", dg.cu().c);
                        }
                    }
                    catch (Exception ex3) {}
                    if (dg.az() != 0) {
                        c.a("F_LEFT");
                        c.a("F_TOP");
                        c.a("F_WIDTH");
                        c.a("F_HEIGHT");
                        Rectangle rectangle = dg.bf(false).a();
                        if (dg.at() && dg.aj() != null) {
                            rectangle = dg.aj().a();
                        }
                        this.a(c, "F_LEFT", this.a(rectangle.x, bb));
                        this.a(c, "F_TOP", this.a(rectangle.y, bc));
                        this.a(c, "F_WIDTH", this.a(rectangle.width, bb));
                        this.a(c, "F_HEIGHT", this.a(rectangle.height, bc));
                        break;
                    }
                    break;
                }
                case 3:
                case 6:
                case 12:
                case 14: {
                    if (!al) {
                        break;
                    }
                    if (d5 == 6) {
                        this.a(c, "v1-Open Polygon");
                    }
                    else if (d5 == 14) {
                        this.a(c, "v1-Highlight Polygon");
                    }
                    else if (d5 == 3) {
                        this.a(c, "v1-Polygon");
                    }
                    else if (d5 == 12) {
                        this.a(c, "v1-Redaction Polygon");
                    }
                    if (dg.ei() && this.b(dg) != null) {
                        this.a(c, "F_BRUSHCOLOR", this.a(this.b(dg)));
                    }
                    this.a(c, "F_LINE_WIDTH", Integer.toString(Math.max(dg.a(bg, bb), 0)));
                    this.a(c, "F_LINE_COLOR", this.a(this.a(dg)));
                    if (this.c(dg)) {
                        this.a(c, "F_TEXT_BACKMODE", "1");
                    }
                    else {
                        this.a(c, "F_TEXT_BACKMODE", "2");
                    }
                    if (dg.az() != 0) {
                        c.a("F_LEFT");
                        c.a("F_TOP");
                        c.a("F_WIDTH");
                        c.a("F_HEIGHT");
                        final da fv = dg.fv();
                        this.a(c, "F_LEFT", String.valueOf(bg.c(fv.a)));
                        this.a(c, "F_TOP", String.valueOf(bg.c(fv.b)));
                        this.a(c, "F_WIDTH", String.valueOf(bg.c(fv.c)));
                        this.a(c, "F_HEIGHT", String.valueOf(bg.c(fv.d)));
                        break;
                    }
                    break;
                }
                case 13: {
                    this.a(c, vf.e);
                    if (this.b(dg) != null) {
                        this.a(c, "F_BRUSHCOLOR", this.a(this.b(dg)));
                    }
                    if (am) {
                        if (this.c(dg) || dg.dz()) {
                            this.a(c, "F_TEXT_BACKMODE", "1");
                        }
                        else {
                            this.a(c, "F_TEXT_BACKMODE", "2");
                        }
                        this.a(c, "F_LINE_WIDTH", Integer.toString(Math.max(dg.a(bg, bb), 0)));
                        this.a(c, "F_LINE_COLOR", this.a(this.a(dg)));
                    }
                    if (dg.az() != 0) {
                        c.a("F_LEFT");
                        c.a("F_TOP");
                        c.a("F_WIDTH");
                        c.a("F_HEIGHT");
                        final da ff = dg.ff();
                        this.a(c, "F_LEFT", this.a(ff.a, bb));
                        this.a(c, "F_TOP", this.a(ff.b, bc));
                        this.a(c, "F_WIDTH", this.a(ff.c, bb));
                        this.a(c, "F_HEIGHT", this.a(ff.d, bc));
                        break;
                    }
                    break;
                }
                case 1:
                case 2:
                case 11: {
                    if (!al) {
                        break;
                    }
                    if (d5 == 1) {
                        this.a(c, "v1-Rectangle");
                    }
                    else if (d5 == 2) {
                        this.a(c, "v1-Oval");
                    }
                    else if (d5 == 11) {
                        this.a(c, "v1-Redaction");
                    }
                    if (dg.ei() && this.b(dg) != null) {
                        this.a(c, "F_BRUSHCOLOR", this.a(this.b(dg)));
                    }
                    this.a(c, "F_LINE_COLOR", this.a(this.a(dg)));
                    this.a(c, "F_LINE_WIDTH", Integer.toString(Math.max(dg.a(bg, bb), 0)));
                    if (this.c(dg)) {
                        this.a(c, "F_TEXT_BACKMODE", "1");
                    }
                    else {
                        this.a(c, "F_TEXT_BACKMODE", "2");
                    }
                    if (dg.az() == 0) {
                        break;
                    }
                    c.a("F_LEFT");
                    c.a("F_TOP");
                    c.a("F_WIDTH");
                    c.a("F_HEIGHT");
                    final da ff2 = dg.ff();
                    this.a(c, "F_LEFT", this.a(ff2.a, bb));
                    this.a(c, "F_TOP", this.a(ff2.b, bc));
                    if (d5 == 2) {
                        this.a(c, "F_WIDTH", this.a(dg.fk(), bb));
                        this.a(c, "F_HEIGHT", this.a(dg.fl(), bc));
                        break;
                    }
                    this.a(c, "F_WIDTH", this.a(ff2.c, bb));
                    this.a(c, "F_HEIGHT", this.a(ff2.d, bc));
                    break;
                }
                case 8: {
                    if (al) {
                        final int c5 = dg.bp().c();
                        if (c5 != 3) {
                            this.a(c, "v1-Note");
                            c.a("F_VIEWOPTION", String.valueOf(c5));
                        }
                        else {
                            this.a(c, vf.f);
                        }
                    }
                    else {
                        this.a(c, vf.f);
                    }
                    if (am) {
                        this.a(c, "F_FORECOLOR", this.a(this.b(dg)));
                    }
                    else {
                        this.a(c, "F_FORECOLOR", Integer.toString(65535));
                    }
                    if (dg.az() != 0) {
                        c.a("F_LEFT");
                        c.a("F_TOP");
                        c.a("F_WIDTH");
                        c.a("F_HEIGHT");
                        final da ff3 = dg.ff();
                        this.a(c, "F_LEFT", this.a(ff3.a, bb));
                        this.a(c, "F_TOP", this.a(ff3.b, bc));
                        this.a(c, "F_WIDTH", this.a(ff3.c, bb));
                        this.a(c, "F_HEIGHT", this.a(ff3.d, bc));
                        break;
                    }
                    break;
                }
                case 4: {
                    if (!al) {
                        break;
                    }
                    this.a(c, "v1-Line");
                    this.a(c, "F_LINE_COLOR", this.a(this.a(dg)));
                    this.a(c, "F_LINE_WIDTH", Integer.toString(Math.max(dg.a(bg, bb), 1)));
                    if (dg.az() != 0) {
                        c.a("F_LINE_START_X");
                        c.a("F_LINE_START_Y");
                        c.a("F_LINE_END_X");
                        c.a("F_LINE_END_Y");
                        c.a("F_LEFT");
                        c.a("F_TOP");
                        c.a("F_WIDTH");
                        c.a("F_HEIGHT");
                        this.a(c, "F_LINE_START_X", this.a(dg.fn().a, bb));
                        this.a(c, "F_LINE_START_Y", this.a(dg.fn().b, bc));
                        this.a(c, "F_LINE_END_X", this.a(dg.fo().a, bb));
                        this.a(c, "F_LINE_END_Y", this.a(dg.fo().b, bc));
                        final da fe2 = dg.fe();
                        this.a(c, "F_LEFT", this.a(fe2.a, bb));
                        this.a(c, "F_TOP", this.a(fe2.b, bc));
                        this.a(c, "F_WIDTH", this.a(fe2.c, bb));
                        this.a(c, "F_HEIGHT", this.a(fe2.d, bc));
                        break;
                    }
                    break;
                }
                case 10: {
                    this.a(c, vf.g);
                    this.a(c, "F_LINE_COLOR", this.a(this.a(dg)));
                    if (ji.util.i.c(204)) {
                        this.a(c, "F_LINE_WIDTH", Integer.toString(Math.max(dg.a(bg, bb), 1)));
                    }
                    else if (am) {
                        this.a(c, "F_LINE_WIDTH", Integer.toString(Math.max(dg.a(bg, bb), 1)));
                    }
                    else {
                        this.a(c, "F_LINE_WIDTH", Integer.toString(Math.max((int)(dg.a(bg, bb) / 2.0), 1)));
                    }
                    if (dg.az() != 0) {
                        c.a("F_LEFT");
                        c.a("F_TOP");
                        c.a("F_WIDTH");
                        c.a("F_HEIGHT");
                        final da fe3 = dg.fe();
                        this.a(c, "F_LEFT", this.a(fe3.a, bb));
                        this.a(c, "F_TOP", this.a(fe3.b, bc));
                        this.a(c, "F_WIDTH", this.a(fe3.c, bb));
                        this.a(c, "F_HEIGHT", this.a(fe3.d, bc));
                        break;
                    }
                    break;
                }
            }
            if (!dg.ay()) {
                this.a(dg, c);
            }
            this.a(sb, c);
            sb.append(">\r\n");
            sb.append("\t\t<F_CUSTOM_BYTES>");
            if (al) {
                this.a(sb, dg, ad);
            }
            sb.append("</F_CUSTOM_BYTES>\r\n");
            switch (d5) {
                case 5: {
                    sb.append("\t\t<F_POINTS/>\r\n");
                    sb.append("\t\t<F_TEXT/>\r\n");
                    break;
                }
                case 9: {
                    if (!b2) {
                        break;
                    }
                    sb.append("\t\t<F_POINTS/>\r\n");
                    sb.append("\t\t<F_TEXT Encoding=\"unicode\">");
                    String s6 = dg.ev();
                    final int index3 = s6.toLowerCase().indexOf("id=");
                    if (index3 > -1) {
                        final int index4 = s6.indexOf(38, index3 + 3);
                        if (index4 != -1) {
                            final String substring = s6.substring(index3 + 3, index4);
                            final int lastIndex = s6.lastIndexOf(60);
                            String substring2 = "";
                            if (lastIndex > -1) {
                                substring2 = s6.substring(lastIndex);
                            }
                            s6 = String.valueOf(String.valueOf(substring)).concat(String.valueOf(String.valueOf(substring2)));
                        }
                    }
                    sb.append(ji.util.d.a8(ji.util.d.a6(s6)));
                    sb.append("</F_TEXT>\r\n");
                    break;
                }
                case 7: {
                    sb.append("\t\t<F_POINTS/>\r\n");
                    sb.append("\t\t<F_TEXT Encoding=\"unicode\">");
                    if (ji.util.d.cs()) {
                        ji.io.h.d(this.k, "XML Translation: Text1 = ".concat(String.valueOf(String.valueOf(dg.ev()))));
                        ji.io.h.d(this.k, "XML Translation: Text1 Codes = ".concat(String.valueOf(String.valueOf(ji.util.d.a7(dg.ev())))));
                        ji.io.h.d(this.k, "XML Translation: Text2 = ".concat(String.valueOf(String.valueOf(ji.util.d.a6(dg.ev())))));
                        ji.io.h.d(this.k, "XML Translation: Text2 Codes = ".concat(String.valueOf(String.valueOf(ji.util.d.a7(ji.util.d.a6(dg.ev()))))));
                        ji.io.h.d(this.k, "XML Translation: Text3 = ".concat(String.valueOf(String.valueOf(ji.util.d.a8(ji.util.d.a6(dg.ev()))))));
                        ji.io.h.d(this.k, "XML Translation: Text3 Codes = ".concat(String.valueOf(String.valueOf(ji.util.d.a7(ji.util.d.a8(ji.util.d.a6(dg.ev())))))));
                    }
                    sb.append(ji.util.d.a8(ji.util.d.a6(dg.ev())));
                    sb.append("</F_TEXT>\r\n");
                    break;
                }
                case 1:
                case 2:
                case 4:
                case 11:
                case 13: {
                    if (al) {
                        sb.append("\t\t<F_POINTS/>\r\n");
                        sb.append("\t\t<F_TEXT/>\r\n");
                        break;
                    }
                    break;
                }
                case 8: {
                    sb.append("\t\t<F_POINTS/>\r\n");
                    sb.append("\t\t<F_TEXT Encoding=\"unicode\">");
                    sb.append(ji.util.d.a8(ji.util.d.a6(dg.ev())));
                    sb.append("</F_TEXT>\r\n");
                    break;
                }
                case 3:
                case 6:
                case 12:
                case 14: {
                    if (!al) {
                        break;
                    }
                }
                case 10: {
                    final da fv2 = dg.fv();
                    sb.append("\t\t<F_POINTS>");
                    final double n4 = fv2.c / 256;
                    final double n5 = fv2.d / 256;
                    final d5[] e = dg.fm().e();
                    if (e.length == 0) {
                        sb.append("</F_POINTS>\r\n");
                        break;
                    }
                    final d5 d7 = e[0];
                    if (d5 == 10) {
                        this.a(d7, fv2, sb, n4, n5, true);
                    }
                    else {
                        this.a(bg.a(d7), sb);
                    }
                    int n6 = ji.util.d.az();
                    if (n6 < 0) {
                        n6 = e.length;
                    }
                    for (int n7 = 1; n7 < e.length && n7 < n6; ++n7) {
                        final d5 d8 = e[n7];
                        if (d5 == 10) {
                            this.a(d8, fv2, sb, n4, n5, false);
                        }
                        else {
                            this.a(bg.a(d8), sb);
                        }
                    }
                    sb.append("</F_POINTS>\r\n");
                    sb.append("\t\t<F_TEXT/>\r\n");
                    break;
                }
            }
            sb.append("\t</PropDesc>\r\n");
            this.a(sb, dg, n2);
            sb.append("</FnAnno>\r\n");
        }
        catch (Exception ex) {
            ji.io.h.d(this.k, "jiAnnotateTranslator:getFnXmlItem");
            ex.printStackTrace();
            ji.io.h.d(this.k, "jiAnnotateTranslator - failed XML: ".concat(String.valueOf(String.valueOf(new String(sb).substring(length)))));
            sb.setLength(length);
        }
    }
    
    private void a(final d5 d5, final StringBuffer sb) throws Exception {
        sb.append(' ');
        sb.append(d5.a);
        sb.append(' ');
        sb.append(d5.b);
    }
    
    private void a(final d5 d5, final da da, final StringBuffer sb, final double n, final double n2, final boolean b) throws Exception {
        final int n3 = (int)(d5.a - da.a);
        final int n4 = (int)(d5.b - da.b);
        final int n5 = (int)Math.round(n3 / n);
        final int n6 = (int)Math.round(n4 / n2);
        if (!b) {
            sb.append(' ');
        }
        sb.append((n5 > 255) ? 255 : ((n5 < 0) ? 0 : n5));
        sb.append(' ');
        sb.append((n6 > 255) ? 255 : ((n6 < 0) ? 0 : n6));
    }
    
    private final void a(final c c, final String s, final String s2) {
        if (c != null) {
            if (c.d(s) != null) {
                c.a(s);
            }
            c.a(s, s2);
        }
    }
    
    private final void a(final c c, final vf vf) {
        if (c != null) {
            if (c.d(vf.a) != null) {
                c.a(vf.a);
            }
            c.a("F_CLASSID", vf.a);
            if (c.d(vf.b) != null) {
                c.a(vf.b);
            }
            c.a("F_CLASSNAME", vf.b);
        }
    }
    
    private final void a(final c c, final String s) {
        this.a(c, vf.i);
        this.a(c, "F_SUBCLASS", s);
    }
    
    private final void b(final c c, final String s, final String s2) {
        if (c != null && c.d(s) == null) {
            c.a(s, s2);
        }
    }
    
    void a(final dg dg, final c c) {
        switch (dg.d5()) {
            case 5: {
                this.b(c, "F_LINE_BACKMODE", "2");
                this.b(c, "F_LINE_STYLE", "0");
                break;
            }
            case 10: {
                this.b(c, "F_LINE_BACKMODE", "2");
                this.b(c, "F_LINE_STYLE", "0");
                break;
            }
            case 7: {
                this.b(c, "F_BORDER_BACKMODE", "2");
                this.b(c, "F_BORDER_COLOR", this.a(this.a(dg)));
                this.b(c, "F_BORDER_STYLE", "0");
                if (!ji.util.i.c(247)) {
                    this.b(c, "F_FONT_BOLD", "true");
                }
                this.b(c, "F_FONT_ITALIC", "false");
                this.b(c, "F_FONT_NAME", "Arial");
                this.b(c, "F_FONT_UNDERLINE", "false");
                break;
            }
        }
    }
    
    void a(final df df, final int n) {
        df.l();
        final ax a = df.a((Component)null);
        while (a.a()) {
            final dg b = df.b(a.b());
            if (b.bs()) {
                ji.io.h.a(this.k, "No postprocessing of annotation: ".concat(String.valueOf(String.valueOf(b))));
            }
            else {
                switch (b.az()) {
                    case 1:
                    case 2: {
                        b.d(0);
                        break;
                    }
                }
                final dh bp = b.bp();
                if (bp == null || n != 1) {
                    continue;
                }
                final c b2 = bp.b();
                final int b3 = b2.b();
                final Vector vector = new Vector<ei>();
                for (int i = 0; i < b3; ++i) {
                    final ei ei = (ei)b2.b(i);
                    switch (ei.k) {
                        case 1:
                        case 2: {
                            ei.k = 0;
                            break;
                        }
                        case 3: {
                            vector.addElement(ei);
                            break;
                        }
                    }
                }
                for (int size = vector.size(), j = 0; j < size; ++j) {
                    b2.b(vector.elementAt(j));
                }
            }
        }
    }
    
    void a(final StringBuffer sb, final c c) {
        final ax d = c.d();
        while (d.a()) {
            final String b = d.b();
            this.b(sb, b, c.d(b).toString());
        }
    }
    
    String a(final long n, final double n2) {
        double n3 = n / n2;
        if (Double.isInfinite(n3) || Double.isNaN(n3)) {
            this.o.c(String.valueOf(String.valueOf(new StringBuffer("Floating Point Math Error: (").append(n).append("/").append(n2).append(")"))));
        }
        if (n3 < 0.0) {
            n3 = 0.0;
        }
        ji.io.h.a(this.k, String.valueOf(String.valueOf(new StringBuffer("Converting pixels ").append(n).append(" with res ").append(n2).append(" to ").append(n3).append(" inches"))));
        return Double.toString(n3);
    }
    
    void a(final StringBuffer sb, final dg dg, final int n) {
        if (dg.bp() == null) {
            return;
        }
        if (ji.util.d.am(n)) {
            if (ji.util.d.cs()) {
                ji.io.h.d(this.k, "Generating CE Security");
            }
            this.b(sb, dg);
        }
        else if (n == 1) {
            sb.append("\t<security>\r\n");
            if (ji.util.d.cs()) {
                ji.io.h.d(this.k, "Generating CS Security");
            }
            this.c(sb, dg);
            sb.append("\t</security>\r\n");
        }
        else {
            sb.append("\t<security>\r\n");
            if (ji.util.d.cs()) {
                ji.io.h.d(this.k, "Generating IS Security");
            }
            this.a(sb, dg);
            sb.append("\t</security>\r\n");
        }
    }
    
    void a(final StringBuffer sb, final dg dg) {
        final dh bp = dg.bp();
        sb.append("\t\t<securityobject ");
        this.b(sb, "libraryid", this.g);
        this.b(sb, "systemtype", "idmis");
        this.b(sb, "objectid", dg.dw());
        this.b(sb, "objecttype", "annotation");
        this.b(sb, "clientpermission", bp.j() ? "change" : "none");
        sb.append(">\r\n");
        final ei ei = (ei)bp.a(0).b(0);
        final ei ei2 = (ei)bp.a(1).b(0);
        final ei ei3 = (ei)bp.a(2).b(0);
        if (ei != null) {
            sb.append("\t\t\t<permission ");
            this.a(sb, "id", 1);
            this.b(sb, "name", ei.h);
            if (ei.i == 0) {
                this.b(sb, "type", "user");
            }
            else {
                this.b(sb, "type", "group");
            }
            this.b(sb, "level", "read");
            switch (ei.k) {
                case 1: {
                    this.b(sb, "updatetype", ee.p[1]);
                    break;
                }
                default: {
                    this.b(sb, "updatetype", ee.p[0]);
                    break;
                }
            }
            sb.append("/>\r\n");
        }
        if (ei2 != null) {
            sb.append("\t\t\t<permission ");
            this.a(sb, "id", 2);
            this.b(sb, "name", ei2.h);
            if (ei2.i == 0) {
                this.b(sb, "type", "user");
            }
            else {
                this.b(sb, "type", "group");
            }
            this.b(sb, "level", "write");
            switch (ei2.k) {
                case 1: {
                    this.b(sb, "updatetype", ee.p[1]);
                    break;
                }
                default: {
                    this.b(sb, "updatetype", ee.p[0]);
                    break;
                }
            }
            sb.append("/>\r\n");
        }
        if (ei3 != null) {
            sb.append("\t\t\t<permission ");
            this.a(sb, "id", 3);
            this.b(sb, "name", ei3.h);
            if (ei3.i == 0) {
                this.b(sb, "type", "user");
            }
            else {
                this.b(sb, "type", "group");
            }
            this.b(sb, "level", "append");
            switch (ei3.k) {
                case 1: {
                    this.b(sb, "updatetype", ee.p[1]);
                    break;
                }
                default: {
                    this.b(sb, "updatetype", ee.p[0]);
                    break;
                }
            }
            sb.append("/>\r\n");
        }
        sb.append("\t\t</securityobject>\r\n");
    }
    
    void b(final StringBuffer sb, final dg dg) {
        final dh bp = dg.bp();
        if (bp == null) {
            return;
        }
        final c a = bp.a(0);
        if (a != null) {
            if (a.b() <= 1) {
                if (a.b() > 0) {
                    final String h = ((ei)a.b(0)).h;
                    if (h != null) {
                        sb.append(h);
                    }
                }
            }
        }
    }
    
    void c(final StringBuffer sb, final dg dg) {
        final dh bp = dg.bp();
        if (bp == null) {
            return;
        }
        sb.append("\t\t<securityobject ");
        this.b(sb, "libraryid", this.g);
        this.b(sb, "systemtype", "idmds");
        this.b(sb, "objectid", dg.dw());
        this.b(sb, "objecttype", "annotation");
        this.b(sb, "clientpermission", bp.j() ? "change" : "none");
        sb.append(">\r\n");
        final c b = bp.b();
        if (b != null) {
            for (int b2 = b.b(), i = 0; i < b2; ++i) {
                final ei ei = (ei)b.b(i);
                if (ei.i != 2) {
                    sb.append("\t\t\t<permission ");
                    this.b(sb, "id", ei.m);
                    this.b(sb, "name", ei.h);
                    if (ei.i == 1) {
                        this.b(sb, "type", "group");
                    }
                    else {
                        this.b(sb, "type", "user");
                    }
                    this.b(sb, "level", ei.l);
                    String s = null;
                    switch (ei.k) {
                        case 2: {
                            s = "add";
                            break;
                        }
                        case 1: {
                            s = "change";
                            break;
                        }
                        case 3: {
                            s = "remove";
                            break;
                        }
                        default: {
                            s = "none";
                            break;
                        }
                    }
                    this.b(sb, "updatetype", s);
                    sb.append("/>\r\n");
                }
            }
        }
        sb.append("\t\t</securityobject>\r\n");
    }
    
    void a(final StringBuffer sb, final dg dg, final ad ad) {
        try {
            if (dg.dd()) {
                this.a(sb, "HYPERLINK", dg.b(ad));
                if (dg.dc() != null) {
                    this.a(sb, "HYPERLINKSETTINGS", dg.dc());
                }
            }
        }
        catch (Exception ex) {}
    }
    
    void a(final StringBuffer sb, final String s, final String s2) throws Exception {
        final StringBuffer sb2 = new StringBuffer(500);
        sb2.append(s);
        sb2.append('=');
        sb2.append(s2);
        sb2.append(';');
        final byte[] bytes = sb2.toString().getBytes();
        for (int i = 0; i < bytes.length; ++i) {
            sb.append(bytes[i] & 0xFF);
            sb.append(' ');
        }
    }
    
    void b(final StringBuffer sb, final String s, final String s2) {
        sb.append(s);
        sb.append("=\"");
        sb.append(this.c(s2));
        sb.append("\" ");
    }
    
    void a(final StringBuffer sb, final String s, final int n) {
        sb.append(s);
        sb.append("=\"");
        sb.append(n);
        sb.append("\" ");
    }
    
    private String c(final String s) {
        if (ji.util.i.c(47)) {
            return ji.util.d.a(s, true);
        }
        return s;
    }
    
    String a(final Calendar calendar) {
        final StringBuffer sb = new StringBuffer(20);
        try {
            sb.append(calendar.get(1));
            sb.append('-');
            final int n = calendar.get(2) + 1;
            if (n < 10) {
                sb.append(0);
            }
            sb.append(n);
            sb.append('-');
            final int value = calendar.get(5);
            if (value < 10) {
                sb.append(0);
            }
            sb.append(value);
            sb.append('T');
            final int value2 = calendar.get(11);
            if (value2 < 10) {
                sb.append(0);
            }
            sb.append(value2);
            sb.append(':');
            final int value3 = calendar.get(12);
            if (value3 < 10) {
                sb.append(0);
            }
            sb.append(value3);
            sb.append(':');
            final int value4 = calendar.get(13);
            if (value4 < 10) {
                sb.append(0);
            }
            sb.append(value4);
            sb.append('.');
            final int value5 = calendar.get(14);
            if (value5 < 10) {
                sb.append(0);
            }
            if (value5 < 100) {
                sb.append(0);
            }
            sb.append(value5);
            sb.append("0000");
            int value6 = 0;
            if (TimeZone.getDefault().inDaylightTime(calendar.getTime())) {
                value6 = calendar.get(16);
            }
            final int n2 = value6 + calendar.get(15);
            final int abs = Math.abs(n2 / 3600000);
            final int abs2 = Math.abs(n2 % 3600000 / 60000);
            sb.append((char)((n2 < 0) ? 45 : 43));
            if (abs < 10) {
                sb.append(0);
            }
            sb.append(abs);
            sb.append(':');
            if (abs2 < 10) {
                sb.append(0);
            }
            sb.append(abs2);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }
    
    String a(final Color color) {
        final int rgb = color.getRGB();
        return Integer.toString((rgb >> 24 << ji.util.d.ig & ji.util.d.if) | (rgb >> 16 & 0xFF) << ji.util.d.ii | (rgb >> 8 & 0xFF) << ji.util.d.ik | (rgb & 0xFF) << ji.util.d.im);
    }
    
    private final byte[] a(final String s, final df df, final Component component, final ad ad, final boolean a, final boolean[] array, final String d, final boolean b, final int n, final boolean b2, final boolean[] array2, final boolean b3, final boolean b4) throws Exception {
        this.d = d;
        final boolean cc = d.cc();
        this.a = a;
        this.c();
        final StringBuffer sb = new StringBuffer("");
        if (df != null) {
            if (df.d() <= 0 || d.b3(ad.aa()) == 1) {
                sb.append("[EMPTY]");
            }
            else {
                int n2 = Math.max(df.i(), ad.j8()) * 2;
                final ax g = df.g();
                int n3 = 0;
                final vc vc = new vc("newList2", n2);
                int n4 = -2;
                while (g.a()) {
                    final dg b5 = df.b(g.b());
                    boolean b6 = false;
                    int n5 = b5.i(-1) - 1;
                    if (n5 < 0) {
                        n5 = 0;
                    }
                    if (array2 != null) {
                        if (n5 < array2.length) {
                            b6 = array2[n5];
                        }
                        else if (array2.length > 0) {
                            b6 = array2[0];
                        }
                    }
                    if (!b5.bh() && !b5.aw() && b5.a(ad.aa(), b6, ad)) {
                        if (b5.d5() == 18) {
                            vc.a("".concat(String.valueOf(String.valueOf(n2))), b5);
                            ++n2;
                        }
                        else if (b5.gh()) {
                            vc.a("".concat(String.valueOf(String.valueOf(b5.ce()))), b5);
                        }
                        else {
                            vc.a("".concat(String.valueOf(String.valueOf(n4--))), b5);
                        }
                    }
                }
                final ax d2 = vc.d();
                final int b7 = vc.b();
                int n6 = 0;
                int max = Math.max(b7 / 40, 1);
                int n7 = 0;
                final String concat = String.valueOf(String.valueOf(d.b(487, s))).concat("...");
                final a6 a2 = new a6(component, 17, concat);
                a2.a(true);
                a2.a(0);
                a2.a(concat);
                final long currentTimeMillis = System.currentTimeMillis();
                while (d2.a()) {
                    final dg dg = (dg)vc.d(d2.b());
                    if (dg != null) {
                        if (!b3) {
                            switch (dg.az()) {
                                case 1:
                                case 2: {
                                    dg.d(0);
                                    break;
                                }
                            }
                        }
                        int n8 = 0;
                        if (array != null) {
                            boolean b8 = false;
                            int n9 = 0;
                            int n10 = 0;
                            for (int i = 0; i < array.length; ++i) {
                                if (array[i]) {
                                    ++n9;
                                }
                            }
                            for (int j = 0; j < array.length; ++j) {
                                if (array[j]) {
                                    ++n10;
                                    if (dg.i(j) - 1 == j) {
                                        if (n9 > 1) {
                                            n8 = n10;
                                        }
                                        else {
                                            n8 = 1;
                                        }
                                        b8 = true;
                                        break;
                                    }
                                }
                            }
                            if (!b8) {
                                continue;
                            }
                        }
                        ++n3;
                        if (dg.fg()) {
                            sb.append(this.a(dg, n8, component, ad, b2, b3, b4));
                            ++n3;
                        }
                    }
                    try {
                        ++n6;
                        if (--max != 0) {
                            continue;
                        }
                        max = max;
                        if (System.currentTimeMillis() - currentTimeMillis <= 1000) {
                            continue;
                        }
                        if (n7 == 0) {
                            ad.d(a2);
                            n7 = 1;
                        }
                        this.a(ad, 100 * n6 / b7);
                    }
                    catch (Exception ex) {}
                }
                if (n3 == 0) {
                    sb.append("[EMPTY]");
                }
            }
        }
        this.a(ad, 0);
        df.l();
        String a3 = new String(sb.toString().getBytes(d), d);
        if (ji.util.i.c(14)) {
            a3 = d.a(a3, true);
        }
        if (cc) {
            if (this.a) {
                return this.a(a3.getBytes(d));
            }
            return a3.getBytes(d);
        }
        else {
            if (this.a) {
                return this.a(a3.getBytes());
            }
            return a3.getBytes();
        }
    }
    
    private final void a(final ad ad, final int n) {
        try {
            ad.a(new a6(this, 14, "".concat(String.valueOf(String.valueOf(n)))));
        }
        catch (Exception ex) {}
    }
    
    private final byte[] a(final byte[] array) {
        String concat = "";
        for (int i = 0; i < array.length; ++i) {
            String s = Integer.toHexString(array[i] & 0xFF).toUpperCase();
            if (s.length() < 1) {
                s = "00";
            }
            else if (s.length() < 2) {
                s = "0".concat(String.valueOf(String.valueOf(s)));
            }
            concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(s)));
        }
        return concat.getBytes();
    }
    
    public final String a(final dg dg, final int n, final Component component, final ad ad, final boolean b, final boolean b2, final boolean b3) throws Exception {
        return this.a(dg, n, component, ad, b, b2, null, b3);
    }
    
    private static int a(final Dimension dimension, final Dimension dimension2) {
        if (dimension == null || dimension2 == null) {
            return 1;
        }
        if (dimension.equals(dimension2)) {
            return 1;
        }
        if (dimension.width * 2 == dimension2.width && dimension.height * 2 == dimension2.height) {
            return 2;
        }
        if (dimension.width * 4 == dimension2.width && dimension.height * 4 == dimension2.height) {
            return 4;
        }
        return 1;
    }
    
    public final String a(final dg dg, final int n, final Component component, final ad ad, final boolean b, final boolean b2, ef ef, final boolean b3) throws Exception {
        if (ef == null) {
            ef = this;
        }
        boolean b4 = true;
        boolean b5 = true;
        boolean b6 = true;
        boolean b7 = true;
        int n2 = 1;
        final Dimension dn = dg.dn();
        final Dimension dk = dg.dk();
        if (dn != null && dk == null) {
            n2 = 1;
        }
        else if (dn == null || dk == null) {
            if (n == 0 && !dg.aw()) {
                final dx bg = ad.bg(dg.i(0));
                if (bg != null && bg.c()) {
                    n2 = bg.d();
                }
            }
        }
        else {
            n2 = a(dn, dk);
        }
        if (this.d == null) {
            this.d = ji.util.d.bm(this.k);
        }
        if (ji.util.d.cc()) {
            this.c = new String("".getBytes(this.d), this.d);
        }
        else {
            this.c = new String("".getBytes());
        }
        if (dg.fg() && dg.k()) {
            final String g = dg.g(dg.d5());
            if (g != null) {
                ef.b(g);
            }
            switch (dg.d5()) {
                case 16: {
                    final String[] fc = dg.fc();
                    if (fc != null) {
                        for (int i = 0; i < fc.length; ++i) {
                            ef.a(fc[i]);
                        }
                        break;
                    }
                    break;
                }
                case 17: {
                    final String[] fd = dg.fd();
                    if (fd != null) {
                        for (int j = 0; j < fd.length; ++j) {
                            ef.a(fd[j]);
                        }
                        break;
                    }
                    break;
                }
                case 1: {
                    this.a(dg.ff(), n2, ef);
                    ef.a(dg.bb(), dg.bc());
                    break;
                }
                case 11: {
                    this.a(dg.ff(), n2, ef);
                    ef.a(dg.bb(), dg.bc());
                    break;
                }
                case 2: {
                    this.a(new da(dg.ff().a, dg.ff().b, dg.fk(), dg.fl()), n2, ef);
                    ef.a(dg.bb(), dg.bc());
                    break;
                }
                case 3: {
                    this.a(dg.fm(), n2, ef);
                    break;
                }
                case 4: {
                    this.a(dg.fn(), dg.fo(), n2, ef);
                    b4 = false;
                    b6 = false;
                    break;
                }
                case 5: {
                    this.a(dg.fn(), dg.fo(), n2, ef);
                    if (dg.eb() != 1) {
                        ef.a("ARROWHEADSIZE", "".concat(String.valueOf(String.valueOf(dg.eb()))));
                    }
                    b4 = false;
                    b6 = false;
                    break;
                }
                case 6: {
                    this.a(dg.fm(), n2, ef);
                    break;
                }
                case 7: {
                    this.a(dg.fn(), n2, ef);
                    if (dg.au() && dg.ap() > 0 && dg.aq() > 0) {
                        this.a(dg.ar(), n2, ef);
                    }
                    if (ji.util.i.c(247)) {
                        final String c1 = dg.c1();
                        if (c1 != null) {
                            ef.a("FONTSPEC", c1);
                        }
                        else if (dg.c3() != null) {
                            ef.a("FONTSPEC", "".concat(String.valueOf(String.valueOf(dg.c3()))));
                        }
                        else {
                            ef.a("FONTTYPE", "".concat(String.valueOf(String.valueOf(dg.cu().c))));
                        }
                        if (dg.a0() != null) {
                            ef.a("FONTSOURCE", "".concat(String.valueOf(String.valueOf(dg.a0()))));
                        }
                    }
                    else {
                        ef.a("FONTTYPE", "".concat(String.valueOf(String.valueOf(dg.cu().c))));
                    }
                    boolean b8 = true;
                    if (ji.util.i.c(247) && dg.cu() != null && dg.c2() != null) {
                        b8 = false;
                        ef.a("FONTHEIGHT", "".concat(String.valueOf(String.valueOf((int)(dg.c6() * dg.c2().getSize() / ji.util.d.a() / n2)))));
                    }
                    if (b8) {
                        ef.a("FONTHEIGHT", "".concat(String.valueOf(String.valueOf((int)(dg.b(component, dg.cu(), null) / ji.util.d.a() / n2)))));
                    }
                    ef.a("SEMITRANSPARENT", "".concat(String.valueOf(String.valueOf(this.a(dg.dz())))));
                    ef.a("BORDER", "".concat(String.valueOf(String.valueOf(this.a(dg.d7())))));
                    if (ji.util.i.c(247)) {
                        if (dg.i()) {
                            ef.a("STRIKETHROUGH", "1");
                        }
                        if (dg.j()) {
                            ef.a("UNDERLINE", "1");
                        }
                    }
                    else if (dg.i()) {
                        ef.a("STRIKETHROUGH", "1");
                    }
                    if (ji.util.i.c(150) && dg.gf() == 1) {
                        ef.a("ALIGNMENT", "RIGHT");
                    }
                    if (dg.d7() && dg.d8() != null) {
                        ef.a("BORDERCOLOR", ji.util.d.l(dg.d8()));
                    }
                    if (dg.ea() != 1) {
                        ef.a("BORDERWIDTH", "".concat(String.valueOf(String.valueOf(dg.ea()))));
                    }
                    ef.a("TEXT", ji.util.d.cb(dg.ev()));
                    b5 = false;
                    break;
                }
                case 8: {
                    this.a(dg.ff(), n2, ef);
                    ef.a(dg.bb(), dg.bc());
                    ef.a("TEXT", ji.util.d.cb(dg.ev()));
                    if (dg.ba()) {
                        ef.a("RECTANGULAR", "1");
                    }
                    if (b2 || ad.bi(19)) {
                        ef.a("NOTEID", dg.h());
                    }
                    b5 = false;
                    break;
                }
                case 10: {
                    this.a(dg.fm(), n2, ef);
                    b4 = false;
                    b6 = false;
                    break;
                }
                case 13: {
                    this.a(dg.ff(), n2, ef);
                    ef.a(dg.bb(), dg.bc());
                    break;
                }
                case 12: {
                    this.a(dg.fm(), n2, ef);
                    break;
                }
                case 14: {
                    this.a(dg.fm(), n2, ef);
                    break;
                }
                case 9: {
                    this.a(dg.fn(), n2, ef);
                    if (dg.b7()) {
                        ef.a("SCALE", "".concat(String.valueOf(String.valueOf(dg.cb()))));
                    }
                    else {
                        try {
                            final da bf = dg.bf(false);
                            this.a(new dc(bf.c, bf.d), n2, ef);
                        }
                        catch (Exception ex) {
                            ji.util.d.a(ex);
                        }
                    }
                    ef.a("RESOURCE", "".concat(String.valueOf(String.valueOf(ji.util.d.bc(dg.ev())))));
                    ef.a("OVERLAY", "".concat(String.valueOf(String.valueOf(this.a(!dg.av())))));
                    b7 = false;
                    b6 = false;
                    b5 = false;
                    b4 = false;
                    break;
                }
            }
            if (dg.d5() == 18) {
                ef.a("PAGE", "".concat(String.valueOf(String.valueOf(dg.i(-1)))));
            }
            else if (dg.d5() != 16) {
                if (n > 0) {
                    ef.a("PAGE", "".concat(String.valueOf(String.valueOf(n))));
                    if (ad != null) {
                        ef.a("PAGEURL", ad.be(n - 1));
                    }
                }
                else {
                    ef.a("PAGE", "".concat(String.valueOf(String.valueOf(dg.i(-1)))));
                    if (ad != null) {
                        ef.a("PAGEURL", ad.be(dg.i(-1) - 1));
                    }
                }
                if (b5) {
                    ef.a("LINEWIDTH", "".concat(String.valueOf(String.valueOf(dg.ee() / n2))));
                }
                if (b6 && dg.ei() && this.b(dg) != null) {
                    ef.a("FILLCOLOR", ji.util.d.l(this.b(dg)));
                }
                if (b7) {
                    ef.a("COLOR", ji.util.d.l(this.a(dg)));
                }
                if (b4) {
                    ef.a("TRANSPARENT", this.a(this.c(dg)));
                }
                if (dg.dv()) {
                    ef.a("LABEL", ji.util.d.bc(dg.du()));
                }
                if (dg.db()) {
                    ef.a("TOOLTIP", ji.util.d.bc(dg.c9()));
                }
                if (dg.dd()) {
                    ef.a("HYPERLINK", ji.util.d.bc(dg.b(ad)));
                }
                if (dg.e7()) {
                    ef.a("ASPECTRATIO", ji.util.d.bc(dg.e6()));
                }
                if (dg.e1()) {
                    ef.a("RULERTOOL", this.a(dg.e1()));
                }
                if (dg.e2()) {
                    ef.a("ANGLETOOL", this.a(dg.e2()));
                }
                if (dg.e2()) {
                    ef.a("ANGLETOOLFLIP", this.a(dg.e5()));
                }
                if (dg.c(ad)) {
                    ef.a("HYPERLINKSETTINGS", ji.util.d.bc(dg.dc()));
                }
                if (dg.de()) {
                    ef.a("ID", ji.util.d.bc(dg.c8()));
                }
                if (dg.b3() != 0 || dg.b4() != 0) {
                    if (dg.d5() == 7) {
                        ef.a("ROTATION", "".concat(String.valueOf(String.valueOf(dg.b3()))));
                        ef.a("TEXTROTATION", "".concat(String.valueOf(String.valueOf(dg.b4()))));
                    }
                    else {
                        ef.a("ROTATION", "".concat(String.valueOf(String.valueOf(dg.b3()))));
                    }
                }
                if (dg.ca() != 0) {
                    ef.a("FLIP", "".concat(String.valueOf(String.valueOf(dg.ca()))));
                }
                if (dg.cd()) {
                    ef.a("INVERT", this.a(dg.cd()));
                }
                if (dg.do()) {
                    ef.a("PAGESIZE", this.a(dg.dk(), n2));
                }
                if (dg.bn() == 1) {
                    if (ad.hc() != 1) {
                        ef.a("SECURITYMODEL", "".concat(String.valueOf(String.valueOf(dg.bn()))));
                    }
                    ef.a("EDIT", this.a(dg.ak(b)));
                    if (dg.bp().c() != 3) {
                        ef.a("VIEW", "".concat(String.valueOf(String.valueOf(dg.bp().c()))));
                    }
                }
                else {
                    final dh bp = dg.bp();
                    ef.a("SECURITYMODEL", "".concat(String.valueOf(String.valueOf(dg.bn()))));
                    ef.a("READ", this.a(bp.h()));
                    ef.a("MODIFY", this.a(bp.i()));
                    ef.a("EXECUTE", this.a(bp.k()));
                    ef.a("PRINT", this.a(bp.l()));
                    ef.a("DELETE", this.a(bp.m()));
                    ef.a("MODIFYSECURITY", this.a(bp.j()));
                    ef.a("OWNER", bp.n());
                    final String e = bp.e();
                    if (ji.util.i.c(8) || !ji.util.d.by(e)) {
                        ef.a("PASSWORDMODIFY", ji.util.d.ce(e));
                    }
                    final String f = bp.f();
                    if (ji.util.i.c(9) || !ji.util.d.by(f)) {
                        ef.a("PASSWORDSECURITY", ji.util.d.ce(f));
                    }
                    final String g2 = bp.g();
                    if (ji.util.i.c(10) || !ji.util.d.by(g2)) {
                        ef.a("PASSWORDTEXT", ji.util.d.ce(g2));
                    }
                    if (!ji.util.d.by(ad.x())) {
                        final c a = bp.a(1);
                        if (a != null && a.b() > 0) {
                            final String h = ((ei)a.b(0)).h;
                            if (h != null) {
                                ef.a("CUSTOMSECURITY", h);
                            }
                        }
                    }
                }
                if (dg.en()) {
                    ef.a("BLANKOUTIMAGE", this.a(dg.en()));
                }
                if (dg.dj() != null) {
                    ef.a("CREATEDATE", dg.dj().toUpperCase());
                }
                else if (dg.di() != null) {
                    ef.a("CREATEDATE", this.a(dg.di()));
                }
                if (dg.ds() != null) {
                    ef.a("MODIFIEDDATE", dg.ds().toUpperCase());
                }
                else if (dg.dr() != null) {
                    ef.a("MODIFIEDDATE", this.a(dg.dr()));
                }
                else if (dg.dj() != null) {
                    ef.a("MODIFIEDDATE", dg.dj().toUpperCase());
                }
                else if (dg.di() != null) {
                    ef.a("MODIFIEDDATE", this.a(dg.di()));
                }
                if (dg.dh()) {
                    ef.a("CREATEDID", ji.util.d.bc(dg.dg()));
                }
                if (dg.dq()) {
                    ef.a("MODIFIEDID", ji.util.d.bc(dg.dp()));
                }
                else if (dg.dh()) {
                    ef.a("MODIFIEDID", ji.util.d.bc(dg.dg()));
                }
                if (b3) {
                    ef.a("HASH", ji.util.d.bc(dg.a6()));
                }
                final String[] a2 = dg.a9();
                if (a2 != null) {
                    for (int k = 0; k < a2.length; ++k) {
                        ji.util.d.s(a2[k], "=");
                        ef.a("CUSTOMPROPERTY", a2[k]);
                    }
                }
            }
            ef.a();
        }
        return this.c;
    }
    
    private final void d(final String s) throws Exception {
        if (!ji.util.d.by(s)) {
            final String bc = ji.util.d.bc(s);
            if (ji.util.d.cc()) {
                this.c = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.c))).append(new String(bc.getBytes(this.d), this.d)).append("\r\n")));
            }
            else {
                this.c = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.c))).append(new String(bc.getBytes())).append("\r\n")));
            }
        }
    }
    
    public final void a(final String s) {
        try {
            this.d(s);
        }
        catch (Exception ex) {}
    }
    
    public final void a(final String s, final String s2) {
        try {
            this.d(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s.toUpperCase()))).append(" = ").append(s2))));
        }
        catch (Exception ex) {}
    }
    
    public final void b(final String s) {
        try {
            this.d(String.valueOf(String.valueOf(new StringBuffer("[").append(s).append("]"))));
        }
        catch (Exception ex) {}
    }
    
    public final void a() {
        try {
            this.d("  ");
        }
        catch (Exception ex) {}
    }
    
    public void a(final double n, final double n2) {
    }
    
    private final String a(final boolean b) {
        if (b) {
            return "1";
        }
        return "0";
    }
    
    private final String a(final Dimension dimension, final int n) {
        if (dimension != null) {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(dimension.width / n))).append(", ").append(dimension.height / n)));
        }
        return "";
    }
    
    private final String a(final GregorianCalendar gregorianCalendar) {
        return ji.util.d.a(gregorianCalendar, false);
    }
    
    private final void a(final d4 d4, final int n, final ef ef) {
        for (int b = d4.b(), i = 0; i < b; ++i) {
            final d5 a = d4.a(i);
            ef.a(String.valueOf(String.valueOf(new StringBuffer("X").append(i + 1))), "".concat(String.valueOf(String.valueOf(a.a / n))));
            ef.a(String.valueOf(String.valueOf(new StringBuffer("Y").append(i + 1))), "".concat(String.valueOf(String.valueOf(a.b / n))));
        }
    }
    
    private final void a(final da da, final int n, final ef ef) {
        ef.a("X", "".concat(String.valueOf(String.valueOf(da.a / n))));
        ef.a("Y", "".concat(String.valueOf(String.valueOf(da.b / n))));
        ef.a("WIDTH", "".concat(String.valueOf(String.valueOf(da.c / n))));
        ef.a("HEIGHT", "".concat(String.valueOf(String.valueOf(da.d / n))));
    }
    
    private final void a(final d5 d5, final d5 d6, final int n, final ef ef) {
        ef.a("X1", "".concat(String.valueOf(String.valueOf(d5.a / n))));
        ef.a("Y1", "".concat(String.valueOf(String.valueOf(d5.b / n))));
        ef.a("X2", "".concat(String.valueOf(String.valueOf(d6.a / n))));
        ef.a("Y2", "".concat(String.valueOf(String.valueOf(d6.b / n))));
    }
    
    private final void a(final d5 d5, final int n, final ef ef) {
        ef.a("X", "".concat(String.valueOf(String.valueOf(d5.a / n))));
        ef.a("Y", "".concat(String.valueOf(String.valueOf(d5.b / n))));
    }
    
    private final void a(final dc dc, final int n, final ef ef) {
        ef.a("WIDTH", "".concat(String.valueOf(String.valueOf(dc.a / n))));
        ef.a("HEIGHT", "".concat(String.valueOf(String.valueOf(dc.b / n))));
    }
    
    private final void c() {
        if (this.b == null) {
            this.b = new String(new char[] { '\r', '\n' });
        }
    }
    
    public final void b() {
    }
    
    static {
        ee.l = "http://localhost:8080/XMLAnnotations/FnDocAnnoList.xsd";
        ee.m = "sec";
        ee.n = "http://localhost:8080/XMLAnnotations/FnDocAnnoList.xsd";
        p = new String[] { "none", "change", "add", "remove" };
        ee.q = false;
    }
    
    private class vc extends c
    {
        private int a;
        
        public vc(final ee ee, final String s, final int a) {
            super(s, true);
            this.a = a;
        }
        
        public k e() {
            if (!i.c(257)) {
                return new adt(this.b(), this.a);
            }
            return super.e();
        }
        
        private class adt extends k
        {
            private int a;
            
            public adt(final vc vc, final int n, final int a) {
                super(n);
                this.a = a;
            }
            
            public void a(final String s) {
                final long long1 = Long.parseLong(s);
                int i;
                for (i = 0; i < super.a.b(); ++i) {
                    final long long2 = Long.parseLong(super.a.b(i).toString());
                    boolean b = false;
                    if (long2 != -1 && long2 < this.a && long1 != -1 && long1 < this.a) {
                        if (long1 > long2) {
                            break;
                        }
                        b = true;
                    }
                    if (!b && long1 < long2) {
                        break;
                    }
                }
                super.a.b(s, i);
            }
            
            public String b() {
                String string = null;
                if (super.a.b() > 0) {
                    string = super.a.b(0).toString();
                    super.a.d(0);
                }
                return string;
            }
        }
    }
    
    private static final class vf
    {
        private String a;
        private String b;
        private static final vf c;
        private static final vf d;
        private static final vf e;
        private static final vf f;
        private static final vf g;
        private static final vf h;
        private static final vf i;
        
        private vf(final String a, final String b) {
            this.b = b;
            this.a = a;
        }
        
        public String toString() {
            return this.b;
        }
        
        static {
            c = new vf("{5CF11946-018F-11D0-A87A-00A0246922A5}", "Arrow");
            d = new vf("{5CF11941-018F-11D0-A87A-00A0246922A5}", "Text");
            e = new vf("{5CF11942-018F-11D0-A87A-00A0246922A5}", "Highlight");
            f = new vf("{5CF11945-018F-11D0-A87A-00A0246922A5}", "StickyNote");
            g = new vf("{5CF11949-018F-11D0-A87A-00A0246922A5}", "Pen");
            h = new vf("{5CF1194C-018F-11D0-A87A-00A0246922A5}", "Stamp");
            i = new vf("{A91E5DF2-6B7B-11D1-B6D7-00609705F027}", "Proprietary");
        }
    }
}
