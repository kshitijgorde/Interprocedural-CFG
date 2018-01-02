import java.net.DatagramPacket;
import java.net.InetAddress;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Prot3Pinger extends Thread
{
    YawApplet main;
    boolean more;
    DataOutputStream out;
    DataInputStream in;
    int fps;
    int quality;
    int packetSize;
    int numNATOpenPackSent;
    
    public Prot3Pinger(final YawApplet main, final DataOutputStream out, final DataInputStream in) {
        this.more = true;
        this.numNATOpenPackSent = 1;
        this.main = main;
        this.out = out;
        this.in = in;
    }
    
    public void run() {
        this.sendNATOpenPack();
        while (this.more) {
            this.fps = this.main.getFps();
            this.quality = this.main.getQuality();
            this.packetSize = this.main.getPktSize();
            try {
                this.out.writeBytes("MPING fps" + this.fps + " ql" + this.quality + " pks" + this.packetSize + "\n");
                this.out.flush();
                String line = null;
                while (line == null) {
                    line = this.in.readLine();
                    if (line == null) {
                        try {
                            Thread.sleep(50L);
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                if (line.startsWith("PONG")) {
                    final int index = line.indexOf(32);
                    if (index != -1 && index + 1 <= line.length()) {
                        if (line.substring(index + 1).equals("RESEND")) {
                            if (this.numNATOpenPackSent >= 10) {
                                this.more = false;
                                System.out.println("Have sent 10 NAT-opening-packets, but no arrived...");
                                continue;
                            }
                            this.sendNATOpenPack();
                            ++this.numNATOpenPackSent;
                        }
                        else if (line.substring(index + 1).equals("LIMIT")) {
                            System.out.println("GOT A LIMIT!!!");
                            final int int1 = this.in.readInt();
                            final byte[] array = new byte[int1];
                            for (int i = 0; i < int1; ++i) {
                                array[i] = this.in.readByte();
                            }
                            this.main.alive = false;
                            this.more = false;
                            this.main.img = Toolkit.getDefaultToolkit().createImage(array);
                            try {
                                final MediaTracker mediaTracker = new MediaTracker(this.main);
                                mediaTracker.addImage(this.main.img, 1);
                                mediaTracker.waitForID(1);
                            }
                            catch (Exception ex3) {}
                            this.main.actualFps = 0.0f;
                            this.main.repaint();
                            return;
                        }
                    }
                }
                Thread.sleep(2000L);
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
                this.more = false;
            }
        }
    }
    
    public void sendNATOpenPack() {
        try {
            System.out.println("Sending NAT opening packet to: " + this.main.host + ":" + this.main.port);
            InetAddress.getByName(this.main.host);
            final byte[] bytes = "n".getBytes();
            this.main.dsoc.send(new DatagramPacket(bytes, bytes.length, InetAddress.getByName(this.main.host), this.main.port));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void kill() {
        this.more = false;
        synchronized (this) {
            this.notifyAll();
        }
    }
}
