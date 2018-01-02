// 
// Decompiled by Procyon v0.5.30
// 

package jfig.canvas;

import jfig.utils.GeometryManager;
import jfig.objects.FigBbox;
import jfig.objects.FigObject;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.Color;
import jfig.gui.ConsoleMessage;
import jfig.gui.StatusMessage;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public interface FigCanvas extends MouseListener, MouseMotionListener, FullRedraw, ObjectPainter, SyncPainter
{
    public static final int NoChanges = 0;
    public static final int MouseMotionRedraw = 2;
    public static final int SyncRedraw = 4;
    public static final int TmpTextRedraw = 8;
    public static final int TmpObjectRedraw = 16;
    public static final int SystemRedraw = 32;
    public static final int ObjectRedraw = 64;
    public static final int FullRedraw = 128;
    public static final int panHOME = 0;
    public static final int panLEFT = 1;
    public static final int panRIGHT = 2;
    public static final int panUP = 3;
    public static final int panDOWN = 4;
    
    void setRulerOffsets();
    
    void setDebug(final boolean p0);
    
    boolean getDebug();
    
    FigTrafo2D getTrafo();
    
    void setTrafo(final FigTrafo2D p0);
    
    Component getComponent();
    
    Graphics getOffscreenGraphics();
    
    void flush();
    
    void setDefaultCursor(final Cursor p0);
    
    void setCursor(final Cursor p0);
    
    Cursor getCursor();
    
    Dimension getSize();
    
    void addCanvasListener(final FigCanvasListener p0);
    
    void setObjectEnumerator(final FigDrawableEnumerator p0);
    
    FigDrawableEnumerator getObjectEnumerator();
    
    void statusMessage(final String p0);
    
    void setStatusMessage(final StatusMessage p0);
    
    void msg(final String p0);
    
    void setConsole(final ConsoleMessage p0);
    
    void setBackground(final Color p0);
    
    Color getBackground();
    
    void setGridColor(final Color p0);
    
    Color getGridColor();
    
    Point getMousePosition();
    
    void showRulers(final boolean p0);
    
    void mousePressed(final MouseEvent p0);
    
    void mouseReleased(final MouseEvent p0);
    
    void mouseEntered(final MouseEvent p0);
    
    void mouseExited(final MouseEvent p0);
    
    void mouseClicked(final MouseEvent p0);
    
    void mouseMoved(final MouseEvent p0);
    
    void mouseDragged(final MouseEvent p0);
    
    boolean gotFocus();
    
    void doPanning(final int p0, final boolean p1);
    
    void paint(final Graphics p0);
    
    void paint(final FigDrawable p0);
    
    void paint(final FigDrawable p0, final int p1);
    
    void repaint(final int p0);
    
    void synchronousRepaint();
    
    void update(final Graphics p0);
    
    void handleRedraw(final Graphics p0);
    
    void drawTmpObjects(final Graphics p0);
    
    void clippedDrawTmpObject(final Graphics p0);
    
    void blitOffscreenBuffer(final Graphics p0);
    
    void blitOffscreenBufferClipped(final Graphics p0);
    
    void printTimingStats();
    
    void doFullRedraw();
    
    void doFullRedraw(final long p0);
    
    void doSimpleRedraw();
    
    void doTextRedraw();
    
    void doObjectRedraw();
    
    void doMotionRedraw();
    
    void doSystemRedraw();
    
    void doSyncRedraw();
    
    void drawSlidersAndCursor(final Graphics p0, final boolean p1);
    
    void eraseObject(final Graphics p0, final FigObject p1);
    
    void drawObject(final Graphics p0, final FigObject p1);
    
    Point getViewportWCmax();
    
    FigBbox getVisibleRegionBoundingBox();
    
    void drawAllObjects(final Graphics p0);
    
    void setGrid(final int p0);
    
    void doZoomOut();
    
    void doZoomIn();
    
    void doZoomFull();
    
    void doZoom11();
    
    void doZoomRegion(final int p0, final int p1, final int p2, final int p3);
    
    void doZoomFit();
    
    void addZoomListener(final ZoomListener p0);
    
    void removeZoomListener(final ZoomListener p0);
    
    void notifyZoomListeners();
    
    void changeRubberbandMode(final int p0);
    
    void changeRubberbandMode(final int p0, final GeometryManager p1);
    
    void changeRubberbandMode(final int p0, final FigTrafo2D p1, final Object p2);
    
    void setRubberbandBasePoint(final Point p0);
    
    void setRubberbandBasePoint(final int p0, final int p1);
    
    void setRubberbandBasePoint2(final Point p0);
    
    void setRubberbandBasePoint2(final int p0, final int p1);
    
    void setRubberbandAspect(final double p0);
    
    void doToggleRubberbandDebug();
    
    void setRubberbandShowLineLengths(final boolean p0);
    
    void requestRenderQuality(final boolean p0);
    
    void requestAntiAliasing(final boolean p0);
    
    void setEnableRulerDragging(final boolean p0);
}
