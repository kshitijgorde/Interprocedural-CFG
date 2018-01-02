// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.player;

import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.SampleBuffer;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.JavaLayerException;
import java.io.InputStream;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.Bitstream;

public class Player
{
    private int frame;
    private Bitstream bitstream;
    private Decoder decoder;
    private AudioDevice audio;
    private boolean closed;
    private boolean complete;
    private int lastPosition;
    
    public Player(final InputStream inputStream) throws JavaLayerException {
        this(inputStream, null);
    }
    
    public Player(final InputStream inputStream, final AudioDevice audio) throws JavaLayerException {
        this.frame = 0;
        this.closed = false;
        this.complete = false;
        this.lastPosition = 0;
        this.bitstream = new Bitstream(inputStream);
        this.decoder = new Decoder();
        if (audio != null) {
            this.audio = audio;
        }
        else {
            this.audio = FactoryRegistry.systemRegistry().createAudioDevice();
        }
        this.audio.open(this.decoder);
    }
    
    public void play() throws JavaLayerException {
        this.play(Integer.MAX_VALUE);
    }
    
    public boolean play(int n) throws JavaLayerException {
        boolean decodeFrame;
        for (decodeFrame = true; n-- > 0 && decodeFrame; decodeFrame = this.decodeFrame()) {}
        if (!decodeFrame) {
            final AudioDevice audio = this.audio;
            if (audio != null) {
                audio.flush();
                synchronized (this) {
                    this.complete = !this.closed;
                    this.close();
                }
            }
        }
        return decodeFrame;
    }
    
    public synchronized void close() {
        final AudioDevice audio = this.audio;
        if (audio != null) {
            this.closed = true;
            this.audio = null;
            audio.close();
            this.lastPosition = audio.getPosition();
            try {
                this.bitstream.close();
            }
            catch (BitstreamException ex) {}
        }
    }
    
    public synchronized boolean isComplete() {
        return this.complete;
    }
    
    public int getPosition() {
        int n = this.lastPosition;
        final AudioDevice audio = this.audio;
        if (audio != null) {
            n = audio.getPosition();
        }
        return n;
    }
    
    protected boolean decodeFrame() throws JavaLayerException {
        try {
            if (this.audio == null) {
                return false;
            }
            final Header frame = this.bitstream.readFrame();
            if (frame == null) {
                return false;
            }
            final SampleBuffer sampleBuffer = (SampleBuffer)this.decoder.decodeFrame(frame, this.bitstream);
            synchronized (this) {
                final AudioDevice audio = this.audio;
                if (audio != null) {
                    audio.write(sampleBuffer.getBuffer(), 0, sampleBuffer.getBufferLength());
                }
            }
            this.bitstream.closeFrame();
        }
        catch (RuntimeException ex) {
            throw new JavaLayerException("Exception decoding audio frame", ex);
        }
        return true;
    }
}
