// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Component;
import java.awt.CheckboxMenuItem;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.PopupMenu;
import java.awt.Point;
import java.awt.MenuItem;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class GraphActionHandler implements MouseListener, MouseMotionListener, KeyListener
{
    public static final int ACTION_ZOOM_OUT = 0;
    public static final int ACTION_ZOOM_IN = 1;
    public static final int ACTION_ZOOM_EXTENTS = 2;
    public static final int ACTION_RESET = 3;
    public static final int ACTION_PAN_RIGHT = 4;
    public static final int ACTION_PAN_LEFT = 5;
    public static final int ACTION_PAN_UP = 6;
    public static final int ACTION_PAN_DOWN = 7;
    public static final int ACTION_COPY = 8;
    public static final int ACTION_UNDO = 9;
    public static final int ACTION_TOGGLE_JUMP = 10;
    public static final int ACTION_AUTOUPDATE = 11;
    public static final int ACTION_UPDATE = 12;
    public static final int ACTION_TOGGLE_HIST = 13;
    public static final int ACTION_TOGGLE_MARKERS = 14;
    public final MenuItem[] ACTION_MENU_ITEMS;
    protected Point pressedPoint;
    protected Point lastDragPoint;
    protected boolean doTranslation;
    PopupMenu popupMenu;
    GraphHandler handler;
    
    public GraphActionHandler(final GraphHandler handler) {
        this.ACTION_MENU_ITEMS = new MenuItem[15];
        this.pressedPoint = new Point();
        this.lastDragPoint = new Point();
        this.doTranslation = false;
        this.popupMenu = new PopupMenu("Commands");
        this.handler = handler;
        this.ACTION_MENU_ITEMS[5] = this.createMenuItem(5, handler.getProperties().getDisplayText("Pan_left"), false);
        this.popupMenu.add(this.ACTION_MENU_ITEMS[5]);
        this.ACTION_MENU_ITEMS[4] = this.createMenuItem(4, handler.getProperties().getDisplayText("Pan_right"), false);
        this.popupMenu.add(this.ACTION_MENU_ITEMS[4]);
        this.ACTION_MENU_ITEMS[6] = this.createMenuItem(6, handler.getProperties().getDisplayText("Pan_up"), false);
        this.popupMenu.add(this.ACTION_MENU_ITEMS[6]);
        this.ACTION_MENU_ITEMS[7] = this.createMenuItem(7, handler.getProperties().getDisplayText("Pan_down"), false);
        this.popupMenu.add(this.ACTION_MENU_ITEMS[7]);
        this.ACTION_MENU_ITEMS[1] = this.createMenuItem(1, handler.getProperties().getDisplayText("Zoom_in"), false);
        this.popupMenu.add(this.ACTION_MENU_ITEMS[1]);
        this.ACTION_MENU_ITEMS[0] = this.createMenuItem(0, handler.getProperties().getDisplayText("Zoom_out"), false);
        this.popupMenu.add(this.ACTION_MENU_ITEMS[0]);
        this.ACTION_MENU_ITEMS[2] = this.createMenuItem(2, handler.getProperties().getDisplayText("Zoom_to_extents"), false);
        this.popupMenu.add(this.ACTION_MENU_ITEMS[2]);
        this.ACTION_MENU_ITEMS[3] = this.createMenuItem(3, handler.getProperties().getDisplayText("Reset_view"), false);
        this.popupMenu.add(this.ACTION_MENU_ITEMS[3]);
        this.ACTION_MENU_ITEMS[9] = this.createMenuItem(9, handler.getProperties().getDisplayText("Undo"), false);
        this.popupMenu.add(this.ACTION_MENU_ITEMS[9]);
        this.popupMenu.addSeparator();
        this.ACTION_MENU_ITEMS[10] = this.createMenuItem(10, handler.getProperties().getDisplayText("Toggle_jump"), true);
        this.popupMenu.add(this.ACTION_MENU_ITEMS[10]);
        this.ACTION_MENU_ITEMS[13] = this.createMenuItem(13, handler.getProperties().getDisplayText("Toggle_Historical_only"), true);
        this.popupMenu.add(this.ACTION_MENU_ITEMS[13]);
        this.ACTION_MENU_ITEMS[11] = this.createMenuItem(11, handler.getProperties().getDisplayText("Toggle_autoupdate"), true);
        this.popupMenu.add(this.ACTION_MENU_ITEMS[11]);
        this.ACTION_MENU_ITEMS[14] = this.createMenuItem(14, handler.getProperties().getDisplayText("Toggle_Markers"), true);
        this.popupMenu.add(this.ACTION_MENU_ITEMS[14]);
        this.popupMenu.addSeparator();
        this.ACTION_MENU_ITEMS[8] = this.createMenuItem(8, handler.getProperties().getDisplayText("Copy_data_to_clipboard"), false);
        this.popupMenu.add(this.ACTION_MENU_ITEMS[8]);
    }
    
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == 18) {
            this.handler.setTranslationCursor(new Cursor(13));
            Toolkit.getDefaultToolkit().sync();
        }
        switch (e.getKeyCode()) {
            case 10:
            case 35:
            case 82: {
                this.handleAction(3, (GraphInt)e.getSource());
                break;
            }
            case 33:
            case 90:
            case 109: {
                this.handleAction(0, (GraphInt)e.getSource());
                break;
            }
            case 34:
            case 88:
            case 107: {
                this.handleAction(1, (GraphInt)e.getSource());
                break;
            }
            case 85:
            case 155: {
                this.handleAction(11, (GraphInt)e.getSource());
                break;
            }
            case 36:
            case 69:
            case 106: {
                this.handleAction(2, (GraphInt)e.getSource());
                break;
            }
            case 39: {
                this.handleAction(4, (GraphInt)e.getSource());
                break;
            }
            case 37: {
                this.handleAction(5, (GraphInt)e.getSource());
                break;
            }
            case 38: {
                this.handleAction(6, (GraphInt)e.getSource());
                break;
            }
            case 40: {
                this.handleAction(7, (GraphInt)e.getSource());
                break;
            }
            case 3:
            case 67: {
                if (e.isControlDown()) {
                    this.handleAction(8, (GraphInt)e.getSource());
                    break;
                }
                break;
            }
            case 27: {
                this.handleAction(9, (GraphInt)e.getSource());
                break;
            }
            case 74: {
                this.handleAction(10, (GraphInt)e.getSource());
                break;
            }
            case 72: {
                this.handleAction(13, (GraphInt)e.getSource());
                break;
            }
            case 77: {
                this.handleAction(14, (GraphInt)e.getSource());
                break;
            }
            case 68: {
                if (e.isControlDown() && e.isAltDown() && e.isShiftDown()) {
                    TrendPlot.traceLevel = (TrendPlot.traceLevel + 1) % 4;
                    System.out.println("Changed traceLevel to: " + TrendPlot.traceLevel);
                    break;
                }
                break;
            }
            case 80: {
                if (e.isControlDown()) {
                    this.handler.setPaintMode(true);
                    break;
                }
                break;
            }
        }
    }
    
    public void keyReleased(final KeyEvent e) {
        this.handler.setTranslationCursor(new Cursor(0));
    }
    
    public void keyTyped(final KeyEvent e) {
    }
    
    public void mouseDragged(final MouseEvent e) {
        final GraphInt graph = (GraphInt)e.getSource();
        if (this.doTranslation) {
            this.handler.setTranslationCursor(new Cursor(13));
            final Point dragloc = e.getPoint();
            final double xdrag = graph.getHorizRange().getValueFromPixel(dragloc.x) - graph.getHorizRange().getValueFromPixel(this.lastDragPoint.x);
            final double ydrag = graph.getVertRange().getValueFromPixel(dragloc.y) - graph.getVertRange().getValueFromPixel(this.lastDragPoint.y);
            this.handler.doTranslation(xdrag, ydrag, (GraphInt)e.getSource());
            this.lastDragPoint.x = dragloc.x;
            this.lastDragPoint.y = dragloc.y;
        }
        else if (!e.isControlDown()) {
            this.drawDragRectangle(graph, this.pressedPoint, e.getPoint(), this.lastDragPoint);
        }
    }
    
    void drawDragRectangle(final GraphInt graph, final Point clickPoint, final Point dragPoint, final Point lastDragPoint) {
        final Graphics g = graph.getGraphics();
        final Color c = g.getColor();
        final Color bgColor = graph.getDataBackground();
        g.setColor(bgColor);
        this.forcePointIntoArea(clickPoint, graph.getDataBounds());
        this.forcePointIntoArea(dragPoint, graph.getDataBounds());
        final float[] hsb = Color.RGBtoHSB(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), null);
        if (hsb[2] < 0.5) {
            g.setXORMode(Color.white);
        }
        else {
            g.setXORMode(Color.black);
        }
        g.drawLine(clickPoint.x, clickPoint.y, lastDragPoint.x, clickPoint.y);
        g.drawLine(lastDragPoint.x, clickPoint.y, lastDragPoint.x, lastDragPoint.y);
        g.drawLine(lastDragPoint.x, lastDragPoint.y, clickPoint.x, lastDragPoint.y);
        g.drawLine(clickPoint.x, lastDragPoint.y, clickPoint.x, clickPoint.y);
        g.drawLine(clickPoint.x, clickPoint.y, dragPoint.x, clickPoint.y);
        g.drawLine(dragPoint.x, clickPoint.y, dragPoint.x, dragPoint.y);
        g.drawLine(dragPoint.x, dragPoint.y, clickPoint.x, dragPoint.y);
        g.drawLine(clickPoint.x, dragPoint.y, clickPoint.x, clickPoint.y);
        lastDragPoint.x = dragPoint.x;
        lastDragPoint.y = dragPoint.y;
        g.setColor(c);
    }
    
    void drawClickLabel(final GraphInt graph, final Point clickPoint) {
        final Graphics g = graph.getGraphics();
        final Color c = g.getColor();
        g.setColor(graph.getDataBackground());
        DataSet ds = null;
        DataSet dstemp = null;
        BNLogRecord brecord = null;
        double smallestDist = -1.0;
        for (int i = 0; i < graph.getNumDataSets(); ++i) {
            dstemp = graph.getDataSetAt(i);
            if (dstemp.getHistoricalSize() >= 1) {
                final BNLogRecord point = dstemp.getClosestPoint(clickPoint.x, clickPoint.y);
                final double currentDist = dstemp.getPixelDistFromPoint(point, clickPoint.x, clickPoint.y);
                if (smallestDist < 0.0 || currentDist < smallestDist) {
                    brecord = point;
                    ds = dstemp;
                    smallestDist = currentDist;
                }
            }
        }
        if (brecord == null) {
            return;
        }
        final Rectangle datarect = graph.getDataBounds();
        if (clickPoint.x >= datarect.x && clickPoint.x <= datarect.x + datarect.width && clickPoint.y >= datarect.y && clickPoint.y <= datarect.y + datarect.height) {
            graph.drawPointLabel(g, ds, brecord, clickPoint.x, clickPoint.y);
        }
        g.setColor(c);
    }
    
    void forcePointIntoArea(final Point p, final Rectangle datarect) {
        if (p.x < datarect.x) {
            p.x = datarect.x;
        }
        else if (p.x > datarect.x + datarect.width) {
            p.x = datarect.x + datarect.width;
        }
        if (p.y < datarect.y) {
            p.y = datarect.y;
        }
        else if (p.y > datarect.y + datarect.height) {
            p.y = datarect.y + datarect.height;
        }
    }
    
    public void mouseMoved(final MouseEvent e) {
    }
    
    public void mouseReleased(final MouseEvent e) {
        if (this.handler.getPaintMode()) {
            this.handler.setPaintMode(false);
        }
        final Point releasedPoint = e.getPoint();
        if (TrendPlot.traceLevel >= 3) {
            TrendPlot.trace(this.getClass(), "Mouse released at " + releasedPoint);
        }
        final GraphInt graph = (GraphInt)e.getSource();
        if (e.isControlDown()) {
            if (TrendPlot.traceLevel >= 2) {
                TrendPlot.trace(this.getClass(), "Drawing click label");
            }
            this.drawClickLabel(graph, e.getPoint());
        }
        else if (this.doTranslation) {
            if (!this.pressedPoint.equals(this.lastDragPoint)) {
                final boolean translatedLeft = this.pressedPoint.x > this.lastDragPoint.x;
                if (TrendPlot.traceLevel >= 2) {
                    if (translatedLeft) {
                        TrendPlot.trace(this.getClass(), "Mouse translate graph left");
                    }
                    else {
                        TrendPlot.trace(this.getClass(), "Mouse translate graph right");
                    }
                }
                this.handler.doSaveGraphState();
                this.handler.doAfterTranslation((GraphInt)e.getSource(), translatedLeft);
            }
            this.handler.setTranslationCursor(new Cursor(0));
            this.doTranslation = false;
        }
        else if (Math.abs(releasedPoint.x - this.pressedPoint.x) > 5 && Math.abs(this.pressedPoint.y - releasedPoint.y) > 5) {
            this.forcePointIntoArea(this.pressedPoint, graph.getDataBounds());
            this.forcePointIntoArea(releasedPoint, graph.getDataBounds());
            if (this.pressedPoint.x > releasedPoint.x) {
                final int temp = this.pressedPoint.x;
                this.pressedPoint.x = releasedPoint.x;
                releasedPoint.x = temp;
            }
            if (this.pressedPoint.y < releasedPoint.y) {
                final int temp = this.pressedPoint.y;
                this.pressedPoint.y = releasedPoint.y;
                releasedPoint.y = temp;
            }
            if (TrendPlot.traceLevel >= 2) {
                TrendPlot.trace(this.getClass(), "Mouse zoom in");
            }
            this.handler.doSaveGraphState();
            this.handler.doZoomToRange(graph.getHorizRange().getValueFromPixel(this.pressedPoint.x), graph.getHorizRange().getValueFromPixel(releasedPoint.x), graph.getVertRange().getValueFromPixel(this.pressedPoint.y), graph.getVertRange().getValueFromPixel(releasedPoint.y), (GraphInt)e.getSource());
            graph.repaint();
        }
        else if (e.isPopupTrigger()) {
            this.showRightClickMenu(e.getPoint().x, e.getPoint().y, graph);
        }
        else {
            this.handler.doRepaintGraphs();
        }
    }
    
    public void mousePressed(final MouseEvent e) {
        final GraphInt graph = (GraphInt)e.getSource();
        if (e.isPopupTrigger()) {
            this.showRightClickMenu(e.getPoint().x, e.getPoint().y, graph);
            return;
        }
        this.pressedPoint = e.getPoint();
        this.forcePointIntoArea(this.lastDragPoint = new Point(this.pressedPoint), graph.getDataBounds());
        if (e.isAltDown()) {
            this.doTranslation = true;
        }
    }
    
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public PopupMenu getPopupMenu() {
        return this.popupMenu;
    }
    
    private void showRightClickMenu(final int x, final int y, final GraphInt graph) {
        if (TrendPlot.traceLevel == 3) {
            TrendPlot.trace(this.getClass(), "Showing menu at" + new Point(x, y));
        }
        ((CheckboxMenuItem)this.ACTION_MENU_ITEMS[11]).setState(this.handler.isAutoupdateEnabled());
        ((CheckboxMenuItem)this.ACTION_MENU_ITEMS[10]).setState(this.handler.isJumpEnabled());
        ((CheckboxMenuItem)this.ACTION_MENU_ITEMS[13]).setState(this.handler.isHistoryOnlyEnabled());
        ((CheckboxMenuItem)this.ACTION_MENU_ITEMS[14]).setState(this.handler.isMarkersEnabled());
        final boolean canPanVertically = !graph.getVertRange().isValueLocked();
        this.ACTION_MENU_ITEMS[6].setEnabled(canPanVertically);
        this.ACTION_MENU_ITEMS[7].setEnabled(canPanVertically);
        this.popupMenu.show(graph, x, y);
    }
    
    private MenuItem createMenuItem(final int type, final String display, final boolean isCheckbox) {
        MenuItem item = null;
        if (isCheckbox) {
            item = new CheckboxMenuItem(display, false);
            item.setActionCommand(String.valueOf(type));
            ((CheckboxMenuItem)item).addItemListener(new ItemListener() {
                public void itemStateChanged(final ItemEvent e) {
                    if (TrendPlot.traceLevel >= 3) {
                        TrendPlot.trace(this.getClass(), "Menu item selected: " + ((MenuItem)e.getSource()).getActionCommand());
                    }
                    final int actionType = Integer.parseInt(((MenuItem)e.getSource()).getActionCommand());
                    final PopupMenu popupMenu = (PopupMenu)((MenuItem)e.getSource()).getParent();
                    final GraphInt graph = (GraphInt)popupMenu.getParent();
                    GraphActionHandler.this.handleAction(actionType, graph);
                }
            });
        }
        else {
            item = new MenuItem(display);
            item.setActionCommand(String.valueOf(type));
            item.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    if (TrendPlot.traceLevel >= 3) {
                        TrendPlot.trace(this.getClass(), "Menu item selected: " + e.getActionCommand());
                    }
                    final int actionType = Integer.parseInt(e.getActionCommand());
                    final PopupMenu popupMenu = (PopupMenu)((MenuItem)e.getSource()).getParent();
                    final GraphInt graph = (GraphInt)popupMenu.getParent();
                    GraphActionHandler.this.handleAction(actionType, graph);
                }
            });
        }
        return item;
    }
    
    private void handleAction(final int actionId, final GraphInt graph) {
        switch (actionId) {
            case 3: {
                if (TrendPlot.traceLevel >= 2) {
                    TrendPlot.trace(this.getClass(), "Reset");
                }
                this.handler.doSaveGraphState();
                this.handler.doResetGraphs();
                break;
            }
            case 0: {
                if (TrendPlot.traceLevel >= 2) {
                    TrendPlot.trace(this.getClass(), "Zoom out");
                }
                this.handler.doSaveGraphState();
                this.handler.doZoomOut(graph);
                break;
            }
            case 1: {
                if (TrendPlot.traceLevel >= 2) {
                    TrendPlot.trace(this.getClass(), "Zoom in");
                }
                this.handler.doSaveGraphState();
                this.handler.doZoomIn(graph);
                break;
            }
            case 11: {
                if (TrendPlot.traceLevel >= 2) {
                    TrendPlot.trace(this.getClass(), "Toggle Autoupdate");
                }
                this.handler.doTogglePollingCurrentData();
                break;
            }
            case 2: {
                if (TrendPlot.traceLevel >= 2) {
                    TrendPlot.trace(this.getClass(), "Zoom to extents");
                }
                this.handler.doSaveGraphState();
                this.handler.doZoomToExtents();
                break;
            }
            case 4: {
                if (TrendPlot.traceLevel >= 2) {
                    TrendPlot.trace(this.getClass(), "Pan right");
                }
                this.handler.doSaveGraphState();
                this.handler.doPan(true, true, graph);
                break;
            }
            case 5: {
                if (TrendPlot.traceLevel >= 2) {
                    TrendPlot.trace(this.getClass(), "Pan left");
                }
                this.handler.doSaveGraphState();
                this.handler.doPan(true, false, graph);
                break;
            }
            case 6: {
                if (TrendPlot.traceLevel >= 2) {
                    TrendPlot.trace(this.getClass(), "Pan up");
                }
                this.handler.doSaveGraphState();
                this.handler.doPan(false, true, graph);
                break;
            }
            case 7: {
                if (TrendPlot.traceLevel >= 2) {
                    TrendPlot.trace(this.getClass(), "Pan down");
                }
                this.handler.doSaveGraphState();
                this.handler.doPan(false, false, graph);
                break;
            }
            case 8: {
                if (TrendPlot.traceLevel >= 2) {
                    TrendPlot.trace(this.getClass(), "Copy view to clipboard");
                }
                this.handler.doCopyToClipboard();
                break;
            }
            case 9: {
                if (TrendPlot.traceLevel >= 2) {
                    TrendPlot.trace(this.getClass(), "Undo last action");
                }
                this.handler.doRetrieveGraphState();
                break;
            }
            case 10: {
                if (TrendPlot.traceLevel >= 2) {
                    TrendPlot.trace(this.getClass(), "Toggle jump panel");
                }
                this.handler.doToggleJumpPane();
                break;
            }
            case 13: {
                if (TrendPlot.traceLevel >= 2) {
                    TrendPlot.trace(this.getClass(), "Toggle historical only");
                }
                this.handler.doToggleHistoricaOnlyMode();
                break;
            }
            case 14: {
                if (TrendPlot.traceLevel >= 2) {
                    TrendPlot.trace(this.getClass(), "Toggle markers");
                }
                this.handler.doToggleMarkers();
                break;
            }
        }
    }
}
