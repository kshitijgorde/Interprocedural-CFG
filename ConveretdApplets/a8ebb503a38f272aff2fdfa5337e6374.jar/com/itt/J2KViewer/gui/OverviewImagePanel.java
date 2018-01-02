// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import javax.imageio.ImageReader;
import com.itt.J2KViewer.georvm.NITFGeoUtils;
import com.itt.IASjTools.IASImage;
import com.itt.J2KViewer.util.Helper;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import com.itt.J2KViewer.imagetools.DRAManager;
import com.itt.J2KViewer.util.ImageUtils;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.itt.J2KViewer.controller.DimensionManager;
import com.itt.J2KViewer.imagetools.MainImageStream;
import java.net.URI;
import com.itt.J2KViewer.controller.PropertyManager;
import com.itt.J2KViewer.controller.ViewCentral;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.image.BufferedImage;
import com.itt.J2KViewer.util.Log;
import java.awt.event.ComponentListener;
import javax.imageio.event.IIOReadUpdateListener;
import com.itt.J2KViewer.util.ViewerConst;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

public class OverviewImagePanel extends JPanel implements PropertyChangeListener, ViewerConst, IIOReadUpdateListener, ComponentListener
{
    private static final long serialVersionUID = 1L;
    private static Log log;
    private BufferedImage currentImage;
    private OverviewImageDisplayPanel overviewImageDisplayPanel;
    OverviewMouseHandler mouseHandler;
    private BufferedImage northArrowImage;
    private Point imageDisplayOffset;
    private Rectangle zoomedViewPort;
    private int zoomLevel;
    private Dimension zoomedImageSize;
    private ViewCentral viewCentral;
    private PropertyManager propertyManager;
    private URI imageURI;
    private MainImageStream mainImageStream;
    private DimensionManager dimensionManager;
    private boolean showOverviewImage;
    static /* synthetic */ Class class$com$itt$J2KViewer$gui$OverviewImagePanel;
    
    public OverviewImagePanel(final ViewCentral viewCentral) {
        this.currentImage = null;
        this.imageDisplayOffset = new Point(0, 0);
        this.zoomedViewPort = null;
        this.zoomLevel = 0;
        this.zoomedImageSize = null;
        this.viewCentral = null;
        this.propertyManager = null;
        this.imageURI = null;
        this.mainImageStream = null;
        this.dimensionManager = null;
        this.showOverviewImage = true;
        this.viewCentral = viewCentral;
        this.dimensionManager = new DimensionManager(viewCentral);
        this.propertyManager = this.viewCentral.getPropertyManager();
        this.showOverviewImage = this.propertyManager.isShowOverviewImage();
        this.setLayout(new BorderLayout());
        this.add(this.overviewImageDisplayPanel = new OverviewImageDisplayPanel(this), "Center");
        this.mouseHandler = new OverviewMouseHandler(this.viewCentral);
        this.overviewImageDisplayPanel.addMouseListener(this.mouseHandler);
        this.overviewImageDisplayPanel.addMouseMotionListener(this.mouseHandler);
        this.overviewImageDisplayPanel.addMouseWheelListener(this.mouseHandler);
        this.addComponentListener(this);
    }
    
    public int getDisplayWidth() {
        return this.overviewImageDisplayPanel.getWidth();
    }
    
    public int getDisplayHeight() {
        return this.overviewImageDisplayPanel.getHeight();
    }
    
    public int getZoomLevel() {
        return this.zoomLevel;
    }
    
    public Rectangle getZoomedImageSize() {
        return new Rectangle(this.zoomedImageSize);
    }
    
    public void setShowOverviewImage(final boolean showOverviewImage) {
        this.showOverviewImage = showOverviewImage;
    }
    
    public boolean getShowOverviewImage() {
        return this.showOverviewImage;
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final String propertyName = propertyChangeEvent.getPropertyName();
        if (propertyName != null) {
            if (propertyName.equals("RotationAngle")) {
                this.dimensionManager.setRotationAngle(this.viewCentral.getPropertyManager().getRotationAngle());
                final Rectangle displaySpace = this.dimensionManager.getDisplaySpace();
                this.setPreferredSize(new Dimension(ImageUtils.fullToZoomed(displaySpace.width, this.zoomLevel), ImageUtils.fullToZoomed(displaySpace.height, this.zoomLevel)));
                this.restartImageStream();
            }
            else if (propertyName.equals("ViewPort")) {
                final Rectangle rectangle = (Rectangle)propertyChangeEvent.getNewValue();
                final Rectangle displaySpace2 = this.dimensionManager.getDisplaySpace();
                if (rectangle != null) {
                    this.zoomedViewPort = new Rectangle(ImageUtils.fullToZoomed(rectangle.x - displaySpace2.x, this.zoomLevel), ImageUtils.fullToZoomed(rectangle.y - displaySpace2.y, this.zoomLevel), ImageUtils.fullToZoomed(rectangle.width, this.zoomLevel), ImageUtils.fullToZoomed(rectangle.height, this.zoomLevel));
                }
                this.repaint();
            }
            else if (propertyName.equals("Open")) {
                if (!this.viewCentral.getPropertyManager().isOpen()) {
                    this.currentImage = null;
                    this.repaint();
                }
            }
            else if (propertyName.equals("NewDRA")) {
                if (this.viewCentral.getPropertyManager().isOpen()) {
                    this.restartImageStream();
                }
            }
            else if (propertyName.equals("RGBMap")) {
                if (this.viewCentral.getPropertyManager().isOpen()) {
                    this.restartImageStream();
                }
            }
            else if (propertyName.equals("UserLocation")) {
                if (this.viewCentral.getPropertyManager().isOpen()) {
                    this.restartImageStream();
                }
            }
            else if (propertyName.equals("overviewColor")) {
                if (this.viewCentral.getPropertyManager().isOpen()) {
                    this.repaint();
                }
            }
            else if (propertyName.equals("QualityLayers")) {
                if (this.viewCentral.getPropertyManager().isOpen()) {
                    this.restartImageStream();
                }
            }
            else if (propertyName.equals("WaveletSharpeningGain") && this.viewCentral.getPropertyManager().isOpen()) {
                this.restartImageStream();
            }
        }
    }
    
    public void restartImageStream() {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        if (this.showOverviewImage) {
            final Dimension imageSize = this.dimensionManager.getImageSize(0);
            try {
                final DRAManager draManager = this.viewCentral.getDRAManager();
                final int[] rgbMap = propertyManager.getRGBMap();
                final int overviewQualityLayers = propertyManager.getOverviewQualityLayers();
                final double sharpGain = propertyManager.getSharpGain();
                final float[] array = new float[rgbMap.length];
                final float[] array2 = new float[rgbMap.length];
                for (int i = 0; i < rgbMap.length; ++i) {
                    array[i] = draManager.getStretchMin(i);
                    array2[i] = draManager.getStretchMax(i);
                }
                this.mainImageStream.getStream().setWindow(0, 0, imageSize.width, imageSize.height, this.zoomLevel, rgbMap, overviewQualityLayers, array, array2, sharpGain, true);
            }
            catch (Exception ex) {
                OverviewImagePanel.log.error("Unable to update overview image.", ex);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.viewCentral.getPropertyManager().isOpen()) {
            if (this.zoomedViewPort != null) {
                graphics.setColor(this.viewCentral.getPropertyManager().getOverviewColor());
                graphics.drawRect(this.zoomedViewPort.x, this.zoomedViewPort.y, this.zoomedViewPort.width, this.zoomedViewPort.height);
                if (this.northArrowImage != null) {
                    final double rotationAngle = this.dimensionManager.getRotationAngle();
                    BufferedImage bufferedImage = this.northArrowImage;
                    if (rotationAngle != 0.0) {
                        bufferedImage = ImageUtils.rotateImage(bufferedImage, rotationAngle, bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);
                    }
                    graphics.drawImage(bufferedImage, 0, 0, this);
                }
            }
            if (this.propertyManager.getTransformationMode() != 6) {
                if (this.viewCentral.getPropertyManager().isShowCrosshair()) {
                    final Point userLocation = this.viewCentral.getPropertyManager().getUserLocation();
                    if (userLocation != null) {
                        final Rectangle displaySpace = this.dimensionManager.getDisplaySpace();
                        final Point imageToDisplay = this.dimensionManager.imageToDisplay(userLocation);
                        final int fullToZoomed = ImageUtils.fullToZoomed(imageToDisplay.x - displaySpace.x, this.zoomLevel);
                        final int fullToZoomed2 = ImageUtils.fullToZoomed(imageToDisplay.y - displaySpace.y, this.zoomLevel);
                        graphics.setColor(this.viewCentral.getPropertyManager().getGotoColor());
                        graphics.drawLine(fullToZoomed - 10, fullToZoomed2, fullToZoomed + 10, fullToZoomed2);
                        graphics.drawLine(fullToZoomed, fullToZoomed2 - 10, fullToZoomed, fullToZoomed2 + 10);
                    }
                }
            }
        }
    }
    
    public void openImage(final URI imageURI) {
        this.viewCentral.getPropertyManager();
        try {
            this.imageURI = imageURI;
            this.mainImageStream = this.viewCentral.getMainImagePanel().getStream();
            this.mainImageStream.getStream().addIIOReadUpdateListener(this);
            final IASImage imageFileProperties = this.mainImageStream.getImageFileProperties();
            this.dimensionManager.newImage(imageFileProperties.getWidth(), imageFileProperties.getHeight(), imageFileProperties.getNumDiscardLevels());
            final Dimension[] imageSizes = this.dimensionManager.getImageSizes();
            this.zoomLevel = imageSizes.length - 1;
            this.zoomedImageSize = imageSizes[this.zoomLevel];
            final Rectangle rectangle = new Rectangle(0, 0, ViewerConst.INFO_PANEL_SIZE.width, ViewerConst.INFO_PANEL_SIZE.height);
            for (int i = 0; i < imageSizes.length; ++i) {
                if (imageSizes[i].width < rectangle.width || imageSizes[i].height < rectangle.height) {
                    this.zoomLevel = i;
                    this.zoomedImageSize = imageSizes[i];
                    break;
                }
            }
            this.setPreferredSize(new Dimension(this.zoomedImageSize.width, this.zoomedImageSize.height));
            final NITFGeoUtils nitfGeoUtils = this.viewCentral.getNITFGeoUtils();
            if (nitfGeoUtils != null && nitfGeoUtils.isReady()) {
                final BufferedImage convertImageToBufferedImageByType = ImageUtils.convertImageToBufferedImageByType(Toolkit.getDefaultToolkit().getImage(Helper.getURLAsResource("NorthArrow.gif")), 2, this);
                this.northArrowImage = ImageUtils.rotateImage(convertImageToBufferedImageByType, nitfGeoUtils.getNorthRotationFactor(), convertImageToBufferedImageByType.getWidth() / 2, convertImageToBufferedImageByType.getHeight() / 2);
            }
            this.viewCentral.eventController().addPropertyChangeListener(this);
        }
        catch (Exception ex) {
            OverviewImagePanel.log.error("Unable to create overview image.", ex);
        }
    }
    
    public Point getImageDisplayOffset() {
        return this.imageDisplayOffset;
    }
    
    public BufferedImage getBufferedImage() {
        return ImageUtils.convertImageToBufferedImage(this.getImage(), this);
    }
    
    public void closeImage() {
    }
    
    public Image getImage() {
        return this.currentImage;
    }
    
    public void imageUpdate(final ImageReader imageReader, final BufferedImage bufferedImage, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int[] array) {
    }
    
    public void passComplete(final ImageReader imageReader, final BufferedImage bufferedImage) {
    }
    
    public void passStarted(final ImageReader imageReader, final BufferedImage bufferedImage, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int[] array) {
    }
    
    public void thumbnailPassComplete(final ImageReader imageReader, final BufferedImage bufferedImage) {
    }
    
    public void thumbnailPassStarted(final ImageReader imageReader, final BufferedImage bufferedImage, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int[] array) {
    }
    
    public void thumbnailUpdate(final ImageReader imageReader, final BufferedImage bufferedImage, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int[] array) {
        if (this.viewCentral.getPropertyManager().getTransformationMode() == 6) {
            return;
        }
        final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
        final int displayWidth = this.getDisplayWidth();
        final int displayHeight = this.getDisplayHeight();
        final Image image = this.createImage(displayWidth, displayHeight);
        if (image != null) {
            final Graphics2D graphics2D = (Graphics2D)image.getGraphics();
            graphics2D.setColor(this.getBackground());
            graphics2D.clearRect(0, 0, displayWidth, displayHeight);
            final AffineTransform overviewImageTransform = dimensionManager.getOverviewImageTransform(this.zoomLevel);
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.drawImage(bufferedImage, overviewImageTransform, this);
        }
        if (image == null) {
            this.currentImage = null;
        }
        else {
            this.currentImage = ImageUtils.convertImageToBufferedImage(image, this);
        }
        this.updateUI();
        this.repaint();
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        if (this.viewCentral.getPropertyManager().isOpen() && this.viewCentral.getPropertyManager().getTransformationMode() != 6 && this.isShowing()) {
            this.restartImageStream();
        }
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
        if (this.viewCentral.getPropertyManager().isOpen() && this.viewCentral.getPropertyManager().getTransformationMode() != 6 && this.getShowOverviewImage()) {
            this.restartImageStream();
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
        OverviewImagePanel.log = new Log((OverviewImagePanel.class$com$itt$J2KViewer$gui$OverviewImagePanel == null) ? (OverviewImagePanel.class$com$itt$J2KViewer$gui$OverviewImagePanel = class$("com.itt.J2KViewer.gui.OverviewImagePanel")) : OverviewImagePanel.class$com$itt$J2KViewer$gui$OverviewImagePanel);
    }
}
