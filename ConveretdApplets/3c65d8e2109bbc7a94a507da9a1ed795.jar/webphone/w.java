// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URLConnection;
import java.net.URL;

public class w extends Thread
{
    public int byte;
    URL try;
    URLConnection e;
    Socket do;
    InetAddress o;
    t r;
    t new;
    bc l;
    webphone char;
    boolean c;
    boolean p;
    boolean else;
    aw f;
    InputStream for;
    OutputStream g;
    byte n;
    byte case;
    boolean s;
    String t;
    int void;
    int j;
    int u;
    static int goto;
    byte[] q;
    String if;
    int long;
    int h;
    String b;
    long v;
    byte[] k;
    int d;
    byte[] a;
    int w;
    byte[] int;
    int i;
    long m;
    
    public w(final t t) {
        this.byte = 0;
        this.try = null;
        this.e = null;
        this.do = null;
        this.o = null;
        this.r = null;
        this.new = null;
        this.l = null;
        this.char = null;
        this.c = false;
        this.p = false;
        this.else = false;
        this.f = null;
        this.for = null;
        this.g = null;
        this.n = 0;
        this.case = 0;
        this.s = true;
        this.t = "";
        this.void = 1;
        this.j = 0;
        this.u = 10000;
        this.q = null;
        this.if = "";
        this.long = 0;
        this.h = 0;
        this.b = "";
        this.v = 0L;
        this.k = new byte[this.u + 350];
        this.d = 0;
        this.a = new byte[this.u + 350];
        this.w = 0;
        this.int = new byte[3200];
        this.i = 0;
        this.m = 0L;
        this.byte = 0;
        this.a(t);
        this.l = t.O;
        this.c = false;
        this.p = false;
        this.else = true;
        this.f = t.d;
        this.char = this.f.ct;
        this.int();
    }
    
    public w(final bc l) {
        this.byte = 0;
        this.try = null;
        this.e = null;
        this.do = null;
        this.o = null;
        this.r = null;
        this.new = null;
        this.l = null;
        this.char = null;
        this.c = false;
        this.p = false;
        this.else = false;
        this.f = null;
        this.for = null;
        this.g = null;
        this.n = 0;
        this.case = 0;
        this.s = true;
        this.t = "";
        this.void = 1;
        this.j = 0;
        this.u = 10000;
        this.q = null;
        this.if = "";
        this.long = 0;
        this.h = 0;
        this.b = "";
        this.v = 0L;
        this.k = new byte[this.u + 350];
        this.d = 0;
        this.a = new byte[this.u + 350];
        this.w = 0;
        this.int = new byte[3200];
        this.i = 0;
        this.m = 0L;
        this.byte = 0;
        this.l = l;
        this.c = false;
        this.p = false;
        this.else = false;
        this.f = l.f;
        this.char = this.f.ct;
        this.int();
    }
    
    public void int() {
        try {
            if (this.char.serverdomainandport.length() > 0 && this.char.serveraddr.length() > 0 && this.char.proxyaddr.length() > 0 && (!this.char.serveraddr.equals(this.char.proxyaddr) || this.char.serverport != this.char.proxyport)) {
                this.b = this.char.serverdomainandport;
            }
        }
        catch (Exception ex) {
            this.f.a(3, "tcp Init0", ex);
        }
    }
    
    public void new() {
        this.p = true;
        this.if();
    }
    
    public void if() {
        try {
            this.byte = 0;
            if (this.for != null) {
                try {
                    this.for.close();
                }
                catch (Exception ex2) {}
            }
            if (this.g != null) {
                try {
                    this.g.close();
                }
                catch (Exception ex3) {}
            }
            if (this.do != null) {
                try {
                    this.do.close();
                }
                catch (Exception ex4) {}
            }
            this.c = false;
            this.try = null;
            this.e = null;
            this.do = null;
            this.for = null;
            this.g = null;
            this.c = false;
        }
        catch (Exception ex) {
            this.f.a(3, "tcp destroy", ex);
        }
    }
    
    void a() {
        try {
            if (this.for != null) {
                try {
                    this.for.close();
                }
                catch (Exception ex) {}
            }
            if (this.g != null) {
                try {
                    this.g.close();
                }
                catch (Exception ex2) {}
            }
            if (this.do != null) {
                try {
                    this.do.close();
                }
                catch (Exception ex3) {}
            }
            this.for = null;
            this.g = null;
            this.do = null;
        }
        catch (Exception ex4) {}
    }
    
    String a(final String s, final int n, final String s2, final int n2) {
        String string = "";
        try {
            final byte[] bytes = s2.getBytes();
            int n3 = 0;
            try {
                if (this.f.eK > 3) {
                    this.f.a(4, "EVENT, tcp tcp req " + this.f.c(s2.length()) + " bytes\r\n" + s2);
                }
                final byte[] array = new byte[9000];
                if (this.do == null || this.for == null || this.g == null || !this.do.isConnected()) {
                    this.do = new Socket(Proxy.NO_PROXY);
                    if (this.f.dK >= 3) {
                        this.do.setTrafficClass(10);
                    }
                    this.do.setPerformancePreferences(1, 5, 2);
                    this.do.setTcpNoDelay(false);
                    this.do.connect(new InetSocketAddress(s, n), n2);
                    this.for = this.do.getInputStream();
                    this.g = this.do.getOutputStream();
                }
                if (this.for == null || this.g == null) {
                    this.f.a(4, "WARNING, tcp tcp no input/output");
                    this.a();
                    return "";
                }
                this.f.a(5, "EVENT, tcp tcp xxx write");
                this.g.write(bytes, 0, bytes.length);
                final long do1 = this.f.do();
                this.f.a(5, "EVENT, tcp tcp xxx read");
                int i = 0;
                while (i < 100) {
                    if (this.p) {
                        break;
                    }
                    final int read = this.for.read(array, 0, array.length - 3);
                    if (read > 0) {
                        array[read] = 0;
                        this.f.a(5, "EVENT, tcp tcp xxx read1 " + this.f.c(read) + " bytes");
                        string += new String(array, 0, read);
                        if (n3 != 0) {
                            this.f.a(4, "EVENT, tcp tcp close request2");
                            break;
                        }
                        if (string.indexOf("Connection: close") > 0) {
                            n3 = 1;
                        }
                        this.f.do(100L);
                        if (this.for.available() < 1) {
                            this.f.a(4, "EVENT, tcp tcp close request1");
                            break;
                        }
                        if (this.f.do() - do1 > n2) {
                            if (string.length() < 1) {
                                this.f.a(4, "EVENT, tcp tcp read timeout");
                                break;
                            }
                            break;
                        }
                        else {
                            ++i;
                        }
                    }
                    else {
                        if (string.length() < 1) {
                            this.f.a(4, "EVENT, tcp tcp read eof");
                            break;
                        }
                        break;
                    }
                }
            }
            catch (Exception ex) {
                this.f.if(4, "TCPTCPRequest1", ex);
            }
            if (n3 != 0) {
                this.a();
            }
            if (this.f.eK > 3) {
                if (string.length() < 1) {
                    this.f.a(4, "EVENT, tcp tcp nothing received");
                }
                else {
                    this.f.a(4, "EVENT, tcp tcp rec " + this.f.c(string.length()) + " bytes\r\n" + string);
                }
            }
        }
        catch (Exception ex2) {
            this.f.if(3, "TCPTCPRequest2", ex2);
        }
        return string;
    }
    
    boolean for() {
        try {
            final int n = 7000;
            final String string = this.t + ":" + this.f.c(this.f.dn);
            final String byte1 = this.f.byte("", string);
            final int if1 = this.f.if(0, string);
            if (if1 < 1 || byte1.length() < 2) {
                this.f.a(5, "EVENT,tcp proxyconnect no proxy address found");
                return false;
            }
            String s = "" + "CONNECT " + string + " HTTP/1.0\r\n";
            if (this.f.e8.length() > 0) {
                s = s + this.f.e8 + "\r\n";
            }
            final String string2 = s + "User-agent: httpswebphone\r\n" + "\r\n";
            int n2 = 0;
            String s2 = this.a(byte1, if1, string2, n);
            int n3 = s2.indexOf("HTTP/1.0 200");
            if (n3 < 0 || n3 > 5) {
                n3 = s2.indexOf("HTTP/1.1 200");
            }
            if (n3 < 0 || n3 > 5) {
                n3 = s2.indexOf("HTTP/1.0 202");
            }
            if (n3 < 0 || n3 > 5) {
                n3 = s2.indexOf("HTTP/1.1 202");
            }
            if (n3 < 0 || n3 > 5) {
                n3 = s2.indexOf("HTTP/1.0 200");
            }
            if (n3 >= 0 && n3 <= 5) {
                n2 = 1;
                if (this.f.cs > 10) {
                    this.f.cs = 10;
                }
                this.f.a(3, "WARNING,tcp proxyconnect connect capability succeeed without auth");
            }
            int n4 = 0;
            if (n2 == 0) {
                for (int i = 0; i < 3; ++i) {
                    if (this.p) {
                        break;
                    }
                    int n5 = s2.toLowerCase().indexOf("\nproxy-authenticate:");
                    if (n5 < 1) {
                        n5 = s2.toLowerCase().indexOf("\nwww-authenticate:");
                    }
                    if (n5 < 1) {
                        this.f.a(3, "WARNING,tcp proxyconnect no ok and no auth request");
                        break;
                    }
                    this.f.a(3, "WARNING,tcp proxyconnect auth try");
                    if (n4 != 0 || this.f.cH.length() < 1 || this.f.aI.length() < 1) {
                        if (this.f.cs > 18) {
                            this.f.cs = 18;
                        }
                        this.char.asyncneedproxyauthui = 1;
                        for (int j = 0; j < 2800; ++j) {
                            this.f.do(100L);
                            if (this.char.asyncneedproxyauthui == 0) {
                                this.f.a(3, "WARNING,tcp proxyconnect auth ui err");
                                break;
                            }
                            if (this.char.asyncneedproxyauthui == 3) {
                                this.f.a(3, "WARNING,tcp proxyconnect auth done");
                                break;
                            }
                            if (this.char.asyncneedproxyauthui == 4) {
                                this.f.a(3, "WARNING,tcp proxyconnect auth canceled");
                                break;
                            }
                        }
                    }
                    if (this.f.cH.length() < 1 || this.f.aI.length() < 1) {
                        this.f.a(3, "WARNING,tcp proxyconnect auth done but no result");
                        break;
                    }
                    final String trim = s2.substring(n5 + 1, s2.indexOf("\r", n5 + 10)).trim();
                    String e8;
                    if (trim.toLowerCase().indexOf("digest") >= 0) {
                        e8 = this.f.a(trim, "", "CONNECT", this.f.aI, this.f.cH);
                    }
                    else {
                        e8 = this.f.a(trim, this.f.aI, this.f.cH);
                    }
                    if (e8.length() <= 0) {
                        this.f.a(3, "WARNING,tcp proxyconnect auth alg ret null");
                        break;
                    }
                    final String string3 = "" + "CONNECT " + string + " HTTP/1.0\r\n" + e8 + "\r\n" + "User-agent: httpswebphone\r\n" + "\r\n";
                    this.a();
                    s2 = this.a(byte1, if1, string3, n);
                    int n6 = s2.indexOf("HTTP/1.0 200");
                    if (n6 < 0 || n6 > 5) {
                        n6 = s2.indexOf("HTTP/1.1 200");
                    }
                    if (n6 < 0 || n6 > 5) {
                        n6 = s2.indexOf("HTTP/1.0 202");
                    }
                    if (n6 < 0 || n6 > 5) {
                        n6 = s2.indexOf("HTTP/1.1 202");
                    }
                    if (n6 < 0 || n6 > 5) {
                        n6 = s2.indexOf("HTTP/1.0 200");
                    }
                    if (n6 >= 0 && n6 <= 5) {
                        this.f.e8 = e8;
                        if (this.f.cs > 10) {
                            this.f.cs = 10;
                        }
                        this.f.a(3, "WARNING,tcp proxyconnect connect capability succeeed with auth");
                        n2 = 1;
                        break;
                    }
                    this.f.a(3, "WARNING,tcp proxyconnect auth failed " + this.f.c(i));
                    n4 = 1;
                }
            }
            if (n2 == 0) {
                this.a();
                this.f.a(3, "WARNING,tcp proxyconnect connect not connected");
                return false;
            }
            this.f.a(3, "EVENT,tcp proxyconnect connect succeed");
            return true;
        }
        catch (Exception ex) {
            this.f.if(3, "TCPProxyConnectDetect", ex);
            this.a();
            return false;
        }
    }
    
    public int a(final String t, int dn, int j) {
        this.if();
        if (this.f.dn > 0 && (this.f.dK == 4 || this.f.dK == 5)) {
            dn = this.f.dn;
        }
        if (this.f.dM == 0L) {
            this.f.dM = this.f.do();
        }
        j = 0;
        this.t = t;
        this.void = dn;
        this.j = j;
        this.long = 0;
        int n = 0;
        this.n = 0;
        this.case = 0;
        this.s = true;
        String s = this.t;
        int n2 = this.void;
        if (this.f.dK == 5) {
            s = this.f.byte(this.f.bT, this.t);
            n2 = this.f.if(this.f.dk, this.t);
            if (n2 < 1 || s.length() < 1) {
                s = this.t;
                n2 = this.void;
            }
        }
        for (int i = j; i < j + 60; i += 2) {
            try {
                ++n;
                if (this.p) {
                    break;
                }
                if (this.f.dK == 5 && n == 3) {
                    this.f.a(3, "EVENT,tcp proxy switch to direct tcp");
                    this.f.dK = 4;
                    s = this.t;
                    n2 = this.void;
                }
                this.a();
                this.f.a(3, "EVENT,tcp connect to " + s + ":" + this.f.c(n2) + " from " + this.f.c(j));
                if ((this.f.dK == 5 && n < 5) || n > 7) {
                    this.do = new Socket(Proxy.NO_PROXY);
                }
                else if (j > 0) {
                    if (!this.f.b4 && n < 2) {
                        this.do = new Socket(Proxy.NO_PROXY);
                    }
                    else if (n == 3) {
                        this.do = new Socket(Proxy.NO_PROXY);
                    }
                    else if (n < 5 || n > 10) {
                        this.do = new Socket(s, n2, InetAddress.getLocalHost(), i);
                    }
                    else if (n > 13 && n < 16) {
                        this.do = new Socket(s, n2);
                    }
                    else {
                        this.do = new Socket();
                    }
                }
                else if (!this.f.b4 && n < 2) {
                    this.do = new Socket(Proxy.NO_PROXY);
                }
                else if (n == 3) {
                    this.do = new Socket(Proxy.NO_PROXY);
                }
                else if (n < 5 || n > 10) {
                    this.do = new Socket();
                }
                else if (n > 13 && n < 16) {
                    this.do = new Socket(s, n2);
                }
                else {
                    this.do = new Socket(s, n2, InetAddress.getLocalHost(), i);
                }
                try {
                    if (this.f.dK >= 3) {
                        this.do.setTrafficClass(10);
                    }
                    this.do.setPerformancePreferences(1, 5, 2);
                    this.do.setTcpNoDelay(false);
                }
                catch (Exception ex) {
                    this.f.if(4, "tcp set parameters", ex);
                }
                if (!this.do.isConnected()) {
                    this.do.connect(new InetSocketAddress(s, n2), 10000);
                    if (!this.do.isConnected()) {
                        continue;
                    }
                    this.f.a(4, "EVENT,tcp connected to " + s + ":" + this.f.c(n2));
                }
                else {
                    this.f.a(4, "EVENT,tcp connected to " + s + ":" + this.f.c(n2));
                }
                this.for = this.do.getInputStream();
                this.g = this.do.getOutputStream();
                if (this.f.dK != 5 || (this.for() && this.do != null)) {
                    if (this.for != null && this.g != null) {
                        this.byte = this.do.getLocalPort();
                        if (this.byte < 1) {
                            this.byte = 8888;
                        }
                        this.f.di = true;
                        this.n = 0;
                        this.case = 0;
                        this.s = true;
                        this.c = true;
                        this.long = 0;
                        return this.byte;
                    }
                }
            }
            catch (Exception ex2) {
                this.f.if(3, "tcp init", ex2);
                try {
                    if (this.p) {
                        break;
                    }
                    if (n > 7) {
                        Thread.sleep(0L, 1);
                    }
                    else if (n > 15) {
                        Thread.sleep(n * 100);
                    }
                }
                catch (Exception ex3) {
                    this.f.if(3, "tcp init sleep", ex3);
                }
            }
        }
        this.f.a(1, "ERROR,cannot connect to " + t + ":" + this.f.c(dn) + " from " + this.f.c(j));
        return this.long = 0;
    }
    
    public boolean a(final String s, int dn, final String s2, final t t, final boolean b) {
        try {
            if (this.f.dn > 0 && (this.f.dK == 4 || this.f.dK == 5)) {
                dn = this.f.dn;
            }
            final byte[] bytes = s2.getBytes();
            final boolean a = this.a(s, dn, bytes, bytes.length, t, b);
            if (!a && this.f.eK > 4) {
                this.f.a(4, "WARNING,tcp send failed");
            }
            return a;
        }
        catch (Exception ex) {
            this.f.a(3, "tcp send string", ex);
            return false;
        }
    }
    
    void a(final t t) {
        if (t == null) {
            return;
        }
        if (this.r != t) {
            this.r = t;
        }
        if (this.new != null) {
            if (this.new == t) {
                return;
            }
            final int au = t.aU;
            final aw f = this.f;
            if (au < 7) {
                return;
            }
            final int au2 = t.aU;
            final aw f2 = this.f;
            if (au2 >= 15) {
                return;
            }
        }
        this.new = t;
    }
    
    public boolean a(final String s, int dn, final byte[] array, final int n, final t t, final boolean b) {
        int n2 = 0;
        try {
            n2 = 1;
            if (this.f.dn > 0 && (this.f.dK == 4 || this.f.dK == 5)) {
                dn = this.f.dn;
            }
            if (n < 1 || n > this.u || array == null || dn < 1) {
                this.f.a(3, "WARNING,invalid tcp packet on send");
                return false;
            }
            if (this.byte < 1 || !this.c || (this.do == null && this.e == null)) {
                n2 = 2888;
                if (this.v == 0L || this.f.do() - this.v > 5000L) {
                    this.v = this.f.do();
                    this.f.a(3, "WARNING,tcp need first connect from " + this.t + ":" + this.f.c(this.void) + " to " + s + ":" + this.f.c(dn));
                }
                this.if = s;
                this.long = dn;
                this.h = this.byte;
                return false;
            }
            if (this.f.dK != 5 && (((dn != this.void || s != this.t) && this.f.dK != 3) || (s != this.t && this.f.dK == 3 && !b))) {
                n2 = 2;
                if (this.v == 0L || this.f.do() - this.v > 5000L) {
                    this.v = this.f.do();
                    this.f.a(3, "WARNING,tcp need to connect from " + this.t + ":" + this.f.c(this.void) + " to " + s + ":" + this.f.c(dn));
                }
                this.if = s;
                this.long = dn;
                this.h = this.byte;
                return false;
            }
            n2 = 3;
            this.a(t);
            if ((this.f.dK == 4 || this.f.dK == 5) && this.f.es == 1) {
                if (this.n < 1) {
                    this.n = (byte)this.f.a(3, 127);
                }
                if (this.s) {
                    final byte[] array2 = new byte[128];
                    this.g.write(array2, 0, this.f.a(array2, this.n, false));
                    this.s = false;
                }
                for (int i = 0; i < n; ++i) {
                    array[i] ^= this.n;
                }
            }
            n2 = 14;
            this.g.write(array, 0, n);
            n2 = 15;
            return true;
        }
        catch (Exception ex) {
            this.f.a(2, "tcp send buff (" + this.f.c(n) + ") to " + s + ":" + this.f.c(dn) + "  " + this.f.c(n2), ex);
            try {
                this.f.a(4, "WARNING, tcp need reconnect because cannot send");
                this.if = this.t;
                this.long = this.void;
                this.h = this.f.eE;
            }
            catch (Exception ex2) {
                this.f.a(2, "tcp reconnect", ex2);
            }
            return false;
        }
    }
    
    public void run() {
        if (this.p) {
            return;
        }
        final byte[] array = new byte[this.u + 350];
        int n = 0;
        try {
            this.setPriority(10);
        }
        catch (Exception ex3) {}
        while (!this.p) {
            try {
                if (!this.c) {
                    this.f.a(4, "WARNING, tcp need init " + this.f.c(n));
                    Thread.sleep(0L, 1);
                    if (++n > 2) {
                        if (n > 40) {
                            n = 40;
                        }
                        Thread.sleep(n * 100);
                    }
                    if (this.p) {
                        continue;
                    }
                    this.a(this.t, this.void, this.j);
                    continue;
                }
                if (this.long > 0) {
                    this.f.a(4, "WARNING, tcp need reconnect because need was set earlier to " + this.f.c(this.long) + " " + this.f.c(n));
                    Thread.sleep(0L, 1);
                    if (++n > 2) {
                        if (n > 40) {
                            n = 40;
                        }
                        Thread.sleep(n * 100);
                    }
                    if (this.p) {
                        continue;
                    }
                    this.a(this.if, this.long, this.h);
                    continue;
                }
                if (this.do != null && !this.do.isConnected()) {
                    this.f.a(4, "WARNING, tcp need reconnect because disconnect detected " + this.f.c(n));
                    Thread.sleep(0L, 1);
                    if (++n > 2) {
                        if (n > 40) {
                            n = 40;
                        }
                        Thread.sleep(n * 100);
                    }
                    if (this.p) {
                        continue;
                    }
                    this.a(this.if, this.long, this.h);
                    continue;
                }
                if (this.for.available() < 1) {
                    Thread.sleep(0L, 1);
                    continue;
                }
                final int read = this.for.read(array, 0, this.u);
                if (read < 1) {
                    Thread.sleep(0L, 1);
                    continue;
                }
                if (read > this.u) {
                    continue;
                }
                if (read > 6 && this.case < 1 && this.f.a(array, read)) {
                    if (this.f.dK == 4 || this.f.dK == 5) {
                        this.case = array[0];
                    }
                    if (this.n < 1) {
                        this.n = (byte)this.f.a(3, 127);
                    }
                    this.g.write(array, 0, this.f.a(array, this.n, false));
                    continue;
                }
                if (this.case > 0 && (this.f.dK == 4 || this.f.dK == 5)) {
                    for (int i = 0; i < read; ++i) {
                        array[i] ^= this.case;
                    }
                }
                if (array[0] != 0 || array[1] != 0 || array[2] != 0) {
                    if (read > 6 && this.case < 1 && this.f.a(array, read)) {
                        if (this.f.dK == 4 || this.f.dK == 5) {
                            this.case = array[0];
                        }
                        if (this.n < 1) {
                            this.n = (byte)this.f.a(3, 127);
                        }
                        this.g.write(array, 0, this.f.a(array, this.n, false));
                    }
                    else if (read == 11 && array[4] == 32 && array[5] == 13 && array[6] == 10 && array[0] == 68 && array[1] == 97 && array[2] == 54 && array[3] == 65 && array[7] == 68 && array[8] == 97 && array[9] == 54 && array[10] == 65) {
                        final String s = "  \r\n";
                        this.g.write(s.getBytes(), 0, s.length());
                    }
                    else if (read == 3 && (this.f.dK == 4 || this.f.dK == 5) && array[0] == 32 && array[1] == 13 && array[2] == 10) {
                        final String s2 = "  \r\n";
                        this.g.write(s2.getBytes(), 0, s2.length());
                    }
                    else if (read > 0) {
                        if (this.f.dK < 3) {
                            this.a(array, read);
                        }
                        else {
                            this.if(array, read);
                        }
                    }
                }
                n = 0;
                continue;
            }
            catch (Exception ex) {
                this.f.if(3, "tcp rec ", ex);
                try {
                    if (this.p) {
                        continue;
                    }
                    this.f.a(3, "WARNING, tcp reconnecting");
                    Thread.sleep(0L, 1);
                    if (++n > 2) {
                        if (n > 40) {
                            n = 40;
                        }
                        Thread.sleep(n * 100);
                    }
                    if (this.p) {
                        continue;
                    }
                    this.a(this.t, this.void, this.j);
                }
                catch (Exception ex2) {
                    this.f.a(2, "tcp reconnect", ex2);
                }
                continue;
            }
            break;
        }
        this.new();
    }
    
    public boolean if(final byte[] array, final int n) {
        int n2 = 0;
        try {
            n2 = 1;
            if (n == 1 && array[0] == 84) {
                return true;
            }
            if (this.f.eK > 5) {
                this.f.a(4, "EVENT, tcp rec " + this.f.c(n) + " (" + this.f.c(this.d) + ") bytes\r\n" + new String(array, 0, n));
            }
            if (n < 1 || n >= this.u) {
                this.f.a(4, "WARNING, tcp dropping 10 " + this.f.c(n));
                return false;
            }
            if (this.f.do() - this.m > 8000L && this.m != 0L) {
                this.d = 0;
            }
            this.m = this.f.do();
            if (n + this.d >= this.u) {
                this.f.a(4, "WARNING, tcp dropping 110 " + this.f.c(n) + " " + this.f.c(this.d));
                this.d = 0;
            }
            this.d = this.f.a(this.k, this.d, array, n);
            if (this.d < 1) {
                return false;
            }
            int n3 = 0;
            for (int i = 0; i <= this.d; ++i) {
                if ((this.k[i] == 68 && this.k[i + 1] == 97 && this.k[i + 2] == 54 && this.k[i + 3] == 65) || (this.k[i + 0] == 46 && this.k[i + 2] == (this.k[i + 1] ^ 0x54) && this.k[i + 3] == (this.k[i + 1] ^ 0x79) && this.k[i + 4] == (this.k[i + 1] ^ 0x37) && this.k[i + 5] == (this.k[i + 1] ^ 0x55))) {
                    final boolean b = this.k[i] != 68;
                    if (i - n3 < 3000 && i - n3 > 1) {
                        if (this.k[n3] == 103) {
                            System.arraycopy(this.k, n3 + 1, this.int, 0, i - n3 - 1);
                            this.a(this.int, i - n3 - 1, true);
                        }
                        else {
                            System.arraycopy(this.k, n3, this.int, 0, i - n3);
                            this.a(this.int, i - n3, false);
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
            if (n3 > 0 && this.d >= n3) {
                System.arraycopy(this.k, n3, this.k, 0, this.d - n3);
                this.d -= n3;
            }
            else {
                this.d = 0;
            }
            return true;
        }
        catch (Exception ex) {
            this.f.a(2, "tcp parse received " + this.f.c(n2), ex);
            return false;
        }
    }
    
    public int a(final byte[] array, int a, final boolean b) {
        int n = 0;
        try {
            n = 13;
            boolean b2 = false;
            a = this.f.a(array, 0, array, a);
            if (a < 1) {
                return 2;
            }
            if (b) {
                n = 14;
                if (this.new != null) {
                    n = 15;
                    if (this.f.eK > 5) {
                        this.f.a(4, "EVENT, tcp rtp rec3 " + this.f.c(a) + " bytes");
                    }
                    b2 = this.new.a(this.t, this.void, array, a);
                }
                else {
                    n = 16;
                    if (this.f.eK >= 4) {
                        this.f.a(4, "ERROR, no lastrtpep found5");
                    }
                }
            }
            else if (this.l != null) {
                n = 17;
                this.f.dM = 1L;
                b2 = this.l.a(this.t, this.void, array, a, this.byte);
            }
            if (b2) {
                return 3;
            }
            return 2;
        }
        catch (Exception ex) {
            this.f.a(2, "tcp parse received innerseconf " + this.f.c(n), ex);
            return 0;
        }
    }
    
    public boolean a(final byte[] array, final int n) {
        int n2 = 0;
        try {
            n2 = 1;
            if (n < 1 || n >= this.u) {
                return false;
            }
            if (this.f.do() - this.m > 8000L) {
                this.d = 0;
            }
            this.m = this.f.do();
            if (n + this.d >= this.u) {
                this.d = 0;
            }
            System.arraycopy(array, 0, this.k, this.d, n);
            this.d += n;
            this.k[this.d] = 0;
            if (this.d < 20) {
                return true;
            }
            n2 = 2;
            int n3 = 0;
            n2 = 6;
            for (int i = 0; i < 60; ++i) {
                if (++n3 > 80) {
                    break;
                }
                final int do1 = this.do();
                if (do1 == 0) {
                    this.d = 0;
                }
                if (do1 < 2) {
                    break;
                }
            }
            long do2 = 0L;
            if (this.f.eK > 4) {
                do2 = this.f.do();
            }
            if (n3 > 50) {
                this.f.a(4, "WARNING, (tcp) recv too many loops");
                this.d = 0;
            }
            if (this.f.eK > 4 && this.f.do() - do2 > 180L) {
                this.f.a(4, "WARNING, (tcp) parsing time was " + this.f.if(this.f.do() - do2) + " msec");
            }
        }
        catch (Exception ex) {
            this.f.a(2, "tcp parse received" + this.f.c(n2), ex);
        }
        return false;
    }
    
    public int do() {
        int n = 0;
        try {
            this.w = 0;
            n = 1;
            if (this.f.dK == 0 || this.f.dK == 4 || this.f.dK == 5) {
                this.f.dK = 3;
            }
            n = 3;
            int for1 = -1;
            int n2 = -1;
            for (int i = 0; i < this.d - 17; ++i) {
                if (this.k[i] == 67 && this.k[i + 1] == 111 && this.k[i + 2] == 110 && this.k[i + 3] == 116 && this.k[i + 4] == 101 && this.k[i + 5] == 110 && this.k[i + 6] == 116 && this.k[i + 7] == 45 && this.k[i + 8] == 76 && this.k[i + 9] == 101 && this.k[i + 10] == 110 && this.k[i + 11] == 103 && this.k[i + 12] == 116 && this.k[i + 13] == 104 && this.k[i + 14] == 58) {
                    n = 4;
                    int n3 = 0;
                    for (int j = i + 15; j < this.d; ++j) {
                        ++n3;
                        if (this.k[j] == 13 || this.k[j] == 10 || n3 > 6) {
                            n = 5;
                            final String trim = new String(this.k, i + 15, j - (i + 15)).trim();
                            if (trim.length() > 0) {
                                for1 = this.f.for(trim, -1);
                            }
                            if (for1 < 0 || for1 >= this.u) {
                                this.d = 0;
                                for1 = -1;
                            }
                            n2 = j;
                            break;
                        }
                    }
                    break;
                }
            }
            if (for1 > 0 || (for1 == 0 && this.f.dK < 3)) {
                n = 6;
                if (n2 < 0) {
                    n2 = 0;
                }
                int n4 = 0;
                final int n5 = n2;
                if (n5 < this.d - 3) {
                    if (++n4 <= 1500) {
                        if (this.k[n5] == 13 && this.k[n5 + 1] == 10 && this.k[n5 + 2] == 13 && this.k[n5 + 3] == 10) {
                            n = 7;
                            final int n6 = n5 + 4;
                            if (this.d - n6 >= for1) {
                                if (this.f.dK == 3) {
                                    System.arraycopy(this.k, n6, this.a, 0, for1);
                                    this.w = for1;
                                }
                                else {
                                    System.arraycopy(this.k, 0, this.a, 0, for1 + n6);
                                    this.w = for1 + n6;
                                }
                                int d = this.d - (n6 + for1);
                                if (d < 0) {
                                    this.f.a(3, "ERROR, tcp minus remainings");
                                    d = 0;
                                }
                                if (d > 0) {
                                    System.arraycopy(this.k, n6 + for1, this.k, 0, d);
                                }
                                this.d = d;
                            }
                        }
                    }
                }
            }
            if (this.w < 1) {
                if (this.d > 1500) {
                    this.d = 0;
                    this.f.a(3, "ERROR, tcp no valid packet found");
                    return 0;
                }
                return 1;
            }
            else {
                if (this.f.dK == 3) {
                    n = 9;
                    this.w = this.f.a(this.a, 0, this.a, this.w);
                    if (this.w < 1) {
                        return 2;
                    }
                }
                n = 11;
                if (this.w < 1) {
                    this.d = 0;
                    this.f.a(3, "ERROR, tcp no currbufflen");
                    return 0;
                }
                boolean b = false;
                if (this.f.dK == 3 && this.a[0] == 103) {
                    n = 12;
                    b = true;
                    System.arraycopy(this.a, 1, this.a, 0, this.w - 1);
                    --this.w;
                }
                n = 13;
                boolean b2 = false;
                if (b) {
                    if (this.new != null) {
                        b2 = this.new.a(this.t, this.void, this.a, this.w);
                    }
                }
                else if (this.l != null) {
                    this.f.dM = 1L;
                    b2 = this.l.a(this.t, this.void, this.a, this.w, this.byte);
                }
                if (b2) {
                    return 3;
                }
                return 2;
            }
        }
        catch (Exception ex) {
            this.f.a(2, "tcp parse received inner" + this.f.c(n), ex);
            return 0;
        }
    }
    
    static {
        w.goto = 0;
    }
}
