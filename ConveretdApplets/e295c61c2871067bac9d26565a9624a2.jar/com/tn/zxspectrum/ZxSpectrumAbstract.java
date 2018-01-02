// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zxspectrum;

import com.tn.components.MemoryFactory;
import com.tn.components.Memory;
import com.tn.util.SampleStreamer;
import com.tn.zx.ZxKeyboardHandler;
import com.tn.zx.ZxAbstract;

public abstract class ZxSpectrumAbstract extends ZxAbstract
{
    private int ivLastPortFE;
    private int ivFlashCount;
    private ZxKeyboardHandler ivKeyboardHandler;
    private ZxSpectrumScreenConverter ivScreenConverter;
    private SampleStreamer ivSampleStreamer;
    private short[] ivSampleBuffer;
    private int ivSampleCount;
    private Memory ivScreenMemory;
    protected byte[] ivContention;
    protected short[] ivFloatAddr;
    
    public ZxSpectrumAbstract(final MemoryFactory pMemoryFactory, final ZxKeyboardHandler pKeyboardHandler, final ZxSpectrumScreenConverter pScreenConverter, final SampleStreamer pSampleStreamer) {
        super(pMemoryFactory);
        this.ivKeyboardHandler = pKeyboardHandler;
        this.ivScreenConverter = pScreenConverter;
        this.ivSampleStreamer = pSampleStreamer;
        this.ivSampleBuffer = new short[1024];
        this.ivSampleCount = 0;
        this.ivContention = new byte[71000];
        this.ivFloatAddr = new short[71000];
    }
    
    protected void convertPixelLine(final int pLineNumber) {
        this.ivScreenConverter.updatePixelLine(this.ivScreenMemory.getBytesRead(), 0, pLineNumber, this.ivLastPortFE);
    }
    
    public ZxKeyboardHandler getKeyboardHandler() {
        return this.ivKeyboardHandler;
    }
    
    public int getLastPortFE() {
        return this.ivLastPortFE;
    }
    
    public SampleStreamer getSampleStreamer() {
        return this.ivSampleStreamer;
    }
    
    public ZxSpectrumScreenConverter getScreenConverter() {
        return this.ivScreenConverter;
    }
    
    public SpectrumStatus getStatus() {
        final SpectrumStatus result = new SpectrumStatus();
        result.setZ80Status(this.getZ80().getStatus());
        result.setULAStatus(new SpectrumULAStatus());
        result.getULAStatus().setPortFE(this.ivLastPortFE);
        return result;
    }
    
    protected void queueSample(final short pSample) {
        this.ivSampleBuffer[this.ivSampleCount] = pSample;
        ++this.ivSampleCount;
        if (this.ivSampleCount >= this.ivSampleBuffer.length) {
            this.ivSampleBuffer = new short[this.ivSampleBuffer.length * 2];
        }
    }
    
    @Override
    protected int readIO(final int pAddress, int pValue) {
        if ((pAddress & 0xC000) == 0x4000) {
            if ((pAddress & 0x1) == 0x0) {
                this.ivZ80.delay(this.ivContention[(int)this.ivZ80.getClock()]);
                this.ivZ80.delay(this.ivContention[(int)this.ivZ80.getClock() + 1]);
            }
            else {
                this.ivZ80.delay(this.ivContention[(int)this.ivZ80.getClock()]);
                this.ivZ80.delay(this.ivContention[(int)this.ivZ80.getClock() + 1]);
                this.ivZ80.delay(this.ivContention[(int)this.ivZ80.getClock() + 2]);
                this.ivZ80.delay(this.ivContention[(int)this.ivZ80.getClock() + 3]);
            }
        }
        else if ((pAddress & 0x1) == 0x0) {
            this.ivZ80.delay(this.ivContention[(int)this.ivZ80.getClock() + 1]);
        }
        if ((pAddress & 0x1) == 0x1) {
            final int adr = this.ivFloatAddr[(int)this.ivZ80.getClock()];
            if (adr > 0) {
                pValue = this.ivScreenMemory.getByte(adr & 0x3FFF);
            }
        }
        if ((pAddress & 0x1) == 0x0) {
            pValue = this.readPortFE(pAddress);
        }
        return super.readIO(pAddress, pValue);
    }
    
    @Override
    public final int readMem(final int pAddress) {
        if ((pAddress & 0xC000) == 0x4000) {
            this.ivZ80.delay(this.ivContention[(int)this.ivZ80.getClock()]);
        }
        return super.readMem(pAddress);
    }
    
    @Override
    public final int readOpcode(final int pAddress) {
        if ((pAddress & 0xC000) == 0x4000) {
            this.ivZ80.delay(this.ivContention[(int)this.ivZ80.getClock()]);
        }
        return super.readOpcode(pAddress);
    }
    
    protected int readPortFE(final int pAddress) {
        return this.ivKeyboardHandler.getKeyMask(pAddress >> 8);
    }
    
    @Override
    public void reset() {
        super.reset();
        this.ivLastPortFE = 255;
    }
    
    public void runOneFrame() {
        this.getScreenConverter().beginFrame();
        this.runOneFrameSpecific();
        ++this.ivFlashCount;
        this.ivScreenConverter.setFlash((this.ivFlashCount & 0x10) != 0x0);
        this.ivKeyboardHandler.convertKeyboard();
        if (this.ivSampleStreamer != null && this.ivSampleCount > 0) {
            try {
                this.ivSampleStreamer.queueSamples(this.ivSampleBuffer, 0, this.ivSampleCount);
                this.ivSampleCount = 0;
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("Sound emulation deactivated");
                this.ivSampleStreamer = null;
            }
        }
        this.getScreenConverter().endFrame();
    }
    
    protected abstract void runOneFrameSpecific();
    
    public void setKeyboardHandler(final ZxKeyboardHandler pKeyboardHandler) {
        this.ivKeyboardHandler = pKeyboardHandler;
    }
    
    public void setSampleStreamer(final SampleStreamer pSampleStreamer) {
        this.ivSampleStreamer = pSampleStreamer;
    }
    
    public void setScreenConverter(final ZxSpectrumScreenConverter pScreenConverter) {
        this.ivScreenConverter = pScreenConverter;
    }
    
    protected void setScreenMemory(final int pBankIndex) {
        this.setScreenMemory(this.getMemoryBank(pBankIndex));
    }
    
    protected void setScreenMemory(final Memory pMemoryBank) {
        this.ivScreenMemory = pMemoryBank;
    }
    
    public void setStatus(final SpectrumStatus pStatus) {
        this.getZ80().setStatus(pStatus.getZ80Status());
        this.ivLastPortFE = pStatus.getULAStatus().getPortFE();
    }
    
    @Override
    public void writeIO(final int pAddress, final int pValue) {
        if ((pAddress & 0xC000) == 0x4000) {
            if ((pAddress & 0x1) == 0x0) {
                this.ivZ80.delay(this.ivContention[(int)this.ivZ80.getClock()]);
                this.ivZ80.delay(this.ivContention[(int)this.ivZ80.getClock() + 1]);
            }
            else {
                this.ivZ80.delay(this.ivContention[(int)this.ivZ80.getClock()]);
                this.ivZ80.delay(this.ivContention[(int)this.ivZ80.getClock() + 1]);
                this.ivZ80.delay(this.ivContention[(int)this.ivZ80.getClock() + 2]);
                this.ivZ80.delay(this.ivContention[(int)this.ivZ80.getClock() + 3]);
            }
        }
        else if ((pAddress & 0x1) == 0x0) {
            this.ivZ80.delay(this.ivContention[(int)this.ivZ80.getClock() + 1]);
        }
        if ((pAddress & 0x1) == 0x0) {
            this.writePortFE(pValue);
        }
        super.writeIO(pAddress, pValue);
    }
    
    @Override
    public final void writeMem(final int pAddress, final int pValue) {
        if ((pAddress & 0xC000) == 0x4000) {
            this.ivZ80.delay(this.ivContention[(int)this.ivZ80.getClock()]);
        }
        super.writeMem(pAddress, pValue);
    }
    
    protected void writePortFE(final int pValue) {
        this.ivLastPortFE = pValue;
    }
}
