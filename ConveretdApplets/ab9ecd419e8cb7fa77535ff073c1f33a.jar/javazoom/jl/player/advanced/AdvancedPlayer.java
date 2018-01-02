// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.player.advanced;

import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.SampleBuffer;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.decoder.JavaLayerException;
import java.io.InputStream;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.Bitstream;

public class AdvancedPlayer
{
    private Bitstream bitstream;
    private Decoder decoder;
    private AudioDevice audio;
    private boolean closed;
    private boolean complete;
    private int lastPosition;
    private PlaybackListener listener;
    
    public AdvancedPlayer(final InputStream inputStream) throws JavaLayerException {
        this(inputStream, null);
    }
    
    public AdvancedPlayer(final InputStream inputStream, final AudioDevice audio) throws JavaLayerException {
        this.closed = false;
        this.complete = false;
        this.lastPosition = 0;
        this.bitstream = new Bitstream(inputStream);
        if (audio != null) {
            this.audio = audio;
        }
        else {
            this.audio = FactoryRegistry.systemRegistry().createAudioDevice();
        }
        this.audio.open(this.decoder = new Decoder());
    }
    
    public void play() throws JavaLayerException {
        this.play(Integer.MAX_VALUE);
    }
    
    public boolean play(int n) throws JavaLayerException {
        boolean decodeFrame = true;
        if (this.listener != null) {
            this.listener.playbackStarted(this.createEvent(PlaybackEvent.STARTED));
        }
        while (n-- > 0 && decodeFrame) {
            decodeFrame = this.decodeFrame();
        }
        final AudioDevice audio = this.audio;
        if (audio != null) {
            audio.flush();
            synchronized (this) {
                this.complete = !this.closed;
                this.close();
            }
            if (this.listener != null) {
                this.listener.playbackFinished(this.createEvent(audio, PlaybackEvent.STOPPED));
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
    
    protected boolean skipFrame() throws JavaLayerException {
        if (this.bitstream.readFrame() == null) {
            return false;
        }
        this.bitstream.closeFrame();
        return true;
    }
    
    public boolean play(final int n, final int n2) throws JavaLayerException {
        boolean skipFrame = true;
        for (int n3 = n; n3-- > 0 && skipFrame; skipFrame = this.skipFrame()) {}
        return this.play(n2 - n);
    }
    
    private PlaybackEvent createEvent(final int n) {
        return this.createEvent(this.audio, n);
    }
    
    private PlaybackEvent createEvent(final AudioDevice audioDevice, final int n) {
        return new PlaybackEvent(this, n, audioDevice.getPosition());
    }
    
    public void setPlayBackListener(final PlaybackListener listener) {
        this.listener = listener;
    }
    
    public PlaybackListener getPlayBackListener() {
        return this.listener;
    }
    
    public void stop() {
        this.listener.playbackFinished(this.createEvent(PlaybackEvent.STOPPED));
        this.close();
    }
}
