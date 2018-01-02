import java.awt.event.MouseEvent;
import java.util.Observable;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.text.DecimalFormat;
import java.awt.Point;
import java.awt.Image;
import java.util.Observer;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class LocatorMap extends JPanel implements MouseListener, MouseMotionListener, Observer
{
    private imgViewer applet;
    private Image markImage;
    private Point loc;
    private DecimalFormat LatLonFormat;
    private Image offScreenBuffer;
    public static final int GEOGRAPHIC_MAP = 0;
    public static final int US_GEOGRAPHIC_MAP = 1;
    public static final int SINUSOIDAL_MAP = 2;
    public static final int POLARSTEREOGRAPHIC_MAP = 3;
    private static final int NUM_MAPS = 4;
    private LocatorMapImpl currentMap;
    private LocatorMapImpl[] maps;
    
    LocatorMap(final imgViewer applet) {
        this.offScreenBuffer = null;
        this.applet = applet;
        this.setBackground(Color.BLACK);
        this.markImage = this.applet.getImage(this.applet.getCodeBase(), "graphics/ctxmarker.gif");
        this.maps = new LocatorMapImpl[4];
        this.currentMap = null;
        this.loc = new Point(1, 1);
        this.LatLonFormat = new DecimalFormat(" 0.00;-0.00");
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setSize(300, 300);
    }
    
    public void initialize(final double n, final double n2) {
        this.setCurrentMap(this.applet.sensorMenu.getCurrentSensor().locatorMap);
        this.setLoc(new LatLong(n, n2));
    }
    
    public GeographicLocatorMapConfig getMapConfig(final int n) {
        return this.getMap(n).getMapConfig();
    }
    
    private LocatorMapImpl getMap(final int n) {
        if (this.maps[n] == null) {
            if (n == 2) {
                this.maps[2] = new SinusoidalLocatorMap(this.applet);
            }
            else if (n == 1) {
                this.maps[1] = new GeographicLocatorMap(this.applet, new USLocatorMapConfig());
            }
            else if (n == 3) {
                this.maps[3] = new PolarStereographicLocatorMap(this.applet);
            }
            else {
                this.maps[n] = new GeographicLocatorMap(this.applet, new LocatorMapConfig());
            }
        }
        return this.maps[n];
    }
    
    private void setCurrentMap(final int n) {
        this.currentMap = this.getMap(n);
        if (this.offScreenBuffer != null) {
            final Graphics graphics = this.offScreenBuffer.getGraphics();
            final int width = this.offScreenBuffer.getWidth(null);
            final int height = this.offScreenBuffer.getHeight(null);
            graphics.setColor(Color.BLACK);
            graphics.fillRect(0, 0, width, height);
            graphics.dispose();
        }
        this.setSize(this.currentMap.imageSize);
        this.setPreferredSize(this.currentMap.imageSize);
        this.applet.locatorMapScroll.doLayout();
    }
    
    private void setLoc(final LatLong latLong) {
        this.loc = this.currentMap.latLongToPixel(latLong);
        this.updateScroll();
    }
    
    private void setLoc(final int n, final int n2) {
        this.loc = this.currentMap.gridToPixel(n, n2);
        this.updateScroll();
    }
    
    private void updateScroll() {
        final Dimension size = this.applet.locatorMapScroll.getSize();
        final int width = this.applet.locatorMapScroll.getVerticalScrollBar().getWidth();
        final int height = this.applet.locatorMapScroll.getHorizontalScrollBar().getHeight();
        final int n = (size.width - width) / 2;
        final int n2 = (size.height - height) / 2;
        int n3 = this.loc.x - n;
        if (n3 < 1) {
            n3 = 1;
        }
        else if (n3 > this.currentMap.imageSize.width - size.width + width) {
            n3 = this.currentMap.imageSize.width - size.width + width + 3;
        }
        int n4 = this.loc.y - n2;
        if (n4 < 1) {
            n4 = 1;
        }
        else if (n4 > this.currentMap.imageSize.height - size.height + height) {
            n4 = this.currentMap.imageSize.height - size.height + height + 3;
        }
        this.applet.locatorMapScroll.getViewport().setViewPosition(new Point(n3, n4));
        this.applet.locatorMapScroll.revalidate();
        this.repaint();
    }
    
    @Override
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    @Override
    public void update(final Observable observable, final Object o) {
        final int locatorMap = this.applet.sensorMenu.getCurrentSensor().locatorMap;
        if (this.maps[locatorMap] != this.currentMap || this.currentMap == null) {
            this.setCurrentMap(locatorMap);
        }
        final MosaicData md = this.applet.imgArea.md;
        this.setLoc(md.gridCol, md.gridRow);
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        if (this.currentMap == null) {
            return;
        }
        final Dimension size = this.getSize();
        if (this.offScreenBuffer != null && (this.offScreenBuffer.getHeight(null) < size.height || this.offScreenBuffer.getWidth(null) < size.width)) {
            this.offScreenBuffer.flush();
            this.offScreenBuffer = null;
        }
        if (this.offScreenBuffer == null) {
            this.offScreenBuffer = this.createImage(size.width, size.height);
        }
        final Graphics graphics2 = this.offScreenBuffer.getGraphics();
        graphics2.drawImage(this.currentMap.mapImage, 0, 0, this);
        if (this.currentMap.useBoundaryImage) {
            graphics2.drawImage(this.currentMap.worldBoundaries, 0, 0, this);
        }
        graphics2.drawImage(this.markImage, this.loc.x - this.markImage.getWidth(null) / 2, this.loc.y - this.markImage.getHeight(null) / 2, this);
        graphics2.dispose();
        graphics.drawImage(this.offScreenBuffer, 0, 0, this);
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.currentMap == null) {
            return;
        }
        this.currentMap.moveTo(mouseEvent.getX(), mouseEvent.getY());
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        this.applet.statusBar.showStatus("");
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.currentMap == null) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final Sensor currentSensor = this.applet.sensorMenu.getCurrentSensor();
        final NavigationModel navModel = currentSensor.navModel;
        final LatLong pixelToLatLong = this.currentMap.pixelToLatLong(x, y);
        final Point pixelToGrid = this.currentMap.pixelToGrid(x, y);
        final StringBuffer sb = new StringBuffer();
        if (pixelToLatLong != null) {
            sb.append("Lat/Long: ");
            sb.append(this.LatLonFormat.format(pixelToLatLong.latitude));
            sb.append(", ");
            sb.append(this.LatLonFormat.format(pixelToLatLong.longitude));
            sb.append(" degrees");
        }
        if (!currentSensor.hideGridEntry) {
            sb.append(", ");
            sb.append(navModel.getModelName());
            sb.append(" ");
            sb.append(navModel.getColName());
            sb.append("/");
            sb.append(navModel.getRowName());
            sb.append("  ");
            sb.append(navModel.getColumnString(pixelToGrid.x));
            sb.append(", ");
            sb.append(navModel.getRowString(pixelToGrid.y));
        }
        this.applet.statusBar.showStatus(sb.toString());
    }
    
    @Override
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void cleanup() {
        if (this.offScreenBuffer != null) {
            for (int i = 0; i < this.maps.length; ++i) {
                if (this.maps[i] != null) {
                    this.maps[i].cleanup();
                }
            }
            this.offScreenBuffer.flush();
            this.offScreenBuffer = null;
        }
    }
}
