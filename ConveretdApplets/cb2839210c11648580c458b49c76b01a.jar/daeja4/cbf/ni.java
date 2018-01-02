// 
// Decompiled by Procyon v0.5.30
// 

package daeja4.cbf;

import java.util.Vector;
import java.util.Date;

public class ni
{
    private int a;
    private String b;
    private int c;
    private int d;
    private int e;
    long f;
    long g;
    int h;
    private String i;
    private long j;
    private Date k;
    private long l;
    private long m;
    private boolean n;
    ni o;
    ni p;
    ni q;
    
    public ni(final byte[] array, final int n, final int a) {
        this.a = a;
        this.b = nh.h(array, 0, 64).trim();
        this.c = nh.a(array, 64, 2);
        this.d = nh.a(array, 66, 1);
        this.e = nh.a(array, 67, 1);
        this.f = nh.b(array, 68, 4);
        this.g = nh.b(array, 72, 4);
        nh.b(this.f);
        nh.b(this.g);
        Label_0201: {
            if (this.d != 1) {
                if (this.d != 5) {
                    this.h = -1;
                    this.i = null;
                    this.j = -1L;
                    this.k = null;
                    this.n = false;
                    break Label_0201;
                }
            }
            try {
                this.h = nh.a(array, 76, 4, "ChildSid");
            }
            catch (Exception ex) {
                this.h = -1;
                ex.printStackTrace();
            }
            this.i = nh.e(array, 80, 16);
            this.j = nh.b(array, 96, 4);
            this.k = nh.f(array, 100, 16);
        }
        if (this.d == 2 || this.d == 5) {
            this.l = nh.b(array, 116, 4);
            this.m = nh.b(array, 120, 4);
            this.n = (this.m <= n);
        }
        else {
            this.l = -1L;
            this.m = 0L;
        }
    }
    
    public int a() {
        return this.a;
    }
    
    public String b() {
        return this.b;
    }
    
    public int c() {
        return (int)this.f;
    }
    
    public int d() {
        return (int)this.g;
    }
    
    public ni e() {
        return this.q;
    }
    
    public String f() {
        return this.i;
    }
    
    public long g() {
        return this.l;
    }
    
    public long h() {
        return this.m;
    }
    
    public String i() {
        String s = null;
        switch (this.d) {
            case 0: {
                s = "INVALID";
                break;
            }
            case 1: {
                s = "STORAGE";
                break;
            }
            case 2: {
                s = "STREAM";
                break;
            }
            case 3: {
                s = "LOCKBYTES";
                break;
            }
            case 4: {
                s = "PROPERTY";
                break;
            }
            case 5: {
                s = "ROOT";
                break;
            }
            default: {
                s = "INVALID";
                break;
            }
        }
        return s;
    }
    
    public String j() {
        return (this.e == 0) ? "RED" : "BLACK";
    }
    
    public boolean k() {
        return this.n;
    }
    
    public String toString() {
        return this.a(0, true, true, false);
    }
    
    public String a(final int n, final boolean b, final boolean b2, final boolean b3) {
        final StringBuffer sb = new StringBuffer();
        if (b && b3) {
            final Vector a = nl.a(this);
            for (int size = a.size(), i = 0; i < size; ++i) {
                sb.append(a.elementAt(i).a(n + 1, b2, b3));
            }
        }
        else {
            sb.append(this.a(n, b2, b3));
        }
        return sb.toString();
    }
    
    public String a(final int n, final boolean b, final boolean b2) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            sb.append("\t");
        }
        final String string = sb.toString();
        final StringBuffer sb2 = new StringBuffer("\n");
        sb2.append(string).append(this.a);
        if (b) {
            sb2.append(":  jiDirectory:  ");
        }
        sb2.append("; name=").append(this.b);
        final String value = String.valueOf(String.valueOf(new StringBuffer("\n;").append(string).append("\t")));
        if (b) {
            sb2.append(value).append("elementCharNameLength=").append(this.c).append(value).append("objectType=").append(this.i()).append(value).append("redBlack=").append(this.j()).append(value).append("leftSiblingSid=");
            nh.b(sb2, this.f).append(value).append("rightSiblingSid=");
            nh.b(sb2, this.g).append(value).append("miniStream=").append(this.k()).append(value).append("childSid=");
            nh.b(sb2, this.h).append(value).append("clsid=").append(this.i).append(value).append("userFlags=").append(this.j).append(value).append("streamSect=").append(this.l).append(value).append("streamLength=").append(this.m).append(value).append("timestamp=").append(this.k).append(value);
        }
        if (b2 && this.q != null) {
            sb2.append(this.q.a(n + 1, true, b, b2));
        }
        return sb2.toString();
    }
    
    public void a(final String s, final Vector vector) {
        final Vector a = nl.a(this);
        for (int size = a.size(), i = 0; i < size; ++i) {
            final ni ni = a.elementAt(i);
            if (ni.b().startsWith(s)) {
                vector.addElement(ni);
            }
        }
    }
    
    public ni a(final String s) {
        ni ni = null;
        final Vector a = nl.a(this.q);
        for (int size = a.size(), i = 0; i < size; ++i) {
            ni = a.elementAt(i);
            if (s.equals(ni.b())) {
                break;
            }
            ni = null;
        }
        return ni;
    }
}
