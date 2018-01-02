// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import com.fluendo.jst.Caps;
import com.jcraft.jogg.StreamState;
import java.util.Vector;
import com.fluendo.jst.Buffer;
import com.fluendo.jst.Message;
import com.fluendo.utils.Debug;
import com.fluendo.jst.Event;
import com.fluendo.utils.MemUtils;
import com.fluendo.jst.Pad;
import com.jcraft.jogg.Packet;
import com.jcraft.jogg.Page;
import com.jcraft.jogg.SyncState;
import com.fluendo.jst.Element;

public class OggDemux extends Element
{
    private SyncState oy;
    private OggChain chain;
    private Page og;
    private Packet op;
    private static final byte[] signature;
    private static final byte[] fishead_signature;
    private static final byte[] cmml_signature;
    private static final int TYPE_NEW = 0;
    private static final int TYPE_UNKNOWN = 1;
    private static final int TYPE_SKELETON = 2;
    private static final int TYPE_CMML = 3;
    private static final int TYPE_MEDIA = 4;
    private OggPayload[] payloads;
    private Pad sinkPad;
    
    private int combineFlows(final OggStream oggStream, int lastRet) {
        oggStream.lastRet = lastRet;
        if (Pad.isFlowSuccess(lastRet)) {
            return lastRet;
        }
        if (lastRet != -1) {
            return lastRet;
        }
        if (this.chain != null) {
            for (int i = 0; i < this.chain.streams.size(); ++i) {
                lastRet = ((OggStream)this.chain.streams.elementAt(i)).lastRet;
                if (lastRet != -1) {
                    return lastRet;
                }
            }
        }
        return lastRet;
    }
    
    public String getFactoryName() {
        return "oggdemux";
    }
    
    public String getMime() {
        return "application/ogg";
    }
    
    public int typeFind(final byte[] array, final int n, final int n2) {
        if (MemUtils.startsWith(array, n, n2, OggDemux.signature)) {
            return 10;
        }
        return -1;
    }
    
    public OggDemux() {
        this.payloads = new OggPayload[] { new TheoraDec(), new VorbisDec(), new KateDec() };
        this.sinkPad = new Pad(2, "sink") {
            protected boolean eventFunc(final Event event) {
                switch (event.getType()) {
                    case 1: {
                        if (OggDemux.this.chain != null) {
                            OggDemux.this.chain.forwardEvent(event);
                        }
                        synchronized (super.streamLock) {
                            Debug.log(4, this + " synced");
                        }
                        break;
                    }
                    case 2: {
                        OggDemux.this.oy.reset();
                        if (OggDemux.this.chain != null) {
                            OggDemux.this.chain.resetStreams();
                            OggDemux.this.chain.forwardEvent(event);
                            break;
                        }
                        break;
                    }
                    case 4: {
                        break;
                    }
                    case 3: {
                        Debug.log(3, "ogg: got EOS");
                        if (OggDemux.this.chain != null) {
                            OggDemux.this.chain.forwardEvent(event);
                            break;
                        }
                        OggDemux.this.postMessage(Message.newError(this, "unsupported media type"));
                        break;
                    }
                    default: {
                        if (OggDemux.this.chain != null) {
                            OggDemux.this.chain.forwardEvent(event);
                            break;
                        }
                        break;
                    }
                }
                return true;
            }
            
            protected int chainFunc(final Buffer buffer) {
                int i = 0;
                final int buffer2 = OggDemux.this.oy.buffer(buffer.length);
                if (buffer.isFlagSet(1)) {
                    Debug.log(3, "ogg: got discont");
                    if (OggDemux.this.chain != null) {
                        OggDemux.this.chain.markDiscont();
                    }
                }
                System.arraycopy(buffer.data, buffer.offset, OggDemux.this.oy.data, buffer2, buffer.length);
                OggDemux.this.oy.wrote(buffer.length);
                while (i == 0) {
                    final int pageout = OggDemux.this.oy.pageout(OggDemux.this.og);
                    if (pageout == 0) {
                        break;
                    }
                    if (pageout == -1) {
                        Debug.log(2, "ogg: pageout gave " + pageout);
                        if (OggDemux.this.chain == null) {
                            continue;
                        }
                        OggDemux.this.chain.markDiscont();
                    }
                    else {
                        final int serialno = OggDemux.this.og.serialno();
                        OggStream stream = null;
                        if (OggDemux.this.chain != null) {
                            stream = OggDemux.this.chain.findStream(serialno);
                        }
                        if (stream == null) {
                            if (OggDemux.this.chain != null && OggDemux.this.chain.isActive()) {
                                OggDemux.this.chain.deActivate();
                                OggDemux.this.chain = null;
                            }
                            if (OggDemux.this.chain == null) {
                                OggDemux.this.chain = new OggChain();
                            }
                            stream = new OggStream(serialno);
                            OggDemux.this.chain.addStream(stream);
                        }
                        i = OggDemux.this.chain.pushPage(OggDemux.this.og, stream);
                    }
                }
                return i;
            }
            
            protected boolean activateFunc(final int n) {
                if (n == 0) {
                    synchronized (OggDemux.this.sinkPad) {
                        OggDemux.this.oy.reset();
                        if (OggDemux.this.chain != null) {
                            OggDemux.this.chain.deActivate();
                            OggDemux.this.chain = null;
                        }
                    }
                }
                return true;
            }
        };
        this.oy = new SyncState();
        this.og = new Page();
        this.op = new Packet();
        this.chain = null;
        this.addPad(this.sinkPad);
    }
    
    static {
        signature = new byte[] { 79, 103, 103, 83 };
        fishead_signature = new byte[] { 102, 105, 115, 104, 101, 97, 100 };
        cmml_signature = new byte[] { 67, 77, 77, 76 };
    }
    
    class OggChain
    {
        private Vector streams;
        private boolean active;
        private boolean synced;
        private long firstTs;
        
        public OggChain() {
            this.streams = new Vector();
            this.synced = false;
            this.active = false;
            this.firstTs = -1L;
        }
        
        public boolean isActive() {
            return this.active;
        }
        
        public void activate() {
            if (this.active) {
                return;
            }
            Debug.log(4, "activating chain");
            for (int i = 0; i < this.streams.size(); ++i) {
                ((OggStream)this.streams.elementAt(i)).activate();
            }
            this.active = true;
            OggDemux.this.noMorePads();
        }
        
        public void deActivate() {
            if (!this.active) {
                return;
            }
            Debug.log(4, "deActivating chain");
            for (int i = 0; i < this.streams.size(); ++i) {
                ((OggStream)this.streams.elementAt(i)).deActivate();
            }
            this.active = false;
        }
        
        public void reStart() {
            if (!this.active) {
                return;
            }
            if (this.firstTs == -1L) {
                long max = 0L;
                long min = Long.MAX_VALUE;
                for (int i = 0; i < this.streams.size(); ++i) {
                    final OggStream oggStream = this.streams.elementAt(i);
                    if (oggStream.type == 4) {
                        final long firstTs = oggStream.getFirstTs();
                        max = Math.max(max, firstTs);
                        min = Math.min(min, firstTs);
                    }
                }
                this.firstTs = max;
            }
            for (int j = 0; j < this.streams.size(); ++j) {
                ((OggStream)this.streams.elementAt(j)).reStart(this.firstTs);
            }
        }
        
        public void addStream(final OggStream oggStream) {
            this.streams.addElement(oggStream);
        }
        
        public void markDiscont() {
            this.synced = false;
            this.firstTs = -1L;
            for (int i = 0; i < this.streams.size(); ++i) {
                ((OggStream)this.streams.elementAt(i)).markDiscont();
            }
        }
        
        public OggStream findStream(final int n) {
            OggStream oggStream = null;
            for (int i = 0; i < this.streams.size(); ++i) {
                oggStream = (OggStream)this.streams.elementAt(i);
                if (oggStream.serialno == n) {
                    break;
                }
                oggStream = null;
            }
            return oggStream;
        }
        
        public void resetStreams() {
            for (int i = 0; i < this.streams.size(); ++i) {
                ((OggStream)this.streams.elementAt(i)).reset();
            }
        }
        
        public boolean forwardEvent(final Event event) {
            for (int i = 0; i < this.streams.size(); ++i) {
                ((OggStream)this.streams.elementAt(i)).pushEvent(event);
            }
            return true;
        }
        
        public int pushPage(final Page page, final OggStream oggStream) {
            final int pushPage = oggStream.pushPage(page);
            if (!this.synced) {
                boolean complete = true;
                boolean b = false;
                for (int i = 0; i < this.streams.size(); ++i) {
                    final OggStream oggStream2 = this.streams.elementAt(i);
                    if (oggStream2.type == 4) {
                        b = true;
                        if (!(complete = oggStream2.isComplete())) {
                            break;
                        }
                    }
                }
                if (complete && b) {
                    Debug.log(4, "streams synced");
                    this.activate();
                    this.reStart();
                    this.synced = true;
                }
            }
            return pushPage;
        }
    }
    
    class OggStream extends Pad
    {
        public int serialno;
        public StreamState os;
        private Vector headers;
        private long baseTs;
        public boolean haveHeaders;
        public Vector queue;
        public boolean started;
        public boolean complete;
        public boolean discont;
        public boolean active;
        public boolean haveKeyframe;
        public boolean sentHeaders;
        public int type;
        public int lastRet;
        private OggPayload payload;
        
        public OggStream(final int serialno) {
            super(1, "serial_" + serialno);
            this.serialno = serialno;
            (this.os = new StreamState()).init(serialno);
            this.os.reset();
            this.queue = new Vector();
            this.headers = new Vector();
            this.haveHeaders = false;
            this.haveKeyframe = false;
            this.payload = null;
            this.discont = true;
            this.complete = false;
            this.started = false;
            this.baseTs = -1L;
            this.lastRet = 0;
        }
        
        public void markDiscont() {
            this.discont = true;
            this.complete = false;
            this.haveKeyframe = false;
            this.started = false;
        }
        
        public void reset() {
            this.markDiscont();
            this.os.reset();
            this.lastRet = 0;
        }
        
        public boolean isComplete() {
            return this.complete;
        }
        
        public void activate() {
            if (this.active) {
                return;
            }
            this.sentHeaders = false;
            this.lastRet = 0;
            OggDemux.this.addPad(this);
            this.active = true;
        }
        
        public void deActivate() {
            if (!this.active) {
                return;
            }
            OggDemux.this.removePad(this);
            this.pushEvent(Event.newEOS());
            this.active = false;
        }
        
        public void reStart(final long baseTs) {
            if (!this.active) {
                return;
            }
            if (this.baseTs == -1L) {
                this.baseTs = baseTs;
            }
            final long n = baseTs - this.baseTs;
            Debug.log(4, this + " pushing segment start " + baseTs + ", time " + n);
            this.pushEvent(Event.newNewsegment(false, 3, baseTs, -1L, n));
            if (!this.sentHeaders) {
                for (int i = 0; i < this.headers.size(); ++i) {
                    final Buffer buffer = this.headers.elementAt(i);
                    buffer.setFlag(1, this.discont);
                    this.discont = false;
                    this.push(buffer);
                }
                this.sentHeaders = true;
            }
            for (int j = 0; j < this.queue.size(); ++j) {
                final Buffer buffer2 = this.queue.elementAt(j);
                if (j == 0) {
                    Debug.log(4, this + " first data buffer: " + buffer2.timestamp);
                }
                buffer2.setFlag(1, this.discont);
                this.discont = false;
                this.push(buffer2);
            }
            this.queue.setSize(0);
            this.started = true;
        }
        
        public long getFirstTs() {
            return this.payload.getFirstTs(this.queue);
        }
        
        private Buffer bufferFromPacket(final Packet packet) {
            final Buffer create = Buffer.create();
            create.copyData(packet.packet_base, packet.packet, packet.bytes);
            create.time_offset = packet.granulepos;
            if (this.payload != null) {
                create.timestamp = this.payload.granuleToTime(packet.granulepos);
            }
            else {
                create.timestamp = -1L;
            }
            create.setFlag(1, this.discont);
            create.setFlag(2, !this.payload.isKeyFrame(packet));
            return create;
        }
        
        private void initNewStream(final Packet packet) {
            this.payload = null;
            for (int i = 0; i < OggDemux.this.payloads.length; ++i) {
                final OggPayload oggPayload = OggDemux.this.payloads[i];
                if (oggPayload.isType(packet)) {
                    try {
                        this.payload = (OggPayload)oggPayload.getClass().newInstance();
                        this.type = 4;
                        final String mime = this.payload.getMime();
                        Debug.log(3, "new stream " + this.serialno + ", mime " + mime);
                        this.setCaps(new Caps(mime));
                        return;
                    }
                    catch (Exception ex) {}
                }
            }
            if (MemUtils.startsWith(packet.packet_base, packet.packet, packet.bytes, OggDemux.fishead_signature)) {
                this.type = 2;
                Debug.log(3, "ignoring skeleton stream " + this.serialno);
                OggDemux.this.postMessage(Message.newWarning(this, "ignoring skeleton stream " + this.serialno));
                return;
            }
            if (MemUtils.startsWith(packet.packet_base, packet.packet, packet.bytes, OggDemux.cmml_signature)) {
                Debug.log(this.type = 3, "ignoring CMML stream " + this.serialno);
                OggDemux.this.postMessage(Message.newWarning(this, "ignoring CMML stream " + this.serialno));
                return;
            }
            this.type = 1;
            Debug.log(3, "ignoring unknown stream " + this.serialno);
            OggDemux.this.postMessage(Message.newWarning(this, "ignoring unknown stream " + this.serialno));
        }
        
        public int pushPacket(final Packet packet) {
            if (this.type == 0) {
                this.initNewStream(packet);
            }
            if (this.type != 4) {
                this.complete = true;
                return 0;
            }
            if (!this.haveHeaders) {
                if (this.payload.isHeader(packet)) {
                    final int takeHeader = this.payload.takeHeader(packet);
                    if (takeHeader < 0) {
                        OggDemux.this.postMessage(Message.newError(this, "cannot read header"));
                        return -5;
                    }
                    this.headers.addElement(this.bufferFromPacket(packet));
                    if (takeHeader > 0) {
                        this.haveHeaders = true;
                    }
                }
                else {
                    this.haveHeaders = true;
                }
            }
            if (this.haveHeaders) {
                if (!this.complete && this.payload.isDiscontinuous()) {
                    this.complete = true;
                }
                if (this.complete && this.started) {
                    return OggDemux.this.combineFlows(this, this.push(this.bufferFromPacket(packet)));
                }
                if (this.haveKeyframe || this.payload.isKeyFrame(packet)) {
                    this.queue.addElement(this.bufferFromPacket(packet));
                    this.haveKeyframe = true;
                    if (packet.granulepos != -1L) {
                        this.complete = true;
                    }
                }
            }
            return 0;
        }
        
        public int pushPage(final Page page) {
            int i = 0;
            if (this.os.pagein(page) < 0) {
                System.err.println("Error reading first page of Ogg bitstream data.");
                OggDemux.this.postMessage(Message.newError(this, "Error reading first page of Ogg bitstream data."));
                return -5;
            }
            while (i == 0) {
                final int packetout = this.os.packetout(OggDemux.this.op);
                if (packetout == 0) {
                    break;
                }
                if (packetout == -1) {
                    Debug.log(2, "ogg error: packetout gave " + packetout);
                    this.discont = true;
                }
                else {
                    i = this.pushPacket(OggDemux.this.op);
                }
            }
            return i;
        }
        
        protected boolean eventFunc(final Event event) {
            return OggDemux.this.sinkPad.pushEvent(event);
        }
    }
}
