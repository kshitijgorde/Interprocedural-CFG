// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.components;

public class PD765AStatus extends ComponentStatus
{
    public int ivSt0;
    public int ivSt1;
    public int ivSt2;
    public int[] ivStack;
    public int ivStackPointer;
    public int ivCommand;
    public int ivPhase;
    public PD765ASector ivSector;
    public int ivSectorPos;
    public int ivSectorCount;
    public boolean ivSK;
    public int ivHD;
    public int ivUS;
    public int ivC;
    public int ivH;
    public int ivR;
    public int ivN;
    public int ivSC;
    public int ivD;
    
    public PD765AStatus() {
        this.ivStack = new int[13];
    }
}
