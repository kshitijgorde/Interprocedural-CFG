// 
// Decompiled by Procyon v0.5.30
// 

package socket;

import java.awt.Dimension;
import java.util.Vector;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.net.Socket;

public class TelnetIO implements StatusPeer
{
    private static int debug;
    private byte neg_state;
    private static final byte STATE_DATA = 0;
    private static final byte STATE_IAC = 1;
    private static final byte STATE_IACSB = 2;
    private static final byte STATE_IACWILL = 3;
    private static final byte STATE_IACDO = 4;
    private static final byte STATE_IACWONT = 5;
    private static final byte STATE_IACDONT = 6;
    private static final byte STATE_IACSBIAC = 7;
    private static final byte STATE_IACSBDATA = 8;
    private static final byte STATE_IACSBDATAIAC = 9;
    private byte current_sb;
    private static final byte IAC = -1;
    private static final byte EOR = -17;
    private static final byte WILL = -5;
    private static final byte WONT = -4;
    private static final byte DO = -3;
    private static final byte DONT = -2;
    private static final byte SB = -6;
    private static final byte SE = -16;
    private static final byte TELOPT_ECHO = 1;
    private static final byte TELOPT_EOR = 25;
    private static final byte TELOPT_NAWS = 31;
    private static final byte TELOPT_TTYPE = 24;
    private static final byte[] IACWILL;
    private static final byte[] IACWONT;
    private static final byte[] IACDO;
    private static final byte[] IACDONT;
    private static final byte[] IACSB;
    private static final byte[] IACSE;
    private static final byte TELQUAL_IS = 0;
    private static final byte TELQUAL_SEND = 1;
    private byte[] receivedDX;
    private byte[] receivedWX;
    private byte[] sentDX;
    private byte[] sentWX;
    private Socket socket;
    private BufferedInputStream is;
    private BufferedOutputStream os;
    private StatusPeer peer;
    
    static {
        TelnetIO.debug = 0;
        IACWILL = new byte[] { -1, -5 };
        IACWONT = new byte[] { -1, -4 };
        IACDO = new byte[] { -1, -3 };
        IACDONT = new byte[] { -1, -2 };
        IACSB = new byte[] { -1, -6 };
        IACSE = new byte[] { -1, -16 };
    }
    
    public TelnetIO() {
        this.neg_state = 0;
        this.peer = (StatusPeer)this;
    }
    
    public int available() throws IOException {
        return this.is.available();
    }
    
    public void connect(final String address) throws IOException {
        this.connect(address, 23);
    }
    
    public void connect(final String address, final int port) throws IOException {
        if (TelnetIO.debug > 0) {
            System.out.println("Telnet.connect(" + address + "," + port + ")");
        }
        this.socket = new Socket(address, port);
        this.is = new BufferedInputStream(this.socket.getInputStream());
        this.os = new BufferedOutputStream(this.socket.getOutputStream());
        this.neg_state = 0;
        this.receivedDX = new byte[256];
        this.sentDX = new byte[256];
        this.receivedWX = new byte[256];
        this.sentWX = new byte[256];
    }
    
    public void disconnect() throws IOException {
        if (TelnetIO.debug > 0) {
            System.out.println("TelnetIO.disconnect()");
        }
        if (this.socket != null) {
            this.socket.close();
        }
    }
    
    private void handle_sb(final byte type, final byte[] sbdata, final int sbcount) throws IOException {
        if (TelnetIO.debug > 1) {
            System.out.println("TelnetIO.handle_sb(" + type + ")");
        }
        switch (type) {
            case 24: {
                if (sbcount > 0 && sbdata[0] == 1) {
                    this.send(TelnetIO.IACSB);
                    this.send((byte)24);
                    this.send((byte)0);
                    final Vector vec = new Vector(2);
                    vec.addElement("TTYPE");
                    String ttype = (String)this.peer.notifyStatus(vec);
                    if (ttype == null) {
                        ttype = "dumb";
                    }
                    final byte[] bttype = new byte[ttype.length()];
                    ttype.getBytes(0, ttype.length(), bttype, 0);
                    this.send(bttype);
                    this.send(TelnetIO.IACSE);
                    break;
                }
                break;
            }
        }
    }
    
    private byte[] negotiate(byte[] buf, final int count) throws IOException {
        if (TelnetIO.debug > 1) {
            System.out.println("TelnetIO.negotiate(" + buf + "," + count + ")");
        }
        final byte[] nbuf = new byte[count];
        final byte[] sbbuf = new byte[count];
        final byte[] sendbuf = new byte[3];
        int sbcount = 0;
        int boffset = 0;
        int noffset = 0;
        Vector vec = new Vector(2);
        while (boffset < count) {
            byte b = buf[boffset++];
            if (b >= 128) {
                b -= 256;
            }
            switch (this.neg_state) {
                case 0: {
                    if (b == -1) {
                        this.neg_state = 1;
                        continue;
                    }
                    nbuf[noffset++] = b;
                    continue;
                }
                case 1: {
                    switch (b) {
                        case -1: {
                            if (TelnetIO.debug > 2) {
                                System.out.print("IAC ");
                            }
                            this.neg_state = 0;
                            nbuf[noffset++] = -1;
                            continue;
                        }
                        case -5: {
                            if (TelnetIO.debug > 2) {
                                System.out.print("WILL ");
                            }
                            this.neg_state = 3;
                            continue;
                        }
                        case -4: {
                            if (TelnetIO.debug > 2) {
                                System.out.print("WONT ");
                            }
                            this.neg_state = 5;
                            continue;
                        }
                        case -2: {
                            if (TelnetIO.debug > 2) {
                                System.out.print("DONT ");
                            }
                            this.neg_state = 6;
                            continue;
                        }
                        case -3: {
                            if (TelnetIO.debug > 2) {
                                System.out.print("DO ");
                            }
                            this.neg_state = 4;
                            continue;
                        }
                        case -17: {
                            if (TelnetIO.debug > 2) {
                                System.out.print("EOR ");
                            }
                            this.neg_state = 0;
                            continue;
                        }
                        case -6: {
                            if (TelnetIO.debug > 2) {
                                System.out.print("SB ");
                            }
                            this.neg_state = 2;
                            sbcount = 0;
                            continue;
                        }
                        default: {
                            if (TelnetIO.debug > 2) {
                                System.out.print("<UNKNOWN " + b + " > ");
                            }
                            this.neg_state = 0;
                            continue;
                        }
                    }
                    break;
                }
                case 3: {
                    byte reply = 0;
                    switch (b) {
                        case 1: {
                            if (TelnetIO.debug > 2) {
                                System.out.println("ECHO");
                            }
                            reply = -3;
                            vec = new Vector(2);
                            vec.addElement("NOLOCALECHO");
                            this.peer.notifyStatus(vec);
                            break;
                        }
                        case 25: {
                            if (TelnetIO.debug > 2) {
                                System.out.println("EOR");
                            }
                            reply = -3;
                            break;
                        }
                        default: {
                            if (TelnetIO.debug > 2) {
                                System.out.println("<UNKNOWN," + b + ">");
                            }
                            reply = -2;
                            break;
                        }
                    }
                    if (TelnetIO.debug > 1) {
                        System.out.println("<" + b + ", WILL =" + -5 + ">");
                    }
                    if (reply != this.sentDX[b + 128] || this.receivedWX[b + 128] != -5) {
                        sendbuf[0] = -1;
                        sendbuf[1] = reply;
                        sendbuf[2] = b;
                        this.send(sendbuf);
                        this.sentDX[b + 128] = reply;
                        this.receivedWX[b + 128] = -5;
                    }
                    this.neg_state = 0;
                    continue;
                }
                case 5: {
                    byte reply = 0;
                    switch (b) {
                        case 1: {
                            if (TelnetIO.debug > 2) {
                                System.out.println("ECHO");
                            }
                            vec = new Vector(2);
                            vec.addElement("LOCALECHO");
                            this.peer.notifyStatus(vec);
                            reply = -2;
                            break;
                        }
                        case 25: {
                            if (TelnetIO.debug > 2) {
                                System.out.println("EOR");
                            }
                            reply = -2;
                            break;
                        }
                        default: {
                            if (TelnetIO.debug > 2) {
                                System.out.println("<UNKNOWN," + b + ">");
                            }
                            reply = -2;
                            break;
                        }
                    }
                    if (reply != this.sentDX[b + 128] || this.receivedWX[b + 128] != -4) {
                        sendbuf[0] = -1;
                        sendbuf[1] = reply;
                        sendbuf[2] = b;
                        this.send(sendbuf);
                        this.sentDX[b + 128] = reply;
                        this.receivedWX[b + 128] = -5;
                    }
                    this.neg_state = 0;
                    continue;
                }
                case 4: {
                    byte reply = 0;
                    switch (b) {
                        case 1: {
                            if (TelnetIO.debug > 2) {
                                System.out.println("ECHO");
                            }
                            reply = -5;
                            vec = new Vector(2);
                            vec.addElement("LOCALECHO");
                            this.peer.notifyStatus(vec);
                            break;
                        }
                        case 24: {
                            if (TelnetIO.debug > 2) {
                                System.out.println("TTYPE");
                            }
                            reply = -5;
                            break;
                        }
                        case 31: {
                            if (TelnetIO.debug > 2) {
                                System.out.println("NAWS");
                            }
                            vec = new Vector(2);
                            vec.addElement("NAWS");
                            final Dimension size = (Dimension)this.peer.notifyStatus(vec);
                            this.receivedDX[b] = -3;
                            if (size == null) {
                                this.send((byte)(-1));
                                this.send((byte)(-4));
                                this.send((byte)31);
                                reply = -4;
                                this.sentWX[b] = -4;
                                break;
                            }
                            reply = -5;
                            this.sentWX[b] = -5;
                            sendbuf[0] = -1;
                            sendbuf[1] = -5;
                            sendbuf[2] = 31;
                            this.send(sendbuf);
                            this.send((byte)(-1));
                            this.send((byte)(-6));
                            this.send((byte)31);
                            this.send((byte)(size.width >> 8));
                            this.send((byte)(size.width & 0xFF));
                            this.send((byte)(size.height >> 8));
                            this.send((byte)(size.height & 0xFF));
                            this.send((byte)(-1));
                            this.send((byte)(-16));
                            break;
                        }
                        default: {
                            if (TelnetIO.debug > 2) {
                                System.out.println("<UNKNOWN," + b + ">");
                            }
                            reply = -4;
                            break;
                        }
                    }
                    if (reply != this.sentWX[128 + b] || this.receivedDX[128 + b] != -3) {
                        sendbuf[0] = -1;
                        sendbuf[1] = reply;
                        sendbuf[2] = b;
                        this.send(sendbuf);
                        this.sentWX[b + 128] = reply;
                        this.receivedDX[b + 128] = -3;
                    }
                    this.neg_state = 0;
                    continue;
                }
                case 6: {
                    byte reply = 0;
                    switch (b) {
                        case 1: {
                            if (TelnetIO.debug > 2) {
                                System.out.println("ECHO");
                            }
                            reply = -4;
                            vec = new Vector(2);
                            vec.addElement("NOLOCALECHO");
                            this.peer.notifyStatus(vec);
                            break;
                        }
                        case 31: {
                            if (TelnetIO.debug > 2) {
                                System.out.println("NAWS");
                            }
                            reply = -4;
                            break;
                        }
                        default: {
                            if (TelnetIO.debug > 2) {
                                System.out.println("<UNKNOWN," + b + ">");
                            }
                            reply = -4;
                            break;
                        }
                    }
                    if (reply != this.sentWX[b + 128] || this.receivedDX[b + 128] != -2) {
                        this.send((byte)(-1));
                        this.send(reply);
                        this.send(b);
                        this.sentWX[b + 128] = reply;
                        this.receivedDX[b + 128] = -2;
                    }
                    this.neg_state = 0;
                    continue;
                }
                case 7: {
                    if (TelnetIO.debug > 2) {
                        System.out.println(b + " ");
                    }
                    if (b == -1) {
                        sbcount = 0;
                        this.current_sb = b;
                        this.neg_state = 8;
                        continue;
                    }
                    System.out.println("(bad) " + b + " ");
                    this.neg_state = 0;
                    continue;
                }
                case 2: {
                    if (TelnetIO.debug > 2) {
                        System.out.println(b + " ");
                    }
                    switch (b) {
                        case -1: {
                            this.neg_state = 7;
                            continue;
                        }
                        default: {
                            this.current_sb = b;
                            sbcount = 0;
                            this.neg_state = 8;
                            continue;
                        }
                    }
                    break;
                }
                case 8: {
                    if (TelnetIO.debug > 2) {
                        System.out.println(b + " ");
                    }
                    switch (b) {
                        case -1: {
                            this.neg_state = 9;
                            continue;
                        }
                        default: {
                            sbbuf[sbcount++] = b;
                            continue;
                        }
                    }
                    break;
                }
                case 9: {
                    if (TelnetIO.debug > 2) {
                        System.out.println(b + " ");
                    }
                    switch (b) {
                        case -1: {
                            this.neg_state = 8;
                            sbbuf[sbcount++] = -1;
                            continue;
                        }
                        case -16: {
                            this.handle_sb(this.current_sb, sbbuf, sbcount);
                            this.current_sb = 0;
                            this.neg_state = 0;
                            continue;
                        }
                        case -6: {
                            this.handle_sb(this.current_sb, sbbuf, sbcount);
                            this.neg_state = 2;
                            continue;
                        }
                        default: {
                            this.neg_state = 0;
                            continue;
                        }
                    }
                    break;
                }
                default: {
                    if (TelnetIO.debug > 2) {
                        System.out.println("This should not happen: " + this.neg_state + " ");
                    }
                    this.neg_state = 0;
                    continue;
                }
            }
        }
        buf = new byte[noffset];
        System.arraycopy(nbuf, 0, buf, 0, noffset);
        return buf;
    }
    
    public Object notifyStatus(final Vector status) {
        if (TelnetIO.debug > 0) {
            System.out.println("TelnetIO.notifyStatus(" + status + ")");
        }
        return null;
    }
    
    public byte[] receive() throws IOException {
        int count = this.is.available();
        byte[] buf = new byte[count];
        count = this.is.read(buf);
        if (count < 0) {
            throw new IOException("Connection closed.");
        }
        if (TelnetIO.debug > 1) {
            System.out.println("TelnetIO.receive(): read bytes: " + count);
        }
        buf = this.negotiate(buf, count);
        return buf;
    }
    
    public void send(final byte b) throws IOException {
        if (TelnetIO.debug > 1) {
            System.out.println("TelnetIO.send(" + b + ")");
        }
        this.os.write(b);
        this.os.flush();
    }
    
    public void send(final byte[] buf) throws IOException {
        if (TelnetIO.debug > 1) {
            System.out.println("TelnetIO.send(" + buf + ")");
        }
        this.os.write(buf);
        this.os.flush();
    }
    
    public void setPeer(final StatusPeer obj) {
        this.peer = obj;
    }
    
    public String toString() {
        return "$Id: TelnetIO.java,v 1.10 1998/02/09 10:22:18 leo Exp $";
    }
}
