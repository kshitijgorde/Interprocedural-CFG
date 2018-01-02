// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zxspectrum;

import java.io.IOException;
import com.tn.util.SampleStreamer;
import com.tn.zx.ZxKeyboardHandler;
import com.tn.components.MemoryFactory;

public class ZxSpectrum48K extends ZxSpectrum16K
{
    public static final int BANK_RAM8000 = 2;
    public static final int BANK_RAMC000 = 3;
    
    public ZxSpectrum48K(final MemoryFactory pMemoryFactory, final ZxKeyboardHandler pKeyboardHandler, final ZxSpectrumScreenConverter pScreenConverter, final SampleStreamer pSampleStreamer) throws IOException {
        super(pMemoryFactory, pKeyboardHandler, pScreenConverter, pSampleStreamer);
        this.setMemoryBank(2, pMemoryFactory.createRam(16384));
        this.setMemoryBank(3, pMemoryFactory.createRam(16384));
    }
    
    @Override
    public SpectrumStatus getStatus() {
        final SpectrumStatus result = super.getStatus();
        result.setMemoryStatus(11, this.getMemoryBank(2).getStatus());
        result.setMemoryStatus(12, this.getMemoryBank(3).getStatus());
        result.setComponentType(16);
        return result;
    }
    
    @Override
    public void init() {
        super.init();
        this.setMemoryPage(2, 2);
        this.setMemoryPage(3, 3);
    }
    
    @Override
    public void setStatus(final SpectrumStatus pStatus) {
        if (pStatus.getComponentType() != 16) {
            throw new RuntimeException("Status is not COMPONENT_TYPE_SPECTRUM_48K");
        }
        super.setStatus(pStatus);
        this.getMemoryBank(2).setStatus(pStatus.getMemoryStatus(11));
        this.getMemoryBank(3).setStatus(pStatus.getMemoryStatus(12));
    }
}
