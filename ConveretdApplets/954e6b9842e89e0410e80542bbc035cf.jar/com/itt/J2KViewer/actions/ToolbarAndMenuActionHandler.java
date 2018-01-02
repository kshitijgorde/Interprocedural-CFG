// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.actions;

import java.beans.PropertyVetoException;
import com.itt.J2KViewer.util.LoginException;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.itt.J2KViewer.controller.ViewCentral;
import com.itt.J2KViewer.util.Log;

public class ToolbarAndMenuActionHandler
{
    private static Log log;
    private ViewCentral viewCentral;
    private JPanel parent;
    static /* synthetic */ Class class$com$itt$J2KViewer$actions$ToolbarAndMenuActionHandler;
    
    public ToolbarAndMenuActionHandler(final JPanel parent, final ViewCentral viewCentral) {
        this.viewCentral = null;
        this.parent = null;
        this.viewCentral = viewCentral;
        this.parent = parent;
    }
    
    public boolean handleRequest(final String s) {
        final boolean b = false;
        try {
            if (s.equals("Zoom In")) {
                this.viewCentral.setTransformationMode(s);
            }
            else if (s.equals("Zoom Out")) {
                this.viewCentral.setTransformationMode(s);
            }
            else if (s.equals("Pan")) {
                this.viewCentral.setTransformationMode(s);
            }
            else if (s.equals("Fly")) {
                this.viewCentral.setTransformationMode(s);
            }
            else if (s.equals("Manual Rotation")) {
                this.viewCentral.setTransformationMode(s);
            }
            else if (s.equals("Magnifying Glass")) {
                this.viewCentral.setTransformationMode(s);
            }
            else if (s.equals("Magnifying Glass Properties...")) {
                this.viewCentral.showMagLensOptions();
            }
            else if (s.equals("Chipping")) {
                if (this.viewCentral.getPropertyManager().getRotationAngle() != 0.0) {
                    if (JOptionPane.showConfirmDialog(this.parent, "Chipping cannot be performed when the image is rotated! \n Would you like to reset the image now?", "", 2) == 0) {
                        this.viewCentral.getPropertyManager().setRotationAngle(0.0);
                        this.viewCentral.viewChanged();
                        this.viewCentral.setTransformationMode(s);
                    }
                }
                else {
                    this.viewCentral.setTransformationMode(s);
                }
            }
            else if (s.equals("Increase Quality")) {
                this.doChangeQuality(1);
            }
            else if (s.equals("Decrease Quality")) {
                this.doChangeQuality(-1);
            }
            else if (s.equals("Quality Layer...")) {
                this.viewCentral.doShowQuality();
            }
            else if (s.equals("Cache Directory...")) {
                this.viewCentral.doShowCacheManager();
            }
            else if (s.equals("User Preferences...")) {
                this.viewCentral.doShowPreferences();
            }
            else if (s.equals("Auto DRA")) {
                this.viewCentral.setTransformationMode("Pan");
                this.viewCentral.applyAutoDRA();
            }
            else if (s.equals("Code Stream...")) {
                this.viewCentral.doShowCodeStreamProps();
            }
            else if (s.equals("XML MetaData...")) {
                this.viewCentral.doShowXmlProps();
            }
            else if (s.equals("About...")) {
                this.viewCentral.doShowHelp();
            }
            else if (s.equals("Close Image")) {
                this.viewCentral.closeImage();
            }
            else if (s.equals("Copy Viewport")) {
                this.viewCentral.capture();
            }
            else if (s.equals("Save View As...")) {
                this.viewCentral.saveViewToFile();
            }
            else if (s.equals("Bands...")) {
                this.viewCentral.setTransformationMode("Pan");
                this.viewCentral.doShowBands();
            }
            else if (s.equals("DRA...")) {
                this.viewCentral.setTransformationMode("Pan");
                this.viewCentral.doShowDRA();
            }
            else if (s.equals("Wavelet Sharpening Dialog")) {
                this.viewCentral.setTransformationMode("Pan");
                this.viewCentral.showWaveletSharpeningDialog();
            }
            else if (s.equals("Annotations Dialog")) {
                this.viewCentral.doShowAnnotations();
            }
            else if (s.equals("Jump To...")) {
                this.viewCentral.doJumpTo();
            }
            else if (s.equals("Exit")) {
                this.viewCentral.exitApplication();
            }
            else if (s.equals("Set View 1:1")) {
                this.viewCentral.zoom1to1();
            }
            else if (s.equals("Create Waypoints")) {
                this.viewCentral.setTransformationMode(s);
            }
            else if (s.equals("Waypoint Following")) {
                this.viewCentral.setTransformationMode(s);
                this.viewCentral.showWaypointDialog();
            }
            else if (s.equals("Add Waypoint")) {
                this.doAddWaypoint();
            }
            else if (s.equals("Remove Waypoint")) {
                this.doRemoveWaypoint();
            }
            else if (s.equals("Remove All Waypoints")) {
                this.doRemoveAllWaypoints();
            }
            return b;
        }
        catch (LoginException ex) {
            this.viewCentral.reportError(null, "Connection Error", "Error connecting to IAS Server: " + ex.getMessage());
            return false;
        }
        catch (Exception ex2) {
            ToolbarAndMenuActionHandler.log.error("Error received handle context menu item selection", ex2);
            return false;
        }
    }
    
    private void doChangeQuality(final int n) {
        try {
            if (this.viewCentral.getPropertyManager() != null && this.viewCentral.getPropertyManager().isAllowChangeQuality()) {
                if (!this.viewCentral.getPropertyManager().isAllowChangeQuality()) {
                    return;
                }
                final int qualityLayers = this.viewCentral.getPropertyManager().getQualityLayers();
                int totalQualityLayers = qualityLayers + n;
                if (totalQualityLayers < 1) {
                    totalQualityLayers = 1;
                }
                if (totalQualityLayers > this.viewCentral.getPropertyManager().getTotalQualityLayers()) {
                    totalQualityLayers = this.viewCentral.getPropertyManager().getTotalQualityLayers();
                }
                if (totalQualityLayers != qualityLayers) {
                    this.viewCentral.getPropertyManager().setQualityLayers(totalQualityLayers);
                    this.viewCentral.viewChanged();
                }
            }
        }
        catch (PropertyVetoException ex) {
            ToolbarAndMenuActionHandler.log.error("Error changing quality", ex);
        }
    }
    
    private void doAddWaypoint() {
        this.viewCentral.getWaypointManager().addWaypoint(this.viewCentral.getMainImagePanel().getPopupTriggerPoint());
        this.viewCentral.viewChanged();
    }
    
    private void doRemoveWaypoint() {
        this.viewCentral.getWaypointManager().removeClosestWaypoint(this.viewCentral.getMainImagePanel().getPopupTriggerPoint());
        this.viewCentral.viewChanged();
    }
    
    private void doRemoveAllWaypoints() {
        this.viewCentral.getWaypointManager().removeAllWaypoints();
        this.viewCentral.viewChanged();
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
        ToolbarAndMenuActionHandler.log = new Log((ToolbarAndMenuActionHandler.class$com$itt$J2KViewer$actions$ToolbarAndMenuActionHandler == null) ? (ToolbarAndMenuActionHandler.class$com$itt$J2KViewer$actions$ToolbarAndMenuActionHandler = class$("com.itt.J2KViewer.actions.ToolbarAndMenuActionHandler")) : ToolbarAndMenuActionHandler.class$com$itt$J2KViewer$actions$ToolbarAndMenuActionHandler);
    }
}
