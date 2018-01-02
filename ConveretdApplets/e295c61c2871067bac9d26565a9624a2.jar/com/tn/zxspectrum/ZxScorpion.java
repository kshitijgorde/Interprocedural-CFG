// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zxspectrum;

import java.io.IOException;
import com.tn.util.SampleStreamer;
import com.tn.zx.ZxKeyboardHandler;
import com.tn.components.MemoryFactory;
import com.tn.components.AY38912;

public class ZxScorpion extends ZxSpectrumAbstract
{
    public static final int BANK_ROMSCORPIONA = 0;
    public static final int BANK_ROMSCORPIONB = 1;
    public static final int BANK_ROMSCORPIONC = 2;
    public static final int BANK_ROMSCORPIOND = 3;
    public static final int BANK_RAM0 = 4;
    public static final int BANK_RAM1 = 5;
    public static final int BANK_RAM2 = 6;
    public static final int BANK_RAM3 = 7;
    public static final int BANK_RAM4 = 8;
    public static final int BANK_RAM5 = 9;
    public static final int BANK_RAM6 = 10;
    public static final int BANK_RAM7 = 11;
    public static final int BANK_RAM8 = 12;
    public static final int BANK_RAM9 = 13;
    public static final int BANK_RAMA = 14;
    public static final int BANK_RAMB = 15;
    public static final int BANK_RAMC = 16;
    public static final int BANK_RAMD = 17;
    public static final int BANK_RAME = 18;
    public static final int BANK_RAMF = 19;
    private int ivLastPort1FFD;
    private int ivLastPort7FFD;
    private AY38912 ivAY38912;
    
    public ZxScorpion(final MemoryFactory pMemoryFactory, final ZxKeyboardHandler pKeyboardHandler, final ZxSpectrumScreenConverter pScreenConverter, final SampleStreamer pSampleStreamer) throws IOException {
        super(pMemoryFactory, pKeyboardHandler, pScreenConverter, pSampleStreamer);
        this.setMemoryBank(0, pMemoryFactory.createRom(16384, "scorpa.rom"));
        this.setMemoryBank(1, pMemoryFactory.createRom(16384, "scorpb.rom"));
        this.setMemoryBank(2, pMemoryFactory.createRom(16384, "scorpc.rom"));
        this.setMemoryBank(3, pMemoryFactory.createRom(16384, "scorpd.rom"));
        this.setMemoryBank(4, pMemoryFactory.createRam(16384));
        this.setMemoryBank(5, pMemoryFactory.createRam(16384));
        this.setMemoryBank(6, pMemoryFactory.createRam(16384));
        this.setMemoryBank(7, pMemoryFactory.createRam(16384));
        this.setMemoryBank(8, pMemoryFactory.createRam(16384));
        this.setMemoryBank(9, pMemoryFactory.createRam(16384));
        this.setMemoryBank(10, pMemoryFactory.createRam(16384));
        this.setMemoryBank(11, pMemoryFactory.createRam(16384));
        this.setMemoryBank(12, pMemoryFactory.createRam(16384));
        this.setMemoryBank(13, pMemoryFactory.createRam(16384));
        this.setMemoryBank(14, pMemoryFactory.createRam(16384));
        this.setMemoryBank(15, pMemoryFactory.createRam(16384));
        this.setMemoryBank(16, pMemoryFactory.createRam(16384));
        this.setMemoryBank(17, pMemoryFactory.createRam(16384));
        this.setMemoryBank(18, pMemoryFactory.createRam(16384));
        this.setMemoryBank(19, pMemoryFactory.createRam(16384));
        this.ivAY38912 = new AY38912();
    }
    
    @Override
    public SpectrumStatus getStatus() {
        final SpectrumStatus result = super.getStatus();
        result.getULAStatus().setPort1FFD(this.ivLastPort1FFD);
        result.getULAStatus().setPort7FFD(this.ivLastPort7FFD);
        result.setMemoryStatus(2, this.getMemoryBank(4).getStatus());
        result.setMemoryStatus(3, this.getMemoryBank(5).getStatus());
        result.setMemoryStatus(4, this.getMemoryBank(6).getStatus());
        result.setMemoryStatus(5, this.getMemoryBank(7).getStatus());
        result.setMemoryStatus(6, this.getMemoryBank(8).getStatus());
        result.setMemoryStatus(7, this.getMemoryBank(9).getStatus());
        result.setMemoryStatus(8, this.getMemoryBank(10).getStatus());
        result.setMemoryStatus(9, this.getMemoryBank(11).getStatus());
        result.setMemoryStatus(100, this.getMemoryBank(12).getStatus());
        result.setMemoryStatus(101, this.getMemoryBank(13).getStatus());
        result.setMemoryStatus(102, this.getMemoryBank(14).getStatus());
        result.setMemoryStatus(103, this.getMemoryBank(15).getStatus());
        result.setMemoryStatus(104, this.getMemoryBank(16).getStatus());
        result.setMemoryStatus(105, this.getMemoryBank(17).getStatus());
        result.setMemoryStatus(106, this.getMemoryBank(18).getStatus());
        result.setMemoryStatus(107, this.getMemoryBank(19).getStatus());
        result.setAY38912Status(this.ivAY38912.getStatus());
        result.setComponentType(200);
        return result;
    }
    
    @Override
    public void init() {
        super.init();
        this.setPort1FFD(0);
        this.setPort7FFD(0);
        this.ivAY38912.init();
    }
    
    @Override
    public int readIO(final int pAddress) {
        int result = 255;
        if ((pAddress & 0x2) != 0x2 && pAddress == 65533) {
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
        this.setPort1FFD(0);
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
    }
    
    public void setPort1FFD(final int pValue) {
        final int temp = this.ivLastPort1FFD;
        this.ivLastPort1FFD = pValue;
        if (((temp ^ pValue) & 0xFF) != 0x0) {
            this.updateMemoryConfiguration();
        }
    }
    
    public void setPort7FFD(final int pValue) {
        this.ivLastPort7FFD = pValue;
        this.updateMemoryConfiguration();
    }
    
    @Override
    public void setStatus(final SpectrumStatus pStatus) {
        if (pStatus.getComponentType() != 200) {
            throw new RuntimeException("Status is not COMPONENT_TYPE_SCORPION_256K");
        }
        super.setStatus(pStatus);
        this.setPort1FFD(pStatus.getULAStatus().getPort1FFD());
        this.setPort7FFD(pStatus.getULAStatus().getPort7FFD());
        this.getMemoryBank(4).setStatus(pStatus.getMemoryStatus(2));
        this.getMemoryBank(5).setStatus(pStatus.getMemoryStatus(3));
        this.getMemoryBank(6).setStatus(pStatus.getMemoryStatus(4));
        this.getMemoryBank(7).setStatus(pStatus.getMemoryStatus(5));
        this.getMemoryBank(8).setStatus(pStatus.getMemoryStatus(6));
        this.getMemoryBank(9).setStatus(pStatus.getMemoryStatus(7));
        this.getMemoryBank(10).setStatus(pStatus.getMemoryStatus(8));
        this.getMemoryBank(11).setStatus(pStatus.getMemoryStatus(9));
        this.getMemoryBank(12).setStatus(pStatus.getMemoryStatus(100));
        this.getMemoryBank(13).setStatus(pStatus.getMemoryStatus(101));
        this.getMemoryBank(14).setStatus(pStatus.getMemoryStatus(102));
        this.getMemoryBank(15).setStatus(pStatus.getMemoryStatus(103));
        this.getMemoryBank(16).setStatus(pStatus.getMemoryStatus(104));
        this.getMemoryBank(17).setStatus(pStatus.getMemoryStatus(105));
        this.getMemoryBank(18).setStatus(pStatus.getMemoryStatus(106));
        this.getMemoryBank(19).setStatus(pStatus.getMemoryStatus(107));
        this.ivAY38912.setStatus(pStatus.getAY38912Status());
    }
    
    private void updateMemoryConfiguration() {
        int bank = 0;
        switch ((this.ivLastPort7FFD & 0x10) | (this.ivLastPort1FFD & 0x3)) {
            case 0: {
                bank = 0;
                break;
            }
            case 16: {
                bank = 1;
                break;
            }
            case 2: {
                bank = 2;
                break;
            }
            case 18: {
                bank = 2;
                break;
            }
            case 1: {
                bank = 12;
                break;
            }
            case 3: {
                bank = 12;
                break;
            }
            case 17: {
                bank = 12;
                break;
            }
            case 19: {
                bank = 12;
                break;
            }
        }
        this.setMemoryPage(0, bank);
        this.setMemoryPage(1, 9);
        this.setMemoryPage(2, 6);
        switch ((this.ivLastPort7FFD & 0x7) | (this.ivLastPort1FFD & 0x10)) {
            case 0: {
                bank = 4;
                break;
            }
            case 1: {
                bank = 5;
                break;
            }
            case 2: {
                bank = 6;
                break;
            }
            case 3: {
                bank = 7;
                break;
            }
            case 4: {
                bank = 8;
                break;
            }
            case 5: {
                bank = 9;
                break;
            }
            case 6: {
                bank = 10;
                break;
            }
            case 7: {
                bank = 11;
                break;
            }
            case 16: {
                bank = 12;
                break;
            }
            case 17: {
                bank = 13;
                break;
            }
            case 18: {
                bank = 14;
                break;
            }
            case 19: {
                bank = 15;
                break;
            }
            case 20: {
                bank = 16;
                break;
            }
            case 21: {
                bank = 17;
                break;
            }
            case 22: {
                bank = 18;
                break;
            }
            case 23: {
                bank = 19;
                break;
            }
        }
        this.setMemoryPage(3, bank);
        switch (this.ivLastPort7FFD & 0x8) {
            case 0: {
                bank = 9;
                break;
            }
            case 8: {
                bank = 11;
                break;
            }
        }
        this.setScreenMemory(bank);
    }
    
    @Override
    public void writeIO(final int pAddress, final int pValue) {
        if ((pAddress & 0x2) != 0x2) {
            if (pAddress == 8189) {
                this.writePort1FFD(pValue);
            }
            else if (pAddress == 32765) {
                this.writePort7FFD(pValue);
            }
            else if (pAddress == 49149) {
                this.writePortBFFD(pValue);
            }
            else if (pAddress == 65533) {
                this.writePortFFFD(pValue);
            }
        }
        super.writeIO(pAddress, pValue);
    }
    
    protected void writePort1FFD(final int pValue) {
        if ((this.ivLastPort7FFD & 0x20) == 0x0) {
            this.setPort1FFD(pValue);
        }
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
