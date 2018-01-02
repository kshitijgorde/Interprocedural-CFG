// 
// Decompiled by Procyon v0.5.30
// 

package jfig.canvas;

import java.util.Map;
import java.awt.RenderingHints;
import jfig.utils.SetupManager;
import javax.swing.JFrame;
import jfig.objects.FigAttribs;
import jfig.utils.GeometryManager;
import jfig.utils.BoundingBoxCalculator;
import java.awt.Graphics2D;
import java.util.Enumeration;
import jfig.objects.FigObject;
import jfig.objects.FigBbox;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Rectangle;
import jfig.utils.Format;
import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.util.Vector;
import jfig.gui.StatusMessage;
import jfig.gui.ConsoleMessage;
import java.awt.Font;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

public class FigSwingCanvas extends JComponent implements FigCanvas, FullRedraw, ObjectPainter, SyncPainter, MouseListener, MouseMotionListener
{
    protected static final Font rulerFont;
    public static final int PAINT_TMP_TEXT = 5;
    public static final int PAINT_NO_CHANGES = 7;
    public static final int PAINT_TOP_RULER = 13;
    public static final int PAINT_RIGHT_RULER = 14;
    public static final int PAINT_CURSOR = 15;
    public static final int PAINT_RUBBERBAND = 16;
    public static final int PAINT_OBJECTS = 27;
    public static final int PAINT_ALL = -1;
    protected FigDrawableEnumerator objectEnumerator;
    protected FigTrafo2D trafo;
    protected ConsoleMessage printer;
    protected StatusMessage helper;
    protected FigCanvasListener canvasListener;
    protected Vector zoomListenerVector;
    public Cursor waitCursor;
    public Cursor defaultCursor;
    protected FigCanvasRubberband rubberband;
    protected ImageObserver theObserver;
    protected Image offscreenImage;
    protected Image tripleBuffer;
    protected Graphics offscreenGR;
    protected Graphics objectGR;
    protected Graphics tripleBufferGR;
    protected int cur_x;
    protected int cur_y;
    protected int old_x;
    protected int old_y;
    protected int cur_width;
    protected int cur_height;
    protected Dimension XXX;
    protected Point ptmp;
    protected Point tmp_sc;
    protected Point tmp_wc;
    protected Color backgroundColor;
    protected Color gridColor;
    protected Color defaultColor;
    protected boolean gridVisible;
    protected int gridMode;
    protected int zoomFitBorderWidth;
    protected int mode;
    protected int BASE_ANCHOR_X;
    protected int BASE_ANCHOR_Y;
    protected int RULER_XL_OFFSET;
    protected int RULER_XR_OFFSET;
    protected int RULER_YT_OFFSET;
    protected int RULER_YB_OFFSET;
    int mouse_drag_x;
    int mouse_drag_y;
    protected boolean hasRulers;
    protected boolean enableRulerDragging;
    protected boolean debug;
    protected boolean Jdebug;
    protected boolean enableTimingInfo;
    protected boolean useTripleBuffering;
    int n_updates;
    int n_redraws;
    long t_redraw;
    long t_redraw_mean;
    int n_grid;
    long t_grid;
    long t_grid_mean;
    long t_delay;
    protected Format two_decimals;
    private Rectangle _lastTextClipRectangle;
    int n_trials_to_create_offscreenImage;
    private Options2D options2D;
    
    public String getSwingRepaintMode() {
        if (this.mode == 5) {
            return "PAINT_TMP_TEXT";
        }
        if (this.mode == 7) {
            return "PAINT_NO_CHANGES";
        }
        if (this.mode == 13) {
            return "PAINT_TOP_RULER";
        }
        if (this.mode == 14) {
            return "PAINT_RIGHT_RULER";
        }
        if (this.mode == 15) {
            return "PAINT_CURSOR";
        }
        if (this.mode == 16) {
            return "PAINT_RUBBERBAND";
        }
        if (this.mode == 27) {
            return "PAINT_OBJECTS";
        }
        if (this.mode == -1) {
            return "PAINT_ALL";
        }
        return "###UNKNOWN_MODE###" + this.mode;
    }
    
    public void setRulerOffsets() {
        if (this.hasRulers) {
            this.BASE_ANCHOR_X = 0;
            this.BASE_ANCHOR_Y = -781;
            this.RULER_XL_OFFSET = 0;
            this.RULER_YT_OFFSET = 26;
            this.RULER_XR_OFFSET = 26;
            this.RULER_YB_OFFSET = 0;
        }
        else {
            this.BASE_ANCHOR_X = 0;
            this.BASE_ANCHOR_Y = 0;
            this.RULER_XL_OFFSET = 0;
            this.RULER_YT_OFFSET = 0;
            this.RULER_XR_OFFSET = 0;
            this.RULER_YB_OFFSET = 0;
        }
    }
    
    public boolean isRightYRuler() {
        return true;
    }
    
    public void setDebug(final boolean debug) {
        this.debug = debug;
    }
    
    public boolean getDebug() {
        return this.debug;
    }
    
    public FigTrafo2D getTrafo() {
        return this.trafo;
    }
    
    public void setTrafo(final FigTrafo2D figTrafo2D) {
        if (this.debug) {
            this.msg("-#- FigSwingCanvas.setTrafo: " + figTrafo2D);
        }
        this.trafo = figTrafo2D;
        this.rubberband.setTrafo(figTrafo2D);
    }
    
    public Component getComponent() {
        return this;
    }
    
    public Graphics getOffscreenGraphics() {
        if (this.debug) {
            this.msg("-#- FigSwingCanvas.getOffscreenGraphics:" + this.offscreenGR);
        }
        return this.offscreenGR;
    }
    
    public void setDefaultCursor(final Cursor defaultCursor) {
        this.setCursor(this.defaultCursor = defaultCursor);
    }
    
    public void addCanvasListener(final FigCanvasListener canvasListener) {
        this.canvasListener = canvasListener;
    }
    
    public void setObjectEnumerator(final FigDrawableEnumerator objectEnumerator) {
        this.objectEnumerator = objectEnumerator;
    }
    
    public FigDrawableEnumerator getObjectEnumerator() {
        return this.objectEnumerator;
    }
    
    public void statusMessage(final String statusMessage) {
        if (this.helper != null) {
            this.helper.setStatusMessage(statusMessage);
        }
        else {
            this.msg(statusMessage);
        }
    }
    
    public void setStatusMessage(final StatusMessage helper) {
        this.helper = helper;
    }
    
    public void msg(final String s) {
        if (this.printer != null) {
            this.printer.consoleMessage(s);
        }
        else {
            System.out.println(s);
        }
    }
    
    public void setConsole(final ConsoleMessage printer) {
        this.printer = printer;
    }
    
    public void setBackground(final Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    public void setGridColor(final Color color) {
        this.gridColor = color;
        this.defaultColor = color;
    }
    
    public Color getBackground() {
        return this.backgroundColor;
    }
    
    public Color getGridColor() {
        return this.gridColor;
    }
    
    public Point getMousePosition() {
        return new Point(this.cur_x, this.cur_y);
    }
    
    public void showRulers(final boolean hasRulers) {
        this.hasRulers = hasRulers;
        this.setRulerOffsets();
    }
    
    public void setEnableRulerDragging(final boolean enableRulerDragging) {
        this.enableRulerDragging = enableRulerDragging;
    }
    
    public void setTripleBuffering(final boolean useTripleBuffering) {
        this.useTripleBuffering = useTripleBuffering;
        this.offscreenGR = null;
        this.initializeOffscreenBuffers();
        this.doFullRedraw();
        if (this.debug) {
            this.msg("-#- FigSwingCanvas.setTripleBuffering: " + this.useTripleBuffering);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.debug) {
            this.msg("-#- FigSwingCanvas: mousePressed: " + mouseEvent);
        }
        this.requestFocus();
        if (this.Jdebug) {
            System.err.println("-#- gotFocus: " + this.hasFocus());
            System.err.println("-#  isRequestFocusEnabled: " + this.isRequestFocusEnabled());
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.getCurrentCanvasSize();
        if (this.hasRulers && (y < 25 || x > this.cur_width - 25)) {
            this.mouse_drag_x = x;
            this.mouse_drag_y = y;
            if (this.debug) {
                this.msg("-#- FigSwingCanvas: doPanning at " + x + ", " + y);
            }
            if (y < 25 && x < 25) {
                this.doPanning(1, false);
            }
            else if (y < 25 && x > this.cur_width - 50 && x < this.cur_width - 25) {
                this.doPanning(2, false);
            }
            else if (x > this.cur_width - 25 && y > this.cur_height - 25) {
                this.doPanning(4, false);
            }
            else if (x > this.cur_width - 25 && y > 25 && y < 50) {
                this.doPanning(3, false);
            }
            else if (x > this.cur_width - 25 && y < 25) {
                this.trafo.doChangeUnits();
                this.doFullRedraw(20L);
            }
            return;
        }
        this.ptmp = this.trafo.screen_to_wc_snapped(x, y, this.ptmp);
        this.tmp_sc = this.trafo.wc_to_screen(this.ptmp, this.tmp_sc);
        this.cur_x = this.tmp_sc.x;
        this.cur_y = this.tmp_sc.y;
        if (this.debug) {
            this.msg("-#- snapped world coords: " + this.ptmp.x + ", " + this.ptmp.y);
        }
        if (this.canvasListener != null) {
            this.canvasListener.mousePressed(new FigCanvasEvent(mouseEvent, new Point(this.tmp_sc), new Point(this.ptmp)));
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.debug) {
            this.msg("-#- FigSwingCanvas: mouseReleased: " + mouseEvent);
        }
        if (!this.enableRulerDragging) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.mouse_drag_x == x && this.mouse_drag_y == y) {
            return;
        }
        if ((this.mouse_drag_x > this.cur_width - 25 || this.mouse_drag_y < 25) && (x > this.cur_width - 25 || y < 25)) {
            int n = this.mouse_drag_x - x;
            int n2 = this.mouse_drag_y - y;
            if (Math.abs(n) < 10) {
                n = 0;
            }
            if (Math.abs(n2) < 10) {
                n2 = 0;
            }
            if (this.debug) {
                this.msg("-#- mouseRelease: drag distance= " + n + ", " + n2);
            }
            final int screen_to_wc = this.trafo.screen_to_wc(n);
            final int screen_to_wc2 = this.trafo.screen_to_wc(n2);
            final Point point = new Point(0, 0);
            final Point anchor2;
            final Point anchor = anchor2 = this.trafo.getAnchor();
            anchor2.x += screen_to_wc;
            final Point point2 = anchor;
            point2.y += screen_to_wc2;
            this.trafo.setAnchor(anchor);
            if (this.debug) {
                this.msg("-#- new viewport anchor at (" + this.trafo.getValueInUnits(anchor.x) + ", " + this.trafo.getValueInUnits(anchor.y) + ") " + this.trafo.get_units_string());
            }
            this.doFullRedraw();
        }
        this.mouse_drag_x = x;
        this.mouse_drag_y = y;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.debug) {
            this.msg("-#- FigSwingCanvas: mouseEntered");
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.debug) {
            this.msg("-#- FigSwingCanvas: mouseMove to (" + x + ", " + y + ")");
        }
        this.ptmp = this.trafo.screen_to_wc_snapped(x, y, this.ptmp);
        this.tmp_sc = this.trafo.wc_to_screen(this.ptmp, this.tmp_sc);
        this.cur_x = this.tmp_sc.x;
        this.cur_y = this.tmp_sc.y;
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        final int min = Math.min(this.old_x, this.cur_x);
        final int min2 = Math.min(this.old_y, this.cur_y);
        final int n = Math.max(this.old_x, this.cur_x) - min + 1;
        final int n2 = Math.max(this.old_y, this.cur_y) - min2 + 1;
        if (this.Jdebug) {
            this.msg("-#- FSC.mouseMoved: " + x + " " + y);
        }
        if (this.hasRulers) {
            this.paintImmediately(min, 0, n, 11);
            if (this.isRightYRuler()) {
                this.paintImmediately(width - 26, min2, width, n2);
            }
            else {
                this.paintImmediately(0, min2, 5, n2);
            }
        }
        final Rectangle rubberBoundingBox = this.rubberband.getRubberBoundingBox(this.old_x, this.old_y, this.cur_x, this.cur_y);
        this.paintImmediately(rubberBoundingBox.x - 4, rubberBoundingBox.y - 4, rubberBoundingBox.width + 7, rubberBoundingBox.height + 7);
        this.old_x = this.cur_x;
        this.old_y = this.cur_y;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public boolean gotFocus() {
        if (this.debug) {
            this.msg("-#- FigSwingCanvas: Got the keyboard focus!");
        }
        return true;
    }
    
    public void doPanning(final int n, final boolean b) {
        this.getCurrentCanvasSize();
        this.tmp_sc.x = this.cur_width;
        this.tmp_sc.y = this.cur_height;
        this.tmp_wc = this.trafo.screen_to_wc(this.tmp_sc, this.tmp_wc);
        final Point anchor = this.trafo.getAnchor();
        if (this.debug) {
            this.msg("-#- FigSwingCanvas.doPanning: anchor=" + anchor);
        }
        final double n2 = b ? 0.4 : 0.05;
        final int n3 = (int)((this.tmp_wc.x - anchor.x) * n2);
        final int n4 = (int)((this.tmp_wc.y - anchor.y) * n2);
        switch (n) {
            case 0: {
                anchor.x = this.BASE_ANCHOR_X;
                anchor.y = this.BASE_ANCHOR_Y;
                this.trafo.setAnchor(anchor);
                break;
            }
            case 2: {
                anchor.x += n3;
                this.trafo.setAnchor(anchor);
                break;
            }
            case 1: {
                anchor.x -= n3;
                this.trafo.setAnchor(anchor);
                break;
            }
            case 4: {
                anchor.y += n4;
                this.trafo.setAnchor(anchor);
                break;
            }
            case 3: {
                anchor.y -= n4;
                this.trafo.setAnchor(anchor);
                break;
            }
            default: {
                this.msg("-E- FigSwingCanvas.doPanning: unknown direction " + n);
                break;
            }
        }
        this.doFullRedraw();
        this.notifyZoomListeners();
    }
    
    public boolean isDoubleBuffered() {
        return false;
    }
    
    public boolean isOptimizedDrawingEnabled() {
        if (this.Jdebug) {
            this.msg("-#- FSC.isOptimizedDrawingEnabled: super=" + super.isOptimizedDrawingEnabled());
        }
        return true;
    }
    
    public boolean isOpaque() {
        if (this.Jdebug) {
            this.msg("-#- FSC.isOpaque: super=" + super.isOpaque());
        }
        return true;
    }
    
    public void processKeyEvent(final KeyEvent keyEvent) {
        if (this.Jdebug) {
            System.err.println("\n### processKeyEvent: " + keyEvent);
        }
        super.processKeyEvent(keyEvent);
    }
    
    public void paint(final Graphics graphics) {
        if (this.Jdebug) {
            this.msg("-#- FSC.paint: " + graphics.getClip());
        }
        this.paintComponent(graphics);
    }
    
    public void paintBorder(final Graphics graphics) {
        if (this.Jdebug) {
            this.msg("-#- FSC.paintBorder: " + graphics.getClip());
        }
    }
    
    public void paintImmediately(final int n, final int n2, final int n3, final int n4) {
        if (this.Jdebug) {
            this.msg("-#- FSC.pI: " + n + " " + n2 + " " + n3 + " " + n4);
        }
        super.paintImmediately(n, n2, n3, n4);
    }
    
    public void paintComponent(final Graphics graphics) {
        try {
            if (this.Jdebug) {
                this.msg("-#- FSC.pC: " + this.getSwingRepaintMode() + " clip=" + graphics.getClip());
            }
            if (graphics == null) {
                return;
            }
            if (this.offscreenImage == null) {
                this.initializeOffscreenBuffers();
            }
            if (this.offscreenImage == null) {
                return;
            }
            if (this.canvasSizeChanged()) {
                this.initializeOffscreenBuffers();
                this.mode = -1;
            }
            switch (this.mode) {
                case 7:
                case 13:
                case 14:
                case 15:
                case 16: {
                    if (this.useTripleBuffering) {
                        this.paintNoChanges(this.tripleBufferGR);
                        this.blitTripleBuffer(graphics);
                    }
                    else {
                        this.paintNoChanges(graphics);
                    }
                    break;
                }
                case 5: {
                    if (this.useTripleBuffering) {
                        this.paintTmpText(this.tripleBufferGR);
                        this.blitTripleBuffer(graphics);
                    }
                    else {
                        this.paintTmpText(graphics);
                    }
                    break;
                }
                default: {
                    if (this.useTripleBuffering) {
                        this.paintAllObjects(this.tripleBufferGR);
                        this.blitTripleBuffer(graphics);
                        this.mode = 7;
                    }
                    else {
                        this.paintAllObjects(graphics);
                        this.mode = 7;
                    }
                    break;
                }
            }
        }
        catch (Exception ex) {
            this.msg("-E- internal in paintComponent: " + ex);
            ex.printStackTrace();
        }
    }
    
    public void paintNoChanges(final Graphics graphics) {
        this.blitOffscreenBuffer(graphics);
        this.drawTmpObjects(graphics);
        graphics.setColor(Color.black);
        if (this.hasRulers) {
            graphics.drawLine(this.cur_x, 5, this.cur_x, 0);
            graphics.drawLine(this.cur_width - 6, this.cur_y, this.cur_width - 1, this.cur_y);
        }
        this.rubberband.paintOnce(graphics, this.cur_x, this.cur_y);
        if (this.rubberband.getMode() != 0) {
            graphics.drawLine(this.cur_x - 3, this.cur_y, this.cur_x + 3, this.cur_y);
            graphics.drawLine(this.cur_x, this.cur_y - 3, this.cur_x, this.cur_y + 3);
        }
    }
    
    public void paintCursor(final Graphics graphics) {
        this.blitOffscreenBuffer(graphics);
        this.drawTmpObjects(graphics);
        graphics.setColor(Color.black);
        graphics.drawLine(this.cur_x - 3, this.cur_y, this.cur_x + 3, this.cur_y);
        graphics.drawLine(this.cur_x, this.cur_y - 3, this.cur_x, this.cur_y + 3);
    }
    
    public void paintRubberband(final Graphics graphics) {
        this.blitOffscreenBuffer(graphics);
        this.drawTmpObjects(graphics);
        graphics.setColor(Color.black);
        this.rubberband.paintOnce(graphics, this.cur_x, this.cur_y);
    }
    
    public void paintTmpText(final Graphics graphics) {
        if (this.objectEnumerator == null) {
            return;
        }
        final FigDrawable tmpObject = this.objectEnumerator.getTmpObject();
        if (tmpObject == null) {
            return;
        }
        this.blitOffscreenBuffer(graphics);
        tmpObject.paint(graphics);
    }
    
    public void paintAllObjects(final Graphics graphics) {
        this.initializeOffscreenBuffers();
        this.clearBuffer(this.offscreenGR);
        this.drawRulers(this.offscreenGR);
        this.drawGrid(this.offscreenGR);
        this.drawAllObjects(this.objectGR);
        this.paintNoChanges(graphics);
    }
    
    public void paint(final FigDrawable figDrawable) {
        try {
            if (this.offscreenGR != null) {
                figDrawable.paint(this.offscreenGR, this.trafo);
            }
            final Graphics graphics = this.getGraphics();
            if (graphics != null) {
                figDrawable.paint(graphics, this.trafo);
            }
        }
        catch (Throwable t) {
            System.err.println("-E- internal error in FigSwingCanvas.paint(obj): " + t);
        }
    }
    
    public void paint(final FigDrawable figDrawable, final int n) {
        try {
            if (this.offscreenGR != null) {
                figDrawable.paint(this.offscreenGR, this.trafo);
            }
            final FigBbox get_sc_bbox = figDrawable.get_sc_bbox();
            this.repaint(n, get_sc_bbox.getXl() - 10, get_sc_bbox.getYt() - 10, get_sc_bbox.getXr() - get_sc_bbox.getXl() + 20, get_sc_bbox.getYb() - get_sc_bbox.getYt() + 20);
        }
        catch (Throwable t) {
            System.err.println("-E- internal error in FigSwingCanvas.paint(obj,millis): " + t);
        }
    }
    
    public void repaint(final int n) {
        super.repaint(n);
    }
    
    public void synchronousRepaint() {
    }
    
    public void update(final Graphics graphics) {
        if (this.debug) {
            this.msg("-#- FigSwingCanvas.update: mode=" + this.mode);
        }
        ++this.n_updates;
        this.paint(graphics);
    }
    
    public void drawTmpObjects(final Graphics graphics) {
        if (this.objectEnumerator == null) {
            return;
        }
        try {
            final FigDrawable tmpObject = this.objectEnumerator.getTmpObject();
            if (tmpObject != null) {
                tmpObject.paint(graphics);
            }
        }
        catch (Throwable t) {
            System.err.println("-E- internal error in FigSwingCanvas.drawTmpObjects: " + t);
        }
    }
    
    public void clippedDrawTmpObject(final Graphics graphics) {
        if (this.objectEnumerator == null) {
            return;
        }
        try {
            final FigDrawable tmpObject = this.objectEnumerator.getTmpObject();
            if (tmpObject == null) {
                return;
            }
            final FigBbox get_sc_bbox = tmpObject.get_sc_bbox();
            graphics.setClip(get_sc_bbox.getXl() - 60, get_sc_bbox.getYt() - 30, get_sc_bbox.getXr() - get_sc_bbox.getXl() + 120, get_sc_bbox.getYb() - get_sc_bbox.getYt() + 30);
            this.blitOffscreenBuffer(graphics);
            tmpObject.paint(graphics);
        }
        catch (Throwable t) {
            System.err.println("-E- internal error in FigSwingCanvas.clippedDrawTmpObject: " + t);
        }
    }
    
    public void blitTripleBuffer(final Graphics graphics) {
        try {
            graphics.drawImage(this.tripleBuffer, 0, 0, this.cur_width, this.cur_height, 0, 0, this.cur_width, this.cur_height, this.theObserver);
        }
        catch (Exception ex) {
            this.msg("-E- FigSwingCanvas.blitTripleBuffer: " + ex);
        }
    }
    
    public void blitOffscreenBuffer(final Graphics graphics) {
        graphics.drawImage(this.offscreenImage, 0, 0, this.cur_width, this.cur_height, 0, 0, this.cur_width, this.cur_height, this.theObserver);
        if (this.debug) {
            this.msg("-#- blitOffscreen buffer, dimensions= " + this.offscreenImage.getWidth(this.theObserver) + " x " + this.offscreenImage.getHeight(this.theObserver) + " pixels");
        }
    }
    
    public void blitOffscreenBufferClipped(final Graphics graphics) {
        final Graphics create = graphics.create(this.RULER_XL_OFFSET, this.RULER_YT_OFFSET, this.cur_width - this.RULER_XL_OFFSET - this.RULER_XR_OFFSET, this.cur_height - this.RULER_YT_OFFSET - this.RULER_YB_OFFSET);
        create.translate(-this.RULER_XL_OFFSET, -this.RULER_YT_OFFSET);
        create.drawImage(this.offscreenImage, 0, 0, this.cur_width, this.cur_height, 0, 0, this.cur_width, this.cur_height, this.theObserver);
        create.dispose();
    }
    
    public void printTimingStats() {
        this.msg("-I- FigSwingCanvas redraw stats:");
        this.msg("n_redraws= " + this.n_redraws + " n_updates= " + this.n_updates);
        this.msg("t_redraw=  " + this.t_redraw + "mean time= " + this.t_redraw_mean * 1.0 / this.n_redraws);
        this.msg(" t_grid=   " + this.t_grid + " mean time= " + this.t_grid_mean * 1.0 / this.n_grid);
    }
    
    public void doFullRedraw() {
        this.doFullRedraw(this.t_delay);
    }
    
    public void doFullRedraw(final long n) {
        if (this.debug) {
            this.msg("-I- doFullRedraw: " + n + " msecs.");
        }
        this.mode = -1;
        this.repaint(n);
    }
    
    public void doSimpleRedraw() {
        this.repaint();
    }
    
    public void doTextRedraw() {
        this.mode = 5;
        if (this.objectEnumerator == null) {
            return;
        }
        final FigDrawable tmpObject = this.objectEnumerator.getTmpObject();
        if (tmpObject == null) {
            return;
        }
        final Rectangle rectangle = tmpObject.get_sc_bbox().getRectangle();
        final Rectangle union = rectangle.union(this._lastTextClipRectangle);
        this._lastTextClipRectangle = rectangle;
        this.paintImmediately(union.x - 5, union.y - 5, union.width + 11, union.height + 11);
    }
    
    public void doObjectRedraw() {
        this.msg("-E- doObjectRedraw not implemented!");
        this.doFullRedraw();
    }
    
    public void doMotionRedraw() {
        this.repaint();
    }
    
    public void doSystemRedraw() {
        if (this.canvasSizeChanged()) {
            this.doFullRedraw();
        }
        else {
            this.doSimpleRedraw();
        }
    }
    
    public void doSyncRedraw() {
        this.doSimpleRedraw();
    }
    
    public void handleRedraw(final Graphics graphics) {
        if (this.debug) {
            this.msg("-W- FigSwingCanvas.handleRedraw() should not be called!");
            try {
                throw new Exception("FigSwingCanvas.handleRedraw call stack");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        this.paintComponent(graphics);
    }
    
    public void eraseObject(final Graphics graphics, final FigObject figObject) {
        graphics.setXORMode(this.backgroundColor);
        figObject.paint(graphics);
        graphics.setPaintMode();
    }
    
    public void drawObject(final Graphics graphics, final FigObject figObject) {
        graphics.setPaintMode();
        figObject.paint(graphics);
    }
    
    public void drawSlidersAndCursor(final Graphics graphics, final boolean b) {
        graphics.setXORMode(this.backgroundColor);
        this.rubberband.paint(graphics, this.cur_x, this.cur_y, b);
        this.drawSliders(graphics, b);
        graphics.setPaintMode();
    }
    
    public void drawSlidersOnce(final Graphics graphics, final int n, final int n2) {
        graphics.drawLine(n, 5, n, 0);
        graphics.drawLine(this.cur_width - 6, n2, this.cur_width - 1, n2);
    }
    
    public void drawSliders(final Graphics graphics, final boolean b) {
        graphics.setXORMode(this.backgroundColor);
        graphics.setColor(this.gridColor);
        if (this.hasRulers) {
            if (b) {
                this.drawSlidersOnce(graphics, this.old_x, this.old_y);
            }
            this.drawSlidersOnce(graphics, this.cur_x, this.cur_y);
        }
    }
    
    public final Point getViewportWCmax() {
        return this.trafo.screen_to_wc(new Point(this.getSize().width, this.getSize().height), new Point(0, 0));
    }
    
    public FigBbox getVisibleRegionBoundingBox() {
        return new FigBbox(this.getTrafo().getWorldCoords(this.RULER_XL_OFFSET, this.RULER_YT_OFFSET), this.getTrafo().getWorldCoords(this.getSize().width - this.RULER_XR_OFFSET, this.getSize().height - this.RULER_YB_OFFSET));
    }
    
    public void drawAllObjects(final Graphics graphics) {
        if (this.debug) {
            this.msg("FigSwingCanvas.drawAllObjects...");
        }
        if (graphics == null) {
            if (this.debug) {
                this.msg("-E- FigSwingCanvas.drawAllObjects: graphics is null!");
            }
            return;
        }
        if (this.objectEnumerator == null) {
            return;
        }
        this.getCurrentCanvasSize();
        graphics.setColor(this.defaultColor);
        int n = 0;
        int n2 = 0;
        final FigBbox figBbox = new FigBbox(this.trafo.getAnchor(), this.getViewportWCmax());
        final Enumeration drawableObjects = this.objectEnumerator.getDrawableObjects();
        while (drawableObjects.hasMoreElements()) {
            final FigDrawable figDrawable = drawableObjects.nextElement();
            if (figDrawable.isVisible(figBbox)) {
                figDrawable.paint(graphics);
                ++n;
            }
            else {
                ++n2;
            }
        }
        if (this.debug) {
            System.out.println("drawAllObjects: drawn/invisible/total: " + n + " " + n2 + " " + (n + n2));
        }
    }
    
    public void setGrid(final int gridMode) {
        this.trafo.setGridMode(gridMode);
        this.doFullRedraw();
    }
    
    public void drawRulers(final Graphics graphics) {
        if (!this.hasRulers) {
            return;
        }
        if (this.debug) {
            this.msg("-#- FigSwingCanvas.drawRulers...");
        }
        double n;
        double n2;
        double n3;
        if (this.trafo.get_units() == 2) {
            n = 2400.0 / this.trafo.getZoomFactor_MultipleOfTwo();
            n2 = 240.0 / this.trafo.getZoomFactor_MultipleOfTwo();
            n3 = 4.166666666666667E-4;
        }
        else if (this.trafo.get_units() == 1) {
            n = 1920.0 / this.trafo.getZoomFactor_MultipleOfTwo();
            n2 = 192.0 / this.trafo.getZoomFactor_MultipleOfTwo();
            n3 = 0.0010416666666666667;
        }
        else {
            n = 1800.0 / this.trafo.getZoomFactor_MultipleOfTwo();
            n2 = 180.0 / this.trafo.getZoomFactor_MultipleOfTwo();
            n3 = 0.0011111111111111111;
        }
        final double n4 = 0.5 * n;
        final int n5 = (int)n;
        final int x = this.trafo.getAnchor().x;
        final int y = this.trafo.getAnchor().y;
        final Point viewportWCmax = this.getViewportWCmax();
        final int x2 = viewportWCmax.x;
        final int y2 = viewportWCmax.y;
        final int cur_width = this.cur_width;
        final int cur_height = this.cur_height;
        final int n6 = x - x % n5 - n5;
        final int n7 = x2 - x2 % n5 + n5;
        final int n8 = y - y % n5 - n5;
        final int n9 = y2 - y2 % n5 + n5;
        if (this.debug) {
            this.msg("-#- drawRulers() wxmin " + n6 + " wxmax " + n7 + " d_label " + n + " wymin " + n8 + " wymax " + n9);
        }
        final int n10 = (n6 + n7) / 2;
        final int n11 = (n8 + n9) / 2;
        final int n12 = (int)(Math.floor(n10 / n) * n);
        final int n13 = (int)(Math.floor(n11 / n) * n);
        this.trafo.wc_to_screen_x(n12);
        this.trafo.wc_to_screen_y(n13);
        graphics.setPaintMode();
        graphics.setColor(this.gridColor);
        graphics.drawLine(0, 25, cur_width, 25);
        graphics.drawLine(cur_width - 25, 0, cur_width - 25, cur_height);
        graphics.setColor(Color.lightGray);
        this.drawTriangles(graphics, cur_width, cur_height);
        graphics.setColor(this.gridColor);
        for (double n14 = n12; n14 < n7; n14 += n2) {
            final int wc_to_screen_x = this.trafo.wc_to_screen_x((int)n14);
            graphics.drawLine(wc_to_screen_x, 21, wc_to_screen_x, 25);
        }
        for (double n15 = n12; n15 < n7; n15 += n4) {
            final int wc_to_screen_x2 = this.trafo.wc_to_screen_x((int)n15);
            graphics.drawLine(wc_to_screen_x2, 18, wc_to_screen_x2, 25);
        }
        for (double n16 = n12; n16 > n6; n16 -= n2) {
            final int wc_to_screen_x3 = this.trafo.wc_to_screen_x((int)n16);
            graphics.drawLine(wc_to_screen_x3, 21, wc_to_screen_x3, 25);
        }
        for (double n17 = n12; n17 > n6; n17 -= n4) {
            final int wc_to_screen_x4 = this.trafo.wc_to_screen_x((int)n17);
            graphics.drawLine(wc_to_screen_x4, 18, wc_to_screen_x4, 25);
        }
        try {
            graphics.setFont(FigSwingCanvas.rulerFont);
            for (double n18 = n12; n18 < n7; n18 += n) {
                final int wc_to_screen_x5 = this.trafo.wc_to_screen_x((int)n18);
                graphics.drawLine(wc_to_screen_x5, 12, wc_to_screen_x5, 25);
                graphics.drawString(this.two_decimals.form(n18 * n3), wc_to_screen_x5 + 4, 16);
            }
            for (double n19 = n12; n19 > n6; n19 -= n) {
                final int wc_to_screen_x6 = this.trafo.wc_to_screen_x((int)n19);
                graphics.drawLine(wc_to_screen_x6, 12, wc_to_screen_x6, 25);
                graphics.drawString(this.two_decimals.form(n19 * n3), wc_to_screen_x6 + 4, 16);
            }
        }
        catch (Throwable t) {
            System.err.println("-E- internal in drawRulers: " + t);
        }
        try {
            for (double n20 = n13; n20 < n9; n20 += n2) {
                final int wc_to_screen_y = this.trafo.wc_to_screen_y((int)n20);
                graphics.drawLine(cur_width - 25, wc_to_screen_y, cur_width - 21, wc_to_screen_y);
            }
            for (double n21 = n13; n21 < n9; n21 += n4) {
                final int wc_to_screen_y2 = this.trafo.wc_to_screen_y((int)n21);
                graphics.drawLine(cur_width - 25, wc_to_screen_y2, cur_width - 18, wc_to_screen_y2);
            }
            for (double n22 = n13; n22 < n9; n22 += n) {
                final int wc_to_screen_y3 = this.trafo.wc_to_screen_y((int)n22);
                graphics.drawLine(cur_width - 25, wc_to_screen_y3, cur_width - 12, wc_to_screen_y3);
                final String form = this.two_decimals.form(n22 * n3);
                if (graphics instanceof Graphics2D) {
                    final Graphics2D graphics2D = (Graphics2D)graphics.create();
                    graphics2D.rotate(-1.5707963267948966, cur_width - 8, wc_to_screen_y3 - 3);
                    graphics2D.drawString(form, cur_width - 8, wc_to_screen_y3 - 3);
                }
                else {
                    graphics.drawString(form, cur_width - 16, wc_to_screen_y3 - 3);
                }
            }
            for (double n23 = n13; n23 > n8; n23 -= n2) {
                final int wc_to_screen_y4 = this.trafo.wc_to_screen_y((int)n23);
                graphics.drawLine(cur_width - 25, wc_to_screen_y4, cur_width - 21, wc_to_screen_y4);
            }
            for (double n24 = n13; n24 > n8; n24 -= n4) {
                final int wc_to_screen_y5 = this.trafo.wc_to_screen_y((int)n24);
                graphics.drawLine(cur_width - 25, wc_to_screen_y5, cur_width - 18, wc_to_screen_y5);
            }
            for (double n25 = n13; n25 > n8; n25 -= n) {
                final int wc_to_screen_y6 = this.trafo.wc_to_screen_y((int)n25);
                graphics.drawLine(cur_width - 25, wc_to_screen_y6, cur_width - 12, wc_to_screen_y6);
                final String form2 = this.two_decimals.form(n25 * n3);
                if (graphics instanceof Graphics2D) {
                    final Graphics2D graphics2D2 = (Graphics2D)graphics.create();
                    graphics2D2.rotate(-1.5707963267948966, cur_width - 8, wc_to_screen_y6 - 3);
                    graphics2D2.drawString(form2, cur_width - 8, wc_to_screen_y6 - 3);
                }
                else {
                    graphics.drawString(form2, cur_width - 16, wc_to_screen_y6 - 3);
                }
            }
        }
        catch (Throwable t2) {
            System.err.println("-E- internal in drawRulers: " + t2);
        }
        graphics.setColor(this.backgroundColor);
        graphics.fillRect(cur_width - 24, 0, 25, 25);
        graphics.setColor(this.gridColor);
        graphics.setFont(FigSwingCanvas.rulerFont);
        graphics.drawString(this.trafo.get_units_string(), cur_width - 16, 16);
        graphics.setColor(this.defaultColor);
    }
    
    public void drawTriangles(final Graphics graphics, final int n, final int n2) {
        final int[] array = new int[3];
        final int[] array2 = new int[3];
        final int[] array3 = new int[3];
        final int[] array4 = new int[3];
        final int[] array5 = new int[3];
        final int[] array6 = new int[3];
        final int[] array7 = new int[3];
        final int[] array8 = new int[3];
        graphics.setColor(Color.lightGray);
        array[0] = 5;
        array3[0] = 10;
        array[1] = 20;
        array3[1] = 1;
        array[2] = 20;
        array3[2] = 19;
        graphics.fillPolygon(array, array3, 3);
        array2[0] = n - 5 - 25;
        array4[0] = 10;
        array2[1] = n - 20 - 25;
        array4[1] = 1;
        array2[2] = n - 20 - 25;
        array4[2] = 19;
        graphics.fillPolygon(array2, array4, 3);
        array5[0] = n - 12;
        array6[0] = 30;
        array5[1] = n - 2;
        array6[1] = 45;
        array5[2] = n - 22;
        array6[2] = 45;
        graphics.fillPolygon(array5, array6, 3);
        array7[0] = n - 12;
        array8[0] = n2 - 5;
        array7[1] = n - 2;
        array8[1] = n2 - 20;
        array7[2] = n - 22;
        array8[2] = n2 - 20;
        graphics.fillPolygon(array7, array8, 3);
    }
    
    public void getCurrentCanvasSize() {
        this.cur_width = this.getSize().width;
        this.cur_height = this.getSize().height;
        if (this.debug) {
            this.msg("-I- FigSwingCanvas size: " + this.cur_width + " x " + this.cur_height);
        }
    }
    
    public void initializeOffscreenBuffers() {
        if (this.debug) {
            this.msg("-#- FigSwingCanvas.initializeOffscreenBuffers...");
        }
        ++this.n_trials_to_create_offscreenImage;
        if (this.offscreenImage == null && this.n_trials_to_create_offscreenImage > 20) {
            this.msg("-E- FigSwingCanvas: problems creating the offscreen buffer,already " + this.n_trials_to_create_offscreenImage + " tries...");
        }
        this.getCurrentCanvasSize();
        if (this.cur_width <= 0 || this.cur_height <= 0) {
            if (this.n_trials_to_create_offscreenImage > 10) {
                this.msg("-W- FigSwingCanvas: size still < 0 ");
            }
            return;
        }
        if (this.offscreenImage == null || this.offscreenGR == null || this.offscreenImage.getWidth(this.theObserver) != this.cur_width || this.offscreenImage.getHeight(this.theObserver) != this.cur_height) {
            if (this.objectGR != null) {
                this.objectGR.dispose();
            }
            if (this.offscreenGR != null) {
                this.offscreenGR.dispose();
            }
            if (this.offscreenImage != null) {
                this.offscreenImage.flush();
            }
            final int max = Math.max(this.cur_width, 1);
            final int max2 = Math.max(this.cur_height, 1);
            this.offscreenImage = this.createImage(max, max2);
            if (this.offscreenImage == null) {
                return;
            }
            this.offscreenGR = this.offscreenImage.getGraphics();
            (this.objectGR = this.offscreenGR.create(this.RULER_XL_OFFSET, this.RULER_YT_OFFSET, this.cur_width - this.RULER_XL_OFFSET - this.RULER_XR_OFFSET, this.cur_height - this.RULER_YT_OFFSET - this.RULER_YB_OFFSET)).translate(-this.RULER_XL_OFFSET, -this.RULER_YT_OFFSET);
            if (this.useTripleBuffering) {
                try {
                    this.tripleBuffer = this.createImage(max, max2);
                    this.tripleBufferGR = this.tripleBuffer.getGraphics();
                }
                catch (Exception ex) {
                    this.msg("-E- failed to create the triple buffer: " + ex);
                    this.msg("-E- falling back to normal double buffering.");
                    this.useTripleBuffering = false;
                }
            }
            if (this.options2D != null) {
                this.options2D.updateGraphics(this.objectGR);
            }
        }
        if (this.debug) {
            this.msg("-#- offscreenImage=" + this.offscreenImage);
        }
    }
    
    public boolean canvasSizeChanged() {
        if (this.offscreenImage == null) {
            return true;
        }
        this.getCurrentCanvasSize();
        return this.offscreenImage.getWidth(this.theObserver) != this.cur_width || this.offscreenImage.getHeight(this.theObserver) != this.cur_height;
    }
    
    public void flush() {
        if (this.offscreenImage != null) {
            this.offscreenImage.flush();
        }
        if (this.tripleBuffer != null) {
            this.tripleBuffer.flush();
        }
    }
    
    public void clearBuffer(final Graphics graphics) {
        graphics.setColor(this.backgroundColor);
        graphics.fillRect(0, 0, this.cur_width, this.cur_height);
        graphics.setColor(this.defaultColor);
    }
    
    public void drawGrid(final Graphics graphics) {
        this.drawGridByHand(graphics);
    }
    
    public void drawGridByHand(final Graphics graphics) {
        if (this.debug) {
            this.msg("-#- FigBasicCanvas.drawGrid()..." + graphics);
        }
        if (graphics == null) {
            return;
        }
        if (this.trafo.gridMode == 0) {
            return;
        }
        long currentTimeMillis = 0L;
        if (this.enableTimingInfo) {
            currentTimeMillis = System.currentTimeMillis();
        }
        graphics.setColor(this.gridColor);
        double n = 1.0;
        this.tmp_wc = this.trafo.screen_to_wc(new Point(this.RULER_XL_OFFSET, this.RULER_YT_OFFSET), this.tmp_wc);
        final int x = this.tmp_wc.x;
        final int y = this.tmp_wc.y;
        this.tmp_wc = this.trafo.screen_to_wc(new Point(this.cur_width - this.RULER_XR_OFFSET, this.cur_height - this.RULER_YB_OFFSET), this.tmp_wc);
        final int x2 = this.tmp_wc.x;
        final int y2 = this.tmp_wc.y;
        if (this.debug) {
            this.msg("-#- FigBasicCanvas.drawGrid(): wxmin,wymin,wxmax, wymax= " + x + " " + y + " " + x2 + " " + y2);
        }
        double n2;
        if (this.trafo.get_units() == 2) {
            n2 = 2400.0;
        }
        else if (this.trafo.get_units() == 1) {
            n2 = 1920.0;
        }
        else if (this.trafo.get_units() == 3) {
            n2 = 1800.0;
        }
        else {
            n2 = 2400.0;
        }
        final double n3 = 0.5 * n2 / this.trafo.getZoomFactor_MultipleOfTwo();
        final int n4 = (x + x2) / 2;
        final int n5 = (y + y2) / 2;
        final int n6 = (int)(Math.floor(n4 / n3) * n3);
        final int n7 = (int)(Math.floor(n5 / n3) * n3);
        this.trafo.wc_to_screen_x(n6);
        this.trafo.wc_to_screen_y(n7);
        if (this.trafo.gridMode == 240) {
            n = 0.25 * n3;
        }
        else if (this.trafo.gridMode == 480) {
            n = 0.5 * n3;
        }
        else if (this.trafo.gridMode == 960) {
            n = 1.0 * n3;
        }
        else if (this.trafo.gridMode == 961) {
            n = 0.2 * n3;
        }
        else {
            this.msg("-E- FigBasicCanvas: illegal gridMode=" + this.trafo.gridMode);
        }
        final int n8 = (int)(n3 / n);
        final int n9 = (int)this.trafo.wc_to_screen(n3);
        final Graphics create = graphics.create(this.RULER_XL_OFFSET, this.RULER_YT_OFFSET, this.cur_width - this.RULER_XL_OFFSET - this.RULER_XR_OFFSET, this.cur_height - this.RULER_YT_OFFSET - this.RULER_YB_OFFSET);
        create.translate(-this.RULER_XL_OFFSET, -this.RULER_YT_OFFSET);
        create.setColor(this.gridColor);
        for (double n10 = n7; n10 < y2; n10 += n3) {
            final int wc_to_screen_y = this.trafo.wc_to_screen_y((int)n10);
            for (double n11 = n6; n11 < x2 + n3; n11 += n) {
                final int wc_to_screen_x = this.trafo.wc_to_screen_x((int)n11);
                create.drawLine(wc_to_screen_x, wc_to_screen_y, wc_to_screen_x, wc_to_screen_y);
            }
            for (double n12 = n6; n12 >= x; n12 -= n) {
                final int wc_to_screen_x2 = this.trafo.wc_to_screen_x((int)n12);
                create.drawLine(wc_to_screen_x2, wc_to_screen_y, wc_to_screen_x2, wc_to_screen_y);
            }
        }
        for (double n13 = n7 - n3; n13 >= y; n13 -= n3) {
            final int wc_to_screen_y2 = this.trafo.wc_to_screen_y((int)n13);
            for (double n14 = n6; n14 < x2 + n3; n14 += n) {
                final int wc_to_screen_x3 = this.trafo.wc_to_screen_x((int)n14);
                create.drawLine(wc_to_screen_x3, wc_to_screen_y2, wc_to_screen_x3, wc_to_screen_y2);
            }
            for (double n15 = n6; n15 >= x; n15 -= n) {
                final int wc_to_screen_x4 = this.trafo.wc_to_screen_x((int)n15);
                create.drawLine(wc_to_screen_x4, wc_to_screen_y2, wc_to_screen_x4, wc_to_screen_y2);
            }
        }
        for (double n16 = n6; n16 < x2; n16 += n3) {
            final int wc_to_screen_x5 = this.trafo.wc_to_screen_x((int)n16);
            for (double n17 = n7; n17 < y2 + n3; n17 += n) {
                final int wc_to_screen_y3 = this.trafo.wc_to_screen_y((int)n17);
                create.drawLine(wc_to_screen_x5, wc_to_screen_y3, wc_to_screen_x5, wc_to_screen_y3);
            }
            for (double n18 = n7; n18 >= y; n18 -= n) {
                final int wc_to_screen_y4 = this.trafo.wc_to_screen_y((int)n18);
                create.drawLine(wc_to_screen_x5, wc_to_screen_y4, wc_to_screen_x5, wc_to_screen_y4);
            }
        }
        for (double n19 = n6 - n3; n19 >= x; n19 -= n3) {
            final int wc_to_screen_x6 = this.trafo.wc_to_screen_x((int)n19);
            for (double n20 = n7; n20 < y2 + n3; n20 += n) {
                final int wc_to_screen_y5 = this.trafo.wc_to_screen_y((int)n20);
                create.drawLine(wc_to_screen_x6, wc_to_screen_y5, wc_to_screen_x6, wc_to_screen_y5);
            }
            for (double n21 = n7; n21 >= y; n21 -= n) {
                final int wc_to_screen_y6 = this.trafo.wc_to_screen_y((int)n21);
                create.drawLine(wc_to_screen_x6, wc_to_screen_y6, wc_to_screen_x6, wc_to_screen_y6);
            }
        }
        create.dispose();
        if (this.enableTimingInfo) {
            this.t_grid = System.currentTimeMillis() - currentTimeMillis;
            ++this.n_grid;
            this.t_grid_mean += this.t_grid;
            if (this.Jdebug) {
                this.msg("-#- FigBasicCanvas.drawGrid took " + this.t_grid + " msec.");
            }
        }
    }
    
    public void setZoomFitBorderWidth(final int zoomFitBorderWidth) {
        this.zoomFitBorderWidth = zoomFitBorderWidth;
    }
    
    public int getZoomFitBorderWidth() {
        return this.zoomFitBorderWidth;
    }
    
    public synchronized void doZoomFit() {
        final FigBbox boundingBox = BoundingBoxCalculator.getBoundingBox(this.objectEnumerator.getDrawableObjects());
        final Dimension size = this.getSize();
        final int zoomFitBorderWidth = this.zoomFitBorderWidth;
        final int n = this.RULER_XL_OFFSET + zoomFitBorderWidth;
        final int n2 = size.width - this.RULER_XR_OFFSET - zoomFitBorderWidth;
        final int n3 = this.RULER_YT_OFFSET + zoomFitBorderWidth;
        final int n4 = size.height - this.RULER_YB_OFFSET - zoomFitBorderWidth;
        final double min = Math.min(32.0 * Math.abs(n2 - n) / (boundingBox.getXr() - boundingBox.getXl()), 32.0 * Math.abs(n4 - n3) / (boundingBox.getYb() - boundingBox.getYt()));
        this.trafo.setAnchor(new Point((int)((boundingBox.getXl() + boundingBox.getXr()) / 2 - (n2 + n) / 2 * 32.0 / min), (int)((boundingBox.getYt() + boundingBox.getYb()) / 2 - (n3 + n4) / 2 * 32.0 / min)));
        this.trafo.set_zoom(min);
        this.doFullRedraw();
        this.notifyZoomListeners();
    }
    
    public synchronized void doZoomOut() {
        if (this.debug) {
            this.msg("-#- FigSwingCanvas.doZoomOut()");
        }
        if (this.trafo.getZoomFactor() <= this.trafo.getMinZoomFactor()) {
            this.statusMessage("already at minimum scale");
            return;
        }
        final Rectangle bounds = this.getBounds();
        final Point screen_to_wc = this.trafo.screen_to_wc(new Point(bounds.width / 2, bounds.height / 2), new Point(0, 0));
        final int screen_to_wc2 = this.trafo.screen_to_wc(bounds.width);
        final int screen_to_wc3 = this.trafo.screen_to_wc(bounds.height);
        this.trafo.set_zoom_region(screen_to_wc.x - screen_to_wc2, screen_to_wc.y - screen_to_wc3, screen_to_wc.x + screen_to_wc2, screen_to_wc.y + screen_to_wc3, bounds.width, bounds.height);
        this.doFullRedraw();
        this.notifyZoomListeners();
    }
    
    public synchronized void doZoomIn() {
        if (this.debug) {
            this.msg("-#- FigSwingCanvas.doZoomIn()");
        }
        if (this.trafo.getZoomFactor() >= this.trafo.getMaxZoomFactor()) {
            this.statusMessage("already at maximum zoom factor...");
        }
        else {
            final Rectangle bounds = this.getBounds();
            final Point screen_to_wc = this.trafo.screen_to_wc(new Point(bounds.width / 4, bounds.height / 4), new Point(0, 0));
            this.trafo.set_zoom_region(screen_to_wc.x, screen_to_wc.y, screen_to_wc.x + this.trafo.screen_to_wc(bounds.width) / 2, screen_to_wc.y + this.trafo.screen_to_wc(bounds.height) / 2, bounds.width, bounds.height);
            this.doFullRedraw();
            this.notifyZoomListeners();
        }
    }
    
    public synchronized void doZoomFull() {
        if (this.debug) {
            this.msg("-#- FigSwingCanvas.doZoomFull()");
        }
        this.trafo.set_zoom(1.0);
        this.doFullRedraw();
        this.notifyZoomListeners();
    }
    
    public synchronized void doZoom11() {
        this.trafo.set_zoom(1.0);
        this.doPanning(0, false);
        this.doFullRedraw();
        this.notifyZoomListeners();
    }
    
    public synchronized void doZoomRegion(final int n, final int n2, final int n3, final int n4) {
        final Rectangle bounds = this.getBounds();
        this.trafo.set_zoom_region(n, n2, n3, n4, bounds.width, bounds.height);
        this.doFullRedraw();
        this.notifyZoomListeners();
    }
    
    public synchronized void doZoomIn14() {
        throw new Error("-E- FigSwingCanvs.doZoomIn14 not implemented!");
    }
    
    public synchronized void doZoomOut07() {
        throw new Error("-E- FigSwingCanvs.doZoomOut07 not implemented!");
    }
    
    public void addZoomListener(final ZoomListener zoomListener) {
        if (this.zoomListenerVector == null) {
            this.zoomListenerVector = new Vector();
        }
        this.zoomListenerVector.addElement(zoomListener);
    }
    
    public void removeZoomListener(final ZoomListener zoomListener) {
        if (this.zoomListenerVector == null) {
            return;
        }
        this.zoomListenerVector.removeElement(zoomListener);
    }
    
    public void notifyZoomListeners() {
        if (this.zoomListenerVector != null) {
            for (int i = 0; i < this.zoomListenerVector.size(); ++i) {
                ((ZoomListener)this.zoomListenerVector.elementAt(i)).zoomChanged(this);
            }
        }
    }
    
    public final void changeRubberbandMode(final int n) {
        this.rubberband.changeMode(n);
    }
    
    public final void changeRubberbandMode(final int n, final GeometryManager geometryManager) {
        this.rubberband.changeMode(n, geometryManager);
    }
    
    public final void changeRubberbandMode(final int n, final FigTrafo2D figTrafo2D, final Object o) {
        this.rubberband.changeMode(n, figTrafo2D, o);
    }
    
    public final void setRubberbandBasePoint(final Point point) {
        this.rubberband.setBasePoint(point.x, point.y);
    }
    
    public final void setRubberbandBasePoint(final int n, final int n2) {
        this.rubberband.setBasePoint(n, n2);
    }
    
    public final void setRubberbandBasePoint2(final Point point) {
        this.rubberband.setBasePoint2(new Point(point));
    }
    
    public final void setRubberbandBasePoint2(final int n, final int n2) {
        this.rubberband.setBasePoint2(new Point(n, n2));
    }
    
    public final void setRubberbandAspect(final double aspect) {
        this.rubberband.setAspect(aspect);
    }
    
    public void doToggleRubberbandDebug() {
        this.rubberband.setDebug(!this.rubberband.getDebug());
    }
    
    public void setRubberbandShowLineLengths(final boolean showLineLengths) {
        this.rubberband.setShowLineLengths(showLineLengths);
    }
    
    public void requestRenderQuality(final boolean b) {
        if (FigAttribs.enableJava2D) {
            this.getOptions2D().requestRenderQuality(b);
        }
    }
    
    public void requestAntiAliasing(final boolean b) {
        if (FigAttribs.enableJava2D) {
            this.getOptions2D().requestAntiAliasing(b);
        }
    }
    
    public Options2D getOptions2D() {
        if (this.options2D == null) {
            if (this == null) {
                throw null;
            }
            this.options2D = new Options2D();
        }
        return this.options2D;
    }
    
    public static void main(final String[] array) throws Exception {
        System.out.println("FigSwingCanvas self test... ");
        final FigSwingCanvas figSwingCanvas = new FigSwingCanvas();
        figSwingCanvas.setDebug(true);
        figSwingCanvas.getTrafo().setSnapRelative(1);
        figSwingCanvas.setPreferredSize(new Dimension(500, 500));
        figSwingCanvas.setMinimumSize(new Dimension(500, 300));
        figSwingCanvas.setRubberbandBasePoint(new Point(250, 250));
        figSwingCanvas.changeRubberbandMode(2);
        final JFrame frame = new JFrame("FigSwingCanvas selftest");
        frame.setSize(new Dimension(500, 400));
        frame.getContentPane().add("Center", figSwingCanvas);
        frame.show();
    }
    
    public FigSwingCanvas() {
        this.printer = null;
        this.helper = null;
        this.canvasListener = null;
        this.zoomListenerVector = null;
        this.theObserver = null;
        this.cur_x = 0;
        this.cur_y = 0;
        this.old_x = -10;
        this.old_y = -10;
        this.cur_width = 0;
        this.cur_height = 0;
        this.backgroundColor = Color.white;
        this.gridColor = Color.black;
        this.defaultColor = Color.black;
        this.zoomFitBorderWidth = 15;
        this.mode = -1;
        this.BASE_ANCHOR_X = 0;
        this.BASE_ANCHOR_Y = -781;
        this.RULER_XL_OFFSET = 0;
        this.RULER_XR_OFFSET = 26;
        this.RULER_YT_OFFSET = 26;
        this.RULER_YB_OFFSET = 0;
        this.mouse_drag_x = -1;
        this.mouse_drag_y = -1;
        this.hasRulers = true;
        this.enableRulerDragging = true;
        this.debug = false;
        this.Jdebug = false;
        this.enableTimingInfo = true;
        this.useTripleBuffering = false;
        this.n_updates = 0;
        this.n_redraws = 0;
        this.t_redraw = 0L;
        this.t_redraw_mean = 0L;
        this.n_grid = 0;
        this.t_grid = 0L;
        this.t_grid_mean = 0L;
        this.t_delay = 30L;
        this.two_decimals = new Format("%.2f");
        this._lastTextClipRectangle = new Rectangle(0, 0, 0, 0);
        this.n_trials_to_create_offscreenImage = 0;
        this.options2D = null;
        if (this.debug) {
            this.msg("-#- FigSwingCanvas constructor...");
        }
        this.setRulerOffsets();
        this.useTripleBuffering = SetupManager.getBoolean("jfig.gui.canvas.TripleBuffering", false);
        ((FigSwingCanvas)(this.theObserver = this)).setBackground(this.backgroundColor);
        this.defaultCursor = new Cursor(0);
        this.waitCursor = new Cursor(3);
        (this.trafo = new FigTrafo2D()).setAnchor(new Point(this.BASE_ANCHOR_X, this.BASE_ANCHOR_Y));
        this.initializeOffscreenBuffers();
        this.ptmp = new Point(0, 0);
        this.tmp_sc = new Point(0, 0);
        this.tmp_wc = new Point(0, 0);
        (this.rubberband = new FigCanvasRubberband(this.trafo)).changeMode(1);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.repaint();
    }
    
    static {
        rulerFont = new Font("MonoSpaced", 0, 8);
    }
    
    public class Options2D
    {
        private RenderingHints hints;
        
        public void requestAntiAliasing(final boolean b) {
            if (b) {
                this.hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            }
            else {
                this.hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            }
            this.updateGraphics(FigSwingCanvas.this.objectGR);
            FigSwingCanvas.this.doFullRedraw();
        }
        
        public void requestRenderQuality(final boolean b) {
            if (b) {
                this.hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                this.hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            }
            else {
                this.hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
                this.hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            }
            this.updateGraphics(FigSwingCanvas.this.objectGR);
            FigSwingCanvas.this.doFullRedraw();
        }
        
        public void updateGraphics(final Graphics graphics) {
            final Graphics2D graphics2D = (Graphics2D)graphics;
            if (graphics2D != null) {
                graphics2D.setRenderingHints(this.hints);
            }
        }
        
        public Options2D() {
            this.hints = null;
            this.hints = new RenderingHints(null);
        }
    }
}
