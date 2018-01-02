// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import com.fluendo.jst.Buffer;
import com.fluendo.jst.Event;
import com.fluendo.utils.Debug;
import com.fluendo.jst.Message;
import com.fluendo.jst.Caps;
import com.fluendo.jst.Pad;
import java.util.Vector;
import com.fluendo.jst.Element;

public class MultipartDemux extends Element
{
    private static final String MIME = "multipart/x-mixed-replace";
    private static final String DEFAULT_BOUNDARY = "--ThisRandomString";
    private Vector streams;
    private byte[] accum;
    private int accumSize;
    private int accumPos;
    private int dataEnd;
    private static final int STATE_FIND_BOUNDARY = 1;
    private static final int STATE_PARSE_HEADERS = 2;
    private static final int STATE_FIND_DATA_END = 3;
    private int state;
    private String boundaryString;
    private byte[] boundary;
    private int boundaryLen;
    private static final byte[] headerEnd;
    private static final int headerEndLen;
    private static final String contentType = "content-type: ";
    private static final int contentTypeLen;
    private MultipartStream currentStream;
    private Pad sinkpad;
    
    public String getFactoryName() {
        return "multipartdemux";
    }
    
    public String getMime() {
        return "multipart/x-mixed-replace";
    }
    
    public int typeFind(final byte[] data, final int offset, final int length) {
        return -1;
    }
    
    public MultipartDemux() {
        this.state = 1;
        this.boundaryString = "--ThisRandomString";
        this.boundary = this.boundaryString.getBytes();
        this.boundaryLen = this.boundary.length;
        this.currentStream = null;
        this.sinkpad = new Pad(2, "sink") {
            protected boolean setCapsFunc(final Caps caps) {
                final String mime = caps.getMime();
                if (!mime.equals("multipart/x-mixed-replace")) {
                    MultipartDemux.this.postMessage(Message.newError(this, "expected \"" + mime + "\", got \"" + mime + "\""));
                    return false;
                }
                final String capsBoundary = caps.getFieldString("boundary", "--ThisRandomString");
                Debug.log(3, this + " boundary string: \"" + capsBoundary + "\"");
                MultipartDemux.this.boundaryString = capsBoundary + "\n";
                MultipartDemux.this.boundary = MultipartDemux.this.boundaryString.getBytes();
                MultipartDemux.this.boundaryLen = MultipartDemux.this.boundary.length;
                return true;
            }
            
            private MultipartStream findStream(final String mime) {
                MultipartStream stream = null;
                for (int i = 0; i < MultipartDemux.this.streams.size(); ++i) {
                    stream = MultipartDemux.this.streams.elementAt(i);
                    if (stream.mimeType.equals(mime)) {
                        break;
                    }
                    stream = null;
                }
                return stream;
            }
            
            private boolean forwardEvent(final Event event) {
                for (int i = 0; i < MultipartDemux.this.streams.size(); ++i) {
                    final MultipartStream stream = MultipartDemux.this.streams.elementAt(i);
                    stream.pushEvent(event);
                }
                return true;
            }
            
            protected boolean eventFunc(final Event event) {
                switch (event.getType()) {
                    case 1: {
                        this.forwardEvent(event);
                        synchronized (this.streamLock) {
                            Debug.log(3, "synced " + this);
                        }
                        break;
                    }
                    case 2:
                    case 3:
                    case 4: {
                        synchronized (this.streamLock) {
                            this.forwardEvent(event);
                        }
                        break;
                    }
                    default: {
                        this.forwardEvent(event);
                        break;
                    }
                }
                return true;
            }
            
            private void accumulateBuffer(final Buffer buf) {
                int lastPos = MultipartDemux.this.accumSize + MultipartDemux.this.accumPos;
                if (MultipartDemux.this.accum.length < lastPos + buf.length) {
                    final byte[] newAcum = new byte[MultipartDemux.this.accum.length + buf.length];
                    System.arraycopy(MultipartDemux.this.accum, MultipartDemux.this.accumPos, newAcum, 0, MultipartDemux.this.accumSize);
                    MultipartDemux.this.accum = newAcum;
                    MultipartDemux.this.accumPos = 0;
                    lastPos = MultipartDemux.this.accumSize;
                }
                System.arraycopy(buf.data, buf.offset, MultipartDemux.this.accum, lastPos, buf.length);
                MultipartDemux.this.accumSize += buf.length;
            }
            
            private void flushBytes(final int bytes) {
                MultipartDemux.this.accumPos += bytes;
                MultipartDemux.this.accumSize -= bytes;
            }
            
            private int findBytes(int startPos, final byte[] bytes, final int bytesLen) {
                int scanPos = startPos;
                int pos = 0;
                for (int size = MultipartDemux.this.accumSize; size > bytesLen; --size) {
                    if (MultipartDemux.this.accum[scanPos] == bytes[pos]) {
                        if (++pos == bytesLen) {
                            return startPos;
                        }
                    }
                    else {
                        scanPos -= pos;
                        size += pos;
                        startPos = scanPos + 1;
                        pos = 0;
                    }
                    ++scanPos;
                }
                return -1;
            }
            
            private boolean findBoundary() {
                final int pos = this.findBytes(MultipartDemux.this.accumPos, MultipartDemux.this.boundary, MultipartDemux.this.boundaryLen);
                if (pos != -1) {
                    this.flushBytes(pos - MultipartDemux.this.accumPos);
                }
                return pos != -1;
            }
            
            private boolean parseHeaders() {
                int headerStart = MultipartDemux.this.accumPos;
                while (true) {
                    final int prevHdr = headerStart;
                    final int pos = this.findBytes(headerStart, MultipartDemux.headerEnd, MultipartDemux.headerEndLen);
                    if (pos == -1) {
                        return false;
                    }
                    if (pos == prevHdr) {
                        this.flushBytes(pos + 1 - MultipartDemux.this.accumPos);
                        return true;
                    }
                    String header = new String(MultipartDemux.this.accum, headerStart, pos - headerStart);
                    header = header.toLowerCase();
                    if (header.startsWith("content-type: ")) {
                        final String mime = header.substring(MultipartDemux.contentTypeLen).trim();
                        MultipartDemux.this.currentStream = this.findStream(mime);
                        if (MultipartDemux.this.currentStream == null) {
                            MultipartDemux.this.currentStream = new MultipartStream(mime);
                            MultipartDemux.this.streams.addElement(MultipartDemux.this.currentStream);
                            MultipartDemux.this.addPad(MultipartDemux.this.currentStream);
                        }
                    }
                    headerStart = pos + 1;
                }
            }
            
            private boolean findDataEnd() {
                final int pos = this.findBytes(MultipartDemux.this.accumPos, MultipartDemux.this.boundary, MultipartDemux.this.boundaryLen);
                if (pos != -1) {
                    MultipartDemux.this.dataEnd = pos - 1;
                }
                return pos != -1;
            }
            
            protected int chainFunc(final Buffer buf) {
                int flowRet = 0;
                this.accumulateBuffer(buf);
                buf.free();
                switch (MultipartDemux.this.state) {
                    case 1: {
                        if (!this.findBoundary()) {
                            break;
                        }
                        this.flushBytes(MultipartDemux.this.boundary.length);
                        MultipartDemux.this.state = 2;
                    }
                    case 2: {
                        if (!this.parseHeaders()) {
                            break;
                        }
                        MultipartDemux.this.state = 3;
                    }
                    case 3: {
                        if (!this.findDataEnd()) {
                            break;
                        }
                        final Buffer data = Buffer.create();
                        final int dataSize = MultipartDemux.this.dataEnd - MultipartDemux.this.accumPos;
                        data.copyData(MultipartDemux.this.accum, MultipartDemux.this.accumPos, dataSize);
                        data.time_offset = -1L;
                        data.timestamp = -1L;
                        this.flushBytes(dataSize);
                        flowRet = MultipartDemux.this.currentStream.push(data);
                        MultipartDemux.this.state = 1;
                        break;
                    }
                    default: {
                        flowRet = -5;
                        break;
                    }
                }
                return flowRet;
            }
        };
        this.accum = new byte[8192];
        this.accumSize = 0;
        this.accumPos = 0;
        this.streams = new Vector();
        this.addPad(this.sinkpad);
    }
    
    static {
        headerEnd = "\n".getBytes();
        headerEndLen = MultipartDemux.headerEnd.length;
        contentTypeLen = "content-type: ".length();
    }
    
    class MultipartStream extends Pad
    {
        private String mimeType;
        
        public MultipartStream(final String mime) {
            super(1, "src_" + mime);
            this.mimeType = mime;
            this.caps = new Caps(mime);
        }
        
        protected boolean eventFunc(final Event event) {
            return MultipartDemux.this.sinkpad.pushEvent(event);
        }
    }
}
