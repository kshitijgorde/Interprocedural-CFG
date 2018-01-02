// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.util;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;

public class SampleStreamerJavax implements SampleStreamer
{
    private SourceDataLine ivLineOut;
    private int ivNumberOfSamplesQueued;
    private int ivSampleSize;
    private byte[] ivSampleBuffer;
    
    public SampleStreamerJavax() {
        this.ivSampleSize = 0;
        this.configure(1000);
    }
    
    private void configure(final int pSampleSize) {
        this.ivSampleSize = pSampleSize;
        this.ivSampleBuffer = new byte[pSampleSize];
        this.ivNumberOfSamplesQueued = 0;
        try {
            this.terminate();
            final int rate = (int)(50 * pSampleSize * 0.99787);
            final AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, rate, 8, 1, 1, rate, true);
            final DataLine.Info infoOut = new DataLine.Info(SourceDataLine.class, format);
            if (!AudioSystem.isLineSupported(infoOut)) {
                throw new RuntimeException("infoOut not supported...");
            }
            (this.ivLineOut = (SourceDataLine)AudioSystem.getLine(infoOut)).open(format, 8192);
            this.ivLineOut.start();
        }
        catch (LineUnavailableException e) {
            throw new RuntimeException("Line unavailable");
        }
    }
    
    @Override
    public void queueSamples(final short[] pSamples, int pOffset, final int pLength) {
        if (this.ivSampleSize != pLength) {
            this.configure(pLength);
        }
        final int remaining = this.ivNumberOfSamplesQueued - this.ivLineOut.getFramePosition();
        if (this.ivLineOut.getBufferSize() < remaining + pLength) {
            return;
        }
        for (int i = 0; i < pLength; ++i) {
            this.ivSampleBuffer[i] = (byte)(pSamples[pOffset++] >> 8);
        }
        this.ivLineOut.write(this.ivSampleBuffer, 0, pLength);
        this.ivNumberOfSamplesQueued += this.ivSampleSize;
    }
    
    public void terminate() {
        if (this.ivLineOut != null) {
            try {
                this.ivLineOut.stop();
                this.ivLineOut.close();
            }
            catch (Throwable t) {
                System.out.println("Could not terminate SourceDataLine: ");
                t.printStackTrace();
            }
        }
    }
}
