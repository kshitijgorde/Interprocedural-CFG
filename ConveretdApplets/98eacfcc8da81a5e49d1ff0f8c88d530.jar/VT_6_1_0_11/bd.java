// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.EOFException;
import java.io.SequenceInputStream;
import java.util.Vector;
import java.util.NoSuchElementException;
import java.net.ProtocolException;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;
import java.io.ByteArrayInputStream;
import java.io.InterruptedIOException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

public final class bd implements cn, Cloneable
{
    private static final Hashtable o;
    private aj p;
    private bC q;
    cM a;
    int b;
    public InputStream c;
    private K r;
    private String s;
    private String t;
    private boolean u;
    private boolean v;
    int d;
    String e;
    String f;
    bf g;
    V h;
    private V w;
    int i;
    int j;
    byte[] k;
    boolean l;
    private IOException x;
    boolean m;
    boolean n;
    private byte[] y;
    private int z;
    private StringBuffer A;
    private boolean B;
    private boolean C;
    private boolean D;
    private q E;
    private boolean F;
    
    bd(final q q, final boolean u, final bC q2) {
        this.b = 0;
        this.r = null;
        this.d = 0;
        this.g = null;
        this.h = new V();
        this.w = new V();
        this.i = -1;
        this.j = 1;
        this.k = null;
        this.l = false;
        this.x = null;
        this.m = false;
        this.n = false;
        this.y = new byte[7];
        this.z = 0;
        this.A = new StringBuffer(400);
        this.B = false;
        this.C = true;
        this.D = false;
        this.E = null;
        this.F = false;
        this.p = q.a();
        this.s = q.b();
        this.t = q.c();
        this.u = u;
        this.q = q2;
        this.v = (q.e() != null);
        q2.a(this, q);
        this.r = q2.a(this);
        this.c = this.r;
    }
    
    bd(final q q, final InputStream c) {
        this.b = 0;
        this.r = null;
        this.d = 0;
        this.g = null;
        this.h = new V();
        this.w = new V();
        this.i = -1;
        this.j = 1;
        this.k = null;
        this.l = false;
        this.x = null;
        this.m = false;
        this.n = false;
        this.y = new byte[7];
        this.z = 0;
        this.A = new StringBuffer(400);
        this.B = false;
        this.C = true;
        this.D = false;
        this.E = null;
        this.F = false;
        this.p = q.a();
        this.s = q.b();
        this.t = q.c();
        this.u = false;
        this.q = null;
        this.v = (q.e() != null);
        this.c = c;
    }
    
    public final int a() {
        if (!this.l) {
            this.b(true);
        }
        return this.d;
    }
    
    public final String b() {
        if (!this.l) {
            this.b(true);
        }
        return this.e;
    }
    
    public final String c() {
        if (!this.l) {
            this.b(true);
        }
        return this.f;
    }
    
    final int d() {
        this.b(false);
        return this.d;
    }
    
    public final void a(final bf g) {
        this.g = g;
    }
    
    public final String a(final String s) {
        if (!this.l) {
            this.b(true);
        }
        return (String)this.h.a(s.trim());
    }
    
    private void a(final String s, final String s2) {
        this.h.a(s.trim(), s2.trim());
    }
    
    private void b(final String s) {
        this.h.b(s.trim());
    }
    
    public final synchronized byte[] e() {
        if (!this.l) {
            this.b(true);
        }
        if (this.k == null) {
            try {
                final InputStream c = this.c;
                if (this.i != 0) {
                    if (this.k == null) {
                        this.k = new byte[0];
                    }
                    int length = this.k.length;
                    try {
                        if (this.a("Content-Length") != null) {
                            int read = 0;
                            this.k = new byte[this.i];
                            do {
                                length += read;
                            } while ((read = c.read(this.k, length, this.i - length)) != -1 && length + read < this.i);
                        }
                        else {
                            int read2 = 0;
                            do {
                                length += read2;
                                this.k = bz.a(this.k, length + 1000);
                            } while ((read2 = c.read(this.k, length, 1000)) != -1);
                            this.k = bz.a(this.k, length);
                        }
                    }
                    catch (IOException ex) {
                        this.k = bz.a(this.k, length);
                        throw ex;
                    }
                    finally {
                        try {
                            c.close();
                        }
                        catch (IOException ex4) {}
                    }
                }
            }
            catch (InterruptedIOException ex2) {
                throw ex2;
            }
            catch (IOException ex3) {
                aF.a(2, "Resp:  (" + this.c.hashCode() + ")", ex3);
                try {
                    this.c.close();
                }
                catch (Exception ex5) {}
                throw ex3;
            }
            this.c.close();
        }
        return this.k;
    }
    
    public final synchronized InputStream f() {
        if (!this.l) {
            this.b(true);
        }
        if (this.k == null) {
            return this.c;
        }
        return new ByteArrayInputStream(this.k);
    }
    
    public final void a(final boolean b) {
        this.n = true;
    }
    
    private synchronized void b(boolean n) {
        if (this.l) {
            return;
        }
        if (this.x != null) {
            this.x.fillInStackTrace();
            throw this.x;
        }
        Label_0028: {
            break Label_0028;
            try {
                do {
                    this.h.clear();
                    final String b = this.b(this.c);
                    String nextToken = null;
                    final StringTokenizer stringTokenizer = new StringTokenizer(b, "\r\n");
                    if (aF.a(2)) {
                        new StringBuffer().append("Resp:  Parsing Response headers from Request \"").append(this.s).append(" ").append(this.t).append("\":  (").append(this.c.hashCode()).append(")\n\n").append(b).toString();
                    }
                    if (!b.regionMatches(true, 0, "HTTP/", 0, 5) && !b.regionMatches(true, 0, "HTTP ", 0, 5)) {
                        this.f = "HTTP/0.9";
                        this.d = 200;
                        this.e = "OK";
                        try {
                            this.k = b.getBytes("8859_1");
                            continue;
                        }
                        catch (UnsupportedEncodingException ex) {
                            throw new Error(ex.toString());
                        }
                    }
                    StringTokenizer stringTokenizer2;
                    try {
                        nextToken = stringTokenizer.nextToken();
                        stringTokenizer2 = new StringTokenizer(nextToken, " \t");
                        this.f = stringTokenizer2.nextToken();
                        this.d = Integer.valueOf(stringTokenizer2.nextToken());
                        if (this.f.equalsIgnoreCase("HTTP")) {
                            this.f = "HTTP/1.0";
                        }
                    }
                    catch (NoSuchElementException ex3) {
                        throw new ProtocolException("Invalid HTTP status line received: " + nextToken);
                    }
                    try {
                        this.e = stringTokenizer2.nextToken("").trim();
                    }
                    catch (NoSuchElementException ex4) {
                        this.e = "";
                    }
                    if (this.d >= 300 && this.v && this.q != null) {
                        this.q.b(this);
                    }
                    a(stringTokenizer, this.h);
                    if (this.h.a("Trailer") != null && this.r != null) {
                        this.r.a();
                    }
                    boolean b2;
                    if (this.f.equalsIgnoreCase("HTTP/0.9") || this.f.equalsIgnoreCase("HTTP/1.0")) {
                        b2 = false;
                    }
                    else {
                        b2 = true;
                    }
                    try {
                        final String s = (String)this.h.a("Connection");
                        final String s2 = (String)this.h.a("Proxy-Connection");
                        if (((!b2 || s == null || !bz.b(s, "close")) && (b2 || (!this.u && s != null && bz.b(s, "keep-alive")) || (this.u && s2 != null && bz.b(s2, "keep-alive")))) || this.q == null) {
                            continue;
                        }
                        this.q.b(this);
                    }
                    catch (dh dh) {}
                } while ((this.d == 100 && equalsIgnoreCase) || (this.d > 101 && this.d < 200));
            }
            catch (IOException ex2) {
                final IOException x = ex2;
                if (!(ex2 instanceof InterruptedIOException)) {
                    this.x = x;
                }
                if (x instanceof ProtocolException) {
                    this.j = 3;
                    if (this.q != null) {
                        this.q.b(this);
                    }
                }
                throw x;
            }
        }
        if (this.d == 100) {
            return;
        }
        int int1 = -1;
        if ((n = (int)this.h.a("Content-Length")) != null) {
            try {
                if ((int1 = Integer.parseInt((String)n)) < 0) {
                    throw new NumberFormatException();
                }
            }
            catch (NumberFormatException ex5) {
                throw new ProtocolException("Invalid Content-length header received: " + (String)n);
            }
        }
        equalsIgnoreCase = false;
        boolean b3 = true;
        boolean b4 = false;
        Vector a = null;
        try {
            a = bz.a((String)this.h.a("Transfer-Encoding"));
        }
        catch (dh dh2) {}
        if (a != null) {
            equalsIgnoreCase = a.lastElement().a().equalsIgnoreCase("chunked");
            for (int i = 0; i < a.size(); ++i) {
                if (a.elementAt(i).a().equalsIgnoreCase("identity")) {
                    a.removeElementAt(i--);
                }
                else {
                    b3 = false;
                }
            }
        }
        try {
            final String s3;
            if ((s3 = (String)this.h.a("Content-Type")) != null) {
                final Vector a2;
                b4 = ((a2 = bz.a(s3)).contains(new Q("multipart/byteranges")) || a2.contains(new Q("multipart/x-byteranges")));
            }
        }
        catch (dh dh3) {}
        if (this.d < 200 || this.d == 204 || this.d == 205 || this.d == 304) {
            this.j = 2;
        }
        else if (equalsIgnoreCase) {
            this.j = 5;
            a.removeElementAt(a.size() - 1);
            if (a.size() > 0) {
                this.a("Transfer-Encoding", bz.a(a));
            }
            else {
                this.b("Transfer-Encoding");
            }
        }
        else if (int1 != -1 && b3) {
            this.j = 4;
        }
        else if (b4 && b3) {
            this.j = 6;
        }
        else if (!this.s.equals("HEAD")) {
            this.j = 3;
            if (this.q != null) {
                this.q.b(this);
            }
            if (this.f.equals("HTTP/0.9")) {
                this.c = new SequenceInputStream(new ByteArrayInputStream(this.k), this.c);
                this.k = null;
            }
        }
        if (this.j == 4) {
            this.i = int1;
        }
        else {
            this.b("Content-Length");
        }
        if (this.s.equals("HEAD")) {
            this.j = 2;
        }
        if (this.j == 2) {
            this.i = 0;
            this.k = new byte[0];
            this.c.close();
        }
        new StringBuffer().append("Resp:  Response entity delimiter: ").append((this.j == 2) ? "No Entity" : ((this.j == 3) ? "Close" : ((this.j == 4) ? "Content-Length" : ((this.j == 5) ? "Chunked" : ((this.j == 6) ? "Multipart" : "???"))))).append(" (").append(this.c.hashCode()).append(")").toString();
        if (this.p.a >= 65537) {
            this.b("Proxy-Connection");
        }
        else {
            if (this.p.e() != null) {
                this.b("Connection");
            }
            else {
                this.b("Proxy-Connection");
            }
            Vector<Q> a3;
            try {
                a3 = (Vector<Q>)bz.a((String)this.h.a("Connection"));
            }
            catch (dh dh4) {
                a3 = null;
            }
            if (a3 != null) {
                for (int j = 0; j < a3.size(); ++j) {
                    final String a4;
                    if (!(a4 = a3.elementAt(j).a()).equalsIgnoreCase("keep-alive")) {
                        a3.removeElementAt(j);
                        this.b(a4);
                        --j;
                    }
                }
                if (a3.size() > 0) {
                    this.a("Connection", bz.a(a3));
                }
                else {
                    this.b("Connection");
                }
            }
            Vector<Q> a5;
            try {
                a5 = (Vector<Q>)bz.a((String)this.h.a("Proxy-Connection"));
            }
            catch (dh dh5) {
                a5 = null;
            }
            if (a5 != null) {
                for (int k = 0; k < a5.size(); ++k) {
                    final String a6;
                    if (!(a6 = a5.elementAt(k).a()).equalsIgnoreCase("keep-alive")) {
                        a5.removeElementAt(k);
                        this.b(a6);
                        --k;
                    }
                }
                if (a5.size() > 0) {
                    this.a("Proxy-Connection", bz.a(a5));
                }
                else {
                    this.b("Proxy-Connection");
                }
            }
        }
        this.l = true;
        if (this.F && !this.p.a(this)) {
            bd a7;
            try {
                a7 = this.p.a(this.E, this.b);
            }
            catch (bw bw) {
                throw new IOException(bw.toString());
            }
            a7.c();
            this.d = a7.d;
            this.e = a7.e;
            this.f = a7.f;
            this.g = a7.g;
            this.i = a7.i;
            this.h = a7.h;
            this.c = a7.c;
            this.k = a7.k;
            this.E = null;
        }
    }
    
    private String b(final InputStream inputStream) {
        if (this.z == 0) {
            new StringBuffer().append("Resp:  Reading Response headers ").append(this.c.hashCode()).toString();
        }
        else {
            new StringBuffer().append("Resp:  Resuming reading Response headers ").append(this.c.hashCode()).toString();
        }
        if (!this.B) {
            try {
                Label_0117: {
                    if (this.z == 0) {
                        int read;
                        while ((read = inputStream.read()) != -1) {
                            if (!Character.isWhitespace((char)read)) {
                                this.y[0] = (byte)read;
                                this.z = 1;
                                break Label_0117;
                            }
                        }
                        throw new EOFException("Encountered premature EOF while reading Version");
                    }
                }
                while (this.z < this.y.length) {
                    final int read2;
                    if ((read2 = inputStream.read(this.y, this.z, this.y.length - this.z)) == -1) {
                        throw new EOFException("Encountered premature EOF while reading Version");
                    }
                    this.z += read2;
                }
            }
            catch (EOFException ex) {
                aF.a(2, "Resp:  (" + this.c.hashCode() + ")", ex);
                throw ex;
            }
            for (int i = 0; i < this.y.length; ++i) {
                this.A.append((char)this.y[i]);
            }
            this.B = true;
        }
        if (this.A.toString().startsWith("HTTP/") || this.A.toString().startsWith("HTTP ")) {
            this.c(inputStream);
        }
        this.z = 0;
        this.B = false;
        this.C = true;
        this.D = false;
        final String string = this.A.toString();
        this.A.setLength(0);
        return string;
    }
    
    final void a(final InputStream inputStream) {
        try {
            this.c(inputStream);
        }
        catch (IOException ex) {
            final IOException x = ex;
            if (!(ex instanceof InterruptedIOException)) {
                this.x = x;
            }
            throw x;
        }
    }
    
    private void c(final InputStream inputStream) {
        while (true) {
            final int read;
            switch (read = inputStream.read()) {
                case -1: {
                    throw new EOFException("Encountered premature EOF while reading headers:\n" + (Object)this.A);
                }
                case 13: {
                    this.D = true;
                    continue;
                }
                case 10: {
                    if (!this.C) {
                        this.A.append('\n');
                        this.C = true;
                        this.D = false;
                        continue;
                    }
                    return;
                }
                case 9:
                case 32: {
                    if (this.C) {
                        this.A.setCharAt(this.A.length() - 1, ' ');
                        this.C = false;
                        continue;
                    }
                    break;
                }
            }
            if (this.D) {
                this.A.append('\r');
                this.D = false;
            }
            this.A.append((char)(read & 0xFF));
            this.C = false;
        }
    }
    
    private static void a(final StringTokenizer stringTokenizer, final V v) {
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken;
            int n;
            if ((n = (nextToken = stringTokenizer.nextToken()).indexOf(58)) == -1) {
                n = nextToken.indexOf(32);
            }
            if (n == -1) {
                throw new ProtocolException("Invalid HTTP header received: " + nextToken);
            }
            final String trim = nextToken.substring(0, n).trim();
            final String trim2 = nextToken.substring(n + 1).trim();
            if (!bd.o.containsKey(trim.toLowerCase())) {
                final String s;
                if ((s = (String)v.a(trim)) == null) {
                    v.a(trim, trim2);
                }
                else {
                    v.a(trim, s + ", " + trim2);
                }
            }
            else {
                v.a(trim, trim2);
            }
        }
    }
    
    final void a(final q e) {
        this.E = e;
        this.F = true;
    }
    
    public final Object clone() {
        bd bd;
        try {
            bd = (bd)super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError(ex.toString());
        }
        bd.h = (V)this.h.clone();
        bd.w = (V)this.w.clone();
        return bd;
    }
    
    static {
        final String[] array = { "age", "location", "content-base", "content-length", "content-location", "content-md5", "content-range", "content-type", "date", "etag", "expires", "proxy-authenticate", "retry-after" };
        o = new Hashtable(array.length);
        for (int i = 0; i < array.length; ++i) {
            bd.o.put(array[i], array[i]);
        }
    }
}
