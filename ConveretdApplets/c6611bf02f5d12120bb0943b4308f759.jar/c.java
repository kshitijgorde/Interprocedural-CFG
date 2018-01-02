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
    public static int c;
    public static int d;
    public static int e;
    public static int f;
    private String l;
    private int m;
    private String n;
    private String o;
    private String p;
    private d q;
    private a r;
    public String g;
    public String h;
    public String i;
    public boolean j;
    public DatagramSocket k;
    private Socket s;
    private PrintWriter t;
    private BufferedReader u;
    private VoipApplet v;
    private String w;
    private String x;
    private String y;
    private String z;
    private int A;
    private String B;
    private int C;
    
    public c(final VoipApplet v, final String l) {
        this.m = 0;
        this.n = "";
        this.o = "";
        this.p = "";
        this.g = "";
        this.h = null;
        this.i = "";
        this.j = true;
        this.k = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.y = "";
        this.z = "";
        this.A = 1;
        this.B = "";
        this.C = -1;
        this.v = v;
        this.l = l;
        this.m = this.hashCode();
        try {
            this.w = InetAddress.getLocalHost().getHostAddress();
            this.x = InetAddress.getLocalHost().getHostName();
        }
        catch (UnknownHostException ex) {
            this.w = "127.0.0.1";
            e.c("INITC", "couldn't get ip " + ex.getMessage());
        }
    }
    
    public final void a(final String l) {
        this.l = l;
    }
    
    public final void b(final String n) {
        this.n = n;
    }
    
    public final void c(final String o) {
        this.o = o;
    }
    
    public final void d(final String p) {
        this.p = p;
    }
    
    private void d() {
        this.j = true;
        this.s = null;
        this.t = null;
        this.u = null;
        this.k = null;
        this.p = "";
        this.o = "";
        this.n = "";
    }
    
    private String e() {
        this.d();
        e.a("TCP-C", "Local IP Address: " + this.w);
        e.a("TCP-C", "Local Host: " + this.x);
        e.a("TCP-C", "ok");
        try {
            (this.s = new Socket()).connect(new InetSocketAddress(InetAddress.getByName(this.l), c.a), 10000);
            this.s.setSoTimeout(20000);
            this.t = new PrintWriter(this.s.getOutputStream(), true);
            this.u = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
        }
        catch (UnknownHostException ex2) {
            e.c("TCP-C", "Don't know about host: " + this.l);
            return "0";
        }
        catch (IOException ex3) {
            e.c("TCP-C", "Couldn't get I/O for the connection to: " + this.l);
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
        this.g("HI 2.0.0");
        if (this.m() == null) {
            e.c("TCP-C", "ERROR - Shutting down");
            this.k();
            return "0";
        }
        e.a("TCP-C", "Connection ready!");
        return this.o();
    }
    
    private String f() {
        int n = 1;
        this.s();
        this.g("UDP " + this.m);
        if (Integer.parseInt(this.n().split("\\s")[1]) == 1) {
            e.a("UDP-C", "UDP PACKET SENT BY CLIENT RECEIVED!");
            n += 4;
        }
        else {
            e.a("UDP-C", "UDP PACKET SENT BY CLIENT LOST!");
        }
        this.r();
        this.g("PORT " + this.m);
        this.n();
        if (this.n.equals("UDP " + this.m)) {
            e.a("UDP-S", "UDP PACKET SENT BY SERVER RECEIVED!");
            n += 8;
        }
        else {
            e.a("UDP-S", "UDP PACKET SENT BY SERVER LOST!");
        }
        if (this.o.equals("TCP " + this.m)) {
            e.a("TCP-S", "TCP CONNECTION BY SERVER SUCCESSFUL!");
            n += 2;
        }
        else {
            e.a("TCP-S", "TCP CONNECTION BY SERVER FAILED!");
        }
        return new Integer(n).toString();
    }
    
    private String g() {
        return this.h() + "-" + this.i();
    }
    
    private static void a(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {
            e.c("WAIT", "Sleep Interrupted " + ex.getMessage());
        }
    }
    
    private String h() {
        final int c;
        final long[] array = new long[c = c.c];
        final long[] array2 = new long[c];
        for (int i = 0; i < c; ++i) {
            final long currentTimeMillis = System.currentTimeMillis();
            final long nanoTime = System.nanoTime();
            this.g("PING " + currentTimeMillis);
            this.n();
            array[i] = (System.nanoTime() - nanoTime) / 1000000L;
            e.a("TCP-C", "Ping " + i + ": " + array[i]);
            a(c.d);
        }
        long n = 0L;
        for (int j = 0; j < c; ++j) {
            n += array[j];
        }
        final long round = Math.round(n / c);
        e.a("TCP-C", "Final Ping " + round);
        long n2 = 0L;
        for (int k = 0; k < c; ++k) {
            array2[k] = Math.abs(round - array[k]);
            n2 += array2[k];
        }
        final long round2 = Math.round(n2 / c);
        e.a("TCP-C", "Final Jitter " + round2);
        return round + "-" + round2;
    }
    
    private String i() {
        String s = "0";
        this.q();
        this.g("PLOSS " + this.m);
        final String n;
        if ((n = this.n()).startsWith("PLOSS")) {
            final String[] split;
            s = (split = n.split("\\s"))[1];
            e.b("PLOSS", "Packets Received " + split[1]);
        }
        return s + "/" + c.e;
    }
    
    private String f(final String s) {
        final String[] split;
        if (s != null && (split = s.split("\\s")).length == 2) {
            c.e = Integer.parseInt(split[0]);
            c.f = Integer.parseInt(split[1]);
        }
        this.q();
        return "" + c.e;
    }
    
    private String j() {
        String s = "0";
        String s2 = "0";
        this.t();
        this.g("SIP " + this.m);
        final String[] split = this.n().split("\\s");
        int n = c.b;
        String b = null;
        Label_0120: {
            String s3 = null;
            switch (this.A) {
                case 2: {
                    s3 = this.y;
                    break;
                }
                case 3: {
                    b = this.B;
                    n = this.b();
                    break Label_0120;
                }
                default: {
                    s3 = this.w;
                    break;
                }
            }
            b = s3;
        }
        e.a("SIP-C", "Expected: " + e.b(b) + "," + n);
        e.a("SIP-C", "Received: " + split[1]);
        String s4;
        if (split[1].equals(e.b(b) + "," + n)) {
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
        this.v.a("partial", "sip1 " + s5);
        if (!s5.equals("0")) {
            this.g("USIP " + this.m);
            this.n();
            if (!this.p.equals("")) {
                e.a("SIP-C", "SIP OPTIONS received");
                s = "1";
                this.g("ORLY " + this.m);
                if (Integer.parseInt(this.n().split("\\s")[1]) == 1) {
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
    
    private String k() {
        this.t.close();
        try {
            this.u.close();
        }
        catch (IOException ex) {
            e.c("TCP-C", "couldn't close in - " + ex.getMessage());
        }
        try {
            this.s.close();
        }
        catch (IOException ex2) {
            e.c("TCP-C", "couldn't close socket - " + ex2.getMessage());
        }
        e.a("TCP-C", "Test Complete");
        if (this.r != null) {
            this.j = false;
        }
        else if (this.k != null) {
            this.k.close();
        }
        return "1\t" + this.x;
    }
    
    private String l() {
        if (this.r != null) {
            this.j = false;
        }
        else if (this.k != null) {
            this.k.close();
        }
        this.d();
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
    
    private String m() {
        e.b("TCP-C", "Server: Trying timeout read: 5 seconds");
        try {
            if (a(this.u, 5000L)) {
                e.b("TCP-C", "Server: Input ready");
                final String line = this.u.readLine();
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
    
    private String n() {
        try {
            final String line = this.u.readLine();
            e.d("TCP-C", "Server: " + line);
            return line;
        }
        catch (IOException ex) {
            e.c("TCP-C", "couldn't read line - " + ex.getMessage());
            return null;
        }
    }
    
    private void g(final String s) {
        this.t.println(s);
        e.d("TCP-C", "User: " + s);
    }
    
    private String o() {
        this.g("ME " + this.m + " " + e.b(this.w) + " " + this.z);
        final String n;
        if ((n = this.n()).startsWith("YOURIP")) {
            final String[] split = n.split("\\s");
            this.y = e.a(Long.parseLong(split[1]));
            e.a("TCP-C", "external IP - " + this.y);
            return "1-" + e.b(this.w) + "-" + split[1];
        }
        return "0";
    }
    
    private void p() {
        if (this.e().equals("1")) {
            this.f();
            this.g();
            this.j();
            this.k();
            return;
        }
        e.c("FULL", "Connect failed, aborting");
    }
    
    private void q() {
        e.a("PLOSS", "Packet Loss Test...");
        try {
            if (this.k == null || !this.k.isBound()) {
                this.k = new DatagramSocket(c.b);
            }
            final byte[] bytes = ("LOSS " + this.m).getBytes();
            final DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(this.l), c.b);
            for (int i = 1; i <= c.e; ++i) {
                this.k.send(datagramPacket);
                a(c.f);
            }
        }
        catch (IOException ex) {
            e.c("PLOSS", "UDP Client test failed");
            e.c("PLOSS", ex.getMessage());
        }
    }
    
    private void r() {
        e.a("SERVE", "Opening UDP and TCP ports 5060");
        (this.r = new a(this)).start();
        try {
            (this.q = new d(this)).start();
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
    
    private void s() {
        e.a("UDP-C", "UDP Test...");
        try {
            if (this.k == null || !this.k.isBound()) {
                this.k = new DatagramSocket(c.b);
            }
            final byte[] bytes = ("UDP " + this.m).getBytes();
            this.k.send(new DatagramPacket(bytes, bytes.length, InetAddress.getByName(this.l), c.b));
        }
        catch (IOException ex) {
            e.c("UDP-C", "UDP Client test failed");
            e.c("UDP-C", ex.getMessage());
        }
    }
    
    private void t() {
        e.a("SIP-C", "SIP Test...");
        try {
            if (this.k == null || !this.k.isBound()) {
                this.k = new DatagramSocket(c.b);
            }
            int n = c.b;
            String b = null;
            Label_0103: {
                String s = null;
                switch (this.A) {
                    case 2: {
                        s = this.y;
                        break;
                    }
                    case 3: {
                        b = this.B;
                        n = this.b();
                        break Label_0103;
                    }
                    default: {
                        s = InetAddress.getLocalHost().getHostAddress();
                        break;
                    }
                }
                b = s;
            }
            final InetAddress byName = InetAddress.getByName(this.l);
            final String string;
            final byte[] bytes = (string = "REGISTER sip:register.ookla.com SIP/2.0\r\nVia: SIP/2.0/UDP " + b + ":" + n + ";branch=z9hG4bK8149125\r\n" + "Max-Forwards: 70\r\n" + "From: Alice <sip:alice@ookla.com>;tag=" + this.m + "\r\n" + "To: Alice <sip:alice@ookla.com>\r\n" + "Call-ID: " + this.m + "@" + byName.getHostAddress() + "\r\n" + "CSeq: 1 REGISTER\r\n" + "Contact: <sip:alice@" + b + ":" + n + ">\r\n" + "Expires: 7200\r\n" + "Content-Length: 0\r\n\r\n").getBytes();
            e.a("SIP-C", "Sending SIP REGISTER Packet - size = " + string.length());
            e.d("SIP-C", string);
            this.k.send(new DatagramPacket(bytes, bytes.length, byName, c.b));
        }
        catch (IOException ex) {
            e.c("SIP-C", "UDP Client test failed");
            e.c("SIP-C", ex.getMessage());
        }
    }
    
    public final void a() {
        final String[] split = this.p.split("\r\n");
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
            if (this.k == null || !this.k.isBound()) {
                this.k = new DatagramSocket(c.b);
            }
            final String string;
            final byte[] bytes = (string = "SIP/2.0 200 OK\r\nVia: SIP/2.0/UDP " + s + ":" + n + ";branch=z9hG4bK8149125\r\n" + "To: Alice <sip:alice@ookla.com>;tag=" + (this.m + 2) + "\r\n" + "From: Server <sip:server@ookla.com>;tag=" + (this.m + 1) + "\r\n" + "Call-ID: " + this.m + "@" + InetAddress.getByName(this.l).getHostAddress() + "\r\n" + "CSeq: 2 OPTIONS\r\n" + "Allow: INVITE, ACK, CANCEL, OPTIONS, BYE, REFER, SUBSCRIBE, NOTIFY\r\n" + "Content-Length: 0\r\n\r\n").getBytes();
            e.a("SIP-C", "Sending SIP OPTIONS reply packet - size = " + string.length());
            e.d("SIP-C", string);
            InetAddress byName = null;
            try {
                byName = InetAddress.getByName(s);
            }
            catch (UnknownHostException ex) {
                e.c("SIP-C", "Error" + ex.getMessage());
            }
            this.k.send(new DatagramPacket(bytes, bytes.length, byName, n));
        }
        catch (IOException ex2) {
            e.c("SIP-C", "UDP Client test failed");
            e.c("SIP-C", ex2.getMessage());
        }
    }
    
    public final String a(final String s, final String s2) {
        String s3 = "";
        if (s.equals("full")) {
            e.a("START", ">>>> Starting full test");
            this.p();
            e.a("START", ">>>> ending full test");
        }
        else if (s.equals("connect")) {
            e.a("START", ">>>> Starting connect test");
            s3 = this.e();
            e.a("START", ">>>> ending connect test");
        }
        else if (s.equals("ports")) {
            e.a("START", ">>>> Starting ports test");
            s3 = this.f();
            e.a("START", ">>>> ending ports test");
        }
        else if (s.equals("ping")) {
            e.a("START", ">>>> Starting ping test");
            s3 = this.h();
            e.a("START", ">>>> ending ping test");
        }
        else if (s.equals("packetloss")) {
            e.a("START", ">>>> Starting packet loss test");
            s3 = this.i();
            e.a("START", ">>>> ending packet loss test");
        }
        else if (s.equals("flashpacketloss")) {
            e.a("START", ">>>> Starting packet loss test - FLASH");
            s3 = this.f(s2);
            e.a("START", ">>>> ending packet loss test - FLASH");
        }
        else if (s.equals("linequality")) {
            e.a("START", ">>>> Starting line quality test");
            s3 = this.g();
            e.a("START", ">>>> ending line quality test");
        }
        else if (s.equals("sip")) {
            e.a("START", ">>>> Starting sip test");
            s3 = this.j();
            e.a("START", ">>>> ending sip test");
        }
        else if (s.equals("disconnect")) {
            e.a("START", ">>>> Starting disconnect");
            s3 = this.k();
            e.a("START", ">>>> ending disconnect");
        }
        else if (s.equals("flashdisconnect")) {
            e.a("START", ">>>> Starting disconnect - FLASH");
            s3 = this.l();
            e.a("START", ">>>> ending disconnect - FLASH");
        }
        else {
            e.c("START", "trying to start a test that doesn't exist");
        }
        return s3;
    }
    
    public final int b() {
        if (this.C == -1) {
            return c.b;
        }
        return this.C;
    }
    
    public final void e(final String z) {
        this.z = z;
        final String[] split = z.split("&");
        for (int i = 0; i < split.length; ++i) {
            final String[] split2;
            if ((split2 = split[i].split("=")).length == 2 && split2[1].length() > 0) {
                try {
                    if (split2[0].equalsIgnoreCase("connection")) {
                        final int int1;
                        c c;
                        int a;
                        if ((int1 = Integer.parseInt(split2[1])) > 3 || int1 < 1) {
                            c = this;
                            a = 1;
                        }
                        else {
                            c = this;
                            a = int1;
                        }
                        c.A = a;
                        e.a("SETTINGS", "Connection Type: " + this.A);
                    }
                    else if (split2[0].equalsIgnoreCase("connectionip")) {
                        this.B = split2[1];
                        e.a("SETTINGS", "Connection IP: " + this.B);
                    }
                    else if (split2[0].equalsIgnoreCase("connectionport")) {
                        this.C = Integer.parseInt(split2[1]);
                        e.a("SETTINGS", "Connection Port: " + this.C);
                    }
                }
                catch (Exception ex) {
                    e.c("SETTINGS", "parsing " + split2[0] + " - " + ex.getMessage());
                }
            }
        }
        if (this.A != 3) {
            this.B = "";
            this.C = c.b;
            e.a("SETTINGS", "Connection IP: " + this.B);
            e.a("SETTINGS", "Connection Port: " + this.C);
        }
    }
    
    public final int c() {
        return this.m;
    }
    
    static {
        c.a = 5060;
        c.b = 5060;
        c.c = 10;
        c.d = 20;
        c.e = 50;
        c.f = 20;
    }
}
