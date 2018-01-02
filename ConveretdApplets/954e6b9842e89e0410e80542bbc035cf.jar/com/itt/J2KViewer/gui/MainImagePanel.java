// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import com.itt.J2KViewer.georvm.transforms.MGRSConversion;
import java.awt.Image;
import org.w3c.dom.NodeList;
import java.util.Iterator;
import org.w3c.dom.Document;
import java.io.IOException;
import java.util.List;
import com.itt.J2KViewer.georvm.transforms.Utm_To_Gdc_Converter;
import com.itt.J2KViewer.georvm.coords.Gdc_Coord_3d;
import com.itt.J2KViewer.georvm.transforms.Geodetic_to_UTM_Converter;
import java.awt.geom.Point2D;
import com.itt.J2KViewer.georvm.coords.Utm_Coord_3d;
import com.ittvis.imageio.util.GeoTiffIIOMetadataAdapter;
import com.itt.J2KViewer.util.ImageUtils;
import com.itt.IASjTools.IASImage;
import com.itt.J2KViewer.util.GeoJumpUtil;
import java.io.File;
import java.awt.Cursor;
import com.itt.J2KViewer.util.Helper;
import java.awt.Toolkit;
import java.awt.event.AdjustmentEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import com.itt.J2KViewer.controller.DimensionManager;
import com.itt.J2KViewer.controller.PropertyManager;
import java.beans.PropertyVetoException;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.net.URI;
import com.itt.J2KViewer.georvm.NITFGeoUtils;
import com.itt.J2KViewer.util.XmlPropertiesParser;
import com.itt.J2KViewer.controller.ViewCentral;
import com.itt.J2KViewer.imagetools.MainImageStream;
import java.awt.Point;
import javax.swing.JScrollBar;
import java.awt.Rectangle;
import com.itt.J2KViewer.util.Log;
import java.beans.PropertyChangeListener;
import java.awt.event.AdjustmentListener;
import javax.swing.JPanel;

public class MainImagePanel extends JPanel implements AdjustmentListener, PropertyChangeListener
{
    private static final long serialVersionUID = 1L;
    private static Log log;
    private static final int UNIT_INCREMENT = 10;
    private Rectangle[] imageSize;
    private MainImageDisplayPanel mainImageDisplayPanel;
    private JScrollBar verticalScrollBar;
    private JScrollBar horizontalScrollBar;
    private boolean showScrollbars;
    private Point imageOrigin;
    private Point imageDisplayOffset;
    private MainImageStream stream;
    private ViewCentral viewCentral;
    private XmlPropertiesParser xmlPropertiesParser;
    private NITFGeoUtils nitfGeoUtils;
    private ImageDisplayMouseHandler mainImageDisplayMouseHandler;
    private Point panOffset;
    private double rotationAngle;
    private Point popupTriggerPoint;
    private URI imageURI;
    static /* synthetic */ Class class$com$itt$J2KViewer$gui$MainImagePanel;
    
    public MainImagePanel(final ViewCentral viewCentral) {
        this.imageOrigin = new Point(0, 0);
        this.imageDisplayOffset = new Point(0, 0);
        this.xmlPropertiesParser = null;
        this.nitfGeoUtils = null;
        this.mainImageDisplayMouseHandler = null;
        this.panOffset = new Point(0, 0);
        this.rotationAngle = 0.0;
        this.viewCentral = viewCentral;
        this.xmlPropertiesParser = this.viewCentral.getXmlPropertiesParser();
        this.setLayout(new BorderLayout());
        this.mainImageDisplayPanel = new MainImageDisplayPanel(this, this.viewCentral);
        (this.verticalScrollBar = new JScrollBar(1)).addAdjustmentListener(this);
        (this.horizontalScrollBar = new JScrollBar(0)).addAdjustmentListener(this);
        this.add(this.mainImageDisplayPanel, "Center");
        this.add(this.verticalScrollBar, "East");
        this.add(this.horizontalScrollBar, "South");
        this.mainImageDisplayMouseHandler = new ImageDisplayMouseHandler(this.viewCentral);
        this.mainImageDisplayPanel.addMouseListener(this.mainImageDisplayMouseHandler);
        this.mainImageDisplayPanel.addMouseMotionListener(this.mainImageDisplayMouseHandler);
        this.mainImageDisplayPanel.addMouseWheelListener(this.mainImageDisplayMouseHandler);
        this.showScrollbars = this.viewCentral.getPropertyManager().isShowScrollbars();
        this.verticalScrollBar.setVisible(this.showScrollbars);
        this.horizontalScrollBar.setVisible(this.showScrollbars);
    }
    
    public Point getPanOffset() {
        return this.panOffset;
    }
    
    public void setPanOffset(final Point panOffset) {
        this.panOffset = panOffset;
    }
    
    public void setPopupTriggerPoint(final Point point) {
        this.popupTriggerPoint = new Point(point);
    }
    
    public Point getPopupTriggerPoint() {
        return new Point(this.popupTriggerPoint);
    }
    
    public double getRotationAngle() {
        return this.rotationAngle;
    }
    
    public void setRotationAngle(final double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }
    
    public MainImageDisplayPanel getImageDisplayPanel() {
        return this.mainImageDisplayPanel;
    }
    
    public int getDisplayWidth() {
        return this.mainImageDisplayPanel.getWidth();
    }
    
    public int getDisplayHeight() {
        return this.mainImageDisplayPanel.getHeight();
    }
    
    public ImageDisplayMouseHandler getMouseHandler() {
        return this.mainImageDisplayMouseHandler;
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final String propertyName = propertyChangeEvent.getPropertyName();
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
        if (propertyName != null) {
            if (propertyName.equals("DiscardedZoomLevels")) {
                if (propertyManager.isOpen()) {
                    propertyManager.setInitialImageLoad(false);
                    final Dimension imageSize = dimensionManager.getImageSize(propertyManager.getDiscardedZoomLevels());
                    try {
                        propertyManager.setCurrentDimensions(new Rectangle(0, 0, imageSize.width, imageSize.height));
                    }
                    catch (PropertyVetoException ex) {
                        MainImagePanel.log.warn("Unable to set current image dimensions.", ex);
                    }
                }
            }
            else if (propertyName.equals("ShowScrollbars")) {
                this.showScrollbars = propertyManager.isShowScrollbars();
                this.verticalScrollBar.setVisible(this.showScrollbars);
                this.horizontalScrollBar.setVisible(this.showScrollbars);
            }
            else if (propertyName.equals("NewDRA")) {
                if (propertyManager.isOpen()) {
                    this.viewCentral.viewChanged();
                }
            }
            else if (propertyName.equals("WaveletSharpeningGain")) {
                this.viewCentral.viewChanged();
            }
            else if (propertyName.equals("RGBMap")) {
                if (propertyManager.isOpen()) {
                    propertyManager.setInitialImageLoad(false);
                    this.viewCentral.viewChanged();
                }
            }
            else if (propertyName.equals("UserLocation")) {
                if (propertyManager.isOpen()) {
                    propertyManager.setInitialImageLoad(false);
                    this.viewCentral.viewChanged();
                }
            }
            else if (propertyName.equals("QualityLayers")) {
                if (propertyManager.isOpen()) {
                    propertyManager.setInitialImageLoad(false);
                    this.viewCentral.viewChanged();
                }
            }
            else if (propertyName.equals("chipEnd") || propertyName.equals("chipStart")) {
                if (propertyManager.getTransformationMode() == 8) {
                    this.mainImageDisplayPanel.repaint();
                }
            }
            else if (propertyName.equals("ShowNorthArrow")) {
                this.viewCentral.viewChanged();
            }
            else if (propertyName.equals("ShowWaypoints")) {
                this.viewCentral.viewChanged();
            }
        }
    }
    
    public Point getImageDisplayOffset() {
        return this.imageDisplayOffset;
    }
    
    public void setImageWindowOrigin(final Point imageOrigin) {
        this.imageOrigin = imageOrigin;
    }
    
    public BufferedImage getBufferedImage() {
        if (this.stream != null) {
            return this.stream.getCurrentImage();
        }
        return null;
    }
    
    public int[] getPixelBuffer() {
        if (this.stream != null) {
            return null;
        }
        return null;
    }
    
    public void drawImageAt(final Point point, final double n) {
        this.mainImageDisplayPanel.paintImage(this.mainImageDisplayPanel.getGraphics(), point.x, point.y, n);
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
        final Rectangle displayWindow = dimensionManager.getDisplayWindow();
        final Point displayWindowCenter = dimensionManager.getDisplayWindowCenter();
        if (propertyManager != null && propertyManager.isOpen() && !adjustmentEvent.getValueIsAdjusting()) {
            final int value = adjustmentEvent.getValue();
            if (adjustmentEvent.getAdjustable() == this.horizontalScrollBar && value != displayWindow.x) {
                dimensionManager.setDisplayWindowCenter(new Point(value + displayWindow.width / 2, displayWindowCenter.y));
                this.viewCentral.viewChanged();
            }
            else if (adjustmentEvent.getAdjustable() == this.verticalScrollBar && value != displayWindow.y) {
                dimensionManager.setDisplayWindowCenter(new Point(displayWindowCenter.x, value + displayWindow.height / 2));
                this.viewCentral.viewChanged();
            }
        }
    }
    
    public void updateDisplay() {
        if (!this.viewCentral.isPanning()) {
            this.setPanOffset(new Point(0, 0));
            this.viewCentral.getPropertyManager().fireDisplayUpdatedEvent();
            this.repaint();
        }
    }
    
    public void changeToZoomCursor(final boolean b, final boolean b2) {
        Cursor customCursor;
        if (!b) {
            String s;
            if (b2) {
                s = "ZoomInCursor.gif";
            }
            else {
                s = "ZoomOutCursor.gif";
            }
            customCursor = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(Helper.getURLAsResource(s)), new Point(3, 3), "ZoomIn");
        }
        else {
            customCursor = new Cursor(0);
        }
        this.mainImageDisplayPanel.setCursor(customCursor);
    }
    
    public void changeCursor(final int n) {
        String s = null;
        Point point = null;
        String s2 = null;
        switch (n) {
            case 1: {
                s = "ZoomInCursor.gif";
                point = new Point(3, 3);
                s2 = "Zoom In";
                break;
            }
            case 2: {
                s = "ZoomOutCursor.gif";
                point = new Point(3, 3);
                s2 = "Zoom Out";
                break;
            }
            case 0: {
                s = "hand32.gif";
                point = new Point(16, 16);
                s2 = "Pan";
                break;
            }
            case 3: {
                s = "RotateCursor.gif";
                point = new Point(16, 16);
                s2 = "Rotate";
                break;
            }
        }
        Cursor customCursor;
        if (s == null) {
            customCursor = new Cursor(0);
        }
        else {
            customCursor = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(Helper.getURLAsResource(s)), point, s2);
        }
        this.mainImageDisplayPanel.setCursor(customCursor);
    }
    
    public void openImage(final URI uri) {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
        final String query = uri.getQuery();
        int int1 = -3;
        double double1 = -91.0;
        double double2 = -181.0;
        double double3 = -1.0;
        double double4 = -1.0;
        String s = null;
        Object o = null;
        String imageURL;
        if (query != null) {
            final String[] split = query.split("&");
            for (int i = 0; i < split.length; ++i) {
                try {
                    final String[] split2 = split[i].split("=");
                    if ("lat".equals(split2[0].toLowerCase())) {
                        double1 = Double.parseDouble(split2[1]);
                        o = "geo";
                    }
                    if ("lon".equals(split2[0].toLowerCase())) {
                        double2 = Double.parseDouble(split2[1]);
                    }
                    if ("east".equals(split2[0].toLowerCase())) {
                        double3 = Double.parseDouble(split2[1]);
                        o = "utm";
                    }
                    if ("north".equals(split2[0].toLowerCase())) {
                        double4 = Double.parseDouble(split2[1]);
                    }
                    if ("mgrs".equals(split2[0].toLowerCase())) {
                        s = split2[1];
                        o = "mgrs";
                    }
                    if ("zoom".equals(split2[0].toLowerCase())) {
                        int1 = Integer.parseInt(split2[1]);
                    }
                }
                catch (NumberFormatException ex4) {}
            }
            final String string = uri.toString();
            imageURL = string.substring(0, string.indexOf(query) - 1);
        }
        else {
            imageURL = uri.toString();
        }
        try {
            this.stream = new MainImageStream(this.viewCentral, this);
            if (propertyManager.getLoginId() != null && propertyManager.getLoginPwd() != null) {
                this.stream.openImage(imageURL, propertyManager.getLoginId(), propertyManager.getLoginPwd(), propertyManager.getCachingIsEnabled(), new File(propertyManager.getJpipCacheDirectory()));
            }
            else {
                this.stream.openImage(imageURL, null, null, propertyManager.getCachingIsEnabled(), new File(propertyManager.getJpipCacheDirectory()));
            }
            this.imageURI = new URI(imageURL);
            propertyManager.setImageURL(imageURL);
        }
        catch (Exception ex) {
            MainImagePanel.log.error("Unable to create new main image stream.", ex);
            this.viewCentral.reportError(null, "Could not open image ", ex.getMessage());
            return;
        }
        final IASImage imageFileProperties = this.stream.getImageFileProperties();
        try {
            this.viewCentral.setImageStream(this.stream);
            final int numDiscardLevels = imageFileProperties.getNumDiscardLevels();
            final int width = this.mainImageDisplayPanel.getWidth();
            final int height = this.mainImageDisplayPanel.getHeight();
            final int width2 = imageFileProperties.getWidth();
            final int height2 = imageFileProperties.getHeight();
            dimensionManager.newImage(width2, height2, numDiscardLevels);
            int bestFitDiscardLevel = dimensionManager.getBestFitDiscardLevel(new Dimension(width, height));
            if (bestFitDiscardLevel > 0) {
                --bestFitDiscardLevel;
            }
            if (int1 >= -2 && int1 <= numDiscardLevels) {
                bestFitDiscardLevel = int1;
            }
            propertyManager.setTotalDimensions(new Rectangle(dimensionManager.getImageSize(0)));
            propertyManager.setDiscardedZoomLevels(bestFitDiscardLevel);
            propertyManager.setCurrentDimensions(new Rectangle(dimensionManager.getImageSize(bestFitDiscardLevel)));
            propertyManager.setMaxDiscardedZoomLevels(numDiscardLevels);
            final int numQualityLayers = imageFileProperties.getNumQualityLayers();
            propertyManager.setTotalQualityLayers(numQualityLayers);
            if (propertyManager.isSetQualityLayersPercentToDownload()) {
                propertyManager.setQualityLayers(propertyManager.getQualityLayersPercentToDownload() * numQualityLayers / 100);
            }
            else {
                propertyManager.setQualityLayers(numQualityLayers);
            }
            if (propertyManager.getOverviewQualityLayersPercentToDownload() != -1) {
                propertyManager.setOverviewQualityLayers(propertyManager.getOverviewQualityLayersPercentToDownload() * numQualityLayers / 100);
            }
            else {
                propertyManager.setOverviewQualityLayers(numQualityLayers);
            }
            final int numComponents = imageFileProperties.getNumComponents();
            propertyManager.setNumberOfComponents(numComponents);
            propertyManager.setDataPrecision(imageFileProperties.getIASComponents()[0].getPrecision());
            this.xmlPropertiesParser = null;
            this.loadXmlMetaData(this.stream, width2, height2);
            this.initializeGeoLocationData();
            int[] rgbMap;
            if (numComponents < 3) {
                rgbMap = new int[] { 0 };
                propertyManager.setShowRGB(false);
            }
            else {
                rgbMap = this.getRGBMap();
                if (rgbMap == null) {
                    rgbMap = new int[] { 0, 1, 2 };
                }
                propertyManager.setShowRGB(true);
            }
            propertyManager.setLastColorBands(rgbMap.clone());
            propertyManager.setLastGrayScaleBand(rgbMap[0]);
            propertyManager.setRGBMap(rgbMap);
            this.setImageWindowOrigin(new Point(0, 0));
        }
        catch (Exception ex2) {
            MainImagePanel.log.error("Exception while opening image.", ex2);
            this.viewCentral.reportError(null, "Opening Image", ex2.getMessage());
            return;
        }
        try {
            final GeoJumpUtil geoJumpUtil = new GeoJumpUtil(this.viewCentral);
            final Dimension imageSize = dimensionManager.getImageSize(0);
            if (o != null) {
                Point userLocation = new Point(-1, -1);
                if ("geo".equals(o)) {
                    userLocation = geoJumpUtil.latLonToPixel(double1, double2);
                }
                else if ("utm".equals(o)) {
                    userLocation = geoJumpUtil.eastNorthToPixel(double3, double4);
                }
                else if ("mgrs".equals(o)) {
                    userLocation = geoJumpUtil.mgrsToPixel(s);
                }
                final int n = userLocation.x - imageSize.width / 2;
                final int n2 = userLocation.y - imageSize.height / 2;
                if (n < 0) {}
                if (n2 < 0) {}
                propertyManager.setViewPort(new Rectangle(0, 0, imageSize.width, imageSize.height));
                if (userLocation.x >= 0 && userLocation.x < imageSize.width && userLocation.y >= 0 && userLocation.y < imageSize.height) {
                    propertyManager.setUserLocation(userLocation);
                    dimensionManager.setDisplayWindowCenter(dimensionManager.imageToDisplay(userLocation));
                }
                else {
                    propertyManager.setUserLocation(null);
                }
            }
            else {
                propertyManager.setViewPort(new Rectangle(0, 0, imageSize.width, imageSize.height));
                propertyManager.setUserLocation(null);
            }
            propertyManager.setOpen(true);
        }
        catch (PropertyVetoException ex3) {
            MainImagePanel.log.error("Unable to mark the image as being open.", ex3);
            return;
        }
        propertyManager.setInitialImageLoad(true);
    }
    
    public void closeImage() {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        if (propertyManager != null && propertyManager.isOpen()) {
            try {
                this.viewCentral.getPropertyManager().setOpen(false);
            }
            catch (PropertyVetoException ex) {
                MainImagePanel.log.error("Unable to mark the image as being closed.", ex);
            }
            try {
                this.stream.cancelStream();
            }
            catch (Exception ex2) {
                MainImagePanel.log.error("Error canceling stream.", ex2);
            }
            this.stream.close();
            this.viewCentral.setImageStream(null);
            try {
                this.viewCentral.getPropertyManager().setIASStatus("Closed");
            }
            catch (PropertyVetoException ex3) {
                MainImagePanel.log.error(ex3.toString());
                ex3.printStackTrace();
            }
            this.getStream().getStream().getInput().saveCacheFile();
            this.mainImageDisplayPanel.repaint();
        }
    }
    
    public void restartImageStream() throws Exception {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
        if (propertyManager != null && propertyManager.isOpen()) {
            final int discardedZoomLevels = propertyManager.getDiscardedZoomLevels();
            final double rotationAngle = propertyManager.getRotationAngle();
            dimensionManager.setDiscardLevel(discardedZoomLevels);
            dimensionManager.setRotationAngle(rotationAngle);
            dimensionManager.setDisplaySize(this.mainImageDisplayPanel.getSize());
            dimensionManager.adjustDisplayWindow(true);
            final Rectangle requestWindow = dimensionManager.getRequestWindow();
            final Rectangle displaySpace = dimensionManager.getDisplaySpace();
            final Rectangle displayWindow = dimensionManager.getDisplayWindow();
            if (displaySpace.width > displayWindow.width && propertyManager.isShowScrollbars()) {
                this.horizontalScrollBar.setVisible(true);
            }
            else {
                this.horizontalScrollBar.setVisible(false);
            }
            if (displaySpace.height > displayWindow.height && propertyManager.isShowScrollbars()) {
                this.verticalScrollBar.setVisible(true);
            }
            else {
                this.verticalScrollBar.setVisible(false);
            }
            try {
                propertyManager.setViewPort(displayWindow);
            }
            catch (PropertyVetoException ex) {
                MainImagePanel.log.error("Error setting viewport.", ex);
            }
            int n;
            if (discardedZoomLevels <= 0) {
                n = 10;
            }
            else {
                n = 10 * ImageUtils.zoomedToFull(1, discardedZoomLevels);
            }
            this.horizontalScrollBar.setValues(displayWindow.x, displayWindow.width, displaySpace.x, displaySpace.x + displaySpace.width - 1);
            this.horizontalScrollBar.setBlockIncrement(displayWindow.width);
            this.horizontalScrollBar.setUnitIncrement(n);
            this.verticalScrollBar.setValues(displayWindow.y, displayWindow.height, displaySpace.y, displaySpace.y + displaySpace.height - 1);
            this.verticalScrollBar.setBlockIncrement(displayWindow.height);
            this.verticalScrollBar.setUnitIncrement(n);
            this.stream.setMaxQualityLayers(propertyManager.getQualityLayers());
            try {
                if (discardedZoomLevels < 0) {
                    this.stream.setWindow(requestWindow.x, requestWindow.y, requestWindow.width, requestWindow.height, 0);
                }
                else {
                    this.stream.setWindow(requestWindow.x, requestWindow.y, requestWindow.width, requestWindow.height, discardedZoomLevels);
                }
            }
            catch (Exception ex2) {
                MainImagePanel.log.error("Failed to set window!", ex2);
            }
        }
    }
    
    private String parseGeoInformation(final GeoTiffIIOMetadataAdapter geoTiffIIOMetadataAdapter, final int n, final int n2, final int n3) {
        String string = null;
        final Utm_Coord_3d[] array = new Utm_Coord_3d[4];
        final Point2D.Double double1 = new Point2D.Double();
        final Point2D.Double double2 = new Point2D.Double();
        final Point2D.Double double3 = new Point2D.Double();
        final Point2D.Double double4 = new Point2D.Double();
        final double n4 = n;
        final double n5 = n2;
        if (geoTiffIIOMetadataAdapter.getModelTiePoints() != null && geoTiffIIOMetadataAdapter.getModelPixelScales() != null) {
            final double[] modelTiePoints = geoTiffIIOMetadataAdapter.getModelTiePoints();
            final double[] modelPixelScales = geoTiffIIOMetadataAdapter.getModelPixelScales();
            double1.x = modelTiePoints[3] + modelPixelScales[0] * -modelTiePoints[0];
            double1.y = modelTiePoints[4] - modelPixelScales[1] * -modelTiePoints[1];
            double2.x = modelTiePoints[3] + modelPixelScales[0] * (n4 - modelTiePoints[0]);
            double2.y = modelTiePoints[4] - modelPixelScales[1] * -modelTiePoints[1];
            double4.x = modelTiePoints[3] + modelPixelScales[0] * (n4 - modelTiePoints[0]);
            double4.y = modelTiePoints[4] - modelPixelScales[1] * (n5 - modelTiePoints[1]);
            double3.x = modelTiePoints[3] + modelPixelScales[0] * -modelTiePoints[0];
            double3.y = modelTiePoints[4] - modelPixelScales[1] * (n5 - modelTiePoints[1]);
        }
        else {
            if (geoTiffIIOMetadataAdapter.getModelTransformation() == null) {
                return null;
            }
            final double[] modelTransformation = geoTiffIIOMetadataAdapter.getModelTransformation();
            double1.x = modelTransformation[3];
            double1.y = modelTransformation[7];
            double2.x = n4 * modelTransformation[0] + modelTransformation[3];
            double2.y = n4 * modelTransformation[4] + modelTransformation[7];
            double4.x = n4 * modelTransformation[0] + n5 * modelTransformation[1] + modelTransformation[3];
            double4.y = n4 * modelTransformation[4] + n5 * modelTransformation[5] + modelTransformation[7];
            double3.x = n5 * modelTransformation[1] + modelTransformation[3];
            double3.y = n5 * modelTransformation[5] + modelTransformation[7];
        }
        final int intValue = Integer.valueOf(geoTiffIIOMetadataAdapter.getGeoKey(1024));
        int n6 = -1;
        if (intValue == 2) {
            n6 = Integer.parseInt(geoTiffIIOMetadataAdapter.getGeoKey(2048));
            final Geodetic_to_UTM_Converter geodetic_to_UTM_Converter = new Geodetic_to_UTM_Converter();
            final Utm_Coord_3d convert_Geodetic_To_UTM = geodetic_to_UTM_Converter.Convert_Geodetic_To_UTM(3.141592653589793 * double1.y / 180.0, 3.141592653589793 * double1.x / 180.0);
            final Utm_Coord_3d convert_Geodetic_To_UTM2 = geodetic_to_UTM_Converter.Convert_Geodetic_To_UTM(3.141592653589793 * double2.y / 180.0, 3.141592653589793 * double2.x / 180.0);
            final Utm_Coord_3d convert_Geodetic_To_UTM3 = geodetic_to_UTM_Converter.Convert_Geodetic_To_UTM(3.141592653589793 * double4.y / 180.0, 3.141592653589793 * double4.x / 180.0);
            final Utm_Coord_3d convert_Geodetic_To_UTM4 = geodetic_to_UTM_Converter.Convert_Geodetic_To_UTM(3.141592653589793 * double3.y / 180.0, 3.141592653589793 * double3.x / 180.0);
            array[0] = convert_Geodetic_To_UTM;
            array[1] = convert_Geodetic_To_UTM2;
            array[2] = convert_Geodetic_To_UTM3;
            array[3] = convert_Geodetic_To_UTM4;
        }
        else if (intValue == 1) {
            n6 = Integer.parseInt(geoTiffIIOMetadataAdapter.getGeoKey(3072));
            int n7;
            boolean b;
            if (n6 > 32600 && n6 <= 32660) {
                n7 = n6 - 32600;
                b = true;
            }
            else {
                if (n6 <= 32700 || n6 > 32760) {
                    MainImagePanel.log.warn("Unsupported GeoTIFF projection code: '" + n6 + "'. GeoTIFF information is being ignored.");
                    return null;
                }
                n7 = n6 - 32700;
                b = false;
            }
            final Utm_Coord_3d utm_Coord_3d = new Utm_Coord_3d(double1.x, double1.y, 0.0, (byte)n7, b);
            final Utm_Coord_3d utm_Coord_3d2 = new Utm_Coord_3d(double2.x, double2.y, 0.0, (byte)n7, b);
            final Utm_Coord_3d utm_Coord_3d3 = new Utm_Coord_3d(double4.x, double4.y, 0.0, (byte)n7, b);
            final Utm_Coord_3d utm_Coord_3d4 = new Utm_Coord_3d(double3.x, double3.y, 0.0, (byte)n7, b);
            final Geodetic_to_UTM_Converter geodetic_to_UTM_Converter2 = new Geodetic_to_UTM_Converter();
            final Gdc_Coord_3d gdc_Coord_3d = new Gdc_Coord_3d();
            Utm_To_Gdc_Converter.Init();
            Utm_To_Gdc_Converter.Convert(utm_Coord_3d, gdc_Coord_3d);
            final Utm_Coord_3d convert_Geodetic_To_UTM5 = geodetic_to_UTM_Converter2.Convert_Geodetic_To_UTM(3.141592653589793 * gdc_Coord_3d.latitude / 180.0, 3.141592653589793 * gdc_Coord_3d.longitude / 180.0);
            Utm_To_Gdc_Converter.Convert(utm_Coord_3d2, gdc_Coord_3d);
            final Utm_Coord_3d convert_Geodetic_To_UTM6 = geodetic_to_UTM_Converter2.Convert_Geodetic_To_UTM(3.141592653589793 * gdc_Coord_3d.latitude / 180.0, 3.141592653589793 * gdc_Coord_3d.longitude / 180.0);
            Utm_To_Gdc_Converter.Convert(utm_Coord_3d3, gdc_Coord_3d);
            final Utm_Coord_3d convert_Geodetic_To_UTM7 = geodetic_to_UTM_Converter2.Convert_Geodetic_To_UTM(3.141592653589793 * gdc_Coord_3d.latitude / 180.0, 3.141592653589793 * gdc_Coord_3d.longitude / 180.0);
            Utm_To_Gdc_Converter.Convert(utm_Coord_3d4, gdc_Coord_3d);
            final Utm_Coord_3d convert_Geodetic_To_UTM8 = geodetic_to_UTM_Converter2.Convert_Geodetic_To_UTM(3.141592653589793 * gdc_Coord_3d.latitude / 180.0, 3.141592653589793 * gdc_Coord_3d.longitude / 180.0);
            array[0] = convert_Geodetic_To_UTM5;
            array[1] = convert_Geodetic_To_UTM6;
            array[2] = convert_Geodetic_To_UTM7;
            array[3] = convert_Geodetic_To_UTM8;
        }
        if (n6 != -1 && array != null) {
            final boolean hemisphere_north = array[0].hemisphere_north;
            String string2 = "";
            final String s = hemisphere_north ? "N" : "S";
            for (int i = 0; i < 4; ++i) {
                string2 += NITFGeoUtils.toIGEOLOFormat(array[i]);
            }
            string = "<GeoTIFF> <NITF>  <Image_Segment>   <ICORDS>" + s + "</ICORDS>" + "   <IGEOLO>" + string2 + "</IGEOLO>" + "  </Image_Segment>" + " </NITF>" + "</GeoTIFF>";
        }
        return string;
    }
    
    private void loadXmlMetaData(final MainImageStream mainImageStream, final int n, final int n2) throws IOException {
        int n3 = 0;
        if (this.xmlPropertiesParser == null) {
            this.xmlPropertiesParser = new XmlPropertiesParser();
        }
        final GeoTiffIIOMetadataAdapter geoInfo = mainImageStream.getStream().getGeoInfo();
        if (geoInfo != null) {
            this.viewCentral.getPropertyManager().setHasUUID(true);
            final String geoInformation = this.parseGeoInformation(geoInfo, n, n2, n3);
            if (geoInformation != null) {
                this.xmlPropertiesParser.addBox(geoInformation, n3++);
            }
        }
        final List xml = mainImageStream.getStream().getXML();
        for (int i = 0; i < xml.size(); ++i) {
            final String s = xml.get(i);
            if (s != null) {
                this.xmlPropertiesParser.addBox(s, n3++);
            }
        }
        this.viewCentral.setXmlPropertiesParser(this.xmlPropertiesParser);
    }
    
    private void initializeGeoLocationData() {
        this.xmlPropertiesParser = this.viewCentral.getXmlPropertiesParser();
        if (this.xmlPropertiesParser != null && this.xmlPropertiesParser.isReady()) {
            final Rectangle totalDimensions = this.viewCentral.getPropertyManager().getTotalDimensions();
            if (totalDimensions == null) {
                MainImagePanel.log.debug("ImagePanel.initGeoLocationData() -- Rectangle object that describes full image dimensions is NULL");
                return;
            }
            this.nitfGeoUtils = new NITFGeoUtils(this.xmlPropertiesParser.getXMLBoxes(), new Dimension(totalDimensions.width, totalDimensions.height));
            if (!this.viewCentral.getPropertyManager().getIsDefaultProjSelected()) {
                try {
                    this.viewCentral.getPropertyManager().setProj(this.nitfGeoUtils.getProj());
                }
                catch (PropertyVetoException ex) {
                    MainImagePanel.log.error("Unable to set initial Projection in PropertyManager.", ex);
                }
            }
        }
        else {
            this.nitfGeoUtils = null;
        }
        this.viewCentral.setNITFGeoUtils(this.nitfGeoUtils);
    }
    
    private int[] getRGBMap() {
        int[] array = null;
        for (final Document document : this.xmlPropertiesParser.getXMLBoxes()) {
            final String nodeName = document.getDocumentElement().getNodeName();
            if (nodeName.equals("OriginalMetadata") || nodeName.equals("parsedFile")) {
                final NodeList elementsByTagName = document.getDocumentElement().getElementsByTagName("IREPBAND");
                if (elementsByTagName.getLength() < 3) {
                    continue;
                }
                int n = -1;
                int n2 = -1;
                int n3 = -1;
                for (int i = 0; i < elementsByTagName.getLength(); ++i) {
                    final String trim = elementsByTagName.item(i).getFirstChild().getNodeValue().trim();
                    if (trim.trim().equalsIgnoreCase("R")) {
                        n = i;
                    }
                    else if (trim.trim().equalsIgnoreCase("G")) {
                        n2 = i;
                    }
                    else if (trim.trim().equalsIgnoreCase("B")) {
                        n3 = i;
                    }
                }
                if (n >= 0 && n2 >= 0 && n3 >= 0) {
                    array = new int[] { n, n2, n3 };
                    break;
                }
                continue;
            }
        }
        return array;
    }
    
    public void adjustZoomLevel(final int n) {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final int discardedZoomLevels = propertyManager.getDiscardedZoomLevels();
        final int discardedZoomLevels2 = discardedZoomLevels + n;
        final int n2 = -2;
        int maxDiscardedZoomLevels = propertyManager.getMaxDiscardedZoomLevels();
        final int maxZoomOutLevel = propertyManager.getMaxZoomOutLevel();
        if (maxZoomOutLevel > 0 && maxZoomOutLevel < maxDiscardedZoomLevels) {
            maxDiscardedZoomLevels = maxZoomOutLevel;
        }
        if (discardedZoomLevels2 >= n2 && discardedZoomLevels2 <= maxDiscardedZoomLevels) {
            final Point imageDisplayOffset = this.getImageDisplayOffset();
            this.adjustImageOriginForZoom(this.getDisplayWidth() / 2 - imageDisplayOffset.x, this.getDisplayHeight() / 2 - imageDisplayOffset.y, discardedZoomLevels, discardedZoomLevels2);
            try {
                this.viewCentral.getPropertyManager().setDiscardedZoomLevels(discardedZoomLevels2);
                this.viewCentral.viewChanged();
            }
            catch (PropertyVetoException ex) {
                MainImagePanel.log.error(ex);
            }
        }
    }
    
    public void adjustImageOriginForZoom(final int n, final int n2, final int n3, final int n4) {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final MainImagePanel mainImagePanel = this.viewCentral.getMainImagePanel();
        final Rectangle viewPort = propertyManager.getViewPort();
        final int n5 = viewPort.x + ImageUtils.zoomedToFull(n, n3);
        final int n6 = viewPort.y + ImageUtils.zoomedToFull(n2, n3);
        int n7 = n5 - ImageUtils.zoomedToFull(mainImagePanel.getDisplayWidth() / 2, n4);
        int n8 = n6 - ImageUtils.zoomedToFull(mainImagePanel.getDisplayHeight() / 2, n4);
        if (n7 < 0) {
            n7 = 0;
        }
        if (n8 < 0) {
            n8 = 0;
        }
        this.setImageWindowOrigin(new Point(n7, n8));
    }
    
    public Image getImage() {
        return this.stream.getCurrentImage();
    }
    
    public MainImageStream getStream() {
        return this.stream;
    }
    
    public Rectangle[] getImageSize() {
        return this.imageSize;
    }
    
    public Rectangle getImageSize(final int n) {
        Rectangle rectangle;
        if (n < 0) {
            rectangle = new Rectangle(ImageUtils.fullToZoomed(this.imageSize[0].x, n), ImageUtils.fullToZoomed(this.imageSize[0].y, n), ImageUtils.fullToZoomed(this.imageSize[0].width, n), ImageUtils.fullToZoomed(this.imageSize[0].height, n));
        }
        else {
            rectangle = this.imageSize[n];
        }
        return rectangle;
    }
    
    public void turnOnMagnificationLens(final boolean b) {
        this.mainImageDisplayPanel.turnOnMagnificationLens(b);
    }
    
    private Point latLonToPixel(final double n, final double n2) {
        final NITFGeoUtils nitfGeoUtils = this.viewCentral.getNITFGeoUtils();
        final String checkDDFormat = nitfGeoUtils.checkDDFormat(n2, n);
        if (checkDDFormat.length() != 0) {
            this.viewCentral.reportError(this, "Geolocation Error", checkDDFormat);
            return new Point(-1, -1);
        }
        int n3;
        int n4;
        try {
            if (nitfGeoUtils.hasRPC()) {
                final double[] pixelRPC = nitfGeoUtils.findPixelRPC(n2, n);
                n3 = (int)pixelRPC[0];
                n4 = (int)pixelRPC[1];
            }
            else {
                final double[] pixel = nitfGeoUtils.findPixel(n2, n);
                n3 = (int)pixel[0];
                n4 = (int)pixel[1];
            }
        }
        catch (NumberFormatException ex) {
            System.out.println("NumberFormatException: " + ex.getMessage());
            return new Point(-1, -1);
        }
        return new Point(n3, n4);
    }
    
    private Point eastNorthToPixel(final double n, final double n2) {
        final NITFGeoUtils nitfGeoUtils = this.viewCentral.getNITFGeoUtils();
        final byte utmZone = nitfGeoUtils.getUtmZone();
        final String checkUTMFormat = nitfGeoUtils.checkUTMFormat(n, n2, utmZone);
        if (checkUTMFormat.length() != 0) {
            this.viewCentral.reportError(this, "Geolocation Error", checkUTMFormat);
            return new Point(-1, -1);
        }
        int n3;
        int n4;
        try {
            final Utm_Coord_3d utm_Coord_3d = new Utm_Coord_3d(n, n2, 0.0, utmZone, nitfGeoUtils.getNSHemisphere() == 'N');
            final Gdc_Coord_3d gdc_Coord_3d = new Gdc_Coord_3d();
            Utm_To_Gdc_Converter.Init();
            Utm_To_Gdc_Converter.Convert(utm_Coord_3d, gdc_Coord_3d);
            final double latitude = gdc_Coord_3d.latitude;
            final double longitude = gdc_Coord_3d.longitude;
            double[] array;
            if (nitfGeoUtils.hasRPC()) {
                array = nitfGeoUtils.findPixelRPC(longitude, latitude);
            }
            else {
                array = nitfGeoUtils.findPixel(longitude, latitude);
            }
            n3 = (int)array[0];
            n4 = (int)array[1];
        }
        catch (NumberFormatException ex) {
            System.out.println("NumberFormatException: " + ex.getMessage());
            return new Point(-1, -1);
        }
        return new Point(n3, n4);
    }
    
    private Point mgrsToPixel(final String s) {
        final NITFGeoUtils nitfGeoUtils = this.viewCentral.getNITFGeoUtils();
        if (nitfGeoUtils.checkMGRSFormat(s).length() != 0) {
            this.viewCentral.reportError(this, "Geolocation Error", "Incorrect MGRS format.\nAn example of proper format is: 15SWC8081751205.");
            return new Point(-1, -1);
        }
        int n;
        int n2;
        try {
            final double[] convert_MGRS_To_Geodetic = new MGRSConversion().Convert_MGRS_To_Geodetic(s);
            double[] array;
            if (nitfGeoUtils.hasRPC()) {
                array = nitfGeoUtils.findPixelRPC(convert_MGRS_To_Geodetic[0], convert_MGRS_To_Geodetic[1]);
            }
            else {
                array = nitfGeoUtils.findPixel(convert_MGRS_To_Geodetic[0], convert_MGRS_To_Geodetic[1]);
            }
            n = (int)array[0];
            n2 = (int)array[1];
        }
        catch (NumberFormatException ex) {
            System.out.println("NumberFormatException: " + ex.getMessage());
            return new Point(-1, -1);
        }
        return new Point(n, n2);
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
        MainImagePanel.log = new Log((MainImagePanel.class$com$itt$J2KViewer$gui$MainImagePanel == null) ? (MainImagePanel.class$com$itt$J2KViewer$gui$MainImagePanel = class$("com.itt.J2KViewer.gui.MainImagePanel")) : MainImagePanel.class$com$itt$J2KViewer$gui$MainImagePanel);
    }
}
