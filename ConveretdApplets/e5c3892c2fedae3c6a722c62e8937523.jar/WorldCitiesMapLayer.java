import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Dimension;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.awt.Component;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Point;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class WorldCitiesMapLayer extends TiledMapLayer
{
    imgViewer applet;
    private Vector visibleCities;
    private Vector potentiallyVisibleCities;
    private boolean citiesNeedFiltering;
    private Point ulMeters;
    private Polygon starPolygon;
    
    public WorldCitiesMapLayer(final imgViewer applet, final Color color, final int n) {
        super(applet.imgArea, "Cities", "City", color, n, true);
        this.citiesNeedFiltering = false;
        this.applet = applet;
        this.visibleCities = new Vector();
        this.potentiallyVisibleCities = new Vector();
        this.starPolygon = new Polygon();
    }
    
    @Override
    public void read(final CancelLoad cancelLoad, final Point point, final int n, final MapLayerLoadingCallback mapLayerLoadingCallback) {
        this.ulMeters = new Point(point);
        this.citiesNeedFiltering = true;
        final String[] filesToLoad = this.fileCache.getFilesToLoad();
        if (filesToLoad == null) {
            return;
        }
        for (int length = filesToLoad.length, n2 = 0; n2 < length && !cancelLoad.cancelled; ++n2) {
            final Vector<City> vector = new Vector<City>();
            if (this.applet.verboseOutput) {
                System.out.println("Reading " + filesToLoad[n2]);
            }
            URL url;
            try {
                url = new URL(this.applet.getCodeBase(), filesToLoad[n2]);
            }
            catch (IOException ex) {
                System.out.println("Exception: Making map layer URL");
                return;
            }
            DataInputStream dataInputStream = null;
            try {
                dataInputStream = new DataInputStream(new BufferedInputStream(url.openStream()));
                int n3 = 50;
                byte[] array = new byte[n3];
                for (int int1 = dataInputStream.readInt(), n4 = 0; n4 < int1 && !cancelLoad.cancelled; ++n4) {
                    final City city = new City();
                    final byte byte1 = dataInputStream.readByte();
                    if (byte1 > n3) {
                        n3 = byte1;
                        array = new byte[n3];
                    }
                    int read = dataInputStream.read(array, 0, byte1);
                    if (read != byte1) {
                        read += dataInputStream.read(array, read, byte1 - read);
                    }
                    if (read != byte1) {
                        throw new IOException("Error reading feature name");
                    }
                    city.name = new String(array, 0, byte1 - 2);
                    city.level = Integer.parseInt(new String(array, byte1 - 1, 1));
                    if (city.level == 1) {
                        city.capital = true;
                    }
                    else {
                        city.capital = false;
                    }
                    dataInputStream.readInt();
                    city.x = dataInputStream.readInt();
                    city.y = dataInputStream.readInt();
                    vector.addElement(city);
                }
                dataInputStream.close();
                dataInputStream = null;
            }
            catch (IOException ex2) {
                try {
                    dataInputStream.close();
                }
                catch (Exception ex3) {}
                System.out.println("Exception:  I/O Error");
            }
            catch (NumberFormatException ex4) {
                try {
                    dataInputStream.close();
                }
                catch (Exception ex5) {}
                System.out.println("Exception:  Read/Format Error");
            }
            if (!cancelLoad.cancelled) {
                this.fileCache.setCacheContents(filesToLoad[n2], vector);
            }
            mapLayerLoadingCallback.incrementFileReadCounter();
        }
        this.fileCache.purge();
        this.citiesNeedFiltering = true;
    }
    
    void filter() {
        int n = 0;
        int n2 = 0;
        this.potentiallyVisibleCities.removeAllElements();
        final double actualPixelSize = this.applet.imgArea.md.actualPixelSize;
        int n3 = 3;
        if (actualPixelSize > 2000.0) {
            n3 = 0;
        }
        final Vector cachedData = this.fileCache.getCachedData();
        final Dimension displaySize = this.applet.imgArea.md.getDisplaySize();
        final Dimension minimumSize = this.applet.imgScroll.getMinimumSize();
        displaySize.width = Math.max(minimumSize.width, displaySize.width);
        displaySize.height = Math.max(minimumSize.height, displaySize.height);
        final int x = this.ulMeters.x;
        final int y = this.ulMeters.y;
        final int n4 = -650;
        final int n5 = displaySize.width + 650;
        final int n6 = -650;
        final int n7 = displaySize.height + 650;
        for (int i = 1; i <= 8; ++i) {
            for (int j = 0; j < cachedData.size(); ++j) {
                final Vector<City> vector = cachedData.elementAt(j);
                if (vector != null) {
                    for (int size = vector.size(), k = 0; k < size; ++k) {
                        boolean b = false;
                        City city = null;
                        int n8 = 10000000;
                        final City city2 = vector.elementAt(k);
                        if (city2.level == i) {
                            final int x2 = city2.x;
                            final int y2 = city2.y;
                            final int n9 = (int)((x2 - x) / actualPixelSize);
                            final int n10 = (int)((y - y2) / actualPixelSize);
                            if (n9 >= n4 && n9 < n5 && n10 >= n6 && n10 < n7) {
                                boolean b2 = false;
                                for (int size2 = this.potentiallyVisibleCities.size(), l = 0; l < size2; ++l) {
                                    final City city3 = this.potentiallyVisibleCities.elementAt(l);
                                    final int n11 = (int)((city2.x - city3.x) / actualPixelSize);
                                    final int n12 = (int)((city2.y - city3.y) / actualPixelSize);
                                    final int n13 = n11 * n11 + n12 * n12;
                                    if (n13 < 10000) {
                                        if (!city3.capital || city2.level > n3) {
                                            b2 = true;
                                            break;
                                        }
                                        b = true;
                                        if (n13 < n8) {
                                            city = city3;
                                            n8 = n13;
                                        }
                                    }
                                }
                                if (!b2) {
                                    boolean b3 = false;
                                    city2.shiftName = false;
                                    if (city2.capital) {
                                        b3 = true;
                                    }
                                    else if (n9 >= 0 && n9 < displaySize.width && n10 >= 20 && n10 < displaySize.height - 20) {
                                        if (n < 200) {
                                            ++n;
                                            b3 = true;
                                        }
                                    }
                                    else if (n2 < 300) {
                                        ++n2;
                                        b3 = true;
                                    }
                                    if (b3) {
                                        this.potentiallyVisibleCities.addElement(city2);
                                        if (b) {
                                            if (city2.y < city.y) {
                                                city2.shiftName = true;
                                                city.shiftName = false;
                                            }
                                            else {
                                                city.shiftName = true;
                                            }
                                        }
                                        if (n >= 200 && n2 >= 300) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public void clip(final Point point, final int n, final Dimension dimension, final ProjectionTransformation projectionTransformation) {
        if (this.citiesNeedFiltering) {
            this.citiesNeedFiltering = false;
            this.filter();
        }
        final double actualPixelSize = this.applet.imgArea.md.actualPixelSize;
        this.visibleCities.removeAllElements();
        for (int size = this.potentiallyVisibleCities.size(), i = 0; i < size; ++i) {
            final City city = this.potentiallyVisibleCities.elementAt(i);
            final int x = city.x;
            final int y = city.y;
            final int x2 = (int)((x - point.x) / actualPixelSize);
            final int y2 = (int)((point.y - y) / actualPixelSize);
            if (x2 >= 0 && x2 < dimension.width && y2 >= 18 && y2 < dimension.height - 18) {
                final City city2 = new City();
                city2.x = x2;
                city2.y = y2;
                city2.level = city.level;
                city2.capital = city.capital;
                city2.name = city.name;
                city2.shiftName = city.shiftName;
                this.visibleCities.addElement(city2);
            }
        }
    }
    
    @Override
    public void draw(final Graphics graphics) {
        graphics.setFont(this.applet.boldFont);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int height = fontMetrics.getHeight();
        for (int size = this.visibleCities.size(), i = 0; i < size; ++i) {
            final City city = this.visibleCities.elementAt(i);
            final int stringWidth = fontMetrics.stringWidth(city.name);
            if (city.capital) {
                this.starPolygon = new Polygon(new int[] { 0, 3, 10, 4, 8, 0, -8, -4, -10, -3 }, new int[] { -10, -3, -3, 1, 9, 4, 9, 1, -3, -3 }, 10);
                graphics.setColor(this.color);
                graphics.translate(city.x, city.y);
                graphics.fillPolygon(this.starPolygon);
                graphics.translate(-city.x, -city.y);
            }
            else {
                graphics.setColor(this.color);
                graphics.fillOval(city.x - 5, city.y - 5, 10, 10);
            }
            int n;
            if (city.shiftName) {
                n = 12 + height;
            }
            else {
                n = -12;
            }
            graphics.setColor(Color.BLACK);
            graphics.drawString(city.name, city.x - stringWidth / 2 - 2, city.y + n + 1);
            graphics.setColor(this.color);
            graphics.drawString(city.name, city.x - stringWidth / 2, city.y + n);
        }
    }
    
    class City
    {
        int x;
        int y;
        int level;
        boolean capital;
        boolean shiftName;
        String name;
    }
}
