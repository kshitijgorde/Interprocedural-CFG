import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class LatLongToModisTile
{
    private ProjectionTransformation projection;
    private double ulGridX;
    private double ulGridY;
    private double gridCellSizeMeters;
    
    LatLongToModisTile() {
        this.ulGridX = -2.0015109354E7;
        this.ulGridY = 1.0007554677E7;
        this.gridCellSizeMeters = 1111950.5196;
        this.projection = CreateProjection.fromProjectionNumber(1010);
    }
    
    public DoublePoint coordinateToDoubleGrid(final int n, final int n2) {
        double n3 = (n - this.ulGridX) / this.gridCellSizeMeters;
        double n4 = (this.ulGridY - n2) / this.gridCellSizeMeters;
        if (n3 < 0.0) {
            n3 = 0.0;
        }
        else if (n3 >= 36.0) {
            n3 = 35.9;
        }
        if (n4 < 0.0) {
            n4 = 0.0;
        }
        else if (n4 >= 18.0) {
            n4 = 17.9;
        }
        return new DoublePoint(n3, n4);
    }
    
    public ModisTile coordinateToTile(final int n, final int n2) {
        return new ModisTile((int)((n - this.ulGridX) / this.gridCellSizeMeters), (int)((this.ulGridY - n2) / this.gridCellSizeMeters));
    }
    
    public ModisTile latLongToTile(final LatLong latLong) {
        final Point latLongToProj = this.projection.latLongToProj(latLong);
        return this.coordinateToTile(latLongToProj.x, latLongToProj.y);
    }
    
    public Point tileToCoordinate(final ModisTile modisTile, final boolean b) {
        double n = 0.0;
        if (b) {
            n = 0.5;
        }
        return new Point((int)Math.round(this.ulGridX + (modisTile.h + n) * this.gridCellSizeMeters), (int)Math.round(this.ulGridY - (modisTile.v + n) * this.gridCellSizeMeters));
    }
    
    public LatLong tileToLatLong(final ModisTile modisTile) {
        final Point tileToCoordinate = this.tileToCoordinate(modisTile, true);
        return this.projection.projToLatLong(tileToCoordinate.x, tileToCoordinate.y);
    }
    
    public boolean isValidTile(final ModisTile modisTile) {
        final ModisTile modisTile2 = new ModisTile(modisTile.h, modisTile.v);
        if (modisTile2.v <= 8) {
            final ModisTile modisTile3 = modisTile2;
            ++modisTile3.v;
        }
        if (modisTile2.h <= 17) {
            final ModisTile modisTile4 = modisTile2;
            ++modisTile4.h;
        }
        final Point tileToCoordinate = this.tileToCoordinate(modisTile2, false);
        LatLong projToLatLong = this.projection.projToLatLong(tileToCoordinate.x, tileToCoordinate.y);
        if ((modisTile.h == 8 && modisTile.v == 2) || (modisTile.h == 8 && modisTile.v == 15) || (modisTile.h == 27 && modisTile.v == 2) || (modisTile.h == 27 && modisTile.v == 15)) {
            projToLatLong = null;
        }
        return projToLatLong != null;
    }
}
