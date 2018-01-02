// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zxspectrum;

import com.tn.components.PD765ADisk;
import java.io.IOException;
import com.tn.util.SampleStreamer;
import com.tn.zx.ZxKeyboardHandler;
import com.tn.components.MemoryFactory;
import com.tn.components.PD765ADrive;
import com.tn.components.PD765A;
import com.tn.components.AY38912;

public class ZxSpectrumPlus3 extends ZxSpectrumAbstract
{
    public static final int BANK_ROMPLUS3A = 0;
    public static final int BANK_ROMPLUS3B = 1;
    public static final int BANK_ROMPLUS3C = 2;
    public static final int BANK_ROMPLUS3D = 3;
    public static final int BANK_RAM0 = 4;
    public static final int BANK_RAM1 = 5;
    public static final int BANK_RAM2 = 6;
    public static final int BANK_RAM3 = 7;
    public static final int BANK_RAM4 = 8;
    public static final int BANK_RAM5 = 9;
    public static final int BANK_RAM6 = 10;
    public static final int BANK_RAM7 = 11;
    private int ivLastPort1FFD;
    private int ivLastPort7FFD;
    private AY38912 ivAY38912;
    private PD765A ivPd765A;
    private PD765ADrive ivPd765ADrive;
    
    public ZxSpectrumPlus3(final MemoryFactory pMemoryFactory, final ZxKeyboardHandler pKeyboardHandler, final ZxSpectrumScreenConverter pScreenConverter, final SampleStreamer pSampleStreamer) throws IOException {
        super(pMemoryFactory, pKeyboardHandler, pScreenConverter, pSampleStreamer);
        this.setMemoryBank(0, pMemoryFactory.createRom(16384, "zxplus3a.rom"));
        this.setMemoryBank(1, pMemoryFactory.createRom(16384, "zxplus3b.rom"));
        this.setMemoryBank(2, pMemoryFactory.createRom(16384, "zxplus3c.rom"));
        this.setMemoryBank(3, pMemoryFactory.createRom(16384, "zxplus3d.rom"));
        this.setMemoryBank(4, pMemoryFactory.createRam(16384));
        this.setMemoryBank(5, pMemoryFactory.createRam(16384));
        this.setMemoryBank(6, pMemoryFactory.createRam(16384));
        this.setMemoryBank(7, pMemoryFactory.createRam(16384));
        this.setMemoryBank(8, pMemoryFactory.createRam(16384));
        this.setMemoryBank(9, pMemoryFactory.createRam(16384));
        this.setMemoryBank(10, pMemoryFactory.createRam(16384));
        this.setMemoryBank(11, pMemoryFactory.createRam(16384));
        this.ivAY38912 = new AY38912();
        this.ivPd765A = new PD765A();
        this.ivPd765ADrive = new PD765ADrive();
        this.ivPd765A.setDrive(0, this.ivPd765ADrive);
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
        result.setAY38912Status(this.ivAY38912.getStatus());
        result.setPD765AStatus(this.ivPd765A.getStatus());
        result.setPD765ADriveStatus(this.ivPd765ADrive.getStatus());
        result.setComponentType(19);
        return result;
    }
    
    @Override
    public void init() {
        super.init();
        this.setPort1FFD(0);
        this.setPort7FFD(0);
        this.ivAY38912.init();
        this.ivPd765A.init();
        this.ivPd765ADrive.init();
    }
    
    public void insertDisk(final PD765ADisk pDisk) {
        this.ivPd765ADrive.insertDisk(pDisk);
    }
    
    @Override
    public int readIO(final int pAddress) {
        int result = 255;
        if ((pAddress & 0x2) != 0x2) {
            if ((pAddress & 0xF002) == 0x2000) {
                result = this.readPort2FFD();
            }
            else if ((pAddress & 0xF002) == 0x3000) {
                result = this.readPort3FFD();
            }
            else if ((pAddress & 0xC002) == 0xC000) {
                result = this.readPortFFFD();
            }
        }
        return super.readIO(pAddress, result);
    }
    
    protected int readPort2FFD() {
        return this.ivPd765A.readMainStatusRegister();
    }
    
    protected int readPort3FFD() {
        return this.ivPd765A.readDataRegister();
    }
    
    protected int readPortFFFD() {
        return this.ivAY38912.readData();
    }
    
    public PD765ADisk removeDisk() {
        return this.ivPd765ADrive.removeDisk();
    }
    
    @Override
    public void reset() {
        super.reset();
        this.setPort1FFD(0);
        this.setPort7FFD(0);
        this.ivAY38912.reset();
        this.ivPd765A.reset();
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
        if (pStatus.getComponentType() != 19) {
            throw new RuntimeException("Status is not COMPONENT_TYPE_SPECTRUM_128K_P3");
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
        this.ivAY38912.setStatus(pStatus.getAY38912Status());
        this.ivPd765A.setStatus(pStatus.getPD765AStatus());
        this.ivPd765ADrive.setStatus(pStatus.getPD765ADriveStatus());
    }
    
    private void updateMemoryConfiguration() {
        int bank = 0;
        if ((this.ivLastPort1FFD & 0x1) == 0x0) {
            switch ((this.ivLastPort7FFD & 0x10) | (this.ivLastPort1FFD & 0x4)) {
                case 0: {
                    bank = 0;
                    break;
                }
                case 16: {
                    bank = 1;
                    break;
                }
                case 4: {
                    bank = 2;
                    break;
                }
                case 20: {
                    bank = 3;
                    break;
                }
            }
            this.setMemoryPage(0, bank);
            this.setMemoryPage(1, 9);
            this.setMemoryPage(2, 6);
            switch (this.ivLastPort7FFD & 0x7) {
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
            }
            this.setMemoryPage(3, bank);
        }
        else {
            switch (this.ivLastPort1FFD & 0x6) {
                case 0: {
                    this.setMemoryPage(0, 4);
                    this.setMemoryPage(1, 5);
                    this.setMemoryPage(2, 6);
                    this.setMemoryPage(3, 7);
                    break;
                }
                case 2: {
                    this.setMemoryPage(0, 8);
                    this.setMemoryPage(1, 9);
                    this.setMemoryPage(2, 10);
                    this.setMemoryPage(3, 11);
                    break;
                }
                case 4: {
                    this.setMemoryPage(0, 8);
                    this.setMemoryPage(1, 9);
                    this.setMemoryPage(2, 10);
                    this.setMemoryPage(3, 7);
                    break;
                }
                case 6: {
                    this.setMemoryPage(0, 8);
                    this.setMemoryPage(1, 11);
                    this.setMemoryPage(2, 10);
                    this.setMemoryPage(3, 7);
                    break;
                }
            }
        }
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
            if ((pAddress & 0xF002) == 0x1000) {
                this.writePort1FFD(pValue);
            }
            else if ((pAddress & 0xF002) == 0x3000) {
                this.writePort3FFD(pValue);
            }
            else if ((pAddress & 0xC002) == 0x4000) {
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
    
    protected void writePort1FFD(final int pValue) {
        if ((this.ivLastPort7FFD & 0x20) == 0x0) {
            this.setPort1FFD(pValue);
        }
    }
    
    protected void writePort3FFD(final int pValue) {
        this.ivPd765A.writeDataRegister(pValue);
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
