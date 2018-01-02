// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.actions;

import com.itt.J2KViewer.util.Helper;
import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import com.itt.J2KViewer.controller.PropertyManager;
import java.beans.PropertyChangeEvent;
import javax.swing.JPanel;
import java.util.HashMap;
import com.itt.J2KViewer.controller.ViewCentral;
import com.itt.J2KViewer.util.Log;
import com.itt.J2KViewer.util.ViewerConst;
import java.beans.PropertyChangeListener;

public class ToolbarAndMenuActionCreator implements PropertyChangeListener, ViewerConst
{
    private static Log log;
    private ViewCentral viewCentral;
    public static final String CLOSE = "Close Image";
    public static final String CLOSE_SHORT_DESC = "Close Image";
    public static final String CAPTURE = "Copy Viewport";
    public static final String CAPTURE_SHORT_DESC = "Copy Current Viewport Contents to the Clipboard";
    public static final String SAVE_VIEW_TO_FILE = "Save View As...";
    public static final String SAVE_VIEW_TO_FILE_SHORT_DESC = "Save Current View to a File";
    public static final String BANDS = "Bands...";
    public static final String BANDS_SHORT_DESC = "Select Bands for Display";
    public static final String DRA = "DRA...";
    public static final String DRA_SHORT_DESC = "Adjust Dynamic Range Values";
    public static final String ZOOM_1to1 = "Set View 1:1";
    public static final String ZOOM_IN = "Zoom In";
    public static final String ZOOM_IN_SHORT_DESC = "Zoom In";
    public static final String ZOOM_OUT = "Zoom Out";
    public static final String ZOOM_OUT_SHORT_DESC = "Zoom Out";
    public static final String PANNING = "Pan";
    public static final String PANNING_SHORT_DESC = "Panning";
    public static final String WAYPOINTS = "Create Waypoints";
    public static final String WAYPOINT_FOLLOWING = "Waypoint Following";
    public static final String FLY = "Fly";
    public static final String ADD_WAYPOINT = "Add Waypoint";
    public static final String REMOVE_WAYPOINT = "Remove Waypoint";
    public static final String REMOVE_ALL_WAYPOINTS = "Remove All Waypoints";
    public static final String JUMP_TO = "Jump To...";
    public static final String JUMP_TO_SHORT_DESC = "Jump To Location";
    public static final String INC_QUALITY = "Increase Quality";
    public static final String INC_QUALITY_SHORT_DESC = "Increase Quality Layer Setting";
    public static final String DEC_QUALITY = "Decrease Quality";
    public static final String DEC_QUALITY_SHORT_DESC = "Decrease Quality Layer Setting";
    public static final String QUALITY = "Quality Layer...";
    public static final String QUALITY_SHORT_DESC = "Select the Maximum Quality Layer to Download";
    public static final String CACHING = "Cache Directory...";
    public static final String CACHING_SHORT_DESC = "Select caching options";
    public static final String ROTATION = "Manual Rotation";
    public static final String AUTO_DRA = "Auto DRA";
    public static final String AUTO_DRA_SHORT_DESC = "Apply the selected DRA";
    public static final String DIALOG_WAVLET_SHARPENING = "Wavelet Sharpening Dialog";
    public static final String ANNOTATIONS = "Annotations Dialog";
    public static final String PROPS = "Code Stream...";
    public static final String PROPS_SHORT_DESC = "Code Stream Properties";
    public static final String PREFERENCES = "User Preferences...";
    public static final String PREFERENCES_SHORT_DESC = "User Preference Properties";
    public static final String XML_PROPS = "XML MetaData...";
    public static final String XML_PROPS_SHORT_DESC = "XML Properties";
    public static final String HELP = "About...";
    public static final String HELP_SHORT_DESC = "Help About";
    public static final String EXIT = "Exit";
    public static final String EXIT_SHORT_DESC = "Exit the Application";
    public static final String LENSING = "Magnifying Glass";
    public static final String LENSING_SHORT_DESC = "Magnifying Glass";
    public static final String MAGNIFY_LENS_OPTIONS = "Magnifying Glass Properties...";
    public static final String CHIPPING = "Chipping";
    public static final String CHIPPING_SHORT_DESC = "Image Chipping";
    public static final int BUTTON = 0;
    public static final int TBAR_BTN = 1;
    public static final int MENU = 2;
    private HashMap actionMap;
    private ToolbarAndMenuActionHandler actionHandler;
    static /* synthetic */ Class class$com$itt$J2KViewer$actions$ToolbarAndMenuActionCreator;
    
    public ToolbarAndMenuActionCreator(final ViewCentral viewCentral) {
        this.viewCentral = null;
        this.actionMap = null;
        this.actionHandler = null;
        this.viewCentral = viewCentral;
        this.actionMap = null;
        viewCentral.eventController().addPropertyChangeListener(this);
    }
    
    public void setupToolbarAndMenuActionHandler(final JPanel panel) {
        this.actionHandler = new ToolbarAndMenuActionHandler(panel, this.viewCentral);
        this.createDefaultActions();
        this.adjustActionState("", this.viewCentral.getPropertyManager());
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        if (propertyChangeEvent.getSource() instanceof PropertyManager) {
            final String propertyName = propertyChangeEvent.getPropertyName();
            if (propertyName.equals("AllowOpenFile")) {
                if (this.isPropertyChanged(propertyChangeEvent)) {
                    this.adjustActionState(propertyName, propertyManager);
                }
            }
            else if (propertyName.equals("AllowOpenURL")) {
                if (this.isPropertyChanged(propertyChangeEvent)) {
                    this.adjustActionState(propertyName, propertyManager);
                }
            }
            else if (propertyName.equals("AllowZoom")) {
                if (this.isPropertyChanged(propertyChangeEvent)) {
                    this.adjustActionState(propertyName, propertyManager);
                }
            }
            else if (propertyName.equals("DiscardedZoomLevels")) {
                this.adjustActionState(propertyName, propertyManager);
            }
            else if (propertyName.equals("AllowChangeQuality")) {
                if (this.isPropertyChanged(propertyChangeEvent)) {
                    this.adjustActionState(propertyName, propertyManager);
                }
            }
            else if (propertyName.equals("Open")) {
                if (this.isPropertyChanged(propertyChangeEvent)) {
                    this.adjustActionState(propertyName, propertyManager);
                }
            }
            else if (propertyName.equals("lensing")) {
                if (this.isPropertyChanged(propertyChangeEvent)) {
                    this.adjustActionState(propertyName, propertyManager);
                }
            }
            else if (propertyName.equals("WaypointsAdjusted")) {
                if (this.isPropertyChanged(propertyChangeEvent)) {
                    this.adjustActionState(propertyName, propertyManager);
                }
            }
            else if (propertyName.equals("chipping") && this.isPropertyChanged(propertyChangeEvent)) {
                this.adjustActionState(propertyName, propertyManager);
            }
        }
    }
    
    private boolean isPropertyChanged(final PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent == null) {
            throw new IllegalArgumentException("PropertyChangeEvent must not be null.");
        }
        if (propertyChangeEvent.getNewValue() instanceof Boolean) {
            return (boolean)propertyChangeEvent.getNewValue() != (boolean)propertyChangeEvent.getOldValue();
        }
        throw new IllegalArgumentException("PropertyChangeEvent must be Boolean property: " + propertyChangeEvent.getPropertyName());
    }
    
    private void adjustActionState(final String s, final PropertyManager propertyManager) {
        final boolean enabled = propertyManager.isAllowPan() && propertyManager.isOpen();
        final boolean enabled2 = propertyManager.isAllowPan() && propertyManager.isOpen();
        final boolean b = propertyManager.isAllowZoom() && propertyManager.isOpen();
        final boolean enabled3 = b && propertyManager.getDiscardedZoomLevels() > -2;
        final boolean enabled4 = b && propertyManager.getDiscardedZoomLevels() < propertyManager.getMaxDiscardedZoomLevels();
        final boolean open = propertyManager.isOpen();
        final boolean open2 = propertyManager.isOpen();
        final boolean open3 = propertyManager.isOpen();
        final boolean open4 = propertyManager.isOpen();
        final boolean open5 = propertyManager.isOpen();
        final boolean open6 = propertyManager.isOpen();
        final boolean open7 = propertyManager.isOpen();
        final boolean open8 = propertyManager.isOpen();
        final boolean open9 = propertyManager.isOpen();
        final boolean open10 = propertyManager.isOpen();
        final boolean open11 = propertyManager.isOpen();
        final boolean open12 = propertyManager.isOpen();
        final boolean open13 = propertyManager.isOpen();
        final boolean enabled5 = true;
        final boolean open14 = propertyManager.isOpen();
        final boolean open15 = propertyManager.isOpen();
        final boolean open16 = propertyManager.isOpen();
        final boolean hasWaypoints = this.viewCentral.getWaypointManager().hasWaypoints();
        final boolean hasWaypoints2 = this.viewCentral.getWaypointManager().hasWaypoints();
        final boolean hasWaypoints3 = this.viewCentral.getWaypointManager().hasWaypoints();
        final boolean enabled6 = propertyManager.isOpen() && propertyManager.isAllowChipping();
        final ToolbarAndMenuAction actionByType = this.getActionByType("Pan");
        if (actionByType != null) {
            actionByType.setEnabled(enabled);
        }
        final ToolbarAndMenuAction actionByType2 = this.getActionByType("Manual Rotation");
        if (actionByType2 != null) {
            actionByType2.setEnabled(enabled2);
        }
        final ToolbarAndMenuAction actionByType3 = this.getActionByType("Zoom In");
        if (actionByType3 != null) {
            actionByType3.setEnabled(enabled3);
        }
        final ToolbarAndMenuAction actionByType4 = this.getActionByType("Zoom Out");
        if (actionByType4 != null) {
            actionByType4.setEnabled(enabled4);
        }
        final ToolbarAndMenuAction actionByType5 = this.getActionByType("Set View 1:1");
        if (actionByType5 != null) {
            actionByType5.setEnabled(open);
        }
        final ToolbarAndMenuAction actionByType6 = this.getActionByType("Create Waypoints");
        if (actionByType6 != null) {
            actionByType6.setEnabled(open16);
        }
        final ToolbarAndMenuAction actionByType7 = this.getActionByType("Waypoint Following");
        if (actionByType7 != null) {
            actionByType7.setEnabled(hasWaypoints);
        }
        final ToolbarAndMenuAction actionByType8 = this.getActionByType("Remove Waypoint");
        if (actionByType8 != null) {
            actionByType8.setEnabled(hasWaypoints2);
        }
        final ToolbarAndMenuAction actionByType9 = this.getActionByType("Remove All Waypoints");
        if (actionByType9 != null) {
            actionByType9.setEnabled(hasWaypoints3);
        }
        final ToolbarAndMenuAction actionByType10 = this.getActionByType("Close Image");
        if (actionByType10 != null) {
            actionByType10.setEnabled(open5);
        }
        final ToolbarAndMenuAction actionByType11 = this.getActionByType("Copy Viewport");
        if (actionByType11 != null) {
            actionByType11.setEnabled(open6);
        }
        final ToolbarAndMenuAction actionByType12 = this.getActionByType("Save View As...");
        if (actionByType12 != null) {
            actionByType12.setEnabled(open7);
        }
        final ToolbarAndMenuAction actionByType13 = this.getActionByType("Bands...");
        if (actionByType13 != null) {
            actionByType13.setEnabled(open8);
        }
        final ToolbarAndMenuAction actionByType14 = this.getActionByType("DRA...");
        if (actionByType14 != null) {
            actionByType14.setEnabled(open9);
        }
        final ToolbarAndMenuAction actionByType15 = this.getActionByType("Jump To...");
        if (actionByType15 != null) {
            actionByType15.setEnabled(open10);
        }
        final ToolbarAndMenuAction actionByType16 = this.getActionByType("XML MetaData...");
        if (actionByType16 != null) {
            actionByType16.setEnabled(open3);
        }
        final ToolbarAndMenuAction actionByType17 = this.getActionByType("Code Stream...");
        if (actionByType17 != null) {
            actionByType17.setEnabled(open2);
        }
        final ToolbarAndMenuAction actionByType18 = this.getActionByType("Auto DRA");
        if (actionByType18 != null) {
            actionByType18.setEnabled(open4);
        }
        final ToolbarAndMenuAction actionByType19 = this.getActionByType("Magnifying Glass");
        if (actionByType19 != null) {
            actionByType19.setEnabled(open11);
        }
        final ToolbarAndMenuAction actionByType20 = this.getActionByType("Quality Layer...");
        if (actionByType20 != null) {
            actionByType20.setEnabled(open12);
        }
        final ToolbarAndMenuAction actionByType21 = this.getActionByType("Fly");
        if (actionByType21 != null) {
            actionByType21.setEnabled(open13);
        }
        final ToolbarAndMenuAction actionByType22 = this.getActionByType("Wavelet Sharpening Dialog");
        if (actionByType22 != null) {
            actionByType22.setEnabled(open14);
        }
        final ToolbarAndMenuAction actionByType23 = this.getActionByType("Annotations Dialog");
        if (actionByType23 != null) {
            actionByType23.setEnabled(open15);
        }
        final ToolbarAndMenuAction actionByType24 = this.getActionByType("Exit");
        if (actionByType24 != null) {
            actionByType24.setEnabled(enabled5);
        }
        final ToolbarAndMenuAction actionByType25 = this.getActionByType("Chipping");
        if (actionByType25 != null) {
            actionByType25.setEnabled(enabled6);
        }
    }
    
    public ToolbarAndMenuAction createAction(final String s, final ImageIcon imageIcon, final String s2, final Integer n) {
        if (this.actionMap == null) {
            this.actionMap = new HashMap();
        }
        if (this.checkActionHandler()) {
            final ToolbarAndMenuAction toolbarAndMenuAction = new ToolbarAndMenuAction(s, s, imageIcon, s2, n);
            if (toolbarAndMenuAction != null) {
                toolbarAndMenuAction.setHandler(this.actionHandler);
                this.actionMap.put(s, toolbarAndMenuAction);
            }
            else {
                ToolbarAndMenuActionCreator.log.error("Error while create action: " + s);
            }
            return toolbarAndMenuAction;
        }
        return null;
    }
    
    public ToolbarAndMenuAction getActionByType(final String s) {
        if (this.actionMap != null) {
            return this.actionMap.get(s);
        }
        ToolbarAndMenuActionCreator.log.debug("Could not find action of type '" + s + "'. Make sure it has been created.");
        return null;
    }
    
    public AbstractButton getAbstractButtonForAction(final String s, final int n) {
        final ToolbarAndMenuAction actionByType = this.getActionByType(s);
        if (actionByType == null) {
            throw new IllegalStateException("Action could not be found for: " + s);
        }
        switch (n) {
            case 0: {
                return new JButton(actionByType);
            }
            case 1: {
                final JButton button = new JButton(actionByType);
                if (button.getIcon() != null) {
                    button.setText("");
                }
                return button;
            }
            case 2: {
                final JMenuItem menuItem = new JMenuItem(actionByType);
                menuItem.setIcon(null);
                return menuItem;
            }
            default: {
                throw new IllegalArgumentException("getAbstractButtonForAction received invalid btn type: " + n);
            }
        }
    }
    
    private void createDefaultActions() {
        if (this.checkActionHandler()) {
            this.createAction("Close Image", Helper.loadImage("CloseImage24.gif", "Close Image"), "Close Image", new Integer(67));
            this.createAction("Copy Viewport", Helper.loadImage("CaptureImage24.gif", "Copy Current View to Clipboard"), "Copy Current Viewport Contents to the Clipboard", new Integer(80));
            this.createAction("Save View As...", Helper.loadImage("SaveViewToFile24.gif", "Save Current View to a File"), "Save Current View to a File", new Integer(83));
            this.createAction("Bands...", Helper.loadImage("Bands24.gif", "Select Bands for Display"), "Select Bands for Display", new Integer(66));
            this.createAction("DRA...", Helper.loadImage("DRA24.gif", "Adjust Dynamic Range Values"), "Adjust Dynamic Range Values", new Integer(68));
            this.createAction("Wavelet Sharpening Dialog", Helper.loadImage("wavesharp24.png", "Wavelet Sharpening"), "Wavelet Sharpening Dialog", new Integer(87));
            this.createAction("Jump To...", Helper.loadImage("JumpTo24.gif", "Jump To a Location"), "Jump To Location", new Integer(74));
            this.createAction("Set View 1:1", Helper.loadImage("onetoone24.png", "View 1:1"), "Set View 1:1", new Integer(49));
            this.createAction("Zoom In", Helper.loadImage("ZoomIn24.gif", "Zoom In"), "Zoom In", new Integer(73));
            final ImageIcon loadImage = Helper.loadImage("ZoomOut24.gif", "Zoom Out");
            this.createAction("Zoom Out", loadImage, "Zoom Out", new Integer(79));
            this.createAction("Increase Quality", loadImage, "Increase Quality Layer Setting", new Integer(81));
            this.createAction("Decrease Quality", loadImage, "Decrease Quality Layer Setting", new Integer(81));
            this.createAction("Quality Layer...", loadImage, "Select the Maximum Quality Layer to Download", new Integer(87));
            this.createAction("Cache Directory...", loadImage, "Select caching options", new Integer(67));
            this.createAction("User Preferences...", loadImage, "User Preference Properties", new Integer(89));
            this.createAction("About...", Helper.loadImage("Help24.gif", "Help"), "Help About", new Integer(72));
            this.createAction("Code Stream...", Helper.loadImage("Information24.gif", "Properties"), "Code Stream Properties", new Integer(80));
            this.createAction("Auto DRA", Helper.loadImage("AutoDRA24.gif", "Properties"), "Apply the selected DRA", new Integer(65));
            this.createAction("XML MetaData...", Helper.loadImage("XmlProps24.gif", "Properties"), "XML Properties", new Integer(88));
            this.createAction("Pan", Helper.loadImage("Pan24.gif", "Pan Mode"), "Panning", new Integer(88));
            this.createAction("Exit", null, "Exit the Application", new Integer(88));
            this.createAction("Magnifying Glass", Helper.loadImage("LensNew.png", "Manhattan Lens"), "Magnifying Glass", new Integer(77));
            this.createAction("Magnifying Glass Properties...", Helper.loadImage("LensNew.png", "Magnifying Glass Properties..."), "Magnifying Glass Properties...", new Integer(79));
            this.createAction("Manual Rotation", Helper.loadImage("Rotate24.png", "Rotate Mode"), "Manual Rotation", new Integer(82));
            this.createAction("Annotations Dialog", Helper.loadImage("Annotations24.png", "Annotations On/Off"), "Annotations Dialog", new Integer(78));
            this.createAction("Waypoint Following", Helper.loadImage("WaypointFollowing24.png", "Waypoint Following"), "Waypoint Following", new Integer(70));
            this.createAction("Create Waypoints", Helper.loadImage("Waypoints24.png", "Waypoints"), "Create Waypoints", new Integer(87));
            this.createAction("Fly", Helper.loadImage("fly24.png", "Fly Mode"), "Fly", new Integer(70));
            this.createAction("Add Waypoint", null, "Add Waypoint", new Integer(87));
            this.createAction("Remove Waypoint", null, "Remove Waypoint", new Integer(69));
            this.createAction("Remove All Waypoints", null, "Remove All Waypoints", new Integer(69));
            this.createAction("Chipping", Helper.loadImage("Chipper24.gif", "Image Chipper"), "Image Chipping", new Integer(77));
        }
    }
    
    private boolean checkActionHandler() {
        if (this.actionHandler != null) {
            return true;
        }
        throw new IllegalStateException("ActionHandler is null in createDefaultActions()");
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
        ToolbarAndMenuActionCreator.log = new Log((ToolbarAndMenuActionCreator.class$com$itt$J2KViewer$actions$ToolbarAndMenuActionCreator == null) ? (ToolbarAndMenuActionCreator.class$com$itt$J2KViewer$actions$ToolbarAndMenuActionCreator = class$("com.itt.J2KViewer.actions.ToolbarAndMenuActionCreator")) : ToolbarAndMenuActionCreator.class$com$itt$J2KViewer$actions$ToolbarAndMenuActionCreator);
    }
}
