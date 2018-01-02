// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.controller;

import javax.swing.SwingUtilities;
import com.itt.J2KViewer.gui.ImageDisplayMouseHandler;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import com.itt.J2KViewer.gui.PropertiesDialog;
import com.itt.J2KViewer.gui.UserPreferencesDialog;
import com.itt.J2KViewer.gui.QualityDialog;
import com.itt.J2KViewer.gui.DynamicRangeDialog;
import com.itt.J2KViewer.gui.SelectChipAreaDialog;
import com.itt.J2KViewer.gui.BandsDialog;
import com.itt.J2KViewer.gui.JumpToDialog;
import com.itt.J2KViewer.gui.AboutDialog;
import com.itt.J2KViewer.gui.AuthenticationDialog;
import com.itt.J2KViewer.gui.XmlPropertiesDialog;
import com.itt.J2KViewer.util.FileSelection;
import java.io.File;
import com.itt.J2KViewer.util.FileChooserFilter;
import com.itt.J2KViewer.util.Helper;
import javax.swing.JOptionPane;
import java.awt.Component;
import com.itt.J2KViewer.gui.LensMagnifierOptionsDialog;
import java.beans.PropertyVetoException;
import com.itt.J2KViewer.gui.WaypointDialog;
import com.itt.J2KViewer.gui.ChooseNorthArrowDialog;
import java.awt.Point;
import com.itt.J2KViewer.gui.AnnotationsDialog;
import java.awt.Frame;
import com.itt.J2KViewer.gui.WaveletSharpeningDialog;
import java.util.ArrayList;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import com.itt.J2KViewer.gui.CacheManagerDialog;
import com.itt.J2KViewer.gui.ChipperDialog;
import com.itt.J2KViewer.exception.J2KExceptionHandler;
import com.itt.J2KViewer.imagetools.MainImageStream;
import com.itt.J2KViewer.georvm.NITFGeoUtils;
import com.itt.J2KViewer.util.XmlPropertiesParser;
import com.itt.J2KViewer.actions.ToolbarAndMenuActionCreator;
import com.itt.J2KViewer.gui.ViewerMainPanel;
import com.itt.J2KViewer.imagetools.DRAManager;
import javax.swing.JFrame;
import com.itt.J2KViewer.gui.OverviewImagePanel;
import com.itt.J2KViewer.gui.MainImagePanel;
import java.net.URI;
import com.itt.J2KViewer.util.Log;
import java.beans.PropertyChangeListener;

public class ViewCentral implements PropertyChangeListener
{
    private static Log log;
    private URI imageURI;
    private MainImagePanel mainImagePanel;
    private OverviewImagePanel overviewImagePanel;
    private PropertyChangeEventMediator eventController;
    private JFrame j2kViewerFrame;
    private PropertyManager propertyManager;
    private DRAManager draManager;
    private ViewerMainPanel viewerMainPanel;
    private ToolbarAndMenuActionCreator actionManager;
    private XmlPropertiesParser xmlPropertiesParser;
    private NITFGeoUtils nitfGeoUtils;
    private MainImageStream imageStream;
    private J2KExceptionHandler exceptionHandler;
    private ChipperDialog chipperDialog;
    private CacheManagerDialog cacheMgrDialog;
    private boolean isPanning;
    private boolean isMainImageUpdated;
    private DimensionManager dimensionManager;
    private WaypointManager waypointManager;
    private static final int RESTART_WAIT_MSECS = 100;
    private static final int RESTART_WAIT_COUNT = 50;
    FlyingThread m_flyingThread;
    static /* synthetic */ Class class$com$itt$J2KViewer$controller$ViewCentral;
    
    public ViewCentral() {
        this.imageURI = null;
        this.mainImagePanel = null;
        this.overviewImagePanel = null;
        this.eventController = null;
        this.j2kViewerFrame = null;
        this.propertyManager = null;
        this.draManager = null;
        this.viewerMainPanel = null;
        this.actionManager = null;
        this.xmlPropertiesParser = null;
        this.nitfGeoUtils = null;
        this.imageStream = null;
        this.exceptionHandler = null;
        this.chipperDialog = null;
        this.cacheMgrDialog = null;
        this.isPanning = false;
        this.isMainImageUpdated = false;
        this.dimensionManager = null;
        this.waypointManager = null;
        this.eventController = new PropertyChangeEventMediator();
        this.propertyManager = new PropertyManager(this);
        this.eventController.addVetoableChangeListener(new PropertyChangeValidator(this.propertyManager));
        this.eventController().addPropertyChangeListener(this);
        this.actionManager = new ToolbarAndMenuActionCreator(this);
        this.draManager = new DRAManager(this);
        this.dimensionManager = new DimensionManager(this);
        this.waypointManager = new WaypointManager(this);
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final String propertyName = propertyChangeEvent.getPropertyName();
        if (propertyName != null) {
            if (propertyName.equals("TranformationMode")) {
                final int intValue = (int)propertyChangeEvent.getOldValue();
                final int intValue2 = (int)propertyChangeEvent.getNewValue();
                if (intValue == 6 || intValue2 == 6) {}
                if (intValue != 6 || intValue2 != 6) {}
            }
            else if (propertyName.equals("Open")) {}
        }
    }
    
    public void applyAutoDRA() {
        if (this.propertyManager.isOpen()) {
            final boolean useIgnoreValueDRA = this.propertyManager.isUseIgnoreValueDRA();
            final int ignoreValueDRA = this.propertyManager.getIgnoreValueDRA();
            this.draManager.useIgnoreValue(useIgnoreValueDRA);
            this.draManager.setIgnoreValue(ignoreValueDRA);
            final ArrayList list = new ArrayList();
            final ArrayList calculateHistogram = this.getImageStream().calculateHistogram(this.propertyManager.isUseIgnoreValueDRA(), this.draManager.getIgnoreValue());
            this.draManager.setHistograms(calculateHistogram.get(0), calculateHistogram.get(1), calculateHistogram.get(2));
            final double defaultDRAPercent = this.propertyManager.getDefaultDRAPercent();
            ViewCentral.log.debug("Performing Auto DRA Stretch:");
            ViewCentral.log.debug("AutoDRA:useIgnoreVal : " + useIgnoreValueDRA);
            ViewCentral.log.debug("AutoDRA:ignoreVal : " + ignoreValueDRA);
            ViewCentral.log.debug("AutoDRA:pctStretch : " + defaultDRAPercent);
            this.draManager.auto();
        }
    }
    
    public void showWaveletSharpeningDialog() {
        new WaveletSharpeningDialog(this, this.getJ2KViewerFrame(), true).setVisible(true);
    }
    
    public void doShowAnnotations() throws Exception {
        final AnnotationsDialog annotationsDialog = new AnnotationsDialog(this.getJ2KViewerFrame(), true, this);
        final Point locationOnScreen = this.getMainImagePanel().getLocationOnScreen();
        annotationsDialog.setResizable(true);
        annotationsDialog.setLocation(locationOnScreen.x + 10, locationOnScreen.y + 10);
        annotationsDialog.setVisible(true);
    }
    
    public void showChooseNorthArrowDialog() {
        new ChooseNorthArrowDialog(this.getJ2KViewerFrame(), true, this).setVisible(true);
    }
    
    public URI getImageURI() {
        return this.imageURI;
    }
    
    public void setImageStream(final MainImageStream imageStream) {
        this.imageStream = imageStream;
    }
    
    public MainImageStream getImageStream() {
        if (this.imageStream == null) {
            System.out.println("Image stream is null");
        }
        return this.imageStream;
    }
    
    public Point getImageDisplayOffset() {
        return this.mainImagePanel.getImageDisplayOffset();
    }
    
    public void setNITFGeoUtils(final NITFGeoUtils nitfGeoUtils) {
        this.nitfGeoUtils = nitfGeoUtils;
    }
    
    public NITFGeoUtils getNITFGeoUtils() {
        return this.nitfGeoUtils;
    }
    
    public XmlPropertiesParser getXmlPropertiesParser() {
        return this.xmlPropertiesParser;
    }
    
    public void setXmlPropertiesParser(final XmlPropertiesParser xmlPropertiesParser) {
        this.xmlPropertiesParser = xmlPropertiesParser;
        this.propertyManager.fireXmlDataParsedEvent();
    }
    
    public PropertyManager getPropertyManager() {
        return this.propertyManager;
    }
    
    public PropertyChangeEventMediator eventController() {
        return this.eventController;
    }
    
    public void setJ2KViewerFrame(final JFrame j2kViewerFrame) {
        this.j2kViewerFrame = j2kViewerFrame;
    }
    
    public DimensionManager getDimensionManager() {
        return this.dimensionManager;
    }
    
    public WaypointManager getWaypointManager() {
        return this.waypointManager;
    }
    
    public void showWaypointDialog() {
        new WaypointDialog(this.getJ2KViewerFrame(), true, this).setVisible(true);
        try {
            this.setTransformationMode("Pan");
        }
        catch (PropertyVetoException ex) {
            ViewCentral.log.error("Error switching mode from waypoint following to panning.", ex);
        }
    }
    
    public void showMagLensOptions() {
        new LensMagnifierOptionsDialog(this, this.getJ2KViewerFrame(), true).setVisible(true);
    }
    
    public void exitApplication() {
        if (this.j2kViewerFrame != null) {
            this.closeImage();
            this.j2kViewerFrame.setVisible(false);
            this.j2kViewerFrame.dispose();
            System.runFinalization();
            System.gc();
            System.exit(0);
        }
    }
    
    public void setOverviewImagePanel(final OverviewImagePanel overviewImagePanel) {
        this.overviewImagePanel = overviewImagePanel;
    }
    
    public OverviewImagePanel getOverviewImagePanel() {
        return this.overviewImagePanel;
    }
    
    public void setMainImagePanel(final MainImagePanel mainImagePanel) {
        this.mainImagePanel = mainImagePanel;
    }
    
    public MainImagePanel getMainImagePanel() {
        return this.mainImagePanel;
    }
    
    public void setPanning(final boolean isPanning) {
        this.isPanning = isPanning;
    }
    
    public boolean isPanning() {
        return this.isPanning;
    }
    
    public void setMainImageUpdated(final boolean isMainImageUpdated) {
        this.isMainImageUpdated = isMainImageUpdated;
    }
    
    public boolean isMainImageUpdated() {
        return this.isMainImageUpdated;
    }
    
    public JFrame getJ2KViewerFrame() {
        return this.j2kViewerFrame;
    }
    
    public DRAManager getDRAManager() {
        return this.draManager;
    }
    
    public void setViewerMainPanel(final ViewerMainPanel viewerMainPanel) {
        this.viewerMainPanel = viewerMainPanel;
    }
    
    public ViewerMainPanel getViewerMainPanel() {
        return this.viewerMainPanel;
    }
    
    public ToolbarAndMenuActionCreator getActionManager() {
        return this.actionManager;
    }
    
    public void openImage(final String s, final boolean b) throws Exception {
        if (s != null) {
            this.propertyManager.setImageURL(s);
            this.doOpenImage(this.imageURI = new URI(s));
            this.setJ2KViewerFrameTitle(s);
        }
    }
    
    public void reportUnknownHostError(final Component component, final String s, final String s2) {
        this.reportError(component, "Error Connecting to IAS Server", "Received 'Unknown Host' error trying to access IAS Server.\nMake sure host and port are correct: " + s + ":" + s2);
    }
    
    public void reportError(Component viewerMainPanel, final String s, final String s2) {
        if (viewerMainPanel == null) {
            viewerMainPanel = this.viewerMainPanel;
        }
        JOptionPane.showMessageDialog(viewerMainPanel, s2, s, 0);
    }
    
    public void closeImage() {
        this.viewerMainPanel.closeImage();
        this.propertyManager.deleteCacheDir();
        this.propertyManager.saveProperties();
        try {
            this.propertyManager.reset();
        }
        catch (PropertyVetoException ex) {
            ViewCentral.log.error("Error resetting properties.", ex);
        }
    }
    
    public void capture() {
        this.viewerMainPanel.copyViewToClipboard();
    }
    
    public void saveViewToFile() {
        int i = 0;
        while (i == 0) {
            String[] array;
            String[] array2;
            if (this.nitfGeoUtils != null && this.nitfGeoUtils.isReady()) {
                array = new String[] { ".tif", ".jpg" };
                array2 = new String[] { "GeoTIFF Files (*.tif)", "JPEG Files (*.jpg)" };
            }
            else {
                array = new String[] { ".jpg" };
                array2 = new String[] { "JPEG Files (*.jpg)" };
            }
            final FileSelection showFileDialog = Helper.showFileDialog(true, this.getViewerMainPanel(), this.getPropertyManager().getSelectedPath(), array, array2);
            i = 1;
            if (showFileDialog != null) {
                File selectedFile = showFileDialog.getSelectedFile();
                final FileChooserFilter fileChooserFilter = (FileChooserFilter)showFileDialog.getSelectedFileFilter();
                final String description = fileChooserFilter.getDescription();
                final String name = selectedFile.getName();
                if (name.indexOf(46) < 0) {
                    selectedFile = new File(selectedFile.getParentFile(), name + fileChooserFilter.getExtension());
                }
                this.getPropertyManager().setSelectedPath(selectedFile.getParent());
                if (selectedFile.exists()) {
                    if (JOptionPane.showConfirmDialog(this.getViewerMainPanel(), "The selected file already exists. Do you want to replace it?", "File Already Exists", 0) == 0) {
                        if (description.equals("JPEG Files (*.jpg)")) {
                            this.viewerMainPanel.saveViewToJPEGFile(selectedFile);
                        }
                        else {
                            if (!description.equals("GeoTIFF Files (*.tif)")) {
                                continue;
                            }
                            this.viewerMainPanel.saveViewToGeoTIFFFile(selectedFile);
                        }
                    }
                    else {
                        i = 0;
                    }
                }
                else if (description.equals("JPEG Files (*.jpg)")) {
                    this.viewerMainPanel.saveViewToJPEGFile(selectedFile);
                }
                else {
                    if (!description.equals("GeoTIFF Files (*.tif)")) {
                        continue;
                    }
                    this.viewerMainPanel.saveViewToGeoTIFFFile(selectedFile);
                }
            }
        }
    }
    
    public void saveViewToGeoTIFFFile() {
        int i = 0;
        while (i == 0) {
            final FileSelection showFileDialog = Helper.showFileDialog(true, this.getViewerMainPanel(), this.getPropertyManager().getSelectedPath(), new String[] { ".tif" }, new String[] { "GeoTIFF Images (*.tif)" });
            i = 1;
            if (showFileDialog != null) {
                File selectedFile = showFileDialog.getSelectedFile();
                final String name = selectedFile.getName();
                if (name.indexOf(46) < 0) {
                    selectedFile = new File(selectedFile.getParentFile(), name + ".tif");
                }
                this.getPropertyManager().setSelectedPath(selectedFile.getParent());
                if (selectedFile.exists()) {
                    if (JOptionPane.showConfirmDialog(this.getViewerMainPanel(), "The selected file already exists. Do you want to replace it?", "File Already Exists", 0) == 0) {
                        this.viewerMainPanel.saveViewToGeoTIFFFile(selectedFile);
                    }
                    else {
                        i = 0;
                    }
                }
                else {
                    this.viewerMainPanel.saveViewToGeoTIFFFile(selectedFile);
                }
            }
        }
    }
    
    public void doShowXmlProps() throws Exception {
        final XmlPropertiesDialog xmlPropertiesDialog = new XmlPropertiesDialog(this.getJ2KViewerFrame(), true, this.propertyManager.getImageURL());
        final Point locationOnScreen = this.getViewerMainPanel().getLocationOnScreen();
        if (this.xmlPropertiesParser != null && this.xmlPropertiesParser.isReady() && this.propertyManager.isOpen()) {
            xmlPropertiesDialog.setXMLData(this.xmlPropertiesParser);
            xmlPropertiesDialog.setResizable(true);
            xmlPropertiesDialog.setLocation(locationOnScreen.x + 10, locationOnScreen.y + 10);
            xmlPropertiesDialog.setVisible(true);
        }
        else {
            ViewCentral.log.error("Could not display XML metadata. Parser is not ready.");
            JOptionPane.showMessageDialog(null, "XML metadata is not available.", "XML Properties", 1);
        }
    }
    
    public boolean doShowAuthentication() throws Exception {
        final AuthenticationDialog authenticationDialog = new AuthenticationDialog(this.getJ2KViewerFrame(), true, this);
        Point locationOnScreen;
        if (this.getViewerMainPanel().isShowing()) {
            locationOnScreen = this.getViewerMainPanel().getLocationOnScreen();
        }
        else {
            locationOnScreen = new Point(80, 80);
        }
        authenticationDialog.setResizable(false);
        authenticationDialog.setLocation(locationOnScreen.x + 20, locationOnScreen.y + 20);
        authenticationDialog.setVisible(true);
        return authenticationDialog.isNewAuthentication();
    }
    
    public void doShowHelp() throws Exception {
        final AboutDialog aboutDialog = new AboutDialog(this.getJ2KViewerFrame(), true, this);
        final Point locationOnScreen = this.getViewerMainPanel().getLocationOnScreen();
        aboutDialog.setResizable(false);
        aboutDialog.setLocation(locationOnScreen.x + 20, locationOnScreen.y + 20);
        aboutDialog.setVisible(true);
    }
    
    public void zoom1to1() {
        if (this.propertyManager.getDiscardedZoomLevels() != 0) {
            final int discardedZoomLevels = 0;
            try {
                this.propertyManager.setDiscardedZoomLevels(discardedZoomLevels);
                this.viewChanged();
            }
            catch (PropertyVetoException ex) {
                ViewCentral.log.error(ex);
            }
        }
    }
    
    public void doJumpTo() throws Exception {
        final JumpToDialog jumpToDialog = new JumpToDialog(this.getJ2KViewerFrame(), true, this);
        final Point locationOnScreen = this.getViewerMainPanel().getLocationOnScreen();
        jumpToDialog.setResizable(false);
        jumpToDialog.setLocation(locationOnScreen.x + 10, locationOnScreen.y + 10);
        jumpToDialog.setVisible(true);
    }
    
    public void doShowBands() throws Exception {
        final BandsDialog bandsDialog = new BandsDialog(this.getJ2KViewerFrame(), true, this);
        final Point locationOnScreen = this.getViewerMainPanel().getLocationOnScreen();
        bandsDialog.setResizable(false);
        bandsDialog.setLocation(locationOnScreen.x + 10, locationOnScreen.y + 10);
        bandsDialog.setVisible(true);
    }
    
    public void doShowChipperDialog() throws Exception {
        (this.chipperDialog = new ChipperDialog(this.getJ2KViewerFrame(), true, this, null)).setVisible(true);
    }
    
    public void doCloseChipperDialog() {
        if (this.chipperDialog != null) {
            this.chipperDialog.setVisible(false);
            this.chipperDialog.dispose();
            this.chipperDialog = null;
        }
    }
    
    public void doShowSelectAreaDialog() {
        final SelectChipAreaDialog selectChipAreaDialog = new SelectChipAreaDialog(this.getJ2KViewerFrame(), this);
        selectChipAreaDialog.setTitle("Select Chip Area");
        final Point location = this.getJ2KViewerFrame().getLocation();
        location.x += 100;
        location.y += 100;
        selectChipAreaDialog.setLocation(location);
        selectChipAreaDialog.setVisible(true);
    }
    
    public void doShowDRA() throws Exception {
        this.propertyManager.setInitialImageLoad(false);
        final DynamicRangeDialog dynamicRangeDialog = new DynamicRangeDialog(this.getJ2KViewerFrame(), true, this);
        final Point locationOnScreen = this.getViewerMainPanel().getLocationOnScreen();
        dynamicRangeDialog.setResizable(false);
        dynamicRangeDialog.setLocation(locationOnScreen.x + 10, locationOnScreen.y + 10);
        dynamicRangeDialog.setVisible(true);
    }
    
    public void doShowQuality() throws Exception {
        final QualityDialog qualityDialog = new QualityDialog(this.getJ2KViewerFrame(), true, this);
        final Point locationOnScreen = this.getViewerMainPanel().getLocationOnScreen();
        qualityDialog.setResizable(false);
        qualityDialog.setLocation(locationOnScreen.x + 10, locationOnScreen.y + 10);
        qualityDialog.setVisible(true);
    }
    
    public void doShowCacheManager() throws Exception {
        this.cacheMgrDialog = new CacheManagerDialog(this.getJ2KViewerFrame(), true, this);
        final Point locationOnScreen = this.getViewerMainPanel().getLocationOnScreen();
        this.cacheMgrDialog.setResizable(false);
        this.cacheMgrDialog.setLocation(locationOnScreen.x + 10, locationOnScreen.y + 10);
        this.cacheMgrDialog.setVisible(true);
    }
    
    public void doShowPreferences() throws Exception {
        final UserPreferencesDialog userPreferencesDialog = new UserPreferencesDialog(this.getJ2KViewerFrame(), true, this);
        final Point locationOnScreen = this.getViewerMainPanel().getLocationOnScreen();
        userPreferencesDialog.setResizable(true);
        userPreferencesDialog.setLocation(locationOnScreen.x + 200, locationOnScreen.y + 200);
        userPreferencesDialog.setVisible(true);
    }
    
    public void doShowCodeStreamProps() throws Exception {
        final PropertiesDialog propertiesDialog = new PropertiesDialog(this.getJ2KViewerFrame(), true, this);
        final Point locationOnScreen = this.getViewerMainPanel().getLocationOnScreen();
        propertiesDialog.setResizable(true);
        propertiesDialog.setLocation(locationOnScreen.x + 10, locationOnScreen.y + 10);
        propertiesDialog.setVisible(true);
    }
    
    public void doEnableCache() throws Exception {
        final UserPreferencesDialog userPreferencesDialog = new UserPreferencesDialog(this.getJ2KViewerFrame(), true, this);
        final Point locationOnScreen = this.getViewerMainPanel().getLocationOnScreen();
        userPreferencesDialog.setResizable(false);
        userPreferencesDialog.setLocation(locationOnScreen.x + 10, locationOnScreen.y + 10);
        userPreferencesDialog.setVisible(true);
    }
    
    private void setJ2KViewerFrameTitle(String decode) {
        if (decode != null && decode.length() > 0) {
            try {
                decode = URLDecoder.decode(decode, "UTF-8");
            }
            catch (UnsupportedEncodingException ex) {
                ViewCentral.log.error("Error while decoding the image URL.", ex);
            }
            decode.substring(decode.lastIndexOf("/") + 1);
        }
    }
    
    public void doOpenImage(final URI uri) {
        this.viewerMainPanel.openImage(uri);
    }
    
    public void setTransformationMode(final String s) throws PropertyVetoException {
        final int transformationMode = this.propertyManager.getTransformationMode();
        if (s.equals("Zoom In") && this.propertyManager.isAllowZoom()) {
            if (transformationMode != 1) {
                this.propertyManager.setTransformationMode(1);
            }
            else {
                this.propertyManager.setTransformationMode(0);
            }
        }
        else if (s.equals("Zoom Out") && this.propertyManager.isAllowZoom()) {
            if (transformationMode != 2) {
                this.propertyManager.setTransformationMode(2);
            }
            else {
                this.propertyManager.setTransformationMode(0);
            }
        }
        else if (s.equals("Magnifying Glass")) {
            if (transformationMode != 6) {
                this.propertyManager.setTransformationMode(6);
            }
            else {
                this.propertyManager.setTransformationMode(-1);
            }
        }
        else if (s.equals("Chipping")) {
            if (transformationMode != 8) {
                this.propertyManager.resetChip();
                this.propertyManager.setTransformationMode(8);
                this.doShowSelectAreaDialog();
            }
            else {
                this.propertyManager.setTransformationMode(0);
            }
        }
        else if (s.equals("Pan")) {
            if (transformationMode != 0) {
                this.propertyManager.setTransformationMode(0);
            }
        }
        else if (s.equals("Manual Rotation")) {
            if (transformationMode != 3) {
                this.propertyManager.setTransformationMode(3);
            }
            else {
                this.propertyManager.setTransformationMode(0);
            }
        }
        else if (s.equals("Fly")) {
            if (transformationMode != 7) {
                this.propertyManager.setTransformationMode(7);
            }
            else {
                this.propertyManager.setTransformationMode(0);
            }
        }
        else if (s.equals("Create Waypoints")) {
            if (transformationMode != 4) {
                this.propertyManager.setTransformationMode(4);
            }
            else {
                this.propertyManager.setTransformationMode(0);
            }
        }
        else if (s.equals("Waypoint Following")) {
            if (transformationMode != 5) {
                this.propertyManager.setTransformationMode(5);
            }
            else {
                this.propertyManager.setTransformationMode(0);
            }
        }
        this.getMainImagePanel().repaint();
    }
    
    public void setImageWindowOrigin(final int n, final int n2) {
        this.mainImagePanel.setImageWindowOrigin(new Point(n, n2));
    }
    
    public void viewChanged() {
        if (this.propertyManager != null && this.propertyManager.isOpen()) {
            try {
                this.mainImagePanel.restartImageStream();
            }
            catch (Exception ex) {
                ViewCentral.log.error("Failed to restart image stream!", ex);
            }
        }
    }
    
    public void startFlying(final int n, final int speedPercentage) {
        (this.m_flyingThread = new FlyingThread(this, n)).setSpeedPercentage(speedPercentage);
        this.m_flyingThread.start();
    }
    
    public void stopFlying() {
        if (this.m_flyingThread != null) {
            this.m_flyingThread.stopFlying();
            this.m_flyingThread = null;
        }
    }
    
    public void setFlyingSpeedPercentage(final int speedPercentage) {
        if (this.m_flyingThread != null) {
            this.m_flyingThread.setSpeedPercentage(speedPercentage);
        }
    }
    
    public void setFlyingQuadrant(final int quadrant) {
        if (this.m_flyingThread != null) {
            this.m_flyingThread.setQuadrant(quadrant);
        }
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
        ViewCentral.log = new Log((ViewCentral.class$com$itt$J2KViewer$controller$ViewCentral == null) ? (ViewCentral.class$com$itt$J2KViewer$controller$ViewCentral = class$("com.itt.J2KViewer.controller.ViewCentral")) : ViewCentral.class$com$itt$J2KViewer$controller$ViewCentral);
    }
    
    protected class FlyingThread extends Thread
    {
        private boolean m_threadStop;
        private ViewCentral m_vc;
        private int m_x;
        private int m_y;
        private ImageDisplayMouseHandler m_mh;
        private Runnable m_sliderWorker;
        private Runnable m_getNewPaintWorker;
        private int m_moveQuadrant;
        private int m_howFarToSlide;
        private int m_slideSpeedDelay;
        private double m_speedPercentage;
        
        public FlyingThread(final ViewCentral vc, final int moveQuadrant) {
            this.m_threadStop = false;
            this.m_howFarToSlide = 40;
            this.m_slideSpeedDelay = 50;
            this.m_speedPercentage = 1.0;
            this.setName("FlyingThread");
            this.m_vc = vc;
            this.m_moveQuadrant = moveQuadrant;
            this.m_sliderWorker = new Runnable() {
                public void run() {
                    FlyingThread.this.m_mh.dragImage(new Point(FlyingThread.this.m_x, FlyingThread.this.m_y));
                    switch (FlyingThread.this.m_moveQuadrant) {
                        case 0: {
                            FlyingThread.this.m_x -= 0;
                            FlyingThread.this.m_y += 2;
                            break;
                        }
                        case 1: {
                            --FlyingThread.this.m_x;
                            ++FlyingThread.this.m_y;
                            break;
                        }
                        case 2: {
                            FlyingThread.this.m_x -= 2;
                            FlyingThread.this.m_y += 0;
                            break;
                        }
                        case 3: {
                            --FlyingThread.this.m_x;
                            --FlyingThread.this.m_y;
                            break;
                        }
                        case 4: {
                            FlyingThread.this.m_x -= 0;
                            FlyingThread.this.m_y -= 2;
                            break;
                        }
                        case 5: {
                            ++FlyingThread.this.m_x;
                            --FlyingThread.this.m_y;
                            break;
                        }
                        case 6: {
                            FlyingThread.this.m_x += 2;
                            FlyingThread.this.m_y += 0;
                            break;
                        }
                        case 7: {
                            ++FlyingThread.this.m_x;
                            ++FlyingThread.this.m_y;
                            break;
                        }
                    }
                }
            };
            this.m_getNewPaintWorker = new Runnable() {
                public void run() {
                    FlyingThread.this.m_mh.dragImageFinish();
                }
            };
        }
        
        public void run() {
            while (!this.m_threadStop) {
                this.m_mh = ViewCentral.this.mainImagePanel.getMouseHandler();
                this.m_x = 1;
                this.m_y = 1;
                this.m_vc.setPanning(true);
                int n = 0;
                while (n++ < this.m_howFarToSlide && !this.m_threadStop) {
                    Helper.sleep(this.getSpeedDelay());
                    SwingUtilities.invokeLater(this.m_sliderWorker);
                }
                ViewCentral.this.getMainImagePanel().getStream().cancelStream();
                ViewCentral.this.setMainImageUpdated(false);
                SwingUtilities.invokeLater(this.m_getNewPaintWorker);
                for (int n2 = 0; !ViewCentral.this.isMainImageUpdated() && n2 < 50; ++n2) {
                    Helper.sleep(100L);
                }
            }
        }
        
        protected int getSpeedDelay() {
            return (int)(this.m_slideSpeedDelay * (1.0 - this.m_speedPercentage));
        }
        
        public void stopFlying() {
            this.m_threadStop = true;
        }
        
        public void setSpeedPercentage(int n) {
            if (n <= 0) {
                n = 1;
            }
            if (n >= 100) {
                n = 100;
            }
            this.m_speedPercentage = n / 100.0;
        }
        
        public void setQuadrant(final int moveQuadrant) {
            this.m_moveQuadrant = moveQuadrant;
        }
    }
}
