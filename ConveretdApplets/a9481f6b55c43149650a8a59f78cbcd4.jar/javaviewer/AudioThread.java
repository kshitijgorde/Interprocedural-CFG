// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import java.io.ByteArrayInputStream;
import javax.sound.sampled.Line;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Control;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioFormat;

final class AudioThread implements Runnable
{
    static float VOLUME_MIN;
    static float VOLUME_MAX;
    static final int BUFF_NUM = 2;
    static final int AUDIO_BUFF_SIZE = 100000;
    static final int AU_BUFF_SIZE = 64000;
    static int ACTUAL_BUFF_SIZE;
    private boolean _$1161;
    private Viewer _$4305;
    private AudioFormat _$1164;
    private SourceDataLine _$1166;
    private FloatControl _$1930;
    public BooleanControl mute;
    public float volumeValue;
    public byte[] au_buffer;
    public int au_idx;
    public boolean buff_flag;
    public int sdlMaxBuffSize;
    private boolean _$1176;
    volatile Thread runner;
    static /* synthetic */ Class class$javax$sound$sampled$SourceDataLine;
    
    public AudioThread(final Viewer $4305) {
        this._$1161 = true;
        this.au_idx = 0;
        this.buff_flag = false;
        this.sdlMaxBuffSize = 0;
        this._$1176 = true;
        this._$4305 = $4305;
    }
    
    public boolean init() {
        this.au_buffer = new byte[64000];
        this._$1164 = new AudioFormat(8000.0f, 16, 1, true, false);
        if (AudioSystem.getMixer(null) == null) {
            System.err.println("AudioSystem Unavailable, exiting!");
            return this._$1161 = false;
        }
        if (!this._$1184()) {
            System.err.println("AudioSystem Unavailable, exiting!");
            return this._$1161 = false;
        }
        this._$4307();
        this._$4308();
        return true;
    }
    
    private void _$4308() {
        if (this._$4305.pVolumeFlg) {
            this.setVolume(this._$4305.pVolume);
        }
        if (this._$4305.pMuteFlg) {
            this.setMute(true);
        }
    }
    
    private void _$4307() {
        if (this._$1166.isControlSupported(BooleanControl.Type.MUTE)) {
            this.mute = (BooleanControl)this._$1166.getControl(BooleanControl.Type.MUTE);
        }
        if (this._$1166.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            this._$1930 = (FloatControl)this._$1166.getControl(FloatControl.Type.MASTER_GAIN);
            final float minimum = this._$1930.getMinimum();
            final float maximum = this._$1930.getMaximum();
            AudioThread.VOLUME_MIN = minimum;
            if (maximum >= 10.0f && minimum <= -10.0f) {
                AudioThread.VOLUME_MAX = 10.0f;
            }
            else if (maximum < minimum * -1.0f) {
                AudioThread.VOLUME_MAX = maximum;
            }
            else {
                AudioThread.VOLUME_MAX = minimum;
            }
            this._$1930.setValue(this.volumeValue);
        }
    }
    
    private final boolean _$1184() {
        try {
            (this._$1166 = (SourceDataLine)AudioSystem.getLine(new DataLine.Info((AudioThread.class$javax$sound$sampled$SourceDataLine == null) ? (AudioThread.class$javax$sound$sampled$SourceDataLine = class$("javax.sound.sampled.SourceDataLine")) : AudioThread.class$javax$sound$sampled$SourceDataLine, this._$1164))).open(this._$1164, 100000);
            AudioThread.ACTUAL_BUFF_SIZE = this._$1166.getBufferSize();
            this._$1166.start();
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public final void destroy() {
        if (this._$1166.isActive()) {
            this._$1166.close();
        }
        this._$1930 = null;
        this.mute = null;
        this._$1166 = null;
    }
    
    public final void stop() {
        this.runner = null;
        try {
            for (int n = 0; n < 1000 && this._$1176; ++n) {
                synchronized (this) {
                    this.notify();
                }
                Thread.sleep(2L);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public final void run() {
        final Thread currentThread = Thread.currentThread();
        final AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.ULAW, 8000.0f, 8, 1, 1, 8000.0f, false);
        byte[] array = new byte[64000];
        final byte[] array2 = new byte[64000];
        int n = 0;
        int n2 = 0;
        AudioInputStream audioInputStream = null;
        try {
            Thread.currentThread().setPriority(8);
        }
        catch (SecurityException ex) {
            ex.printStackTrace();
        }
        while (this.runner == currentThread) {
            if (!this._$1161) {
                break;
            }
            try {
                synchronized (this) {
                    this.wait();
                }
            }
            catch (Exception ex2) {
                break;
            }
            this.buff_flag = true;
            if (this.au_idx > 16000) {
                this.finishBuffer();
            }
            else {
                try {
                    audioInputStream = new AudioInputStream(new ByteArrayInputStream(this.au_buffer, 0, this.au_idx), audioFormat, this.au_idx);
                    audioInputStream = AudioSystem.getAudioInputStream(this._$1164, audioInputStream);
                    int read;
                    while ((read = audioInputStream.read(array2, 0, array2.length)) != -1) {
                        if (n2 <= 2) {
                            for (int i = 0; i < read; ++i) {
                                array[n++] = array2[i];
                            }
                            if (n2 >= 2) {
                                this._$1166.write(array, 0, n);
                                array = null;
                            }
                            ++n2;
                        }
                        else {
                            final int n3 = AudioThread.ACTUAL_BUFF_SIZE - this._$1166.available();
                            if (n3 < 4000) {
                                this._$1166.write(array2, 0, read);
                            }
                            else {
                                if (n3 >= 6560 || n3 <= 4000) {
                                    continue;
                                }
                                this._$1166.write(array2, 0, 6560 - n3);
                            }
                        }
                    }
                }
                catch (Exception ex3) {}
                this.finishBuffer();
            }
        }
        this._$1176 = false;
        if (audioInputStream != null) {
            try {
                audioInputStream.close();
            }
            catch (Exception ex4) {}
        }
    }
    
    public final void finishBuffer() {
        this.au_idx = 0;
        this.buff_flag = false;
    }
    
    public final void setVolume(final int n) {
        this.volumeValue = -AudioThread.VOLUME_MAX + n * AudioThread.VOLUME_MAX * 2.0f / 100.0f;
        if (this.volumeValue <= -AudioThread.VOLUME_MAX + 0.1) {
            this._$1930.setValue(AudioThread.VOLUME_MIN);
            this.volumeValue = -AudioThread.VOLUME_MAX;
        }
        else {
            this._$1930.setValue(this.volumeValue);
        }
    }
    
    public final void setMute(final boolean value) {
        this.mute.setValue(value);
    }
    
    static {
        AudioThread.ACTUAL_BUFF_SIZE = 100000;
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
