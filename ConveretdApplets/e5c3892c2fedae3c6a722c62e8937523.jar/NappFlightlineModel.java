import java.awt.Point;
import java.text.DecimalFormat;

// 
// Decompiled by Procyon v0.5.30
// 

public class NappFlightlineModel extends NavigationModel
{
    private String modelName;
    private String rowName;
    private String colName;
    private DecimalFormat threeDigitFormat;
    
    public NappFlightlineModel() {
        this.modelName = "Flight";
        this.rowName = "Stn";
        this.colName = "Line";
        this.threeDigitFormat = new DecimalFormat("000");
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
        return 2000;
    }
    
    @Override
    public int getMinimumColumn() {
        return 1040;
    }
    
    @Override
    public int getMaximumRow() {
        return 833;
    }
    
    @Override
    public int getMinimumRow() {
        return 1;
    }
    
    @Override
    public String getColumnString(final int n) {
        final int n2 = n / 16;
        final int n3 = n - n2 * 16;
        char c = 'E';
        if ((n3 & 0x1) == 0x1) {
            c = 'W';
        }
        int n4 = n3 / 2;
        ++n4;
        return "" + this.threeDigitFormat.format(n2) + n4 + c;
    }
    
    @Override
    public String getRowString(final int n) {
        return "" + n;
    }
    
    @Override
    public int getColumnNumberFromString(final String s) {
        final int n = this.getMinimumColumn() - 1;
        if (s.length() != 5) {
            return n;
        }
        final String substring = s.substring(4, 5);
        final String substring2 = s.substring(3, 4);
        final String substring3 = s.substring(0, 3);
        int n2 = 0;
        if (substring.equalsIgnoreCase("W")) {
            n2 = 1;
        }
        else if (!substring.equalsIgnoreCase("E")) {
            return n;
        }
        int int1;
        int int2;
        try {
            int1 = Integer.parseInt(substring2);
            int2 = Integer.parseInt(substring3);
        }
        catch (NumberFormatException ex) {
            return n;
        }
        return int2 * 16 + (int1 - 1) * 2 + n2;
    }
    
    @Override
    public int getRowNumberFromString(final String s) {
        final int n = this.getMinimumRow() - 1;
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            return n;
        }
        return int1;
    }
    
    @Override
    public int getColumnDigits() {
        return 5;
    }
    
    @Override
    public Point latLongToGrid(final double n, final double n2) {
        return new Point((int)Math.round((-n2 - 0.03125) * 16.0), (int)Math.round(n * 32.0 - 767.0));
    }
    
    @Override
    public DoublePoint projToDoubleGrid(final Point point, final ProjectionTransformation projectionTransformation) {
        final LatLong projToLatLong = projectionTransformation.projToLatLong(point.x, point.y);
        return new DoublePoint((-projToLatLong.longitude - 0.03125) * 16.0, projToLatLong.latitude * 32.0 - 767.0);
    }
    
    @Override
    public LatLong gridToLatLong(final int n, final int n2) {
        final double n3 = (n2 + 767.0) / 32.0;
        double n4 = -(n / 16.0 + 0.03125);
        if (n4 < -180.0) {
            n4 += 360.0;
        }
        return new LatLong(n3, n4);
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
