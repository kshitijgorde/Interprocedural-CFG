// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.controller;

import java.util.Vector;
import java.awt.Point;
import java.util.List;

public class WaypointManager
{
    private ViewCentral viewCentral;
    private List waypointList;
    private int scrollWait;
    private int scrollPercent;
    private int scrollUnits;
    private WaypointNavigationThread navigationThread;
    
    public WaypointManager(final ViewCentral viewCentral) {
        this.scrollWait = 40;
        this.scrollPercent = 10;
        this.scrollUnits = 1;
        this.viewCentral = viewCentral;
    }
    
    public void setScrollWait(final int scrollWait) {
        this.scrollWait = scrollWait;
    }
    
    public int getScrollWait() {
        return this.scrollWait;
    }
    
    public int getScrollPercent() {
        return this.scrollPercent;
    }
    
    public void setScrollUnits(final int scrollUnits) {
        this.scrollUnits = scrollUnits;
    }
    
    public int getScrollUnits() {
        return this.scrollUnits;
    }
    
    public void removeAllWaypoints() {
        if (this.waypointList != null) {
            this.waypointList.clear();
        }
        this.waypointList = null;
        this.viewCentral.getPropertyManager().setWaypointsAdjusted(true);
    }
    
    public void addWaypoint(final Point point) {
        final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
        if (this.waypointList == null) {
            this.waypointList = new Vector();
        }
        this.waypointList.add(dimensionManager.displayWindowToImage(point));
        this.viewCentral.getPropertyManager().setWaypointsAdjusted(true);
    }
    
    public void removeClosestWaypoint(final Point point) {
        if (this.waypointList != null && !this.waypointList.isEmpty()) {
            final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
            int n = 0;
            double n2 = Double.MAX_VALUE;
            final Point displayWindowToImage = dimensionManager.displayWindowToImage(point);
            for (int i = 0; i < this.waypointList.size(); ++i) {
                final Point point2 = this.waypointList.get(i);
                final double n3 = displayWindowToImage.x - point2.x;
                final double n4 = displayWindowToImage.y - point2.y;
                final double sqrt = Math.sqrt(n3 * n3 + n4 * n4);
                if (sqrt < n2) {
                    n = i;
                    n2 = sqrt;
                }
            }
            this.waypointList.remove(n);
            this.viewCentral.getPropertyManager().setWaypointsAdjusted(true);
        }
    }
    
    public List getWaypointList() {
        return this.waypointList;
    }
    
    public boolean hasWaypoints() {
        return this.waypointList != null && !this.waypointList.isEmpty();
    }
    
    public void start() {
        if ((this.navigationThread == null || !this.navigationThread.isAlive()) && this.waypointList != null && !this.waypointList.isEmpty()) {
            this.viewCentral.getMainImagePanel().getStream().cancelStream();
            if (this.waypointList.size() > 0) {
                (this.navigationThread = new WaypointNavigationThread(this.viewCentral)).start();
            }
        }
    }
    
    public void stop() {
        if (this.navigationThread != null) {
            this.navigationThread.setDone();
            this.navigationThread = null;
        }
    }
}
