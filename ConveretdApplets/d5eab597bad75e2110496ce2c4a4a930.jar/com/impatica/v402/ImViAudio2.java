// 
// Decompiled by Procyon v0.5.30
// 

package com.impatica.v402;

import javax.sound.sampled.Control;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

public class ImViAudio2 implements ImVi
{
    SourceDataLine MASTER_GAIN;
    int available;
    int flush;
    FloatControl forName;
    static final AudioFormat getControl;
    static Class getFramePosition;
    
    public final int I() {
        return this.MASTER_GAIN.getFramePosition() + this.flush;
    }
    
    public ImViAudio2(final ImSound imSound) {
        final DataLine.Info info = new DataLine.Info((ImViAudio2.getFramePosition == null) ? (ImViAudio2.getFramePosition = MASTER_GAIN("javax.sound.sampled.SourceDataLine")) : ImViAudio2.getFramePosition, ImViAudio2.getControl);
        SourceDataLine master_GAIN;
        try {
            master_GAIN = (SourceDataLine)AudioSystem.getLine(info);
            master_GAIN.open(ImViAudio2.getControl, 8192);
        }
        catch (LineUnavailableException ex) {
            master_GAIN = null;
        }
        this.MASTER_GAIN = master_GAIN;
    }
    
    public final boolean Z() {
        return true;
    }
    
    public final boolean I(final ImIsys imIsys, final ImIstream imIstream) {
        return false;
    }
    
    public final void I(final boolean b) {
    }
    
    public final void I(final int n, final int n2, final int n3, final int n4) {
    }
    
    public final void I(final int available) {
        this.available = available;
        if (this.MASTER_GAIN != null) {
            this.flush = this.available - this.MASTER_GAIN.getFramePosition();
        }
    }
    
    public final void Z(final int n) {
        if (this.MASTER_GAIN == null) {
            return;
        }
        if (this.forName == null) {
            this.forName = (FloatControl)this.MASTER_GAIN.getControl(FloatControl.Type.MASTER_GAIN);
        }
        if (this.forName == null) {
            return;
        }
        final double n2 = n / 255.0;
        this.forName.setValue((float)(Math.log((n2 == 0.0) ? 1.0E-4 : n2) / Math.log(10.0) * 20.0));
    }
    
    public final void start() {
        if (this.MASTER_GAIN != null) {
            this.MASTER_GAIN.start();
        }
    }
    
    public final void stop() {
        if (this.MASTER_GAIN != null) {
            this.MASTER_GAIN.stop();
            this.MASTER_GAIN.flush();
        }
    }
    
    public final int I(final byte[] array, final int n, int n2) {
        final SourceDataLine master_GAIN = this.MASTER_GAIN;
        if (master_GAIN == null) {
            return 0;
        }
        final int n3 = master_GAIN.available() - 2;
        if (n2 >= n3) {
            n2 = n3;
        }
        if (n2 <= 0) {
            return 0;
        }
        return master_GAIN.write(array, n, n2);
    }
    
    private static final Class MASTER_GAIN(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        getControl = new AudioFormat(8000.0f, 16, 1, true, true);
    }
}
