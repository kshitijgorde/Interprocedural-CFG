// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.util;

import com.softsynth.jsyn.SynthException;
import com.softsynth.jsyn.SynthSample;
import com.softsynth.jsyn.SampleReader_16V1;
import com.softsynth.jsyn.LineOut;
import com.softsynth.jsyn.SynthContext;

public class SampleStreamerJSyn2 implements SampleStreamer
{
    private SynthContext ivSynthContext;
    private LineOut ivLineOut;
    private SampleReader_16V1 ivSampleReader_16V1;
    private SynthSample[] ivSynthSampleBufferTable;
    private static final int ivNumberOfSynthSampleBuffers = 6;
    private int ivIndexFreeSynthSample;
    private int ivNumberOfSamplesQueued;
    private int ivSampleSize;
    private short[] ivSampleBuffer;
    
    public SampleStreamerJSyn2() throws SynthException {
        this.ivIndexFreeSynthSample = 0;
        this.ivSampleSize = 0;
        this.initialize();
    }
    
    private void configure(final int pSampleSize) throws SynthException {
        this.ivSampleSize = pSampleSize;
        this.ivSynthSampleBufferTable = new SynthSample[6];
        for (int i = 0; i < 6; ++i) {
            this.ivSynthSampleBufferTable[i] = new SynthSample(this.ivSynthContext, pSampleSize);
        }
        this.ivSampleBuffer = new short[pSampleSize];
        final int rate = (int)(50 * pSampleSize * 0.99787);
        this.ivSampleReader_16V1.rate.set((double)rate);
    }
    
    private void initialize() throws SynthException {
        (this.ivSynthContext = new SynthContext()).startEngine(0);
        this.ivSynthContext.enableDeletionByGarbageCollector(false);
        this.ivLineOut = new LineOut(this.ivSynthContext);
        this.ivSampleReader_16V1 = new SampleReader_16V1(this.ivSynthContext);
        this.ivSampleReader_16V1.rate.set(10000.0);
        this.ivSampleReader_16V1.amplitude.set(1.0);
        this.ivSampleReader_16V1.output.connect(0, this.ivLineOut.input, 0);
        this.ivSampleReader_16V1.output.connect(0, this.ivLineOut.input, 1);
        this.ivLineOut.start();
        this.ivSampleReader_16V1.start();
    }
    
    @Override
    public void queueSamples(final short[] pSamples, final int pOffset, final int pLength) {
        if (this.ivSampleSize != pLength) {
            this.configure(pLength);
        }
        final int remaining = this.ivNumberOfSamplesQueued - this.ivSampleReader_16V1.samplePort.getNumFramesMoved();
        if (remaining > this.ivSampleSize * 5) {
            return;
        }
        System.arraycopy(pSamples, pOffset, this.ivSampleBuffer, 0, pLength);
        this.ivSynthSampleBufferTable[this.ivIndexFreeSynthSample].write(this.ivSampleBuffer);
        this.ivSampleReader_16V1.samplePort.queue(this.ivSynthSampleBufferTable[this.ivIndexFreeSynthSample]);
        this.ivNumberOfSamplesQueued += this.ivSampleSize - 1;
        if (++this.ivIndexFreeSynthSample >= 6) {
            this.ivIndexFreeSynthSample = 0;
        }
    }
    
    public void terminate() {
        try {
            this.ivSynthContext.terminate();
        }
        catch (Exception ex) {}
    }
}
