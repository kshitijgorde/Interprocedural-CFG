// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.components;

public class PD765ASector
{
    private int ivC;
    private int ivH;
    private int ivR;
    private int ivN;
    private int ivSt1;
    private int ivSt2;
    private byte[] ivData;
    
    public int getC() {
        return this.ivC;
    }
    
    public byte[] getData() {
        return this.ivData;
    }
    
    public int getH() {
        return this.ivH;
    }
    
    public int getN() {
        return this.ivN;
    }
    
    public int getR() {
        return this.ivR;
    }
    
    public int getSt1() {
        return this.ivSt1;
    }
    
    public int getSt2() {
        return this.ivSt2;
    }
    
    public void setC(final int pC) {
        this.ivC = pC;
    }
    
    public void setData(final byte[] pData) {
        this.ivData = pData;
    }
    
    public void setH(final int pH) {
        this.ivH = pH;
    }
    
    public void setN(final int pN) {
        this.ivN = pN;
    }
    
    public void setR(final int pR) {
        this.ivR = pR;
    }
    
    public void setSt1(final int pSt1) {
        this.ivSt1 = pSt1;
    }
    
    public void setSt2(final int pSt2) {
        this.ivSt2 = pSt2;
    }
    
    @Override
    public String toString() {
        return "Sector: C=" + this.ivC + ",H=" + this.ivH + ",R=" + this.ivR + ",N=" + this.ivN + ",ST1=" + this.ivSt1 + ",ST2=" + this.ivSt2 + ", Length=" + this.ivData.length;
    }
}
