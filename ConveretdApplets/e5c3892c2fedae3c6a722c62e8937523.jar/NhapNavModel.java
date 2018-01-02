import java.awt.Point;
import java.text.DecimalFormat;

// 
// Decompiled by Procyon v0.5.30
// 

public class NhapNavModel extends NavigationModel
{
    private String modelName;
    private String rowName;
    private String colName;
    private DecimalFormat threeDigitFormat;
    private DecimalFormat twoDigitFormat;
    private int referencePoints;
    
    public NhapNavModel(final int referencePoints) {
        this.modelName = "Nhap";
        this.rowName = "Row";
        this.colName = "Col";
        this.referencePoints = referencePoints;
        this.threeDigitFormat = new DecimalFormat("000");
        this.twoDigitFormat = new DecimalFormat("00");
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
        return 1000;
    }
    
    @Override
    public int getMinimumColumn() {
        return 520;
    }
    
    @Override
    public int getMaximumRow() {
        return this.referencePoints * 50 - 1;
    }
    
    @Override
    public int getMinimumRow() {
        return this.referencePoints * 24;
    }
    
    @Override
    public String getColumnString(final int n) {
        final int n2 = n / 8;
        return "" + this.threeDigitFormat.format(n2) + (n - n2 * 8 + 1);
    }
    
    @Override
    public String getRowString(final int n) {
        final int n2 = n / this.referencePoints;
        return "" + this.twoDigitFormat.format(n2) + this.twoDigitFormat.format(n - (int)(n2 * this.referencePoints + 1.0 / (2.0 * this.referencePoints)) + 1);
    }
    
    @Override
    public int getColumnNumberFromString(final String s) {
        final int n = this.getMinimumColumn() - 1;
        if (s.length() != 4) {
            return n;
        }
        final String substring = s.substring(0, 3);
        final String substring2 = s.substring(3, 4);
        int int1;
        int int2;
        try {
            int1 = Integer.parseInt(substring2);
            int2 = Integer.parseInt(substring);
        }
        catch (NumberFormatException ex) {
            return n;
        }
        return int2 * 8 + (int1 - 1);
    }
    
    @Override
    public int getRowNumberFromString(final String s) {
        final int n = this.getMinimumRow() - 1;
        if (s.length() != 4) {
            return n;
        }
        final String substring = s.substring(0, 2);
        final String substring2 = s.substring(2, 4);
        int int1;
        int int2;
        try {
            int1 = Integer.parseInt(substring2);
            int2 = Integer.parseInt(substring);
        }
        catch (NumberFormatException ex) {
            return n;
        }
        return int2 * this.referencePoints + (int1 - 1);
    }
    
    @Override
    public int getColumnDigits() {
        return 4;
    }
    
    @Override
    public Point latLongToGrid(final double n, final double n2) {
        return new Point((int)Math.round((-n2 - 0.0625) * 8.0), (int)Math.round(n * this.referencePoints));
    }
    
    @Override
    public DoublePoint projToDoubleGrid(final Point point, final ProjectionTransformation projectionTransformation) {
        final LatLong projToLatLong = projectionTransformation.projToLatLong(point.x, point.y);
        return new DoublePoint((-projToLatLong.longitude + 0.0625) * 8.0, projToLatLong.latitude * this.referencePoints);
    }
    
    @Override
    public LatLong gridToLatLong(final int n, final int n2) {
        return new LatLong(n2 / this.referencePoints, -n / 8.0 - 0.0625);
    }
    
    @Override
    public Point gridToProjCoords(final int n, final int n2, final ProjectionTransformation projectionTransformation) {
        return projectionTransformation.latLongToProj(this.gridToLatLong(n, n2));
    }
    
    @Override
    public int checkColumnBounds(int n) {
        final int maximumColumn = this.getMaximumColumn();
        final int minimumColumn = this.getMinimumColumn();
        if (n > maximumColumn) {
            n = maximumColumn;
        }
        else if (n < minimumColumn) {
            n = minimumColumn;
        }
        return n;
    }
    
    @Override
    public int checkRowBounds(int n) {
        final int maximumRow = this.getMaximumRow();
        final int minimumRow = this.getMinimumRow();
        if (n > maximumRow) {
            n = maximumRow;
        }
        else if (n < minimumRow) {
            n = minimumRow;
        }
        return n;
    }
    
    @Override
    public int getRowDownDirection() {
        return -1;
    }
    
    @Override
    public boolean allowColumnWrapAround() {
        return false;
    }
}
