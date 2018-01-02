import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class ModisTileMapLayer extends MapLayer
{
    private imgViewer applet;
    private Point[] horizontalLines;
    private Point[] verticalLines;
    private LatLongToModisTile tileConv;
    private Dimension dispSize;
    private boolean limitLeft;
    private boolean limitRight;
    private boolean limitTop;
    private boolean limitBottom;
    
    public ModisTileMapLayer(final imgViewer applet, final Color color, final int n) {
        super(applet.imgArea, "MODIS Tiles", color, n, true);
        this.applet = applet;
        this.tileConv = new LatLongToModisTile();
    }
    
    @Override
    public int setDisplayAreaUsingLatLong(final LatLong latLong, final LatLong latLong2, final int n) {
        return 0;
    }
    
    @Override
    public int setDisplayAreaUsingProjCoords(final Point point, final Point point2, final int n) {
        return 0;
    }
    
    @Override
    public void read(final CancelLoad cancelLoad, final Point point, final int n, final MapLayerLoadingCallback mapLayerLoadingCallback) {
    }
    
    @Override
    public void clip(final Point point, final int n, final Dimension dimension, final ProjectionTransformation projectionTransformation) {
        final double actualPixelSize = this.applet.imgArea.md.actualPixelSize;
        this.dispSize = new Dimension(dimension);
        final Point point2 = new Point((int)(point.x + dimension.width * actualPixelSize), (int)(point.y - dimension.height * actualPixelSize));
        final ModisTile coordinateToTile = this.tileConv.coordinateToTile(point.x, point.y);
        final ModisTile coordinateToTile2 = this.tileConv.coordinateToTile(point2.x, point2.y);
        final Point tileToCoordinate = this.tileConv.tileToCoordinate(coordinateToTile, false);
        if (tileToCoordinate.x < point.x) {
            final ModisTile modisTile = coordinateToTile;
            ++modisTile.h;
        }
        if (tileToCoordinate.y > point.y) {
            final ModisTile modisTile2 = coordinateToTile;
            ++modisTile2.v;
        }
        this.limitLeft = false;
        this.limitRight = false;
        this.limitTop = false;
        this.limitBottom = false;
        if (coordinateToTile.h <= 0) {
            coordinateToTile.h = 0;
            this.limitLeft = true;
        }
        else if (coordinateToTile.h > 36) {
            coordinateToTile.h = 36;
        }
        if (coordinateToTile.v <= 0) {
            coordinateToTile.v = 0;
            this.limitTop = true;
        }
        else if (coordinateToTile.v > 18) {
            coordinateToTile.v = 18;
        }
        if (coordinateToTile2.h < 0) {
            coordinateToTile2.h = 0;
        }
        else if (coordinateToTile2.h >= 36) {
            coordinateToTile2.h = 36;
            this.limitRight = true;
        }
        if (coordinateToTile2.v < 0) {
            coordinateToTile2.v = 0;
        }
        else if (coordinateToTile2.v >= 18) {
            coordinateToTile2.v = 18;
            this.limitBottom = true;
        }
        this.horizontalLines = new Point[coordinateToTile2.v - coordinateToTile.v + 1];
        this.verticalLines = new Point[coordinateToTile2.h - coordinateToTile.h + 1];
        final ModisTile modisTile3 = new ModisTile(coordinateToTile.h, coordinateToTile.v);
        for (int i = coordinateToTile.v; i <= coordinateToTile2.v; ++i) {
            modisTile3.v = i;
            this.horizontalLines[i - coordinateToTile.v] = this.tileConv.tileToCoordinate(modisTile3, false);
        }
        modisTile3.v = coordinateToTile.v;
        for (int j = coordinateToTile.h; j <= coordinateToTile2.h; ++j) {
            modisTile3.h = j;
            this.verticalLines[j - coordinateToTile.h] = this.tileConv.tileToCoordinate(modisTile3, false);
        }
        for (int k = 0; k < this.horizontalLines.length; ++k) {
            this.horizontalLines[k].x = (int)((this.horizontalLines[k].x - point.x) / actualPixelSize);
            this.horizontalLines[k].y = (int)((point.y - this.horizontalLines[k].y) / actualPixelSize);
        }
        for (int l = 0; l < this.verticalLines.length; ++l) {
            this.verticalLines[l].x = (int)((this.verticalLines[l].x - point.x) / actualPixelSize);
            this.verticalLines[l].y = (int)((point.y - this.verticalLines[l].y) / actualPixelSize);
        }
    }
    
    @Override
    public void draw(final Graphics graphics) {
        if (this.dispSize == null) {
            return;
        }
        int x = 0;
        int n = this.dispSize.width;
        if (this.limitLeft) {
            x = this.verticalLines[0].x;
        }
        if (this.limitRight) {
            n = this.verticalLines[this.verticalLines.length - 1].x;
        }
        for (int i = 0; i < this.horizontalLines.length; ++i) {
            final int y = this.horizontalLines[i].y;
            graphics.setColor(this.color);
            graphics.drawLine(x, y, n, y);
        }
        int y2 = 0;
        int n2 = this.dispSize.height;
        if (this.limitTop) {
            y2 = this.horizontalLines[0].y;
        }
        if (this.limitBottom) {
            n2 = this.horizontalLines[this.horizontalLines.length - 1].y;
        }
        for (int j = 0; j < this.verticalLines.length; ++j) {
            final int x2 = this.verticalLines[j].x;
            graphics.setColor(this.color);
            graphics.drawLine(x2, y2, x2, n2);
        }
    }
}
