// 
// Decompiled by Procyon v0.5.30
// 

package powersoft.powerj.ui;

import java.awt.Event;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import powersoft.powerj.util.Debug;
import java.awt.Point;
import java.awt.Window;
import java.awt.Image;

public class UiUtil
{
    protected static Image _offscreenBuffer;
    
    public static Point centerWindow(final Window window, final boolean onScreen) {
        Debug.assert(window != null, "window != null");
        final Dimension currSize = window.getSize();
        final Dimension screenSize = window.getToolkit().getScreenSize();
        Point parentOffset = new Point(0, 0);
        final Container parent = window.getParent();
        Dimension parentSize;
        if (onScreen || parent == null) {
            parentSize = screenSize;
        }
        else {
            parentSize = parent.getSize();
            parentOffset = parent.getLocation();
        }
        int x = parentOffset.x + (parentSize.width - currSize.width) / 2;
        int y = parentOffset.y + (parentSize.height - currSize.height) / 2;
        if (x + currSize.width > screenSize.width) {
            x = screenSize.width - currSize.width;
        }
        if (x < 0) {
            x = 0;
        }
        if (y + currSize.height > screenSize.height) {
            y = screenSize.height - currSize.height;
        }
        if (y < 0) {
            y = 0;
        }
        parentOffset.x = x;
        parentOffset.y = y;
        return parentOffset;
    }
    
    public static void waitForImage(Component comp, final Image image) {
        if (image == null) {
            return;
        }
        if (comp == null) {
            comp = new Canvas();
        }
        final MediaTracker tracker = new MediaTracker(comp);
        try {
            tracker.addImage(image, 0);
            tracker.waitForID(0);
        }
        catch (InterruptedException e) {
            return;
        }
        finally {
            try {
                tracker.removeImage(image);
            }
            catch (Exception ex) {}
        }
        for (int i = 0; i < 45 && (image.getWidth(comp) == -1 || image.getHeight(comp) == -1); ++i) {
            try {
                Thread.sleep(20);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public static void freeOffscreenBuffer() {
        if (UiUtil._offscreenBuffer != null) {
            UiUtil._offscreenBuffer = null;
        }
    }
    
    public static Image getOffscreenBuffer(final Component comp, final int minWidth, final int minHeight) {
        if (UiUtil._offscreenBuffer != null && (UiUtil._offscreenBuffer.getWidth(null) < minWidth || UiUtil._offscreenBuffer.getHeight(null) < minHeight)) {
            freeOffscreenBuffer();
        }
        if (UiUtil._offscreenBuffer == null) {
            UiUtil._offscreenBuffer = comp.createImage(minWidth, minHeight);
        }
        return UiUtil._offscreenBuffer;
    }
    
    public static void paintFocusRect(final Component comp, final Graphics g, final int x, final int y, final int width, final int height) {
        final int back = comp.getBackground().getRGB();
        final int fore = Color.black.getRGB();
        final int pixelCount = Math.max(width, height);
        final int[] pixels = new int[pixelCount];
        for (int i = 0; i < pixelCount / 2; ++i) {
            pixels[2 * i] = back;
            pixels[2 * i + 1] = fore;
        }
        ImageProducer ip = new MemoryImageSource(width, 1, pixels, 0, width);
        Image image = comp.createImage(ip);
        waitForImage(comp, image);
        g.drawImage(image, x, y, comp);
        g.drawImage(image, x, x + height - 1, comp);
        image.flush();
        ip = new MemoryImageSource(1, height, pixels, 0, 1);
        image = comp.createImage(ip);
        waitForImage(comp, image);
        g.drawImage(image, x, y, comp);
        g.drawImage(image, x + width - 1, y, comp);
        image.flush();
    }
    
    public static void drawGrayedString(final Component comp, final Graphics g, final String str, final int x, final int y) {
        final Color c = g.getColor();
        final Color background = comp.getBackground();
        g.setColor(background.brighter());
        g.drawString(str, x + 1, y + 1);
        g.setColor(background.darker());
        g.drawString(str, x, y);
        g.setColor(c);
    }
    
    public static Image createGrayedImage(final Component comp, final Image image) {
        final ImageProducer ip = new FilteredImageSource(image.getSource(), new GrayedImageFilter());
        final Image grayedImage = comp.createImage(ip);
        waitForImage(comp, grayedImage);
        return grayedImage;
    }
    
    public static String getEventDescription(final Event e, final boolean verbose) {
        String str = null;
        switch (e.id) {
            case 1001: {
                str = "ACTION_EVENT";
                break;
            }
            case 1004: {
                str = "GOT_FOCUS";
                break;
            }
            case 1005: {
                str = "LOST_FOCUS";
                break;
            }
            case 403: {
                str = "KEY_ACTION";
                break;
            }
            case 404: {
                str = "KEY_ACTION_RELEASE";
                break;
            }
            case 401: {
                str = "KEY_PRESS";
                break;
            }
            case 402: {
                str = "KEY_RELEASE";
                break;
            }
            case 701: {
                str = "LIST_SELECT";
                break;
            }
            case 702: {
                str = "LIST_DESELECT";
                break;
            }
            case 1002: {
                str = "LOAD_FILE";
                break;
            }
            case 1003: {
                str = "SAVE_FILE";
                break;
            }
            case 501: {
                str = "MOUSE_DOWN";
                break;
            }
            case 502: {
                str = "MOUSE_UP";
                break;
            }
            case 506: {
                str = "MOUSE_DRAG";
                break;
            }
            case 503: {
                str = "MOUSE_MOVE";
                break;
            }
            case 504: {
                str = "MOUSE_ENTER";
                break;
            }
            case 505: {
                str = "MOUSE_EXIT";
                break;
            }
            case 201: {
                str = "WINDOW_DESTROY";
                break;
            }
            case 605: {
                str = "SCROLL_ABSOLUTE";
                break;
            }
            case 602: {
                str = "SCROLL_LINE_DOWN";
                break;
            }
            case 601: {
                str = "SCROLL_LINE_UP";
                break;
            }
            case 604: {
                str = "SCROLL_PAGE_DOWN";
                break;
            }
            case 603: {
                str = "SCROLL_PAGE_UP";
                break;
            }
            case 204: {
                str = "WINDOW_DEICONIFY";
                break;
            }
            case 202: {
                str = "WINDOW_EXPOSE";
                break;
            }
            case 203: {
                str = "WINDOW_ICONIFY";
                break;
            }
            case 205: {
                str = "WINDOW_MOVED";
                break;
            }
            default: {
                str = "**UNKNOWN**";
                break;
            }
        }
        str = String.valueOf(str) + "[";
        if (verbose) {
            str = String.valueOf(str) + (String.valueOf(e.id) + ",x=" + e.x + ",y=" + e.y);
            str = String.valueOf(str) + (",key=" + e.key);
            if (e.shiftDown()) {
                str = String.valueOf(str) + ",shift";
            }
            if (e.controlDown()) {
                str = String.valueOf(str) + ",control";
            }
            if (e.metaDown()) {
                str = String.valueOf(str) + ",meta";
            }
            str = String.valueOf(str) + (",target=" + ((e.target == null) ? "null" : e.target));
            str = String.valueOf(str) + (",arg=" + ((e.arg == null) ? "null" : e.arg));
        }
        else {
            str = String.valueOf(str) + ("target=" + ((e.target == null) ? "null" : e.target.getClass().getName()));
        }
        str = String.valueOf(str) + "]";
        return str;
    }
    
    static {
        UiUtil._offscreenBuffer = null;
    }
}
