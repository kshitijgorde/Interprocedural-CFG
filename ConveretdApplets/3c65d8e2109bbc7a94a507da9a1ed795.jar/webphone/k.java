// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.DatagramSocket;

public class k extends Thread
{
    bc else;
    aw char;
    DatagramSocket long;
    boolean if;
    public int for;
    public int do;
    boolean a;
    int byte;
    int new;
    byte[] case;
    byte[] try;
    String int;
    int goto;
    
    public k(final bc else1, final String int1, final int goto1) {
        this.else = null;
        this.char = null;
        this.long = null;
        this.if = false;
        this.for = 0;
        this.do = 0;
        this.a = true;
        this.byte = 0;
        this.new = 0;
        this.case = null;
        this.try = null;
        this.int = "";
        this.goto = 0;
        this.else = else1;
        this.char = else1.f;
        this.int = int1;
        this.goto = goto1;
    }
    
    public void a() {
        try {
            this.if = true;
            this.setPriority(1);
        }
        catch (Exception ex) {
            this.char.if(4, "UDPTestDestroy", ex);
        }
    }
    
    public boolean if() {
        try {
            this.case = new byte[1024];
            this.try = new byte[1024];
            this.byte = this.char.a(this.case, (byte)0, true);
            this.new = this.char.a(this.try, (byte)0, true);
            (this.long = new DatagramSocket()).setSoTimeout(3000);
            this.long.setTrafficClass(10);
            this.setPriority(10);
            this.start();
            return true;
        }
        catch (Exception ex) {
            this.char.if(4, "UDPTestInit", ex);
            return false;
        }
    }
    
    public int for() {
        if (this.for < 1) {
            return 0;
        }
        if (this.do < 1) {
            return 100;
        }
        int n = this.do * 100 / this.for;
        if (n > 100) {
            n = 100;
        }
        else if (n < 0) {
            n = 0;
        }
        return 100 - n;
    }
    
    public int int() {
        if (this.for < 1) {
            return 100;
        }
        if (this.do < 1) {
            return 0;
        }
        int n = this.do * 100 / this.for;
        if (n > 100) {
            n = 100;
        }
        else if (n < 0) {
            n = 0;
        }
        return n;
    }
    
    public boolean do() {
        try {
            if (this.a || !this.char.cv) {
                this.long.send(new DatagramPacket(this.case, this.byte, InetAddress.getByName(this.int), this.goto));
            }
            else {
                this.long.send(new DatagramPacket(this.try, this.new, InetAddress.getByName(this.int), this.goto));
            }
            this.a = !this.a;
            ++this.for;
            return true;
        }
        catch (Exception ex) {
            this.char.if(4, "UDPTestSend", ex);
            return false;
        }
    }
    
    public void run() {
        try {
            final byte[] array = new byte[2000];
            while (!this.if) {
                try {
                    final DatagramPacket datagramPacket = new DatagramPacket(array, array.length);
                    this.long.receive(datagramPacket);
                    if (datagramPacket == null || datagramPacket.getLength() < 1) {
                        Thread.sleep(0L, 1);
                    }
                    else {
                        if (!this.char.a(datagramPacket.getData(), datagramPacket.getLength())) {
                            continue;
                        }
                        ++this.do;
                    }
                }
                catch (Exception ex) {
                    this.char.if(4, "udptest rec ", ex);
                    if (this.if) {
                        continue;
                    }
                    this.char.do(1L);
                }
            }
            this.char.a(3, "EVENT,udptest finished");
        }
        catch (Exception ex2) {
            this.char.if(4, "UDPTestExec", ex2);
        }
    }
}
