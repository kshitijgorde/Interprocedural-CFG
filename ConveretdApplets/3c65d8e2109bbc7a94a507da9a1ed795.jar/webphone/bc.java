// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.util.Iterator;
import java.util.Collections;
import java.util.LinkedList;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.List;

public class bc extends Thread
{
    public List try;
    public i l;
    public w m;
    public aq new;
    public int j;
    public boolean d;
    public t for;
    public int k;
    s a;
    webphone byte;
    aw f;
    bi if;
    public boolean i;
    private int c;
    private int b;
    public boolean case;
    private t void;
    private String long;
    private String goto;
    public int h;
    Socket int;
    InputStream else;
    OutputStream e;
    boolean do;
    public boolean g;
    boolean char;
    
    public bc(final webphone byte1) {
        this.try = null;
        this.l = null;
        this.m = null;
        this.new = null;
        this.j = 16100;
        this.d = true;
        this.for = null;
        this.k = 0;
        this.a = null;
        this.byte = null;
        this.f = null;
        this.if = null;
        this.i = false;
        this.c = -1;
        this.b = -1;
        this.case = true;
        this.void = null;
        this.long = "";
        this.goto = "";
        this.h = 0;
        this.int = null;
        this.else = null;
        this.e = null;
        this.do = false;
        this.g = false;
        this.char = false;
        try {
            this.f = byte1.common;
            this.a(3, "EVENT,creating sip stack");
            this.h = this.f.for(1, 99999);
            this.byte = byte1;
        }
        catch (Exception ex) {
            this.a(1, "mainCtor", ex);
        }
    }
    
    void e() {
        try {
            if (this.else != null) {
                try {
                    this.else.close();
                }
                catch (Exception ex) {}
            }
            if (this.e != null) {
                try {
                    this.e.close();
                }
                catch (Exception ex2) {}
            }
            if (this.int != null) {
                try {
                    this.int.close();
                }
                catch (Exception ex3) {}
            }
            this.else = null;
            this.e = null;
            this.int = null;
        }
        catch (Exception ex4) {}
    }
    
    String a(final String s, final int n, final String s2, final byte[] array, final int n2, final int soTimeout, final boolean b) {
        String string = "";
        try {
            byte[] bytes;
            int length;
            if (s2.length() > 0) {
                bytes = s2.getBytes();
                length = bytes.length;
            }
            else {
                bytes = array;
                length = n2;
            }
            int n3 = 0;
            try {
                if (this.f.eK > 3) {
                    this.f.a(4, "EVENT, tcp req " + this.f.c(s2.length()) + " bytes\r\n" + s2);
                }
                final byte[] array2 = new byte[9000];
                if (this.int == null || this.else == null || this.e == null || !this.int.isConnected()) {
                    (this.int = new Socket(Proxy.NO_PROXY)).setSoTimeout(soTimeout);
                    this.int.connect(new InetSocketAddress(s, n), soTimeout);
                    this.else = this.int.getInputStream();
                    this.e = this.int.getOutputStream();
                }
                final byte[] array3 = new byte[1024];
                int n4 = 0;
                this.e.write(bytes, 0, length);
                final long do1 = this.f.do();
                int i = 0;
                while (i < 100) {
                    if (this.byte.isterminated) {
                        break;
                    }
                    final int read = this.else.read(array2, 0, array2.length - 5);
                    if (read > 0) {
                        if (b) {
                            if (read + n4 >= 80) {
                                this.f.a(4, "EVENT, tcp too much read");
                                string = "PINGPACKET2";
                                n3 = 1;
                                break;
                            }
                            System.arraycopy(array2, 0, array3, n4, read);
                            n4 += read;
                            if (this.f.a(array3, n4)) {
                                this.f.a(4, "EVENT, pinganswer received");
                                string = "PINGPACKET";
                                n3 = 1;
                                break;
                            }
                        }
                        array2[read] = 0;
                        string += new String(array2, 0, read);
                        if (n3 != 0) {
                            this.f.a(4, "EVENT, tcp close request2");
                            break;
                        }
                        if (string.indexOf("Connection: close") > 0) {
                            n3 = 1;
                        }
                        this.f.do(100L);
                        if (this.else.available() < 1) {
                            this.f.a(4, "EVENT, tcp close request1");
                            break;
                        }
                        if (this.f.do() - do1 > soTimeout) {
                            if (string.length() < 1) {
                                this.f.a(4, "EVENT, tcp read timeout");
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
                            this.f.a(4, "EVENT, tcp read eof");
                            break;
                        }
                        break;
                    }
                }
            }
            catch (Exception ex) {
                this.f.if(4, "TCPRequest1", ex);
            }
            if (n3 != 0) {
                this.e();
            }
            if (this.f.eK > 3) {
                if (string.length() < 1) {
                    this.f.a(4, "EVENT, tcp nothing received");
                }
                else {
                    this.f.a(4, "EVENT, tcp rec " + this.f.c(string.length()) + " bytes\r\n" + string);
                }
            }
        }
        catch (Exception ex2) {
            this.f.if(3, "TCPRequest2", ex2);
        }
        return string;
    }
    
    public boolean a(final String s, final int n) {
        try {
            final String string = s + ":" + this.f.c(this.f.dn);
            final String byte1 = this.f.byte("", string);
            final int if1 = this.f.if(0, string);
            if (if1 < 1 || byte1.length() < 2) {
                this.f.a(5, "EVENT,proxydetect no proxy address found");
                return false;
            }
            this.f.a(3, "EVENT,proxydetect " + byte1 + ":" + this.f.c(if1));
            this.e();
            String s2 = "" + "CONNECT " + string + " HTTP/1.0\r\n";
            if (this.f.e8.length() > 0) {
                s2 = s2 + this.f.e8 + "\r\n";
            }
            final String string2 = s2 + "User-agent: httpswebphone\r\n" + "\r\n";
            int n2 = 0;
            String s3 = this.a(byte1, if1, string2, null, 0, n, false);
            int n3 = s3.indexOf("HTTP/1.0 200");
            if (n3 < 0 || n3 > 5) {
                n3 = s3.indexOf("HTTP/1.1 200");
            }
            if (n3 < 0 || n3 > 5) {
                n3 = s3.indexOf("HTTP/1.0 202");
            }
            if (n3 < 0 || n3 > 5) {
                n3 = s3.indexOf("HTTP/1.1 202");
            }
            if (n3 < 0 || n3 > 5) {
                n3 = s3.indexOf("HTTP/1.0 200");
            }
            if (n3 >= 0 && n3 <= 5) {
                n2 = 1;
                if (this.f.cs > 10) {
                    this.f.cs = 10;
                }
                if (this.f.e8.length() < 1) {
                    this.f.a(3, "WARNING,proxydetect connect capability succeeed without auth");
                }
                else {
                    this.f.a(3, "WARNING,proxydetect connect capability succeeed with stored credentials");
                }
            }
            if (n2 == 0) {
                int n4 = 0;
                int i = 0;
                while (i < 3) {
                    if (this.byte.isterminated) {
                        break;
                    }
                    String s4 = s3.toLowerCase();
                    int n5 = s4.indexOf("\nproxy-authenticate:");
                    if (n5 < 1) {
                        n5 = s4.indexOf("\nwww-authenticate:");
                    }
                    if (n5 < 1) {
                        if (s4.length() < 1) {
                            this.f.a(3, "WARNING,proxydetect no answer from local http proxy");
                            break;
                        }
                        this.f.a(3, "WARNING,proxydetect no ok and no auth request");
                        break;
                    }
                    else {
                        String string3 = "";
                        for (int j = 0; j < 10; ++j) {
                            int n6 = s4.toLowerCase().indexOf("\nproxy-authenticate:");
                            if (n6 < 1) {
                                n6 = s4.indexOf("\nwww-authenticate:");
                            }
                            if (n6 < 1) {
                                break;
                            }
                            string3 += s4.substring(n6 + 1, s4.indexOf("\r", n6 + 10)).trim();
                            s4 = s4.substring(n6 + 15);
                        }
                        boolean b = true;
                        if (string3.indexOf("basic") >= 0) {
                            b = true;
                        }
                        else if (string3.indexOf("digest") >= 0 && this.f.b7) {
                            b = true;
                        }
                        else if (string3.indexOf("ntlm") >= 0 || string3.indexOf("kerberos") >= 0 || string3.indexOf("ldap") >= 0 || string3.indexOf("smb") >= 0 || string3.indexOf("sasl") >= 0 || string3.indexOf("msnt") >= 0 || string3.indexOf("pam") >= 0 || string3.indexOf("getpwam") >= 0 || string3.indexOf("negotiate") >= 0) {
                            b = false;
                        }
                        if (!b) {
                            n2 = 0;
                            break;
                        }
                        this.f.a(3, "WARNING,proxydetect auth try " + this.f.c(i));
                        this.f.e8 = "";
                        if (this.f.cH.length() < 1 || n4 != 0 || this.f.aI.length() < 1) {
                            this.byte.asyncneedproxyauthui = 1;
                            for (int k = 0; k < 1800; ++k) {
                                this.f.do(100L);
                                if (this.byte.asyncneedproxyauthui == 0) {
                                    this.f.a(3, "WARNING,proxydetect auth ui err");
                                    break;
                                }
                                if (this.byte.asyncneedproxyauthui == 3) {
                                    this.f.a(3, "WARNING,proxydetect auth done");
                                    break;
                                }
                                if (this.byte.asyncneedproxyauthui == 4) {
                                    this.f.a(3, "WARNING,proxydetect auth canceled");
                                    break;
                                }
                            }
                        }
                        if (this.f.cH.length() < 1 || n4 != 0 || this.f.aI.length() < 1) {
                            this.f.a(3, "WARNING,proxydetect auth done but no result");
                            break;
                        }
                        String e8;
                        if (string3.toLowerCase().indexOf("digest") >= 0) {
                            e8 = this.f.a(string3, "", "CONNECT", this.f.aI, this.f.cH);
                        }
                        else {
                            e8 = this.f.a(string3, this.f.aI, this.f.cH);
                        }
                        if (e8.length() <= 0) {
                            this.f.a(3, "WARNING,proxydetect auth alg ret null");
                            break;
                        }
                        final String string4 = "" + "CONNECT " + string + " HTTP/1.0\r\n" + e8 + "\r\n" + "User-agent: httpswebphone\r\n" + "\r\n";
                        this.e();
                        s3 = this.a(byte1, if1, string4, null, 0, n, false);
                        int n7 = s3.indexOf("HTTP/1.0 200");
                        if (n7 < 0 || n7 > 5) {
                            n7 = s3.indexOf("HTTP/1.1 200");
                        }
                        if (n7 < 0 || n7 > 5) {
                            n7 = s3.indexOf("HTTP/1.0 202");
                        }
                        if (n7 < 0 || n7 > 5) {
                            n7 = s3.indexOf("HTTP/1.1 202");
                        }
                        if (n7 < 0 || n7 > 5) {
                            n7 = s3.indexOf("HTTP/1.0 200");
                        }
                        if (n7 >= 0 && n7 <= 5) {
                            this.f.e8 = e8;
                            if (this.f.cs > 10) {
                                this.f.cs = 10;
                            }
                            this.byte.asynccfgsave = true;
                            this.f.a(3, "EVENT,proxydetect connect capability succeeed with auth");
                            n2 = 1;
                            break;
                        }
                        this.f.a(3, "WARNING,proxydetect auth failed " + this.f.c(i));
                        this.f.e8 = "";
                        if (s3.length() > 0 && (s3.indexOf("HTTP/1.0 401") >= 0 || s3.indexOf("HTTP/1.1 401") >= 0 || s3.indexOf("HTTP/1.0 407") >= 0 || s3.indexOf("HTTP/1.1 407") >= 0 || s3.indexOf("HTTP/1.0 404") >= 0 || s3.indexOf("HTTP/1.1 404") >= 0 || s3.indexOf("HTTP/1.0 405") >= 0 || s3.indexOf("HTTP/1.1 405") >= 0)) {
                            this.f.cH = "";
                        }
                        n4 = 1;
                        if (s3.length() < 1) {
                            break;
                        }
                        ++i;
                    }
                }
            }
            if (n2 == 0) {
                this.e();
                this.f.a(3, "WARNING,proxydetect connect not connected");
                return false;
            }
            final byte[] array = new byte[1024];
            final int a = this.f.a(array, (byte)0, false);
            final long do1 = this.f.do();
            for (int l = 0; l < 3; ++l) {
                final String a2 = this.a(byte1, if1, "", array, a, n, true);
                if (a2.length() > 2 && (a2.indexOf("Pax") == 0 || a2.equals("PINGPACKET") || a2.equals("PINGPACKET2"))) {
                    this.e();
                    this.f.a(3, "EVENT,proxydetect connect answer received");
                    return true;
                }
                if (this.f.do() - do1 > n * (1 + l)) {
                    break;
                }
            }
            this.f.a(3, "WARNING,proxydetect connect answer not received");
        }
        catch (Exception ex) {
            this.f.e8 = "";
            this.f.cH = "";
            this.f.if(3, "HTTPProxyConnectDetect", ex);
        }
        this.e();
        return false;
    }
    
    public void b() {
        if (!this.f.cR) {
            return;
        }
        try {
            DatagramSocket datagramSocket = null;
            int n = 0;
            final byte[] array = new byte[1024];
            final int a = this.f.a(array, (byte)0, true);
            this.f.a(new byte[1024], (byte)0, true);
            this.f.a(new byte[1024], (byte)0, true);
            String s = this.byte.serveraddr;
            int n2 = this.byte.serverport;
            if (n2 < 1) {
                n2 = this.byte.proxyport;
            }
            if (n2 < 1) {}
            if (s.length() < 1) {
                s = this.byte.proxyaddr;
            }
            if (s.length() < 1) {
                s = this.f.fm;
            }
            String proxyaddr = this.f.char(s);
            int proxyport = this.f.a(true, false);
            this.byte.serverdomainandport = proxyaddr + ":" + this.f.c(proxyport);
            this.byte.usr_serverdomainandport = this.byte.serverdomainandport;
            this.byte.usr_serverport = proxyport;
            this.byte.serverport = proxyport;
            this.byte.proxydomainandport = this.byte.usr_serverdomainandport;
            this.byte.proxyaddr = proxyaddr;
            this.byte.proxyport = proxyport;
            this.byte.usr_proxydomainandport = this.byte.usr_serverdomainandport;
            this.byte.usr_proxyaddr = proxyaddr;
            this.byte.usr_proxyport = proxyport;
            this.byte.jComboBox1xx.addItem(this.byte.serverdomainandport);
            this.byte.jComboBox1xx.setSelectedItem(this.byte.serverdomainandport);
            this.f.a(3, "EVENT, using random UDP port " + this.f.c(proxyport));
            this.f.byte("", proxyaddr);
            final byte[] array2 = new byte[1600];
            boolean b = true;
            if (this.f.ed > 13) {
                b = false;
                this.f.cM = 0;
                this.f.a(1);
                final aw f = this.f;
                ++f.cV;
                if (this.f.cV > 10) {
                    this.f.a(3, "EVENT, not skipping udp transport because too many skip " + this.f.c(this.f.ed) + " " + this.f.c(this.f.cV));
                    this.f.ed = 1;
                    this.f.cV = 0;
                    b = true;
                }
                else {
                    this.f.a(3, "EVENT, skip udp transport because subs switch " + this.f.c(this.f.ed) + " " + this.f.c(this.f.cV));
                }
            }
            if (this.f.cG > 3) {
                if (++this.f.C > 15) {
                    this.f.a(3, "EVENT, not skipping direct udp transport because too many skip " + this.f.c(this.f.cG) + " " + this.f.c(this.f.C));
                    this.f.C = 0;
                    this.f.cG = 1;
                }
                else {
                    this.f.a(3, "EVENT, skip direct udp transport because subs switch " + this.f.c(this.f.cG) + " " + this.f.c(this.f.C));
                }
            }
            if (true && this.f.at.length() > 0) {
                try {
                    proxyaddr = this.f.at;
                    proxyport = 5060;
                    datagramSocket = new DatagramSocket();
                    int localPort = datagramSocket.getLocalPort();
                    if (localPort < 1) {
                        localPort = 49211;
                    }
                    String username = this.byte.username;
                    if (username.length() < 1) {
                        username = "test_webphhone";
                    }
                    if (this.f.at.indexOf(":") > 0) {
                        proxyaddr = this.f.at.substring(0, this.f.at.indexOf(":")).trim();
                        proxyport = this.f.for(this.f.at.substring(this.f.at.indexOf(":") + 1).trim(), 5060);
                    }
                    final String string = "" + "REGISTER sip:" + this.f.at + " SIP/2.0\r\n" + "Call-ID: " + this.f.if(this.f.if()) + "test\r\n" + "Via: SIP/2.0/UDP " + this.f.d() + ":" + this.f.c(localPort) + ";branch=z9hG4bK-" + this.f.c(this.f.a(0, 999999)) + ";rport\r\n" + "From: <sip:" + username + "@" + this.f.at + ">;tag=" + this.f.c(this.f.a(0, 999999)) + "\r\n" + "To: <sip:" + username + "@" + this.f.at + ">\r\n" + "Contact: <sip:" + this.f.d() + ":" + this.f.c(localPort) + ">\r\n" + "CSeq: " + this.f.c(this.f.a(1, 4000)) + " REGISTER\r\n" + "Expires: 60\r\n" + "Event: registration\r\n" + "User-Agent: WebphoneTest\r\n" + "Content-Length: 0\r\n\r\n";
                    this.a(3, "SEND:  to " + proxyaddr + ":" + this.f.c(proxyport) + "\r\n" + string);
                    if ((this.f.eQ == 0 || this.f.eQ < 0) && this.f.cG < 1) {
                        datagramSocket.setSoTimeout(1600);
                    }
                    else {
                        datagramSocket.setSoTimeout(800);
                    }
                    int n3;
                    if (this.f.cG < 1) {
                        n3 = 3;
                    }
                    else if (this.f.cG == 1) {
                        n3 = 2;
                    }
                    else {
                        n3 = 1;
                    }
                    for (int i = 0; i < n3; ++i) {
                        datagramSocket.send(new DatagramPacket(string.getBytes(), string.length(), InetAddress.getByName(proxyaddr), proxyport));
                    }
                    final DatagramPacket datagramPacket = new DatagramPacket(array2, array2.length);
                    datagramSocket.receive(datagramPacket);
                    if (datagramPacket.getLength() > 30) {
                        n = 1;
                    }
                    else {
                        datagramSocket.receive(datagramPacket);
                        if (datagramPacket.getLength() > 30) {
                            n = 1;
                        }
                    }
                }
                catch (Exception ex) {
                    this.f.if(4, "TransportAutoDetectDirect", ex);
                }
                if (n != 0) {
                    final aw f2 = this.f;
                    f2.cG -= 2;
                    if (this.f.cG < 0) {
                        this.f.cG = 0;
                    }
                    this.a(2, "EVENT,autodetected direct UDP transport to " + proxyaddr);
                    this.f.dK = 0;
                    this.f.t = false;
                    this.f.cR = false;
                    this.byte.serverdomainandport = this.f.at;
                    this.byte.serveraddr = proxyaddr;
                    this.byte.serverport = proxyport;
                    this.byte.usr_serverdomainandport = this.f.at;
                    this.byte.usr_serveraddr = proxyaddr;
                    this.byte.usr_serverport = proxyport;
                    this.byte.jComboBox1xx.addItem(this.byte.serverdomainandport);
                    this.byte.jComboBox1xx.setSelectedItem(this.byte.serverdomainandport);
                    this.byte.proxydomainandport = this.byte.usr_serverdomainandport;
                    this.byte.proxyaddr = proxyaddr;
                    this.byte.proxyport = proxyport;
                    this.byte.usr_proxydomainandport = this.byte.usr_serverdomainandport;
                    this.byte.usr_proxyaddr = proxyaddr;
                    this.byte.usr_proxyport = proxyport;
                    if (this.byte.proxyaddr.length() > 0) {
                        this.byte.proxyaddr = proxyaddr;
                        this.byte.proxyport = proxyport;
                    }
                    this.byte.asynccfgsave = true;
                    return;
                }
                final aw f3 = this.f;
                ++f3.cG;
                if (this.f.cG > 6) {
                    this.f.cG = 6;
                }
                String s2 = this.byte.serveraddr;
                int n4 = this.byte.serverport;
                if (n4 < 1) {
                    n4 = this.byte.proxyport;
                }
                if (n4 < 1) {}
                if (s2.length() < 1) {
                    s2 = this.byte.proxyaddr;
                }
                if (s2.length() < 1) {
                    s2 = this.f.fm;
                }
                proxyaddr = this.f.char(s2);
                proxyport = this.f.a(true, true);
                this.byte.serverport = proxyport;
                this.byte.proxyport = proxyport;
            }
            if (this.f.m && b) {
                try {
                    datagramSocket = new DatagramSocket();
                    if (this.f.eQ == 0 || this.f.eQ < 0) {
                        datagramSocket.setSoTimeout(1600);
                    }
                    else {
                        datagramSocket.setSoTimeout(800);
                    }
                    final int for1 = this.f.for(1, 5);
                    int n5;
                    int n6;
                    if (for1 < 2) {
                        n5 = 1;
                        n6 = 2;
                    }
                    else if (for1 > 3) {
                        n5 = 3;
                        n6 = 0;
                    }
                    else if (for1 > 2) {
                        n5 = 4;
                        n6 = 0;
                    }
                    else {
                        n5 = 2;
                        n6 = 0;
                    }
                    for (int j = 0; j < n5; ++j) {
                        datagramSocket.send(new DatagramPacket(array, a, InetAddress.getByName(proxyaddr), proxyport));
                    }
                    final DatagramPacket datagramPacket2 = new DatagramPacket(array2, array2.length);
                    datagramSocket.receive(datagramPacket2);
                    if (this.f.a(datagramPacket2.getData(), datagramPacket2.getLength())) {
                        n = 1;
                    }
                    else {
                        datagramSocket.receive(datagramPacket2);
                        if (this.f.a(datagramPacket2.getData(), datagramPacket2.getLength())) {
                            n = 1;
                        }
                    }
                    if (n == 0 && n6 > 0) {
                        for (int k = 0; k < n6; ++k) {
                            datagramSocket.send(new DatagramPacket(array, a, InetAddress.getByName(proxyaddr), proxyport));
                        }
                        final DatagramPacket datagramPacket3 = new DatagramPacket(array2, array2.length);
                        datagramSocket.receive(datagramPacket3);
                        if (this.f.a(datagramPacket3.getData(), datagramPacket3.getLength())) {
                            n = 1;
                        }
                        else {
                            datagramSocket.receive(datagramPacket3);
                            if (this.f.a(datagramPacket3.getData(), datagramPacket3.getLength())) {
                                n = 1;
                            }
                        }
                    }
                    if (this.f.dS > 0 && n != 0 && this.f.cM < 3) {
                        if (datagramSocket != null) {
                            try {
                                datagramSocket.close();
                            }
                            catch (Exception ex6) {}
                        }
                        n = 0;
                        final k l = new k(this, proxyaddr, proxyport);
                        final int a2 = this.f.a(0, 20);
                        if (l.if()) {
                            boolean b2 = false;
                            for (int n7 = 0; n7 < 80 + a2; ++n7) {
                                if (!l.do()) {
                                    this.a(4, "WARNING,UDP transportcheck cannot send");
                                    b2 = true;
                                    break;
                                }
                                if (n7 > 20 + a2 && (l.do < 1 || l.for < 10)) {
                                    this.a(4, "WARNING,UDP transportcheck no packets received");
                                    b2 = true;
                                    break;
                                }
                                if (n7 > 30 + a2 && l.for() < this.f.dS - 4) {
                                    this.a(4, "EVENT,UDP transport low packet loss detected at round " + this.f.c(n7) + ": " + this.f.c(l.for()) + " " + this.f.c(l.for) + " " + this.f.c(l.do));
                                    break;
                                }
                                if (n7 > 40 + a2 && l.for() > this.f.dS * 4) {
                                    this.a(4, "EVENT,UDP transport high packet loss detected at round " + this.f.c(n7) + ": " + this.f.c(l.for()) + " " + this.f.c(l.for) + " " + this.f.c(l.do));
                                    b2 = true;
                                    break;
                                }
                                this.f.do(15L);
                            }
                            if (!b2) {
                                int n8;
                                for (n8 = 0; n8 < 4 && l.for() > this.f.dS && l.for() < 100; ++n8) {
                                    this.f.do(75L);
                                }
                                if (l.for() > this.f.dS) {
                                    this.a(3, "WARNING,UDP transport too high packet loss detected: " + this.f.c(l.for()) + " " + this.f.c(l.for) + " " + this.f.c(l.do));
                                }
                                else {
                                    this.a(4, "EVENT,UDP transport low packet loss detected at stage " + this.f.c(n8) + ": " + this.f.c(l.for()) + " " + this.f.c(l.for) + " " + this.f.c(l.do));
                                    n = 1;
                                }
                            }
                            l.a();
                        }
                        if (n == 0) {
                            this.f.a(1);
                            this.f.cM = 0;
                        }
                        else {
                            final aw f4 = this.f;
                            ++f4.cM;
                        }
                    }
                }
                catch (Exception ex2) {
                    this.f.if(4, "TransportAutoDetectUDP", ex2);
                }
            }
            if (datagramSocket != null) {
                try {
                    datagramSocket.close();
                }
                catch (Exception ex7) {}
            }
            this.byte.asynccfgsave = true;
            if (n != 0) {
                this.a(2, "EVENT,autodetected UDP transport");
                this.f.dK = 0;
                return;
            }
            final byte[] array3 = new byte[1024];
            final int a3 = this.f.a(array3, (byte)0, false);
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            if (n == 0 && this.f.fe) {
                try {
                    socket = new Socket(Proxy.NO_PROXY);
                    int soTimeout = 1800;
                    if (this.f.eQ != 4 && this.f.eQ >= 0) {
                        soTimeout = 1000;
                    }
                    socket.setSoTimeout(soTimeout);
                    socket.connect(new InetSocketAddress(proxyaddr, this.f.dn), soTimeout);
                    inputStream = socket.getInputStream();
                    outputStream = socket.getOutputStream();
                    outputStream.write(array3, 0, a3);
                    final long do1 = this.f.do();
                    final byte[] array4 = new byte[1024];
                    int n9 = 0;
                    for (int n10 = 0; n10 < soTimeout / 100; ++n10) {
                        if (this.byte.isterminated) {
                            break;
                        }
                        final int read = inputStream.read(array2, 0, array2.length - 10);
                        if (read <= 0) {
                            break;
                        }
                        if (read + n9 < 1000) {
                            System.arraycopy(array2, 0, array4, n9, read);
                            n9 += read;
                            if (this.f.a(array4, n9)) {
                                this.f.b4 = false;
                                n = 1;
                                break;
                            }
                        }
                        else {
                            n9 = 0;
                        }
                        this.f.do(100L);
                        if (inputStream.available() < 1) {
                            if (n10 >= 2) {
                                break;
                            }
                            outputStream.write(array3, 0, a3);
                            this.f.do(100L);
                        }
                        if (this.f.do() - do1 > soTimeout) {
                            break;
                        }
                    }
                }
                catch (Exception ex3) {
                    this.f.if(4, "TransportAutoDetectTCP", ex3);
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    }
                    catch (Exception ex8) {}
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    }
                    catch (Exception ex9) {}
                }
                if (socket != null) {
                    try {
                        socket.close();
                    }
                    catch (Exception ex10) {}
                }
            }
            if (n != 0) {
                this.f.dK = 4;
                this.f.eI = 1;
                this.f.t = true;
                this.a(2, "EVENT,autodetected direct TCP tunneling transport");
                return;
            }
            if (n == 0 && this.f.int) {
                try {
                    socket = new Socket();
                    int soTimeout2 = 1800;
                    if (this.f.eQ != 4 && this.f.eQ >= 0) {
                        soTimeout2 = 1000;
                    }
                    socket.setSoTimeout(soTimeout2);
                    socket.connect(new InetSocketAddress(proxyaddr, this.f.dn), soTimeout2);
                    inputStream = socket.getInputStream();
                    outputStream = socket.getOutputStream();
                    outputStream.write(array3, 0, a3);
                    final long do2 = this.f.do();
                    String string2 = "";
                    for (int n11 = 0; n11 < soTimeout2 / 100; ++n11) {
                        if (this.byte.isterminated) {
                            break;
                        }
                        int read2 = inputStream.read(array2, 0, array2.length - 10);
                        if (read2 <= 0) {
                            break;
                        }
                        if (read2 > 10) {
                            read2 = 10;
                        }
                        array2[read2] = 0;
                        string2 += new String(array2, 0, read2);
                        if (string2.length() > 2 && string2.indexOf("Pax") == 0) {
                            n = 1;
                            this.f.b4 = true;
                            break;
                        }
                        if (string2.length() > 0 && string2.charAt(0) != 'P') {
                            string2 = "";
                            outputStream.write(array, 0, array.length);
                        }
                        else {
                            this.f.do(100L);
                            if (inputStream.available() < 1) {
                                if (n11 >= 2) {
                                    break;
                                }
                                outputStream.write(array, 0, array.length);
                                this.f.do(100L);
                            }
                            if (this.f.do() - do2 > soTimeout2) {
                                break;
                            }
                        }
                    }
                }
                catch (Exception ex4) {
                    this.f.if(4, "TransportAutoDetectTCPProxy", ex4);
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    }
                    catch (Exception ex11) {}
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    }
                    catch (Exception ex12) {}
                }
                if (socket != null) {
                    try {
                        socket.close();
                    }
                    catch (Exception ex13) {}
                }
            }
            if (n != 0) {
                this.f.dK = 4;
                this.f.eI = 1;
                this.f.t = true;
                this.a(2, "EVENT,autodetected TCP tunneling transport via proxy");
                return;
            }
            if (this.f.an) {
                int n12 = 2000;
                if (this.f.eQ != 5 && this.f.eQ >= 0) {
                    n12 = 1300;
                }
                if (this.a(proxyaddr, n12)) {
                    this.f.dK = 5;
                    this.f.eI = 1;
                    this.f.t = true;
                    this.a(2, "EVENT,autodetected HTTP proxy tunneling transport");
                    return;
                }
            }
            if (this.f.dt) {
                this.f.dK = 3;
                this.f.eI = 1;
                this.f.t = true;
                this.a(2, "EVENT,autodetected HTTP tunneling transport");
            }
            else {
                this.f.dK = 0;
                this.f.eI = 1;
                this.f.t = true;
            }
        }
        catch (Exception ex5) {
            this.f.if(3, "TransportAutoDetectExt", ex5);
        }
    }
    
    public void f() {
        try {
            this.f.b9 = true;
            if (this.f.aM > 0) {
                this.j = this.f.aM;
            }
            else if (this.f.eE > 0) {
                this.j = this.f.eE;
            }
            else if (this.f.ai > 0) {
                this.j = this.f.ai;
            }
            else if (this.f.Y > 0) {
                this.j = this.f.Y;
            }
            else {
                this.j = this.f.for(10000, 20000);
            }
            this.if = new bi(this);
            this.try = Collections.synchronizedList(new LinkedList<Object>());
            this.a = new s(this);
            this.b();
            if (this.f.dK == 0) {
                String s = this.byte.serveraddr;
                if (this.byte.proxyaddr.length() > 0) {
                    s = this.byte.proxyaddr;
                }
                this.f.af = this.f.char(s);
                this.f.do(false);
                this.l = new i(this);
                this.j = this.l.a(this.j);
                if (this.j < 1) {
                    this.a(1, "ERROR,failed to init main sip listener");
                    return;
                }
                this.l.start();
            }
            else if (this.f.dK == 3 && this.f.fm.length() > 0) {
                String s2;
                int n;
                if (this.f.fm.indexOf(":") > 0) {
                    s2 = this.f.fm.substring(0, this.f.fm.indexOf(":")).trim();
                    n = this.f.for(this.f.fm.substring(this.f.fm.indexOf(":") + 1).trim(), 5060);
                }
                else {
                    s2 = this.f.fm;
                    n = 5060;
                }
                if (this.f.dT > 0) {
                    n = this.f.dT;
                }
                this.new = new aq(this);
                this.j = this.new.a(s2, n, this.j);
                this.new.start();
                this.f.do(50L);
                this.f.do(0L);
                this.f.do(1L);
                this.f.do(50L);
            }
            else {
                final String serveraddr = this.byte.serveraddr;
                final int serverport = this.byte.serverport;
                final String char1 = this.f.char(serveraddr);
                int n2 = this.byte.proxyport;
                String char2 = this.f.char(this.byte.proxyaddr);
                if (n2 < 1) {
                    n2 = serverport;
                }
                if (char2.length() < 4) {
                    char2 = char1;
                }
                if (this.f.dT > 0 && this.f.dK == 3) {
                    n2 = this.f.dT;
                }
                if (this.f.dK == 3 && this.f.aX > 0) {
                    this.new = new aq(this);
                    this.j = this.new.a(char2, n2, this.j);
                    this.new.start();
                    this.f.do(50L);
                    this.f.do(0L);
                    this.f.do(1L);
                    this.f.do(50L);
                }
                else {
                    this.m = new w(this);
                    if (this.f.dn > 0 && this.f.dK == 4) {
                        n2 = this.f.dn;
                    }
                    this.j = this.m.a(char2, n2, this.j);
                    this.m.start();
                }
            }
            this.if.start();
            final aw f = this.f;
            ++f.al;
        }
        catch (Exception ex) {
            this.a(1, "mainInit", ex);
        }
    }
    
    public void a(final int n, String string) {
        if (this.f.eK > 3) {
            string = string + " [mt: " + this.f.c(this.h) + "]";
        }
        this.f.a(n, string);
    }
    
    public void a(final int n, String string, final Exception ex) {
        if (this.f.eK > 3) {
            string = string + " [mt: " + this.f.c(this.h) + "]";
        }
        this.f.a(n, string, ex);
    }
    
    public int do() {
        if (this.f.Y > 0) {
            return this.f.Y;
        }
        if (this.j > 0) {
            return this.j;
        }
        if (this.f.eE > 0) {
            return this.f.eE;
        }
        if (this.f.ai > 0) {
            return this.f.ai;
        }
        return 5060;
    }
    
    public void goto() {
        try {
            this.do = true;
            this.int();
            this.a(true);
            Thread.sleep(1L);
            if (this.f.bX > 0) {
                Thread.sleep(1L);
                Thread.sleep(0L);
                Thread.sleep(1L);
                Thread.sleep(0L);
                this.f.do((long)this.f.bX);
                Thread.sleep(1L);
                Thread.sleep(0L);
                Thread.sleep(1L);
                Thread.sleep(0L);
            }
            this.try = null;
            if (this.l != null) {
                this.l.if();
            }
            if (this.m != null) {
                this.m.new();
            }
            if (this.new != null) {
                this.new.new();
            }
            if (this.if != null) {
                this.if.do();
            }
            this.new = null;
            this.m = null;
            this.l = null;
            this.a = null;
            this.if = null;
        }
        catch (Exception ex) {
            this.a(2, "mainDestroy", ex);
        }
    }
    
    public boolean byte() {
        try {
            if (this.i) {
                return false;
            }
            if (this.try == null) {
                this.a(3, "ERROR,eplist is null on try SendNATKeepalive");
                return false;
            }
            final Iterator<t> iterator = this.try.iterator();
            while (iterator.hasNext()) {
                final t t = iterator.next();
                if (t != null && t.aU == 3 && t.B == 1) {
                    this.a(3, "EVENT,destroying old noptions endpoint");
                    t.j();
                    iterator.remove();
                }
            }
            this.a(3, "EVENT,creating natkeepalive endpoint");
            final t t2 = new t(this.byte, this);
            t2.B = 1;
            t2.aU = 3;
            this.try.add(t2);
            t2.e();
            t2.e();
            return true;
        }
        catch (Exception ex) {
            this.a(2, "mainSendNATKeepalive", ex);
            return false;
        }
    }
    
    public boolean for() {
        try {
            if (this.i) {
                return false;
            }
            if (this.try == null) {
                this.a(3, "ERROR,eplist is null on try SendOptions");
                return false;
            }
            final Iterator<t> iterator = this.try.iterator();
            while (iterator.hasNext()) {
                final t t = iterator.next();
                if (t != null && t.aU == 3 && t.B == 1) {
                    this.a(3, "EVENT,destroying old options endpoint");
                    t.j();
                    iterator.remove();
                }
            }
            this.a(3, "EVENT,creating options endpoint");
            final t t2 = new t(this.byte, this);
            t2.B = 1;
            t2.aU = 3;
            this.try.add(t2);
            t2.char();
            return true;
        }
        catch (Exception ex) {
            this.a(2, "mainSendOptions", ex);
            return false;
        }
    }
    
    public boolean d() {
        try {
            for (final t t : this.try) {
                if (t != null && t.aU >= 7 && t.aU < 15 && t.af != 2) {
                    t.af = 1;
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "SetupConf", ex);
        }
        return false;
    }
    
    public boolean a(final boolean b) {
        try {
            if (this.i) {
                return false;
            }
            this.g = false;
            if (this.try == null) {
                this.a(3, "ERROR,eplist is null on try unregister");
                this.g = true;
                return false;
            }
            if (b) {
                t t = null;
                for (final t t2 : this.try) {
                    if (t2 != null && t2.aU == 4 && t2.u < 1) {
                        this.a(3, "EVENT,unregistering old endpoint");
                        t2.if();
                        t = t2;
                    }
                }
                if (this.f.w > 0L && t != null) {
                    long w = this.f.w;
                    if (this.do) {
                        w /= 3L;
                    }
                    for (int n = 0; n < w / 100L; ++n) {
                        if (t.u > 2) {
                            break;
                        }
                        this.f.do(100L);
                    }
                }
            }
            final Iterator<t> iterator2 = this.try.iterator();
            while (iterator2.hasNext()) {
                final t t3 = iterator2.next();
                if (t3 != null) {
                    this.a(3, "EVENT,destroying old endpoint");
                    t3.j();
                    iterator2.remove();
                }
            }
            this.a(4, "EVENT,unregistering finished");
            this.d = true;
            return this.g = true;
        }
        catch (Exception ex) {
            this.a(2, "mainUnRegister", ex);
            this.g = true;
            return false;
        }
    }
    
    public boolean new() {
        try {
            if (this.i) {
                return false;
            }
            if (this.try == null) {
                this.a(3, "ERROR,eplist is null on try register");
                return false;
            }
            this.f.bQ = this.f.do();
            final Iterator<t> iterator = this.try.iterator();
            while (iterator.hasNext()) {
                final t t = iterator.next();
                if (t != null && t.aU == 4) {
                    this.a(3, "EVENT,destroying old reg endpoint");
                    t.j();
                    iterator.remove();
                }
            }
            this.a(3, "EVENT,creating reg endpoint");
            final t for1 = new t(this.byte, this);
            for1.B = 1;
            for1.aU = 4;
            this.try.add(for1);
            for1.else();
            this.for = for1;
            return this.d = true;
        }
        catch (Exception ex) {
            this.a(1, "mainRegister", ex);
            return false;
        }
    }
    
    public boolean a(final String s, final int n, final String s2, final t t) {
        try {
            if (this.f.eK > 2 && s2.length() > 6) {
                int n2 = 0;
                if (this.l != null && this.f.dK == 0) {
                    n2 = this.l.a;
                }
                else if (this.m != null) {
                    n2 = this.m.void;
                }
                else if (this.new != null) {
                    n2 = this.new.j;
                }
                else if (this.l != null) {
                    n2 = this.l.a;
                }
                this.a(3, "SEND:  to " + s + ":" + this.f.c(n) + " from port " + this.f.c(n2) + "\r\n" + s2);
            }
            final byte[] bytes = s2.getBytes();
            return this.if.if(new bf(this.f, s, n, bytes, bytes.length, t, false));
        }
        catch (Exception ex) {
            this.a(1, "mainSend", ex);
            return false;
        }
    }
    
    public void if() {
        if (this.char) {
            return;
        }
        try {
            if (this.f.dl < 2 && this.f.cL < 2) {
                return;
            }
            this.char = true;
            final long do1 = this.f.do();
            if (!this.f.bl) {
                if (this.f.em > 0) {
                    this.f.em = 1;
                }
                if (this.f.aD > 0) {
                    this.f.aD = 1;
                }
            }
            if (this.f.em > 1) {
                final a0 a0 = new a0(this.f);
                if (a0 == null) {
                    return;
                }
                a0.e = this.f.s;
                if (a0.byte()) {
                    a0.try();
                    final a6 a2 = new a6(this.f, this.f.s);
                    if (a2 == null) {
                        return;
                    }
                    if (a2.case()) {
                        a2.byte();
                        this.a(3, "EVENT, wideband cabability ok");
                    }
                    else {
                        a2.byte();
                        if (this.f.em > 0) {
                            this.f.em = 1;
                        }
                        if (this.f.aD > 0) {
                            this.f.aD = 1;
                        }
                        this.f.bl = false;
                        this.a(3, "WARNING, wideband disabled");
                    }
                }
                else {
                    a0.try();
                    if (this.f.em > 0) {
                        this.f.em = 1;
                    }
                    if (this.f.aD > 0) {
                        this.f.aD = 1;
                    }
                    this.f.bl = false;
                    this.a(3, "WARNING, wideband disabled");
                }
            }
            if (this.f.aD > 1) {
                final a0 a3 = new a0(this.f);
                if (a3 == null) {
                    return;
                }
                a3.e = this.f.ea;
                if (a3.byte()) {
                    a3.try();
                    final a6 a4 = new a6(this.f, this.f.ea);
                    if (a4 == null) {
                        return;
                    }
                    if (a4.case()) {
                        a4.byte();
                        this.a(3, "EVENT, ultrawideband cabability ok");
                    }
                    else {
                        a4.byte();
                        if (this.f.aD > 0) {
                            this.f.aD = 1;
                        }
                        if (this.f.em < 1) {
                            this.f.bl = false;
                        }
                        this.a(3, "WARNING, ultrawideband disabled");
                    }
                }
                else {
                    a3.try();
                    if (this.f.aD > 0) {
                        this.f.aD = 1;
                    }
                    if (this.f.em < 1) {
                        this.f.bl = false;
                    }
                    this.a(3, "WARNING, ultrawideband disabled");
                }
            }
            this.f.z = this.f.bD;
            this.f.dl = this.f.em;
            this.f.cL = this.f.aD;
            if (this.f.do() - do1 >= 3000L) {
                this.a(3, "WARNING, audio capability check took " + this.f.c((int)((this.f.do() - do1) / 1000L)) + " seconds");
            }
        }
        catch (Exception ex) {
            this.a(3, "audio test on first call", ex);
        }
    }
    
    public boolean do(int a) {
        try {
            if (this.i) {
                return false;
            }
            if (this.f.df < 2) {
                a = 1;
            }
            if (this.try == null) {
                this.a(3, "ERROR,eplist is null on call");
                return false;
            }
            final aw f = this.f;
            ++f.bm;
            final aw f2 = this.f;
            if (0 > 1 && this.f.bm > 10) {
                this.a(0, "ERROR,trial version need restart to continue (max 10 calls per session is allowed)");
                return false;
            }
            if (this.f.ab && (this.k < 2 || this.k == 3)) {
                this.c();
                if (this.k < 2 || this.k == 3) {
                    this.a(0, "ERROR, Not connected");
                    return false;
                }
            }
            this.a(3, "EVENT,init call");
            a = this.a(a);
            if (a < 1) {
                return false;
            }
            if (this.f.ev > 1) {
                for (final t t : this.try) {
                    if (t != null && t.aU >= 7 && t.aU <= 16) {
                        t.a(true, 0);
                    }
                }
            }
            if (this.f.eN > 1) {
                for (final t t2 : this.try) {
                    if (t2 != null && t2.aU >= 13 && t2.aU <= 16) {
                        t2.int(true);
                    }
                }
            }
            if (this.f.dH && this.f.new(this.byte.called)) {
                if (this.f.dl > 1) {
                    this.f.dl = 1;
                }
                if (this.f.cL > 1) {
                    this.f.cL = 1;
                }
            }
            else {
                this.f.z = this.f.bD;
                this.f.dl = this.f.em;
                this.f.cL = this.f.aD;
            }
            this.if();
            this.f.cu = false;
            final t for1 = new t(this.byte, this);
            for1.B = 1;
            for1.aU = 7;
            for1.a8 = a;
            this.byte.activeline = for1.a8;
            this.try.add(for1);
            final boolean a2 = for1.a();
            this.d = true;
            this.for = for1;
            return a2;
        }
        catch (Exception ex) {
            this.a(1, "mainCall", ex);
            return false;
        }
    }
    
    public boolean a(final t t, final String r, final String s, final int a8) {
        try {
            if (this.i) {
                return false;
            }
            if (t == null || t.aU != 6 || t.B != 1) {
                for (final t t2 : this.try) {
                    if (t2 == null) {
                        continue;
                    }
                    if (t2.aU == 6 && t2.B == 1 && t2.r == r && (a8 < 1 || a8 == t2.a8)) {
                        return t2.do(s);
                    }
                }
                final t for1 = new t(this.byte, this);
                for1.B = 1;
                for1.aU = 6;
                for1.r = r;
                if (a8 > 0) {
                    for1.a8 = a8;
                }
                this.try.add(for1);
                final boolean do1 = for1.do(s);
                this.for = for1;
                this.d = true;
                return do1;
            }
            return t.do(s);
        }
        catch (Exception ex) {
            this.a(1, "SendChatX", ex);
            return false;
        }
    }
    
    public boolean for(final t t) {
        try {
            this.a(3, "EVENT,incoming call");
            this.f.cy = "";
            this.f.eH = "";
            if (t == null || this.byte.isterminated) {
                return false;
            }
            int a8 = t.a8;
            if (a8 < 1) {
                a8 = this.a(-1);
            }
            if (a8 < 1) {
                t.if("no line");
            }
            else {
                this.f.z = this.f.bD;
                this.f.dl = this.f.em;
                this.f.cL = this.f.aD;
                this.if();
                this.f.eY = true;
                this.byte.asyncrec_call = t;
                if (t.a8 < 1) {
                    t.a8 = a8;
                }
            }
            this.d = true;
            this.for = t;
            return true;
        }
        catch (Exception ex) {
            this.a(1, "main.IncomingCall", ex);
            return false;
        }
    }
    
    public boolean a(final t asyncrec_chat) {
        try {
            this.a(3, "EVENT,incoming chat");
            if (asyncrec_chat == null || this.byte.isterminated) {
                return false;
            }
            this.byte.asyncrec_chat = asyncrec_chat;
            return true;
        }
        catch (Exception ex) {
            this.a(1, "main.IncomingChat", ex);
            return false;
        }
    }
    
    public void try(int n) {
        try {
            if (this.try == null) {
                return;
            }
            if (this.f.df < 2) {
                n = 1;
            }
            if (n > 0) {
                final t if1 = this.if(n, false);
                if (if1 != null && if1.aU >= 7 && if1.aU < 15) {
                    if1.a("main hangup1");
                    this.for = if1;
                    this.d = true;
                    return;
                }
            }
            for (final t for1 : this.try) {
                if (for1 == null) {
                    continue;
                }
                if (for1 == null || for1.aU < 7 || for1.aU >= 15) {
                    continue;
                }
                for1.a("main hangup2");
                this.for = for1;
                if (n != -2) {
                    break;
                }
            }
            this.f.dI = true;
            this.d = true;
        }
        catch (Exception ex) {
            this.a(1, "mainHangup", ex);
        }
    }
    
    public boolean char() {
        try {
            if (this.try == null) {
                return false;
            }
            for (final t t : this.try) {
                if (t != null && t.aU >= 8 && t.aU < 15) {
                    return true;
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "HasEpInCall", ex);
        }
        return false;
    }
    
    public boolean if(final t t) {
        try {
            if (this.try == null) {
                return false;
            }
            for (final t t2 : this.try) {
                if (t2 != null && t2 != t && t2.aU >= 8 && t2.aU < 15) {
                    return true;
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "HasEpInCallExcept", ex);
        }
        return false;
    }
    
    public boolean if(final int n) {
        try {
            if (this.try == null) {
                return false;
            }
            for (final t t : this.try) {
                if (t == null) {
                    continue;
                }
                if (t.B == 0 && t.aU >= 7 && t.aU < 12 && (n < 1 || t.a8 == n)) {
                    return true;
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "HasIncomingCallInprogress", ex);
        }
        return false;
    }
    
    public boolean g() {
        try {
            if (this.try == null) {
                return false;
            }
            for (final t t : this.try) {
                if (t != null && t.B == 0 && t.aU >= 7 && t.aU < 13) {
                    return true;
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "HasIncomingCall", ex);
        }
        return false;
    }
    
    public int a() {
        int n = 0;
        try {
            if (this.try == null) {
                return 0;
            }
            for (final t t : this.try) {
                if (t != null && t.aU >= 7 && t.aU < 15) {
                    ++n;
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "GetCallsInProgress", ex);
        }
        return n;
    }
    
    public boolean for(final int n, final boolean b) {
        try {
            if (this.try == null) {
                return true;
            }
            for (final t t : this.try) {
                if (t != null && t.a8 == n && t.aU >= 7 && t.aU < 15) {
                    return false;
                }
            }
            if (b) {
                for (final t t2 : this.try) {
                    if (t2 != null && t2.a8 == n && t2.aU >= 7 && t2.aU < 16) {
                        return false;
                    }
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "LineIsFree", ex);
        }
        return true;
    }
    
    public int a(final int n) {
        final int new1 = this.new(n);
        if (new1 < 1) {
            this.a(1, "ERROR,no free line");
        }
        return new1;
    }
    
    public int new(final int n) {
        try {
            if (this.f.df < 2) {
                return 1;
            }
            if (n > 0 && this.for(n, true)) {
                return n;
            }
            for (int i = 1; i <= this.f.df; ++i) {
                if (this.for(i, true)) {
                    return i;
                }
            }
            if (n > 0 && this.for(n, false)) {
                return n;
            }
            for (int j = 1; j <= this.f.df; ++j) {
                if (this.for(j, false)) {
                    return j;
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "GetFreeLine", ex);
        }
        return 0;
    }
    
    public String c() {
        try {
            if (this.try == null) {
                return "";
            }
            String s = "";
            this.k = 0;
            for (final t t : this.try) {
                if (t != null && t.aU == 3 && !t.aP) {
                    s = "Messaging";
                    this.k = 1;
                    break;
                }
            }
            for (final t t2 : this.try) {
                if (t2 != null && t2.aU == 4) {
                    s = "Connecting...";
                    this.k = 1;
                    break;
                }
            }
            for (final t t3 : this.try) {
                if (t3 != null && t3.aU == 4 && t3.Q == 0 && this.f.do() - t3.o < 9000L) {
                    s = "Registering...";
                    this.k = 1;
                    break;
                }
            }
            for (final t t4 : this.try) {
                if (t4 != null && t4.aU == 4 && (t4.Q == 2 || (t4.Q == 0 && this.f.do() - t4.o > 9000L))) {
                    s = "Register Failed";
                    this.k = 3;
                    break;
                }
            }
            for (final t t5 : this.try) {
                if (t5 != null && t5.aU == 4 && t5.Q == 1) {
                    s = "Registered.";
                    this.k = 2;
                    break;
                }
            }
            for (final t t6 : this.try) {
                if (t6 != null && (t6.aU == 15 || t6.aU == 16) && this.f.do() - t6.aF < 7000L) {
                    s = "Call Finished";
                    this.k = 16;
                    break;
                }
            }
            for (final t t7 : this.try) {
                if (t7 != null && (t7.aU == 7 || t7.aU == 8)) {
                    s = "Call Initiated";
                    this.k = 4;
                    break;
                }
            }
            for (final t t8 : this.try) {
                if (t8 != null && t8.aU == 9) {
                    s = "Calling...";
                    this.k = 4;
                    break;
                }
            }
            for (final t t9 : this.try) {
                if (t9 != null && t9.aU == 10) {
                    if (t9.B == 1) {
                        s = "InProgress...";
                    }
                    else {
                        s = "Incoming...";
                    }
                    this.k = 5;
                    break;
                }
            }
            for (final t t10 : this.try) {
                if (t10 != null && t10.aU == 11) {
                    if (t10.B == 1) {
                        s = "Ringing...";
                    }
                    else {
                        s = "Incoming...";
                    }
                    this.k = 6;
                    break;
                }
            }
            for (final t t11 : this.try) {
                if (t11 != null && (t11.aU == 12 || t11.aU == 13 || t11.aU == 14) && t11.d()) {
                    s = this.f.a("Muted") + " (" + this.f.a(this.f.do() - t11.bh) + ")";
                    this.k = 7;
                    break;
                }
            }
            for (final t t12 : this.try) {
                if (t12 != null && (t12.aU == 12 || t12.aU == 13 || t12.aU == 14) && t12.do()) {
                    s = this.f.a("Hold") + " (" + this.f.a(this.f.do() - t12.bh) + ")";
                    this.k = 7;
                    break;
                }
            }
            for (final t t13 : this.try) {
                if (t13 != null && t13.aU == 12 && !t13.do() && !t13.d()) {
                    s = this.f.a("In Call") + " (" + this.f.a(this.f.do() - t13.bh) + ")";
                    this.k = 7;
                    break;
                }
            }
            for (final t t14 : this.try) {
                if (t14 != null && (t14.aU == 13 || t14.aU == 14) && !t14.do() && !t14.d()) {
                    s = this.f.a("Speaking") + " (" + this.f.a(this.f.do() - t14.bh) + ")";
                    this.k = 7;
                    break;
                }
            }
            return s;
        }
        catch (Exception ex) {
            this.a(2, "mainGetBestStatusText", ex);
            return "";
        }
    }
    
    public String long() {
        return this.c();
    }
    
    public t if(int n, final boolean b) {
        try {
            if (this.f.df < 2) {
                n = 1;
            }
            if (this.try == null) {
                return null;
            }
            for (final t t : this.try) {
                if (t != null && t.a8 == n && t.aU >= 12 && t.aU < 16) {
                    return t;
                }
            }
            for (final t t2 : this.try) {
                if (t2 != null && t2.a8 == n && t2.aU >= 7 && t2.aU < 16) {
                    return t2;
                }
            }
            for (final t t3 : this.try) {
                if (t3 != null && t3.a8 == n) {
                    return t3;
                }
            }
            if (!b) {
                for (final t t4 : this.try) {
                    if (t4 != null && t4.aU >= 7 && t4.aU < 16) {
                        return t4;
                    }
                }
                for (final t t5 : this.try) {
                    if (t5 != null) {
                        return t5;
                    }
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "mainGetLineEp", ex);
        }
        return null;
    }
    
    public t do(int n, final boolean b) {
        if (this.f.df < 2) {
            n = 1;
        }
        if (n < 1) {
            n = 1;
        }
        t t = null;
        try {
            if (this.try == null) {
                return null;
            }
            for (final t t2 : this.try) {
                if (t2 != null && t2.a8 == n && t2.aU == 3) {
                    t = t2;
                    break;
                }
            }
            for (final t t3 : this.try) {
                if (t3 != null && t3.a8 == n && t3.aU == 4) {
                    t = t3;
                    break;
                }
            }
            for (final t t4 : this.try) {
                if (t4 != null && t4.a8 == n) {
                    t = t4;
                    break;
                }
            }
            for (final t t5 : this.try) {
                if (t5 != null && t5.a8 == n && t5.aU == 4 && t5.Q == 0 && this.f.do() - t5.o < 9000L) {
                    t = t5;
                    break;
                }
            }
            for (final t t6 : this.try) {
                if (t6 != null && t6.a8 == n && t6.aU == 4 && (t6.Q == 2 || (t6.Q == 0 && this.f.do() - t6.o > 9000L))) {
                    t = t6;
                    break;
                }
            }
            for (final t t7 : this.try) {
                if (t7 != null && t7.a8 == n && t7.aU == 4 && t7.Q == 1) {
                    t = t7;
                    break;
                }
            }
            for (final t t8 : this.try) {
                if (t8 != null && t8.a8 == n && (t8.aU == 15 || t8.aU == 16) && this.f.do() - t8.aF < 7000L) {
                    t = t8;
                    break;
                }
            }
            for (final t t9 : this.try) {
                if (t9 != null && t9.a8 == n && (t9.aU == 7 || t9.aU == 8)) {
                    t = t9;
                    break;
                }
            }
            for (final t t10 : this.try) {
                if (t10 != null && t10.a8 == n && t10.aU == 9) {
                    t = t10;
                    break;
                }
            }
            for (final t t11 : this.try) {
                if (t11 != null && t11.a8 == n && t11.aU == 10) {
                    t = t11;
                    break;
                }
            }
            for (final t t12 : this.try) {
                if (t12 != null && t12.a8 == n && t12.aU == 11) {
                    t = t12;
                    break;
                }
            }
            for (final t t13 : this.try) {
                if (t13 != null && t13.a8 == n && t13.aU == 12) {
                    t = t13;
                    break;
                }
            }
            for (final t t14 : this.try) {
                if (t14 != null && t14.a8 == n && (t14.aU == 13 || t14.aU == 14)) {
                    t = t14;
                    break;
                }
            }
            if (!b && t == null) {
                for (final t t15 : this.try) {
                    if (t15 != null) {
                        t = t15;
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "mainGetLineEpEx", ex);
        }
        return t;
    }
    
    public int byte(int n) {
        if (this.f.df < 2) {
            n = 1;
        }
        int n2 = 0;
        try {
            if (this.try == null) {
                return n2;
            }
            final Iterator<t> iterator = this.try.iterator();
            while (iterator.hasNext()) {
                if (iterator.next() != null) {
                    n2 = 0;
                    break;
                }
            }
            for (final t t : this.try) {
                if (t != null && t.a8 == n) {
                    n2 = 0;
                    break;
                }
            }
            for (final t t2 : this.try) {
                if (t2 != null && t2.a8 == n && t2.aU == 4 && t2.Q == 0 && this.f.do() - t2.o < 9000L) {
                    n2 = 1;
                    break;
                }
            }
            for (final t t3 : this.try) {
                if (t3 != null && t3.a8 == n && t3.aU == 4 && (t3.Q == 2 || (t3.Q == 0 && this.f.do() - t3.o > 9000L))) {
                    n2 = 3;
                    break;
                }
            }
            for (final t t4 : this.try) {
                if (t4 != null && t4.a8 == n && t4.aU == 4 && t4.Q == 1) {
                    n2 = 2;
                    break;
                }
            }
            for (final t t5 : this.try) {
                if (t5 != null && t5.a8 == n && (t5.aU == 15 || t5.aU == 16) && this.f.do() - t5.aF < 7000L) {
                    n2 = 16;
                    break;
                }
            }
            for (final t t6 : this.try) {
                if (t6 != null && t6.a8 == n && (t6.aU == 7 || t6.aU == 8)) {
                    n2 = 4;
                    break;
                }
            }
            for (final t t7 : this.try) {
                if (t7 != null && t7.a8 == n && t7.aU == 9) {
                    n2 = 4;
                    break;
                }
            }
            for (final t t8 : this.try) {
                if (t8 != null && t8.a8 == n && t8.aU == 10) {
                    n2 = 5;
                    break;
                }
            }
            for (final t t9 : this.try) {
                if (t9 != null && t9.a8 == n && t9.aU == 11) {
                    n2 = 6;
                    break;
                }
            }
            for (final t t10 : this.try) {
                if (t10 != null && t10.a8 == n && t10.aU == 12) {
                    n2 = 7;
                    break;
                }
            }
            for (final t t11 : this.try) {
                if (t11 != null && t11.a8 == n && (t11.aU == 13 || t11.aU == 14)) {
                    n2 = 7;
                    break;
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "mainGetBestStatusText", ex);
        }
        return n2;
    }
    
    public void a(final int n, final int n2) {
        try {
            if (this.try == null) {
                return;
            }
            if (n == -2) {
                final Iterator<t> iterator = this.try.iterator();
                while (iterator.hasNext()) {
                    if (iterator.next() != null) {
                        if (this.case) {
                            this.void.a(true, n2);
                        }
                        else {
                            this.void.a(false, n2);
                        }
                    }
                }
                return;
            }
            if (this.void == null) {
                if (n > 0) {
                    this.void = this.if(n, false);
                }
                else {
                    this.void = this.a(n, false);
                }
            }
            if (this.void != null) {
                if (this.void.d()) {
                    this.void.a(false, n2);
                }
                else {
                    this.void.a(true, n2);
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "mainMute", ex);
        }
    }
    
    public void char(final int n) {
        try {
            if (this.try == null) {
                return;
            }
            if (n == -2) {
                for (final t t : this.try) {
                    if (t != null) {
                        if (!t.do()) {
                            this.void.int(true);
                        }
                        else {
                            this.void.int(false);
                        }
                    }
                }
                return;
            }
            if (this.void == null) {
                if (n > 0) {
                    this.void = this.if(n, false);
                }
                else {
                    this.void = this.a(n, false);
                }
            }
            if (this.void != null) {
                if (this.void.do()) {
                    this.void.int(false);
                }
                else {
                    this.void.int(true);
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "mainHold", ex);
        }
    }
    
    public void for(final int n) {
        try {
            if (this.try == null) {
                return;
            }
            if (n == -2) {
                for (final t t : this.try) {
                    if (t != null) {
                        if (this.case) {
                            t.int(true);
                        }
                        else {
                            t.int(false);
                        }
                    }
                }
                return;
            }
            if (this.void == null) {
                if (n > 0) {
                    this.void = this.if(n, false);
                }
                else {
                    this.void = this.a(n, false);
                }
            }
            if (this.void != null) {
                if (!this.case) {
                    this.void.int(false);
                }
                else {
                    this.void.int(true);
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "mainHoldEx", ex);
        }
    }
    
    public void int(int activeline) {
        try {
            if (this.try == null) {
                return;
            }
            if (this.try == null) {
                return;
            }
            if (this.f.df < 2) {
                activeline = 1;
            }
            t if1 = null;
            int n = 0;
            if (activeline == -2) {
                final Iterator<t> iterator = this.try.iterator();
                while (iterator.hasNext()) {
                    if1 = iterator.next();
                    if (if1 != null && if1.aU >= 7 && if1.aU < 13 && if1.B == 0) {
                        if1.o();
                        this.for = if1;
                        n = 1;
                    }
                }
            }
            if (activeline > 0) {
                if1 = this.if(activeline, false);
                if (if1 != null && if1.aU >= 7 && if1.aU < 13 && if1.B == 0) {
                    if1.o();
                    n = 1;
                    this.for = if1;
                }
            }
            if (n == 0) {
                final Iterator<t> iterator2 = this.try.iterator();
                while (iterator2.hasNext()) {
                    if1 = iterator2.next();
                    if (if1 != null && if1.aU >= 7 && if1.aU < 13 && if1.B == 0) {
                        if1.o();
                        n = 1;
                        this.for = if1;
                        break;
                    }
                }
            }
            if (n == 0) {
                this.a(4, "WARNIN,no call to accept2");
            }
            else {
                if (activeline > 0) {
                    this.byte.activeline = activeline;
                }
                this.f.cu = false;
                if (this.f.ev == 1 || this.f.ev > 2) {
                    for (final t t : this.try) {
                        if (t != null && t != if1 && t.aU >= 7 && t.aU <= 16) {
                            t.a(true, 0);
                        }
                    }
                }
                if (this.f.eN == 1 || this.f.eN > 2) {
                    for (final t t2 : this.try) {
                        if (t2 != null && t2 != if1 && t2.aU >= 13 && t2.aU <= 16) {
                            t2.int(true);
                        }
                    }
                }
            }
            this.f.dI = true;
            this.d = true;
        }
        catch (Exception ex) {
            this.a(1, "mainAccept", ex);
        }
    }
    
    public void do(final t for1) {
        try {
            this.a(3, "EVENT,accept call");
            if (for1 == null) {
                return;
            }
            if (for1.a8 < 1) {
                for1.a8 = this.a(-1);
            }
            if (for1.a8 > 0) {
                this.byte.activeline = for1.a8;
            }
            if (for1.a8 < 1) {
                for1.if("no line");
            }
            else {
                for1.o();
            }
            this.f.dI = true;
            this.for = for1;
            this.d = true;
        }
        catch (Exception ex) {
            this.a(1, "mainAcceptEp", ex);
        }
    }
    
    public void a(t for1, int n) {
        try {
            this.a(3, "EVENT,reject call");
            if (this.f.df < 2) {
                n = 1;
            }
            if (this.try == null) {
                return;
            }
            if (n == -2) {
                t for2 = null;
                final Iterator<t> iterator = this.try.iterator();
                while (iterator.hasNext()) {
                    for2 = iterator.next();
                    if (for2 != null && for2.aU >= 7) {
                        for2.if("reject");
                    }
                }
                this.for = for2;
                this.d = true;
                return;
            }
            if (for1 == null) {
                if (n > 0) {
                    for1 = this.if(n, false);
                }
                else {
                    for1 = this.a(n, false);
                }
            }
            if (for1 == null) {
                this.a(3, "WARNING,no ep on reject");
                return;
            }
            if (for1.aU >= 7) {
                for1.if("reject2");
                this.for = for1;
            }
            else {
                this.a(3, "WARNING,no call on reject");
            }
            this.f.dI = true;
            this.d = true;
        }
        catch (Exception ex) {
            this.a(1, "mainRejectEp", ex);
        }
    }
    
    public boolean if(final String s) {
        try {
            this.a(3, "EVENT,send dtmf");
            if (s.length() < 1) {
                return false;
            }
            final t a = this.a(this.byte.activeline, true);
            if (a == null) {
                this.a(1, "EVENT,no active endpoing for DTMF");
                return false;
            }
            if (this.f.do() - this.f.q > 7000L || this.f.cy.length() > 15) {
                this.f.cy = "";
            }
            this.f.q = this.f.do();
            this.f.cy += s;
            this.a(1, "EVENT,send DTMF " + this.f.cy);
            this.a(6, a, -1, s, "");
            return true;
        }
        catch (Exception ex) {
            this.a(1, "mainSendDTMF", ex);
            return false;
        }
    }
    
    public boolean a(t a, final String s) {
        try {
            if (a == null) {
                a = this.a(this.byte.activeline, true);
            }
            if (a == null) {
                this.a(1, "EVENT,no active endpoing for DTMF");
                return false;
            }
            if (s.length() < 1) {
                return false;
            }
            this.a(4, "EVENT,sending DTMF " + s);
            return a.new(s);
        }
        catch (Exception ex) {
            this.a(1, "mainSendDTMFEx", ex);
            return false;
        }
    }
    
    public t a(final int n, final boolean b) {
        try {
            if (this.try == null) {
                return null;
            }
            if (n > 0) {
                for (final t t : this.try) {
                    if (t != null) {
                        if (t.a8 != n) {
                            continue;
                        }
                        if (t.aU >= 12 && t.aU < 15) {
                            return t;
                        }
                        continue;
                    }
                }
                if (!b) {
                    for (final t t2 : this.try) {
                        if (t2 != null) {
                            if (t2.a8 != n) {
                                continue;
                            }
                            if (t2.aU >= 7 && t2.aU < 15) {
                                return t2;
                            }
                            continue;
                        }
                    }
                }
            }
            for (final t t3 : this.try) {
                if (t3 == null) {
                    continue;
                }
                if (t3.aU >= 12 && t3.aU < 15) {
                    return t3;
                }
            }
            if (!b) {
                for (final t t4 : this.try) {
                    if (t4 == null) {
                        continue;
                    }
                    if (t4.aU >= 7 && t4.aU < 15) {
                        return t4;
                    }
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "GetEPWithCallExceptep", ex);
        }
        return null;
    }
    
    public t a(final int n, final boolean b, final t t) {
        try {
            if (this.try == null) {
                return null;
            }
            if (n > 0) {
                for (final t t2 : this.try) {
                    if (t2 != null && t2.a8 == n) {
                        if (t2 == t) {
                            continue;
                        }
                        if (t2.aU >= 12 && t2.aU < 15) {
                            return t2;
                        }
                        continue;
                    }
                }
                if (!b) {
                    for (final t t3 : this.try) {
                        if (t3 != null && t3.a8 == n) {
                            if (t3 == t) {
                                continue;
                            }
                            if (t3.aU >= 7 && t3.aU < 15) {
                                return t3;
                            }
                            continue;
                        }
                    }
                }
            }
            for (final t t4 : this.try) {
                if (t4 != null) {
                    if (t4 == t) {
                        continue;
                    }
                    if (t4.aU >= 12 && t4.aU < 15) {
                        return t4;
                    }
                    continue;
                }
            }
            if (!b) {
                for (final t t5 : this.try) {
                    if (t5 != null) {
                        if (t5 == t) {
                            continue;
                        }
                        if (t5.aU >= 7 && t5.aU < 15) {
                            return t5;
                        }
                        continue;
                    }
                }
            }
            t t6 = this.a(n, b);
            if (t6 == null) {
                t6 = this.a(-1, false);
            }
            if (t6 != null) {
                return t6;
            }
        }
        catch (Exception ex) {
            this.a(2, "GetEPWithCall", ex);
        }
        return null;
    }
    
    public boolean if(final boolean b) {
        try {
            if (!this.f.cR) {
                return false;
            }
            try {
                final i l = this.l;
                final i i = this.l;
                l.setPriority(1);
            }
            catch (Exception ex2) {}
            String av;
            int x;
            String a;
            if (b || (!this.f.fe && this.f.dt)) {
                this.f.dK = 3;
                final String serveraddr = this.byte.serveraddr;
                int serverport = this.byte.serverport;
                av = this.f.char(serveraddr);
                x = this.byte.proxyport;
                a = this.f.char(this.byte.proxyaddr);
                if (x < 1) {
                    x = serverport;
                }
                if (a.length() < 4) {
                    a = av;
                }
                if (this.new == null) {
                    this.new = new aq(this);
                    if (this.f.dT > 0 && this.f.dK == 3) {
                        serverport = (x = this.f.dT);
                    }
                    this.j = this.new.a(av, serverport, this.j);
                    this.new.start();
                }
            }
            else {
                this.f.dK = 4;
                final String serveraddr2 = this.byte.serveraddr;
                final int serverport2 = this.byte.serverport;
                av = this.f.char(serveraddr2);
                x = this.byte.proxyport;
                a = this.f.char(this.byte.proxyaddr);
                if (x < 1) {
                    x = serverport2;
                }
                if (a.length() < 4) {
                    a = av;
                }
                if (this.m == null) {
                    this.m = new w(this);
                    if (this.f.dn > 0 && this.f.dK == 4) {
                        x = this.f.dn;
                    }
                    this.j = this.m.a(a, x, this.j);
                    this.m.start();
                }
            }
            if (this.l != null) {
                final i j = this.l;
                final i k = this.l;
                j.setPriority(5);
            }
            if (this.try == null) {
                return false;
            }
            for (final t t : this.try) {
                if (t == null) {
                    continue;
                }
                if (t.aU >= 15) {
                    continue;
                }
                t.az = 0;
                t.av = av;
                t.A = a;
                t.X = x;
                if (this.f.dK == 0 && this.f.cR) {
                    t.aS = this.f.Q + 1;
                }
                else {
                    if (this.f.dK != 3) {
                        continue;
                    }
                    t.aS = this.f.Q * 3;
                }
            }
            return true;
        }
        catch (Exception ex) {
            this.a(2, "mainSwitchTransportProtocol", ex);
            return false;
        }
    }
    
    public boolean void() {
        try {
            if (this.try == null) {
                return false;
            }
            for (final t t : this.try) {
                if (t == null) {
                    continue;
                }
                if (t.aU >= 7 && t.aU < 15) {
                    return true;
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "mainHasCallInprogress", ex);
        }
        return false;
    }
    
    public boolean try() {
        try {
            if (this.try == null) {
                return false;
            }
            for (final t t : this.try) {
                if (t == null) {
                    continue;
                }
                if (t.aU >= 7 && t.aU <= 15) {
                    return true;
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "mainHasUnfinishedCall", ex);
        }
        return false;
    }
    
    public boolean case() {
        try {
            if (this.try == null) {
                return false;
            }
            for (final t t : this.try) {
                if (t == null) {
                    continue;
                }
                if (t.aU >= 12 && t.aU < 15) {
                    return true;
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "HasCallInSpeaking", ex);
        }
        return false;
    }
    
    public t a(final String s) {
        try {
            if (this.try == null) {
                return null;
            }
            for (final t t : this.try) {
                if (t == null) {
                    continue;
                }
                if (t.bc.equalsIgnoreCase(s)) {
                    return t;
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "mainFindEp", ex);
        }
        return null;
    }
    
    public void else() {
        this.case(5000);
    }
    
    public void case(int n) {
        n /= 50;
        for (int n2 = 0; n2 < n && this.c >= 0; ++n2) {
            this.f.do(50L);
        }
        this.f.do(1L);
        this.f.do(0L);
        this.f.do(101L);
        this.f.do(1L);
        this.f.do(0L);
    }
    
    public void a(final int c, final t void1, final int b, final String long1, final String goto1) {
        try {
            this.void = void1;
            this.long = long1;
            this.goto = goto1;
            this.b = b;
            this.c = c;
        }
        catch (Exception ex) {
            this.a(2, "mainAsyncAction", ex);
        }
    }
    
    public void run() {
        try {
            this.a(3, "EVENT,sip stack run on " + this.f.d());
            this.f();
            long do1 = 0L;
            long n = 0L;
            final aw f = this.f;
            ++f.al;
            int n2 = 0;
            while (!this.byte.isterminated) {
                boolean b = true;
                if (n2 != 0) {
                    break;
                }
                if (this.f.o && !this.byte.isrunning) {
                    n2 = 1;
                }
                else {
                    if (this.f.cR && this.f.dM != 0L && this.f.dM != 1L && this.f.do() - this.f.dM > 18000L && this.f.dt && (this.f.dK == 4 || this.f.dK == 5) && this.f.do() - this.f.dU < 700000L) {
                        this.a(3, "WARNING,switch transport to http because no tcp read");
                        this.if(true);
                    }
                    if (this.f.do() - do1 > 70L * this.f.eB) {
                        this.f.do();
                        this.do(false);
                        do1 = this.f.do();
                        if (this.f.do() - n <= 310L * this.f.eB) {
                            continue;
                        }
                        n = this.f.do();
                        this.byte.asyncrec_statuschanged = true;
                    }
                    else {
                        if (this.c >= 0) {
                            if (this.b < 1 && this.byte != null && this.byte.activeline > 0 && this.c != 17) {
                                this.b = this.byte.activeline;
                            }
                            try {
                                final int c = this.c;
                                this.c = -1;
                                if (c == 0) {
                                    this.do(this.b);
                                }
                                else if (c == 1) {
                                    this.a(this.void, this.long, this.goto, this.b);
                                }
                                else if (c == 2) {
                                    this.int(this.b);
                                }
                                else if (c == 3) {
                                    this.a(this.void, this.b);
                                }
                                else if (c == 4) {
                                    this.try(this.b);
                                }
                                else if (c == 10) {
                                    this.int(-2);
                                }
                                else if (c == 11) {
                                    this.a((t)null, -2);
                                }
                                else if (c == 12) {
                                    this.try(-2);
                                }
                                else if (c == 13) {
                                    this.a(-2, this.f.for(this.long, 0));
                                }
                                else if (c == 5) {
                                    this.new();
                                }
                                else if (c == 16) {
                                    this.a(true);
                                }
                                else if (c == 18) {
                                    this.byte.asyncclearcredentials = true;
                                }
                                else if (c == 14) {
                                    this.for();
                                }
                                else if (c == 19) {
                                    this.d();
                                }
                                else if (c == 15) {
                                    this.byte();
                                }
                                else if (c == 6) {
                                    this.a(this.void, this.long);
                                }
                                else if (c == 9) {
                                    this.char(this.b);
                                }
                                else if (c == 17) {
                                    this.for(this.b);
                                }
                                else if (c == 7) {
                                    this.a(this.b, this.f.for(this.long, 0));
                                }
                                else {
                                    this.a(4, "EVENT,maint unknown async oop request");
                                }
                                b = false;
                            }
                            catch (Exception ex) {
                                this.a(2, "mainAsyncCheck", ex);
                            }
                            this.void = null;
                            this.b = -1;
                            this.long = "";
                            this.goto = "";
                        }
                        if (this.d) {
                            this.d = false;
                            n = this.f.do();
                            this.byte.asyncrec_statuschanged = true;
                            b = false;
                            if (this.for != null) {
                                int a8 = this.for.a8;
                                if (a8 < 1) {
                                    a8 = 1;
                                }
                                String s = "0";
                                if (this.for.B == 0) {
                                    s = "2";
                                }
                                else if (this.for.B == 1) {
                                    s = "1";
                                }
                                this.f.g("STATUS," + this.f.c(a8) + "," + this.f.b(this.for.aU) + "," + this.for.r() + "," + this.for.h() + "," + s);
                            }
                        }
                        if (b) {
                            this.f.do(50L);
                        }
                        else {
                            this.f.do(1L);
                        }
                    }
                }
            }
            this.int();
            this.a(3, "EVENT,sip stack run terminated");
        }
        catch (Exception ex2) {
            this.a(1, "maimrun", ex2);
        }
    }
    
    public void int() {
        try {
            if (this.f.dK == 0 && !this.f.aQ && this.f.cR && this.f.bu) {
                this.f.aQ = true;
                --this.f.ed;
                if (this.f.ed < 0) {
                    this.f.ed = 0;
                }
                this.f.a(5, "EVENT, subsswitchtotcp decreased to " + this.f.c(this.f.ed));
            }
        }
        catch (Exception ex) {
            this.a(1, "maimrun", ex);
        }
    }
    
    public void do(final boolean b) {
        try {
            if (this.try == null) {
                return;
            }
            final Iterator<t> iterator = this.try.iterator();
            if (iterator == null) {
                return;
            }
            while (iterator.hasNext()) {
                final t t = iterator.next();
                if (t == null) {
                    continue;
                }
                if (t.aU == 17) {
                    t.j();
                    iterator.remove();
                }
                else {
                    t.for(b);
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "mainTimer", ex);
        }
    }
    
    public boolean a(final String s, final int n, final byte[] array, final int n2, final int n3) {
        int n4 = 0;
        try {
            n4 = 1;
            if (n2 < 25) {
                n4 = 2;
                if (n2 < 5 && n2 > 2 && array != null && array[0] == 32 && array[1] == 13 && array[2] == 10) {
                    n4 = 3;
                    this.a(s, n, "  \r\n", null);
                }
                else {
                    n4 = 4;
                }
                return true;
            }
            n4 = 5;
            if (this.a == null) {
                return false;
            }
            if (this.i) {
                return false;
            }
            this.a.if();
            n4 = 6;
            final String s2 = new String(array, 0, n2);
            n4 = 7;
            this.a(3, "REC," + s + ":" + this.f.c(n) + " on port " + this.f.c(n3) + "\r\n" + s2);
            this.a.a(s2, s, n);
            n4 = 8;
            t a = this.a(this.a.n);
            n4 = 9;
            if (a != null) {
                this.a(5, "EVENT,old ep found for message");
            }
            else if (a == null && this.a.if == 1) {
                if (this.a.j.length() > 0) {
                    final t a2 = this.a(this.a.j);
                    this.a.j = "";
                    if (a2 != null && a2.aU > 7 && a2.aU < 15) {
                        this.a(1, "EVENT,session replaced");
                        a2.bc = this.a.n;
                        a2.e = a2.U.m;
                        a2.aJ = 0;
                        a2.aM = this.f.do();
                        a = a2;
                    }
                }
                if (a == null) {
                    this.a(3, "EVENT,creating new ep for INVITE");
                    a = new t(this.byte, this);
                    a.bc = this.a.n;
                    a.B = 0;
                    a.if(7);
                    if (a.a8 < 1) {
                        a.a8 = this.a(-1);
                    }
                    this.try.add(a);
                    this.d = true;
                }
            }
            else if (a == null && this.a.if == 20) {
                this.a(3, "EVENT,creating new ep for MESSAGEX");
                a = new t(this.byte, this);
                a.bc = this.a.n;
                a.B = 0;
                a.if(6);
                this.try.add(a);
                this.d = true;
            }
            else if (a == null && this.a.if == 11) {
                this.a(3, "EVENT,creating new ep for SUBSCRIBE");
                a = new t(this.byte, this);
                a.bc = this.a.n;
                a.B = 0;
                a.if(5);
                this.try.add(a);
                this.d = true;
            }
            else if (a == null && this.a.if == 2) {
                this.a(3, "EVENT,creating new ep for OPTIONS");
                a = new t(this.byte, this);
                a.bc = this.a.n;
                a.B = 0;
                a.if(3);
                this.try.add(a);
            }
            else if (a == null) {
                if (s2.length() > 50) {
                    this.a(3, "WARNING,cannot find ep for this recmessage " + this.f.c(this.try.size()) + " " + this.a.n);
                }
                else {
                    this.a(4, "EVENT,no ep processing needed for this message");
                }
                return false;
            }
            n4 = 10;
            if (a != null) {
                n4 = 11;
                this.a.a(a.U);
                n4 = 12;
                a.if(s, n);
                n4 = 13;
            }
            return true;
        }
        catch (Exception ex) {
            this.a(2, "mainReceive (" + this.f.c(n4) + ")", ex);
            return false;
        }
    }
}
