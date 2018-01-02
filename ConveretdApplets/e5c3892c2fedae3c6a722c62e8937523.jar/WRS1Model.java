import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class WRS1Model extends NavigationModel
{
    private String modelName;
    private String rowName;
    private String pathName;
    private ll2PathRow convToWRS1;
    
    WRS1Model() {
        this.modelName = "WRS-1";
        this.rowName = "Row";
        this.pathName = "Path";
        this.convToWRS1 = new ll2PathRow(1, false);
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
        this.convToWRS1.toPathRow(n, n2);
        return new Point(this.convToWRS1.path, this.convToWRS1.row);
    }
    
    @Override
    public DoublePoint projToDoubleGrid(final Point point, final ProjectionTransformation projectionTransformation) {
        final LatLong projToLatLong = projectionTransformation.projToLatLong(point.x, point.y);
        this.convToWRS1.toPathRow(projToLatLong.latitude, projToLatLong.longitude);
        return new DoublePoint(this.convToWRS1.doublePath, this.convToWRS1.doubleRow);
    }
    
    @Override
    public LatLong gridToLatLong(final int n, final int n2) {
        this.convToWRS1.toLatLong(n, n2);
        return new LatLong(this.convToWRS1.latitude, this.convToWRS1.longitude);
    }
    
    @Override
    public Point gridToProjCoords(final int n, final int n2, final ProjectionTransformation projectionTransformation) {
        return projectionTransformation.latLongToProj(this.gridToLatLong(n, n2));
    }
    
    @Override
    public int checkColumnBounds(int i) {
        while (i > 251) {
            i -= 251;
        }
        while (i < 1) {
            i += 251;
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
