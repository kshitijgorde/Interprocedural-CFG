// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.util.Observable;

public class C1541 extends Observable
{
    public static final int IO_OFFSET = 12288;
    public static final boolean DEBUG = true;
    public static final int SERIAL_ATN = 8;
    public static final int SERIAL_CLK_OUT = 16;
    public static final int SERIAL_DATA_OUT = 32;
    public static final int SERIAL_CLK_IN = 64;
    public static final int SERIAL_DATA_IN = 128;
    public static final int TALK = 64;
    public static final int LISTEN = 32;
    public static final int DATA = 96;
    public static final int OPEN = 240;
    public static final int CLOSE = 224;
    private int[] memory;
    private C64Reader reader;
    private static final int IDLE = 0;
    private static final int ATN = 1;
    private static final int RECEIVING = 2;
    private static final int SENDING = 3;
    private static final int READ_BIT = 4;
    private static final int WAIT_BIT = 5;
    private static final int READ_BYTE = 6;
    private static final int WRITE_BYTE = 7;
    private static final int LOAD_FILE = 1;
    private static final int SAVE_FILE = 2;
    private static final int LOGICAL_CHANNEL = 3;
    private static final int ATN_SEEN = 10;
    private static final int ATN_READ_BIT = 11;
    private static final int ATN_WAIT_BIT = 12;
    private static final int WAIT_LISTENER_READY = 13;
    private static final int WRITE_BIT_CLK1 = 14;
    private static final int WRITE_BIT_CLK2 = 15;
    private static final int WRITE_END = 16;
    private static final int WAIT_LISTENER_EOI_HANDSHAKE = 17;
    private static final int READ_FILENAME = 1;
    private DiskChannel[] channel;
    private boolean atnLast;
    private int mode;
    private long eoiTimeout;
    private int eoi;
    private boolean lastChar;
    private int role;
    private int floppyMode;
    private int floppyChannel;
    private String filename;
    private long waitTimeout;
    private int readMode;
    private String tmpFilename;
    int rbState;
    int rbByte;
    int rbCtr;
    int eoiCtr;
    int wByte;
    int wBitPos;
    int wBytePos;
    int wState;
    long wCyclesWait;
    byte[] bytesToWrite;
    boolean wEOI;
    
    public C1541(final int[] memory) {
        this.channel = new DiskChannel[16];
        this.atnLast = false;
        this.mode = 0;
        this.eoiTimeout = 0L;
        this.lastChar = false;
        this.role = 0;
        this.floppyMode = 0;
        this.floppyChannel = 0;
        this.waitTimeout = 0L;
        this.readMode = 1;
        this.tmpFilename = "";
        this.rbCtr = 0;
        this.eoiCtr = 0;
        this.wEOI = false;
        this.memory = memory;
        this.reader = new C64Reader();
        for (int i = 0; i < 16; ++i) {
            this.channel[i] = new DiskChannel(i);
        }
    }
    
    public void reset() {
        this.mode = 0;
        this.role = 0;
        this.rbState = 0;
        this.rbByte = 0;
        this.floppyMode = 0;
        this.floppyChannel = 0;
        this.clockHi();
    }
    
    public C64Reader getReader() {
        return this.reader;
    }
    
    public void tick(final long n) {
        if (this.waitTimeout != 0L && this.waitTimeout < n) {
            System.out.print(".");
            this.tick(n, true);
        }
    }
    
    public void tick(final long n, final boolean b) {
        final int n2 = this.memory[68864];
        final boolean atnLast = (n2 & 0x8) != 0x0;
        final boolean b2 = (n2 & 0x20) != 0x0;
        final boolean b3 = (n2 & 0x10) != 0x0;
        final boolean b4 = atnLast & !this.atnLast;
        switch (this.mode) {
            case 6: {
                if (b4) {
                    this.mode = 0;
                    return;
                }
                final int byte1 = this.readByte(n2, n, false, b);
                if (byte1 == 0) {
                    break;
                }
                this.mode = 0;
                if (atnLast) {
                    this.handleATNByte(byte1);
                    break;
                }
                System.out.println("//// Read byte: " + Integer.toString(byte1, 16) + " => " + (char)byte1);
                this.dataLo();
                if (this.readMode != 1) {
                    break;
                }
                this.tmpFilename += (char)byte1;
                if (this.lastChar) {
                    System.out.println("Filename: " + this.tmpFilename);
                    this.filename = this.tmpFilename;
                    break;
                }
                break;
            }
            case 7: {
                if (b4) {
                    this.mode = 0;
                    return;
                }
                if (this.writeByte(n2, n, b)) {
                    this.reset();
                    break;
                }
                break;
            }
            case 10: {
                if (atnLast && !b3) {
                    this.dataHi();
                    this.mode = 6;
                    this.readByte(n2, n, true, false);
                    break;
                }
                if (!atnLast) {
                    this.mode = 0;
                    break;
                }
                break;
            }
            case 0: {
                if (atnLast & b3) {
                    System.out.println("C1541: ATN Seen...");
                    this.dataLo();
                    this.mode = 10;
                }
                if (!atnLast && this.role == 64) {
                    if (!b3 && b2) {
                        this.mode = 7;
                        this.initWrite(n);
                        break;
                    }
                    break;
                }
                else {
                    if (!atnLast && this.role != 0 && !b3) {
                        this.mode = 6;
                        this.readByte(n2, n, true, false);
                        break;
                    }
                    break;
                }
                break;
            }
        }
        this.atnLast = atnLast;
    }
    
    private int readByte(final int n, final long n2, final boolean b, final boolean b2) {
        final boolean b3 = (n & 0x20) != 0x0;
        final boolean b4 = (n & 0x10) != 0x0;
        if (b) {
            this.rbCtr = 0;
            this.rbByte = 0;
            this.rbState = 5;
            this.dataHi();
            System.out.println("Start reading byte - data lo");
            this.waitTimeout = 200L + n2;
            this.eoiCtr = 0;
            this.lastChar = false;
            return 0;
        }
        if (b2) {
            System.out.println("//// EOI Timeout???");
            if (this.eoiCtr == 0) {
                System.out.println("//// EOI 1 => dataLo");
                this.dataLo();
                this.waitTimeout = 80L + n2;
            }
            else {
                System.out.println("///// EOI 2 => dataHi");
                this.dataHi();
                this.waitTimeout = 0L;
                this.lastChar = true;
            }
            ++this.eoiCtr;
        }
        if (this.rbState == 5) {
            if (b4) {
                this.rbState = 4;
            }
        }
        else if (!b4) {
            this.rbByte |= (b3 ? 0 : (1 << this.rbCtr));
            this.rbState = 5;
            ++this.rbCtr;
            this.waitTimeout = 0L;
        }
        if (this.rbCtr == 8) {
            return this.rbByte;
        }
        return 0;
    }
    
    private void initWriteByte(final int wByte, final long n) {
        System.out.print("***>> InitW: " + Integer.toString(wByte & 0xFF, 16) + " '" + (char)((wByte > 32) ? wByte : 46) + "' ");
        this.wByte = wByte;
        this.wBitPos = 0;
        this.wCyclesWait = n + 100L;
        this.waitTimeout = n + 100L;
        this.wState = 13;
    }
    
    private boolean writeByte(final int n, final long n2, final boolean b) {
        final boolean b2 = (n & 0x20) != 0x0;
        if (this.wCyclesWait > n2) {
            return false;
        }
        switch (this.wState) {
            case 13: {
                this.clockHi();
                if (b2) {
                    System.out.print("[-R]");
                    break;
                }
                if (this.bytesToWrite == null) {
                    this.waitTimeout = 0L;
                    return true;
                }
                if (!this.wEOI) {
                    System.out.print("[R]");
                    this.wState = 15;
                    break;
                }
                System.out.print("[R(EOI)]");
                this.wState = 17;
                break;
            }
            case 17: {
                if (b2) {
                    System.out.println("EOI handshake!!!");
                    this.wEOI = false;
                    this.wState = 13;
                    break;
                }
                break;
            }
            case 14: {
                if ((this.wByte & 1 << this.wBitPos) == 0x0) {
                    this.dataLo();
                }
                else {
                    this.dataHi();
                }
                ++this.wBitPos;
                this.clockHi();
                this.wCyclesWait = n2 + 70L;
                if (this.wBitPos < 8) {
                    this.wState = 15;
                    break;
                }
                this.wState = 16;
                break;
            }
            case 15: {
                this.clockLo();
                this.dataLo();
                this.wCyclesWait = n2 + 70L;
                this.wState = 14;
                break;
            }
            case 16: {
                this.clockLo();
                if (b2) {
                    System.out.println("Ack: " + Integer.toString(this.memory[164], 16));
                    ++this.wBytePos;
                    if (this.wBytePos % 10 == 0) {
                        this.setChanged();
                        this.notifyObservers("Loading " + this.filename + " " + 100 * this.wBytePos / this.bytesToWrite.length + "%");
                    }
                    if (this.wBytePos == this.bytesToWrite.length - 1) {
                        this.wEOI = true;
                    }
                    else if (this.wBytePos >= this.bytesToWrite.length) {
                        this.waitTimeout = 0L;
                        this.wEOI = false;
                        System.out.println("******** Write finished!!!");
                        this.setChanged();
                        this.notifyObservers("");
                        return true;
                    }
                    this.initWriteByte(this.bytesToWrite[this.wBytePos], n2);
                    break;
                }
                break;
            }
        }
        return false;
    }
    
    private void initWrite(final long n) {
        this.clockLo();
        this.wBytePos = 0;
        this.wEOI = false;
        if (this.floppyMode == 1) {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if ((this.filename = this.reader.readFile(this.filename, -1, byteArrayOutputStream)) != null) {
                this.bytesToWrite = byteArrayOutputStream.toByteArray();
                System.out.println("C1541 have " + this.bytesToWrite.length + " bytes to write");
                this.initWriteByte(this.bytesToWrite[0], n);
            }
            else {
                System.out.println("File not found... should signal error...");
                this.bytesToWrite = null;
                this.initWriteByte(0, n);
            }
        }
        if (this.floppyMode == 3) {
            System.out.println("Should write logical channel data!");
            this.bytesToWrite = this.channel[this.floppyChannel].getData();
            this.initWriteByte(this.bytesToWrite[0], n);
        }
    }
    
    private void handleATNByte(final int n) {
        final int n2 = n & 0xF0;
        final int n3 = n & 0x1F;
        final int n4 = n & 0xF;
        System.out.println("ATN Byte: " + n + " " + Integer.toString(n, 16));
        Label_0556: {
            switch (n2) {
                case 64:
                case 80: {
                    this.role = 0;
                    if (n3 == 31) {
                        System.out.println("  >> UNTALK!!!");
                        break;
                    }
                    System.out.println("  Received TALK for dev: " + n3);
                    if (n3 == 8) {
                        System.out.println("### DEV: 8 ACTIVE as 1541!");
                        this.role = 64;
                        break;
                    }
                    break;
                }
                case 32:
                case 48: {
                    this.role = 0;
                    if (n3 == 31) {
                        System.out.println("  >> UNLISTEN!!!");
                        if (this.floppyMode != 3) {
                            break;
                        }
                        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        if ((this.tmpFilename = this.reader.readFile(this.tmpFilename, -1, byteArrayOutputStream)) != null) {
                            this.channel[this.floppyChannel].setData(byteArrayOutputStream.toByteArray());
                            System.out.println("Setting channel " + this.floppyChannel + " to " + this.tmpFilename + " size: " + this.channel[this.floppyChannel].getData().length);
                            this.channel[this.floppyChannel].setFilename(this.tmpFilename);
                            this.filename = this.tmpFilename;
                            break;
                        }
                        System.out.println("#### File not found error???");
                        break;
                    }
                    else {
                        System.out.println("  Received LISTEN for dev: " + n3);
                        if (n3 == 8) {
                            System.out.println("### DEV: 8 ACTIVE as 1541!");
                            this.role = 32;
                            break;
                        }
                        break;
                    }
                    break;
                }
                case 240: {
                    System.out.println("### OPEN sec addr: " + n4);
                    this.tmpFilename = "";
                    this.readMode = 1;
                    if (n4 == 0) {
                        System.out.println("### => LOAD File!");
                        this.floppyMode = 1;
                        break Label_0556;
                    }
                    if (n4 == 1) {
                        System.out.println("### => SAVE File!");
                        this.floppyMode = 2;
                        this.readMode = 1;
                        break Label_0556;
                    }
                    if (n4 == 15) {
                        System.out.println("### => Error...");
                        break Label_0556;
                    }
                    System.out.println("Logical channel: " + n4);
                    this.floppyMode = 3;
                    this.floppyChannel = n4;
                    break Label_0556;
                }
                case 224: {
                    System.out.println("### Close: secAdr: " + n4);
                    this.channel[n4].close();
                    break;
                }
                case 96: {
                    System.out.println("### DATA sec addr: " + n4);
                    System.out.println("Setting floppy channel!");
                    this.floppyChannel = n4;
                    break;
                }
            }
        }
    }
    
    private void clockLo() {
        final int[] memory = this.memory;
        final int n = 68864;
        memory[n] &= 0xFFFFFFBF;
    }
    
    private void clockHi() {
        final int[] memory = this.memory;
        final int n = 68864;
        memory[n] |= 0x40;
    }
    
    public void dataLo() {
        final int[] memory = this.memory;
        final int n = 68864;
        memory[n] &= 0xFFFFFF7F;
    }
    
    public void dataHi() {
        final int[] memory = this.memory;
        final int n = 68864;
        memory[n] |= 0x80;
    }
    
    public void handleDisk(final int n, final long n2) {
        System.out.print("EMU: ");
        printSerial(n);
        this.tick(n2, false);
    }
    
    public static void printSerial(final int n) {
        if ((n & 0x8) != 0x0) {
            System.out.print("A1");
        }
        else {
            System.out.print("A0");
        }
        System.out.print(" C" + (((n & 0x10) != 0x0) ? 1 : 0));
        System.out.print(" D" + (((n & 0x20) != 0x0) ? 1 : 0));
        System.out.print(" c" + (((n & 0x40) != 0x0) ? 1 : 0));
        System.out.println(" d" + (((n & 0x80) != 0x0) ? 1 : 0) + " (iec)");
    }
}
