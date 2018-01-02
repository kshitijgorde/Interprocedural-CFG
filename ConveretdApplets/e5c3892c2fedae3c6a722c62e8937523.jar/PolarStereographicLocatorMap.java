import java.awt.Point;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

public class PolarStereographicLocatorMap extends LocatorMapImpl
{
    private imgViewer applet;
    private ProjectionTransformation projTran;
    private final int GRID_CELL_SIZE = 120000;
    
    PolarStereographicLocatorMap(final imgViewer applet) {
        this.applet = applet;
        this.projTran = CreateProjection.fromProjectionNumber(1012);
        this.mapImage = applet.getImage(applet.getCodeBase(), "graphics/antarctica.jpg");
        this.useBoundaryImage = false;
        this.imageSize = new Dimension(443, 442);
    }
    
    @Override
    public Point latLongToPixel(final LatLong latLong) {
        return this.projToPixel(this.projTran.latLongToProj(latLong));
    }
    
    @Override
    public Point gridToPixel(final int n, final int n2) {
        return this.projToPixel(new Point(n * 120000, n2 * 120000));
    }
    
    private Point projToPixel(final Point point) {
        return new Point((int)((point.x + 3360000.0) / 15169.300225733634), (int)((3360000.0 - point.y) / 15203.619909502262));
    }
    
    private Point pixelToProj(final int n, final int n2) {
        return new Point((int)(-3360000.0 + n * 15169.300225733634), (int)(3360000.0 - n2 * 15203.619909502262));
    }
    
    @Override
    public LatLong pixelToLatLong(final int n, final int n2) {
        final Point pixelToProj = this.pixelToProj(n, n2);
        return this.projTran.projToLatLong(pixelToProj.x, pixelToProj.y);
    }
    
    @Override
    public Point pixelToGrid(final int n, final int n2) {
        final Point pixelToProj = this.pixelToProj(n, n2);
        return new Point(Math.round(pixelToProj.x / 120000), Math.round(pixelToProj.y / 120000));
    }
    
    @Override
    public void moveTo(final int n, final int n2) {
        final Point pixelToGrid = this.pixelToGrid(n, n2);
        this.applet.imgArea.md.scrollData(pixelToGrid.x, pixelToGrid.y, 0, 0, false, true, false);
    }
}
