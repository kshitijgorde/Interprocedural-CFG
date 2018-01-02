// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.z80;

import com.tn.util.Array;
import com.tn.components.ComponentStatus;

public class Z80Status extends ComponentStatus
{
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
    
    public static Z80Status get(final Z80A pZ80) {
        final Z80Status result = new Z80Status();
        result.setBC(pZ80.getBC());
        result.setDE(pZ80.getDE());
        result.setHL(pZ80.getHL());
        result.setAF(pZ80.getAF());
        result.setBC2(pZ80.getBC2());
        result.setDE2(pZ80.getDE2());
        result.setHL2(pZ80.getHL2());
        result.setAF2(pZ80.getAF2());
        result.setIX(pZ80.getIX());
        result.setIY(pZ80.getIY());
        result.setSP(pZ80.getSP());
        result.setPC(pZ80.getPC());
        result.setI(pZ80.getI());
        result.setR(pZ80.getR());
        result.setIFF1(pZ80.getIFF1());
        result.setIFF2(pZ80.getIFF2());
        result.setIntMode(pZ80.getIntMode());
        return result;
    }
    
    public int getA() {
        return this.ivA;
    }
    
    public int getA2() {
        return this.ivA2;
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
    
    public int getD() {
        return this.ivD;
    }
    
    public int getDE() {
        return this.ivD << 8 | this.ivE;
    }
    
    public int getDE2() {
        return this.ivD2 << 8 | this.ivE2;
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
    
    public int getF2() {
        return this.ivF2;
    }
    
    public int getH() {
        return this.ivH;
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
    
    public int getSP() {
        return this.ivSP;
    }
    
    private static String intToHex(final int pValue, final int pTargetSize) {
        return Array.b2x(Array.toBytesMsbf(pValue, pTargetSize));
    }
    
    public static void set(final Z80Status pStatus, final Z80A pZ80) {
        pZ80.setBC(pStatus.getBC());
        pZ80.setDE(pStatus.getDE());
        pZ80.setHL(pStatus.getHL());
        pZ80.setAF(pStatus.getAF());
        pZ80.setBC2(pStatus.getBC2());
        pZ80.setDE2(pStatus.getDE2());
        pZ80.setHL2(pStatus.getHL2());
        pZ80.setAF2(pStatus.getAF2());
        pZ80.setIX(pStatus.getIX());
        pZ80.setIY(pStatus.getIY());
        pZ80.setSP(pStatus.getSP());
        pZ80.setPC(pStatus.getPC());
        pZ80.setI(pStatus.getI());
        pZ80.setR(pStatus.getR());
        pZ80.setIFF1(pStatus.getIFF1());
        pZ80.setIFF2(pStatus.getIFF2());
        pZ80.setIntMode(pStatus.getIntMode());
    }
    
    public void setA(final int pValue) {
        this.ivA = pValue;
    }
    
    public void setA2(final int pValue) {
        this.ivA2 = pValue;
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
    
    public void setF2(final int pValue) {
        this.ivF2 = pValue;
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
    
    public void setSP(final int pValue) {
        this.ivSP = pValue;
    }
    
    @Override
    public String toString() {
        String r = "";
        r = "A=" + intToHex(this.getA(), 1) + ", F=" + intToHex(this.getF(), 1) + ", AF=" + intToHex(this.getAF(), 2) + "\n";
        r = String.valueOf(r) + "B=" + intToHex(this.getB(), 1) + ", C=" + intToHex(this.getC(), 1) + ", BC=" + intToHex(this.getBC(), 2) + "\n";
        r = String.valueOf(r) + "D=" + intToHex(this.getD(), 1) + ", E=" + intToHex(this.getE(), 1) + ", DE=" + intToHex(this.getDE(), 2) + "\n";
        r = String.valueOf(r) + "H=" + intToHex(this.getH(), 1) + ", L=" + intToHex(this.getL(), 1) + ", HL=" + intToHex(this.getHL(), 2) + "\n";
        r = String.valueOf(r) + "IX=" + intToHex(this.getIX(), 2) + ", IY=" + intToHex(this.getIY(), 2) + "\n";
        r = String.valueOf(r) + "SP=" + intToHex(this.getSP(), 2) + ", PC=" + intToHex(this.getPC(), 2) + "\n";
        r = String.valueOf(r) + "I=" + intToHex(this.getI(), 1) + ", R=" + intToHex(this.getR(), 1) + "\n";
        r = String.valueOf(r) + "IMODE=" + this.getIntMode() + ", IFF1=" + this.getIFF1() + ", IFF2=" + this.getIFF2() + "\n";
        return r;
    }
}
