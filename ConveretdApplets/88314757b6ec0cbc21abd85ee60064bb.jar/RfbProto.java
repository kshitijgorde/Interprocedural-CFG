import java.util.zip.Deflater;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.io.IOException;
import java.applet.Applet;
import EDU.oswego.cs.dl.util.concurrent.BoundedBuffer;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

final class RfbProto
{
    final String versionMsg = "RFB 003.003\n";
    static final int ConnFailed = 0;
    static final int NoAuth = 1;
    static final int VncAuth = 2;
    static final int VncAuthOK = 0;
    static final int VncAuthFailed = 1;
    static final int VncAuthTooMany = 2;
    static final int FramebufferUpdate = 0;
    static final int SetColourMapEntries = 1;
    static final int Bell = 2;
    static final int ServerCutText = 3;
    static final int SetPixelFormat = 0;
    static final int FixColourMapEntries = 1;
    static final int SetEncodings = 2;
    static final int FramebufferUpdateRequest = 3;
    static final int KeyboardEvent = 4;
    static final int PointerEvent = 5;
    static final int ClientCutText = 6;
    static final int EncodingRaw = 0;
    static final int EncodingCopyRect = 1;
    static final int EncodingRRE = 2;
    static final int EncodingCoRRE = 4;
    static final int EncodingHextile = 5;
    static final int EncodingZlib = 6;
    static final int EncodingTight = 7;
    static final int EncodingCompressLevel0 = -256;
    static final int EncodingQualityLevel0 = -32;
    static final int EncodingXCursor = -240;
    static final int EncodingRichCursor = -239;
    static final int EncodingPointerPos = -232;
    static final int EncodingLastRect = -224;
    static final int EncodingNewFBSize = -223;
    static final int MaxNormalEncoding = 7;
    final int HextileRaw = 1;
    final int HextileBackgroundSpecified = 2;
    final int HextileForegroundSpecified = 4;
    final int HextileAnySubrects = 8;
    final int HextileSubrectsColoured = 16;
    static final int TightExplicitFilter = 4;
    static final int TightFill = 8;
    static final int TightJpeg = 9;
    static final int TightMaxSubencoding = 9;
    static final int TightFilterCopy = 0;
    static final int TightFilterPalette = 1;
    static final int TightFilterGradient = 2;
    static final int TightMinToCompress = 12;
    static final boolean verbose = false;
    final String host;
    final int port;
    final Socket sock;
    final DataInputStream is;
    private final OutputStream os;
    SessionRecorder rec;
    boolean inNormalProtocol;
    final VncViewer viewer;
    boolean brokenKeyPressed;
    boolean wereZlibUpdates;
    boolean recordFromBeginning;
    boolean zlibWarningShown;
    boolean tightWarningShown;
    int numUpdatesInSession;
    private boolean closed;
    private ThreadGroup iothreads;
    private final BoundedBuffer eventQueue;
    int serverMajor;
    int serverMinor;
    String desktopName;
    int framebufferWidth;
    int framebufferHeight;
    int bitsPerPixel;
    int depth;
    boolean bigEndian;
    boolean trueColour;
    int redMax;
    int greenMax;
    int blueMax;
    int redShift;
    int greenShift;
    int blueShift;
    int updateNRects;
    int updateRectX;
    int updateRectY;
    int updateRectW;
    int updateRectH;
    int updateRectEncoding;
    int copyRectSrcX;
    int copyRectSrcY;
    
    RfbProto(final String h, final int p, final VncViewer v) throws IOException {
        this.inNormalProtocol = false;
        this.brokenKeyPressed = false;
        this.wereZlibUpdates = false;
        this.recordFromBeginning = true;
        this.iothreads = new ThreadGroup("rfb.iothreads");
        this.eventQueue = new BoundedBuffer(50);
        this.viewer = v;
        this.host = h;
        this.port = p;
        if (this.viewer.socketFactory == null) {
            this.sock = new Socket(this.host, this.port);
        }
        else {
            try {
                final Class factoryClass = Class.forName(this.viewer.socketFactory);
                final SocketFactory factory = factoryClass.newInstance();
                if (this.viewer.inAnApplet) {
                    this.sock = factory.createSocket(this.host, this.port, this.viewer);
                }
                else {
                    this.sock = factory.createSocket(this.host, this.port, this.viewer.mainArgs);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new IOException(e.getMessage());
            }
        }
        this.is = new DataInputStream(new FastBufferedInputStream(this.sock.getInputStream(), 16384));
        this.os = this.sock.getOutputStream();
        new Thread(this.iothreads, new Runnable() {
            public void run() {
                RfbProto.this.writeQueuedEvents();
            }
        }).start();
    }
    
    private void writeQueuedEvents() {
        RfbOutputEvent event = null;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                event = (RfbOutputEvent)this.eventQueue.poll(5000L);
                if (event == null) {
                    continue;
                }
                event.process();
            }
            catch (IOException e) {
                this.viewer.fatalError("I/O error", e);
            }
            catch (InterruptedException e2) {
                this.viewer.fatalError("interrupted", e2);
            }
        }
    }
    
    private void enqueueEvent(final RfbOutputEvent event) throws IOException {
        if (this.os != null) {
            try {
                while (!this.eventQueue.offer(event, 5000L)) {}
            }
            catch (InterruptedException e) {
                e.printStackTrace();
                throw new IOException("write() attempt was interrupted");
            }
        }
    }
    
    void write(final byte[] b, final int off, final int len) throws IOException {
        this.enqueueEvent(new RfbOutputEvent() {
            public void process() throws IOException {
                RfbProto.this.os.write(b, off, len);
            }
        });
    }
    
    synchronized void close() {
        if (!this.closed) {
            try {
                this.sock.close();
                this.closed = true;
                this.iothreads.interrupt();
                new Exception("STACK TRACE for close()").printStackTrace();
                System.out.println("RFB socket closed");
                if (this.rec != null) {
                    this.rec.close();
                    this.rec = null;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    synchronized boolean closed() {
        return this.closed;
    }
    
    void readVersionMsg() throws Exception {
        final byte[] b = new byte[12];
        this.is.readFully(b);
        if (b[0] != 82 || b[1] != 70 || b[2] != 66 || b[3] != 32 || b[4] < 48 || b[4] > 57 || b[5] < 48 || b[5] > 57 || b[6] < 48 || b[6] > 57 || b[7] != 46 || b[8] < 48 || b[8] > 57 || b[9] < 48 || b[9] > 57 || b[10] < 48 || b[10] > 57 || b[11] != 10) {
            throw new Exception("Host " + this.host + " port " + this.port + " is not an RFB server");
        }
        this.serverMajor = (b[4] - 48) * 100 + (b[5] - 48) * 10 + (b[6] - 48);
        this.serverMinor = (b[8] - 48) * 100 + (b[9] - 48) * 10 + (b[10] - 48);
    }
    
    void writeVersionMsg() throws IOException {
        this.enqueueEvent(new RfbOutputEvent() {
            public void process() throws IOException {
                RfbProto.this.os.write("RFB 003.003\n".getBytes());
            }
        });
    }
    
    int readAuthScheme() throws Exception {
        final int authScheme = this.is.readInt();
        switch (authScheme) {
            case 0: {
                final int reasonLen = this.is.readInt();
                final byte[] reason = new byte[reasonLen];
                this.is.readFully(reason);
                throw new Exception(new String(reason));
            }
            case 1:
            case 2: {
                return authScheme;
            }
            default: {
                throw new Exception("Unknown authentication scheme from RFB server: " + authScheme);
            }
        }
    }
    
    void writeClientInit() throws IOException {
        this.enqueueEvent(new RfbOutputEvent() {
            public void process() throws IOException {
                if (RfbProto.this.viewer.options.shareDesktop) {
                    RfbProto.this.os.write(1);
                }
                else {
                    RfbProto.this.os.write(0);
                }
            }
        });
    }
    
    void readServerInit() throws IOException {
        this.framebufferWidth = this.is.readUnsignedShort();
        this.framebufferHeight = this.is.readUnsignedShort();
        this.bitsPerPixel = this.is.readUnsignedByte();
        this.depth = this.is.readUnsignedByte();
        this.bigEndian = (this.is.readUnsignedByte() != 0);
        this.trueColour = (this.is.readUnsignedByte() != 0);
        this.redMax = this.is.readUnsignedShort();
        this.greenMax = this.is.readUnsignedShort();
        this.blueMax = this.is.readUnsignedShort();
        this.redShift = this.is.readUnsignedByte();
        this.greenShift = this.is.readUnsignedByte();
        this.blueShift = this.is.readUnsignedByte();
        final byte[] pad = new byte[3];
        this.is.readFully(pad);
        final int nameLength = this.is.readInt();
        final byte[] name = new byte[nameLength];
        this.is.readFully(name);
        this.desktopName = new String(name);
        this.inNormalProtocol = true;
    }
    
    void startSession(final String fname) throws IOException {
        (this.rec = new SessionRecorder(fname)).writeHeader();
        this.rec.write("RFB 003.003\n".getBytes());
        this.rec.writeIntBE(1);
        this.rec.writeShortBE(this.framebufferWidth);
        this.rec.writeShortBE(this.framebufferHeight);
        final byte[] fbsServerInitMsg = { 32, 24, 0, 1, 0, -1, 0, -1, 0, -1, 16, 8, 0, 0, 0, 0 };
        this.rec.write(fbsServerInitMsg);
        this.rec.writeIntBE(this.desktopName.length());
        this.rec.write(this.desktopName.getBytes());
        this.numUpdatesInSession = 0;
        if (this.wereZlibUpdates) {
            this.recordFromBeginning = false;
        }
        this.zlibWarningShown = false;
        this.tightWarningShown = false;
    }
    
    void closeSession() throws IOException {
        if (this.rec != null) {
            this.rec.close();
            this.rec = null;
        }
    }
    
    void setFramebufferSize(final int width, final int height) {
        this.framebufferWidth = width;
        this.framebufferHeight = height;
    }
    
    int readServerMessageType() throws IOException {
        final int msgType = this.is.readUnsignedByte();
        if (this.rec != null && msgType == 2) {
            this.rec.writeByte(msgType);
            if (this.numUpdatesInSession > 0) {
                this.rec.flush();
            }
        }
        return msgType;
    }
    
    void readFramebufferUpdate() throws IOException {
        this.is.readByte();
        this.updateNRects = this.is.readUnsignedShort();
        if (this.rec != null) {
            this.rec.writeByte(0);
            this.rec.writeByte(0);
            this.rec.writeShortBE(this.updateNRects);
        }
        ++this.numUpdatesInSession;
    }
    
    void readFramebufferUpdateRectHdr() throws Exception {
        this.updateRectX = this.is.readUnsignedShort();
        this.updateRectY = this.is.readUnsignedShort();
        this.updateRectW = this.is.readUnsignedShort();
        this.updateRectH = this.is.readUnsignedShort();
        this.updateRectEncoding = this.is.readInt();
        if (this.updateRectEncoding == 6 || this.updateRectEncoding == 7) {
            this.wereZlibUpdates = true;
        }
        if (this.rec != null) {
            if (this.numUpdatesInSession > 1) {
                this.rec.flush();
            }
            this.rec.writeShortBE(this.updateRectX);
            this.rec.writeShortBE(this.updateRectY);
            this.rec.writeShortBE(this.updateRectW);
            this.rec.writeShortBE(this.updateRectH);
            if (this.updateRectEncoding == 6 && !this.recordFromBeginning) {
                if (!this.zlibWarningShown) {
                    System.out.println("Warning: Raw encoding will be used instead of Zlib in recorded session.");
                    this.zlibWarningShown = true;
                }
                this.rec.writeIntBE(0);
            }
            else {
                this.rec.writeIntBE(this.updateRectEncoding);
                if (this.updateRectEncoding == 7 && !this.recordFromBeginning && !this.tightWarningShown) {
                    System.out.println("Warning: Re-compressing Tight-encoded updates for session recording.");
                    this.tightWarningShown = true;
                }
            }
        }
        if (this.updateRectEncoding < 0 || this.updateRectEncoding > 7) {
            return;
        }
        if (this.updateRectX + this.updateRectW > this.framebufferWidth || this.updateRectY + this.updateRectH > this.framebufferHeight) {
            throw new Exception("Framebuffer update rectangle too large: " + this.updateRectW + "x" + this.updateRectH + " at (" + this.updateRectX + "," + this.updateRectY + ")");
        }
    }
    
    void readCopyRect() throws IOException {
        this.copyRectSrcX = this.is.readUnsignedShort();
        this.copyRectSrcY = this.is.readUnsignedShort();
        if (this.rec != null) {
            this.rec.writeShortBE(this.copyRectSrcX);
            this.rec.writeShortBE(this.copyRectSrcY);
        }
    }
    
    String readServerCutText() throws IOException {
        final byte[] pad = new byte[3];
        this.is.readFully(pad);
        final int len = this.is.readInt();
        final byte[] text = new byte[len];
        this.is.readFully(text);
        return new String(text);
    }
    
    int readCompactLen() throws IOException {
        final int[] portion = { this.is.readUnsignedByte(), 0, 0 };
        int byteCount = 1;
        int len = portion[0] & 0x7F;
        if ((portion[0] & 0x80) != 0x0) {
            portion[1] = this.is.readUnsignedByte();
            ++byteCount;
            len |= (portion[1] & 0x7F) << 7;
            if ((portion[1] & 0x80) != 0x0) {
                portion[2] = this.is.readUnsignedByte();
                ++byteCount;
                len |= (portion[2] & 0xFF) << 14;
            }
        }
        if (this.rec != null && this.recordFromBeginning) {
            for (int i = 0; i < byteCount; ++i) {
                this.rec.writeByte(portion[i]);
            }
        }
        return len;
    }
    
    void writeFramebufferUpdateRequest(final int x, final int y, final int w, final int h, final boolean incremental) throws IOException {
        this.enqueueEvent(new RfbOutputEvent() {
            public void process() throws IOException {
                final byte[] b = { 3, (byte)(incremental ? 1 : 0), (byte)(x >> 8 & 0xFF), (byte)(x & 0xFF), (byte)(y >> 8 & 0xFF), (byte)(y & 0xFF), (byte)(w >> 8 & 0xFF), (byte)(w & 0xFF), (byte)(h >> 8 & 0xFF), (byte)(h & 0xFF) };
                RfbProto.this.os.write(b, 0, 10);
            }
        });
    }
    
    void writeSetPixelFormat(final int bitsPerPixel, final int depth, final boolean bigEndian, final boolean trueColour, final int redMax, final int greenMax, final int blueMax, final int redShift, final int greenShift, final int blueShift) throws IOException {
        this.enqueueEvent(new RfbOutputEvent() {
            public void process() throws IOException {
                final byte[] b = { 0, 0, 0, 0, (byte)bitsPerPixel, (byte)depth, (byte)(bigEndian ? 1 : 0), (byte)(trueColour ? 1 : 0), (byte)(redMax >> 8 & 0xFF), (byte)(redMax & 0xFF), (byte)(greenMax >> 8 & 0xFF), (byte)(greenMax & 0xFF), (byte)(blueMax >> 8 & 0xFF), (byte)(blueMax & 0xFF), (byte)redShift, (byte)greenShift, (byte)blueShift, 0, 0, 0 };
                RfbProto.this.os.write(b, 0, 20);
            }
        });
    }
    
    void writeSetEncodings(final int[] encs, final int len) throws IOException {
        this.enqueueEvent(new RfbOutputEvent() {
            public void process() throws IOException {
                final byte[] b = new byte[4 + 4 * len];
                b[0] = 2;
                b[2] = (byte)(len >> 8 & 0xFF);
                b[3] = (byte)(len & 0xFF);
                for (int i = 0; i < len; ++i) {
                    b[4 + 4 * i] = (byte)(encs[i] >> 24 & 0xFF);
                    b[5 + 4 * i] = (byte)(encs[i] >> 16 & 0xFF);
                    b[6 + 4 * i] = (byte)(encs[i] >> 8 & 0xFF);
                    b[7 + 4 * i] = (byte)(encs[i] & 0xFF);
                }
                RfbProto.this.os.write(b);
            }
        });
    }
    
    void writeClientCutText(final String text) throws IOException {
        this.enqueueEvent(new RfbOutputEvent() {
            public void process() throws IOException {
                final byte[] b = new byte[8 + text.length()];
                b[0] = 6;
                b[4] = (byte)(text.length() >> 24 & 0xFF);
                b[5] = (byte)(text.length() >> 16 & 0xFF);
                b[6] = (byte)(text.length() >> 8 & 0xFF);
                b[7] = (byte)(text.length() & 0xFF);
                System.arraycopy(text.getBytes(), 0, b, 8, text.length());
                RfbProto.this.os.write(b);
            }
        });
    }
    
    void writePointerEvent(final MouseEvent evt) throws IOException {
        this.enqueueEvent(new MousePointerEvent(evt, this.os, this.viewer.options.reverseMouseButtons2And3));
    }
    
    void writeKeyEvent(final KeyEvent evt) throws IOException {
        this.enqueueEvent(new RfbKeyEvent(evt, this.os));
    }
    
    void recordCompressedData(final byte[] data, final int off, final int len) throws IOException {
        final Deflater deflater = new Deflater();
        deflater.setInput(data, off, len);
        final int bufSize = len + len / 100 + 12;
        final byte[] buf = new byte[bufSize];
        deflater.finish();
        final int compressedSize = deflater.deflate(buf);
        this.recordCompactLen(compressedSize);
        this.rec.write(buf, 0, compressedSize);
    }
    
    void recordCompressedData(final byte[] data) throws IOException {
        this.recordCompressedData(data, 0, data.length);
    }
    
    void recordCompactLen(final int len) throws IOException {
        final byte[] buf = new byte[3];
        int bytes = 0;
        buf[bytes++] = (byte)(len & 0x7F);
        if (len > 127) {
            final byte[] array = buf;
            final int n = bytes - 1;
            array[n] |= (byte)128;
            buf[bytes++] = (byte)(len >> 7 & 0x7F);
            if (len > 16383) {
                final byte[] array2 = buf;
                final int n2 = bytes - 1;
                array2[n2] |= (byte)128;
                buf[bytes++] = (byte)(len >> 14 & 0xFF);
            }
        }
        this.rec.write(buf, 0, bytes);
    }
    
    static class RfbKeyEvent implements RfbOutputEvent
    {
        protected final OutputStream os;
        private final KeyEvent evt;
        static final int CTRL_MASK = 2;
        static final int SHIFT_MASK = 1;
        static final int META_MASK = 4;
        static final int ALT_MASK = 8;
        protected static final byte[] eventBuf;
        protected static int eventBufLen;
        private static int oldModifiers;
        protected static boolean brokenKeyPressed;
        
        RfbKeyEvent(final OutputStream os) {
            this(null, os);
        }
        
        RfbKeyEvent(final KeyEvent evt, final OutputStream os) {
            this.os = os;
            this.evt = evt;
        }
        
        protected final void writeModifierKeyEvents(final int newModifiers) {
            if ((newModifiers & 0x2) != (RfbKeyEvent.oldModifiers & 0x2)) {
                this.writeKeyEvent(65507, (newModifiers & 0x2) != 0x0);
            }
            if ((newModifiers & 0x1) != (RfbKeyEvent.oldModifiers & 0x1)) {
                this.writeKeyEvent(65505, (newModifiers & 0x1) != 0x0);
            }
            if ((newModifiers & 0x4) != (RfbKeyEvent.oldModifiers & 0x4)) {
                this.writeKeyEvent(65511, (newModifiers & 0x4) != 0x0);
            }
            if ((newModifiers & 0x8) != (RfbKeyEvent.oldModifiers & 0x8)) {
                this.writeKeyEvent(65513, (newModifiers & 0x8) != 0x0);
            }
            RfbKeyEvent.oldModifiers = newModifiers;
        }
        
        private void writeKeyEvent(final int keysym, final boolean down) {
            RfbKeyEvent.eventBuf[RfbKeyEvent.eventBufLen++] = 4;
            RfbKeyEvent.eventBuf[RfbKeyEvent.eventBufLen++] = (byte)(down ? 1 : 0);
            RfbKeyEvent.eventBuf[RfbKeyEvent.eventBufLen++] = 0;
            RfbKeyEvent.eventBuf[RfbKeyEvent.eventBufLen++] = 0;
            RfbKeyEvent.eventBuf[RfbKeyEvent.eventBufLen++] = (byte)(keysym >> 24 & 0xFF);
            RfbKeyEvent.eventBuf[RfbKeyEvent.eventBufLen++] = (byte)(keysym >> 16 & 0xFF);
            RfbKeyEvent.eventBuf[RfbKeyEvent.eventBufLen++] = (byte)(keysym >> 8 & 0xFF);
            RfbKeyEvent.eventBuf[RfbKeyEvent.eventBufLen++] = (byte)(keysym & 0xFF);
        }
        
        public void process() throws IOException {
            int keyChar = this.evt.getKeyChar();
            if (keyChar == 0) {
                keyChar = 65535;
            }
            if (keyChar == 65535) {
                final int code = this.evt.getKeyCode();
                if (code == 17 || code == 16 || code == 157 || code == 18) {
                    return;
                }
            }
            final boolean down = this.evt.getID() == 401;
            int key = 0;
            if (this.evt.isActionKey()) {
                switch (this.evt.getKeyCode()) {
                    case 36: {
                        key = 65360;
                        break;
                    }
                    case 37: {
                        key = 65361;
                        break;
                    }
                    case 38: {
                        key = 65362;
                        break;
                    }
                    case 39: {
                        key = 65363;
                        break;
                    }
                    case 40: {
                        key = 65364;
                        break;
                    }
                    case 33: {
                        key = 65365;
                        break;
                    }
                    case 34: {
                        key = 65366;
                        break;
                    }
                    case 35: {
                        key = 65367;
                        break;
                    }
                    case 155: {
                        key = 65379;
                        break;
                    }
                    case 112: {
                        key = 65470;
                        break;
                    }
                    case 113: {
                        key = 65471;
                        break;
                    }
                    case 114: {
                        key = 65472;
                        break;
                    }
                    case 115: {
                        key = 65473;
                        break;
                    }
                    case 116: {
                        key = 65474;
                        break;
                    }
                    case 117: {
                        key = 65475;
                        break;
                    }
                    case 118: {
                        key = 65476;
                        break;
                    }
                    case 119: {
                        key = 65477;
                        break;
                    }
                    case 120: {
                        key = 65478;
                        break;
                    }
                    case 121: {
                        key = 65479;
                        break;
                    }
                    case 122: {
                        key = 65480;
                        break;
                    }
                    case 123: {
                        key = 65481;
                        break;
                    }
                    default: {
                        return;
                    }
                }
            }
            else {
                key = keyChar;
                if (key < 32) {
                    if (this.evt.isControlDown()) {
                        key += 96;
                    }
                    else {
                        switch (key) {
                            case 8: {
                                key = 65288;
                                break;
                            }
                            case 9: {
                                key = 65289;
                                break;
                            }
                            case 10: {
                                key = 65293;
                                break;
                            }
                            case 27: {
                                key = 65307;
                                break;
                            }
                        }
                    }
                }
                else if (key == 127) {
                    key = 65535;
                }
                else if (key > 255 && (key < 65280 || key > 65535) && (key < 8352 || key > 8367)) {
                    return;
                }
            }
            if (key == 229 || key == 197 || key == 228 || key == 196 || key == 246 || key == 214 || key == 167 || key == 189 || key == 163) {
                if (down) {
                    RfbKeyEvent.brokenKeyPressed = true;
                }
                if (!down && !RfbKeyEvent.brokenKeyPressed) {
                    RfbKeyEvent.eventBufLen = 0;
                    this.writeModifierKeyEvents(this.evt.getModifiers());
                    this.writeKeyEvent(key, true);
                    this.os.write(RfbKeyEvent.eventBuf, 0, RfbKeyEvent.eventBufLen);
                }
                if (!down) {
                    RfbKeyEvent.brokenKeyPressed = false;
                }
            }
            RfbKeyEvent.eventBufLen = 0;
            this.writeModifierKeyEvents(this.evt.getModifiers());
            this.writeKeyEvent(key, down);
            if (!down) {
                this.writeModifierKeyEvents(0);
            }
            this.os.write(RfbKeyEvent.eventBuf, 0, RfbKeyEvent.eventBufLen);
        }
        
        static {
            eventBuf = new byte[76];
            RfbKeyEvent.oldModifiers = 0;
            RfbKeyEvent.brokenKeyPressed = false;
        }
    }
    
    static final class MousePointerEvent extends RfbKeyEvent
    {
        private final MouseEvent evt;
        private final boolean isReverseMouseButtons2And3;
        private static int pointerMask;
        
        MousePointerEvent(final MouseEvent evt, final OutputStream os, final boolean isReverseMouseButtons2And3) {
            super(os);
            this.evt = evt;
            this.isReverseMouseButtons2And3 = isReverseMouseButtons2And3;
        }
        
        public void process() throws IOException {
            int modifiers = this.evt.getModifiers();
            int mask2 = 2;
            int mask3 = 4;
            if (this.isReverseMouseButtons2And3) {
                mask2 = 4;
                mask3 = 2;
            }
            if (this.evt.getID() == 501) {
                if ((modifiers & 0x8) != 0x0) {
                    MousePointerEvent.pointerMask = mask2;
                    modifiers &= 0xFFFFFFF7;
                }
                else if ((modifiers & 0x4) != 0x0) {
                    MousePointerEvent.pointerMask = mask3;
                    modifiers &= 0xFFFFFFFB;
                }
                else {
                    MousePointerEvent.pointerMask = 1;
                }
            }
            else if (this.evt.getID() == 502) {
                MousePointerEvent.pointerMask = 0;
                if ((modifiers & 0x8) != 0x0) {
                    modifiers &= 0xFFFFFFF7;
                }
                else if ((modifiers & 0x4) != 0x0) {
                    modifiers &= 0xFFFFFFFB;
                }
            }
            RfbKeyEvent.eventBufLen = 0;
            this.writeModifierKeyEvents(modifiers);
            int x = this.evt.getX();
            int y = this.evt.getY();
            if (x < 0) {
                x = 0;
            }
            if (y < 0) {
                y = 0;
            }
            RfbKeyEvent.eventBuf[RfbKeyEvent.eventBufLen++] = 5;
            RfbKeyEvent.eventBuf[RfbKeyEvent.eventBufLen++] = (byte)MousePointerEvent.pointerMask;
            RfbKeyEvent.eventBuf[RfbKeyEvent.eventBufLen++] = (byte)(x >> 8 & 0xFF);
            RfbKeyEvent.eventBuf[RfbKeyEvent.eventBufLen++] = (byte)(x & 0xFF);
            RfbKeyEvent.eventBuf[RfbKeyEvent.eventBufLen++] = (byte)(y >> 8 & 0xFF);
            RfbKeyEvent.eventBuf[RfbKeyEvent.eventBufLen++] = (byte)(y & 0xFF);
            if (MousePointerEvent.pointerMask == 0) {
                this.writeModifierKeyEvents(0);
            }
            super.os.write(RfbKeyEvent.eventBuf, 0, RfbKeyEvent.eventBufLen);
        }
        
        static {
            MousePointerEvent.pointerMask = 0;
        }
    }
    
    interface RfbOutputEvent
    {
        void process() throws IOException;
    }
}
