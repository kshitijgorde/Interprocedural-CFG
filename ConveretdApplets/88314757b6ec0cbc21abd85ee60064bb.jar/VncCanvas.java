import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.awt.Toolkit;
import java.awt.Insets;
import java.lang.reflect.Method;
import java.awt.image.ImageProducer;
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

final class VncCanvas extends Canvas implements KeyListener, MouseListener, MouseMotionListener
{
    private static final long serialVersionUID = 530846158295280027L;
    final VncViewer viewer;
    final RfbProto rfb;
    final ColorModel cm8;
    final ColorModel cm24;
    Color[] colors;
    int bytesPixel;
    Image memImage;
    Graphics memGraphics;
    Image rawPixelsImage;
    MemoryImageSource pixelsSource;
    byte[] pixels8;
    int[] pixels24;
    byte[] zlibBuf;
    int zlibBufLen;
    Inflater zlibInflater;
    static final int tightZlibBufferSize = 512;
    final Inflater[] tightInflaters;
    Rectangle jpegRect;
    boolean inputEnabled;
    final Object eventLock;
    final boolean verbose = false;
    private Color hextile_bg;
    private Color hextile_fg;
    boolean showSoftCursor;
    int[] softCursorPixels;
    MemoryImageSource softCursorSource;
    Image softCursor;
    int cursorX;
    int cursorY;
    int cursorWidth;
    int cursorHeight;
    int hotX;
    int hotY;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$java$applet$Applet;
    
    VncCanvas(final VncViewer v) throws IOException {
        this.zlibBufLen = 0;
        this.eventLock = new Object();
        this.showSoftCursor = false;
        this.cursorX = 0;
        this.cursorY = 0;
        this.viewer = v;
        this.rfb = this.viewer.rfb;
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
    
    public Dimension getPreferredSize() {
        return new Dimension(this.rfb.framebufferWidth, this.rfb.framebufferHeight);
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(this.rfb.framebufferWidth, this.rfb.framebufferHeight);
    }
    
    public Dimension getMaximumSize() {
        return new Dimension(this.rfb.framebufferWidth, this.rfb.framebufferHeight);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void paint(final Graphics g) {
        synchronized (this.memImage) {
            g.drawImage(this.memImage, 0, 0, null);
        }
        if (this.showSoftCursor) {
            final int x0 = this.cursorX - this.hotX;
            final int y0 = this.cursorY - this.hotY;
            final Rectangle r = new Rectangle(x0, y0, this.cursorWidth, this.cursorHeight);
            if (r.intersects(g.getClipBounds())) {
                g.drawImage(this.softCursor, x0, y0, null);
            }
        }
    }
    
    public boolean imageUpdate(final Image img, final int infoflags, final int x, final int y, final int width, final int height) {
        if ((infoflags & 0xA0) == 0x0) {
            return true;
        }
        if ((infoflags & 0x20) != 0x0 && this.jpegRect != null) {
            synchronized (this.jpegRect) {
                synchronized (this.memImage) {
                    this.memGraphics.drawImage(img, this.jpegRect.x, this.jpegRect.y, null);
                }
                this.scheduleRepaint(this.jpegRect.x, this.jpegRect.y, this.jpegRect.width, this.jpegRect.height);
                this.jpegRect.notify();
            }
        }
        return false;
    }
    
    public synchronized void enableInput(final boolean enable) {
        if (enable && !this.inputEnabled) {
            this.inputEnabled = true;
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            if (this.viewer.showControls) {
                this.viewer.buttonPanel.enableRemoteAccessControls(true);
            }
        }
        else if (!enable && this.inputEnabled) {
            this.inputEnabled = false;
            this.removeMouseListener(this);
            this.removeMouseMotionListener(this);
            if (this.viewer.showControls) {
                this.viewer.buttonPanel.enableRemoteAccessControls(false);
            }
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
        final int fbWidth = this.rfb.framebufferWidth;
        final int fbHeight = this.rfb.framebufferHeight;
        if (this.memImage == null) {
            this.memImage = this.viewer.createImage(fbWidth, fbHeight);
            this.memGraphics = this.memImage.getGraphics();
        }
        else if (this.memImage.getWidth(null) != fbWidth || this.memImage.getHeight(null) != fbHeight) {
            synchronized (this.memImage) {
                if (this.memGraphics != null) {
                    this.memGraphics.dispose();
                }
                this.memImage = this.viewer.createImage(fbWidth, fbHeight);
                this.memGraphics = this.memImage.getGraphics();
            }
        }
        if (this.bytesPixel == 1) {
            this.pixels24 = null;
            this.pixels8 = new byte[fbWidth * fbHeight];
            this.pixelsSource = new MemoryImageSource(fbWidth, fbHeight, this.cm8, this.pixels8, 0, fbWidth);
        }
        else {
            this.pixels8 = null;
            this.pixels24 = new int[fbWidth * fbHeight];
            this.pixelsSource = new MemoryImageSource(fbWidth, fbHeight, this.cm24, this.pixels24, 0, fbWidth);
        }
        this.pixelsSource.setAnimated(true);
        this.rawPixelsImage = this.createImage(this.pixelsSource);
        if (this.viewer.inSeparateFrame) {
            if (this.viewer.desktopScrollPane != null) {
                this.resizeDesktopFrame();
            }
        }
        else if (this.viewer.inAnApplet) {
            this.resizeApplet();
        }
        else {
            this.setSize(fbWidth, fbHeight);
        }
        this.viewer.moveFocusToDesktop();
    }
    
    void resizeApplet() {
        this.setSize(this.rfb.framebufferWidth, this.rfb.framebufferHeight);
        String id = this.viewer.getParameter("appletId");
        if (id != null) {
            id = "document.all('" + id + "')";
            try {
                final Class jsclass = Class.forName("netscape.javascript.JSObject");
                final Method eval = jsclass.getMethod("eval", (VncCanvas.class$java$lang$String == null) ? (VncCanvas.class$java$lang$String = class$("java.lang.String")) : VncCanvas.class$java$lang$String);
                final Method getWindow = jsclass.getMethod("getWindow", (VncCanvas.class$java$applet$Applet == null) ? (VncCanvas.class$java$applet$Applet = class$("java.applet.Applet")) : VncCanvas.class$java$applet$Applet);
                final Object jsobject = getWindow.invoke(null, this.viewer);
                eval.invoke(jsobject, id + ".width=" + this.rfb.framebufferWidth);
                eval.invoke(jsobject, id + ".height=" + this.rfb.framebufferHeight);
            }
            catch (Exception e) {
                System.err.println("resizeApplet() failed...");
                System.err.flush();
                e.printStackTrace();
            }
        }
        else {
            this.viewer.fireResizeEvent(this.rfb.framebufferWidth, this.rfb.framebufferHeight);
        }
    }
    
    void resizeDesktopFrame() {
        this.setSize(this.rfb.framebufferWidth, this.rfb.framebufferHeight);
        final Insets insets = this.viewer.desktopScrollPane.getInsets();
        this.viewer.desktopScrollPane.setSize(this.rfb.framebufferWidth + 2 * Math.min(insets.left, insets.right), this.rfb.framebufferHeight + 2 * Math.min(insets.top, insets.bottom));
        this.viewer.vncFrame.pack();
        final Dimension screenSize = this.viewer.vncFrame.getToolkit().getScreenSize();
        final Dimension newSize;
        final Dimension frameSize = newSize = this.viewer.vncFrame.getSize();
        final Dimension dimension = screenSize;
        dimension.height -= 30;
        final Dimension dimension2 = screenSize;
        dimension2.width -= 30;
        boolean needToResizeFrame = false;
        if (frameSize.height > screenSize.height) {
            newSize.height = screenSize.height;
            needToResizeFrame = true;
        }
        if (frameSize.width > screenSize.width) {
            newSize.width = screenSize.width;
            needToResizeFrame = true;
        }
        if (needToResizeFrame) {
            this.viewer.vncFrame.setSize(newSize);
        }
        this.viewer.desktopScrollPane.doLayout();
    }
    
    public void processNormalProtocol() throws Exception {
        this.viewer.checkRecordingStatus();
        this.rfb.writeFramebufferUpdateRequest(0, 0, this.rfb.framebufferWidth, this.rfb.framebufferHeight, false);
        while (true) {
            final int msgType = this.rfb.readServerMessageType();
            switch (msgType) {
                case 0: {
                    this.rfb.readFramebufferUpdate();
                    boolean cursorPosReceived = false;
                    for (int i = 0; i < this.rfb.updateNRects; ++i) {
                        this.rfb.readFramebufferUpdateRectHdr();
                        final int rx = this.rfb.updateRectX;
                        final int ry = this.rfb.updateRectY;
                        final int rw = this.rfb.updateRectW;
                        final int rh = this.rfb.updateRectH;
                        if (this.rfb.updateRectEncoding == -224) {
                            break;
                        }
                        if (this.rfb.updateRectEncoding == -223) {
                            this.rfb.setFramebufferSize(rw, rh);
                            this.updateFramebufferSize();
                            break;
                        }
                        if (this.rfb.updateRectEncoding == -240 || this.rfb.updateRectEncoding == -239) {
                            this.handleCursorShapeUpdate(this.rfb.updateRectEncoding, rx, ry, rw, rh);
                        }
                        else if (this.rfb.updateRectEncoding == -232) {
                            this.softCursorMove(rx, ry);
                            cursorPosReceived = true;
                        }
                        else {
                            switch (this.rfb.updateRectEncoding) {
                                case 0: {
                                    this.handleRawRect(rx, ry, rw, rh);
                                    break;
                                }
                                case 1: {
                                    this.handleCopyRect(rx, ry, rw, rh);
                                    break;
                                }
                                case 2: {
                                    this.handleRRERect(rx, ry, rw, rh);
                                    break;
                                }
                                case 4: {
                                    this.handleCoRRERect(rx, ry, rw, rh);
                                    break;
                                }
                                case 5: {
                                    this.handleHextileRect(rx, ry, rw, rh);
                                    break;
                                }
                                case 6: {
                                    this.handleZlibRect(rx, ry, rw, rh);
                                    break;
                                }
                                case 7: {
                                    this.handleTightRect(rx, ry, rw, rh);
                                    break;
                                }
                                default: {
                                    throw new Exception("Unknown RFB rectangle encoding " + this.rfb.updateRectEncoding);
                                }
                            }
                        }
                    }
                    boolean fullUpdateNeeded = false;
                    if (this.viewer.checkRecordingStatus()) {
                        fullUpdateNeeded = true;
                    }
                    if (this.viewer.deferUpdateRequests > 0 && this.rfb.is.available() == 0 && !cursorPosReceived) {
                        synchronized (this.eventLock) {
                            try {
                                this.eventLock.wait(this.viewer.deferUpdateRequests);
                            }
                            catch (InterruptedException e) {
                                throw new Exception("Interrupted while waiting for updates");
                            }
                        }
                    }
                    if (this.viewer.options.eightBitColors != (this.bytesPixel == 1)) {
                        this.setPixelFormat();
                        fullUpdateNeeded = true;
                    }
                    this.rfb.writeFramebufferUpdateRequest(0, 0, this.rfb.framebufferWidth, this.rfb.framebufferHeight, !fullUpdateNeeded);
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
                    final String s = this.rfb.readServerCutText();
                    this.viewer.clipboard.setCutText(s);
                    continue;
                }
                default: {
                    throw new Exception("Unknown RFB message type " + msgType);
                }
            }
        }
    }
    
    void handleRawRect(final int x, final int y, final int w, final int h) throws IOException {
        this.handleRawRect(x, y, w, h, true);
    }
    
    void handleRawRect(final int x, final int y, final int w, final int h, final boolean paint) throws IOException {
        if (this.bytesPixel == 1) {
            for (int dy = y; dy < y + h; ++dy) {
                this.rfb.is.readFully(this.pixels8, dy * this.rfb.framebufferWidth + x, w);
                if (this.rfb.rec != null) {
                    this.rfb.rec.write(this.pixels8, dy * this.rfb.framebufferWidth + x, w);
                }
            }
        }
        else {
            final byte[] buf = new byte[w * 4];
            for (int dy2 = y; dy2 < y + h; ++dy2) {
                this.rfb.is.readFully(buf);
                if (this.rfb.rec != null) {
                    this.rfb.rec.write(buf);
                }
                final int offset = dy2 * this.rfb.framebufferWidth + x;
                for (int i = 0; i < w; ++i) {
                    this.pixels24[offset + i] = ((buf[i * 4 + 2] & 0xFF) << 16 | (buf[i * 4 + 1] & 0xFF) << 8 | (buf[i * 4] & 0xFF));
                }
            }
        }
        this.handleUpdatedPixels(x, y, w, h);
        if (paint) {
            this.scheduleRepaint(x, y, w, h);
        }
    }
    
    void handleCopyRect(final int x, final int y, final int w, final int h) throws IOException {
        this.rfb.readCopyRect();
        synchronized (this.memImage) {
            this.memGraphics.copyArea(this.rfb.copyRectSrcX, this.rfb.copyRectSrcY, w, h, x - this.rfb.copyRectSrcX, y - this.rfb.copyRectSrcY);
        }
        this.scheduleRepaint(x, y, w, h);
    }
    
    void handleRRERect(final int x, final int y, final int w, final int h) throws IOException {
        final int nSubrects = this.rfb.is.readInt();
        final byte[] bg_buf = new byte[this.bytesPixel];
        this.rfb.is.readFully(bg_buf);
        Color pixel;
        if (this.bytesPixel == 1) {
            pixel = this.colors[bg_buf[0] & 0xFF];
        }
        else {
            pixel = new Color(bg_buf[2] & 0xFF, bg_buf[1] & 0xFF, bg_buf[0] & 0xFF);
        }
        synchronized (this.memImage) {
            this.memGraphics.setColor(pixel);
            this.memGraphics.fillRect(x, y, w, h);
            final byte[] buf = new byte[nSubrects * (this.bytesPixel + 8)];
            this.rfb.is.readFully(buf);
            final DataInputStream ds = new DataInputStream(new ByteArrayInputStream(buf));
            if (this.rfb.rec != null) {
                this.rfb.rec.writeIntBE(nSubrects);
                this.rfb.rec.write(bg_buf);
                this.rfb.rec.write(buf);
            }
            for (int j = 0; j < nSubrects; ++j) {
                if (this.bytesPixel == 1) {
                    pixel = this.colors[ds.readUnsignedByte()];
                }
                else {
                    ds.skip(4L);
                    pixel = new Color(buf[j * 12 + 2] & 0xFF, buf[j * 12 + 1] & 0xFF, buf[j * 12] & 0xFF);
                }
                final int sx = x + ds.readUnsignedShort();
                final int sy = y + ds.readUnsignedShort();
                final int sw = ds.readUnsignedShort();
                final int sh = ds.readUnsignedShort();
                this.memGraphics.setColor(pixel);
                this.memGraphics.fillRect(sx, sy, sw, sh);
            }
        }
        this.scheduleRepaint(x, y, w, h);
    }
    
    void handleCoRRERect(final int x, final int y, final int w, final int h) throws IOException {
        final int nSubrects = this.rfb.is.readInt();
        final byte[] bg_buf = new byte[this.bytesPixel];
        this.rfb.is.readFully(bg_buf);
        Color pixel;
        if (this.bytesPixel == 1) {
            pixel = this.colors[bg_buf[0] & 0xFF];
        }
        else {
            pixel = new Color(bg_buf[2] & 0xFF, bg_buf[1] & 0xFF, bg_buf[0] & 0xFF);
        }
        synchronized (this.memImage) {
            this.memGraphics.setColor(pixel);
            this.memGraphics.fillRect(x, y, w, h);
            final byte[] buf = new byte[nSubrects * (this.bytesPixel + 4)];
            this.rfb.is.readFully(buf);
            if (this.rfb.rec != null) {
                this.rfb.rec.writeIntBE(nSubrects);
                this.rfb.rec.write(bg_buf);
                this.rfb.rec.write(buf);
            }
            int i = 0;
            for (int j = 0; j < nSubrects; ++j) {
                if (this.bytesPixel == 1) {
                    pixel = this.colors[buf[i++] & 0xFF];
                }
                else {
                    pixel = new Color(buf[i + 2] & 0xFF, buf[i + 1] & 0xFF, buf[i] & 0xFF);
                    i += 4;
                }
                final int sx = x + (buf[i++] & 0xFF);
                final int sy = y + (buf[i++] & 0xFF);
                final int sw = buf[i++] & 0xFF;
                final int sh = buf[i++] & 0xFF;
                this.memGraphics.setColor(pixel);
                this.memGraphics.fillRect(sx, sy, sw, sh);
            }
        }
        this.scheduleRepaint(x, y, w, h);
    }
    
    void handleHextileRect(final int x, final int y, final int w, final int h) throws IOException {
        this.hextile_bg = Color.black;
        this.hextile_fg = Color.black;
        for (int ty = y; ty < y + h; ty += 16) {
            int th = 16;
            if (y + h - ty < 16) {
                th = y + h - ty;
            }
            for (int tx = x; tx < x + w; tx += 16) {
                int tw = 16;
                if (x + w - tx < 16) {
                    tw = x + w - tx;
                }
                this.handleHextileSubrect(tx, ty, tw, th);
            }
            this.scheduleRepaint(x, y, w, h);
        }
    }
    
    void handleHextileSubrect(final int tx, final int ty, final int tw, final int th) throws IOException {
        final int subencoding = this.rfb.is.readUnsignedByte();
        if (this.rfb.rec != null) {
            this.rfb.rec.writeByte(subencoding);
        }
        final int n = subencoding;
        this.rfb.getClass();
        if ((n & 0x1) != 0x0) {
            this.handleRawRect(tx, ty, tw, th, false);
            return;
        }
        final byte[] cbuf = new byte[this.bytesPixel];
        final int n2 = subencoding;
        this.rfb.getClass();
        if ((n2 & 0x2) != 0x0) {
            this.rfb.is.readFully(cbuf);
            if (this.bytesPixel == 1) {
                this.hextile_bg = this.colors[cbuf[0] & 0xFF];
            }
            else {
                this.hextile_bg = new Color((cbuf[2] & 0xFF) << 16 | (cbuf[1] & 0xFF) << 8 | (cbuf[0] & 0xFF));
            }
            if (this.rfb.rec != null) {
                this.rfb.rec.write(cbuf);
            }
        }
        synchronized (this.memImage) {
            this.memGraphics.setColor(this.hextile_bg);
            this.memGraphics.fillRect(tx, ty, tw, th);
            final int n3 = subencoding;
            this.rfb.getClass();
            if ((n3 & 0x4) != 0x0) {
                this.rfb.is.readFully(cbuf);
                if (this.bytesPixel == 1) {
                    this.hextile_fg = this.colors[cbuf[0] & 0xFF];
                }
                else {
                    this.hextile_fg = new Color((cbuf[2] & 0xFF) << 16 | (cbuf[1] & 0xFF) << 8 | (cbuf[0] & 0xFF));
                }
                if (this.rfb.rec != null) {
                    this.rfb.rec.write(cbuf);
                }
            }
            final int n4 = subencoding;
            this.rfb.getClass();
            if ((n4 & 0x8) == 0x0) {
                return;
            }
            final int nSubrects = this.rfb.is.readUnsignedByte();
            int bufsize = nSubrects * 2;
            final int n5 = subencoding;
            this.rfb.getClass();
            if ((n5 & 0x10) != 0x0) {
                bufsize += nSubrects * this.bytesPixel;
            }
            final byte[] buf = new byte[bufsize];
            this.rfb.is.readFully(buf);
            if (this.rfb.rec != null) {
                this.rfb.rec.writeByte(nSubrects);
                this.rfb.rec.write(buf);
            }
            int i = 0;
            final int n6 = subencoding;
            this.rfb.getClass();
            if ((n6 & 0x10) == 0x0) {
                this.memGraphics.setColor(this.hextile_fg);
                for (int j = 0; j < nSubrects; ++j) {
                    final int b1 = buf[i++] & 0xFF;
                    final int b2 = buf[i++] & 0xFF;
                    final int sx = tx + (b1 >> 4);
                    final int sy = ty + (b1 & 0xF);
                    final int sw = (b2 >> 4) + 1;
                    final int sh = (b2 & 0xF) + 1;
                    this.memGraphics.fillRect(sx, sy, sw, sh);
                }
            }
            else if (this.bytesPixel == 1) {
                for (int j = 0; j < nSubrects; ++j) {
                    this.hextile_fg = this.colors[buf[i++] & 0xFF];
                    final int b1 = buf[i++] & 0xFF;
                    final int b2 = buf[i++] & 0xFF;
                    final int sx = tx + (b1 >> 4);
                    final int sy = ty + (b1 & 0xF);
                    final int sw = (b2 >> 4) + 1;
                    final int sh = (b2 & 0xF) + 1;
                    this.memGraphics.setColor(this.hextile_fg);
                    this.memGraphics.fillRect(sx, sy, sw, sh);
                }
            }
            else {
                for (int j = 0; j < nSubrects; ++j) {
                    this.hextile_fg = new Color(buf[i + 2] & 0xFF, buf[i + 1] & 0xFF, buf[i] & 0xFF);
                    i += 4;
                    final int b1 = buf[i++] & 0xFF;
                    final int b2 = buf[i++] & 0xFF;
                    final int sx = tx + (b1 >> 4);
                    final int sy = ty + (b1 & 0xF);
                    final int sw = (b2 >> 4) + 1;
                    final int sh = (b2 & 0xF) + 1;
                    this.memGraphics.setColor(this.hextile_fg);
                    this.memGraphics.fillRect(sx, sy, sw, sh);
                }
            }
        }
    }
    
    void handleZlibRect(final int x, final int y, final int w, final int h) throws Exception {
        final int nBytes = this.rfb.is.readInt();
        if (this.zlibBuf == null || this.zlibBufLen < nBytes) {
            this.zlibBufLen = nBytes * 2;
            this.zlibBuf = new byte[this.zlibBufLen];
        }
        this.rfb.is.readFully(this.zlibBuf, 0, nBytes);
        if (this.rfb.rec != null && this.rfb.recordFromBeginning) {
            this.rfb.rec.writeIntBE(nBytes);
            this.rfb.rec.write(this.zlibBuf, 0, nBytes);
        }
        if (this.zlibInflater == null) {
            this.zlibInflater = new Inflater();
        }
        this.zlibInflater.setInput(this.zlibBuf, 0, nBytes);
        if (this.bytesPixel == 1) {
            for (int dy = y; dy < y + h; ++dy) {
                this.zlibInflater.inflate(this.pixels8, dy * this.rfb.framebufferWidth + x, w);
                if (this.rfb.rec != null && !this.rfb.recordFromBeginning) {
                    this.rfb.rec.write(this.pixels8, dy * this.rfb.framebufferWidth + x, w);
                }
            }
        }
        else {
            final byte[] buf = new byte[w * 4];
            for (int dy2 = y; dy2 < y + h; ++dy2) {
                this.zlibInflater.inflate(buf);
                final int offset = dy2 * this.rfb.framebufferWidth + x;
                for (int i = 0; i < w; ++i) {
                    this.pixels24[offset + i] = ((buf[i * 4 + 2] & 0xFF) << 16 | (buf[i * 4 + 1] & 0xFF) << 8 | (buf[i * 4] & 0xFF));
                }
                if (this.rfb.rec != null && !this.rfb.recordFromBeginning) {
                    this.rfb.rec.write(buf);
                }
            }
        }
        this.handleUpdatedPixels(x, y, w, h);
        this.scheduleRepaint(x, y, w, h);
    }
    
    void handleTightRect(final int x, final int y, final int w, final int h) throws Exception {
        int comp_ctl = this.rfb.is.readUnsignedByte();
        if (this.rfb.rec != null) {
            if (this.rfb.recordFromBeginning || comp_ctl == 128 || comp_ctl == 144) {
                this.rfb.rec.writeByte(comp_ctl);
            }
            else {
                this.rfb.rec.writeByte(comp_ctl | 0xF);
            }
        }
        for (int stream_id = 0; stream_id < 4; ++stream_id) {
            if ((comp_ctl & 0x1) != 0x0 && this.tightInflaters[stream_id] != null) {
                this.tightInflaters[stream_id] = null;
            }
            comp_ctl >>= 1;
        }
        if (comp_ctl > 9) {
            throw new Exception("Incorrect tight subencoding: " + comp_ctl);
        }
        if (comp_ctl == 8) {
            synchronized (this.memImage) {
                if (this.bytesPixel == 1) {
                    final int idx = this.rfb.is.readUnsignedByte();
                    this.memGraphics.setColor(this.colors[idx]);
                    if (this.rfb.rec != null) {
                        this.rfb.rec.writeByte(idx);
                    }
                }
                else {
                    final byte[] buf = new byte[3];
                    this.rfb.is.readFully(buf);
                    if (this.rfb.rec != null) {
                        this.rfb.rec.write(buf);
                    }
                    final Color bg = new Color(0xFF000000 | (buf[0] & 0xFF) << 16 | (buf[1] & 0xFF) << 8 | (buf[2] & 0xFF));
                    this.memGraphics.setColor(bg);
                }
                this.memGraphics.fillRect(x, y, w, h);
            }
            this.scheduleRepaint(x, y, w, h);
            return;
        }
        if (comp_ctl == 9) {
            final byte[] jpegData = new byte[this.rfb.readCompactLen()];
            this.rfb.is.readFully(jpegData);
            if (this.rfb.rec != null) {
                if (!this.rfb.recordFromBeginning) {
                    this.rfb.recordCompactLen(jpegData.length);
                }
                this.rfb.rec.write(jpegData);
            }
            final Image jpegImage = Toolkit.getDefaultToolkit().createImage(jpegData);
            synchronized (this.jpegRect = new Rectangle(x, y, w, h)) {
                Toolkit.getDefaultToolkit().prepareImage(jpegImage, -1, -1, this);
                try {
                    this.jpegRect.wait(3000L);
                }
                catch (InterruptedException e) {
                    throw new Exception("Interrupted while decoding JPEG image");
                }
            }
            this.jpegRect = null;
            return;
        }
        int numColors = 0;
        int rowSize = w;
        final byte[] palette8 = new byte[2];
        final int[] palette9 = new int[256];
        boolean useGradient = false;
        if ((comp_ctl & 0x4) != 0x0) {
            final int filter_id = this.rfb.is.readUnsignedByte();
            if (this.rfb.rec != null) {
                this.rfb.rec.writeByte(filter_id);
            }
            if (filter_id == 1) {
                numColors = this.rfb.is.readUnsignedByte() + 1;
                if (this.rfb.rec != null) {
                    this.rfb.rec.writeByte(numColors - 1);
                }
                if (this.bytesPixel == 1) {
                    if (numColors != 2) {
                        throw new Exception("Incorrect tight palette size: " + numColors);
                    }
                    this.rfb.is.readFully(palette8);
                    if (this.rfb.rec != null) {
                        this.rfb.rec.write(palette8);
                    }
                }
                else {
                    final byte[] buf2 = new byte[numColors * 3];
                    this.rfb.is.readFully(buf2);
                    if (this.rfb.rec != null) {
                        this.rfb.rec.write(buf2);
                    }
                    for (int i = 0; i < numColors; ++i) {
                        palette9[i] = ((buf2[i * 3] & 0xFF) << 16 | (buf2[i * 3 + 1] & 0xFF) << 8 | (buf2[i * 3 + 2] & 0xFF));
                    }
                }
                if (numColors == 2) {
                    rowSize = (w + 7) / 8;
                }
            }
            else if (filter_id == 2) {
                useGradient = true;
            }
            else if (filter_id != 0) {
                throw new Exception("Incorrect tight filter id: " + filter_id);
            }
        }
        if (numColors == 0 && this.bytesPixel == 4) {
            rowSize *= 3;
        }
        final int dataSize = h * rowSize;
        if (dataSize < 12) {
            if (numColors != 0) {
                final byte[] indexedData = new byte[dataSize];
                this.rfb.is.readFully(indexedData);
                if (this.rfb.rec != null) {
                    this.rfb.rec.write(indexedData);
                }
                if (numColors == 2) {
                    if (this.bytesPixel == 1) {
                        this.decodeMonoData(x, y, w, h, indexedData, palette8);
                    }
                    else {
                        this.decodeMonoData(x, y, w, h, indexedData, palette9);
                    }
                }
                else {
                    int i = 0;
                    for (int dy = y; dy < y + h; ++dy) {
                        for (int dx = x; dx < x + w; ++dx) {
                            this.pixels24[dy * this.rfb.framebufferWidth + dx] = palette9[indexedData[i++] & 0xFF];
                        }
                    }
                }
            }
            else if (useGradient) {
                final byte[] buf2 = new byte[w * h * 3];
                this.rfb.is.readFully(buf2);
                if (this.rfb.rec != null) {
                    this.rfb.rec.write(buf2);
                }
                this.decodeGradientData(x, y, w, h, buf2);
            }
            else if (this.bytesPixel == 1) {
                for (int dy2 = y; dy2 < y + h; ++dy2) {
                    this.rfb.is.readFully(this.pixels8, dy2 * this.rfb.framebufferWidth + x, w);
                    if (this.rfb.rec != null) {
                        this.rfb.rec.write(this.pixels8, dy2 * this.rfb.framebufferWidth + x, w);
                    }
                }
            }
            else {
                final byte[] buf2 = new byte[w * 3];
                for (int dy3 = y; dy3 < y + h; ++dy3) {
                    this.rfb.is.readFully(buf2);
                    if (this.rfb.rec != null) {
                        this.rfb.rec.write(buf2);
                    }
                    final int offset = dy3 * this.rfb.framebufferWidth + x;
                    for (int i = 0; i < w; ++i) {
                        this.pixels24[offset + i] = ((buf2[i * 3] & 0xFF) << 16 | (buf2[i * 3 + 1] & 0xFF) << 8 | (buf2[i * 3 + 2] & 0xFF));
                    }
                }
            }
        }
        else {
            final int zlibDataLen = this.rfb.readCompactLen();
            final byte[] zlibData = new byte[zlibDataLen];
            this.rfb.is.readFully(zlibData);
            if (this.rfb.rec != null && this.rfb.recordFromBeginning) {
                this.rfb.rec.write(zlibData);
            }
            final int stream_id2 = comp_ctl & 0x3;
            if (this.tightInflaters[stream_id2] == null) {
                this.tightInflaters[stream_id2] = new Inflater();
            }
            final Inflater myInflater = this.tightInflaters[stream_id2];
            myInflater.setInput(zlibData);
            final byte[] buf3 = new byte[dataSize];
            myInflater.inflate(buf3);
            if (this.rfb.rec != null && !this.rfb.recordFromBeginning) {
                this.rfb.recordCompressedData(buf3);
            }
            if (numColors != 0) {
                if (numColors == 2) {
                    if (this.bytesPixel == 1) {
                        this.decodeMonoData(x, y, w, h, buf3, palette8);
                    }
                    else {
                        this.decodeMonoData(x, y, w, h, buf3, palette9);
                    }
                }
                else {
                    int j = 0;
                    for (int dy4 = y; dy4 < y + h; ++dy4) {
                        for (int dx2 = x; dx2 < x + w; ++dx2) {
                            this.pixels24[dy4 * this.rfb.framebufferWidth + dx2] = palette9[buf3[j++] & 0xFF];
                        }
                    }
                }
            }
            else if (useGradient) {
                this.decodeGradientData(x, y, w, h, buf3);
            }
            else if (this.bytesPixel == 1) {
                int destOffset = y * this.rfb.framebufferWidth + x;
                for (int dy4 = 0; dy4 < h; ++dy4) {
                    System.arraycopy(buf3, dy4 * w, this.pixels8, destOffset, w);
                    destOffset += this.rfb.framebufferWidth;
                }
            }
            else {
                int srcOffset = 0;
                for (int dy5 = 0; dy5 < h; ++dy5) {
                    myInflater.inflate(buf3);
                    final int destOffset2 = (y + dy5) * this.rfb.framebufferWidth + x;
                    for (int k = 0; k < w; ++k) {
                        this.pixels24[destOffset2 + k] = ((buf3[srcOffset] & 0xFF) << 16 | (buf3[srcOffset + 1] & 0xFF) << 8 | (buf3[srcOffset + 2] & 0xFF));
                        srcOffset += 3;
                    }
                }
            }
        }
        this.handleUpdatedPixels(x, y, w, h);
        this.scheduleRepaint(x, y, w, h);
    }
    
    void decodeMonoData(final int x, final int y, final int w, final int h, final byte[] src, final byte[] palette) {
        int i = y * this.rfb.framebufferWidth + x;
        final int rowBytes = (w + 7) / 8;
        for (int dy = 0; dy < h; ++dy) {
            int dx;
            for (dx = 0; dx < w / 8; ++dx) {
                final byte b = src[dy * rowBytes + dx];
                for (int n = 7; n >= 0; --n) {
                    this.pixels8[i++] = palette[b >> n & 0x1];
                }
            }
            for (int n = 7; n >= 8 - w % 8; --n) {
                this.pixels8[i++] = palette[src[dy * rowBytes + dx] >> n & 0x1];
            }
            i += this.rfb.framebufferWidth - w;
        }
    }
    
    void decodeMonoData(final int x, final int y, final int w, final int h, final byte[] src, final int[] palette) {
        int i = y * this.rfb.framebufferWidth + x;
        final int rowBytes = (w + 7) / 8;
        for (int dy = 0; dy < h; ++dy) {
            int dx;
            for (dx = 0; dx < w / 8; ++dx) {
                final byte b = src[dy * rowBytes + dx];
                for (int n = 7; n >= 0; --n) {
                    this.pixels24[i++] = palette[b >> n & 0x1];
                }
            }
            for (int n = 7; n >= 8 - w % 8; --n) {
                this.pixels24[i++] = palette[src[dy * rowBytes + dx] >> n & 0x1];
            }
            i += this.rfb.framebufferWidth - w;
        }
    }
    
    void decodeGradientData(final int x, final int y, final int w, final int h, final byte[] buf) {
        final byte[] prevRow = new byte[w * 3];
        final byte[] thisRow = new byte[w * 3];
        final byte[] pix = new byte[3];
        final int[] est = new int[3];
        int offset = y * this.rfb.framebufferWidth + x;
        for (int dy = 0; dy < h; ++dy) {
            for (int c = 0; c < 3; ++c) {
                thisRow[c] = (pix[c] = (byte)(prevRow[c] + buf[dy * w * 3 + c]));
            }
            this.pixels24[offset++] = ((pix[0] & 0xFF) << 16 | (pix[1] & 0xFF) << 8 | (pix[2] & 0xFF));
            for (int dx = 1; dx < w; ++dx) {
                for (int c = 0; c < 3; ++c) {
                    est[c] = (prevRow[dx * 3 + c] & 0xFF) + (pix[c] & 0xFF) - (prevRow[(dx - 1) * 3 + c] & 0xFF);
                    if (est[c] > 255) {
                        est[c] = 255;
                    }
                    else if (est[c] < 0) {
                        est[c] = 0;
                    }
                    thisRow[dx * 3 + c] = (pix[c] = (byte)(est[c] + buf[(dy * w + dx) * 3 + c]));
                }
                this.pixels24[offset++] = ((pix[0] & 0xFF) << 16 | (pix[1] & 0xFF) << 8 | (pix[2] & 0xFF));
            }
            System.arraycopy(thisRow, 0, prevRow, 0, w * 3);
            offset += this.rfb.framebufferWidth - w;
        }
    }
    
    void handleUpdatedPixels(final int x, final int y, final int w, final int h) {
        this.pixelsSource.newPixels(x, y, w, h);
        synchronized (this.memImage) {
            this.memGraphics.setClip(x, y, w, h);
            this.memGraphics.drawImage(this.rawPixelsImage, 0, 0, null);
            this.memGraphics.setClip(0, 0, this.rfb.framebufferWidth, this.rfb.framebufferHeight);
        }
    }
    
    void scheduleRepaint(final int x, final int y, final int w, final int h) {
        this.repaint(this.viewer.deferScreenUpdates, x, y, w, h);
    }
    
    public void keyPressed(final KeyEvent evt) {
        this.processLocalKeyEvent(evt);
    }
    
    public void keyReleased(final KeyEvent evt) {
        this.processLocalKeyEvent(evt);
    }
    
    public void keyTyped(final KeyEvent evt) {
        evt.consume();
    }
    
    public void mousePressed(final MouseEvent evt) {
        this.processLocalMouseEvent(evt, false);
    }
    
    public void mouseReleased(final MouseEvent evt) {
        this.processLocalMouseEvent(evt, false);
    }
    
    public void mouseMoved(final MouseEvent evt) {
        this.processLocalMouseEvent(evt, true);
    }
    
    public void mouseDragged(final MouseEvent evt) {
        this.processLocalMouseEvent(evt, true);
    }
    
    public void processLocalKeyEvent(final KeyEvent evt) {
        if (this.viewer.rfb != null && this.rfb.inNormalProtocol) {
            if (!this.inputEnabled) {
                if ((evt.getKeyChar() == 'r' || evt.getKeyChar() == 'R') && evt.getID() == 401) {
                    try {
                        this.rfb.writeFramebufferUpdateRequest(0, 0, this.rfb.framebufferWidth, this.rfb.framebufferHeight, false);
                    }
                    catch (IOException e) {
                        this.viewer.fatalError("failed to write update request", e);
                    }
                }
            }
            else {
                synchronized (this.eventLock) {
                    try {
                        this.rfb.writeKeyEvent(evt);
                    }
                    catch (IOException e2) {
                        this.viewer.fatalError("failed to write event", e2);
                    }
                    this.eventLock.notify();
                }
            }
        }
        evt.consume();
    }
    
    public void processLocalMouseEvent(final MouseEvent evt, final boolean moved) {
        if (this.viewer.rfb != null && this.rfb.inNormalProtocol) {
            if (moved) {
                this.softCursorMove(evt.getX(), evt.getY());
            }
            synchronized (this.eventLock) {
                try {
                    this.rfb.writePointerEvent(evt);
                }
                catch (IOException e) {
                    this.viewer.fatalError("failed to write mouse event", e);
                }
                this.eventLock.notify();
            }
        }
    }
    
    public void mouseClicked(final MouseEvent evt) {
    }
    
    public void mouseEntered(final MouseEvent evt) {
    }
    
    public void mouseExited(final MouseEvent evt) {
    }
    
    synchronized void handleCursorShapeUpdate(final int encodingType, final int xhot, final int yhot, final int width, final int height) throws IOException {
        final int bytesPerRow = (width + 7) / 8;
        final int bytesMaskData = bytesPerRow * height;
        this.softCursorFree();
        if (width * height == 0) {
            return;
        }
        if (this.viewer.options.ignoreCursorUpdates) {
            if (encodingType == -240) {
                this.rfb.is.skipBytes(6 + bytesMaskData * 2);
            }
            else {
                this.rfb.is.skipBytes(width * height + bytesMaskData);
            }
            return;
        }
        this.softCursorPixels = new int[width * height];
        if (encodingType == -240) {
            final byte[] rgb = new byte[6];
            this.rfb.is.readFully(rgb);
            final int[] colors = { 0xFF000000 | (rgb[3] & 0xFF) << 16 | (rgb[4] & 0xFF) << 8 | (rgb[5] & 0xFF), 0xFF000000 | (rgb[0] & 0xFF) << 16 | (rgb[1] & 0xFF) << 8 | (rgb[2] & 0xFF) };
            final byte[] pixBuf = new byte[bytesMaskData];
            this.rfb.is.readFully(pixBuf);
            final byte[] maskBuf = new byte[bytesMaskData];
            this.rfb.is.readFully(maskBuf);
            int i = 0;
            for (int y = 0; y < height; ++y) {
                int x;
                for (x = 0; x < width / 8; ++x) {
                    final byte pixByte = pixBuf[y * bytesPerRow + x];
                    final byte maskByte = maskBuf[y * bytesPerRow + x];
                    for (int n = 7; n >= 0; --n) {
                        int result;
                        if ((maskByte >> n & 0x1) != 0x0) {
                            result = colors[pixByte >> n & 0x1];
                        }
                        else {
                            result = 0;
                        }
                        this.softCursorPixels[i++] = result;
                    }
                }
                for (int n = 7; n >= 8 - width % 8; --n) {
                    int result;
                    if ((maskBuf[y * bytesPerRow + x] >> n & 0x1) != 0x0) {
                        result = colors[pixBuf[y * bytesPerRow + x] >> n & 0x1];
                    }
                    else {
                        result = 0;
                    }
                    this.softCursorPixels[i++] = result;
                }
            }
        }
        else {
            final byte[] pixBuf2 = new byte[width * height * this.bytesPixel];
            this.rfb.is.readFully(pixBuf2);
            final byte[] maskBuf2 = new byte[bytesMaskData];
            this.rfb.is.readFully(maskBuf2);
            int j = 0;
            for (int y2 = 0; y2 < height; ++y2) {
                int x2;
                for (x2 = 0; x2 < width / 8; ++x2) {
                    final byte maskByte2 = maskBuf2[y2 * bytesPerRow + x2];
                    for (int n2 = 7; n2 >= 0; --n2) {
                        int result2;
                        if ((maskByte2 >> n2 & 0x1) != 0x0) {
                            if (this.bytesPixel == 1) {
                                result2 = this.cm8.getRGB(pixBuf2[j]);
                            }
                            else {
                                result2 = (0xFF000000 | (pixBuf2[j * 4 + 1] & 0xFF) << 16 | (pixBuf2[j * 4 + 2] & 0xFF) << 8 | (pixBuf2[j * 4 + 3] & 0xFF));
                            }
                        }
                        else {
                            result2 = 0;
                        }
                        this.softCursorPixels[j++] = result2;
                    }
                }
                for (int n2 = 7; n2 >= 8 - width % 8; --n2) {
                    int result2;
                    if ((maskBuf2[y2 * bytesPerRow + x2] >> n2 & 0x1) != 0x0) {
                        if (this.bytesPixel == 1) {
                            result2 = this.cm8.getRGB(pixBuf2[j]);
                        }
                        else {
                            result2 = (0xFF000000 | (pixBuf2[j * 4 + 1] & 0xFF) << 16 | (pixBuf2[j * 4 + 2] & 0xFF) << 8 | (pixBuf2[j * 4 + 3] & 0xFF));
                        }
                    }
                    else {
                        result2 = 0;
                    }
                    this.softCursorPixels[j++] = result2;
                }
            }
        }
        this.softCursorSource = new MemoryImageSource(width, height, this.softCursorPixels, 0, width);
        this.softCursor = this.createImage(this.softCursorSource);
        this.cursorWidth = width;
        this.cursorHeight = height;
        this.hotX = xhot;
        this.hotY = yhot;
        this.showSoftCursor = true;
        this.repaint(this.viewer.deferCursorUpdates, this.cursorX - this.hotX, this.cursorY - this.hotY, this.cursorWidth, this.cursorHeight);
    }
    
    synchronized void softCursorMove(final int x, final int y) {
        if (this.showSoftCursor) {
            this.repaint(this.viewer.deferCursorUpdates, this.cursorX - this.hotX, this.cursorY - this.hotY, this.cursorWidth, this.cursorHeight);
            this.repaint(this.viewer.deferCursorUpdates, x - this.hotX, y - this.hotY, this.cursorWidth, this.cursorHeight);
        }
        this.cursorX = x;
        this.cursorY = y;
    }
    
    synchronized void softCursorFree() {
        if (this.showSoftCursor) {
            this.showSoftCursor = false;
            this.softCursor = null;
            this.softCursorSource = null;
            this.softCursorPixels = null;
            this.repaint(this.viewer.deferCursorUpdates, this.cursorX - this.hotX, this.cursorY - this.hotY, this.cursorWidth, this.cursorHeight);
        }
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
