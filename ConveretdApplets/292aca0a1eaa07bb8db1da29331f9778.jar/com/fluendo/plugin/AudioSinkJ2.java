// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import com.util.ObjectByte;
import com.fluendo.utils.Debug;
import javax.sound.sampled.LineUnavailableException;
import com.fluendo.jst.Object;
import com.fluendo.jst.Message;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import java.util.LinkedList;
import javax.sound.sampled.SourceDataLine;

public class AudioSinkJ2 extends AudioSink
{
    public static final int SEGSIZE = 8192;
    private SourceDataLine line;
    private int channels;
    private long samplesWritten;
    private boolean liveStream;
    private LinkedList replayBuffer;
    private int replayBufferSize;
    private int bufferReplayIndex;
    private int index;
    private int sizeBytes1s;
    private int timeSRePlay;
    static /* synthetic */ Class class$javax$sound$sampled$SourceDataLine;
    
    public AudioSinkJ2() {
        this.line = null;
        this.liveStream = true;
        this.replayBuffer = new LinkedList();
        this.replayBufferSize = 0;
        this.bufferReplayIndex = 0;
        this.index = 0;
        this.timeSRePlay = 30;
    }
    
    public int getTimeSRePlay() {
        return this.timeSRePlay;
    }
    
    public void setTimeSRePlay(final int timeSRePlay) {
        this.timeSRePlay = timeSRePlay;
    }
    
    public boolean isLiveStream() {
        return this.liveStream;
    }
    
    public void setLiveStream(final boolean liveStream) {
        this.liveStream = liveStream;
    }
    
    protected RingBuffer createRingBuffer() {
        return new RingBuffer(this);
    }
    
    protected boolean open(final RingBuffer ring) {
        this.channels = ring.channels;
        final AudioFormat format = new AudioFormat(ring.rate, 16, ring.channels, true, true);
        final DataLine.Info info = new DataLine.Info((AudioSinkJ2.class$javax$sound$sampled$SourceDataLine == null) ? (AudioSinkJ2.class$javax$sound$sampled$SourceDataLine = class$("javax.sound.sampled.SourceDataLine")) : AudioSinkJ2.class$javax$sound$sampled$SourceDataLine, format);
        System.out.println("format.getChannels()-->" + format.getChannels());
        System.out.println("format.getSampleRate()-->" + format.getSampleRate());
        System.out.println("format.getSampleSizeInBits()-->" + format.getSampleSizeInBits());
        this.sizeBytes1s = (int)(format.getChannels() * format.getSampleRate() * (format.getSampleSizeInBits() / 8));
        try {
            (this.line = (SourceDataLine)AudioSystem.getLine(info)).open(format);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
            this.postMessage(Message.newError(this, "Could not open audio device."));
            return false;
        }
        catch (Exception e2) {
            e2.printStackTrace();
            this.postMessage(Message.newError(this, "Unknown problem opening audio device"));
            return false;
        }
        Debug.log(3, "line info: available: " + this.line.available());
        Debug.log(3, "line info: buffer: " + this.line.getBufferSize());
        Debug.log(3, "line info: framePosition: " + this.line.getFramePosition());
        ring.segSize = 8192;
        ring.segTotal = this.line.getBufferSize() / ring.segSize;
        while (ring.segTotal < 4) {
            ring.segSize >>= 1;
            ring.segTotal = this.line.getBufferSize() / ring.segSize;
        }
        ring.emptySeg = new byte[ring.segSize];
        this.samplesWritten = 0L;
        this.line.start();
        return true;
    }
    
    protected boolean close(final RingBuffer ring) {
        this.line.stop();
        this.line.close();
        return true;
    }
    
    protected int write(final byte[] data, final int offset, final int length) {
        int written = 0;
        ++this.index;
        if (this.replayBufferSize == 0) {
            this.replayBufferSize = this.sizeBytes1s * this.timeSRePlay / length;
        }
        if (!this.liveStream) {
            if (this.bufferReplayIndex < this.replayBufferSize) {
                ++this.bufferReplayIndex;
                final byte[] data2 = new byte[length];
                System.arraycopy(data, offset, data2, 0, length);
                final ObjectByte dataByte = new ObjectByte(data2);
                dataByte.setLength(length);
                dataByte.setOffset(0);
                dataByte.index = this.index;
                this.replayBuffer.addLast(dataByte);
                final ObjectByte replayData = this.replayBuffer.getFirst();
                written = this.line.write(replayData.getData(), replayData.getOffset(), replayData.getLength());
                this.samplesWritten += written / (2 * this.channels);
                this.replayBuffer.removeFirst();
            }
            else {
                this.bufferReplayIndex = 0;
                this.liveStream = true;
            }
        }
        if (this.liveStream) {
            final byte[] data2 = new byte[data.length];
            System.arraycopy(data, offset, data2, 0, length);
            final ObjectByte dataByte = new ObjectByte(data2);
            dataByte.setLength(length);
            dataByte.setOffset(0);
            dataByte.index = this.index;
            this.replayBuffer.addLast(dataByte);
            written = this.line.write(data, offset, length);
            this.samplesWritten += written / (2 * this.channels);
            if (this.replayBuffer.size() > this.replayBufferSize) {
                this.replayBuffer.removeFirst();
            }
        }
        return written;
    }
    
    protected long delay() {
        final int frame = this.line.getFramePosition();
        final long delay = this.samplesWritten - frame;
        return delay;
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
    
    public void setLiveStreaming(final boolean live) {
        this.setLiveStream(live);
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError().initCause(x);
        }
    }
}
