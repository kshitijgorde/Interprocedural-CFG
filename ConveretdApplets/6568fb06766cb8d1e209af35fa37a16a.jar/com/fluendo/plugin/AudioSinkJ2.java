// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import javax.sound.sampled.Mixer;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import com.fluendo.utils.Debug;
import com.fluendo.jst.Object;
import com.fluendo.jst.Message;
import javax.sound.sampled.SourceDataLine;

public class AudioSinkJ2 extends AudioSink
{
    public static final int SEGSIZE = 8192;
    private SourceDataLine line;
    private int channels;
    private long samplesWritten;
    static /* synthetic */ Class class$javax$sound$sampled$SourceDataLine;
    
    public AudioSinkJ2() {
        this.line = null;
    }
    
    protected RingBuffer createRingBuffer() {
        return new RingBuffer(this);
    }
    
    protected boolean open(final RingBuffer ringBuffer) {
        this.channels = ringBuffer.channels;
        this.line = this.openLine(ringBuffer.channels, ringBuffer.rate);
        if (this.line == null) {
            this.postMessage(Message.newError(this, "Could not open audio device."));
            return false;
        }
        Debug.log(3, "line info: available: " + this.line.available());
        Debug.log(3, "line info: buffer: " + this.line.getBufferSize());
        Debug.log(3, "line info: framePosition: " + this.line.getFramePosition());
        ringBuffer.segSize = 8192;
        ringBuffer.segTotal = this.line.getBufferSize() / ringBuffer.segSize;
        while (ringBuffer.segTotal < 4) {
            ringBuffer.segSize >>= 1;
            ringBuffer.segTotal = this.line.getBufferSize() / ringBuffer.segSize;
        }
        ringBuffer.emptySeg = new byte[ringBuffer.segSize];
        this.samplesWritten = 0L;
        this.line.start();
        return true;
    }
    
    protected SourceDataLine openLine(final int n, final int n2) {
        final AudioFormat audioFormat = new AudioFormat(n2, 16, n, true, true);
        final DataLine.Info info = new DataLine.Info((AudioSinkJ2.class$javax$sound$sampled$SourceDataLine == null) ? (AudioSinkJ2.class$javax$sound$sampled$SourceDataLine = class$("javax.sound.sampled.SourceDataLine")) : AudioSinkJ2.class$javax$sound$sampled$SourceDataLine, audioFormat);
        SourceDataLine sourceDataLine = null;
        try {
            final Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
            for (int i = 0; i < mixerInfo.length; ++i) {
                Debug.log(3, "mixer description: " + mixerInfo[i].getDescription() + ", vendor: " + mixerInfo[i].getVendor());
                final String description = mixerInfo[i].getDescription();
                final String vendor = mixerInfo[i].getVendor();
                if (description.indexOf("ALSA") >= 0 || vendor.indexOf("ALSA") >= 0) {
                    if (description.indexOf("IEC958") < 0) {
                        try {
                            final Line.Info[] sourceLineInfo = AudioSystem.getMixer(mixerInfo[i]).getSourceLineInfo(info);
                            for (int j = 0; j < sourceLineInfo.length; ++j) {
                                Debug.log(3, "Mixer supports line: " + sourceLineInfo[j].toString());
                                final AudioFormat[] formats = ((DataLine.Info)sourceLineInfo[j]).getFormats();
                                for (int k = 0; k < formats.length; ++k) {
                                    Debug.log(3, "Format: " + formats[k].toString());
                                }
                            }
                            Debug.log(3, "Attempting to get a line from ALSA mixer");
                            sourceDataLine = (SourceDataLine)AudioSystem.getMixer(mixerInfo[i]).getLine(info);
                            sourceDataLine.open(audioFormat);
                            break;
                        }
                        catch (Exception ex) {
                            if (sourceDataLine != null) {
                                sourceDataLine.close();
                                sourceDataLine = null;
                            }
                            Debug.log(3, "mixer: " + mixerInfo[i].getDescription() + " failed: " + ex);
                        }
                    }
                }
            }
            if (sourceDataLine == null) {
                sourceDataLine = (SourceDataLine)AudioSystem.getLine(info);
                sourceDataLine.open(audioFormat);
            }
        }
        catch (LineUnavailableException ex2) {
            Debug.error(ex2.toString());
            return null;
        }
        catch (Exception ex3) {
            Debug.error(ex3.toString());
            return null;
        }
        return sourceDataLine;
    }
    
    public boolean test() {
        final SourceDataLine openLine = this.openLine(2, 44000);
        if (openLine == null) {
            return false;
        }
        openLine.close();
        return true;
    }
    
    protected boolean close(final RingBuffer ringBuffer) {
        this.line.stop();
        this.line.close();
        return true;
    }
    
    protected int write(final byte[] array, int n, int n2) {
        int n3 = 0;
        if (n < 0 || n >= array.length || n + n2 > array.length || n2 <= 0) {
            Debug.debug("Invalid audio write offset=" + n + ", length=" + n2 + ", data.length=" + array.length);
            return n2;
        }
        while (true) {
            final int available = this.line.available();
            if (n2 <= available) {
                Debug.debug("Doing complete audio write of " + n2 + " bytes");
                n3 += this.line.write(array, n, n2);
                break;
            }
            if (available > 0) {
                Debug.debug("Doing partial audio write of " + available + " bytes");
                n3 += this.line.write(array, n, available);
                n += available;
                n2 -= available;
            }
            if (n2 <= 0) {
                break;
            }
            try {
                final AudioFormat format = this.line.getFormat();
                final long n4 = (long)(this.line.getBufferSize() * 1000 / format.getSampleRate() / format.getSampleSizeInBits() * 8.0f / 4.0f);
                Debug.debug("Sleeping for " + n4 + "ms");
                Thread.sleep(n4);
            }
            catch (InterruptedException ex) {}
        }
        this.samplesWritten += n3 / (2 * this.channels);
        return n3;
    }
    
    protected long delay() {
        return this.samplesWritten - this.line.getFramePosition();
    }
    
    protected void reset() {
        Debug.log(4, "reset audio: " + this.line);
        this.line.flush();
        this.samplesWritten = this.line.getFramePosition();
        Debug.log(4, "samples written: " + this.samplesWritten);
    }
    
    public String getFactoryName() {
        return "audiosinkj2";
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
