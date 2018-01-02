// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

public class IECBus
{
    private int iecData;
    private boolean iecATN;
    private int iecClk;
    private int bit;
    private int max;
    private DiskListener listener;
    
    public IECBus() {
        this.iecData = 0;
        this.iecATN = false;
        this.iecClk = 0;
        this.bit = 1;
        this.max = 0;
    }
    
    public void setListener(final DiskListener listener) {
        this.listener = listener;
    }
    
    public int getIECID() {
        final int bit = this.bit;
        this.max |= this.bit;
        this.bit <<= 1;
        return bit;
    }
    
    public void setDataHi(final int n, final boolean b) {
        if (b) {
            this.iecData |= n;
        }
        else {
            this.iecData &= ~n;
        }
    }
    
    public void setClockHi(final int n, final boolean b) {
        if (b) {
            this.iecClk |= n;
        }
        else {
            this.iecClk &= ~n;
        }
    }
    
    public void setATNHi(final int n, final boolean iecATN) {
        final boolean iecATN2 = this.iecATN;
        this.iecATN = iecATN;
        if (this.listener != null && iecATN2 != this.iecATN) {
            this.listener.atnChanged(iecATN);
        }
    }
    
    public boolean isClockLo() {
        return this.iecClk != this.max;
    }
    
    public boolean isDataHi() {
        return this.iecData != 0;
    }
    
    public boolean isDataLo() {
        return this.iecData != this.max;
    }
    
    public boolean isATNHi() {
        return this.iecATN;
    }
    
    public void printSerial() {
        System.out.print("IEC: ");
        if (this.isATNHi()) {
            System.out.print("A1");
        }
        else {
            System.out.print("A0");
        }
        System.out.print(" C" + (((this.iecClk & 0x1) != 0x0) ? 1 : 0));
        System.out.print(" D" + (((this.iecData & 0x1) != 0x0) ? 1 : 0));
        System.out.print(" c" + (((this.iecClk & 0x2) != 0x0) ? 1 : 0));
        System.out.print(" d" + (((this.iecData & 0x2) != 0x0) ? 1 : 0));
        System.out.println(" => C" + (this.isClockLo() ? 0 : 1) + " D" + (this.isDataLo() ? 0 : 1));
    }
}
