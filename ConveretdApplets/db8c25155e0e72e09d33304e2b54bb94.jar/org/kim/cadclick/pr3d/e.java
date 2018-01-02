// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.pr3d;

import java.util.Enumeration;
import java.util.Vector;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.DataInputStream;
import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.io.StreamTokenizer;

class e implements o
{
    private StreamTokenizer af;
    protected Hashtable am;
    protected String ao;
    protected String aw;
    private Prove3d ai;
    private int ag;
    private InputStream ah;
    private boolean aj;
    boolean av;
    int ar;
    double an;
    String as;
    int at;
    double ap;
    String au;
    private double[] aq;
    private int al;
    private String[] ak;
    private int ae;
    
    protected n if(final byte[] array) throws IOException {
        this.a(array);
        this.am = new Hashtable();
        final g g = new g("SCENEGRAPH", 0, this.ai);
        this.a(true, g.bm);
        if (g.bm.cx.length == 0) {
            return null;
        }
        return g;
    }
    
    private int a() throws IOException {
        if (this.av) {
            this.av = false;
            final int at = this.at;
            this.at = this.ar;
            this.ar = at;
            final double ap = this.ap;
            this.ap = this.an;
            this.an = ap;
            final String au = this.au;
            this.au = this.as;
            this.as = au;
        }
        else {
            this.af.nextToken();
            this.ar = this.at;
            this.an = this.ap;
            this.as = this.au;
            this.at = this.af.ttype;
            this.ap = this.af.nval;
            this.au = this.af.sval;
        }
        if (this.at == -2) {
            this.af.nextToken();
            this.ar = this.at;
            this.an = this.ap;
            this.as = this.au;
            this.at = this.af.ttype;
            this.ap = this.af.nval;
            this.au = this.af.sval;
            if (this.at == -3 && this.au != null) {
                final String lowerCase = this.au.toLowerCase();
                if (lowerCase.startsWith("e")) {
                    String s = lowerCase.substring(1);
                    boolean b = true;
                    if (s.startsWith("+")) {
                        s = s.substring(1);
                    }
                    else if (s.startsWith("-")) {
                        b = false;
                        s = s.substring(1);
                    }
                    final int intValue = Integer.valueOf(s);
                    this.at = this.ar;
                    this.ap = this.an;
                    this.au = this.as;
                    if (b) {
                        this.ap *= Math.pow(10.0, intValue);
                    }
                    else {
                        this.ap /= Math.pow(10.0, intValue);
                    }
                    return this.at;
                }
            }
            this.if();
        }
        return this.at;
    }
    
    protected e(final Prove3d ai, final boolean aj) {
        this.aw = "";
        this.ag = 0;
        this.ah = null;
        this.av = false;
        this.aq = new double[1000];
        this.ak = new String[10];
        this.ai = ai;
        this.aj = aj;
    }
    
    private int if() {
        this.av = true;
        final int at = this.at;
        this.at = this.ar;
        this.ar = at;
        final double ap = this.ap;
        this.ap = this.an;
        this.an = ap;
        final String au = this.au;
        this.au = this.as;
        this.as = au;
        return this.at;
    }
    
    private n a(final String s) {
        final n n = this.am.get(s);
        if (n instanceof g || n instanceof j) {
            final n do1 = n.do();
            if (do1 instanceof g) {
                final g g = (g)do1;
                g.bp = new float[12];
                g.bl = new float[12];
            }
            else if (do1 instanceof j) {
                final j j = (j)do1;
            }
            return do1;
        }
        if (!(n instanceof h)) {
            return n;
        }
        if (n.ay == 6 || n.ay == 9) {
            return n;
        }
        return n.do();
    }
    
    private void a(final d d) throws IOException {
        switch (d.cc) {
            case 1: {
                if (this.a() == -1) {
                    throw new IOException();
                }
                if (this.au.equalsIgnoreCase("true")) {
                    d.cj = true;
                    return;
                }
                d.cj = false;
            }
            case 4: {
                d.ck = new float[3];
                int n = 0;
                while (this.a() != -1) {
                    d.ck[n] = (float)this.ap;
                    if (++n >= 3) {
                        return;
                    }
                }
                throw new IOException();
            }
            case 6: {
                if (this.a() == -1) {
                    throw new IOException();
                }
                d.ci = (float)this.ap;
            }
            case 8: {
                if (this.a() == -1) {
                    throw new IOException();
                }
                d.ch = (int)this.ap;
            }
            case 10: {
                if (this.a() == -1) {
                    throw new IOException();
                }
                final String au = this.au;
                if (au.equals("DEF")) {
                    if (this.a() == -1) {
                        throw new IOException();
                    }
                    final String au2 = this.au;
                    if (this.a() == -1) {
                        throw new IOException();
                    }
                    final n if1 = this.if(this.au, au2);
                    d.b6 = if1;
                    if1.ax = d.cf;
                    return;
                }
                else if (au.equals("USE")) {
                    if (this.a() == -1) {
                        throw new IOException();
                    }
                    final n a = this.a(this.au);
                    if (a != null) {
                        d.b6 = a;
                        a.ax = d.cf;
                        return;
                    }
                    break;
                }
                else {
                    final n if2 = this.if(au, "");
                    if ((d.b6 = if2) != null) {
                        if2.ax = d.cf;
                        return;
                    }
                    break;
                }
                break;
            }
            case 12: {
                d.ck = new float[4];
                int n2 = 0;
                while (this.a() != -1) {
                    d.ck[n2] = (float)this.ap;
                    if (++n2 >= 4) {
                        return;
                    }
                }
                throw new IOException();
            }
            case 14: {
                if (this.a() == -1) {
                    throw new IOException();
                }
                d.b7 = this.au;
            }
            case 3: {
                if (this.a() == -1) {
                    throw new IOException();
                }
                d.cm = this.ap;
            }
            case 16: {
                d.ck = new float[2];
                int n3 = 0;
                while (this.a() != -1) {
                    d.ck[n3] = (float)this.ap;
                    if (++n3 >= 2) {
                        return;
                    }
                }
                throw new IOException();
            }
            case 18: {
                d.ck = new float[3];
                int n4 = 0;
                while (this.a() != -1) {
                    d.ck[n4] = (float)this.ap;
                    if (++n4 >= 3) {
                        return;
                    }
                }
                throw new IOException();
            }
            case 5: {
                this.a(3);
                d.ck = new float[this.al];
                for (int i = 0; i < this.al; ++i) {
                    d.ck[i] = (float)this.aq[i];
                }
            }
            case 7: {
                this.a(1);
                d.ck = new float[this.al];
                for (int j = 0; j < this.al; ++j) {
                    d.ck[j] = (float)this.aq[j];
                }
            }
            case 9: {
                this.a(1);
                d.b3 = new int[this.al];
                for (int k = 0; k < this.al; ++k) {
                    d.b3[k] = (int)this.aq[k];
                }
            }
            case 11: {
                this.a(false, d);
            }
            case 13: {
                this.a(4);
                d.ck = new float[this.al];
                for (int l = 0; l < this.al; ++l) {
                    d.ck[l] = (float)this.aq[l];
                }
            }
            case 15: {
                this.a(1);
                d.cv = new String[this.ae];
                for (int n5 = 0; n5 < this.ae; ++n5) {
                    d.cv[n5] = this.ak[n5];
                }
            }
            case 17: {
                this.a(2);
                d.ck = new float[this.al];
                for (int n6 = 0; n6 < this.al; ++n6) {
                    d.ck[n6] = (float)this.aq[n6];
                }
            }
            case 19: {
                this.a(3);
                d.ck = new float[this.al];
                for (int n7 = 0; n7 < this.al; ++n7) {
                    d.ck[n7] = (float)this.aq[n7];
                }
            }
        }
    }
    
    public n a(String substring, final String s) {
        final int lastIndex = substring.lastIndexOf("http:");
        if (lastIndex != -1) {
            substring = substring.substring(lastIndex);
        }
        this.ao = substring;
        int n = this.ao.lastIndexOf("\\");
        if (n == -1) {
            n = this.ao.lastIndexOf("/");
        }
        if (n != -1) {
            this.aw = this.ao.substring(0, n + 1);
        }
        else {
            this.aw = "";
        }
        try {
            final URL int1 = this.ai.int(substring);
            if (this.aj) {
                this.ai.try("loading " + s);
                this.ai.if(0.0f);
            }
            byte[] a = this.ai.a(int1, this.aj);
            if (this.aj) {
                this.ai.if(1.0f);
                this.ai.try("parsing " + s);
                this.ai.if(0.0f);
            }
            if ((a[0] & 0xFF) == 0x1F && (a[1] & 0xFF) == 0x8B) {
                final int n2 = a.length - 4;
                final byte[] array = new byte[(a[n2] & 0xFF) | (a[n2 + 1] & 0xFF) << 8 | (a[n2 + 2] & 0xFF) << 16 | (a[n2 + 3] & 0xFF) << 24];
                new DataInputStream(new GZIPInputStream(new ByteArrayInputStream(a))).readFully(array);
                a = array;
            }
            final n if1 = this.if(a);
            if (this.aj) {
                this.ai.if(1.0f);
            }
            return if1;
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
            this.ai.case("Wrong file name:" + substring);
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
            this.ai.case("Exception while parsing the scene");
        }
        return null;
    }
    
    private void a(final byte[] array) {
        (this.af = new StreamTokenizer(new BufferedReader(new InputStreamReader(new ByteArrayInputStream(array))))).commentChar(35);
        this.af.ordinaryChar(91);
        this.af.ordinaryChar(93);
        this.af.ordinaryChar(123);
        this.af.ordinaryChar(125);
        this.af.whitespaceChars(44, 44);
        this.af.wordChars(95, 95);
        this.af.wordChars(33, 33);
        this.af.wordChars(36, 43);
        this.af.wordChars(45, 45);
        this.af.wordChars(128, 255);
    }
    
    private void a(boolean b, final d d) throws IOException {
        do {
            this.a();
            if (this.at == 91) {
                b = true;
            }
            if (this.at == 93) {
                b = false;
            }
            if (this.at == -1) {
                b = false;
            }
            if (this.at == -3) {
                final String au = this.au;
                if (au.equals("DEF")) {
                    if (this.a() == -1) {
                        throw new IOException();
                    }
                    final String au2 = this.au;
                    if (this.a() == -1) {
                        throw new IOException();
                    }
                    final n if1 = this.if(this.au, au2);
                    if (if1 == null) {
                        continue;
                    }
                    this.a(d, if1);
                }
                else if (au.equals("USE")) {
                    if (this.a() == -1) {
                        throw new IOException();
                    }
                    final n a = this.a(this.au);
                    if (a == null) {
                        continue;
                    }
                    this.a(d, a);
                }
                else if (au.equals("ROUTE")) {
                    if (this.a() == -1) {
                        throw new IOException();
                    }
                    if (this.a() == -1) {
                        throw new IOException();
                    }
                    if (this.a() == -1) {
                        throw new IOException();
                    }
                    continue;
                }
                else {
                    final n if2 = this.if(au, "");
                    if (if2 == null) {
                        continue;
                    }
                    this.a(d, if2);
                }
            }
        } while (b);
    }
    
    public void a(final g g) {
        final Hashtable<String, Vector> hashtable = new Hashtable<String, Vector>();
        final Vector vector = new Vector<h>();
        final Enumeration<n> elements = this.am.elements();
        while (elements.hasMoreElements()) {
            final n n = elements.nextElement();
            switch (n.ay) {
                case 6:
                case 9: {
                    final String[] array = (n.ay != 9) ? ((h)n).aC.cv : ((h)n).aG.cv;
                    if (array.length <= 0) {
                        continue;
                    }
                    final String string = String.valueOf(this.aw) + array[0];
                    if (!hashtable.containsKey(string)) {
                        hashtable.put(string, new Vector<n>());
                    }
                    hashtable.get(string).addElement(n);
                    continue;
                }
                case 2: {
                    vector.addElement((h)n);
                }
                default: {
                    continue;
                }
            }
        }
        final float n2 = hashtable.size() + vector.size();
        if (n2 != 0.0f && this.aj) {
            this.ai.try("loading textures");
            this.ai.if(0.0f);
            System.gc();
        }
        int n3 = 0;
        final Enumeration<Vector<n>> elements2 = hashtable.elements();
        while (elements2.hasMoreElements()) {
            if (this.aj) {
                this.ai.if(n3 / n2);
            }
            final Enumeration<n> elements3 = elements2.nextElement().elements();
            while (elements3.hasMoreElements()) {
                elements3.nextElement().if(this.aw);
            }
            ++n3;
        }
        final Enumeration<h> elements4 = vector.elements();
        while (elements4.hasMoreElements()) {
            if (this.aj) {
                this.ai.if(n3 / n2);
            }
            elements4.nextElement().if(this.aw);
            ++n3;
        }
        System.gc();
        if (n2 != 0.0f && this.aj) {
            this.ai.if(1.0f);
        }
    }
    
    protected void a(final d d, final n b6) {
        if (d.cx == null) {
            (d.cx = new n[1])[0] = b6;
            d.b6 = b6;
        }
        else {
            final n[] cx = d.cx;
            System.arraycopy(cx, 0, d.cx = new n[cx.length + 1], 0, cx.length);
            d.cx[cx.length] = b6;
        }
        b6.ax = d.cf;
    }
    
    private void a(final int n) throws IOException {
        final boolean b = false;
        this.ae = (b ? 1 : 0);
        this.al = (b ? 1 : 0);
        boolean b2 = false;
        int n2 = 0;
        while (this.a() != -1) {
            ++n2;
            if (this.at == -2) {
                if (this.al == this.aq.length) {
                    final double[] aq = new double[this.aq.length + 1000];
                    System.arraycopy(this.aq, 0, aq, 0, this.aq.length);
                    this.aq = aq;
                }
                this.aq[this.al++] = this.ap;
            }
            else if (this.at == 91) {
                b2 = true;
            }
            else {
                if (this.at == 93) {
                    return;
                }
                if (this.at == 34) {
                    if (this.ae == this.ak.length) {
                        final String[] ak = new String[this.ak.length + 10];
                        System.arraycopy(this.ak, 0, ak, 0, this.ak.length);
                        this.ak = ak;
                    }
                    this.ak[this.ae++] = this.au;
                }
            }
            if (b2 || (!b2 && n2 != n)) {
                continue;
            }
            return;
        }
        throw new IOException();
    }
    
    private n if(final String s, String string) throws IOException {
        final n do1 = this.ai.do(s, string);
        if (do1 != null) {
            if (string.equals("")) {
                string = "xyz-noname" + this.ag++;
            }
            final n put = this.am.put(string, do1);
            if (put != null) {
                this.am.put("xyz-noname" + this.ag++, put);
            }
            int n = 0;
            while (this.a() != -1) {
                if (this.at == 123) {
                    ++n;
                }
                else if (this.at == 125) {
                    --n;
                }
                else if (this.at == -3) {
                    try {
                        this.a(do1.do(this.au));
                    }
                    catch (Stv_X3DException ex) {
                        if (this.ai.appletParam_statistics) {
                            System.out.println("Unrecognized field type '" + this.au + "' at line: " + this.af.lineno());
                        }
                    }
                }
                if (n <= 0) {
                    return do1;
                }
            }
            throw new IOException();
        }
        if (this.ai.appletParam_statistics) {
            System.out.println("Unrecognized node type '" + s + "' at line: " + this.af.lineno());
        }
        if ((s.equalsIgnoreCase("proto") || s.equalsIgnoreCase("externproto")) && this.a() == -1) {
            throw new IOException();
        }
        int n2 = 0;
        while (this.a() != -1) {
            if (this.at == 123 || this.at == 91) {
                ++n2;
            }
            else if (this.at == 125 || this.at == 93) {
                --n2;
            }
            if (n2 == 0) {
                if (this.ai.appletParam_statistics) {
                    System.out.println("---skipped to line: " + this.af.lineno());
                    return do1;
                }
                return do1;
            }
        }
        throw new IOException();
    }
}
