import java.awt.Shape;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.PathIterator;
import java.awt.Dimension;
import java.awt.geom.GeneralPath;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.Point;
import java.awt.Component;
import java.awt.Color;
import java.io.File;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class ShapeFileMapLayer extends TiledMapLayer
{
    private imgViewer applet;
    private int lineWidth;
    private Vector clippedShapes;
    private boolean valid;
    private String filename;
    private Vector shapes;
    private Rectangle2D.Double visibleArea;
    private boolean needsLoading;
    private ShapeFileAttributesPanel attributesPanel;
    private String activeAttribute;
    private String filterAttribute;
    private String filterValue;
    private AffineTransform identityTransform;
    private int displayedIndex;
    private Rectangle2D extent;
    
    ShapeFileMapLayer(final imgViewer applet, final String s, final String s2, final ShapeFileAttributesDialog shapeFileAttributesDialog, final File file) {
        super(applet.imgArea, s, s2, Color.RED, 0, true);
        this.needsLoading = false;
        this.activeAttribute = "";
        this.filterAttribute = "nofilter";
        this.filterValue = "";
        this.displayedIndex = -1;
        this.applet = applet;
        this.lineWidth = 1;
        this.visibleArea = new Rectangle2D.Double();
        this.setFile(file, shapeFileAttributesDialog);
        this.identityTransform = new AffineTransform();
    }
    
    @Override
    public void setLayerOn(final boolean layerOn) {
        if (!this.isLayerOn() && layerOn) {
            final Rectangle2D.Double extent = this.getExtent();
            if (!this.applet.imgArea.intersectsGeoBox(extent)) {
                this.applet.md.gotoLatLong(extent.y + extent.height / 2.0, extent.x + extent.width / 2.0);
            }
        }
        super.setLayerOn(layerOn);
    }
    
    public boolean isValid() {
        return this.attributesPanel != null;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    @Override
    public int setDisplayAreaUsingLatLong(final LatLong latLong, final LatLong latLong2, final int n) {
        final double n2 = this.to15deg(latLong.longitude, true);
        final double n3 = this.to15deg(latLong2.longitude, false) + 15;
        final double n4 = this.to15deg(latLong2.latitude, true);
        final Rectangle2D.Double visibleArea = new Rectangle2D.Double(n2, n4, n3 - n2, this.to15deg(latLong.latitude, false) + 15 - n4);
        if (!visibleArea.equals(this.visibleArea)) {
            this.visibleArea = visibleArea;
            this.needsLoading = true;
            this.clippedShapes = null;
            return 1;
        }
        return 0;
    }
    
    @Override
    public int setDisplayAreaUsingProjCoords(final Point point, final Point point2, final int n) {
        final Rectangle2D.Double visibleArea = new Rectangle2D.Double(-180.0, -90.0, 360.0, 180.0);
        if (!visibleArea.equals(this.visibleArea)) {
            this.visibleArea = visibleArea;
            this.needsLoading = true;
            this.clippedShapes = null;
            return 1;
        }
        return 0;
    }
    
    public static boolean isValidShapeFile(final File file) {
        final String absolutePath = file.getAbsolutePath();
        final String substring = absolutePath.substring(0, absolutePath.lastIndexOf(46));
        final File file2 = new File(substring + ".dbf");
        final File file3 = new File(substring + ".shp");
        final File file4 = new File(substring + ".shx");
        if (!file2.canRead() || !file3.canRead() || !file4.canRead()) {
            return false;
        }
        final File file5 = new File(substring + ".prj");
        if (file5.canRead()) {
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(file5));
                final String trim = bufferedReader.readLine().trim();
                if (trim != null && trim.startsWith("PROJCS")) {
                    return false;
                }
            }
            catch (Exception ex) {}
            finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                }
                catch (Exception ex2) {}
            }
        }
        return true;
    }
    
    private void setFile(final File file, final ShapeFileAttributesDialog shapeFileAttributesDialog) {
        final String absolutePath = file.getAbsolutePath();
        this.filename = absolutePath.substring(0, absolutePath.lastIndexOf(46));
        this.needsLoading = true;
        ShapeFileReader shapeFileReader = null;
        DBFReader dbfReader = null;
        String[] columnNames = null;
        boolean b = false;
        try {
            shapeFileReader = new ShapeFileReader();
            shapeFileReader.open(this.filename);
            this.extent = shapeFileReader.getExtent();
            shapeFileReader.close();
            shapeFileReader = null;
            dbfReader = new DBFReader();
            dbfReader.open(this.filename);
            columnNames = dbfReader.getColumnNames();
            b = true;
        }
        catch (IOException ex) {}
        finally {
            if (shapeFileReader != null) {
                try {
                    shapeFileReader.close();
                }
                catch (Exception ex2) {}
            }
            if (dbfReader != null) {
                try {
                    dbfReader.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (b) {
            this.attributesPanel = shapeFileAttributesDialog.addShapeFile(this.getName(), this, columnNames);
        }
    }
    
    public Rectangle2D.Double getExtent() {
        final Rectangle2D.Double double1 = new Rectangle2D.Double();
        double1.setRect(this.extent);
        return double1;
    }
    
    public void setActiveAttribute(final String s) {
        if (!s.equals(this.activeAttribute)) {
            this.activeAttribute = new String(s);
            if (!this.needsLoading) {
                this.needsLoading = true;
                this.applet.md.mapLayers.showLayers();
            }
        }
    }
    
    public void setFilter(final String s, final String s2) {
        if (!s.equals(this.filterAttribute) || !s2.equals(this.filterValue)) {
            this.filterAttribute = new String(s);
            this.filterValue = new String(s2);
            if (!this.needsLoading) {
                this.needsLoading = true;
                this.applet.md.mapLayers.showLayers();
            }
        }
    }
    
    @Override
    public void read(final CancelLoad cancelLoad, final Point point, final int n, final MapLayerLoadingCallback mapLayerLoadingCallback) {
        if (!this.needsLoading) {
            return;
        }
        this.valid = false;
        if (cancelLoad.cancelled) {
            return;
        }
        this.shapes = new Vector();
        if (this.applet.verboseOutput) {
            System.out.println("Reading " + this.filename);
        }
        ShapeFileReader shapeFileReader = null;
        DBFReader dbfReader = null;
        try {
            if (this.extent.intersects(this.visibleArea)) {
                shapeFileReader = new ShapeFileReader();
                shapeFileReader.open(this.filename);
                dbfReader = new DBFReader();
                dbfReader.open(this.filename);
                final int columnIndex = dbfReader.getColumnIndex(this.activeAttribute);
                int columnIndex2 = -1;
                if (!this.filterValue.equals("")) {
                    columnIndex2 = dbfReader.getColumnIndex(this.filterAttribute);
                }
                for (int recordCount = shapeFileReader.getRecordCount(), i = 0; i < recordCount; ++i) {
                    if (cancelLoad.cancelled) {
                        break;
                    }
                    if (columnIndex2 != -1) {
                        final String column = dbfReader.readColumn(i, columnIndex2);
                        if (column == null) {
                            continue;
                        }
                        if (!column.equals(this.filterValue)) {
                            continue;
                        }
                    }
                    final Rectangle2D.Double double1 = (Rectangle2D.Double)shapeFileReader.getBoundingBox(i);
                    if (double1 != null && this.visibleArea.intersects(double1.x, double1.y, double1.width, double1.height)) {
                        final GeneralPath shape = shapeFileReader.getShape(i, this.visibleArea);
                        if (shape != null) {
                            final ShapefileEntry shapefileEntry = new ShapefileEntry();
                            shapefileEntry.index = i;
                            shapefileEntry.path = shape;
                            shapefileEntry.isLine = false;
                            if (columnIndex != -1) {
                                shapefileEntry.attribute = dbfReader.readColumn(i, columnIndex);
                            }
                            else {
                                shapefileEntry.attribute = null;
                            }
                            this.shapes.addElement(shapefileEntry);
                        }
                    }
                }
            }
            if (!cancelLoad.cancelled) {
                this.valid = true;
            }
        }
        catch (IOException ex) {
            System.out.println("Exception:  I/O Error" + ex.toString());
            ex.printStackTrace();
        }
        finally {
            if (shapeFileReader != null) {
                try {
                    shapeFileReader.close();
                }
                catch (Exception ex2) {}
            }
            if (dbfReader != null) {
                try {
                    dbfReader.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (cancelLoad.cancelled) {
            this.shapes = null;
        }
        else {
            this.needsLoading = false;
        }
        mapLayerLoadingCallback.incrementFileReadCounter();
    }
    
    @Override
    public void clip(final Point point, final int n, final Dimension dimension, final ProjectionTransformation projectionTransformation) {
        if (!this.valid || this.shapes == null) {
            return;
        }
        this.clippedShapes = new Vector();
        final int x = point.x;
        final int y = point.y;
        final double actualPixelSize = this.applet.imgArea.md.actualPixelSize;
        final int size = this.shapes.size();
        final float[] array = new float[6];
        final Rectangle2D.Double double1 = new Rectangle2D.Double(0.0, 0.0, dimension.width, dimension.height);
        for (int i = 0; i < size; ++i) {
            final ShapefileEntry shapefileEntry = this.shapes.elementAt(i);
            final PathIterator pathIterator = shapefileEntry.path.getPathIterator(this.identityTransform);
            boolean isLine = false;
            final GeneralPath path = new GeneralPath();
            while (!pathIterator.isDone()) {
                final int currentSegment = pathIterator.currentSegment(array);
                if (currentSegment == 4) {
                    path.closePath();
                }
                else {
                    final Point latLongToProj = projectionTransformation.latLongToProj(new LatLong(array[1], array[0]));
                    final int n2 = (int)((latLongToProj.x - x) / actualPixelSize);
                    final int n3 = (int)((y - latLongToProj.y) / actualPixelSize);
                    if (currentSegment == 0) {
                        path.moveTo(n2, n3);
                    }
                    else if (currentSegment == 1) {
                        path.lineTo(n2, n3);
                        isLine = true;
                    }
                }
                pathIterator.next();
            }
            boolean b = false;
            if (isLine) {
                b = path.intersects(double1);
            }
            else {
                final PathIterator pathIterator2 = path.getPathIterator(this.identityTransform);
                while (!pathIterator2.isDone()) {
                    pathIterator2.currentSegment(array);
                    b = double1.contains(array[0], array[1]);
                    if (b) {
                        break;
                    }
                    pathIterator2.next();
                }
            }
            if (b) {
                final ShapefileEntry shapefileEntry2 = new ShapefileEntry();
                shapefileEntry2.isLine = isLine;
                shapefileEntry2.path = path;
                shapefileEntry2.attribute = shapefileEntry.attribute;
                shapefileEntry2.index = shapefileEntry.index;
                this.clippedShapes.add(shapefileEntry2);
            }
        }
    }
    
    @Override
    public MapLayerFeatureInfo findFeatureName(final int n, final int n2) {
        this.displayedIndex = -1;
        if (this.clippedShapes == null) {
            return null;
        }
        double area = 1.0E11;
        String name = null;
        int displayedIndex = -1;
        final int size = this.clippedShapes.size();
        final float[] array = new float[6];
        for (int i = 0; i < size; ++i) {
            final ShapefileEntry shapefileEntry = this.clippedShapes.elementAt(i);
            if (shapefileEntry.isLine) {
                if (shapefileEntry.path.contains(n, n2)) {
                    final Rectangle bounds = shapefileEntry.path.getBounds();
                    final double n3 = bounds.width * bounds.height;
                    if (n3 < area) {
                        area = n3;
                        name = shapefileEntry.attribute;
                        displayedIndex = shapefileEntry.index;
                    }
                }
            }
            else {
                final PathIterator pathIterator = shapefileEntry.path.getPathIterator(this.identityTransform);
                while (!pathIterator.isDone()) {
                    if (pathIterator.currentSegment(array) == 0) {
                        final int n4 = (int)array[0];
                        final int n5 = (int)array[1];
                        if (n >= n4 - 3 && n < n4 + 3 && n2 >= n5 - 3 && n2 < n5 + 3) {
                            name = shapefileEntry.attribute;
                            displayedIndex = shapefileEntry.index;
                            area = 1.0;
                            break;
                        }
                    }
                    pathIterator.next();
                }
                if (displayedIndex != -1) {
                    break;
                }
            }
        }
        if ((this.displayedIndex = displayedIndex) != -1) {
            final MapLayerFeatureInfo mapLayerFeatureInfo = new MapLayerFeatureInfo();
            mapLayerFeatureInfo.name = name;
            if (mapLayerFeatureInfo.name == null) {
                mapLayerFeatureInfo.name = this.getName();
            }
            mapLayerFeatureInfo.area = area;
            return mapLayerFeatureInfo;
        }
        return null;
    }
    
    @Override
    public void updateAttributeWindow() {
        if (this.displayedIndex != -1) {
            DBFReader dbfReader = null;
            String[] record = null;
            try {
                dbfReader = new DBFReader();
                dbfReader.open(this.filename);
                record = dbfReader.readRecord(this.displayedIndex);
            }
            catch (IOException ex) {}
            finally {
                if (dbfReader != null) {
                    try {
                        dbfReader.close();
                    }
                    catch (Exception ex2) {}
                }
            }
            if (record != null) {
                this.attributesPanel.setAttributeValues(record);
            }
        }
    }
    
    @Override
    public void draw(final Graphics graphics) {
        if (this.clippedShapes == null) {
            return;
        }
        final int size = this.clippedShapes.size();
        if (size > 0) {
            final Graphics2D graphics2D = (Graphics2D)graphics;
            graphics2D.translate(-1, 1);
            graphics2D.setColor(Color.BLACK);
            graphics2D.setStroke(new BasicStroke(this.lineWidth, 1, 1));
            final float[] array = new float[6];
            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < size; ++j) {
                    final ShapefileEntry shapefileEntry = this.clippedShapes.elementAt(j);
                    if (shapefileEntry.isLine) {
                        graphics2D.draw(shapefileEntry.path);
                    }
                    else {
                        final PathIterator pathIterator = shapefileEntry.path.getPathIterator(this.identityTransform);
                        while (!pathIterator.isDone()) {
                            if (pathIterator.currentSegment(array) == 0) {
                                graphics2D.fillOval((int)array[0] - 3, (int)array[1] - 3, 6, 6);
                            }
                            pathIterator.next();
                        }
                    }
                }
                if (i == 0) {
                    graphics2D.setColor(this.color);
                    graphics2D.translate(1, -1);
                }
            }
        }
    }
    
    class ShapefileEntry
    {
        int index;
        GeneralPath path;
        String attribute;
        boolean isLine;
    }
}
