// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.net.HttpURLConnection;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.net.Authenticator;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Proxy;
import java.net.URLConnection;
import java.net.URL;

public class aq extends Thread
{
    public int else;
    int B;
    URL case;
    URLConnection o;
    URL try;
    URLConnection v;
    Proxy J;
    InetAddress H;
    t I;
    t int;
    bc z;
    webphone b;
    boolean m;
    boolean E;
    aw q;
    InputStream for;
    OutputStream r;
    InputStream char;
    OutputStream Q;
    String K;
    int j;
    int u;
    int y;
    int L;
    static int f;
    byte[] F;
    boolean i;
    boolean byte;
    boolean new;
    boolean d;
    boolean P;
    long T;
    int c;
    String if;
    int g;
    int s;
    String l;
    String O;
    String h;
    String C;
    byte[] M;
    byte[] void;
    byte[] x;
    int k;
    private Object D;
    long N;
    long G;
    int p;
    boolean e;
    int long;
    long goto;
    boolean S;
    byte[] w;
    int n;
    byte[] a;
    int R;
    byte[] do;
    int t;
    long A;
    
    public aq(final t t) {
        this.else = 0;
        this.B = 9999999;
        this.case = null;
        this.o = null;
        this.try = null;
        this.v = null;
        this.J = null;
        this.H = null;
        this.I = null;
        this.int = null;
        this.z = null;
        this.b = null;
        this.m = false;
        this.E = false;
        this.q = null;
        this.for = null;
        this.r = null;
        this.char = null;
        this.Q = null;
        this.K = "";
        this.j = 1;
        this.u = 0;
        this.y = 1;
        this.L = 10000;
        this.F = null;
        this.i = false;
        this.byte = false;
        this.new = false;
        this.d = false;
        this.P = false;
        this.T = 0L;
        this.c = 5000;
        this.if = "";
        this.g = 0;
        this.s = 0;
        this.l = "";
        this.O = "";
        this.h = "";
        this.C = "";
        this.M = null;
        this.void = null;
        this.x = null;
        this.k = 0;
        this.D = null;
        this.N = 0L;
        this.G = 0L;
        this.p = 0;
        this.e = false;
        this.long = 0;
        this.goto = 0L;
        this.S = false;
        this.w = new byte[this.L + 650];
        this.n = 0;
        this.a = new byte[this.L + 650];
        this.R = 0;
        this.do = new byte[3200];
        this.t = 0;
        this.A = 0L;
        this.else = 0;
        this.a(t);
        this.z = t.O;
        this.m = false;
        this.E = false;
        this.q = t.d;
        this.b = this.q.ct;
        this.if();
    }
    
    public aq(final bc z) {
        this.else = 0;
        this.B = 9999999;
        this.case = null;
        this.o = null;
        this.try = null;
        this.v = null;
        this.J = null;
        this.H = null;
        this.I = null;
        this.int = null;
        this.z = null;
        this.b = null;
        this.m = false;
        this.E = false;
        this.q = null;
        this.for = null;
        this.r = null;
        this.char = null;
        this.Q = null;
        this.K = "";
        this.j = 1;
        this.u = 0;
        this.y = 1;
        this.L = 10000;
        this.F = null;
        this.i = false;
        this.byte = false;
        this.new = false;
        this.d = false;
        this.P = false;
        this.T = 0L;
        this.c = 5000;
        this.if = "";
        this.g = 0;
        this.s = 0;
        this.l = "";
        this.O = "";
        this.h = "";
        this.C = "";
        this.M = null;
        this.void = null;
        this.x = null;
        this.k = 0;
        this.D = null;
        this.N = 0L;
        this.G = 0L;
        this.p = 0;
        this.e = false;
        this.long = 0;
        this.goto = 0L;
        this.S = false;
        this.w = new byte[this.L + 650];
        this.n = 0;
        this.a = new byte[this.L + 650];
        this.R = 0;
        this.do = new byte[3200];
        this.t = 0;
        this.A = 0L;
        this.else = 0;
        this.z = z;
        this.m = false;
        this.E = false;
        this.q = z.f;
        this.b = this.q.ct;
        this.if();
    }
    
    public void if() {
        try {
            this.B = this.q.e3;
            this.y = this.q.for(2, 9999999);
            this.M = new byte[this.L + 650];
            this.void = new byte[this.L + 650];
            this.x = new byte[this.L + 650];
            this.D = new Object();
            this.k = 0;
            if (this.B > 9999999) {
                this.B = this.q.a(6999999, 9999999);
            }
            if (this.b.serverdomainandport.length() > 0 && this.b.serveraddr.length() > 0 && this.b.proxyaddr.length() > 0 && (!this.b.serveraddr.equals(this.b.proxyaddr) || this.b.serverport != this.b.proxyport)) {
                this.l = this.b.serverdomainandport;
            }
            this.q.a(5, "EVENT,http module initialized");
        }
        catch (Exception ex) {
            this.q.a(3, "http Init", ex);
        }
    }
    
    public void new() {
        this.E = true;
        this.int();
    }
    
    public void int() {
        try {
            this.else = 0;
            this.m = false;
            if (this.o != null) {
                try {
                    this.o.setReadTimeout(100);
                }
                catch (Exception ex2) {}
            }
            if (this.v != null) {
                try {
                    this.v.setReadTimeout(100);
                }
                catch (Exception ex3) {}
            }
            if (this.for != null) {
                try {
                    this.for.close();
                }
                catch (Exception ex4) {}
            }
            if (this.r != null) {
                try {
                    this.r.close();
                }
                catch (Exception ex5) {}
            }
            if (this.char != null) {
                try {
                    this.char.close();
                }
                catch (Exception ex6) {}
            }
            if (this.Q != null) {
                try {
                    this.Q.close();
                }
                catch (Exception ex7) {}
            }
            this.case = null;
            this.o = null;
            this.try = null;
            this.v = null;
            this.for = null;
            this.r = null;
            this.char = null;
            this.Q = null;
            this.m = false;
        }
        catch (Exception ex) {
            this.q.a(3, "http destroy", ex);
        }
    }
    
    public int a(String k, int j, final int n) {
        try {
            this.int();
            this.q.a(4, "EVENT,http init with " + k + ":" + this.q.c(j) + " " + this.q.c(n));
            final String s = k;
            final int n2 = j;
            if (this.q.dT > 0 && this.q.dK == 3) {
                j = this.q.dT;
            }
            if (this.q.eG > 0) {
                try {
                    System.setProperty("java.net.useSystemProxies", "true");
                }
                catch (Exception ex) {
                    this.q.if(3, "SetUseSystemProxies", ex);
                }
                Authenticator.setDefault(new be(this.q));
            }
            if (this.q.eG > 0 && this.q.eG != 4) {
                try {
                    k = this.q.byte(k, k);
                    j = this.q.if(j, k);
                    if (this.q.eG == 3 && this.q.db.length() > 0) {
                        this.J = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(k, j));
                    }
                }
                catch (Exception ex2) {
                    this.q.a(3, "set http proxy", ex2);
                }
            }
            if (this.q.dK == 3 && this.q.fm.length() > 0) {
                if (this.q.fm.indexOf(":") > 0) {
                    k = this.q.fm.substring(0, this.q.fm.indexOf(":")).trim();
                    j = this.q.for(this.q.fm.substring(this.q.fm.indexOf(":") + 1).trim(), 5060);
                }
                else {
                    k = this.q.fm;
                    j = 5060;
                }
            }
            if (this.q.dT > 0) {
                j = this.q.dT;
            }
            if (j < 1 || k.length() < 2) {
                k = s;
                j = n2;
            }
            this.K = k;
            this.j = j;
            this.u = n;
            this.g = 0;
            if (n > 0) {
                this.else = n;
            }
            else {
                this.else = 8888;
            }
            this.O = k + ":" + this.q.c(j);
            if (k.length() < 5 || k.charAt(0) != 'h' || k.charAt(1) != 't') {
                this.O = "http://" + this.O;
            }
            this.q.a(3, "EVENT,http main url is " + this.O);
            this.h = this.O + "/s?b=2";
            this.case = new URL(this.h);
            this.C = this.O + "/r?a=1";
            this.try = new URL(this.C);
            this.q.di = true;
            this.d = true;
            this.m = true;
            return this.else;
        }
        catch (Exception ex3) {
            this.q.if(3, "http init", ex3);
            this.q.a(2, "ERROR,http cannot connect to " + k + ":" + this.q.c(j) + " from " + this.q.c(n));
            return this.g = 0;
        }
    }
    
    public boolean a(final String s, final int n, final String s2, final t t, final boolean b) {
        try {
            final byte[] bytes = s2.getBytes();
            return this.a(s, n, bytes, bytes.length, t, b);
        }
        catch (Exception ex) {
            this.q.a(3, "http send string", ex);
            return false;
        }
    }
    
    void a(final t t) {
        if (t == null) {
            return;
        }
        if (this.I != t) {
            this.I = t;
        }
        if (this.int != null) {
            if (this.int == t) {
                return;
            }
            final int au = t.aU;
            final aw q = this.q;
            if (au < 7) {
                return;
            }
            final int au2 = t.aU;
            final aw q2 = this.q;
            if (au2 >= 15) {
                return;
            }
        }
        this.int = t;
    }
    
    public boolean a(final String s, int dt, final byte[] array, final int n, final t t, final boolean b) {
        int n2 = 0;
        try {
            n2 = 1;
            if (n < 1 || n > this.L || array == null) {
                this.q.a(4, "WARNING, http dropping 1 " + this.q.c(n));
                return false;
            }
            n2 = 2;
            if (t != null && t.aU >= 15 && t == this.int) {
                this.int = null;
            }
            n2 = 3;
            if (b && t != null && t.aU > 15) {
                return false;
            }
            n2 = 4;
            this.a(t);
            n2 = 5;
            if (this.q.dT > 0 && this.q.dK == 3) {
                dt = this.q.dT;
            }
            String o = s + ":" + this.q.c(dt);
            if (s.length() < 5 || s.charAt(0) != 'h' || s.charAt(1) != 't') {
                o = "http://" + o;
            }
            if (dt > 0 && s.length() > 0 && !this.O.equals(o)) {
                this.O = o;
            }
            if (this.k + n > this.L) {
                this.q.a(4, "WARNING, http dropping 3 " + this.q.c(n));
                this.k = 0;
            }
            n2 = 6;
            this.k = 0;
            if (b) {
                n2 = 7;
                this.T = this.q.do();
                if (this.k < 1 || this.void[0] == 103) {
                    n2 = 8;
                    System.arraycopy(array, 0, this.void, this.k, n);
                    this.k += n;
                }
                else {
                    this.q.a(4, "WARNING, http dropping 6 " + this.q.c(n));
                }
            }
            else {
                n2 = 9;
                System.arraycopy(array, 0, this.void, this.k, n);
                this.k += n;
            }
            n2 = 10;
            if (!b) {
                this.N = this.q.do();
            }
            return this.for(this.void, this.k);
        }
        catch (Exception ex) {
            this.q.a(3, "http add buff " + this.q.c(n2), ex);
            return false;
        }
    }
    
    public void run() {
        if (this.E) {
            return;
        }
        int n = 0;
        try {
            this.setPriority(10);
        }
        catch (Exception ex4) {}
        final byte[] array = { 76, 0, 0 };
        this.q.a(4, "EVENT,http run with streaming set to " + this.q.c(this.q.dP));
        while (!this.E) {
            try {
                if (!this.d) {
                    Thread.sleep(1L);
                }
                else if (!this.P) {
                    Thread.sleep(1L);
                }
                else {
                    n = 1001;
                    this.q.bZ = false;
                    if (this.q.eK > 4 && this.q.dP > 5) {
                        this.q.a(5, "EVENT, http rec connect " + this.q.c(this.q.dP) + " to " + this.O);
                    }
                    if (this.v != null) {
                        try {
                            this.v.setReadTimeout(100);
                        }
                        catch (Exception ex5) {}
                    }
                    if (this.char != null) {
                        this.char.close();
                        this.char = null;
                    }
                    if (this.Q != null) {
                        this.Q.close();
                        this.Q = null;
                    }
                    if (this.v != null) {
                        try {
                            ((HttpURLConnection)this.v).disconnect();
                        }
                        catch (Exception ex) {
                            this.q.a(4, "http rec disconnect disconnect", ex);
                        }
                        this.v = null;
                    }
                    n = 1002;
                    if (this.try == null || !this.C.equals(this.O + "/r?a=1")) {
                        n = 1003;
                        this.C = this.O + "/r?a=1";
                        this.q.a(3, "EVENT,http creating new url2 " + this.C);
                        this.try = new URL(this.O);
                    }
                    if (this.try == null) {
                        this.q.a(3, "EVENT,null url2 " + this.O);
                        if (this.E) {
                            continue;
                        }
                        Thread.sleep(100L);
                    }
                    else {
                        n = 1004;
                        if (this.J == null) {
                            n = 1005;
                            this.v = this.try.openConnection();
                        }
                        else {
                            n = 1006;
                            this.v = this.try.openConnection(this.J);
                        }
                        if (this.v == null) {
                            if (this.E) {
                                continue;
                            }
                            Thread.sleep(100L);
                        }
                        else {
                            try {
                                n = 1007;
                                this.G = this.q.do();
                                this.v.setDoInput(true);
                                if (this.q.aC) {
                                    this.v.setDoOutput(false);
                                }
                                else {
                                    this.v.setDoOutput(true);
                                }
                                this.v.setDefaultUseCaches(false);
                                this.v.setUseCaches(false);
                                if (((this.p > 3 && this.p < 6) || (this.p > 15 && this.p < 23)) && this.q.cH.length() > 0 && this.q.aI.length() > 0) {
                                    this.v.setRequestProperty("Proxy-Authorization", "Basic " + new String(cf.if(this.q.aI + ":" + this.q.cH)));
                                    if (this.q.eK > 4) {
                                        this.q.a(5, "EVENT, http rec auto send auth");
                                    }
                                }
                                n = 1008;
                                if (this.T != 0L && this.q.do() - this.T < 10000L) {
                                    this.c = 2000;
                                }
                                else {
                                    this.c = 12000;
                                }
                                if (this.q.dP < 5) {
                                    this.v.setReadTimeout(this.c);
                                }
                                n = 1009;
                                this.v.setConnectTimeout(this.c);
                                this.v.setAllowUserInteraction(false);
                                if (this.q.dP > 5) {
                                    this.v.setRequestProperty("User-Agent", "Mozilla/5.0 (rv:" + this.q.c(this.y) + ")");
                                }
                                else {
                                    this.v.setRequestProperty("User-Agent", "Mozilla/5.0 (SV;" + this.q.c(this.y) + ")");
                                }
                                this.v.setRequestProperty("Content-Type", "application/octet-stream");
                                ((HttpURLConnection)this.v).setInstanceFollowRedirects(true);
                                n = 1010;
                            }
                            catch (Exception ex2) {
                                this.q.a(3, "set url2 properties " + this.q.c(n), ex2);
                            }
                            if (this.q.eK > 5 && this.q.dP > 5) {
                                this.q.a(4, "EVENT, http rec connect1");
                            }
                            n = 1009;
                            if (!this.q.aC) {
                                this.Q = this.v.getOutputStream();
                                if (this.q.eK > 5 && this.q.dP > 5) {
                                    this.q.a(4, "EVENT, http rec connect2");
                                }
                                if (this.q.dP > 5) {
                                    this.q.do(1L);
                                }
                                n = 1011;
                                this.Q.write(array, 0, 1);
                                if (this.q.eK > 5 && this.q.dP > 5) {
                                    this.q.a(4, "EVENT, http rec connect3");
                                }
                                n = 1010;
                                this.Q.flush();
                                this.Q.close();
                            }
                            this.G = this.q.do();
                            if (this.q.eK > 5 && this.q.dP > 5) {
                                this.q.a(4, "EVENT, http rec connect4");
                            }
                            this.Q = null;
                            n = 1011;
                            if (this.E) {
                                break;
                            }
                            if (this.q.eK > 5 && this.q.dP > 5) {
                                this.q.a(4, "EVENT, http rec connect5");
                            }
                            this.char = this.v.getInputStream();
                            n = 1012;
                            if (this.char == null) {
                                n = 1013;
                                this.q.a(4, "WARNING, http receiver cannot obtain input");
                                if (this.E) {
                                    continue;
                                }
                                Thread.sleep(100L);
                            }
                            else {
                                n = 1014;
                                if (this.q.eK > 6 && this.q.dP > 5) {
                                    this.q.a(4, "EVENT, http rec connect6");
                                }
                                this.q.do();
                                while (!this.E) {
                                    n = 1015;
                                    if (this.q.bZ) {
                                        this.q.a(5, "EVENT, http reconnecting1 with " + this.q.c(this.q.dP));
                                        this.q.bZ = false;
                                        break;
                                    }
                                    this.G = this.q.do();
                                    final int read = this.char.read(this.M, 0, this.L - 10);
                                    this.G = this.q.do();
                                    if (read > this.L) {
                                        this.q.a(4, "WARNING, http dropping 8 " + this.q.c(read));
                                    }
                                    else {
                                        n = 1016;
                                        if (this.E) {
                                            break;
                                        }
                                        if (read < 1) {
                                            if (this.q.aX != 3 && this.q.dP > 5) {
                                                this.q.a(4, "WARNING, http receiver stream finished");
                                                break;
                                            }
                                            break;
                                        }
                                        else {
                                            if (!this.byte) {
                                                this.byte = true;
                                                this.q.a(4, "EVENT,first http answer received on receiver thread " + this.q.c(read));
                                            }
                                            n = 1017;
                                            if (read != 1 || this.M[0] != 84) {
                                                synchronized (this.D) {
                                                    this.do(this.M, read);
                                                }
                                            }
                                            n = 1018;
                                        }
                                    }
                                }
                                n = 1019;
                                try {
                                    if (this.q.eK > 4 && this.q.dP > 5) {
                                        this.q.a(4, "WARNING, http rec close");
                                    }
                                    this.char.close();
                                    this.char = null;
                                }
                                catch (Exception ex6) {}
                                n = 1020;
                            }
                        }
                    }
                }
            }
            catch (Exception ex3) {
                final String lowerCase = ex3.getMessage().toLowerCase();
                boolean b = false;
                if (lowerCase != null && lowerCase.indexOf("ead timed") > 0) {
                    if (this.q.dP > 5) {
                        this.q.a(4, "EVENT, http receiver timeout. reinit." + this.q.c(n) + " " + this.q.c(this.c) + " " + this.q.if(this.q.do() - this.G) + " " + lowerCase);
                    }
                    else {
                        b = true;
                    }
                }
                else {
                    this.q.if(3, "http rec " + this.q.c(n), ex3);
                    try {
                        if (!this.E) {
                            Thread.sleep(1L);
                        }
                    }
                    catch (Exception ex7) {}
                }
                if (!b && lowerCase.indexOf("ocket closed") < 0) {
                    try {
                        this.q.a(5, "EVENT, http read errorstream");
                        final int responseCode = ((HttpURLConnection)this.v).getResponseCode();
                        this.q.a(4, "EVENT, http response code is " + this.q.c(responseCode));
                        final InputStream errorStream = ((HttpURLConnection)this.v).getErrorStream();
                        for (int n2 = 0; n2 < 10 && errorStream.read(this.M) > 0; ++n2) {}
                        if (responseCode == 401 || responseCode == 407) {
                            ++this.p;
                            if ((this.p > 2 && this.p < 5) || (this.p > 10 && this.p < 15)) {
                                this.q.cH = "";
                            }
                        }
                        errorStream.close();
                        this.q.a(5, "EVENT, http read errorstream ready");
                    }
                    catch (Exception ex8) {
                        this.q.if(3, "http rec read errorstream", ex3);
                    }
                }
                this.G = this.q.do();
            }
        }
        this.new();
    }
    
    public boolean for(final byte[] array, final int n) {
        if (this.q.eK > 7) {
            this.q.a(4, "EVENT, http sending " + this.q.c(n) + " bytes (" + this.q.c(this.q.dP) + ")");
        }
        final long do1 = this.q.do();
        boolean b;
        if (this.q.dP != 1) {
            b = this.if(array, n);
            if (!b) {
                b = this.if(array, n);
            }
            if (!b) {
                this.try();
                b = this.if(array, n);
            }
        }
        else {
            b = this.new(array, n);
        }
        if (this.q.do() - do1 > 2000L) {
            this.q.a(4, "WARNING, http send took " + this.q.if((this.q.do() - do1) / 1000L) + " sec");
        }
        return b;
    }
    
    public boolean a(final int n) {
        boolean b = false;
        this.q.a(5, "EVENT, http proxy connect a " + this.q.c(n));
        final byte[] array = new byte[5100];
        Label_1406: {
            try {
                final byte[] array2 = { 111, 112, 13, 10, 0, 0 };
                if (this.J == null) {
                    this.o = this.case.openConnection();
                }
                else {
                    this.o = this.case.openConnection(this.J);
                }
                this.q.a(5, "EVENT, http proxy connect b " + this.q.c(n));
                try {
                    this.o.setDoInput(true);
                    this.o.setDoOutput(true);
                    this.o.setDefaultUseCaches(false);
                    this.o.setUseCaches(false);
                    this.o.setReadTimeout(8000);
                    this.o.setConnectTimeout(11000);
                    this.o.setAllowUserInteraction(false);
                    if ((this.p > 3 && this.p < 6) || (this.p > 15 && this.p < 23)) {
                        if ((this.q.cH.length() < 1 || this.q.aI.length() < 1) && !this.S) {
                            this.S = true;
                            this.b.asyncneedproxyauthui = 1;
                            for (int i = 0; i < 1800; ++i) {
                                this.q.do(100L);
                                if (this.b.asyncneedproxyauthui == 0) {
                                    this.q.a(3, "WARNING,http proxydetect auth ui err");
                                    break;
                                }
                                if (this.b.asyncneedproxyauthui == 3) {
                                    this.q.a(3, "WARNING,http proxydetect auth done");
                                    break;
                                }
                                if (this.b.asyncneedproxyauthui == 4) {
                                    this.q.a(3, "WARNING,http proxydetect auth canceled");
                                    break;
                                }
                            }
                        }
                        if (this.q.aI.length() > 0 && this.q.cH.length() > 0) {
                            this.o.setRequestProperty("Proxy-Authorization", "Basic " + new String(cf.if(this.q.aI + ":" + this.q.cH)));
                            if (this.q.eK > 4) {
                                this.q.a(5, "EVENT, http send auto send proxy auth");
                            }
                        }
                    }
                    this.o.setRequestProperty("User-Agent", "Mozilla/5.0 (NT;" + this.q.c(this.y) + ")");
                    this.o.setRequestProperty("Content-Type", "application/octet-stream");
                    ((HttpURLConnection)this.o).setInstanceFollowRedirects(true);
                }
                catch (Exception ex) {
                    this.q.a(3, "set url proxy connect properties", ex);
                }
                this.q.a(5, "EVENT, http proxy connect c " + this.q.c(n));
                this.r = this.o.getOutputStream();
                this.q.a(5, "EVENT, http proxy connect d " + this.q.c(n));
                this.q.do(1L);
                this.r.write(array2, 0, 4);
                this.q.a(5, "EVENT, http proxy connect e " + this.q.c(n));
                this.r.flush();
                this.r.close();
                this.r = null;
                this.q.a(5, "EVENT, http proxy connect f " + this.q.c(n));
                this.for = this.o.getInputStream();
                this.q.a(5, "EVENT, http proxy connect g " + this.q.c(n));
                int n2 = 0;
                if (this.for != null) {
                    for (int j = 0; j < 100; ++j) {
                        final int read = this.for.read(array, 0, 5000);
                        if (read < 1) {
                            break;
                        }
                        b = true;
                        this.q.a(5, "EVENT, http proxy connect read " + this.q.c(read));
                        n2 += read;
                    }
                }
                else {
                    this.q.a(5, "WARNING, http proxy connect failed because no input stream");
                }
                if (b) {
                    this.q.a(5, "EVENT, http proxy connect succeed");
                }
                else {
                    this.q.a(5, "WARNING, http proxy connect failed");
                }
            }
            catch (Exception ex2) {
                b = false;
                this.q.a(3, "htt send proxyconnect", ex2);
                int responseCode = 0;
                try {
                    responseCode = ((HttpURLConnection)this.o).getResponseCode();
                    this.q.a(4, "EVENT, http proxy init response code is " + this.q.c(responseCode) + " " + ((HttpURLConnection)this.o).getResponseMessage());
                    final InputStream errorStream = ((HttpURLConnection)this.o).getErrorStream();
                    int n3 = 0;
                    for (int k = 0; k < 10; ++k) {
                        final int read2 = errorStream.read(array);
                        if (read2 <= 0) {
                            break;
                        }
                        n3 += read2;
                    }
                    String string = "";
                    for (final Map.Entry<String, List<String>> entry : ((HttpURLConnection)this.o).getHeaderFields().entrySet()) {
                        string = string + (Object)entry.getKey() + " : " + entry.getValue() + "\r\n";
                    }
                    this.q.a(4, "EVENT, http errstream rec: " + string + "\r\n" + new String(array, 0, n3));
                    if (responseCode == 407 || responseCode == 401) {
                        ++this.p;
                        if ((this.p > 2 && this.p < 5) || (this.p > 10 && this.p < 15)) {
                            this.q.cH = "";
                        }
                    }
                    errorStream.close();
                }
                catch (Exception ex3) {
                    if (responseCode == 407 || responseCode == 401) {
                        ++this.p;
                    }
                    if ((this.p <= 2 || this.p >= 5) && (this.p <= 10 || this.p >= 15)) {
                        break Label_1406;
                    }
                    this.q.cH = "";
                }
            }
        }
        if (this.for != null) {
            try {
                this.for.close();
            }
            catch (Exception ex4) {}
            this.for = null;
        }
        if (this.r != null) {
            try {
                this.r.close();
            }
            catch (Exception ex5) {}
            this.r = null;
        }
        this.o = null;
        return b;
    }
    
    public boolean try() {
        try {
            this.q.a(5, "EVENT, http connecting");
            this.goto = this.q.do();
            this.P = false;
            this.a();
            if (this.case == null || !this.h.equals(this.O + "/s?b=2")) {
                this.q.a(3, "EVENT,http creating new url " + this.O);
                this.h = this.O + "/s?b=2";
                this.case = new URL(this.h);
            }
            for (int i = 0; i < 10; ++i) {
                if (this.a(i)) {
                    break;
                }
                if (this.E) {
                    break;
                }
            }
            this.q.a(5, "EVENT, http connect to " + this.h);
            if (this.J == null) {
                this.o = this.case.openConnection();
            }
            else {
                this.o = this.case.openConnection(this.J);
            }
            try {
                this.o.setDoInput(false);
                this.o.setDoOutput(true);
                this.o.setDefaultUseCaches(false);
                this.o.setUseCaches(false);
                this.o.setConnectTimeout(7000);
                this.o.setAllowUserInteraction(false);
                if (((this.p > 3 && this.p < 6) || (this.p > 15 && this.p < 23)) && this.q.cH.length() > 0 && this.q.aI.length() > 0) {
                    this.o.setRequestProperty("Proxy-Authorization", "Basic " + new String(cf.if(this.q.aI + ":" + this.q.cH)));
                    if (this.q.eK > 4) {
                        this.q.a(5, "EVENT, http send auto send auth");
                    }
                }
                this.o.setRequestProperty("User-Agent", "Mozilla/5.0 (NT;" + this.q.c(this.y) + ")");
                this.o.setRequestProperty("Content-Type", "application/octet-stream");
                ((HttpURLConnection)this.o).setFixedLengthStreamingMode(this.B);
                ((HttpURLConnection)this.o).setInstanceFollowRedirects(true);
            }
            catch (Exception ex) {
                this.q.a(3, "set url properties", ex);
            }
            this.r = this.o.getOutputStream();
            this.long = 0;
            this.e = true;
            this.goto = this.q.do();
            this.q.do(1L);
            this.q.do(0L);
            this.q.do(1L);
            this.q.a(5, "EVENT, http connected to " + this.h);
            return true;
        }
        catch (Exception ex2) {
            this.q.a(3, "htt send connect", ex2);
            this.a();
            return false;
        }
    }
    
    public void case() {
        if (this.Q != null) {
            this.q.a(5, "EVENT, http rec disconnect");
        }
        if (this.v != null) {
            try {
                this.v.setReadTimeout(100);
            }
            catch (Exception ex) {
                this.q.a(4, "http rec disconnect set readtimeout", ex);
            }
        }
        if (this.Q != null) {
            try {
                this.Q.close();
            }
            catch (Exception ex2) {
                this.q.a(4, "http rec disconnect output", ex2);
            }
            this.Q = null;
        }
        if (this.char != null) {
            try {
                this.char.close();
            }
            catch (Exception ex3) {
                this.q.a(4, "http rec disconnect input", ex3);
            }
            this.char = null;
        }
        if (this.v != null) {
            try {
                ((HttpURLConnection)this.v).disconnect();
            }
            catch (Exception ex4) {
                this.q.a(4, "http rec disconnect disconnect", ex4);
            }
            this.v = null;
        }
    }
    
    public void a() {
        this.long = 0;
        this.e = false;
        this.P = false;
        if (this.r != null) {
            this.q.a(5, "EVENT, http send disconnect");
        }
        if (this.o != null) {
            try {
                this.o.setReadTimeout(100);
            }
            catch (Exception ex) {
                this.q.a(4, "http send disconnect set readtimeout", ex);
            }
        }
        if (this.r != null) {
            try {
                this.r.close();
            }
            catch (Exception ex2) {
                this.q.a(4, "http send disconnect output", ex2);
            }
            this.r = null;
        }
        if (this.for != null) {
            try {
                this.for.close();
            }
            catch (Exception ex3) {
                this.q.a(4, "http send disconnect input", ex3);
            }
            this.for = null;
        }
        if (this.o != null) {
            try {
                ((HttpURLConnection)this.o).disconnect();
            }
            catch (Exception ex4) {
                this.q.a(4, "http send disconnect disconnect", ex4);
            }
            this.o = null;
        }
        this.long = 0;
        this.e = false;
    }
    
    public boolean if(final byte[] array, final int n) {
        boolean try1 = false;
        try {
            if (n < 1 || n > this.B || n > this.L || array == null) {
                return false;
            }
            if (this.q.eK > 7) {
                this.q.a(5, "EVENT, http send xxx 1 (" + this.q.c(n) + ")");
            }
            if (this.long + n > this.B && this.e && this.r != null) {
                if (this.q.eK > 7) {
                    this.q.a(5, "EVENT, http send xxx 2 (" + this.q.c(n) + ")");
                }
                if (this.B > 10000 && this.q.eK > 4) {
                    this.q.a(5, "EVENT, http send dumb packet");
                }
                try {
                    final int n2 = this.B - this.long;
                    final byte[] array2 = new byte[n2];
                    for (int i = 0; i < n2; ++i) {
                        array2[i] = 0;
                    }
                    this.r.write(array2, 0, n2);
                    this.r.flush();
                }
                catch (Exception ex) {
                    this.q.a(4, "http send dumb packet", ex);
                }
                this.a();
            }
            if (!this.e || this.r == null || (this.q.ef && this.q.do() - this.goto > 3500L)) {
                if (this.q.eK > 7) {
                    this.q.a(5, "EVENT, http send xxx 3 (" + this.q.c(n) + ")");
                }
                this.q.ef = false;
                if (this.q.bZ) {
                    this.q.a(5, "EVENT, http reconnecting2 with " + this.q.c(this.q.dP));
                    this.q.bZ = false;
                    this.case();
                }
                this.try();
            }
            if (this.e) {
                if (this.q.eK > 7) {
                    this.q.a(5, "EVENT, http send xxx 4 (" + this.q.c(n) + ")");
                }
                try1 = this.try(array, n);
                if (!try1 && this.q.eK > 5) {
                    this.q.a(4, "WARNING,  http failed to send " + this.q.c(n) + " bytes 3");
                }
            }
            else if (this.q.eK > 5) {
                this.q.a(4, "WARNING, http not sending " + this.q.c(n) + " bytes");
            }
        }
        catch (Exception ex2) {
            this.q.a(3, "http send send", ex2);
            this.a();
        }
        return try1;
    }
    
    public boolean try(final byte[] array, final int n) {
        try {
            if (this.r == null) {
                this.try();
                if (this.r == null) {
                    return false;
                }
            }
            this.r.write(array, 0, n);
            this.r.flush();
            this.P = true;
            this.long += n;
            return true;
        }
        catch (Exception ex) {
            this.e = false;
            final String message = ex.getMessage();
            if (message != null && message.indexOf("ead timed") > 0) {
                this.q.if(4, "http send sendex timeout", ex);
            }
            else {
                this.q.if(3, "http send sendex", ex);
                this.do();
            }
            this.a();
            return false;
        }
    }
    
    public void do() {
        try {
            this.q.a(6, "EVENT, http send on error");
            if (this.o == null) {
                return;
            }
            this.q.a(4, "EVENT, http send response code is " + this.q.c(((HttpURLConnection)this.o).getResponseCode()));
            final InputStream errorStream = ((HttpURLConnection)this.o).getErrorStream();
            if (errorStream == null) {
                return;
            }
            for (int n = 0; n < 10 && errorStream.read(this.x) > 0; ++n) {}
            errorStream.close();
        }
        catch (Exception ex) {}
    }
    
    public boolean new(final byte[] array, final int n) {
        int n2 = 0;
        try {
            n2 = 1;
            if (n < 1 || n > this.L || array == null) {
                return false;
            }
            if (this.q.eK > 5) {
                this.q.a(3, "EVENT, http sending " + this.q.c(n) + " bytes");
            }
            n2 = 2;
            if (this.r != null) {
                this.r.flush();
                this.r.close();
                this.r = null;
            }
            if (this.for != null) {
                this.for.close();
                this.for = null;
            }
            n2 = 3;
            if (this.case == null || !this.h.equals(this.O + "/s?b=2")) {
                n2 = 4;
                this.q.a(3, "EVENT,http creating new url " + this.O);
                this.h = this.O + "/s?b=2";
                this.case = new URL(this.h);
            }
            n2 = 5;
            if (this.J == null) {
                this.o = this.case.openConnection();
            }
            else {
                this.o = this.case.openConnection(this.J);
            }
            try {
                this.o.setDoInput(true);
                this.o.setDoOutput(true);
                this.o.setDefaultUseCaches(false);
                this.o.setUseCaches(false);
                this.o.setReadTimeout(1000);
                this.o.setConnectTimeout(7000);
                this.o.setAllowUserInteraction(false);
                this.o.setRequestProperty("User-Agent", "Mozilla/5.0 (NT;" + this.q.c(this.y) + ")");
                this.o.setRequestProperty("Content-Type", "application/octet-stream");
                ((HttpURLConnection)this.o).setInstanceFollowRedirects(true);
            }
            catch (Exception ex) {
                this.q.a(3, "set url properties", ex);
            }
            n2 = 6;
            this.r = this.o.getOutputStream();
            n2 = 7;
            if (this.r != null) {
                n2 = 8;
                this.r.write(array, 0, n);
                n2 = 9;
                this.r.flush();
                this.e = true;
                this.r.close();
                this.r = null;
                n2 = 10;
                this.for = this.o.getInputStream();
                final long do1 = this.q.do();
                if (this.for != null) {
                    n2 = 11;
                    for (int i = 0; i < 900; ++i) {
                        n2 = 12;
                        final int read = this.for.read(this.x, 0, this.L);
                        if (read < 1) {
                            break;
                        }
                        if (read > this.L) {
                            break;
                        }
                        if (!this.new) {
                            this.new = true;
                            this.q.a(4, "EVENT,first http answer received on sender thread " + this.q.c(read));
                        }
                        if (this.x[0] != 84) {
                            synchronized (this.D) {
                                this.do(this.x, read);
                            }
                        }
                        if (this.q.do() - do1 > 1200L) {
                            break;
                        }
                    }
                    n2 = 13;
                    if (this.for != null) {
                        this.for.close();
                        this.for = null;
                    }
                    n2 = 14;
                }
                else {
                    this.q.a(3, "WARNING,cannot get http input on send");
                }
            }
            else {
                n2 = 15;
                this.q.a(3, "WARNING,cannot get http output on send");
            }
            n2 = 16;
        }
        catch (Exception ex2) {
            this.e = false;
            final String message = ex2.getMessage();
            if (message == null || message.indexOf("ead timed") <= 0) {
                this.q.if(3, "http send " + this.q.c(n2), ex2);
            }
            try {
                this.q.a(4, "EVENT, http send response code is " + this.q.c(((HttpURLConnection)this.o).getResponseCode()));
                final InputStream errorStream = ((HttpURLConnection)this.o).getErrorStream();
                for (int n3 = 0; n3 < 10 && errorStream.read(this.x) > 0; ++n3) {}
                errorStream.close();
            }
            catch (Exception ex3) {}
        }
        return false;
    }
    
    public boolean int(final byte[] array, final int n) {
        int n2 = 0;
        try {
            n2 = 1;
            if (n < 1 || n > this.L || array == null) {
                this.q.a(4, "WARNING, http dropping 2 " + this.q.c(n));
                return false;
            }
            if (this.q.eK > 5) {
                this.q.a(3, "EVENT, http sending " + this.q.c(n) + " " + this.q.c(n) + " bytes");
            }
            this.r.write(array, 0, n);
            return true;
        }
        catch (Exception ex) {
            this.q.a(2, "http send buff (" + this.q.c(n) + ") to " + this.q.c(n2), ex);
            return false;
        }
    }
    
    public boolean do(final byte[] array, final int n) {
        int n2 = 0;
        try {
            n2 = 1;
            if (n == 1 && array[0] == 84) {
                return true;
            }
            if (this.q.eK > 5) {
                this.q.a(4, "EVENT, http rec " + this.q.c(n) + " (" + this.q.c(this.n) + ") bytes\r\n" + new String(array, 0, n));
            }
            if (n < 1 || n >= this.L) {
                this.q.a(4, "WARNING, http dropping 10 " + this.q.c(n));
                return false;
            }
            if (this.q.do() - this.A > 8000L && this.A != 0L) {
                this.n = 0;
            }
            this.A = this.q.do();
            if (n + this.n >= this.L) {
                this.q.a(4, "WARNING, http dropping 110 " + this.q.c(n) + " " + this.q.c(this.n));
                this.n = 0;
            }
            this.n = this.q.a(this.w, this.n, array, n);
            if (this.n < 1) {
                if (this.q.eK > 3) {
                    this.q.a(4, "WARNING, ecnrypt warning 1");
                }
                return false;
            }
            int n3 = 0;
            for (int i = 0; i <= this.n; ++i) {
                if ((this.w[i] == 68 && this.w[i + 1] == 97 && this.w[i + 2] == 54 && this.w[i + 3] == 65) || (this.w[i + 0] == 46 && this.w[i + 2] == (this.w[i + 1] ^ 0x54) && this.w[i + 3] == (this.w[i + 1] ^ 0x79) && this.w[i + 4] == (this.w[i + 1] ^ 0x37) && this.w[i + 5] == (this.w[i + 1] ^ 0x55))) {
                    boolean b = true;
                    if (this.w[i] == 68) {
                        b = false;
                    }
                    if (i - n3 < 3000 && i - n3 > 1) {
                        if (this.w[n3] == 103) {
                            System.arraycopy(this.w, n3 + 1, this.do, 0, i - n3 - 1);
                            this.a(this.do, i - n3 - 1, true);
                        }
                        else {
                            System.arraycopy(this.w, n3, this.do, 0, i - n3);
                            this.a(this.do, i - n3, false);
                        }
                    }
                    if (b) {
                        n3 = i + 6;
                        i += 5;
                    }
                    else {
                        n3 = i + 4;
                        i += 3;
                    }
                }
            }
            if (n3 >= this.n) {
                this.n = 0;
            }
            else {
                System.arraycopy(this.w, n3, this.w, 0, this.n - n3);
                this.n -= n3;
            }
            return true;
        }
        catch (Exception ex) {
            this.q.a(2, "http parse received " + this.q.c(n2), ex);
            return false;
        }
    }
    
    public boolean a(final byte[] array, final int n) {
        int n2 = 0;
        try {
            n2 = 1;
            this.q.a(4, "EVENT, http rec " + this.q.c(n) + " (" + this.q.c(this.n) + ") bytes");
            if (this.q.eK > 5) {
                this.q.a(4, "EVENT, http rec " + this.q.c(n) + " (" + this.q.c(this.n) + ") bytes\r\n" + new String(array, 0, n));
            }
            if (n < 1 || n >= this.L) {
                this.q.a(4, "WARNING, http dropping 10 " + this.q.c(n));
                return false;
            }
            if (this.q.do() - this.A > 8000L) {
                this.n = 0;
            }
            this.A = this.q.do();
            if (n + this.n >= this.L) {
                this.q.a(4, "WARNING, http dropping 110 " + this.q.c(n) + " " + this.q.c(this.n));
                this.n = 0;
            }
            System.arraycopy(array, 0, this.w, this.n, n);
            this.n += n;
            this.w[this.n] = 0;
            if (this.n < 20) {
                return true;
            }
            n2 = 2;
            int n3 = 0;
            final int n4 = 0;
            n2 = 6;
            boolean b = false;
            for (int i = 0; i < 190; ++i) {
                if (++n3 > 180) {
                    break;
                }
                if (this.q.eK > 5) {
                    this.q.a(4, "EVENT, http parse ret " + this.q.c(n4));
                }
                if (n4 < 1) {
                    this.n = 0;
                }
                if (n4 > 0) {
                    b = true;
                }
                if (n4 < 2) {
                    break;
                }
                if (this.n < 1) {
                    break;
                }
            }
            long do1 = 0L;
            if (this.q.eK > 4) {
                do1 = this.q.do();
            }
            if (n3 > 180) {
                this.q.a(4, "WARNING, (http) recv too many loops");
                this.n = 0;
            }
            if (this.q.eK > 4 && this.q.do() - do1 > 180L) {
                this.q.a(4, "WARNING, (http) parsing time was " + this.q.if(this.q.do() - do1) + " msec");
            }
            return b;
        }
        catch (Exception ex) {
            this.q.a(2, "http parse received" + this.q.c(n2), ex);
            return false;
        }
    }
    
    public int for() {
        final int n = 0;
        try {
            if (this.n < 1) {
                return 0;
            }
            this.R = 0;
            if (this.q.aX == 2) {
                System.arraycopy(this.w, 0, this.a, 0, this.n);
                this.R = this.n;
                this.n = 0;
                return this.byte();
            }
            int n2 = this.n;
            if (n2 > 18) {
                n2 = 18;
            }
            int n3 = 0;
            int n4 = -1;
            int n5 = 0;
            for (int i = 0; i < n2; ++i) {
                if ((this.w[i] >= 48 && this.w[i] <= 57) || (this.w[i] >= 65 && this.w[i] <= 70) || (this.w[i] >= 97 && this.w[i] <= 102)) {
                    if (n4 < 0) {
                        n4 = i;
                    }
                    ++n5;
                }
                else if (n4 >= 0) {
                    n3 = i;
                    break;
                }
            }
            int intValue = 0;
            if (n4 >= 0 && n5 > 0) {
                intValue = Integer.valueOf(new String(this.w, n4, n5), 16);
            }
            if (intValue < 1 || intValue > 2200) {
                return 0;
            }
            for (int n6 = n3; n6 < n2 && (this.w[n6] == 13 || this.w[n6] == 10); ++n6) {
                n3 = n6 + 1;
            }
            final int n7 = this.n - (n3 + intValue);
            if (n7 < 0) {
                return 0;
            }
            System.arraycopy(this.w, n3, this.a, 0, intValue);
            this.R = intValue;
            if ((this.n = n7) > 0) {
                System.arraycopy(this.w, n3 + intValue, this.w, 0, n7);
            }
            return this.byte();
        }
        catch (Exception ex) {
            this.q.a(2, "tcp parse received2 inner" + this.q.c(n), ex);
            return 0;
        }
    }
    
    public int byte() {
        int n = 0;
        try {
            if (this.R < 1) {
                return 0;
            }
            this.R = this.q.a(this.a, 0, this.a, this.R);
            if (this.R < 1) {
                if (this.q.eK > 3) {
                    this.q.a(4, "WARNING, ecnrypt warning 2");
                }
                return 2;
            }
            n = 11;
            if (this.R < 1) {
                this.R = 0;
                this.q.a(3, "ERROR, tcp no currbufflen after decrypt");
                return 0;
            }
            boolean b = false;
            if (this.a[0] == 103) {
                n = 125;
                b = true;
            }
            n = 13;
            boolean b2 = false;
            if (b) {
                if (this.int != null) {
                    System.arraycopy(this.a, 1, this.a, 0, this.R - 1);
                    --this.R;
                    b2 = this.int.a(this.K, this.j, this.a, this.R);
                }
                else if (this.q.eK >= 4) {
                    this.q.a(3, "ERROR, no lastrtpep found3");
                }
            }
            else if (this.z != null) {
                b2 = this.z.a(this.K, this.j, this.a, this.R, this.else);
            }
            this.R = 0;
            if (b2) {
                return 3;
            }
            return 2;
        }
        catch (Exception ex) {
            this.q.a(2, "tcp parse received3 inner" + this.q.c(n), ex);
            return 0;
        }
    }
    
    public int char() {
        int n = 0;
        try {
            this.R = 0;
            if (this.n < 1) {
                return 0;
            }
            this.n = this.q.a(this.w, 0, this.w, this.n);
            if (this.n < 1) {
                if (this.q.eK > 3) {
                    this.q.a(4, "WARNING, ecnrypt warning 3");
                }
                return 2;
            }
            n = 11;
            if (this.n < 1) {
                this.n = 0;
                this.q.a(3, "ERROR, tcp no recbufflen after decrypt");
                return 0;
            }
            if (this.q.eK > 4) {
                this.q.a(4, "EVENT, http rec3 " + this.q.c(this.n) + " bytes\r\n" + new String(this.w, 0, this.n));
            }
            boolean b = false;
            if (this.w[0] == 103) {
                n = 125;
                b = true;
                System.arraycopy(this.w, 1, this.a, 0, this.n - 1);
                this.R = this.n - 1;
                this.n = 0;
            }
            if (!b) {
                n = 1;
                n = 3;
                int for1 = -1;
                int n2 = -1;
                for (int i = 0; i < this.n - 17; ++i) {
                    if (this.w[i] == 67 && this.w[i + 1] == 111 && this.w[i + 2] == 110 && this.w[i + 3] == 116 && this.w[i + 4] == 101 && this.w[i + 5] == 110 && this.w[i + 6] == 116 && this.w[i + 7] == 45 && this.w[i + 8] == 76 && this.w[i + 9] == 101 && this.w[i + 10] == 110 && this.w[i + 11] == 103 && this.w[i + 12] == 116 && this.w[i + 13] == 104 && this.w[i + 14] == 58) {
                        n = 4;
                        int n3 = 0;
                        for (int j = i + 15; j < this.n; ++j) {
                            ++n3;
                            if (this.w[j] == 13 || this.w[j] == 10 || n3 > 6) {
                                n = 5;
                                final String trim = new String(this.w, i + 15, j - (i + 15)).trim();
                                if (trim.length() > 0) {
                                    for1 = this.q.for(trim, -1);
                                }
                                if (for1 < 0 || for1 >= this.L) {
                                    this.n = 0;
                                    for1 = -1;
                                }
                                n2 = j;
                                break;
                            }
                        }
                        break;
                    }
                }
                if (for1 >= 0) {
                    n = 6;
                    if (n2 < 0) {
                        n2 = 0;
                    }
                    int n4 = 0;
                    final int n5 = n2;
                    if (n5 < this.n - 3) {
                        if (++n4 <= 1500) {
                            if (this.w[n5] == 13 && this.w[n5 + 1] == 10 && this.w[n5 + 2] == 13 && this.w[n5 + 3] == 10) {
                                n = 7;
                                final int n6 = n5 + 4;
                                if (this.n - n6 >= for1) {
                                    System.arraycopy(this.w, 0, this.a, 0, for1 + n6);
                                    this.R = for1 + n6;
                                    int n7 = this.n - (n6 + for1);
                                    if (n7 < 0) {
                                        this.q.a(3, "ERROR, tcp minus remainings");
                                        n7 = 0;
                                    }
                                    if (n7 > 0) {
                                        System.arraycopy(this.w, n6 + for1, this.w, 0, n7);
                                    }
                                    this.n = n7;
                                }
                            }
                        }
                    }
                }
                if (this.R < 1) {
                    if (this.n > 1500) {
                        this.q.a(3, "ERROR, tcp no valid packet found " + this.q.c(this.n) + "\r\n" + new String(this.w, 0, this.n));
                        return this.n = 0;
                    }
                    return 1;
                }
                else {
                    n = 9;
                    this.R = this.q.a(this.a, 0, this.a, this.R);
                    if (this.R < 1) {
                        if (this.q.eK > 3) {
                            this.q.a(4, "WARNING, ecnrypt warning 4");
                        }
                        return 2;
                    }
                    n = 11;
                    if (this.R < 5) {
                        this.n = 0;
                        this.q.a(3, "ERROR, tcp no currbufflen");
                        return 0;
                    }
                    b = false;
                    if (this.a[0] == 103) {
                        n = 12;
                        b = true;
                        System.arraycopy(this.a, 1, this.a, 0, this.R - 1);
                        --this.R;
                    }
                }
            }
            int n8 = 0;
            int n9 = 0;
            this.a[this.R + 0] = 46;
            this.a[this.R + 1] = (byte)this.q.a(1, 126);
            this.a[this.R + 2] = (byte)(this.a[this.R + 1] ^ 0x54);
            this.a[this.R + 3] = (byte)(this.a[this.R + 1] ^ 0x79);
            this.a[this.R + 4] = (byte)(this.a[this.R + 1] ^ 0x37);
            this.a[this.R + 5] = (byte)(this.a[this.R + 1] ^ 0x55);
            this.a[this.R + 6] = 0;
            n = 521;
            for (int k = 0; k <= this.R; ++k) {
                if ((this.a[k] == 68 && this.a[k + 1] == 97 && this.a[k + 2] == 54 && this.a[k + 3] == 65) || (this.w[k + 0] == 46 && this.w[k + 2] == (this.w[k + 1] ^ 0x54) && this.w[k + 3] == (this.w[k + 1] ^ 0x79) && this.w[k + 4] == (this.w[k + 1] ^ 0x37) && this.w[k + 5] == (this.w[k + 1] ^ 0x55))) {
                    boolean b2 = true;
                    if (this.w[k] == 68) {
                        b2 = false;
                    }
                    n = 523;
                    if (k - n8 < 3000) {
                        System.arraycopy(this.a, n8, this.do, 0, k - n8);
                        final int a = this.a(this.do, k - n8, b);
                        if (a > n9) {
                            n9 = a;
                        }
                    }
                    if (b2) {
                        n8 = k + 6;
                        k += 5;
                    }
                    else {
                        n8 = k + 4;
                        k += 3;
                    }
                    n = 524;
                }
            }
            n = 525;
            return n9;
        }
        catch (Exception ex) {
            this.q.a(2, "tcp parse received inner " + this.q.c(n), ex);
            return 0;
        }
    }
    
    public int a(final byte[] array, int a, final boolean b) {
        int n = 0;
        try {
            n = 13;
            boolean b2 = false;
            a = this.q.a(array, 0, array, a);
            if (a < 1) {
                if (this.q.eK > 3) {
                    this.q.a(4, "WARNING, ecnrypt warning 5");
                }
                return 2;
            }
            if (b) {
                n = 14;
                if (this.int != null) {
                    n = 15;
                    if (this.q.eK > 5) {
                        this.q.a(4, "EVENT, http rtp rec3 " + this.q.c(a) + " bytes");
                    }
                    b2 = this.int.a(this.K, this.j, array, a);
                }
                else {
                    n = 16;
                    if (this.q.eK >= 4) {
                        this.q.a(3, "ERROR, no lastrtpep found2");
                    }
                }
            }
            else if (this.z != null) {
                n = 17;
                if (a > 21) {
                    this.q.ef = false;
                    this.q.bZ = false;
                    this.q.bM = 0L;
                }
                b2 = this.z.a(this.K, this.j, array, a, this.else);
            }
            if (b2) {
                return 3;
            }
            return 2;
        }
        catch (Exception ex) {
            this.q.a(2, "tcp parse received innerseconf " + this.q.c(n), ex);
            return 0;
        }
    }
    
    static {
        aq.f = 0;
    }
}
