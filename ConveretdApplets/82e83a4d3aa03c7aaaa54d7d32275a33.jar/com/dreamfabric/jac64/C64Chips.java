// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

public abstract class C64Chips
{
    public static final boolean DEBUG_INTERRUPS = false;
    public static final int VIC_IRQ = 1;
    public static final int CIA_TIMER_IRQ = 2;
    public static final int KEYBOARD_NMI = 1;
    public static final int CIA_TIMER_NMI = 2;
    private int nmiFlags;
    private int oldNmiFlags;
    private int irqFlags;
    private int oldIrqFlags;
    private Observer observer;
    MOS6510Core cpu;
    
    public C64Chips() {
        this.nmiFlags = 0;
        this.oldNmiFlags = 0;
        this.irqFlags = 0;
        this.oldIrqFlags = 0;
    }
    
    public void init(final MOS6510Core cpu) {
        this.cpu = cpu;
    }
    
    public int getNMIFlags() {
        return this.nmiFlags;
    }
    
    public int getIRQFlags() {
        return this.irqFlags;
    }
    
    public boolean setIRQ(final int n) {
        final boolean b = (this.irqFlags & n) == 0x0;
        this.irqFlags |= n;
        if (this.irqFlags != this.oldIrqFlags) {
            this.cpu.setIRQLow(this.irqFlags != 0);
            this.oldIrqFlags = this.irqFlags;
        }
        return b;
    }
    
    public void clearIRQ(final int n) {
        this.irqFlags &= ~n;
        if (this.irqFlags != this.oldIrqFlags) {
            this.cpu.setIRQLow(this.irqFlags != 0);
            this.oldIrqFlags = this.irqFlags;
        }
    }
    
    public boolean setNMI(final int n) {
        final boolean b = (this.nmiFlags & n) == 0x0;
        this.nmiFlags |= n;
        if (this.nmiFlags != this.oldNmiFlags) {
            this.cpu.setNMILow(this.nmiFlags != 0);
            this.oldNmiFlags = this.nmiFlags;
        }
        return b;
    }
    
    public void clearNMI(final int n) {
        this.nmiFlags &= ~n;
        if (this.nmiFlags != this.oldNmiFlags) {
            this.cpu.setNMILow(this.nmiFlags != 0);
            this.oldNmiFlags = this.nmiFlags;
        }
    }
    
    public abstract void reset();
    
    public abstract int performRead(final int p0, final long p1);
    
    public abstract void performWrite(final int p0, final int p1, final long p2);
    
    public abstract void updateChips(final long p0);
    
    public void setObserver(final Observer observer) {
        this.observer = observer;
    }
    
    public void update(final Object o, final Object o2) {
        if (this.observer != null) {
            this.observer.update(o, o2);
        }
    }
}
