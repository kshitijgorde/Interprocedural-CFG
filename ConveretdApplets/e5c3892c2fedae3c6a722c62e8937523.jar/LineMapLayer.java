import java.awt.Shape;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.Dimension;
import java.awt.geom.GeneralPath;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.awt.Point;
import java.awt.Component;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class LineMapLayer extends TiledMapLayer
{
    private imgViewer applet;
    private float lineWidth;
    private Vector clippedPaths;
    private boolean antialias;
    private boolean hasNames;
    private AffineTransform identityTransform;
    
    LineMapLayer(final imgViewer applet, final String s, final String s2, final Color color, final float lineWidth, final boolean hasNames, final boolean antialias, final int n, final boolean b) {
        super(applet.imgArea, s, s2, color, n, b);
        this.applet = applet;
        this.lineWidth = lineWidth;
        this.hasNames = hasNames;
        this.antialias = antialias;
        this.identityTransform = new AffineTransform();
    }
    
    @Override
    public void read(final CancelLoad cancelLoad, final Point point, final int n, final MapLayerLoadingCallback mapLayerLoadingCallback) {
        final String[] filesToLoad = this.fileCache.getFilesToLoad();
        if (filesToLoad == null) {
            return;
        }
        for (int length = filesToLoad.length, n2 = 0; n2 < length && !cancelLoad.cancelled; ++n2) {
            final Vector<NamedEntry> vector = new Vector<NamedEntry>();
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
                byte[] array = null;
                byte b = 0;
                if (this.hasNames) {
                    b = 50;
                    array = new byte[b];
                }
                for (int int1 = dataInputStream.readInt(), n3 = 0; n3 < int1 && !cancelLoad.cancelled; ++n3) {
                    final NamedEntry namedEntry = new NamedEntry();
                    namedEntry.numPoints = 0;
                    if (this.hasNames) {
                        final byte byte1 = dataInputStream.readByte();
                        if (byte1 > b) {
                            b = byte1;
                            array = new byte[b];
                        }
                        int read = dataInputStream.read(array, 0, byte1);
                        if (read != byte1) {
                            read += dataInputStream.read(array, read, byte1 - read);
                        }
                        if (read != byte1) {
                            throw new IOException("Error reading feature name");
                        }
                        namedEntry.name = new String(array, 0, byte1);
                    }
                    int n4 = dataInputStream.readInt();
                    int int2 = 1;
                    if (n4 == 0) {
                        int2 = dataInputStream.readInt();
                    }
                    final GeneralPath path = new GeneralPath();
                    for (int i = 0; i < int2; ++i) {
                        int n5 = 0;
                        if (int2 > 1 || n4 == 0) {
                            n4 = dataInputStream.readInt();
                        }
                        if (n4 < 0) {
                            n5 = 1;
                            n4 = -n4;
                        }
                        int int3 = dataInputStream.readInt();
                        int int4 = dataInputStream.readInt();
                        path.moveTo(int3, int4);
                        if (n5 == 1) {
                            for (int j = 1; j < n4; ++j) {
                                final short short1 = dataInputStream.readShort();
                                final short short2 = dataInputStream.readShort();
                                int3 += short1;
                                int4 += short2;
                                path.lineTo(int3, int4);
                            }
                        }
                        else {
                            for (int k = 1; k < n4; ++k) {
                                path.lineTo(dataInputStream.readInt(), dataInputStream.readInt());
                            }
                        }
                    }
                    namedEntry.path = path;
                    final NamedEntry namedEntry2 = namedEntry;
                    namedEntry2.numPoints += n4;
                    vector.addElement(namedEntry);
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
    }
    
    @Override
    public void clip(final Point point, final int n, final Dimension dimension, final ProjectionTransformation projectionTransformation) {
        final double[] array = new double[6];
        this.clippedPaths = new Vector();
        final int x = point.x;
        final int y = point.y;
        final double actualPixelSize = this.applet.imgArea.md.actualPixelSize;
        final Vector cachedData = this.fileCache.getCachedData();
        final Rectangle2D.Double double1 = new Rectangle2D.Double(x, y - dimension.height * actualPixelSize, dimension.width * actualPixelSize, dimension.height * actualPixelSize);
        final AffineTransform affineTransform = new AffineTransform();
        affineTransform.scale(1.0 / actualPixelSize, -1.0 / actualPixelSize);
        affineTransform.translate(-x, -y);
        for (int i = 0; i < cachedData.size(); ++i) {
            final Vector<NamedEntry> vector = cachedData.elementAt(i);
            if (vector != null) {
                for (int size = vector.size(), j = 0; j < size; ++j) {
                    final NamedEntry namedEntry = vector.elementAt(j);
                    boolean b;
                    if (namedEntry.numPoints != 1) {
                        b = namedEntry.path.intersects(double1);
                    }
                    else {
                        namedEntry.path.getPathIterator(this.identityTransform).currentSegment(array);
                        b = double1.contains(array[0], array[1]);
                    }
                    if (b) {
                        final NamedEntry namedEntry2 = new NamedEntry();
                        namedEntry2.name = namedEntry.name;
                        namedEntry2.numPoints = namedEntry.numPoints;
                        namedEntry2.path = (GeneralPath)namedEntry.path.createTransformedShape(affineTransform);
                        this.clippedPaths.addElement(namedEntry2);
                    }
                }
            }
        }
    }
    
    @Override
    public MapLayerFeatureInfo findFeatureName(final int n, final int n2) {
        if (!this.hasNames) {
            return null;
        }
        if (this.clippedPaths == null) {
            return null;
        }
        double area = 1.0E11;
        String name = null;
        final float[] array = new float[6];
        for (int size = this.clippedPaths.size(), i = 0; i < size; ++i) {
            final NamedEntry namedEntry = this.clippedPaths.elementAt(i);
            if (namedEntry.numPoints != 1) {
                if (namedEntry.path.contains(n, n2)) {
                    final Rectangle bounds = namedEntry.path.getBounds();
                    final double n3 = bounds.width * bounds.height;
                    if (n3 < area) {
                        area = n3;
                        name = namedEntry.name;
                    }
                }
            }
            else {
                namedEntry.path.getPathIterator(this.identityTransform).currentSegment(array);
                final int n4 = (int)array[0];
                final int n5 = (int)array[1];
                if (n >= n4 - 5 && n < n4 + 5 && n2 >= n5 - 5 && n2 < n5 + 5) {
                    area = 1.0;
                    name = namedEntry.name;
                }
            }
        }
        if (name != null) {
            final MapLayerFeatureInfo mapLayerFeatureInfo = new MapLayerFeatureInfo();
            mapLayerFeatureInfo.name = name;
            mapLayerFeatureInfo.area = area;
            return mapLayerFeatureInfo;
        }
        return null;
    }
    
    @Override
    public void draw(final Graphics graphics) {
        if (this.clippedPaths == null) {
            return;
        }
        final int size = this.clippedPaths.size();
        if (size > 0) {
            final Graphics2D graphics2D = (Graphics2D)graphics;
            Object renderingHint = null;
            final float[] array = new float[6];
            if (this.antialias) {
                renderingHint = graphics2D.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            }
            graphics2D.translate(-1, 1);
            graphics2D.setColor(Color.BLACK);
            final Stroke stroke = graphics2D.getStroke();
            graphics2D.setStroke(new BasicStroke(this.lineWidth, 1, 1));
            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < size; ++j) {
                    final NamedEntry namedEntry = this.clippedPaths.elementAt(j);
                    if (namedEntry.numPoints != 1) {
                        graphics2D.draw(namedEntry.path);
                    }
                    else {
                        namedEntry.path.getPathIterator(this.identityTransform).currentSegment(array);
                        graphics2D.fillOval((int)array[0] - 5, (int)array[1] - 5, 10, 10);
                    }
                }
                if (i == 0) {
                    graphics2D.setColor(this.color);
                    graphics.translate(1, -1);
                }
            }
            if (this.antialias) {
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, renderingHint);
            }
            graphics2D.setStroke(stroke);
        }
    }
    
    class NamedEntry
    {
        String name;
        int numPoints;
        GeneralPath path;
    }
}
