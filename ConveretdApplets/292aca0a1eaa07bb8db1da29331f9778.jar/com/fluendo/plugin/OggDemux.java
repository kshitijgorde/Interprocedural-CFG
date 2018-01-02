// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import com.fluendo.jst.Caps;
import java.util.Vector;
import com.jcraft.jogg.StreamState;
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
    
    private int combineFlows(final OggStream stream, int ret) {
        stream.lastRet = ret;
        if (Pad.isFlowSuccess(ret)) {
            return ret;
        }
        if (ret != -1) {
            return ret;
        }
        if (this.chain != null) {
            for (int i = 0; i < this.chain.streams.size(); ++i) {
                final OggStream ostream = this.chain.streams.elementAt(i);
                ret = ostream.lastRet;
                if (ret != -1) {
                    return ret;
                }
            }
        }
        return ret;
    }
    
    public String getFactoryName() {
        return "oggdemux";
    }
    
    public String getMime() {
        return "application/ogg";
    }
    
    public int typeFind(final byte[] data, final int offset, final int length) {
        if (MemUtils.startsWith(data, offset, length, OggDemux.signature)) {
            return 10;
        }
        return -1;
    }
    
    public OggDemux() {
        this.payloads = new OggPayload[] { new TheoraDec(), new VorbisDec() };
        this.sinkPad = new Pad(2, "sink") {
            protected boolean eventFunc(final Event event) {
                switch (event.getType()) {
                    case 1: {
                        if (OggDemux.this.chain != null) {
                            OggDemux.this.chain.forwardEvent(event);
                        }
                        synchronized (this.streamLock) {
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
            
            protected int chainFunc(final Buffer buf) {
                int flowRet = 0;
                final int index = OggDemux.this.oy.buffer(buf.length);
                if (buf.isFlagSet(1)) {
                    Debug.log(3, "ogg: got discont");
                    if (OggDemux.this.chain != null) {
                        OggDemux.this.chain.markDiscont();
                    }
                }
                System.arraycopy(buf.data, buf.offset, OggDemux.this.oy.data, index, buf.length);
                OggDemux.this.oy.wrote(buf.length);
                while (flowRet == 0) {
                    final int res = OggDemux.this.oy.pageout(OggDemux.this.og);
                    if (res == 0) {
                        break;
                    }
                    if (res == -1) {
                        Debug.log(2, "ogg: pageout gave " + res);
                        if (OggDemux.this.chain == null) {
                            continue;
                        }
                        OggDemux.this.chain.markDiscont();
                    }
                    else {
                        final int serial = OggDemux.this.og.serialno();
                        OggStream stream = null;
                        if (OggDemux.this.chain != null) {
                            stream = OggDemux.this.chain.findStream(serial);
                        }
                        if (stream == null) {
                            if (OggDemux.this.chain != null && OggDemux.this.chain.isActive()) {
                                OggDemux.this.chain.deActivate();
                                OggDemux.this.chain = null;
                            }
                            if (OggDemux.this.chain == null) {
                                OggDemux.this.chain = new OggChain();
                            }
                            stream = new OggStream(serial);
                            OggDemux.this.chain.addStream(stream);
                        }
                        flowRet = OggDemux.this.chain.pushPage(OggDemux.this.og, stream);
                    }
                }
                return flowRet;
            }
            
            protected boolean activateFunc(final int mode) {
                if (mode == 0) {
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
    
    class OggStream extends Pad
    {
        public int serialno;
        public StreamState os;
        private Vector headers;
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
        
        public OggStream(final int serial) {
            super(1, "serial_" + serial);
            this.serialno = serial;
            (this.os = new StreamState()).init(serial);
            this.os.reset();
            this.queue = new Vector();
            this.headers = new Vector();
            this.haveHeaders = false;
            this.haveKeyframe = false;
            this.payload = null;
            this.discont = true;
            this.complete = false;
            this.started = false;
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
        
        public void reStart(final long firstTs) {
            if (!this.active) {
                return;
            }
            Debug.log(4, this + " pushing segment start " + firstTs);
            this.pushEvent(Event.newNewsegment(false, 3, firstTs, -1L, firstTs));
            if (!this.sentHeaders) {
                for (int i = 0; i < this.headers.size(); ++i) {
                    final Buffer buf = this.headers.elementAt(i);
                    buf.setFlag(1, this.discont);
                    this.discont = false;
                    this.push(buf);
                }
                this.sentHeaders = true;
            }
            for (int i = 0; i < this.queue.size(); ++i) {
                final Buffer buf = this.queue.elementAt(i);
                if (i == 0) {
                    Debug.log(4, this + " pushing first data buffer: " + buf.timestamp);
                }
                buf.setFlag(1, this.discont);
                this.discont = false;
                this.push(buf);
            }
            this.queue.setSize(0);
            this.started = true;
        }
        
        public long getFirstTs() {
            return this.payload.getFirstTs(this.queue);
        }
        
        private Buffer bufferFromPacket(final Packet op) {
            final Buffer data = Buffer.create();
            data.copyData(op.packet_base, op.packet, op.bytes);
            data.time_offset = op.granulepos;
            if (this.payload != null) {
                data.timestamp = this.payload.granuleToTime(op.granulepos);
            }
            else {
                data.timestamp = -1L;
            }
            data.setFlag(1, this.discont);
            data.setFlag(2, !this.payload.isKeyFrame(op));
            return data;
        }
        
        private void initNewStream(final Packet op) {
            this.payload = null;
            for (int i = 0; i < OggDemux.this.payloads.length; ++i) {
                final OggPayload pl = OggDemux.this.payloads[i];
                if (pl.isType(op)) {
                    try {
                        this.payload = (OggPayload)pl.getClass().newInstance();
                        this.type = 4;
                        final String mime = this.payload.getMime();
                        Debug.log(3, "new stream " + this.serialno + ", mime " + mime);
                        this.setCaps(new Caps(mime));
                        return;
                    }
                    catch (Exception ex) {}
                }
            }
            if (MemUtils.startsWith(op.packet_base, op.packet, op.bytes, OggDemux.fishead_signature)) {
                this.type = 2;
                Debug.log(3, "ignoring skeleton stream " + this.serialno);
                OggDemux.this.postMessage(Message.newWarning(this, "ignoring skeleton stream " + this.serialno));
                return;
            }
            if (MemUtils.startsWith(op.packet_base, op.packet, op.bytes, OggDemux.cmml_signature)) {
                Debug.log(this.type = 3, "ignoring CMML stream " + this.serialno);
                OggDemux.this.postMessage(Message.newWarning(this, "ignoring CMML stream " + this.serialno));
                return;
            }
            this.type = 1;
            Debug.log(3, "ignoring unknown stream " + this.serialno);
            OggDemux.this.postMessage(Message.newWarning(this, "ignoring unknown stream " + this.serialno));
        }
        
        public int pushPacket(final Packet op) {
            if (this.type == 0) {
                this.initNewStream(op);
            }
            if (this.type != 4) {
                this.complete = true;
                return 0;
            }
            if (!this.haveHeaders) {
                if (this.payload.isHeader(op)) {
                    if (this.payload.takeHeader(op) < 0) {
                        OggDemux.this.postMessage(Message.newError(this, "cannot read header"));
                        return -5;
                    }
                    final Buffer data = this.bufferFromPacket(op);
                    this.headers.addElement(data);
                }
                else {
                    this.haveHeaders = true;
                }
            }
            if (this.haveHeaders) {
                if (this.complete && this.started) {
                    final Buffer data2 = this.bufferFromPacket(op);
                    final int ret = this.push(data2);
                    return OggDemux.this.combineFlows(this, ret);
                }
                if (this.haveKeyframe || this.payload.isKeyFrame(op)) {
                    final Buffer data = this.bufferFromPacket(op);
                    this.queue.addElement(data);
                    this.haveKeyframe = true;
                    if (op.granulepos != -1L) {
                        this.complete = true;
                    }
                }
            }
            return 0;
        }
        
        public int pushPage(final Page og) {
            int flowRet = 0;
            int res = this.os.pagein(og);
            if (res < 0) {
                System.err.println("Error reading first page of Ogg bitstream data.");
                OggDemux.this.postMessage(Message.newError(this, "Error reading first page of Ogg bitstream data."));
                return -5;
            }
            while (flowRet == 0) {
                res = this.os.packetout(OggDemux.this.op);
                if (res == 0) {
                    break;
                }
                if (res == -1) {
                    Debug.log(2, "ogg error: packetout gave " + res);
                    this.discont = true;
                }
                else {
                    flowRet = this.pushPacket(OggDemux.this.op);
                }
            }
            return flowRet;
        }
        
        protected boolean eventFunc(final Event event) {
            return OggDemux.this.sinkPad.pushEvent(event);
        }
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
                final OggStream stream = this.streams.elementAt(i);
                stream.activate();
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
                final OggStream stream = this.streams.elementAt(i);
                stream.deActivate();
            }
            this.active = false;
        }
        
        public void reStart() {
            if (!this.active) {
                return;
            }
            if (this.firstTs == -1L) {
                long maxTs = 0L;
                long minTs = Long.MAX_VALUE;
                for (int i = 0; i < this.streams.size(); ++i) {
                    final OggStream stream = this.streams.elementAt(i);
                    if (stream.type == 4) {
                        final long ts = stream.getFirstTs();
                        maxTs = Math.max(maxTs, ts);
                        minTs = Math.min(minTs, ts);
                    }
                }
                this.firstTs = maxTs;
            }
            for (int j = 0; j < this.streams.size(); ++j) {
                final OggStream stream2 = this.streams.elementAt(j);
                stream2.reStart(this.firstTs);
            }
        }
        
        public void addStream(final OggStream stream) {
            this.streams.addElement(stream);
        }
        
        public void markDiscont() {
            this.synced = false;
            this.firstTs = -1L;
            for (int i = 0; i < this.streams.size(); ++i) {
                final OggStream stream = this.streams.elementAt(i);
                stream.markDiscont();
            }
        }
        
        public OggStream findStream(final int serial) {
            OggStream stream = null;
            for (int i = 0; i < this.streams.size(); ++i) {
                stream = this.streams.elementAt(i);
                if (stream.serialno == serial) {
                    break;
                }
                stream = null;
            }
            return stream;
        }
        
        public void resetStreams() {
            for (int i = 0; i < this.streams.size(); ++i) {
                final OggStream stream = this.streams.elementAt(i);
                stream.reset();
            }
        }
        
        public boolean forwardEvent(final Event event) {
            for (int i = 0; i < this.streams.size(); ++i) {
                final OggStream stream = this.streams.elementAt(i);
                stream.pushEvent(event);
            }
            return true;
        }
        
        public int pushPage(final Page og, final OggStream stream) {
            int flowRet = 0;
            flowRet = stream.pushPage(og);
            if (!this.synced) {
                boolean check = true;
                for (int i = 0; i < this.streams.size(); ++i) {
                    final OggStream cstream = this.streams.elementAt(i);
                    if (!(check = cstream.isComplete())) {
                        break;
                    }
                }
                if (check) {
                    Debug.log(4, "steams synced");
                    this.activate();
                    this.reStart();
                    this.synced = true;
                }
            }
            return flowRet;
        }
    }
}
