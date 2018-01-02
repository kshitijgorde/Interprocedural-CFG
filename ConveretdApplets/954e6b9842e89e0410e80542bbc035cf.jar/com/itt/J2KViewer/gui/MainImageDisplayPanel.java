// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.Color;
import com.itt.J2KViewer.georvm.NITFGeoUtils;
import com.itt.J2KViewer.util.Helper;
import java.awt.Toolkit;
import com.itt.J2KViewer.controller.DimensionManager;
import com.itt.J2KViewer.controller.PropertyManager;
import java.beans.PropertyChangeEvent;
import com.itt.J2KViewer.util.ImageUtils;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Image;
import com.itt.J2KViewer.controller.ViewCentral;
import com.itt.J2KViewer.util.ViewerConst;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

public class MainImageDisplayPanel extends JPanel implements PropertyChangeListener, ViewerConst
{
    private static final String STATE_NORTHARROW_LOC = "NorthArrowLocation";
    private static final int EDGE_LEFT = 0;
    private static final int EDGE_RIGHT = 1;
    private static final int EDGE_TOP = 2;
    private static final int EDGE_BOTTOM = 3;
    private static final long serialVersionUID = 1L;
    private MainImagePanel mainImagePanel;
    private ViewCentral viewCentral;
    private Image image;
    private Image offscreenImage;
    private Graphics g;
    private int offsetX;
    private int offsetY;
    private int chip_x;
    private int chip_y;
    private int chip_width;
    private int chip_height;
    private InternalMouseHandler mh;
    private BufferedImage northArrowImage;
    private boolean draggingNorthArrow;
    private Rectangle northArrowRect;
    private NorthArrowEdges northArrowEdges;
    private Cursor origCursor;
    private Cursor overNorthArrowCursor;
    private Cursor moveCursor;
    private boolean m_bShowingMagnificationLens;
    private LensMagnifier m_lensMagnifier;
    
    public MainImageDisplayPanel(final MainImagePanel mainImagePanel, final ViewCentral viewCentral) {
        this.image = null;
        this.offscreenImage = null;
        this.mh = new InternalMouseHandler();
        this.draggingNorthArrow = false;
        this.northArrowEdges = null;
        this.overNorthArrowCursor = Cursor.getPredefinedCursor(13);
        this.moveCursor = Cursor.getPredefinedCursor(1);
        this.m_bShowingMagnificationLens = false;
        this.mainImagePanel = mainImagePanel;
        this.viewCentral = viewCentral;
        this.viewCentral.eventController().addPropertyChangeListener(this);
        this.addMouseListener(this.mh);
        this.addMouseMotionListener(this.mh);
        this.addMouseListener(this.m_lensMagnifier = new LensMagnifier(this.viewCentral, this));
        this.addMouseMotionListener(this.m_lensMagnifier);
        this.viewCentral.eventController().addPropertyChangeListener(this.m_lensMagnifier);
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent componentEvent) {
                MainImageDisplayPanel.this.handleResize(componentEvent);
            }
        });
    }
    
    private void handleResize(final ComponentEvent componentEvent) {
        if (this.viewCentral.getPropertyManager().isOpen() && this.isShowing()) {
            this.viewCentral.viewChanged();
        }
    }
    
    public LensMagnifier getLensMagnifier() {
        return this.m_lensMagnifier;
    }
    
    public Shape getLensShape() {
        return this.m_lensMagnifier.getLensShape();
    }
    
    public boolean isShowingMagnificationLens() {
        return this.m_bShowingMagnificationLens;
    }
    
    public void paintMagnificationLens(final Graphics graphics) {
        if (this.m_bShowingMagnificationLens) {
            this.m_lensMagnifier.paint(graphics);
        }
    }
    
    public void paintMagnificationLensForPrint(final Graphics graphics) {
        if (this.m_bShowingMagnificationLens) {
            this.m_lensMagnifier.paintLastPaintedImage(graphics);
        }
    }
    
    protected void turnOnMagnificationLens(final boolean bShowingMagnificationLens) {
        this.m_bShowingMagnificationLens = bShowingMagnificationLens;
        if (this.m_bShowingMagnificationLens) {
            this.m_lensMagnifier.clearImage();
        }
        else if (this.viewCentral.getPropertyManager().isShowSplitPane()) {
            this.viewCentral.getOverviewImagePanel().restartImageStream();
        }
        this.invalidate();
        this.repaint();
    }
    
    public BufferedImage getBufferedImage() {
        return this.mainImagePanel.getBufferedImage();
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.mainImagePanel != null) {
            this.image = this.mainImagePanel.getBufferedImage();
        }
        if (this.image != null) {
            final Point imageDisplayOffset = this.mainImagePanel.getImageDisplayOffset();
            this.paintImage(graphics, imageDisplayOffset.x, imageDisplayOffset.y, 0.0);
        }
        this.viewCentral.setMainImageUpdated(true);
    }
    
    public void paintImage(final Graphics graphics, final int offsetX, final int offsetY, final double n) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        final int width = this.getWidth();
        final int height = this.getHeight();
        final int discardedZoomLevels = this.viewCentral.getPropertyManager().getDiscardedZoomLevels();
        if (this.offscreenImage == null || this.offscreenImage.getWidth(this) != width || this.offscreenImage.getHeight(this) != height) {
            this.offscreenImage = this.createImage(width, height);
        }
        final Graphics2D graphics2D = (Graphics2D)this.offscreenImage.getGraphics();
        graphics2D.setColor(this.getBackground());
        graphics2D.clearRect(0, 0, width, height);
        if (this.image != null) {
            final AffineTransform affineTransform = new AffineTransform();
            affineTransform.rotate(n, width / 2, height / 2);
            affineTransform.translate(offsetX, offsetY);
            if (n != 0.0) {
                graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            }
            graphics2D.drawImage(this.image, affineTransform, this);
        }
        this.drawCrosshair(graphics2D);
        this.paintWaypoints(graphics2D, offsetX, offsetY);
        this.paintNorthArrow(graphics2D);
        this.paintMagnificationLens(graphics2D);
        ((Graphics2D)graphics).drawImage(this.offscreenImage, 0, 0, this);
        if (this.viewCentral.getPropertyManager().getTransformationMode() == 8) {
            final Point chipStart = this.viewCentral.getPropertyManager().getChipStart();
            final Point chipEnd = this.viewCentral.getPropertyManager().getChipEnd();
            if (chipStart != null && chipEnd != null) {
                this.chip_x = 0;
                this.chip_y = 0;
                this.chip_width = 0;
                this.chip_height = 0;
                final Point imageDisplayOffset = this.viewCentral.getMainImagePanel().getImageDisplayOffset();
                final Rectangle viewPort = this.viewCentral.getPropertyManager().getViewPort();
                int fullToZoomed;
                int fullToZoomed2;
                if (imageDisplayOffset.x != 0) {
                    final int x = imageDisplayOffset.x;
                    fullToZoomed = ImageUtils.fullToZoomed((int)chipStart.getX(), discardedZoomLevels) + x;
                    fullToZoomed2 = ImageUtils.fullToZoomed((int)chipEnd.getX(), discardedZoomLevels) + x;
                }
                else if (viewPort.x != 0) {
                    final int fullToZoomed3 = ImageUtils.fullToZoomed(viewPort.x, discardedZoomLevels);
                    fullToZoomed = ImageUtils.fullToZoomed((int)chipStart.getX(), discardedZoomLevels) - fullToZoomed3;
                    fullToZoomed2 = ImageUtils.fullToZoomed((int)chipEnd.getX(), discardedZoomLevels) - fullToZoomed3;
                }
                else {
                    fullToZoomed = ImageUtils.fullToZoomed((int)chipStart.getX(), discardedZoomLevels);
                    fullToZoomed2 = ImageUtils.fullToZoomed((int)chipEnd.getX(), discardedZoomLevels);
                }
                int fullToZoomed4;
                int fullToZoomed5;
                if (imageDisplayOffset.y != 0) {
                    final int y = imageDisplayOffset.y;
                    fullToZoomed4 = ImageUtils.fullToZoomed((int)chipStart.getY(), discardedZoomLevels) + y;
                    fullToZoomed5 = ImageUtils.fullToZoomed((int)chipEnd.getY(), discardedZoomLevels) + y;
                }
                else if (viewPort.y != 0) {
                    final int fullToZoomed6 = ImageUtils.fullToZoomed(viewPort.y, discardedZoomLevels);
                    fullToZoomed4 = ImageUtils.fullToZoomed((int)chipStart.getY(), discardedZoomLevels) - fullToZoomed6;
                    fullToZoomed5 = ImageUtils.fullToZoomed((int)chipEnd.getY(), discardedZoomLevels) - fullToZoomed6;
                }
                else {
                    fullToZoomed4 = ImageUtils.fullToZoomed((int)chipStart.getY(), discardedZoomLevels);
                    fullToZoomed5 = ImageUtils.fullToZoomed((int)chipEnd.getY(), discardedZoomLevels);
                }
                if (fullToZoomed < fullToZoomed2) {
                    this.chip_x = fullToZoomed;
                    this.chip_width = fullToZoomed2 - fullToZoomed;
                }
                else {
                    this.chip_x = fullToZoomed2;
                    this.chip_width = fullToZoomed - fullToZoomed2;
                }
                if (fullToZoomed4 < fullToZoomed5) {
                    this.chip_y = fullToZoomed4;
                    this.chip_height = fullToZoomed5 - fullToZoomed4;
                }
                else {
                    this.chip_y = fullToZoomed5;
                    this.chip_height = fullToZoomed4 - fullToZoomed5;
                }
                graphics.setColor(this.viewCentral.getPropertyManager().getGotoColor());
                this.viewCentral.getPropertyManager().setChipRectangle(this.chip_x, this.chip_y, this.chip_width, this.chip_height);
                graphics.drawRect(this.chip_x, this.chip_y, this.chip_width, this.chip_height);
            }
        }
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!(propertyChangeEvent.getSource() instanceof PropertyManager) || propertyChangeEvent.getPropertyName().equals("gotoColor")) {}
    }
    
    public void drawCrosshair(final Graphics graphics) {
        if (this.viewCentral.getPropertyManager().isShowCrosshair()) {
            final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
            final Point userLocation = this.viewCentral.getPropertyManager().getUserLocation();
            if (userLocation != null) {
                final Rectangle displayWindow = dimensionManager.getDisplayWindow();
                final Point imageToDisplay = dimensionManager.imageToDisplay(userLocation);
                final int discardedZoomLevels = this.viewCentral.getPropertyManager().getDiscardedZoomLevels();
                final int n = this.offsetX + ImageUtils.fullToZoomed(imageToDisplay.x - displayWindow.x, discardedZoomLevels);
                final int n2 = this.offsetY + ImageUtils.fullToZoomed(imageToDisplay.y - displayWindow.y, discardedZoomLevels);
                graphics.setColor(this.viewCentral.getPropertyManager().getGotoColor());
                graphics.drawLine(n - 20, n2, n + 20, n2);
                graphics.drawLine(n, n2 - 20, n, n2 + 20);
            }
        }
    }
    
    protected void buildNorthArrowImage() {
        final NITFGeoUtils nitfGeoUtils = this.viewCentral.getNITFGeoUtils();
        if (nitfGeoUtils != null && nitfGeoUtils.isReady()) {
            final BufferedImage convertImageToBufferedImageByType = ImageUtils.convertImageToBufferedImageByType(Toolkit.getDefaultToolkit().getImage(Helper.getURLAsResource(this.viewCentral.getPropertyManager().getNorthArrowImage())), 2, this);
            this.northArrowImage = ImageUtils.rotateImage(convertImageToBufferedImageByType, nitfGeoUtils.getNorthRotationFactor(), convertImageToBufferedImageByType.getWidth() / 2, convertImageToBufferedImageByType.getHeight() / 2);
        }
    }
    
    protected void paintWaypoints(final Graphics graphics, final int n, final int n2) {
        final int n3 = 7;
        if (this.viewCentral.getPropertyManager().isShowWaypoints()) {
            final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
            final List waypointList = this.viewCentral.getWaypointManager().getWaypointList();
            if (waypointList != null && !waypointList.isEmpty()) {
                final Rectangle displayWindow = dimensionManager.getDisplayWindow();
                final int discardedZoomLevels = this.viewCentral.getPropertyManager().getDiscardedZoomLevels();
                for (int i = 0; i < waypointList.size(); ++i) {
                    final Point imageToDisplay = dimensionManager.imageToDisplay(waypointList.get(i));
                    final int n4 = n + ImageUtils.fullToZoomed(imageToDisplay.x - displayWindow.x, discardedZoomLevels);
                    final int n5 = n2 + ImageUtils.fullToZoomed(imageToDisplay.y - displayWindow.y, discardedZoomLevels);
                    graphics.setColor(Color.YELLOW);
                    graphics.drawLine(n4 - n3, n5 + n3, n4, n5 - n3);
                    graphics.drawLine(n4, n5 - n3, n4 + n3, n5 + n3);
                    graphics.drawLine(n4 + n3, n5 + n3, n4 - n3, n5 + n3);
                    graphics.drawString(Integer.toString(i + 1), n4 + n3, n5 - n3);
                }
            }
        }
    }
    
    protected void paintNorthArrow(final Graphics graphics) {
        if (!this.viewCentral.getPropertyManager().isShowNorthArrow()) {
            return;
        }
        if (this.northArrowImage == null) {
            this.buildNorthArrowImage();
        }
        if (this.northArrowImage != null) {
            if (!this.draggingNorthArrow) {
                this.setupInitialNorthArrowLocation();
            }
            this.keepNorthArrowWithinImage(0, 0);
            BufferedImage bufferedImage = this.northArrowImage;
            final double rotationAngle = this.viewCentral.getDimensionManager().getRotationAngle();
            if (rotationAngle != 0.0) {
                bufferedImage = ImageUtils.rotateImage(bufferedImage, rotationAngle, bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);
            }
            graphics.drawImage(bufferedImage, this.northArrowRect.x, this.northArrowRect.y, this);
        }
    }
    
    protected void setupInitialNorthArrowLocation() {
        final int width = this.northArrowImage.getWidth();
        final int height = this.northArrowImage.getHeight();
        if (this.northArrowEdges == null) {
            this.loadNorthArrowLocationWithRespectToBorderLocation();
        }
        int distanceFromClosestHoriz;
        if (this.northArrowEdges.closestHorizontalEdge == 0) {
            distanceFromClosestHoriz = this.northArrowEdges.distanceFromClosestHoriz;
        }
        else {
            distanceFromClosestHoriz = this.getWidth() - this.northArrowEdges.distanceFromClosestHoriz;
        }
        int distanceFromClosestVert;
        if (this.northArrowEdges.closestVerticalEdge == 2) {
            distanceFromClosestVert = this.northArrowEdges.distanceFromClosestVert;
        }
        else {
            distanceFromClosestVert = this.getHeight() - this.northArrowEdges.distanceFromClosestVert;
        }
        this.northArrowRect = new Rectangle(distanceFromClosestHoriz, distanceFromClosestVert, width, height);
    }
    
    protected void keepNorthArrowWithinImage(final int n, final int n2) {
        if (this.northArrowRect.x + n < n) {
            this.northArrowRect.x = 0;
        }
        if (this.northArrowRect.y + n2 < n2) {
            this.northArrowRect.y = 0;
        }
        if (this.northArrowRect.x + n + this.northArrowImage.getWidth() > n + this.image.getWidth(null)) {
            this.northArrowRect.x = this.image.getWidth(null) - this.northArrowImage.getWidth();
        }
        if (this.northArrowRect.y + n2 + this.northArrowImage.getHeight() > n2 + this.image.getHeight(null)) {
            this.northArrowRect.y = this.image.getHeight(null) - this.northArrowImage.getHeight();
        }
    }
    
    protected void loadNorthArrowLocationWithRespectToBorderLocation() {
        this.northArrowEdges = new NorthArrowEdges();
    }
    
    protected void saveNorthArrowLocationWithRespectToBorderLocation() {
        if (this.northArrowRect.x - 0 < this.image.getWidth(null) - this.northArrowRect.x) {
            this.northArrowEdges.closestHorizontalEdge = 0;
            this.northArrowEdges.distanceFromClosestHoriz = this.northArrowRect.x;
        }
        else {
            this.northArrowEdges.closestHorizontalEdge = 1;
            this.northArrowEdges.distanceFromClosestHoriz = this.image.getWidth(null) - this.northArrowRect.x;
        }
        if (this.northArrowRect.y - 0 < this.image.getHeight(null) - this.northArrowRect.y) {
            this.northArrowEdges.closestVerticalEdge = 2;
            this.northArrowEdges.distanceFromClosestVert = this.northArrowRect.y;
        }
        else {
            this.northArrowEdges.closestVerticalEdge = 3;
            this.northArrowEdges.distanceFromClosestVert = this.image.getHeight(null) - this.northArrowRect.y;
        }
    }
    
    public void setCursor(final Cursor cursor) {
        super.setCursor(cursor);
        if (cursor != this.overNorthArrowCursor && cursor != this.moveCursor) {
            this.origCursor = cursor;
        }
    }
    
    public void handleMouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger()) {
            return;
        }
        final Point point = mouseEvent.getPoint();
        if (this.draggingNorthArrow) {
            this.draggingNorthArrow = false;
            this.setCursor(this.overNorthArrowCursor);
            this.saveNorthArrowLocationWithRespectToBorderLocation();
            this.invalidate();
            this.repaint();
            return;
        }
        if (point.x >= 0 && point.y >= 0 && this.northArrowRect != null && new Rectangle(this.northArrowRect).contains(point.x, point.y)) {
            this.draggingNorthArrow = true;
            this.setCursor(this.moveCursor);
            this.invalidate();
            this.repaint();
        }
    }
    
    protected void handleMousePressed(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        if (point.x >= 0 && point.y >= 0 && this.northArrowRect != null && new Rectangle(this.northArrowRect).contains(point.x, point.y)) {
            this.draggingNorthArrow = true;
            this.setCursor(this.moveCursor);
            this.invalidate();
            this.repaint();
        }
    }
    
    protected void handleMouseReleased(final MouseEvent mouseEvent) {
    }
    
    protected void handleMouseDragged(final MouseEvent mouseEvent) {
        if (this.northArrowRect == null) {
            return;
        }
        if (this.draggingNorthArrow) {
            this.dragNorthArrow(mouseEvent);
        }
    }
    
    protected void handleMouseMoved(final MouseEvent mouseEvent) {
        if (this.northArrowRect == null) {
            return;
        }
        final Point point = mouseEvent.getPoint();
        if (this.viewCentral.getPropertyManager().getTransformationMode() != 0) {
            this.draggingNorthArrow = false;
            if (this.origCursor != null) {
                this.setCursor(this.origCursor);
            }
            return;
        }
        if (this.draggingNorthArrow) {
            this.dragNorthArrow(mouseEvent);
            return;
        }
        if (point.x >= 0 && point.y >= 0) {
            if (new Rectangle(this.northArrowRect).contains(point.x, point.y)) {
                this.setCursor(this.overNorthArrowCursor);
            }
            else if (this.getCursor() == this.overNorthArrowCursor && this.origCursor != null) {
                this.setCursor(this.origCursor);
            }
        }
    }
    
    protected void dragNorthArrow(final MouseEvent mouseEvent) {
        final Rectangle rectangle = new Rectangle(this.northArrowRect);
        final int n = mouseEvent.getX() - rectangle.x;
        final int n2 = mouseEvent.getY() - rectangle.y;
        final Rectangle northArrowRect = this.northArrowRect;
        northArrowRect.x += n - this.northArrowImage.getWidth() / 2;
        final Rectangle northArrowRect2 = this.northArrowRect;
        northArrowRect2.y += n2 - this.northArrowImage.getHeight() / 2;
        this.keepNorthArrowWithinImage(0, 0);
        this.invalidate();
        this.repaint();
    }
    
    protected class InternalMouseHandler extends MouseAdapter implements MouseMotionListener
    {
        public void mouseClicked(final MouseEvent mouseEvent) {
            MainImageDisplayPanel.this.handleMouseClicked(mouseEvent);
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
            MainImageDisplayPanel.this.handleMouseMoved(mouseEvent);
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            if (MainImageDisplayPanel.this.viewCentral.getPropertyManager().getTransformationMode() == 0) {
                MainImageDisplayPanel.this.handleMouseReleased(mouseEvent);
            }
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
        }
    }
    
    protected class NorthArrowEdges
    {
        public int closestHorizontalEdge;
        public int closestVerticalEdge;
        public int distanceFromClosestHoriz;
        public int distanceFromClosestVert;
        
        protected NorthArrowEdges() {
            this.closestHorizontalEdge = 0;
            this.closestVerticalEdge = 3;
            this.distanceFromClosestHoriz = 0;
            this.distanceFromClosestVert = 0;
        }
    }
}
