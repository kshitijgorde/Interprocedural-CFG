import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class WRS2Model extends NavigationModel
{
    private String modelName;
    private String rowName;
    private String pathName;
    private ll2PathRow convToWRS2;
    
    WRS2Model() {
        this.modelName = "WRS-2";
        this.rowName = "Row";
        this.pathName = "Path";
        this.convToWRS2 = new ll2PathRow(2, false);
    }
    
    protected WRS2Model(final boolean b) {
        this.modelName = "WRS-2";
        this.rowName = "Row";
        this.pathName = "Path";
        this.convToWRS2 = new ll2PathRow(2, b);
    }
    
    @Override
    public String getModelName() {
        return this.modelName;
    }
    
    @Override
    public String getColName() {
        return this.pathName;
    }
    
    @Override
    public String getRowName() {
        return this.rowName;
    }
    
    @Override
    public int getMaximumColumn() {
        return 233;
    }
    
    @Override
    public int getMinimumColumn() {
        return 1;
    }
    
    @Override
    public int getMaximumRow() {
        return 124;
    }
    
    @Override
    public int getMinimumRow() {
        return 1;
    }
    
    @Override
    public Point latLongToGrid(final double n, final double n2) {
        this.convToWRS2.toPathRow(n, n2);
        return new Point(this.convToWRS2.path, this.convToWRS2.row);
    }
    
    @Override
    public DoublePoint projToDoubleGrid(final Point point, final ProjectionTransformation projectionTransformation) {
        final LatLong projToLatLong = projectionTransformation.projToLatLong(point.x, point.y);
        this.convToWRS2.toPathRow(projToLatLong.latitude, projToLatLong.longitude);
        return new DoublePoint(this.convToWRS2.doublePath, this.convToWRS2.doubleRow);
    }
    
    @Override
    public LatLong gridToLatLong(final int n, final int n2) {
        this.convToWRS2.toLatLong(n, n2);
        return new LatLong(this.convToWRS2.latitude, this.convToWRS2.longitude);
    }
    
    @Override
    public Point gridToProjCoords(final int n, final int n2, final ProjectionTransformation projectionTransformation) {
        return projectionTransformation.latLongToProj(this.gridToLatLong(n, n2));
    }
    
    @Override
    public int checkColumnBounds(int i) {
        while (i > 233) {
            i -= 233;
        }
        while (i < 1) {
            i += 233;
        }
        return i;
    }
    
    @Override
    public int checkRowBounds(int i) {
        while (i > 124) {
            i -= 124;
        }
        while (i < 1) {
            i += 124;
        }
        return i;
    }
    
    @Override
    public boolean allowColumnWrapAround() {
        return true;
    }
}
