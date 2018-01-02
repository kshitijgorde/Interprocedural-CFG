// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.awt.Dimension;
import com.itt.J2KViewer.controller.PropertyChangeEventMediator;
import java.awt.geom.Point2D;
import java.awt.Component;
import java.awt.Rectangle;
import com.itt.J2KViewer.controller.WaypointManager;
import com.itt.J2KViewer.util.ImageUtils;
import com.itt.J2KViewer.controller.DimensionManager;
import java.beans.PropertyVetoException;
import java.awt.event.MouseWheelEvent;
import com.itt.J2KViewer.controller.PropertyManager;
import java.awt.event.MouseEvent;
import com.itt.J2KViewer.util.Helper;
import java.awt.Toolkit;
import com.itt.J2KViewer.util.PolarRectConversion;
import java.awt.Cursor;
import java.awt.Point;
import com.itt.J2KViewer.controller.ViewCentral;
import com.itt.J2KViewer.util.Log;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;

public class ImageDisplayMouseHandler extends MouseAdapter implements MouseMotionListener, MouseWheelListener
{
    private static Log log;
    private ViewCentral viewCentral;
    private Point initialPanLocation;
    private Point currentPanOffset;
    private Point cumulativePanOffset;
    private ContextMenu contextMenu;
    private double initialRotationAngle;
    private double initialRotationMouseAngle;
    private double currentRotationAngle;
    private Cursor[] flyCursors;
    private PolarRectConversion m_polarRectConv;
    private boolean cropping;
    private static final int startXY = 0;
    private static final int endXstartY = 1;
    private static final int endXY = 2;
    private static final int startXendY = 3;
    private static final int startX = 4;
    private static final int startY = 5;
    private static final int endX = 6;
    private static final int endY = 7;
    private static final String NONE = "none";
    private static final String CORNER = "corner";
    private static final String SIDE = "side";
    private int closestFeature;
    private String proximity;
    static /* synthetic */ Class class$com$itt$J2KViewer$gui$ImageDisplayMouseHandler;
    
    public ImageDisplayMouseHandler(final ViewCentral viewCentral) {
        this.currentPanOffset = new Point(0, 0);
        this.cumulativePanOffset = new Point(0, 0);
        this.contextMenu = null;
        this.initialRotationAngle = 0.0;
        this.initialRotationMouseAngle = 0.0;
        this.currentRotationAngle = 0.0;
        this.flyCursors = new Cursor[8];
        this.cropping = false;
        this.proximity = "none";
        this.viewCentral = viewCentral;
        this.m_polarRectConv = new PolarRectConversion();
        int n = 0;
        this.flyCursors[n++] = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(Helper.getURLAsResource("fly/arrownorth32.gif")), new Point(16, 8), "n");
        this.flyCursors[n++] = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(Helper.getURLAsResource("fly/arrownortheast32.gif")), new Point(0, 8), "ne");
        this.flyCursors[n++] = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(Helper.getURLAsResource("fly/arroweast32.gif")), new Point(0, 8), "e");
        this.flyCursors[n++] = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(Helper.getURLAsResource("fly/arrowsoutheast32.gif")), new Point(0, 8), "se");
        this.flyCursors[n++] = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(Helper.getURLAsResource("fly/arrowsouth32.gif")), new Point(0, 8), "s");
        this.flyCursors[n++] = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(Helper.getURLAsResource("fly/arrowsouthwest32.gif")), new Point(0, 8), "sw");
        this.flyCursors[n++] = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(Helper.getURLAsResource("fly/arrowwest32.gif")), new Point(0, 8), "w");
        this.flyCursors[n++] = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(Helper.getURLAsResource("fly/arrownorthwest32.gif")), new Point(0, 8), "nw");
    }
    
    private int getFlyingSpeedPercentage(final MouseEvent mouseEvent) {
        final MainImagePanel mainImagePanel = this.viewCentral.getMainImagePanel();
        final int n = mainImagePanel.getDisplayWidth() / 2;
        final int n2 = mainImagePanel.getDisplayHeight() / 2;
        final int n3 = mouseEvent.getX() - n;
        final int n4 = mouseEvent.getY() - n2;
        final double n5 = Math.sqrt(n3 * n3 + n4 * n4) / Math.sqrt(n * n + n2 * n2);
        return (int)(100.0 * (2.0 * n5 - n5 * n5));
    }
    
    private int handleMousePositionInFlyMode(final PropertyManager propertyManager, final MouseEvent mouseEvent) {
        final int displayWidth = this.viewCentral.getMainImagePanel().getDisplayWidth();
        final int displayHeight = this.viewCentral.getMainImagePanel().getDisplayHeight();
        this.m_polarRectConv.setWidth(displayWidth);
        this.m_polarRectConv.setHeight(displayHeight);
        this.m_polarRectConv.setRectangular(mouseEvent.getX(), mouseEvent.getY());
        final int quadrant = this.m_polarRectConv.getQuadrant();
        this.viewCentral.getMainImagePanel().getImageDisplayPanel().setCursor(this.flyCursors[quadrant]);
        return quadrant;
    }
    
    public void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final int discardedZoomLevels = propertyManager.getDiscardedZoomLevels();
        int n = propertyManager.getMaxZoomOutLevel();
        if (n < 0) {
            n = propertyManager.getMaxDiscardedZoomLevels();
        }
        final int wheelRotation = mouseWheelEvent.getWheelRotation();
        try {
            if (wheelRotation < 0) {
                if (discardedZoomLevels > -2) {
                    this.viewCentral.getPropertyManager().setDiscardedZoomLevels(discardedZoomLevels - 1);
                    this.viewCentral.viewChanged();
                }
            }
            else if (discardedZoomLevels < n) {
                this.viewCentral.getPropertyManager().setDiscardedZoomLevels(discardedZoomLevels + 1);
                this.viewCentral.viewChanged();
            }
        }
        catch (PropertyVetoException ex) {}
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
        if ((mouseEvent.getModifiers() & 0x10) != 0x0 && propertyManager != null && propertyManager.isOpen() && propertyManager.getTransformationMode() == 8) {
            if (propertyManager.isCropping()) {
                if (this.proximity.equals("corner")) {
                    this.getClosestCorner(new Point(mouseEvent.getX(), mouseEvent.getY()));
                }
                else if (this.proximity.equals("side")) {
                    this.getClosestSide(new Point(mouseEvent.getX(), mouseEvent.getY()));
                }
            }
            else {
                try {
                    propertyManager.setChipStart(null);
                    propertyManager.setChipEnd(null);
                }
                catch (PropertyVetoException ex) {
                    ImageDisplayMouseHandler.log.error(ex);
                }
                final Point displayWindowToImage = dimensionManager.displayWindowToImage(new Point(mouseEvent.getX(), mouseEvent.getY()));
                if (displayWindowToImage != null) {
                    try {
                        propertyManager.setChipStart(new Point(displayWindowToImage.x, displayWindowToImage.y));
                    }
                    catch (PropertyVetoException ex2) {
                        ImageDisplayMouseHandler.log.error(ex2);
                    }
                }
            }
        }
        if (propertyManager != null && propertyManager.isOpen() && propertyManager.getTransformationMode() == 7 && (mouseEvent.getModifiers() & 0x10) != 0x0 && this.viewCentral.isMainImageUpdated()) {
            this.viewCentral.startFlying(this.handleMousePositionInFlyMode(propertyManager, mouseEvent), this.getFlyingSpeedPercentage(mouseEvent));
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
        final WaypointManager waypointManager = this.viewCentral.getWaypointManager();
        final MainImagePanel mainImagePanel = this.viewCentral.getMainImagePanel();
        final int discardedZoomLevels = propertyManager.getDiscardedZoomLevels();
        if (propertyManager != null && propertyManager.isOpen() && propertyManager.getTransformationMode() != 6) {
            if (mouseEvent.getClickCount() == 1 && mouseEvent.getButton() == 1) {
                final int x = mouseEvent.getX();
                final int y = mouseEvent.getY();
                final Rectangle displayWindow = dimensionManager.getDisplayWindow();
                final Point point = new Point(displayWindow.x + ImageUtils.zoomedToFull(x, discardedZoomLevels), displayWindow.y + ImageUtils.zoomedToFull(y, discardedZoomLevels));
                final int transformationMode = this.viewCentral.getPropertyManager().getTransformationMode();
                int n = propertyManager.getMaxZoomOutLevel();
                if (n < 0) {
                    n = propertyManager.getMaxDiscardedZoomLevels();
                }
                try {
                    switch (transformationMode) {
                        case 1: {
                            if (discardedZoomLevels > -2) {
                                dimensionManager.setDisplayWindowCenter(point);
                                this.viewCentral.getPropertyManager().setDiscardedZoomLevels(discardedZoomLevels - 1);
                                this.viewCentral.viewChanged();
                                break;
                            }
                            break;
                        }
                        case 2: {
                            if (discardedZoomLevels < n) {
                                dimensionManager.setDisplayWindowCenter(point);
                                this.viewCentral.getPropertyManager().setDiscardedZoomLevels(discardedZoomLevels + 1);
                                this.viewCentral.viewChanged();
                                break;
                            }
                            break;
                        }
                        case 4: {
                            waypointManager.addWaypoint(new Point(x, y));
                            mainImagePanel.getImageDisplayPanel().repaint();
                            break;
                        }
                    }
                }
                catch (PropertyVetoException ex) {
                    ImageDisplayMouseHandler.log.error(ex);
                }
            }
            else if (mouseEvent.getClickCount() == 2) {}
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
        final MainImagePanel mainImagePanel = this.viewCentral.getMainImagePanel();
        propertyManager.setMouseReleased(new Point(mouseEvent.getX(), mouseEvent.getY()));
        if (mouseEvent.isPopupTrigger() && this.viewCentral.getPropertyManager().isShowContextMenu()) {
            if (this.contextMenu != null) {
                mainImagePanel.setPopupTriggerPoint(new Point(mouseEvent.getX(), mouseEvent.getY()));
                this.contextMenu.show(mainImagePanel, mouseEvent.getX(), mouseEvent.getY());
            }
            else {
                ImageDisplayMouseHandler.log.error("ContextMenu is null. Make sure it has been set during ImagePanel setup.");
            }
            return;
        }
        if (propertyManager != null && propertyManager.isOpen()) {
            if (this.initialPanLocation != null) {
                if (propertyManager.getTransformationMode() == 3) {
                    this.viewCentral.getPropertyManager().setRotationAngle(this.initialRotationAngle + this.currentRotationAngle);
                    this.initialPanLocation = null;
                    this.initialRotationAngle = 0.0;
                    this.initialRotationMouseAngle = 0.0;
                    this.viewCentral.setPanning(false);
                    propertyManager.setInitialImageLoad(false);
                    this.viewCentral.viewChanged();
                }
                else if (propertyManager.getTransformationMode() == 0) {
                    propertyManager.setInitialImageLoad(false);
                    this.dragImageFinish();
                }
            }
            if (propertyManager != null && propertyManager.isOpen() && propertyManager.getTransformationMode() == 7) {
                this.viewCentral.stopFlying();
            }
            if ((mouseEvent.getModifiers() & 0x10) != 0x0 && propertyManager.getTransformationMode() == 8 && propertyManager.getChipStart() != null) {
                if (!propertyManager.isCropping()) {
                    final Point displayWindowToImage = dimensionManager.displayWindowToImage(new Point(mouseEvent.getX(), mouseEvent.getY()));
                    final Point chipStart = propertyManager.getChipStart();
                    if (displayWindowToImage.x != chipStart.x) {
                        if (displayWindowToImage.y != chipStart.y) {
                            if (displayWindowToImage != null) {
                                try {
                                    propertyManager.setChipEnd(new Point(displayWindowToImage.x, displayWindowToImage.y));
                                    propertyManager.setCropping(true);
                                }
                                catch (PropertyVetoException ex) {
                                    ImageDisplayMouseHandler.log.error(ex);
                                }
                                catch (Exception ex2) {
                                    ImageDisplayMouseHandler.log.error(ex2);
                                }
                            }
                            return;
                        }
                    }
                    try {
                        this.viewCentral.doCloseChipperDialog();
                        propertyManager.setChipStart(null);
                        propertyManager.setChipEnd(null);
                    }
                    catch (PropertyVetoException ex3) {
                        ImageDisplayMouseHandler.log.error(ex3);
                    }
                }
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
        if (propertyManager != null && propertyManager.isOpen() && propertyManager.getTransformationMode() == 7 && (mouseEvent.getModifiers() & 0x10) != 0x0) {
            final int handleMousePositionInFlyMode = this.handleMousePositionInFlyMode(propertyManager, mouseEvent);
            this.viewCentral.setFlyingSpeedPercentage(this.getFlyingSpeedPercentage(mouseEvent));
            this.viewCentral.setFlyingQuadrant(handleMousePositionInFlyMode);
        }
        if (propertyManager.isAllowPan() && propertyManager != null && propertyManager.isOpen() && (propertyManager.getTransformationMode() == 0 || propertyManager.getTransformationMode() == 3) && (mouseEvent.getModifiers() & 0x10) != 0x0 && propertyManager.getTransformationMode() != 6) {
            final MainImagePanel mainImagePanel = this.viewCentral.getMainImagePanel();
            final int n = mainImagePanel.getDisplayWidth() / 2;
            final int n2 = mainImagePanel.getDisplayHeight() / 2;
            if (this.initialPanLocation == null) {
                this.initialPanLocation = mouseEvent.getPoint();
                this.initialRotationAngle = dimensionManager.getRotationAngle();
                this.initialRotationMouseAngle = Math.atan2(mouseEvent.getX() - n, n2 - mouseEvent.getY());
                this.viewCentral.setPanning(true);
            }
            else if (propertyManager.getTransformationMode() == 3) {
                final double currentRotationAngle = Math.atan2(mouseEvent.getX() - n, n2 - mouseEvent.getY()) - this.initialRotationMouseAngle;
                this.currentRotationAngle = currentRotationAngle;
                mainImagePanel.drawImageAt(new Point(0, 0), currentRotationAngle);
            }
            else if (propertyManager.getTransformationMode() == 0) {
                this.dragImage(mouseEvent.getPoint());
            }
        }
        if ((mouseEvent.getModifiers() & 0x10) != 0x0 && propertyManager.getTransformationMode() == 8 && propertyManager.getChipStart() != null) {
            final Point displayWindowToImage = dimensionManager.displayWindowToImage(new Point(mouseEvent.getX(), mouseEvent.getY()));
            if (displayWindowToImage != null) {
                try {
                    if (propertyManager.isCropping() && !this.proximity.equals("none")) {
                        final Point chipStart = propertyManager.getChipStart();
                        final Point chipEnd = propertyManager.getChipEnd();
                        switch (this.closestFeature) {
                            case 0: {
                                propertyManager.setChipStart(new Point(displayWindowToImage.x, displayWindowToImage.y));
                                break;
                            }
                            case 1: {
                                propertyManager.setChipStart(new Point(chipStart.x, displayWindowToImage.y));
                                propertyManager.setChipEnd(new Point(displayWindowToImage.x, chipEnd.y));
                                break;
                            }
                            case 3: {
                                propertyManager.setChipStart(new Point(displayWindowToImage.x, chipStart.y));
                                propertyManager.setChipEnd(new Point(chipEnd.x, displayWindowToImage.y));
                                break;
                            }
                            case 2: {
                                propertyManager.setChipEnd(new Point(displayWindowToImage.x, displayWindowToImage.y));
                                break;
                            }
                            case 4: {
                                propertyManager.setChipStart(new Point(displayWindowToImage.x, chipStart.y));
                                break;
                            }
                            case 5: {
                                propertyManager.setChipStart(new Point(chipStart.x, displayWindowToImage.y));
                                break;
                            }
                            case 6: {
                                propertyManager.setChipEnd(new Point(displayWindowToImage.x, chipEnd.y));
                                break;
                            }
                            case 7: {
                                propertyManager.setChipEnd(new Point(chipEnd.x, displayWindowToImage.y));
                                break;
                            }
                        }
                    }
                    else if (!propertyManager.isCropping()) {
                        propertyManager.setChipEnd(new Point(displayWindowToImage.x, displayWindowToImage.y));
                    }
                }
                catch (PropertyVetoException ex) {
                    ImageDisplayMouseHandler.log.error(ex);
                }
            }
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
        if (propertyManager != null && propertyManager.isOpen()) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            if (propertyManager.getTransformationMode() == 7) {
                this.handleMousePositionInFlyMode(propertyManager, mouseEvent);
            }
            Point point = new Point(-1, -1);
            Object rawDataValues = null;
            Object rgbValues = null;
            if (propertyManager.getTransformationMode() != 6 || !this.viewCentral.getMainImagePanel().getImageDisplayPanel().getLensShape().contains(new Point(x, y))) {
                final int discardedZoomLevels = propertyManager.getDiscardedZoomLevels();
                final Point displayWindowToImage = dimensionManager.displayWindowToImage(new Point(x, y));
                final Dimension imageSize = dimensionManager.getImageSize(0);
                if (displayWindowToImage.x >= 0 && displayWindowToImage.y >= 0 && displayWindowToImage.x < imageSize.width && displayWindowToImage.y < imageSize.height) {
                    point = new Point(displayWindowToImage);
                    final Rectangle requestWindow = dimensionManager.getRequestWindow();
                    if (requestWindow != null) {
                        final Point point2 = new Point(ImageUtils.fullToZoomed(displayWindowToImage.x - requestWindow.x, discardedZoomLevels), ImageUtils.fullToZoomed(displayWindowToImage.y - requestWindow.y, discardedZoomLevels));
                        int x2;
                        int y2;
                        if (discardedZoomLevels < 0) {
                            x2 = displayWindowToImage.x - requestWindow.x;
                            y2 = displayWindowToImage.y - requestWindow.y;
                        }
                        else {
                            x2 = point2.x;
                            y2 = point2.y;
                        }
                        rawDataValues = this.viewCentral.getImageStream().getRawDataValues(x2, y2);
                        rgbValues = this.viewCentral.getImageStream().getRGBValues(x, y);
                    }
                }
                final PropertyChangeEventMediator eventController = this.viewCentral.eventController();
                eventController.firePropertyChange(PropertyChangeEventMediator.createPropertyChangeEvent(this, "MouseMoved", point, point));
                eventController.firePropertyChange(PropertyChangeEventMediator.createPropertyChangeEvent(this, "DataValuesChanged", rawDataValues, rawDataValues));
                eventController.firePropertyChange(PropertyChangeEventMediator.createPropertyChangeEvent(this, "RGBValuesChanged", rgbValues, rgbValues));
            }
            if (propertyManager.getTransformationMode() == 8 && propertyManager.isCropping()) {
                this.proximity = this.checkProximity(new Point(x, y));
            }
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        if (propertyManager != null && propertyManager.isOpen() && propertyManager.getTransformationMode() != 6) {
            final PropertyChangeEventMediator eventController = this.viewCentral.eventController();
            final Point point = new Point(-1, -1);
            final Object o = null;
            final Object o2 = null;
            eventController.firePropertyChange(PropertyChangeEventMediator.createPropertyChangeEvent(this, "MouseMoved", point, point));
            eventController.firePropertyChange(PropertyChangeEventMediator.createPropertyChangeEvent(this, "DataValuesChanged", o, o));
            eventController.firePropertyChange(PropertyChangeEventMediator.createPropertyChangeEvent(this, "RGBValuesChanged", o2, o2));
        }
    }
    
    public void dragImage(final Point point) {
        if (this.initialPanLocation == null) {
            this.initialPanLocation = new Point(point);
        }
        else {
            final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
            final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
            final MainImagePanel mainImagePanel = this.viewCentral.getMainImagePanel();
            final int discardedZoomLevels = propertyManager.getDiscardedZoomLevels();
            final Point panOffset = mainImagePanel.getPanOffset();
            final Point displayWindowCenter = dimensionManager.getDisplayWindowCenter();
            final Point adjustedDisplayWindowCenter = dimensionManager.getAdjustedDisplayWindowCenter(new Point(displayWindowCenter.x + ImageUtils.zoomedToFull(this.initialPanLocation.x - point.x, discardedZoomLevels), displayWindowCenter.y + ImageUtils.zoomedToFull(this.initialPanLocation.y - point.y, discardedZoomLevels)));
            final int fullToZoomed = ImageUtils.fullToZoomed(displayWindowCenter.x - adjustedDisplayWindowCenter.x, discardedZoomLevels);
            final int fullToZoomed2 = ImageUtils.fullToZoomed(displayWindowCenter.y - adjustedDisplayWindowCenter.y, discardedZoomLevels);
            this.currentPanOffset = new Point(fullToZoomed, fullToZoomed2);
            if (fullToZoomed != 0 || fullToZoomed2 != 0) {
                this.cumulativePanOffset.x = panOffset.x + fullToZoomed;
                this.cumulativePanOffset.y = panOffset.y + fullToZoomed2;
                mainImagePanel.drawImageAt(this.cumulativePanOffset, 0.0);
            }
        }
    }
    
    public void dragImageFinish() {
        final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        if (this.cumulativePanOffset.x != 0 || this.cumulativePanOffset.y != 0) {
            final Point displayWindowCenter = dimensionManager.getDisplayWindowCenter();
            final int discardedZoomLevels = propertyManager.getDiscardedZoomLevels();
            dimensionManager.setDisplayWindowCenter(new Point(displayWindowCenter.x - ImageUtils.zoomedToFull(this.currentPanOffset.x, discardedZoomLevels), displayWindowCenter.y - ImageUtils.zoomedToFull(this.currentPanOffset.y, discardedZoomLevels)));
        }
        this.viewCentral.setPanning(false);
        this.viewCentral.viewChanged();
        this.initialPanLocation = null;
    }
    
    private Point getPointOnFullImage(final MouseEvent mouseEvent) {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        if (propertyManager == null || !propertyManager.isOpen()) {
            return null;
        }
        final Rectangle viewPort = propertyManager.getViewPort();
        final int discardedZoomLevels = propertyManager.getDiscardedZoomLevels();
        final Point imageDisplayOffset = this.viewCentral.getMainImagePanel().getImageDisplayOffset();
        int n = viewPort.x + ImageUtils.zoomedToFull(mouseEvent.getX() - imageDisplayOffset.x, discardedZoomLevels);
        int n2 = viewPort.y + ImageUtils.zoomedToFull(mouseEvent.getY() - imageDisplayOffset.y, discardedZoomLevels);
        if (n >= 0 && n2 >= 0 && n < viewPort.x + viewPort.width && n2 < viewPort.y + viewPort.height) {
            return new Point(n, n2);
        }
        if (!this.isXInBounds(n, viewPort) && this.isYInBounds(n2, viewPort)) {
            n = this.getOutOfBoundsX(n, viewPort);
        }
        else if (this.isXInBounds(n, viewPort) && !this.isYInBounds(n2, viewPort)) {
            n2 = this.getOutOfBoundsY(n2, viewPort);
        }
        else if (!this.isXInBounds(n, viewPort) && !this.isYInBounds(n2, viewPort)) {
            n = this.getOutOfBoundsX(n, viewPort);
            n2 = this.getOutOfBoundsY(n2, viewPort);
        }
        return new Point(n, n2);
    }
    
    private boolean isXInBounds(final int n, final Rectangle rectangle) {
        return n >= 0 && n < rectangle.x + rectangle.width;
    }
    
    private boolean isYInBounds(final int n, final Rectangle rectangle) {
        return n >= 0 && n < rectangle.y + rectangle.height;
    }
    
    private int getOutOfBoundsX(final int n, final Rectangle rectangle) {
        int n2 = n;
        if (n < 0) {
            n2 = 0;
        }
        if (n > rectangle.x + rectangle.width) {
            n2 = rectangle.x + rectangle.width;
        }
        return n2;
    }
    
    private int getOutOfBoundsY(final int n, final Rectangle rectangle) {
        int n2 = n;
        if (n < 0) {
            n2 = 0;
        }
        if (n > rectangle.y + rectangle.height) {
            n2 = rectangle.y + rectangle.height;
        }
        return n2;
    }
    
    public void setContextMenu(final ContextMenu contextMenu) {
        this.contextMenu = contextMenu;
    }
    
    public void getClosestCorner(final Point point) {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
        final Point[] array = new Point[4];
        final Point chipStart = propertyManager.getChipStart();
        final Point chipEnd = propertyManager.getChipEnd();
        array[0] = new Point(chipStart);
        array[1] = new Point(chipEnd.x, chipStart.y);
        array[2] = new Point(chipEnd);
        array[3] = new Point(chipStart.x, chipEnd.y);
        final Point displayWindowToImage = dimensionManager.displayWindowToImage(point);
        double n = 9.9999999E7;
        for (int i = 0; i < array.length; ++i) {
            final double distance = Point2D.distance(displayWindowToImage.x, displayWindowToImage.y, array[i].x, array[i].y);
            if (distance < n) {
                this.closestFeature = i;
                n = distance;
            }
        }
    }
    
    public void getClosestSide(final Point point) {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
        final Point chipStart = propertyManager.getChipStart();
        final Point chipEnd = propertyManager.getChipEnd();
        final Point displayWindowToImage = dimensionManager.displayWindowToImage(point);
        final double[] array = new double[4];
        final int n = 0;
        final int n2 = 1;
        final int n3 = 2;
        final int n4 = 3;
        array[n] = Math.abs(chipStart.x - displayWindowToImage.x);
        array[n2] = Math.abs(chipStart.y - displayWindowToImage.y);
        array[n3] = Math.abs(chipEnd.x - displayWindowToImage.x);
        array[n4] = Math.abs(chipEnd.y - displayWindowToImage.y);
        double n5 = 9999999.0;
        int n6 = 0;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] < n5) {
                n5 = array[i];
                n6 = i;
            }
        }
        switch (n6) {
            case 0: {
                this.closestFeature = 4;
                break;
            }
            case 1: {
                this.closestFeature = 5;
                break;
            }
            case 2: {
                this.closestFeature = 6;
                break;
            }
            case 3: {
                this.closestFeature = 7;
                break;
            }
        }
    }
    
    public String checkProximity(final Point point) {
        final Rectangle chipRectangle = this.viewCentral.getPropertyManager().getChipRectangle();
        final int outcode = chipRectangle.outcode(point.x, point.y);
        final int n = 0;
        final int n2 = 1;
        final int n3 = 2;
        final int n4 = 3;
        final Point[] array = new Point[4];
        array[n] = new Point(chipRectangle.x, chipRectangle.y);
        array[n2] = new Point(chipRectangle.x + chipRectangle.width, chipRectangle.y);
        array[n3] = new Point(chipRectangle.x + chipRectangle.width, chipRectangle.y + chipRectangle.height);
        array[n4] = new Point(chipRectangle.x, chipRectangle.y + chipRectangle.height);
        for (int i = 0; i < array.length; ++i) {
            if (Point2D.distance(point.x, point.y, array[i].x, array[i].y) < 10.0) {
                if (i == n) {
                    this.viewCentral.getMainImagePanel().getImageDisplayPanel().setCursor(new Cursor(6));
                }
                else if (i == n2) {
                    this.viewCentral.getMainImagePanel().getImageDisplayPanel().setCursor(new Cursor(7));
                }
                else if (i == n3) {
                    this.viewCentral.getMainImagePanel().getImageDisplayPanel().setCursor(new Cursor(5));
                }
                else if (i == n4) {
                    this.viewCentral.getMainImagePanel().getImageDisplayPanel().setCursor(new Cursor(4));
                }
                return "corner";
            }
        }
        switch (outcode) {
            case 1: {
                if (Math.abs(point.x - chipRectangle.x) < 8) {
                    this.viewCentral.getMainImagePanel().getImageDisplayPanel().setCursor(new Cursor(10));
                    return "side";
                }
                break;
            }
            case 2: {
                if (Math.abs(point.y - chipRectangle.y) < 8) {
                    this.viewCentral.getMainImagePanel().getImageDisplayPanel().setCursor(new Cursor(8));
                    return "side";
                }
                break;
            }
            case 4: {
                if (Math.abs(point.x - (chipRectangle.x + chipRectangle.width)) < 8) {
                    this.viewCentral.getMainImagePanel().getImageDisplayPanel().setCursor(new Cursor(11));
                    return "side";
                }
                break;
            }
            case 8: {
                if (Math.abs(point.y - (chipRectangle.y + chipRectangle.height)) < 8) {
                    this.viewCentral.getMainImagePanel().getImageDisplayPanel().setCursor(new Cursor(9));
                    return "side";
                }
                break;
            }
        }
        this.viewCentral.getMainImagePanel().getImageDisplayPanel().setCursor(new Cursor(0));
        return "none";
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        ImageDisplayMouseHandler.log = new Log((ImageDisplayMouseHandler.class$com$itt$J2KViewer$gui$ImageDisplayMouseHandler == null) ? (ImageDisplayMouseHandler.class$com$itt$J2KViewer$gui$ImageDisplayMouseHandler = class$("com.itt.J2KViewer.gui.ImageDisplayMouseHandler")) : ImageDisplayMouseHandler.class$com$itt$J2KViewer$gui$ImageDisplayMouseHandler);
    }
}
