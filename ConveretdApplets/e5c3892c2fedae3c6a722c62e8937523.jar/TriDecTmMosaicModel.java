import java.awt.Point;
import java.text.DecimalFormat;

// 
// Decompiled by Procyon v0.5.30
// 

public class TriDecTmMosaicModel extends NavigationModel
{
    private String modelName;
    private String rowName;
    private String colName;
    private DecimalFormat twoDigitFormat;
    
    public TriDecTmMosaicModel() {
        this.modelName = "Grid";
        this.rowName = "Row";
        this.colName = "Col";
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
        return 59;
    }
    
    @Override
    public int getMinimumColumn() {
        return 0;
    }
    
    @Override
    public int getMaximumRow() {
        return 35;
    }
    
    @Override
    public int getMinimumRow() {
        return 0;
    }
    
    @Override
    public String getColumnString(final int n) {
        return this.twoDigitFormat.format(n + 1);
    }
    
    @Override
    public String getRowString(final int n) {
        int n2 = n - 18;
        String s;
        if (n2 < 0) {
            s = "S";
            n2 = -n2 - 1;
        }
        else {
            s = "N";
        }
        return s + this.twoDigitFormat.format(n2 * 5);
    }
    
    @Override
    public int getColumnNumberFromString(final String s) {
        final int n = this.getMinimumColumn() - 1;
        if (s.length() != 2) {
            return n;
        }
        final String substring = s.substring(0, 2);
        int int1;
        try {
            int1 = Integer.parseInt(substring);
        }
        catch (NumberFormatException ex) {
            return n;
        }
        return int1 - 1;
    }
    
    @Override
    public int getRowNumberFromString(final String s) {
        final int n = this.getMinimumRow() - 1;
        if (s.length() != 3) {
            return n;
        }
        final String substring = s.substring(0, 1);
        final String substring2 = s.substring(1, 3);
        boolean b = false;
        if (substring.equalsIgnoreCase("N")) {
            b = true;
        }
        else if (!substring.equalsIgnoreCase("S")) {
            return n;
        }
        int int1;
        try {
            int1 = Integer.parseInt(substring2);
        }
        catch (NumberFormatException ex) {
            return n;
        }
        if (!b) {
            int1 = -int1;
        }
        else {
            int1 += 5;
        }
        return (int1 + 85) / 5;
    }
    
    @Override
    public int getColumnDigits() {
        return 2;
    }
    
    @Override
    public int getRowDigits() {
        return 3;
    }
    
    private int getUtmZoneFromLongitude(final double n) {
        return (int)(Math.abs(-180.0 - n) / 6.0) + 1;
    }
    
    private int getLongitudeFromUtmZone(final int n) {
        return (n - 1) * 6 - 180 + 3;
    }
    
    @Override
    public Point latLongToGrid(double n, final double n2) {
        final int utmZoneFromLongitude = this.getUtmZoneFromLongitude(n2);
        this.getLongitudeFromUtmZone(utmZoneFromLongitude);
        final int n3 = utmZoneFromLongitude - 1;
        n += 90.0;
        return new Point(n3, (int)(n / 5.0));
    }
    
    @Override
    public DoublePoint projToDoubleGrid(final Point point, final ProjectionTransformation projectionTransformation) {
        final LatLong projToLatLong = projectionTransformation.projToLatLong(point.x, point.y);
        final int utmZoneFromLongitude = this.getUtmZoneFromLongitude(projToLatLong.longitude);
        final double n = utmZoneFromLongitude - 1 + (projToLatLong.longitude - (this.getLongitudeFromUtmZone(utmZoneFromLongitude) - 3.0)) / 6.0;
        final LatLong latLong = projToLatLong;
        latLong.latitude += 90.0;
        final double n2 = (int)(projToLatLong.latitude / 5.0);
        return new DoublePoint(n, n2 + (projToLatLong.latitude - (int)n2 * 5) / 5.0);
    }
    
    @Override
    public LatLong gridToLatLong(final int n, final int n2) {
        return new LatLong(n2 * 5.0 - 90.0 + 2.5, n * 6.0 + 3.0 - 180.0);
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
            n -= maximumColumn + 1;
        }
        else if (n < minimumColumn) {
            n += maximumColumn + 1;
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
    public int getColumnRightDirection() {
        return 1;
    }
    
    @Override
    public boolean allowColumnWrapAround() {
        return true;
    }
}
