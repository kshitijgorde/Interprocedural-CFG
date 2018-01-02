// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.components;

public class PD765A
{
    private int ivSt0;
    private int ivSt1;
    private int ivSt2;
    private int[] ivStack;
    private int ivStackPointer;
    private int ivCommand;
    private int ivPhase;
    private PD765ASector ivSector;
    private int ivSectorPos;
    private int ivSectorCount;
    private boolean ivSK;
    private int ivHD;
    private int ivUS;
    private int ivC;
    private int ivH;
    private int ivR;
    private int ivN;
    private int ivSC;
    private int ivD;
    private boolean ivDebug;
    private PD765ADrive[] ivDrives;
    private static final int PHASE_COMMAND = 0;
    private static final int PHASE_EXECUTEREAD = 1;
    private static final int PHASE_EXECUTEWRITE = 2;
    private static final int PHASE_EXECUTEWRITEID = 3;
    private static final int PHASE_RESULT = 4;
    private static final int CMD_READDATA = 6;
    private static final int CMD_READDELETEDDATA = 12;
    private static final int CMD_WRITEDATA = 5;
    private static final int CMD_WRITEDELETEDDATA = 9;
    private static final int CMD_READDIAGNOSTIC = 2;
    private static final int CMD_READID = 10;
    private static final int CMD_WRITEID = 13;
    private static final int CMD_SCANEQUAL = 17;
    private static final int CMD_SCANLOWOREQUAL = 25;
    private static final int CMD_SCANHIGHOREQUAL = 29;
    private static final int CMD_RECALIBRATE = 7;
    private static final int CMD_SENSEINTERRUPTSTATUS = 8;
    private static final int CMD_SPECIFY = 3;
    private static final int CMD_SENSEDRIVESTATUS = 4;
    private static final int CMD_VERSION = 16;
    private static final int CMD_SEEK = 15;
    private static final int CMD_INVALID = 0;
    private static final int STATE_READ_COMMAND = 0;
    private static final int STATE_WRITE_RESULT = 1;
    private static final int STATE_READDATA = 2;
    private static final int[] ivCommandSizes;
    
    static {
        ivCommandSizes = new int[] { 0, 0, 9, 3, 2, 9, 9, 2, 1, 9, 2, 0, 9, 6, 0, 3, 1, 9, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 9, 0, 0 };
    }
    
    public PD765A() {
        this.ivDebug = true;
        this.ivStack = new int[13];
        this.ivStackPointer = 0;
        this.ivCommand = 0;
        this.ivPhase = 0;
        this.ivDrives = new PD765ADrive[4];
    }
    
    private void cmdInvalid() {
        this.popAll();
        this.ivSt0 = 128;
        this.ivPhase = 4;
        this.push(this.ivSt0);
    }
    
    private void cmdReadData() {
        this.readCmd9();
        if (this.findSector(false)) {
            this.ivSt1 = this.ivSector.getSt1();
            this.ivSt2 = this.ivSector.getSt2();
            this.ivPhase = 1;
        }
        else {
            this.ivPhase = 4;
        }
        this.writeReply7();
    }
    
    private void cmdReadDeletedData() {
        this.readCmd9();
        if (this.findSector(true)) {
            this.ivSt1 = this.ivSector.getSt1();
            this.ivSt2 = (this.ivSector.getSt2() ^ 0x40);
            this.ivPhase = 1;
        }
        else {
            this.ivPhase = 4;
        }
        this.writeReply7();
    }
    
    private void cmdReadDiagnostic() {
        this.readCmd9();
        if (this.ivDebug) {
            System.out.print("PD765 READ DIAGNOSTIC:        ");
            System.out.print("*** NOT SUPPORTED ***");
            System.out.println();
        }
        this.ivSt0 = 128;
        this.ivPhase = 4;
        this.push(this.ivSt0);
    }
    
    private void cmdReadId() {
        this.readCmd2();
        this.ivSK = false;
        this.ivSector = null;
        this.ivR = 0;
        while (this.ivR < 256 && this.ivSector == null) {
            this.findSector(false);
            ++this.ivR;
        }
        if (this.ivSector != null) {
            this.ivSt0 &= 0x7;
            this.ivSt1 = this.ivSector.getSt1();
            this.ivSt2 = this.ivSector.getSt2();
            this.ivC = this.ivSector.getC();
            this.ivH = this.ivSector.getH();
            this.ivR = this.ivSector.getR();
            this.ivN = this.ivSector.getN();
        }
        this.ivPhase = 4;
        this.writeReply7();
    }
    
    private void cmdRecalibrate() {
        this.readCmd2();
        if (this.ivDrives[this.ivUS] != null && this.ivDrives[this.ivUS].containsDisk()) {
            this.ivDrives[this.ivUS].setTrack(0);
            this.ivSt0 |= 0x20;
        }
        else {
            this.ivSt0 |= 0xD8;
        }
    }
    
    private void cmdScanEqual() {
        this.readCmd9();
        if (this.ivDebug) {
            System.out.print("PD765 SCAN EQUAL:             ");
            System.out.print("*** NOT SUPPORTED ***");
            System.out.println();
        }
        this.ivPhase = 4;
        this.push(this.ivSt0 = 128);
    }
    
    private void cmdScanHighOrEqual() {
        this.readCmd9();
        if (this.ivDebug) {
            System.out.print("PD765 SCAN HIGH OR EQUAL:     ");
            System.out.print("*** NOT SUPPORTED ***");
            System.out.println();
        }
        this.ivPhase = 4;
        this.push(this.ivSt0 = 128);
    }
    
    private void cmdScanLowOrEqual() {
        this.readCmd9();
        if (this.ivDebug) {
            System.out.print("PD765 SCAN LOW OR EQUAL:      ");
            System.out.print("*** NOT SUPPORTED ***");
            System.out.println();
        }
        this.ivPhase = 4;
        this.push(this.ivSt0 = 128);
    }
    
    private void cmdSeek() {
        final int ncn = this.peek(2);
        this.readCmd2();
        if (this.ivDrives[this.ivUS] != null) {
            this.ivDrives[this.ivUS].setTrack(ncn);
            this.ivSt0 |= 0x20;
        }
        else {
            this.ivSt0 |= 0x30;
        }
    }
    
    private void cmdSenseDriveStatus() {
        this.readCmd2();
        if (this.ivDrives[this.ivUS] != null && this.ivDrives[this.ivUS].containsDisk()) {
            this.push(this.ivSt0 | (this.ivDrives[this.ivUS].getWriteProtect() ? 96 : 32));
        }
        else {
            this.push(this.ivSt0 | 0x80);
        }
        this.ivPhase = 4;
    }
    
    private void cmdSenseInterruptStatus() {
        this.pop();
        this.ivSt0 &= 0x3F;
        this.ivPhase = 4;
        if (this.ivDrives[this.ivUS] != null) {
            this.push(this.ivDrives[this.ivUS].getTrack());
            this.push(this.ivSt0);
        }
        else {
            this.push(0);
            this.push(this.ivSt0 | 0x8);
        }
        this.ivSt0 &= 0xD0;
    }
    
    private void cmdSpecify() {
        final int codes2 = this.pop();
        final int codes3 = this.pop();
        final int cmd = this.pop();
    }
    
    private void cmdVersion() {
        final int cmd = this.pop();
        this.ivPhase = 4;
        this.push(this.ivSt0 = 128);
    }
    
    private void cmdWriteData() {
        this.readCmd9();
        if (this.findSector(false) & this.isWriteEnabled()) {
            this.ivSector.setSt1(0);
            this.ivSector.setSt2(0);
            this.ivPhase = 2;
        }
        else {
            this.ivPhase = 4;
        }
        this.writeReply7();
    }
    
    private void cmdWriteDeletedData() {
        this.readCmd9();
        if (this.findSector(false) & this.isWriteEnabled()) {
            this.ivSector.setSt1(0);
            this.ivSector.setSt2(64);
            this.ivPhase = 2;
        }
        else {
            this.ivPhase = 4;
        }
        this.writeReply7();
    }
    
    private void cmdWriteId() {
        if (this.ivPhase == 0) {
            this.ivN = this.peek(2);
            this.ivSC = this.peek(3);
            this.ivD = this.peek(5);
            this.readCmd2();
            if (this.ivDrives[this.ivUS] != null) {
                for (int r = 0; r < 256; ++r) {
                    this.ivDrives[this.ivUS].deleteSector(this.ivHD, r);
                }
                this.ivSectorCount = 0;
                this.ivPhase = 3;
            }
        }
        else if (this.ivPhase == 3) {
            final int n = this.pop();
            final int r2 = this.pop();
            final int h = this.pop();
            final int c = this.pop();
            if (this.ivDrives[this.ivUS] != null) {
                if (this.isWriteEnabled()) {
                    final PD765ASector s = new PD765ASector();
                    s.setC(c);
                    s.setH(h);
                    s.setR(r2);
                    s.setN(n);
                    s.setSt1(0);
                    s.setSt2(0);
                    s.setData(new byte[128 << this.ivN]);
                    for (int i = 0; i < s.getData().length; ++i) {
                        s.getData()[i] = (byte)this.ivD;
                    }
                    this.ivDrives[this.ivUS].writeSector(this.ivHD, this.ivSectorCount, s);
                    if (++this.ivSectorCount >= 9) {
                        this.ivSt1 |= 0x80;
                        this.ivPhase = 4;
                    }
                }
                else {
                    this.ivSt0 |= 0x40;
                    this.ivPhase = 4;
                }
            }
            else {
                this.ivSt0 |= 0x48;
                this.ivPhase = 4;
            }
            if (this.ivPhase == 4) {
                this.writeReply7();
            }
        }
    }
    
    private boolean findSector(final boolean pDeleted) {
        final int deleted = pDeleted ? 32 : 0;
        final int sk = this.ivSK ? 32 : 0;
        this.ivSector = null;
        this.ivSectorPos = 0;
        if (this.ivDrives[this.ivUS] != null && this.ivDrives[this.ivUS].containsDisk()) {
            for (int index = 0; index < 256; ++index) {
                this.ivSector = this.ivDrives[this.ivUS].readSector(this.ivHD, index);
                if (this.ivSector != null && this.ivSector.getR() == this.ivR && ((this.ivSector.getSt2() ^ deleted) & sk) == 0x0) {
                    return true;
                }
            }
            this.ivSt0 |= 0x40;
            this.ivSt1 |= 0x5;
        }
        else {
            this.ivSt0 |= 0x58;
        }
        return false;
    }
    
    public PD765ADrive getDrive(final int pIndex) {
        return this.ivDrives[pIndex];
    }
    
    public PD765AStatus getStatus() {
        final PD765AStatus result = new PD765AStatus();
        result.ivSt0 = this.ivSt0;
        result.ivSt1 = this.ivSt1;
        result.ivSt2 = this.ivSt2;
        result.ivStack = this.ivStack;
        result.ivStackPointer = this.ivStackPointer;
        result.ivCommand = this.ivCommand;
        result.ivPhase = this.ivPhase;
        result.ivSector = this.ivSector;
        result.ivSectorPos = this.ivSectorPos;
        result.ivSectorCount = this.ivSectorCount;
        result.ivSK = this.ivSK;
        result.ivHD = this.ivHD;
        result.ivUS = this.ivUS;
        result.ivC = this.ivC;
        result.ivH = this.ivH;
        result.ivR = this.ivR;
        result.ivN = this.ivN;
        result.ivSC = this.ivSC;
        result.ivD = this.ivD;
        return result;
    }
    
    public void init() {
        this.reset();
    }
    
    private boolean isWriteEnabled() {
        if (this.ivDrives[this.ivUS] != null && this.ivDrives[this.ivUS].getWriteProtect()) {
            this.ivSt1 |= 0x2;
            return false;
        }
        return true;
    }
    
    private int peek(final int pStackPos) {
        return this.ivStack[pStackPos];
    }
    
    private int pop() {
        final int[] ivStack = this.ivStack;
        final int ivStackPointer = this.ivStackPointer - 1;
        this.ivStackPointer = ivStackPointer;
        return ivStack[ivStackPointer];
    }
    
    private void popAll() {
        this.ivStackPointer = 0;
    }
    
    private void push(final int pValue) {
        this.ivStack[this.ivStackPointer++] = pValue;
    }
    
    private void readCmd2() {
        this.ivSK = ((this.peek(0) & 0x20) == 0x20);
        this.ivHD = (this.peek(1) & 0x4) >> 2;
        this.ivUS = (this.peek(1) & 0x3);
        this.ivSt0 = (this.peek(1) & 0x7);
        this.ivSt1 = 0;
        this.ivSt2 = 0;
        this.popAll();
    }
    
    private void readCmd9() {
        this.ivSK = ((this.peek(0) & 0x20) == 0x20);
        this.ivHD = (this.peek(1) & 0x4) >> 2;
        this.ivUS = (this.peek(1) & 0x3);
        this.ivC = this.peek(2);
        this.ivH = this.peek(3);
        this.ivR = this.peek(4);
        this.ivN = this.peek(5);
        this.ivSt0 = (this.peek(1) & 0x7);
        this.ivSt1 = 0;
        this.ivSt2 = 0;
        this.popAll();
    }
    
    public int readDataRegister() {
        if (this.ivPhase == 4) {
            if (this.ivStackPointer == 0) {
                return 128;
            }
            final int result = this.pop();
            if (this.ivStackPointer == 0) {
                this.ivPhase = 0;
            }
            return result;
        }
        else {
            if (this.ivPhase == 1) {
                final int result = this.ivSector.getData()[this.ivSectorPos++] & 0xFF;
                if (this.ivSectorPos == this.ivSector.getData().length) {
                    this.ivPhase = 4;
                }
                return result;
            }
            return 128;
        }
    }
    
    public int readMainStatusRegister() {
        switch (this.ivPhase) {
            case 0: {
                return 128;
            }
            case 1: {
                return 224;
            }
            case 3: {
                return 160;
            }
            case 2: {
                return 160;
            }
            case 4: {
                return 192;
            }
            default: {
                throw new RuntimeException("Bug in ivPhase handling");
            }
        }
    }
    
    public void reset() {
        this.ivStackPointer = 0;
        this.ivCommand = 0;
        this.ivPhase = 0;
    }
    
    public void setDrive(final int pIndex, final PD765ADrive pDrive) {
        this.ivDrives[pIndex] = pDrive;
    }
    
    public void setStatus(final PD765AStatus pStatus) {
        this.ivSt0 = pStatus.ivSt0;
        this.ivSt1 = pStatus.ivSt1;
        this.ivSt2 = pStatus.ivSt2;
        this.ivStack = pStatus.ivStack;
        this.ivStackPointer = pStatus.ivStackPointer;
        this.ivCommand = pStatus.ivCommand;
        this.ivPhase = pStatus.ivPhase;
        this.ivSector = pStatus.ivSector;
        this.ivSectorPos = pStatus.ivSectorPos;
        this.ivSectorCount = pStatus.ivSectorCount;
        this.ivSK = pStatus.ivSK;
        this.ivHD = pStatus.ivHD;
        this.ivUS = pStatus.ivUS;
        this.ivC = pStatus.ivC;
        this.ivH = pStatus.ivH;
        this.ivR = pStatus.ivR;
        this.ivN = pStatus.ivN;
        this.ivSC = pStatus.ivSC;
        this.ivD = pStatus.ivD;
    }
    
    public void writeDataRegister(final int pData) {
        if (this.ivPhase == 0) {
            if (this.ivStackPointer == 0) {
                if ((this.ivSt0 & 0x20) != 0x0 != (pData == 8)) {
                    this.ivCommand = 0;
                }
                else {
                    this.ivCommand = (pData & 0x1F);
                }
            }
            this.push(pData);
            if (this.ivStackPointer > PD765A.ivCommandSizes[this.ivCommand]) {
                this.ivCommand = 0;
            }
            if (this.ivStackPointer >= PD765A.ivCommandSizes[this.ivCommand]) {
                switch (this.ivCommand) {
                    case 13: {
                        this.cmdWriteId();
                        break;
                    }
                    case 5: {
                        this.cmdWriteData();
                        break;
                    }
                    case 9: {
                        this.cmdWriteDeletedData();
                        break;
                    }
                    case 10: {
                        this.cmdReadId();
                        break;
                    }
                    case 6: {
                        this.cmdReadData();
                        break;
                    }
                    case 12: {
                        this.cmdReadDeletedData();
                        break;
                    }
                    case 2: {
                        this.cmdReadDiagnostic();
                        break;
                    }
                    case 17: {
                        this.cmdScanEqual();
                        break;
                    }
                    case 25: {
                        this.cmdScanLowOrEqual();
                        break;
                    }
                    case 29: {
                        this.cmdScanHighOrEqual();
                        break;
                    }
                    case 7: {
                        this.cmdRecalibrate();
                        break;
                    }
                    case 15: {
                        this.cmdSeek();
                        break;
                    }
                    case 3: {
                        this.cmdSpecify();
                        break;
                    }
                    case 16: {
                        this.cmdVersion();
                        break;
                    }
                    case 4: {
                        this.cmdSenseDriveStatus();
                        break;
                    }
                    case 8: {
                        this.cmdSenseInterruptStatus();
                        break;
                    }
                    case 0: {
                        this.cmdInvalid();
                        break;
                    }
                    default: {
                        this.cmdInvalid();
                        break;
                    }
                }
            }
        }
        else if (this.ivPhase == 2) {
            this.ivSector.getData()[this.ivSectorPos++] = (byte)pData;
            if (this.ivSectorPos == this.ivSector.getData().length) {
                this.ivPhase = 4;
            }
        }
        else if (this.ivPhase == 3) {
            this.push(pData);
            if (this.ivStackPointer >= 4) {
                this.cmdWriteId();
            }
        }
    }
    
    private void writeReply7() {
        this.push(this.ivN);
        this.push(this.ivR);
        this.push(this.ivH);
        this.push(this.ivC);
        this.push(this.ivSt2);
        this.push(this.ivSt1);
        this.push(this.ivSt0);
    }
}
