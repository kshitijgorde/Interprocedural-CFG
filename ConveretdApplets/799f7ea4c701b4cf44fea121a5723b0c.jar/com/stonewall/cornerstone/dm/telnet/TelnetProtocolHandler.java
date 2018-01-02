// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.telnet;

import java.io.IOException;
import java.awt.Dimension;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.dm.cli.CLI;

public abstract class TelnetProtocolHandler extends CLI
{
    static final Log log;
    public static final String ID = "$Id: TelnetProtocolHandler.java,v 2.11 2001/01/01 12:30:16 leo Exp $";
    private static final int debug = 0;
    private byte[] tempbuf;
    private byte[] crlf;
    private byte[] cr;
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
    
    static {
        log = Log.getLog(TelnetProtocolHandler.class);
        IACWILL = new byte[] { -1, -5 };
        IACWONT = new byte[] { -1, -4 };
        IACDO = new byte[] { -1, -3 };
        IACDONT = new byte[] { -1, -2 };
        IACSB = new byte[] { -1, -6 };
        IACSE = new byte[] { -1, -16 };
    }
    
    public TelnetProtocolHandler() {
        this.tempbuf = new byte[0];
        this.crlf = new byte[2];
        this.cr = new byte[2];
        this.neg_state = 0;
        this.reset();
        this.crlf[0] = 13;
        this.crlf[1] = 10;
        this.cr[0] = 13;
        this.cr[1] = 0;
    }
    
    protected abstract String getTerminalType();
    
    protected abstract Dimension getWindowSize();
    
    protected abstract void setLocalEcho(final boolean p0);
    
    protected abstract void notifyEndOfRecord();
    
    protected abstract void write(final byte[] p0) throws IOException;
    
    private void write(final byte b) throws IOException {
        final byte[] one = { b };
        this.write(one);
    }
    
    public void reset() {
        this.neg_state = 0;
        this.receivedDX = new byte[256];
        this.sentDX = new byte[256];
        this.receivedWX = new byte[256];
        this.sentWX = new byte[256];
    }
    
    public void sendTelnetControl(final byte code) throws IOException {
        final byte[] b = { -1, code };
        this.write(b);
    }
    
    private void handle_sb(final byte type, final byte[] sbdata, final int sbcount) throws IOException {
        TelnetProtocolHandler.log.debug("TelnetIO.handle_sb(" + type + ")");
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
        byte[] nbuf = new byte[buf.length * 2];
        for (int i = 0; i < buf.length; ++i) {
            switch (buf[i]) {
                case -1: {
                    nbuf[nbufptr++] = -1;
                    nbuf[nbufptr++] = -1;
                    break;
                }
                case 10: {
                    while (nbuf.length - nbufptr < this.crlf.length) {
                        final byte[] xbuf = new byte[nbuf.length * 2];
                        System.arraycopy(nbuf, 0, xbuf, 0, nbufptr);
                        nbuf = xbuf;
                    }
                    for (int j = 0; j < this.crlf.length; ++j) {
                        nbuf[nbufptr++] = this.crlf[j];
                    }
                    break;
                }
                case 13: {
                    while (nbuf.length - nbufptr < this.cr.length) {
                        final byte[] xbuf = new byte[nbuf.length * 2];
                        System.arraycopy(nbuf, 0, xbuf, 0, nbufptr);
                        nbuf = xbuf;
                    }
                    for (int j = 0; j < this.cr.length; ++j) {
                        nbuf[nbufptr++] = this.cr[j];
                    }
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
    
    public void setCRLF(final String xcrlf) {
        this.crlf = xcrlf.getBytes();
    }
    
    public void setCR(final String xcr) {
        this.cr = xcr.getBytes();
    }
    
    public int negotiate(final byte[] nbuf) throws IOException {
        final byte[] sbbuf = new byte[this.tempbuf.length];
        final int count = this.tempbuf.length;
        final byte[] buf = this.tempbuf;
        final byte[] sendbuf = new byte[3];
        int sbcount = 0;
        int boffset = 0;
        int noffset = 0;
        boolean dobreak = false;
        if (count == 0) {
            return -1;
        }
        while (!dobreak && boffset < count) {
            byte b = buf[boffset++];
            if (b >= 128) {
                b -= 256;
            }
            switch (this.neg_state) {
                case 0: {
                    if (b == -1) {
                        this.neg_state = 1;
                        dobreak = true;
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
                    TelnetProtocolHandler.log.debug("(bad) " + b + " ");
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
                    TelnetProtocolHandler.log.debug("This should not happen: " + this.neg_state + " ");
                    this.neg_state = 0;
                    continue;
                }
            }
        }
        final byte[] xb = new byte[count - boffset];
        System.arraycopy(this.tempbuf, boffset, xb, 0, count - boffset);
        this.tempbuf = xb;
        return noffset;
    }
    
    public void inputfeed(final byte[] b, final int len) {
        final byte[] xb = new byte[this.tempbuf.length + len];
        System.arraycopy(this.tempbuf, 0, xb, 0, this.tempbuf.length);
        System.arraycopy(b, 0, xb, this.tempbuf.length, len);
        this.tempbuf = xb;
    }
}
