// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.examples;

import com.jcraft.jogg.StreamState;
import com.fluendo.jheora.YUVBuffer;
import com.fluendo.jheora.State;
import com.fluendo.jheora.Comment;
import com.fluendo.jheora.Info;
import com.jcraft.jogg.Packet;
import com.jcraft.jogg.Page;
import java.io.FileInputStream;
import java.util.Vector;
import com.jcraft.jogg.SyncState;
import java.io.InputStream;

public class OggTheoraPerf
{
    private static final int BUFFSIZE = 8192;
    private InputStream inputStream;
    private SyncState oy;
    private Vector streams;
    private boolean stopping;
    
    public OggTheoraPerf(final InputStream is) {
        this.inputStream = is;
        this.oy = new SyncState();
        this.streams = new Vector();
        this.stopping = false;
    }
    
    public static void main(final String[] args) {
        try {
            int run = 1;
            final long start = System.currentTimeMillis();
            while (run-- > 0) {
                final OggTheoraPerf perf = new OggTheoraPerf(new FileInputStream(args[0]));
                perf.start();
            }
            final long end = System.currentTimeMillis();
            System.out.println("ellapsed: " + (end - start));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void start() {
        System.out.println("started ogg reader");
        final Page og = new Page();
        final Packet op = new Packet();
        try {
            while (!this.stopping) {
                final int index = this.oy.buffer(8192);
                final int read = this.inputStream.read(this.oy.data, index, 8192);
                if (read < 0) {
                    break;
                }
                this.oy.wrote(read);
                while (!this.stopping) {
                    int res = this.oy.pageout(og);
                    if (res == 0) {
                        break;
                    }
                    if (res == -1) {
                        continue;
                    }
                    final int serial = og.serialno();
                    OggStream stream = null;
                    for (int i = 0; i < this.streams.size(); ++i) {
                        stream = this.streams.elementAt(i);
                        if (stream.serialno == serial) {
                            break;
                        }
                        stream = null;
                    }
                    if (stream == null) {
                        System.out.println("new stream " + serial);
                        stream = new OggStream(serial);
                        this.streams.addElement(stream);
                    }
                    res = stream.os.pagein(og);
                    if (res < 0) {
                        System.err.println("Error reading first page of Ogg bitstream data.");
                        return;
                    }
                    while (!this.stopping) {
                        res = stream.os.packetout(op);
                        if (res == 0) {
                            break;
                        }
                        if (res == -1) {
                            continue;
                        }
                        if (stream.bos) {
                            if (op.packet_base[op.packet + 1] == 118) {
                                System.out.println("found vorbis audio");
                            }
                            else if (op.packet_base[op.packet + 1] == 115) {
                                System.out.println("found smoke video");
                            }
                            else if (op.packet_base[op.packet + 1] == 116) {
                                System.out.println("found theora video");
                                stream.consumer = new TheoraDec();
                            }
                            stream.bos = false;
                        }
                        if (stream.consumer == null) {
                            continue;
                        }
                        stream.consumer.consume(op);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            this.stopping = true;
        }
        System.out.println("ogg reader done");
    }
    
    public void stop() {
        this.stopping = true;
    }
    
    class TheoraDec implements Consumer
    {
        private Info ti;
        private Comment tc;
        private State ts;
        private int packet;
        private YUVBuffer yuv;
        
        public TheoraDec() {
            this.packet = 0;
            this.ti = new Info();
            this.tc = new Comment();
            this.ts = new State();
            this.yuv = new YUVBuffer();
        }
        
        public void consume(final Packet op) {
            if (this.packet < 3) {
                if (this.ti.decodeHeader(this.tc, op) < 0) {
                    System.err.println("does not contain Theora video data.");
                    return;
                }
                if (this.packet == 2) {
                    this.ts.decodeInit(this.ti);
                    System.out.println("theora dimension: " + this.ti.width + "x" + this.ti.height);
                    System.out.println("theora aspect: " + this.ti.aspect_numerator + "x" + this.ti.aspect_denominator);
                    System.out.println("theora framerate: " + this.ti.fps_numerator + "x" + this.ti.fps_denominator);
                }
            }
            else {
                if (this.ts.decodePacketin(op) != 0) {
                    System.err.println("Error Decoding Theora.");
                    return;
                }
                if (this.ts.decodeYUVout(this.yuv) != 0) {
                    System.err.println("Error getting the picture.");
                    return;
                }
            }
            ++this.packet;
        }
    }
    
    class OggStream
    {
        public int serialno;
        public StreamState os;
        public boolean bos;
        public Consumer consumer;
        
        public OggStream(final int serial) {
            this.serialno = serial;
            (this.os = new StreamState()).init(serial);
            this.os.reset();
            this.bos = true;
        }
    }
    
    interface Consumer
    {
        void consume(final Packet p0);
    }
}
