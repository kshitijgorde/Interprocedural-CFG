// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.controller;

import com.itt.IASjTools.IASJPIPServer;
import com.itt.J2KViewer.util.Base64;
import com.itt.J2KViewer.util.Helper;
import java.beans.PropertyChangeEvent;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.io.IOException;
import java.beans.PropertyVetoException;
import java.awt.Point;
import java.awt.Rectangle;
import com.itt.J2KViewer.imagetools.NITFChipper;
import com.itt.J2KViewer.georvm.Projection;
import java.io.File;
import java.awt.Color;
import com.itt.J2KViewer.util.Log;
import com.itt.J2KViewer.util.ViewerConst;

public class PropertyManager implements ViewerConst
{
    private Log log;
    private boolean isLensVisible;
    private Color lensColor;
    private Color gotoColor;
    private Color overviewColor;
    private String loginID;
    private String loginPwd;
    private File state_folder;
    private File stateFolderCache;
    private File state_file;
    private boolean propFilePreviouslySet;
    private String imageURL;
    private int discardedZoomLevels;
    private int totalQualityLayers;
    private int qualityLayers;
    private int qualityLayersPercentToDownload;
    private int overviewQualityLayers;
    private int overviewQltyLayersPercentToDwnld;
    private boolean percentQualitySet;
    private boolean allowZoom;
    private boolean allowPan;
    private boolean allowChangeQuality;
    private boolean allowChipping;
    private int defaultDRAType;
    private boolean uponLoadDoAutoDRA;
    private boolean isInitialImageLoad;
    private double defaultDraStretchPercent;
    private double currentDraStretchPercent;
    private int ignoreValueDRA;
    private boolean useIgnoreValueDRA;
    private double sharpGain;
    private boolean showContextMenu;
    private boolean showToolBar;
    private boolean showScrollbars;
    private boolean showRecentFilesMenu;
    private boolean showSecurityBanner;
    private boolean showCopyrightBanner;
    private boolean showSplitPane;
    private boolean showOverviewImage;
    private String securitySegment;
    private long bytesTransferred;
    private long totalBytes;
    private int maxDiscardedZoomLevels;
    private int transformationMode;
    private int[] RGBMap;
    private int[] lastColorBands;
    private int lastGrayScaleBand;
    private boolean isShowRGB;
    private int dataPrecision;
    private boolean isDataSigned;
    private boolean isLoginSuccess;
    private boolean isLoginRequired;
    private Projection currentProj;
    private boolean isShowGeoLocationPanel;
    private boolean isShowStatusPanel;
    private boolean isShowDynamicRangePanel;
    private int applicationWidth;
    private int applicationHeight;
    private boolean isDefaultProjSelected;
    private double rotationAngle;
    private int lensType;
    private int lensRadius;
    private int lensMagnification;
    private int geographicDisplayFormat;
    private boolean isShowNorthArrow;
    private boolean isShowWaypoints;
    private boolean isWaypointNavigationRunning;
    private String northArrowImage;
    private String chippingServiceURL;
    private String chippingServiceType;
    private String chippingCredentials;
    private String chippingResultKey;
    private String chippingConversionOptions;
    private String chippingSelectedCoordinateType;
    private String chippingTargetResolutionOptions;
    private String chippingFTPHostText;
    private String chippingFTPHostPathText;
    private String chippingFTPHostLoginText;
    private String chippingFTPHostPasswdText;
    private boolean chipCropping;
    private NITFChipper nitfChipper;
    private boolean showChipWarning;
    private Rectangle chip;
    private Point chipStart;
    private Point chipEnd;
    private boolean hasUUID;
    private int chipperPort;
    private String chipperHost;
    private String chipperServicePath;
    private String topLevelDirectory;
    private String chipperOutputDirectory;
    private String cacheDir;
    private String defaultJpipCacheDirectory;
    private boolean cachingIsEnabled;
    private boolean cacheClearOnAppExit;
    private boolean sslEnabled;
    private Rectangle viewPort;
    private Rectangle priorViewPort;
    private Rectangle totalDimensions;
    private Rectangle currentDimensions;
    private int numberOfComponents;
    private String kduStatus;
    private boolean open;
    private int maxZoomOutLevel;
    private String lastSelectedPath;
    private int pixelDepth;
    private ViewCentral viewCentral;
    private Point userLocation;
    private Point pointClicked;
    private Point lensCenterPoint;
    private Point mouseReleased;
    private boolean isShowCrosshair;
    private int transmissionLength;
    static /* synthetic */ Class class$com$itt$J2KViewer$controller$PropertyManager;
    
    public PropertyManager(final ViewCentral viewCentral) {
        this.log = new Log((PropertyManager.class$com$itt$J2KViewer$controller$PropertyManager == null) ? (PropertyManager.class$com$itt$J2KViewer$controller$PropertyManager = class$("com.itt.J2KViewer.controller.PropertyManager")) : PropertyManager.class$com$itt$J2KViewer$controller$PropertyManager);
        this.isLensVisible = false;
        this.lensColor = Color.yellow;
        this.gotoColor = Color.yellow;
        this.overviewColor = Color.yellow;
        this.loginID = null;
        this.loginPwd = null;
        this.state_folder = null;
        this.stateFolderCache = null;
        this.state_file = null;
        this.propFilePreviouslySet = false;
        this.imageURL = null;
        this.discardedZoomLevels = Integer.MIN_VALUE;
        this.totalQualityLayers = -1;
        this.qualityLayers = -1;
        this.qualityLayersPercentToDownload = -1;
        this.overviewQualityLayers = -1;
        this.overviewQltyLayersPercentToDwnld = -1;
        this.percentQualitySet = false;
        this.allowZoom = true;
        this.allowPan = true;
        this.allowChangeQuality = true;
        this.allowChipping = false;
        this.defaultDRAType = 0;
        this.uponLoadDoAutoDRA = true;
        this.isInitialImageLoad = true;
        this.defaultDraStretchPercent = 2.0;
        this.ignoreValueDRA = 0;
        this.useIgnoreValueDRA = false;
        this.sharpGain = 1.0;
        this.showContextMenu = true;
        this.showToolBar = true;
        this.showScrollbars = true;
        this.showRecentFilesMenu = true;
        this.showSecurityBanner = false;
        this.showCopyrightBanner = false;
        this.showSplitPane = true;
        this.showOverviewImage = true;
        this.securitySegment = "FILE";
        this.bytesTransferred = 0L;
        this.totalBytes = 0L;
        this.maxDiscardedZoomLevels = -1;
        this.transformationMode = 0;
        this.RGBMap = null;
        this.lastColorBands = null;
        this.lastGrayScaleBand = 0;
        this.isShowRGB = true;
        this.dataPrecision = 0;
        this.isDataSigned = false;
        this.isLoginSuccess = false;
        this.isLoginRequired = false;
        this.currentProj = null;
        this.isShowGeoLocationPanel = true;
        this.isShowStatusPanel = true;
        this.isShowDynamicRangePanel = true;
        this.applicationWidth = 900;
        this.applicationHeight = 720;
        this.isDefaultProjSelected = false;
        this.rotationAngle = 0.0;
        this.lensType = 0;
        this.lensRadius = 80;
        this.lensMagnification = 2;
        this.geographicDisplayFormat = 0;
        this.isShowNorthArrow = true;
        this.isShowWaypoints = true;
        this.isWaypointNavigationRunning = false;
        this.northArrowImage = "NorthArrow.gif";
        this.chippingServiceURL = null;
        this.chippingServiceType = "CLIENT";
        this.chippingCredentials = null;
        this.chippingResultKey = null;
        this.chippingConversionOptions = null;
        this.chippingSelectedCoordinateType = null;
        this.chippingTargetResolutionOptions = null;
        this.chippingFTPHostText = null;
        this.chippingFTPHostPathText = null;
        this.chippingFTPHostLoginText = null;
        this.chippingFTPHostPasswdText = null;
        this.chipCropping = false;
        this.nitfChipper = null;
        this.showChipWarning = true;
        this.chip = null;
        this.chipStart = null;
        this.chipEnd = null;
        this.hasUUID = false;
        this.chipperPort = -1;
        this.chipperHost = null;
        this.chipperServicePath = null;
        this.topLevelDirectory = null;
        this.chipperOutputDirectory = null;
        this.cacheDir = null;
        this.defaultJpipCacheDirectory = null;
        this.cachingIsEnabled = false;
        this.cacheClearOnAppExit = false;
        this.sslEnabled = false;
        this.viewPort = null;
        this.priorViewPort = null;
        this.totalDimensions = null;
        this.currentDimensions = null;
        this.open = false;
        this.maxZoomOutLevel = Integer.MIN_VALUE;
        this.lastSelectedPath = null;
        this.pixelDepth = 0;
        this.viewCentral = null;
        this.userLocation = null;
        this.pointClicked = null;
        this.lensCenterPoint = null;
        this.mouseReleased = null;
        this.isShowCrosshair = true;
        this.transmissionLength = -1;
        this.viewCentral = viewCentral;
        try {
            this.reset();
        }
        catch (PropertyVetoException ex) {
            this.log.error("Error resetting properties.", ex);
        }
    }
    
    public boolean setPropFileAndDir(final String s, final String s2) {
        boolean b = false;
        this.state_folder = new File(s);
        this.stateFolderCache = new File(s + "/Cache");
        this.state_file = new File(s + "/" + s2);
        if (!this.state_folder.exists()) {
            if (!this.state_folder.mkdir()) {
                System.out.println("Unable to create properties folder: " + s);
            }
            else {
                System.out.println("Successfully created properties folder: " + s);
                try {
                    b = this.state_file.createNewFile();
                }
                catch (IOException ex) {
                    this.log.error("Error creating properties file.", ex);
                }
                if (!b) {
                    System.out.println("Unable to create properties file: " + s2);
                }
                else {
                    System.out.println("Successfully created properties file: " + s2);
                }
                if (!this.stateFolderCache.mkdir()) {
                    System.out.println("Unable to create cache folder: " + s + "Cache");
                }
                else {
                    System.out.println("Successfully created cache folder: " + s + "Cache");
                }
            }
        }
        else if (this.state_folder.exists() && !this.state_file.exists()) {
            try {
                b = this.state_file.createNewFile();
            }
            catch (IOException ex2) {
                this.log.error("Error creating properties file.", ex2);
            }
            if (!b) {
                System.out.println("Unable to create properties file: " + s2);
            }
            else {
                System.out.println("Successfully created properties file: " + s2);
            }
        }
        else if (this.state_folder.exists() && !this.stateFolderCache.exists()) {
            if (!this.stateFolderCache.mkdir()) {
                System.out.println("Unable to create cache folder: " + s + "Cache");
            }
            else {
                System.out.println("Successfully created cache folder: " + s + "Cache");
            }
        }
        else if (this.state_folder.exists() && this.state_file.exists() && this.stateFolderCache.exists()) {
            this.propFilePreviouslySet = true;
            b = true;
        }
        return b;
    }
    
    public boolean getPropFilePreviouslySet() {
        return this.propFilePreviouslySet;
    }
    
    public boolean deleteCacheDir() {
        if (this.viewCentral.getPropertyManager().getCacheClearOnAppExit()) {
            final File file = new File(this.viewCentral.getPropertyManager().getJpipCacheDirectory());
            if (this.deleteDir(file)) {
                return file.mkdirs();
            }
        }
        return false;
    }
    
    public boolean deleteDir(final File file) {
        if (file.isDirectory()) {
            final String[] list = file.list();
            for (int i = 0; i < list.length; ++i) {
                if (!this.deleteDir(new File(file, list[i]))) {
                    return false;
                }
            }
        }
        return file.toString() != this.cacheDir && file.toString().endsWith(".kjc") && file.delete();
    }
    
    public void saveProperties() {
        final Properties properties = new Properties();
        properties.setProperty("lensColor", this.lensColor.toString());
        properties.setProperty("gotoColor", this.gotoColor.toString());
        properties.setProperty("overviewColor", this.overviewColor.toString());
        properties.setProperty("lensType", new Integer(this.lensType).toString());
        properties.setProperty("lensRadius", new Integer(this.lensRadius).toString());
        properties.setProperty("lensMagnification", new Integer(this.lensMagnification).toString());
        properties.setProperty("uponLoadDoAutoDRA", new Boolean(this.uponLoadDoAutoDRA).toString());
        properties.setProperty("transformationMode", new Integer(this.transformationMode).toString());
        if (this.qualityLayersPercentToDownload == -1) {
            properties.setProperty("qualityLayersPercentToDownload", new Integer(100).toString());
        }
        else {
            properties.setProperty("qualityLayersPercentToDownload", new Integer(this.qualityLayersPercentToDownload).toString());
        }
        if (this.overviewQltyLayersPercentToDwnld == -1) {
            properties.setProperty("overviewQualityLayersPercentToDownload", new Integer(100).toString());
        }
        else {
            properties.setProperty("overviewQualityLayersPercentToDownload", new Integer(this.overviewQltyLayersPercentToDwnld).toString());
        }
        properties.setProperty("defaultDRAType", new Integer(this.defaultDRAType).toString());
        properties.setProperty("defaultStretchPercent", new Double(this.defaultDraStretchPercent).toString());
        properties.setProperty("ignoreValDRA", new Integer(this.ignoreValueDRA).toString());
        properties.setProperty("enableIgnoreValDRA", new Boolean(this.useIgnoreValueDRA).toString());
        properties.setProperty("showGeoLocationPanel", new Boolean(this.isShowGeoLocationPanel).toString());
        properties.setProperty("showStatusPanel", new Boolean(this.isShowStatusPanel).toString());
        properties.setProperty("showDynamicRangePanel", new Boolean(this.isShowDynamicRangePanel).toString());
        properties.setProperty("applicationWidth", new Integer(this.applicationWidth).toString());
        properties.setProperty("applicationHeight", new Integer(this.applicationHeight).toString());
        properties.setProperty("showChipWarning", new Boolean(this.showChipWarning).toString());
        if (this.isDefaultProjSelected) {
            properties.setProperty("defaultProjection", this.currentProj.toString());
        }
        if (this.cachingIsEnabled) {
            properties.setProperty("cacheDir", this.cacheDir);
            properties.setProperty("cachingIsEnabled", new Boolean(this.cachingIsEnabled).toString());
            properties.setProperty("cacheClearOnAppExit", new Boolean(this.cacheClearOnAppExit).toString());
        }
        properties.setProperty("northArrowImage", this.northArrowImage);
        properties.setProperty("showOverviewImage", new Boolean(this.showOverviewImage).toString());
        if (this.chipperOutputDirectory != null) {
            properties.setProperty("chipperOutputDirectory", this.chipperOutputDirectory);
        }
        if (this.state_file != null && this.state_file.getName().length() > 0) {
            try {
                final FileOutputStream fileOutputStream = new FileOutputStream(this.state_file);
                properties.store(fileOutputStream, "Enterprise Viewer Properties File");
                fileOutputStream.close();
            }
            catch (FileNotFoundException ex) {
                this.log.error("Error opening file.", ex);
            }
            catch (IOException ex2) {
                this.log.error("Error writing properties.", ex2);
            }
        }
    }
    
    public void reset() throws PropertyVetoException {
        this.setBytesTransferred(0L);
        this.setCurrentDimensions(null);
        this.setDiscardedZoomLevels(Integer.MIN_VALUE);
        this.setImageURL(null);
        this.setMaxDiscardedZoomLevels(-1);
        this.setTotalQualityLayers(-1);
        this.setTotalBytes(0L);
        this.setTotalDimensions(null);
        this.setViewPort(null);
        this.setRotationAngle(0.0);
        this.setMaxZoomOutLevel(500);
        this.setTransformationMode(0);
        this.setPixelDepth(0);
        this.setUserLocation(null);
        this.setShowNorthArrow(true);
    }
    
    public void fireXmlDataParsedEvent() {
        this.viewCentral.eventController().firePropertyChange(PropertyChangeEventMediator.createPropertyChangeEvent(this, "XmlDataParsed", new Boolean(false), new Boolean(true)));
    }
    
    public void setShowNorthArrow(final boolean b) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "ShowNorthArrow", new Boolean(this.isShowNorthArrow), new Boolean(b));
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.isShowNorthArrow = (boolean)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public boolean isShowNorthArrow() {
        return this.isShowNorthArrow;
    }
    
    public void setShowWaypoints(final boolean isShowWaypoints) {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "ShowWaypoints", new Boolean(this.isShowWaypoints), new Boolean(isShowWaypoints));
        this.isShowWaypoints = isShowWaypoints;
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public boolean isShowWaypoints() {
        return this.isShowWaypoints;
    }
    
    public void setWaypointsAdjusted(final boolean b) {
        this.viewCentral.eventController().firePropertyChange(PropertyChangeEventMediator.createPropertyChangeEvent(this, "WaypointsAdjusted", new Boolean(false), new Boolean(b)));
    }
    
    public void setWaypointNavigationRunning(final boolean isWaypointNavigationRunning) {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "WaypointNavigationRunning", new Boolean(this.isWaypointNavigationRunning), new Boolean(isWaypointNavigationRunning));
        this.isWaypointNavigationRunning = isWaypointNavigationRunning;
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public void setNorthArrowImage(final String northArrowImage) {
        this.northArrowImage = northArrowImage;
    }
    
    public void fireDisplayUpdatedEvent() {
        this.viewCentral.eventController().firePropertyChange(PropertyChangeEventMediator.createPropertyChangeEvent(this, "DisplayUpdated", null, null));
    }
    
    public String getNorthArrowImage() {
        return this.northArrowImage;
    }
    
    public Rectangle getChipRectangle() {
        return this.chip;
    }
    
    public void setChipRectangle(final int n, final int n2, final int n3, final int n4) {
        this.chip = null;
        if (n3 == 0 || n4 == 0) {
            this.chip = null;
        }
        else {
            this.chip = new Rectangle(n, n2, n3, n4);
        }
    }
    
    public void resetChip() {
        try {
            this.setChipStart(null);
            this.setChipEnd(null);
            this.setChipRectangle(0, 0, 0, 0);
        }
        catch (PropertyVetoException ex) {
            this.log.error("Failed to reset chip rectangle!", ex);
        }
    }
    
    public Point getChipStart() {
        return this.chipStart;
    }
    
    public void setChipStart(final Point point) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "chipStart", this.chipStart, point);
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.chipStart = (Point)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public Point getChipEnd() {
        return this.chipEnd;
    }
    
    public void setChipEnd(final Point point) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "chipEnd", this.chipEnd, point);
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.chipEnd = (Point)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public void setMouseReleased(final Point mouseReleased) {
        this.mouseReleased = mouseReleased;
    }
    
    public Point getMouseReleased() {
        return this.mouseReleased;
    }
    
    public void setUserLocation(final Point point) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "UserLocation", this.userLocation, point);
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.userLocation = (Point)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public Point getUserLocation() {
        if (this.userLocation == null) {
            return null;
        }
        return new Point(this.userLocation);
    }
    
    public void setShowCrosshair(final boolean isShowCrosshair) {
        this.isShowCrosshair = isShowCrosshair;
    }
    
    public boolean isShowCrosshair() {
        return this.isShowCrosshair;
    }
    
    public void setPointClicked(final Point pointClicked) {
        this.pointClicked = pointClicked;
    }
    
    public Point getPointClicked() {
        return this.pointClicked;
    }
    
    public void setIsShowGeoLocationPanel(final boolean isShowGeoLocationPanel) {
        this.isShowGeoLocationPanel = isShowGeoLocationPanel;
    }
    
    public boolean getIsShowGeoLocationPanel() {
        return this.isShowGeoLocationPanel;
    }
    
    public void setIsShowStatusPanel(final boolean isShowStatusPanel) {
        this.isShowStatusPanel = isShowStatusPanel;
    }
    
    public boolean getIsShowStatusPanel() {
        return this.isShowStatusPanel;
    }
    
    public void setIsShowDynamicRangePanel(final boolean isShowDynamicRangePanel) {
        this.isShowDynamicRangePanel = isShowDynamicRangePanel;
    }
    
    public boolean getIsShowDynamicRangePanel() {
        return this.isShowDynamicRangePanel;
    }
    
    public void setLensCenterPoint(final Point point) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "lensCenterPoint", this.lensCenterPoint, point);
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.lensCenterPoint = (Point)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public Point getLensCenterPoint() {
        return this.lensCenterPoint;
    }
    
    public Color getLensColor() {
        return this.lensColor;
    }
    
    public void setLensColor(final Color color) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "lensColor", this.lensColor, color);
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.lensColor = (Color)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public int getLensType() {
        return this.lensType;
    }
    
    public void setLensType(final int n) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "lensType", new Integer(this.lensType), new Integer(n));
        this.lensType = (int)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public int getLensRadius() {
        return this.lensRadius;
    }
    
    public void setLensRadius(final int n) {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "lensRadius", new Integer(this.lensRadius), new Integer(n));
        this.lensRadius = (int)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public int getLensMagnification() {
        return this.lensMagnification;
    }
    
    public void setLensMagnification(final int n) {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "lensMagnification", new Integer(this.lensMagnification), new Integer(n));
        this.lensMagnification = (int)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public void setApplicationWidth(final int applicationWidth) {
        this.applicationWidth = applicationWidth;
    }
    
    public void setApplicationHeight(final int applicationHeight) {
        this.applicationHeight = applicationHeight;
    }
    
    public int[] getApplicationSize() {
        return new int[] { this.applicationWidth, this.applicationHeight };
    }
    
    public Color getGotoColor() {
        return this.gotoColor;
    }
    
    public void setGotoColor(final Color color) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "gotoColor", this.gotoColor, color);
        this.gotoColor = (Color)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public Color getOverviewColor() {
        return this.overviewColor;
    }
    
    public void setOverviewColor(final Color color) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "overviewColor", this.overviewColor, color);
        this.overviewColor = (Color)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public void setIsLoginSuccess(final boolean isLoginSuccess) {
        this.isLoginSuccess = isLoginSuccess;
    }
    
    public boolean isLoginSuccess() {
        return this.isLoginSuccess;
    }
    
    public void setIsLoginRequired(final boolean isLoginRequired) {
        this.isLoginRequired = isLoginRequired;
    }
    
    public boolean isLoginRequired() {
        return this.isLoginRequired;
    }
    
    public void setDataSigned(final boolean isDataSigned) {
        this.isDataSigned = isDataSigned;
    }
    
    public boolean isDataSigned() {
        return this.isDataSigned;
    }
    
    public void setDataPrecision(final int dataPrecision) {
        this.dataPrecision = dataPrecision;
    }
    
    public int getDataPrecision() {
        return this.dataPrecision;
    }
    
    public void setImageURL(final String s) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "ImageURL", this.imageURL, s);
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.imageURL = (String)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public String getImageURL() {
        if (this.imageURL == null) {
            return null;
        }
        return new String(this.imageURL);
    }
    
    public void setNewDRA(final boolean b) {
        this.viewCentral.eventController().firePropertyChange(PropertyChangeEventMediator.createPropertyChangeEvent(this, "NewDRA", new Boolean(b), new Boolean(false)));
    }
    
    public void setNewDataRange(final boolean b) {
        this.viewCentral.eventController().firePropertyChange(PropertyChangeEventMediator.createPropertyChangeEvent(this, "NewDataRange", new Boolean(b), new Boolean(false)));
    }
    
    public void setDefaultDRAType(final int n) {
        PropertyChangeEventMediator.createPropertyChangeEvent(this, "DefaultDRAType", new Integer(this.defaultDRAType), new Integer(n));
        this.defaultDRAType = n;
        this.viewCentral.getDRAManager().setAutoStretchType(n);
    }
    
    public int getDefaultDRAType() {
        return this.defaultDRAType;
    }
    
    public void setDefaultDRAPercent(final double defaultDraStretchPercent) {
        this.defaultDraStretchPercent = defaultDraStretchPercent;
    }
    
    public double getDefaultDRAPercent() {
        return this.defaultDraStretchPercent;
    }
    
    public void setCurrentDRAPercent(final double currentDraStretchPercent) {
        this.currentDraStretchPercent = currentDraStretchPercent;
    }
    
    public double getCurrentDRAPercent() {
        return this.currentDraStretchPercent;
    }
    
    public void setIgnoreValueDRA(final int ignoreValueDRA) throws PropertyVetoException {
        this.ignoreValueDRA = ignoreValueDRA;
        this.viewCentral.getDRAManager().setIgnoreValue(this.ignoreValueDRA);
    }
    
    public int getIgnoreValueDRA() {
        return this.ignoreValueDRA;
    }
    
    public void setUseIgnoreValueDRA(final boolean useIgnoreValueDRA) {
        this.useIgnoreValueDRA = useIgnoreValueDRA;
        this.viewCentral.getDRAManager().useIgnoreValue(this.useIgnoreValueDRA);
    }
    
    public boolean isUseIgnoreValueDRA() {
        return this.useIgnoreValueDRA;
    }
    
    public void setSharpGain(final double n) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "WaveletSharpeningGain", new Double(this.sharpGain), new Double(n));
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.sharpGain = (double)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public double getSharpGain() {
        return this.sharpGain;
    }
    
    public void setDiscardedZoomLevels(final int n) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "DiscardedZoomLevels", new Integer(this.discardedZoomLevels), new Integer(n));
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.discardedZoomLevels = (int)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public int getDiscardedZoomLevels() {
        return this.discardedZoomLevels;
    }
    
    public void setRotationAngle(final double rotationAngle) {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "RotationAngle", new Double(this.rotationAngle), new Double(rotationAngle));
        this.rotationAngle = rotationAngle;
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public double getRotationAngle() {
        return this.rotationAngle;
    }
    
    public void setTotalQualityLayers(final int n) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "TotalQualityLayers", new Integer(this.totalQualityLayers), new Integer(n));
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.totalQualityLayers = (int)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public int getTotalQualityLayers() {
        return this.totalQualityLayers;
    }
    
    public void setQualityLayers(final int n) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "QualityLayers", new Integer(this.qualityLayers), new Integer(n));
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.qualityLayers = (int)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public int getQualityLayers() {
        return this.qualityLayers;
    }
    
    public void setQualityLayersPercentToDownload(final int qualityLayersPercentToDownload) {
        this.percentQualitySet = true;
        this.qualityLayersPercentToDownload = qualityLayersPercentToDownload;
    }
    
    public int getQualityLayersPercentToDownload() {
        return this.qualityLayersPercentToDownload;
    }
    
    public boolean isSetQualityLayersPercentToDownload() {
        return this.percentQualitySet;
    }
    
    public void setOverviewQualityLayersPercentToDownload(final int overviewQltyLayersPercentToDwnld) {
        this.overviewQltyLayersPercentToDwnld = overviewQltyLayersPercentToDwnld;
    }
    
    public int getOverviewQualityLayersPercentToDownload() {
        return this.overviewQltyLayersPercentToDwnld;
    }
    
    public void setOverviewQualityLayers(final int overviewQualityLayers) {
        this.overviewQualityLayers = overviewQualityLayers;
    }
    
    public int getOverviewQualityLayers() {
        return this.overviewQualityLayers;
    }
    
    public Rectangle getPriorViewPort() {
        return this.priorViewPort;
    }
    
    public void setViewPort(final Rectangle rectangle) throws PropertyVetoException {
        this.priorViewPort = this.getViewPort();
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "ViewPort", this.viewPort, rectangle);
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.viewPort = (Rectangle)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public Rectangle getViewPort() {
        if (this.viewPort == null) {
            return null;
        }
        return new Rectangle(this.viewPort);
    }
    
    public void setAllowZoom(final boolean b) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "AllowZoom", new Boolean(this.allowZoom), new Boolean(b));
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.allowZoom = (boolean)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public boolean isAllowZoom() {
        return this.allowZoom;
    }
    
    public void setUponLoadDoAutoDRA(final boolean uponLoadDoAutoDRA) {
        this.uponLoadDoAutoDRA = uponLoadDoAutoDRA;
    }
    
    public boolean isUponLoadDoAutoDRA() {
        return this.uponLoadDoAutoDRA;
    }
    
    public void setInitialImageLoad(final boolean isInitialImageLoad) {
        this.isInitialImageLoad = isInitialImageLoad;
    }
    
    public boolean isInitialImageLoad() {
        return this.isInitialImageLoad;
    }
    
    public void setAllowPan(final boolean b) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "AllowPanL", new Boolean(this.allowPan), new Boolean(b));
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.allowPan = (boolean)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public boolean isAllowPan() {
        return this.allowPan;
    }
    
    public void setShowContextMenu(final boolean b) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "ShowContextMenu", new Boolean(b), new Boolean(b));
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.showContextMenu = (boolean)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public boolean isShowContextMenu() {
        return this.showContextMenu;
    }
    
    public void setGeographicDisplayFormat(final int geographicDisplayFormat) {
        this.geographicDisplayFormat = geographicDisplayFormat;
    }
    
    public int getGeographicDisplayFormat() {
        return this.geographicDisplayFormat;
    }
    
    public void setShowToolBar(final boolean b) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "ShowToolBar", new Boolean(this.showToolBar), new Boolean(b));
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.showToolBar = (boolean)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public boolean isShowToolBar() {
        return this.showToolBar;
    }
    
    public boolean isShowSplitPane() {
        return this.showSplitPane;
    }
    
    public void setShowSplitPane(final boolean showSplitPane) {
        this.showSplitPane = showSplitPane;
    }
    
    public boolean isShowOverviewImage() {
        return this.showOverviewImage;
    }
    
    public void setShowOverviewImage(final boolean showOverviewImage) {
        this.showOverviewImage = showOverviewImage;
    }
    
    public void setShowScrollbars(final boolean b) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "AllowOpenFile", new Boolean(this.showScrollbars), new Boolean(b));
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.showScrollbars = (boolean)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public boolean isShowScrollbars() {
        return this.showScrollbars;
    }
    
    public void setBytesTransferred(final long n) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "BytesTransferred", new Long(this.bytesTransferred), new Long(n));
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.bytesTransferred = (long)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public long getBytesTransferred() {
        return this.bytesTransferred;
    }
    
    public long getBytesTransferredAsKB() {
        return Helper.divideAndRoundUp(this.bytesTransferred, 1024L);
    }
    
    public void setTotalBytes(final long totalBytes) {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "TotalBytes", new Long(this.totalBytes), new Long(totalBytes));
        this.totalBytes = totalBytes;
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public long getTotalBytes() {
        return this.totalBytes;
    }
    
    public long getTotalBytesAsKB() {
        return Helper.divideAndRoundUp(this.totalBytes, 1024L);
    }
    
    public void setMaxDiscardedZoomLevels(final int n) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "MaxDiscardedZoomLevels", new Integer(this.maxDiscardedZoomLevels), new Integer(n));
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.maxDiscardedZoomLevels = (int)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public int getMaxDiscardedZoomLevels() {
        return this.maxDiscardedZoomLevels;
    }
    
    public void setShowRGB(final boolean isShowRGB) throws PropertyVetoException {
        this.isShowRGB = isShowRGB;
    }
    
    public boolean isShowRGB() {
        return this.isShowRGB;
    }
    
    public void setLastColorBands(final int[] lastColorBands) {
        this.lastColorBands = lastColorBands;
    }
    
    public int[] getLastColorBands() {
        return this.lastColorBands.clone();
    }
    
    public void setLastGrayScaleBand(final int lastGrayScaleBand) {
        this.lastGrayScaleBand = lastGrayScaleBand;
    }
    
    public int getLastGrayScaleBand() {
        return this.lastGrayScaleBand;
    }
    
    public void setRGBMap(final int[] array) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "RGBMap", this.RGBMap, array);
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.RGBMap = (int[])propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public int[] getRGBMap() {
        return this.RGBMap.clone();
    }
    
    public Projection getProj() {
        return this.currentProj;
    }
    
    public boolean getIsDefaultProjSelected() {
        return this.isDefaultProjSelected;
    }
    
    public void setIsDefaultProjSelected(final boolean isDefaultProjSelected) {
        this.isDefaultProjSelected = isDefaultProjSelected;
    }
    
    public boolean hasRPC() {
        return this.viewCentral.getNITFGeoUtils().hasRPC();
    }
    
    public void setProj(final Projection projection) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "DisplayedProjection", this.currentProj, projection);
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.currentProj = (Projection)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public void setTotalDimensions(final Rectangle rectangle) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "TotalDimensions", this.totalDimensions, rectangle);
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.totalDimensions = (Rectangle)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public Rectangle getTotalDimensions() {
        if (this.totalDimensions == null) {
            return null;
        }
        return new Rectangle(this.totalDimensions);
    }
    
    public void setCurrentDimensions(final Rectangle rectangle) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "CurrentDimensions", this.currentDimensions, rectangle);
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.currentDimensions = (Rectangle)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public Rectangle getCurrentDimensions() {
        if (this.currentDimensions != null) {
            return new Rectangle(this.currentDimensions);
        }
        return null;
    }
    
    public void setAllowChangeQuality(final boolean b) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "AllowChangeQuality", new Boolean(this.allowChangeQuality), new Boolean(b));
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.allowChangeQuality = (boolean)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public boolean isAllowChangeQuality() {
        return this.allowChangeQuality;
    }
    
    public void setNumberOfComponents(final int n) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "NumberOfComponents", new Integer(this.numberOfComponents), new Integer(n));
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.numberOfComponents = (int)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public int getNumberOfComponents() {
        return this.numberOfComponents;
    }
    
    public void setPixelDepth(final int pixelDepth) {
        this.pixelDepth = pixelDepth;
    }
    
    public int getPixelDepth() {
        return this.pixelDepth;
    }
    
    public void setIASStatus(final String s) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "KduStatus", this.kduStatus, s);
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.kduStatus = (String)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public String getKduStatus() {
        return this.kduStatus;
    }
    
    public void setOpen(final boolean b) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "Open", new Boolean(this.open), new Boolean(b));
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.open = (boolean)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public boolean isOpen() {
        return this.open;
    }
    
    public void setLoginId(final String loginID) {
        this.loginID = loginID;
    }
    
    public String getLoginId() {
        return this.loginID;
    }
    
    public void setLoginPwd(final String loginPwd) {
        this.loginPwd = loginPwd;
    }
    
    public String getBasicAuthenticationString() {
        String encodeBytes = null;
        if (this.loginID != null && this.loginPwd != null) {
            encodeBytes = Base64.encodeBytes((this.loginID + ":" + this.loginPwd).getBytes());
        }
        return encodeBytes;
    }
    
    public boolean getIsLensVisible() {
        return this.isLensVisible;
    }
    
    public void setIsLensVisible(final boolean isLensVisible) {
        this.isLensVisible = isLensVisible;
    }
    
    public String getLoginPwd() {
        return this.loginPwd;
    }
    
    public void setSelectedPath(final String lastSelectedPath) {
        this.lastSelectedPath = lastSelectedPath;
    }
    
    public String getSelectedPath() {
        return this.lastSelectedPath;
    }
    
    public void setMaxZoomOutLevel(final int n) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "maxZoomOutLevel", new Integer(this.maxZoomOutLevel), new Integer(n));
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.maxZoomOutLevel = (int)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public int getMaxZoomOutLevel() {
        return this.maxZoomOutLevel;
    }
    
    public void setTransformationMode(final int n) throws PropertyVetoException {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "TranformationMode", new Integer(this.transformationMode), new Integer(n));
        this.viewCentral.eventController().fireVetoableChange(propertyChangeEvent);
        this.transformationMode = (int)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public int getTransformationMode() {
        return this.transformationMode;
    }
    
    public String getChipperHost() {
        return this.chipperHost;
    }
    
    public void setChipperHost(final String chipperHost) {
        this.chipperHost = chipperHost;
    }
    
    public int getChipperPort() {
        return this.chipperPort;
    }
    
    public void setChipperPort(final int chipperPort) {
        this.chipperPort = chipperPort;
    }
    
    public String getTopLevelDirectory() {
        return this.topLevelDirectory;
    }
    
    public void setTopLevelDirectory(final String topLevelDirectory) {
        this.topLevelDirectory = topLevelDirectory;
    }
    
    public String getChipperServicePath() {
        return this.chipperServicePath;
    }
    
    public void setChipperServicePath(final String chipperServicePath) {
        this.chipperServicePath = chipperServicePath;
    }
    
    public String getChipperOutputDirectory() {
        return this.chipperOutputDirectory;
    }
    
    public void setChipperOutputDirectory(final String chipperOutputDirectory) {
        this.chipperOutputDirectory = chipperOutputDirectory;
    }
    
    public void setCachingIsEnabled(final boolean cachingIsEnabled) {
        this.cachingIsEnabled = cachingIsEnabled;
    }
    
    public boolean getCachingIsEnabled() {
        return this.cachingIsEnabled;
    }
    
    public void setJpipCacheDirectory(final String cacheDir) {
        this.cacheDir = cacheDir;
    }
    
    public String getJpipCacheDirectory() {
        if (this.cacheDir == null) {
            return this.cacheDir = this.stateFolderCache.toString();
        }
        return this.cacheDir;
    }
    
    public void setCacheClearOnAppExit(final boolean cacheClearOnAppExit) {
        this.cacheClearOnAppExit = cacheClearOnAppExit;
    }
    
    public boolean getCacheClearOnAppExit() {
        return this.cacheClearOnAppExit;
    }
    
    public String getDefaultJpipCacheDirectory() {
        return this.defaultJpipCacheDirectory = this.stateFolderCache.toString();
    }
    
    public boolean isSslEnabled() {
        return this.sslEnabled;
    }
    
    public void setSslEnabled(final boolean sslEnabled) {
        this.sslEnabled = sslEnabled;
        if (this.sslEnabled) {
            IASJPIPServer.JPIP_CHANNEL_TYPE = "https";
        }
        else {
            IASJPIPServer.JPIP_CHANNEL_TYPE = "http";
        }
    }
    
    public void setShowRecentFilesMenu(final boolean showRecentFilesMenu) {
        this.showRecentFilesMenu = showRecentFilesMenu;
    }
    
    public boolean isShowRecentFilesMenu() {
        return this.showRecentFilesMenu;
    }
    
    public void setShowSecurityBanner(final boolean showSecurityBanner) {
        this.showSecurityBanner = showSecurityBanner;
    }
    
    public boolean isShowSecurityBanner() {
        return this.showSecurityBanner;
    }
    
    public void setSecuritySegment(final String securitySegment) {
        this.securitySegment = securitySegment;
    }
    
    public String getSecuritySegment() {
        return this.securitySegment;
    }
    
    public void setShowCopyrightBanner(final boolean showCopyrightBanner) {
        this.showCopyrightBanner = showCopyrightBanner;
    }
    
    public boolean isShowCopyrightBanner() {
        return this.showCopyrightBanner;
    }
    
    public void setAllowChipping(final boolean b) {
        final PropertyChangeEvent propertyChangeEvent = PropertyChangeEventMediator.createPropertyChangeEvent(this, "chipping", new Boolean(this.allowChipping), new Boolean(b));
        this.allowChipping = (boolean)propertyChangeEvent.getNewValue();
        this.viewCentral.eventController().firePropertyChange(propertyChangeEvent);
    }
    
    public boolean isAllowChipping() {
        return this.allowChipping;
    }
    
    public void setShowChipWarning(final boolean showChipWarning) {
        this.showChipWarning = showChipWarning;
    }
    
    public boolean isShowChipWarning() {
        return this.showChipWarning;
    }
    
    public void setChipper(final NITFChipper nitfChipper) {
        this.nitfChipper = nitfChipper;
    }
    
    public NITFChipper getChipper() {
        return this.nitfChipper;
    }
    
    public void setChippingResultKey(final String chippingResultKey) {
        this.chippingResultKey = chippingResultKey;
    }
    
    public String getChippingResultKey() {
        if (this.chippingResultKey == null) {
            return null;
        }
        return new String(this.chippingResultKey);
    }
    
    public void setChippingConversionOptions(final String chippingConversionOptions) {
        this.chippingConversionOptions = chippingConversionOptions;
    }
    
    public String getChippingConversionOptions() {
        if (this.chippingConversionOptions == null) {
            return null;
        }
        return new String(this.chippingConversionOptions);
    }
    
    public void setChippingTargetResolutionOptions(final String chippingTargetResolutionOptions) {
        this.chippingTargetResolutionOptions = chippingTargetResolutionOptions;
    }
    
    public String getChippingTargetResolutionOptions() {
        if (this.chippingTargetResolutionOptions == null) {
            return null;
        }
        return new String(this.chippingTargetResolutionOptions);
    }
    
    public String getChippingServiceURL() {
        return this.chippingServiceURL;
    }
    
    public void setChippingServiceURL(final String chippingServiceURL) {
        this.chippingServiceURL = chippingServiceURL;
    }
    
    public String getChippingServiceType() {
        return this.chippingServiceType;
    }
    
    public void setChippingServiceType(final String chippingServiceType) {
        this.chippingServiceType = chippingServiceType;
    }
    
    public void setChippingSelectedCoordinateType(final String chippingSelectedCoordinateType) {
        this.chippingSelectedCoordinateType = chippingSelectedCoordinateType;
    }
    
    public String getChippingSelectedCoordinateType() {
        if (this.chippingSelectedCoordinateType == null) {
            return null;
        }
        return new String(this.chippingSelectedCoordinateType);
    }
    
    public void setChippingFTPHost(final String chippingFTPHostText) {
        this.chippingFTPHostText = chippingFTPHostText;
    }
    
    public String getChippingFTPHost() {
        if (this.chippingFTPHostText == null) {
            return null;
        }
        return new String(this.chippingFTPHostText);
    }
    
    public void setChippingFTPHostPath(final String chippingFTPHostPathText) {
        this.chippingFTPHostPathText = chippingFTPHostPathText;
    }
    
    public String getChippingFTPHostPath() {
        if (this.chippingFTPHostPathText == null) {
            return null;
        }
        return new String(this.chippingFTPHostPathText);
    }
    
    public void setChippingFTPHostLogin(final String chippingFTPHostLoginText) {
        this.chippingFTPHostLoginText = chippingFTPHostLoginText;
    }
    
    public String getChippingFTPHostLogin() {
        if (this.chippingFTPHostLoginText == null) {
            return null;
        }
        return new String(this.chippingFTPHostLoginText);
    }
    
    public void setChippingFTPHostPasswd(final String chippingFTPHostPasswdText) {
        this.chippingFTPHostPasswdText = chippingFTPHostPasswdText;
    }
    
    public String getChippingFTPHostPasswd() {
        if (this.chippingFTPHostPasswdText == null) {
            return null;
        }
        return new String(this.chippingFTPHostPasswdText);
    }
    
    public boolean isCropping() {
        return this.chipCropping;
    }
    
    public void setCropping(final boolean chipCropping) {
        this.chipCropping = chipCropping;
    }
    
    public void setChippingCredentials(final String chippingCredentials) {
        this.chippingCredentials = chippingCredentials;
    }
    
    public String getChippingCredentials() {
        if (this.chippingCredentials == null) {
            return null;
        }
        return new String(this.chippingCredentials);
    }
    
    public void setTransmissionLength(final int transmissionLength) {
        this.transmissionLength = transmissionLength;
    }
    
    public int getTransmissionLength() {
        return this.transmissionLength;
    }
    
    public void setHasUUID(final boolean hasUUID) {
        this.hasUUID = hasUUID;
    }
    
    public boolean hasUUID() {
        return this.hasUUID;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
