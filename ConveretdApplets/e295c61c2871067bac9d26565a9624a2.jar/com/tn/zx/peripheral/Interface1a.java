// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zx.peripheral;

import java.io.IOException;
import java.io.File;
import com.tn.components.MemoryFactory;
import com.tn.components.Memory;
import com.tn.zx.ZxExpansionPort;
import com.tn.zx.ZxIoHandler;

public final class Interface1a implements ZxIoHandler
{
    private ZxExpansionPort ivConnectedSpectrum;
    private Memory ivRom;
    private int ivOldE7read;
    private int ivOldE7write;
    private int ivOldEFread;
    private int ivOldEFwrite;
    private int ivOldF7read;
    private int ivOldF7write;
    boolean ivRomActive;
    private Microdrive[] ivMicrodrives;
    private int ivIndexActiveMicrodrive;
    
    public Interface1a(final MemoryFactory pMemoryFactory) throws IOException {
        this.ivOldE7read = 0;
        this.ivOldE7write = 0;
        this.ivOldEFread = 1;
        this.ivOldEFwrite = 0;
        this.ivOldF7read = 0;
        this.ivOldF7write = 0;
        this.ivMicrodrives = new Microdrive[8];
        this.ivIndexActiveMicrodrive = -1;
        this.ivRomActive = false;
        this.ivRom = pMemoryFactory.createRom(16384, "if1.rom");
        this.ivMicrodrives[0] = new Microdrive();
        this.ivMicrodrives[1] = new Microdrive();
        this.ivMicrodrives[2] = new Microdrive();
        this.ivMicrodrives[3] = new Microdrive();
        this.ivMicrodrives[7] = new Microdrive();
        this.ivMicrodrives[0].insertCartridge(new MicrodriveCartridgeMdrFile(new File("bottle1.mdr")));
        this.ivMicrodrives[1].insertCartridge(new MicrodriveCartridgeMdrFile(new File("bottle2.mdr")));
        this.ivMicrodrives[2].insertCartridge(new MicrodriveCartridgeMdrFile(new File("bottle3.mdr")));
        this.ivMicrodrives[3].insertCartridge(new MicrodriveCartridgeMdrFile(new File("utility.mdr")));
        this.ivMicrodrives[7].insertCartridge(new MicrodriveCartridgeMdrFile(new File("dummycrt.mdr")));
    }
    
    public void connectMicrodrive(final Microdrive pMicrodrive, final int pIndex) {
        if (pIndex < 0 || pIndex > 7) {
            throw new RuntimeException("Invalid value of parameter pIndex (must be 0..7): " + pIndex);
        }
        this.ivMicrodrives[pIndex] = pMicrodrive;
    }
    
    public void connectToZXSpectrum(final ZxExpansionPort pZXSpectrum) {
        this.ivConnectedSpectrum = pZXSpectrum;
        for (int i = 0; i < 256; ++i) {
            if ((i & 0x18) != 0x18) {
                pZXSpectrum.addIOHandler(this, i);
            }
        }
        pZXSpectrum.addBreakpointHandler(this, 8);
        pZXSpectrum.addBreakpointHandler(this, 1792);
        pZXSpectrum.addBreakpointHandler(this, 5896);
        this.setRomActive(this.ivRomActive);
    }
    
    public void disconnectFromZXSpectrum() {
        if (this.ivConnectedSpectrum != null) {
            this.ivConnectedSpectrum.removeIOHandler(this);
            this.ivConnectedSpectrum.removeBreakpointHandler(this);
            this.ivConnectedSpectrum.deactivateExternalMemory(0, this.ivRom);
            this.ivConnectedSpectrum = null;
        }
    }
    
    public void disconnectMicrodrive(final int pIndex) {
        this.ivMicrodrives[pIndex] = null;
    }
    
    private Microdrive getActiveMicrodrive() {
        if (this.ivIndexActiveMicrodrive >= 0) {
            return this.ivMicrodrives[this.ivIndexActiveMicrodrive];
        }
        return null;
    }
    
    private int getLowered(final int pOldValue, final int pNewValue) {
        return ~pNewValue & pOldValue;
    }
    
    public Interface1Status getStatus() {
        final Interface1Status result = new Interface1Status();
        result.setRomActive(this.ivRomActive);
        return result;
    }
    
    public void init() {
        this.reset();
    }
    
    private void microdriveShift(final int pPortEFValue) {
        final Microdrive md = null;
        if (this.ivIndexActiveMicrodrive != -1) {
            ++this.ivIndexActiveMicrodrive;
        }
        if ((pPortEFValue & 0x1) == 0x0) {
            this.ivIndexActiveMicrodrive = 0;
        }
        if (this.ivIndexActiveMicrodrive >= 8) {
            this.ivIndexActiveMicrodrive = -1;
        }
        if (this.ivIndexActiveMicrodrive >= 0 && this.getActiveMicrodrive() != null) {
            this.getActiveMicrodrive().writePortEF(this.readPortEF());
        }
    }
    
    @Override
    public int readIO(final int pAddress, int pValue) {
        if ((pAddress & 0x18) != 0x18) {
            if ((pAddress & 0x18) == 0x0) {
                pValue = this.readPortE7();
            }
            else if ((pAddress & 0x18) == 0x8) {
                pValue = this.readPortEF();
            }
            else if ((pAddress & 0x18) == 0x10) {
                pValue = this.readPortF7();
            }
        }
        return pValue;
    }
    
    @Override
    public void readOpcode1(final int pAddress) {
    }
    
    @Override
    public int readOpcode2(final int pAddress, final int pOpcode) {
        if (pAddress == 8 || pAddress == 5896) {
            this.setRomActive(true);
        }
        if (pAddress == 1792) {
            this.setRomActive(false);
        }
        return pOpcode;
    }
    
    private int readPortE7() {
        int result = 252;
        if (this.getActiveMicrodrive() != null) {
            result = this.getActiveMicrodrive().readPortE7();
        }
        return result;
    }
    
    private int readPortEF() {
        this.ivOldEFread &= 0xF8;
        if (this.getActiveMicrodrive() != null) {
            this.ivOldEFread |= (this.getActiveMicrodrive().readPortEF() & 0x7);
        }
        return this.ivOldEFread;
    }
    
    private int readPortF7() {
        return 255;
    }
    
    public void reset() {
        this.setRomActive(false);
    }
    
    private void setRomActive(final boolean pRomActive) {
        this.ivRomActive = pRomActive;
        if (this.ivConnectedSpectrum != null) {
            if (this.ivRomActive) {
                this.ivConnectedSpectrum.activateExternalMemory(0, this.ivRom);
            }
            else {
                this.ivConnectedSpectrum.deactivateExternalMemory(0, this.ivRom);
            }
        }
    }
    
    public void setStatus(final Interface1Status pInterface1Status) {
        this.setRomActive(pInterface1Status.getRomActive());
    }
    
    @Override
    public void writeIO(final int pAddress, final int pValue) {
        if ((pAddress & 0x18) != 0x18) {
            if ((pAddress & 0x18) == 0x0) {
                this.writePortE7(pValue);
            }
            else if ((pAddress & 0x18) == 0x8) {
                this.writePortEF(pValue);
            }
            else if ((pAddress & 0x18) == 0x10) {
                this.writePortF7(pValue);
            }
        }
    }
    
    private void writePortE7(final int pValue) {
        if (this.getActiveMicrodrive() != null) {
            this.getActiveMicrodrive().writePortE7(pValue);
        }
    }
    
    private void writePortEF(final int pValue) {
        final int lowered = this.getLowered(this.ivOldEFwrite, pValue);
        this.ivOldEFwrite = pValue;
        if ((lowered & 0x2) != 0x0) {
            this.microdriveShift(pValue);
        }
        if (this.getActiveMicrodrive() != null) {
            this.getActiveMicrodrive().writePortEF(pValue);
        }
    }
    
    private void writePortF7(final int pValue) {
    }
}
