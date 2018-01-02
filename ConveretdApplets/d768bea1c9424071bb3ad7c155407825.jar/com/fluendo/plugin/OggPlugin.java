// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import com.jcraft.jogg.StreamState;
import java.io.IOException;
import com.fluendo.player.MediaBuffer;
import com.fluendo.utils.Debug;
import com.jcraft.jogg.Packet;
import com.jcraft.jogg.Page;
import java.util.Vector;
import com.jcraft.jogg.SyncState;
import java.awt.Component;
import java.io.InputStream;
import com.fluendo.player.DataConsumer;
import com.fluendo.player.Plugin;

public class OggPlugin extends Plugin
{
    private static final int BUFFSIZE = 4096;
    private DataConsumer audioConsumer;
    private DataConsumer videoConsumer;
    private InputStream inputStream;
    private Component component;
    private SyncState oy;
    private Vector streams;
    private Page og;
    private Packet op;
    private boolean stopping;
    
    public OggPlugin() {
        super(3);
    }
    
    public String getMime() {
        return "application/ogg";
    }
    
    public int typeFind(final byte[] array, final int n, final int n2) {
        return 0;
    }
    
    public void initDemuxer(final InputStream inputStream, final Component component, final DataConsumer audioConsumer, final DataConsumer videoConsumer) {
        this.inputStream = inputStream;
        this.audioConsumer = audioConsumer;
        this.videoConsumer = videoConsumer;
        this.component = component;
        this.oy = new SyncState();
        this.streams = new Vector();
        this.stopping = false;
        this.og = new Page();
        this.op = new Packet();
    }
    
    public boolean demux() throws IOException {
        final int read = this.inputStream.read(this.oy.data, this.oy.buffer(4096), 4096);
        if (read < 0) {
            return false;
        }
        this.oy.wrote(read);
        while (!this.stopping) {
            final int pageout = this.oy.pageout(this.og);
            if (pageout == 0) {
                break;
            }
            if (pageout == -1) {
                continue;
            }
            final int serialno = this.og.serialno();
            OggStream oggStream = null;
            for (int i = 0; i < this.streams.size(); ++i) {
                oggStream = (OggStream)this.streams.elementAt(i);
                if (oggStream.serialno == serialno) {
                    break;
                }
                oggStream = null;
            }
            if (oggStream == null) {
                Debug.log(3, "new stream " + serialno);
                oggStream = new OggStream(serialno);
                this.streams.addElement(oggStream);
            }
            if (oggStream.os.pagein(this.og) < 0) {
                System.err.println("Error reading first page of Ogg bitstream data.");
                return false;
            }
            while (!this.stopping) {
                final int packetout = oggStream.os.packetout(this.op);
                if (packetout == 0) {
                    break;
                }
                if (packetout == -1) {
                    Debug.log(2, "ogg error: packetout gave " + packetout);
                }
                else {
                    if (oggStream.bos) {
                        final Plugin typeFind = Plugin.makeTypeFind(this.op.packet_base, this.op.packet, this.op.bytes);
                        if (typeFind == null) {
                            throw new RuntimeException("unkown stream type");
                        }
                        typeFind.initDecoder(this.component);
                        if (typeFind.type == 1) {
                            if (this.audioConsumer != null) {
                                this.audioConsumer.setPlugin(typeFind);
                            }
                            oggStream.consumer = this.audioConsumer;
                        }
                        else {
                            if (typeFind.type != 2) {
                                throw new RuntimeException("unkown plugin type");
                            }
                            if (this.videoConsumer != null) {
                                Debug.log(3, "setting up plugin for video consumer");
                                this.videoConsumer.setPlugin(typeFind);
                            }
                            oggStream.consumer = this.videoConsumer;
                        }
                        oggStream.bos = false;
                    }
                    if (oggStream.consumer == null) {
                        continue;
                    }
                    final MediaBuffer create = MediaBuffer.create();
                    create.copyData(this.op.packet_base, this.op.packet, this.op.bytes);
                    create.time_offset = this.op.granulepos;
                    create.timestamp = -1L;
                    oggStream.consumer.consume(create);
                }
            }
        }
        return !this.stopping;
    }
    
    public void stop() {
        this.stopping = true;
    }
    
    class OggStream
    {
        public int serialno;
        public StreamState os;
        public boolean bos;
        DataConsumer consumer;
        
        public OggStream(final int serialno) {
            this.serialno = serialno;
            (this.os = new StreamState()).init(serialno);
            this.os.reset();
            this.bos = true;
        }
    }
}
