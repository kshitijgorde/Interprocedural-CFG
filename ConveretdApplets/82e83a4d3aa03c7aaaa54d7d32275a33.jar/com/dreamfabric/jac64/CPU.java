// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

public class CPU extends MOS6510Core
{
    public static final int IO_OFFSET = 12288;
    public static final int BASIC_ROM2 = 106496;
    public static final int KERNAL_ROM2 = 122880;
    public static final int CHAR_ROM2 = 118784;
    public static final boolean EMULATE_1541 = true;
    public boolean basicROM;
    public boolean kernalROM;
    public boolean charROM;
    public boolean ioON;
    public boolean running;
    public boolean exit;
    private static final long CYCLES_PER_DEBUG = 10000000L;
    public static final boolean DEBUG = false;
    private C1541Emu c1541;
    private Loader loader;
    
    public CPU(final IMonitor monitor, final String s, final Loader loader) {
        super(monitor, s);
        this.basicROM = true;
        this.kernalROM = true;
        this.charROM = false;
        this.ioON = true;
        this.running = true;
        this.exit = false;
        this.memory = new int[131072];
        this.loader = loader;
        this.c1541 = new C1541Emu(new DefaultIMon(), s);
    }
    
    public C1541Emu getDrive() {
        return this.c1541;
    }
    
    protected final int fetchByte(int rindex) {
        this.chips.updateChips(this.cycles);
        while (this.baLowUntil > this.cycles) {
            this.chips.updateChips(++this.cycles);
        }
        if ((this.basicROM && (rindex & 0xE000) == 0xA000) || (this.kernalROM && (rindex & 0xE000) == 0xE000) || (this.charROM && (rindex & 0xF000) == 0xD000)) {
            rindex |= 0x10000;
        }
        this.rindex = rindex;
        if (this.ioON && (rindex & 0x1F000) == 0xD000) {
            return this.chips.performRead(rindex, this.cycles);
        }
        return this.memory[rindex];
    }
    
    private void fixRindex(int rindex) {
        if ((this.basicROM && (rindex & 0xE000) == 0xA000) || (this.kernalROM && (rindex & 0xE000) == 0xE000) || (this.charROM && (rindex & 0xF000) == 0xD000)) {
            rindex |= 0x10000;
        }
        this.rindex = rindex;
    }
    
    protected final void writeByte(int n, final int n2) {
        this.chips.updateChips(this.cycles);
        if (n <= 1) {
            this.memory[n] = n2;
            final int n3 = (this.memory[0] ^ 0xFF) | this.memory[1];
            this.kernalROM = ((n3 & 0x2) == 0x2);
            this.basicROM = ((n3 & 0x3) == 0x3);
            this.charROM = ((n3 & 0x3) != 0x0 && (n3 & 0x4) == 0x0);
            this.ioON = ((n3 & 0x3) != 0x0 && (n3 & 0x4) != 0x0);
        }
        n &= 0xFFFF;
        if (this.ioON && (n & 0xF000) == 0xD000) {
            this.chips.performWrite(n, n2, this.cycles);
        }
        else {
            this.memory[n] = n2;
        }
    }
    
    public void patchROM(final PatchListener list) {
        this.list = list;
        int n = 128158;
        this.memory[n++] = 32;
        this.memory[n++] = 210;
        this.memory[n++] = 245;
        System.out.println("Patched LOAD at: " + Hex.hex2(n));
        this.memory[n++] = 256;
        this.memory[n++] = 96;
    }
    
    public void runBasic() {
        this.memory[631] = 82;
        this.memory[632] = 85;
        this.memory[633] = 78;
        this.memory[634] = 13;
        this.memory[198] = 4;
    }
    
    public void enterText(String upperCase) {
        System.out.println("Entering text into textbuffer: " + upperCase);
        upperCase = upperCase.toUpperCase();
        final int length = upperCase.length();
        int n = 0;
        for (int i = 0; i < length; ++i) {
            int char1 = upperCase.charAt(i);
            if (char1 == 126) {
                char1 = 13;
            }
            this.memory[631 + n] = char1;
            if (++n == 5) {
                this.memory[198] = n;
                n = 0;
                int n2 = 5;
                while (n2 > 0 && this.memory[198] > 0) {
                    try {
                        Thread.sleep(50L);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (--n2 == 0) {
                        System.out.println("Buffer still full: " + this.memory[198]);
                    }
                }
            }
        }
        this.memory[198] = n;
        int n3 = 5;
        while (n3 > 0 && this.memory[198] > 0) {
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            if (--n3 == 0) {
                System.out.println("Buffer still full: " + this.memory[198]);
            }
        }
    }
    
    protected void installROMS() {
        this.loadROM(this.loader.getResourceStream("/roms/kernal.c64"), 122880, 8192);
        this.loadROM(this.loader.getResourceStream("/roms/basic.c64"), 106496, 8192);
        this.loadROM(this.loader.getResourceStream("/roms/chargen.c64"), 118784, 4096);
    }
    
    public void run(final int n) {
        this.reset();
        this.exit = false;
        this.running = true;
        this.loop(n);
    }
    
    public void start() {
        while (!this.exit) {
            this.run(64738);
            if (!this.exit) {
                this.monitor.info("Resetting!!!!");
                this.reset();
            }
        }
    }
    
    public void stop() {
        this.running = false;
        this.exit = true;
    }
    
    public void reset() {
        this.running = false;
        this.exit = false;
        this.writeByte(1, 7);
        super.reset();
        this.c1541.reset();
    }
    
    public void loop(final int pc) {
        this.pc = pc;
        long n = this.cycles + 10000000L;
        this.monitor.info("Starting CPU at: " + Integer.toHexString(this.pc));
        try {
            while (this.running) {
                if (this.monitor.isEnabled() && this.baLowUntil <= this.cycles) {
                    this.fixRindex(this.pc);
                    this.monitor.disAssemble(this.memory, this.rindex, this.acc, this.x, this.y, (byte)this.getStatusByte(), this.interruptInExec, this.lastInterrupt);
                }
                this.emulateOp();
                this.c1541.tick(this.cycles);
                ++this.nr_ins;
                if (n < this.cycles) {
                    final long n2 = System.currentTimeMillis() - this.lastMillis;
                    this.monitor.getLevel();
                    this.nr_irq = 0L;
                    this.nr_ins = 0L;
                    this.lastMillis = System.currentTimeMillis();
                    n = this.cycles + 10000000L;
                }
            }
        }
        catch (Exception ex) {
            this.monitor.error("Exception in loop " + this.pc + " : " + ex);
            ex.printStackTrace();
            this.monitor.disAssemble(this.memory, this.rindex, this.acc, this.x, this.y, (byte)this.getStatusByte(), this.interruptInExec, this.lastInterrupt);
        }
    }
}
