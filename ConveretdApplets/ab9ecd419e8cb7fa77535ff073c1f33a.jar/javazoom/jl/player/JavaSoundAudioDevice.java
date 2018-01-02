// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.player;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javazoom.jl.decoder.JavaLayerException;
import javax.sound.sampled.DataLine;
import javazoom.jl.decoder.Decoder;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;

public class JavaSoundAudioDevice extends AudioDeviceBase
{
    private SourceDataLine source;
    private AudioFormat fmt;
    private byte[] byteBuf;
    static /* synthetic */ Class class$javax$sound$sampled$SourceDataLine;
    
    public JavaSoundAudioDevice() {
        this.source = null;
        this.fmt = null;
        this.byteBuf = new byte[1024];
    }
    
    protected void setAudioFormat(final AudioFormat fmt) {
        this.fmt = fmt;
    }
    
    protected AudioFormat getAudioFormat() {
        if (this.fmt == null) {
            final Decoder decoder = this.getDecoder();
            this.fmt = new AudioFormat(decoder.getOutputFrequency(), 16, decoder.getOutputChannels(), true, false);
        }
        return this.fmt;
    }
    
    protected DataLine.Info getSourceLineInfo() {
        return new DataLine.Info((JavaSoundAudioDevice.class$javax$sound$sampled$SourceDataLine == null) ? (JavaSoundAudioDevice.class$javax$sound$sampled$SourceDataLine = class$("javax.sound.sampled.SourceDataLine")) : JavaSoundAudioDevice.class$javax$sound$sampled$SourceDataLine, this.getAudioFormat(), 4000);
    }
    
    public void open(final AudioFormat audioFormat) throws JavaLayerException {
        if (!this.isOpen()) {
            this.setAudioFormat(audioFormat);
            this.openImpl();
            this.setOpen(true);
        }
    }
    
    protected void openImpl() throws JavaLayerException {
    }
    
    protected void createSource() throws JavaLayerException {
        Throwable t = null;
        try {
            final Line line = AudioSystem.getLine(this.getSourceLineInfo());
            if (line instanceof SourceDataLine) {
                (this.source = (SourceDataLine)line).open(this.fmt, this.millisecondsToBytes(this.fmt, 2000));
                this.source.start();
            }
        }
        catch (RuntimeException ex) {
            t = ex;
        }
        catch (LinkageError linkageError) {
            t = linkageError;
        }
        catch (LineUnavailableException ex2) {
            t = ex2;
        }
        if (this.source == null) {
            throw new JavaLayerException("cannot obtain source audio line", t);
        }
    }
    
    public int millisecondsToBytes(final AudioFormat audioFormat, final int n) {
        return (int)(n * (audioFormat.getSampleRate() * audioFormat.getChannels() * audioFormat.getSampleSizeInBits()) / 8000.0);
    }
    
    protected void closeImpl() {
        if (this.source != null) {
            this.source.close();
        }
    }
    
    protected void writeImpl(final short[] array, final int n, final int n2) throws JavaLayerException {
        if (this.source == null) {
            this.createSource();
        }
        this.source.write(this.toByteArray(array, n, n2), 0, n2 * 2);
    }
    
    protected byte[] getByteArray(final int n) {
        if (this.byteBuf.length < n) {
            this.byteBuf = new byte[n + 1024];
        }
        return this.byteBuf;
    }
    
    protected byte[] toByteArray(final short[] array, int n, int n2) {
        final byte[] byteArray = this.getByteArray(n2 * 2);
        int n3 = 0;
        while (n2-- > 0) {
            final short n4 = array[n++];
            byteArray[n3++] = (byte)n4;
            byteArray[n3++] = (byte)(n4 >>> 8);
        }
        return byteArray;
    }
    
    protected void flushImpl() {
        if (this.source != null) {
            this.source.drain();
        }
    }
    
    public int getPosition() {
        int n = 0;
        if (this.source != null) {
            n = (int)(this.source.getMicrosecondPosition() / 1000L);
        }
        return n;
    }
    
    public void test() throws JavaLayerException {
        try {
            this.open(new AudioFormat(22050.0f, 16, 1, true, false));
            final short[] array = new short[2205];
            this.write(array, 0, array.length);
            this.flush();
            this.close();
        }
        catch (RuntimeException ex) {
            throw new JavaLayerException("Device test failed: " + ex);
        }
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
