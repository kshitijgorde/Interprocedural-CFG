import java.net.DatagramPacket;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.DatagramSocket;

// 
// Decompiled by Procyon v0.5.30
// 

public final class c
{
    static int a;
    static int b;
    static int c;
    private String h;
    private int i;
    private String j;
    private String k;
    private String l;
    private d m;
    private a n;
    public String d;
    public String e;
    public boolean f;
    public DatagramSocket g;
    private Socket o;
    private PrintWriter p;
    private BufferedReader q;
    private VoipApplet r;
    private String s;
    private String t;
    private String u;
    private int v;
    private String w;
    private int x;
    
    public c(final VoipApplet r, final String h) {
        this.i = 0;
        this.j = "";
        this.k = "";
        this.l = "";
        this.d = "";
        this.e = "";
        this.f = true;
        this.g = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.t = "";
        this.u = "";
        this.v = 1;
        this.w = "";
        this.x = -1;
        this.r = r;
        this.h = h;
        this.i = this.hashCode();
        try {
            this.s = InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException ex) {
            this.s = "127.0.0.1";
            e.c("INITC", "couldn't get ip " + ex.getMessage());
        }
    }
    
    public final void a(final String h) {
        this.h = h;
    }
    
    public final void b(final String j) {
        this.j = j;
    }
    
    public final void c(final String k) {
        this.k = k;
    }
    
    public final void d(final String l) {
        this.l = l;
    }
    
    private void c() {
        this.f = true;
        this.o = null;
        this.p = null;
        this.q = null;
        this.g = null;
        this.l = "";
        this.k = "";
        this.j = "";
    }
    
    private String d() {
        this.c();
        e.a("TCP-C", "Local IP Address: " + this.s);
        e.a("TCP-C", "ok");
        try {
            (this.o = new Socket()).connect(new InetSocketAddress(InetAddress.getByName(this.h), c.a), 10000);
            this.p = new PrintWriter(this.o.getOutputStream(), true);
            this.q = new BufferedReader(new InputStreamReader(this.o.getInputStream()));
        }
        catch (UnknownHostException ex2) {
            e.c("TCP-C", "Don't know about host: " + this.h);
            return "0";
        }
        catch (IOException ex3) {
            e.c("TCP-C", "Couldn't get I/O for the connection to: " + this.h);
            return "0";
        }
        catch (Exception ex) {
            e.c("TCP-C", ex.getMessage());
            final StackTraceElement[] stackTrace = ex.getStackTrace();
            for (int i = 0; i < stackTrace.length; ++i) {
                e.c("TCP-C", stackTrace[i].toString());
            }
            return "0";
        }
        e.a("TCP-C", "TCP CONNECTION BY CLIENT RECEIVED!");
        if (this.k() == null) {
            e.c("TCP-C", "ERROR - Shutting down");
            this.j();
            return "0";
        }
        e.a("TCP-C", "Connection ready!");
        return this.m();
    }
    
    private String e() {
        int n = 1;
        this.q();
        this.g("UDP " + this.i);
        if (Integer.parseInt(this.l().split("\\s")[1]) == 1) {
            e.a("UDP-C", "UDP PACKET SENT BY CLIENT RECEIVED!");
            n += 4;
        }
        else {
            e.a("UDP-C", "UDP PACKET SENT BY CLIENT LOST!");
        }
        this.p();
        this.g("PORT " + this.i);
        this.l();
        if (this.j.equals("UDP " + this.i)) {
            e.a("UDP-S", "UDP PACKET SENT BY SERVER RECEIVED!");
            n += 8;
        }
        else {
            e.a("UDP-S", "UDP PACKET SENT BY SERVER LOST!");
        }
        if (this.k.equals("TCP " + this.i)) {
            e.a("TCP-S", "TCP CONNECTION BY SERVER SUCCESSFUL!");
            n += 2;
        }
        else {
            e.a("TCP-S", "TCP CONNECTION BY SERVER FAILED!");
        }
        return new Integer(n).toString();
    }
    
    private String f() {
        return this.g() + "-" + this.h();
    }
    
    private static void a(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {
            e.c("WAIT", "Sleep Interrupted " + ex.getMessage());
        }
    }
    
    private String g() {
        final long[] array = new long[10];
        final long[] array2 = new long[10];
        for (int i = 0; i < 10; ++i) {
            final long currentTimeMillis = System.currentTimeMillis();
            this.g("PING " + currentTimeMillis);
            this.l();
            array[i] = System.currentTimeMillis() - currentTimeMillis;
            e.a("TCP-C", "Ping " + i + ": " + array[i]);
            a(20);
        }
        long n = 0L;
        for (int j = 0; j < 10; ++j) {
            n += array[j];
        }
        final long n2 = n / 10L;
        e.a("TCP-C", "Final Ping " + n2);
        long n3 = 0L;
        for (int k = 0; k < 10; ++k) {
            array2[k] = Math.abs(n2 - array[k]);
            n3 += array2[k];
        }
        final long n4 = n3 / 10L;
        e.a("TCP-C", "Final Jitter " + n4);
        return n2 + "-" + n4;
    }
    
    private String h() {
        String s = "0";
        this.o();
        this.g("PLOSS " + this.i);
        final String l;
        if ((l = this.l()).startsWith("PLOSS")) {
            final String[] split;
            s = (split = l.split("\\s"))[1];
            e.b("PLOSS", "Packets Received " + split[1]);
        }
        return s + "/" + c.c;
    }
    
    private String i() {
        String s = "0";
        String s2 = "0";
        this.r();
        this.g("SIP " + this.i);
        final String[] split = this.l().split("\\s");
        int n = c.b;
        String w = null;
        Label_0120: {
            String s3 = null;
            switch (this.v) {
                case 2: {
                    s3 = this.t;
                    break;
                }
                case 3: {
                    w = this.w;
                    n = this.b();
                    break Label_0120;
                }
                default: {
                    s3 = this.s;
                    break;
                }
            }
            w = s3;
        }
        e.a("SIP-C", "Expected: " + e.b(w) + "," + n);
        e.a("SIP-C", "Received: " + split[1]);
        String s4;
        if (split[1].equals(e.b(w) + "," + n)) {
            e.a("SIP-C", "NO SIP-AWARE FIREWALL DETECTED!");
            s4 = "1";
        }
        else if (split[1].equals("failed")) {
            e.a("SIP-C", "SIP TEST FAILED");
            s4 = "0";
        }
        else {
            e.a("SIP-C", "SIP-AWARE FIREWALL DETECTED!");
            s4 = "2";
        }
        final String s5 = s4;
        this.r.a("partial", "sip1 " + s5);
        if (!s5.equals("0")) {
            this.g("USIP " + this.i);
            this.l();
            if (!this.l.equals("")) {
                e.a("SIP-C", "SIP OPTIONS received");
                s = "1";
                this.g("ORLY " + this.i);
                if (Integer.parseInt(this.l().split("\\s")[1]) == 1) {
                    e.a("SIP-C", "SIP OPTIONS reply received by server");
                    s2 = "1";
                }
                else {
                    s2 = "0";
                    e.a("SIP-C", "SIP OPTIONS reply lost");
                }
            }
            else {
                e.a("SIP-C", "SIP OPTIONS by server lost");
                s = "0";
            }
        }
        else {
            e.a("SIP-C", "Test 2 and 3 Skipped because test 1 failed");
        }
        return s5 + "-" + s + "-" + s2;
    }
    
    private String j() {
        this.p.close();
        try {
            this.q.close();
        }
        catch (IOException ex) {
            e.c("TCP-C", "couldn't close in - " + ex.getMessage());
        }
        try {
            this.o.close();
        }
        catch (IOException ex2) {
            e.c("TCP-C", "couldn't close socket - " + ex2.getMessage());
        }
        e.a("TCP-C", "Test Complete");
        if (this.n != null) {
            this.f = false;
        }
        else if (this.g != null) {
            this.g.close();
        }
        return "1";
    }
    
    private static boolean a(final Reader reader, long n) {
        try {
            while (!reader.ready() && n > 0L) {
                Thread.sleep(100L);
                n -= 100L;
            }
            return reader.ready();
        }
        catch (IOException ex) {
            return false;
        }
        catch (Exception ex2) {
            return false;
        }
    }
    
    private String k() {
        try {
            if (a(this.q, 5000L)) {
                final String line = this.q.readLine();
                e.d("TCP-C", "Server: " + line);
                return line;
            }
            e.c("TCP-C", "Server: Timed Out after 5 seconds");
            return null;
        }
        catch (IOException ex) {
            e.c("TCP-C", "couldn't read line - " + ex.getMessage());
            return null;
        }
    }
    
    private String l() {
        try {
            final String line = this.q.readLine();
            e.d("TCP-C", "Server: " + line);
            return line;
        }
        catch (IOException ex) {
            e.c("TCP-C", "couldn't read line - " + ex.getMessage());
            return null;
        }
    }
    
    private void g(final String s) {
        this.p.println(s);
        e.d("TCP-C", "User: " + s);
    }
    
    private String m() {
        this.g("ME " + this.i + " " + e.b(this.s) + " " + this.u);
        final String l;
        if ((l = this.l()).startsWith("YOURIP")) {
            final String[] split = l.split("\\s");
            this.t = e.a(Long.parseLong(split[1]));
            e.a("TCP-C", "external IP - " + this.t);
            return "1-" + e.b(this.s) + "-" + split[1];
        }
        return "0";
    }
    
    private void n() {
        if (this.d().equals("1")) {
            this.e();
            this.f();
            this.i();
            this.j();
            return;
        }
        e.c("FULL", "Connect failed, aborting");
    }
    
    private void o() {
        e.a("PLOSS", "Packet Loss Test...");
        try {
            if (this.g == null || !this.g.isBound()) {
                this.g = new DatagramSocket(c.b);
            }
            final byte[] bytes = ("LOSS " + this.i).getBytes();
            final DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(this.h), c.b);
            for (int i = 1; i <= c.c; ++i) {
                this.g.send(datagramPacket);
                a(20);
            }
        }
        catch (IOException ex) {
            e.c("PLOSS", "UDP Client test failed");
            e.c("PLOSS", ex.getMessage());
        }
    }
    
    private void p() {
        e.a("SERVE", "Opening UDP and TCP ports 5060");
        (this.n = new a(this)).start();
        try {
            (this.m = new d(this)).start();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            e.c("TCP-S", "open tcp ports failed");
        }
        try {
            Thread.sleep(100L);
        }
        catch (InterruptedException ex2) {
            System.out.println("Sleep Interrupted");
        }
    }
    
    private void q() {
        e.a("UDP-C", "UDP Test...");
        try {
            if (this.g == null || !this.g.isBound()) {
                this.g = new DatagramSocket(c.b);
            }
            final byte[] bytes = ("UDP " + this.i).getBytes();
            this.g.send(new DatagramPacket(bytes, bytes.length, InetAddress.getByName(this.h), c.b));
        }
        catch (IOException ex) {
            e.c("UDP-C", "UDP Client test failed");
            e.c("UDP-C", ex.getMessage());
        }
    }
    
    private void r() {
        e.a("SIP-C", "SIP Test...");
        try {
            if (this.g == null || !this.g.isBound()) {
                this.g = new DatagramSocket(c.b);
            }
            int n = c.b;
            String w = null;
            Label_0103: {
                String s = null;
                switch (this.v) {
                    case 2: {
                        s = this.t;
                        break;
                    }
                    case 3: {
                        w = this.w;
                        n = this.b();
                        break Label_0103;
                    }
                    default: {
                        s = InetAddress.getLocalHost().getHostAddress();
                        break;
                    }
                }
                w = s;
            }
            final InetAddress byName = InetAddress.getByName(this.h);
            final String string;
            final byte[] bytes = (string = "REGISTER sip:register.ookla.com SIP/2.0\r\nVia: SIP/2.0/UDP " + w + ":" + n + ";branch=z9hG4bK8149125\r\n" + "Max-Forwards: 70\r\n" + "From: Alice <sip:alice@ookla.com>;tag=" + this.i + "\r\n" + "To: Alice <sip:alice@ookla.com>\r\n" + "Call-ID: " + this.i + "@" + byName.getHostAddress() + "\r\n" + "CSeq: 1 REGISTER\r\n" + "Contact: <sip:alice@" + w + ":" + n + ">\r\n" + "Expires: 7200\r\n" + "Content-Length: 0\r\n\r\n").getBytes();
            e.a("SIP-C", "Sending SIP REGISTER Packet - size = " + string.length());
            e.d("SIP-C", string);
            this.g.send(new DatagramPacket(bytes, bytes.length, byName, c.b));
        }
        catch (IOException ex) {
            e.c("SIP-C", "UDP Client test failed");
            e.c("SIP-C", ex.getMessage());
        }
    }
    
    public final void a() {
        final String[] split = this.l.split("\r\n");
        String s = "";
        int n = 0;
        for (int i = 0; i < split.length; ++i) {
            if (split[i].startsWith("Contact")) {
                final String[] split2 = split[i].substring(9).split("@")[1].split(">");
                e.b("SIP-C", split2[0]);
                final String[] split3;
                s = (split3 = split2[0].split(":"))[0];
                try {
                    n = new Integer(split3[1]);
                }
                catch (Exception ex3) {
                    n = c.b;
                }
            }
        }
        e.a("SIP-C", "SIP Reply...");
        try {
            if (this.g == null || !this.g.isBound()) {
                this.g = new DatagramSocket(c.b);
            }
            final String string;
            final byte[] bytes = (string = "SIP/2.0 200 OK\r\nVia: SIP/2.0/UDP " + s + ":" + n + ";branch=z9hG4bK8149125\r\n" + "To: Alice <sip:alice@ookla.com>;tag=" + (this.i + 2) + "\r\n" + "From: Server <sip:server@ookla.com>;tag=" + (this.i + 1) + "\r\n" + "Call-ID: " + this.i + "@" + InetAddress.getByName(this.h).getHostAddress() + "\r\n" + "CSeq: 2 OPTIONS\r\n" + "Allow: INVITE, ACK, CANCEL, OPTIONS, BYE, REFER, SUBSCRIBE, NOTIFY\r\n" + "Content-Length: 0\r\n\r\n").getBytes();
            e.a("SIP-C", "Sending SIP OPTIONS reply packet - size = " + string.length());
            e.d("SIP-C", string);
            InetAddress byName = null;
            try {
                byName = InetAddress.getByName(s);
            }
            catch (UnknownHostException ex) {
                e.c("SIP-C", "Error" + ex.getMessage());
            }
            this.g.send(new DatagramPacket(bytes, bytes.length, byName, n));
        }
        catch (IOException ex2) {
            e.c("SIP-C", "UDP Client test failed");
            e.c("SIP-C", ex2.getMessage());
        }
    }
    
    public final String e(final String s) {
        String s2 = "";
        if (s.equals("full")) {
            e.a("START", ">>>> Starting full test");
            this.n();
            e.a("START", ">>>> ending full test");
        }
        else if (s.equals("connect")) {
            e.a("START", ">>>> Starting connect test");
            s2 = this.d();
            e.a("START", ">>>> ending connect test");
        }
        else if (s.equals("ports")) {
            e.a("START", ">>>> Starting ports test");
            s2 = this.e();
            e.a("START", ">>>> ending ports test");
        }
        else if (s.equals("ping")) {
            e.a("START", ">>>> Starting ping test");
            s2 = this.g();
            e.a("START", ">>>> ending ping test");
        }
        else if (s.equals("packetloss")) {
            e.a("START", ">>>> Starting packet loss test");
            s2 = this.h();
            e.a("START", ">>>> ending packet loss test");
        }
        else if (s.equals("linequality")) {
            e.a("START", ">>>> Starting line quality test");
            s2 = this.f();
            e.a("START", ">>>> ending line quality test");
        }
        else if (s.equals("sip")) {
            e.a("START", ">>>> Starting sip test");
            s2 = this.i();
            e.a("START", ">>>> ending sip test");
        }
        else if (s.equals("disconnect")) {
            e.a("START", ">>>> Starting disconnect");
            s2 = this.j();
            e.a("START", ">>>> ending disconnect");
        }
        else {
            e.c("START", "trying to start a test that doesn't exist");
        }
        return s2;
    }
    
    public final int b() {
        if (this.x == -1) {
            return c.b;
        }
        return this.x;
    }
    
    public final void f(final String u) {
        this.u = u;
        final String[] split = u.split("&");
        for (int i = 0; i < split.length; ++i) {
            final String[] split2;
            if ((split2 = split[i].split("=")).length == 2 && split2[1].length() > 0) {
                try {
                    if (split2[0].equalsIgnoreCase("connection")) {
                        final int int1;
                        c c;
                        int v;
                        if ((int1 = Integer.parseInt(split2[1])) > 3 || int1 < 1) {
                            c = this;
                            v = 1;
                        }
                        else {
                            c = this;
                            v = int1;
                        }
                        c.v = v;
                        e.a("SETTINGS", "Connection Type: " + this.v);
                    }
                    else if (split2[0].equalsIgnoreCase("connectionip")) {
                        this.w = split2[1];
                        e.a("SETTINGS", "Connection IP: " + this.w);
                    }
                    else if (split2[0].equalsIgnoreCase("connectionport")) {
                        this.x = Integer.parseInt(split2[1]);
                        e.a("SETTINGS", "Connection Port: " + this.x);
                    }
                }
                catch (Exception ex) {
                    e.c("SETTINGS", "parsing " + split2[0] + " - " + ex.getMessage());
                }
            }
        }
        if (this.v != 3) {
            this.w = "";
            this.x = c.b;
            e.a("SETTINGS", "Connection IP: " + this.w);
            e.a("SETTINGS", "Connection Port: " + this.x);
        }
    }
    
    static {
        c.a = 5060;
        c.b = 5060;
        c.c = 50;
    }
}
