// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.controller;

import java.util.List;
import com.itt.J2KViewer.util.ImageUtils;
import com.itt.J2KViewer.util.Helper;
import java.awt.Point;
import javax.swing.SwingUtilities;
import com.itt.J2KViewer.util.Log;

public class WaypointNavigationThread extends Thread
{
    private ViewCentral viewCentral;
    private Log log;
    private boolean done;
    private static final int RESTART_WAIT_MSECS = 100;
    private static final int RESTART_WAIT_COUNT = 50;
    static /* synthetic */ Class class$com$itt$J2KViewer$controller$WaypointNavigationThread;
    
    public WaypointNavigationThread(final ViewCentral viewCentral) {
        this.log = new Log((WaypointNavigationThread.class$com$itt$J2KViewer$controller$WaypointNavigationThread == null) ? (WaypointNavigationThread.class$com$itt$J2KViewer$controller$WaypointNavigationThread = class$("com.itt.J2KViewer.controller.WaypointNavigationThread")) : WaypointNavigationThread.class$com$itt$J2KViewer$controller$WaypointNavigationThread);
        this.done = false;
        this.viewCentral = viewCentral;
    }
    
    public void setDone() {
        this.done = true;
    }
    
    public void run() {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
        final WaypointManager waypointManager = this.viewCentral.getWaypointManager();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                WaypointNavigationThread.this.viewCentral.getPropertyManager().setWaypointNavigationRunning(true);
            }
        });
        final List waypointList = this.viewCentral.getWaypointManager().getWaypointList();
        dimensionManager.setDisplayWindowCenter(dimensionManager.imageToDisplay(waypointList.get(0)));
        this.viewCentral.getMainImagePanel().getStream().cancelStream();
        this.viewCentral.setMainImageUpdated(false);
        this.viewCentral.viewChanged();
        for (int n = 0; !this.viewCentral.isMainImageUpdated() && n < 50; ++n) {
            Helper.sleep(100L);
        }
        Helper.sleep(1000L);
        final boolean showNorthArrow = propertyManager.isShowNorthArrow();
        if (showNorthArrow) {
            try {
                propertyManager.setShowNorthArrow(false);
            }
            catch (Exception ex) {
                this.log.error("Unable to display north arrow.", ex);
            }
        }
        final int min = Math.min(this.viewCentral.getMainImagePanel().getDisplayHeight(), this.viewCentral.getMainImagePanel().getDisplayWidth());
        final int scrollUnits = waypointManager.getScrollUnits();
        int n2 = 0;
        Point displayWindowCenter = dimensionManager.getDisplayWindowCenter();
        final int discardedZoomLevels = this.viewCentral.getPropertyManager().getDiscardedZoomLevels();
        Point point = dimensionManager.getDisplayWindowCenter();
        for (int n3 = 0; !this.done && n3 < waypointList.size() - 1; ++n3) {
            final Point point2 = waypointList.get(n3);
            final Point point3 = waypointList.get(n3 + 1);
            final Point imageToDisplay = dimensionManager.imageToDisplay(point2);
            final Point imageToDisplay2 = dimensionManager.imageToDisplay(point3);
            final double n4 = ImageUtils.fullToZoomed(imageToDisplay2.x - imageToDisplay.x, discardedZoomLevels);
            final double n5 = ImageUtils.fullToZoomed(imageToDisplay2.y - imageToDisplay.y, discardedZoomLevels);
            final double max = Math.max(Math.abs(n4), Math.abs(n5));
            final int n6 = (int)Math.round(max / scrollUnits);
            int n7 = 1;
            this.viewCentral.setPanning(true);
            while (!this.done && n7 <= n6) {
                final int n8 = Math.max(2, min * waypointManager.getScrollPercent() / 100) / scrollUnits;
                ++n2;
                point = dimensionManager.getAdjustedDisplayWindowCenter(new Point(imageToDisplay.x + ImageUtils.zoomedToFull(scrollUnits * (int)(n7 * n4 / max), discardedZoomLevels), imageToDisplay.y + ImageUtils.zoomedToFull(scrollUnits * (int)(n7 * n5 / max), discardedZoomLevels)));
                final int fullToZoomed = ImageUtils.fullToZoomed(displayWindowCenter.x - point.x, discardedZoomLevels);
                final int fullToZoomed2 = ImageUtils.fullToZoomed(displayWindowCenter.y - point.y, discardedZoomLevels);
                if (fullToZoomed != 0 || fullToZoomed2 != 0) {
                    final DrawTask drawTask = new DrawTask(fullToZoomed, fullToZoomed2);
                    try {
                        SwingUtilities.invokeAndWait(drawTask);
                    }
                    catch (Exception ex2) {
                        this.log.error("Unable to draw image", ex2);
                    }
                }
                if (n2 > n8) {
                    dimensionManager.setDisplayWindowCenter(point);
                    dimensionManager.adjustDisplayWindow(true);
                    displayWindowCenter = point;
                    this.viewCentral.getMainImagePanel().getStream().cancelStream();
                    this.viewCentral.setMainImageUpdated(false);
                    this.viewCentral.setPanning(false);
                    this.viewCentral.viewChanged();
                    for (int n9 = 0; !this.viewCentral.isMainImageUpdated() && n9 < 50; ++n9) {
                        Helper.sleep(100L);
                    }
                    this.viewCentral.setPanning(true);
                    n2 = 0;
                }
                else {
                    Helper.sleep(waypointManager.getScrollWait());
                }
                ++n7;
            }
        }
        if (showNorthArrow) {
            try {
                propertyManager.setShowNorthArrow(true);
            }
            catch (Exception ex3) {
                this.log.error("Unable to display north arrow", ex3);
            }
        }
        dimensionManager.setDisplayWindowCenter(point);
        this.viewCentral.setPanning(false);
        this.viewCentral.viewChanged();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                WaypointNavigationThread.this.viewCentral.getPropertyManager().setWaypointNavigationRunning(false);
            }
        });
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    protected class DrawTask implements Runnable
    {
        public int x;
        public int y;
        
        public DrawTask(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
        
        public void run() {
            WaypointNavigationThread.this.viewCentral.getMainImagePanel().drawImageAt(new Point(this.x, this.y), 0.0);
        }
    }
}
