// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.draw;

import java.io.Serializable;
import java.awt.image.ImageObserver;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Image;
import edu.hws.jcm.awt.Controller;
import java.awt.Color;
import java.util.Vector;
import edu.hws.jcm.awt.Computable;
import edu.hws.jcm.awt.InputObject;
import edu.hws.jcm.awt.ErrorReporter;
import java.awt.Canvas;

public class DisplayCanvas extends Canvas implements ErrorReporter, InputObject, Computable
{
    private Vector coordinateRects;
    private boolean useOffscreenCanvas;
    private boolean handleMouseZooms;
    private Color errorBackground;
    private Color errorForeground;
    private String errorMessage;
    private Controller errorSource;
    private Draggable dragged;
    private transient boolean dragging;
    private transient boolean draggingZoomWindow;
    private transient CRData draggingInRect;
    private transient int dragXmax;
    private transient int dragXmin;
    private transient int dragYmax;
    private transient int dragYmin;
    private transient int lastX;
    private transient int lastY;
    private transient int startX;
    private transient int startY;
    private transient Image OSC;
    private transient Graphics OSG;
    private transient boolean OSCvalid;
    private transient int OSCwidth;
    private transient int OSCheight;
    
    public DisplayCanvas() {
        this.useOffscreenCanvas = true;
        this.handleMouseZooms = false;
        this.errorBackground = new Color(220, 255, 220);
        this.errorForeground = new Color(0, 120, 0);
        this.OSCwidth = -1;
        this.OSCheight = -1;
        this.setBackground(Color.white);
        this.enableEvents(48L);
    }
    
    public DisplayCanvas(final CoordinateRect coordinateRect) {
        this();
        if (coordinateRect != null) {
            this.addCoordinateRect(coordinateRect);
        }
    }
    
    public void releaseResources() {
        this.OSC = null;
        this.OSG = null;
    }
    
    public void setHandleMouseZooms(final boolean handleMouseZooms) {
        this.handleMouseZooms = handleMouseZooms;
    }
    
    public boolean getHandleMouseZooms() {
        return this.handleMouseZooms;
    }
    
    public boolean getUseOffscreenCanvas() {
        return this.useOffscreenCanvas;
    }
    
    public void setUseOffscreenCanvas(final boolean useOffscreenCanvas) {
        if (!(this.useOffscreenCanvas = useOffscreenCanvas)) {
            this.OSC = null;
            this.OSG = null;
        }
    }
    
    public void add(final Drawable drawable) {
        if (this.coordinateRects == null) {
            this.addCoordinateRect(new CoordinateRect());
        }
        this.coordinateRects.elementAt(0).coords.add(drawable);
    }
    
    public void add(final Drawable drawable, final int n) {
        if (this.coordinateRects == null || n < 0 || n >= this.coordinateRects.size()) {
            throw new IllegalArgumentException("Internal programming error:  CoordinateRect index (" + n + ")out of range.");
        }
        this.coordinateRects.elementAt(n).coords.add(drawable);
    }
    
    public void addCoordinateRect(final CoordinateRect coordinateRect) {
        this.addCoordinateRect(coordinateRect, 0.0, 1.0, 0.0, 1.0, null);
    }
    
    public void addCoordinateRect(final CoordinateRect coords, final double xmin, final double xmax, final double ymin, final double ymax, final Color background) {
        if (xmin < 0.0 || xmin > 1.0 || xmax < 0.0 || xmax > 1.0 || xmin >= xmax || ymin < 0.0 || ymin > 1.0 || ymax < 0.0 || ymax > 1.0 || ymin >= ymax) {
            throw new IllegalArgumentException("Illegal values for area covered by CoordinateRect.");
        }
        if (coords == null) {
            throw new IllegalArgumentException("Can't add null CoordinateRect to DisplayCanvas.");
        }
        final CRData crData = new CRData();
        crData.coords = coords;
        crData.xmin = xmin;
        crData.xmax = xmax;
        crData.ymin = ymin;
        crData.ymax = ymax;
        crData.background = background;
        if (this.coordinateRects == null) {
            this.coordinateRects = new Vector();
        }
        this.coordinateRects.addElement(crData);
        coords.setOwner(this);
    }
    
    public int addNewCoordinateRect(final double n, final double n2, final double n3, final double n4) {
        this.addCoordinateRect(new CoordinateRect(), n, n2, n3, n4, null);
        return this.coordinateRects.size() - 1;
    }
    
    public int addNewCoordinateRect(final double n, final double n2, final double n3, final double n4, final Color color) {
        this.addCoordinateRect(new CoordinateRect(), n, n2, n3, n4, color);
        return this.coordinateRects.size() - 1;
    }
    
    public CoordinateRect getCoordinateRect() {
        return this.getCoordinateRect(0);
    }
    
    public CoordinateRect getCoordinateRect(final int n) {
        if (n == 0 && (this.coordinateRects == null || this.coordinateRects.size() == 0)) {
            this.addNewCoordinateRect(0.0, 1.0, 0.0, 1.0);
        }
        if (this.coordinateRects == null || n < 0 || n >= this.coordinateRects.size()) {
            return null;
        }
        return this.coordinateRects.elementAt(n).coords;
    }
    
    public CoordinateRect findCoordinateRectAt(final int n, final int n2) {
        if (this.coordinateRects == null) {
            return null;
        }
        for (int i = this.coordinateRects.size() - 1; i >= 0; --i) {
            final CRData crData = this.coordinateRects.elementAt(i);
            final int width = this.getSize().width;
            if (width <= 0) {
                return null;
            }
            final int height = this.getSize().height;
            final int n3 = (int)(crData.xmin * width);
            final int n4 = (int)(crData.ymin * height);
            final int n5 = (int)(crData.xmax * width);
            final int n6 = (int)(crData.ymax * height);
            if (n >= n3 && n < n5 && n2 >= n4 && n2 < n6) {
                return crData.coords;
            }
        }
        return null;
    }
    
    public synchronized void doRedraw() {
        this.OSCvalid = false;
        if (this.errorMessage != null) {
            this.clearErrorMessage();
        }
        else {
            this.repaint();
        }
    }
    
    public synchronized void doRedraw(final int n) {
        if (this.coordinateRects != null && n >= 0 && n < this.coordinateRects.size()) {
            final CRData crData = this.coordinateRects.elementAt(n);
            this.OSCvalid = false;
            if (this.errorMessage != null) {
                this.clearErrorMessage();
            }
            else {
                final int width = this.getSize().width;
                final int height = this.getSize().height;
                final int n2 = (int)(crData.xmin * width);
                final int n3 = (int)(crData.ymin * height);
                this.repaint(n2, n3, (int)(crData.xmax * width) - n2, (int)(crData.ymax * height) - n3);
            }
        }
    }
    
    public synchronized void doRedraw(final CoordinateRect coordinateRect) {
        for (int n = (this.coordinateRects == null) ? -1 : this.coordinateRects.size(), i = 0; i < n; ++i) {
            if (((CRData)this.coordinateRects.elementAt(i)).coords == coordinateRect) {
                this.doRedraw(i);
                break;
            }
        }
    }
    
    public synchronized void checkInput() {
        if (this.coordinateRects != null) {
            for (int size = this.coordinateRects.size(), i = 0; i < size; ++i) {
                ((CRData)this.coordinateRects.elementAt(i)).coords.checkInput();
            }
        }
    }
    
    public synchronized void compute() {
        if (this.coordinateRects != null) {
            for (int size = this.coordinateRects.size(), i = 0; i < size; ++i) {
                ((CRData)this.coordinateRects.elementAt(i)).coords.compute();
            }
        }
    }
    
    public void notifyControllerOnChange(final Controller controller) {
        if (this.coordinateRects != null) {
            for (int size = this.coordinateRects.size(), i = 0; i < size; ++i) {
                ((CRData)this.coordinateRects.elementAt(i)).coords.notifyControllerOnChange(controller);
            }
        }
    }
    
    public Color getErrorBackground() {
        return this.errorBackground;
    }
    
    public void setErrorBackground(final Color errorBackground) {
        if (errorBackground != null) {
            this.errorBackground = errorBackground;
        }
    }
    
    public Color getErrorForeground() {
        return this.errorForeground;
    }
    
    public void setErrorForeground(final Color errorForeground) {
        if (errorForeground != null) {
            this.errorForeground = errorForeground;
        }
    }
    
    public synchronized String getErrorMessage() {
        return this.errorMessage;
    }
    
    public synchronized void setErrorMessage(final Controller errorSource, final String s) {
        if (s == null || s.trim().length() == 0) {
            if (this.errorMessage != null) {
                this.clearErrorMessage();
                if (this.errorSource != errorSource) {
                    this.errorSource.errorCleared();
                }
                this.repaint();
            }
        }
        else {
            this.errorMessage = s.trim();
            this.errorSource = errorSource;
            this.OSCvalid = false;
            this.repaint();
        }
    }
    
    public synchronized void clearErrorMessage() {
        if (this.errorMessage == null) {
            return;
        }
        this.errorMessage = null;
        if (this.errorSource != null) {
            this.errorSource.errorCleared();
        }
        this.errorSource = null;
        this.repaint();
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.getID() == 501) {
            this.dragging = false;
            this.dragged = null;
            if (this.errorMessage != null) {
                if (this.errorSource != null) {
                    this.errorSource.errorCleared();
                }
                this.errorSource = null;
                this.errorMessage = null;
                this.repaint();
                mouseEvent.consume();
                return;
            }
            final CoordinateRect coords = this.findCoords(mouseEvent);
            if (coords != null) {
                this.dragged = coords.checkDraggables(mouseEvent);
            }
            if (this.dragged != null) {
                return;
            }
            if (this.handleMouseZooms && mouseEvent.getClickCount() <= 1 && !mouseEvent.isAltDown() && !mouseEvent.isMetaDown() && !mouseEvent.isControlDown()) {
                super.processMouseEvent(mouseEvent);
                if (!mouseEvent.isConsumed()) {
                    this.doMouseZoom_pressed(mouseEvent);
                }
                return;
            }
        }
        else {
            if (mouseEvent.getID() == 502 && this.handleMouseZooms && this.dragged != null) {
                this.dragged.finishDrag(mouseEvent);
                this.dragged = null;
                return;
            }
            if (mouseEvent.getID() == 502 && this.handleMouseZooms && this.dragging) {
                this.doMouseZoom_released(mouseEvent);
                return;
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        if (this.dragged != null && mouseEvent.getID() == 506) {
            this.dragged.continueDrag(mouseEvent);
        }
        else if (this.dragging && mouseEvent.getID() == 506) {
            this.doMouseZoom_moved(mouseEvent);
        }
        else {
            super.processMouseMotionEvent(mouseEvent);
        }
    }
    
    private void drawErrorMessage(final Graphics graphics) {
        if (this.errorMessage == null) {
            return;
        }
        final Font font = new Font("Helvetica", 1, 12);
        final FontMetrics fontMetrics = graphics.getFontMetrics(font);
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        final int height2 = fontMetrics.getHeight();
        final int leading = fontMetrics.getLeading();
        final int n = width - 80;
        final int n2 = 30;
        int n3 = (height - 60 - height2) / height2;
        if (n3 <= 0) {
            n3 = 1;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(this.errorMessage, " \t\r\n");
        int n4 = 0;
        final String[] array = new String[n3];
        String s = "   ";
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (fontMetrics.stringWidth(nextToken) > n) {
                String string = "";
                final int stringWidth = fontMetrics.stringWidth("...");
                for (int i = 0; i < nextToken.length(); ++i) {
                    string += nextToken.charAt(i);
                    if (fontMetrics.stringWidth(string) + stringWidth > n) {
                        break;
                    }
                }
                nextToken = string;
            }
            final String string2 = s + " " + nextToken;
            if (fontMetrics.stringWidth(string2) > n) {
                array[n4] = s;
                if (++n4 == n3) {
                    break;
                }
                s = nextToken;
            }
            else {
                s = string2;
            }
        }
        if (n4 < n3) {
            array[n4] = s;
            ++n4;
        }
        if (n4 == 1) {
            final StringBuffer sb = new StringBuffer();
            final String[] array2 = array;
            final int n5 = 0;
            array2[n5] = sb.append(array2[n5]).append("    ").toString();
        }
        final int n6 = width - 60;
        final int n7 = (n4 + 1) * height2 + 50;
        int n8 = height / 2 - n7 / 2;
        if (n8 < 0) {
            n8 = 0;
        }
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(this.errorBackground);
        graphics.fillRect(n2, n8, n6, n7);
        graphics.setColor(this.errorForeground);
        graphics.drawRect(n2, n8, n6, n7);
        graphics.drawRect(n2 + 1, n8 + 1, n6 - 2, n7 - 2);
        graphics.drawLine(n2, n8 + 23 + height2, n2 + n6 - 2, n8 + 23 + height2);
        graphics.drawLine(n2, n8 + 24 + height2, n2 + n6 - 2, n8 + 24 + height2);
        graphics.setFont(font);
        graphics.drawString("ERROR MESSAGE", width / 2 - fontMetrics.stringWidth("(Error Message)") / 2, n8 + 10 + height2);
        if (n4 == 1) {
            graphics.drawString(array[0], width / 2 - fontMetrics.stringWidth(array[0]) / 2, n8 + 35 + 2 * height2);
        }
        else {
            for (int j = 0; j < n4; ++j) {
                graphics.drawString(array[j], n2 + 10, n8 + 35 + (j + 2) * height2 - leading);
            }
        }
    }
    
    private CoordinateRect findCoords(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int n = (this.coordinateRects == null) ? -1 : this.coordinateRects.size();
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        for (int i = n - 1; i >= 0; --i) {
            final CRData crData = this.coordinateRects.elementAt(i);
            final double n2 = (int)(crData.xmin * width);
            final double n3 = (int)(crData.ymin * height);
            final double n4 = (int)(crData.xmax * width) - 1;
            final double n5 = (int)(crData.ymax * height) - 1;
            if (x >= n2 && x <= n4 && y >= n3 && y <= n5) {
                return crData.coords;
            }
        }
        return null;
    }
    
    private synchronized void doMouseZoom_pressed(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() > 1 || mouseEvent.isAltDown() || mouseEvent.isMetaDown() || mouseEvent.isControlDown()) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int n = (this.coordinateRects == null) ? -1 : this.coordinateRects.size();
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        for (int i = n - 1; i >= 0; --i) {
            final CRData draggingInRect = this.coordinateRects.elementAt(i);
            this.dragXmin = (int)(draggingInRect.xmin * width);
            this.dragYmin = (int)(draggingInRect.ymin * height);
            this.dragXmax = (int)(draggingInRect.xmax * width) - 1;
            this.dragYmax = (int)(draggingInRect.ymax * height) - 1;
            if (x >= this.dragXmin && x <= this.dragXmax && y >= this.dragYmin && y <= this.dragYmax) {
                this.dragging = true;
                this.draggingZoomWindow = false;
                this.draggingInRect = draggingInRect;
                this.startX = x;
                this.startY = y;
                this.lastX = x;
                this.lastY = y;
                break;
            }
        }
    }
    
    private synchronized void doMouseZoom_released(final MouseEvent mouseEvent) {
        final Graphics graphics = this.getGraphics();
        this.putDragRect(graphics);
        graphics.dispose();
        final CoordinateRect coords = this.draggingInRect.coords;
        if ((Math.abs(this.lastX - this.startX) < 4 && Math.abs(this.lastY - this.startY) < 4) || Math.abs(this.startX - this.lastX) < 2 || Math.abs(this.startY - this.lastY) < 2) {
            if (this.draggingZoomWindow) {
                return;
            }
            if (mouseEvent.isShiftDown()) {
                coords.zoomOutFromPixel(this.startX, this.startY);
            }
            else {
                coords.zoomInOnPixel(this.startX, this.startY);
            }
        }
        else {
            coords.setLimits(coords.pixelToX(this.startX), coords.pixelToX(this.lastX), coords.pixelToY(this.startY), coords.pixelToY(this.lastY));
        }
        this.dragging = false;
    }
    
    private synchronized void doMouseZoom_moved(final MouseEvent mouseEvent) {
        final Graphics graphics = this.getGraphics();
        this.putDragRect(graphics);
        this.lastX = mouseEvent.getX();
        this.lastY = mouseEvent.getY();
        this.putDragRect(graphics);
        graphics.dispose();
    }
    
    private void putDragRect(final Graphics graphics) {
        if (this.lastX < this.dragXmin) {
            this.lastX = this.dragXmin;
        }
        if (this.lastX > this.dragXmax) {
            this.lastX = this.dragXmax;
        }
        if (this.lastY < this.dragYmin) {
            this.lastY = this.dragYmin;
        }
        if (this.lastY > this.dragYmax) {
            this.lastY = this.dragYmax;
        }
        if ((Math.abs(this.startX - this.lastX) < 4 && Math.abs(this.startY - this.lastY) < 4) || Math.abs(this.startX - this.lastX) < 2 || Math.abs(this.startY - this.lastY) < 2) {
            return;
        }
        this.draggingZoomWindow = true;
        Color xorMode = this.draggingInRect.background;
        if (xorMode == null) {
            xorMode = this.getBackground();
        }
        graphics.setXORMode(xorMode);
        if (xorMode.getRed() <= 100 && xorMode.getGreen() <= 100 && xorMode.getBlue() <= 150) {
            graphics.setColor(Color.white);
        }
        else {
            graphics.setColor(Color.black);
        }
        int n;
        int n2;
        if (this.startX < this.lastX) {
            n = this.startX;
            n2 = this.lastX - this.startX;
        }
        else {
            n = this.lastX;
            n2 = this.startX - this.lastX;
        }
        int n3;
        int n4;
        if (this.startY < this.lastY) {
            n3 = this.startY;
            n4 = this.lastY - this.startY;
        }
        else {
            n3 = this.lastY;
            n4 = this.startY - this.lastY;
        }
        graphics.drawRect(n, n3, n2, n4);
        graphics.setPaintMode();
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(350, 350);
    }
    
    private void drawCoordinateRects(final Graphics graphics, final int n, final int n2, final Rectangle clip) {
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, n, n2);
        final int n3 = (this.coordinateRects == null) ? -1 : this.coordinateRects.size();
        try {
            for (int i = 0; i < n3; ++i) {
                final CRData crData = this.coordinateRects.elementAt(i);
                final Rectangle rectangle = new Rectangle();
                rectangle.x = (int)(crData.xmin * n);
                rectangle.y = (int)(crData.ymin * n2);
                rectangle.width = (int)(crData.xmax * n) - rectangle.x;
                rectangle.height = (int)(crData.ymax * n2) - rectangle.y;
                final Rectangle clip2 = (clip == null) ? rectangle : rectangle.intersection(clip);
                if (clip == null || !clip2.isEmpty()) {
                    graphics.setClip(clip2);
                    if (crData.background != null) {
                        graphics.setColor(crData.background);
                        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                    }
                    crData.coords.draw(graphics, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                }
            }
        }
        finally {
            graphics.setClip(clip);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this.errorMessage == null) {
            try {
                this.checkOSC();
                if (this.OSC != null) {
                    graphics.drawImage(this.OSC, 0, 0, this);
                }
                else {
                    this.drawCoordinateRects(graphics, this.getSize().width, this.getSize().height, graphics.getClipBounds());
                }
                if (this.dragging) {
                    this.putDragRect(graphics);
                }
            }
            catch (RuntimeException ex) {
                this.errorMessage = "Internal Error? (stack trace on System.out):  " + ex.toString();
                ex.printStackTrace();
                graphics.setClip(0, 0, this.getSize().width, this.getSize().height);
            }
        }
        if (this.errorMessage != null) {
            this.drawErrorMessage(graphics);
            this.OSCvalid = false;
        }
    }
    
    public void drawTemp(final DrawTemp drawTemp) {
        if (this.coordinateRects == null || this.coordinateRects.size() == 0) {
            this.addCoordinateRect(new CoordinateRect());
        }
        this.drawTemp(drawTemp, 0);
    }
    
    public synchronized void drawTemp(final DrawTemp drawTemp, final int n) {
        if (n < 0 || n >= this.coordinateRects.size()) {
            throw new IllegalArgumentException("Invalid CoordinateRect index, " + n);
        }
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        final CRData crData = this.coordinateRects.elementAt(n);
        final Rectangle rectangle = new Rectangle();
        rectangle.x = (int)(crData.xmin * this.getSize().width);
        rectangle.y = (int)(crData.ymin * this.getSize().height);
        rectangle.width = (int)(crData.xmax * this.getSize().width) - rectangle.x;
        rectangle.height = (int)(crData.ymax * this.getSize().height) - rectangle.y;
        graphics.setClip(rectangle);
        drawTemp.draw(graphics, crData.coords);
        graphics.dispose();
        if (this.useOffscreenCanvas && this.OSCvalid && this.OSC != null) {
            final Graphics graphics2 = this.OSC.getGraphics();
            graphics2.setClip(rectangle);
            drawTemp.draw(graphics2, crData.coords);
            graphics2.dispose();
        }
    }
    
    private synchronized void checkOSC() {
        if (!this.useOffscreenCanvas || (this.OSCvalid && this.OSC != null && this.OSCwidth == this.getSize().width && this.OSCheight == this.getSize().height)) {
            return;
        }
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        if (this.OSC == null || width != this.OSCwidth || height != this.OSCheight) {
            this.OSCvalid = false;
            this.OSCwidth = width;
            this.OSCheight = height;
            try {
                this.OSC = this.createImage(this.OSCwidth, this.OSCheight);
                this.OSG = this.OSC.getGraphics();
            }
            catch (OutOfMemoryError outOfMemoryError) {
                this.OSC = null;
                this.OSG = null;
            }
        }
        if (this.OSC == null || this.OSCvalid) {
            return;
        }
        this.OSCvalid = true;
        this.OSG.setClip(0, 0, width, height);
        this.drawCoordinateRects(this.OSG, width, height, null);
    }
    
    private static class CRData implements Serializable
    {
        CoordinateRect coords;
        double xmin;
        double xmax;
        double ymin;
        double ymax;
        Color background;
    }
}
