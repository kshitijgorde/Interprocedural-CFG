// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zx;

import com.tn.components.MemoryFactory;
import java.util.Vector;
import com.tn.components.Memory;
import com.tn.z80.Z80A;
import com.tn.z80.Z80IoHandler2;

public abstract class ZxAbstract implements Z80IoHandler2, ZxExpansionPort
{
    private static final String copyright = "© Copyright Troels Noergaard, 2001, 2006. All Rights Reserved.";
    protected Z80A ivZ80;
    private Memory ivRomBankFF;
    private Vector ivMemoryBanks;
    private Memory[] ivInternalMemory;
    private Memory[] ivExternalMemory;
    private int[][] ivReadMemory;
    private int[][] ivWriteMemory;
    private Vector[] ivIOHandlers;
    private Vector[] ivBreakpointHandlers;
    
    public ZxAbstract(final MemoryFactory pMemoryFactory) {
        this.ivZ80 = new Z80A(this);
        this.ivRomBankFF = pMemoryFactory.createRom(16384);
        this.ivMemoryBanks = new Vector(16);
        this.ivInternalMemory = new Memory[4];
        this.ivExternalMemory = new Memory[4];
        this.ivReadMemory = new int[4][];
        this.ivWriteMemory = new int[4][];
        this.ivBreakpointHandlers = new Vector[65536];
        this.ivIOHandlers = new Vector[256];
    }
    
    @Override
    public void activateExternalMemory(final int pPageIndex, final Memory p16KMemoryBank) {
        if (pPageIndex < 0 || pPageIndex > 4) {
            throw new RuntimeException("Invalid page index " + pPageIndex + ", it must be 0, 1, 2 or 3");
        }
        if (p16KMemoryBank.getSize() != 16384) {
            throw new RuntimeException("Invalid memory bank. Size is " + p16KMemoryBank.getSize() + ", it must be 16384");
        }
        if (this.ivExternalMemory[pPageIndex] != null && this.ivExternalMemory[pPageIndex] != p16KMemoryBank) {
            throw new RuntimeException("Another external memory bank is active. page: " + pPageIndex + ", memory: " + p16KMemoryBank);
        }
        this.ivExternalMemory[pPageIndex] = p16KMemoryBank;
        this.updateMemoryConfiguration();
    }
    
    @Override
    public void addBreakpointHandler(final ZxIoHandler pIoHandler, final int p16BitAddress) {
        this.addHandler(pIoHandler, this.ivBreakpointHandlers, p16BitAddress);
    }
    
    private void addHandler(final Object pHandler, final Vector[] pHandlerTable, final int pIndex) {
        if (pHandlerTable[pIndex] == null) {
            pHandlerTable[pIndex] = new Vector(1);
        }
        final Vector v = pHandlerTable[pIndex];
        if (!v.contains(pHandler)) {
            v.addElement(pHandler);
        }
    }
    
    @Override
    public void addIOHandler(final ZxIoHandler pIOHandler, final int p8BitAddress) {
        this.addHandler(pIOHandler, this.ivIOHandlers, p8BitAddress);
    }
    
    @Override
    public void deactivateExternalMemory(final int pPageIndex, final Memory p16KMemoryBank) {
        if (pPageIndex < 0 || pPageIndex > 4) {
            throw new RuntimeException("Invalid page index " + pPageIndex + ", it must be 0, 1, 2 or 3");
        }
        final Memory memory = this.ivExternalMemory[pPageIndex];
        this.ivExternalMemory[pPageIndex] = null;
        this.updateMemoryConfiguration();
    }
    
    protected Memory getMemoryBank(final int pBankIndex) {
        return this.ivMemoryBanks.elementAt(pBankIndex);
    }
    
    @Override
    public Z80A getZ80() {
        return this.ivZ80;
    }
    
    public void init() {
        this.ivZ80.performReset();
        for (int i = 0; i < this.ivMemoryBanks.size(); ++i) {
            final Memory memoryBank = this.ivMemoryBanks.elementAt(i);
            if (memoryBank != null) {
                memoryBank.init();
            }
        }
        this.ivExternalMemory = new Memory[4];
        this.ivInternalMemory = new Memory[4];
        this.updateMemoryConfiguration();
    }
    
    @Override
    public int readIO(final int pAddress) {
        return this.readIO(pAddress, 255);
    }
    
    protected int readIO(final int pAddress, int pPortValue) {
        if (this.ivIOHandlers[pAddress & 0xFF] != null) {
            final Vector v = this.ivIOHandlers[pAddress & 0xFF];
            for (int i = 0; i < v.size(); ++i) {
                pPortValue = v.elementAt(i).readIO(pAddress, pPortValue);
            }
        }
        return pPortValue;
    }
    
    @Override
    public int readMem(final int pAddress) {
        return this.ivReadMemory[pAddress >> 14][pAddress & 0x3FFF];
    }
    
    @Override
    public int readOpcode(final int pAddress) {
        if (this.ivBreakpointHandlers[pAddress] == null) {
            return this.ivReadMemory[pAddress >> 14][pAddress & 0x3FFF];
        }
        final Vector v = this.ivBreakpointHandlers[pAddress];
        for (int i = 0; i < v.size(); ++i) {
            v.elementAt(i).readOpcode1(pAddress);
        }
        int opcode = this.ivReadMemory[pAddress >> 14][pAddress & 0x3FFF];
        for (int j = 0; j < v.size(); ++j) {
            opcode = v.elementAt(j).readOpcode2(pAddress, opcode);
        }
        return opcode;
    }
    
    @Override
    public void removeBreakpointHandler(final ZxIoHandler pBreakpointHandler) {
        for (int i = 0; i < 65536; ++i) {
            this.removeBreakpointHandler(pBreakpointHandler, i);
        }
    }
    
    public void removeBreakpointHandler(final ZxIoHandler pBreakpointHandler, final int p16BitAddress) {
        this.removeHandler(pBreakpointHandler, this.ivBreakpointHandlers, p16BitAddress);
    }
    
    private void removeHandler(final Object pHandler, final Vector[] pHandlerTable, final int pIndex) {
        if (pHandlerTable[pIndex] == null) {
            return;
        }
        final Vector v = pHandlerTable[pIndex];
        v.removeElement(pHandler);
        if (v.size() == 0) {
            pHandlerTable[pIndex] = null;
        }
    }
    
    @Override
    public void removeIOHandler(final ZxIoHandler pIOHandler) {
        for (int i = 0; i < 256; ++i) {
            this.removeIOHandler(pIOHandler, i);
        }
    }
    
    public void removeIOHandler(final ZxIoHandler pIOHandler, final int p8BitAddress) {
        this.removeHandler(pIOHandler, this.ivIOHandlers, p8BitAddress);
    }
    
    public void reset() {
        this.ivZ80.performReset();
    }
    
    protected void setMemoryBank(final int pBankIndex, final Memory pMemoryBank) {
        if (this.ivMemoryBanks.size() <= pBankIndex) {
            this.ivMemoryBanks.setSize(pBankIndex + 1);
        }
        this.ivMemoryBanks.setElementAt(pMemoryBank, pBankIndex);
    }
    
    protected void setMemoryPage(final int pPageIndex, final int pBankIndex) {
        this.setMemoryPage(pPageIndex, this.getMemoryBank(pBankIndex));
    }
    
    private void setMemoryPage(final int pPageIndex, final Memory pMemoryBank) {
        if (pMemoryBank.getSize() != 16384) {
            throw new IllegalArgumentException("Size of memorybank is not 16384");
        }
        this.ivInternalMemory[pPageIndex] = pMemoryBank;
        this.updateMemoryConfiguration();
    }
    
    public void terminate() {
        this.ivZ80.terminate();
        this.ivZ80 = null;
    }
    
    private void updateMemoryConfiguration() {
        for (int i = 0; i < 4; ++i) {
            Memory memoryBank;
            if (this.ivExternalMemory[i] != null) {
                memoryBank = this.ivExternalMemory[i];
            }
            else if (this.ivInternalMemory[i] != null) {
                memoryBank = this.ivInternalMemory[i];
            }
            else {
                memoryBank = this.ivRomBankFF;
            }
            this.ivReadMemory[i] = memoryBank.getBytesRead();
            this.ivWriteMemory[i] = memoryBank.getBytesWrite();
        }
    }
    
    @Override
    public void writeIO(final int pAddress, final int pValue) {
        if (this.ivIOHandlers[pAddress & 0xFF] != null) {
            final Vector v = this.ivIOHandlers[pAddress & 0xFF];
            for (int i = 0; i < v.size(); ++i) {
                v.elementAt(i).writeIO(pAddress, pValue);
            }
        }
    }
    
    @Override
    public void writeMem(final int pAddress, final int pValue) {
        this.ivWriteMemory[pAddress >> 14][pAddress & 0x3FFF] = pValue;
    }
}
