import java.awt.Point;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

public class SinusoidalLocatorMap extends LocatorMapImpl
{
    private imgViewer applet;
    private LatLongToModisTile modisTileConversion;
    private ProjectionTransformation projTran;
    
    SinusoidalLocatorMap(final imgViewer applet) {
        this.applet = applet;
        this.modisTileConversion = new LatLongToModisTile();
        this.projTran = CreateProjection.fromProjectionNumber(1010);
        this.mapImage = applet.getImage(applet.getCodeBase(), "graphics/modistiles.jpg");
        this.useBoundaryImage = false;
        this.imageSize = new Dimension(503, 251);
    }
    
    @Override
    public Point latLongToPixel(final LatLong latLong) {
        final ModisTile latLongToTile = this.modisTileConversion.latLongToTile(latLong);
        return this.gridToPixel(latLongToTile.h, latLongToTile.v);
    }
    
    @Override
    public Point gridToPixel(final int n, final int n2) {
        final Point tileToCoordinate = this.modisTileConversion.tileToCoordinate(new ModisTile(n, n2), true);
        return new Point((int)((tileToCoordinate.x + 2.0015109354E7) / 79582.93977256461), (int)((1.0007554677E7 - tileToCoordinate.y) / 79741.47152509961));
    }
    
    private Point pixelToProj(final int n, final int n2) {
        return new Point((int)(-2.0015109354E7 + n * 79582.93977256461), (int)(1.0007554677E7 - n2 * 79741.47152509961));
    }
    
    @Override
    public LatLong pixelToLatLong(final int n, final int n2) {
        final Point pixelToProj = this.pixelToProj(n, n2);
        return this.projTran.projToLatLong(pixelToProj.x, pixelToProj.y);
    }
    
    @Override
    public Point pixelToGrid(final int n, final int n2) {
        final Point pixelToProj = this.pixelToProj(n, n2);
        final ModisTile coordinateToTile = this.modisTileConversion.coordinateToTile(pixelToProj.x, pixelToProj.y);
        return new Point(coordinateToTile.h, coordinateToTile.v);
    }
    
    @Override
    public void moveTo(final int n, final int n2) {
        final Point pixelToProj = this.pixelToProj(n, n2);
        final ModisTile coordinateToTile = this.modisTileConversion.coordinateToTile(pixelToProj.x, pixelToProj.y);
        if (this.modisTileConversion.isValidTile(coordinateToTile)) {
            this.applet.imgArea.md.scrollData(coordinateToTile.h, coordinateToTile.v, 0, 0, false, true, false);
        }
        else {
            this.applet.statusBar.showStatus("Selected tile does not contain data!");
        }
    }
}
