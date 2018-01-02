// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.InputStream;

public abstract class MOS6510Core extends MOS6510Ops
{
    protected int[] memory;
    public static final int NMI_INT = 1;
    public static final int IRQ_INT = 2;
    protected PatchListener list;
    protected C64Chips chips;
    protected IMonitor monitor;
    public String codebase;
    public boolean checkInterrupt;
    public boolean NMILow;
    public boolean NMILastLow;
    public boolean IRQLow;
    public int lastInterrupt;
    private boolean pageboundary;
    public boolean busAvailable;
    public long baLowUntil;
    boolean sign;
    boolean zero;
    boolean overflow;
    boolean carry;
    boolean decimal;
    boolean brk;
    protected int acc;
    protected int x;
    protected int y;
    protected int s;
    protected long nmiCycleStart;
    protected long irqCycleStart;
    private String[] debugInfo;
    protected int jumpTo;
    public long cycles;
    protected long lastMillis;
    protected long nr_ins;
    protected long nr_irq;
    protected long start;
    protected int pc;
    protected int interruptInExec;
    protected boolean disableInterupt;
    protected int rindex;
    protected int lastReadOP;
    
    public MOS6510Core(final IMonitor monitor, final String codebase) {
        this.chips = null;
        this.checkInterrupt = false;
        this.NMILow = false;
        this.NMILastLow = false;
        this.IRQLow = false;
        this.lastInterrupt = 0;
        this.pageboundary = false;
        this.busAvailable = true;
        this.baLowUntil = 0L;
        this.sign = false;
        this.zero = false;
        this.overflow = false;
        this.carry = false;
        this.decimal = false;
        this.brk = false;
        this.acc = 0;
        this.x = 0;
        this.y = 0;
        this.s = 255;
        this.nmiCycleStart = 0L;
        this.irqCycleStart = 0L;
        this.jumpTo = -1;
        this.cycles = 0L;
        this.lastMillis = 0L;
        this.nr_ins = 0L;
        this.nr_irq = 0L;
        this.start = System.currentTimeMillis();
        this.interruptInExec = 0;
        this.disableInterupt = false;
        this.rindex = 0;
        this.lastReadOP = 0;
        this.monitor = monitor;
        this.codebase = codebase;
    }
    
    public int[] getMemory() {
        return this.memory;
    }
    
    public void stealCycles(final int n) {
        this.cycles += n;
    }
    
    public void jump(final int jumpTo) {
        this.jumpTo = jumpTo;
    }
    
    public long getCycles() {
        return this.cycles;
    }
    
    public void setIRQLow(final boolean irqLow) {
        if (!this.IRQLow && irqLow) {
            this.checkInterrupt = true;
            this.irqCycleStart = this.cycles + 2L;
        }
        this.IRQLow = irqLow;
    }
    
    public void setNMILow(final boolean b) {
        if (!this.NMILow && b) {
            this.checkInterrupt = true;
            this.nmiCycleStart = this.cycles + 2L;
        }
        if (!(this.NMILow = b)) {
            this.NMILastLow = b;
        }
    }
    
    public int getSP() {
        return this.s;
    }
    
    private final void doInterrupt(final int n, final int n2) {
        this.fetchByte(this.pc);
        ++this.cycles;
        this.fetchByte(this.pc + 1);
        ++this.cycles;
        this.push((this.pc & 0xFF00) >> 8);
        this.push(this.pc & 0xFF);
        this.push(n2);
        ++this.interruptInExec;
        this.pc = this.fetchByte(n + 1) << 8;
        ++this.cycles;
        this.pc += this.fetchByte(n);
        ++this.cycles;
    }
    
    protected final int getStatusByte() {
        return (this.carry ? 1 : 0) + (this.zero ? 2 : 0) + (this.disableInterupt ? 4 : 0) + (this.decimal ? 8 : 0) + (this.brk ? 16 : 0) + 32 + (this.overflow ? 64 : 0) + (this.sign ? 128 : 0);
    }
    
    private final void setStatusByte(final int n) {
        this.carry = ((n & 0x1) != 0x0);
        this.zero = ((n & 0x2) != 0x0);
        this.disableInterupt = ((n & 0x4) != 0x0);
        this.decimal = ((n & 0x8) != 0x0);
        this.brk = ((n & 0x10) != 0x0);
        this.overflow = ((n & 0x40) != 0x0);
        this.sign = ((n & 0x80) != 0x0);
    }
    
    protected abstract int fetchByte(final int p0);
    
    protected abstract void writeByte(final int p0, final int p1);
    
    private final void setZS(final int n) {
        this.zero = (n == 0);
        this.sign = (n > 127);
    }
    
    private final void setCarry(final int n) {
        this.carry = (n > 127);
    }
    
    private final int pop() {
        final int s = this.s + 1 & 0xFF;
        this.s = s;
        final int fetchByte = this.fetchByte(s | 0x100);
        ++this.cycles;
        return fetchByte;
    }
    
    private final void push(final int n) {
        this.writeByte((this.s & 0xFF) | 0x100, n);
        this.s = (this.s - 1 & 0xFF);
        ++this.cycles;
    }
    
    private final void opADCimp(final int n) {
        int n2 = n + this.acc + (this.carry ? 1 : 0);
        this.zero = ((n2 & 0xFF) == 0x0);
        if (this.decimal) {
            int n3 = (this.acc & 0xF) + (n & 0xF) + (this.carry ? 1 : 0);
            if (n3 > 9) {
                n3 += 6;
            }
            if (n3 <= 15) {
                n2 = (n3 & 0xF) + (this.acc & 0xF0) + (n & 0xF0);
            }
            else {
                n2 = (n3 & 0xF) + (this.acc & 0xF0) + (n & 0xF0) + 16;
            }
            this.overflow = (((this.acc ^ n) & 0x80) == 0x0 && ((this.acc ^ n2) & 0x80) != 0x0);
            this.sign = ((n2 & 0x80) > 0);
            if ((n2 & 0x1F0) > 144) {
                n2 += 96;
            }
            this.carry = (n2 > 153);
        }
        else {
            this.overflow = (((this.acc ^ n) & 0x80) == 0x0 && ((this.acc ^ n2) & 0x80) != 0x0);
            this.carry = (n2 > 255);
            this.sign = ((n2 & 0x80) > 0);
        }
        this.acc = (n2 & 0xFF);
    }
    
    private final void branch(final boolean b, final int pc, final int n) {
        if (b) {
            final int pc2 = this.pc;
            this.pc = pc;
            if (n == 1) {
                this.fetchByte(this.pc);
                ++this.cycles;
            }
            else {
                if (this.pc < pc2) {
                    this.fetchByte(this.pc + 256);
                }
                else {
                    this.fetchByte(this.pc - 256);
                }
                ++this.cycles;
                this.fetchByte(this.pc);
                ++this.cycles;
            }
        }
    }
    
    private final void opSBCimp(final int n) {
        final int n2 = this.acc - n - (this.carry ? 0 : 1);
        final boolean carry = n2 >= 0;
        int n3 = n2 & 0x1FF;
        this.sign = ((n3 & 0x80) == 0x80);
        this.zero = ((n3 & 0xFF) == 0x0);
        this.overflow = (((this.acc ^ n3) & 0x80) != 0x0 && ((this.acc ^ n) & 0x80) != 0x0);
        if (this.decimal) {
            final int n4 = (this.acc & 0xF) - (n & 0xF) - (this.carry ? 0 : 1);
            if ((n4 & 0x10) > 0) {
                n3 = ((n4 - 6 & 0xF) | (this.acc & 0xF0) - (n & 0xF0) - 16);
            }
            else {
                n3 = ((n4 & 0xF) | (this.acc & 0xF0) - (n & 0xF0));
            }
            if ((n3 & 0x100) > 0) {
                n3 -= 96;
            }
        }
        this.acc = (n3 & 0xFF);
        this.carry = carry;
    }
    
    public void emulateOp() {
        if (this.checkInterrupt) {
            if (this.NMILow && !this.NMILastLow && this.cycles > this.nmiCycleStart) {
                this.lastInterrupt = 1;
                this.doInterrupt(65530, this.getStatusByte() & 0xEF);
                this.disableInterupt = true;
                this.NMILastLow = this.NMILow;
                return;
            }
            if ((this.IRQLow && this.cycles > this.irqCycleStart) || this.brk) {
                if (!this.disableInterupt) {
                    this.lastInterrupt = 2;
                    final int statusByte = this.getStatusByte();
                    int n;
                    if (this.brk) {
                        n = (statusByte | 0x10);
                        ++this.pc;
                    }
                    else {
                        n = (statusByte & 0xEF);
                    }
                    this.doInterrupt(65534, n);
                    this.disableInterupt = true;
                    this.brk = false;
                    return;
                }
                this.brk = false;
                this.checkInterrupt = (this.NMILow && !this.NMILastLow);
            }
            else if (this.jumpTo != -1) {
                this.pc = this.jumpTo;
                this.jumpTo = -1;
            }
        }
        int n2 = MOS6510Core.INSTRUCTION_SET[this.fetchByte(this.pc++)];
        final int n3 = n2 & 0xFF;
        final int n4 = n2 & 0xF00;
        final boolean b = (n2 & 0x1000) != 0x0;
        int n5 = ((n2 & 0x2000) != 0x0) ? 1 : 0;
        int n6 = 0;
        int n7 = 0;
        this.lastReadOP = this.rindex;
        ++this.cycles;
        int fetchByte = this.fetchByte(this.pc);
        ++this.cycles;
        switch (n4) {
            case 256: {
                ++this.pc;
                n2 = fetchByte;
                break;
            }
            case 768: {
                ++this.pc;
                n6 = (this.fetchByte(this.pc++) << 8) + fetchByte;
                ++this.cycles;
                if (b) {
                    n2 = this.fetchByte(n6);
                    ++this.cycles;
                    break;
                }
                break;
            }
            case 512: {
                ++this.pc;
                n6 = fetchByte;
                if (b) {
                    n2 = this.fetchByte(n6);
                    ++this.cycles;
                    break;
                }
                break;
            }
            case 1024:
            case 1280: {
                ++this.pc;
                this.fetchByte(fetchByte);
                ++this.cycles;
                if (n4 == 1024) {
                    n6 = (fetchByte + this.x & 0xFF);
                }
                else {
                    n6 = (fetchByte + this.y & 0xFF);
                }
                if (b) {
                    n2 = this.fetchByte(n6);
                    ++this.cycles;
                    break;
                }
                break;
            }
            case 1536:
            case 1792: {
                ++this.pc;
                final int n8 = this.fetchByte(this.pc++) << 8;
                ++this.cycles;
                if (n4 == 1536) {
                    fetchByte += this.x;
                }
                else {
                    fetchByte += this.y;
                }
                n2 = this.fetchByte(n8 + (fetchByte & 0xFF));
                ++this.cycles;
                n6 = n8 + fetchByte;
                if (b && (fetchByte > 255 || n5 != 0)) {
                    n2 = this.fetchByte(n6);
                    ++this.cycles;
                    break;
                }
                break;
            }
            case 2048: {
                ++this.pc;
                n6 = this.pc + (byte)fetchByte;
                if (((n6 ^ this.pc) & 0xFF00) > 0) {
                    n7 = 2;
                    break;
                }
                n7 = 1;
                break;
            }
            case 2816: {
                n2 = this.acc;
                n5 = 0;
                break;
            }
            case 2304: {
                ++this.pc;
                this.fetchByte(fetchByte);
                n7 = (fetchByte + this.x & 0xFF);
                ++this.cycles;
                final int n9 = this.fetchByte(n7 + 1) << 8;
                ++this.cycles;
                n6 = (n9 | this.fetchByte(n7));
                ++this.cycles;
                if (b) {
                    n2 = this.fetchByte(n6);
                    ++this.cycles;
                    break;
                }
                break;
            }
            case 2560: {
                ++this.pc;
                final int n10 = this.fetchByte(fetchByte + 1) << 8;
                ++this.cycles;
                final int fetchByte2 = this.fetchByte(fetchByte);
                ++this.cycles;
                fetchByte = fetchByte2 + this.y;
                n2 = this.fetchByte(n10 + (fetchByte & 0xFF));
                n6 = n10 + fetchByte;
                ++this.cycles;
                if (b && (fetchByte > 255 || n5 != 0)) {
                    n2 = this.fetchByte(n6);
                    ++this.cycles;
                    break;
                }
                break;
            }
            case 3072: {
                ++this.pc;
                final int n11 = (this.fetchByte(this.pc) << 8) + fetchByte;
                ++this.cycles;
                n7 = ((n11 & 0xFFF00) | (n11 + 1 & 0xFF));
                final int fetchByte3 = this.fetchByte(n11);
                ++this.cycles;
                n6 = fetchByte3 + (this.fetchByte(n7) << 8);
                ++this.cycles;
                break;
            }
        }
        if (b && n5 != 0) {
            this.writeByte(n6, n2);
            ++this.cycles;
        }
        switch (n3) {
            case 0: {
                this.brk = true;
                this.checkInterrupt = true;
                System.err.println("Break... at " + Integer.toString(this.pc, 16) + ": " + this.cycles);
                this.monitor.info("Break... at " + Integer.toString(this.pc, 16) + ": " + this.cycles);
                try {
                    Thread.sleep(1000L);
                }
                catch (Exception ex) {}
                break;
            }
            case 11: {
                this.setZS(this.acc &= n2);
                break;
            }
            case 28: {
                this.opADCimp(n2);
                break;
            }
            case 70: {
                this.opSBCimp(n2);
                break;
            }
            case 1: {
                this.setZS(this.acc |= n2);
                break;
            }
            case 19: {
                this.setZS(this.acc ^= n2);
                break;
            }
            case 13: {
                this.sign = (n2 > 127);
                this.overflow = ((n2 & 0x40) > 0);
                this.zero = ((this.acc & n2) == 0x0);
                break;
            }
            case 21: {
                this.carry = ((n2 & 0x1) != 0x0);
                n2 >>= 1;
                this.zero = (n2 == 0);
                this.sign = false;
                break;
            }
            case 14: {
                final int n12 = (n2 << 1) + (this.carry ? 1 : 0);
                this.carry = ((n12 & 0x100) != 0x0);
                n2 = (n12 & 0xFF);
                this.setZS(n2);
                break;
            }
            case 30: {
                final boolean carry = (n2 & 0x1) != 0x0;
                n2 = (n2 >> 1) + (this.carry ? 128 : 0);
                this.carry = carry;
                this.setZS(n2);
                break;
            }
            case 40: {
                this.setZS(this.acc = this.x);
                break;
            }
            case 53: {
                this.setZS(this.x = this.acc);
                break;
            }
            case 44: {
                this.setZS(this.acc = this.y);
                break;
            }
            case 55: {
                this.setZS(this.y = this.acc);
                break;
            }
            case 58: {
                this.setZS(this.x = this.s);
                break;
            }
            case 45: {
                this.s = (this.x & 0xFF);
                break;
            }
            case 63: {
                n2 = (n2 - 1 & 0xFF);
                this.setZS(n2);
                break;
            }
            case 72: {
                n2 = (n2 + 1 & 0xFF);
                this.setZS(n2);
                break;
            }
            case 73: {
                this.setZS(this.x = (this.x + 1 & 0xFF));
                break;
            }
            case 65: {
                this.setZS(this.x = (this.x - 1 & 0xFF));
                break;
            }
            case 64: {
                this.setZS(this.y = (this.y + 1 & 0xFF));
                break;
            }
            case 39: {
                this.setZS(this.y = (this.y - 1 & 0xFF));
                break;
            }
            case 10: {
                ++this.pc;
                n6 = (this.fetchByte(this.pc) << 8) + fetchByte;
                ++this.cycles;
                this.fetchByte(this.s | 0x100);
                ++this.cycles;
                this.push((this.pc & 0xFF00) >> 8);
                this.push(this.pc & 0xFF);
                this.pc = n6;
                break;
            }
            case 24: {
                this.pc = n6;
                break;
            }
            case 27: {
                this.fetchByte(this.s | 0x100);
                ++this.cycles;
                this.pc = this.pop() + (this.pop() << 8);
                this.fetchByte(++this.pc);
                ++this.cycles;
                break;
            }
            case 18: {
                this.fetchByte(this.s | 0x100);
                ++this.cycles;
                this.setStatusByte(this.pop());
                this.pc = this.pop() + (this.pop() << 8);
                this.brk = false;
                --this.interruptInExec;
                this.checkInterrupt = true;
                break;
            }
            case 2: {
                this.monitor.info("TRAP Instruction executed");
            }
            case 5: {
                this.setCarry(n2);
                n2 = (n2 << 1 & 0xFF);
                this.setZS(n2);
                break;
            }
            case 22: {
                this.push(this.acc);
                break;
            }
            case 31: {
                this.fetchByte(this.s | 0x100);
                ++this.cycles;
                this.setZS(this.acc = this.pop());
                break;
            }
            case 6: {
                this.brk = true;
                this.push(this.getStatusByte());
                this.brk = false;
                break;
            }
            case 15: {
                this.setStatusByte(this.pop());
                this.brk = false;
                this.checkInterrupt = true;
                break;
            }
            case 7: {
                this.setZS(this.acc &= n2);
                this.carry = ((this.acc & 0x80) != 0x0);
                break;
            }
            case 61: {
                n2 = this.acc - n2;
                this.carry = (n2 >= 0);
                this.setZS(n2 & 0xFF);
                break;
            }
            case 69: {
                n2 = this.x - n2;
                this.carry = (n2 >= 0);
                this.setZS(n2 & 0xFF);
                break;
            }
            case 60: {
                n2 = this.y - n2;
                this.carry = (n2 >= 0);
                this.setZS(n2 & 0xFF);
                break;
            }
            case 42: {
                this.branch(!this.carry, n6, n7);
                break;
            }
            case 56: {
                this.branch(this.carry, n6, n7);
                break;
            }
            case 74: {
                this.branch(this.zero, n6, n7);
                break;
            }
            case 67: {
                this.branch(!this.zero, n6, n7);
                break;
            }
            case 25: {
                this.branch(!this.overflow, n6, n7);
                break;
            }
            case 33: {
                this.branch(this.overflow, n6, n7);
                break;
            }
            case 8: {
                this.branch(!this.sign, n6, n7);
                break;
            }
            case 16: {
                this.branch(this.sign, n6, n7);
                break;
            }
            case 9: {
                this.carry = false;
                break;
            }
            case 17: {
                this.carry = true;
                break;
            }
            case 68: {
                this.decimal = false;
                break;
            }
            case 75: {
                this.decimal = true;
                break;
            }
            case 57: {
                this.overflow = false;
                break;
            }
            case 34: {
                this.disableInterupt = true;
                break;
            }
            case 26: {
                this.disableInterupt = false;
                this.checkInterrupt = true;
                break;
            }
            case 50: {
                this.setZS(this.acc = n2);
                break;
            }
            case 51: {
                this.setZS(this.x = n2);
                break;
            }
            case 49: {
                this.setZS(this.y = n2);
                break;
            }
            case 36: {
                n2 = this.acc;
                break;
            }
            case 38: {
                n2 = this.x;
                break;
            }
            case 37: {
                n2 = this.y;
                break;
            }
            case 41: {
                this.setZS(this.acc = (fetchByte & this.x & (this.acc | 0xEE)));
                break;
            }
            case 32: {
                final int n13 = fetchByte & this.acc;
                this.acc = (this.carry ? (n13 >> 1 | 0x80) : (n13 >> 1));
                if (!this.decimal) {
                    this.setZS(this.acc);
                    this.carry = ((this.acc & 0x40) != 0x0);
                    this.overflow = (((this.acc & 0x40) ^ (this.acc & 0x20) << 1) != 0x0);
                    break;
                }
                this.sign = this.carry;
                this.zero = (this.acc == 0);
                this.overflow = (((n13 ^ this.acc) & 0x40) != 0x0);
                if ((n13 & 0xF) + (n13 & 0x1) > 5) {
                    this.acc = ((this.acc & 0xF0) | (this.acc + 6 & 0xF));
                }
                final boolean carry2 = (n13 + (n13 & 0x10) & 0x1F0) > 80;
                this.carry = carry2;
                if (carry2) {
                    this.acc += 96;
                    break;
                }
                break;
            }
            case 23: {
                this.acc &= n2;
                final boolean carry3 = (this.acc & 0x1) != 0x0;
                this.acc >>= 1;
                this.carry = carry3;
                this.setZS(this.acc);
                break;
            }
            case 62: {
                n2 = (n2 - 1 & 0xFF);
                this.setZS(n2);
                final int n14 = this.acc - n2;
                this.carry = (n14 >= 0);
                this.setZS(n14 & 0xFF);
                break;
            }
            case 71: {
                n2 = (n2 + 1 & 0xFF);
                this.opSBCimp(n2);
                break;
            }
            case 52: {
                final int n15 = n2;
                this.x = n15;
                this.setZS(this.acc = n15);
            }
            case 54: {
                final int n16 = (this.acc | 0xEE) & fetchByte;
                this.acc = n16;
                this.x = n16;
                this.setZS(this.acc);
                break;
            }
            case 12: {
                final int n17 = (n2 << 1) + (this.carry ? 1 : 0);
                this.carry = ((n17 & 0x100) != 0x0);
                n2 = (n17 & 0xFF);
                this.acc &= n2;
                this.zero = (this.acc == 0);
                this.sign = (this.acc > 127);
                break;
            }
            case 29: {
                final boolean carry4 = (n2 & 0x1) != 0x0;
                n2 = (n2 >> 1) + (this.carry ? 128 : 0);
                this.carry = carry4;
                this.opADCimp(n2);
                break;
            }
            case 66: {
                this.x = (this.acc & this.x) - fetchByte;
                this.carry = (this.x >= 0);
                this.setZS(this.x &= 0xFF);
                break;
            }
            case 43: {
                n2 = (this.acc & this.x & (n6 >> 8) + 1);
                break;
            }
            case 46: {
                n2 = (this.acc & this.x & (n6 >> 8) + 1);
                this.s = (this.acc & this.x);
                break;
            }
            case 48: {
                n2 = (this.x & (n6 >> 8) + 1);
                break;
            }
            case 47: {
                n2 = (this.y & (n6 >> 8) + 1);
                break;
            }
            case 35: {
                n2 = (this.acc & this.x);
                break;
            }
            case 20: {
                this.carry = ((n2 & 0x1) != 0x0);
                n2 >>= 1;
                this.setZS(this.acc ^= n2);
                break;
            }
            case 3: {
                this.setCarry(n2);
                n2 = (n2 << 1 & 0xFF);
                this.setZS(this.acc |= n2);
                break;
            }
            case 100: {
                if (this.acc == 0) {
                    this.monitor.info("**** LOAD FILE! ***** PC = " + Integer.toString(this.pc, 16) + " => " + Integer.toString(this.rindex, 16));
                }
                else {
                    this.monitor.info("**** VERIFY!    ***** PC = " + this.pc + " => " + this.rindex);
                }
                int n18 = this.memory[187] + (this.memory[188] << 8);
                final int n19;
                this.monitor.info("Filename len:" + (n19 = this.memory[183]));
                String string = "";
                for (int i = 0; i < n19; ++i) {
                    string += (char)this.memory[n18++];
                }
                final String string2 = string + '\n';
                final int n20 = this.memory[185];
                this.monitor.info("name = " + string2);
                this.monitor.info("Sec Address: " + n20);
                int n21 = -1;
                if (n20 == 0) {
                    n21 = this.memory[43] + (this.memory[44] << 8);
                }
                if (this.list != null && this.list.readFile(string2, n21)) {
                    this.acc = 0;
                }
                --this.pc;
                break;
            }
        }
        if (n5 != 0) {
            this.writeByte(n6, n2);
            ++this.cycles;
        }
        else if (n4 == 2816) {
            this.acc = n2;
        }
    }
    
    public void init(final C64Chips chips) {
        MOS6510Ops.init();
        this.installROMS();
        this.chips = chips;
    }
    
    protected abstract void installROMS();
    
    protected abstract void patchROM(final PatchListener p0);
    
    public void reset() {
        this.chips.reset();
        this.sign = false;
        this.zero = false;
        this.overflow = false;
        this.carry = false;
        this.decimal = false;
        this.brk = false;
        this.disableInterupt = false;
        this.interruptInExec = 0;
        this.rindex = 0;
        this.checkInterrupt = false;
        this.NMILow = false;
        this.NMILastLow = false;
        this.IRQLow = false;
        if (this.list != null) {
            this.patchROM(this.list);
        }
    }
    
    public void setDebug(final int n, final String s) {
        if (this.debugInfo == null) {
            this.debugInfo = new String[65536];
        }
        this.debugInfo[n & 0xFFFF] = s;
    }
    
    public String getDebug(final int n) {
        if (this.debugInfo != null) {
            return this.debugInfo[n & 0xFFFF];
        }
        return null;
    }
    
    protected void loadROM(final InputStream inputStream, final int n, final int n2) {
        try {
            final DataInputStream dataInputStream = new DataInputStream(inputStream);
            if (dataInputStream != null) {
                final byte[] array = new byte[n2];
                try {
                    dataInputStream.readFully(array);
                    this.monitor.info("Installing rom at :" + Integer.toString(n, 16) + " size:" + array.length);
                    for (int i = 0; i < array.length; ++i) {
                        this.memory[i + n] = (array[i] & 0xFF);
                    }
                }
                catch (IOException ex2) {
                    this.monitor.error("Problem reading rom file ");
                }
                finally {
                    try {
                        dataInputStream.close();
                    }
                    catch (Exception ex3) {}
                }
            }
        }
        catch (Exception ex) {
            this.monitor.error("Error loading resource" + ex);
        }
    }
}
