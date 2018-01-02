import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class ModisTileModel extends NavigationModel
{
    private String modelName;
    private String rowName;
    private String colName;
    private LatLongToModisTile convToTile;
    
    ModisTileModel() {
        this.modelName = "MODIS";
        this.rowName = "V";
        this.colName = "H";
        this.convToTile = new LatLongToModisTile();
    }
    
    @Override
    public String getModelName() {
        return this.modelName;
    }
    
    @Override
    public String getColName() {
        return this.colName;
    }
    
    @Override
    public String getRowName() {
        return this.rowName;
    }
    
    @Override
    public int getMaximumColumn() {
        return 35;
    }
    
    @Override
    public int getMinimumColumn() {
        return 0;
    }
    
    @Override
    public int getMaximumRow() {
        return 17;
    }
    
    @Override
    public int getMinimumRow() {
        return 0;
    }
    
    @Override
    public Point latLongToGrid(final double n, final double n2) {
        final ModisTile latLongToTile = this.convToTile.latLongToTile(new LatLong(n, n2));
        return new Point(latLongToTile.h, latLongToTile.v);
    }
    
    @Override
    public DoublePoint projToDoubleGrid(final Point point, final ProjectionTransformation projectionTransformation) {
        return this.convToTile.coordinateToDoubleGrid(point.x, point.y);
    }
    
    @Override
    public LatLong gridToLatLong(final int n, final int n2) {
        return this.convToTile.tileToLatLong(new ModisTile(n, n2));
    }
    
    @Override
    public Point gridToProjCoords(final int n, final int n2, final ProjectionTransformation projectionTransformation) {
        return this.convToTile.tileToCoordinate(new ModisTile(n, n2), true);
    }
    
    @Override
    public int checkColumnBounds(int i) {
        while (i > 35) {
            i -= 36;
        }
        while (i < 0) {
            i += 36;
        }
        return i;
    }
    
    @Override
    public int checkRowBounds(int i) {
        while (i > 17) {
            i -= 18;
        }
        while (i < 0) {
            i += 18;
        }
        return i;
    }
    
    @Override
    public int getColumnRightDirection() {
        return 1;
    }
    
    @Override
    public boolean isValidGridCell(final int n, final int n2) {
        return this.convToTile.isValidTile(new ModisTile(n, n2));
    }
}
