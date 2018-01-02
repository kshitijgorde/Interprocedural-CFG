// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zxspectrum;

import java.io.IOException;
import com.tn.util.SampleStreamer;
import com.tn.zx.ZxKeyboardHandler;
import com.tn.components.MemoryFactory;
import com.tn.components.AY38912;

public class ZxSpectrum128K extends ZxSpectrumAbstract
{
    public static final int BANK_ROM128A = 0;
    public static final int BANK_ROM128B = 1;
    public static final int BANK_RAM0 = 2;
    public static final int BANK_RAM1 = 3;
    public static final int BANK_RAM2 = 4;
    public static final int BANK_RAM3 = 5;
    public static final int BANK_RAM4 = 6;
    public static final int BANK_RAM5 = 7;
    public static final int BANK_RAM6 = 8;
    public static final int BANK_RAM7 = 9;
    public static final int SCREEN_CYCLE_1 = 14361;
    public static final int BORDER_CYCLE_1 = 14365;
    private int ivLastPort7FFD;
    private AY38912 ivAY38912;
    
    public ZxSpectrum128K(final MemoryFactory pMemoryFactory, final ZxKeyboardHandler pKeyboardHandler, final ZxSpectrumScreenConverter pScreenConverter, final SampleStreamer pSampleStreamer) throws IOException {
        super(pMemoryFactory, pKeyboardHandler, pScreenConverter, pSampleStreamer);
        this.setMemoryBank(0, pMemoryFactory.createRom(16384, "zx128a.rom"));
        this.setMemoryBank(1, pMemoryFactory.createRom(16384, "zx128b.rom"));
        this.setMemoryBank(2, pMemoryFactory.createRam(16384));
        this.setMemoryBank(3, pMemoryFactory.createRam(16384));
        this.setMemoryBank(4, pMemoryFactory.createRam(16384));
        this.setMemoryBank(5, pMemoryFactory.createRam(16384));
        this.setMemoryBank(6, pMemoryFactory.createRam(16384));
        this.setMemoryBank(7, pMemoryFactory.createRam(16384));
        this.setMemoryBank(8, pMemoryFactory.createRam(16384));
        this.setMemoryBank(9, pMemoryFactory.createRam(16384));
        this.ivAY38912 = new AY38912();
        int cycle = 14361;
        for (int i = 0; i < 192; ++i) {
            for (int j = 0; j < 128; ++j) {
                this.ivContention[cycle++] = (byte)(((j & 0x7) < 6) ? (6 - (j & 0x7)) : 0);
            }
            cycle += 100;
        }
        cycle = 14361;
        for (int t = 0; t < 3; ++t) {
            for (int r = 0; r < 8; ++r) {
                for (int l = 0; l < 8; ++l) {
                    for (int c = 0; c < 32; c += 2) {
                        this.ivFloatAddr[cycle + 0] = (short)(16384 + t * 2048 + r * 32 + l * 256 + c);
                        this.ivFloatAddr[cycle + 1] = (short)(22528 + t * 256 + r * 32 + c);
                        this.ivFloatAddr[cycle + 2] = (short)(16384 + t * 2048 + r * 32 + l * 256 + c + 1);
                        this.ivFloatAddr[cycle + 3] = (short)(22528 + t * 256 + r * 32 + c + 1);
                        cycle += 8;
                    }
                    cycle += 100;
                }
            }
        }
    }
    
    @Override
    public SpectrumStatus getStatus() {
        final SpectrumStatus result = super.getStatus();
        result.getULAStatus().setPort7FFD(this.ivLastPort7FFD);
        result.setMemoryStatus(2, this.getMemoryBank(2).getStatus());
        result.setMemoryStatus(3, this.getMemoryBank(3).getStatus());
        result.setMemoryStatus(4, this.getMemoryBank(4).getStatus());
        result.setMemoryStatus(5, this.getMemoryBank(5).getStatus());
        result.setMemoryStatus(6, this.getMemoryBank(6).getStatus());
        result.setMemoryStatus(7, this.getMemoryBank(7).getStatus());
        result.setMemoryStatus(8, this.getMemoryBank(8).getStatus());
        result.setMemoryStatus(9, this.getMemoryBank(9).getStatus());
        result.setAY38912Status(this.ivAY38912.getStatus());
        result.setComponentType(17);
        return result;
    }
    
    @Override
    public void init() {
        super.init();
        this.setMemoryPage(1, 7);
        this.setMemoryPage(2, 4);
        this.setPort7FFD(0);
        this.ivAY38912.init();
    }
    
    @Override
    public int readIO(final int pAddress) {
        int result = 255;
        if ((pAddress & 0xC002) == 0xC000) {
            result = this.readPortFFFD();
        }
        return super.readIO(pAddress, result);
    }
    
    protected int readPortFFFD() {
        return this.ivAY38912.readData();
    }
    
    @Override
    public void reset() {
        super.reset();
        this.setPort7FFD(0);
        this.ivAY38912.reset();
    }
    
    @Override
    protected void runOneFrameSpecific() {
        if (this.getSampleStreamer() == null) {
            for (int i = 0; i < 311; ++i) {
                this.getZ80().decodeInstruction(228);
                this.convertPixelLine(i);
            }
        }
        else {
            for (int i = 0; i < 311; ++i) {
                this.getZ80().decodeInstruction(76);
                this.ivAY38912.step(5);
                this.queueSample((short)((this.getLastPortFE() & 0x10) + this.ivAY38912.getOutputABC() << 9));
                this.getZ80().decodeInstruction(76);
                this.ivAY38912.step(5);
                this.queueSample((short)((this.getLastPortFE() & 0x10) + this.ivAY38912.getOutputABC() << 9));
                this.getZ80().decodeInstruction(76);
                this.ivAY38912.step(4);
                this.queueSample((short)((this.getLastPortFE() & 0x10) + this.ivAY38912.getOutputABC() << 9));
                this.convertPixelLine(i);
            }
        }
        this.getZ80().performINT();
        this.getZ80().subtractClk(70908L);
    }
    
    public void setPort7FFD(final int pValue) {
        this.ivLastPort7FFD = pValue;
        int bank = 0;
        switch (pValue & 0x10) {
            case 0: {
                bank = 1;
                break;
            }
            case 16: {
                bank = 0;
                break;
            }
        }
        this.setMemoryPage(0, bank);
        switch (pValue & 0x7) {
            case 0: {
                bank = 2;
                break;
            }
            case 1: {
                bank = 3;
                break;
            }
            case 2: {
                bank = 4;
                break;
            }
            case 3: {
                bank = 5;
                break;
            }
            case 4: {
                bank = 6;
                break;
            }
            case 5: {
                bank = 7;
                break;
            }
            case 6: {
                bank = 8;
                break;
            }
            case 7: {
                bank = 9;
                break;
            }
        }
        this.setMemoryPage(3, bank);
        switch (pValue & 0x8) {
            case 0: {
                bank = 7;
                break;
            }
            case 8: {
                bank = 9;
                break;
            }
        }
        this.setScreenMemory(bank);
    }
    
    @Override
    public void setStatus(final SpectrumStatus pStatus) {
        if (pStatus.getComponentType() != 17) {
            throw new RuntimeException("Status is not COMPONENT_TYPE_SPECTRUM_128K");
        }
        super.setStatus(pStatus);
        this.setPort7FFD(pStatus.getULAStatus().getPort7FFD());
        this.getMemoryBank(2).setStatus(pStatus.getMemoryStatus(2));
        this.getMemoryBank(3).setStatus(pStatus.getMemoryStatus(3));
        this.getMemoryBank(4).setStatus(pStatus.getMemoryStatus(4));
        this.getMemoryBank(5).setStatus(pStatus.getMemoryStatus(5));
        this.getMemoryBank(6).setStatus(pStatus.getMemoryStatus(6));
        this.getMemoryBank(7).setStatus(pStatus.getMemoryStatus(7));
        this.getMemoryBank(8).setStatus(pStatus.getMemoryStatus(8));
        this.getMemoryBank(9).setStatus(pStatus.getMemoryStatus(9));
        this.ivAY38912.setStatus(pStatus.getAY38912Status());
    }
    
    @Override
    public void writeIO(final int pAddress, final int pValue) {
        if ((pAddress & 0x2) != 0x2) {
            if ((pAddress & 0xC002) == 0x4000) {
                this.writePort7FFD(pValue);
            }
            else if ((pAddress & 0xC002) == 0x8000) {
                this.writePortBFFD(pValue);
            }
            else if ((pAddress & 0xC002) == 0xC000) {
                this.writePortFFFD(pValue);
            }
        }
        super.writeIO(pAddress, pValue);
    }
    
    protected void writePort7FFD(final int pValue) {
        if ((this.ivLastPort7FFD & 0x20) == 0x0) {
            this.setPort7FFD(pValue);
        }
    }
    
    protected void writePortBFFD(final int pValue) {
        this.ivAY38912.writeData(pValue);
    }
    
    protected void writePortFFFD(final int pValue) {
        this.ivAY38912.latchAddress(pValue);
    }
}
