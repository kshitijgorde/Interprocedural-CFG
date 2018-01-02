import java.net.URL;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Enumeration;
import java.io.IOException;
import java.util.Stack;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class C42 extends C18
{
    char i;
    C25 j;
    C32 k;
    C35 m;
    InputStream n;
    C24 o;
    public static String p;
    Stack q;
    boolean s;
    
    protected void a(final C32 c32) {
        if (c32.e() == null || c32.e() == 0) {
            this.k = null;
            return;
        }
        if (super.m.get(c32.e()) == null) {
            super.s.addElement(c32);
            this.b(c32.e(), c32);
        }
        this.k = super.m.get(c32.e());
    }
    
    public void j(final C46 o) throws IOException {
        try {
            super.o = o;
            this.h();
            if (this.o instanceof C13) {}
            if (super.o != null) {
                super.o.R();
                if (((C13)this.o).f() != null) {
                    super.o.k(((C13)this.o).f());
                }
            }
            this.g();
        }
        catch (IOException ex) {
            System.out.println("Exception last Widget=" + this.m);
            throw ex;
        }
        finally {
            this.n.close();
        }
    }
    
    private void b(final C35 m) {
        this.m = m;
        if (!this.s && m != null) {
            m.e(false);
        }
        if (m != null) {
            if (m instanceof C32) {
                this.a((C32)m);
            }
            else if (m instanceof C17) {
                final String c = ((C17)m).c();
                if (c == null) {
                    super.v = (C17)m;
                    if (super.o != null) {
                        super.o.x((C17)m);
                    }
                }
                else {
                    super.q.put(c, m);
                }
            }
            else if (m instanceof C25) {
                this.j = (C25)m;
            }
            else if (m instanceof C28) {
                if (this.k != null) {
                    ((C28)m).f(this.k.e());
                }
                if (this.j != null && (this.j.g() != null || this.j.f() != null)) {
                    ((C28)m).o(this.j);
                    super.u.addElement(m);
                }
            }
            this.i(m);
        }
    }
    
    private Enumeration c(final String s) {
        final Vector<String> vector = new Vector<String>();
        int n = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "()", true);
        StringBuffer sb = new StringBuffer();
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (n != 0) {
                sb.append(nextToken);
            }
            if (nextToken.equals("(")) {
                ++n;
            }
            else if (nextToken.equals(")")) {
                --n;
            }
            if (n == 0 && sb.toString().indexOf(")") != -1) {
                vector.addElement(sb.toString().substring(0, sb.toString().length() - 1));
                sb = new StringBuffer();
            }
        }
        return vector.elements();
    }
    
    protected boolean d(final String s) {
        final String lowerCase = s.toLowerCase();
        if (lowerCase.startsWith("author") && s.length() > 8) {
            return true;
        }
        if (lowerCase.startsWith("created") && s.length() > 9) {
            return true;
        }
        if (lowerCase.startsWith("creator") && s.length() > 9) {
            if (s.substring(8).toLowerCase().indexOf("turbocad") != -1) {
                System.out.println("Turbo cAD");
                if (this.o instanceof C13) {
                    ((C13)this.o).n(true);
                }
            }
            return true;
        }
        return (lowerCase.startsWith("description") && s.length() > 13) || (lowerCase.startsWith("drawinginfo") && s.length() > 13) || (lowerCase.startsWith("modified") && s.length() > 10) || (lowerCase.startsWith("sourcecreated") && s.length() > 15) || (lowerCase.startsWith("sourcefilename") && s.length() > 16) || (lowerCase.startsWith("sourcemodified") && s.length() > 16);
    }
    
    static {
        C42.p = "Copyright (c) 1998 - Arnona Internet Software inc. All Rights Reserved";
    }
    
    private void h() throws IOException {
        C27 c27 = new C27(C02.j, this.n);
        this.q = new Stack();
        this.s = true;
        char i = '\0';
        int n = 0;
        boolean b = false;
        StringBuffer sb = new StringBuffer();
        int read = 0;
        for (int n2 = 0; n2 < 12 && read != -1; ++n2) {
            read = c27.read();
            sb.append((char)read);
        }
        final String string = sb.toString();
        if (read == -1 || (!string.startsWith("(DWF V") && !string.startsWith("(VR2 V")) || string.length() < 12) {
            throw new IOException("Invalid file, not a DWF format");
        }
        final String substring = string.substring(6, 8);
        final String substring2 = string.substring(9, 11);
        final int int1 = Integer.parseInt(substring);
        final int int2 = Integer.parseInt(substring2);
        if (int1 * 100 + int2 < 23) {
            c27 = new C27(this.n);
        }
        final C50 c28 = new C50(c27);
        this.o = new C13(c28, int1, int2, super.t);
        this.i(new C43("TimesRoman", 12, false, false));
        while (i != '\uffff') {
            this.i = i;
            i = (char)c28.read();
            if (i == '\uffff') {
                continue;
            }
            if (i == '{') {
                final long j = c28.i();
                if (j > 2147483647L) {
                    System.out.println("Warning: 392833 " + j);
                }
                this.b(this.o.b((c28.read() << 8) + c28.read(), (int)(j - 2L)));
            }
            else {
                if (i == '(' && !b) {
                    if (++n == 1) {
                        sb = new StringBuffer();
                    }
                    if (n == 1) {
                        continue;
                    }
                }
                if (i == ')' && !b && n > 0) {
                    if (--n == 0) {
                        if (sb.toString().indexOf("(") == -1 || !sb.toString().toLowerCase().startsWith("drawinginfo")) {
                            if (!this.d(sb.toString())) {
                                this.b(this.o.a(sb.toString()));
                            }
                        }
                        else {
                            final Enumeration c29 = this.c(sb.toString());
                            while (c29.hasMoreElements()) {
                                final String s = c29.nextElement();
                                if (!this.d(s)) {
                                    this.b(this.o.a(s));
                                }
                            }
                        }
                    }
                    if (n == 0) {
                        continue;
                    }
                }
                if (n > 0 && i == '\'' && this.i != '\\') {
                    b = !b;
                }
                if (n > 0) {
                    sb.append(i);
                }
                if (n != 0) {
                    continue;
                }
                if (i == 'V') {
                    this.s = true;
                }
                else if (i == 'v') {
                    this.s = false;
                }
                else {
                    this.b(this.o.c(i));
                }
            }
        }
    }
    
    public C42(final InputStream n, final URL url) throws IOException {
        super(n, url);
        this.s = true;
        this.n = n;
        final C32 c32 = new C32(0);
        c32.f("All Layers");
        this.b(new Integer(0), c32);
        super.s.addElement(c32);
    }
}
