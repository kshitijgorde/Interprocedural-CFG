// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.telnet;

import java.io.IOException;
import java.awt.Dimension;

public abstract class TelnetProtocolHandler
{
    private static final int debug = 0;
    private static byte[] one;
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
    private static final byte TELOPT_SGA = 3;
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
    
    public TelnetProtocolHandler() {
        this.neg_state = 0;
        this.reset();
    }
    
    protected abstract String getTerminalType();
    
    protected abstract Dimension getWindowSize();
    
    protected abstract void setLocalEcho(final boolean p0);
    
    protected abstract void notifyEndOfRecord();
    
    protected abstract void write(final byte[] p0) throws IOException;
    
    private void write(final byte b) throws IOException {
        TelnetProtocolHandler.one[0] = b;
        this.write(TelnetProtocolHandler.one);
    }
    
    public void reset() {
        this.neg_state = 0;
        this.receivedDX = new byte[256];
        this.sentDX = new byte[256];
        this.receivedWX = new byte[256];
        this.sentWX = new byte[256];
    }
    
    private void handle_sb(final byte type, final byte[] sbdata, final int sbcount) throws IOException {
        switch (type) {
            case 24: {
                if (sbcount > 0 && sbdata[0] == 1) {
                    this.write(TelnetProtocolHandler.IACSB);
                    this.write((byte)24);
                    this.write((byte)0);
                    String ttype = this.getTerminalType();
                    if (ttype == null) {
                        ttype = "dumb";
                    }
                    this.write(ttype.getBytes());
                    this.write(TelnetProtocolHandler.IACSE);
                    break;
                }
                break;
            }
        }
    }
    
    public void startup() throws IOException {
        final byte[] sendbuf = { -1, -3, 3 };
        this.write(sendbuf);
        this.sentDX[3] = -3;
    }
    
    public void transpose(final byte[] buf) throws IOException {
        int nbufptr = 0;
        final byte[] nbuf = new byte[buf.length * 2];
        for (int i = 0; i < buf.length; ++i) {
            switch (buf[i]) {
                case -1: {
                    nbuf[nbufptr++] = -1;
                    nbuf[nbufptr++] = -1;
                    break;
                }
                case 10: {
                    nbuf[nbufptr++] = 13;
                    nbuf[nbufptr++] = 10;
                    break;
                }
                case 13: {
                    nbuf[nbufptr++] = 13;
                    nbuf[nbufptr++] = 0;
                    break;
                }
                default: {
                    nbuf[nbufptr++] = buf[i];
                    break;
                }
            }
        }
        final byte[] xbuf = new byte[nbufptr];
        System.arraycopy(nbuf, 0, xbuf, 0, nbufptr);
        this.write(xbuf);
    }
    
    public int negotiate(final byte[] buf, final int count) throws IOException {
        final byte[] nbuf = new byte[count];
        final byte[] sbbuf = new byte[count];
        final byte[] sendbuf = new byte[3];
        int sbcount = 0;
        int boffset = 0;
        int noffset = 0;
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
                            this.neg_state = 0;
                            nbuf[noffset++] = -1;
                            continue;
                        }
                        case -5: {
                            this.neg_state = 3;
                            continue;
                        }
                        case -4: {
                            this.neg_state = 5;
                            continue;
                        }
                        case -2: {
                            this.neg_state = 6;
                            continue;
                        }
                        case -3: {
                            this.neg_state = 4;
                            continue;
                        }
                        case -17: {
                            this.notifyEndOfRecord();
                            this.neg_state = 0;
                            continue;
                        }
                        case -6: {
                            this.neg_state = 2;
                            sbcount = 0;
                            continue;
                        }
                        default: {
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
                            reply = -3;
                            this.setLocalEcho(false);
                            break;
                        }
                        case 3: {
                            reply = -3;
                            break;
                        }
                        case 25: {
                            reply = -3;
                            break;
                        }
                        default: {
                            reply = -2;
                            break;
                        }
                    }
                    if (reply != this.sentDX[b + 128] || -5 != this.receivedWX[b + 128]) {
                        sendbuf[0] = -1;
                        sendbuf[1] = reply;
                        sendbuf[2] = b;
                        this.write(sendbuf);
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
                            this.setLocalEcho(true);
                            reply = -2;
                            break;
                        }
                        case 3: {
                            reply = -2;
                            break;
                        }
                        case 25: {
                            reply = -2;
                            break;
                        }
                        default: {
                            reply = -2;
                            break;
                        }
                    }
                    if (reply != this.sentDX[b + 128] || -4 != this.receivedWX[b + 128]) {
                        sendbuf[0] = -1;
                        sendbuf[1] = reply;
                        sendbuf[2] = b;
                        this.write(sendbuf);
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
                            reply = -5;
                            this.setLocalEcho(true);
                            break;
                        }
                        case 3: {
                            reply = -5;
                            break;
                        }
                        case 24: {
                            reply = -5;
                            break;
                        }
                        case 31: {
                            final Dimension size = this.getWindowSize();
                            this.receivedDX[b] = -3;
                            if (size == null) {
                                this.write((byte)(-1));
                                this.write((byte)(-4));
                                this.write((byte)31);
                                reply = -4;
                                this.sentWX[b] = -4;
                                break;
                            }
                            reply = -5;
                            this.sentWX[b] = -5;
                            sendbuf[0] = -1;
                            sendbuf[1] = -5;
                            sendbuf[2] = 31;
                            this.write(sendbuf);
                            this.write((byte)(-1));
                            this.write((byte)(-6));
                            this.write((byte)31);
                            this.write((byte)(size.width >> 8));
                            this.write((byte)(size.width & 0xFF));
                            this.write((byte)(size.height >> 8));
                            this.write((byte)(size.height & 0xFF));
                            this.write((byte)(-1));
                            this.write((byte)(-16));
                            break;
                        }
                        default: {
                            reply = -4;
                            break;
                        }
                    }
                    if (reply != this.sentWX[128 + b] || -3 != this.receivedDX[128 + b]) {
                        sendbuf[0] = -1;
                        sendbuf[1] = reply;
                        sendbuf[2] = b;
                        this.write(sendbuf);
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
                            reply = -4;
                            this.setLocalEcho(false);
                            break;
                        }
                        case 3: {
                            reply = -4;
                            break;
                        }
                        case 31: {
                            reply = -4;
                            break;
                        }
                        default: {
                            reply = -4;
                            break;
                        }
                    }
                    if (reply != this.sentWX[b + 128] || -2 != this.receivedDX[b + 128]) {
                        this.write((byte)(-1));
                        this.write(reply);
                        this.write(b);
                        this.sentWX[b + 128] = reply;
                        this.receivedDX[b + 128] = -2;
                    }
                    this.neg_state = 0;
                    continue;
                }
                case 7: {
                    if (b == -1) {
                        sbcount = 0;
                        this.current_sb = b;
                        this.neg_state = 8;
                        continue;
                    }
                    System.err.println("(bad) " + b + " ");
                    this.neg_state = 0;
                    continue;
                }
                case 2: {
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
                    this.neg_state = 0;
                    continue;
                }
            }
        }
        System.arraycopy(nbuf, 0, buf, 0, noffset);
        return noffset;
    }
    
    static {
        TelnetProtocolHandler.one = new byte[1];
        IACWILL = new byte[] { -1, -5 };
        IACWONT = new byte[] { -1, -4 };
        IACDO = new byte[] { -1, -3 };
        IACDONT = new byte[] { -1, -2 };
        IACSB = new byte[] { -1, -6 };
        IACSE = new byte[] { -1, -16 };
    }
}
