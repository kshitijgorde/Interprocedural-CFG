// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

public class CIA
{
    public static final boolean TIMER_DEBUG = false;
    public static final boolean WRITE_DEBUG = false;
    public static final int PRA = 0;
    public static final int PRB = 1;
    public static final int DDRA = 2;
    public static final int DDRB = 3;
    public static final int TIMALO = 4;
    public static final int TIMAHI = 5;
    public static final int TIMBLO = 6;
    public static final int TIMBHI = 7;
    public static final int TODTEN = 8;
    public static final int TODSEC = 9;
    public static final int TODMIN = 10;
    public static final int TODHRS = 11;
    public static final int CIAICR = 13;
    public static final int CIACRA = 14;
    public static final int CIACRB = 15;
    boolean timersONA;
    boolean timersONB;
    int ta;
    int tb;
    int ciaicrRead;
    int timerATrigg;
    int timerBTrigg;
    boolean[] timersIRQ;
    long nextTimersA;
    long nextTimersB;
    public static final int TIMER_B_DIV_MASK = 96;
    public static final int TIMER_B_DIV_VAL = 64;
    int timerBDiv;
    int[] latchValue;
    long nextUpdate;
    public long nextCIAUpdate;
    public int[] ciaMemory;
    private int[] cpuMemory;
    private int offset;
    public int serialFake;
    private C64Chips chips;
    private int lastTrigg;
    
    public CIA(final int[] cpuMemory, final int offset, final C64Chips chips) {
        this.timersONA = false;
        this.timersONB = false;
        this.ta = 0;
        this.tb = 0;
        this.timerATrigg = 0;
        this.timerBTrigg = 0;
        this.timersIRQ = new boolean[2];
        this.nextTimersA = 0L;
        this.nextTimersB = 0L;
        this.timerBDiv = 1;
        this.latchValue = new int[2];
        this.nextUpdate = 0L;
        this.nextCIAUpdate = 0L;
        this.ciaMemory = new int[16];
        this.serialFake = 0;
        this.lastTrigg = 0;
        this.cpuMemory = cpuMemory;
        this.offset = offset;
        this.chips = chips;
    }
    
    public void reset() {
        this.timerATrigg = 0;
        this.timerBTrigg = 0;
        this.timersONA = false;
        this.timersONB = false;
    }
    
    public String ciaID() {
        return (this.offset == 68608) ? "CIA 1" : "CIA 2";
    }
    
    public int updateCIA(final long n) {
        final int n2 = 0;
        this.nextCIAUpdate += 37L;
        if (this.timersONA && this.nextTimersA <= n) {
            this.ciaicrRead |= 0x1;
            this.timerATrigg = (this.timersIRQ[0] ? 1 : 0);
            if ((this.ciaMemory[14] & 0x8) == 0x0) {
                this.nextTimersA = this.nextTimersA + this.latchValue[0] + 2L;
            }
            else {
                this.timersONA = false;
                final int[] cpuMemory = this.cpuMemory;
                final int n3 = this.offset + 14;
                cpuMemory[n3] &= 0xFE;
            }
        }
        if (this.timersONB && this.nextTimersB <= n) {
            this.ciaicrRead |= 0x2;
            this.timerBTrigg = (this.timersIRQ[1] ? 2 : 0);
            if ((this.ciaMemory[15] & 0x8) == 0x0) {
                if ((this.ciaMemory[15] & 0x60) == 0x40) {
                    this.nextTimersB += this.latchValue[1] * this.latchValue[0];
                }
                else {
                    this.nextTimersB += this.latchValue[1];
                }
                this.nextTimersB += 2L;
            }
            else {
                this.timersONB = false;
                final int[] cpuMemory2 = this.cpuMemory;
                final int n4 = this.offset + 15;
                cpuMemory2[n4] &= 0xFE;
            }
        }
        final int n5 = n2 + (this.timerATrigg + this.timerBTrigg);
        if (n5 > 0) {
            this.ciaicrRead |= 0x80;
        }
        if (n > this.nextUpdate) {
            this.nextUpdate += 100000L;
            final int n6 = (this.cpuMemory[this.offset + 8] & 0xF) + 1;
            this.cpuMemory[this.offset + 8] = n6 % 10;
            if (n6 > 9) {
                int n7 = (this.cpuMemory[this.offset + 9] & 0x7F) + 1;
                if ((n7 & 0xF) > 9) {
                    n7 += 6;
                }
                if (n7 > 89) {
                    n7 = 0;
                }
                if ((this.cpuMemory[this.offset + 9] = n7) == 0) {
                    int n8 = (this.cpuMemory[this.offset + 10] & 0x7F) + 1;
                    if ((n8 & 0xF) > 9) {
                        n8 += 6;
                    }
                    if (n8 > 89) {
                        n8 = 0;
                    }
                    if ((this.cpuMemory[this.offset + 10] = n8) == 0) {
                        int n9 = (this.cpuMemory[this.offset + 11] & 0x1F) + 1;
                        if ((n9 & 0xF) > 9) {
                            n9 += 6;
                        }
                        if (n9 > 17) {
                            n9 = 0;
                        }
                        this.cpuMemory[this.offset + 11] = n9;
                    }
                }
            }
        }
        if (this.timersONA && this.nextTimersA < this.nextCIAUpdate) {
            this.nextCIAUpdate = this.nextTimersA;
        }
        if (this.timersONB && this.nextTimersB < this.nextCIAUpdate) {
            this.nextCIAUpdate = this.nextTimersB;
        }
        return n5;
    }
    
    public int performRead(int n, final long n2) {
        int ciaicrRead = this.cpuMemory[n];
        n -= this.offset;
        switch (n) {
            case 4: {
                if (!this.timersONA) {
                    return this.ta & 0xFF;
                }
                if (this.nextTimersA > n2) {
                    return (int)(this.nextTimersA - n2) & 0xFF;
                }
                return 0;
            }
            case 5: {
                if (!this.timersONA) {
                    return this.ta >> 8;
                }
                if (this.nextTimersA > n2) {
                    return (int)(this.nextTimersA - n2) >> 8;
                }
                return 0;
            }
            case 6: {
                if (!this.timersONB) {
                    return this.tb & 0xFF;
                }
                if (this.nextTimersB > n2) {
                    return (int)(this.nextTimersB - n2) & 0xFF;
                }
                return 0;
            }
            case 7: {
                if (!this.timersONB) {
                    return this.tb >> 8;
                }
                if (this.nextTimersB > n2) {
                    return (int)(this.nextTimersB - n2) >> 8;
                }
                return 0;
            }
            case 13: {
                ciaicrRead = this.ciaicrRead;
                this.ciaicrRead = 0;
                this.timerATrigg = 0;
                this.timerBTrigg = 0;
                if (this.offset == 68608) {
                    this.chips.clearIRQ(2);
                    break;
                }
                this.chips.clearNMI(2);
                break;
            }
        }
        return ciaicrRead;
    }
    
    public void performWrite(int n, final int n2, final long n3) {
        n -= this.offset;
        switch (n) {
            case 0: {}
            case 4: {
                this.latchValue[0] = ((this.latchValue[0] & 0xFF00) | n2);
                break;
            }
            case 5: {
                this.latchValue[0] = ((this.latchValue[0] & 0xFF) | n2 << 8);
                break;
            }
            case 6: {
                this.latchValue[1] = ((this.latchValue[1] & 0xFF00) | n2);
                break;
            }
            case 7: {
                this.latchValue[1] = ((this.latchValue[1] & 0xFF) | n2 << 8);
                break;
            }
            case 13: {
                final boolean b = (n2 & 0x80) != 0x0;
                if ((n2 & 0x1) == 0x1) {
                    this.timersIRQ[0] = b;
                }
                if ((n2 & 0x2) == 0x2) {
                    this.timersIRQ[1] = b;
                }
                this.timerATrigg = 0;
                this.timerBTrigg = 0;
                this.ciaicrRead = 0;
                if (b) {
                    final int[] ciaMemory = this.ciaMemory;
                    final int n4 = 13;
                    ciaMemory[n4] |= (n2 & 0x7F);
                }
                else {
                    final int[] ciaMemory2 = this.ciaMemory;
                    final int n5 = 13;
                    ciaMemory2[n5] &= ~(n2 & 0x7F);
                }
                return;
            }
            case 14: {
                if ((n2 & 0x10) != 0x0) {
                    this.latchValue[0] = this.ciaMemory[4] + (this.ciaMemory[5] << 8);
                    this.timerATrigg = 0;
                }
                if ((n2 & 0x1) == 0x1) {
                    this.timersONA = true;
                    this.timerATrigg = 0;
                    this.nextTimersA = n3 + this.latchValue[0];
                }
                else {
                    this.timersONA = false;
                    this.timerATrigg = 0;
                    this.ta = (int)(this.nextTimersA - n3);
                    if (this.ta < 0) {
                        this.ta = 0;
                    }
                }
                if ((n2 & 0x2) != 0x0) {
                    this.println("******* Timer A shows on dc01 b6 - mode: " + (n2 & 0x4));
                    break;
                }
                break;
            }
            case 15: {
                if ((n2 & 0x10) != 0x0) {
                    this.latchValue[1] = this.ciaMemory[6] + (this.ciaMemory[7] << 8);
                    this.timerBTrigg = 0;
                }
                if ((n2 & 0x1) == 0x1) {
                    this.timersONB = true;
                    this.nextTimersB = n3 + this.latchValue[1];
                    this.timerBTrigg = 0;
                }
                else {
                    this.timersONB = false;
                    this.timerBTrigg = 0;
                    this.tb = (int)(this.nextTimersB - n3);
                    if (this.tb < 0) {
                        this.tb = 0;
                    }
                }
                if ((n2 & 0x2) != 0x0) {
                    this.println("******* Timer B shows on dc01 b6 - mode: " + (n2 & 0x4));
                    break;
                }
                break;
            }
        }
        this.ciaMemory[n] = n2;
    }
    
    private void println(final String s) {
        System.out.println(this.ciaID() + ": " + s);
    }
    
    public void printStatus() {
        System.out.println("--------------------------");
        this.println(" status");
        System.out.println("Timer A on: " + this.timersONA);
        System.out.println("Timer A next trigger: " + this.nextTimersA);
        System.out.println("CIA CRA: " + Hex.hex2(this.ciaMemory[14]) + " => " + (((this.ciaMemory[14] & 0x8) == 0x0) ? "cont" : "one-shot"));
        System.out.println("Timer A Interrupt: " + this.timerATrigg);
        System.out.println("Timer A Latch: " + this.latchValue[0]);
        System.out.println("Timer B on: " + this.timersONB);
        System.out.println("Timer B next trigger: " + this.nextTimersB);
        System.out.println("CIA CRB: " + Hex.hex2(this.ciaMemory[15]) + " => " + (((this.ciaMemory[15] & 0x8) == 0x0) ? "cont" : "one-shot"));
        System.out.println("Timer B Interrupt: " + this.timerBTrigg);
        System.out.println("Timer B Latch: " + this.latchValue[1]);
        System.out.println("--------------------------");
    }
}
