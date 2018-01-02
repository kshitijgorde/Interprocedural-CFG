import java.awt.Polygon;
import java.awt.Point;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

public class MosaicCoords
{
    private Dimension mosaicPixels;
    private Point mosaicUL;
    private boolean mosaicULValid;
    private Point displayedUL;
    private boolean displayedULValid;
    private Polygon cellViewport;
    private Dimension viewportPixels;
    private int[] tempX;
    private int[] tempY;
    
    public Dimension getMosaicPixels() {
        return this.mosaicPixels;
    }
    
    public Dimension getViewportPixels() {
        return this.viewportPixels;
    }
    
    MosaicCoords() {
        this.mosaicUL = new Point(0, 0);
        this.displayedUL = new Point(0, 0);
        this.tempX = new int[4];
        this.tempY = new int[4];
        this.viewportPixels = new Dimension(300, 300);
    }
    
    public void update(final imgViewer imgViewer, final TOC[] array, final Sensor sensor, final Dimension dimension, final ProjectionTransformation projectionTransformation, final int n, final int n2, final double n3, final int n4, final int n5) {
        this.mosaicULValid = false;
        this.displayedULValid = false;
        this.calculateMosaicPixelsAndLocation(array, sensor, projectionTransformation);
        this.mosaicULValid = true;
        final int width = this.mosaicPixels.width;
        final int height = this.mosaicPixels.height;
        Math.max(width, dimension.width);
        Math.max(height, dimension.height);
        this.calcCellViewport(imgViewer, array, projectionTransformation);
        if (n == 1) {
            this.adjustViewport(imgViewer, array, projectionTransformation, n3, n4, n5);
        }
        else if (n == 0) {
            final TOC toc = array[n2];
            if (toc.valid) {
                final Metadata metadata = toc.scenes[toc.currentDateIndex];
                this.updateDisplayedUL(metadata.ulX, metadata.ulY, toc.valid);
            }
            else {
                final Point centerProjCoords;
                final Point point = centerProjCoords = toc.getCenterProjCoords(projectionTransformation);
                centerProjCoords.x -= (int)(300.0 * n3);
                final Point point2 = point;
                point2.y += (int)(300.0 * n3);
                this.updateDisplayedUL(point.x, point.y, true);
            }
        }
        else {
            this.updateDisplayedUL(this.mosaicUL.x, this.mosaicUL.y, this.mosaicULValid);
        }
        this.calcScreenLocations(array, sensor, n3);
    }
    
    private void calcCellViewport(final imgViewer imgViewer, final TOC[] array, final ProjectionTransformation projectionTransformation) {
        final TOC toc = array[imgViewer.md.getMosaicCenterIndex()];
        this.tempX[0] = toc.maxX;
        this.tempY[0] = toc.maxY;
        this.tempX[1] = toc.minX;
        this.tempY[1] = toc.maxY;
        this.tempX[2] = toc.minX;
        this.tempY[2] = toc.minY;
        this.tempX[3] = toc.maxX;
        this.tempY[3] = toc.minY;
        this.cellViewport = new Polygon(this.tempX, this.tempY, 4);
    }
    
    private void adjustViewport(final imgViewer imgViewer, final TOC[] array, final ProjectionTransformation projectionTransformation, final double n, final int n2, final int n3) {
        final Polygon cellViewport = this.cellViewport;
        final Point centerProjCoords = array[imgViewer.md.getMosaicCenterIndex()].getCenterProjCoords(projectionTransformation);
        final int n4 = 0;
        final int n5 = 0;
        final int mosaicHeight = imgViewer.md.getMosaicHeight();
        int mosaicCenterIndex;
        final int n6 = mosaicCenterIndex = imgViewer.md.getMosaicCenterIndex();
        if (n2 < 0) {
            mosaicCenterIndex += mosaicHeight;
        }
        else if (n2 > 0) {
            mosaicCenterIndex -= mosaicHeight;
        }
        final Point centerProjCoords2 = array[mosaicCenterIndex].getCenterProjCoords(projectionTransformation);
        final int n7 = -(centerProjCoords.x - centerProjCoords2.x) * Math.abs(n2) / 3;
        final int n8 = (centerProjCoords.y - centerProjCoords2.y) * Math.abs(n2) / 3;
        final int n9 = n4 + n7;
        final int n10 = n5 + n8;
        final int gridRow = array[n6].gridRow;
        final NavigationModel navModel = imgViewer.sensorMenu.getCurrentSensor().navModel;
        int n11 = n6;
        if (n3 < 0 && gridRow > navModel.getMinimumRow()) {
            --n11;
        }
        else if (n3 > 0 && gridRow < navModel.getMaximumRow()) {
            ++n11;
        }
        final Point centerProjCoords3 = array[n11].getCenterProjCoords(projectionTransformation);
        final int n12 = -(centerProjCoords.x - centerProjCoords3.x) * Math.abs(n3) / 3;
        final int n13 = (centerProjCoords.y - centerProjCoords3.y) * Math.abs(n3) / 3;
        final int n14 = n9 + n12;
        final int n15 = n10 + n13;
        final int[] array2 = new int[4];
        final int[] array3 = new int[4];
        for (int i = 0; i < 4; ++i) {
            array2[i] = cellViewport.xpoints[i] + n14;
            array3[i] = cellViewport.ypoints[i] - n15;
        }
        final Polygon polygon = new Polygon(array2, array3, 4);
        for (int j = 0; j < array.length; ++j) {
            array[j].filterScenesToViewport(polygon);
        }
        int n16 = array2[0];
        int n17 = array2[0];
        int n18 = array3[0];
        int n19 = array3[0];
        for (int k = 1; k < 4; ++k) {
            if (array2[k] < n16) {
                n16 = array2[k];
            }
            if (array2[k] > n17) {
                n17 = array2[k];
            }
            if (array3[k] < n18) {
                n18 = array3[k];
            }
            if (array3[k] > n19) {
                n19 = array3[k];
            }
        }
        final Dimension maxSceneSize = array[imgViewer.md.getMosaicCenterIndex()].maxSceneSize;
        this.updateDisplayedUL(n16 - maxSceneSize.width / 2, n19 + maxSceneSize.height / 2, true);
        this.viewportPixels = new Dimension((int)Math.round((n17 - n16 + maxSceneSize.width) / n), (int)Math.round((n19 - n18 + maxSceneSize.height) / n));
    }
    
    private void calculateMosaicPixelsAndLocation(final TOC[] array, final Sensor sensor, final ProjectionTransformation projectionTransformation) {
        this.mosaicUL.x = 100000000;
        this.mosaicUL.y = -100000000;
        for (int i = 0; i < array.length; ++i) {
            final TOC toc = array[i];
            if (toc.valid) {
                if (toc.minX < this.mosaicUL.x) {
                    this.mosaicUL.x = toc.minX;
                }
                if (toc.maxY > this.mosaicUL.y) {
                    this.mosaicUL.y = toc.maxY;
                }
            }
        }
        final double lowestResolution = sensor.getLowestResolution();
        int x = 100000000;
        int x2 = -100000000;
        int y = 100000000;
        int y2 = -100000000;
        for (int j = 0; j < array.length; ++j) {
            final Point centerProjCoords = array[j].getCenterProjCoords(projectionTransformation);
            if (centerProjCoords.x < x) {
                x = centerProjCoords.x;
            }
            if (centerProjCoords.x > x2) {
                x2 = centerProjCoords.x;
            }
            if (centerProjCoords.y < y) {
                y = centerProjCoords.y;
            }
            if (centerProjCoords.y > y2) {
                y2 = centerProjCoords.y;
            }
        }
        final int n = (int)((y2 - y) / lowestResolution) * 3 / 2;
        int max = (int)((x2 - x) / lowestResolution) * 3 / 2;
        int max2 = n;
        final int x3 = x - (x2 - x) / 4;
        final int y3 = y2 + (y2 - y) / 4;
        if (y3 > this.mosaicUL.y) {
            this.mosaicUL.y = y3;
        }
        if (x3 < this.mosaicUL.x) {
            this.mosaicUL.x = x3;
        }
        for (int k = 0; k < array.length; ++k) {
            final TOC toc2 = array[k];
            if (toc2.valid) {
                final int n2 = (int)Math.round((this.mosaicUL.y - toc2.minY) / lowestResolution);
                max = Math.max((int)Math.round((toc2.maxX - this.mosaicUL.x) / lowestResolution), max);
                max2 = Math.max(n2, max2);
            }
        }
        this.mosaicPixels = new Dimension(max, max2);
    }
    
    private void calcScreenLocations(final TOC[] array, final Sensor sensor, final double n) {
        for (int i = 0; i < array.length; ++i) {
            final TOC toc = array[i];
            if (toc.valid) {
                final double offsetResolution = sensor.getOffsetResolution();
                for (int j = 0; j < toc.numImg; ++j) {
                    final Metadata metadata = toc.scenes[j];
                    final int n2 = metadata.ulX - this.displayedUL.x;
                    final int n3 = this.displayedUL.y - metadata.ulY;
                    for (int k = 0; k < 4; ++k) {
                        this.tempX[k] = (int)Math.round((n2 + metadata.sampOffset[k] * offsetResolution) / n);
                        this.tempY[k] = (int)Math.round((n3 + metadata.lineOffset[k] * offsetResolution) / n);
                    }
                    metadata.screenLocation = new Polygon(this.tempX, this.tempY, 4);
                }
            }
        }
    }
    
    public Point getUpperLeftCorner() {
        if (this.displayedULValid) {
            return new Point(this.displayedUL);
        }
        return null;
    }
    
    public void updateDisplayedUL(final int x, final int y, final boolean b) {
        this.displayedUL.x = x;
        this.displayedUL.y = y;
        if (b) {
            this.displayedULValid = true;
        }
        else {
            this.displayedULValid = false;
        }
    }
    
    public void invalidate() {
        this.displayedULValid = false;
    }
}
