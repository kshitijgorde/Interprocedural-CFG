// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import com.itt.J2KViewer.controller.PropertyChangeEventMediator;
import com.itt.J2KViewer.controller.DimensionManager;
import com.itt.J2KViewer.controller.PropertyManager;
import com.itt.J2KViewer.util.ImageUtils;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.Rectangle;
import java.awt.Point;
import com.itt.J2KViewer.controller.ViewCentral;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;

public class OverviewMouseHandler extends MouseAdapter implements MouseMotionListener, MouseWheelListener
{
    private ViewCentral viewCentral;
    private Point initialPanLocation;
    private Rectangle initialViewPort;
    
    public OverviewMouseHandler(final ViewCentral viewCentral) {
        this.initialPanLocation = null;
        this.initialViewPort = null;
        this.viewCentral = viewCentral;
    }
    
    public void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
        this.viewCentral.getMainImagePanel().adjustZoomLevel(mouseWheelEvent.getWheelRotation());
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.initialPanLocation = null;
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
        final Rectangle fullToZoomed = ImageUtils.fullToZoomed(dimensionManager.getDisplaySpace(), this.viewCentral.getOverviewImagePanel().getZoomLevel());
        if (propertyManager != null && propertyManager.isOpen() && mouseEvent.getClickCount() == 1 && mouseEvent.getButton() == 1) {
            final int n = mouseEvent.getX() + fullToZoomed.x;
            final int n2 = mouseEvent.getY() + fullToZoomed.y;
            final OverviewImagePanel overviewImagePanel = this.viewCentral.getOverviewImagePanel();
            if (fullToZoomed.contains(n, n2)) {
                final int zoomLevel = overviewImagePanel.getZoomLevel();
                final int zoomedToFull = ImageUtils.zoomedToFull(n, zoomLevel);
                final int zoomedToFull2 = ImageUtils.zoomedToFull(n2, zoomLevel);
                propertyManager.setInitialImageLoad(false);
                dimensionManager.setDisplayWindowCenter(new Point(zoomedToFull, zoomedToFull2));
                this.viewCentral.viewChanged();
            }
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
        final Rectangle rectangle = new Rectangle();
        Rectangle fullToZoomed = new Rectangle();
        if (propertyManager.isOpen()) {
            fullToZoomed = ImageUtils.fullToZoomed(dimensionManager.getDisplaySpace(), this.viewCentral.getOverviewImagePanel().getZoomLevel());
        }
        if (propertyManager != null && propertyManager.isOpen() && propertyManager.getTransformationMode() != 6) {
            final int n = mouseEvent.getX() + fullToZoomed.x;
            final int n2 = mouseEvent.getY() + fullToZoomed.y;
            final OverviewImagePanel overviewImagePanel = this.viewCentral.getOverviewImagePanel();
            final Object o = null;
            final Object o2 = null;
            Point point;
            if (fullToZoomed.contains(n, n2)) {
                final int zoomLevel = overviewImagePanel.getZoomLevel();
                final Point displayToImage = dimensionManager.displayToImage(new Point(ImageUtils.zoomedToFull(n, zoomLevel), ImageUtils.zoomedToFull(n2, zoomLevel)));
                if (displayToImage.x <= dimensionManager.getImageSize(0).width && displayToImage.y <= dimensionManager.getImageSize(0).height) {
                    point = displayToImage;
                }
                else {
                    point = new Point(-1, -1);
                }
            }
            else {
                point = new Point(-1, -1);
            }
            final PropertyChangeEventMediator eventController = this.viewCentral.eventController();
            eventController.firePropertyChange(PropertyChangeEventMediator.createPropertyChangeEvent(this, "MouseMoved", point, point));
            eventController.firePropertyChange(PropertyChangeEventMediator.createPropertyChangeEvent(this, "DataValuesChanged", o, o));
            eventController.firePropertyChange(PropertyChangeEventMediator.createPropertyChangeEvent(this, "RGBValuesChanged", o2, o2));
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
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        this.viewCentral.getDimensionManager();
        if (propertyManager != null && propertyManager.isOpen() && propertyManager.getTransformationMode() != 6 && this.initialPanLocation != null) {
            this.initialPanLocation = null;
            this.viewCentral.getMainImagePanel();
            propertyManager.setInitialImageLoad(false);
            propertyManager.getViewPort();
            this.viewCentral.viewChanged();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        if (propertyManager != null && propertyManager.isOpen() && propertyManager.isAllowPan() && (mouseEvent.getModifiers() & 0x10) != 0x0 && propertyManager.getTransformationMode() != 6) {
            final int zoomLevel = this.viewCentral.getOverviewImagePanel().getZoomLevel();
            final int zoomedToFull = ImageUtils.zoomedToFull(mouseEvent.getX(), zoomLevel);
            final int zoomedToFull2 = ImageUtils.zoomedToFull(mouseEvent.getY(), zoomLevel);
            final Rectangle viewPort = propertyManager.getViewPort();
            if (viewPort.contains(zoomedToFull, zoomedToFull2)) {
                this.initialPanLocation = new Point(mouseEvent.getX(), mouseEvent.getY());
                this.initialViewPort = viewPort;
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        if (propertyManager != null && propertyManager.isOpen() && propertyManager.isAllowPan() && (mouseEvent.getModifiers() & 0x10) != 0x0 && propertyManager.getTransformationMode() != 6 && this.initialPanLocation != null) {
            final OverviewImagePanel overviewImagePanel = this.viewCentral.getOverviewImagePanel();
            this.viewCentral.getMainImagePanel();
            overviewImagePanel.getZoomLevel();
            mouseEvent.getPoint();
        }
    }
}
