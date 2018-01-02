// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.DatagramSocket;

public class i extends Thread
{
    public int a;
    DatagramSocket long;
    InetAddress goto;
    t b;
    bc char;
    boolean for;
    boolean if;
    boolean byte;
    boolean int;
    aw case;
    int void;
    byte[] try;
    byte[] do;
    int new;
    int else;
    
    public i(final t b) {
        this.a = 0;
        this.long = null;
        this.goto = null;
        this.b = null;
        this.char = null;
        this.for = false;
        this.if = false;
        this.byte = false;
        this.int = false;
        this.case = null;
        this.void = 0;
        this.try = null;
        this.do = null;
        this.new = 0;
        this.else = 1;
        this.a = 0;
        this.b = b;
        this.for = false;
        this.if = false;
        this.byte = true;
        this.case = b.d;
    }
    
    public i(final bc char1) {
        this.a = 0;
        this.long = null;
        this.goto = null;
        this.b = null;
        this.char = null;
        this.for = false;
        this.if = false;
        this.byte = false;
        this.int = false;
        this.case = null;
        this.void = 0;
        this.try = null;
        this.do = null;
        this.new = 0;
        this.else = 1;
        this.a = 0;
        this.char = char1;
        this.for = false;
        this.if = false;
        this.byte = false;
        this.case = char1.f;
    }
    
    public void if() {
        try {
            this.if = true;
            this.a();
        }
        catch (Exception ex) {}
    }
    
    public void a() {
        try {
            if (this.for) {
                this.long.close();
            }
        }
        catch (Exception ex) {}
    }
    
    public int a(int void1) {
        try {
            if (void1 < 1) {
                void1 = 19855;
            }
            this.void = void1;
            int n = 0;
            int i = void1;
            while (i < void1 + 100) {
                try {
                    ++n;
                    if (!this.if) {
                        this.case.a(3, "EVENT,udp bind to port " + this.case.c(i));
                        this.long = new DatagramSocket(i);
                        this.a = i;
                        this.for = true;
                        try {
                            if (this.case.cZ >= 0) {
                                this.long.setTrafficClass(this.case.cZ);
                            }
                        }
                        catch (Exception ex) {
                            this.case.if(5, "udp set parameters", ex);
                        }
                        return this.a;
                    }
                }
                catch (Exception ex2) {
                    this.case.a(3, "udp bind", ex2);
                    try {
                        if (this.if) {
                            break;
                        }
                        if (n > 20) {
                            Thread.sleep(0L, 1);
                        }
                        else if (n == 40) {
                            Thread.sleep(200L);
                        }
                    }
                    catch (Exception ex3) {
                        this.case.if(3, "udp init sleep", ex3);
                    }
                    i += 2;
                    continue;
                }
                break;
            }
            this.case.a(0, "ERROR,udp cannot bind on " + this.case.c(void1));
        }
        catch (Exception ex4) {}
        return 0;
    }
    
    public int a(final int n, final boolean b) {
        try {
            if (n < 1) {
                return n;
            }
            this.void = n;
            int n2 = 0;
            int i = 0;
            while (i < 2) {
                try {
                    if (n2 <= 0 || !b) {
                        ++n2;
                        if (!this.if) {
                            this.case.a(3, "EVENT,udp strict bind to port " + this.case.c(n) + " (" + this.case.c(i) + ")");
                            this.long = new DatagramSocket(n);
                            this.a = n;
                            this.for = true;
                            return this.a;
                        }
                    }
                }
                catch (Exception ex) {
                    this.case.a(3, "udp strict bind", ex);
                    ++i;
                    continue;
                }
                break;
            }
            this.case.a(0, "ERROR,udp cannot strict bind on " + this.case.c(n));
        }
        catch (Exception ex2) {}
        return 0;
    }
    
    public boolean a(final String s, final int n, final String s2, final t t) {
        try {
            if (this.long == null || !this.for) {
                this.a(this.a);
            }
            final byte[] bytes = s2.getBytes();
            return this.a(s, n, bytes, bytes.length, t);
        }
        catch (Exception ex) {
            this.case.a(2, "udp send buff99 " + s + ":" + this.case.c(n), ex);
            return false;
        }
    }
    
    public boolean a(final String s, final int n, final String s2) {
        try {
            if (this.long == null || !this.for) {
                this.a(this.a);
            }
            final byte[] bytes = s2.getBytes();
            return this.a(s, n, bytes, bytes.length, null);
        }
        catch (Exception ex) {
            this.case.a(2, "udp send buff1 " + s + ":" + this.case.c(n), ex);
            return false;
        }
    }
    
    public boolean a(final String s, final int n, final byte[] array, int if1, final t b) {
        try {
            if (this.long == null || !this.for) {
                this.a(this.a);
            }
            if (!this.for) {
                return false;
            }
            if (b != null && this.b != b) {
                this.b = b;
            }
            if (s == null || n < 2 || n > 70000 || s.length() < 2) {
                if (s == null) {
                    this.case.a(2, "ERROR,null udp send address");
                }
                else {
                    this.case.a(2, "ERROR,invalid udp send address " + s + ":" + this.case.c(n));
                }
                return false;
            }
            if (this.case.t && if1 > 4 && !this.case.if(array, if1) && (array[0] != 68 || array[1] != 97 || array[2] != 54 || array[3] != 65) && (array[0] != 46 || array[2] != (array[1] ^ 0x54) || array[3] != (array[1] ^ 0x79) || array[4] != (array[1] ^ 0x37) || array[5] != (array[1] ^ 0x55))) {
                if (this.do == null || this.new < if1 + 32) {
                    this.do = new byte[if1 + 32];
                    this.new = if1 + 20;
                }
                if1 = this.case.if(this.do, 0, array, if1);
                this.long.send(new DatagramPacket(this.do, if1, InetAddress.getByName(s), n));
            }
            else {
                this.long.send(new DatagramPacket(array, if1, InetAddress.getByName(s), n));
            }
            return true;
        }
        catch (Exception ex) {
            this.case.a(2, "udp send buff " + s + ":" + this.case.c(n), ex);
            if (ex.getMessage().indexOf("cess deni") > 0) {
                this.case.r = true;
            }
            return false;
        }
    }
    
    public void run() {
        if (this.if) {
            return;
        }
        final byte[] array = new byte[3600];
        int n = 0;
        while (!this.if) {
            try {
                if (!this.for) {
                    this.case.do(1000L);
                    this.a(this.void);
                    continue;
                }
                final DatagramPacket datagramPacket = new DatagramPacket(array, array.length);
                this.long.receive(datagramPacket);
                if (datagramPacket == null || datagramPacket.getLength() < 1) {
                    Thread.sleep(0L, 1);
                    continue;
                }
                if (datagramPacket.getLength() > 1550) {
                    continue;
                }
                if (n == 0 && this.case.c8) {
                    n = 1;
                    this.setPriority(10);
                }
                final byte[] data = datagramPacket.getData();
                final int a = this.case.a(data, 0, data, datagramPacket.getLength());
                if (a < 1) {
                    continue;
                }
                if (this.byte) {
                    this.b.a(datagramPacket.getAddress().getHostAddress(), datagramPacket.getPort(), data, a);
                }
                else {
                    this.case.dm = true;
                    this.char.a(datagramPacket.getAddress().getHostAddress(), datagramPacket.getPort(), data, a, this.a);
                }
                continue;
            }
            catch (Exception ex) {
                this.case.if(3, "udp rec ", ex);
                if (!this.if) {
                    try {
                        Thread.sleep(0L, 1);
                    }
                    catch (Exception ex2) {}
                    continue;
                }
                continue;
            }
            break;
        }
        this.if();
    }
}
