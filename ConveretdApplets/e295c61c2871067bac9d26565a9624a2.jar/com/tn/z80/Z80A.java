// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.z80;

import com.tn.util.Array;

public final class Z80A
{
    private static final String copyright = "© Copyright Troels Noergaard, 2001, 2008. All Rights Reserved.";
    private int ivPC;
    private int ivB;
    private int ivC;
    private int ivD;
    private int ivE;
    private int ivH;
    private int ivL;
    private int ivA;
    private int ivF;
    private int ivSP;
    private int ivIX;
    private int ivIY;
    private int ivB2;
    private int ivC2;
    private int ivD2;
    private int ivE2;
    private int ivH2;
    private int ivL2;
    private int ivA2;
    private int ivF2;
    private int ivI;
    private int ivR;
    private int ivR7;
    private boolean ivIFF1;
    private boolean ivIFF2;
    private int ivIntMode;
    private int ivClk;
    private long ivHaltClk;
    private long ivClkOffset;
    private long addedCycles;
    private Z80IoHandler2 ivIO;
    private int[] ivFlagsAndOrXor8;
    private int[] ivFlagsInc8;
    private int[] ivFlagsDec8;
    
    public Z80A(final Z80IoHandler2 pIoHandler) {
        this.ivIO = pIoHandler;
        this.ivFlagsAndOrXor8 = new int[256];
        for (int i = 0; i < 256; ++i) {
            this.setF_C(false);
            this.setF_N(false);
            this.setF_PV(this.parity8(i));
            this.setF_3((i & 0x8) != 0x0);
            this.setF_H(true);
            this.setF_5((i & 0x20) != 0x0);
            this.setF_Z(i == 0);
            this.setF_S(i >= 128);
            this.ivFlagsAndOrXor8[i] = this.getF();
        }
        this.ivFlagsInc8 = new int[256];
        for (int i = 0; i < 256; ++i) {
            this.setF_C(false);
            this.setF_N(false);
            this.setF_PV(i == 128);
            this.setF_3((i & 0x8) != 0x0);
            this.setF_H((i & 0xF) == 0x0);
            this.setF_5((i & 0x20) != 0x0);
            this.setF_Z(i == 0);
            this.setF_S(i >= 128);
            this.ivFlagsInc8[i] = this.getF();
        }
        this.ivFlagsDec8 = new int[256];
        for (int i = 0; i < 256; ++i) {
            this.setF_C(false);
            this.setF_N(true);
            this.setF_PV(i == 127);
            this.setF_3((i & 0x8) != 0x0);
            this.setF_H((i & 0xF) == 0xF);
            this.setF_5((i & 0x20) != 0x0);
            this.setF_Z(i == 0);
            this.setF_S(i >= 128);
            this.ivFlagsDec8[i] = this.getF();
        }
    }
    
    private int adc16(final int src1, final int src2) {
        final int res = src1 + src2 + (this.getF_C() ? 1 : 0);
        this.setF_C(res > 65535);
        this.setF_N(false);
        this.setF_PV(((src1 ^ src2 ^ 0x8000) & (src1 ^ res) & 0x8000) != 0x0);
        this.setF_3((res & 0x800) != 0x0);
        this.setF_H(((src1 ^ src2 ^ res) & 0x1000) != 0x0);
        this.setF_5((res & 0x2000) != 0x0);
        this.setF_Z((res & 0xFFFF) == 0x0);
        this.setF_S((res & 0x8000) != 0x0);
        return res & 0xFFFF;
    }
    
    private int adc8(final int src1, final int src2) {
        final int res = src1 + src2 + (this.getF_C() ? 1 : 0);
        this.setF_C(res > 255);
        this.setF_N(false);
        this.setF_PV(((src1 ^ src2 ^ 0x80) & (src1 ^ res) & 0x80) != 0x0);
        this.setF_3((res & 0x8) != 0x0);
        this.setF_H(((src1 ^ src2 ^ res) & 0x10) != 0x0);
        this.setF_5((res & 0x20) != 0x0);
        this.setF_Z((res & 0xFF) == 0x0);
        this.setF_S((res & 0x80) != 0x0);
        return res & 0xFF;
    }
    
    private int add16(final int src1, final int src2) {
        final int res = src1 + src2;
        this.setF_C(res > 65535);
        this.setF_N(false);
        this.setF_3((res & 0x800) != 0x0);
        this.setF_H(((src1 ^ src2 ^ res) & 0x1000) != 0x0);
        this.setF_5((res & 0x2000) != 0x0);
        return res & 0xFFFF;
    }
    
    private int add8(final int src1, final int src2) {
        final int res = src1 + src2;
        this.setF_C(res > 255);
        this.setF_N(false);
        this.setF_PV(((src1 ^ src2 ^ 0x80) & (src1 ^ res) & 0x80) != 0x0);
        this.setF_3((res & 0x8) != 0x0);
        this.setF_H(((src1 ^ src2 ^ res) & 0x10) != 0x0);
        this.setF_5((res & 0x20) != 0x0);
        this.setF_Z((res & 0xFF) == 0x0);
        this.setF_S((res & 0x80) != 0x0);
        return res & 0xFF;
    }
    
    public void adjustClock(final int pNumberOfClockCycles) {
        this.ivHaltClk += pNumberOfClockCycles;
        this.ivClk -= pNumberOfClockCycles;
    }
    
    private int and8(final int src1, final int src2) {
        final int res = src1 & src2;
        this.ivF = this.ivFlagsAndOrXor8[res];
        return res;
    }
    
    private void bit(final int bit, final int src) {
        final int res = src & 1 << bit;
        this.setF_N(false);
        this.setF_PV(this.parity8(res));
        this.setF_3((res & 0x8) != 0x0);
        this.setF_H(true);
        this.setF_5((res & 0x20) != 0x0);
        this.setF_Z(res == 0);
        this.setF_S(res >= 128);
    }
    
    private void call(final boolean pCC) {
        final int pc = this.get_NN();
        if (pCC) {
            this.readMem(this.ivPC - 1 & 0xFFFF, 1);
            this.push(this.getPC());
            this.setPC(pc);
        }
    }
    
    private void cp8(final int src1, final int src2) {
        final int res = src1 - src2;
        this.setF_C(res < 0);
        this.setF_N(true);
        this.setF_PV(((src1 ^ src2) & (src1 ^ res) & 0x80) != 0x0);
        this.setF_3((res & 0x8) != 0x0);
        this.setF_H(((src1 ^ src2 ^ res) & 0x10) != 0x0);
        this.setF_5((res & 0x20) != 0x0);
        this.setF_Z((res & 0xFF) == 0x0);
        this.setF_S((res & 0x80) != 0x0);
    }
    
    private void cpd() {
        final int src1 = this.getA();
        final int src2 = this.get_IHLI();
        final int res = src1 - src2;
        this.readMem(this.getHL(), 5);
        this.setBC(this.dec16(this.getBC()));
        this.setHL(this.dec16(this.getHL()));
        this.setF_N(true);
        this.setF_PV(this.getBC() != 0);
        this.setF_3((res & 0x8) != 0x0);
        this.setF_H(((src1 ^ src2 ^ res) & 0x10) != 0x0);
        this.setF_5((res & 0x20) != 0x0);
        this.setF_Z((res & 0xFF) == 0x0);
        this.setF_S((res & 0x80) != 0x0);
    }
    
    private void cpi() {
        final int src1 = this.getA();
        final int src2 = this.get_IHLI();
        final int res = src1 - src2;
        this.readMem(this.getHL(), 5);
        this.setBC(this.dec16(this.getBC()));
        this.setHL(this.inc16(this.getHL()));
        this.setF_N(true);
        this.setF_PV(this.getBC() != 0);
        this.setF_3((res & 0x8) != 0x0);
        this.setF_H(((src1 ^ src2 ^ res) & 0x10) != 0x0);
        this.setF_5((res & 0x20) != 0x0);
        this.setF_Z((res & 0xFF) == 0x0);
        this.setF_S((res & 0x80) != 0x0);
    }
    
    private void daa() {
        int modifier = 0;
        if ((this.getA() & 0xF) > 9 || this.getF_H()) {
            modifier = 6;
            this.setF_H(true);
        }
        if (this.getA() > 153 || this.getF_C()) {
            modifier += 96;
            this.setF_C(true);
        }
        if (this.getF_N()) {
            this.setA(this.getA() - modifier & 0xFF);
        }
        else {
            this.setA(this.getA() + modifier & 0xFF);
        }
        this.setF_Z(this.getA() == 0);
        this.setF_S(this.getA() > 127);
    }
    
    private int dec16(final int src) {
        return src - 1 & 0xFFFF;
    }
    
    private int dec8(final int src) {
        final int res = src - 1 & 0xFF;
        this.ivF = (this.ivFlagsDec8[res] | (this.ivF & 0x1));
        return res;
    }
    
    private void decodeCB(final int pOpcode) {
        ++this.ivR;
        int r = -1;
        switch (pOpcode & 0x7) {
            case 0: {
                r = this.ivB;
                break;
            }
            case 1: {
                r = this.ivC;
                break;
            }
            case 2: {
                r = this.ivD;
                break;
            }
            case 3: {
                r = this.ivE;
                break;
            }
            case 4: {
                r = this.ivH;
                break;
            }
            case 5: {
                r = this.ivL;
                break;
            }
            case 6: {
                r = this.ivIO.readMem(this.getHL());
                this.ivClk -= 3;
                this.readMem(this.getHL(), 1);
                break;
            }
            case 7: {
                r = this.ivA;
                break;
            }
        }
        switch (pOpcode & 0xC0) {
            case 0: {
                switch (pOpcode & 0x38) {
                    case 0: {
                        r = this.rlc8(r);
                        break;
                    }
                    case 8: {
                        r = this.rrc8(r);
                        break;
                    }
                    case 16: {
                        r = this.rl8(r);
                        break;
                    }
                    case 24: {
                        r = this.rr8(r);
                        break;
                    }
                    case 32: {
                        r = this.sla8(r);
                        break;
                    }
                    case 40: {
                        r = this.sra8(r);
                        break;
                    }
                    case 48: {
                        r = this.sll8(r);
                        break;
                    }
                    case 56: {
                        r = this.srl8(r);
                        break;
                    }
                }
                break;
            }
            case 64: {
                this.bit(pOpcode >> 3 & 0x7, r);
                return;
            }
            case 128: {
                r = this.res(pOpcode >> 3 & 0x7, r);
                break;
            }
            case 192: {
                r = this.set(pOpcode >> 3 & 0x7, r);
                break;
            }
        }
        switch (pOpcode & 0x7) {
            case 0: {
                this.ivB = r;
                break;
            }
            case 1: {
                this.ivC = r;
                break;
            }
            case 2: {
                this.ivD = r;
                break;
            }
            case 3: {
                this.ivE = r;
                break;
            }
            case 4: {
                this.ivH = r;
                break;
            }
            case 5: {
                this.ivL = r;
                break;
            }
            case 6: {
                this.ivIO.writeMem(this.getHL(), r);
                this.ivClk -= 3;
                break;
            }
            case 7: {
                this.ivA = r;
                break;
            }
        }
    }
    
    private void decodeED(final int pOpcode) {
        ++this.ivR;
        switch (pOpcode) {
            case 64: {
                this.setB(this.inC());
                break;
            }
            case 72: {
                this.setC(this.inC());
                break;
            }
            case 80: {
                this.setD(this.inC());
                break;
            }
            case 88: {
                this.setE(this.inC());
                break;
            }
            case 96: {
                this.setH(this.inC());
                break;
            }
            case 104: {
                this.setL(this.inC());
                break;
            }
            case 112: {
                this.inC();
                break;
            }
            case 120: {
                this.setA(this.inC());
                break;
            }
            case 65: {
                this.set_IO_C(this.getB());
                break;
            }
            case 73: {
                this.set_IO_C(this.getC());
                break;
            }
            case 81: {
                this.set_IO_C(this.getD());
                break;
            }
            case 89: {
                this.set_IO_C(this.getE());
                break;
            }
            case 97: {
                this.set_IO_C(this.getH());
                break;
            }
            case 105: {
                this.set_IO_C(this.getL());
                break;
            }
            case 113: {
                this.set_IO_C(0);
                break;
            }
            case 121: {
                this.set_IO_C(this.getA());
                break;
            }
            case 66: {
                this.setHL(this.sbc16(this.getHL(), this.getBC()));
                this.ivClk -= 7;
                break;
            }
            case 82: {
                this.setHL(this.sbc16(this.getHL(), this.getDE()));
                this.ivClk -= 7;
                break;
            }
            case 98: {
                this.setHL(this.sbc16(this.getHL(), this.getHL()));
                this.ivClk -= 7;
                break;
            }
            case 114: {
                this.setHL(this.sbc16(this.getHL(), this.getSP()));
                this.ivClk -= 7;
                break;
            }
            case 67: {
                this.set_INNI_w(this.getBC());
                break;
            }
            case 83: {
                this.set_INNI_w(this.getDE());
                break;
            }
            case 99: {
                this.set_INNI_w(this.getHL());
                break;
            }
            case 115: {
                this.set_INNI_w(this.getSP());
                break;
            }
            case 74: {
                this.setHL(this.adc16(this.getHL(), this.getBC()));
                this.ivClk -= 7;
                break;
            }
            case 90: {
                this.setHL(this.adc16(this.getHL(), this.getDE()));
                this.ivClk -= 7;
                break;
            }
            case 106: {
                this.setHL(this.adc16(this.getHL(), this.getHL()));
                this.ivClk -= 7;
                break;
            }
            case 122: {
                this.setHL(this.adc16(this.getHL(), this.getSP()));
                this.ivClk -= 7;
                break;
            }
            case 75: {
                this.setBC(this.get_IADRI_w(this.get_NN()));
                break;
            }
            case 91: {
                this.setDE(this.get_IADRI_w(this.get_NN()));
                break;
            }
            case 107: {
                this.setHL(this.get_IADRI_w(this.get_NN()));
                break;
            }
            case 123: {
                this.setSP(this.get_IADRI_w(this.get_NN()));
                break;
            }
            case 71: {
                this.setI(this.getA());
                --this.ivClk;
                break;
            }
            case 79: {
                this.setR(this.getA());
                --this.ivClk;
                break;
            }
            case 87: {
                final int res = this.getI();
                this.setF_N(false);
                this.setF_PV(this.getIFF2());
                this.setF_3((res & 0x8) != 0x0);
                this.setF_H(false);
                this.setF_5((res & 0x20) != 0x0);
                this.setF_Z(res == 0);
                this.setF_S(res >= 128);
                this.setA(res);
                --this.ivClk;
                break;
            }
            case 95: {
                final int res = this.getR();
                this.setF_N(false);
                this.setF_PV(this.getIFF2());
                this.setF_3((res & 0x8) != 0x0);
                this.setF_H(false);
                this.setF_5((res & 0x20) != 0x0);
                this.setF_Z(res == 0);
                this.setF_S(res >= 128);
                this.setA(res);
                --this.ivClk;
                break;
            }
            case 103: {
                final int temp = this.get_IHLI();
                this.readMem(this.getHL(), 4);
                this.set_IHLI((this.getA() << 4 & 0xF0) | temp >> 4);
                final int res = (this.getA() & 0xF0) | (temp & 0xF);
                this.setF_N(false);
                this.setF_PV(this.parity8(res));
                this.setF_3((res & 0x8) != 0x0);
                this.setF_H(false);
                this.setF_5((res & 0x20) != 0x0);
                this.setF_Z(res == 0);
                this.setF_S(res >= 128);
                this.setA(res);
                break;
            }
            case 111: {
                final int temp = this.get_IHLI();
                this.readMem(this.getHL(), 4);
                this.set_IHLI((temp << 4 & 0xF0) | (this.getA() & 0xF));
                final int res = (this.getA() & 0xF0) | (temp >> 4 & 0xF);
                this.setF_N(false);
                this.setF_PV(this.parity8(res));
                this.setF_3((res & 0x8) != 0x0);
                this.setF_H(false);
                this.setF_5((res & 0x20) != 0x0);
                this.setF_Z(res == 0);
                this.setF_S(res >= 128);
                this.setA(res);
                break;
            }
            case 160: {
                this.ldi();
                break;
            }
            case 168: {
                this.ldd();
                break;
            }
            case 176: {
                this.ldi();
                if (this.getF_PV()) {
                    this.setPC(this.getPC() - 2 & 0xFFFF);
                    this.readMem(this.getDE() - 1 & 0xFFFF, 5);
                    break;
                }
                break;
            }
            case 184: {
                this.ldd();
                if (this.getF_PV()) {
                    this.setPC(this.getPC() - 2 & 0xFFFF);
                    this.readMem(this.getDE() + 1 & 0xFFFF, 5);
                    break;
                }
                break;
            }
            case 161: {
                this.cpi();
                break;
            }
            case 169: {
                this.cpd();
                break;
            }
            case 177: {
                this.cpi();
                if (this.getF_PV() && !this.getF_Z()) {
                    this.setPC(this.getPC() - 2 & 0xFFFF);
                    this.readMem(this.getHL() - 1 & 0xFFFF, 5);
                    break;
                }
                break;
            }
            case 185: {
                this.cpd();
                if (this.getF_PV() && !this.getF_Z()) {
                    this.setPC(this.getPC() - 2 & 0xFFFF);
                    this.readMem(this.getHL() + 1 & 0xFFFF, 5);
                    break;
                }
                break;
            }
            case 162: {
                this.ini();
                break;
            }
            case 170: {
                this.ind();
                break;
            }
            case 178: {
                this.ini();
                if (!this.getF_Z()) {
                    this.setPC(this.getPC() - 2 & 0xFFFF);
                    this.readMem(this.getHL() - 1 & 0xFFFF, 5);
                    break;
                }
                break;
            }
            case 186: {
                this.ind();
                if (!this.getF_Z()) {
                    this.setPC(this.getPC() - 2 & 0xFFFF);
                    this.readMem(this.getHL() + 1 & 0xFFFF, 5);
                    break;
                }
                break;
            }
            case 163: {
                this.outi();
                break;
            }
            case 171: {
                this.outd();
                break;
            }
            case 179: {
                this.outi();
                if (!this.getF_Z()) {
                    this.setPC(this.getPC() - 2 & 0xFFFF);
                    this.readMem(this.getHL() - 1 & 0xFFFF, 5);
                    break;
                }
                break;
            }
            case 187: {
                this.outd();
                if (!this.getF_Z()) {
                    this.setPC(this.getPC() - 2 & 0xFFFF);
                    this.readMem(this.getHL() + 1 & 0xFFFF, 5);
                    break;
                }
                break;
            }
            case 68:
            case 76:
            case 84:
            case 92:
            case 100:
            case 108:
            case 116:
            case 124: {
                this.setA(this.sub8(0, this.getA()));
                break;
            }
            case 69:
            case 77:
            case 85:
            case 93:
            case 101:
            case 109:
            case 117:
            case 125: {
                this.setIFF1(this.getIFF2());
                this.ret();
                break;
            }
            case 70:
            case 78:
            case 102:
            case 110: {
                this.setIntMode(0);
                break;
            }
            case 86:
            case 118: {
                this.setIntMode(1);
                break;
            }
            case 94:
            case 126: {
                this.setIntMode(2);
                break;
            }
        }
    }
    
    public void decodeInstruction(final int pNumberOfClockCycles) {
        this.ivClk += pNumberOfClockCycles;
        this.ivClkOffset += pNumberOfClockCycles;
        this.ivHaltClk = this.getClock() + pNumberOfClockCycles + 99L;
        while (this.ivClk > 0) {
            final int opcode = this.ivIO.readOpcode(this.ivPC++);
            this.ivPC &= 0xFFFF;
            ++this.ivR;
            this.ivClk -= 4;
            switch (opcode) {
                case 192: {
                    --this.ivClk;
                    if (!this.getF_Z()) {
                        this.ret();
                        continue;
                    }
                    continue;
                }
                case 208: {
                    --this.ivClk;
                    if (!this.getF_C()) {
                        this.ret();
                        continue;
                    }
                    continue;
                }
                case 224: {
                    --this.ivClk;
                    if (!this.getF_PV()) {
                        this.ret();
                        continue;
                    }
                    continue;
                }
                case 240: {
                    --this.ivClk;
                    if (!this.getF_S()) {
                        this.ret();
                        continue;
                    }
                    continue;
                }
                case 200: {
                    --this.ivClk;
                    if (this.getF_Z()) {
                        this.ret();
                        continue;
                    }
                    continue;
                }
                case 216: {
                    --this.ivClk;
                    if (this.getF_C()) {
                        this.ret();
                        continue;
                    }
                    continue;
                }
                case 232: {
                    --this.ivClk;
                    if (this.getF_PV()) {
                        this.ret();
                        continue;
                    }
                    continue;
                }
                case 248: {
                    --this.ivClk;
                    if (this.getF_S()) {
                        this.ret();
                        continue;
                    }
                    continue;
                }
                default: {
                    continue;
                }
                case 0: {
                    continue;
                }
                case 16: {
                    this.ivB = (this.ivB - 1 & 0xFF);
                    --this.ivClk;
                    this.jr(this.ivB != 0);
                    continue;
                }
                case 32: {
                    this.jr(!this.getF_Z());
                    continue;
                }
                case 48: {
                    this.jr(!this.getF_C());
                    continue;
                }
                case 1: {
                    this.setBC(this.get_NN());
                    continue;
                }
                case 17: {
                    this.setDE(this.get_NN());
                    continue;
                }
                case 33: {
                    this.setHL(this.get_NN());
                    continue;
                }
                case 49: {
                    this.setSP(this.get_NN());
                    continue;
                }
                case 2: {
                    this.set_IBCI(this.ivA);
                    continue;
                }
                case 18: {
                    this.set_IDEI(this.ivA);
                    continue;
                }
                case 34: {
                    this.set_INNI_w(this.getHL());
                    continue;
                }
                case 50: {
                    this.set_INNI(this.ivA);
                    continue;
                }
                case 3: {
                    this.setBC(this.inc16(this.getBC()));
                    this.ivClk -= 2;
                    continue;
                }
                case 19: {
                    this.setDE(this.inc16(this.getDE()));
                    this.ivClk -= 2;
                    continue;
                }
                case 35: {
                    this.setHL(this.inc16(this.getHL()));
                    this.ivClk -= 2;
                    continue;
                }
                case 51: {
                    this.setSP(this.inc16(this.getSP()));
                    this.ivClk -= 2;
                    continue;
                }
                case 4: {
                    this.ivB = this.inc8(this.ivB);
                    continue;
                }
                case 20: {
                    this.ivD = this.inc8(this.ivD);
                    continue;
                }
                case 36: {
                    this.ivH = this.inc8(this.ivH);
                    continue;
                }
                case 52: {
                    final int temp = this.inc8(this.get_IHLI());
                    --this.ivClk;
                    this.set_IHLI(temp);
                    continue;
                }
                case 5: {
                    this.ivB = this.dec8(this.ivB);
                    continue;
                }
                case 21: {
                    this.ivD = this.dec8(this.ivD);
                    continue;
                }
                case 37: {
                    this.ivH = this.dec8(this.ivH);
                    continue;
                }
                case 53: {
                    final int temp = this.dec8(this.get_IHLI());
                    --this.ivClk;
                    this.set_IHLI(temp);
                    continue;
                }
                case 6: {
                    this.ivB = this.get_N();
                    continue;
                }
                case 22: {
                    this.ivD = this.get_N();
                    continue;
                }
                case 38: {
                    this.ivH = this.get_N();
                    continue;
                }
                case 54: {
                    this.set_IHLI(this.get_N());
                    continue;
                }
                case 7: {
                    this.rlca();
                    continue;
                }
                case 23: {
                    this.rla();
                    continue;
                }
                case 39: {
                    this.daa();
                    continue;
                }
                case 55: {
                    this.setF_C(true);
                    this.setF_N(false);
                    this.setF_H(false);
                    continue;
                }
                case 8: {
                    final int temp = this.getAF2();
                    this.setAF2(this.getAF());
                    this.setAF(temp);
                    continue;
                }
                case 24: {
                    this.jr(true);
                    continue;
                }
                case 40: {
                    this.jr(this.getF_Z());
                    continue;
                }
                case 56: {
                    this.jr(this.getF_C());
                    continue;
                }
                case 9: {
                    this.setHL(this.add16(this.getHL(), this.getBC()));
                    this.ivClk -= 7;
                    continue;
                }
                case 25: {
                    this.setHL(this.add16(this.getHL(), this.getDE()));
                    this.ivClk -= 7;
                    continue;
                }
                case 41: {
                    this.setHL(this.add16(this.getHL(), this.getHL()));
                    this.ivClk -= 7;
                    continue;
                }
                case 57: {
                    this.setHL(this.add16(this.getHL(), this.getSP()));
                    this.ivClk -= 7;
                    continue;
                }
                case 10: {
                    this.ivA = this.get_IBCI();
                    continue;
                }
                case 26: {
                    this.ivA = this.get_IDEI();
                    continue;
                }
                case 42: {
                    this.setHL(this.get_IADRI_w(this.get_NN()));
                    continue;
                }
                case 58: {
                    this.ivA = this.get_INNI();
                    continue;
                }
                case 11: {
                    this.setBC(this.dec16(this.getBC()));
                    this.ivClk -= 2;
                    continue;
                }
                case 27: {
                    this.setDE(this.dec16(this.getDE()));
                    this.ivClk -= 2;
                    continue;
                }
                case 43: {
                    this.setHL(this.dec16(this.getHL()));
                    this.ivClk -= 2;
                    continue;
                }
                case 59: {
                    this.setSP(this.dec16(this.getSP()));
                    this.ivClk -= 2;
                    continue;
                }
                case 12: {
                    this.ivC = this.inc8(this.ivC);
                    continue;
                }
                case 28: {
                    this.ivE = this.inc8(this.ivE);
                    continue;
                }
                case 44: {
                    this.ivL = this.inc8(this.ivL);
                    continue;
                }
                case 60: {
                    this.ivA = this.inc8(this.ivA);
                    continue;
                }
                case 13: {
                    this.ivC = this.dec8(this.ivC);
                    continue;
                }
                case 29: {
                    this.ivE = this.dec8(this.ivE);
                    continue;
                }
                case 45: {
                    this.ivL = this.dec8(this.ivL);
                    continue;
                }
                case 61: {
                    this.ivA = this.dec8(this.ivA);
                    continue;
                }
                case 14: {
                    this.ivC = this.get_N();
                    continue;
                }
                case 30: {
                    this.ivE = this.get_N();
                    continue;
                }
                case 46: {
                    this.ivL = this.get_N();
                    continue;
                }
                case 62: {
                    this.ivA = this.get_N();
                    continue;
                }
                case 15: {
                    this.rrca();
                    continue;
                }
                case 31: {
                    this.rra();
                    continue;
                }
                case 47: {
                    this.setA(this.getA() ^ 0xFF);
                    this.setF_H(true);
                    this.setF_N(true);
                    continue;
                }
                case 63: {
                    this.setF_H(this.getF_C());
                    this.setF_C(!this.getF_C());
                    this.setF_N(false);
                    continue;
                }
                case 64: {
                    this.ivB = this.ivB;
                    continue;
                }
                case 65: {
                    this.ivB = this.ivC;
                    continue;
                }
                case 66: {
                    this.ivB = this.ivD;
                    continue;
                }
                case 67: {
                    this.ivB = this.ivE;
                    continue;
                }
                case 68: {
                    this.ivB = this.ivH;
                    continue;
                }
                case 69: {
                    this.ivB = this.ivL;
                    continue;
                }
                case 70: {
                    this.ivB = this.get_IHLI();
                    continue;
                }
                case 71: {
                    this.ivB = this.ivA;
                    continue;
                }
                case 72: {
                    this.ivC = this.ivB;
                    continue;
                }
                case 73: {
                    this.ivC = this.ivC;
                    continue;
                }
                case 74: {
                    this.ivC = this.ivD;
                    continue;
                }
                case 75: {
                    this.ivC = this.ivE;
                    continue;
                }
                case 76: {
                    this.ivC = this.ivH;
                    continue;
                }
                case 77: {
                    this.ivC = this.ivL;
                    continue;
                }
                case 78: {
                    this.ivC = this.get_IHLI();
                    continue;
                }
                case 79: {
                    this.ivC = this.ivA;
                    continue;
                }
                case 80: {
                    this.ivD = this.ivB;
                    continue;
                }
                case 81: {
                    this.ivD = this.ivC;
                    continue;
                }
                case 82: {
                    this.ivD = this.ivD;
                    continue;
                }
                case 83: {
                    this.ivD = this.ivE;
                    continue;
                }
                case 84: {
                    this.ivD = this.ivH;
                    continue;
                }
                case 85: {
                    this.ivD = this.ivL;
                    continue;
                }
                case 86: {
                    this.ivD = this.get_IHLI();
                    continue;
                }
                case 87: {
                    this.ivD = this.ivA;
                    continue;
                }
                case 88: {
                    this.ivE = this.ivB;
                    continue;
                }
                case 89: {
                    this.ivE = this.ivC;
                    continue;
                }
                case 90: {
                    this.ivE = this.ivD;
                    continue;
                }
                case 91: {
                    this.ivE = this.ivE;
                    continue;
                }
                case 92: {
                    this.ivE = this.ivH;
                    continue;
                }
                case 93: {
                    this.ivE = this.ivL;
                    continue;
                }
                case 94: {
                    this.ivE = this.get_IHLI();
                    continue;
                }
                case 95: {
                    this.ivE = this.ivA;
                    continue;
                }
                case 96: {
                    this.ivH = this.ivB;
                    continue;
                }
                case 97: {
                    this.ivH = this.ivC;
                    continue;
                }
                case 98: {
                    this.ivH = this.ivD;
                    continue;
                }
                case 99: {
                    this.ivH = this.ivE;
                    continue;
                }
                case 100: {
                    this.ivH = this.ivH;
                    continue;
                }
                case 101: {
                    this.ivH = this.ivL;
                    continue;
                }
                case 102: {
                    this.ivH = this.get_IHLI();
                    continue;
                }
                case 103: {
                    this.ivH = this.ivA;
                    continue;
                }
                case 104: {
                    this.ivL = this.ivB;
                    continue;
                }
                case 105: {
                    this.ivL = this.ivC;
                    continue;
                }
                case 106: {
                    this.ivL = this.ivD;
                    continue;
                }
                case 107: {
                    this.ivL = this.ivE;
                    continue;
                }
                case 108: {
                    this.ivL = this.ivH;
                    continue;
                }
                case 109: {
                    this.ivL = this.ivL;
                    continue;
                }
                case 110: {
                    this.ivL = this.get_IHLI();
                    continue;
                }
                case 111: {
                    this.ivL = this.ivA;
                    continue;
                }
                case 112: {
                    this.set_IHLI(this.ivB);
                    continue;
                }
                case 113: {
                    this.set_IHLI(this.ivC);
                    continue;
                }
                case 114: {
                    this.set_IHLI(this.ivD);
                    continue;
                }
                case 115: {
                    this.set_IHLI(this.ivE);
                    continue;
                }
                case 116: {
                    this.set_IHLI(this.ivH);
                    continue;
                }
                case 117: {
                    this.set_IHLI(this.ivL);
                    continue;
                }
                case 118: {
                    this.setPC(this.getPC() - 1 & 0xFFFF);
                    this.ivHaltClk = this.getClock() - 4L;
                    continue;
                }
                case 119: {
                    this.set_IHLI(this.getA());
                    continue;
                }
                case 120: {
                    this.ivA = this.ivB;
                    continue;
                }
                case 121: {
                    this.ivA = this.ivC;
                    continue;
                }
                case 122: {
                    this.ivA = this.ivD;
                    continue;
                }
                case 123: {
                    this.ivA = this.ivE;
                    continue;
                }
                case 124: {
                    this.ivA = this.ivH;
                    continue;
                }
                case 125: {
                    this.ivA = this.ivL;
                    continue;
                }
                case 126: {
                    this.ivA = this.get_IHLI();
                    continue;
                }
                case 127: {
                    this.ivA = this.ivA;
                    continue;
                }
                case 128: {
                    this.ivA = this.add8(this.ivA, this.ivB);
                    continue;
                }
                case 129: {
                    this.ivA = this.add8(this.ivA, this.ivC);
                    continue;
                }
                case 130: {
                    this.ivA = this.add8(this.ivA, this.ivD);
                    continue;
                }
                case 131: {
                    this.ivA = this.add8(this.ivA, this.ivE);
                    continue;
                }
                case 132: {
                    this.ivA = this.add8(this.ivA, this.ivH);
                    continue;
                }
                case 133: {
                    this.ivA = this.add8(this.ivA, this.ivL);
                    continue;
                }
                case 134: {
                    this.ivA = this.add8(this.ivA, this.get_IHLI());
                    continue;
                }
                case 135: {
                    this.ivA = this.add8(this.ivA, this.ivA);
                    continue;
                }
                case 136: {
                    this.ivA = this.adc8(this.ivA, this.ivB);
                    continue;
                }
                case 137: {
                    this.ivA = this.adc8(this.ivA, this.ivC);
                    continue;
                }
                case 138: {
                    this.ivA = this.adc8(this.ivA, this.ivD);
                    continue;
                }
                case 139: {
                    this.ivA = this.adc8(this.ivA, this.ivE);
                    continue;
                }
                case 140: {
                    this.ivA = this.adc8(this.ivA, this.ivH);
                    continue;
                }
                case 141: {
                    this.ivA = this.adc8(this.ivA, this.ivL);
                    continue;
                }
                case 142: {
                    this.ivA = this.adc8(this.ivA, this.get_IHLI());
                    continue;
                }
                case 143: {
                    this.ivA = this.adc8(this.ivA, this.ivA);
                    continue;
                }
                case 144: {
                    this.ivA = this.sub8(this.ivA, this.ivB);
                    continue;
                }
                case 145: {
                    this.ivA = this.sub8(this.ivA, this.ivC);
                    continue;
                }
                case 146: {
                    this.ivA = this.sub8(this.ivA, this.ivD);
                    continue;
                }
                case 147: {
                    this.ivA = this.sub8(this.ivA, this.ivE);
                    continue;
                }
                case 148: {
                    this.ivA = this.sub8(this.ivA, this.ivH);
                    continue;
                }
                case 149: {
                    this.ivA = this.sub8(this.ivA, this.ivL);
                    continue;
                }
                case 150: {
                    this.ivA = this.sub8(this.ivA, this.get_IHLI());
                    continue;
                }
                case 151: {
                    this.ivA = this.sub8(this.ivA, this.ivA);
                    continue;
                }
                case 152: {
                    this.ivA = this.sbc8(this.ivA, this.ivB);
                    continue;
                }
                case 153: {
                    this.ivA = this.sbc8(this.ivA, this.ivC);
                    continue;
                }
                case 154: {
                    this.ivA = this.sbc8(this.ivA, this.ivD);
                    continue;
                }
                case 155: {
                    this.ivA = this.sbc8(this.ivA, this.ivE);
                    continue;
                }
                case 156: {
                    this.ivA = this.sbc8(this.ivA, this.ivH);
                    continue;
                }
                case 157: {
                    this.ivA = this.sbc8(this.ivA, this.ivL);
                    continue;
                }
                case 158: {
                    this.ivA = this.sbc8(this.ivA, this.get_IHLI());
                    continue;
                }
                case 159: {
                    this.ivA = this.sbc8(this.ivA, this.ivA);
                    continue;
                }
                case 160: {
                    this.ivA = this.and8(this.ivA, this.ivB);
                    continue;
                }
                case 161: {
                    this.ivA = this.and8(this.ivA, this.ivC);
                    continue;
                }
                case 162: {
                    this.ivA = this.and8(this.ivA, this.ivD);
                    continue;
                }
                case 163: {
                    this.ivA = this.and8(this.ivA, this.ivE);
                    continue;
                }
                case 164: {
                    this.ivA = this.and8(this.ivA, this.ivH);
                    continue;
                }
                case 165: {
                    this.ivA = this.and8(this.ivA, this.ivL);
                    continue;
                }
                case 166: {
                    this.ivA = this.and8(this.ivA, this.get_IHLI());
                    continue;
                }
                case 167: {
                    this.ivA = this.and8(this.ivA, this.ivA);
                    continue;
                }
                case 168: {
                    this.ivA = this.xor8(this.ivA, this.ivB);
                    continue;
                }
                case 169: {
                    this.ivA = this.xor8(this.ivA, this.ivC);
                    continue;
                }
                case 170: {
                    this.ivA = this.xor8(this.ivA, this.ivD);
                    continue;
                }
                case 171: {
                    this.ivA = this.xor8(this.ivA, this.ivE);
                    continue;
                }
                case 172: {
                    this.ivA = this.xor8(this.ivA, this.ivH);
                    continue;
                }
                case 173: {
                    this.ivA = this.xor8(this.ivA, this.ivL);
                    continue;
                }
                case 174: {
                    this.ivA = this.xor8(this.ivA, this.get_IHLI());
                    continue;
                }
                case 175: {
                    this.ivA = this.xor8(this.ivA, this.ivA);
                    continue;
                }
                case 176: {
                    this.ivA = this.or8(this.ivA, this.ivB);
                    continue;
                }
                case 177: {
                    this.ivA = this.or8(this.ivA, this.ivC);
                    continue;
                }
                case 178: {
                    this.ivA = this.or8(this.ivA, this.ivD);
                    continue;
                }
                case 179: {
                    this.ivA = this.or8(this.ivA, this.ivE);
                    continue;
                }
                case 180: {
                    this.ivA = this.or8(this.ivA, this.ivH);
                    continue;
                }
                case 181: {
                    this.ivA = this.or8(this.ivA, this.ivL);
                    continue;
                }
                case 182: {
                    this.ivA = this.or8(this.ivA, this.get_IHLI());
                    continue;
                }
                case 183: {
                    this.ivA = this.or8(this.ivA, this.ivA);
                    continue;
                }
                case 184: {
                    this.cp8(this.ivA, this.ivB);
                    continue;
                }
                case 185: {
                    this.cp8(this.ivA, this.ivC);
                    continue;
                }
                case 186: {
                    this.cp8(this.ivA, this.ivD);
                    continue;
                }
                case 187: {
                    this.cp8(this.ivA, this.ivE);
                    continue;
                }
                case 188: {
                    this.cp8(this.ivA, this.ivH);
                    continue;
                }
                case 189: {
                    this.cp8(this.ivA, this.ivL);
                    continue;
                }
                case 190: {
                    this.cp8(this.ivA, this.get_IHLI());
                    continue;
                }
                case 191: {
                    this.cp8(this.ivA, this.ivA);
                    continue;
                }
                case 193: {
                    this.setBC(this.pop());
                    continue;
                }
                case 209: {
                    this.setDE(this.pop());
                    continue;
                }
                case 225: {
                    this.setHL(this.pop());
                    continue;
                }
                case 241: {
                    this.setAF(this.pop());
                    continue;
                }
                case 194: {
                    this.jp(!this.getF_Z());
                    continue;
                }
                case 210: {
                    this.jp(!this.getF_C());
                    continue;
                }
                case 226: {
                    this.jp(!this.getF_PV());
                    continue;
                }
                case 242: {
                    this.jp(!this.getF_S());
                    continue;
                }
                case 195: {
                    this.jp(true);
                    continue;
                }
                case 211: {
                    this.set_IO_N(this.getA());
                    continue;
                }
                case 227: {
                    final int temp = this.pop();
                    --this.ivClk;
                    this.push(this.getHL());
                    this.setHL(temp);
                    this.readMem(this.ivSP + 1 & 0xFFFF, 2);
                    continue;
                }
                case 243: {
                    this.setIFF1(false);
                    this.setIFF2(false);
                    continue;
                }
                case 196: {
                    this.call(!this.getF_Z());
                    continue;
                }
                case 212: {
                    this.call(!this.getF_C());
                    continue;
                }
                case 228: {
                    this.call(!this.getF_PV());
                    continue;
                }
                case 244: {
                    this.call(!this.getF_S());
                    continue;
                }
                case 197: {
                    --this.ivClk;
                    this.push(this.getBC());
                    continue;
                }
                case 213: {
                    --this.ivClk;
                    this.push(this.getDE());
                    continue;
                }
                case 229: {
                    --this.ivClk;
                    this.push(this.getHL());
                    continue;
                }
                case 245: {
                    --this.ivClk;
                    this.push(this.getAF());
                    continue;
                }
                case 198: {
                    this.ivA = this.add8(this.ivA, this.get_N());
                    continue;
                }
                case 214: {
                    this.ivA = this.sub8(this.ivA, this.get_N());
                    continue;
                }
                case 230: {
                    this.ivA = this.and8(this.ivA, this.get_N());
                    continue;
                }
                case 246: {
                    this.ivA = this.or8(this.ivA, this.get_N());
                    continue;
                }
                case 199: {
                    --this.ivClk;
                    this.push(this.getPC());
                    this.setPC(0);
                    continue;
                }
                case 215: {
                    --this.ivClk;
                    this.push(this.getPC());
                    this.setPC(16);
                    continue;
                }
                case 231: {
                    --this.ivClk;
                    this.push(this.getPC());
                    this.setPC(32);
                    continue;
                }
                case 247: {
                    --this.ivClk;
                    this.push(this.getPC());
                    this.setPC(48);
                    continue;
                }
                case 201: {
                    this.ret();
                    continue;
                }
                case 217: {
                    int temp = this.getBC();
                    this.setBC(this.getBC2());
                    this.setBC2(temp);
                    temp = this.getDE();
                    this.setDE(this.getDE2());
                    this.setDE2(temp);
                    temp = this.getHL();
                    this.setHL(this.getHL2());
                    this.setHL2(temp);
                    continue;
                }
                case 233: {
                    this.setPC(this.getHL());
                    continue;
                }
                case 249: {
                    this.setSP(this.getHL());
                    this.ivClk -= 2;
                    continue;
                }
                case 202: {
                    this.jp(this.getF_Z());
                    continue;
                }
                case 218: {
                    this.jp(this.getF_C());
                    continue;
                }
                case 234: {
                    this.jp(this.getF_PV());
                    continue;
                }
                case 250: {
                    this.jp(this.getF_S());
                    continue;
                }
                case 203: {
                    this.decodeCB(this.get_Opcode(4));
                    continue;
                }
                case 219: {
                    this.setA(this.get_IO_N());
                    continue;
                }
                case 235: {
                    final int temp = this.getDE();
                    this.setDE(this.getHL());
                    this.setHL(temp);
                    continue;
                }
                case 251: {
                    this.setIFF1(true);
                    this.setIFF2(true);
                    continue;
                }
                case 204: {
                    this.call(this.getF_Z());
                    continue;
                }
                case 220: {
                    this.call(this.getF_C());
                    continue;
                }
                case 236: {
                    this.call(this.getF_PV());
                    continue;
                }
                case 252: {
                    this.call(this.getF_S());
                    continue;
                }
                case 206: {
                    this.ivA = this.adc8(this.ivA, this.get_N());
                    continue;
                }
                case 222: {
                    this.ivA = this.sbc8(this.ivA, this.get_N());
                    continue;
                }
                case 238: {
                    this.ivA = this.xor8(this.ivA, this.get_N());
                    continue;
                }
                case 254: {
                    this.cp8(this.ivA, this.get_N());
                    continue;
                }
                case 207: {
                    --this.ivClk;
                    this.push(this.getPC());
                    this.setPC(8);
                    continue;
                }
                case 223: {
                    --this.ivClk;
                    this.push(this.getPC());
                    this.setPC(24);
                    continue;
                }
                case 239: {
                    --this.ivClk;
                    this.push(this.getPC());
                    this.setPC(40);
                    continue;
                }
                case 255: {
                    --this.ivClk;
                    this.push(this.getPC());
                    this.setPC(56);
                    continue;
                }
                case 205: {
                    this.call(true);
                    continue;
                }
                case 221: {
                    this.decodeIX(this.get_Opcode(4));
                    continue;
                }
                case 237: {
                    this.decodeED(this.get_Opcode(4));
                    continue;
                }
                case 253: {
                    this.decodeIY(this.get_Opcode(4));
                    continue;
                }
            }
        }
    }
    
    private void decodeIX(final int pOpcode) {
        ++this.ivR;
        switch (pOpcode) {
            case 9: {
                this.setIX(this.add16(this.getIX(), this.getBC()));
                this.ivClk -= 7;
                break;
            }
            case 25: {
                this.setIX(this.add16(this.getIX(), this.getDE()));
                this.ivClk -= 7;
                break;
            }
            case 41: {
                this.setIX(this.add16(this.getIX(), this.getIX()));
                this.ivClk -= 7;
                break;
            }
            case 57: {
                this.setIX(this.add16(this.getIX(), this.getSP()));
                this.ivClk -= 7;
                break;
            }
            case 33: {
                this.setIX(this.get_NN());
                break;
            }
            case 34: {
                this.set_INNI_w(this.getIX());
                break;
            }
            case 35: {
                this.setIX(this.inc16(this.getIX()));
                this.ivClk -= 2;
                break;
            }
            case 36: {
                this.setIXh(this.inc8(this.getIXh()));
                break;
            }
            case 37: {
                this.setIXh(this.dec8(this.getIXh()));
                break;
            }
            case 38: {
                this.setIXh(this.get_N());
                break;
            }
            case 42: {
                this.setIX(this.get_IADRI_w(this.get_NN()));
                break;
            }
            case 43: {
                this.setIX(this.dec16(this.getIX()));
                this.ivClk -= 2;
                break;
            }
            case 44: {
                this.setIXl(this.inc8(this.getIXl()));
                break;
            }
            case 45: {
                this.setIXl(this.dec8(this.getIXl()));
                break;
            }
            case 46: {
                this.setIXl(this.get_N());
                break;
            }
            case 52: {
                final int a = this.get_IXD();
                this.readMem(this.ivPC - 1 & 0xFFFF, 5);
                final int b = this.inc8(this.get_IADRI(a));
                this.readMem(a, 1);
                this.set_IADRI(a, b);
                break;
            }
            case 53: {
                final int a = this.get_IXD();
                this.readMem(this.ivPC - 1 & 0xFFFF, 5);
                final int b = this.dec8(this.get_IADRI(a));
                this.readMem(a, 1);
                this.set_IADRI(a, b);
                break;
            }
            case 54: {
                final int a = this.get_IXD();
                final int b = this.get_N();
                this.readMem(this.ivPC - 1 & 0xFFFF, 2);
                this.set_IADRI(a, b);
                break;
            }
            case 68: {
                this.setB(this.getIXh());
                break;
            }
            case 69: {
                this.setB(this.getIXl());
                break;
            }
            case 70: {
                this.setB(this.get_IIXDI());
                break;
            }
            case 76: {
                this.setC(this.getIXh());
                break;
            }
            case 77: {
                this.setC(this.getIXl());
                break;
            }
            case 78: {
                this.setC(this.get_IIXDI());
                break;
            }
            case 84: {
                this.setD(this.getIXh());
                break;
            }
            case 85: {
                this.setD(this.getIXl());
                break;
            }
            case 86: {
                this.setD(this.get_IIXDI());
                break;
            }
            case 92: {
                this.setE(this.getIXh());
                break;
            }
            case 93: {
                this.setE(this.getIXl());
                break;
            }
            case 94: {
                this.setE(this.get_IIXDI());
                break;
            }
            case 96: {
                this.setIXh(this.getB());
                break;
            }
            case 97: {
                this.setIXh(this.getC());
                break;
            }
            case 98: {
                this.setIXh(this.getD());
                break;
            }
            case 99: {
                this.setIXh(this.getE());
                break;
            }
            case 100: {
                this.setIXh(this.getIXh());
                break;
            }
            case 101: {
                this.setIXh(this.getIXl());
                break;
            }
            case 102: {
                this.setH(this.get_IIXDI());
                break;
            }
            case 103: {
                this.setIXh(this.getA());
                break;
            }
            case 104: {
                this.setIXl(this.getB());
                break;
            }
            case 105: {
                this.setIXl(this.getC());
                break;
            }
            case 106: {
                this.setIXl(this.getD());
                break;
            }
            case 107: {
                this.setIXl(this.getE());
                break;
            }
            case 108: {
                this.setIXl(this.getIXh());
                break;
            }
            case 109: {
                this.setIXl(this.getIXl());
                break;
            }
            case 110: {
                this.setL(this.get_IIXDI());
                break;
            }
            case 111: {
                this.setIXl(this.getA());
                break;
            }
            case 112: {
                this.set_IIXDI(this.getB());
                break;
            }
            case 113: {
                this.set_IIXDI(this.getC());
                break;
            }
            case 114: {
                this.set_IIXDI(this.getD());
                break;
            }
            case 115: {
                this.set_IIXDI(this.getE());
                break;
            }
            case 116: {
                this.set_IIXDI(this.getH());
                break;
            }
            case 117: {
                this.set_IIXDI(this.getL());
                break;
            }
            case 119: {
                this.set_IIXDI(this.getA());
                break;
            }
            case 124: {
                this.setA(this.getIXh());
                break;
            }
            case 125: {
                this.setA(this.getIXl());
                break;
            }
            case 126: {
                this.setA(this.get_IIXDI());
                break;
            }
            case 132: {
                this.setA(this.add8(this.getA(), this.getIXh()));
                break;
            }
            case 133: {
                this.setA(this.add8(this.getA(), this.getIXl()));
                break;
            }
            case 134: {
                this.setA(this.add8(this.getA(), this.get_IIXDI()));
                break;
            }
            case 140: {
                this.setA(this.adc8(this.getA(), this.getIXh()));
                break;
            }
            case 141: {
                this.setA(this.adc8(this.getA(), this.getIXl()));
                break;
            }
            case 142: {
                this.setA(this.adc8(this.getA(), this.get_IIXDI()));
                break;
            }
            case 148: {
                this.setA(this.sub8(this.getA(), this.getIXh()));
                break;
            }
            case 149: {
                this.setA(this.sub8(this.getA(), this.getIXl()));
                break;
            }
            case 150: {
                this.setA(this.sub8(this.getA(), this.get_IIXDI()));
                break;
            }
            case 156: {
                this.setA(this.sbc8(this.getA(), this.getIXh()));
                break;
            }
            case 157: {
                this.setA(this.sbc8(this.getA(), this.getIXl()));
                break;
            }
            case 158: {
                this.setA(this.sbc8(this.getA(), this.get_IIXDI()));
                break;
            }
            case 164: {
                this.setA(this.and8(this.getA(), this.getIXh()));
                break;
            }
            case 165: {
                this.setA(this.and8(this.getA(), this.getIXl()));
                break;
            }
            case 166: {
                this.setA(this.and8(this.getA(), this.get_IIXDI()));
                break;
            }
            case 172: {
                this.setA(this.xor8(this.getA(), this.getIXh()));
                break;
            }
            case 173: {
                this.setA(this.xor8(this.getA(), this.getIXl()));
                break;
            }
            case 174: {
                this.setA(this.xor8(this.getA(), this.get_IIXDI()));
                break;
            }
            case 180: {
                this.setA(this.or8(this.getA(), this.getIXh()));
                break;
            }
            case 181: {
                this.setA(this.or8(this.getA(), this.getIXl()));
                break;
            }
            case 182: {
                this.setA(this.or8(this.getA(), this.get_IIXDI()));
                break;
            }
            case 188: {
                this.cp8(this.getA(), this.getIXh());
                break;
            }
            case 189: {
                this.cp8(this.getA(), this.getIXl());
                break;
            }
            case 190: {
                this.cp8(this.getA(), this.get_IIXDI());
                break;
            }
            case 203: {
                this.decodeIXIYCB(this.get_IXD());
                break;
            }
            case 221: {
                this.decodeIX(this.get_Opcode());
                break;
            }
            case 225: {
                this.setIX(this.pop());
                break;
            }
            case 227: {
                final int temp = this.pop();
                --this.ivClk;
                this.push(this.getIX());
                this.setIX(temp);
                this.readMem(this.ivSP + 1 & 0xFFFF, 2);
                break;
            }
            case 229: {
                --this.ivClk;
                this.push(this.getIX());
                break;
            }
            case 233: {
                this.setPC(this.getIX());
                break;
            }
            case 237: {
                this.decodeED(this.get_Opcode(8));
                break;
            }
            case 249: {
                this.setSP(this.getIX());
                this.ivClk -= 2;
                break;
            }
            case 253: {
                this.decodeIY(this.get_Opcode());
                break;
            }
            default: {
                this.ivPC = (this.ivPC - 1 & 0xFFFF);
                --this.ivR;
                this.ivClk += 4;
                break;
            }
        }
    }
    
    private void decodeIXIYCB(final int pAddress) {
        final int opcode = this.ivIO.readOpcode(this.ivPC);
        this.ivClk -= 3;
        this.readMem(this.ivPC++, 2);
        this.ivPC &= 0xFFFF;
        int r = this.ivIO.readMem(pAddress);
        this.ivClk -= 3;
        this.readMem(pAddress, 1);
        switch (opcode & 0xC0) {
            case 0: {
                switch (opcode & 0x38) {
                    case 0: {
                        r = this.rlc8(r);
                        break;
                    }
                    case 8: {
                        r = this.rrc8(r);
                        break;
                    }
                    case 16: {
                        r = this.rl8(r);
                        break;
                    }
                    case 24: {
                        r = this.rr8(r);
                        break;
                    }
                    case 32: {
                        r = this.sla8(r);
                        break;
                    }
                    case 40: {
                        r = this.sra8(r);
                        break;
                    }
                    case 48: {
                        r = this.sll8(r);
                        break;
                    }
                    case 56: {
                        r = this.srl8(r);
                        break;
                    }
                }
                break;
            }
            case 64: {
                this.bit(opcode >> 3 & 0x7, r);
                return;
            }
            case 128: {
                r = this.res(opcode >> 3 & 0x7, r);
                break;
            }
            case 192: {
                r = this.set(opcode >> 3 & 0x7, r);
                break;
            }
        }
        this.ivIO.writeMem(pAddress, r);
        this.ivClk -= 3;
        switch (opcode & 0x7) {
            case 0: {
                this.ivB = r;
                break;
            }
            case 1: {
                this.ivC = r;
                break;
            }
            case 2: {
                this.ivD = r;
                break;
            }
            case 3: {
                this.ivE = r;
                break;
            }
            case 4: {
                this.ivH = r;
                break;
            }
            case 5: {
                this.ivL = r;
            }
            case 7: {
                this.ivA = r;
                break;
            }
        }
    }
    
    private void decodeIY(final int pOpcode) {
        ++this.ivR;
        switch (pOpcode) {
            case 9: {
                this.setIY(this.add16(this.getIY(), this.getBC()));
                this.ivClk -= 7;
                break;
            }
            case 25: {
                this.setIY(this.add16(this.getIY(), this.getDE()));
                this.ivClk -= 7;
                break;
            }
            case 41: {
                this.setIY(this.add16(this.getIY(), this.getIY()));
                this.ivClk -= 7;
                break;
            }
            case 57: {
                this.setIY(this.add16(this.getIY(), this.getSP()));
                this.ivClk -= 7;
                break;
            }
            case 33: {
                this.setIY(this.get_NN());
                break;
            }
            case 34: {
                this.set_INNI_w(this.getIY());
                break;
            }
            case 35: {
                this.setIY(this.inc16(this.getIY()));
                this.ivClk -= 2;
                break;
            }
            case 36: {
                this.setIYh(this.inc8(this.getIYh()));
                break;
            }
            case 37: {
                this.setIYh(this.dec8(this.getIYh()));
                break;
            }
            case 38: {
                this.setIYh(this.get_N());
                break;
            }
            case 42: {
                this.setIY(this.get_IADRI_w(this.get_NN()));
                break;
            }
            case 43: {
                this.setIY(this.dec16(this.getIY()));
                this.ivClk -= 2;
                break;
            }
            case 44: {
                this.setIYl(this.inc8(this.getIYl()));
                break;
            }
            case 45: {
                this.setIYl(this.dec8(this.getIYl()));
                break;
            }
            case 46: {
                this.setIYl(this.get_N());
                break;
            }
            case 52: {
                final int a = this.get_IYD();
                this.readMem(this.ivPC - 1 & 0xFFFF, 5);
                final int b = this.inc8(this.get_IADRI(a));
                this.readMem(a, 1);
                this.set_IADRI(a, b);
                break;
            }
            case 53: {
                final int a = this.get_IYD();
                this.readMem(this.ivPC - 1 & 0xFFFF, 5);
                final int b = this.dec8(this.get_IADRI(a));
                this.readMem(a, 1);
                this.set_IADRI(a, b);
                break;
            }
            case 54: {
                final int a = this.get_IYD();
                final int b = this.get_N();
                this.readMem(this.ivPC - 1 & 0xFFFF, 2);
                this.set_IADRI(a, b);
                break;
            }
            case 68: {
                this.setB(this.getIYh());
                break;
            }
            case 69: {
                this.setB(this.getIYl());
                break;
            }
            case 70: {
                this.setB(this.get_IIYDI());
                break;
            }
            case 76: {
                this.setC(this.getIYh());
                break;
            }
            case 77: {
                this.setC(this.getIYl());
                break;
            }
            case 78: {
                this.setC(this.get_IIYDI());
                break;
            }
            case 84: {
                this.setD(this.getIYh());
                break;
            }
            case 85: {
                this.setD(this.getIYl());
                break;
            }
            case 86: {
                this.setD(this.get_IIYDI());
                break;
            }
            case 92: {
                this.setE(this.getIYh());
                break;
            }
            case 93: {
                this.setE(this.getIYl());
                break;
            }
            case 94: {
                this.setE(this.get_IIYDI());
                break;
            }
            case 96: {
                this.setIYh(this.getB());
                break;
            }
            case 97: {
                this.setIYh(this.getC());
                break;
            }
            case 98: {
                this.setIYh(this.getD());
                break;
            }
            case 99: {
                this.setIYh(this.getE());
                break;
            }
            case 100: {
                this.setIYh(this.getIYh());
                break;
            }
            case 101: {
                this.setIYh(this.getIYl());
                break;
            }
            case 102: {
                this.setH(this.get_IIYDI());
                break;
            }
            case 103: {
                this.setIYh(this.getA());
                break;
            }
            case 104: {
                this.setIYl(this.getB());
                break;
            }
            case 105: {
                this.setIYl(this.getC());
                break;
            }
            case 106: {
                this.setIYl(this.getD());
                break;
            }
            case 107: {
                this.setIYl(this.getE());
                break;
            }
            case 108: {
                this.setIYl(this.getIYh());
                break;
            }
            case 109: {
                this.setIYl(this.getIYl());
                break;
            }
            case 110: {
                this.setL(this.get_IIYDI());
                break;
            }
            case 111: {
                this.setIYl(this.getA());
                break;
            }
            case 112: {
                this.set_IIYDI(this.getB());
                break;
            }
            case 113: {
                this.set_IIYDI(this.getC());
                break;
            }
            case 114: {
                this.set_IIYDI(this.getD());
                break;
            }
            case 115: {
                this.set_IIYDI(this.getE());
                break;
            }
            case 116: {
                this.set_IIYDI(this.getH());
                break;
            }
            case 117: {
                this.set_IIYDI(this.getL());
                break;
            }
            case 119: {
                this.set_IIYDI(this.getA());
                break;
            }
            case 124: {
                this.setA(this.getIYh());
                break;
            }
            case 125: {
                this.setA(this.getIYl());
                break;
            }
            case 126: {
                this.setA(this.get_IIYDI());
                break;
            }
            case 132: {
                this.setA(this.add8(this.getA(), this.getIYh()));
                break;
            }
            case 133: {
                this.setA(this.add8(this.getA(), this.getIYl()));
                break;
            }
            case 134: {
                this.setA(this.add8(this.getA(), this.get_IIYDI()));
                break;
            }
            case 140: {
                this.setA(this.adc8(this.getA(), this.getIYh()));
                break;
            }
            case 141: {
                this.setA(this.adc8(this.getA(), this.getIYl()));
                break;
            }
            case 142: {
                this.setA(this.adc8(this.getA(), this.get_IIYDI()));
                break;
            }
            case 148: {
                this.setA(this.sub8(this.getA(), this.getIYh()));
                break;
            }
            case 149: {
                this.setA(this.sub8(this.getA(), this.getIYl()));
                break;
            }
            case 150: {
                this.setA(this.sub8(this.getA(), this.get_IIYDI()));
                break;
            }
            case 156: {
                this.setA(this.sbc8(this.getA(), this.getIYh()));
                break;
            }
            case 157: {
                this.setA(this.sbc8(this.getA(), this.getIYl()));
                break;
            }
            case 158: {
                this.setA(this.sbc8(this.getA(), this.get_IIYDI()));
                break;
            }
            case 164: {
                this.setA(this.and8(this.getA(), this.getIYh()));
                break;
            }
            case 165: {
                this.setA(this.and8(this.getA(), this.getIYl()));
                break;
            }
            case 166: {
                this.setA(this.and8(this.getA(), this.get_IIYDI()));
                break;
            }
            case 172: {
                this.setA(this.xor8(this.getA(), this.getIYh()));
                break;
            }
            case 173: {
                this.setA(this.xor8(this.getA(), this.getIYl()));
                break;
            }
            case 174: {
                this.setA(this.xor8(this.getA(), this.get_IIYDI()));
                break;
            }
            case 180: {
                this.setA(this.or8(this.getA(), this.getIYh()));
                break;
            }
            case 181: {
                this.setA(this.or8(this.getA(), this.getIYl()));
                break;
            }
            case 182: {
                this.setA(this.or8(this.getA(), this.get_IIYDI()));
                break;
            }
            case 188: {
                this.cp8(this.getA(), this.getIYh());
                break;
            }
            case 189: {
                this.cp8(this.getA(), this.getIYl());
                break;
            }
            case 190: {
                this.cp8(this.getA(), this.get_IIYDI());
                break;
            }
            case 203: {
                this.decodeIXIYCB(this.get_IYD());
                break;
            }
            case 221: {
                this.decodeIX(this.get_Opcode());
                break;
            }
            case 225: {
                this.setIY(this.pop());
                break;
            }
            case 227: {
                final int temp = this.pop();
                --this.ivClk;
                this.push(this.getIY());
                this.setIY(temp);
                this.readMem(this.ivSP + 1 & 0xFFFF, 2);
                break;
            }
            case 229: {
                --this.ivClk;
                this.push(this.getIY());
                break;
            }
            case 233: {
                this.setPC(this.getIY());
                break;
            }
            case 237: {
                this.decodeED(this.get_Opcode(8));
                break;
            }
            case 249: {
                this.setSP(this.getIY());
                this.ivClk -= 2;
                break;
            }
            case 253: {
                this.decodeIY(this.get_Opcode());
                break;
            }
            default: {
                this.ivPC = (this.ivPC - 1 & 0xFFFF);
                --this.ivR;
                this.ivClk += 4;
                break;
            }
        }
    }
    
    public void delay(final int pNumberOfClockCycles) {
        this.adjustClock(pNumberOfClockCycles);
    }
    
    private int get_DISP() {
        final int res = (byte)this.ivIO.readMem(this.ivPC++);
        this.ivPC &= 0xFFFF;
        this.ivClk -= 3;
        return res;
    }
    
    private int get_IADRI(final int pAddress) {
        final int res = this.ivIO.readMem(pAddress);
        this.ivClk -= 3;
        return res;
    }
    
    private int get_IADRI_w(final int pAddress) {
        int res = this.ivIO.readMem(pAddress);
        this.ivClk -= 3;
        res |= this.ivIO.readMem(pAddress + 1 & 0xFFFF) << 8;
        this.ivClk -= 3;
        return res;
    }
    
    private int get_IBCI() {
        final int res = this.ivIO.readMem(this.ivB << 8 | this.ivC);
        this.ivClk -= 3;
        return res;
    }
    
    private int get_IDEI() {
        final int res = this.ivIO.readMem(this.ivD << 8 | this.ivE);
        this.ivClk -= 3;
        return res;
    }
    
    private int get_IHLI() {
        final int res = this.ivIO.readMem(this.ivH << 8 | this.ivL);
        this.ivClk -= 3;
        return res;
    }
    
    private int get_IIXDI() {
        final int adr = this.get_IXD();
        this.readMem(this.ivPC - 1 & 0xFFFF, 5);
        final int res = this.ivIO.readMem(adr);
        this.ivClk -= 3;
        return res;
    }
    
    private int get_IIYDI() {
        final int adr = this.get_IYD();
        this.readMem(this.ivPC - 1 & 0xFFFF, 5);
        final int res = this.ivIO.readMem(adr);
        this.ivClk -= 3;
        return res;
    }
    
    private int get_INNI() {
        final int res = this.ivIO.readMem(this.get_NN());
        this.ivClk -= 3;
        return res;
    }
    
    private int get_IO_C() {
        final int res = this.ivIO.readIO(this.ivB << 8 | this.ivC);
        this.ivClk -= 4;
        return res;
    }
    
    private int get_IO_N() {
        final int res = this.ivIO.readIO(this.get_N() | this.ivA << 8);
        this.ivClk -= 4;
        return res;
    }
    
    private int get_ISPI() {
        final int res = this.ivIO.readMem(this.ivSP);
        this.ivClk -= 3;
        return res;
    }
    
    private int get_IXD() {
        return this.ivIX + this.get_DISP() & 0xFFFF;
    }
    
    private int get_IYD() {
        return this.ivIY + this.get_DISP() & 0xFFFF;
    }
    
    private int get_N() {
        final int res = this.ivIO.readMem(this.ivPC++);
        this.ivPC &= 0xFFFF;
        this.ivClk -= 3;
        return res;
    }
    
    private int get_NN() {
        int res = this.ivIO.readMem(this.ivPC++);
        this.ivClk -= 3;
        res |= this.ivIO.readMem(this.ivPC++ & 0xFFFF) << 8;
        this.ivPC &= 0xFFFF;
        this.ivClk -= 3;
        return res;
    }
    
    private int get_Opcode() {
        final int res = this.ivIO.readOpcode(this.ivPC++);
        this.ivPC &= 0xFFFF;
        this.ivClk -= 4;
        return res;
    }
    
    private int get_Opcode(final int pD) {
        final int temp = this.get_Opcode();
        return temp;
    }
    
    public int getA() {
        return this.ivA;
    }
    
    public int getAF() {
        return this.ivA << 8 | this.ivF;
    }
    
    public int getAF2() {
        return this.ivA2 << 8 | this.ivF2;
    }
    
    public int getB() {
        return this.ivB;
    }
    
    public int getBC() {
        return this.ivB << 8 | this.ivC;
    }
    
    public int getBC2() {
        return this.ivB2 << 8 | this.ivC2;
    }
    
    public int getC() {
        return this.ivC;
    }
    
    public long getClock() {
        return this.ivClkOffset - this.ivClk;
    }
    
    public long getClockFull() {
        return this.ivClkOffset - this.ivClk + this.addedCycles;
    }
    
    public int getD() {
        return this.ivD;
    }
    
    public int getDE() {
        return this.ivD << 8 | this.ivE;
    }
    
    public int getDE2() {
        return this.ivD2 << 8 | this.ivE2;
    }
    
    private int getDReg45AF(final int pOpcode) {
        switch (pOpcode & 0x30) {
            case 0: {
                return this.getBC();
            }
            case 16: {
                return this.getDE();
            }
            case 32: {
                return this.getHL();
            }
            case 48: {
                return this.getAF();
            }
            default: {
                throw new RuntimeException("BUG: pOpcode = " + pOpcode);
            }
        }
    }
    
    private int getDReg45SP(final int pOpcode) {
        switch (pOpcode & 0x30) {
            case 0: {
                return this.getBC();
            }
            case 16: {
                return this.getDE();
            }
            case 32: {
                return this.getHL();
            }
            case 48: {
                return this.getSP();
            }
            default: {
                throw new RuntimeException("BUG: pOpcode = " + pOpcode);
            }
        }
    }
    
    public int getE() {
        return this.ivE;
    }
    
    public int getF() {
        return this.ivF;
    }
    
    public boolean getF_3() {
        return (this.ivF & 0x8) != 0x0;
    }
    
    public boolean getF_5() {
        return (this.ivF & 0x20) != 0x0;
    }
    
    public boolean getF_C() {
        return (this.ivF & 0x1) != 0x0;
    }
    
    public boolean getF_H() {
        return (this.ivF & 0x10) != 0x0;
    }
    
    public boolean getF_N() {
        return (this.ivF & 0x2) != 0x0;
    }
    
    public boolean getF_PV() {
        return (this.ivF & 0x4) != 0x0;
    }
    
    public boolean getF_S() {
        return (this.ivF & 0x80) != 0x0;
    }
    
    public boolean getF_Z() {
        return (this.ivF & 0x40) != 0x0;
    }
    
    private boolean getFlag34(final int pOpcode) {
        switch (pOpcode & 0x18) {
            case 0: {
                return !this.getF_Z();
            }
            case 8: {
                return this.getF_Z();
            }
            case 16: {
                return !this.getF_C();
            }
            case 24: {
                return this.getF_C();
            }
            default: {
                throw new RuntimeException("BUG: pOpcode = " + pOpcode);
            }
        }
    }
    
    private boolean getFlag345(final int pOpcode) {
        switch (pOpcode & 0x38) {
            case 0: {
                return !this.getF_Z();
            }
            case 8: {
                return this.getF_Z();
            }
            case 16: {
                return !this.getF_C();
            }
            case 24: {
                return this.getF_C();
            }
            case 32: {
                return !this.getF_PV();
            }
            case 40: {
                return this.getF_PV();
            }
            case 48: {
                return !this.getF_S();
            }
            case 56: {
                return this.getF_S();
            }
            default: {
                throw new RuntimeException("BUG: pOpcode = " + pOpcode);
            }
        }
    }
    
    public int getH() {
        return this.ivH;
    }
    
    public boolean getHALT() {
        return this.ivHaltClk == this.getClock() - 4L;
    }
    
    public int getHL() {
        return this.ivH << 8 | this.ivL;
    }
    
    public int getHL2() {
        return this.ivH2 << 8 | this.ivL2;
    }
    
    public int getI() {
        return this.ivI;
    }
    
    public boolean getIFF1() {
        return this.ivIFF1;
    }
    
    public boolean getIFF2() {
        return this.ivIFF2;
    }
    
    public int getIntMode() {
        return this.ivIntMode;
    }
    
    public int getIX() {
        return this.ivIX;
    }
    
    public int getIXh() {
        return this.ivIX >>> 8;
    }
    
    public int getIXl() {
        return this.ivIX & 0xFF;
    }
    
    public int getIY() {
        return this.ivIY;
    }
    
    public int getIYh() {
        return this.ivIY >>> 8;
    }
    
    public int getIYl() {
        return this.ivIY & 0xFF;
    }
    
    public int getL() {
        return this.ivL;
    }
    
    public int getPC() {
        return this.ivPC;
    }
    
    public int getR() {
        return this.ivR7 | (this.ivR & 0x7F);
    }
    
    private int getReg012(final int pOpcode) {
        switch (pOpcode & 0x7) {
            case 0: {
                return this.ivB;
            }
            case 1: {
                return this.ivC;
            }
            case 2: {
                return this.ivD;
            }
            case 3: {
                return this.ivE;
            }
            case 4: {
                return this.ivH;
            }
            case 5: {
                return this.ivL;
            }
            case 6: {
                final int temp = this.ivIO.readMem(this.getHL());
                this.ivClk -= 3;
                return temp;
            }
            case 7: {
                return this.ivA;
            }
            default: {
                throw new RuntimeException("BUG: pOpcode = " + pOpcode);
            }
        }
    }
    
    private int getReg345(final int pOpcode) {
        switch (pOpcode & 0x38) {
            case 0: {
                return this.ivB;
            }
            case 8: {
                return this.ivC;
            }
            case 16: {
                return this.ivD;
            }
            case 24: {
                return this.ivE;
            }
            case 32: {
                return this.ivH;
            }
            case 40: {
                return this.ivL;
            }
            case 48: {
                return this.ivIO.readMem(this.getHL());
            }
            case 56: {
                return this.ivA;
            }
            default: {
                throw new RuntimeException("BUG: pOpcode = " + pOpcode);
            }
        }
    }
    
    public int getSP() {
        return this.ivSP;
    }
    
    public Z80Status getStatus() {
        final Z80Status result = new Z80Status();
        result.setBC(this.getBC());
        result.setDE(this.getDE());
        result.setHL(this.getHL());
        result.setAF(this.getAF());
        result.setBC2(this.getBC2());
        result.setDE2(this.getDE2());
        result.setHL2(this.getHL2());
        result.setAF2(this.getAF2());
        result.setIX(this.getIX());
        result.setIY(this.getIY());
        result.setSP(this.getSP());
        result.setPC(this.getPC());
        result.setI(this.getI());
        result.setR(this.getR());
        result.setIFF1(this.getIFF1());
        result.setIFF2(this.getIFF2());
        result.setIntMode(this.getIntMode());
        return result;
    }
    
    private int inC() {
        final int res = this.ivIO.readIO(this.getBC());
        this.ivClk -= 4;
        this.setF_N(false);
        this.setF_PV(this.parity8(res));
        this.setF_3((res & 0x8) != 0x0);
        this.setF_H(false);
        this.setF_5((res & 0x20) != 0x0);
        this.setF_Z(res == 0);
        this.setF_S(res >= 128);
        return res;
    }
    
    private int inc16(final int src) {
        return src + 1 & 0xFFFF;
    }
    
    private int inc8(final int src) {
        final int res = src + 1 & 0xFF;
        this.ivF = (this.ivFlagsInc8[res] | (this.ivF & 0x1));
        return res;
    }
    
    private void ind() {
        --this.ivClk;
        this.set_IHLI(this.get_IO_C());
        this.setB(this.getB() - 1 & 0xFF);
        this.setHL(this.dec16(this.getHL()));
        this.setF_N(true);
        this.setF_Z(this.getB() == 0);
    }
    
    private void ini() {
        --this.ivClk;
        this.set_IHLI(this.get_IO_C());
        this.setB(this.getB() - 1 & 0xFF);
        this.setHL(this.inc16(this.getHL()));
        this.setF_N(true);
        this.setF_Z(this.getB() == 0);
    }
    
    private static String intToHex(final int pValue, final int pTargetSize) {
        return Array.b2x(Array.toBytesMsbf(pValue, pTargetSize));
    }
    
    private void jp(final boolean pCC) {
        final int temp = this.get_NN();
        if (pCC) {
            this.ivPC = temp;
        }
    }
    
    private void jr(final boolean pCC) {
        int dis = (byte)this.ivIO.readMem(this.ivPC);
        this.ivClk -= 3;
        if (pCC) {
            this.readMem(this.ivPC, 5);
        }
        else {
            dis = 0;
        }
        this.ivPC = (this.ivPC + dis + 1 & 0xFFFF);
    }
    
    private void ldd() {
        this.set_IDEI(this.get_IHLI());
        this.readMem(this.getDE(), 2);
        this.setBC(this.dec16(this.getBC()));
        this.setDE(this.dec16(this.getDE()));
        this.setHL(this.dec16(this.getHL()));
        this.setF_N(false);
        this.setF_PV(this.getBC() != 0);
        this.setF_H(false);
    }
    
    private void ldi() {
        this.set_IDEI(this.get_IHLI());
        this.readMem(this.getDE(), 2);
        this.setBC(this.dec16(this.getBC()));
        this.setDE(this.inc16(this.getDE()));
        this.setHL(this.inc16(this.getHL()));
        this.setF_N(false);
        this.setF_PV(this.getBC() != 0);
        this.setF_H(false);
    }
    
    private int or8(final int src1, final int src2) {
        final int res = src1 | src2;
        this.ivF = this.ivFlagsAndOrXor8[res];
        return res;
    }
    
    private void outd() {
        --this.ivClk;
        this.set_IO_C(this.get_IHLI());
        this.setB(this.getB() - 1 & 0xFF);
        this.setHL(this.dec16(this.getHL()));
        this.setF_N(true);
        this.setF_Z(this.getB() == 0);
    }
    
    private void outi() {
        --this.ivClk;
        this.setB(this.getB() - 1 & 0xFF);
        this.set_IO_C(this.get_IHLI());
        this.setHL(this.inc16(this.getHL()));
        this.setF_N(true);
        this.setF_Z(this.getB() == 0);
    }
    
    private boolean parity8(int src) {
        src ^= src >> 4;
        src ^= src >> 2;
        src ^= src >> 1;
        return (src & 0x1) == 0x0;
    }
    
    public void performINT() {
        if (!this.getIFF1()) {
            return;
        }
        if (this.getHALT()) {
            this.setPC(this.getPC() + 1 & 0xFFFF);
        }
        this.setIFF1(false);
        this.setIFF2(false);
        if (this.ivIntMode == 0) {
            this.push(this.getPC());
            this.setPC(56);
            this.ivClk -= 7;
        }
        if (this.ivIntMode == 1) {
            this.push(this.getPC());
            this.setPC(56);
            this.ivClk -= 7;
        }
        if (this.ivIntMode == 2) {
            this.push(this.getPC());
            this.setPC(this.get_IADRI_w((this.getI() << 8) + 255));
            this.ivClk -= 7;
        }
    }
    
    public void performNMI() {
        if (this.getHALT()) {
            this.setPC(this.getPC() + 1 & 0xFFFF);
        }
        this.setIFF1(false);
        this.push(this.getPC());
        this.setPC(102);
        this.ivClk -= 5;
    }
    
    public void performReset() {
        this.setIFF1(false);
        this.setIFF2(false);
        this.setIntMode(0);
        this.setPC(0);
    }
    
    public int pop() {
        int res = this.ivIO.readMem(this.ivSP++);
        this.ivClk -= 3;
        res |= this.ivIO.readMem(this.ivSP++ & 0xFFFF) << 8;
        this.ivSP &= 0xFFFF;
        this.ivClk -= 3;
        return res;
    }
    
    public void push(final int pValue) {
        this.ivIO.writeMem(--this.ivSP & 0xFFFF, pValue >> 8);
        this.ivClk -= 3;
        final Z80IoHandler2 ivIO = this.ivIO;
        final int ivSP = this.ivSP - 1;
        this.ivSP = ivSP;
        ivIO.writeMem(this.ivSP = (ivSP & 0xFFFF), pValue & 0xFF);
        this.ivClk -= 3;
    }
    
    private int readMem(final int pAddress, final int pCycles) {
        int result = 0;
        for (int i = 0; i < pCycles; ++i) {
            result = this.ivIO.readMem(pAddress);
            --this.ivClk;
        }
        return result;
    }
    
    private int res(final int bit, final int src) {
        return src & (1 << bit ^ 0xFF);
    }
    
    private void ret() {
        this.setPC(this.pop());
    }
    
    private int rl8(final int src) {
        final int res = (src << 1 | (this.getF_C() ? 1 : 0)) & 0xFF;
        this.setF_C(src >= 128);
        this.setF_N(false);
        this.setF_PV(this.parity8(res));
        this.setF_3((res & 0x8) != 0x0);
        this.setF_H(false);
        this.setF_5((res & 0x20) != 0x0);
        this.setF_Z(res == 0);
        this.setF_S(res >= 128);
        return res;
    }
    
    private void rla() {
        final int res = this.getA() << 1 | (this.getF_C() ? 1 : 0);
        this.setF_C(res > 255);
        this.setF_H(false);
        this.setF_N(false);
        this.setA(res & 0xFF);
    }
    
    private int rlc8(final int src) {
        final int res = (src << 1 | ((src >= 128) ? 1 : 0)) & 0xFF;
        this.setF_C(src >= 128);
        this.setF_N(false);
        this.setF_PV(this.parity8(res));
        this.setF_3((res & 0x8) != 0x0);
        this.setF_H(false);
        this.setF_5((res & 0x20) != 0x0);
        this.setF_Z(res == 0);
        this.setF_S(res >= 128);
        return res;
    }
    
    private void rlca() {
        int res = this.getA() << 1;
        if (res > 255) {
            res -= 255;
        }
        this.setA(res);
        this.setF_C((res & 0x1) != 0x0);
        this.setF_H(false);
        this.setF_N(false);
    }
    
    private int rr8(final int src) {
        final int res = (src >> 1 | (this.getF_C() ? 128 : 0)) & 0xFF;
        this.setF_C((src & 0x1) == 0x1);
        this.setF_N(false);
        this.setF_PV(this.parity8(res));
        this.setF_3((res & 0x8) != 0x0);
        this.setF_H(false);
        this.setF_5((res & 0x20) != 0x0);
        this.setF_Z(res == 0);
        this.setF_S(res >= 128);
        return res;
    }
    
    private void rra() {
        final int src = this.getA();
        this.setA(src >> 1 | (this.getF_C() ? 128 : 0));
        this.setF_C((src & 0x1) != 0x0);
        this.setF_H(false);
        this.setF_N(false);
    }
    
    private int rrc8(final int src) {
        final int res = src >> 1 | (((src & 0x1) == 0x1) ? 128 : 0);
        this.setF_C(res >= 128);
        this.setF_N(false);
        this.setF_PV(this.parity8(res));
        this.setF_3((res & 0x8) != 0x0);
        this.setF_H(false);
        this.setF_5((res & 0x20) != 0x0);
        this.setF_Z(res == 0);
        this.setF_S(res >= 128);
        return res;
    }
    
    private void rrca() {
        final int src = this.getA();
        this.setA(src >> 1 | (((src & 0x1) != 0x0) ? 128 : 0));
        this.setF_C((src & 0x1) != 0x0);
        this.setF_H(false);
        this.setF_N(false);
    }
    
    private int sbc16(final int src1, final int src2) {
        final int res = src1 - src2 - (this.getF_C() ? 1 : 0);
        this.setF_C(res < 0);
        this.setF_N(true);
        this.setF_PV(((src1 ^ src2) & (src1 ^ res) & 0x8000) != 0x0);
        this.setF_3((res & 0x800) != 0x0);
        this.setF_H(((src1 ^ src2 ^ res) & 0x1000) != 0x0);
        this.setF_5((res & 0x2000) != 0x0);
        this.setF_Z((res & 0xFFFF) == 0x0);
        this.setF_S((res & 0x8000) != 0x0);
        return res & 0xFFFF;
    }
    
    private int sbc8(final int src1, final int src2) {
        final int res = src1 - src2 - (this.getF_C() ? 1 : 0);
        this.setF_C(res < 0);
        this.setF_N(true);
        this.setF_PV(((src1 ^ src2) & (src1 ^ res) & 0x80) != 0x0);
        this.setF_3((res & 0x8) != 0x0);
        this.setF_H(((src1 ^ src2 ^ res) & 0x10) != 0x0);
        this.setF_5((res & 0x20) != 0x0);
        this.setF_Z((res & 0xFF) == 0x0);
        this.setF_S((res & 0x80) != 0x0);
        return res & 0xFF;
    }
    
    private int set(final int bit, final int src) {
        return src | 1 << bit;
    }
    
    private void set_IADRI(final int pAddress, final int pValue) {
        this.ivIO.writeMem(pAddress, pValue);
        this.ivClk -= 3;
    }
    
    private void set_IBCI(final int pByte) {
        this.ivIO.writeMem(this.ivB << 8 | this.ivC, pByte);
        this.ivClk -= 3;
    }
    
    private void set_IDEI(final int pByte) {
        this.ivIO.writeMem(this.ivD << 8 | this.ivE, pByte);
        this.ivClk -= 3;
    }
    
    private void set_IHLI(final int pByte) {
        this.ivIO.writeMem(this.ivH << 8 | this.ivL, pByte);
        this.ivClk -= 3;
    }
    
    private void set_IIXDI(final int pByte) {
        final int adr = this.get_IXD();
        this.readMem(this.ivPC - 1 & 0xFFFF, 5);
        this.ivIO.writeMem(adr, pByte);
        this.ivClk -= 3;
    }
    
    private void set_IIYDI(final int pByte) {
        final int adr = this.get_IYD();
        this.readMem(this.ivPC - 1 & 0xFFFF, 5);
        this.ivIO.writeMem(adr, pByte);
        this.ivClk -= 3;
    }
    
    private void set_INNI(final int pByte) {
        final int nn = this.get_NN();
        this.ivIO.writeMem(nn, pByte);
        this.ivClk -= 3;
    }
    
    private void set_INNI_w(final int pValue) {
        final int nn = this.get_NN();
        this.ivIO.writeMem(nn, pValue & 0xFF);
        this.ivClk -= 3;
        this.ivIO.writeMem(nn + 1 & 0xFFFF, pValue >> 8);
        this.ivClk -= 3;
    }
    
    private void set_IO_C(final int pByte) {
        this.ivIO.writeIO(this.ivB << 8 | this.ivC, pByte);
        this.ivClk -= 4;
    }
    
    private void set_IO_N(final int pByte) {
        this.ivIO.writeIO(this.get_N() | this.ivA << 8, pByte);
        this.ivClk -= 4;
    }
    
    private void set_ISPI(final int pByte) {
        this.ivIO.writeMem(this.ivSP, pByte);
        this.ivClk -= 3;
    }
    
    public void setA(final int pValue) {
        this.ivA = pValue;
    }
    
    public void setAF(final int pValue) {
        this.ivA = pValue >>> 8;
        this.ivF = (pValue & 0xFF);
    }
    
    public void setAF2(final int pValue) {
        this.ivA2 = pValue >>> 8;
        this.ivF2 = (pValue & 0xFF);
    }
    
    public void setB(final int pValue) {
        this.ivB = pValue;
    }
    
    public void setBC(final int pValue) {
        this.ivB = pValue >>> 8;
        this.ivC = (pValue & 0xFF);
    }
    
    public void setBC2(final int pValue) {
        this.ivB2 = pValue >>> 8;
        this.ivC2 = (pValue & 0xFF);
    }
    
    public void setC(final int pValue) {
        this.ivC = pValue;
    }
    
    public void setD(final int pValue) {
        this.ivD = pValue;
    }
    
    public void setDE(final int pValue) {
        this.ivD = pValue >>> 8;
        this.ivE = (pValue & 0xFF);
    }
    
    public void setDE2(final int pValue) {
        this.ivD2 = pValue >>> 8;
        this.ivE2 = (pValue & 0xFF);
    }
    
    private void setDReg45AF(final int pOpcode, final int pValue) {
        switch (pOpcode & 0x30) {
            case 0: {
                this.setBC(pValue);
            }
            case 16: {
                this.setDE(pValue);
            }
            case 32: {
                this.setHL(pValue);
            }
            case 48: {
                this.setAF(pValue);
            }
            default: {
                throw new RuntimeException("BUG: pOpcode = " + pOpcode);
            }
        }
    }
    
    private void setDReg45SP(final int pOpcode, final int pValue) {
        switch (pOpcode & 0x30) {
            case 0: {
                this.setBC(pValue);
            }
            case 16: {
                this.setDE(pValue);
            }
            case 32: {
                this.setHL(pValue);
            }
            case 48: {
                this.setSP(pValue);
            }
            default: {
                throw new RuntimeException("BUG: pOpcode = " + pOpcode);
            }
        }
    }
    
    public void setE(final int pValue) {
        this.ivE = pValue;
    }
    
    public void setF(final int pValue) {
        this.ivF = pValue;
    }
    
    public void setF_3(final boolean pValue) {
        if (pValue) {
            this.ivF |= 0x8;
        }
        else {
            this.ivF &= 0xF7;
        }
    }
    
    public void setF_5(final boolean pValue) {
        if (pValue) {
            this.ivF |= 0x20;
        }
        else {
            this.ivF &= 0xDF;
        }
    }
    
    public void setF_C(final boolean pValue) {
        if (pValue) {
            this.ivF |= 0x1;
        }
        else {
            this.ivF &= 0xFE;
        }
    }
    
    public void setF_H(final boolean pValue) {
        if (pValue) {
            this.ivF |= 0x10;
        }
        else {
            this.ivF &= 0xEF;
        }
    }
    
    public void setF_N(final boolean pValue) {
        if (pValue) {
            this.ivF |= 0x2;
        }
        else {
            this.ivF &= 0xFD;
        }
    }
    
    public void setF_PV(final boolean pValue) {
        if (pValue) {
            this.ivF |= 0x4;
        }
        else {
            this.ivF &= 0xFB;
        }
    }
    
    public void setF_S(final boolean pValue) {
        if (pValue) {
            this.ivF |= 0x80;
        }
        else {
            this.ivF &= 0x7F;
        }
    }
    
    public void setF_Z(final boolean pValue) {
        if (pValue) {
            this.ivF |= 0x40;
        }
        else {
            this.ivF &= 0xBF;
        }
    }
    
    public void setH(final int pValue) {
        this.ivH = pValue;
    }
    
    public void setHL(final int pValue) {
        this.ivH = pValue >>> 8;
        this.ivL = (pValue & 0xFF);
    }
    
    public void setHL2(final int pValue) {
        this.ivH2 = pValue >>> 8;
        this.ivL2 = (pValue & 0xFF);
    }
    
    public void setI(final int pValue) {
        this.ivI = pValue;
    }
    
    public void setIFF1(final boolean pValue) {
        this.ivIFF1 = pValue;
    }
    
    public void setIFF2(final boolean pValue) {
        this.ivIFF2 = pValue;
    }
    
    public void setIntMode(final int pValue) {
        this.ivIntMode = pValue;
    }
    
    public void setIoHandler(final Z80IoHandler2 pIoHandler) {
        this.ivIO = pIoHandler;
    }
    
    public void setIX(final int pValue) {
        this.ivIX = pValue;
    }
    
    public void setIXh(final int pValue) {
        this.ivIX = ((this.ivIX & 0xFF) | pValue << 8);
    }
    
    public void setIXl(final int pValue) {
        this.ivIX = ((this.ivIX & 0xFF00) | pValue);
    }
    
    public void setIY(final int pValue) {
        this.ivIY = pValue;
    }
    
    public void setIYh(final int pValue) {
        this.ivIY = ((this.ivIY & 0xFF) | pValue << 8);
    }
    
    public void setIYl(final int pValue) {
        this.ivIY = ((this.ivIY & 0xFF00) | pValue);
    }
    
    public void setL(final int pValue) {
        this.ivL = pValue;
    }
    
    public void setPC(final int pValue) {
        this.ivPC = pValue;
    }
    
    public void setR(final int pValue) {
        this.ivR = pValue;
        this.ivR7 = (pValue & 0x80);
    }
    
    private void setReg012(final int pOpcode, final int pValue) {
        switch (pOpcode & 0x7) {
            case 0: {
                this.ivB = pValue;
            }
            case 1: {
                this.ivC = pValue;
            }
            case 2: {
                this.ivD = pValue;
            }
            case 3: {
                this.ivE = pValue;
            }
            case 4: {
                this.ivH = pValue;
            }
            case 5: {
                this.ivL = pValue;
            }
            case 6: {
                this.ivIO.writeMem(this.getHL(), pValue);
                this.ivClk -= 3;
            }
            case 7: {
                this.ivA = pValue;
            }
            default: {
                throw new RuntimeException("BUG: pOpcode = " + pOpcode);
            }
        }
    }
    
    private void setReg345(final int pOpcode, final int pValue) {
        switch (pOpcode & 0x38) {
            case 0: {
                this.ivB = pValue;
            }
            case 8: {
                this.ivC = pValue;
            }
            case 16: {
                this.ivD = pValue;
            }
            case 24: {
                this.ivE = pValue;
            }
            case 32: {
                this.ivH = pValue;
            }
            case 40: {
                this.ivL = pValue;
            }
            case 48: {
                this.ivIO.writeMem(this.getHL(), pValue);
                this.ivClk -= 3;
            }
            case 56: {
                this.ivA = pValue;
            }
            default: {
                throw new RuntimeException("BUG: pOpcode = " + pOpcode);
            }
        }
    }
    
    public void setSP(final int pValue) {
        this.ivSP = pValue;
    }
    
    public void setStatus(final Z80Status pStatus) {
        this.setBC(pStatus.getBC());
        this.setDE(pStatus.getDE());
        this.setHL(pStatus.getHL());
        this.setAF(pStatus.getAF());
        this.setBC2(pStatus.getBC2());
        this.setDE2(pStatus.getDE2());
        this.setHL2(pStatus.getHL2());
        this.setAF2(pStatus.getAF2());
        this.setIX(pStatus.getIX());
        this.setIY(pStatus.getIY());
        this.setSP(pStatus.getSP());
        this.setPC(pStatus.getPC());
        this.setI(pStatus.getI());
        this.setR(pStatus.getR());
        this.setIFF1(pStatus.getIFF1());
        this.setIFF2(pStatus.getIFF2());
        this.setIntMode(pStatus.getIntMode());
    }
    
    private int sla8(final int src) {
        final int res = src << 1 & 0xFF;
        this.setF_C(src >= 128);
        this.setF_N(false);
        this.setF_PV(this.parity8(res));
        this.setF_3((res & 0x8) != 0x0);
        this.setF_H(false);
        this.setF_5((res & 0x20) != 0x0);
        this.setF_Z(res == 0);
        this.setF_S(res >= 128);
        return res;
    }
    
    private int sll8(final int src) {
        final int res = (src << 1 | 0x1) & 0xFF;
        this.setF_C(src >= 128);
        this.setF_N(false);
        this.setF_PV(this.parity8(res));
        this.setF_3((res & 0x8) != 0x0);
        this.setF_H(false);
        this.setF_5((res & 0x20) != 0x0);
        this.setF_Z(res == 0);
        this.setF_S(res >= 128);
        return res;
    }
    
    private int slra8(final int src) {
        final int res = src << 1 & 0xFF;
        this.setF_C(src >= 128);
        this.setF_N(false);
        this.setF_PV(this.parity8(res));
        this.setF_3((res & 0x8) != 0x0);
        this.setF_H(false);
        this.setF_5((res & 0x20) != 0x0);
        this.setF_Z(res == 0);
        this.setF_S(res >= 128);
        return res;
    }
    
    private int sra8(final int src) {
        final int res = src >> 1 | (src & 0x80);
        this.setF_C((src & 0x1) == 0x1);
        this.setF_N(false);
        this.setF_PV(this.parity8(res));
        this.setF_3((res & 0x8) != 0x0);
        this.setF_H(false);
        this.setF_5((res & 0x20) != 0x0);
        this.setF_Z(res == 0);
        this.setF_S(res >= 128);
        return res;
    }
    
    private int srl8(final int src) {
        final int res = src >> 1;
        this.setF_C((src & 0x1) == 0x1);
        this.setF_N(false);
        this.setF_PV(this.parity8(res));
        this.setF_3((res & 0x8) != 0x0);
        this.setF_H(false);
        this.setF_5((res & 0x20) != 0x0);
        this.setF_Z(res == 0);
        this.setF_S(res >= 128);
        return res;
    }
    
    private int sub8(final int src1, final int src2) {
        final int res = src1 - src2;
        this.setF_C(res < 0);
        this.setF_N(true);
        this.setF_PV(((src1 ^ src2) & (src1 ^ res) & 0x80) != 0x0);
        this.setF_3((res & 0x8) != 0x0);
        this.setF_H(((src1 ^ src2 ^ res) & 0x10) != 0x0);
        this.setF_5((res & 0x20) != 0x0);
        this.setF_Z((res & 0xFF) == 0x0);
        this.setF_S((res & 0x80) != 0x0);
        return res & 0xFF;
    }
    
    public void subtractClk(final long pCycles) {
        this.ivClkOffset -= pCycles;
        this.addedCycles += pCycles;
    }
    
    public void terminate() {
        this.ivIO = null;
    }
    
    @Override
    public String toString() {
        String r = "";
        r = "AF=" + intToHex(this.getAF(), 2) + ", AF'=" + intToHex(this.getAF2(), 2) + "\n";
        r = String.valueOf(r) + "BC=" + intToHex(this.getBC(), 2) + ", BC'=" + intToHex(this.getBC2(), 2) + "\n";
        r = String.valueOf(r) + "DE=" + intToHex(this.getDE(), 2) + ", DE'=" + intToHex(this.getDE2(), 2) + "\n";
        r = String.valueOf(r) + "HL=" + intToHex(this.getHL(), 2) + ", HL'=" + intToHex(this.getHL2(), 2) + "\n";
        r = String.valueOf(r) + "IX=" + intToHex(this.getIX(), 2) + ", IY=" + intToHex(this.getIY(), 2) + "\n";
        r = String.valueOf(r) + "SP=" + intToHex(this.getSP(), 2) + ", PC=" + intToHex(this.getPC(), 2) + "\n";
        r = String.valueOf(r) + "I=" + intToHex(this.getI(), 1) + ", R=" + intToHex(this.getR(), 1) + "\n";
        r = String.valueOf(r) + "IMODE=" + this.getIntMode() + ", IFF1=" + this.getIFF1() + ", IFF2=" + this.getIFF2() + "\n";
        return r;
    }
    
    private int xor8(final int src1, final int src2) {
        final int res = src1 ^ src2;
        this.ivF = this.ivFlagsAndOrXor8[res];
        return res;
    }
}
