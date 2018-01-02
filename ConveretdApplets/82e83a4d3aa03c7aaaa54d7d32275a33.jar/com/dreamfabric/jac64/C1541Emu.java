// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

import java.io.InputStream;
import java.io.DataInputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class C1541Emu extends MOS6510Core
{
    public static final boolean DEBUG = false;
    public static final boolean IODEBUG = false;
    public static final int C1541ROM = 49152;
    public static final int RESET_VECTOR = 65532;
    public C1541Chips chips;
    
    public C1541Emu(final IMonitor monitor, final String s) {
        super(monitor, s);
        this.memory = new int[65536];
        this.init(this.chips = new C1541Chips(this));
        this.loadDebug("c1541dbg.txt");
    }
    
    public void setReader(final C64Reader reader) {
        this.chips.setReader(reader);
    }
    
    protected final int fetchByte(final int n) {
        if (n < 2048 || n >= 49152) {
            return this.memory[n];
        }
        final int n2 = n & 0xFF00;
        if (n2 == 6144 || n2 == 7168) {
            return this.chips.performRead(n, this.cycles);
        }
        return 0;
    }
    
    protected final void writeByte(final int n, final int n2) {
        if (n < 2048) {
            this.memory[n] = n2;
        }
        final int n3 = n & 0xFF00;
        if (n3 == 6144 || n3 == 7168) {
            this.chips.performWrite(n, n2, this.cycles);
        }
    }
    
    public void reset() {
        super.reset();
        this.pc = (this.memory[65532] | this.memory[65533] << 8);
        System.out.println("C1541: Reset to " + Integer.toHexString(this.pc));
    }
    
    public void tick(final long n) {
        while (this.cycles < n) {
            final boolean overflow = this.overflow;
            this.overflow = ((this.chips.via2PerControl & 0xE) == 0xE || overflow);
            this.emulateOp();
            if (this.chips.nextCheck < this.cycles) {
                this.chips.updateChips(this.cycles);
            }
            this.overflow = overflow;
        }
    }
    
    public void patchROM(final PatchListener patchListener) {
    }
    
    public void loadDebug(final String s) {
        try {
            URL resource = this.getClass().getResource(s);
            this.monitor.info("Loading debug from URL: " + resource);
            if (resource == null) {
                resource = new URL(this.codebase + s);
            }
            String line;
            while ((line = new BufferedReader(new InputStreamReader(resource.openConnection().getInputStream())).readLine()) != null) {
                final String[] split = line.split("\t");
                int int1 = -1;
                try {
                    int1 = Integer.parseInt(split[0].trim(), 16);
                }
                catch (Exception ex) {}
                if (int1 != -1) {
                    this.setDebug(int1, split[1].trim());
                }
            }
        }
        catch (Exception ex2) {
            System.out.println("Failed to load debug text: " + s);
        }
    }
    
    protected void readROM(final String s, final int n, final int n2) {
        try {
            URL resource = this.getClass().getResource(s);
            this.monitor.info("URL: " + resource);
            this.monitor.info("Read ROM " + s);
            if (resource == null) {
                resource = new URL(this.codebase + s);
            }
            this.loadROM(new DataInputStream(resource.openConnection().getInputStream()), n, n2);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected void installROMS() {
        this.readROM("/roms/c1541.rom", 49152, 16384);
    }
}
