// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zxspectrum;

import java.io.IOException;
import com.tn.util.SampleStreamer;
import com.tn.zx.ZxKeyboardHandler;
import com.tn.components.MemoryFactory;

public class ZxSpectrum16K extends ZxSpectrumAbstract
{
    public static final int BANK_ROM48 = 0;
    public static final int BANK_RAM4000 = 1;
    public static final int SCREEN_CYCLE_1 = 14335;
    public static final int BORDER_CYCLE_1 = 14339;
    
    public ZxSpectrum16K(final MemoryFactory pMemoryFactory, final ZxKeyboardHandler pKeyboardHandler, final ZxSpectrumScreenConverter pScreenConverter, final SampleStreamer pSampleStreamer) throws IOException {
        super(pMemoryFactory, pKeyboardHandler, pScreenConverter, pSampleStreamer);
        this.setMemoryBank(0, pMemoryFactory.createRom(16384, "spectrum.rom"));
        this.setMemoryBank(1, pMemoryFactory.createRam(16384));
        int cycle = 14335;
        for (int i = 0; i < 192; ++i) {
            for (int j = 0; j < 128; ++j) {
                this.ivContention[cycle++] = (byte)(((j & 0x7) < 6) ? (6 - (j & 0x7)) : 0);
            }
            cycle += 96;
        }
        cycle = 14335;
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
                    cycle += 96;
                }
            }
        }
    }
    
    @Override
    public SpectrumStatus getStatus() {
        final SpectrumStatus result = super.getStatus();
        result.setMemoryStatus(10, this.getMemoryBank(1).getStatus());
        result.setComponentType(15);
        return result;
    }
    
    @Override
    public void init() {
        super.init();
        this.setMemoryPage(0, 0);
        this.setMemoryPage(1, 1);
        this.setScreenMemory(1);
    }
    
    @Override
    protected void runOneFrameSpecific() {
        if (this.getSampleStreamer() == null) {
            for (int i = 0; i < 312; ++i) {
                this.getZ80().decodeInstruction(224);
                this.convertPixelLine(i);
            }
        }
        else {
            for (int i = 0; i < 312; ++i) {
                this.getZ80().decodeInstruction(75);
                this.queueSample((short)((this.getLastPortFE() & 0x10) << 9));
                this.getZ80().decodeInstruction(75);
                this.queueSample((short)((this.getLastPortFE() & 0x10) << 9));
                this.getZ80().decodeInstruction(74);
                this.queueSample((short)((this.getLastPortFE() & 0x10) << 9));
                this.convertPixelLine(i);
            }
        }
        this.getZ80().performINT();
        this.getZ80().subtractClk(69888L);
    }
    
    @Override
    public void setStatus(final SpectrumStatus pStatus) {
        pStatus.getComponentType();
        super.setStatus(pStatus);
        this.getMemoryBank(1).setStatus(pStatus.getMemoryStatus(10));
    }
}
