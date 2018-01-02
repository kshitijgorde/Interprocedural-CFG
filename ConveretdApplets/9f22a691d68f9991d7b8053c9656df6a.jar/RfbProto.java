import java.util.zip.Deflater;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.applet.Applet;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

class RfbProto
{
    static final String versionMsg_3_3 = "RFB 003.003\n";
    static final String versionMsg_3_7 = "RFB 003.007\n";
    static final String versionMsg_3_8 = "RFB 003.008\n";
    static final String StandardVendor = "STDV";
    static final String TridiaVncVendor = "TRDV";
    static final String TightVncVendor = "TGHT";
    static final int SecTypeInvalid = 0;
    static final int SecTypeNone = 1;
    static final int SecTypeVncAuth = 2;
    static final int SecTypeTight = 16;
    static final int NoTunneling = 0;
    static final String SigNoTunneling = "NOTUNNEL";
    static final int AuthNone = 1;
    static final int AuthVNC = 2;
    static final int AuthUnixLogin = 129;
    static final String SigAuthNone = "NOAUTH__";
    static final String SigAuthVNC = "VNCAUTH_";
    static final String SigAuthUnixLogin = "ULGNAUTH";
    static final int VncAuthOK = 0;
    static final int VncAuthFailed = 1;
    static final int VncAuthTooMany = 2;
    static final int FramebufferUpdate = 0;
    static final int SetColourMapEntries = 1;
    static final int Bell = 2;
    static final int ServerCutText = 3;
    static final int EndOfContinuousUpdates = 150;
    static final String SigEndOfContinuousUpdates = "CUS_EOCU";
    static final int SetPixelFormat = 0;
    static final int FixColourMapEntries = 1;
    static final int SetEncodings = 2;
    static final int FramebufferUpdateRequest = 3;
    static final int KeyboardEvent = 4;
    static final int PointerEvent = 5;
    static final int ClientCutText = 6;
    static final int EnableContinuousUpdates = 150;
    static final String SigEnableContinuousUpdates = "CUC_ENCU";
    static final int EncodingRaw = 0;
    static final int EncodingCopyRect = 1;
    static final int EncodingRRE = 2;
    static final int EncodingCoRRE = 4;
    static final int EncodingHextile = 5;
    static final int EncodingZlib = 6;
    static final int EncodingTight = 7;
    static final int EncodingZRLE = 16;
    static final int EncodingCompressLevel0 = -256;
    static final int EncodingQualityLevel0 = -32;
    static final int EncodingXCursor = -240;
    static final int EncodingRichCursor = -239;
    static final int EncodingPointerPos = -232;
    static final int EncodingLastRect = -224;
    static final int EncodingNewFBSize = -223;
    static final String SigEncodingRaw = "RAW_____";
    static final String SigEncodingCopyRect = "COPYRECT";
    static final String SigEncodingRRE = "RRE_____";
    static final String SigEncodingCoRRE = "CORRE___";
    static final String SigEncodingHextile = "HEXTILE_";
    static final String SigEncodingZlib = "ZLIB____";
    static final String SigEncodingTight = "TIGHT___";
    static final String SigEncodingZRLE = "ZRLE____";
    static final String SigEncodingCompressLevel0 = "COMPRLVL";
    static final String SigEncodingQualityLevel0 = "JPEGQLVL";
    static final String SigEncodingXCursor = "X11CURSR";
    static final String SigEncodingRichCursor = "RCHCURSR";
    static final String SigEncodingPointerPos = "POINTPOS";
    static final String SigEncodingLastRect = "LASTRECT";
    static final String SigEncodingNewFBSize = "NEWFBSIZ";
    static final int MaxNormalEncoding = 255;
    static final int HextileRaw = 1;
    static final int HextileBackgroundSpecified = 2;
    static final int HextileForegroundSpecified = 4;
    static final int HextileAnySubrects = 8;
    static final int HextileSubrectsColoured = 16;
    static final int TightMinToCompress = 12;
    static final int TightExplicitFilter = 4;
    static final int TightFill = 8;
    static final int TightJpeg = 9;
    static final int TightMaxSubencoding = 9;
    static final int TightFilterCopy = 0;
    static final int TightFilterPalette = 1;
    static final int TightFilterGradient = 2;
    String host;
    int port;
    Socket sock;
    OutputStream os;
    SessionRecorder rec;
    boolean inNormalProtocol;
    VncViewer viewer;
    private DataInputStream is;
    private long numBytesRead;
    boolean brokenKeyPressed;
    boolean wereZlibUpdates;
    boolean recordFromBeginning;
    boolean zlibWarningShown;
    boolean tightWarningShown;
    int numUpdatesInSession;
    boolean timing;
    long timeWaitedIn100us;
    long timedKbits;
    int serverMajor;
    int serverMinor;
    int clientMajor;
    int clientMinor;
    boolean protocolTightVNC;
    CapsContainer tunnelCaps;
    CapsContainer authCaps;
    CapsContainer serverMsgCaps;
    CapsContainer clientMsgCaps;
    CapsContainer encodingCaps;
    private boolean closed;
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
    byte[] eventBuf;
    int eventBufLen;
    static final int CTRL_MASK = 2;
    static final int SHIFT_MASK = 1;
    static final int META_MASK = 4;
    static final int ALT_MASK = 8;
    int pointerMask;
    int oldModifiers;
    
    public long getNumBytesRead() {
        return this.numBytesRead;
    }
    
    RfbProto(final String host, final int port, final VncViewer viewer) throws IOException {
        this.inNormalProtocol = false;
        this.numBytesRead = 0L;
        this.brokenKeyPressed = false;
        this.wereZlibUpdates = false;
        this.recordFromBeginning = true;
        this.eventBuf = new byte[72];
        this.pointerMask = 0;
        this.oldModifiers = 0;
        this.viewer = viewer;
        this.host = host;
        this.port = port;
        if (this.viewer.socketFactory == null) {
            this.sock = new Socket(this.host, this.port);
        }
        else {
            try {
                final SocketFactory socketFactory = (SocketFactory)Class.forName(this.viewer.socketFactory).newInstance();
                if (this.viewer.inAnApplet) {
                    this.sock = socketFactory.createSocket(this.host, this.port, this.viewer);
                }
                else {
                    this.sock = socketFactory.createSocket(this.host, this.port, this.viewer.mainArgs);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                throw new IOException(ex.getMessage());
            }
        }
        this.is = new DataInputStream(new BufferedInputStream(this.sock.getInputStream(), 16384));
        this.os = this.sock.getOutputStream();
        this.timing = false;
        this.timeWaitedIn100us = 5L;
        this.timedKbits = 0L;
    }
    
    synchronized void close() {
        try {
            this.sock.close();
            this.closed = true;
            System.out.println("RFB socket closed");
            if (this.rec != null) {
                this.rec.close();
                this.rec = null;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    synchronized boolean closed() {
        return this.closed;
    }
    
    void readVersionMsg() throws Exception {
        final byte[] array = new byte[12];
        this.readFully(array);
        if (array[0] != 82 || array[1] != 70 || array[2] != 66 || array[3] != 32 || array[4] < 48 || array[4] > 57 || array[5] < 48 || array[5] > 57 || array[6] < 48 || array[6] > 57 || array[7] != 46 || array[8] < 48 || array[8] > 57 || array[9] < 48 || array[9] > 57 || array[10] < 48 || array[10] > 57 || array[11] != 10) {
            throw new Exception("Host " + this.host + " port " + this.port + " is not an RFB server");
        }
        this.serverMajor = (array[4] - 48) * 100 + (array[5] - 48) * 10 + (array[6] - 48);
        this.serverMinor = (array[8] - 48) * 100 + (array[9] - 48) * 10 + (array[10] - 48);
        if (this.serverMajor < 3) {
            throw new Exception("RFB server does not support protocol version 3");
        }
    }
    
    void writeVersionMsg() throws IOException {
        this.clientMajor = 3;
        if (this.serverMajor > 3 || this.serverMinor >= 8) {
            this.clientMinor = 8;
            this.os.write("RFB 003.008\n".getBytes());
        }
        else if (this.serverMinor >= 7) {
            this.clientMinor = 7;
            this.os.write("RFB 003.007\n".getBytes());
        }
        else {
            this.clientMinor = 3;
            this.os.write("RFB 003.003\n".getBytes());
        }
        this.protocolTightVNC = false;
        this.initCapabilities();
    }
    
    int negotiateSecurity() throws Exception {
        return (this.clientMinor >= 7) ? this.selectSecurityType() : this.readSecurityType();
    }
    
    int readSecurityType() throws Exception {
        final int u32 = this.readU32();
        switch (u32) {
            case 0: {
                this.readConnFailedReason();
                return 0;
            }
            case 1:
            case 2: {
                return u32;
            }
            default: {
                throw new Exception("Unknown security type from RFB server: " + u32);
            }
        }
    }
    
    int selectSecurityType() throws Exception {
        int n = 0;
        final int u8 = this.readU8();
        if (u8 == 0) {
            this.readConnFailedReason();
            return 0;
        }
        final byte[] array = new byte[u8];
        this.readFully(array);
        for (int i = 0; i < u8; ++i) {
            if (array[i] == 16) {
                this.protocolTightVNC = true;
                this.os.write(16);
                return 16;
            }
        }
        for (int j = 0; j < u8; ++j) {
            if (array[j] == 1 || array[j] == 2) {
                n = array[j];
                break;
            }
        }
        if (n == 0) {
            throw new Exception("Server did not offer supported security type");
        }
        this.os.write(n);
        return n;
    }
    
    void authenticateNone() throws Exception {
        if (this.clientMinor >= 8) {
            this.readSecurityResult("No authentication");
        }
    }
    
    void authenticateVNC(String s) throws Exception {
        final byte[] array = new byte[16];
        this.readFully(array);
        if (s.length() > 8) {
            s = s.substring(0, 8);
        }
        final int index = s.indexOf(0);
        if (index != -1) {
            s = s.substring(0, index);
        }
        final byte[] array2 = { 0, 0, 0, 0, 0, 0, 0, 0 };
        System.arraycopy(s.getBytes(), 0, array2, 0, s.length());
        final DesCipher desCipher = new DesCipher(array2);
        desCipher.encrypt(array, 0, array, 0);
        desCipher.encrypt(array, 8, array, 8);
        this.os.write(array);
        this.readSecurityResult("VNC authentication");
    }
    
    void readSecurityResult(final String s) throws Exception {
        final int u32 = this.readU32();
        switch (u32) {
            case 0: {
                System.out.println(s + ": success");
            }
            case 1: {
                if (this.clientMinor >= 8) {
                    this.readConnFailedReason();
                }
                throw new Exception(s + ": failed");
            }
            case 2: {
                throw new Exception(s + ": failed, too many tries");
            }
            default: {
                throw new Exception(s + ": unknown result " + u32);
            }
        }
    }
    
    void readConnFailedReason() throws Exception {
        final byte[] array = new byte[this.readU32()];
        this.readFully(array);
        throw new Exception(new String(array));
    }
    
    void initCapabilities() {
        this.tunnelCaps = new CapsContainer();
        this.authCaps = new CapsContainer();
        this.serverMsgCaps = new CapsContainer();
        this.clientMsgCaps = new CapsContainer();
        this.encodingCaps = new CapsContainer();
        this.authCaps.add(1, "STDV", "NOAUTH__", "No authentication");
        this.authCaps.add(2, "STDV", "VNCAUTH_", "Standard VNC password authentication");
        this.encodingCaps.add(1, "STDV", "COPYRECT", "Standard CopyRect encoding");
        this.encodingCaps.add(2, "STDV", "RRE_____", "Standard RRE encoding");
        this.encodingCaps.add(4, "STDV", "CORRE___", "Standard CoRRE encoding");
        this.encodingCaps.add(5, "STDV", "HEXTILE_", "Standard Hextile encoding");
        this.encodingCaps.add(16, "STDV", "ZRLE____", "Standard ZRLE encoding");
        this.encodingCaps.add(6, "TRDV", "ZLIB____", "Zlib encoding");
        this.encodingCaps.add(7, "TGHT", "TIGHT___", "Tight encoding");
        this.encodingCaps.add(-256, "TGHT", "COMPRLVL", "Compression level");
        this.encodingCaps.add(-32, "TGHT", "JPEGQLVL", "JPEG quality level");
        this.encodingCaps.add(-240, "TGHT", "X11CURSR", "X-style cursor shape update");
        this.encodingCaps.add(-239, "TGHT", "RCHCURSR", "Rich-color cursor shape update");
        this.encodingCaps.add(-232, "TGHT", "POINTPOS", "Pointer position update");
        this.encodingCaps.add(-224, "TGHT", "LASTRECT", "LastRect protocol extension");
        this.encodingCaps.add(-223, "TGHT", "NEWFBSIZ", "Framebuffer size change");
    }
    
    void setupTunneling() throws IOException {
        final int u32 = this.readU32();
        if (u32 != 0) {
            this.readCapabilityList(this.tunnelCaps, u32);
            this.writeInt(0);
        }
    }
    
    int negotiateAuthenticationTight() throws Exception {
        final int u32 = this.readU32();
        if (u32 == 0) {
            return 1;
        }
        this.readCapabilityList(this.authCaps, u32);
        for (int i = 0; i < this.authCaps.numEnabled(); ++i) {
            final int byOrder = this.authCaps.getByOrder(i);
            if (byOrder == 1 || byOrder == 2) {
                this.writeInt(byOrder);
                return byOrder;
            }
        }
        throw new Exception("No suitable authentication scheme found");
    }
    
    void readCapabilityList(final CapsContainer capsContainer, final int n) throws IOException {
        final byte[] array = new byte[4];
        final byte[] array2 = new byte[8];
        for (int i = 0; i < n; ++i) {
            final int u32 = this.readU32();
            this.readFully(array);
            this.readFully(array2);
            capsContainer.enable(new CapabilityInfo(u32, array, array2));
        }
    }
    
    void writeInt(final int n) throws IOException {
        this.os.write(new byte[] { (byte)(n >> 24 & 0xFF), (byte)(n >> 16 & 0xFF), (byte)(n >> 8 & 0xFF), (byte)(n & 0xFF) });
    }
    
    void writeClientInit() throws IOException {
        if (this.viewer.options.shareDesktop) {
            this.os.write(1);
        }
        else {
            this.os.write(0);
        }
        this.viewer.options.disableShareDesktop();
    }
    
    void readServerInit() throws IOException {
        this.framebufferWidth = this.readU16();
        this.framebufferHeight = this.readU16();
        this.bitsPerPixel = this.readU8();
        this.depth = this.readU8();
        this.bigEndian = (this.readU8() != 0);
        this.trueColour = (this.readU8() != 0);
        this.redMax = this.readU16();
        this.greenMax = this.readU16();
        this.blueMax = this.readU16();
        this.redShift = this.readU8();
        this.greenShift = this.readU8();
        this.blueShift = this.readU8();
        this.readFully(new byte[3]);
        final byte[] array = new byte[this.readU32()];
        this.readFully(array);
        this.desktopName = new String(array);
        if (this.protocolTightVNC) {
            final int u16 = this.readU16();
            final int u17 = this.readU16();
            final int u18 = this.readU16();
            this.readU16();
            this.readCapabilityList(this.serverMsgCaps, u16);
            this.readCapabilityList(this.clientMsgCaps, u17);
            this.readCapabilityList(this.encodingCaps, u18);
        }
        this.inNormalProtocol = true;
    }
    
    void startSession(final String s) throws IOException {
        (this.rec = new SessionRecorder(s)).writeHeader();
        this.rec.write("RFB 003.003\n".getBytes());
        this.rec.writeIntBE(1);
        this.rec.writeShortBE(this.framebufferWidth);
        this.rec.writeShortBE(this.framebufferHeight);
        this.rec.write(new byte[] { 32, 24, 0, 1, 0, -1, 0, -1, 0, -1, 16, 8, 0, 0, 0, 0 });
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
    
    void setFramebufferSize(final int framebufferWidth, final int framebufferHeight) {
        this.framebufferWidth = framebufferWidth;
        this.framebufferHeight = framebufferHeight;
    }
    
    int readServerMessageType() throws IOException {
        final int u8 = this.readU8();
        if (this.rec != null && u8 == 2) {
            this.rec.writeByte(u8);
            if (this.numUpdatesInSession > 0) {
                this.rec.flush();
            }
        }
        return u8;
    }
    
    void readFramebufferUpdate() throws IOException {
        this.skipBytes(1);
        this.updateNRects = this.readU16();
        if (this.rec != null) {
            this.rec.writeByte(0);
            this.rec.writeByte(0);
            this.rec.writeShortBE(this.updateNRects);
        }
        ++this.numUpdatesInSession;
    }
    
    void readFramebufferUpdateRectHdr() throws Exception {
        this.updateRectX = this.readU16();
        this.updateRectY = this.readU16();
        this.updateRectW = this.readU16();
        this.updateRectH = this.readU16();
        this.updateRectEncoding = this.readU32();
        if (this.updateRectEncoding == 6 || this.updateRectEncoding == 16 || this.updateRectEncoding == 7) {
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
        if (this.updateRectEncoding < 0 || this.updateRectEncoding > 255) {
            return;
        }
        if (this.updateRectX + this.updateRectW > this.framebufferWidth || this.updateRectY + this.updateRectH > this.framebufferHeight) {
            throw new Exception("Framebuffer update rectangle too large: " + this.updateRectW + "x" + this.updateRectH + " at (" + this.updateRectX + "," + this.updateRectY + ")");
        }
    }
    
    void readCopyRect() throws IOException {
        this.copyRectSrcX = this.readU16();
        this.copyRectSrcY = this.readU16();
        if (this.rec != null) {
            this.rec.writeShortBE(this.copyRectSrcX);
            this.rec.writeShortBE(this.copyRectSrcY);
        }
    }
    
    String readServerCutText() throws IOException {
        this.skipBytes(3);
        final byte[] array = new byte[this.readU32()];
        this.readFully(array);
        return new String(array);
    }
    
    int readCompactLen() throws IOException {
        final int[] array = { this.readU8(), 0, 0 };
        int n = 1;
        int n2 = array[0] & 0x7F;
        if ((array[0] & 0x80) != 0x0) {
            array[1] = this.readU8();
            ++n;
            n2 |= (array[1] & 0x7F) << 7;
            if ((array[1] & 0x80) != 0x0) {
                array[2] = this.readU8();
                ++n;
                n2 |= (array[2] & 0xFF) << 14;
            }
        }
        if (this.rec != null && this.recordFromBeginning) {
            for (int i = 0; i < n; ++i) {
                this.rec.writeByte(array[i]);
            }
        }
        return n2;
    }
    
    void writeFramebufferUpdateRequest(final int n, final int n2, final int n3, final int n4, final boolean b) throws IOException {
        this.os.write(new byte[] { 3, (byte)(b ? 1 : 0), (byte)(n >> 8 & 0xFF), (byte)(n & 0xFF), (byte)(n2 >> 8 & 0xFF), (byte)(n2 & 0xFF), (byte)(n3 >> 8 & 0xFF), (byte)(n3 & 0xFF), (byte)(n4 >> 8 & 0xFF), (byte)(n4 & 0xFF) });
    }
    
    void writeSetPixelFormat(final int n, final int n2, final boolean b, final boolean b2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) throws IOException {
        this.os.write(new byte[] { 0, 0, 0, 0, (byte)n, (byte)n2, (byte)(b ? 1 : 0), (byte)(b2 ? 1 : 0), (byte)(n3 >> 8 & 0xFF), (byte)(n3 & 0xFF), (byte)(n4 >> 8 & 0xFF), (byte)(n4 & 0xFF), (byte)(n5 >> 8 & 0xFF), (byte)(n5 & 0xFF), (byte)n6, (byte)n7, (byte)n8, 0, 0, 0 });
    }
    
    void writeFixColourMapEntries(final int n, final int n2, final int[] array, final int[] array2, final int[] array3) throws IOException {
        final byte[] array4 = new byte[6 + n2 * 6];
        array4[0] = 1;
        array4[2] = (byte)(n >> 8 & 0xFF);
        array4[3] = (byte)(n & 0xFF);
        array4[4] = (byte)(n2 >> 8 & 0xFF);
        array4[5] = (byte)(n2 & 0xFF);
        for (int i = 0; i < n2; ++i) {
            array4[6 + i * 6] = (byte)(array[i] >> 8 & 0xFF);
            array4[6 + i * 6 + 1] = (byte)(array[i] & 0xFF);
            array4[6 + i * 6 + 2] = (byte)(array2[i] >> 8 & 0xFF);
            array4[6 + i * 6 + 3] = (byte)(array2[i] & 0xFF);
            array4[6 + i * 6 + 4] = (byte)(array3[i] >> 8 & 0xFF);
            array4[6 + i * 6 + 5] = (byte)(array3[i] & 0xFF);
        }
        this.os.write(array4);
    }
    
    void writeSetEncodings(final int[] array, final int n) throws IOException {
        final byte[] array2 = new byte[4 + 4 * n];
        array2[0] = 2;
        array2[2] = (byte)(n >> 8 & 0xFF);
        array2[3] = (byte)(n & 0xFF);
        for (int i = 0; i < n; ++i) {
            array2[4 + 4 * i] = (byte)(array[i] >> 24 & 0xFF);
            array2[5 + 4 * i] = (byte)(array[i] >> 16 & 0xFF);
            array2[6 + 4 * i] = (byte)(array[i] >> 8 & 0xFF);
            array2[7 + 4 * i] = (byte)(array[i] & 0xFF);
        }
        this.os.write(array2);
    }
    
    void writeClientCutText(final String s) throws IOException {
        final byte[] array = new byte[8 + s.length()];
        array[0] = 6;
        array[4] = (byte)(s.length() >> 24 & 0xFF);
        array[5] = (byte)(s.length() >> 16 & 0xFF);
        array[6] = (byte)(s.length() >> 8 & 0xFF);
        array[7] = (byte)(s.length() & 0xFF);
        System.arraycopy(s.getBytes(), 0, array, 8, s.length());
        this.os.write(array);
    }
    
    void writePointerEvent(final MouseEvent mouseEvent) throws IOException {
        int modifiers = mouseEvent.getModifiers();
        int pointerMask = 2;
        int pointerMask2 = 4;
        if (this.viewer.options.reverseMouseButtons2And3) {
            pointerMask = 4;
            pointerMask2 = 2;
        }
        if (mouseEvent.getID() == 501) {
            if ((modifiers & 0x8) != 0x0) {
                this.pointerMask = pointerMask;
                modifiers &= 0xFFFFFFF7;
            }
            else if ((modifiers & 0x4) != 0x0) {
                this.pointerMask = pointerMask2;
                modifiers &= 0xFFFFFFFB;
            }
            else {
                this.pointerMask = 1;
            }
        }
        else if (mouseEvent.getID() == 502) {
            this.pointerMask = 0;
            if ((modifiers & 0x8) != 0x0) {
                modifiers &= 0xFFFFFFF7;
            }
            else if ((modifiers & 0x4) != 0x0) {
                modifiers &= 0xFFFFFFFB;
            }
        }
        this.eventBufLen = 0;
        this.writeModifierKeyEvents(modifiers);
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        this.eventBuf[this.eventBufLen++] = 5;
        this.eventBuf[this.eventBufLen++] = (byte)this.pointerMask;
        this.eventBuf[this.eventBufLen++] = (byte)(x >> 8 & 0xFF);
        this.eventBuf[this.eventBufLen++] = (byte)(x & 0xFF);
        this.eventBuf[this.eventBufLen++] = (byte)(y >> 8 & 0xFF);
        this.eventBuf[this.eventBufLen++] = (byte)(y & 0xFF);
        if (this.pointerMask == 0) {
            this.writeModifierKeyEvents(0);
        }
        this.os.write(this.eventBuf, 0, this.eventBufLen);
    }
    
    void writeKeyEvent(final KeyEvent keyEvent) throws IOException {
        int keyChar = keyEvent.getKeyChar();
        if (keyChar == 0) {
            keyChar = 65535;
        }
        if (keyChar == 65535) {
            final int keyCode = keyEvent.getKeyCode();
            if (keyCode == 17 || keyCode == 16 || keyCode == 157 || keyCode == 18) {
                return;
            }
        }
        final boolean b = keyEvent.getID() == 401;
        int n = 0;
        if (keyEvent.isActionKey()) {
            switch (keyEvent.getKeyCode()) {
                case 36: {
                    n = 65360;
                    break;
                }
                case 37: {
                    n = 65361;
                    break;
                }
                case 38: {
                    n = 65362;
                    break;
                }
                case 39: {
                    n = 65363;
                    break;
                }
                case 40: {
                    n = 65364;
                    break;
                }
                case 33: {
                    n = 65365;
                    break;
                }
                case 34: {
                    n = 65366;
                    break;
                }
                case 35: {
                    n = 65367;
                    break;
                }
                case 155: {
                    n = 65379;
                    break;
                }
                case 112: {
                    n = 65470;
                    break;
                }
                case 113: {
                    n = 65471;
                    break;
                }
                case 114: {
                    n = 65472;
                    break;
                }
                case 115: {
                    n = 65473;
                    break;
                }
                case 116: {
                    n = 65474;
                    break;
                }
                case 117: {
                    n = 65475;
                    break;
                }
                case 118: {
                    n = 65476;
                    break;
                }
                case 119: {
                    n = 65477;
                    break;
                }
                case 120: {
                    n = 65478;
                    break;
                }
                case 121: {
                    n = 65479;
                    break;
                }
                case 122: {
                    n = 65480;
                    break;
                }
                case 123: {
                    n = 65481;
                    break;
                }
                default: {
                    return;
                }
            }
        }
        else {
            n = keyChar;
            if (n < 32) {
                if (keyEvent.isControlDown()) {
                    n += 96;
                }
                else {
                    switch (n) {
                        case 8: {
                            n = 65288;
                            break;
                        }
                        case 9: {
                            n = 65289;
                            break;
                        }
                        case 10: {
                            n = 65293;
                            break;
                        }
                        case 27: {
                            n = 65307;
                            break;
                        }
                    }
                }
            }
            else if (n == 127) {
                n = 65535;
            }
            else if (n > 255 && (n < 65280 || n > 65535) && (n < 8352 || n > 8367)) {
                return;
            }
        }
        if (n == 229 || n == 197 || n == 228 || n == 196 || n == 246 || n == 214 || n == 167 || n == 189 || n == 163) {
            if (b) {
                this.brokenKeyPressed = true;
            }
            if (!b && !this.brokenKeyPressed) {
                this.eventBufLen = 0;
                this.writeModifierKeyEvents(keyEvent.getModifiers());
                this.writeKeyEvent(n, true);
                this.os.write(this.eventBuf, 0, this.eventBufLen);
            }
            if (!b) {
                this.brokenKeyPressed = false;
            }
        }
        this.eventBufLen = 0;
        this.writeModifierKeyEvents(keyEvent.getModifiers());
        this.writeKeyEvent(n, b);
        if (!b) {
            this.writeModifierKeyEvents(0);
        }
        this.os.write(this.eventBuf, 0, this.eventBufLen);
    }
    
    void writeKeyEvent(final int n, final boolean b) {
        this.eventBuf[this.eventBufLen++] = 4;
        this.eventBuf[this.eventBufLen++] = (byte)(b ? 1 : 0);
        this.eventBuf[this.eventBufLen++] = 0;
        this.eventBuf[this.eventBufLen++] = 0;
        this.eventBuf[this.eventBufLen++] = (byte)(n >> 24 & 0xFF);
        this.eventBuf[this.eventBufLen++] = (byte)(n >> 16 & 0xFF);
        this.eventBuf[this.eventBufLen++] = (byte)(n >> 8 & 0xFF);
        this.eventBuf[this.eventBufLen++] = (byte)(n & 0xFF);
    }
    
    void writeModifierKeyEvents(final int oldModifiers) {
        if ((oldModifiers & 0x2) != (this.oldModifiers & 0x2)) {
            this.writeKeyEvent(65507, (oldModifiers & 0x2) != 0x0);
        }
        if ((oldModifiers & 0x1) != (this.oldModifiers & 0x1)) {
            this.writeKeyEvent(65505, (oldModifiers & 0x1) != 0x0);
        }
        if ((oldModifiers & 0x4) != (this.oldModifiers & 0x4)) {
            this.writeKeyEvent(65511, (oldModifiers & 0x4) != 0x0);
        }
        if ((oldModifiers & 0x8) != (this.oldModifiers & 0x8)) {
            this.writeKeyEvent(65513, (oldModifiers & 0x8) != 0x0);
        }
        this.oldModifiers = oldModifiers;
    }
    
    void recordCompressedData(final byte[] array, final int n, final int n2) throws IOException {
        final Deflater deflater = new Deflater();
        deflater.setInput(array, n, n2);
        final byte[] array2 = new byte[n2 + n2 / 100 + 12];
        deflater.finish();
        final int deflate = deflater.deflate(array2);
        this.recordCompactLen(deflate);
        this.rec.write(array2, 0, deflate);
    }
    
    void recordCompressedData(final byte[] array) throws IOException {
        this.recordCompressedData(array, 0, array.length);
    }
    
    void recordCompactLen(final int n) throws IOException {
        final byte[] array = new byte[3];
        int n2 = 0;
        array[n2++] = (byte)(n & 0x7F);
        if (n > 127) {
            final byte[] array2 = array;
            final int n3 = n2 - 1;
            array2[n3] |= (byte)128;
            array[n2++] = (byte)(n >> 7 & 0x7F);
            if (n > 16383) {
                final byte[] array3 = array;
                final int n4 = n2 - 1;
                array3[n4] |= (byte)128;
                array[n2++] = (byte)(n >> 14 & 0xFF);
            }
        }
        this.rec.write(array, 0, n2);
    }
    
    public void startTiming() {
        this.timing = true;
        if (this.timeWaitedIn100us > 10000L) {
            this.timedKbits = this.timedKbits * 10000L / this.timeWaitedIn100us;
            this.timeWaitedIn100us = 10000L;
        }
    }
    
    public void stopTiming() {
        this.timing = false;
        if (this.timeWaitedIn100us < this.timedKbits / 2L) {
            this.timeWaitedIn100us = this.timedKbits / 2L;
        }
    }
    
    public long kbitsPerSecond() {
        return this.timedKbits * 10000L / this.timeWaitedIn100us;
    }
    
    public long timeWaited() {
        return this.timeWaitedIn100us;
    }
    
    public void readFully(final byte[] array) throws IOException {
        this.readFully(array, 0, array.length);
    }
    
    public void readFully(final byte[] array, final int n, final int n2) throws IOException {
        long currentTimeMillis = 0L;
        if (this.timing) {
            currentTimeMillis = System.currentTimeMillis();
        }
        this.is.readFully(array, n, n2);
        if (this.timing) {
            long n3 = (System.currentTimeMillis() - currentTimeMillis) * 10L;
            final int n4 = n2 * 8 / 1000;
            if (n3 > n4 * 1000) {
                n3 = n4 * 1000;
            }
            if (n3 < n4 / 4) {
                n3 = n4 / 4;
            }
            this.timeWaitedIn100us += n3;
            this.timedKbits += n4;
        }
        this.numBytesRead += n2;
    }
    
    final int available() throws IOException {
        return this.is.available();
    }
    
    final int skipBytes(final int n) throws IOException {
        final int skipBytes = this.is.skipBytes(n);
        this.numBytesRead += skipBytes;
        return skipBytes;
    }
    
    final int readU8() throws IOException {
        final int unsignedByte = this.is.readUnsignedByte();
        ++this.numBytesRead;
        return unsignedByte;
    }
    
    final int readU16() throws IOException {
        final int unsignedShort = this.is.readUnsignedShort();
        this.numBytesRead += 2L;
        return unsignedShort;
    }
    
    final int readU32() throws IOException {
        final int int1 = this.is.readInt();
        this.numBytesRead += 4L;
        return int1;
    }
}
