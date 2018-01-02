import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.awt.Insets;
import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.io.IOException;
import java.awt.image.DirectColorModel;
import java.awt.Rectangle;
import java.util.zip.Inflater;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.image.ColorModel;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class VncCanvas extends Canvas implements KeyListener, MouseListener, MouseMotionListener
{
    VncViewer viewer;
    RfbProto rfb;
    ColorModel cm8;
    ColorModel cm24;
    Color[] colors;
    int bytesPixel;
    int maxWidth;
    int maxHeight;
    int scalingFactor;
    int scaledWidth;
    int scaledHeight;
    Image memImage;
    Graphics memGraphics;
    Image rawPixelsImage;
    MemoryImageSource pixelsSource;
    byte[] pixels8;
    int[] pixels24;
    long statStartTime;
    int statNumUpdates;
    int statNumTotalRects;
    int statNumPixelRects;
    int statNumRectsTight;
    int statNumRectsTightJPEG;
    int statNumRectsZRLE;
    int statNumRectsHextile;
    int statNumRectsRaw;
    int statNumRectsCopy;
    int statNumBytesEncoded;
    int statNumBytesDecoded;
    byte[] zrleBuf;
    int zrleBufLen;
    byte[] zrleTilePixels8;
    int[] zrleTilePixels24;
    ZlibInStream zrleInStream;
    boolean zrleRecWarningShown;
    byte[] zlibBuf;
    int zlibBufLen;
    Inflater zlibInflater;
    static final int tightZlibBufferSize = 512;
    Inflater[] tightInflaters;
    Rectangle jpegRect;
    boolean inputEnabled;
    private Color hextile_bg;
    private Color hextile_fg;
    boolean showSoftCursor;
    MemoryImageSource softCursorSource;
    Image softCursor;
    int cursorX;
    int cursorY;
    int cursorWidth;
    int cursorHeight;
    int origCursorWidth;
    int origCursorHeight;
    int hotX;
    int hotY;
    int origHotX;
    int origHotY;
    
    public VncCanvas(final VncViewer viewer, final int maxWidth, final int maxHeight) throws IOException {
        this.maxWidth = 0;
        this.maxHeight = 0;
        this.zrleBufLen = 0;
        this.zrleRecWarningShown = false;
        this.zlibBufLen = 0;
        this.showSoftCursor = false;
        this.cursorX = 0;
        this.cursorY = 0;
        this.viewer = viewer;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.rfb = this.viewer.rfb;
        this.scalingFactor = this.viewer.options.scalingFactor;
        this.tightInflaters = new Inflater[4];
        this.cm8 = new DirectColorModel(8, 7, 56, 192);
        this.cm24 = new DirectColorModel(24, 16711680, 65280, 255);
        this.colors = new Color[256];
        for (int i = 0; i < 256; ++i) {
            this.colors[i] = new Color(this.cm8.getRGB(i));
        }
        this.setPixelFormat();
        this.inputEnabled = false;
        if (!this.viewer.options.viewOnly) {
            this.enableInput(true);
        }
        this.addKeyListener(this);
    }
    
    public VncCanvas(final VncViewer vncViewer) throws IOException {
        this(vncViewer, 0, 0);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.scaledWidth, this.scaledHeight);
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(this.scaledWidth, this.scaledHeight);
    }
    
    public Dimension getMaximumSize() {
        return new Dimension(this.scaledWidth, this.scaledHeight);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        synchronized (this.memImage) {
            if (this.rfb.framebufferWidth == this.scaledWidth) {
                graphics.drawImage(this.memImage, 0, 0, null);
            }
            else {
                this.paintScaledFrameBuffer(graphics);
            }
        }
        if (this.showSoftCursor) {
            final int n = this.cursorX - this.hotX;
            final int n2 = this.cursorY - this.hotY;
            if (new Rectangle(n, n2, this.cursorWidth, this.cursorHeight).intersects(graphics.getClipBounds())) {
                graphics.drawImage(this.softCursor, n, n2, null);
            }
        }
    }
    
    public void paintScaledFrameBuffer(final Graphics graphics) {
        graphics.drawImage(this.memImage, 0, 0, this.scaledWidth, this.scaledHeight, null);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0xA0) == 0x0) {
            return true;
        }
        if ((n & 0x20) != 0x0 && this.jpegRect != null) {
            synchronized (this.jpegRect) {
                this.memGraphics.drawImage(image, this.jpegRect.x, this.jpegRect.y, null);
                this.scheduleRepaint(this.jpegRect.x, this.jpegRect.y, this.jpegRect.width, this.jpegRect.height);
                this.jpegRect.notify();
            }
        }
        return false;
    }
    
    public synchronized void enableInput(final boolean b) {
        if (b && !this.inputEnabled) {
            this.inputEnabled = true;
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            if (this.viewer.showControls) {
                this.viewer.buttonPanel.enableRemoteAccessControls(true);
            }
            this.createSoftCursor();
        }
        else if (!b && this.inputEnabled) {
            this.inputEnabled = false;
            this.removeMouseListener(this);
            this.removeMouseMotionListener(this);
            if (this.viewer.showControls) {
                this.viewer.buttonPanel.enableRemoteAccessControls(false);
            }
            this.createSoftCursor();
        }
    }
    
    public void setPixelFormat() throws IOException {
        if (this.viewer.options.eightBitColors) {
            this.rfb.writeSetPixelFormat(8, 8, false, true, 7, 7, 3, 0, 3, 6);
            this.bytesPixel = 1;
        }
        else {
            this.rfb.writeSetPixelFormat(32, 24, false, true, 255, 255, 255, 16, 8, 0);
            this.bytesPixel = 4;
        }
        this.updateFramebufferSize();
    }
    
    void updateFramebufferSize() {
        final int framebufferWidth = this.rfb.framebufferWidth;
        final int framebufferHeight = this.rfb.framebufferHeight;
        if (this.maxWidth > 0 && this.maxHeight > 0) {
            this.scalingFactor = Math.min(this.maxWidth * 100 / framebufferWidth, this.maxHeight * 100 / framebufferHeight);
            if (this.scalingFactor > 100) {
                this.scalingFactor = 100;
            }
            System.out.println("Scaling desktop at " + this.scalingFactor + "%");
        }
        this.scaledWidth = (framebufferWidth * this.scalingFactor + 50) / 100;
        this.scaledHeight = (framebufferHeight * this.scalingFactor + 50) / 100;
        if (this.memImage == null) {
            this.memImage = this.viewer.vncContainer.createImage(framebufferWidth, framebufferHeight);
            this.memGraphics = this.memImage.getGraphics();
        }
        else if (this.memImage.getWidth(null) != framebufferWidth || this.memImage.getHeight(null) != framebufferHeight) {
            synchronized (this.memImage) {
                this.memImage = this.viewer.vncContainer.createImage(framebufferWidth, framebufferHeight);
                this.memGraphics = this.memImage.getGraphics();
            }
        }
        if (this.bytesPixel == 1) {
            this.pixels24 = null;
            this.pixels8 = new byte[framebufferWidth * framebufferHeight];
            this.pixelsSource = new MemoryImageSource(framebufferWidth, framebufferHeight, this.cm8, this.pixels8, 0, framebufferWidth);
            this.zrleTilePixels24 = null;
            this.zrleTilePixels8 = new byte[4096];
        }
        else {
            this.pixels8 = null;
            this.pixels24 = new int[framebufferWidth * framebufferHeight];
            this.pixelsSource = new MemoryImageSource(framebufferWidth, framebufferHeight, this.cm24, this.pixels24, 0, framebufferWidth);
            this.zrleTilePixels8 = null;
            this.zrleTilePixels24 = new int[4096];
        }
        this.pixelsSource.setAnimated(true);
        this.rawPixelsImage = Toolkit.getDefaultToolkit().createImage(this.pixelsSource);
        if (this.viewer.inSeparateFrame) {
            if (this.viewer.desktopScrollPane != null) {
                this.resizeDesktopFrame();
            }
        }
        else {
            this.setSize(this.scaledWidth, this.scaledHeight);
        }
        this.viewer.moveFocusToDesktop();
    }
    
    void resizeDesktopFrame() {
        this.setSize(this.scaledWidth, this.scaledHeight);
        final Insets insets = this.viewer.desktopScrollPane.getInsets();
        this.viewer.desktopScrollPane.setSize(this.scaledWidth + 2 * Math.min(insets.left, insets.right), this.scaledHeight + 2 * Math.min(insets.top, insets.bottom));
        this.viewer.vncFrame.pack();
        final Dimension screenSize = this.viewer.vncFrame.getToolkit().getScreenSize();
        final Dimension size;
        final Dimension dimension = size = this.viewer.vncFrame.getSize();
        final Dimension dimension2 = screenSize;
        dimension2.height -= 30;
        final Dimension dimension3 = screenSize;
        dimension3.width -= 30;
        boolean b = false;
        if (dimension.height > screenSize.height) {
            size.height = screenSize.height;
            b = true;
        }
        if (dimension.width > screenSize.width) {
            size.width = screenSize.width;
            b = true;
        }
        if (b) {
            this.viewer.vncFrame.setSize(size);
        }
        this.viewer.desktopScrollPane.doLayout();
    }
    
    public void processNormalProtocol() throws Exception {
        this.viewer.checkRecordingStatus();
        this.rfb.writeFramebufferUpdateRequest(0, 0, this.rfb.framebufferWidth, this.rfb.framebufferHeight, false);
        this.resetStats();
        int n = 0;
        while (true) {
            final int serverMessageType = this.rfb.readServerMessageType();
            switch (serverMessageType) {
                case 0: {
                    if (this.statNumUpdates == this.viewer.debugStatsExcludeUpdates && n == 0) {
                        this.resetStats();
                        n = 1;
                    }
                    else if (this.statNumUpdates == this.viewer.debugStatsMeasureUpdates && n != 0) {
                        this.viewer.disconnect();
                    }
                    this.rfb.readFramebufferUpdate();
                    ++this.statNumUpdates;
                    boolean b = false;
                    for (int i = 0; i < this.rfb.updateNRects; ++i) {
                        this.rfb.readFramebufferUpdateRectHdr();
                        ++this.statNumTotalRects;
                        final int updateRectX = this.rfb.updateRectX;
                        final int updateRectY = this.rfb.updateRectY;
                        final int updateRectW = this.rfb.updateRectW;
                        final int updateRectH = this.rfb.updateRectH;
                        final int updateRectEncoding = this.rfb.updateRectEncoding;
                        final RfbProto rfb = this.rfb;
                        if (updateRectEncoding == -224) {
                            break;
                        }
                        final int updateRectEncoding2 = this.rfb.updateRectEncoding;
                        final RfbProto rfb2 = this.rfb;
                        if (updateRectEncoding2 == -223) {
                            this.rfb.setFramebufferSize(updateRectW, updateRectH);
                            this.updateFramebufferSize();
                            break;
                        }
                        final int updateRectEncoding3 = this.rfb.updateRectEncoding;
                        final RfbProto rfb3 = this.rfb;
                        if (updateRectEncoding3 != -240) {
                            final int updateRectEncoding4 = this.rfb.updateRectEncoding;
                            final RfbProto rfb4 = this.rfb;
                            if (updateRectEncoding4 != -239) {
                                final int updateRectEncoding5 = this.rfb.updateRectEncoding;
                                final RfbProto rfb5 = this.rfb;
                                if (updateRectEncoding5 == -232) {
                                    this.softCursorMove(updateRectX, updateRectY);
                                    b = true;
                                    continue;
                                }
                                final long numBytesRead = this.rfb.getNumBytesRead();
                                this.rfb.startTiming();
                                switch (this.rfb.updateRectEncoding) {
                                    case 0: {
                                        ++this.statNumRectsRaw;
                                        this.handleRawRect(updateRectX, updateRectY, updateRectW, updateRectH);
                                        break;
                                    }
                                    case 1: {
                                        ++this.statNumRectsCopy;
                                        this.handleCopyRect(updateRectX, updateRectY, updateRectW, updateRectH);
                                        break;
                                    }
                                    case 2: {
                                        this.handleRRERect(updateRectX, updateRectY, updateRectW, updateRectH);
                                        break;
                                    }
                                    case 4: {
                                        this.handleCoRRERect(updateRectX, updateRectY, updateRectW, updateRectH);
                                        break;
                                    }
                                    case 5: {
                                        ++this.statNumRectsHextile;
                                        this.handleHextileRect(updateRectX, updateRectY, updateRectW, updateRectH);
                                        break;
                                    }
                                    case 16: {
                                        ++this.statNumRectsZRLE;
                                        this.handleZRLERect(updateRectX, updateRectY, updateRectW, updateRectH);
                                        break;
                                    }
                                    case 6: {
                                        this.handleZlibRect(updateRectX, updateRectY, updateRectW, updateRectH);
                                        break;
                                    }
                                    case 7: {
                                        ++this.statNumRectsTight;
                                        this.handleTightRect(updateRectX, updateRectY, updateRectW, updateRectH);
                                        break;
                                    }
                                    default: {
                                        throw new Exception("Unknown RFB rectangle encoding " + this.rfb.updateRectEncoding);
                                    }
                                }
                                this.rfb.stopTiming();
                                ++this.statNumPixelRects;
                                this.statNumBytesDecoded += updateRectW * updateRectH * this.bytesPixel;
                                this.statNumBytesEncoded += (int)(this.rfb.getNumBytesRead() - numBytesRead);
                                continue;
                            }
                        }
                        this.handleCursorShapeUpdate(this.rfb.updateRectEncoding, updateRectX, updateRectY, updateRectW, updateRectH);
                    }
                    boolean b2 = false;
                    if (this.viewer.checkRecordingStatus()) {
                        b2 = true;
                    }
                    if (this.viewer.deferUpdateRequests > 0 && this.rfb.available() == 0 && !b) {
                        synchronized (this.rfb) {
                            try {
                                this.rfb.wait(this.viewer.deferUpdateRequests);
                            }
                            catch (InterruptedException ex) {}
                        }
                    }
                    this.viewer.autoSelectEncodings();
                    if (this.viewer.options.eightBitColors != (this.bytesPixel == 1)) {
                        this.setPixelFormat();
                        b2 = true;
                    }
                    this.rfb.writeFramebufferUpdateRequest(0, 0, this.rfb.framebufferWidth, this.rfb.framebufferHeight, !b2);
                    continue;
                }
                case 1: {
                    throw new Exception("Can't handle SetColourMapEntries message");
                }
                case 2: {
                    Toolkit.getDefaultToolkit().beep();
                    continue;
                }
                case 3: {
                    this.viewer.clipboard.setCutText(this.rfb.readServerCutText());
                    continue;
                }
                default: {
                    throw new Exception("Unknown RFB message type " + serverMessageType);
                }
            }
        }
    }
    
    void handleRawRect(final int n, final int n2, final int n3, final int n4) throws IOException {
        this.handleRawRect(n, n2, n3, n4, true);
    }
    
    void handleRawRect(final int n, final int n2, final int n3, final int n4, final boolean b) throws IOException {
        if (this.bytesPixel == 1) {
            for (int i = n2; i < n2 + n4; ++i) {
                this.rfb.readFully(this.pixels8, i * this.rfb.framebufferWidth + n, n3);
                if (this.rfb.rec != null) {
                    this.rfb.rec.write(this.pixels8, i * this.rfb.framebufferWidth + n, n3);
                }
            }
        }
        else {
            final byte[] array = new byte[n3 * 4];
            for (int j = n2; j < n2 + n4; ++j) {
                this.rfb.readFully(array);
                if (this.rfb.rec != null) {
                    this.rfb.rec.write(array);
                }
                final int n5 = j * this.rfb.framebufferWidth + n;
                for (int k = 0; k < n3; ++k) {
                    this.pixels24[n5 + k] = ((array[k * 4 + 2] & 0xFF) << 16 | (array[k * 4 + 1] & 0xFF) << 8 | (array[k * 4] & 0xFF));
                }
            }
        }
        this.handleUpdatedPixels(n, n2, n3, n4);
        if (b) {
            this.scheduleRepaint(n, n2, n3, n4);
        }
    }
    
    void handleCopyRect(final int n, final int n2, final int n3, final int n4) throws IOException {
        this.rfb.readCopyRect();
        this.memGraphics.copyArea(this.rfb.copyRectSrcX, this.rfb.copyRectSrcY, n3, n4, n - this.rfb.copyRectSrcX, n2 - this.rfb.copyRectSrcY);
        this.scheduleRepaint(n, n2, n3, n4);
    }
    
    void handleRRERect(final int n, final int n2, final int n3, final int n4) throws IOException {
        final int u32 = this.rfb.readU32();
        final byte[] array = new byte[this.bytesPixel];
        this.rfb.readFully(array);
        Color color;
        if (this.bytesPixel == 1) {
            color = this.colors[array[0] & 0xFF];
        }
        else {
            color = new Color(array[2] & 0xFF, array[1] & 0xFF, array[0] & 0xFF);
        }
        this.memGraphics.setColor(color);
        this.memGraphics.fillRect(n, n2, n3, n4);
        final byte[] array2 = new byte[u32 * (this.bytesPixel + 8)];
        this.rfb.readFully(array2);
        final DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(array2));
        if (this.rfb.rec != null) {
            this.rfb.rec.writeIntBE(u32);
            this.rfb.rec.write(array);
            this.rfb.rec.write(array2);
        }
        for (int i = 0; i < u32; ++i) {
            Color color2;
            if (this.bytesPixel == 1) {
                color2 = this.colors[dataInputStream.readUnsignedByte()];
            }
            else {
                dataInputStream.skip(4L);
                color2 = new Color(array2[i * 12 + 2] & 0xFF, array2[i * 12 + 1] & 0xFF, array2[i * 12] & 0xFF);
            }
            final int n5 = n + dataInputStream.readUnsignedShort();
            final int n6 = n2 + dataInputStream.readUnsignedShort();
            final int unsignedShort = dataInputStream.readUnsignedShort();
            final int unsignedShort2 = dataInputStream.readUnsignedShort();
            this.memGraphics.setColor(color2);
            this.memGraphics.fillRect(n5, n6, unsignedShort, unsignedShort2);
        }
        this.scheduleRepaint(n, n2, n3, n4);
    }
    
    void handleCoRRERect(final int n, final int n2, final int n3, final int n4) throws IOException {
        final int u32 = this.rfb.readU32();
        final byte[] array = new byte[this.bytesPixel];
        this.rfb.readFully(array);
        Color color;
        if (this.bytesPixel == 1) {
            color = this.colors[array[0] & 0xFF];
        }
        else {
            color = new Color(array[2] & 0xFF, array[1] & 0xFF, array[0] & 0xFF);
        }
        this.memGraphics.setColor(color);
        this.memGraphics.fillRect(n, n2, n3, n4);
        final byte[] array2 = new byte[u32 * (this.bytesPixel + 4)];
        this.rfb.readFully(array2);
        if (this.rfb.rec != null) {
            this.rfb.rec.writeIntBE(u32);
            this.rfb.rec.write(array);
            this.rfb.rec.write(array2);
        }
        int n5 = 0;
        for (int i = 0; i < u32; ++i) {
            Color color2;
            if (this.bytesPixel == 1) {
                color2 = this.colors[array2[n5++] & 0xFF];
            }
            else {
                color2 = new Color(array2[n5 + 2] & 0xFF, array2[n5 + 1] & 0xFF, array2[n5] & 0xFF);
                n5 += 4;
            }
            final int n6 = n + (array2[n5++] & 0xFF);
            final int n7 = n2 + (array2[n5++] & 0xFF);
            final int n8 = array2[n5++] & 0xFF;
            final int n9 = array2[n5++] & 0xFF;
            this.memGraphics.setColor(color2);
            this.memGraphics.fillRect(n6, n7, n8, n9);
        }
        this.scheduleRepaint(n, n2, n3, n4);
    }
    
    void handleHextileRect(final int n, final int n2, final int n3, final int n4) throws IOException {
        this.hextile_bg = new Color(0);
        this.hextile_fg = new Color(0);
        for (int i = n2; i < n2 + n4; i += 16) {
            int n5 = 16;
            if (n2 + n4 - i < 16) {
                n5 = n2 + n4 - i;
            }
            for (int j = n; j < n + n3; j += 16) {
                int n6 = 16;
                if (n + n3 - j < 16) {
                    n6 = n + n3 - j;
                }
                this.handleHextileSubrect(j, i, n6, n5);
            }
            this.scheduleRepaint(n, n2, n3, n4);
        }
    }
    
    void handleHextileSubrect(final int n, final int n2, final int n3, final int n4) throws IOException {
        final int u8 = this.rfb.readU8();
        if (this.rfb.rec != null) {
            this.rfb.rec.writeByte(u8);
        }
        final int n5 = u8;
        final RfbProto rfb = this.rfb;
        if ((n5 & 0x1) != 0x0) {
            this.handleRawRect(n, n2, n3, n4, false);
            return;
        }
        final byte[] array = new byte[this.bytesPixel];
        final int n6 = u8;
        final RfbProto rfb2 = this.rfb;
        if ((n6 & 0x2) != 0x0) {
            this.rfb.readFully(array);
            if (this.bytesPixel == 1) {
                this.hextile_bg = this.colors[array[0] & 0xFF];
            }
            else {
                this.hextile_bg = new Color(array[2] & 0xFF, array[1] & 0xFF, array[0] & 0xFF);
            }
            if (this.rfb.rec != null) {
                this.rfb.rec.write(array);
            }
        }
        this.memGraphics.setColor(this.hextile_bg);
        this.memGraphics.fillRect(n, n2, n3, n4);
        final int n7 = u8;
        final RfbProto rfb3 = this.rfb;
        if ((n7 & 0x4) != 0x0) {
            this.rfb.readFully(array);
            if (this.bytesPixel == 1) {
                this.hextile_fg = this.colors[array[0] & 0xFF];
            }
            else {
                this.hextile_fg = new Color(array[2] & 0xFF, array[1] & 0xFF, array[0] & 0xFF);
            }
            if (this.rfb.rec != null) {
                this.rfb.rec.write(array);
            }
        }
        final int n8 = u8;
        final RfbProto rfb4 = this.rfb;
        if ((n8 & 0x8) == 0x0) {
            return;
        }
        final int u9 = this.rfb.readU8();
        int n9 = u9 * 2;
        final int n10 = u8;
        final RfbProto rfb5 = this.rfb;
        if ((n10 & 0x10) != 0x0) {
            n9 += u9 * this.bytesPixel;
        }
        final byte[] array2 = new byte[n9];
        this.rfb.readFully(array2);
        if (this.rfb.rec != null) {
            this.rfb.rec.writeByte(u9);
            this.rfb.rec.write(array2);
        }
        int n11 = 0;
        final int n12 = u8;
        final RfbProto rfb6 = this.rfb;
        if ((n12 & 0x10) == 0x0) {
            this.memGraphics.setColor(this.hextile_fg);
            for (int i = 0; i < u9; ++i) {
                final int n13 = array2[n11++] & 0xFF;
                final int n14 = array2[n11++] & 0xFF;
                this.memGraphics.fillRect(n + (n13 >> 4), n2 + (n13 & 0xF), (n14 >> 4) + 1, (n14 & 0xF) + 1);
            }
        }
        else if (this.bytesPixel == 1) {
            for (int j = 0; j < u9; ++j) {
                this.hextile_fg = this.colors[array2[n11++] & 0xFF];
                final int n15 = array2[n11++] & 0xFF;
                final int n16 = array2[n11++] & 0xFF;
                final int n17 = n + (n15 >> 4);
                final int n18 = n2 + (n15 & 0xF);
                final int n19 = (n16 >> 4) + 1;
                final int n20 = (n16 & 0xF) + 1;
                this.memGraphics.setColor(this.hextile_fg);
                this.memGraphics.fillRect(n17, n18, n19, n20);
            }
        }
        else {
            for (int k = 0; k < u9; ++k) {
                this.hextile_fg = new Color(array2[n11 + 2] & 0xFF, array2[n11 + 1] & 0xFF, array2[n11] & 0xFF);
                n11 += 4;
                final int n21 = array2[n11++] & 0xFF;
                final int n22 = array2[n11++] & 0xFF;
                final int n23 = n + (n21 >> 4);
                final int n24 = n2 + (n21 & 0xF);
                final int n25 = (n22 >> 4) + 1;
                final int n26 = (n22 & 0xF) + 1;
                this.memGraphics.setColor(this.hextile_fg);
                this.memGraphics.fillRect(n23, n24, n25, n26);
            }
        }
    }
    
    void handleZRLERect(final int n, final int n2, final int n3, final int n4) throws Exception {
        if (this.zrleInStream == null) {
            this.zrleInStream = new ZlibInStream();
        }
        final int u32 = this.rfb.readU32();
        if (u32 > 67108864) {
            throw new Exception("ZRLE decoder: illegal compressed data size");
        }
        if (this.zrleBuf == null || this.zrleBufLen < u32) {
            this.zrleBufLen = u32 + 4096;
            this.zrleBuf = new byte[this.zrleBufLen];
        }
        this.rfb.readFully(this.zrleBuf, 0, u32);
        if (this.rfb.rec != null) {
            if (this.rfb.recordFromBeginning) {
                this.rfb.rec.writeIntBE(u32);
                this.rfb.rec.write(this.zrleBuf, 0, u32);
            }
            else if (!this.zrleRecWarningShown) {
                System.out.println("Warning: ZRLE session can be recorded only from the beginning");
                System.out.println("Warning: Recorded file may be corrupted");
                this.zrleRecWarningShown = true;
            }
        }
        this.zrleInStream.setUnderlying(new MemInStream(this.zrleBuf, 0, u32), u32);
        for (int i = n2; i < n2 + n4; i += 64) {
            final int min = Math.min(n2 + n4 - i, 64);
            for (int j = n; j < n + n3; j += 64) {
                final int min2 = Math.min(n + n3 - j, 64);
                final int u33 = this.zrleInStream.readU8();
                final boolean b = (u33 & 0x80) != 0x0;
                final int n5 = u33 & 0x7F;
                final int[] array = new int[128];
                this.readZrlePalette(array, n5);
                if (n5 == 1) {
                    final int n6 = array[0];
                    this.memGraphics.setColor((this.bytesPixel == 1) ? this.colors[n6] : new Color(0xFF000000 | n6));
                    this.memGraphics.fillRect(j, i, min2, min);
                }
                else {
                    if (!b) {
                        if (n5 == 0) {
                            this.readZrleRawPixels(min2, min);
                        }
                        else {
                            this.readZrlePackedPixels(min2, min, array, n5);
                        }
                    }
                    else if (n5 == 0) {
                        this.readZrlePlainRLEPixels(min2, min);
                    }
                    else {
                        this.readZrlePackedRLEPixels(min2, min, array);
                    }
                    this.handleUpdatedZrleTile(j, i, min2, min);
                }
            }
        }
        this.zrleInStream.reset();
        this.scheduleRepaint(n, n2, n3, n4);
    }
    
    int readPixel(final InStream inStream) throws Exception {
        int u8;
        if (this.bytesPixel == 1) {
            u8 = inStream.readU8();
        }
        else {
            u8 = ((inStream.readU8() & 0xFF) << 16 | (inStream.readU8() & 0xFF) << 8 | (inStream.readU8() & 0xFF));
        }
        return u8;
    }
    
    void readPixels(final InStream inStream, final int[] array, final int n) throws Exception {
        if (this.bytesPixel == 1) {
            final byte[] array2 = new byte[n];
            inStream.readBytes(array2, 0, n);
            for (int i = 0; i < n; ++i) {
                array[i] = (array2[i] & 0xFF);
            }
        }
        else {
            final byte[] array3 = new byte[n * 3];
            inStream.readBytes(array3, 0, n * 3);
            for (int j = 0; j < n; ++j) {
                array[j] = ((array3[j * 3 + 2] & 0xFF) << 16 | (array3[j * 3 + 1] & 0xFF) << 8 | (array3[j * 3] & 0xFF));
            }
        }
    }
    
    void readZrlePalette(final int[] array, final int n) throws Exception {
        this.readPixels(this.zrleInStream, array, n);
    }
    
    void readZrleRawPixels(final int n, final int n2) throws Exception {
        if (this.bytesPixel == 1) {
            this.zrleInStream.readBytes(this.zrleTilePixels8, 0, n * n2);
        }
        else {
            this.readPixels(this.zrleInStream, this.zrleTilePixels24, n * n2);
        }
    }
    
    void readZrlePackedPixels(final int n, final int n2, final int[] array, final int n3) throws Exception {
        final int n4 = (n3 > 16) ? 8 : ((n3 > 4) ? 4 : ((n3 > 2) ? 2 : 1));
        int i = 0;
        for (int j = 0; j < n2; ++j) {
            final int n5 = i + n;
            int u8 = 0;
            int n6 = 0;
            while (i < n5) {
                if (n6 == 0) {
                    u8 = this.zrleInStream.readU8();
                    n6 = 8;
                }
                n6 -= n4;
                final int n7 = u8 >> n6 & (1 << n4) - 1 & 0x7F;
                if (this.bytesPixel == 1) {
                    this.zrleTilePixels8[i++] = (byte)array[n7];
                }
                else {
                    this.zrleTilePixels24[i++] = array[n7];
                }
            }
        }
    }
    
    void readZrlePlainRLEPixels(final int n, final int n2) throws Exception {
        int i = 0;
        final int n3 = i + n * n2;
        while (i < n3) {
            final int pixel = this.readPixel(this.zrleInStream);
            int n4 = 1;
            int j;
            do {
                j = this.zrleInStream.readU8();
                n4 += j;
            } while (j == 255);
            if (n4 > n3 - i) {
                throw new Exception("ZRLE decoder: assertion failed (len <= end-ptr)");
            }
            if (this.bytesPixel == 1) {
                while (n4-- > 0) {
                    this.zrleTilePixels8[i++] = (byte)pixel;
                }
            }
            else {
                while (n4-- > 0) {
                    this.zrleTilePixels24[i++] = pixel;
                }
            }
        }
    }
    
    void readZrlePackedRLEPixels(final int n, final int n2, final int[] array) throws Exception {
        int i = 0;
        final int n3 = i + n * n2;
        while (i < n3) {
            final int u8 = this.zrleInStream.readU8();
            int n4 = 1;
            if ((u8 & 0x80) != 0x0) {
                int j;
                do {
                    j = this.zrleInStream.readU8();
                    n4 += j;
                } while (j == 255);
                if (n4 > n3 - i) {
                    throw new Exception("ZRLE decoder: assertion failed (len <= end - ptr)");
                }
            }
            final int n5 = array[u8 & 0x7F];
            if (this.bytesPixel == 1) {
                while (n4-- > 0) {
                    this.zrleTilePixels8[i++] = (byte)n5;
                }
            }
            else {
                while (n4-- > 0) {
                    this.zrleTilePixels24[i++] = n5;
                }
            }
        }
    }
    
    void handleUpdatedZrleTile(final int n, final int n2, final int n3, final int n4) {
        int[] array;
        int[] array2;
        if (this.bytesPixel == 1) {
            array = (int[])this.zrleTilePixels8;
            array2 = (int[])this.pixels8;
        }
        else {
            array = this.zrleTilePixels24;
            array2 = this.pixels24;
        }
        int n5 = 0;
        int n6 = n2 * this.rfb.framebufferWidth + n;
        for (int i = 0; i < n4; ++i) {
            System.arraycopy(array, n5, array2, n6, n3);
            n5 += n3;
            n6 += this.rfb.framebufferWidth;
        }
        this.handleUpdatedPixels(n, n2, n3, n4);
    }
    
    void handleZlibRect(final int n, final int n2, final int n3, final int n4) throws Exception {
        final int u32 = this.rfb.readU32();
        if (this.zlibBuf == null || this.zlibBufLen < u32) {
            this.zlibBufLen = u32 * 2;
            this.zlibBuf = new byte[this.zlibBufLen];
        }
        this.rfb.readFully(this.zlibBuf, 0, u32);
        if (this.rfb.rec != null && this.rfb.recordFromBeginning) {
            this.rfb.rec.writeIntBE(u32);
            this.rfb.rec.write(this.zlibBuf, 0, u32);
        }
        if (this.zlibInflater == null) {
            this.zlibInflater = new Inflater();
        }
        this.zlibInflater.setInput(this.zlibBuf, 0, u32);
        if (this.bytesPixel == 1) {
            for (int i = n2; i < n2 + n4; ++i) {
                this.zlibInflater.inflate(this.pixels8, i * this.rfb.framebufferWidth + n, n3);
                if (this.rfb.rec != null && !this.rfb.recordFromBeginning) {
                    this.rfb.rec.write(this.pixels8, i * this.rfb.framebufferWidth + n, n3);
                }
            }
        }
        else {
            final byte[] array = new byte[n3 * 4];
            for (int j = n2; j < n2 + n4; ++j) {
                this.zlibInflater.inflate(array);
                final int n5 = j * this.rfb.framebufferWidth + n;
                for (int k = 0; k < n3; ++k) {
                    this.pixels24[n5 + k] = ((array[k * 4 + 2] & 0xFF) << 16 | (array[k * 4 + 1] & 0xFF) << 8 | (array[k * 4] & 0xFF));
                }
                if (this.rfb.rec != null && !this.rfb.recordFromBeginning) {
                    this.rfb.rec.write(array);
                }
            }
        }
        this.handleUpdatedPixels(n, n2, n3, n4);
        this.scheduleRepaint(n, n2, n3, n4);
    }
    
    void handleTightRect(final int n, final int n2, final int n3, final int n4) throws Exception {
        int u8 = this.rfb.readU8();
        Label_0087: {
            if (this.rfb.rec != null) {
                if (!this.rfb.recordFromBeginning) {
                    final int n5 = u8;
                    final RfbProto rfb = this.rfb;
                    if (n5 != 8 << 4) {
                        final int n6 = u8;
                        final RfbProto rfb2 = this.rfb;
                        if (n6 != 9 << 4) {
                            this.rfb.rec.writeByte(u8 | 0xF);
                            break Label_0087;
                        }
                    }
                }
                this.rfb.rec.writeByte(u8);
            }
        }
        for (int i = 0; i < 4; ++i) {
            if ((u8 & 0x1) != 0x0 && this.tightInflaters[i] != null) {
                this.tightInflaters[i] = null;
            }
            u8 >>= 1;
        }
        final int n7 = u8;
        final RfbProto rfb3 = this.rfb;
        if (n7 > 9) {
            throw new Exception("Incorrect tight subencoding: " + u8);
        }
        final int n8 = u8;
        final RfbProto rfb4 = this.rfb;
        if (n8 == 8) {
            if (this.bytesPixel == 1) {
                final int u9 = this.rfb.readU8();
                this.memGraphics.setColor(this.colors[u9]);
                if (this.rfb.rec != null) {
                    this.rfb.rec.writeByte(u9);
                }
            }
            else {
                final byte[] array = new byte[3];
                this.rfb.readFully(array);
                if (this.rfb.rec != null) {
                    this.rfb.rec.write(array);
                }
                this.memGraphics.setColor(new Color(0xFF000000 | (array[0] & 0xFF) << 16 | (array[1] & 0xFF) << 8 | (array[2] & 0xFF)));
            }
            this.memGraphics.fillRect(n, n2, n3, n4);
            this.scheduleRepaint(n, n2, n3, n4);
            return;
        }
        final int n9 = u8;
        final RfbProto rfb5 = this.rfb;
        if (n9 == 9) {
            ++this.statNumRectsTightJPEG;
            final byte[] array2 = new byte[this.rfb.readCompactLen()];
            this.rfb.readFully(array2);
            if (this.rfb.rec != null) {
                if (!this.rfb.recordFromBeginning) {
                    this.rfb.recordCompactLen(array2.length);
                }
                this.rfb.rec.write(array2);
            }
            final Image image = Toolkit.getDefaultToolkit().createImage(array2);
            synchronized (this.jpegRect = new Rectangle(n, n2, n3, n4)) {
                Toolkit.getDefaultToolkit().prepareImage(image, -1, -1, this);
                try {
                    this.jpegRect.wait(3000L);
                }
                catch (InterruptedException ex) {
                    throw new Exception("Interrupted while decoding JPEG image");
                }
            }
            this.jpegRect = null;
            return;
        }
        int n10 = 0;
        int n11 = n3;
        final byte[] array3 = new byte[2];
        final int[] array4 = new int[256];
        boolean b = false;
        final int n12 = u8;
        final RfbProto rfb6 = this.rfb;
        if ((n12 & 0x4) != 0x0) {
            final int u10 = this.rfb.readU8();
            if (this.rfb.rec != null) {
                this.rfb.rec.writeByte(u10);
            }
            final int n13 = u10;
            final RfbProto rfb7 = this.rfb;
            if (n13 == 1) {
                n10 = this.rfb.readU8() + 1;
                if (this.rfb.rec != null) {
                    this.rfb.rec.writeByte(n10 - 1);
                }
                if (this.bytesPixel == 1) {
                    if (n10 != 2) {
                        throw new Exception("Incorrect tight palette size: " + n10);
                    }
                    this.rfb.readFully(array3);
                    if (this.rfb.rec != null) {
                        this.rfb.rec.write(array3);
                    }
                }
                else {
                    final byte[] array5 = new byte[n10 * 3];
                    this.rfb.readFully(array5);
                    if (this.rfb.rec != null) {
                        this.rfb.rec.write(array5);
                    }
                    for (int j = 0; j < n10; ++j) {
                        array4[j] = ((array5[j * 3] & 0xFF) << 16 | (array5[j * 3 + 1] & 0xFF) << 8 | (array5[j * 3 + 2] & 0xFF));
                    }
                }
                if (n10 == 2) {
                    n11 = (n3 + 7) / 8;
                }
            }
            else {
                final int n14 = u10;
                final RfbProto rfb8 = this.rfb;
                if (n14 == 2) {
                    b = true;
                }
                else {
                    final boolean b2 = u10 != 0;
                    final RfbProto rfb9 = this.rfb;
                    if (b2) {
                        throw new Exception("Incorrect tight filter id: " + u10);
                    }
                }
            }
        }
        if (n10 == 0 && this.bytesPixel == 4) {
            n11 *= 3;
        }
        final int n16;
        final int n15 = n16 = n4 * n11;
        final RfbProto rfb10 = this.rfb;
        if (n16 < 12) {
            if (n10 != 0) {
                final byte[] array6 = new byte[n15];
                this.rfb.readFully(array6);
                if (this.rfb.rec != null) {
                    this.rfb.rec.write(array6);
                }
                if (n10 == 2) {
                    if (this.bytesPixel == 1) {
                        this.decodeMonoData(n, n2, n3, n4, array6, array3);
                    }
                    else {
                        this.decodeMonoData(n, n2, n3, n4, array6, array4);
                    }
                }
                else {
                    int n17 = 0;
                    for (int k = n2; k < n2 + n4; ++k) {
                        for (int l = n; l < n + n3; ++l) {
                            this.pixels24[k * this.rfb.framebufferWidth + l] = array4[array6[n17++] & 0xFF];
                        }
                    }
                }
            }
            else if (b) {
                final byte[] array7 = new byte[n3 * n4 * 3];
                this.rfb.readFully(array7);
                if (this.rfb.rec != null) {
                    this.rfb.rec.write(array7);
                }
                this.decodeGradientData(n, n2, n3, n4, array7);
            }
            else if (this.bytesPixel == 1) {
                for (int n18 = n2; n18 < n2 + n4; ++n18) {
                    this.rfb.readFully(this.pixels8, n18 * this.rfb.framebufferWidth + n, n3);
                    if (this.rfb.rec != null) {
                        this.rfb.rec.write(this.pixels8, n18 * this.rfb.framebufferWidth + n, n3);
                    }
                }
            }
            else {
                final byte[] array8 = new byte[n3 * 3];
                for (int n19 = n2; n19 < n2 + n4; ++n19) {
                    this.rfb.readFully(array8);
                    if (this.rfb.rec != null) {
                        this.rfb.rec.write(array8);
                    }
                    final int n20 = n19 * this.rfb.framebufferWidth + n;
                    for (int n21 = 0; n21 < n3; ++n21) {
                        this.pixels24[n20 + n21] = ((array8[n21 * 3] & 0xFF) << 16 | (array8[n21 * 3 + 1] & 0xFF) << 8 | (array8[n21 * 3 + 2] & 0xFF));
                    }
                }
            }
        }
        else {
            final byte[] input = new byte[this.rfb.readCompactLen()];
            this.rfb.readFully(input);
            if (this.rfb.rec != null && this.rfb.recordFromBeginning) {
                this.rfb.rec.write(input);
            }
            final int n22 = u8 & 0x3;
            if (this.tightInflaters[n22] == null) {
                this.tightInflaters[n22] = new Inflater();
            }
            final Inflater inflater = this.tightInflaters[n22];
            inflater.setInput(input);
            final byte[] array9 = new byte[n15];
            inflater.inflate(array9);
            if (this.rfb.rec != null && !this.rfb.recordFromBeginning) {
                this.rfb.recordCompressedData(array9);
            }
            if (n10 != 0) {
                if (n10 == 2) {
                    if (this.bytesPixel == 1) {
                        this.decodeMonoData(n, n2, n3, n4, array9, array3);
                    }
                    else {
                        this.decodeMonoData(n, n2, n3, n4, array9, array4);
                    }
                }
                else {
                    int n23 = 0;
                    for (int n24 = n2; n24 < n2 + n4; ++n24) {
                        for (int n25 = n; n25 < n + n3; ++n25) {
                            this.pixels24[n24 * this.rfb.framebufferWidth + n25] = array4[array9[n23++] & 0xFF];
                        }
                    }
                }
            }
            else if (b) {
                this.decodeGradientData(n, n2, n3, n4, array9);
            }
            else if (this.bytesPixel == 1) {
                int n26 = n2 * this.rfb.framebufferWidth + n;
                for (int n27 = 0; n27 < n4; ++n27) {
                    System.arraycopy(array9, n27 * n3, this.pixels8, n26, n3);
                    n26 += this.rfb.framebufferWidth;
                }
            }
            else {
                int n28 = 0;
                for (int n29 = 0; n29 < n4; ++n29) {
                    inflater.inflate(array9);
                    final int n30 = (n2 + n29) * this.rfb.framebufferWidth + n;
                    for (int n31 = 0; n31 < n3; ++n31) {
                        this.pixels24[n30 + n31] = ((array9[n28] & 0xFF) << 16 | (array9[n28 + 1] & 0xFF) << 8 | (array9[n28 + 2] & 0xFF));
                        n28 += 3;
                    }
                }
            }
        }
        this.handleUpdatedPixels(n, n2, n3, n4);
        this.scheduleRepaint(n, n2, n3, n4);
    }
    
    void decodeMonoData(final int n, final int n2, final int n3, final int n4, final byte[] array, final byte[] array2) {
        int n5 = n2 * this.rfb.framebufferWidth + n;
        final int n6 = (n3 + 7) / 8;
        for (int i = 0; i < n4; ++i) {
            int j;
            for (j = 0; j < n3 / 8; ++j) {
                final byte b = array[i * n6 + j];
                for (int k = 7; k >= 0; --k) {
                    this.pixels8[n5++] = array2[b >> k & 0x1];
                }
            }
            for (int l = 7; l >= 8 - n3 % 8; --l) {
                this.pixels8[n5++] = array2[array[i * n6 + j] >> l & 0x1];
            }
            n5 += this.rfb.framebufferWidth - n3;
        }
    }
    
    void decodeMonoData(final int n, final int n2, final int n3, final int n4, final byte[] array, final int[] array2) {
        int n5 = n2 * this.rfb.framebufferWidth + n;
        final int n6 = (n3 + 7) / 8;
        for (int i = 0; i < n4; ++i) {
            int j;
            for (j = 0; j < n3 / 8; ++j) {
                final byte b = array[i * n6 + j];
                for (int k = 7; k >= 0; --k) {
                    this.pixels24[n5++] = array2[b >> k & 0x1];
                }
            }
            for (int l = 7; l >= 8 - n3 % 8; --l) {
                this.pixels24[n5++] = array2[array[i * n6 + j] >> l & 0x1];
            }
            n5 += this.rfb.framebufferWidth - n3;
        }
    }
    
    void decodeGradientData(final int n, final int n2, final int n3, final int n4, final byte[] array) {
        final byte[] array2 = new byte[n3 * 3];
        final byte[] array3 = new byte[n3 * 3];
        final byte[] array4 = new byte[3];
        final int[] array5 = new int[3];
        int n5 = n2 * this.rfb.framebufferWidth + n;
        for (int i = 0; i < n4; ++i) {
            for (int j = 0; j < 3; ++j) {
                array3[j] = (array4[j] = (byte)(array2[j] + array[i * n3 * 3 + j]));
            }
            this.pixels24[n5++] = ((array4[0] & 0xFF) << 16 | (array4[1] & 0xFF) << 8 | (array4[2] & 0xFF));
            for (int k = 1; k < n3; ++k) {
                for (int l = 0; l < 3; ++l) {
                    array5[l] = (array2[k * 3 + l] & 0xFF) + (array4[l] & 0xFF) - (array2[(k - 1) * 3 + l] & 0xFF);
                    if (array5[l] > 255) {
                        array5[l] = 255;
                    }
                    else if (array5[l] < 0) {
                        array5[l] = 0;
                    }
                    array3[k * 3 + l] = (array4[l] = (byte)(array5[l] + array[(i * n3 + k) * 3 + l]));
                }
                this.pixels24[n5++] = ((array4[0] & 0xFF) << 16 | (array4[1] & 0xFF) << 8 | (array4[2] & 0xFF));
            }
            System.arraycopy(array3, 0, array2, 0, n3 * 3);
            n5 += this.rfb.framebufferWidth - n3;
        }
    }
    
    void handleUpdatedPixels(final int n, final int n2, final int n3, final int n4) {
        this.pixelsSource.newPixels(n, n2, n3, n4);
        this.memGraphics.setClip(n, n2, n3, n4);
        this.memGraphics.drawImage(this.rawPixelsImage, 0, 0, null);
        this.memGraphics.setClip(0, 0, this.rfb.framebufferWidth, this.rfb.framebufferHeight);
    }
    
    void scheduleRepaint(final int n, final int n2, final int n3, final int n4) {
        if (this.rfb.framebufferWidth == this.scaledWidth) {
            this.repaint(this.viewer.deferScreenUpdates, n, n2, n3, n4);
        }
        else {
            final int n5 = n * this.scalingFactor / 100;
            final int n6 = n2 * this.scalingFactor / 100;
            this.repaint(this.viewer.deferScreenUpdates, n5, n6, ((n + n3) * this.scalingFactor + 49) / 100 - n5 + 1, ((n2 + n4) * this.scalingFactor + 49) / 100 - n6 + 1);
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        this.processLocalKeyEvent(keyEvent);
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        this.processLocalKeyEvent(keyEvent);
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        keyEvent.consume();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.processLocalMouseEvent(mouseEvent, false);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.processLocalMouseEvent(mouseEvent, false);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.processLocalMouseEvent(mouseEvent, true);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.processLocalMouseEvent(mouseEvent, true);
    }
    
    public void processLocalKeyEvent(final KeyEvent keyEvent) {
        if (this.viewer.rfb != null && this.rfb.inNormalProtocol) {
            if (!this.inputEnabled) {
                if ((keyEvent.getKeyChar() == 'r' || keyEvent.getKeyChar() == 'R') && keyEvent.getID() == 401) {
                    try {
                        this.rfb.writeFramebufferUpdateRequest(0, 0, this.rfb.framebufferWidth, this.rfb.framebufferHeight, false);
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            else {
                synchronized (this.rfb) {
                    try {
                        this.rfb.writeKeyEvent(keyEvent);
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                    this.rfb.notify();
                }
            }
        }
        keyEvent.consume();
    }
    
    public void processLocalMouseEvent(final MouseEvent mouseEvent, final boolean b) {
        if (this.viewer.rfb != null && this.rfb.inNormalProtocol) {
            if (b) {
                this.softCursorMove(mouseEvent.getX(), mouseEvent.getY());
            }
            if (this.rfb.framebufferWidth != this.scaledWidth) {
                mouseEvent.translatePoint((mouseEvent.getX() * 100 + this.scalingFactor / 2) / this.scalingFactor - mouseEvent.getX(), (mouseEvent.getY() * 100 + this.scalingFactor / 2) / this.scalingFactor - mouseEvent.getY());
            }
            synchronized (this.rfb) {
                try {
                    this.rfb.writePointerEvent(mouseEvent);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                this.rfb.notify();
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    void resetStats() {
        this.statStartTime = System.currentTimeMillis();
        this.statNumUpdates = 0;
        this.statNumTotalRects = 0;
        this.statNumPixelRects = 0;
        this.statNumRectsTight = 0;
        this.statNumRectsTightJPEG = 0;
        this.statNumRectsZRLE = 0;
        this.statNumRectsHextile = 0;
        this.statNumRectsRaw = 0;
        this.statNumRectsCopy = 0;
        this.statNumBytesEncoded = 0;
        this.statNumBytesDecoded = 0;
    }
    
    synchronized void handleCursorShapeUpdate(final int n, final int origHotX, final int origHotY, final int origCursorWidth, final int origCursorHeight) throws IOException {
        this.softCursorFree();
        if (origCursorWidth * origCursorHeight == 0) {
            return;
        }
        if (this.viewer.options.ignoreCursorUpdates) {
            final int n2 = (origCursorWidth + 7) / 8 * origCursorHeight;
            final RfbProto rfb = this.rfb;
            if (n == -240) {
                this.rfb.skipBytes(6 + n2 * 2);
            }
            else {
                this.rfb.skipBytes(origCursorWidth * origCursorHeight * this.bytesPixel + n2);
            }
            return;
        }
        this.softCursorSource = this.decodeCursorShape(n, origCursorWidth, origCursorHeight);
        this.origCursorWidth = origCursorWidth;
        this.origCursorHeight = origCursorHeight;
        this.origHotX = origHotX;
        this.origHotY = origHotY;
        this.createSoftCursor();
        this.showSoftCursor = true;
        this.repaint(this.viewer.deferCursorUpdates, this.cursorX - this.hotX, this.cursorY - this.hotY, this.cursorWidth, this.cursorHeight);
    }
    
    synchronized MemoryImageSource decodeCursorShape(final int n, final int n2, final int n3) throws IOException {
        final int n4 = (n2 + 7) / 8;
        final int n5 = n4 * n3;
        final int[] array = new int[n2 * n3];
        final RfbProto rfb = this.rfb;
        if (n == -240) {
            final byte[] array2 = new byte[6];
            this.rfb.readFully(array2);
            final int[] array3 = { 0xFF000000 | (array2[3] & 0xFF) << 16 | (array2[4] & 0xFF) << 8 | (array2[5] & 0xFF), 0xFF000000 | (array2[0] & 0xFF) << 16 | (array2[1] & 0xFF) << 8 | (array2[2] & 0xFF) };
            final byte[] array4 = new byte[n5];
            this.rfb.readFully(array4);
            final byte[] array5 = new byte[n5];
            this.rfb.readFully(array5);
            int n6 = 0;
            for (int i = 0; i < n3; ++i) {
                int j;
                for (j = 0; j < n2 / 8; ++j) {
                    final byte b = array4[i * n4 + j];
                    final byte b2 = array5[i * n4 + j];
                    for (int k = 7; k >= 0; --k) {
                        int n7;
                        if ((b2 >> k & 0x1) != 0x0) {
                            n7 = array3[b >> k & 0x1];
                        }
                        else {
                            n7 = 0;
                        }
                        array[n6++] = n7;
                    }
                }
                for (int l = 7; l >= 8 - n2 % 8; --l) {
                    int n8;
                    if ((array5[i * n4 + j] >> l & 0x1) != 0x0) {
                        n8 = array3[array4[i * n4 + j] >> l & 0x1];
                    }
                    else {
                        n8 = 0;
                    }
                    array[n6++] = n8;
                }
            }
        }
        else {
            final byte[] array6 = new byte[n2 * n3 * this.bytesPixel];
            this.rfb.readFully(array6);
            final byte[] array7 = new byte[n5];
            this.rfb.readFully(array7);
            int n9 = 0;
            for (int n10 = 0; n10 < n3; ++n10) {
                int n11;
                for (n11 = 0; n11 < n2 / 8; ++n11) {
                    final byte b3 = array7[n10 * n4 + n11];
                    for (int n12 = 7; n12 >= 0; --n12) {
                        int rgb;
                        if ((b3 >> n12 & 0x1) != 0x0) {
                            if (this.bytesPixel == 1) {
                                rgb = this.cm8.getRGB(array6[n9]);
                            }
                            else {
                                rgb = (0xFF000000 | (array6[n9 * 4 + 2] & 0xFF) << 16 | (array6[n9 * 4 + 1] & 0xFF) << 8 | (array6[n9 * 4] & 0xFF));
                            }
                        }
                        else {
                            rgb = 0;
                        }
                        array[n9++] = rgb;
                    }
                }
                for (int n13 = 7; n13 >= 8 - n2 % 8; --n13) {
                    int rgb2;
                    if ((array7[n10 * n4 + n11] >> n13 & 0x1) != 0x0) {
                        if (this.bytesPixel == 1) {
                            rgb2 = this.cm8.getRGB(array6[n9]);
                        }
                        else {
                            rgb2 = (0xFF000000 | (array6[n9 * 4 + 2] & 0xFF) << 16 | (array6[n9 * 4 + 1] & 0xFF) << 8 | (array6[n9 * 4] & 0xFF));
                        }
                    }
                    else {
                        rgb2 = 0;
                    }
                    array[n9++] = rgb2;
                }
            }
        }
        return new MemoryImageSource(n2, n3, array, 0, n2);
    }
    
    synchronized void createSoftCursor() {
        if (this.softCursorSource == null) {
            return;
        }
        int scaleCursor = this.viewer.options.scaleCursor;
        if (scaleCursor == 0 || !this.inputEnabled) {
            scaleCursor = 100;
        }
        final int n = this.cursorX - this.hotX;
        final int n2 = this.cursorY - this.hotY;
        final int cursorWidth = this.cursorWidth;
        final int cursorHeight = this.cursorHeight;
        this.cursorWidth = (this.origCursorWidth * scaleCursor + 50) / 100;
        this.cursorHeight = (this.origCursorHeight * scaleCursor + 50) / 100;
        this.hotX = (this.origHotX * scaleCursor + 50) / 100;
        this.hotY = (this.origHotY * scaleCursor + 50) / 100;
        this.softCursor = Toolkit.getDefaultToolkit().createImage(this.softCursorSource);
        if (scaleCursor != 100) {
            this.softCursor = this.softCursor.getScaledInstance(this.cursorWidth, this.cursorHeight, 4);
        }
        if (this.showSoftCursor) {
            this.repaint(this.viewer.deferCursorUpdates, Math.min(n, this.cursorX - this.hotX), Math.min(n2, this.cursorY - this.hotY), Math.max(cursorWidth, this.cursorWidth), Math.max(cursorHeight, this.cursorHeight));
        }
    }
    
    synchronized void softCursorMove(final int cursorX, final int cursorY) {
        final int cursorX2 = this.cursorX;
        final int cursorY2 = this.cursorY;
        this.cursorX = cursorX;
        this.cursorY = cursorY;
        if (this.showSoftCursor) {
            this.repaint(this.viewer.deferCursorUpdates, cursorX2 - this.hotX, cursorY2 - this.hotY, this.cursorWidth, this.cursorHeight);
            this.repaint(this.viewer.deferCursorUpdates, this.cursorX - this.hotX, this.cursorY - this.hotY, this.cursorWidth, this.cursorHeight);
        }
    }
    
    synchronized void softCursorFree() {
        if (this.showSoftCursor) {
            this.showSoftCursor = false;
            this.softCursor = null;
            this.softCursorSource = null;
            this.repaint(this.viewer.deferCursorUpdates, this.cursorX - this.hotX, this.cursorY - this.hotY, this.cursorWidth, this.cursorHeight);
        }
    }
}
