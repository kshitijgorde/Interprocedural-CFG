// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.examples;

import com.jcraft.jogg.StreamState;
import com.jcraft.jogg.Packet;
import com.jcraft.jogg.Page;
import java.io.FileInputStream;
import java.util.Vector;
import com.jcraft.jogg.SyncState;
import java.io.InputStream;

public class OggPerf
{
    private static final int BUFFSIZE = 8192;
    private InputStream inputStream;
    private SyncState oy;
    private Vector streams;
    private boolean stopping;
    
    public OggPerf(final InputStream is) {
        this.inputStream = is;
        this.oy = new SyncState();
        this.streams = new Vector();
        this.stopping = false;
    }
    
    public static void main(final String[] args) {
        try {
            int run = 10;
            final long start = System.currentTimeMillis();
            while (run-- > 0) {
                final OggPerf perf = new OggPerf(new FileInputStream(args[0]));
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
                        if (!stream.bos) {
                            continue;
                        }
                        if (op.packet_base[op.packet + 1] == 118) {
                            System.out.println("found vorbis audio");
                        }
                        else if (op.packet_base[op.packet + 1] == 115) {
                            System.out.println("found smoke video");
                        }
                        else if (op.packet_base[op.packet + 1] == 116) {
                            System.out.println("found theora video");
                        }
                        stream.bos = false;
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
    
    class OggStream
    {
        public int serialno;
        public StreamState os;
        public boolean bos;
        
        public OggStream(final int serial) {
            this.serialno = serial;
            (this.os = new StreamState()).init(serial);
            this.os.reset();
            this.bos = true;
        }
    }
}
