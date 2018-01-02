import java.awt.Event;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Toolkit;
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
        final Container parent = this.getParent();
        if (parent instanceof Frame) {
            this.frame = (Frame)parent;
            return;
        }
        this.frame = null;
    }
    
    public void enabledCameraControl(final int n) {
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
    
    public void cameraConnected(final boolean cameraConnected) {
        this.cameraConnected = cameraConnected;
        if (this.videoConnected && this.cameraConnected) {
            this.cameraChanged(this.wvDispatcher.getCurrentCameraId());
        }
    }
    
    public void cameraSelected(final String s) {
    }
    
    private void initPanoramaCanvas(Image bgImage) {
        this.bgImage = bgImage;
        final WvCameraInfo wvCameraInfo = this.wvDispatcher.getWvCameraInfo();
        final WvScope hardScope = wvCameraInfo.getHardScope();
        final WvScope softScope = wvCameraInfo.getSoftScope();
        final WvScope viewScope = wvCameraInfo.getViewScope();
        int width = 0;
        int height = 0;
        if (bgImage != null) {
            width = bgImage.getWidth(this);
            height = bgImage.getHeight(this);
        }
        this.isPanoramaEmpty = (hardScope.isEmpty() && softScope.isEmpty());
        if (width <= 0 || height <= 0) {
            bgImage = null;
            this.size.width = ((hardScope.getDegRangeX() >= 12000) ? 480 : 240);
            this.size.height = this.size.width * hardScope.getDegRangeY() / hardScope.getDegRangeX();
        }
        else {
            this.size.width = width;
            this.size.height = height;
        }
        if (this.size.height > 320) {
            this.size.width *= (int)(320.0 / this.size.height);
            this.size.height = 320;
        }
        (this.wvMapper = new WvMapper(hardScope, this.size)).mapRect(hardScope, this.hardRect);
        this.wvMapper.mapRect(softScope, this.softRect);
        this.wvMapper.mapRect(viewScope, this.viewRect);
        if (!this.hardRect.equals(this.viewRect)) {
            if (bgImage != null) {
                final Image image = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(bgImage.getSource(), new CropImageFilter(this.viewRect.x, this.viewRect.y, this.viewRect.width, this.viewRect.height)));
                try {
                    final MediaTracker mediaTracker = new MediaTracker(this);
                    mediaTracker.addImage(image, 0);
                    mediaTracker.waitForAll();
                }
                catch (Exception ex) {
                    System.out.println(this + " " + ex);
                }
                this.bgImage = image;
            }
            final double n = this.size.width / viewScope.getDegRangeX();
            final double n2 = this.size.height / viewScope.getDegRangeY();
            if (n < n2) {
                this.size.height = (int)(viewScope.getDegRangeY() * n);
            }
            else {
                this.size.width = (int)(viewScope.getDegRangeX() * n2);
            }
            final int width2 = this.wvMapper.getWidth(wvCameraInfo.getMaxValue(2, 0));
            final int n3 = width2 * 3 / 4;
            if (this.viewRect.width < width2 && this.viewRect.height < n3) {
                this.bgImage = null;
            }
            (this.wvMapper = new WvMapper(viewScope, this.size)).mapRect(softScope, this.softRect);
        }
        if (this.frame != null) {
            this.resize(this.size.width, this.size.height);
            this.frame.pack();
            final String s = this.storageHash.get("time" + this.wvDispatcher.getCurrentCameraId());
            String string = "Panorama Controller";
            if (s != null) {
                string = "Captured Date: " + s;
            }
            this.frame.setTitle(string);
        }
        else {
            this.getGraphics().clearRect(0, 0, this.size.width * 2, this.size.height * 2);
            final Container parent = this.getParent();
            parent.update(parent.getGraphics());
            this.resize(this.size.width, this.size.height);
            this.invalidate();
            this.getParent().layout();
        }
        this.createBackBuffer = true;
        this.wvMapper.mapRect(wvCameraInfo.getPanValue(), wvCameraInfo.getTiltValue(), wvCameraInfo.getZoomValue(), this.focusRect);
        this.makeFocusInsideOfWindow(this.focusRect);
        this.focusMaxWidth = this.wvMapper.getWidth(wvCameraInfo.getMaxValue(2, 1)) + 1;
        this.focusMinWidth = this.wvMapper.getWidth(wvCameraInfo.getMinValue(2, 1)) - 1;
    }
    
    public void disconnect(final int n) {
        this.setCursor(0);
        this.disable();
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void videoConnected(final boolean videoConnected) {
        this.videoConnected = videoConnected;
        if (this.videoConnected) {
            this.getPanoramaDate();
        }
        if (this.videoConnected && this.cameraConnected) {
            this.cameraChanged(this.wvDispatcher.getCurrentCameraId());
        }
    }
    
    public void ptzbChangedInternal(final Object o, final int n, final int n2) {
        if (!this.wvDispatcher.hasFloor()) {
            return;
        }
        int panValue = this.wvMapper.getPanValue(this.focusRect.x + this.focusRect.width / 2);
        int tiltValue = this.wvMapper.getTiltValue(this.focusRect.y + this.focusRect.height / 2);
        int zoomValue = this.wvMapper.getZoomValue(this.focusRect.width);
        switch (n) {
            case 0: {
                panValue = n2;
                break;
            }
            case 1: {
                tiltValue = n2;
                break;
            }
            case 2: {
                zoomValue = n2;
                break;
            }
        }
        if (this.wvMapper != null) {
            this.wvMapper.mapRect(panValue, tiltValue, zoomValue, this.focusRect);
        }
        this.makeFocusInsideOfWindow(this.focusRect);
        this.repaint();
    }
    
    public void waitingCameraControl(final int n) {
        this.setCursor(0);
        this.action = 0;
        this.disable();
        this.repaint();
    }
    
    public void failedToGetCameraControl() {
        this.disable();
        this.repaint();
    }
    
    public Dimension preferredSize() {
        return this.size;
    }
    
    private Image getPanoramaImage(final int n) {
        Image panoramaImage = this.storageHash.get("image" + n);
        if (panoramaImage == null) {
            panoramaImage = this.wvDispatcher.getPanoramaImage(n);
            if (panoramaImage != null) {
                this.storageHash.put("image" + n, panoramaImage);
            }
        }
        return panoramaImage;
    }
    
    public void cameraChanged(final int n) {
        if (this.videoConnected && this.cameraConnected) {
            this.initPanoramaCanvas(this.getPanoramaImage(n));
        }
        this.repaint();
    }
    
    public void cameraWillChanged() {
    }
    
    private void setCursor(final int n) {
        if (this.frame != null) {
            this.frame.setCursor(this.isPanoramaEmpty ? 0 : n);
        }
    }
    
    private void moveFocus(final int n, final int n2) {
        this.focusRect.move(Math.min(Math.max(n, this.softRect.x), this.softRect.x + this.softRect.width) - this.focusRect.width / 2, Math.min(Math.max(n2, this.softRect.y), this.softRect.y + this.softRect.height) - this.focusRect.height / 2);
        this.makeFocusInsideOfWindow(this.focusRect);
        this.repaint();
    }
    
    public void kickOff() {
        this.frame = null;
        this.videoConnected = false;
        this.cameraConnected = false;
        this.disable();
    }
    
    public void ptzbChanged(final WvPTZB wvPTZB) {
        final int pan = wvPTZB.pan;
        final int tilt = wvPTZB.tilt;
        final int zoom = wvPTZB.zoom;
        if (this.wvMapper != null) {
            this.wvMapper.mapRect(pan, tilt, zoom, this.focusRect);
        }
        this.makeFocusInsideOfWindow(this.focusRect);
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
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
            graphics.drawImage(this.backBuffer, 0, 0, this.size.width, this.size.height, this);
            return;
        }
        if (this.bgImage != null) {
            this.backGC.drawImage(this.bgImage, 0, 0, this.size.width, this.size.height, this);
        }
        this.backGC.setColor(Color.gray);
        int n = 0;
        do {
            this.backGC.draw3DRect(n, n, this.size.width - n * 2, this.size.height - n * 2, false);
        } while (++n < 2);
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
        int n2 = 0;
        do {
            this.backGC.drawRect(this.focusRect.x + n2, this.focusRect.y + n2, this.focusRect.width - n2 * 2, this.focusRect.height - n2 * 2);
        } while (++n2 < 2);
        final int n3 = this.focusRect.x + this.focusRect.width / 2;
        final int n4 = this.focusRect.y + this.focusRect.height / 2;
        this.backGC.drawLine(n3 - 2, n4, n3 + 2, n4);
        this.backGC.drawLine(n3, n4 - 2, n3, n4 + 2);
        graphics.drawImage(this.backBuffer, 0, 0, this.size.width, this.size.height, this);
    }
    
    private void getPanoramaDate() {
        final String syncSendCommand = this.wvDispatcher.syncSendCommand("GetPanoramaList".trim() + "?item=camera_id&item=date_and_time_string");
        if (syncSendCommand == null) {
            return;
        }
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(syncSendCommand, "\r\n=");
            while (stringTokenizer.hasMoreTokens()) {
                if (stringTokenizer.nextToken().equals("camera_id")) {
                    final String nextToken = stringTokenizer.nextToken();
                    stringTokenizer.nextToken();
                    this.storageHash.put("time" + nextToken, stringTokenizer.nextToken().substring(4, 25));
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        final int n3 = Math.abs(n - this.mouseDownX) + Math.abs(n2 - this.mouseDownY);
        switch (this.action) {
            case 2: {
                if (n3 < 4) {
                    this.moveFocus(n, n2);
                    break;
                }
                this.focusRect.width = Math.max(this.rubberRect.width, this.focusMinWidth);
                this.focusRect.height = Math.max(this.rubberRect.height, this.focusMinWidth * 3 / 4);
                this.moveFocus(this.rubberRect.x + this.rubberRect.width / 2, this.rubberRect.y + this.rubberRect.height / 2);
                break;
            }
            case 1:
            case 3: {
                if (n == this.mouseDownX && n2 == this.mouseDownY && !this.onceMoved) {
                    this.moveFocus(n, n2);
                    break;
                }
                break;
            }
        }
        if (this.wvMapper != null) {
            int x = this.focusRect.x;
            int y = this.focusRect.y;
            if (x == 1) {
                x = 0;
            }
            if (x == this.size.width - this.focusRect.width - 1) {
                x = this.size.width - this.focusRect.width;
            }
            if (y == 1) {
                y = 0;
            }
            if (y == this.size.height - this.focusRect.height - 1) {
                y = this.size.height - this.focusRect.height;
            }
            int panValue = this.wvMapper.getPanValue(x + this.focusRect.width / 2);
            int tiltValue = this.wvMapper.getTiltValue(y + this.focusRect.height / 2);
            int n4 = ((this.action != 3 && this.action != 2) || n3 < 4) ? this.wvDispatcher.getPtzValue(2) : this.wvMapper.getZoomValue(this.focusRect.width);
            final WvCameraInfo wvCameraInfo = this.wvDispatcher.getWvCameraInfo();
            final int minValue = wvCameraInfo.getMinValue(0, 1);
            final int maxValue = wvCameraInfo.getMaxValue(0, 1);
            if (maxValue - panValue < 100) {
                panValue = maxValue;
            }
            else if (panValue - minValue < 100) {
                panValue = minValue;
            }
            final int minValue2 = wvCameraInfo.getMinValue(1, 1);
            final int maxValue2 = wvCameraInfo.getMaxValue(1, 1);
            if (maxValue2 - tiltValue < 100) {
                tiltValue = maxValue2;
            }
            else if (tiltValue - minValue2 < 100) {
                tiltValue = minValue2;
            }
            final int minValue3 = wvCameraInfo.getMinValue(2, 1);
            final int maxValue3 = wvCameraInfo.getMaxValue(2, 1);
            if (maxValue3 - n4 < 100) {
                n4 = maxValue3;
            }
            else if (n4 - minValue3 < 100) {
                n4 = minValue3;
            }
            this.wvDispatcher.setPtzValue(panValue, tiltValue, n4);
        }
        this.rubberRect.reshape(0, 0, 0, 0);
        this.setCursor(this.isEnabled() ? 1 : 0);
        return false;
    }
    
    private int getActionType(final int n, final int n2) {
        this.focusInnerRect.reshape(this.focusRect.x, this.focusRect.y, this.focusRect.width, this.focusRect.height);
        this.focusOuterRect.reshape(this.focusRect.x, this.focusRect.y, this.focusRect.width, this.focusRect.height);
        this.focusInnerRect.grow(-2, -2);
        this.focusOuterRect.grow(2, 2);
        final int n3 = 20 - this.focusOuterRect.width;
        if (n3 > 0) {
            this.focusOuterRect.grow(n3 / 2, n3 * 3 / 8);
        }
        final int n4 = 12 - this.focusInnerRect.width;
        if (n4 > 0) {
            this.focusInnerRect.grow(n4 / 2, n4 * 3 / 8);
        }
        if (this.focusInnerRect.inside(n, n2)) {
            return 1;
        }
        if (this.focusOuterRect.inside(n, n2)) {
            return 3;
        }
        return 2;
    }
    
    public Dimension minimumSize() {
        return this.size;
    }
    
    public void presetCameraOperationFinished(final String s) {
        this.initPanoramaCanvas(this.getPanoramaImage(this.wvDispatcher.getWvCameraInfo().getCurrentCameraId()));
        this.repaint();
    }
    
    public void imageSizeChanged(final String s) {
    }
    
    public void cameraPower(final boolean b) {
        if (this.wvDispatcher.hasFloor()) {
            this.enable(b);
            if (!b) {
                this.setCursor(0);
            }
        }
    }
    
    public WvPanoramaCanvas(final WvDispatcher wvDispatcher) {
        this.size = new Dimension();
        this.hardRect = new Rectangle();
        this.softRect = new Rectangle();
        this.viewRect = new Rectangle();
        this.focusRect = new Rectangle();
        this.focusOuterRect = new Rectangle();
        this.focusInnerRect = new Rectangle();
        this.rubberRect = new Rectangle();
        this.storageHash = new Hashtable();
        this.wvDispatcher = wvDispatcher;
    }
    
    private void makeFocusInsideOfWindow(final Rectangle rectangle) {
        rectangle.x = Math.min(Math.max(rectangle.x, 1), this.size.width - rectangle.width - 1);
        rectangle.y = Math.min(Math.max(rectangle.y, 1), this.size.height - rectangle.height - 1);
    }
    
    public boolean mouseDown(final Event event, final int mouseDownX, final int mouseDownY) {
        this.action = this.getActionType(mouseDownX, mouseDownY);
        this.onceMoved = false;
        this.mouseDownX = mouseDownX;
        this.mouseDownY = mouseDownY;
        this.mouseDownCenterX = this.focusRect.x + this.focusRect.width / 2;
        this.mouseDownCenterY = this.focusRect.y + this.focusRect.height / 2;
        this.offsetToCenterX = this.mouseDownCenterX - mouseDownX;
        this.offsetToCenterY = this.mouseDownCenterY - mouseDownY;
        this.rubberRect.reshape(mouseDownX, mouseDownX, 0, 0);
        return false;
    }
    
    public void ptzbChangedByAnotherClient(final WvPTZB wvPTZB) {
        final int pan = wvPTZB.pan;
        final int tilt = wvPTZB.tilt;
        final int zoom = wvPTZB.zoom;
        if (this.wvMapper != null) {
            this.wvMapper.mapRect(pan, tilt, zoom, this.focusRect);
        }
        this.makeFocusInsideOfWindow(this.focusRect);
        this.repaint();
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        this.onceMoved = true;
        if (!this.isEnabled()) {
            return false;
        }
        switch (this.action) {
            case 1: {
                this.setCursor(13);
                this.moveFocus(this.offsetToCenterX + n, this.offsetToCenterY + n2);
                break;
            }
            case 2: {
                final int abs = Math.abs(this.mouseDownX - n);
                final int abs2 = Math.abs(this.mouseDownY - n2);
                if (abs * 3 / 4 > abs2) {
                    this.rubberRect.width = Math.min(abs, this.focusMaxWidth);
                    this.rubberRect.height = this.rubberRect.width * 3 / 4;
                }
                else {
                    this.rubberRect.height = Math.min(abs2, this.focusMaxWidth * 3 / 4 + 1);
                    this.rubberRect.width = this.rubberRect.height * 4 / 3;
                }
                this.rubberRect.width = Math.max(1, this.rubberRect.width);
                this.rubberRect.height = Math.max(1, this.rubberRect.height);
                this.rubberRect.x = ((n > this.mouseDownX) ? this.mouseDownX : (this.mouseDownX - this.rubberRect.width));
                this.rubberRect.y = ((n2 > this.mouseDownY) ? this.mouseDownY : (this.mouseDownY - this.rubberRect.height));
                break;
            }
            case 3: {
                final int n3 = n - this.mouseDownCenterX;
                final int n4 = n2 - this.mouseDownCenterY;
                final int abs3 = Math.abs(n3);
                final int abs4 = Math.abs(n4);
                this.focusRect.reshape(this.mouseDownCenterX - abs3, this.mouseDownCenterY - abs4, abs3 * 2, abs4 * 2);
                if (abs3 * 3 / 4 > abs4) {
                    this.focusRect.height = this.focusRect.width * 3 / 4;
                }
                else {
                    this.focusRect.width = this.focusRect.height * 4 / 3;
                }
                int cursor = 0;
                if (n3 < 0 && n4 > 0) {
                    cursor = 4;
                }
                else if (n3 > 0 && n4 > 0) {
                    cursor = 5;
                }
                else if (n3 > 0 && n4 < 0) {
                    cursor = 7;
                }
                else if (n3 < 0 && n4 < 0) {
                    cursor = 6;
                }
                this.setCursor(cursor);
                this.focusRect.width = Math.max(this.focusMinWidth, Math.min(this.focusMaxWidth - 1, this.focusRect.width));
                this.focusRect.height = this.focusRect.width * 3 / 4;
                this.moveFocus(this.mouseDownCenterX, this.mouseDownCenterY);
                break;
            }
        }
        this.repaint();
        return false;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.action = this.getActionType(n, n2);
        if (this.frame == null) {
            return false;
        }
        switch (this.action) {
            case 3: {
                this.cursorType = 0;
                final int n3 = n - (this.focusRect.x + this.focusRect.width / 2);
                final int n4 = n2 - (this.focusRect.y + this.focusRect.height / 2);
                if (n3 < 0 && n4 > 0) {
                    this.cursorType = 4;
                }
                else if (n3 > 0 && n4 > 0) {
                    this.cursorType = 5;
                }
                else if (n3 > 0 && n4 < 0) {
                    this.cursorType = 7;
                }
                else if (n3 < 0 && n4 < 0) {
                    this.cursorType = 6;
                }
                else if (n3 == 0) {
                    this.cursorType = ((n4 > 0) ? 9 : 8);
                }
                else if (n4 == 0) {
                    this.cursorType = ((n3 > 0) ? 11 : 10);
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
