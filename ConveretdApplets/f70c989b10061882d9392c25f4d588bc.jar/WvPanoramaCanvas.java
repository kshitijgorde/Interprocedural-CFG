import java.awt.Event;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.image.ImageObserver;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.util.Hashtable;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvPanoramaCanvas extends Canvas implements WvEventListener, WvPresetListener, WvFloorListener, WvPTZBListener, WvUtilListener
{
    private static final int DEFAULTLARGEWIDTH = 480;
    private static final int DEFAULTSMALLWIDTH = 240;
    private int action;
    private static final int NONE = 0;
    private static final int INSIDE = 1;
    private static final int OUTSIDE = 2;
    private static final int RESIZE = 3;
    private Dimension size;
    private Rectangle hardRect;
    private Rectangle softRect;
    private Rectangle viewRect;
    private Rectangle focusRect;
    private Rectangle focusOuterRect;
    private Rectangle focusInnerRect;
    private Rectangle rubberRect;
    private int focusMinWidth;
    private int focusMaxWidth;
    private WvDispatcher wvDispatcher;
    private WvMapper wvMapper;
    private Image bgImage;
    private Hashtable storageHash;
    private Image backBuffer;
    private Graphics backGC;
    private boolean createBackBuffer;
    private Frame frame;
    private int cursorType;
    private int mouseDownX;
    private int mouseDownY;
    private int mouseDownCenterX;
    private int mouseDownCenterY;
    private int offsetToCenterX;
    private int offsetToCenterY;
    private boolean videoConnected;
    private boolean cameraConnected;
    private boolean onceMoved;
    private boolean isPanoramaEmpty;
    
    public void connect(final String s) {
        final Container container = this.getParent();
        if (container instanceof Frame) {
            this.frame = (Frame)container;
            return;
        }
        this.frame = null;
    }
    
    public void enabledCameraControl(final int i) {
        if (this.wvDispatcher.getWvCameraInfo().cameraEnabled()) {
            this.setCursor(1);
            this.enable();
        }
        this.repaint();
    }
    
    public void disabledCameraControl() {
        this.setCursor(0);
        this.action = 0;
        this.disable();
        this.repaint();
    }
    
    public void cameraConnected(final boolean flag) {
        this.cameraConnected = flag;
        if (this.videoConnected && this.cameraConnected) {
            final int i = this.wvDispatcher.getCurrentCameraId();
            this.cameraChanged(i);
        }
    }
    
    public void cameraSelected(final String s) {
    }
    
    private void initPanoramaCanvas(Image image) {
        this.bgImage = image;
        final WvCameraInfo wvcamerainfo = this.wvDispatcher.getWvCameraInfo();
        final WvScope wvscope = wvcamerainfo.getHardScope();
        final WvScope wvscope2 = wvcamerainfo.getSoftScope();
        final WvScope wvscope3 = wvcamerainfo.getViewScope();
        int i = 0;
        int j = 0;
        if (image != null) {
            i = image.getWidth(this);
            j = image.getHeight(this);
        }
        this.isPanoramaEmpty = (wvscope.isEmpty() && wvscope2.isEmpty());
        if (i <= 0 || j <= 0) {
            image = null;
            this.size.width = ((wvscope.getDegRangeX() < 12000) ? 240 : 480);
            this.size.height = this.size.width * wvscope.getDegRangeY() / wvscope.getDegRangeX();
        }
        else {
            this.size.width = i;
            this.size.height = j;
        }
        if (this.size.height > 320) {
            final double d = 320.0 / this.size.height;
            this.size.width *= (int)d;
            this.size.height = 320;
        }
        (this.wvMapper = new WvMapper(wvscope, this.size)).mapRect(wvscope, this.hardRect);
        this.wvMapper.mapRect(wvscope2, this.softRect);
        this.wvMapper.mapRect(wvscope3, this.viewRect);
        if (!this.hardRect.equals(this.viewRect)) {
            if (image != null) {
                final ImageProducer imageproducer = image.getSource();
                final CropImageFilter cropimagefilter = new CropImageFilter(this.viewRect.x, this.viewRect.y, this.viewRect.width, this.viewRect.height);
                final FilteredImageSource filteredimagesource = new FilteredImageSource(imageproducer, cropimagefilter);
                final Image image2 = Toolkit.getDefaultToolkit().createImage(filteredimagesource);
                try {
                    final MediaTracker mediatracker = new MediaTracker(this);
                    mediatracker.addImage(image2, 0);
                    mediatracker.waitForAll();
                }
                catch (Exception exception) {
                    System.out.println(this + " " + exception);
                }
                this.bgImage = image2;
            }
            final double d2 = this.size.width / wvscope3.getDegRangeX();
            final double d3 = this.size.height / wvscope3.getDegRangeY();
            if (d2 < d3) {
                this.size.height = (int)(wvscope3.getDegRangeY() * d2);
            }
            else {
                this.size.width = (int)(wvscope3.getDegRangeX() * d3);
            }
            final int k1 = this.wvMapper.getWidth(wvcamerainfo.getMaxValue(2, 0));
            final int l1 = k1 * 3 / 4;
            if (this.viewRect.width < k1 && this.viewRect.height < l1) {
                this.bgImage = null;
            }
            (this.wvMapper = new WvMapper(wvscope3, this.size)).mapRect(wvscope2, this.softRect);
        }
        if (this.frame != null) {
            this.resize(this.size.width, this.size.height);
            this.frame.pack();
            final int m = this.wvDispatcher.getCurrentCameraId();
            final String s = this.storageHash.get("time" + m);
            String s2 = "Panorama Controller";
            if (s != null) {
                s2 = "Captured Date: " + s;
            }
            this.frame.setTitle(s2);
        }
        else {
            Graphics g = this.getGraphics();
            g.clearRect(0, 0, this.size.width * 2, this.size.height * 2);
            final Container container = this.getParent();
            g = container.getGraphics();
            container.update(g);
            this.resize(this.size.width, this.size.height);
            this.invalidate();
            this.getParent().layout();
        }
        this.createBackBuffer = true;
        final int l2 = wvcamerainfo.getPanValue();
        final int i2 = wvcamerainfo.getTiltValue();
        final int j2 = wvcamerainfo.getZoomValue();
        this.wvMapper.mapRect(l2, i2, j2, this.focusRect);
        this.makeFocusInsideOfWindow(this.focusRect);
        this.focusMaxWidth = this.wvMapper.getWidth(wvcamerainfo.getMaxValue(2, 1)) + 1;
        this.focusMinWidth = this.wvMapper.getWidth(wvcamerainfo.getMinValue(2, 1)) - 1;
    }
    
    public void disconnect(final int i) {
        this.setCursor(0);
        this.disable();
        this.repaint();
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void videoConnected(final boolean flag) {
        this.videoConnected = flag;
        if (this.videoConnected) {
            this.getPanoramaDate();
        }
        if (this.videoConnected && this.cameraConnected) {
            final int i = this.wvDispatcher.getCurrentCameraId();
            this.cameraChanged(i);
        }
    }
    
    public void ptzbChangedInternal(final Object obj, final int i, final int j) {
        if (!this.wvDispatcher.hasFloor()) {
            return;
        }
        int k = this.wvMapper.getPanValue(this.focusRect.x + this.focusRect.width / 2);
        int l = this.wvMapper.getTiltValue(this.focusRect.y + this.focusRect.height / 2);
        int i2 = this.wvMapper.getZoomValue(this.focusRect.width);
        switch (i) {
            case 0: {
                k = j;
                break;
            }
            case 1: {
                l = j;
                break;
            }
            case 2: {
                i2 = j;
                break;
            }
        }
        if (this.wvMapper != null) {
            this.wvMapper.mapRect(k, l, i2, this.focusRect);
        }
        this.makeFocusInsideOfWindow(this.focusRect);
        this.repaint();
    }
    
    public void waitingCameraControl(final int i) {
        this.disable();
    }
    
    public void failedToGetCameraControl() {
        this.disable();
        this.repaint();
    }
    
    public Dimension preferredSize() {
        return this.size;
    }
    
    private Image getPanoramaImage(final int i) {
        Image image = this.storageHash.get("image" + i);
        if (image == null) {
            image = this.wvDispatcher.getPanoramaImage(i);
            if (image != null) {
                this.storageHash.put("image" + i, image);
            }
        }
        return image;
    }
    
    public void cameraChanged(final int i) {
        if (this.videoConnected && this.cameraConnected) {
            final Image image = this.getPanoramaImage(i);
            this.initPanoramaCanvas(image);
        }
        this.repaint();
    }
    
    public void cameraWillChanged() {
    }
    
    private void setCursor(final int i) {
        if (this.frame != null) {
            this.frame.setCursor(this.isPanoramaEmpty ? 0 : i);
        }
    }
    
    private void moveFocus(final int i, final int j) {
        final int k = Math.min(Math.max(i, this.softRect.x), this.softRect.x + this.softRect.width);
        final int l = Math.min(Math.max(j, this.softRect.y), this.softRect.y + this.softRect.height);
        final int i2 = k - this.focusRect.width / 2;
        final int j2 = l - this.focusRect.height / 2;
        this.focusRect.move(i2, j2);
        this.makeFocusInsideOfWindow(this.focusRect);
        this.repaint();
    }
    
    public void kickOff() {
        this.frame = null;
        this.videoConnected = false;
        this.cameraConnected = false;
        this.disable();
    }
    
    public void ptzbChanged(final WvPTZB wvptzb) {
        final int i = wvptzb.pan;
        final int j = wvptzb.tilt;
        final int k = wvptzb.zoom;
        if (this.wvMapper != null) {
            this.wvMapper.mapRect(i, j, k, this.focusRect);
        }
        this.makeFocusInsideOfWindow(this.focusRect);
        this.repaint();
    }
    
    public void paint(final Graphics g) {
        if (this.backBuffer == null || this.createBackBuffer) {
            if (this.size.width <= 0 || this.size.height <= 0) {
                return;
            }
            this.backBuffer = this.createImage(this.size.width, this.size.height);
            if (this.backBuffer == null) {
                return;
            }
            this.backGC = this.backBuffer.getGraphics();
            this.createBackBuffer = false;
        }
        this.backGC.setColor(Color.lightGray);
        this.backGC.fillRect(0, 0, this.size.width, this.size.height);
        if (this.isPanoramaEmpty) {
            g.drawImage(this.backBuffer, 0, 0, this.size.width, this.size.height, this);
            return;
        }
        if (this.bgImage != null) {
            this.backGC.drawImage(this.bgImage, 0, 0, this.size.width, this.size.height, this);
        }
        this.backGC.setColor(Color.gray);
        int i = 0;
        do {
            this.backGC.draw3DRect(i, i, this.size.width - i * 2, this.size.height - i * 2, false);
        } while (++i < 2);
        this.backGC.setColor(Color.black);
        if (this.softRect.isEmpty()) {
            if (this.softRect.width == 0) {
                this.backGC.drawLine(this.softRect.x, this.softRect.y, this.softRect.x, this.softRect.y + this.softRect.height);
            }
            if (this.softRect.height == 0) {
                this.backGC.drawLine(this.softRect.x, this.softRect.y, this.softRect.x + this.softRect.width, this.softRect.y);
            }
        }
        else {
            this.backGC.drawRect(this.softRect.x, this.softRect.y, this.softRect.width, this.softRect.height);
        }
        if (this.action == 2 && !this.rubberRect.isEmpty()) {
            this.backGC.setColor(Color.blue);
            this.backGC.drawRect(this.rubberRect.x, this.rubberRect.y, this.rubberRect.width, this.rubberRect.height);
        }
        this.backGC.setColor(this.wvDispatcher.hasFloor() ? Color.yellow : Color.blue);
        i = 0;
        do {
            this.backGC.drawRect(this.focusRect.x + i, this.focusRect.y + i, this.focusRect.width - i * 2, this.focusRect.height - i * 2);
        } while (++i < 2);
        i = this.focusRect.x + this.focusRect.width / 2;
        final int j = this.focusRect.y + this.focusRect.height / 2;
        this.backGC.drawLine(i - 2, j, i + 2, j);
        this.backGC.drawLine(i, j - 2, i, j + 2);
        g.drawImage(this.backBuffer, 0, 0, this.size.width, this.size.height, this);
    }
    
    private void getPanoramaDate() {
        final String s = String.valueOf("GetPanoramaList".trim()) + "?item=camera_id&item=date_and_time_string";
        final String s2 = this.wvDispatcher.syncSendCommand(s);
        if (s2 == null) {
            return;
        }
        try {
            final StringTokenizer stringtokenizer = new StringTokenizer(s2, "\r\n=");
            while (stringtokenizer.hasMoreTokens()) {
                final String s3 = stringtokenizer.nextToken();
                if (s3.equals("camera_id")) {
                    final String s4 = stringtokenizer.nextToken();
                    stringtokenizer.nextToken();
                    final String s5 = stringtokenizer.nextToken().substring(4, 25);
                    this.storageHash.put("time" + s4, s5);
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public boolean mouseUp(final Event event, final int i, final int j) {
        final int k = Math.abs(i - this.mouseDownX) + Math.abs(j - this.mouseDownY);
        switch (this.action) {
            case 2: {
                if (k < 4) {
                    this.moveFocus(i, j);
                    break;
                }
                this.focusRect.width = Math.max(this.rubberRect.width, this.focusMinWidth);
                this.focusRect.height = Math.max(this.rubberRect.height, this.focusMinWidth * 3 / 4);
                this.moveFocus(this.rubberRect.x + this.rubberRect.width / 2, this.rubberRect.y + this.rubberRect.height / 2);
                break;
            }
            case 1:
            case 3: {
                if (i == this.mouseDownX && j == this.mouseDownY && !this.onceMoved) {
                    this.moveFocus(i, j);
                    break;
                }
                break;
            }
        }
        if (this.wvMapper != null) {
            int l = this.focusRect.x;
            int i2 = this.focusRect.y;
            if (l == 1) {
                l = 0;
            }
            if (l == this.size.width - this.focusRect.width - 1) {
                l = this.size.width - this.focusRect.width;
            }
            if (i2 == 1) {
                i2 = 0;
            }
            if (i2 == this.size.height - this.focusRect.height - 1) {
                i2 = this.size.height - this.focusRect.height;
            }
            int j2 = this.wvMapper.getPanValue(l + this.focusRect.width / 2);
            int k2 = this.wvMapper.getTiltValue(i2 + this.focusRect.height / 2);
            int l2 = ((this.action == 3 || this.action == 2) && k >= 4) ? this.wvMapper.getZoomValue(this.focusRect.width) : this.wvDispatcher.getPtzValue(2);
            final WvCameraInfo wvcamerainfo = this.wvDispatcher.getWvCameraInfo();
            int i3 = wvcamerainfo.getMinValue(0, 1);
            int j3 = wvcamerainfo.getMaxValue(0, 1);
            if (j3 - j2 < 100) {
                j2 = j3;
            }
            else if (j2 - i3 < 100) {
                j2 = i3;
            }
            i3 = wvcamerainfo.getMinValue(1, 1);
            j3 = wvcamerainfo.getMaxValue(1, 1);
            if (j3 - k2 < 100) {
                k2 = j3;
            }
            else if (k2 - i3 < 100) {
                k2 = i3;
            }
            i3 = wvcamerainfo.getMinValue(2, 1);
            j3 = wvcamerainfo.getMaxValue(2, 1);
            if (j3 - l2 < 100) {
                l2 = j3;
            }
            else if (l2 - i3 < 100) {
                l2 = i3;
            }
            this.wvDispatcher.setPtzValue(j2, k2, l2);
        }
        this.rubberRect.reshape(0, 0, 0, 0);
        this.setCursor(this.isEnabled() ? 1 : 0);
        return false;
    }
    
    private int getActionType(final int i, final int j) {
        this.focusInnerRect.reshape(this.focusRect.x, this.focusRect.y, this.focusRect.width, this.focusRect.height);
        this.focusOuterRect.reshape(this.focusRect.x, this.focusRect.y, this.focusRect.width, this.focusRect.height);
        this.focusInnerRect.grow(-2, -2);
        this.focusOuterRect.grow(2, 2);
        int k = 20 - this.focusOuterRect.width;
        if (k > 0) {
            this.focusOuterRect.grow(k / 2, k * 3 / 8);
        }
        k = 12 - this.focusInnerRect.width;
        if (k > 0) {
            this.focusInnerRect.grow(k / 2, k * 3 / 8);
        }
        if (this.focusInnerRect.inside(i, j)) {
            return 1;
        }
        return this.focusOuterRect.inside(i, j) ? 3 : 2;
    }
    
    public Dimension minimumSize() {
        return this.size;
    }
    
    public void presetCameraOperationFinished(final String s) {
        final int i = this.wvDispatcher.getWvCameraInfo().getCurrentCameraId();
        final Image image = this.getPanoramaImage(i);
        this.initPanoramaCanvas(image);
        this.repaint();
    }
    
    public void imageSizeChanged(final String s) {
    }
    
    public void cameraPower(final boolean flag) {
        if (this.wvDispatcher.hasFloor()) {
            this.enable(flag);
            if (!flag) {
                this.setCursor(0);
            }
        }
    }
    
    public WvPanoramaCanvas(final WvDispatcher wvdispatcher) {
        this.size = new Dimension();
        this.hardRect = new Rectangle();
        this.softRect = new Rectangle();
        this.viewRect = new Rectangle();
        this.focusRect = new Rectangle();
        this.focusOuterRect = new Rectangle();
        this.focusInnerRect = new Rectangle();
        this.rubberRect = new Rectangle();
        this.storageHash = new Hashtable();
        this.wvDispatcher = wvdispatcher;
    }
    
    private void makeFocusInsideOfWindow(final Rectangle rectangle) {
        rectangle.x = Math.min(Math.max(rectangle.x, 1), this.size.width - rectangle.width - 1);
        rectangle.y = Math.min(Math.max(rectangle.y, 1), this.size.height - rectangle.height - 1);
    }
    
    public boolean mouseDown(final Event event, final int i, final int j) {
        this.action = this.getActionType(i, j);
        this.onceMoved = false;
        this.mouseDownX = i;
        this.mouseDownY = j;
        this.mouseDownCenterX = this.focusRect.x + this.focusRect.width / 2;
        this.mouseDownCenterY = this.focusRect.y + this.focusRect.height / 2;
        this.offsetToCenterX = this.mouseDownCenterX - i;
        this.offsetToCenterY = this.mouseDownCenterY - j;
        this.rubberRect.reshape(i, i, 0, 0);
        return false;
    }
    
    public void ptzbChangedByAnotherClient(final WvPTZB wvptzb) {
        final int i = wvptzb.pan;
        final int j = wvptzb.tilt;
        final int k = wvptzb.zoom;
        if (this.wvMapper != null) {
            this.wvMapper.mapRect(i, j, k, this.focusRect);
        }
        this.makeFocusInsideOfWindow(this.focusRect);
        this.repaint();
    }
    
    public boolean mouseDrag(final Event event, final int i, final int j) {
        this.onceMoved = true;
        if (!this.isEnabled()) {
            return false;
        }
        switch (this.action) {
            case 1: {
                this.setCursor(13);
                this.moveFocus(this.offsetToCenterX + i, this.offsetToCenterY + j);
                break;
            }
            case 2: {
                final int k = Math.abs(this.mouseDownX - i);
                final int l = Math.abs(this.mouseDownY - j);
                if (k * 3 / 4 > l) {
                    this.rubberRect.width = Math.min(k, this.focusMaxWidth);
                    this.rubberRect.height = this.rubberRect.width * 3 / 4;
                }
                else {
                    this.rubberRect.height = Math.min(l, this.focusMaxWidth * 3 / 4 + 1);
                    this.rubberRect.width = this.rubberRect.height * 4 / 3;
                }
                this.rubberRect.width = Math.max(1, this.rubberRect.width);
                this.rubberRect.height = Math.max(1, this.rubberRect.height);
                this.rubberRect.x = ((i <= this.mouseDownX) ? (this.mouseDownX - this.rubberRect.width) : this.mouseDownX);
                this.rubberRect.y = ((j <= this.mouseDownY) ? (this.mouseDownY - this.rubberRect.height) : this.mouseDownY);
                break;
            }
            case 3: {
                final int i2 = i - this.mouseDownCenterX;
                final int j2 = j - this.mouseDownCenterY;
                final int k2 = Math.abs(i2);
                final int l2 = Math.abs(j2);
                this.focusRect.reshape(this.mouseDownCenterX - k2, this.mouseDownCenterY - l2, k2 * 2, l2 * 2);
                if (k2 * 3 / 4 > l2) {
                    this.focusRect.height = this.focusRect.width * 3 / 4;
                }
                else {
                    this.focusRect.width = this.focusRect.height * 4 / 3;
                }
                byte byte0 = 0;
                if (i2 < 0 && j2 > 0) {
                    byte0 = 4;
                }
                else if (i2 > 0 && j2 > 0) {
                    byte0 = 5;
                }
                else if (i2 > 0 && j2 < 0) {
                    byte0 = 7;
                }
                else if (i2 < 0 && j2 < 0) {
                    byte0 = 6;
                }
                this.setCursor(byte0);
                this.focusRect.width = Math.max(this.focusMinWidth, Math.min(this.focusMaxWidth - 1, this.focusRect.width));
                this.focusRect.height = this.focusRect.width * 3 / 4;
                this.moveFocus(this.mouseDownCenterX, this.mouseDownCenterY);
                break;
            }
        }
        this.repaint();
        return false;
    }
    
    public boolean mouseMove(final Event event, final int i, final int j) {
        this.action = this.getActionType(i, j);
        if (this.frame == null) {
            return false;
        }
        switch (this.action) {
            case 3: {
                this.cursorType = 0;
                final int k = i - (this.focusRect.x + this.focusRect.width / 2);
                final int l = j - (this.focusRect.y + this.focusRect.height / 2);
                if (k < 0 && l > 0) {
                    this.cursorType = 4;
                }
                else if (k > 0 && l > 0) {
                    this.cursorType = 5;
                }
                else if (k > 0 && l < 0) {
                    this.cursorType = 7;
                }
                else if (k < 0 && l < 0) {
                    this.cursorType = 6;
                }
                else if (k == 0) {
                    this.cursorType = ((l <= 0) ? 8 : 9);
                }
                else if (l == 0) {
                    this.cursorType = ((k <= 0) ? 10 : 11);
                }
                this.setCursor(this.cursorType);
                break;
            }
            default: {
                this.setCursor(this.isEnabled() ? 1 : 0);
                break;
            }
        }
        return false;
    }
}
