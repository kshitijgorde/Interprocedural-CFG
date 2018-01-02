// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.util.Enumeration;
import java.util.Properties;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.Paint;
import java.awt.Color;
import java.awt.Font;
import com.itt.J2KViewer.util.SecurityUtil;
import java.awt.Image;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import com.itt.J2KViewer.imagetools.ImageSelection;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import com.itt.J2KViewer.georvm.coords.Utm_Coord_3d;
import java.awt.Rectangle;
import java.io.OutputStream;
import java.awt.Point;
import com.itt.J2KViewer.util.geotiff.GeoTIFFWriter;
import java.io.FileOutputStream;
import java.awt.image.BufferedImage;
import com.itt.J2KViewer.util.ImageUtils;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import com.itt.J2KViewer.util.ViewerConst;
import com.itt.J2KViewer.util.JpegQuality;
import java.io.File;
import java.awt.event.ActionEvent;
import com.itt.J2KViewer.georvm.NITFGeoUtils;
import java.beans.PropertyVetoException;
import java.beans.PropertyChangeEvent;
import javax.swing.AbstractButton;
import com.itt.J2KViewer.actions.ToolbarAndMenuActionCreator;
import javax.swing.Icon;
import com.itt.J2KViewer.util.Helper;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JSplitPane;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.net.URI;
import java.io.IOException;
import com.itt.J2KViewer.controller.PropertyManager;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import java.security.SecureRandom;
import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.TrustManager;
import java.net.URL;
import com.itt.IASjTools.IASJPIPServer;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JToolBar;
import com.itt.J2KViewer.controller.ViewCentral;
import com.itt.J2KViewer.util.Log;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

public class ViewerMainPanel extends JPanel implements PropertyChangeListener, ActionListener
{
    private static final long serialVersionUID = 1L;
    private Log log;
    private ViewCentral viewCentral;
    private MainImagePanel mainImagePanel;
    private InfoPane infoPane;
    private JToolBar tbarMain;
    private StatusBar bottomStatusPanel;
    private JButton zoomInButton;
    private JButton zoomOutButton;
    private JButton panningButton;
    private JButton chippingButton;
    private JButton rotationButton;
    private JButton flyButton;
    private JButton waypointsButton;
    private JButton waypointFollowingButton;
    private JButton magLensButton;
    private JButton[] tbarButtons;
    private JButton manhattanButton;
    private JPanel centerPanel;
    private JComboBox rotateToComboBox;
    private JComboBox stretchComboBox;
    private int defaultDRAType;
    private int bannerYOffset;
    private int bannerXOffset;
    static /* synthetic */ Class class$com$itt$J2KViewer$gui$ViewerMainPanel;
    
    public ViewerMainPanel(final ViewCentral viewCentral) {
        this.log = new Log((ViewerMainPanel.class$com$itt$J2KViewer$gui$ViewerMainPanel == null) ? (ViewerMainPanel.class$com$itt$J2KViewer$gui$ViewerMainPanel = class$("com.itt.J2KViewer.gui.ViewerMainPanel")) : ViewerMainPanel.class$com$itt$J2KViewer$gui$ViewerMainPanel);
        this.viewCentral = null;
        this.mainImagePanel = null;
        this.infoPane = null;
        this.tbarMain = null;
        this.bottomStatusPanel = null;
        this.zoomInButton = null;
        this.zoomOutButton = null;
        this.panningButton = null;
        this.chippingButton = null;
        this.rotationButton = null;
        this.flyButton = null;
        this.waypointsButton = null;
        this.waypointFollowingButton = null;
        this.magLensButton = null;
        this.tbarButtons = null;
        this.manhattanButton = null;
        this.centerPanel = null;
        this.rotateToComboBox = null;
        this.stretchComboBox = null;
        this.bannerYOffset = 0;
        this.bannerXOffset = 0;
        (this.viewCentral = viewCentral).setViewerMainPanel(this);
        viewCentral.eventController().addPropertyChangeListener(this);
        this.initPanel();
    }
    
    private boolean needAuthorization(final String s, final String s2, final int n, final String s3) throws IOException {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final String basicAuthenticationString = propertyManager.getBasicAuthenticationString();
        boolean b = true;
        final URL url = new URL(IASJPIPServer.JPIP_CHANNEL_TYPE, s2, n, "/login-required");
        final URL url2 = new URL(IASJPIPServer.JPIP_CHANNEL_TYPE, s2, n, s3);
        int n2;
        if (propertyManager.isSslEnabled()) {
            final TrustManager[] array = { new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    
                    public void checkClientTrusted(final X509Certificate[] array, final String s) {
                    }
                    
                    public void checkServerTrusted(final X509Certificate[] array, final String s) {
                    }
                } };
            try {
                final SSLContext instance = SSLContext.getInstance("SSL");
                instance.init(null, array, new SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(instance.getSocketFactory());
            }
            catch (Exception ex) {
                this.log.error("unable to accept server certificate by install local trust manager.");
            }
            final HttpsURLConnection httpsURLConnection = (HttpsURLConnection)url2.openConnection();
            if (basicAuthenticationString != null) {
                httpsURLConnection.setRequestProperty("Authorization", "Basic " + basicAuthenticationString);
            }
            try {
                httpsURLConnection.connect();
                n2 = httpsURLConnection.getResponseCode();
            }
            catch (SocketException ex2) {
                return false;
            }
        }
        else {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            try {
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == 200) {
                    final String trim = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())).readLine().trim();
                    if (trim.equalsIgnoreCase("true") || trim.equalsIgnoreCase("false")) {
                        return false;
                    }
                }
            }
            catch (SocketException ex3) {
                return false;
            }
            final HttpURLConnection httpURLConnection2 = (HttpURLConnection)url2.openConnection();
            if (basicAuthenticationString != null) {
                httpURLConnection2.setRequestProperty("Authorization", "Basic " + basicAuthenticationString);
            }
            try {
                httpURLConnection2.connect();
                n2 = httpURLConnection2.getResponseCode();
            }
            catch (SocketException ex4) {
                return false;
            }
        }
        if (n2 != 401) {
            b = false;
        }
        return b;
    }
    
    public void openImage(final URI uri) {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final String scheme = uri.getScheme();
        boolean b = false;
        if ("jpip".equalsIgnoreCase(scheme) || "jpips".equalsIgnoreCase(scheme)) {
            propertyManager.setSslEnabled("jpips".equalsIgnoreCase(scheme));
            final String host = uri.getHost();
            final int port = uri.getPort();
            final String path = uri.getPath();
            int n = 0;
            int n2 = 0;
            try {
                if (this.needAuthorization(IASJPIPServer.JPIP_CHANNEL_TYPE, host, port, path)) {
                    while (n == 0 && n2++ < 3) {
                        if (this.viewCentral.doShowAuthentication()) {
                            if (this.needAuthorization(IASJPIPServer.JPIP_CHANNEL_TYPE, host, port, path)) {
                                continue;
                            }
                            n = 1;
                            b = true;
                        }
                        else {
                            n = 1;
                        }
                    }
                }
                else {
                    b = true;
                }
            }
            catch (Exception ex) {
                this.log.error("Unable to check authorization to open the resource: " + ex.getMessage(), ex);
                this.viewCentral.reportError(this, "Authentication Error", "Unable to check authorization to open the resource: " + ex.getMessage());
            }
        }
        else {
            b = true;
        }
        if (b) {
            this.closeImage();
            this.viewCentral.eventController().addPropertyChangeListener(this.mainImagePanel);
            this.mainImagePanel.openImage(uri);
            if (this.viewCentral.getPropertyManager().isShowSplitPane()) {
                final OverviewImagePanel overviewImagePanel = this.infoPane.getOverviewImagePanel();
                this.viewCentral.eventController().addPropertyChangeListener(overviewImagePanel);
                overviewImagePanel.openImage(uri);
                overviewImagePanel.restartImageStream();
            }
            try {
                this.mainImagePanel.restartImageStream();
            }
            catch (Exception ex2) {
                this.log.error("Failed to restart image stream!", ex2);
            }
            this.mainImagePanel.getImageDisplayPanel().getLensMagnifier().openImage();
        }
        else {
            this.viewCentral.reportError(this, "Authentication Error", "Authentication failure.");
        }
        if (this.viewCentral.getPropertyManager().isAllowChipping()) {
            this.checkChippingService();
        }
        this.setupStretchComboBox();
    }
    
    public void closeImage() {
        if (this.viewCentral.getPropertyManager().isOpen()) {
            this.viewCentral.eventController().removePropertyChangeListener(this.mainImagePanel);
            this.mainImagePanel.closeImage();
            if (this.viewCentral.getPropertyManager().isShowSplitPane()) {
                final OverviewImagePanel overviewImagePanel = this.infoPane.getOverviewImagePanel();
                this.viewCentral.eventController().removePropertyChangeListener(overviewImagePanel);
                overviewImagePanel.closeImage();
            }
        }
    }
    
    private void initPanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(0), BorderFactory.createEmptyBorder(3, 3, 3, 3)));
        this.setName(((ViewerMainPanel.class$com$itt$J2KViewer$gui$ViewerMainPanel == null) ? (ViewerMainPanel.class$com$itt$J2KViewer$gui$ViewerMainPanel = class$("com.itt.J2KViewer.gui.ViewerMainPanel")) : ViewerMainPanel.class$com$itt$J2KViewer$gui$ViewerMainPanel).getName());
        this.viewCentral.getActionManager().setupToolbarAndMenuActionHandler(this.mainImagePanel);
        this.centerPanel = new JPanel();
        this.mainImagePanel = new MainImagePanel(this.viewCentral);
        this.mainImagePanel.getMouseHandler().setContextMenu(new ContextMenu(this.viewCentral.getActionManager()));
        this.viewCentral.setMainImagePanel(this.mainImagePanel);
        this.centerPanel.setLayout(new BoxLayout(this.centerPanel, 3));
        if (this.viewCentral.getPropertyManager().isShowCopyrightBanner()) {
            this.centerPanel.add(new LicenseBar(this.viewCentral));
        }
        if (this.viewCentral.getPropertyManager().isShowSecurityBanner()) {
            this.centerPanel.add(new SecurityBar(this.viewCentral));
        }
        if (this.viewCentral.getPropertyManager().isShowSplitPane()) {
            this.infoPane = new InfoPane(this.viewCentral);
            if (!this.viewCentral.getPropertyManager().isShowOverviewImage()) {
                this.infoPane.setDividerLocation(28);
            }
            this.centerPanel.add(this.mainImagePanel);
            this.add(new JSplitPane(1, this.infoPane, this.centerPanel), "Center");
            this.updateUI();
        }
        else {
            final DynamicRangePanel dynamicRangePanel = new DynamicRangePanel(this.viewCentral);
            this.centerPanel.add(this.mainImagePanel);
            this.add(this.centerPanel, "Center");
        }
        if (this.viewCentral.getPropertyManager().isShowSecurityBanner()) {
            this.centerPanel.add(new SecurityBar(this.viewCentral));
        }
        this.setupMainToolBar();
        (this.tbarButtons = new JButton[9])[1] = this.zoomInButton;
        this.tbarButtons[2] = this.zoomOutButton;
        this.tbarButtons[0] = this.panningButton;
        this.tbarButtons[3] = this.rotationButton;
        this.tbarButtons[8] = this.chippingButton;
        this.tbarButtons[4] = this.waypointsButton;
        this.tbarButtons[5] = this.waypointFollowingButton;
        this.tbarButtons[6] = this.magLensButton;
        this.tbarButtons[7] = this.flyButton;
        this.showToolBar();
        this.setupStatusPanel();
        this.add(this.bottomStatusPanel, "South");
        this.updateUI();
    }
    
    private void setupStatusPanel() {
        this.bottomStatusPanel = new StatusBar(this.viewCentral);
        this.viewCentral.eventController().addPropertyChangeListener(this.bottomStatusPanel);
    }
    
    private void setupMainToolBar() {
        final ToolbarAndMenuActionCreator actionManager = this.viewCentral.getActionManager();
        (this.tbarMain = new JToolBar()).setBorder(BorderFactory.createBevelBorder(0));
        if (System.getProperty("os.name").toLowerCase().indexOf("win") != -1) {
            final AbstractButton abstractButtonForAction = actionManager.getAbstractButtonForAction("Copy Viewport", 1);
            abstractButtonForAction.setMargin(new Insets(0, 0, 0, 0));
            this.tbarMain.add(abstractButtonForAction);
        }
        final AbstractButton abstractButtonForAction2 = actionManager.getAbstractButtonForAction("Save View As...", 1);
        abstractButtonForAction2.setMargin(new Insets(0, 0, 0, 0));
        this.tbarMain.add(abstractButtonForAction2);
        this.tbarMain.addSeparator(new Dimension(30, 20));
        final JButton button = (JButton)actionManager.getAbstractButtonForAction("Set View 1:1", 1);
        button.setSelectedIcon(Helper.loadImage("OnetoOneIcon16.gif", "1:1 View"));
        this.tbarMain.add(button);
        (this.zoomInButton = (JButton)actionManager.getAbstractButtonForAction("Zoom In", 1)).setSelectedIcon(Helper.loadImage("ZoomInSelected24.gif", "Zoom In"));
        this.zoomInButton.setMargin(new Insets(0, 0, 0, 0));
        this.tbarMain.add(this.zoomInButton);
        (this.zoomOutButton = (JButton)actionManager.getAbstractButtonForAction("Zoom Out", 1)).setSelectedIcon(Helper.loadImage("ZoomOutSelected24.gif", "Zoom In"));
        this.zoomOutButton.setMargin(new Insets(0, 0, 0, 0));
        this.tbarMain.add(this.zoomOutButton);
        (this.magLensButton = (JButton)actionManager.getAbstractButtonForAction("Magnifying Glass", 1)).setSelectedIcon(Helper.loadImage("LensNew.png", "Magnification Lens"));
        this.tbarMain.add(this.magLensButton);
        (this.panningButton = (JButton)actionManager.getAbstractButtonForAction("Pan", 1)).setSelectedIcon(Helper.loadImage("PanSelected24.gif", "Panning"));
        this.panningButton.setMargin(new Insets(0, 0, 0, 0));
        this.tbarMain.add(this.panningButton);
        (this.flyButton = (JButton)actionManager.getAbstractButtonForAction("Fly", 1)).setSelectedIcon(Helper.loadImage("fly24.png", "Fly"));
        this.tbarMain.add(this.flyButton);
        (this.waypointsButton = (JButton)actionManager.getAbstractButtonForAction("Create Waypoints", 1)).setSelectedIcon(Helper.loadImage("Waypoints24.png", "Waypoints"));
        this.tbarMain.add(this.waypointsButton);
        (this.waypointFollowingButton = (JButton)actionManager.getAbstractButtonForAction("Waypoint Following", 1)).setSelectedIcon(Helper.loadImage("WaypointFollowing24.png", "Waypoint Following"));
        this.tbarMain.add(this.waypointFollowingButton);
        (this.rotationButton = (JButton)actionManager.getAbstractButtonForAction("Manual Rotation", 1)).setSelectedIcon(Helper.loadImage("Rotate24.png", "Rotation"));
        this.tbarMain.add(this.rotationButton);
        (this.rotateToComboBox = new JComboBox()).setToolTipText("Select a rotation angle");
        this.rotateToComboBox.addActionListener(this);
        this.rotateToComboBox.setMaximumSize(new Dimension(80, 30));
        this.tbarMain.add(this.rotateToComboBox);
        (this.chippingButton = (JButton)actionManager.getAbstractButtonForAction("Chipping", 1)).setSelectedIcon(Helper.loadImage("Chipper24.gif", "Chipping"));
        this.chippingButton.setMargin(new Insets(0, 0, 0, 0));
        this.tbarMain.add(this.chippingButton);
        final AbstractButton abstractButtonForAction3 = actionManager.getAbstractButtonForAction("Jump To...", 1);
        abstractButtonForAction3.setMargin(new Insets(0, 0, 0, 0));
        this.tbarMain.add(abstractButtonForAction3);
        this.tbarMain.addSeparator(new Dimension(30, 20));
        (this.stretchComboBox = new JComboBox((E[])this.viewCentral.getDRAManager().getStretchTypes())).setToolTipText("Auto DRA Type");
        this.stretchComboBox.addActionListener(this);
        this.stretchComboBox.setMaximumSize(new Dimension(110, 30));
        this.tbarMain.add(this.stretchComboBox);
        final AbstractButton abstractButtonForAction4 = actionManager.getAbstractButtonForAction("Auto DRA", 1);
        abstractButtonForAction4.setMargin(new Insets(0, 0, 0, 0));
        this.tbarMain.add(abstractButtonForAction4);
        this.tbarMain.addSeparator(new Dimension(30, 20));
        final AbstractButton abstractButtonForAction5 = actionManager.getAbstractButtonForAction("Bands...", 1);
        abstractButtonForAction5.setMargin(new Insets(0, 0, 0, 0));
        this.tbarMain.add(abstractButtonForAction5);
        final AbstractButton abstractButtonForAction6 = actionManager.getAbstractButtonForAction("DRA...", 1);
        abstractButtonForAction6.setMargin(new Insets(0, 0, 0, 0));
        this.tbarMain.add(abstractButtonForAction6);
        this.tbarMain.add(actionManager.getAbstractButtonForAction("Wavelet Sharpening Dialog", 1));
        this.tbarMain.add(actionManager.getAbstractButtonForAction("Annotations Dialog", 1));
    }
    
    private void resetButtonState(final int n, final int n2) {
        if (this.tbarButtons != null) {
            switch (n) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8: {
                    this.tbarButtons[n].setSelected(false);
                    break;
                }
            }
            switch (n2) {
                case -1: {
                    this.tbarButtons[1].setSelected(false);
                    this.tbarButtons[2].setSelected(false);
                    this.tbarButtons[0].setSelected(false);
                    this.tbarButtons[3].setSelected(false);
                    this.tbarButtons[7].setSelected(false);
                    this.tbarButtons[4].setSelected(false);
                    this.tbarButtons[5].setSelected(false);
                    this.tbarButtons[6].setSelected(false);
                    break;
                }
                case 1:
                case 2: {
                    this.tbarButtons[n2].setSelected(true);
                    break;
                }
                case 6: {
                    this.tbarButtons[n2].setSelected(true);
                    break;
                }
                case 0: {
                    this.tbarButtons[n2].setSelected(true);
                    break;
                }
                case 3: {
                    this.tbarButtons[n2].setSelected(true);
                    break;
                }
                case 7: {
                    this.tbarButtons[n2].setSelected(true);
                    break;
                }
                case 4: {
                    this.tbarButtons[n2].setSelected(true);
                    break;
                }
                case 5: {
                    this.tbarButtons[n2].setSelected(true);
                    break;
                }
                case 8: {
                    this.tbarButtons[n2].setSelected(true);
                    break;
                }
            }
            if (this.mainImagePanel != null) {
                this.mainImagePanel.changeCursor(n2);
            }
        }
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final String propertyName = propertyChangeEvent.getPropertyName();
        if (propertyName != null) {
            if (propertyName.equals("ShowToolBar")) {
                this.showToolBar();
            }
            else if (!propertyName.equals("DiscardedZoomLevels")) {
                if (propertyName.equals("Open")) {
                    try {
                        if (this.viewCentral.getPropertyManager().isOpen()) {
                            this.viewCentral.getPropertyManager().setTransformationMode(0);
                            this.adjustRotationItems();
                        }
                        else {
                            this.viewCentral.getPropertyManager().setTransformationMode(-1);
                        }
                    }
                    catch (PropertyVetoException ex) {
                        this.log.error(ex);
                    }
                }
                else if (propertyName.equals("TranformationMode")) {
                    final int intValue = (int)propertyChangeEvent.getOldValue();
                    final int intValue2 = (int)propertyChangeEvent.getNewValue();
                    this.resetButtonState(intValue, intValue2);
                    if (intValue2 == 6) {
                        this.mainImagePanel.turnOnMagnificationLens(true);
                    }
                    else if (intValue == 6) {
                        this.mainImagePanel.turnOnMagnificationLens(false);
                    }
                }
                else if (propertyName.equals("RotationAngle")) {
                    this.adjustRotationItems();
                }
            }
        }
    }
    
    private void adjustRotationItems() {
        final double rotationAngle = this.viewCentral.getPropertyManager().getRotationAngle();
        final NITFGeoUtils nitfGeoUtils = this.viewCentral.getNITFGeoUtils();
        this.rotateToComboBox.removeAllItems();
        String s = Double.toString(Math.toDegrees(rotationAngle));
        final int index = s.indexOf(46);
        if (index > 0) {
            s = s.substring(0, index + 2);
        }
        this.rotateToComboBox.addItem(new RotateItem(s + "°", rotationAngle));
        if (rotationAngle != 0.0) {
            this.rotateToComboBox.addItem(new RotateItem("Reset", 0.0));
        }
        if (nitfGeoUtils != null && nitfGeoUtils.isReady()) {
            this.rotateToComboBox.addItem(new RotateItem("North Up", -nitfGeoUtils.getNorthRotationFactor()));
            if (nitfGeoUtils.hasRPC()) {
                this.rotateToComboBox.addItem(new RotateItem("Look Angle", -nitfGeoUtils.getRPCUpRotationFactor()));
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.rotateToComboBox) {
            final RotateItem rotateItem = (RotateItem)this.rotateToComboBox.getSelectedItem();
            if (rotateItem != null) {
                final double access$000 = rotateItem.value;
                if (access$000 != this.viewCentral.getPropertyManager().getRotationAngle()) {
                    this.viewCentral.getPropertyManager().setRotationAngle(access$000);
                    try {
                        this.viewCentral.getPropertyManager().setTransformationMode(0);
                    }
                    catch (Exception ex) {}
                    this.viewCentral.viewChanged();
                }
            }
        }
        if (actionEvent.getSource() == this.stretchComboBox) {
            final int selectedIndex = this.stretchComboBox.getSelectedIndex();
            if (selectedIndex != this.viewCentral.getDRAManager().getAutoStretchType()) {
                this.viewCentral.getDRAManager().setAutoStretchType(selectedIndex);
                this.viewCentral.getDRAManager().apply(selectedIndex);
            }
        }
    }
    
    private void showToolBar() {
        if (this.viewCentral.getPropertyManager().isShowToolBar()) {
            this.add(this.tbarMain, "North");
        }
        else {
            this.remove(this.tbarMain);
        }
        this.updateUI();
    }
    
    public void saveViewToJPEGFile(final File file) {
        final MainImagePanel mainImagePanel = this.viewCentral.getMainImagePanel();
        BufferedImage bufferedImage = mainImagePanel.getBufferedImage();
        final MainImageDisplayPanel imageDisplayPanel = mainImagePanel.getImageDisplayPanel();
        imageDisplayPanel.drawCrosshair(bufferedImage.getGraphics());
        imageDisplayPanel.paintWaypoints(bufferedImage.getGraphics(), 0, 0);
        imageDisplayPanel.paintNorthArrow(bufferedImage.getGraphics());
        imageDisplayPanel.paintMagnificationLensForPrint(bufferedImage.getGraphics());
        if (this.viewCentral.getPropertyManager().isShowSecurityBanner()) {
            bufferedImage = this.drawSecurityInformation(bufferedImage);
        }
        if (file.exists()) {
            file.delete();
        }
        final JpegQuality jpegQuality = (JpegQuality)JOptionPane.showInputDialog(this.viewCentral.getJ2KViewerFrame(), "Select a quality setting:", "Quality", -1, null, ViewerConst.JPEG_QUALITY, ViewerConst.JPEG_QUALITY[0]);
        if (jpegQuality != null) {
            try {
                ImageUtils.writeJPEG(bufferedImage, ImageIO.createImageOutputStream(file), jpegQuality.value);
            }
            catch (IOException ex) {
                this.log.error("Error while creating JPEG image.", ex);
            }
        }
    }
    
    public void saveViewToGeoTIFFFile(final File file) {
        final MainImagePanel mainImagePanel = this.viewCentral.getMainImagePanel();
        BufferedImage bufferedImage = mainImagePanel.getBufferedImage();
        final MainImageDisplayPanel imageDisplayPanel = mainImagePanel.getImageDisplayPanel();
        imageDisplayPanel.drawCrosshair(bufferedImage.getGraphics());
        imageDisplayPanel.paintWaypoints(bufferedImage.getGraphics(), 0, 0);
        imageDisplayPanel.paintNorthArrow(bufferedImage.getGraphics());
        imageDisplayPanel.paintMagnificationLensForPrint(bufferedImage.getGraphics());
        if (this.viewCentral.getPropertyManager().isShowSecurityBanner()) {
            bufferedImage = this.drawSecurityInformation(bufferedImage);
        }
        if (file.exists()) {
            file.delete();
        }
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            final GeoTIFFWriter geoTIFFWriter = new GeoTIFFWriter(bufferedImage);
            final Rectangle viewPort = this.viewCentral.getPropertyManager().getViewPort();
            final Point displayToImage = this.viewCentral.getDimensionManager().displayToImage(new Point(viewPort.x, viewPort.y));
            final NITFGeoUtils nitfGeoUtils = this.viewCentral.getNITFGeoUtils();
            final Utm_Coord_3d utmLocation = nitfGeoUtils.getUTMLocation(displayToImage.x, displayToImage.y);
            final double n = -nitfGeoUtils.getUTMRotationFactor() - this.viewCentral.getDimensionManager().getRotationAngle();
            double xPixelScale = nitfGeoUtils.getXPixelScale();
            double yPixelScale = nitfGeoUtils.getYPixelScale();
            final int discardedZoomLevels = this.viewCentral.getPropertyManager().getDiscardedZoomLevels();
            if (discardedZoomLevels > 0) {
                xPixelScale *= 1 << discardedZoomLevels;
                yPixelScale *= 1 << discardedZoomLevels;
            }
            else if (discardedZoomLevels < 0) {
                xPixelScale /= 1 << -discardedZoomLevels;
                yPixelScale /= 1 << -discardedZoomLevels;
            }
            geoTIFFWriter.setTransformation(utmLocation, n, xPixelScale, yPixelScale, this.bannerXOffset, this.bannerYOffset);
            geoTIFFWriter.write(fileOutputStream);
        }
        catch (IOException ex) {
            this.log.error("Error while creating GeoTIFF image.", ex);
        }
    }
    
    public void copyViewToClipboard() {
        final MainImagePanel mainImagePanel = this.viewCentral.getMainImagePanel();
        BufferedImage bufferedImage = mainImagePanel.getBufferedImage();
        final MainImageDisplayPanel imageDisplayPanel = mainImagePanel.getImageDisplayPanel();
        imageDisplayPanel.drawCrosshair(bufferedImage.getGraphics());
        imageDisplayPanel.paintWaypoints(bufferedImage.getGraphics(), 0, 0);
        imageDisplayPanel.paintNorthArrow(bufferedImage.getGraphics());
        imageDisplayPanel.paintMagnificationLensForPrint(bufferedImage.getGraphics());
        if (this.viewCentral.getPropertyManager().isShowSecurityBanner()) {
            bufferedImage = this.drawSecurityInformation(bufferedImage);
        }
        final int width = bufferedImage.getWidth();
        final int height = bufferedImage.getHeight();
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageUtils.writeJPEG(bufferedImage, ImageIO.createImageOutputStream(byteArrayOutputStream), 1.0f);
        }
        catch (IOException ex) {
            this.log.error("Error while creating JPEG image.", ex);
        }
        final Image image = Toolkit.getDefaultToolkit().createImage(byteArrayOutputStream.toByteArray());
        try {
            final MediaTracker mediaTracker = new MediaTracker(mainImagePanel);
            mediaTracker.addImage(image, 0, width, height);
            mediaTracker.waitForID(0);
        }
        catch (Exception ex2) {
            this.log.error("MediaTracker Error!  ", ex2);
        }
        try {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new ImageSelection(image), null);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            this.log.error("The java virtual machine has run out of memory.", outOfMemoryError);
            this.viewCentral.reportError(this, "Out of Memory", "The java virtual machine has run out of memory: " + outOfMemoryError.getMessage());
        }
        if (imageDisplayPanel.isShowingMagnificationLens()) {
            this.viewCentral.viewChanged();
        }
    }
    
    private BufferedImage drawSecurityInformation(final BufferedImage bufferedImage) {
        boolean b = false;
        String s;
        if ("FILE".equalsIgnoreCase(this.viewCentral.getPropertyManager().getSecuritySegment())) {
            s = "FILE";
        }
        else {
            s = "IMAGE";
        }
        final String securityClassification = SecurityUtil.getSecurityClassification(this.viewCentral, s);
        final String classificationString = SecurityUtil.getClassificationString(securityClassification);
        final String securityReleasability = SecurityUtil.getSecurityReleasability(this.viewCentral, s);
        final String string = "Collection Date : " + SecurityUtil.getCollectionDate(this.viewCentral);
        final Color classificationColor = SecurityUtil.getClassificationColor(securityClassification);
        String s2;
        if (securityReleasability.trim().length() != 0) {
            s2 = classificationString + " : " + securityReleasability;
        }
        else {
            s2 = classificationString + " ";
        }
        final Graphics2D graphics = bufferedImage.createGraphics();
        final Font font = new Font("SansSerif", 1, 12);
        graphics.setFont(font);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int stringWidth = fontMetrics.stringWidth(s2 + " " + string);
        int width;
        if (stringWidth + 20 < bufferedImage.getWidth()) {
            width = bufferedImage.getWidth();
        }
        else {
            width = stringWidth + 20;
            b = true;
        }
        final int bannerYOffset = fontMetrics.getHeight() + 10;
        this.bannerYOffset = bannerYOffset;
        final BufferedImage bufferedImage2 = new BufferedImage(width, bannerYOffset, bufferedImage.getType());
        final Graphics2D graphics2 = bufferedImage2.createGraphics();
        graphics2.setBackground(classificationColor);
        graphics2.clearRect(0, 0, bufferedImage2.getWidth(), bufferedImage2.getHeight());
        graphics2.setFont(font);
        graphics2.setPaint(Color.BLACK);
        graphics2.drawString(s2, 10, bannerYOffset - 7);
        graphics2.drawString(string, bufferedImage2.getWidth() - (fontMetrics.stringWidth(string) + 10), bannerYOffset - 7);
        final BufferedImage bufferedImage3 = new BufferedImage(width, bufferedImage.getHeight() + 2 * bannerYOffset, bufferedImage.getType());
        final Graphics2D graphics3 = bufferedImage3.createGraphics();
        graphics3.drawImage(bufferedImage2, 0, 0, null);
        if (b) {
            graphics3.drawImage(bufferedImage, this.bannerXOffset = (width - bufferedImage.getWidth()) / 2, bannerYOffset, null);
        }
        else {
            graphics3.drawImage(bufferedImage, 0, bannerYOffset, null);
        }
        graphics3.drawImage(bufferedImage2, 0, bufferedImage.getHeight() + bannerYOffset, null);
        return bufferedImage3;
    }
    
    public void setupStretchComboBox() {
        this.defaultDRAType = this.viewCentral.getPropertyManager().getDefaultDRAType();
        final String[] stretchTypes = this.viewCentral.getDRAManager().getStretchTypes();
        stretchTypes[2] = this.viewCentral.getPropertyManager().getDefaultDRAPercent() + " Percent";
        this.stretchComboBox.removeAllItems();
        for (int i = 0; i < stretchTypes.length; ++i) {
            this.stretchComboBox.addItem(stretchTypes[i]);
        }
        if (this.viewCentral.getPropertyManager().isUponLoadDoAutoDRA()) {
            this.stretchComboBox.setSelectedIndex(this.defaultDRAType);
        }
        else {
            this.stretchComboBox.setSelectedIndex(0);
        }
    }
    
    public void updateStretchPercent() {
        final String[] stretchTypes = this.viewCentral.getDRAManager().getStretchTypes();
        stretchTypes[2] = this.viewCentral.getPropertyManager().getDefaultDRAPercent() + " Percent";
        final int selectedIndex = this.stretchComboBox.getSelectedIndex();
        this.stretchComboBox.removeAllItems();
        for (int i = 0; i < stretchTypes.length; ++i) {
            this.stretchComboBox.addItem(stretchTypes[i]);
        }
        this.stretchComboBox.setSelectedIndex(selectedIndex);
    }
    
    public void checkChippingService() {
        this.log.debug("ViewerMainPanel: Checking chipping...");
        try {
            final Properties envVars = Helper.getEnvVars();
            String property = null;
            final Enumeration<?> propertyNames = envVars.propertyNames();
            while (propertyNames.hasMoreElements()) {
                final String s = (String)propertyNames.nextElement();
                if (s.equalsIgnoreCase("javaidljar")) {
                    property = envVars.getProperty(s);
                    break;
                }
            }
            if (property == null) {
                this.log.debug("JavaIDLJar environment variable does not exist");
                this.viewCentral.getPropertyManager().setAllowChipping(false);
            }
        }
        catch (Throwable t) {
            this.log.info("Failed checking availability of chipping.");
            System.out.println(t.toString());
            this.viewCentral.getPropertyManager().setAllowChipping(false);
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
    
    class RotateItem
    {
        private String label;
        private double value;
        
        public RotateItem(final String label, final double value) {
            this.label = label;
            this.value = value;
        }
        
        public String toString() {
            return this.label;
        }
    }
}
