import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class NavigationModel
{
    public abstract String getModelName();
    
    public abstract String getColName();
    
    public abstract String getRowName();
    
    public abstract Point latLongToGrid(final double p0, final double p1);
    
    public abstract DoublePoint projToDoubleGrid(final Point p0, final ProjectionTransformation p1);
    
    public abstract LatLong gridToLatLong(final int p0, final int p1);
    
    public abstract Point gridToProjCoords(final int p0, final int p1, final ProjectionTransformation p2);
    
    public abstract int checkColumnBounds(final int p0);
    
    public abstract int checkRowBounds(final int p0);
    
    public abstract int getMinimumColumn();
    
    public abstract int getMaximumColumn();
    
    public abstract int getMinimumRow();
    
    public abstract int getMaximumRow();
    
    public String getColumnString(final int n) {
        return "" + n;
    }
    
    public String getRowString(final int n) {
        return "" + n;
    }
    
    public int getColumnNumberFromString(final String s) {
        return Integer.parseInt(s);
    }
    
    public int getRowNumberFromString(final String s) {
        return Integer.parseInt(s);
    }
    
    public int getColumnDigits() {
        return 3;
    }
    
    public int getRowDigits() {
        return 3;
    }
    
    public int getRowDownDirection() {
        return 1;
    }
    
    public int getColumnRightDirection() {
        return -1;
    }
    
    public boolean isValidGridCell(final int n, final int n2) {
        return true;
    }
    
    public boolean allowColumnWrapAround() {
        return false;
    }
    
    public boolean allowRowWrapAround() {
        return false;
    }
}
