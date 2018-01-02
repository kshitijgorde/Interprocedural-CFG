// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class a3 extends Thread
{
    InetAddress a;
    aw if;
    
    public a3(final aw if1) {
        this.a = null;
        this.if = null;
        this.if = if1;
    }
    
    public void run() {
        try {
            DatagramSocket datagramSocket = null;
            int ai;
            if (this.if.b9) {
                ai = this.if.for(10000, 20000);
            }
            else if (this.if.aM > 0) {
                ai = this.if.aM;
            }
            else if (this.if.eE > 0) {
                ai = this.if.eE;
            }
            else if (this.if.ai > 0) {
                ai = this.if.ai;
            }
            else if (this.if.Y > 0) {
                ai = this.if.Y;
            }
            else {
                ai = this.if.for(10000, 20000);
            }
            boolean b = false;
            int i = ai;
            while (i < ai + 50) {
                try {
                    datagramSocket = new DatagramSocket(i);
                    this.if.a(3, "EVENT,udp nat bind to port " + this.if.c(i));
                    ai = i;
                    b = true;
                }
                catch (Exception ex) {
                    this.if.if(3, "udp nat bind", ex);
                    i += 2;
                    continue;
                }
                break;
            }
            if (!b) {
                return;
            }
            final byte[] bytes = "GG".getBytes();
            final byte[] array = new byte[1600];
            final long do1 = this.if.do();
            int n = 0;
            datagramSocket.setSoTimeout(6000);
            for (int j = 0; j < 3; ++j) {
                if (this.if.fk.length() > 1) {
                    datagramSocket.send(new DatagramPacket(bytes, bytes.length, InetAddress.getByName(this.if.fk), 44446));
                }
                else {
                    datagramSocket.send(new DatagramPacket(bytes, bytes.length, InetAddress.getByName("109.200.0.194"), 44446));
                    datagramSocket.send(new DatagramPacket(bytes, bytes.length, InetAddress.getByName("81.95.156.98"), 44446));
                    datagramSocket.send(new DatagramPacket(bytes, bytes.length, InetAddress.getByName("174.123.79.226"), 44446));
                }
                DatagramPacket datagramPacket = null;
                try {
                    datagramPacket = new DatagramPacket(array, array.length);
                    datagramSocket.receive(datagramPacket);
                }
                catch (Exception ex3) {}
                if (this.if.cz) {
                    break;
                }
                if (this.if.do() - do1 > 15000L) {
                    break;
                }
                if (datagramPacket != null) {
                    if (datagramPacket.getLength() > 1550) {
                        break;
                    }
                    if (datagramPacket.getLength() < 1) {
                        if (this.if.do() - do1 > 5000L) {
                            break;
                        }
                        this.if.do(200L);
                    }
                    else {
                        final String s = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                        final int index = s.indexOf(" is ");
                        if (index > 0) {
                            final String substring = s.substring(index + 4);
                            final String substring2 = substring.substring(0, substring.indexOf(":"));
                            final String substring3 = substring.substring(substring.indexOf(":") + 1, substring.indexOf(";"));
                            final String char1 = this.if.char(substring2);
                            final int for1 = this.if.for(substring3, 0);
                            if (for1 > 0 && char1.length() > 3 && char1.charAt(0) != '0' && !char1.equals("127.0.0.1")) {
                                datagramSocket.close();
                                datagramSocket = null;
                                ++n;
                                if (this.if.I > 0 && (for1 != this.if.Y || !char1.equals(this.if.bk)) && (this.if.I > 1 || for1 == ai)) {
                                    if (this.if.bk.length() < 4) {
                                        this.if.bk = char1;
                                    }
                                    if (this.if.Y < 4) {
                                        this.if.Y = for1;
                                    }
                                    if (!this.if.b9) {
                                        this.if.ai = ai;
                                    }
                                    this.if.a(3, "EVENT,udp nat detected " + char1 + ":" + this.if.c(for1));
                                }
                            }
                            break;
                        }
                        break;
                    }
                }
            }
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
        catch (Exception ex2) {
            this.if.if(2, "udp nat ", ex2);
            if (ex2.getMessage().indexOf("cess deni") > 0) {
                this.if.r = true;
            }
        }
    }
}
