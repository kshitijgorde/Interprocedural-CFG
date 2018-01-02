// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

import javax.sound.sampled.Control;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

public class SIDMixerSE extends SIDMixer
{
    private SourceDataLine dataLine;
    private SourceDataLine syncDataLine;
    private FloatControl volume;
    byte[] syncBuffer;
    static /* synthetic */ Class class$javax$sound$sampled$SourceDataLine;
    
    public SIDMixerSE() {
        this.syncBuffer = new byte[4096];
    }
    
    public SIDMixerSE(final SID6581[] array, final SID sid) {
        this.syncBuffer = new byte[4096];
        this.init(array, sid);
    }
    
    public void init(final SID6581[] array, final SID sid) {
        super.init(array, sid);
        final AudioFormat audioFormat = new AudioFormat(44000.0f, 16, 1, true, false);
        final DataLine.Info info = new DataLine.Info((SIDMixerSE.class$javax$sound$sampled$SourceDataLine == null) ? (SIDMixerSE.class$javax$sound$sampled$SourceDataLine = class$("javax.sound.sampled.SourceDataLine")) : SIDMixerSE.class$javax$sound$sampled$SourceDataLine, audioFormat, SIDMixerSE.DL_BUFFER_SIZE);
        final DataLine.Info info2 = new DataLine.Info((SIDMixerSE.class$javax$sound$sampled$SourceDataLine == null) ? (SIDMixerSE.class$javax$sound$sampled$SourceDataLine = class$("javax.sound.sampled.SourceDataLine")) : SIDMixerSE.class$javax$sound$sampled$SourceDataLine, audioFormat, 1760);
        try {
            this.dataLine = (SourceDataLine)AudioSystem.getLine(info);
            this.syncDataLine = (SourceDataLine)AudioSystem.getLine(info2);
            if (this.dataLine == null) {
                System.out.println("DataLine: not existing...");
            }
            else {
                System.out.println("Format: " + this.dataLine.getFormat());
                this.dataLine.open(this.dataLine.getFormat(), SIDMixerSE.DL_BUFFER_SIZE);
                this.syncDataLine.open(this.syncDataLine.getFormat(), 1760);
                this.volume = (FloatControl)this.dataLine.getControl(FloatControl.Type.MASTER_GAIN);
                this.setMasterVolume(100);
                this.dataLine.start();
                this.syncDataLine.start();
            }
        }
        catch (Exception ex) {
            System.out.println("Problem while getting data line ");
            ex.printStackTrace();
        }
    }
    
    public void write(final byte[] array) {
        if (!this.fullSpeed && this.getBufferLevel() > 80.0) {
            this.dataLine.write(array, 0, 88);
            this.syncDataLine.write(this.syncBuffer, 0, 88);
        }
        else {
            this.dataLine.write(array, 0, 88);
        }
    }
    
    public long getMicros() {
        if (this.dataLine == null) {
            return System.currentTimeMillis() * 1000L;
        }
        return this.dataLine.getMicrosecondPosition();
    }
    
    public boolean hasSound() {
        return this.dataLine != null;
    }
    
    public int available() {
        return this.dataLine.available();
    }
    
    public void setMasterVolume(final int masterVolume) {
        this.masterVolume = masterVolume;
        if (this.volume != null) {
            this.volume.setValue(-10.0f + 0.1f * masterVolume);
        }
    }
    
    public int getMasterVolume() {
        return this.masterVolume;
    }
    
    public double getBufferLevel() {
        if (this.dataLine != null) {
            return 100.0 - 100.0 * this.dataLine.available() / SIDMixerSE.DL_BUFFER_SIZE;
        }
        return 0.0;
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
