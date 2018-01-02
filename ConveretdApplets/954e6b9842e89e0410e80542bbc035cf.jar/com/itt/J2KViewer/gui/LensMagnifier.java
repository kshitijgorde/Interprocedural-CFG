// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import javax.swing.JPanel;
import javax.imageio.ImageReader;
import java.beans.PropertyChangeEvent;
import com.itt.J2KViewer.imagetools.DRAManager;
import com.itt.J2KViewer.controller.PropertyManager;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Rectangle;
import com.itt.J2KViewer.controller.PropertyChangeEventMediator;
import com.itt.J2KViewer.util.ImageUtils;
import java.awt.image.ImageObserver;
import java.awt.geom.Point2D;
import com.itt.IASjTools.IASImage;
import com.itt.J2KViewer.imagetools.MainImageStream;
import java.awt.image.BufferedImage;
import java.awt.Shape;
import java.awt.Image;
import com.itt.J2KViewer.controller.DimensionManager;
import java.awt.event.MouseEvent;
import com.itt.J2KViewer.controller.ViewCentral;
import java.awt.Point;
import java.awt.Color;
import com.itt.J2KViewer.util.Log;
import java.beans.PropertyChangeListener;
import javax.imageio.event.IIOReadUpdateListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;

public class LensMagnifier extends MouseAdapter implements MouseMotionListener, IIOReadUpdateListener, PropertyChangeListener
{
    public static final int CIRCLE = 0;
    public static final int SQUARE = 1;
    private static Log log;
    protected Color m_outlineColor;
    protected MainImageDisplayPanel m_parentJPanel;
    protected int m_radius;
    protected Point m_centerLocation;
    protected Point m_mousePressed;
    private ViewCentral viewCentral;
    private boolean m_bDragging;
    private MouseEvent m_DragEvent;
    protected boolean m_bStreamIsOpen;
    protected DimensionManager m_lensDimMgr;
    protected int m_maxDiscardLevel;
    private Image m_rotatedImage;
    private int m_bits;
    private int m_magPower;
    private int m_lensType;
    private int m_fakeZoomLevelsNeeded;
    private boolean m_useLastPaintedImage;
    private Image m_lastPaintedImage;
    private Shape m_lensShape;
    private BufferedImage currentImage;
    private MainImageStream mainImageStream;
    static /* synthetic */ Class class$com$itt$J2KViewer$gui$LensMagnifier;
    
    public LensMagnifier(final ViewCentral viewCentral, final MainImageDisplayPanel parentJPanel) {
        this.m_outlineColor = Color.black;
        this.m_parentJPanel = null;
        this.m_radius = 80;
        this.m_centerLocation = null;
        this.m_mousePressed = new Point(0, 0);
        this.m_bDragging = false;
        this.m_bStreamIsOpen = false;
        this.m_lensDimMgr = null;
        this.m_rotatedImage = null;
        this.m_bits = 8;
        this.m_magPower = 2;
        this.m_lensType = 0;
        this.m_fakeZoomLevelsNeeded = 0;
        this.m_useLastPaintedImage = false;
        this.m_lastPaintedImage = null;
        this.m_lensShape = null;
        this.currentImage = null;
        this.mainImageStream = null;
        this.m_parentJPanel = parentJPanel;
        this.viewCentral = viewCentral;
        this.m_lensDimMgr = new DimensionManager(viewCentral);
        if (this.viewCentral.getPropertyManager() != null) {
            this.m_radius = this.viewCentral.getPropertyManager().getLensRadius();
            this.m_lensType = this.viewCentral.getPropertyManager().getLensType();
            this.m_outlineColor = this.viewCentral.getPropertyManager().getLensColor();
            this.m_magPower = this.viewCentral.getPropertyManager().getLensMagnification();
        }
    }
    
    public void openImage() {
        this.mainImageStream = this.viewCentral.getMainImagePanel().getStream();
        this.mainImageStream.getStream().addIIOReadUpdateListener(this);
        final IASImage imageFileProperties = this.mainImageStream.getImageFileProperties();
        this.m_lensDimMgr.newImage(imageFileProperties.getWidth(), imageFileProperties.getHeight(), this.m_maxDiscardLevel);
    }
    
    public void setLensType(final int lensType) {
        this.clearImage();
        this.m_lensType = lensType;
        this.m_parentJPanel.repaint();
    }
    
    public int getLensType() {
        return this.m_lensType;
    }
    
    public Shape getLensShape() {
        return this.m_lensShape;
    }
    
    public int getLensRadius() {
        return this.m_radius;
    }
    
    public void setLensRadius(final int radius) {
        this.clearImage();
        this.m_radius = radius;
        this.m_parentJPanel.repaint();
    }
    
    public Color getLensOutlineColor() {
        return this.m_outlineColor;
    }
    
    public void setLensOutlineColor(final Color outlineColor) {
        this.m_outlineColor = outlineColor;
        this.m_parentJPanel.repaint();
    }
    
    public void setLensMagnification(final int magPower) {
        this.clearImage();
        this.m_magPower = magPower;
        this.m_parentJPanel.repaint();
    }
    
    public int getLensMagnification() {
        return this.m_magPower;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.viewCentral.getPropertyManager().getTransformationMode() == 6) {
            this.handleMouseMoved(mouseEvent);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.viewCentral.getPropertyManager().getTransformationMode() == 6) {
            this.handleMousePressed(mouseEvent);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.viewCentral.getPropertyManager().getTransformationMode() == 6 && mouseEvent.getButton() == 1) {
            this.handleMouseReleased(mouseEvent);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.viewCentral.getPropertyManager().getTransformationMode() == 6) {
            this.handleMouseDragged(mouseEvent);
        }
    }
    
    public void handleMouseMoved(final MouseEvent mouseEvent) {
        if (this.m_lensShape.contains(mouseEvent.getPoint())) {
            Point point = new Point(-1, -1);
            Object rawDataValues = null;
            Object rgbValues = null;
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            int n = x - (this.m_centerLocation.x - this.m_radius);
            int n2 = y - (this.m_centerLocation.y - this.m_radius);
            if (this.currentImage != null && this.m_fakeZoomLevelsNeeded > 0) {
                final int n3 = this.currentImage.getWidth(null) / 2 - this.m_radius / (this.m_fakeZoomLevelsNeeded * 2);
                final int n4 = this.currentImage.getHeight(null) / 2 - this.m_radius / (this.m_fakeZoomLevelsNeeded * 2);
                n = n / (this.m_fakeZoomLevelsNeeded * 2) + n3;
                n2 = n2 / (this.m_fakeZoomLevelsNeeded * 2) + n4;
            }
            final int discardedZoomLevels = this.viewCentral.getPropertyManager().getDiscardedZoomLevels();
            final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
            final Rectangle displayWindow = dimensionManager.getDisplayWindow();
            final Point point2 = new Point(displayWindow.x + ImageUtils.zoomedToFull(this.m_centerLocation.x, discardedZoomLevels), displayWindow.y + ImageUtils.zoomedToFull(this.m_centerLocation.y, discardedZoomLevels));
            final int n5 = discardedZoomLevels - (int)(Math.log(this.m_magPower) / Math.log(2.0));
            final Point displayToImage = this.m_lensDimMgr.displayToImage(new Point(point2.x + ImageUtils.zoomedToFull(mouseEvent.getX() - this.m_centerLocation.x, n5), point2.y + ImageUtils.zoomedToFull(mouseEvent.getY() - this.m_centerLocation.y, n5)));
            final Dimension imageSize = dimensionManager.getImageSize(0);
            if (displayToImage.x >= 0 && displayToImage.y >= 0 && displayToImage.x < imageSize.width && displayToImage.y < imageSize.height) {
                point = new Point(displayToImage);
                final Rectangle requestWindow = this.m_lensDimMgr.getRequestWindow();
                final Point point3 = new Point(ImageUtils.fullToZoomed(displayToImage.x - requestWindow.x, n5), ImageUtils.fullToZoomed(displayToImage.y - requestWindow.y, n5));
                int x2;
                int y2;
                if (n5 < 0) {
                    x2 = displayToImage.x - requestWindow.x;
                    y2 = displayToImage.y - requestWindow.y;
                }
                else {
                    x2 = point3.x;
                    y2 = point3.y;
                }
                rawDataValues = this.getRawDataValues(x2, y2);
                rgbValues = this.getRGBValues(n, n2);
            }
            final PropertyChangeEventMediator eventController = this.viewCentral.eventController();
            eventController.firePropertyChange(PropertyChangeEventMediator.createPropertyChangeEvent(this, "MouseMoved", point, point));
            eventController.firePropertyChange(PropertyChangeEventMediator.createPropertyChangeEvent(this, "DataValuesChanged", rawDataValues, rawDataValues));
            eventController.firePropertyChange(PropertyChangeEventMediator.createPropertyChangeEvent(this, "RGBValuesChanged", rgbValues, rgbValues));
        }
    }
    
    public void handleMousePressed(final MouseEvent dragEvent) {
        this.m_mousePressed = new Point(dragEvent.getX(), dragEvent.getY());
        if (SwingUtilities.isLeftMouseButton(dragEvent)) {
            this.m_centerLocation.x = dragEvent.getX();
            this.m_centerLocation.y = dragEvent.getY();
            this.m_bDragging = true;
            this.m_DragEvent = dragEvent;
            this.m_parentJPanel.invalidate();
            this.m_parentJPanel.repaint();
        }
    }
    
    public void handleMouseReleased(final MouseEvent mouseEvent) {
        if (this.m_bDragging) {
            this.clearImage();
            this.restartImageStream();
            this.m_bDragging = false;
            this.m_parentJPanel.repaint();
        }
    }
    
    public void handleMouseDragged(final MouseEvent dragEvent) {
        this.m_rotatedImage = null;
        final int x = dragEvent.getX();
        final int y = dragEvent.getY();
        if (x < 0 || y < 0 || x > this.m_parentJPanel.getWidth() || y > this.m_parentJPanel.getHeight()) {
            return;
        }
        this.m_bDragging = true;
        this.m_DragEvent = dragEvent;
        final int n = dragEvent.getX() - this.m_mousePressed.x;
        final int n2 = dragEvent.getY() - this.m_mousePressed.y;
        this.m_mousePressed.x = dragEvent.getX();
        this.m_mousePressed.y = dragEvent.getY();
        final Point centerLocation = this.m_centerLocation;
        centerLocation.x += n;
        final Point centerLocation2 = this.m_centerLocation;
        centerLocation2.y += n2;
        this.m_parentJPanel.invalidate();
        this.m_parentJPanel.repaint();
    }
    
    public void paintLastPaintedImage(final Graphics graphics) {
        this.m_useLastPaintedImage = true;
        this.paint(graphics);
        this.m_useLastPaintedImage = false;
    }
    
    public void paint(final Graphics graphics) {
        this.adjustLensLocation(this.m_parentJPanel.getWidth(), this.m_parentJPanel.getHeight());
        if (this.m_lensType == 1) {
            this.m_lensShape = new Rectangle2D.Float(this.m_centerLocation.x - this.m_radius, this.m_centerLocation.y - this.m_radius, this.m_radius * 2, this.m_radius * 2);
        }
        else {
            this.m_lensShape = new Ellipse2D.Double(this.m_centerLocation.x - this.m_radius, this.m_centerLocation.y - this.m_radius, this.m_radius * 2, this.m_radius * 2);
        }
        graphics.setClip(this.m_lensShape);
        if (this.m_bDragging && !this.m_DragEvent.isShiftDown()) {
            graphics.setColor(this.m_outlineColor);
            final int n = this.m_radius / 4;
            graphics.drawLine(this.m_centerLocation.x - n, this.m_centerLocation.y, this.m_centerLocation.x + n, this.m_centerLocation.y);
            graphics.drawLine(this.m_centerLocation.x, this.m_centerLocation.y - n, this.m_centerLocation.x, this.m_centerLocation.y + n);
        }
        else {
            Image lastPaintedImage;
            if (this.m_useLastPaintedImage) {
                lastPaintedImage = this.m_lastPaintedImage;
            }
            else {
                lastPaintedImage = this.getLensContent();
            }
            this.paintImage(graphics, 0.0, this.m_lastPaintedImage = lastPaintedImage);
        }
        graphics.setColor(this.m_outlineColor);
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setStroke(new BasicStroke(2.0f));
        graphics2D.draw(this.m_lensShape);
        graphics.setClip(null);
    }
    
    protected void paintImage(final Graphics graphics, final double n, final Image image) {
        ((Graphics2D)graphics).drawImage(image, this.m_centerLocation.x - this.m_radius, this.m_centerLocation.y - this.m_radius, this.m_parentJPanel);
    }
    
    protected void adjustLensLocation(final int n, final int n2) {
        if (this.m_centerLocation == null) {
            this.m_centerLocation = new Point(n / 2, n2 / 2);
        }
    }
    
    protected Image getLensContent() {
        if (this.m_rotatedImage != null) {
            final Image rotatedImage = this.m_rotatedImage;
            if (this.m_fakeZoomLevelsNeeded == 0) {
                return rotatedImage;
            }
        }
        if (this.m_rotatedImage == null) {
            this.restartImageStream();
        }
        Image image;
        if (this.m_fakeZoomLevelsNeeded == 0 || this.m_rotatedImage == null) {
            image = this.m_parentJPanel.getBufferedImage();
        }
        else {
            image = this.m_rotatedImage;
        }
        final Image image2 = this.m_parentJPanel.createImage(this.m_radius * 2, this.m_radius * 2);
        final Graphics2D graphics2D = (Graphics2D)image2.getGraphics();
        graphics2D.setColor(this.m_parentJPanel.getBackground());
        graphics2D.clearRect(0, 0, this.m_radius * 2, this.m_radius * 2);
        final int n = 0;
        final int n2 = 0;
        final int n3 = this.m_radius * 2;
        final int n4 = this.m_radius * 2;
        int n5 = this.m_radius / this.m_magPower;
        int n6;
        int n7;
        if (this.m_fakeZoomLevelsNeeded > 0 && this.m_rotatedImage != null) {
            n5 = this.m_radius / (this.m_fakeZoomLevelsNeeded * 2);
            this.m_rotatedImage = null;
            n6 = image.getWidth(null) / 2 - n5;
            n7 = image.getHeight(null) / 2 - n5;
        }
        else {
            n6 = this.m_centerLocation.x - n5;
            n7 = this.m_centerLocation.y - n5;
        }
        graphics2D.drawImage(image, n, n2, n + n3, n2 + n4, n6, n7, n6 + n5 * 2, n7 + n5 * 2, null);
        return image2;
    }
    
    public void clearImage() {
        this.m_rotatedImage = null;
    }
    
    public void restartImageStream() {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
        final int discardedZoomLevels = propertyManager.getDiscardedZoomLevels();
        final double rotationAngle = propertyManager.getRotationAngle();
        final Point displayWindowToImage = dimensionManager.displayWindowToImage(this.m_centerLocation);
        int discardLevel = discardedZoomLevels - (int)(Math.log(this.m_magPower) / Math.log(2.0));
        if (discardLevel < 0) {
            this.m_fakeZoomLevelsNeeded = -1 * discardLevel;
            discardLevel = 0;
        }
        else {
            this.m_fakeZoomLevelsNeeded = 0;
        }
        this.m_lensDimMgr.setDisplaySize(new Dimension(this.m_radius * 2, this.m_radius * 2));
        this.m_lensDimMgr.setDiscardLevel(discardLevel);
        this.m_lensDimMgr.setDisplayWindowCenter(this.m_lensDimMgr.imageToDisplay(displayWindowToImage));
        this.m_lensDimMgr.setRotationAngle(rotationAngle);
        this.m_lensDimMgr.adjustDisplayWindow(false);
        final Rectangle requestWindow = this.m_lensDimMgr.getRequestWindow();
        this.viewCentral.getDimensionManager().getImageSize(0);
        try {
            final DRAManager draManager = this.viewCentral.getDRAManager();
            final int[] rgbMap = propertyManager.getRGBMap();
            final int qualityLayers = propertyManager.getQualityLayers();
            final double sharpGain = propertyManager.getSharpGain();
            final float[] array = new float[rgbMap.length];
            final float[] array2 = new float[rgbMap.length];
            for (int i = 0; i < rgbMap.length; ++i) {
                array[i] = draManager.getStretchMin(i);
                array2[i] = draManager.getStretchMax(i);
            }
            this.mainImageStream.getStream().setWindow(requestWindow.x, requestWindow.y, requestWindow.width, requestWindow.height, discardLevel, rgbMap, qualityLayers, array, array2, sharpGain, true);
        }
        catch (Exception ex) {
            LensMagnifier.log.error("Unable to update overview image.", ex);
        }
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final String propertyName = propertyChangeEvent.getPropertyName();
        if (propertyName != null) {
            if (propertyName.equals("lensColor")) {
                this.setLensOutlineColor(this.viewCentral.getPropertyManager().getLensColor());
            }
            else if (propertyName.equals("lensType")) {
                this.setLensType(this.viewCentral.getPropertyManager().getLensType());
            }
            else if (propertyName.equals("lensRadius")) {
                this.setLensRadius(this.viewCentral.getPropertyManager().getLensRadius());
            }
            else if (propertyName.equals("lensMagnification")) {
                this.setLensMagnification(this.viewCentral.getPropertyManager().getLensMagnification());
            }
            else if (propertyName.equals("ViewPort") && this.viewCentral.getPropertyManager().getTransformationMode() == 6) {
                this.restartImageStream();
            }
        }
    }
    
    public int[] getRGBValues(final int n, final int n2) {
        int[] array = null;
        if (this.currentImage != null) {
            try {
                final int rgb = this.currentImage.getRGB(n, n2);
                array = new int[] { (rgb & 0xFF0000) >> 16, (rgb & 0xFF00) >> 8, rgb & 0xFF };
            }
            catch (Exception ex) {}
        }
        return array;
    }
    
    public int[] getRawDataValues(final int n, final int n2) {
        int[] array = null;
        final int[] rgbMap = this.viewCentral.getPropertyManager().getRGBMap();
        final Object rawDataThumbnail = this.mainImageStream.getStream().getRawDataThumbnail();
        if (rawDataThumbnail != null && this.currentImage != null && rgbMap != null) {
            array = new int[rgbMap.length];
            int n3 = (n + n2 * this.mainImageStream.getStream().getRawDataBufferSizeThumbnail().width) * rgbMap.length;
            if (rawDataThumbnail instanceof int[]) {
                final int[] array2 = (int[])rawDataThumbnail;
                if (n3 < array2.length) {
                    for (int i = 0; i < rgbMap.length; ++i) {
                        array[i] = array2[n3++];
                    }
                }
            }
            else if (rawDataThumbnail instanceof short[]) {
                final short[] array3 = (short[])rawDataThumbnail;
                if (n3 < array3.length) {
                    for (int j = 0; j < rgbMap.length; ++j) {
                        array[j] = (0xFFFF & array3[n3++]);
                    }
                }
            }
            else {
                final byte[] array4 = (byte[])rawDataThumbnail;
                if (n3 < array4.length) {
                    for (int k = 0; k < rgbMap.length; ++k) {
                        array[k] = (0xFF & array4[n3++]);
                    }
                }
            }
        }
        return array;
    }
    
    public void thumbnailUpdate(final ImageReader imageReader, final BufferedImage bufferedImage, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int[] array) {
        if (this.viewCentral.getPropertyManager().getTransformationMode() == 6) {
            this.viewCentral.getDimensionManager();
            final int n7 = this.m_radius * 2;
            final int n8 = this.m_radius * 2;
            this.m_rotatedImage = this.m_parentJPanel.createImage(n7, n8);
            if (this.m_rotatedImage != null) {
                final Graphics2D graphics2D = (Graphics2D)this.m_rotatedImage.getGraphics();
                graphics2D.setColor(this.m_parentJPanel.getBackground());
                graphics2D.clearRect(0, 0, n7, n8);
                graphics2D.drawImage(bufferedImage, this.m_lensDimMgr.getRequestTransform(), this.m_parentJPanel);
            }
            this.m_parentJPanel.repaint();
            if (this.m_rotatedImage == null) {
                this.currentImage = null;
            }
            else {
                this.currentImage = ImageUtils.convertImageToBufferedImage(this.m_rotatedImage, this.m_parentJPanel);
            }
        }
    }
    
    public Image getImage() {
        return this.m_parentJPanel.getBufferedImage();
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
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        LensMagnifier.log = new Log((LensMagnifier.class$com$itt$J2KViewer$gui$LensMagnifier == null) ? (LensMagnifier.class$com$itt$J2KViewer$gui$LensMagnifier = class$("com.itt.J2KViewer.gui.LensMagnifier")) : LensMagnifier.class$com$itt$J2KViewer$gui$LensMagnifier);
    }
}
