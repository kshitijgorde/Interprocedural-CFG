// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

public class C1541Chips extends C64Chips implements DiskListener
{
    public static final int GCR_SECTOR_SIZE = 354;
    public static final boolean DEBUG_IRQ = false;
    public static final boolean DEBUG_WRITE = false;
    public static final boolean DEBUG_GCR = false;
    public static final boolean DEBUG_IEC = false;
    public static final Object LED_MOTOR;
    public static final Object HEAD_MOVED;
    public static final Object SECTOR_UPDATE;
    public static final int[] GCR;
    public static final int[] GCR_REV;
    private C1541Emu cpu;
    private int diskID1;
    private int diskID2;
    private int track;
    private int hTrack;
    private int sector;
    private int sectorPos;
    private int currentTrackSize;
    private int[] gcrSector;
    private int via1PB;
    private int via1PA;
    private int via1CB;
    private int via1CA;
    private int via1T1Ctr;
    private int via1T1Latch;
    private int via1T2Ctr;
    private int via1T2Latch;
    private int via1SerialRegister;
    private int via1AuxControl;
    private int via1PerControl;
    private int via1IFlag;
    private int via1IEnable;
    private int via2PB;
    private int via2PA;
    private int via2CB;
    private int via2CA;
    private int via2T1Ctr;
    private int via2T1Latch;
    private int via2T2Ctr;
    private int via2T2Latch;
    private int via2SerialRegister;
    private int via2AuxControl;
    int via2PerControl;
    private int via2IFlag;
    private int via2IEnable;
    private boolean diskChanged;
    private boolean writeProtected;
    public boolean ledOn;
    public boolean motorOn;
    public int currentTrack;
    public int currentSector;
    public int headOutBeyond;
    private C64Reader reader;
    C64Screen cia2;
    int iecLines;
    private int lastRead;
    long lastCycles;
    long nextCheck;
    
    public C1541Chips(final C1541Emu cpu) {
        this.diskID1 = 0;
        this.diskID2 = 0;
        this.track = 1;
        this.hTrack = 2;
        this.sector = 0;
        this.sectorPos = 0;
        this.currentTrackSize = 21;
        this.gcrSector = new int[354];
        this.diskChanged = true;
        this.writeProtected = true;
        this.headOutBeyond = 0;
        this.lastCycles = 0L;
        this.nextCheck = 0L;
        this.init(this.cpu = cpu);
    }
    
    public void initIEC2(final C64Screen cia2) {
        this.cia2 = cia2;
    }
    
    public void setReader(final C64Reader reader) {
        System.out.println("C1541: Setting reader...");
        (this.reader = reader).setDiskListener(this);
    }
    
    public final int performRead(final int n, final long n2) {
        switch (n) {
            case 6144: {
                return ((this.via1PB & 0x1A) | ((this.iecLines & this.cia2.iecLines) >> 7 & 0x1) | ((this.iecLines & this.cia2.iecLines) >> 4 & 0x4) | (this.cia2.iecLines << 3 & 0x80)) ^ 0x85;
            }
            case 6145:
            case 6159: {
                this.via1IFlag &= 0xFFFFFFFD;
                this.checkInterrupt(1, "read from pa");
                return 255;
            }
            case 6146: {
                return this.via1CB;
            }
            case 6147: {
                return this.via1CB;
            }
            case 6148: {
                this.via1IFlag &= 0xBF;
                this.checkInterrupt(1, "read T1 low");
                return this.via1T1Ctr & 0xFF;
            }
            case 6149: {
                return this.via1T1Ctr >> 8;
            }
            case 6150: {
                return this.via1T1Latch & 0xFF;
            }
            case 6151: {
                return this.via1T1Latch >> 8;
            }
            case 6152: {
                this.via1IFlag &= 0xDF;
                this.checkInterrupt(1, "read T2 low");
                return this.via1T2Ctr & 0xFF;
            }
            case 6153: {
                return this.via1T2Ctr >> 8;
            }
            case 6154: {
                return this.via1SerialRegister;
            }
            case 6155: {
                return this.via1AuxControl;
            }
            case 6156: {
                return this.via1PerControl;
            }
            case 6157: {
                return this.via1IFlag;
            }
            case 6158: {
                return this.via1IEnable;
            }
            case 7168: {
                return (this.via2PB & 0x6F) | this.sync() | this.writeProtect();
            }
            case 7169:
            case 7183: {
                return this.readByte();
            }
            case 7170: {
                return this.via2CB;
            }
            case 7171: {
                return this.via2CB;
            }
            case 7172: {
                this.via2IFlag &= 0xBF;
                this.checkInterrupt(1, "read T1 low");
                return this.via2T1Ctr & 0xFF;
            }
            case 7173: {
                return this.via2T1Ctr >> 8;
            }
            case 7174: {
                return this.via2T1Latch & 0xFF;
            }
            case 7175: {
                return this.via2T1Latch >> 8;
            }
            case 7176: {
                this.via2IFlag &= 0xDF;
                this.checkInterrupt(2, "read T2 low");
                return this.via2T2Ctr & 0xFF;
            }
            case 7177: {
                return this.via2T2Ctr >> 8;
            }
            case 7178: {
                return this.via2SerialRegister;
            }
            case 7179: {
                return this.via2AuxControl;
            }
            case 7180: {
                return this.via2PerControl;
            }
            case 7181: {
                return this.via2IFlag;
            }
            case 7182: {
                return this.via2IEnable;
            }
            default: {
                return 0;
            }
        }
    }
    
    public final void performWrite(final int n, int n2, final long n3) {
        switch (n) {
            case 6144: {
                this.via1PB = n2;
                n2 = (~this.via1PB & this.via1CB);
                this.iecLines = ((n2 << 6 & (~n2 ^ this.cia2.iecLines) << 3 & 0x80) | (n2 << 3 & 0x40));
                break;
            }
            case 6145: {
                this.via1IFlag &= 0xFFFFFFFD;
                this.checkInterrupt(1, "wrote pa");
                this.via1PA = n2;
                break;
            }
            case 6146: {
                this.via1CB = n2;
                break;
            }
            case 6147: {
                this.via1CA = n2;
                break;
            }
            case 6148: {
                this.via1T1Latch = ((this.via1T1Latch & 0xFF00) | n2);
                break;
            }
            case 6149: {
                this.via1T1Latch = ((this.via1T1Latch & 0xFF) | n2 << 8);
                this.via1IFlag &= 0xBF;
                this.via1T1Ctr = this.via1T1Latch;
                this.checkInterrupt(1, "write T1 high");
                break;
            }
            case 6150: {
                this.via1T1Latch = ((this.via1T1Latch & 0xFF00) | n2);
                break;
            }
            case 6151: {
                this.via1T1Latch = ((this.via1T1Latch & 0xFF) | n2 << 8);
                break;
            }
            case 6152: {
                this.via1T2Latch = ((this.via1T2Latch & 0xFF00) | n2);
                break;
            }
            case 6153: {
                this.via1T2Latch = ((this.via1T2Latch & 0xFF) | n2 << 8);
                this.via1IFlag &= 0xDF;
                this.via1T2Ctr = this.via1T1Latch;
                this.checkInterrupt(1, "write T2 high");
                break;
            }
            case 6154: {
                this.via1SerialRegister = n2;
                break;
            }
            case 6155: {
                this.via1AuxControl = n2;
                break;
            }
            case 6156: {
                this.via1PerControl = n2;
                break;
            }
            case 6157: {
                this.via1IFlag &= ~n2;
                this.checkInterrupt(1, "write IFlag");
                break;
            }
            case 6158: {
                if ((n2 & 0x80) == 0x80) {
                    this.via1IEnable |= (n2 & 0x7F);
                }
                else {
                    this.via1IEnable &= ~n2;
                }
                System.out.println("Wrote IE CIA1: " + this.via1IEnable);
                this.checkInterrupt(1, "write IE");
                break;
            }
            case 7168: {
                final boolean ledOn = this.ledOn;
                final boolean motorOn = this.motorOn;
                this.ledOn = ((n2 & 0x8) != 0x0);
                this.motorOn = ((n2 & 0x4) != 0x0);
                if (ledOn != this.ledOn | motorOn != this.motorOn) {
                    this.update(this, C1541Chips.LED_MOTOR);
                }
                if (((this.via2PB ^ n2) & 0x3) != 0x0) {
                    if ((this.via2PB & 0x3) == (n2 + 1 & 0x3)) {
                        this.headOut();
                    }
                    else if ((this.via2PB & 0x3) == (n2 - 1 & 0x3)) {
                        this.headIn();
                    }
                }
                this.via2PB = n2;
                break;
            }
            case 7169: {
                this.via2PA = n2;
                break;
            }
            case 7170: {
                this.via2CB = n2;
                break;
            }
            case 7171: {
                this.via2CA = n2;
                break;
            }
            case 7172: {
                this.via2T1Latch = ((this.via2T1Latch & 0xFF00) | n2);
                break;
            }
            case 7173: {
                this.via2T1Latch = ((this.via2T1Latch & 0xFF) | n2 << 8);
                this.via2IFlag &= 0xBF;
                this.via2T1Ctr = this.via2T1Latch;
                this.checkInterrupt(2, "write T1 high: " + n2 + " latch: " + this.via2T1Latch);
                break;
            }
            case 7174: {
                this.via2T1Latch = ((this.via2T1Latch & 0xFF00) | n2);
                break;
            }
            case 7175: {
                this.via2T1Latch = ((this.via2T1Latch & 0xFF) | n2 << 8);
                break;
            }
            case 7176: {
                this.via2T2Latch = ((this.via2T2Latch & 0xFF00) | n2);
                break;
            }
            case 7177: {
                this.via2T2Latch = ((this.via2T2Latch & 0xFF) | n2 << 8);
                this.via2IFlag &= 0xDF;
                this.via2T2Ctr = this.via2T2Latch;
                this.checkInterrupt(2, "write T2 high");
                break;
            }
            case 7178: {
                this.via2SerialRegister = n2;
                break;
            }
            case 7179: {
                this.via2AuxControl = n2;
                break;
            }
            case 7180: {
                this.via2PerControl = n2;
                break;
            }
            case 7181: {
                this.via2IFlag &= ~n2;
                this.checkInterrupt(2, "write IFlag");
                break;
            }
            case 7182: {
                if ((n2 & 0x80) == 0x80) {
                    this.via2IEnable |= (n2 & 0x7F);
                }
                else {
                    this.via2IEnable &= ~n2;
                }
                this.checkInterrupt(2, "write IE");
                break;
            }
        }
    }
    
    private void checkInterrupt(final int n, final String s) {
        if (n == 1) {
            if ((this.via1IFlag & this.via1IEnable) == 0x0) {
                this.via1IFlag &= 0x7F;
                this.clearIRQ(1);
            }
            else {
                this.via1IFlag |= 0x80;
                this.setIRQ(1);
            }
        }
        else if ((this.via2IFlag & this.via2IEnable) == 0x0) {
            this.via2IFlag &= 0x7F;
            this.clearIRQ(2);
        }
        else {
            this.via2IFlag |= 0x80;
            this.setIRQ(2);
        }
    }
    
    public final void updateChips(final long lastCycles) {
        if (this.nextCheck > lastCycles) {
            return;
        }
        final int n = (int)(lastCycles - this.lastCycles);
        this.lastCycles = lastCycles;
        this.nextCheck = lastCycles + 13L;
        this.via1T1Ctr -= n;
        if (this.via1T1Ctr <= 0) {
            if ((this.via1AuxControl & 0x40) == 0x40) {
                this.via1T1Ctr += this.via1T1Latch;
            }
            else {
                this.via1T1Ctr &= 0xFFFF;
            }
            this.via1IFlag |= 0x40;
            this.checkInterrupt(1, "clock wrap, T1");
        }
        if ((this.via1AuxControl & 0x20) == 0x0) {
            this.via1T2Ctr -= n;
            if (this.via1T2Ctr <= 0) {
                this.via1IFlag |= 0x20;
                this.via1T2Ctr &= 0xFFFF;
                this.checkInterrupt(1, "clock wrap, T2");
            }
        }
        this.via2T1Ctr -= n;
        if (this.via2T1Ctr <= 0) {
            if ((this.via2AuxControl & 0x40) == 0x40) {
                this.via2T1Ctr += this.via2T1Latch;
            }
            else {
                this.via2T1Ctr &= 0xFFFF;
            }
            this.via2IFlag |= 0x40;
            this.checkInterrupt(2, "clock wrap, T1:  latch:" + this.via2T1Latch);
        }
        if ((this.via2AuxControl & 0x20) == 0x0) {
            this.via2T2Ctr -= n;
            if (this.via2T2Ctr <= 0) {
                this.via2IFlag |= 0x20;
                this.via2T2Ctr &= 0xFFFF;
                this.checkInterrupt(2, "clock wrap, T2");
            }
        }
    }
    
    public void diskChanged() {
        this.diskChanged = true;
        final byte[] sector = this.reader.getSector(18, 0);
        this.diskID1 = (sector[162] & 0xFF);
        this.diskID2 = (sector[163] & 0xFF);
        System.out.println("Disk changed => Disk ID:" + this.diskID1 + "," + this.diskID2);
    }
    
    public void atnChanged(final boolean b) {
        if (b) {
            this.via1IFlag |= 0x2;
            this.checkInterrupt(1, "atn went high");
        }
        final int n = ~this.via1PB & this.via1CB;
        this.iecLines = ((n << 6 & (~n ^ this.cia2.iecLines) << 3 & 0x80) | (n << 3 & 0x40));
    }
    
    public void reset() {
        this.track = 1;
        this.hTrack = 2;
        this.sector = 0;
        this.sectorPos = 0;
        this.diskChanged = false;
        this.writeProtected = true;
        this.currentTrackSize = C64Reader.getSectorCount(this.track);
        this.readGCRSector(this.track, this.sector);
    }
    
    private int writeProtect() {
        if (this.diskChanged) {
            System.out.println("C1541: //// Disk change detected???!!! ////");
            this.diskChanged = false;
            return this.writeProtected ? 16 : 0;
        }
        return this.writeProtected ? 0 : 16;
    }
    
    private int sync() {
        if (this.gcrSector[this.sectorPos] == 255) {
            return 0;
        }
        this.forward();
        return 128;
    }
    
    private int readByte() {
        final int n = this.gcrSector[this.sectorPos];
        this.forward();
        return n;
    }
    
    private void forward() {
        ++this.sectorPos;
        if (this.sectorPos == 354) {
            this.sectorPos = 0;
            this.sector = (this.sector + 1) % this.currentTrackSize;
            this.readGCRSector(this.track, this.sector);
        }
        this.update(this, C1541Chips.SECTOR_UPDATE);
    }
    
    private void headOut() {
        if (this.hTrack > 2) {
            --this.hTrack;
        }
        else {
            ++this.headOutBeyond;
        }
        System.out.println("1541: Move head In to: " + this.hTrack);
        this.updateHTrack();
        this.update(this, C1541Chips.HEAD_MOVED);
    }
    
    private void headIn() {
        if (this.hTrack < 70) {
            ++this.hTrack;
        }
        System.out.println("1541: Move head Out to: " + this.hTrack);
        this.updateHTrack();
        this.update(this, C1541Chips.HEAD_MOVED);
    }
    
    private void updateHTrack() {
        if (this.track != this.hTrack >> 1) {
            this.track = this.hTrack >> 1;
            this.sector = 0;
            this.sectorPos = 0;
            this.currentTrackSize = C64Reader.getSectorCount(this.track);
            System.out.println("1541: New Track " + this.track + " reading: " + this.track + ", " + this.sector + " s/t: " + this.currentTrackSize);
            this.readGCRSector(this.track, this.sector);
        }
    }
    
    private long getGCR(final int n) {
        return C1541Chips.GCR[n >> 4] << 5 | C1541Chips.GCR[n & 0xF];
    }
    
    private char i2c(final int n) {
        if (n < 32) {
            return '.';
        }
        return (char)n;
    }
    
    private int makeGCR(final int[] array, int n, final int n2, final int n3, final int n4, final int n5) {
        final int n6 = n2 ^ n3 ^ n4 ^ n5;
        final long n7 = this.getGCR(n2) << 30 | this.getGCR(n3) << 20 | this.getGCR(n4) << 10 | this.getGCR(n5);
        long n8 = 32L;
        for (int i = 0; i < 5; ++i) {
            array[n++] = (int)(n7 >> (int)n8 & 0xFFL);
            n8 -= 8L;
        }
        return n6;
    }
    
    private void readGCRSector(final int currentTrack, final int currentSector) {
        this.currentTrack = currentTrack;
        this.currentSector = currentSector;
        final byte[] sector = this.reader.getSector(currentTrack, currentSector);
        int n = 0;
        this.gcrSector[n++] = 255;
        this.makeGCR(this.gcrSector, n, 8, currentSector ^ currentTrack ^ this.diskID1 ^ this.diskID2, currentSector, currentTrack);
        n += 5;
        this.makeGCR(this.gcrSector, n, this.diskID2, this.diskID1, 15, 15);
        n += 5;
        for (int i = 0; i < 9; ++i) {
            this.gcrSector[n++] = 85;
        }
        this.gcrSector[n++] = 255;
        int n2 = 0x7 ^ this.makeGCR(this.gcrSector, n, 7, sector[0] & 0xFF, sector[1] & 0xFF, sector[2] & 0xFF);
        n += 5;
        for (int j = 3; j < 255; j += 4) {
            n2 ^= this.makeGCR(this.gcrSector, n, sector[j] & 0xFF, sector[j + 1] & 0xFF, sector[j + 2] & 0xFF, sector[j + 3] & 0xFF);
            n += 5;
        }
        this.makeGCR(this.gcrSector, n, sector[255] & 0xFF, (n2 ^ (sector[255] & 0xFF)) & 0xFF, 0, 0);
        n += 5;
        for (int k = 0; k < 8; ++k) {
            this.gcrSector[n++] = 85;
        }
    }
    
    public static void main(final String[] array) {
        final String s = array[0];
        final int[] array2 = new int[s.length() * 2];
        int n = 0;
        int n2 = 0;
        int i = 0;
        for (int j = 0; j < s.length(); ++j) {
            final char c = (char)(s.charAt(j) & '\u00ff');
            for (n2 |= (C1541Chips.GCR[c >> 4] << 5 | C1541Chips.GCR[c & '\u000f']) << i, i += 10; i >= 8; i -= 8, n2 >>= 8) {
                array2[n++] = (n2 & 0xFF);
            }
        }
        System.out.println("GCR Data:");
        for (int k = 0; k < n; ++k) {
            if (k % 16 == 0) {
                System.out.println("");
            }
            System.out.print(Integer.toHexString(array2[k]) + " ");
        }
        int n3 = 0;
        int n4 = 0;
        System.out.println("Decoded: ");
        for (int l = 0; l < n; ++l) {
            n3 |= array2[l] << n4;
            n4 += 8;
            if (n4 >= 10) {
                final int n5 = n3 & 0x3FF;
                final int n6 = C1541Chips.GCR_REV[n5 >> 5] << 4 | C1541Chips.GCR_REV[n5 & 0x1F];
                n3 >>= 10;
                n4 -= 10;
                System.out.print((char)n6);
            }
        }
        System.out.println("");
    }
    
    static {
        LED_MOTOR = new Object();
        HEAD_MOVED = new Object();
        SECTOR_UPDATE = new Object();
        GCR = new int[] { 10, 11, 18, 19, 14, 15, 22, 23, 9, 25, 26, 27, 13, 29, 30, 21 };
        GCR_REV = new int[] { 255, 255, 255, 255, 255, 255, 255, 255, 255, 8, 0, 1, 255, 12, 4, 5, 255, 255, 2, 3, 255, 15, 6, 7, 255, 9, 10, 11, 255, 13, 14, 255 };
    }
}
