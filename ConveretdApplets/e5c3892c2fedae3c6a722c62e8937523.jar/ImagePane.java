import java.awt.geom.GeneralPath;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.util.Vector;
import java.awt.image.ImageObserver;
import java.awt.Polygon;
import java.util.Observable;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.Rectangle;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Image;
import java.text.DecimalFormat;
import java.util.Observer;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImagePane extends JPanel implements KeyListener, MouseListener, ActionListener, MouseMotionListener, Observer
{
    imgViewer applet;
    MosaicData md;
    private int[] tempX;
    private int[] tempY;
    private DecimalFormat LatLonFormat;
    private Image offScreenBuffer;
    private Image downloadIcon;
    private int osbHeight;
    private int osbWidth;
    private Dimension preferredSize;
    private Point offsetToCenterDisplay;
    MapLayers mapLayers;
    private Point dragMouseLoc;
    private Point dragScrollLoc;
    private Point dragOffset;
    public LatLong savedRightClickLoc;
    private boolean dragActive;
    public Cursor currentCursor;
    private int savedWidth;
    private int savedHeight;
    private boolean modeChanged;
    LogoImage logo;
    Rectangle logoLoc;
    Rectangle downloadLoc;
    SceneMenu sceneMenu;
    private Timer refreshTimer;
    private Color swathHighlightColor;
    
    ImagePane(final imgViewer applet, final LocatorMap locatorMap) {
        this.offScreenBuffer = null;
        this.osbHeight = -1;
        this.osbWidth = -1;
        this.dragMouseLoc = null;
        this.dragScrollLoc = null;
        this.dragOffset = null;
        this.dragActive = false;
        this.savedWidth = 0;
        this.savedHeight = 0;
        this.swathHighlightColor = new Color(174, 255, 51);
        this.applet = applet;
        this.setBackground(Color.BLACK);
        this.applet.imgArea = this;
        this.md = new MosaicData(this.applet, this, locatorMap);
        this.mapLayers = this.md.mapLayers;
        this.tempX = new int[4];
        this.tempY = new int[4];
        this.LatLonFormat = new DecimalFormat(" 0.000000;-0.000000");
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setCursor(this.currentCursor = this.applet.crosshairCursor);
        this.dragOffset = new Point(0, 0);
        this.offsetToCenterDisplay = new Point(0, 0);
        this.logo = new LogoImage(this.applet);
        final MediaTracker mediaTracker = new MediaTracker(this.applet);
        mediaTracker.addImage(this.downloadIcon = this.applet.getImage(this.applet.getCodeBase(), "graphics/downloadIcon.gif"), 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (mediaTracker.isErrorID(0)) {
            System.out.println("Error loading graphics/" + this.downloadIcon);
        }
        this.sceneMenu = new SceneMenu(this.applet, this.md);
        this.refreshTimer = new Timer(1000, this);
        super.setSize(300, 300);
    }
    
    @Override
    public Dimension getPreferredSize() {
        if (this.preferredSize != null) {
            return new Dimension(this.preferredSize);
        }
        return new Dimension(300, 300);
    }
    
    public Point getOffsetToCenterDisplay() {
        return new Point(this.offsetToCenterDisplay);
    }
    
    @Override
    public void setSize(final Dimension dimension) {
        final Dimension size = this.getParent().getSize();
        final int width = size.width;
        final int height = size.height;
        final int max = Math.max(dimension.width, width);
        final int max2 = Math.max(dimension.height, height);
        final Dimension preferredSize = this.getPreferredSize();
        if (max > preferredSize.width) {
            this.offsetToCenterDisplay.x = (max - preferredSize.width) / 2;
        }
        else {
            this.offsetToCenterDisplay.x = 0;
        }
        if (max2 > preferredSize.height) {
            this.offsetToCenterDisplay.y = (max2 - preferredSize.height) / 2;
        }
        else {
            this.offsetToCenterDisplay.y = 0;
        }
        if (this.modeChanged) {
            this.applet.imgScroll.getViewport().setViewPosition(new Point(0, 0));
        }
        if (max != this.savedWidth || max2 != this.savedHeight) {
            super.setSize(max, max2);
            super.setPreferredSize(new Dimension(max, max2));
            this.savedWidth = max;
            this.savedHeight = max2;
            this.mapLayers.clip();
        }
        this.getParent().doLayout();
        if (this.modeChanged) {
            int n = 0;
            int n2 = 0;
            if (max > size.width || max2 > size.height) {
                final int n3 = max - size.width;
                final int n4 = max2 - size.height;
                n = n3 / 2;
                n2 = n4 / 2;
                if (n < 0) {
                    n = 0;
                }
                if (n2 < 0) {
                    n2 = 0;
                }
            }
            this.applet.imgScroll.getViewport().setViewPosition(new Point(n, n2));
        }
        final Point upperLeftCorner = this.md.mosaicCoords.getUpperLeftCorner();
        if (upperLeftCorner == null) {
            return;
        }
        final double actualPixelSize = this.md.actualPixelSize;
        final Point point = upperLeftCorner;
        point.x -= (int)Math.round(this.offsetToCenterDisplay.x * actualPixelSize);
        final Point point2 = upperLeftCorner;
        point2.y += (int)Math.round(this.offsetToCenterDisplay.y * actualPixelSize);
        this.mapLayers.load(upperLeftCorner, new Point(upperLeftCorner.x + (int)Math.round(this.savedWidth * actualPixelSize), upperLeftCorner.y - (int)Math.round(this.savedHeight * actualPixelSize)), this.md.getProjection(), this.md.getProjectionCode());
    }
    
    public Rectangle2D.Float getDisplayAreaRectangle() {
        final Point upperLeftCorner = this.md.mosaicCoords.getUpperLeftCorner();
        final double actualPixelSize = this.md.actualPixelSize;
        return new Rectangle2D.Float(upperLeftCorner.x, upperLeftCorner.y, Math.round(this.savedWidth * actualPixelSize), Math.round(this.savedHeight * actualPixelSize));
    }
    
    @Override
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    @Override
    public void update(final Observable observable, final Object o) {
        if (this.md.imageLoader.isBusy()) {
            this.refreshTimer.start();
        }
        if (this.md.isUnstableTOC()) {
            this.refreshTimer.start();
            return;
        }
        this.modeChanged = false;
        if (o == MosaicData.DISPLAY_MODE_CHANGED) {
            this.modeChanged = true;
        }
        this.clear();
        this.setSize(this.preferredSize = new Dimension(this.md.getDisplaySize()));
        if (o == this.md.targetXY) {
            final Point upperLeftCorner = this.md.mosaicCoords.getUpperLeftCorner();
            final double actualPixelSize = this.md.actualPixelSize;
            int n = (int)Math.round((this.md.targetXY.x - upperLeftCorner.x) / actualPixelSize);
            int n2 = (int)Math.round((upperLeftCorner.y - this.md.targetXY.y) / actualPixelSize);
            final Dimension minimumSize = this.getParent().getMinimumSize();
            final int n3 = this.savedWidth - minimumSize.width;
            final int n4 = this.savedHeight - minimumSize.height;
            if (n < 0) {
                n = 0;
            }
            else if (n > n3) {
                n = n3;
            }
            if (n2 < 0) {
                n2 = 0;
            }
            else if (n2 > n4) {
                n2 = n4;
            }
            this.applet.imgScroll.getViewport().setViewPosition(new Point(n, n2));
        }
        this.modeChanged = false;
        this.repaint();
    }
    
    public void clear() {
        if (this.modeChanged) {}
    }
    
    private void drawSceneHighlight(final Graphics graphics, final Metadata metadata, final boolean b, final boolean b2, final boolean b3) {
        final Sensor currentSensor = this.applet.sensorMenu.getCurrentSensor();
        if (!this.md.getCurrentCell().valid) {
            return;
        }
        final Polygon screenLocation = metadata.screenLocation;
        final double atan2 = Math.atan2(screenLocation.ypoints[1] - screenLocation.ypoints[0], screenLocation.xpoints[1] - screenLocation.xpoints[0]);
        final double sin = Math.sin(atan2);
        final double cos = Math.cos(atan2);
        for (int i = 0; i < 4; ++i) {
            int n = currentSensor.borderX[i];
            int n2 = currentSensor.borderY[i];
            if (b) {
                n *= 2;
                if (b2 && i <= 1) {
                    n2 *= 2;
                }
                else if (b3 && i >= 2) {
                    n2 *= 2;
                }
                else if (n2 > 0) {
                    n2 = -1;
                }
                else {
                    n2 = 1;
                }
            }
            this.tempX[i] = screenLocation.xpoints[i] + (int)Math.round(n * cos - n2 * sin) - this.dragOffset.x;
            this.tempY[i] = screenLocation.ypoints[i] + (int)Math.round(n * sin + n2 * cos) + this.dragOffset.y;
        }
        if (b) {
            graphics.setColor(this.swathHighlightColor);
        }
        else {
            graphics.setColor(currentSensor.getBorderColor(metadata));
        }
        graphics.fillPolygon(this.tempX, this.tempY, 4);
        if (currentSensor.useHighlightTransparentPixelFix) {
            for (int j = 0; j < 4; ++j) {
                final int n3 = (currentSensor.borderX[j] > 0) ? -1 : 1;
                final int n4 = (currentSensor.borderY[j] > 0) ? -1 : 1;
                this.tempX[j] = screenLocation.xpoints[j] + (int)Math.round(n3 * cos - n4 * sin) - this.dragOffset.x;
                this.tempY[j] = screenLocation.ypoints[j] + (int)Math.round(n3 * sin + n4 * cos) + this.dragOffset.y;
            }
            graphics.setColor(Color.BLACK);
            graphics.fillPolygon(this.tempX, this.tempY, 4);
        }
    }
    
    private boolean checkSwathTopOrBottom(final Metadata metadata, final Metadata metadata2, final boolean b) {
        boolean b2 = false;
        if (metadata2 == null || !metadata2.visible) {
            b2 = true;
        }
        else {
            int n;
            if (b) {
                n = metadata.screenLocation.ypoints[3] - metadata2.screenLocation.ypoints[0];
            }
            else {
                n = metadata2.screenLocation.ypoints[3] - metadata.screenLocation.ypoints[0];
            }
            if (n < -2) {
                b2 = true;
            }
        }
        return b2;
    }
    
    private void paintMosaic(final Graphics graphics) {
        final Sensor currentSensor = this.applet.sensorMenu.getCurrentSensor();
        final Point upperLeftCorner = this.getUpperLeftCorner();
        if (upperLeftCorner == null) {
            return;
        }
        final int x = upperLeftCorner.x;
        final int y = upperLeftCorner.y;
        final int pixelSize = this.md.pixelSize;
        final int numCellsAtResolution = currentSensor.getNumCellsAtResolution(pixelSize);
        final double actualPixelSize = this.md.actualPixelSize;
        final ZOrderList zOrder = this.md.getZOrder();
        final Color color = new Color(204, 255, 255);
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        int n = (currentSensor.hasSwathMode && this.applet.toolsMenu.isSwathModeEnabled()) ? 1 : 0;
        Vector<Metadata> scenesInSwath = null;
        int date = 0;
        Sensor sensor = null;
        if (n != 0) {
            final Metadata currentScene = this.md.getCurrentScene();
            if (currentScene != null) {
                scenesInSwath = (Vector<Metadata>)this.md.getScenesInSwath(currentScene);
                date = currentScene.date;
                sensor = currentScene.getSensor();
            }
            if (scenesInSwath == null || scenesInSwath.size() == 0) {
                n = 0;
            }
        }
        Metadata up;
        while ((up = zOrder.up()) != null) {
            if (!this.md.canDisplay(up)) {
                continue;
            }
            final boolean top = zOrder.isTop();
            boolean b = false;
            boolean checkSwathTopOrBottom = false;
            boolean checkSwathTopOrBottom2 = false;
            if (n != 0 && up.date == date && sensor == up.getSensor()) {
                b = true;
                Metadata metadata = null;
                for (int i = 0; i < scenesInSwath.size(); ++i) {
                    final Metadata metadata2 = scenesInSwath.elementAt(i);
                    if (metadata2 == up) {
                        checkSwathTopOrBottom = this.checkSwathTopOrBottom(metadata2, metadata, false);
                        Metadata metadata3 = null;
                        if (i < scenesInSwath.size() - 1) {
                            metadata3 = scenesInSwath.elementAt(i + 1);
                        }
                        checkSwathTopOrBottom2 = this.checkSwathTopOrBottom(metadata2, metadata3, true);
                        break;
                    }
                    metadata = metadata2;
                }
            }
            if (up.image != null && up.imageRes == pixelSize) {
                if ((top || b) && this.md.canShowHighlight()) {
                    graphics.translate(this.offsetToCenterDisplay.x, this.offsetToCenterDisplay.y);
                    if (b) {
                        this.drawSceneHighlight(graphics, up, true, checkSwathTopOrBottom, checkSwathTopOrBottom2);
                    }
                    if (top) {
                        this.drawSceneHighlight(graphics, up, false, false, false);
                    }
                    graphics.translate(-this.offsetToCenterDisplay.x, -this.offsetToCenterDisplay.y);
                }
                graphics.drawImage(up.image, (int)Math.round((up.ulX - x) / actualPixelSize), (int)Math.round((y - up.ulY) / actualPixelSize), this);
            }
            else {
                if (numCellsAtResolution == 0) {
                    continue;
                }
                graphics.translate(this.offsetToCenterDisplay.x, this.offsetToCenterDisplay.y);
                if ((top || b) && this.md.canShowHighlight()) {
                    if (b) {
                        this.drawSceneHighlight(graphics, up, true, checkSwathTopOrBottom, checkSwathTopOrBottom2);
                    }
                    if (top) {
                        this.drawSceneHighlight(graphics, up, false, false, false);
                    }
                }
                graphics.setColor(color);
                if (this.dragActive) {
                    final int[] xpoints = up.screenLocation.xpoints;
                    final int[] ypoints = up.screenLocation.ypoints;
                    for (int j = 0; j < 4; ++j) {
                        array[j] = xpoints[j] - this.dragOffset.x;
                        array2[j] = ypoints[j] + this.dragOffset.y;
                    }
                    graphics.fillPolygon(array, array2, 4);
                }
                else {
                    graphics.fillPolygon(up.screenLocation);
                }
                graphics.translate(-this.offsetToCenterDisplay.x, -this.offsetToCenterDisplay.y);
            }
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        this.repaint();
        if (!this.md.isBusy() && !this.md.imageLoader.isBusy()) {
            this.refreshTimer.stop();
        }
    }
    
    public void paintComponent(final Graphics graphics) {
        final Dimension size = this.applet.imgScroll.getViewport().getSize();
        if (this.refreshTimer.isRunning() && !this.md.isBusy() && !this.md.imageLoader.isBusy()) {
            this.refreshTimer.stop();
        }
        final Dimension size2 = this.getSize();
        final Dimension size3 = this.getParent().getSize();
        size2.width = Math.max(size2.width, size3.width);
        size2.height = Math.max(size2.height, size3.height);
        if (this.offScreenBuffer == null || this.osbWidth < size2.width || this.osbHeight < size2.height) {
            if (this.offScreenBuffer != null) {
                this.offScreenBuffer.flush();
            }
            this.osbWidth = Math.max(size2.width + 20, this.osbWidth);
            this.osbHeight = Math.max(size2.height + 20, this.osbHeight);
            this.offScreenBuffer = this.createImage(this.osbWidth, this.osbHeight);
        }
        if (this.md.isBusy()) {
            this.md.checkForCompletedLoad();
        }
        if (this.offScreenBuffer != null) {
            final Graphics graphics2 = this.offScreenBuffer.getGraphics();
            graphics2.setColor(Color.BLACK);
            graphics2.fillRect(0, 0, this.osbWidth, this.osbHeight);
            this.paintMosaic(graphics2);
            this.mapLayers.paint(graphics2);
            if (this.applet.sensorMenu.getCurrentSensor().hasUserDefinedArea) {
                this.applet.userDefinedAreaDialog.getUserDefinedArea().drawArea(graphics2);
            }
            final Point viewPosition = this.applet.imgScroll.getViewport().getViewPosition();
            final Metadata currentScene = this.md.getCurrentScene();
            if (currentScene != null && currentScene.isDownloadable) {
                final int width = this.downloadIcon.getWidth(null);
                final int height = this.downloadIcon.getHeight(null);
                final int n = 0;
                final int n2 = 0 + viewPosition.x;
                final int n3 = n + viewPosition.y;
                graphics2.setColor(new Color(255, 255, 255, 210));
                graphics2.fillRect(n2, n3, width, height);
                this.downloadLoc = new Rectangle(n2, n3, width, height);
                graphics2.drawImage(this.downloadIcon, n2, n3, this);
            }
            else {
                this.downloadLoc = null;
            }
            final Image logo = this.logo.getLogo();
            final int width2 = logo.getWidth(null);
            final int height2 = logo.getHeight(null);
            int n4;
            int n5;
            if (this.logo.getLocation() == 2) {
                n4 = size.width - width2;
                n5 = size.height - height2;
            }
            else {
                n4 = 0;
                n5 = size.height - height2;
            }
            final int n6 = n4 + viewPosition.x;
            final int n7 = n5 + viewPosition.y;
            this.logoLoc = new Rectangle(n6, n7, width2, height2);
            graphics2.drawImage(logo, n6, n7, this);
            if (this.md.isBusy()) {
                this.md.checkForCompletedLoad();
            }
            graphics2.dispose();
            graphics.drawImage(this.offScreenBuffer, 0, 0, this);
        }
    }
    
    @Override
    public void keyPressed(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 39: {
                this.md.scrollInDirection(1, 0, 0, 0);
                break;
            }
            case 37: {
                this.md.scrollInDirection(0, 1, 0, 0);
                break;
            }
            case 38: {
                this.md.scrollInDirection(0, 0, 1, 0);
                break;
            }
            case 40: {
                this.md.scrollInDirection(0, 0, 0, 1);
                break;
            }
            case 33: {
                this.md.sceneFilter.prevDate();
                break;
            }
            case 34: {
                this.md.sceneFilter.nextDate();
                break;
            }
            case 36: {
                this.md.sceneFilter.gotoFirstDate();
                break;
            }
            case 35: {
                this.md.sceneFilter.gotoLastDate();
                break;
            }
        }
    }
    
    @Override
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    @Override
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.applet.userDefinedAreaDialog.isVisible()) {
            this.applet.userDefinedAreaDialog.getUserDefinedArea().mousePressed(this.getXYOnScreen(mouseEvent.getX(), mouseEvent.getY()));
        }
        else {
            final Sensor currentSensor = this.applet.sensorMenu.getCurrentSensor();
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            this.savedRightClickLoc = this.getLatLongOnScreen(x, y);
            this.dragMouseLoc = mouseEvent.getPoint();
            this.dragScrollLoc = this.applet.imgScroll.getViewport().getViewPosition();
            this.dragOffset.x = 0;
            this.dragOffset.y = 0;
            this.requestFocusInWindow();
            if (this.logoLoc.contains(x, y)) {
                this.logo.clicked();
                return;
            }
            if (this.downloadLoc != null && this.downloadLoc.contains(x, y) && (currentSensor.isDownloadable || currentSensor.mightBeDownloadable)) {
                final ArrayList<String> list = new ArrayList<String>();
                list.add("Images that display the 'Downloadable' label include a Level 1");
                list.add("product that may be immediately downloaded at no charge.");
                list.add("Scenes that do not display this label may have other products");
                list.add("available for immediate download.");
                if (currentSensor.isOrderable) {
                    list.add("If you would like the Level 1 product, you may submit a");
                    list.add("processing request.");
                }
                list.add("Add the scenes to your Scene List and click the Send to Cart");
                list.add("button to download any available products.");
                JOptionPane.showMessageDialog(this.applet.getDialogContainer(), list.toArray(), "Download Information", 1);
                return;
            }
            if (this.md.isUnstableTOC()) {
                return;
            }
            this.mapLayers.findFeatureName(x, y, true);
            this.dragActive = true;
            final int n = x - this.offsetToCenterDisplay.x;
            final int n2 = y - this.offsetToCenterDisplay.y;
            final Metadata scene = this.md.findSceneAt(n, n2);
            if (scene == null) {
                return;
            }
            switch (mouseEvent.getClickCount()) {
                case 1: {
                    final int modifiers = mouseEvent.getModifiers();
                    if ((modifiers & 0xC) == 0x0 && (modifiers & 0xF) != 0x0) {
                        this.md.lowerScene(scene);
                        break;
                    }
                    if ((modifiers & 0xC) != 0x0) {
                        this.dragActive = false;
                        Vector scenes = null;
                        if (currentSensor.isFullMosaic) {
                            scenes = this.md.findScenesAt(n, n2);
                        }
                        boolean b = false;
                        if (currentSensor.getNumCellsAtResolution(this.md.pixelSize) == 0) {
                            b = true;
                        }
                        boolean b2 = true;
                        if (currentSensor.isFullMosaic) {
                            b2 = false;
                        }
                        boolean b3 = true;
                        boolean b4 = true;
                        if (currentSensor.isFullMosaic) {
                            b3 = false;
                            b4 = false;
                        }
                        this.sceneMenu.configureMenu(scene, scenes, b, b2, b3, b4);
                        this.sceneMenu.show(this, x, y);
                        break;
                    }
                    this.md.setSelectedScene(scene);
                    break;
                }
                case 2: {
                    if ((mouseEvent.getModifiers() & 0x10) == 0x10) {
                        this.md.sceneFilter.nextDate();
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.applet.userDefinedAreaDialog.isVisible()) {
            this.applet.userDefinedAreaDialog.getUserDefinedArea().mouseRelease(this.getXYOnScreen(mouseEvent.getX(), mouseEvent.getY()));
        }
        else {
            if (!this.dragActive) {
                return;
            }
            this.dragActive = false;
            this.currentCursor = this.applet.crosshairCursor;
            if (!this.applet.showingWait) {
                this.setCursor(this.currentCursor);
            }
            int n = 1;
            if (this.dragOffset.x == 0 && this.dragOffset.y == 0) {
                n = 0;
            }
            if (Math.abs(this.dragOffset.x) > 10 || Math.abs(this.dragOffset.y) > 10) {
                final Point upperLeftCorner = this.getUpperLeftCorner();
                if (upperLeftCorner != null) {
                    final double actualPixelSize = this.md.actualPixelSize;
                    final Point viewPosition = this.applet.imgScroll.getViewport().getViewPosition();
                    final Point point = upperLeftCorner;
                    point.x += (int)Math.round(viewPosition.x * actualPixelSize);
                    final Point point2 = upperLeftCorner;
                    point2.y -= (int)Math.round(viewPosition.y * actualPixelSize);
                    final Point point3 = upperLeftCorner;
                    point3.x += (int)Math.round(this.offsetToCenterDisplay.x * actualPixelSize);
                    final Point point4 = upperLeftCorner;
                    point4.y -= (int)Math.round(this.offsetToCenterDisplay.y * actualPixelSize);
                    this.dragOffset.x = 0;
                    this.dragOffset.y = 0;
                    n = (this.md.gotoXY(upperLeftCorner.x, upperLeftCorner.y) ? 0 : 1);
                }
            }
            this.dragOffset.x = 0;
            this.dragOffset.y = 0;
            if (n != 0) {
                this.mapLayers.clip();
                this.repaint();
            }
        }
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        this.applet.statusBar.showStatus("");
    }
    
    @Override
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.applet.userDefinedAreaDialog.isVisible()) {
            this.applet.userDefinedAreaDialog.getUserDefinedArea().mouseDragged(this.getXYOnScreen(mouseEvent.getX(), mouseEvent.getY()));
            return;
        }
        if (!this.dragActive) {
            return;
        }
        if (this.md.isUnstableTOC()) {
            return;
        }
        if (this.currentCursor != this.applet.moveCursor) {
            this.currentCursor = this.applet.moveCursor;
            if (!this.applet.showingWait) {
                this.setCursor(this.currentCursor);
            }
        }
        final Dimension size = this.getSize();
        final Dimension size2 = this.getParent().getSize();
        final int n = size.width - size2.width;
        final int n2 = size.height - size2.height;
        final int n3 = this.dragMouseLoc.x - mouseEvent.getX();
        final int n4 = this.dragMouseLoc.y - mouseEvent.getY();
        int n5 = this.dragScrollLoc.x + n3;
        int n6 = this.dragScrollLoc.y + n4;
        int x;
        if (n5 < 0) {
            x = n5;
            n5 = 0;
        }
        else if (n5 > n) {
            x = n5 - n;
            n5 = n;
        }
        else {
            x = 0;
        }
        int y;
        if (n6 < 0) {
            y = -n6;
            n6 = 0;
        }
        else if (n6 > n2) {
            y = n2 - n6;
            n6 = n2;
        }
        else {
            y = 0;
        }
        if (x != this.dragOffset.x || y != this.dragOffset.y) {
            this.dragOffset.x = x;
            this.dragOffset.y = y;
            this.mapLayers.clip();
            this.repaint();
        }
        if (n5 != this.dragScrollLoc.x || n6 != this.dragScrollLoc.y) {
            this.applet.imgScroll.getViewport().setViewPosition(new Point(n5, n6));
            this.dragScrollLoc = this.applet.imgScroll.getViewport().getViewPosition();
            this.repaint();
        }
    }
    
    @Override
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.md.isUnstableTOC()) {
            this.applet.statusBar.showStatus("Loading Imagery...");
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final String featureName = this.mapLayers.findFeatureName(x, y, false);
        final LatLong latLongOnScreen = this.getLatLongOnScreen(x, y);
        if (latLongOnScreen != null) {
            String s;
            if (featureName == null) {
                s = new String("");
            }
            else {
                s = new String(" - " + featureName);
            }
            this.applet.statusBar.showStatus("Lat/Long: " + this.LatLonFormat.format(latLongOnScreen.latitude) + ", " + this.LatLonFormat.format(latLongOnScreen.longitude) + " degrees" + s);
        }
        else {
            this.applet.statusBar.showStatus("");
        }
    }
    
    public Point getUpperLeftCorner() {
        final Point upperLeftCorner = this.md.mosaicCoords.getUpperLeftCorner();
        if (upperLeftCorner == null) {
            return null;
        }
        final double actualPixelSize = this.md.actualPixelSize;
        final Point point = upperLeftCorner;
        point.x -= (int)Math.round(this.offsetToCenterDisplay.x * actualPixelSize);
        final Point point2 = upperLeftCorner;
        point2.y += (int)Math.round(this.offsetToCenterDisplay.y * actualPixelSize);
        if (this.dragOffset.x != 0 || this.dragOffset.y != 0) {
            final Point point3 = upperLeftCorner;
            point3.x += (int)Math.round(this.dragOffset.x * actualPixelSize);
            final Point point4 = upperLeftCorner;
            point4.y += (int)Math.round(this.dragOffset.y * actualPixelSize);
        }
        return upperLeftCorner;
    }
    
    private Point getXYOnScreen(final int n, final int n2) {
        final Point upperLeftCorner = this.getUpperLeftCorner();
        if (upperLeftCorner == null) {
            return null;
        }
        final int x = upperLeftCorner.x;
        final int y = upperLeftCorner.y;
        final double actualPixelSize = this.md.actualPixelSize;
        return new Point(x + (int)Math.round(n * actualPixelSize), y - (int)Math.round(n2 * actualPixelSize));
    }
    
    private LatLong getLatLongOnScreen(final int n, final int n2) {
        final Point xyOnScreen = this.getXYOnScreen(n, n2);
        if (xyOnScreen == null) {
            return null;
        }
        return this.md.getLatLong(xyOnScreen);
    }
    
    public LatLong getRightClickLatLong() {
        return this.savedRightClickLoc;
    }
    
    public boolean intersectsGeoBox(final Rectangle2D.Double double1) {
        final Rectangle2D.Float displayAreaRectangle = this.getDisplayAreaRectangle();
        final ProjectionTransformation projection = this.md.getProjection();
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        array[0] = (int)displayAreaRectangle.x;
        array[1] = (int)(displayAreaRectangle.x + displayAreaRectangle.width);
        array[2] = (int)(displayAreaRectangle.x + displayAreaRectangle.width);
        array[3] = (int)displayAreaRectangle.x;
        array2[0] = (int)displayAreaRectangle.y;
        array2[1] = (int)displayAreaRectangle.y;
        array2[2] = (int)(displayAreaRectangle.y - displayAreaRectangle.height);
        array2[3] = (int)(displayAreaRectangle.y - displayAreaRectangle.height);
        final LatLong[] array3 = new LatLong[4];
        for (int i = 0; i < 4; ++i) {
            array3[i] = projection.projToLatLong(array[i], array2[i]);
            if (array3[i] == null) {
                return true;
            }
        }
        boolean b = false;
        if (array3[0].longitude > 100.0) {
            for (int j = 1; j < 4; ++j) {
                if (array3[j].longitude < -100.0) {
                    b = true;
                    final LatLong latLong = array3[j];
                    latLong.longitude += 360.0;
                }
            }
        }
        else if (array3[0].longitude < -100.0) {
            for (int k = 1; k < 4; ++k) {
                if (array3[k].longitude > 100.0) {
                    b = true;
                    final LatLong latLong2 = array3[k];
                    latLong2.longitude -= 360.0;
                }
            }
        }
        if (this.buildPath(array3).intersects(double1)) {
            return true;
        }
        if (b) {
            double n = 360.0;
            if (array3[0].longitude > 100.0) {
                n = -360.0;
            }
            for (int l = 0; l < 4; ++l) {
                final LatLong latLong3 = array3[l];
                latLong3.longitude += n;
            }
            if (this.buildPath(array3).intersects(double1)) {
                return true;
            }
        }
        return false;
    }
    
    private GeneralPath buildPath(final LatLong[] array) {
        final GeneralPath generalPath = new GeneralPath();
        generalPath.moveTo((float)array[0].longitude, (float)array[0].latitude);
        for (int i = 1; i < array.length; ++i) {
            generalPath.lineTo((float)array[i].longitude, (float)array[i].latitude);
        }
        generalPath.lineTo((float)array[0].longitude, (float)array[0].latitude);
        return generalPath;
    }
    
    public void cleanup() {
        this.logo.cleanup();
        if (this.offScreenBuffer != null) {
            this.offScreenBuffer.flush();
            this.offScreenBuffer = null;
        }
    }
}
